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
package custom.contract.jpiere.base.plugin.org.adempiere.base;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;

import org.compiere.model.MCharge;
import org.compiere.model.MTax;
import org.compiere.model.MTaxProvider;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;

import custom.contract.jpiere.base.plugin.org.adempiere.model.CustomContractTaxProvider;
import custom.contract.jpiere.base.plugin.org.adempiere.model.MContractContent;
import custom.contract.jpiere.base.plugin.org.adempiere.model.MContractContentTax;
import custom.contract.jpiere.base.plugin.org.adempiere.model.MContractLine;
import custom.contract.jpiere.base.plugin.org.adempiere.model.MEstimation;
import custom.contract.jpiere.base.plugin.org.adempiere.model.MEstimationLine;
import custom.contract.jpiere.base.plugin.org.adempiere.model.MRecognition;
import custom.contract.jpiere.base.plugin.org.adempiere.model.MRecognitionLine;

/**
 * Interface JPiere Tax Provider
 *
 * @author Hideaki Hagiwara
 *
 */
public interface ICustomContractTaxProvider {

	/**	Logger							*/
	static CLogger	log = CLogger.getCLogger ("ICustomContractTaxProvider");

	public BigDecimal calculateTax (MTax m_tax, BigDecimal amount, boolean taxIncluded, int scale, RoundingMode roundingMode);

	public boolean calculateEstimationTaxTotal(MTaxProvider provider, MEstimation estimation);

	public boolean recalculateTax(MTaxProvider provider, MEstimationLine line, boolean newRecord);

	public boolean updateEstimationTax(MTaxProvider provider, MEstimationLine line);

	public boolean updateHeaderTax(MTaxProvider provider, MEstimationLine line);


	public boolean calculateRecognitionTaxTotal(MTaxProvider provider, MRecognition estimation);

	public boolean recalculateTax(MTaxProvider provider, MRecognitionLine line, boolean newRecord);

	public boolean updateRecognitionTax(MTaxProvider provider, MRecognitionLine line);

	public boolean updateHeaderTax(MTaxProvider provider, MRecognitionLine line);

	/**
	 * JPIERE-0541: Calculate Contract Content Tax
	 *
	 * @param provider
	 * @param contractContent
	 * @return
	 */
	default public boolean calculateContractContentTaxTotal(MTaxProvider provider, MContractContent contractContent)
	{
		BigDecimal totalLines = Env.ZERO;
		ArrayList<Integer> taxList = new ArrayList<Integer>();
		MContractLine[] lines = contractContent.getLines();
		for (int i = 0; i < lines.length; i++)
		{
			MContractLine line = lines[i];
			totalLines = totalLines.add(line.getLineNetAmt());
			Integer taxID = Integer.valueOf(line.getC_Tax_ID());
			if (!taxList.contains(taxID))
			{
				MContractContentTax eTax = MContractContentTax.get (line, contractContent.getPrecision(), false, contractContent.get_TrxName());	//	current Tax

				//JPIERE-0369:Start
				if(eTax.isTaxIncluded() != contractContent.isTaxIncluded())
				{
					if(line.getC_Charge_ID() != 0)
					{
                        MCharge charge = MCharge.get(Env.getCtx(), line.getC_Charge_ID());
                        if(charge.isSameTax())
                        {
    						//Don't setting common Tax Rate master for tax-included lines and tax-excluded lines
    						log.saveError("Error", Msg.getMsg(Env.getCtx(), "JP_NotSet_Common_TaxRate"));
    						return false;

                        }else {

                            if(eTax.isTaxIncluded() == charge.isTaxIncluded())
                            {
                            	;//No Problem
                            }else {

                            	//Don't setting common Tax Rate master for tax-included lines and tax-excluded lines
        						log.saveError("Error", Msg.getMsg(Env.getCtx(), "JP_NotSet_Common_TaxRate"));
        						return false;
                            }
                        }

					}else {

						//Don't setting common Tax Rate master for tax-included lines and tax-excluded lines
						log.saveError("Error", Msg.getMsg(Env.getCtx(), "JP_NotSet_Common_TaxRate"));
						return false;
					}

				}else{

					if(line.getC_Charge_ID() != 0)
					{
						MCharge charge = MCharge.get(Env.getCtx(), line.getC_Charge_ID());
						if(!charge.isSameTax())
						{
							if(eTax.isTaxIncluded() != charge.isTaxIncluded())
							{
								//Don't setting common Tax Rate master for tax-included lines and tax-excluded lines
								log.saveError("Error", Msg.getMsg(Env.getCtx(), "JP_NotSet_Common_TaxRate"));
								return false;
							}
						}
					}

				}
				//JPiere-0369: End

				if (!calculateTaxFromContractLines(line, eTax))
					return false;
				if (!eTax.save(contractContent.get_TrxName()))
					return false;
				taxList.add(taxID);
			}
		}

		//	Taxes
		BigDecimal grandTotal = totalLines;
		MContractContentTax[] taxes = contractContent.getTaxes(true);

		RoundingMode roundingMode = CustomContractTaxProvider.getRoundingMode(lines[0].getParent().getC_BPartner_ID(), lines[0].getParent().isSOTrx(), provider);

		for (int i = 0; i < taxes.length; i++)
		{
			MContractContentTax eTax = taxes[i];
			MTax tax = MTax.get(eTax.getCtx(), eTax.getC_Tax_ID());
			if (tax.isSummary())
			{
				MTax[] cTaxes = tax.getChildTaxes(false);
				for (int j = 0; j < cTaxes.length; j++)
				{
					MTax cTax = cTaxes[j];
					BigDecimal taxAmt = calculateTax(cTax, eTax.getTaxBaseAmt(), eTax.isTaxIncluded(), contractContent.getPrecision(), roundingMode);//JPIERE-0369
					//
					MContractContentTax newOTax = new MContractContentTax(contractContent.getCtx(), 0, contractContent.get_TrxName());
					newOTax.set_ValueOfColumn("AD_Client_ID", contractContent.getAD_Client_ID());
					newOTax.setAD_Org_ID(contractContent.getAD_Org_ID());
					newOTax.setJP_ContractContent_ID(contractContent.getJP_ContractContent_ID());
					newOTax.setC_Tax_ID(cTax.getC_Tax_ID());
					newOTax.setIsTaxIncluded(eTax.isTaxIncluded());//JPIERE-0369
					newOTax.setTaxBaseAmt(eTax.getTaxBaseAmt());
					newOTax.setTaxAmt(taxAmt);
					if (!newOTax.save(contractContent.get_TrxName()))
						return false;
					//
					if (!eTax.isTaxIncluded())//JPIERE-0369
						grandTotal = grandTotal.add(taxAmt);
				}
				if (!eTax.delete(true, contractContent.get_TrxName()))
					return false;
				if (!eTax.save(contractContent.get_TrxName()))
					return false;
			}
			else
			{
				if (!eTax.isTaxIncluded())//JPIERE-0369
					grandTotal = grandTotal.add(eTax.getTaxAmt());
			}
		}
		//
		contractContent.setTotalLines(totalLines);
		contractContent.setGrandTotal(grandTotal);
		return true;
	}


	/**
	 * JPIERE-0541: Calculate Contract Content Tax
	 *
	 * @param provider
	 * @param line
	 * @param newRecord
	 * @return
	 */
	default public boolean recalculateTax(MTaxProvider provider, MContractLine line, boolean newRecord)
	{
		if (!newRecord && line.is_ValueChanged(MContractLine.COLUMNNAME_C_Tax_ID) && !line.getParent().isProcessed())
		{
    		if (!updateContractContentTax(line, true))
				return false;
		}

		if(!updateContractContentTax(line, false))
			return false;

		return updateHeaderTax(provider, line);
	}


	/**
	 * JPIERE-0541: Calculate Contract Content Tax
	 *
	 * @param provider
	 * @param line
	 * @return
	 */
	default public boolean updateContractContentTax(MTaxProvider provider, MContractLine line)
	{
		return  updateContractContentTax(line, false);
	}


	/**
	 * JPIERE-0541: Calculate Contract Content Tax
	 *
	 * @param line
	 * @param oldTax
	 * @return
	 */
	private boolean updateContractContentTax(MContractLine line, boolean oldTax)
	{
		MContractContentTax tax = MContractContentTax.get (line, line.getPrecision(), oldTax, line.get_TrxName());
		if (tax != null)
		{
			//JPIERE-0369:Start
			if(tax.isTaxIncluded() != line.getParent().isTaxIncluded())
			{
				if(line.getC_Charge_ID() != 0)
				{
                    MCharge charge = MCharge.get(Env.getCtx(), line.getC_Charge_ID());
                    if(charge.isSameTax())
                    {
						//Don't setting common Tax Rate master for tax-included lines and tax-excluded lines
						log.saveError("Error", Msg.getMsg(Env.getCtx(), "JP_NotSet_Common_TaxRate"));
						return false;

                    }else {

                        if(tax.isTaxIncluded() == charge.isTaxIncluded())
                        {
                        	;//No Problem
                        }else {

                        	//Don't setting common Tax Rate master for tax-included lines and tax-excluded lines
    						log.saveError("Error", Msg.getMsg(Env.getCtx(), "JP_NotSet_Common_TaxRate"));
    						return false;
                        }
                    }

				}else {

					//Don't setting common Tax Rate master for tax-included lines and tax-excluded lines
					log.saveError("Error", Msg.getMsg(Env.getCtx(), "JP_NotSet_Common_TaxRate"));
					return false;
				}

			}else{

				if(line.getC_Charge_ID() != 0)
				{
					MCharge charge = MCharge.get(Env.getCtx(), line.getC_Charge_ID());
					if(!charge.isSameTax())
					{
						if(tax.isTaxIncluded() != charge.isTaxIncluded())
						{
							//Don't setting common Tax Rate master for tax-included lines and tax-excluded lines
							log.saveError("Error", Msg.getMsg(Env.getCtx(), "JP_NotSet_Common_TaxRate"));
							return false;
						}
					}
				}

			}
			//JPiere-0369: End

			if (!calculateTaxFromContractLines(line,tax))
				return false;
			if (tax.getTaxAmt().signum() != 0 || tax.getTaxBaseAmt().signum() != 0) {
				if (!tax.save(line.get_TrxName()))
					return false;
			} else {
				if (!tax.is_new() && !tax.delete(false, line.get_TrxName()))
					return false;
			}
		}
		return true;
	}


	/**
	 * JPIERE-0541: Calculate Contract Content Tax
	 *
	 * @param provider
	 * @param line
	 * @return
	 */
	default public boolean updateHeaderTax(MTaxProvider provider, MContractLine line)
	{
		//Update Contract Content
		String sql = "UPDATE JP_ContractContent cc"
		+ " SET TotalLines = "
		    + "(SELECT COALESCE(SUM(LineNetAmt),0) FROM JP_ContractLine cl WHERE cc.JP_ContractContent_ID=cl.JP_ContractContent_ID)"
		+ "WHERE JP_ContractContent_ID=?";
		int no = DB.executeUpdate(sql, new Object[]{Integer.valueOf(line.getJP_ContractContent_ID())}, false, line.get_TrxName(), 0);
		if (no != 1)
		{
			log.warning("(1) #" + no);
			return false;
		}

		sql = "UPDATE JP_ContractContent cc "
				+ " SET GrandTotal=TotalLines+"
					+ "(SELECT COALESCE(SUM(TaxAmt),0) FROM JP_ContractContentTax cct WHERE cc.JP_ContractContent_ID=cct.JP_ContractContent_ID AND cct.IsTaxIncluded='N' ) "
					+ "WHERE JP_ContractContent_ID=?";
		no = DB.executeUpdate(sql, new Object[]{Integer.valueOf(line.getJP_ContractContent_ID())}, false, line.get_TrxName(), 0);
		if (no != 1)
			log.warning("(2) #" + no);

		line.clearParent();
		return no == 1;
	}


	/**
	 * JPIERE-0541: Calculate Contract Content Tax
	 *
	 * @param line
	 * @param m_ContractContentTax
	 * @return
	 */
	private boolean calculateTaxFromContractLines (MContractLine line, MContractContentTax m_ContractContentTax)
	{
		BigDecimal taxBaseAmt = Env.ZERO;
		BigDecimal taxAmt = Env.ZERO;

		MTax tax = MTax.get(m_ContractContentTax.getCtx(), m_ContractContentTax.getC_Tax_ID());
		boolean documentLevel = tax.isDocumentLevel();

		RoundingMode roundingMode = CustomContractTaxProvider.getRoundingMode(line.getParent().getC_BPartner_ID(), line.getParent().isSOTrx(), tax.getC_TaxProvider());

		//
		String sql = "SELECT LineNetAmt FROM JP_ContractLine WHERE JP_ContractContent_ID=? AND C_Tax_ID=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement (sql, m_ContractContentTax.get_TrxName());
			pstmt.setInt (1,m_ContractContentTax.getJP_ContractContent_ID());
			pstmt.setInt (2, m_ContractContentTax.getC_Tax_ID());
			rs = pstmt.executeQuery ();
			while (rs.next ())
			{
				BigDecimal baseAmt = rs.getBigDecimal(1);
				taxBaseAmt = taxBaseAmt.add(baseAmt);
				//
				if (!documentLevel)		// calculate line tax
					taxAmt = taxAmt.add(calculateTax(tax, baseAmt, m_ContractContentTax.isTaxIncluded(), line.getPrecision(), roundingMode));
			}
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, m_ContractContentTax.get_TrxName(), e);
			taxBaseAmt = null;
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		//
		if (taxBaseAmt == null)
			return false;

		//	Calculate Tax
		if (documentLevel)		//	document level
			taxAmt = calculateTax(tax, taxBaseAmt, m_ContractContentTax.isTaxIncluded(), line.getPrecision(), roundingMode);
		m_ContractContentTax.setTaxAmt(taxAmt);

		//	Set Base
		if (m_ContractContentTax.isTaxIncluded())
			m_ContractContentTax.setTaxBaseAmt (taxBaseAmt.subtract(taxAmt));
		else
			m_ContractContentTax.setTaxBaseAmt (taxBaseAmt);
		if (log.isLoggable(Level.FINE)) log.fine(toString());
		return true;

	}	//	calculateTaxFromLines


}