package stocks.model.stock;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.LocalDate;

import stocks.model.utils.LocalDateDeserializer;
import stocks.model.utils.LocalDateSerializer;

/**
 * The following class represents a transaction made for a stock. A transaction may be buying or
 * selling stock.
 */
public class Transaction {
  //The variable represents the date at which the transaction took place.
  @JsonDeserialize(using = LocalDateDeserializer.class)
  @JsonSerialize(using = LocalDateSerializer.class)
  private LocalDate date;
  //The variable represents the volume of stock bought(or sold).
  private double volume;
  //The variable represents the type of transaction (selling or buying).
  private TransactionType type;
  //The variable represents the unit price of the stock.
  private double costPerStock;
  //The variable represents the total investment made during the transaction.
  private double transactionCostBasis;
  //The variable represents the total commission spent on the transaction.
  private double transactionCommission;

  public Transaction() {

  }

  /**
   * The following constructor initializes the transaction for a stock.
   *
   * @param date           the date which the transaction took place.
   * @param volume         the volume of stock.
   * @param type           the type of the transaction.
   * @param costPerStock   the unit price of the stock.
   * @param commissionRate commission to buy a stock
   */
  public Transaction(LocalDate date, double volume, TransactionType type,
                     double costPerStock, double commissionRate) {
    this.date = date;
    this.volume = volume;
    this.type = type;
    this.costPerStock = costPerStock;
    this.transactionCommission = (volume * costPerStock) * (commissionRate / 100);
    this.transactionCostBasis = (volume * costPerStock) + this.transactionCommission;
  }

  //The following method returns the total commission paid in the transactions.
  public double getTransactionCommission() {
    return transactionCommission;
  }

  //The following method returns the date of the transaction.
  public LocalDate getDate() {
    return date;
  }

  //The following method returns the type of the transaction.
  public TransactionType getType() {
    return type;
  }

  //The following method returns the unit price of the stock.
  public double getCostPerStock() {
    return costPerStock;
  }

  //The following method returns the volume of stock for the transaction.
  public double getVolume() {
    return volume;
  }

  //The following method returns the investment made in the stock for the transaction.
  public double getTransactionCostBasis() {
    return transactionCostBasis;
  }
}
