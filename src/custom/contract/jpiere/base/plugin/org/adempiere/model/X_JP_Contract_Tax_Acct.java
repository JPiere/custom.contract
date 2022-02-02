/******************************************************************************
 * Product: iDempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2012 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
/** Generated Model - DO NOT CHANGE */
package custom.contract.jpiere.base.plugin.org.adempiere.model;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.*;

/** Generated Model for JP_Contract_Tax_Acct
 *  @author iDempiere (generated) 
 *  @version Release 8.2 - $Id$ */
public class X_JP_Contract_Tax_Acct extends PO implements I_JP_Contract_Tax_Acct, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20220202L;

    /** Standard Constructor */
    public X_JP_Contract_Tax_Acct (Properties ctx, int JP_Contract_Tax_Acct_ID, String trxName)
    {
      super (ctx, JP_Contract_Tax_Acct_ID, trxName);
      /** if (JP_Contract_Tax_Acct_ID == 0)
        {
			setC_AcctSchema_ID (0);
			setC_Tax_ID (0);
			setJP_Contract_Acct_ID (0);
			setJP_Contract_Tax_Acct_ID (0);
        } */
    }

    /** Load Constructor */
    public X_JP_Contract_Tax_Acct (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 3 - Client - Org 
      */
    protected int get_AccessLevel()
    {
      return accessLevel.intValue();
    }

    /** Load Meta Data */
    protected POInfo initPO (Properties ctx)
    {
      POInfo poi = POInfo.getPOInfo (ctx, Table_ID, get_TrxName());
      return poi;
    }

    public String toString()
    {
      StringBuilder sb = new StringBuilder ("X_JP_Contract_Tax_Acct[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.compiere.model.I_C_AcctSchema getC_AcctSchema() throws RuntimeException
    {
		return (org.compiere.model.I_C_AcctSchema)MTable.get(getCtx(), org.compiere.model.I_C_AcctSchema.Table_Name)
			.getPO(getC_AcctSchema_ID(), get_TrxName());	}

	/** Set Accounting Schema.
		@param C_AcctSchema_ID 
		Rules for accounting
	  */
	public void setC_AcctSchema_ID (int C_AcctSchema_ID)
	{
		if (C_AcctSchema_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_AcctSchema_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_AcctSchema_ID, Integer.valueOf(C_AcctSchema_ID));
	}

	/** Get Accounting Schema.
		@return Rules for accounting
	  */
	public int getC_AcctSchema_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_AcctSchema_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_Tax getC_Tax() throws RuntimeException
    {
		return (org.compiere.model.I_C_Tax)MTable.get(getCtx(), org.compiere.model.I_C_Tax.Table_Name)
			.getPO(getC_Tax_ID(), get_TrxName());	}

	/** Set Tax.
		@param C_Tax_ID 
		Tax identifier
	  */
	public void setC_Tax_ID (int C_Tax_ID)
	{
		if (C_Tax_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_Tax_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_Tax_ID, Integer.valueOf(C_Tax_ID));
	}

	/** Get Tax.
		@return Tax identifier
	  */
	public int getC_Tax_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Tax_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_JP_Contract_Acct getJP_Contract_Acct() throws RuntimeException
    {
		return (I_JP_Contract_Acct)MTable.get(getCtx(), I_JP_Contract_Acct.Table_Name)
			.getPO(getJP_Contract_Acct_ID(), get_TrxName());	}

	/** Set Contract Acct Info.
		@param JP_Contract_Acct_ID Contract Acct Info	  */
	public void setJP_Contract_Acct_ID (int JP_Contract_Acct_ID)
	{
		if (JP_Contract_Acct_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_JP_Contract_Acct_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_JP_Contract_Acct_ID, Integer.valueOf(JP_Contract_Acct_ID));
	}

	/** Get Contract Acct Info.
		@return Contract Acct Info	  */
	public int getJP_Contract_Acct_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_JP_Contract_Acct_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Tax Contract Acct.
		@param JP_Contract_Tax_Acct_ID Tax Contract Acct	  */
	public void setJP_Contract_Tax_Acct_ID (int JP_Contract_Tax_Acct_ID)
	{
		if (JP_Contract_Tax_Acct_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_JP_Contract_Tax_Acct_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_JP_Contract_Tax_Acct_ID, Integer.valueOf(JP_Contract_Tax_Acct_ID));
	}

	/** Get Tax Contract Acct.
		@return Tax Contract Acct	  */
	public int getJP_Contract_Tax_Acct_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_JP_Contract_Tax_Acct_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set JP_Contract_Tax_Acct_UU.
		@param JP_Contract_Tax_Acct_UU JP_Contract_Tax_Acct_UU	  */
	public void setJP_Contract_Tax_Acct_UU (String JP_Contract_Tax_Acct_UU)
	{
		set_ValueNoCheck (COLUMNNAME_JP_Contract_Tax_Acct_UU, JP_Contract_Tax_Acct_UU);
	}

	/** Get JP_Contract_Tax_Acct_UU.
		@return JP_Contract_Tax_Acct_UU	  */
	public String getJP_Contract_Tax_Acct_UU () 
	{
		return (String)get_Value(COLUMNNAME_JP_Contract_Tax_Acct_UU);
	}

	public I_C_ValidCombination getJP_GL_TaxCredit_A() throws RuntimeException
    {
		return (I_C_ValidCombination)MTable.get(getCtx(), I_C_ValidCombination.Table_Name)
			.getPO(getJP_GL_TaxCredit_Acct(), get_TrxName());	}

	/** Set Tax Credit(GL Journal).
		@param JP_GL_TaxCredit_Acct Tax Credit(GL Journal)	  */
	public void setJP_GL_TaxCredit_Acct (int JP_GL_TaxCredit_Acct)
	{
		set_Value (COLUMNNAME_JP_GL_TaxCredit_Acct, Integer.valueOf(JP_GL_TaxCredit_Acct));
	}

	/** Get Tax Credit(GL Journal).
		@return Tax Credit(GL Journal)	  */
	public int getJP_GL_TaxCredit_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_JP_GL_TaxCredit_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_C_ValidCombination getJP_GL_TaxDue_A() throws RuntimeException
    {
		return (I_C_ValidCombination)MTable.get(getCtx(), I_C_ValidCombination.Table_Name)
			.getPO(getJP_GL_TaxDue_Acct(), get_TrxName());	}

	/** Set Tax Due(GL Journal).
		@param JP_GL_TaxDue_Acct Tax Due(GL Journal)	  */
	public void setJP_GL_TaxDue_Acct (int JP_GL_TaxDue_Acct)
	{
		set_Value (COLUMNNAME_JP_GL_TaxDue_Acct, Integer.valueOf(JP_GL_TaxDue_Acct));
	}

	/** Get Tax Due(GL Journal).
		@return Tax Due(GL Journal)	  */
	public int getJP_GL_TaxDue_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_JP_GL_TaxDue_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_C_ValidCombination getJP_GL_TaxExpense_A() throws RuntimeException
    {
		return (I_C_ValidCombination)MTable.get(getCtx(), I_C_ValidCombination.Table_Name)
			.getPO(getJP_GL_TaxExpense_Acct(), get_TrxName());	}

	/** Set Tax Expense(GL Journal).
		@param JP_GL_TaxExpense_Acct Tax Expense(GL Journal)	  */
	public void setJP_GL_TaxExpense_Acct (int JP_GL_TaxExpense_Acct)
	{
		set_Value (COLUMNNAME_JP_GL_TaxExpense_Acct, Integer.valueOf(JP_GL_TaxExpense_Acct));
	}

	/** Get Tax Expense(GL Journal).
		@return Tax Expense(GL Journal)	  */
	public int getJP_GL_TaxExpense_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_JP_GL_TaxExpense_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_C_ValidCombination getJP_TaxCredit_A() throws RuntimeException
    {
		return (I_C_ValidCombination)MTable.get(getCtx(), I_C_ValidCombination.Table_Name)
			.getPO(getJP_TaxCredit_Acct(), get_TrxName());	}

	/** Set Tax Credit(Recognition Doc).
		@param JP_TaxCredit_Acct Tax Credit(Recognition Doc)	  */
	public void setJP_TaxCredit_Acct (int JP_TaxCredit_Acct)
	{
		set_Value (COLUMNNAME_JP_TaxCredit_Acct, Integer.valueOf(JP_TaxCredit_Acct));
	}

	/** Get Tax Credit(Recognition Doc).
		@return Tax Credit(Recognition Doc)	  */
	public int getJP_TaxCredit_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_JP_TaxCredit_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_C_ValidCombination getJP_TaxDue_A() throws RuntimeException
    {
		return (I_C_ValidCombination)MTable.get(getCtx(), I_C_ValidCombination.Table_Name)
			.getPO(getJP_TaxDue_Acct(), get_TrxName());	}

	/** Set Tax Due(Recognition Doc).
		@param JP_TaxDue_Acct Tax Due(Recognition Doc)	  */
	public void setJP_TaxDue_Acct (int JP_TaxDue_Acct)
	{
		set_Value (COLUMNNAME_JP_TaxDue_Acct, Integer.valueOf(JP_TaxDue_Acct));
	}

	/** Get Tax Due(Recognition Doc).
		@return Tax Due(Recognition Doc)	  */
	public int getJP_TaxDue_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_JP_TaxDue_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_C_ValidCombination getJP_TaxExpense_A() throws RuntimeException
    {
		return (I_C_ValidCombination)MTable.get(getCtx(), I_C_ValidCombination.Table_Name)
			.getPO(getJP_TaxExpense_Acct(), get_TrxName());	}

	/** Set Tax Expense(Recognition Doc).
		@param JP_TaxExpense_Acct Tax Expense(Recognition Doc)	  */
	public void setJP_TaxExpense_Acct (int JP_TaxExpense_Acct)
	{
		set_Value (COLUMNNAME_JP_TaxExpense_Acct, Integer.valueOf(JP_TaxExpense_Acct));
	}

	/** Get Tax Expense(Recognition Doc).
		@return Tax Expense(Recognition Doc)	  */
	public int getJP_TaxExpense_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_JP_TaxExpense_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_C_ValidCombination getT_Credit_A() throws RuntimeException
    {
		return (I_C_ValidCombination)MTable.get(getCtx(), I_C_ValidCombination.Table_Name)
			.getPO(getT_Credit_Acct(), get_TrxName());	}

	/** Set Tax Credit.
		@param T_Credit_Acct 
		Account for Tax you can reclaim
	  */
	public void setT_Credit_Acct (int T_Credit_Acct)
	{
		set_Value (COLUMNNAME_T_Credit_Acct, Integer.valueOf(T_Credit_Acct));
	}

	/** Get Tax Credit.
		@return Account for Tax you can reclaim
	  */
	public int getT_Credit_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_T_Credit_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_C_ValidCombination getT_Due_A() throws RuntimeException
    {
		return (I_C_ValidCombination)MTable.get(getCtx(), I_C_ValidCombination.Table_Name)
			.getPO(getT_Due_Acct(), get_TrxName());	}

	/** Set Tax Due.
		@param T_Due_Acct 
		Account for Tax you have to pay
	  */
	public void setT_Due_Acct (int T_Due_Acct)
	{
		set_Value (COLUMNNAME_T_Due_Acct, Integer.valueOf(T_Due_Acct));
	}

	/** Get Tax Due.
		@return Account for Tax you have to pay
	  */
	public int getT_Due_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_T_Due_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_C_ValidCombination getT_Expense_A() throws RuntimeException
    {
		return (I_C_ValidCombination)MTable.get(getCtx(), I_C_ValidCombination.Table_Name)
			.getPO(getT_Expense_Acct(), get_TrxName());	}

	/** Set Tax Expense.
		@param T_Expense_Acct 
		Account for paid tax you cannot reclaim
	  */
	public void setT_Expense_Acct (int T_Expense_Acct)
	{
		set_Value (COLUMNNAME_T_Expense_Acct, Integer.valueOf(T_Expense_Acct));
	}

	/** Get Tax Expense.
		@return Account for paid tax you cannot reclaim
	  */
	public int getT_Expense_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_T_Expense_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}