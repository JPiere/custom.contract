package custom.contract.jpiere.base.plugin.org.adempiere.model;

import java.sql.ResultSet;
import java.util.Properties;


public class MContractLog extends X_JP_ContractLog {
	
	//Info
	public int createDocNum = 0;
	
	//To be confirmed
	public int confirmNum = 0;
	public int skipContractContentNum = 0;
	public int skipContractLineNum = 0;
	
	//Not fine
	public int warnNum = 0;
	public int errorNum = 0;

	

	
	public MContractLog(Properties ctx, int JP_ContractLog_ID, String trxName) 
	{
		super(ctx, JP_ContractLog_ID, trxName);
		
	}
	
	public MContractLog(Properties ctx, ResultSet rs, String trxName) 
	{
		super(ctx, rs, trxName);
	}
	
	
}
