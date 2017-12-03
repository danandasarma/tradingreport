package com.jpmc.trading.reportengine.service;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.jpmc.trading.reportengine.dto.Instruction;
import com.jpmc.trading.reportengine.exception.ApplicationException;
import com.jpmc.trading.reportengine.operations.InstructionPredicate;
import com.jpmc.trading.reportengine.parser.IDataParser;
import com.jpmc.trading.reportengine.parser.InstructionCSVParser;

/**
 * The Class ReportEngineService.
 */
public class ReportEngineService {

	/** The instruction parser. */
	private IDataParser instructionParser;

	/** The instructions. */
	private List<Instruction> instructions = new ArrayList<>();

	/** The Constant TRADE_TYPE_BUY. */
	private static final String TRADE_TYPE_BUY = "Buy";

	/** The Constant TRADE_TYPE_SELL. */
	private static final String TRADE_TYPE_SELL = "Sell";

	/**
	 * Gets the incoming instructions.
	 *
	 * @return the incoming instructions
	 * @throws ApplicationException
	 *             the application exception
	 */
	public void getIncomingInstructions() throws ApplicationException {

		Map<LocalDate, Double> dailyTradeAmountsMap = new LinkedHashMap<>();
		System.out.println("Parsing incoming instructions...");
		instructionParser = new InstructionCSVParser();

		instructions = instructionParser.parseInstructions();

		List<Instruction> incomingInstructions = instructions.stream()
				.filter(instruction -> TRADE_TYPE_BUY.equals(instruction.getTradeType())).collect(Collectors.toList());

		incomingInstructions.sort((instruction1, instruction2) -> instruction1.getSettlementDate()
				.compareTo(instruction2.getSettlementDate()));

		System.out.println("Incoming instructions:");

		incomingInstructions.forEach(instruction -> System.out.println(instruction));

		for (int day = 1; day <= 30; day++) {
			Double tradeAmount = 0d;
			LocalDate currentDate = LocalDate.of(2017, Month.NOVEMBER, day);
			List<Instruction> settlementDateList = incomingInstructions.stream()
					.filter(InstructionPredicate.isForSettlement(currentDate)).collect(Collectors.toList());

			for (Instruction instruction : settlementDateList) {
				Double settlementAmount = instruction.getPricePerUnit() * instruction.getUnits()
						* instruction.getExchangeRate();

				tradeAmount += settlementAmount;
			}
			dailyTradeAmountsMap.put(currentDate, tradeAmount);
		}

		NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("en", "US"));

		dailyTradeAmountsMap.entrySet().stream().forEach(instruction -> System.out.println(
				"Trade amount on " + instruction.getKey() + " is " + currencyFormatter.format(instruction.getValue())));
	}

	/**
	 * Gets the outgoing instructions.
	 *
	 * @return the outgoing instructions
	 * @throws ApplicationException
	 *             the application exception
	 */
	public void getOutgoingInstructions() throws ApplicationException {
		instructionParser = new InstructionCSVParser();

		instructions = instructionParser.parseInstructions();

		Map<LocalDate, Instruction> outgoingInstructionsMap = instructions.stream()
				.filter(instruction -> TRADE_TYPE_SELL.equals(instruction.getTradeType())).sorted()
				.collect(Collectors.toMap(Instruction::getSettlementDate, Function.identity()));

		System.out.println("Outgoing instructions:");

		outgoingInstructionsMap.entrySet().stream().forEach(e -> System.out.println(e.getKey() + ":" + e.getValue()));
	}
}
