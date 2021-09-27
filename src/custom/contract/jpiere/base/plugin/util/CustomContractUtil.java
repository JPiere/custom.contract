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
package custom.contract.jpiere.base.plugin.util;

import java.util.List;

import org.adempiere.base.Service;
import org.compiere.model.MTax;
import org.compiere.util.Util;

import custom.contract.jpiere.base.plugin.org.adempiere.base.ICustomContractTaxProvider;
import custom.contract.jpiere.base.plugin.org.adempiere.base.ICustomContractTaxProviderFactory;

public class CustomContractUtil {

	public CustomContractUtil() {
		;
	}

	public static final String COUSTOM_CONTRACT_TAX_PROVIDER = "custom.contract.jpiere.base.plugin.org.adempiere.model.CustomContractTaxProvider";

	public static ICustomContractTaxProvider getCustomContractTaxProvider(MTax m_tax)
	{
		String className = m_tax.getC_TaxProvider().getC_TaxProviderCfg().getTaxProviderClass();
		if(Util.isEmpty(className))
			className = COUSTOM_CONTRACT_TAX_PROVIDER;

		ICustomContractTaxProvider calculator = null;
		List<ICustomContractTaxProviderFactory> factoryList = Service.locator().list(ICustomContractTaxProviderFactory.class).getServices();
		if (factoryList != null)
		{
			for (ICustomContractTaxProviderFactory factory : factoryList)
			{
				calculator = factory.newCustomContractTaxProviderInstance(className);
				if (calculator != null)
				{
					return calculator;
				}

			}//For
		}

		return null;
	}
}
