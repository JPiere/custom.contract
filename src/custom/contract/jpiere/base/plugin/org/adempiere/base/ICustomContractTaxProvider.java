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

import org.compiere.model.MTax;
import org.compiere.model.MTaxProvider;

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

	public BigDecimal calculateTax (MTax m_tax, BigDecimal amount, boolean taxIncluded, int scale, RoundingMode roundingMode);

	public boolean calculateEstimationTaxTotal(MTaxProvider provider, MEstimation estimation);

	public boolean recalculateTax(MTaxProvider provider, MEstimationLine line, boolean newRecord);

	public boolean updateEstimationTax(MTaxProvider provider, MEstimationLine line);

	public boolean updateHeaderTax(MTaxProvider provider, MEstimationLine line);


	public boolean calculateRecognitionTaxTotal(MTaxProvider provider, MRecognition estimation);

	public boolean recalculateTax(MTaxProvider provider, MRecognitionLine line, boolean newRecord);

	public boolean updateRecognitionTax(MTaxProvider provider, MRecognitionLine line);

	public boolean updateHeaderTax(MTaxProvider provider, MRecognitionLine line);

}