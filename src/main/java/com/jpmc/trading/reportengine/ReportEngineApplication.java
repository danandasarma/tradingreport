package com.jpmc.trading.reportengine;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jpmc.trading.reportengine.dto.TradeType;
import com.jpmc.trading.reportengine.exception.ApplicationException;
import com.jpmc.trading.reportengine.service.ReportEngineService;

/**
 * The Class ReportEngineApplication.
 */
public class ReportEngineApplication {

	/** The report engine service. */
	private static ReportEngineService reportEngineService;

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(ReportEngineApplication.class);

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 * @throws ApplicationException
	 */
	public static void main(final String[] args) throws ApplicationException {
		logger.info("Reporting eninge application...");
		try {
			reportEngineService = new ReportEngineService();

			HelpFormatter formatter = new HelpFormatter();

			Options options = new Options();

			// add options
			options.addOption("ISR", false, "Generate incoming settlement report");
			options.addOption("OSR", false, "Generate outgoing settlement report");
			options.addOption("IRR", false, "Generate incoming rankings report");
			options.addOption("ORR", false, "Generate outgoing rankings report");

			formatter.printHelp("report", options);

			CommandLineParser parser = new DefaultParser();
			CommandLine cmd = parser.parse(options, args);

			if (cmd.hasOption("ISR")) {
				// Generate incoming settlement report
				reportEngineService.printDailyTradeAmounts(TradeType.SELL);
			} else if (cmd.hasOption("OSR")) {
				// Generate outgoing settlement report
				reportEngineService.printDailyTradeAmounts(TradeType.BUY);
			} else if (cmd.hasOption("IRR")) {
				// Generate incoming rankings report
				reportEngineService.printEntitiesRanking(TradeType.SELL);
			} else if (cmd.hasOption("ORR")) {
				// Generate outgoing rankings report
				reportEngineService.printEntitiesRanking(TradeType.BUY);
			} else {
				logger.error("Invalid option passed. Please check the usage");
			}
		} catch (ApplicationException e) {
			logger.error("ApplicationException while generating the report:", e);
			throw e;
		} catch (ParseException e) {
			logger.error("ParseException while generating the report:", e);
			throw new ApplicationException(e.getMessage(), e);
		}
	}
}
