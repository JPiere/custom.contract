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
package custom.contract.jpiere.base.plugin.org.adempiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Interface for JP_EstimationLine
 *  @author iDempiere (generated) 
 *  @version Release 9
 */
@SuppressWarnings("all")
public interface I_JP_EstimationLine 
{

    /** TableName=JP_EstimationLine */
    public static final String Table_Name = "JP_EstimationLine";

    /** AD_Table_ID=1000081 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 1 - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(1);

    /** Load Meta Data */

    /** Column name AD_Client_ID */
    public static final String COLUMNNAME_AD_Client_ID = "AD_Client_ID";

	/** Get Client.
	  * Client/Tenant for this installation.
	  */
	public int getAD_Client_ID();

    /** Column name AD_OrgTrx_ID */
    public static final String COLUMNNAME_AD_OrgTrx_ID = "AD_OrgTrx_ID";

	/** Set Trx Organization.
	  * Performing or initiating organization
	  */
	public void setAD_OrgTrx_ID (int AD_OrgTrx_ID);

	/** Get Trx Organization.
	  * Performing or initiating organization
	  */
	public int getAD_OrgTrx_ID();

    /** Column name AD_Org_ID */
    public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

	/** Set Organization.
	  * Organizational entity within client
	  */
	public void setAD_Org_ID (int AD_Org_ID);

	/** Get Organization.
	  * Organizational entity within client
	  */
	public int getAD_Org_ID();

    /** Column name C_Activity_ID */
    public static final String COLUMNNAME_C_Activity_ID = "C_Activity_ID";

	/** Set Activity.
	  * Business Activity
	  */
	public void setC_Activity_ID (int C_Activity_ID);

	/** Get Activity.
	  * Business Activity
	  */
	public int getC_Activity_ID();

	public org.compiere.model.I_C_Activity getC_Activity() throws RuntimeException;

    /** Column name C_BPartner_ID */
    public static final String COLUMNNAME_C_BPartner_ID = "C_BPartner_ID";

	/** Set Business Partner.
	  * Identifies a Business Partner
	  */
	public void setC_BPartner_ID (int C_BPartner_ID);

	/** Get Business Partner.
	  * Identifies a Business Partner
	  */
	public int getC_BPartner_ID();

	public org.compiere.model.I_C_BPartner getC_BPartner() throws RuntimeException;

    /** Column name C_BPartner_Location_ID */
    public static final String COLUMNNAME_C_BPartner_Location_ID = "C_BPartner_Location_ID";

	/** Set Partner Location.
	  * Identifies the (ship to) address for this Business Partner
	  */
	public void setC_BPartner_Location_ID (int C_BPartner_Location_ID);

	/** Get Partner Location.
	  * Identifies the (ship to) address for this Business Partner
	  */
	public int getC_BPartner_Location_ID();

	public org.compiere.model.I_C_BPartner_Location getC_BPartner_Location() throws RuntimeException;

    /** Column name C_Campaign_ID */
    public static final String COLUMNNAME_C_Campaign_ID = "C_Campaign_ID";

	/** Set Campaign.
	  * Marketing Campaign
	  */
	public void setC_Campaign_ID (int C_Campaign_ID);

	/** Get Campaign.
	  * Marketing Campaign
	  */
	public int getC_Campaign_ID();

	public org.compiere.model.I_C_Campaign getC_Campaign() throws RuntimeException;

    /** Column name C_Charge_ID */
    public static final String COLUMNNAME_C_Charge_ID = "C_Charge_ID";

	/** Set Charge.
	  * Additional document charges
	  */
	public void setC_Charge_ID (int C_Charge_ID);

	/** Get Charge.
	  * Additional document charges
	  */
	public int getC_Charge_ID();

	public org.compiere.model.I_C_Charge getC_Charge() throws RuntimeException;

    /** Column name C_Currency_ID */
    public static final String COLUMNNAME_C_Currency_ID = "C_Currency_ID";

	/** Set Currency.
	  * The Currency for this record
	  */
	public void setC_Currency_ID (int C_Currency_ID);

	/** Get Currency.
	  * The Currency for this record
	  */
	public int getC_Currency_ID();

	public org.compiere.model.I_C_Currency getC_Currency() throws RuntimeException;

    /** Column name C_ProjectPhase_ID */
    public static final String COLUMNNAME_C_ProjectPhase_ID = "C_ProjectPhase_ID";

	/** Set Project Phase.
	  * Phase of a Project
	  */
	public void setC_ProjectPhase_ID (int C_ProjectPhase_ID);

	/** Get Project Phase.
	  * Phase of a Project
	  */
	public int getC_ProjectPhase_ID();

	public org.compiere.model.I_C_ProjectPhase getC_ProjectPhase() throws RuntimeException;

    /** Column name C_ProjectTask_ID */
    public static final String COLUMNNAME_C_ProjectTask_ID = "C_ProjectTask_ID";

	/** Set Project Task.
	  * Actual Project Task in a Phase
	  */
	public void setC_ProjectTask_ID (int C_ProjectTask_ID);

	/** Get Project Task.
	  * Actual Project Task in a Phase
	  */
	public int getC_ProjectTask_ID();

	public org.compiere.model.I_C_ProjectTask getC_ProjectTask() throws RuntimeException;

    /** Column name C_Project_ID */
    public static final String COLUMNNAME_C_Project_ID = "C_Project_ID";

	/** Set Project.
	  * Financial Project
	  */
	public void setC_Project_ID (int C_Project_ID);

	/** Get Project.
	  * Financial Project
	  */
	public int getC_Project_ID();

	public org.compiere.model.I_C_Project getC_Project() throws RuntimeException;

    /** Column name C_Tax_ID */
    public static final String COLUMNNAME_C_Tax_ID = "C_Tax_ID";

	/** Set Tax.
	  * Tax identifier
	  */
	public void setC_Tax_ID (int C_Tax_ID);

	/** Get Tax.
	  * Tax identifier
	  */
	public int getC_Tax_ID();

	public org.compiere.model.I_C_Tax getC_Tax() throws RuntimeException;

    /** Column name C_UOM_ID */
    public static final String COLUMNNAME_C_UOM_ID = "C_UOM_ID";

	/** Set UOM.
	  * Unit of Measure
	  */
	public void setC_UOM_ID (int C_UOM_ID);

	/** Get UOM.
	  * Unit of Measure
	  */
	public int getC_UOM_ID();

	public org.compiere.model.I_C_UOM getC_UOM() throws RuntimeException;

    /** Column name Created */
    public static final String COLUMNNAME_Created = "Created";

	/** Get Created.
	  * Date this record was created
	  */
	public Timestamp getCreated();

    /** Column name CreatedBy */
    public static final String COLUMNNAME_CreatedBy = "CreatedBy";

	/** Get Created By.
	  * User who created this records
	  */
	public int getCreatedBy();

    /** Column name DateDelivered */
    public static final String COLUMNNAME_DateDelivered = "DateDelivered";

	/** Set Date Delivered.
	  * Date when the product was delivered
	  */
	public void setDateDelivered (Timestamp DateDelivered);

	/** Get Date Delivered.
	  * Date when the product was delivered
	  */
	public Timestamp getDateDelivered();

    /** Column name DateInvoiced */
    public static final String COLUMNNAME_DateInvoiced = "DateInvoiced";

	/** Set Date Invoiced.
	  * Date printed on Invoice
	  */
	public void setDateInvoiced (Timestamp DateInvoiced);

	/** Get Date Invoiced.
	  * Date printed on Invoice
	  */
	public Timestamp getDateInvoiced();

    /** Column name DateOrdered */
    public static final String COLUMNNAME_DateOrdered = "DateOrdered";

	/** Set Date Ordered.
	  * Date of Order
	  */
	public void setDateOrdered (Timestamp DateOrdered);

	/** Get Date Ordered.
	  * Date of Order
	  */
	public Timestamp getDateOrdered();

    /** Column name DatePromised */
    public static final String COLUMNNAME_DatePromised = "DatePromised";

	/** Set Date Promised.
	  * Date Order was promised
	  */
	public void setDatePromised (Timestamp DatePromised);

	/** Get Date Promised.
	  * Date Order was promised
	  */
	public Timestamp getDatePromised();

    /** Column name Description */
    public static final String COLUMNNAME_Description = "Description";

	/** Set Description.
	  * Optional short description of the record
	  */
	public void setDescription (String Description);

	/** Get Description.
	  * Optional short description of the record
	  */
	public String getDescription();

    /** Column name Discount */
    public static final String COLUMNNAME_Discount = "Discount";

	/** Set Discount %.
	  * Discount in percent
	  */
	public void setDiscount (BigDecimal Discount);

	/** Get Discount %.
	  * Discount in percent
	  */
	public BigDecimal getDiscount();

    /** Column name FreightAmt */
    public static final String COLUMNNAME_FreightAmt = "FreightAmt";

	/** Set Freight Amount.
	  * Freight Amount 
	  */
	public void setFreightAmt (BigDecimal FreightAmt);

	/** Get Freight Amount.
	  * Freight Amount 
	  */
	public BigDecimal getFreightAmt();

    /** Column name IsActive */
    public static final String COLUMNNAME_IsActive = "IsActive";

	/** Set Active.
	  * The record is active in the system
	  */
	public void setIsActive (boolean IsActive);

	/** Get Active.
	  * The record is active in the system
	  */
	public boolean isActive();

    /** Column name IsDescription */
    public static final String COLUMNNAME_IsDescription = "IsDescription";

	/** Set Description Only.
	  * if true, the line is just description and no transaction
	  */
	public void setIsDescription (boolean IsDescription);

	/** Get Description Only.
	  * if true, the line is just description and no transaction
	  */
	public boolean isDescription();

    /** Column name JP_ASI_From_ID */
    public static final String COLUMNNAME_JP_ASI_From_ID = "JP_ASI_From_ID";

	/** Set Attribute Info(From)	  */
	public void setJP_ASI_From_ID (int JP_ASI_From_ID);

	/** Get Attribute Info(From)	  */
	public int getJP_ASI_From_ID();

	public I_M_AttributeSetInstance getJP_ASI_From() throws RuntimeException;

    /** Column name JP_ASI_To_ID */
    public static final String COLUMNNAME_JP_ASI_To_ID = "JP_ASI_To_ID";

	/** Set Attribute Info(To)	  */
	public void setJP_ASI_To_ID (int JP_ASI_To_ID);

	/** Get Attribute Info(To)	  */
	public int getJP_ASI_To_ID();

	public I_M_AttributeSetInstance getJP_ASI_To() throws RuntimeException;

    /** Column name JP_CommunicationColumn */
    public static final String COLUMNNAME_JP_CommunicationColumn = "JP_CommunicationColumn";

	/** Set Communication Column	  */
	public void setJP_CommunicationColumn (String JP_CommunicationColumn);

	/** Get Communication Column	  */
	public String getJP_CommunicationColumn();

    /** Column name JP_EstimationLine_ID */
    public static final String COLUMNNAME_JP_EstimationLine_ID = "JP_EstimationLine_ID";

	/** Set Estimation &amp;
 Handwritten Line	  */
	public void setJP_EstimationLine_ID (int JP_EstimationLine_ID);

	/** Get Estimation &amp;
 Handwritten Line	  */
	public int getJP_EstimationLine_ID();

    /** Column name JP_EstimationLine_UU */
    public static final String COLUMNNAME_JP_EstimationLine_UU = "JP_EstimationLine_UU";

	/** Set JP_EstimationLine_UU	  */
	public void setJP_EstimationLine_UU (String JP_EstimationLine_UU);

	/** Get JP_EstimationLine_UU	  */
	public String getJP_EstimationLine_UU();

    /** Column name JP_Estimation_ID */
    public static final String COLUMNNAME_JP_Estimation_ID = "JP_Estimation_ID";

	/** Set Estimation &amp;
 Handwritten	  */
	public void setJP_Estimation_ID (int JP_Estimation_ID);

	/** Get Estimation &amp;
 Handwritten	  */
	public int getJP_Estimation_ID();

	public I_JP_Estimation getJP_Estimation() throws RuntimeException;

    /** Column name JP_LocatorFrom_ID */
    public static final String COLUMNNAME_JP_LocatorFrom_ID = "JP_LocatorFrom_ID";

	/** Set Locator(From)	  */
	public void setJP_LocatorFrom_ID (int JP_LocatorFrom_ID);

	/** Get Locator(From)	  */
	public int getJP_LocatorFrom_ID();

	public org.compiere.model.I_M_Locator getJP_LocatorFrom() throws RuntimeException;

    /** Column name JP_LocatorTo_ID */
    public static final String COLUMNNAME_JP_LocatorTo_ID = "JP_LocatorTo_ID";

	/** Set Locator(To)	  */
	public void setJP_LocatorTo_ID (int JP_LocatorTo_ID);

	/** Get Locator(To)	  */
	public int getJP_LocatorTo_ID();

	public org.compiere.model.I_M_Locator getJP_LocatorTo() throws RuntimeException;

    /** Column name JP_Locator_ID */
    public static final String COLUMNNAME_JP_Locator_ID = "JP_Locator_ID";

	/** Set Locator	  */
	public void setJP_Locator_ID (int JP_Locator_ID);

	/** Get Locator	  */
	public int getJP_Locator_ID();

	public org.compiere.model.I_M_Locator getJP_Locator() throws RuntimeException;

    /** Column name JP_ScheduledCost */
    public static final String COLUMNNAME_JP_ScheduledCost = "JP_ScheduledCost";

	/** Set Scheduled Cost	  */
	public void setJP_ScheduledCost (BigDecimal JP_ScheduledCost);

	/** Get Scheduled Cost	  */
	public BigDecimal getJP_ScheduledCost();

    /** Column name JP_ScheduledCostLineAmt */
    public static final String COLUMNNAME_JP_ScheduledCostLineAmt = "JP_ScheduledCostLineAmt";

	/** Set Scheduled Cost Line Amt	  */
	public void setJP_ScheduledCostLineAmt (BigDecimal JP_ScheduledCostLineAmt);

	/** Get Scheduled Cost Line Amt	  */
	public BigDecimal getJP_ScheduledCostLineAmt();

    /** Column name JP_TaxAmt */
    public static final String COLUMNNAME_JP_TaxAmt = "JP_TaxAmt";

	/** Set Tax Amt	  */
	public void setJP_TaxAmt (BigDecimal JP_TaxAmt);

	/** Get Tax Amt	  */
	public BigDecimal getJP_TaxAmt();

    /** Column name JP_TaxBaseAmt */
    public static final String COLUMNNAME_JP_TaxBaseAmt = "JP_TaxBaseAmt";

	/** Set Tax base Amt	  */
	public void setJP_TaxBaseAmt (BigDecimal JP_TaxBaseAmt);

	/** Get Tax base Amt	  */
	public BigDecimal getJP_TaxBaseAmt();

    /** Column name Line */
    public static final String COLUMNNAME_Line = "Line";

	/** Set Line No.
	  * Unique line for this document
	  */
	public void setLine (int Line);

	/** Get Line No.
	  * Unique line for this document
	  */
	public int getLine();

    /** Column name LineNetAmt */
    public static final String COLUMNNAME_LineNetAmt = "LineNetAmt";

	/** Set Line Amount.
	  * Line Extended Amount (Quantity * Actual Price) without Freight and Charges
	  */
	public void setLineNetAmt (BigDecimal LineNetAmt);

	/** Get Line Amount.
	  * Line Extended Amount (Quantity * Actual Price) without Freight and Charges
	  */
	public BigDecimal getLineNetAmt();

    /** Column name Link_OrderLine_ID */
    public static final String COLUMNNAME_Link_OrderLine_ID = "Link_OrderLine_ID";

	/** Set Linked Order Line.
	  * This field links a sales order line to the purchase order line that is generated from it.
	  */
	public void setLink_OrderLine_ID (int Link_OrderLine_ID);

	/** Get Linked Order Line.
	  * This field links a sales order line to the purchase order line that is generated from it.
	  */
	public int getLink_OrderLine_ID();

	public org.compiere.model.I_C_OrderLine getLink_OrderLine() throws RuntimeException;

    /** Column name M_AttributeSetInstance_ID */
    public static final String COLUMNNAME_M_AttributeSetInstance_ID = "M_AttributeSetInstance_ID";

	/** Set Attribute Info.
	  * Product Attribute Set Instance
	  */
	public void setM_AttributeSetInstance_ID (int M_AttributeSetInstance_ID);

	/** Get Attribute Info.
	  * Product Attribute Set Instance
	  */
	public int getM_AttributeSetInstance_ID();

	public I_M_AttributeSetInstance getM_AttributeSetInstance() throws RuntimeException;

    /** Column name M_Product_ID */
    public static final String COLUMNNAME_M_Product_ID = "M_Product_ID";

	/** Set Product.
	  * Product, Service, Item
	  */
	public void setM_Product_ID (int M_Product_ID);

	/** Get Product.
	  * Product, Service, Item
	  */
	public int getM_Product_ID();

	public org.compiere.model.I_M_Product getM_Product() throws RuntimeException;

    /** Column name M_Promotion_ID */
    public static final String COLUMNNAME_M_Promotion_ID = "M_Promotion_ID";

	/** Set Promotion	  */
	public void setM_Promotion_ID (int M_Promotion_ID);

	/** Get Promotion	  */
	public int getM_Promotion_ID();

	public org.compiere.model.I_M_Promotion getM_Promotion() throws RuntimeException;

    /** Column name M_Shipper_ID */
    public static final String COLUMNNAME_M_Shipper_ID = "M_Shipper_ID";

	/** Set Shipper.
	  * Method or manner of product delivery
	  */
	public void setM_Shipper_ID (int M_Shipper_ID);

	/** Get Shipper.
	  * Method or manner of product delivery
	  */
	public int getM_Shipper_ID();

	public org.compiere.model.I_M_Shipper getM_Shipper() throws RuntimeException;

    /** Column name M_Warehouse_ID */
    public static final String COLUMNNAME_M_Warehouse_ID = "M_Warehouse_ID";

	/** Set Org Warehouse.
	  * Storage Warehouse and Service Point
	  */
	public void setM_Warehouse_ID (int M_Warehouse_ID);

	/** Get Org Warehouse.
	  * Storage Warehouse and Service Point
	  */
	public int getM_Warehouse_ID();

	public org.compiere.model.I_M_Warehouse getM_Warehouse() throws RuntimeException;

    /** Column name PP_Cost_Collector_ID */
    public static final String COLUMNNAME_PP_Cost_Collector_ID = "PP_Cost_Collector_ID";

	/** Set Manufacturing Cost Collector	  */
	public void setPP_Cost_Collector_ID (int PP_Cost_Collector_ID);

	/** Get Manufacturing Cost Collector	  */
	public int getPP_Cost_Collector_ID();

	public org.eevolution.model.I_PP_Cost_Collector getPP_Cost_Collector() throws RuntimeException;

    /** Column name PriceActual */
    public static final String COLUMNNAME_PriceActual = "PriceActual";

	/** Set Unit Price.
	  * Actual Price 
	  */
	public void setPriceActual (BigDecimal PriceActual);

	/** Get Unit Price.
	  * Actual Price 
	  */
	public BigDecimal getPriceActual();

    /** Column name PriceCost */
    public static final String COLUMNNAME_PriceCost = "PriceCost";

	/** Set Cost Price.
	  * Price per Unit of Measure including all indirect costs (Freight, etc.)
	  */
	public void setPriceCost (BigDecimal PriceCost);

	/** Get Cost Price.
	  * Price per Unit of Measure including all indirect costs (Freight, etc.)
	  */
	public BigDecimal getPriceCost();

    /** Column name PriceEntered */
    public static final String COLUMNNAME_PriceEntered = "PriceEntered";

	/** Set Price.
	  * Price Entered - the price based on the selected/base UoM
	  */
	public void setPriceEntered (BigDecimal PriceEntered);

	/** Get Price.
	  * Price Entered - the price based on the selected/base UoM
	  */
	public BigDecimal getPriceEntered();

    /** Column name PriceLimit */
    public static final String COLUMNNAME_PriceLimit = "PriceLimit";

	/** Set Limit Price.
	  * Lowest price for a product
	  */
	public void setPriceLimit (BigDecimal PriceLimit);

	/** Get Limit Price.
	  * Lowest price for a product
	  */
	public BigDecimal getPriceLimit();

    /** Column name PriceList */
    public static final String COLUMNNAME_PriceList = "PriceList";

	/** Set List Price.
	  * List Price
	  */
	public void setPriceList (BigDecimal PriceList);

	/** Get List Price.
	  * List Price
	  */
	public BigDecimal getPriceList();

    /** Column name QtyDelivered */
    public static final String COLUMNNAME_QtyDelivered = "QtyDelivered";

	/** Set Delivered Qty.
	  * Delivered Quantity
	  */
	public void setQtyDelivered (BigDecimal QtyDelivered);

	/** Get Delivered Qty.
	  * Delivered Quantity
	  */
	public BigDecimal getQtyDelivered();

    /** Column name QtyEntered */
    public static final String COLUMNNAME_QtyEntered = "QtyEntered";

	/** Set Quantity.
	  * The Quantity Entered is based on the selected UoM
	  */
	public void setQtyEntered (BigDecimal QtyEntered);

	/** Get Quantity.
	  * The Quantity Entered is based on the selected UoM
	  */
	public BigDecimal getQtyEntered();

    /** Column name QtyInvoiced */
    public static final String COLUMNNAME_QtyInvoiced = "QtyInvoiced";

	/** Set Invoiced Qty.
	  * Invoiced Quantity
	  */
	public void setQtyInvoiced (BigDecimal QtyInvoiced);

	/** Get Invoiced Qty.
	  * Invoiced Quantity
	  */
	public BigDecimal getQtyInvoiced();

    /** Column name QtyLostSales */
    public static final String COLUMNNAME_QtyLostSales = "QtyLostSales";

	/** Set Lost Sales Qty.
	  * Quantity of potential sales
	  */
	public void setQtyLostSales (BigDecimal QtyLostSales);

	/** Get Lost Sales Qty.
	  * Quantity of potential sales
	  */
	public BigDecimal getQtyLostSales();

    /** Column name QtyOrdered */
    public static final String COLUMNNAME_QtyOrdered = "QtyOrdered";

	/** Set Ordered Qty.
	  * Ordered Quantity
	  */
	public void setQtyOrdered (BigDecimal QtyOrdered);

	/** Get Ordered Qty.
	  * Ordered Quantity
	  */
	public BigDecimal getQtyOrdered();

    /** Column name QtyReserved */
    public static final String COLUMNNAME_QtyReserved = "QtyReserved";

	/** Set Reserved Qty.
	  * Reserved Quantity
	  */
	public void setQtyReserved (BigDecimal QtyReserved);

	/** Get Reserved Qty.
	  * Reserved Quantity
	  */
	public BigDecimal getQtyReserved();

    /** Column name RRAmt */
    public static final String COLUMNNAME_RRAmt = "RRAmt";

	/** Set Revenue Recognition Amt.
	  * Revenue Recognition Amount
	  */
	public void setRRAmt (BigDecimal RRAmt);

	/** Get Revenue Recognition Amt.
	  * Revenue Recognition Amount
	  */
	public BigDecimal getRRAmt();

    /** Column name RRStartDate */
    public static final String COLUMNNAME_RRStartDate = "RRStartDate";

	/** Set Revenue Recognition Start.
	  * Revenue Recognition Start Date
	  */
	public void setRRStartDate (Timestamp RRStartDate);

	/** Get Revenue Recognition Start.
	  * Revenue Recognition Start Date
	  */
	public Timestamp getRRStartDate();

    /** Column name Ref_OrderLine_ID */
    public static final String COLUMNNAME_Ref_OrderLine_ID = "Ref_OrderLine_ID";

	/** Set Ref Order Line.
	  * Reference to corresponding Sales/Purchase Order
	  */
	public void setRef_OrderLine_ID (int Ref_OrderLine_ID);

	/** Get Ref Order Line.
	  * Reference to corresponding Sales/Purchase Order
	  */
	public int getRef_OrderLine_ID();

	public org.compiere.model.I_C_OrderLine getRef_OrderLine() throws RuntimeException;

    /** Column name S_ResourceAssignment_ID */
    public static final String COLUMNNAME_S_ResourceAssignment_ID = "S_ResourceAssignment_ID";

	/** Set Resource Assign.
	  * Resource Assignment
	  */
	public void setS_ResourceAssignment_ID (int S_ResourceAssignment_ID);

	/** Get Resource Assign.
	  * Resource Assignment
	  */
	public int getS_ResourceAssignment_ID();

    /** Column name Updated */
    public static final String COLUMNNAME_Updated = "Updated";

	/** Get Updated.
	  * Date this record was updated
	  */
	public Timestamp getUpdated();

    /** Column name UpdatedBy */
    public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

	/** Get Updated By.
	  * User who updated this records
	  */
	public int getUpdatedBy();

    /** Column name User1_ID */
    public static final String COLUMNNAME_User1_ID = "User1_ID";

	/** Set User Element List 1.
	  * User defined list element #1
	  */
	public void setUser1_ID (int User1_ID);

	/** Get User Element List 1.
	  * User defined list element #1
	  */
	public int getUser1_ID();

	public org.compiere.model.I_C_ElementValue getUser1() throws RuntimeException;

    /** Column name User2_ID */
    public static final String COLUMNNAME_User2_ID = "User2_ID";

	/** Set User Element List 2.
	  * User defined list element #2
	  */
	public void setUser2_ID (int User2_ID);

	/** Get User Element List 2.
	  * User defined list element #2
	  */
	public int getUser2_ID();

	public org.compiere.model.I_C_ElementValue getUser2() throws RuntimeException;
}
