package com.jpmc.trading.reportengine.parser;

import java.util.List;

import com.jpmc.trading.reportengine.dto.Instruction;
import com.jpmc.trading.reportengine.exception.ApplicationException;

public interface IDataParser {

	public List<Instruction> parseInstructions() throws ApplicationException;
}
