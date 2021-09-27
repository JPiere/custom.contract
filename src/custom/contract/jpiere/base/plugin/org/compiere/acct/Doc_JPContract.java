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
package custom.contract.jpiere.base.plugin.org.compiere.acct;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.compiere.acct.Doc;
import org.compiere.acct.Fact;
import org.compiere.model.MAcctSchema;
import org.compiere.util.Env;

import custom.contract.jpiere.base.plugin.org.adempiere.model.MContract;

/**
 * Post Contract Documents.
 *
 *
 * JPIERE-0363: Contract Document
 *
 *   Table:              JP_Contract
 *   Document Types:     JPC
 *
 * @author Hideaki Hagiwara(h.hagiwara@oss-erp.co.jp)
 */
public class Doc_JPContract extends Doc
{

	public Doc_JPContract (MAcctSchema as, ResultSet rs, String trxName)
	{
		super (as, MContract.class, rs, "JPC", trxName);
	}

	protected String loadDocumentDetails ()
	{
		return null;
	}

	public BigDecimal getBalance ()
	{
		return Env.ZERO;
	}

	public ArrayList<Fact> createFacts (MAcctSchema as)
	{
		ArrayList<Fact> facts = new ArrayList<Fact>();

		return facts;
	}
}
