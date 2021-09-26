/******************************************************************************
 * Product: JPiere                                                            *
 * Copyright (C) Hideaki Hagiwara (h.hagiwara@oss-erp.co.jp)                  *
 *                                                                            *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY.                          *
 * See the GNU General Public License for more details.                       *
 *                                                                            *
 * JPiere is maintained by OSS ERP Solutions Co., Ltd.                        *
 * (http://www.oss-erp.co.jp)                                                 *
 *****************************************************************************/

package custom.contract.jpiere.base.plugin.org.adempiere.process;

import java.util.ArrayList;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MDocType;
import org.compiere.model.MInvoice;
import org.compiere.model.MInvoiceLine;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.MProduct;
import org.compiere.model.MUOM;
import org.compiere.model.PO;
import org.compiere.process.DocAction;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Util;

import custom.contract.jpiere.base.plugin.org.adempiere.model.MContract;
import custom.contract.jpiere.base.plugin.org.adempiere.model.MContractAcct;
import custom.contract.jpiere.base.plugin.org.adempiere.model.MContractLine;
import custom.contract.jpiere.base.plugin.org.adempiere.model.MContractLogDetail;
import custom.contract.jpiere.base.plugin.org.adempiere.model.MContractProcPeriod;

/**
* JPIERE-0363
*
* @author Hideaki Hagiwara
*
*/
public class DefaultContractProcessCreateDerivativeInvoice extends AbstractContractProcess {

	ArrayList<MOrderLine> overQtyOrderedLineList = new ArrayList<MOrderLine>();

	@Override
	protected void prepare()
	{

		super.prepare();
	}

	@Override
	protected String doIt() throws Exception
	{
		super.doIt();

		int JP_ContractProcPeriod_ID = 0;
		if(m_ContractContent.getParent().getJP_ContractType().equals(MContract.JP_CONTRACTTYPE_PeriodContract))
			JP_ContractProcPeriod_ID = getJP_ContractProctPeriod_ID();

		if(m_ContractContent.getParent().getJP_ContractType().equals(MContract.JP_CONTRACTTYPE_PeriodContract)
				&& JP_ContractProcPeriod_ID == 0)
		{
			String descriptionMsg = Msg.getMsg(getCtx(), "NotFound") + " : " + Msg.getElement(getCtx(), "JP_ContractProcPeriod_ID");
			createContractLogDetail(MContractLogDetail.JP_CONTRACTLOGMSG_UnexpectedError, null,  null, descriptionMsg);
			return "";
		}

		MContractProcPeriod orderProcPeriod = getBaseDocContractProcPeriodFromDerivativeDocContractProcPeriod(JP_ContractProcPeriod_ID);
		if(m_ContractContent.getParent().getJP_ContractType().equals(MContract.JP_CONTRACTTYPE_PeriodContract)
				&& orderProcPeriod == null)
		{
			String descriptionMsg = Msg.getMsg(getCtx(), "NotFound") + " : " + Msg.getElement(getCtx(), "JP_ContractProcPeriod_ID");
			createContractLogDetail(MContractLogDetail.JP_CONTRACTLOGMSG_UnexpectedError, null,  null, descriptionMsg);
			return "";
		}

		//Check Contract Acct Info
		int JP_Contract_Acct_ID = m_ContractContent.getJP_Contract_Acct_ID();
		if(JP_Contract_Acct_ID > 0)
		{
			MContractAcct contractAcct = MContractAcct.get(getCtx(), JP_Contract_Acct_ID);
			if(contractAcct.isPostingContractAcctJP() && contractAcct.isPostingRecognitionDocJP()
					&& contractAcct.getJP_RecogToInvoicePolicy() != null && !contractAcct.getJP_RecogToInvoicePolicy().equals("NO"))
			{
				String descriptionMsg = Msg.getMsg(Env.getCtx(),"JP_Inconsistency",new Object[]{Msg.getElement(Env.getCtx(), "JP_CreateDerivativeDocPolicy"),Msg.getElement(Env.getCtx(), "JP_RecogToInvoicePolicy")});
				createContractLogDetail(MContractLogDetail.JP_CONTRACTLOGMSG_UnexpectedError, null,  null, descriptionMsg);
				return "";
			}
		}

		//Check Doc Type of Ship/Receipt
		int C_DocTypeShipment_ID = m_ContractContent.getJP_BaseDocDocType().getC_DocTypeShipment_ID();
		if(C_DocTypeShipment_ID > 0)
		{
			MDocType io_DocType = MDocType.get(getCtx(), C_DocTypeShipment_ID);
			if(io_DocType.get_ValueAsBoolean("IsCreateInvoiceJP"))
			{
				//Document Type for Shipment of Base Doc DocType is to create Invoice.
				String msg1 = Msg.getMsg(getCtx(), "JP_DocTypeForShipmentOfBaseDocDocType");
				String msg2 = Msg.getMsg(getCtx(),"JP_Inconsistency",new Object[]{Msg.getElement(Env.getCtx(), "JP_BaseDocDocType_ID"),Msg.getElement(Env.getCtx(), "JP_CreateDerivativeDocPolicy")});
				createContractLogDetail(MContractLogDetail.JP_CONTRACTLOGMSG_UnexpectedError, null,  null, msg1 +" : " +msg2);
				return "";
			}

		}else{

			String msg = Msg.getMsg(getCtx(), "NotFound") + "  " + Msg.getElement(getCtx(), "C_DocTypeShipment_ID");
			createContractLogDetail(MContractLogDetail.JP_CONTRACTLOGMSG_UnexpectedError, null,  null, msg);
			return "";
		}

		//Check Header Overlap -> Unnecessary. because order : invoice = 1 : N. need overlap.
//		if(isOverlapPeriodInvoice(orderProcPeriod.getJP_ContractProcPeriod_ID()))
//			return "";


		MOrder[] orders = m_ContractContent.getOrderByContractPeriod(getCtx(), orderProcPeriod.getJP_ContractProcPeriod_ID(), get_TrxName());
		for(int i = 0; i < orders.length; i++)
		{
			if(!orders[i].getDocStatus().equals(DocAction.STATUS_Completed))
			{
				createContractLogDetail(MContractLogDetail.JP_CONTRACTLOGMSG_SkippedForDocumentStatusOfOrderIsNotCompleted, null, orders[i], null);
				continue;
			}


			/** Pre check - Pre judgment create Document or not. */
			MOrderLine[] orderLines = orders[i].getLines(true, "");
			boolean isCreateDocLine = false;
			for(int j = 0; j < orderLines.length; j++)
			{
				if(!isCreateInvoiceLine(orderLines[j], JP_ContractProcPeriod_ID, false))
					continue;

				isCreateDocLine = true;
				break;

			}

			if(!isCreateDocLine)
			{
				if(overQtyOrderedLineList.size() > 0)
				{
					for(MOrderLine oLine : overQtyOrderedLineList)
						createContractLogDetail(MContractLogDetail.JP_CONTRACTLOGMSG_OverOrderedQuantity, null, oLine, null);
				}else{
					createContractLogDetail(MContractLogDetail.JP_CONTRACTLOGMSG_AllContractContentLineWasSkipped, null, orders[i], null);
				}
				continue;
			}


			/** Create Invoice header */
			isCreateDocLine = false; //Reset
			MInvoice invoice = new MInvoice(getCtx(), 0, get_TrxName());
			PO.copyValues(orders[i], invoice);
			if(orders[i].getBill_BPartner_ID() > 0)
				invoice.setC_BPartner_ID(orders[i].getBill_BPartner_ID());
			if(orders[i].getBill_Location_ID() > 0)
				invoice.setC_BPartner_Location_ID(orders[i].getBill_Location_ID());
			if(orders[i].getBill_User_ID() > 0)
				invoice.setAD_User_ID(orders[i].getBill_User_ID());
			invoice.setC_Order_ID(orders[i].getC_Order_ID());
			invoice.setProcessed(false);
			invoice.setDocStatus(DocAction.STATUS_Drafted);
			invoice.setAD_Org_ID(m_ContractContent.getAD_Org_ID());
			invoice.setAD_OrgTrx_ID(m_ContractContent.getAD_OrgTrx_ID());
			invoice.setDateInvoiced(getDateInvoiced());
			invoice.setDocumentNo(""); //Reset Document No
			invoice.setC_DocTypeTarget_ID(orders[i].getC_DocTypeTarget().getC_DocTypeInvoice_ID());
			invoice.setDateAcct(getDateAcct());

			try{
				invoice.saveEx(get_TrxName());
			} catch (AdempiereException e) {
				createContractLogDetail(MContractLogDetail.JP_CONTRACTLOGMSG_SaveError, null, null, e.getMessage());
				throw e;
			}finally {
				;
			}

			orders[i].set_TrxName(get_TrxName());
			isCreateDocLine = false; //Reset
			for(int j = 0; j < orderLines.length; j++)
			{

				if(!isCreateInvoiceLine(orderLines[j], JP_ContractProcPeriod_ID, true))
					continue;

				int JP_ContractLine_ID = orderLines[j].get_ValueAsInt("JP_ContractLine_ID");
				MContractLine contractLine = MContractLine.get(getCtx(), JP_ContractLine_ID);
				MInvoiceLine iLine = new MInvoiceLine(getCtx(), 0, get_TrxName());
				PO.copyValues(orderLines[j], iLine);
				iLine.setC_OrderLine_ID(orderLines[j].getC_OrderLine_ID());
				iLine.setProcessed(false);
				iLine.setC_Invoice_ID(invoice.getC_Invoice_ID());
				iLine.setAD_Org_ID(invoice.getAD_Org_ID());
				iLine.setAD_OrgTrx_ID(invoice.getAD_OrgTrx_ID());
				iLine.setQtyEntered(contractLine.getQtyInvoiced());
				if(iLine.getM_Product_ID() > 0)
					iLine.setC_UOM_ID(MProduct.get(getCtx(), iLine.getM_Product_ID()).getC_UOM_ID());
				else
					iLine.setC_UOM_ID(MUOM.getDefault_UOM_ID(getCtx()));
				iLine.setQtyInvoiced(contractLine.getQtyInvoiced());
				iLine.set_ValueNoCheck("JP_ContractProcPeriod_ID", JP_ContractProcPeriod_ID);

				try{
					iLine.saveEx(get_TrxName());
				} catch (AdempiereException e) {
					createContractLogDetail(MContractLogDetail.JP_CONTRACTLOGMSG_SaveError, null, invoice, e.getMessage());
					throw e;
				}finally {
					;
				}
				isCreateDocLine = true;
			}//for J

			if(isCreateDocLine)
			{
				String docAction = getDocAction();
				if(!Util.isEmpty(docAction))
				{
					if(!invoice.processIt(docAction))
					{
						createContractLogDetail(MContractLogDetail.JP_CONTRACTLOGMSG_DocumentActionError, null, invoice, invoice.getProcessMsg());
						throw new AdempiereException(invoice.getProcessMsg());
					}

					if(!docAction.equals(DocAction.ACTION_Complete))
					{
						invoice.setDocAction(DocAction.ACTION_Complete);
						try {
							invoice.saveEx(get_TrxName());//DocStatus is Draft
						} catch (AdempiereException e) {
							createContractLogDetail(MContractLogDetail.JP_CONTRACTLOGMSG_SaveError, null, invoice, e.getMessage());
							throw e;
						}finally {
							;
						}
					}
				}else{

					invoice.setDocAction(DocAction.ACTION_Complete);
					try {
						invoice.saveEx(get_TrxName());//DocStatus is Draft
					} catch (AdempiereException e) {
						createContractLogDetail(MContractLogDetail.JP_CONTRACTLOGMSG_SaveError, null, invoice, e.getMessage());
						throw e;
					}finally {
						;
					}
				}

			}else{

				//if by any chance
				invoice.deleteEx(true, get_TrxName());
				createContractLogDetail(MContractLogDetail.JP_CONTRACTLOGMSG_AllContractContentLineWasSkipped, null, orders[i], null);
				continue;
			}

			createContractLogDetail(MContractLogDetail.JP_CONTRACTLOGMSG_CreatedDocument, null, invoice, null);

		}//for i

		return "";
	}

	private boolean isCreateInvoiceLine(MOrderLine orderLine, int JP_ContractProcPeriod_ID, boolean isCreateLog)
	{

		int JP_ContractLine_ID = orderLine.get_ValueAsInt("JP_ContractLine_ID");
		if(JP_ContractLine_ID == 0)
			return false;

		MContractLine contractLine = MContractLine.get(getCtx(), JP_ContractLine_ID);


		String logMsg = getSkipReason_CreateDerivativeInvoiceLine(getCtx(), m_ContractContent, contractLine, orderLine , JP_ContractProcPeriod_ID, getJP_ContractProcess_ID(), true, true, get_TrxName());

		if(logMsg == null)
		{
			return true;

		}

		if(isCreateLog)
		{
			if (logMsg.equals(MContractLogDetail.JP_CONTRACTLOGMSG_SkippedContractProcessForOverlapContractProcessPeriod))
			{
				//Check Overlap
				MInvoiceLine[] iLines = contractLine.getInvoiceLineByContractPeriod(getCtx(), JP_ContractProcPeriod_ID, get_TrxName());
				if(iLines != null && iLines.length > 0)
				{
					createContractLogDetail(MContractLogDetail.JP_CONTRACTLOGMSG_SkippedContractProcessForOverlapContractProcessPeriod, contractLine, iLines[0], null);
				}

			}else {

				createContractLogDetail(logMsg, contractLine, orderLine, null);

			}

		}else {

			if(logMsg.equals(MContractLogDetail.JP_CONTRACTLOGMSG_OverOrderedQuantity))
			{
				overQtyOrderedLineList.add(orderLine);
			}
		}

		return false;

	}
}
