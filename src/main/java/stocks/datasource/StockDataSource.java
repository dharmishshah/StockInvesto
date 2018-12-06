package stocks.datasource;

public interface StockDataSource {

  /**
   * This method gets the stock cost price for a particular ticker symbol.
   */
  StringBuilder getData(String tickerSymbol);
}
