package com.jpmc.trading.reportengine.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.jpmc.trading.reportengine.dto.Instruction;
import com.jpmc.trading.reportengine.exception.ApplicationException;
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
		instructionParser = new InstructionCSVParser();

		instructions = instructionParser.parseInstructions();

		Map<LocalDate, Instruction> incomingInstructionsMap = instructions.stream()
				.filter(instruction -> TRADE_TYPE_BUY.equals(instruction.getTradeType()))
				.collect(Collectors.toMap(Instruction::getSettlementDate, Function.identity()));
		
		System.out.println("Incoming instructions:");

		incomingInstructionsMap.entrySet().stream().forEach(e -> System.out.println(e.getKey() + ":" + e.getValue()));
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
