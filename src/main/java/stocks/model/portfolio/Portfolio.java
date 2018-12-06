package stocks.model.portfolio;


import java.util.HashMap;
import java.util.Map;

import stocks.model.stock.Stock;

/**
 * The following class represents the portfolio. A portfolio is collection of stock.
 */
public class Portfolio {
  //The variable represents the name of the portfolio.
  private String name;
  //The variable represents the list of stocks in the portfolio.
  private Map<String, Stock> stocks;

  /**
   * The following constructor initializes the portfolio.
   *
   * @param name the name of the portfolio.
   */
  public Portfolio(String name) {
    this.name = name;
    this.stocks = new HashMap<String, Stock>();
  }

  /**
   * The following method returns the name of the portfolio.
   *
   * @return the name of the portfolio.
   */
  public String getName() {
    return this.name;
  }

  /**
   * The following method returns the stocks in the portfolio.
   *
   * @return the all stocks in the portfolio.
   */
  public Map<String, Stock> getStocks() {
    return this.stocks;
  }


}
