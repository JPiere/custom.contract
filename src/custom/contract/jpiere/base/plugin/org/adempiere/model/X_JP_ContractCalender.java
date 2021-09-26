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

/** Generated Model for JP_ContractCalender
 *  @author iDempiere (generated) 
 *  @version Release 8.2 - $Id$ */
public class X_JP_ContractCalender extends PO implements I_JP_ContractCalender, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20210926L;

    /** Standard Constructor */
    public X_JP_ContractCalender (Properties ctx, int JP_ContractCalender_ID, String trxName)
    {
      super (ctx, JP_ContractCalender_ID, trxName);
      /** if (JP_ContractCalender_ID == 0)
        {
			setJP_ContractCalender_ID (0);
			setName (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_JP_ContractCalender (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_JP_ContractCalender[")
        .append(get_ID()).append(",Name=").append(getName()).append("]");
      return sb.toString();
    }

	/** Set Description.
		@param Description 
		Optional short description of the record
	  */
	public void setDescription (String Description)
	{
		set_Value (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	public String getDescription () 
	{
		return (String)get_Value(COLUMNNAME_Description);
	}

	/** Set Contract Calender.
		@param JP_ContractCalender_ID Contract Calender	  */
	public void setJP_ContractCalender_ID (int JP_ContractCalender_ID)
	{
		if (JP_ContractCalender_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_JP_ContractCalender_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_JP_ContractCalender_ID, Integer.valueOf(JP_ContractCalender_ID));
	}

	/** Get Contract Calender.
		@return Contract Calender	  */
	public int getJP_ContractCalender_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_JP_ContractCalender_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set JP_ContractCalender_UU.
		@param JP_ContractCalender_UU JP_ContractCalender_UU	  */
	public void setJP_ContractCalender_UU (String JP_ContractCalender_UU)
	{
		set_ValueNoCheck (COLUMNNAME_JP_ContractCalender_UU, JP_ContractCalender_UU);
	}

	/** Get JP_ContractCalender_UU.
		@return JP_ContractCalender_UU	  */
	public String getJP_ContractCalender_UU () 
	{
		return (String)get_Value(COLUMNNAME_JP_ContractCalender_UU);
	}

	public I_JP_ContractCategory getJP_ContractCategory() throws RuntimeException
    {
		return (I_JP_ContractCategory)MTable.get(getCtx(), I_JP_ContractCategory.Table_Name)
			.getPO(getJP_ContractCategory_ID(), get_TrxName());	}

	/** Set Contract Category.
		@param JP_ContractCategory_ID Contract Category	  */
	public void setJP_ContractCategory_ID (int JP_ContractCategory_ID)
	{
		if (JP_ContractCategory_ID < 1) 
			set_Value (COLUMNNAME_JP_ContractCategory_ID, null);
		else 
			set_Value (COLUMNNAME_JP_ContractCategory_ID, Integer.valueOf(JP_ContractCategory_ID));
	}

	/** Get Contract Category.
		@return Contract Category	  */
	public int getJP_ContractCategory_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_JP_ContractCategory_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_JP_ContractContent getJP_ContractContent() throws RuntimeException
    {
		return (I_JP_ContractContent)MTable.get(getCtx(), I_JP_ContractContent.Table_Name)
			.getPO(getJP_ContractContent_ID(), get_TrxName());	}

	/** Set Contract Content.
		@param JP_ContractContent_ID Contract Content	  */
	public void setJP_ContractContent_ID (int JP_ContractContent_ID)
	{
		if (JP_ContractContent_ID < 1) 
			set_Value (COLUMNNAME_JP_ContractContent_ID, null);
		else 
			set_Value (COLUMNNAME_JP_ContractContent_ID, Integer.valueOf(JP_ContractContent_ID));
	}

	/** Get Contract Content.
		@return Contract Content	  */
	public int getJP_ContractContent_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_JP_ContractContent_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_JP_ContractLine getJP_ContractLine() throws RuntimeException
    {
		return (I_JP_ContractLine)MTable.get(getCtx(), I_JP_ContractLine.Table_Name)
			.getPO(getJP_ContractLine_ID(), get_TrxName());	}

	/** Set Contract Content Line.
		@param JP_ContractLine_ID Contract Content Line	  */
	public void setJP_ContractLine_ID (int JP_ContractLine_ID)
	{
		if (JP_ContractLine_ID < 1) 
			set_Value (COLUMNNAME_JP_ContractLine_ID, null);
		else 
			set_Value (COLUMNNAME_JP_ContractLine_ID, Integer.valueOf(JP_ContractLine_ID));
	}

	/** Get Contract Content Line.
		@return Contract Content Line	  */
	public int getJP_ContractLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_JP_ContractLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Name.
		@param Name 
		Alphanumeric identifier of the entity
	  */
	public void setName (String Name)
	{
		set_Value (COLUMNNAME_Name, Name);
	}

	/** Get Name.
		@return Alphanumeric identifier of the entity
	  */
	public String getName () 
	{
		return (String)get_Value(COLUMNNAME_Name);
	}

	/** Set Search Key.
		@param Value 
		Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value)
	{
		set_Value (COLUMNNAME_Value, Value);
	}

	/** Get Search Key.
		@return Search key for the record in the format required - must be unique
	  */
	public String getValue () 
	{
		return (String)get_Value(COLUMNNAME_Value);
	}
}