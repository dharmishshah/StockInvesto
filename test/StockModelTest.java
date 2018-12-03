import org.junit.Test;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import stocks.model.stock.Stock;
import stocks.model.stock.StockModel;
import stocks.model.stock.StockOperations;
import stocks.model.stock.Transaction;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.fail;

public class StockModelTest {

  private StockOperations<Stock> stockOperations;

  @org.junit.Before
  public void setUp() throws Exception {
    Map<String, Map<String, Map<String, Double>>> stockData = new HashMap<String, Map<String,
            Map<String, Double>>>();
    stockOperations = new StockModel(stockData);

  }

  /**
   * This method tests empty stock data.
   */
  @Test
  public void testEmptyStockData() {
    try {
      stockOperations = new StockModel(null);
    } catch (IllegalArgumentException iae) {
      assertEquals("stock data is blank", iae.getMessage());
    }
  }

  /**
   * This method tests adding new stock data.
   */
  @Test
  public void testAddStock() {

    Stock stock = stockOperations.addNewStock("AAPL", 12,
            LocalDate.parse("2018-11-14"), 2);
    assertEquals("AAPL", stock.getTickerSymbol());
    assertEquals(12.0, stock.getVolume());
    assertEquals(2286.4320000000002, stock.getTotalCostBasis());

  }

  /**
   * This method tests invalid stock data.
   */
  @Test
  public void testAddStockInvalid1() {

    try {
      Stock stock = stockOperations.addNewStock(null, 12,
              LocalDate.parse("2018-11-14"), 2.5);
    } catch (IllegalArgumentException iae) {
      assertEquals("Invalid ticker symbol\n", iae.getMessage());
    }

    try {
      Stock stock = stockOperations.addNewStock("", 12,
              LocalDate.parse("2018-11-14"), 2);
    } catch (IllegalArgumentException iae) {
      assertEquals("Invalid ticker symbol\n", iae.getMessage());
    }

    try {
      Stock stock = stockOperations.addNewStock("AAPL", -12,
              LocalDate.parse("2018-11-14"), 3);
    } catch (IllegalArgumentException iae) {
      assertEquals("Invalid volume\n", iae.getMessage());
    }

    try {
      Stock stock = stockOperations.addNewStock("AAPL", 12, null,
              3);
    } catch (IllegalArgumentException iae) {
      assertEquals("Invalid date\n", iae.getMessage());
    }

    try {
      Stock stock = stockOperations.addNewStock("AAPL", 12,
              LocalDate.now().plusDays(4), 5);
    } catch (IllegalArgumentException iae) {
      assertEquals("Date cannot be greater than today's date\n", iae.getMessage());
    }

    try {
      Stock stock = stockOperations.addNewStock("AAPL", 12, LocalDate.now(), 6);
    } catch (IllegalArgumentException iae) {
      assertEquals("Market is closed\n", iae.getMessage());
    }


    try {
      Stock stock = stockOperations.addNewStock("AAPL", 12,
              LocalDate.parse("2018-11-14"), -6);
      fail();
    } catch (IllegalArgumentException iae) {
      assertEquals("Commission Rate cannot be less than 0 or greater than 100\n",
              iae.getMessage());
    }

    try {
      Stock stock = stockOperations.addNewStock("AAPL", 12,
              LocalDate.parse("2018-11-14"), -10.0);
      fail();
    } catch (IllegalArgumentException iae) {
      assertEquals("Commission Rate cannot be less than 0 or greater than 100\n",
              iae.getMessage());
    }

    try {
      Stock stock = stockOperations.addNewStock("AAPL", 12,
              LocalDate.parse("2018-11-14"), -112.4);
      fail();
    } catch (IllegalArgumentException iae) {
      assertEquals("Commission Rate cannot be less than 0 or greater than 100\n",
              iae.getMessage());
    }

  }

  /**
   * This method tests updating a already existing new stock data.
   */
  @Test
  public void testUpdateStock() {

    Stock stock = stockOperations.addNewStock("AAPL", 12,
            LocalDate.parse("2018-11-14"), 2);
    assertEquals("AAPL", stock.getTickerSymbol());
    assertEquals(12.0, stock.getVolume());
    assertEquals(2286.4320000000002, stock.getTotalCostBasis());
    stock = stockOperations.updateExistingStock(stock, 13, LocalDate.parse("2018-11-12"),
            4);
    assertEquals("AAPL", stock.getTickerSymbol());
    assertEquals(25.0, stock.getVolume());
    assertEquals(4911.6104, stock.getTotalCostBasis());


  }

  /**
   * This method tests updating a already existing new stock data and checking transaction log.
   */
  @Test
  public void testUpdateStockTransactions() {

    Stock stock = stockOperations.addNewStock("AAPL", 12,
            LocalDate.parse("2018-11-14"), 3);
    assertEquals("AAPL", stock.getTickerSymbol());
    assertEquals(12.0, stock.getVolume());
    assertEquals(2308.8480000000004, stock.getTotalCostBasis());
    stock = stockOperations.updateExistingStock(stock, 13,
            LocalDate.parse("2018-11-12"), 3);
    assertEquals("AAPL", stock.getTickerSymbol());
    assertEquals(25.0, stock.getVolume());
    assertEquals(4908.784300000001, stock.getTotalCostBasis());
    List<Transaction> transactions = stock.getTransactions();
    assertEquals(2308.8480000000004, transactions.get(0).getTransactionCostBasis());
    assertEquals(12.0, transactions.get(0).getVolume());
    assertEquals("2018-11-14", transactions.get(0).getDate().toString());
    assertEquals(2599.9363, transactions.get(1).getTransactionCostBasis());
    assertEquals(13.0, transactions.get(1).getVolume());
    assertEquals("2018-11-12", transactions.get(1).getDate().toString());
  }

  /**
   * This method tests invalid stock data.
   */
  @Test
  public void testUpdateStockInvalid1() {
    Stock stock = stockOperations.addNewStock("AAPL", 12,
            LocalDate.parse("2018-11-14"), 3);

    try {
      stock = stockOperations.updateExistingStock(null, 13,
              LocalDate.parse("2018-11-12"), 3);
    } catch (IllegalArgumentException iae) {
      assertEquals("Stock cannot be blank.", iae.getMessage());
    }

    try {
      stock = stockOperations.updateExistingStock(stock, -13, null, 2);
    } catch (IllegalArgumentException iae) {
      assertEquals("Invalid volume\n", iae.getMessage());
    }

    try {
      stock = stockOperations.updateExistingStock(stock, 13, null, 3);
    } catch (IllegalArgumentException iae) {
      assertEquals("Invalid date\n", iae.getMessage());
    }

    try {
      stock = stockOperations.updateExistingStock(stock, 13, LocalDate.now().plusDays(4),
              4);
    } catch (IllegalArgumentException iae) {
      assertEquals("Date cannot be greater than today's date\n", iae.getMessage());
    }

    try {
      stock = stockOperations.addNewStock("AAPL", 12, LocalDate.now(),
              4);
    } catch (IllegalArgumentException iae) {
      assertEquals("Market is closed\n", iae.getMessage());
    }

    try {
      stock = stockOperations.addNewStock("AAPL", 12, LocalDate.now(),
              -4);
    } catch (IllegalArgumentException iae) {
      assertEquals("Commission Rate cannot be less than 0 or greater than 100\n",
              iae.getMessage());
    }

    try {
      stock = stockOperations.addNewStock("AAPL", 12, LocalDate.now(),
              400);
    } catch (IllegalArgumentException iae) {
      assertEquals("Commission Rate cannot be less than 0 or greater than 100\n",
              iae.getMessage());
    }

  }

  /**
   * This method tests adding new stock data and getting its stock evaluation.
   */
  @Test
  public void testStockEvaluation() {

    Stock stock = stockOperations.addNewStock("AAPL", 12,
            LocalDate.parse("2018-11-12"), 5);
    assertEquals("AAPL", stock.getTickerSymbol());
    assertEquals(12.0, stock.getVolume());
    assertEquals(2446.542, stock.getTotalCostBasis());
    double costEvaluation = stockOperations.getCurrentValuationOfStock(LocalDate.parse("2018-11"
            + "-14"), stock);
    assertEquals(2241.6, costEvaluation);

  }

  /**
   * This method tests adding new stock data and getting its stock evaluation.
   */
  @Test
  public void testStockEvaluationInvalid() {

    Stock stock = stockOperations.addNewStock("AAPL", 12,
            LocalDate.parse("2018-11-12"), 2);
    assertEquals("AAPL", stock.getTickerSymbol());
    assertEquals(12.0, stock.getVolume());
    assertEquals(2376.6408, stock.getTotalCostBasis());
    try {
      double costEvaluation = stockOperations.getCurrentValuationOfStock(null, stock);
    } catch (IllegalArgumentException iae) {
      assertEquals("Invalid date\n", iae.getMessage());
    }

    try {
      double costEvaluation = stockOperations.getCurrentValuationOfStock(LocalDate.parse("2018-11" +
              "-14"), null);
    } catch (IllegalArgumentException iae) {
      assertEquals("Stock cannot be blank.", iae.getMessage());
    }

  }

  /**
   * This method tests adding new stock data and getting its stock cost basis.
   */
  @Test
  public void testCostBasisEvaluation() {

    Stock stock = stockOperations.addNewStock("AAPL", 12,
            LocalDate.parse("2018-11-12"), 2);
    assertEquals("AAPL", stock.getTickerSymbol());
    assertEquals(12.0, stock.getVolume());
    assertEquals(2376.6408, stock.getTotalCostBasis());
    double costEvaluation = stockOperations.getTotalCostBasisOfStock(LocalDate.parse("2018-11" +
            "-14"), stock);
    assertEquals(2376.64, costEvaluation);

  }

  /**
   * This method tests adding new stock data and getting cost basis for invalid data..
   */
  @Test
  public void testStockCostBasisInvalid() {

    Stock stock = stockOperations.addNewStock("AAPL", 12,
            LocalDate.parse("2018-11-12"), 2);
    assertEquals("AAPL", stock.getTickerSymbol());
    assertEquals(12.0, stock.getVolume());
    assertEquals(2376.6408, stock.getTotalCostBasis());
    try {
      double costEvaluation = stockOperations.getTotalCostBasisOfStock(null, stock);
    } catch (IllegalArgumentException iae) {
      assertEquals("Invalid date\n", iae.getMessage());
    }

    try {
      double costEvaluation = stockOperations.getTotalCostBasisOfStock(LocalDate.parse("2018-11" +
              "-14"), null);
    } catch (IllegalArgumentException iae) {
      assertEquals("Stock cannot be blank.", iae.getMessage());
    }
  }

  /**
   * This method tests adding new stock data and getting its stock current value.
   */
  @Test
  public void testCostPriceEvaluation() {

    Stock stock = stockOperations.addNewStock("AAPL", 12,
            LocalDate.parse("2018-11-12"), 2);
    assertEquals("AAPL", stock.getTickerSymbol());
    assertEquals(12.0, stock.getVolume());
    assertEquals(2376.6408, stock.getTotalCostBasis());
    double costEvaluation = stockOperations.getCostPriceStock("AAPL",
            LocalDate.parse("2018-11-12"), false);
    assertEquals(194.17, costEvaluation);

  }

  /**
   * This method tests adding new stock data and getting its stock current value if data is
   * available for specified date.
   */
  @Test
  public void testCostPriceEvaluation1() {

    Stock stock = stockOperations.addNewStock("AAPL", 12,
            LocalDate.parse("2018-11-12"), 2);
    assertEquals("AAPL", stock.getTickerSymbol());
    assertEquals(12.0, stock.getVolume());
    assertEquals(2376.6408, stock.getTotalCostBasis());
    double costEvaluation = stockOperations.getCostPriceStock("AAPL",
            LocalDate.parse("2018-11-12"), true);
    assertEquals(194.17, costEvaluation);

  }

  /**
   * This method tests adding new stock data and getting its stock current value if data is not
   * available for specified date.
   */
  @Test
  public void testCostPriceEvaluation2() {

    Stock stock = stockOperations.addNewStock("AAPL", 12,
            LocalDate.parse("2018-11-12"), 2);
    assertEquals("AAPL", stock.getTickerSymbol());
    assertEquals(12.0, stock.getVolume());
    assertEquals(2376.6408, stock.getTotalCostBasis());
    double costEvaluation = stockOperations.getCostPriceStock("AAPL",
            LocalDate.parse("2018-11-10"), true);
    assertEquals(204.47, costEvaluation);

  }

  /**
   * This method tests adding new stock data and getting its stock current value if data is not
   * available for specified date.
   */
  @Test
  public void testCostPriceEvaluation3() {

    Stock stock = stockOperations.addNewStock("AAPL", 12,
            LocalDate.parse("2018-11-12"), 3);
    assertEquals("AAPL", stock.getTickerSymbol());
    assertEquals(12.0, stock.getVolume());
    assertEquals(2399.9411999999998, stock.getTotalCostBasis());

    try {
      stockOperations.getCostPriceStock("AAPL",
              LocalDate.parse("1992-11-10"), true);
    } catch (IllegalArgumentException iae) {
      assertEquals("No price data found\n", iae.getMessage());
    }


  }

  /**
   * This method tests adding new stock data and getting current value for invalid data.
   */
  @Test
  public void testStockCostPriceInvalid() {

    Stock stock = stockOperations.addNewStock("AAPL", 12,
            LocalDate.parse("2018-11-12"), 3);
    assertEquals("AAPL", stock.getTickerSymbol());
    assertEquals(12.0, stock.getVolume());
    assertEquals(2399.9411999999998, stock.getTotalCostBasis());
    try {
      double costEvaluation = stockOperations.getTotalCostBasisOfStock(null, stock);
    } catch (IllegalArgumentException iae) {
      assertEquals("Invalid date\n", iae.getMessage());
    }

    try {
      double costEvaluation = stockOperations.getTotalCostBasisOfStock(LocalDate.parse("2018-11" +
              "-14"), null);
    } catch (IllegalArgumentException iae) {
      assertEquals("Stock cannot be blank.", iae.getMessage());
    }
  }

  /**
   * This method tests adding new stock data with invalid ticker symbol.
   */
  @Test
  public void testInvalidTickerSymbol() {

    try {
      Stock stock = stockOperations.addNewStock("123", 12,
              LocalDate.parse("2018-11-14"), 3);
    } catch (IllegalArgumentException iae) {
      assertEquals("No price data found\n", iae.getMessage());
    }
  }

  /**
   * This method tests adding new stock data with invalid date.
   */
  @Test
  public void testInvalidDate() {

    try {
      Stock stock = stockOperations.addNewStock("AAPL", 12,
              LocalDate.parse("2018-11-10"), 3);
    } catch (IllegalArgumentException iae) {
      assertEquals("Market is closed\n", iae.getMessage());
    }
  }
}
