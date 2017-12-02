package com.jpmc.trading.reportengine.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import com.jpmc.trading.reportengine.ReportEngineApplication;
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
	private static final String TRADE_TYPE_BUY = "B";

	/** The Constant TRADE_TYPE_SELL. */
	private static final String TRADE_TYPE_SELL = "S";

	/**
	 * Gets the incoming instructions.
	 *
	 * @return the incoming instructions
	 * @throws ApplicationException the application exception
	 */
	public void getIncomingInstructions() throws ApplicationException {
		instructionParser = new InstructionCSVParser();

		instructions = instructionParser.parseInstructions();
		
		System.out.println("instructions:" + instructions);
		
		Stream<Instruction> buyInstructions = instructions.stream().filter(instruction -> TRADE_TYPE_BUY.equals(instruction.getTradeType())).collect(arg0);
		
		buyInstructions.sorted();
	}
}
