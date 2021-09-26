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

import org.compiere.model.MBPartnerLocation;
import org.compiere.model.MDocType;
import org.compiere.model.MOrgInfo;
import org.compiere.model.MWarehouse;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.util.Env;
import org.compiere.util.Msg;

import custom.contract.jpiere.base.plugin.org.adempiere.model.MContract;
import custom.contract.jpiere.base.plugin.org.adempiere.model.MContractContent;

/** JPIERE-0432 Create Contarct By Copy
*
* @author Hideaki Hagiwara
*
*/
public abstract class AbstractCreateContractByCopy extends AbstractCreateContractFromTemplate {


	protected MContract to_Contract = null;
	protected MContractContent to_ContractContent = null;

	protected int from_JP_Contract_ID = 0;
	protected int from_JP_ContractContent_ID = 0;

	protected MContract from_Contract = null;
	protected MContractContent from_ContractContent = null;

	protected String p_JP_ContractTabLevel = null;
	protected  static final String JP_ContractTabLevel_Document  = "CD";
	protected  static final String JP_ContractTabLevel_Content  = "CC";


	int Record_ID = 0;


	@Override
	protected void prepare()
	{
		super.prepare();

		Record_ID = getRecord_ID();
		if(Record_ID > 0)
		{
			ProcessInfoParameter[] para = getParameter();
			for (int i = 0; i < para.length; i++)
			{
				String name = para[i].getParameterName();

				if (para[i].getParameter() == null)
				{
					;

				}else if (name.equals("JP_ContractTabLevel")){

					p_JP_ContractTabLevel = para[i].getParameterAsString();

				}else if (name.equals("JP_CopyFrom_Contract_ID")){

					from_JP_Contract_ID = para[i].getParameterAsInt();

				}else if (name.equals("JP_CopyFrom_ContractContent_ID")){

					from_JP_ContractContent_ID = para[i].getParameterAsInt();

				}else{

//					log.log(Level.SEVERE, "Unknown Parameter: " + name);

				}//if

			}//for


			if(p_JP_ContractTabLevel.equals(JP_ContractTabLevel_Document))
			{
				to_Contract = new MContract(getCtx(), Record_ID, get_TrxName());
				from_Contract = new MContract(getCtx(),from_JP_Contract_ID, get_TrxName());
				if(from_JP_ContractContent_ID != 0)
					from_ContractContent = new MContractContent(getCtx(),from_JP_ContractContent_ID, get_TrxName());


			}else if(p_JP_ContractTabLevel.equals(JP_ContractTabLevel_Content)){

				to_ContractContent = new MContractContent(getCtx(), Record_ID, get_TrxName());
				to_Contract = to_ContractContent.getParent();

				from_ContractContent = new MContractContent(getCtx(),from_JP_ContractContent_ID, get_TrxName());
				from_Contract  = new MContract(getCtx(),from_JP_Contract_ID, get_TrxName());
			}

		}else{
//			log.log(Level.SEVERE, "Record_ID <= 0 ");
		}

	}

	@Override
	protected String doIt() throws Exception
	{
		if(to_Contract.getJP_ContractType().equals(MContract.JP_CONTRACTTYPE_GeneralContract))
		{
			throw new Exception("JP_GeneralContractContent");//General Contract can not have Contract Content.
		}

		if(from_JP_ContractContent_ID > 0)
		{
			if(from_Contract.getJP_Contract_ID() != from_ContractContent.getJP_Contract_ID())
			{
				String msg0 = Msg.getElement(Env.getCtx(), "JP_Contract_ID");
				String msg1 = Msg.getElement(Env.getCtx(), "JP_ContractContent_ID");
				throw new Exception(Msg.getMsg(Env.getCtx(),"JP_Different",new Object[]{msg0,msg1}));
			}
		}

		if(to_Contract.getJP_ContractT_ID() != from_Contract.getJP_ContractT_ID())
		{
			throw new Exception(Msg.getMsg(getCtx(), "JP_DifferentContractTemplate"));
		}

		if(p_JP_ContractTabLevel.equals(JP_ContractTabLevel_Content))
		{
			if(from_JP_ContractContent_ID == 0)
			{
				String msg0 = Msg.getElement(Env.getCtx(), "JP_ContractContent_ID");
				throw new Exception(Msg.getMsg(Env.getCtx(),"JP_Mandatory",new Object[]{msg0}));
			}

			if(to_ContractContent.getJP_ContractContentT_ID() != from_ContractContent.getJP_ContractContentT_ID())
			{
				throw new Exception(Msg.getMsg(getCtx(), "JP_DifferentContractTemplate"));
			}
		}

		return Msg.getMsg(getCtx(), "Success");
	}

	protected void setDocumentNoOfContractContent(MContractContent from, MContractContent to)
	{
		MDocType docType = MDocType.get(getCtx(), from.getC_DocType_ID());
		to.setC_DocType_ID(docType.getC_DocType_ID());
		if(docType.isDocNoControlled())
		{
			to.setDocumentNo(null);
		}
	}

	protected void setBPartnerOfContractContent(MContractContent from, MContractContent to)
	{

		if(to.getParent().getC_BPartner_ID() == from.getC_BPartner_ID())
		{
			to.setC_BPartner_ID(from.getC_BPartner_ID());
			to.setC_BPartner_Location_ID(from.getC_BPartner_Location_ID());
			to.setAD_User_ID(from.getAD_User_ID());

			to.setDropShip_BPartner_ID(from.getDropShip_BPartner_ID());
			to.setDropShip_Location_ID(from.getDropShip_Location_ID());
			to.setDropShip_User_ID(from.getDropShip_User_ID());
			to.setIsDropShip(from.isDropShip());

			to.setBill_BPartner_ID(from.getBill_BPartner_ID());
			to.setBill_Location_ID(from.getBill_Location_ID());
			to.setBill_User_ID(from.getBill_User_ID());

		}else {

			if(from.getC_BPartner_ID() == from.getJP_ContractContentT().getC_BPartner_ID())
			{

				to.setC_BPartner_ID(from.getC_BPartner_ID());
				to.setC_BPartner_Location_ID(from.getC_BPartner_Location_ID());
				to.setAD_User_ID(from.getAD_User_ID());

				to.setDropShip_BPartner_ID(from.getDropShip_BPartner_ID());
				to.setDropShip_Location_ID(from.getDropShip_Location_ID());
				to.setDropShip_User_ID(from.getDropShip_User_ID());
				to.setIsDropShip(from.isDropShip());

				to.setBill_BPartner_ID(from.getBill_BPartner_ID());
				to.setBill_Location_ID(from.getBill_Location_ID());
				to.setBill_User_ID(from.getBill_User_ID());

			}else {

				to.setC_BPartner_ID(to.getParent().getC_BPartner_ID());
				if(to.getParent().getC_BPartner_Location_ID() != 0)
				{
					to.setC_BPartner_Location_ID(to.getParent().getC_BPartner_Location_ID());
				}else {

					MBPartnerLocation[]  bpLocations = MBPartnerLocation.getForBPartner(getCtx(), to.getParent().getC_BPartner_ID(), get_TrxName());
					if(bpLocations.length > 1)
					{
						to.setC_BPartner_Location_ID(bpLocations[0].getC_BPartner_Location_ID());
					}
				}

				if(to.getParent().getAD_User_ID() != 0)
				{
					to.setAD_User_ID(to.getParent().getAD_User_ID());
				}else {

					to.setAD_User_ID(0);
				}

				to.setDropShip_BPartner_ID(0);
				to.setDropShip_Location_ID(0);
				to.setDropShip_User_ID(0);
				to.setIsDropShip(false);

				to.setBill_BPartner_ID(0);
				to.setBill_Location_ID(0);
				to.setBill_User_ID(0);
			}

		}

	}

	protected void setWarehouseOfContractContent(MContractContent from, MContractContent to) throws Exception
	{
		if(from.getDocBaseType().equals(MContractContent.DOCBASETYPE_APInvoice)
				|| from.getDocBaseType().equals(MContractContent.DOCBASETYPE_ARInvoice) )
			return ;


		if(from.getM_Warehouse() != null && from.getM_Warehouse().getAD_Org_ID() == to.getAD_Org_ID())
		{
			to.setM_Warehouse_ID(from.getM_Warehouse_ID());

		}else{

			if(MOrgInfo.get(null, to.getAD_Org_ID(),get_TrxName()).getM_Warehouse_ID() != 0)
			{
				to.setM_Warehouse_ID(MOrgInfo.get(null, to.getAD_Org_ID(),get_TrxName()).getM_Warehouse_ID());

			}else{

				MWarehouse[] warehouses =  MWarehouse.getForOrg(getCtx(), from.getAD_Org_ID());
				if(warehouses.length > 0)
				{
					to.setM_Warehouse_ID(warehouses[0].getM_Warehouse_ID());

				}else {

					throw new Exception(Msg.getMsg(Env.getCtx(),"NotFound") + Msg.getElement(getCtx(), "M_Warehouse_ID") + " : "
											+ Msg.getElement(getCtx(), "CopyFrom") + " : " + Msg.getElement(getCtx(), "JP_ContractContent_ID") + "_"+  from.getDocumentNo());

				}

			}

		}
	}

}
