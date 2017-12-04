package com.jpmc.trading.reportengine.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class Instruction {

	private String entity;

	private String tradeType;

	private Double exchangeRate;

	private String currency;

	private LocalDate instructionDate;

	private LocalDate settlementDate;

	private Integer units;

	private Double pricePerUnit;
}
