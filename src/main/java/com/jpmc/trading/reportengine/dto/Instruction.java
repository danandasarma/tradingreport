package com.jpmc.trading.reportengine.dto;

import java.time.LocalDate;

public class Instruction {

	private String entity;

	private String tradeType;

	private Double exchangeRate;

	private String currency;

	private LocalDate instructionDate;

	private LocalDate settlementDate;

	private Integer units;

	private Double pricePerUnit;

	public String getEntity() {
		return this.entity;
	}

	public void setEntity(final String entity) {
		this.entity = entity;
	}

	public String getTradeType() {
		return this.tradeType;
	}

	public void setTradeType(final String tradeType) {
		this.tradeType = tradeType;
	}

	public Double getExchangeRate() {
		return this.exchangeRate;
	}

	public void setExchangeRate(final Double exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

	public String getCurrency() {
		return this.currency;
	}

	public void setCurrency(final String currency) {
		this.currency = currency;
	}

	public LocalDate getInstructionDate() {
		return this.instructionDate;
	}

	public void setInstructionDate(final LocalDate instructionDate) {
		this.instructionDate = instructionDate;
	}

	public LocalDate getSettlementDate() {
		return this.settlementDate;
	}

	public void setSettlementDate(final LocalDate settlementDate) {
		this.settlementDate = settlementDate;
	}

	public Integer getUnits() {
		return this.units;
	}

	public void setUnits(final Integer units) {
		this.units = units;
	}

	public Double getPricePerUnit() {
		return this.pricePerUnit;
	}

	public void setPricePerUnit(final Double pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((this.currency == null) ? 0 : this.currency.hashCode());
		result = (prime * result) + ((this.entity == null) ? 0 : this.entity.hashCode());
		result = (prime * result) + ((this.exchangeRate == null) ? 0 : this.exchangeRate.hashCode());
		result = (prime * result) + ((this.instructionDate == null) ? 0 : this.instructionDate.hashCode());
		result = (prime * result) + ((this.pricePerUnit == null) ? 0 : this.pricePerUnit.hashCode());
		result = (prime * result) + ((this.settlementDate == null) ? 0 : this.settlementDate.hashCode());
		result = (prime * result) + ((this.tradeType == null) ? 0 : this.tradeType.hashCode());
		result = (prime * result) + ((this.units == null) ? 0 : this.units.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		final Instruction other = (Instruction) obj;
		if (this.currency == null) {
			if (other.currency != null) {
				return false;
			}
		} else if (!this.currency.equals(other.currency)) {
			return false;
		}
		if (this.entity == null) {
			if (other.entity != null) {
				return false;
			}
		} else if (!this.entity.equals(other.entity)) {
			return false;
		}
		if (this.exchangeRate == null) {
			if (other.exchangeRate != null) {
				return false;
			}
		} else if (!this.exchangeRate.equals(other.exchangeRate)) {
			return false;
		}
		if (this.instructionDate == null) {
			if (other.instructionDate != null) {
				return false;
			}
		} else if (!this.instructionDate.equals(other.instructionDate)) {
			return false;
		}
		if (this.pricePerUnit == null) {
			if (other.pricePerUnit != null) {
				return false;
			}
		} else if (!this.pricePerUnit.equals(other.pricePerUnit)) {
			return false;
		}
		if (this.settlementDate == null) {
			if (other.settlementDate != null) {
				return false;
			}
		} else if (!this.settlementDate.equals(other.settlementDate)) {
			return false;
		}
		if (this.tradeType == null) {
			if (other.tradeType != null) {
				return false;
			}
		} else if (!this.tradeType.equals(other.tradeType)) {
			return false;
		}
		if (this.units == null) {
			if (other.units != null) {
				return false;
			}
		} else if (!this.units.equals(other.units)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Trade Instruction [entity=" + this.entity + ", tradeType=" + this.tradeType + ", exchangeRate="
				+ this.exchangeRate + ", currency=" + this.currency + ", instructionDate=" + this.instructionDate
				+ ", settlementDate=" + this.settlementDate + ", units=" + this.units + ", pricePerUnit="
				+ this.pricePerUnit + "]";
	}
}
