import org.junit.Test;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import stocks.model.portfolio.Portfolio;
import stocks.model.portfolio.PortfolioModel;
import stocks.model.portfolio.PortfolioOperations;
import stocks.model.stock.Stock;
import stocks.model.stock.StockModel;
import stocks.model.stock.StockOperations;

import static junit.framework.TestCase.assertEquals;

public class PortfolioModelTest {

  private PortfolioOperations portfolioOperations;

  private StockOperations<Stock> stockOperations;

  @org.junit.Before
  public void setUp() throws Exception {

    Map<String, Map<String, Map<String, Double>>> stockData = new HashMap<String, Map<String,
            Map<String, Double>>>();
    portfolioOperations = new PortfolioModel(stockData);
    stockOperations = new StockModel(stockData);

  }

  /**
   * This method tests empty stock data.
   */
  @Test
  public void testEmptyStockData() {
    try {
      portfolioOperations = new PortfolioModel(null);
    } catch (IllegalArgumentException iae) {
      assertEquals("stock data is blank", iae.getMessage());
    }
  }

  /**
   * This method tests whether adding a portfolio works or not.
   */
  @Test
  public void testAddPortfolio() {

    portfolioOperations.addPortfolio("portfolio");
    Map<Integer, Portfolio> list = portfolioOperations.viewPortfolios();
    Portfolio p = (Portfolio) list.get(1);
    assertEquals("PORTFOLIO", p.getName());
  }

  /**
   * This method tests whether adding multiple portfolio works or not.
   */
  @Test
  public void testAddPortfolioMultiple() {

    portfolioOperations.addPortfolio("portfolio 1");
    Map<Integer, Portfolio> list = portfolioOperations.viewPortfolios();
    assertEquals("PORTFOLIO 1", list.get(1).getName());

    portfolioOperations.addPortfolio("portfolio 2");
    list = portfolioOperations.viewPortfolios();
    assertEquals("PORTFOLIO 1", list.get(1).getName());
    assertEquals("PORTFOLIO 2", list.get(2).getName());

    portfolioOperations.addPortfolio("portfolio 3");
    list = portfolioOperations.viewPortfolios();
    assertEquals("PORTFOLIO 1", list.get(1).getName());
    assertEquals("PORTFOLIO 2", list.get(2).getName());
    assertEquals("PORTFOLIO 3", list.get(3).getName());

  }

  /**
   * This method tests whether adding multiple portfolio works or not.
   */
  @Test
  public void testAddPortfolioInvalid() {

    try {
      portfolioOperations.addPortfolio("");
    } catch (IllegalArgumentException iae) {
      assertEquals("Invalid portfolio name or duplicate portfolio name\n", iae.getMessage());
    }

    try {
      portfolioOperations.addPortfolio(null);
    } catch (IllegalArgumentException iae) {
      assertEquals("Invalid portfolio name or duplicate portfolio name\n", iae.getMessage());
    }
  }

  /**
   * This method tests whether adding multiple portfolio works or not.
   */
  @Test
  public void testAddStockPortfolioInvalid() {

    try {
      portfolioOperations.addStock(0, "AAPL", 12,
              LocalDate.parse("2018-11-14"), 2.5);
    } catch (IllegalArgumentException iae) {
      assertEquals("Invalid portfolio Id\n", iae.getMessage());
    }

    try {
      portfolioOperations.addStock(-6, "AAPL", 12,
              LocalDate.parse("2018-11-14"), 2);
    } catch (IllegalArgumentException iae) {
      assertEquals("Invalid portfolio Id\n", iae.getMessage());
    }

    try {
      portfolioOperations.addStock(1, "AAPL", 12,
              LocalDate.parse("2018-11-14"), 3);
    } catch (IllegalArgumentException iae) {
      assertEquals("Invalid portfolio Id\n", iae.getMessage());
    }

    portfolioOperations.addPortfolio("Portfolio 1");

    try {
      portfolioOperations.addStock(1, "", 12,
              LocalDate.parse("2018-11-14"), 5);
    } catch (IllegalArgumentException iae) {
      assertEquals("Invalid ticker symbol\n", iae.getMessage());
    }


    try {
      portfolioOperations.addStock(1, null, 12,
              LocalDate.parse("2018-11-14"), 2);
    } catch (IllegalArgumentException iae) {
      assertEquals("Invalid ticker symbol\n", iae.getMessage());
    }

    try {
      portfolioOperations.addStock(1, "AAPL", -12,
              LocalDate.parse("2018-11-14"), 3);
    } catch (IllegalArgumentException iae) {
      assertEquals("Invalid volume\n", iae.getMessage());
    }

    try {
      portfolioOperations.addStock(1, "AAPL", 12,
              null, 2);
    } catch (IllegalArgumentException iae) {
      assertEquals("Invalid date\n", iae.getMessage());
    }

    try {
      portfolioOperations.addStock(1, "AAPL", 12,
              LocalDate.now().plusDays(4), 5);
    } catch (IllegalArgumentException iae) {
      assertEquals("Date cannot be greater than today's date\n", iae.getMessage());
    }

  }

  /**
   * This method tests whether adding duplicate portfolio works or not.
   */
  @Test
  public void testAddPortfolioDuplicate() {

    portfolioOperations.addPortfolio("portfolio 1");
    Map<Integer, Portfolio> list = portfolioOperations.viewPortfolios();
    assertEquals("PORTFOLIO 1", list.get(1).getName());

    try {
      portfolioOperations.addPortfolio("portfolio 1");
    } catch (IllegalArgumentException iae) {
      assertEquals("Invalid portfolio name or duplicate portfolio name\n", iae.getMessage());
    }

  }

  /**
   * This method tests whether adding a portfolio and adding a stock in that portfolio.
   */
  @Test
  public void testAddPortfolioAddStock() {

    portfolioOperations.addPortfolio("portfolio");
    Map<Integer, Portfolio> list = portfolioOperations.viewPortfolios();
    Portfolio p = (Portfolio) list.get(1);
    assertEquals("PORTFOLIO", p.getName());
    portfolioOperations.addStock(1, "AAPL", 12,
            LocalDate.parse("2018-11-09"), 2);
    Map<String, Stock> stocks = portfolioOperations.viewStocks(1);
    assertEquals(12.0, stocks.get("AAPL").getVolume());
    assertEquals("AAPL", stocks.get("AAPL").getTickerSymbol());
    assertEquals(2241.6,
            stockOperations.getCurrentValuationOfStock(LocalDate.parse("2018-11-14"),
                    stocks.get("AAPL")));
    assertEquals(2502.71,
            stockOperations.getTotalCostBasisOfStock(LocalDate.parse("2018-11-09"),
                    stocks.get("AAPL")));

  }

  /**
   * This method tests whether adding a portfolio and adding multiple stock in that portfolio.
   */
  @Test
  public void testAddPortfolioAddMultipleStock() {

    portfolioOperations.addPortfolio("portfolio");
    Map<Integer, Portfolio> list = portfolioOperations.viewPortfolios();
    Portfolio p = (Portfolio) list.get(1);
    assertEquals("PORTFOLIO", p.getName());
    portfolioOperations.addStock(1, "AAPL", 12,
            LocalDate.parse("2018-11-09"), 2);
    Map<String, Stock> stocks = portfolioOperations.viewStocks(1);
    assertEquals(12.0, stocks.get("AAPL").getVolume());
    assertEquals("AAPL", stocks.get("AAPL").getTickerSymbol());
    assertEquals(2241.6,
            stockOperations.getCurrentValuationOfStock(LocalDate.parse("2018-11-14"),
                    stocks.get("AAPL")));
    assertEquals(2502.71,
            stockOperations.getTotalCostBasisOfStock(LocalDate.parse("2018-11-09"),
                    stocks.get("AAPL")));
    portfolioOperations.addStock(1, "GOOG", 15,
            LocalDate.parse("2018-11-09"), 4);
    stocks = portfolioOperations.viewStocks(1);
    assertEquals(15.0, stocks.get("GOOG").getVolume());
    assertEquals("GOOG", stocks.get("GOOG").getTickerSymbol());
    assertEquals(15654.9,
            stockOperations.getCurrentValuationOfStock(LocalDate.parse("2018-11-14"),
                    stocks.get("GOOG")));
    assertEquals(16631.94,
            stockOperations.getTotalCostBasisOfStock(LocalDate.parse("2018-11-09"),
                    stocks.get("GOOG")));

  }

  /**
   * This method tests whether adding a portfolio and adding multiple stock in that portfolio.
   */
  @Test
  public void testAddPortfolioAddMultipleSameStock() {

    portfolioOperations.addPortfolio("portfolio");
    Map<Integer, Portfolio> list = portfolioOperations.viewPortfolios();
    Portfolio p = (Portfolio) list.get(1);
    assertEquals("PORTFOLIO", p.getName());
    portfolioOperations.addStock(1, "AAPL", 12,
            LocalDate.parse("2018-11-09"), 5);
    Map<String, Stock> stocks = portfolioOperations.viewStocks(1);
    assertEquals(12.0, stocks.get("AAPL").getVolume());
    assertEquals("AAPL", stocks.get("AAPL").getTickerSymbol());
    assertEquals(2241.6,
            stockOperations.getCurrentValuationOfStock(LocalDate.parse("2018-11-14"),
                    stocks.get("AAPL")));
    assertEquals(2576.32,
            stockOperations.getTotalCostBasisOfStock(LocalDate.parse("2018-11-09"),
                    stocks.get("AAPL")));
    portfolioOperations.addStock(1, "AAPL", 15,
            LocalDate.parse("2018-11-09"), 2);
    stocks = portfolioOperations.viewStocks(1);
    assertEquals(27.0, stocks.get("AAPL").getVolume());
    assertEquals("AAPL", stocks.get("AAPL").getTickerSymbol());
    assertEquals(7285.2,
            stockOperations.getCurrentValuationOfStock(LocalDate.parse("2018-11-14"),
                    stocks.get("AAPL")));
    assertEquals(5704.71,
            stockOperations.getTotalCostBasisOfStock(LocalDate.parse("2018-11-09"),
                    stocks.get("AAPL")));

  }

  /**
   * This method tests whether adding a portfolio and adding a stock in that portfolio.
   */
  @Test
  public void testAddMultiplePortfolioAddStock() {

    portfolioOperations.addPortfolio("portfolio 1");
    Map<Integer, Portfolio> list = portfolioOperations.viewPortfolios();
    assertEquals("PORTFOLIO 1", list.get(1).getName());
    portfolioOperations.addStock(1, "AAPL", 12,
            LocalDate.parse("2018-11-09"), 6);
    Map<String, Stock> stocks = portfolioOperations.viewStocks(1);
    assertEquals(12.0, stocks.get("AAPL").getVolume());
    assertEquals("AAPL", stocks.get("AAPL").getTickerSymbol());
    assertEquals(2241.6,
            stockOperations.getCurrentValuationOfStock(LocalDate.parse("2018-11-14"),
                    stocks.get("AAPL")));
    assertEquals(2600.86,
            stockOperations.getTotalCostBasisOfStock(LocalDate.parse("2018-11-09"),
                    stocks.get("AAPL")));

    portfolioOperations.addPortfolio("portfolio 2");
    list = portfolioOperations.viewPortfolios();
    assertEquals("PORTFOLIO 2", list.get(2).getName());
    portfolioOperations.addStock(2, "GOOG", 12,
            LocalDate.parse("2018-11-09"), 2);
    stocks = portfolioOperations.viewStocks(2);
    assertEquals(12.0, stocks.get("GOOG").getVolume());
    assertEquals("GOOG", stocks.get("GOOG").getTickerSymbol());
    assertEquals(12523.92,
            stockOperations.getCurrentValuationOfStock(LocalDate.parse("2018-11-14"),
                    stocks.get("GOOG")));
    assertEquals(13049.68,
            stockOperations.getTotalCostBasisOfStock(LocalDate.parse("2018-11-09"),
                    stocks.get("GOOG")));

  }

  /**
   * This method tests whether adding a portfolio and adding multiple stock in that portfolio.
   */
  @Test
  public void testAddMultiplePortfolioAddMultipleStock() {

    portfolioOperations.addPortfolio("portfolio 1");
    Map<Integer, Portfolio> list = portfolioOperations.viewPortfolios();
    Portfolio p = (Portfolio) list.get(1);
    assertEquals("PORTFOLIO 1", p.getName());
    portfolioOperations.addStock(1, "AAPL", 12,
            LocalDate.parse("2018-11-09"), 2.5);
    Map<String, Stock> stocks = portfolioOperations.viewStocks(1);
    assertEquals(12.0, stocks.get("AAPL").getVolume());
    assertEquals("AAPL", stocks.get("AAPL").getTickerSymbol());
    assertEquals(2241.6,
            stockOperations.getCurrentValuationOfStock(LocalDate.parse("2018-11-14"),
                    stocks.get("AAPL")));
    assertEquals(2514.98,
            stockOperations.getTotalCostBasisOfStock(LocalDate.parse("2018-11-09"),
                    stocks.get("AAPL")));
    portfolioOperations.addStock(1, "GOOG", 15,
            LocalDate.parse("2018-11-09"), 1);
    stocks = portfolioOperations.viewStocks(1);
    assertEquals(15.0, stocks.get("GOOG").getVolume());
    assertEquals("GOOG", stocks.get("GOOG").getTickerSymbol());
    assertEquals(15654.9,
            stockOperations.getCurrentValuationOfStock(LocalDate.parse("2018-11-14"),
                    stocks.get("GOOG")));
    assertEquals(16152.17,
            stockOperations.getTotalCostBasisOfStock(LocalDate.parse("2018-11-09"),
                    stocks.get("GOOG")));


    portfolioOperations.addPortfolio("portfolio 2");
    list = portfolioOperations.viewPortfolios();
    assertEquals("PORTFOLIO 2", list.get(2).getName());
    portfolioOperations.addStock(2, "GOOG", 12,
            LocalDate.parse("2018-11-09"), 1);
    stocks = portfolioOperations.viewStocks(2);
    assertEquals(12.0, stocks.get("GOOG").getVolume());
    assertEquals("GOOG", stocks.get("GOOG").getTickerSymbol());
    assertEquals(12523.92,
            stockOperations.getCurrentValuationOfStock(LocalDate.parse("2018-11-14"),
                    stocks.get("GOOG")));
    assertEquals(12921.74,
            stockOperations.getTotalCostBasisOfStock(LocalDate.parse("2018-11-09"),
                    stocks.get("GOOG")));
    portfolioOperations.addStock(2, "AAPL", 15,
            LocalDate.parse("2018-11-09"), 3);
    stocks = portfolioOperations.viewStocks(1);
    assertEquals(12.0, stocks.get("AAPL").getVolume());
    assertEquals("AAPL", stocks.get("AAPL").getTickerSymbol());
    assertEquals(2241.6,
            stockOperations.getCurrentValuationOfStock(LocalDate.parse("2018-11-14"),
                    stocks.get("AAPL")));
    assertEquals(2514.98,
            stockOperations.getTotalCostBasisOfStock(LocalDate.parse("2018-11-09"),
                    stocks.get("AAPL")));

  }

  /**
   * This method tests whether adding a portfolio and adding a stock in that portfolio.
   */
  @Test
  public void testAddPortfolioAddStockDetails() {

    portfolioOperations.addPortfolio("portfolio");
    Map<Integer, Portfolio> list = portfolioOperations.viewPortfolios();
    Portfolio p = (Portfolio) list.get(1);
    assertEquals("PORTFOLIO", p.getName());
    portfolioOperations.addStock(1, "AAPL", 12,
            LocalDate.parse("2018-11-09"), 2);
    Map<String, Stock> stocks = portfolioOperations.viewStocks(1);
    assertEquals(12.0, stocks.get("AAPL").getVolume());
    assertEquals("AAPL", stocks.get("AAPL").getTickerSymbol());
    assertEquals(2241.6,
            stockOperations.getCurrentValuationOfStock(LocalDate.parse("2018-11-14"),
                    stocks.get("AAPL")));
    assertEquals(2502.71,
            stockOperations.getTotalCostBasisOfStock(LocalDate.parse("2018-11-09"),
                    stocks.get("AAPL")));

  }

}
