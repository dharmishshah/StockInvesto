package stocks.model.stock;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import stocks.model.utils.LocalDateDeserializer;
import stocks.model.utils.LocalDateSerializer;

/**
 * The following class represents the stock of a company. It consists of the following attributes:
 * 1) the ticker symbol of the stock. 2) the total amount invested in the stock. 3) the current
 * price of the stock. 4) the date at which the stock was bought first time. 5) the record of all
 * transactions for the stock.
 */
public class Stock {
  //Variable represents the ticker symbol of the stock.
  private String tickerSymbol;
  //Variable represents the total amount invested in the stock till date.
  private double totalCostBasis;
  //Variable represents the current price of the stock.
  private double currentPrice;
  //Variable represents the quantity of the stocks bought till date.
  private double volume;
  //Variable represents the date at which stock was bought first time.
  // generates "yyyy-MM-dd" output
  @JsonDeserialize(using = LocalDateDeserializer.class)
  @JsonSerialize(using = LocalDateSerializer.class)
  private LocalDate date;
  //Variable represents the total valuation of the stock till date.
  private double currentTotalValue;
  //The list represents the list of transactions made for the stock.
  private List<Transaction> transactions;
  //The list represents the total commission paid till date for the stock.
  private double totalCommission;

  public Stock(){

  }

  /**
   * The following constructor initializes a stock.
   *
   * @param tickerSymbol   represents the ticker symbol of the stock.
   * @param currentPrice   represents the the current (buying) price of the stock.
   * @param volume         represents the quantity of stock bought.
   * @param date           the date at which the stock was bought.
   * @param commissionRate commission for buying a stock
   */
  public Stock(String tickerSymbol, double currentPrice, double volume,
               LocalDate date, double commissionRate) {
    this.tickerSymbol = tickerSymbol;
    this.totalCommission = (currentPrice * volume) * (commissionRate / 100);
    this.totalCostBasis = (currentPrice * volume) + this.totalCommission;
    this.currentTotalValue = currentPrice * volume;
    this.volume = volume;
    this.currentPrice = currentPrice;
    this.date = date;
    Transaction transaction1 = new Transaction(date, volume, TransactionType.Buy
            , currentPrice, commissionRate);
    this.transactions = new ArrayList<>();
    this.transactions.add(transaction1);
  }

  //The following method returns the ticker symbol of the stock.
  public String getTickerSymbol() {
    return tickerSymbol;
  }

  /**
   * The following method updates the current total valuation of the stock.
   *
   * @param currentTotalValue the current total value of the stock.
   */
  void setCurrentTotalValue(double currentTotalValue) {
    this.currentTotalValue = currentTotalValue;
  }

  //The following method returns the total investment of the stock.
  public double getTotalCostBasis() {
    return totalCostBasis;
  }

  /**
   * The following method updates the total investment made in the stock.
   *
   * @param totalCostBasis the total investment made in the stock.
   */
  void setTotalCostBasis(double totalCostBasis) {
    this.totalCostBasis = totalCostBasis;
  }

  /**
   * The following method updates the current price of the stock.
   *
   * @param currentPrice the current price of the stock.
   */
  void setCurrentPrice(double currentPrice) {
    this.currentPrice = currentPrice;
  }

  //The following method returns the volume of the stock.
  public double getVolume() {
    return volume;
  }

  /**
   * The following method updates the volume of the stock.
   *
   * @param volume the volume of the stock bought.
   */
  void setVolume(double volume) {
    this.volume = volume;
  }

  //The following method returns ths date at which the stock was bought first time.
  public LocalDate getDate() {
    return date;
  }

  //The following method returns the list of transactions for the stock.
  public List<Transaction> getTransactions() {
    return transactions;
  }


  /**
   * The following method updates the list of transactions for the stock.
   *
   * @param transactions the list of transactions.
   */
  void setTransactions(List<Transaction> transactions) {
    this.transactions = transactions;
  }

  //The following method returns the total commission paid for the stock
  public double getTotalCommission() {
    return totalCommission;
  }

  /**
   * The following method updates the commission for the stock.
   *
   * @param totalCommission the total existing commission
   */
  public void setTotalCommission(double totalCommission) {
    this.totalCommission = totalCommission;
  }

}
