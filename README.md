# Engine for trading reports

## 1. Problem Statement
	* Build a report engine that takes instructions sent by various clients to JP Morgan to execute in the international market. 
	* The data contains details such as trade type, agreed fx, currency, instruction & settlement dates, units and price per unit for each of the entities.
	* Based on the data, generate report that shows
		** Amount in USD settled incoming everyday
		** Amount in USD settled outgoing everyday
		** Ranking of entities based on incoming and outgoing amount. Eg: If entity foo instructs the highest amount for a buy instruction, then foo is rank 1 for outgoing
	* The below mentioned conditions have to be met while generating the reports.
		** A work week starts Monday and ends Friday, unless the currency of the trade is AED or SAR, where the work week starts Sunday and ends Thursday. No other holidays to be taken into account.
		** A trade can only be settled on a working day.
		** If an instructed settlement date falls on a weekend, then the settlement date should be changed to the next working day.
		** USD amount of a trade = Price per unit * Units * Agreed Fx

## 2. Prerequisites
	* Please make sure that the following technology stack is installed on a machine before building the application
		** Java (1.8.x)
		** Maven (3.3.9)

## 3. Implementation details
	* A maven project has been created, whose main class takes one of the different options available to generate the corresponding report.
	* A CSV file containing the sample data is processed to get the instructions based on the trade type (the trade type could be eitehr 'BUY' OR 'SELL').
	* As per the conditions mentioned in the problem statement, trade report for incoming/outgoing is generated.
	* And, based on the total amount traded the entities are given rankings and the corresponding reports are generated.

## 4. Sample data
	* The 'trade_instructions.csv' file contains 150 instructions for each of the trade types 'BUY' and 'SELL'.
	* Fifteen entities (with names Entity-1, Entity-2 etc.) are used for this exercise.
	* Also, fifteen different currencies including AED & SAR are used for the sample.
	* The exchange rates have been taken from the site, https://www.exchange-rates.org/
	* The units and price per unit mentioned in the sample data should be used only for demonstation purposes.

## 5. Build
	* Make sure that the prerequisites are installed and are running.
	* Run the following command for building the runnable jar.
		** mvn clean package -Dmaven.test.skip

## 6. Run
	* After building the runnable jar as per the above instructions, run the below command for generating respective report.
		** java -jar target\report-engine.jar <option>
			*** where the <option> can be one of the following
				**** IRR - Generate incoming rankings report
				**** ISR - Generate incoming settlement report
				**** ORR - Generate outgoing rankings report
				**** OSR - Generate outgoing settlement report