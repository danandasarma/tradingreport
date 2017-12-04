package com.jpmc.trading.reportengine.operations;

import java.time.LocalDate;
import java.util.function.Predicate;

import com.jpmc.trading.reportengine.dto.Instruction;

public class InstructionPredicate {
	
	public static Predicate<Instruction> isForSettlement(LocalDate currentDate) {
		return instruction -> instruction.getSettlementDate().equals(currentDate);
	}
}
