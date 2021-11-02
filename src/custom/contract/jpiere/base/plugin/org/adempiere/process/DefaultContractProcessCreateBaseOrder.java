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

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.util.ProcessUtil;
import org.compiere.model.MColumn;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.MProcess;
import org.compiere.model.PO;
import org.compiere.process.DocAction;
import org.compiere.process.ProcessInfo;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Util;
import org.compiere.wf.MWFProcess;

import custom.contract.jpiere.base.plugin.org.adempiere.model.MContract;
import custom.contract.jpiere.base.plugin.org.adempiere.model.MContractLine;
import custom.contract.jpiere.base.plugin.org.adempiere.model.MContractLogDetail;


/**
* JPIERE-0363
*
* @author Hideaki Hagiwara
*
*/
public class DefaultContractProcessCreateBaseOrder extends AbstractContractProcess
{

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

		//Check Overlap Header
		MOrder[] orders = m_ContractContent.getOrderByContractPeriod(Env.getCtx(), JP_ContractProcPeriod_ID, get_TrxName());
		if(orders != null && orders.length > 0)
		{
			createContractLogDetail(MContractLogDetail.JP_CONTRACTLOGMSG_SkippedContractProcessForOverlapContractProcessPeriod, null,  orders[0], null);
			return "";
		}//Check Overlap


		/** Pre check - Pre judgment create Document or not. */
		MContractLine[] 	m_lines = m_ContractContent.getLines();
		boolean isCreateDocLine = false;
		for(int i = 0; i < m_lines.length; i++)
		{
			if(!isCreateDocLine(m_lines[i], JP_ContractProcPeriod_ID, false))
				continue;

			isCreateDocLine = true;
			break;
		}


		if(!isCreateDocLine)
		{
			createContractLogDetail(MContractLogDetail.JP_CONTRACTLOGMSG_AllContractContentLineWasSkipped, null, null, null);
			return "";
		}


		/** Create Order header */
		MOrder order = new MOrder(getCtx(), 0, get_TrxName());


		PO.copyValues(m_ContractContent, order);
		order.setProcessed(false);
		order.setDocStatus(DocAction.STATUS_Drafted);
		order.setDocAction(DocAction.ACTION_Complete);
		order.setAD_Org_ID(m_ContractContent.getAD_Org_ID());
		order.setAD_OrgTrx_ID(m_ContractContent.getAD_OrgTrx_ID());
		order.setDateOrdered(getDateOrdered());
		order.setDateAcct(getDateAcct());
		order.setDatePromised(getOrderHeaderDatePromised(p_DateAcct)); //DateAcct is basis.
		order.setDocumentNo(""); //Reset Document No
		order.setC_DocTypeTarget_ID(m_ContractContent.getJP_BaseDocDocType_ID());
		order.setC_DocType_ID(m_ContractContent.getJP_BaseDocDocType_ID());
		order.set_ValueOfColumn("JP_Contract_ID", m_ContractContent.getParent().getJP_Contract_ID());
		order.set_ValueOfColumn("JP_ContractContent_ID", m_ContractContent.getJP_ContractContent_ID());
		if(m_ContractContent.getParent().getJP_ContractType().equals(MContract.JP_CONTRACTTYPE_PeriodContract))
			order.set_ValueOfColumn("JP_ContractProcPeriod_ID", JP_ContractProcPeriod_ID);

		try {
			order.saveEx(get_TrxName());
		} catch (AdempiereException e) {
			createContractLogDetail(MContractLogDetail.JP_CONTRACTLOGMSG_SaveError, null, null, e.getMessage());
			throw e;
		}finally {
			;
		}

		/** Create Order Line */
		isCreateDocLine = false; //Reset
		for(int i = 0; i < m_lines.length; i++)
		{
			if(!isCreateDocLine(m_lines[i], JP_ContractProcPeriod_ID, true))
				continue;

			MOrderLine oline = new MOrderLine(getCtx(), 0, get_TrxName());
			PO.copyValues(m_lines[i], oline);
			oline.setC_Order_ID(order.getC_Order_ID());
			oline.setAD_Org_ID(order.getAD_Org_ID());
			oline.setAD_OrgTrx_ID(order.getAD_OrgTrx_ID());
			oline.setProcessed(false);


			//
			if(m_lines[i].getC_BPartner_ID() == 0)
				oline.setC_BPartner_ID(order.getC_BPartner_ID());
			if(m_lines[i].getC_BPartner_Location_ID() == 0)
				oline.setC_BPartner_Location_ID(order.getC_BPartner_Location_ID());
			oline.setM_Warehouse_ID(order.getM_Warehouse_ID());
			oline.setC_Currency_ID(order.getC_Currency_ID());


			//Qty
			if(m_lines[i].getM_Product_ID() > 0)
			{
				oline.setC_UOM_ID(m_lines[i].getM_Product().getC_UOM_ID());
				oline.setQtyEntered(m_lines[i].getQtyOrdered());
			}else{
				oline.setQtyEntered(m_lines[i].getQtyEntered());

			}
			oline.setQtyOrdered(m_lines[i].getQtyOrdered());
			oline.setQtyReserved(Env.ZERO);
			oline.setQtyDelivered(Env.ZERO);
			oline.setQtyInvoiced(Env.ZERO);

			//Contract Info
			oline.set_ValueNoCheck("JP_ContractLine_ID", m_lines[i].getJP_ContractLine_ID());
			if(m_ContractContent.getParent().getJP_ContractType().equals(MContract.JP_CONTRACTTYPE_PeriodContract))
				oline.set_ValueOfColumn("JP_ContractProcPeriod_ID", JP_ContractProcPeriod_ID);

			//Date
			oline.setDateOrdered(order.getDateOrdered());
			oline.setDatePromised(getOrderLineDatePromised(m_lines[i]));

			try {
				oline.saveEx(get_TrxName());//DocStatus is Draft
			} catch (AdempiereException e) {
				createContractLogDetail(MContractLogDetail.JP_CONTRACTLOGMSG_SaveError, null, order, e.getMessage());
				throw e;
			}finally {
				;
			}
			isCreateDocLine = true;
		}


		if(isCreateDocLine)
		{
			String docAction = getDocAction();
			updateContractProcStatus();
			if(!Util.isEmpty(docAction))
			{
				if(docAction.equals(DocAction.ACTION_Complete))
				{
					ProcessInfo pInfo = getProcessInfo();
					pInfo.setPO(order);
					pInfo.setRecord_ID(order.getC_Order_ID());
					pInfo.setTable_ID(MOrder.Table_ID);
					MColumn docActionColumn = MColumn.get(getCtx(), MOrder.Table_Name, MOrder.COLUMNNAME_DocAction);
					MProcess process = MProcess.get(docActionColumn.getAD_Process_ID());
					MWFProcess wfProcess = ProcessUtil.startWorkFlow(Env.getCtx(), pInfo, process.getAD_Workflow_ID());
					if(wfProcess.getWFState().equals(MWFProcess.WFSTATE_Terminated))
					{
						String msg = wfProcess.getTextMsg();
						//String msg = order.getProcessMsg();
						createContractLogDetail(MContractLogDetail.JP_CONTRACTLOGMSG_DocumentActionError, null, order, msg);
						throw new AdempiereException(msg);
					}

				}else if(docAction.equals(DocAction.ACTION_Prepare)){

					if(order.processIt(DocAction.ACTION_Prepare))
					{
						try {
							order.saveEx(get_TrxName());//DocStatus is Draft
						} catch (AdempiereException e) {
							createContractLogDetail(MContractLogDetail.JP_CONTRACTLOGMSG_SaveError, null, order, e.getMessage());
							throw e;
						}finally {
							;
						}

					}else {

	                    createContractLogDetail(MContractLogDetail.JP_CONTRACTLOGMSG_DocumentActionError, null, order, order.getProcessMsg());
	                    throw new AdempiereException(order.getProcessMsg());
					}

				}else {

					try {
						order.saveEx(get_TrxName());//DocStatus is Draft
					} catch (AdempiereException e) {
						createContractLogDetail(MContractLogDetail.JP_CONTRACTLOGMSG_SaveError, null, order, e.getMessage());
						throw e;
					}finally {
						;
					}

				}
			}

		}else{

			//if by any chance
			order.deleteEx(true, get_TrxName());
			createContractLogDetail(MContractLogDetail.JP_CONTRACTLOGMSG_AllContractContentLineWasSkipped, null, null, null);
			return "";
		}


		createContractLogDetail(MContractLogDetail.JP_CONTRACTLOGMSG_CreatedDocument, null, order, null);
		return "";

	}//doIt()


	/**
	 *
	 * Check Create Doc Line or Not.
	 *
	 * @param contractLine
	 * @param JP_ContractProcPeriod_ID
	 * @param isCreateLog
	 * @return
	 */
	private boolean isCreateDocLine(MContractLine contractLine, int JP_ContractProcPeriod_ID, boolean isCreateLog)
	{

		String logMsg = AbstractContractProcess.getSkipReason_CreateBaseOrderLine(getCtx(), m_ContractContent, contractLine, JP_ContractProcPeriod_ID, true, get_TrxName());

		if(logMsg == null)
		{
			return true;

		}

		if(isCreateLog)
		{
			if (logMsg.equals(MContractLogDetail.JP_CONTRACTLOGMSG_SkippedContractProcessForOverlapContractProcessPeriod))
			{
				MOrderLine[] oLines = contractLine.getOrderLineByContractPeriod(getCtx(), JP_ContractProcPeriod_ID, get_TrxName());
				if(oLines != null && oLines.length > 0)
				{
					createContractLogDetail(logMsg, contractLine, oLines[0], null);
				}

			}else {

				createContractLogDetail(logMsg, contractLine, null, null);

			}
		}

		return false;

	}//isCreateDocLine
}
