package stocks.model.stock;

import java.time.LocalDate;

/**
 * The following interface represents the operations performed by the stock. The operations
 * performed by the stock are: 1) Update existing stock. 2) add new stock. 3)stocks.view stocks for
 * a portfolio. 4) get the current valuation of the stock. 5) get total investment for a stock. 6)
 * get the current cost price of the stock.
 */
public interface StockOperations<Stock> {
  /**
   * The following method updates the existing stock.
   *
   * @param stock          the stock to be updated.
   * @param volume         the volume of stock to be updated.
   * @param date           the date at which stock is to be updated.
   * @param commissionRate commission to buy a stock
   * @return the updated stock.
   */
  Stock updateExistingStock(Stock stock, double volume, LocalDate date, double commissionRate);

  /**
   * The following adds new stock.
   *
   * @param tickerSymbol   the ticker symbol of the stock.
   * @param volume         the quantity of the stock to be added.
   * @param date           the date at which new stock to be added.
   * @param commissionRate commission to buy a stock
   * @return the new stock object.
   */
  Stock addNewStock(String tickerSymbol, double volume, LocalDate date, double commissionRate);


  /**
   * The following method returns the current valuation of th stock for the given date. eg: the
   * following method will return the price we will fetch if sell the stock on the given date.
   *
   * @param date  the date for which the valuation is to be found.
   * @param stock the stock of the company whose valuation is to be found.
   * @return the current valuation of the stock.
   */
  double getCurrentValuationOfStock(LocalDate date, Stock stock);

  /**
   * The following method returns the total amount invested in the stock for the given date. eg: the
   * following method will return the amount in the stock till the given date.
   *
   * @param date  the date for which the investment is to be found.
   * @param stock the stock of the company whose valuation is to be found.
   * @return the total investment made in the portfolio.
   */
  double getTotalCostBasisOfStock(LocalDate date, Stock stock);

  /**
   * The following method returns the cost price of a stock based on ticker symbol and specified
   * date using third party API. It saves the data as csv file for every unique symbol.
   *
   * @param date         the date for which the cost price is to be found.
   * @param tickerSymbol the stock of the company whose cost price is to be found.
   * @param isView       true if view only false otherwise
   * @return the cost price of the stock.
   */
  double getCostPriceStock(String tickerSymbol, LocalDate date, boolean isView);

  /**
   * The following method returns the total commission paid for the stock till the given date.
   *
   * @param date  the date till which commission is to be calculated.
   * @param stock the stock under consideration.
   * @return total commission paid
   */
  double getTotalCommissionPaid(LocalDate date, Stock stock);
}
