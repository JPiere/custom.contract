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
package custom.contract.jpiere.base.plugin.factory;

import java.util.logging.Level;

import org.adempiere.base.IProcessFactory;
import org.adempiere.base.equinox.EquinoxExtensionLocator;
import org.compiere.process.ProcessCall;
import org.compiere.util.CLogger;

/**
 *  JPIERE-0506 Custom Contract
 *
 *  Custom Contrat Process Factory
 *
 *
 *  @author Hideaki Hagiwara(h.hagiwara@oss-erp.co.jp)
 *
 */
public class CustomContractProcessFactory implements IProcessFactory {

	private final static CLogger log = CLogger.getCLogger(CustomContractProcessFactory.class);

	/**
	 * default constructor
	 */
	public CustomContractProcessFactory() {
	}

	/* (non-Javadoc)
	 * @see org.adempiere.base.IProcessFactory#newProcessInstance(java.lang.String)
	 */
	@Override
	public ProcessCall newProcessInstance(String className) {

		if (className.startsWith("custom.contract."))
		{
			ProcessCall process = null;
			process = EquinoxExtensionLocator.instance().locate(ProcessCall.class, "custom.contract.jpiere.base.plugin.org.adempiere.process", className, null).getExtension();
			if (process == null) {
				//Get Class
				Class<?> processClass = null;
				//use context classloader if available
				ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
				if (classLoader != null)
				{
					try
					{
						processClass = classLoader.loadClass(className);
					}
					catch (ClassNotFoundException ex)
					{
						if (log.isLoggable(Level.FINE))log.log(Level.FINE, className, ex);
					}
				}
				if (processClass == null)
				{
					classLoader = this.getClass().getClassLoader();
					try
					{
						processClass = classLoader.loadClass(className);
					}
					catch (ClassNotFoundException ex)
					{
						log.log(Level.WARNING, className, ex);
						return null;
					}
				}

				if (processClass == null) {
					return null;
				}

				//Get Process
				try
				{
					process = (ProcessCall)processClass.getDeclaredConstructor().newInstance();
				}
				catch (Exception ex)
				{
					log.log(Level.WARNING, "Instance for " + className, ex);
					return null;
				}
			}
			return process;
		}//if (className.startsWith("jpiere.plugin.bulkimport.process"))
		return null;
	}

}
