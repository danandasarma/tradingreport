package com.jpmc.trading.reportengine.service;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jpmc.trading.reportengine.dto.Entity;
import com.jpmc.trading.reportengine.dto.Instruction;
import com.jpmc.trading.reportengine.dto.TradeType;
import com.jpmc.trading.reportengine.exception.ApplicationException;
import com.jpmc.trading.reportengine.parser.IDataParser;
import com.jpmc.trading.reportengine.parser.InstructionCSVParser;
import com.jpmc.trading.reportengine.util.ReportEngineUtil;

/**
 * The Class ReportEngineService.
 */
public class ReportEngineService {

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(ReportEngineService.class);

	/** The instruction parser. */
	private IDataParser instructionParser;

	/** The instructions. */
	private List<Instruction> filteredInstructions;

	private NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("en", "US"));

	private String message = "";

	public void printDailyTradeAmounts(TradeType tradeType) throws ApplicationException {

		if (tradeType.name().equals(TradeType.BUY.name())) {
			message = "Outgoing trade amount on ";
		} else {
			message = "Incoming trade amount on ";
		}

		filteredInstructions = getFilteredInstructions(tradeType);

		Map<LocalDate, Double> dailyTradeAmountsMap = ReportEngineUtil.calculateDailyTradeAmounts(filteredInstructions);

		dailyTradeAmountsMap.entrySet().stream().forEach(instruction -> logger
				.info(message + instruction.getKey() + " is " + currencyFormatter.format(instruction.getValue())));
	}

	public void printEntitiesRanking(TradeType tradeType) throws ApplicationException {

		if (tradeType.name().equals(TradeType.BUY.name())) {
			message = "Outgoing entities based on ranking \n **********************************";
		} else {
			message = "Incoming entities based on ranking \n **********************************";
		}

		filteredInstructions = getFilteredInstructions(tradeType);

		List<Entity> entities = ReportEngineUtil.getEntityRankings(filteredInstructions);

		logger.info(message);
		logger.info("Entity Name \tTrade Amount");
		entities.stream().forEach(
				entity -> logger.info(entity.getName() + "\t" + currencyFormatter.format(entity.getTradeAmount())));
	}

	private List<Instruction> getFilteredInstructions(TradeType tradeType) throws ApplicationException {

		instructionParser = new InstructionCSVParser();

		List<Instruction> instructions = instructionParser.parseInstructions();

		filteredInstructions = instructions.stream()
				.filter(instruction -> tradeType.name().equalsIgnoreCase(instruction.getTradeType()))
				.collect(Collectors.toList()).stream().map(ReportEngineUtil.adjustSettlementDate())
				.collect(Collectors.toList());

		filteredInstructions.sort((instruction1, instruction2) -> instruction1.getSettlementDate()
				.compareTo(instruction2.getSettlementDate()));

		return filteredInstructions;
	}
}
