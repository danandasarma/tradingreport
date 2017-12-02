package com.jpmc.trading.reportengine;

import com.jpmc.trading.reportengine.exception.ApplicationException;
import com.jpmc.trading.reportengine.service.ReportEngineService;

/**
 * The Class ReportEngineApplication.
 */
public class ReportEngineApplication {

	/** The report engine service. */
	private static ReportEngineService reportEngineService;

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(final String[] args) {
		System.out.println("Reporting eninge application...");
		try {
			reportEngineService = new ReportEngineService();

			reportEngineService.getIncomingInstructions();
			
			reportEngineService.getOutgoingInstructions();
		} catch (ApplicationException e) {
		}
	}
}
