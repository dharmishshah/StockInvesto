

import org.junit.Test;

import java.io.StringReader;

import stocks.controller.IStockController;
import stocks.controller.StockController;

import static junit.framework.TestCase.assertEquals;

public class StockControllerTest {

  private String userInput;
  private StringBuffer output;
  private IStockController stockController;


  @org.junit.Before
  public void setUp() throws Exception {

    userInput = "1\nport\n2\n11/14/2018\n3\n1\nAAPL\n2\n12\n11/09/2018\n5\n1\n11/14/2018\n10\n";
    output = new StringBuffer();
    stockController = new StockController(new StringReader(userInput), output);


  }

  /**
   * This method tests simple buying of stock by a user.
   */
  @Test
  public void testSimpleBuyingStock() {

    stockController.handleOperation();
    assertEquals("Menu\n" +
            "1.Add Portfolio\n" +
            "2.View Portfolio\n" +
            "3.Buy Stock by Volume\n" +
            "4.Buy Stock by Amount\n" +
            "5.View Stock\n" +
            "6.Invest In Existing Portfolio By Strategy \n" +
            "7.Save Portfolio\n" +
            "8.Load Portfolio\n" +
            "9.Use Existing Strategy\n" +
            "10.Exit\n" +
            "Please enter portfolio name\n" +
            "Menu\n" +
            "1.Add Portfolio\n" +
            "2.View Portfolio\n" +
            "3.Buy Stock by Volume\n" +
            "4.Buy Stock by Amount\n" +
            "5.View Stock\n" +
            "6.Invest In Existing Portfolio By Strategy \n" +
            "7.Save Portfolio\n" +
            "8.Load Portfolio\n" +
            "9.Use Existing Strategy\n" +
            "10.Exit\n" +
            "Enter the start date in MM/DD/YYYY format\n" +
            "Portfolio Name\t\t\tPortfolio Volume\t\t\tPortfolio Cost Basis\t\t\tPortfolio " +
            "Value\t\t\tPortfolio Commission\n" +
            "PORT\t\t\t\t\t\t.00\t\t\t\t\t\t$0.0\t\t\t\t\t\t$0.0\t\t\t\t\t\t$0.0\n" +
            "(Here Total Cost Basis is sum of the commission paid and amount investment " +
            "made in the stock)\n" +
            "Menu\n" +
            "1.Add Portfolio\n" +
            "2.View Portfolio\n" +
            "3.Buy Stock by Volume\n" +
            "4.Buy Stock by Amount\n" +
            "5.View Stock\n" +
            "6.Invest In Existing Portfolio By Strategy \n" +
            "7.Save Portfolio\n" +
            "8.Load Portfolio\n" +
            "9.Use Existing Strategy\n" +
            "10.Exit\n" +
            "Select a portfolio\n" +
            "\n" +
            "1.PORT\n" +
            "\n" +
            "Enter ticker symbol\n" +
            "Enter the commission rate\n" +
            "Enter stock volume\n" +
            "Enter the start date in MM/DD/YYYY format\n" +
            "Menu\n" +
            "1.Add Portfolio\n" +
            "2.View Portfolio\n" +
            "3.Buy Stock by Volume\n" +
            "4.Buy Stock by Amount\n" +
            "5.View Stock\n" +
            "6.Invest In Existing Portfolio By Strategy \n" +
            "7.Save Portfolio\n" +
            "8.Load Portfolio\n" +
            "9.Use Existing Strategy\n" +
            "10.Exit\n" +
            "Select a portfolio\n" +
            "\n" +
            "1.PORT\n" +
            "\n" +
            "Enter the start date in MM/DD/YYYY format\n" +
            "Stock Name\t\t\tVolume\t\t\tStock Cost Basis\t\t\tStock Value\t\t\tCommission_Paid\n" +
            "AAPL\t\t\t 12.00   \t\t\t$2502.71\t\t\t   $2241.60\t\t\t   $49.07\n" +
            "TOTAL\t\t\t 12.00\t\t\t   $2502.71\t\t\t   $2241.60\t\t\t   $49.07\n" +
            "(Here Total Cost Basis is sum of the commission paid and amount " +
            "investment made in the stock)\n" +
            "Menu\n" +
            "1.Add Portfolio\n" +
            "2.View Portfolio\n" +
            "3.Buy Stock by Volume\n" +
            "4.Buy Stock by Amount\n" +
            "5.View Stock\n" +
            "6.Invest In Existing Portfolio By Strategy \n" +
            "7.Save Portfolio\n" +
            "8.Load Portfolio\n" +
            "9.Use Existing Strategy\n" +
            "10.Exit\n", output.toString());
  }

  /**
   * This method tests adding a portfolio.
   */
  @Test
  public void testAddPortfolio() {
    userInput = "1\nport\n2\n11/09/2018\n10\n";
    output = new StringBuffer();
    stockController = new StockController(new StringReader(userInput), output);
    stockController.handleOperation();
    assertEquals("Menu\n" +
            "1.Add Portfolio\n" +
            "2.View Portfolio\n" +
            "3.Buy Stock by Volume\n" +
            "4.Buy Stock by Amount\n" +
            "5.View Stock\n" +
            "6.Invest In Existing Portfolio By Strategy \n" +
            "7.Save Portfolio\n" +
            "8.Load Portfolio\n" +
            "9.Use Existing Strategy\n" +
            "10.Exit\n" +
            "Please enter portfolio name\n" +
            "Menu\n" +
            "1.Add Portfolio\n" +
            "2.View Portfolio\n" +
            "3.Buy Stock by Volume\n" +
            "4.Buy Stock by Amount\n" +
            "5.View Stock\n" +
            "6.Invest In Existing Portfolio By Strategy \n" +
            "7.Save Portfolio\n" +
            "8.Load Portfolio\n" +
            "9.Use Existing Strategy\n" +
            "10.Exit\n" +
            "Enter the start date in MM/DD/YYYY format\n" +
            "Portfolio Name\t\t\tPortfolio Volume\t\t\tPortfolio Cost Basis\t\t\tPortfolio " +
            "Value\t\t\tPortfolio Commission\n" +
            "PORT\t\t\t\t\t\t.00\t\t\t\t\t\t$0.0\t\t\t\t\t\t$0.0\t\t\t\t\t\t$0.0\n" +
            "(Here Total Cost Basis is sum of the commission paid and amount investment " +
            "made in the stock)\n" +
            "Menu\n" +
            "1.Add Portfolio\n" +
            "2.View Portfolio\n" +
            "3.Buy Stock by Volume\n" +
            "4.Buy Stock by Amount\n" +
            "5.View Stock\n" +
            "6.Invest In Existing Portfolio By Strategy \n" +
            "7.Save Portfolio\n" +
            "8.Load Portfolio\n" +
            "9.Use Existing Strategy\n" +
            "10.Exit\n", output.toString());
  }

  /**
   * This method tests whether adding a blank input.
   */
  @Test
  public void testBlankInput() {
    try {
      stockController = new StockController(null, output);
    } catch (IllegalArgumentException iae) {
      assertEquals("Input Readable object is blank.", iae.getMessage());
    }
  }

  /**
   * This method tests whether adding a blank output.
   */
  @Test
  public void testBlankOutput() {
    try {
      stockController = new StockController(new StringReader(userInput), null);
    } catch (IllegalArgumentException iae) {
      assertEquals("Output Appendable object is blank.", iae.getMessage());
    }
  }

  /**
   * This method tests whether adding a blank input and output.
   */
  @Test
  public void testBlankInputOutput() {
    try {
      stockController = new StockController(null, null);
    } catch (IllegalArgumentException iae) {
      assertEquals("Input Readable object is blank.", iae.getMessage());
    }
  }

  /**
   * This method tests when passing invalid user choice.
   */
  @Test
  public void testInvalidChoice() {
    userInput = "\nsdeds\n8\n0\n-1\n1\nportfolio 1\n10\n";
    output = new StringBuffer();
    stockController = new StockController(new StringReader(userInput), output);
    stockController.handleOperation();
    assertEquals("Menu\n" +
            "1.Add Portfolio\n" +
            "2.View Portfolio\n" +
            "3.Buy Stock by Volume\n" +
            "4.Buy Stock by Amount\n" +
            "5.View Stock\n" +
            "6.Invest In Existing Portfolio By Strategy \n" +
            "7.Save Portfolio\n" +
            "8.Load Portfolio\n" +
            "9.Use Existing Strategy\n" +
            "10.Exit\n" +
            "Menu\n" +
            "1.Add Portfolio\n" +
            "2.View Portfolio\n" +
            "3.Buy Stock by Volume\n" +
            "4.Buy Stock by Amount\n" +
            "5.View Stock\n" +
            "6.Invest In Existing Portfolio By Strategy \n" +
            "7.Save Portfolio\n" +
            "8.Load Portfolio\n" +
            "9.Use Existing Strategy\n" +
            "10.Exit\n" +
            "Menu\n" +
            "1.Add Portfolio\n" +
            "2.View Portfolio\n" +
            "3.Buy Stock by Volume\n" +
            "4.Buy Stock by Amount\n" +
            "5.View Stock\n" +
            "6.Invest In Existing Portfolio By Strategy \n" +
            "7.Save Portfolio\n" +
            "8.Load Portfolio\n" +
            "9.Use Existing Strategy\n" +
            "10.Exit\n" +
            "LOAD PORTFOLIO OPTIONS\n" +
            "1.Load a specific portfolio\n" +
            "2.Load all\n" +
            "Invalid Menu item selected\n" +
            "Menu\n" +
            "1.Add Portfolio\n" +
            "2.View Portfolio\n" +
            "3.Buy Stock by Volume\n" +
            "4.Buy Stock by Amount\n" +
            "5.View Stock\n" +
            "6.Invest In Existing Portfolio By Strategy \n" +
            "7.Save Portfolio\n" +
            "8.Load Portfolio\n" +
            "9.Use Existing Strategy\n" +
            "10.Exit\n" +
            "Invalid Menu item selected\n" +
            "Menu\n" +
            "1.Add Portfolio\n" +
            "2.View Portfolio\n" +
            "3.Buy Stock by Volume\n" +
            "4.Buy Stock by Amount\n" +
            "5.View Stock\n" +
            "6.Invest In Existing Portfolio By Strategy \n" +
            "7.Save Portfolio\n" +
            "8.Load Portfolio\n" +
            "9.Use Existing Strategy\n" +
            "10.Exit\n" +
            "Please enter portfolio name\n" +
            "Menu\n" +
            "1.Add Portfolio\n" +
            "2.View Portfolio\n" +
            "3.Buy Stock by Volume\n" +
            "4.Buy Stock by Amount\n" +
            "5.View Stock\n" +
            "6.Invest In Existing Portfolio By Strategy \n" +
            "7.Save Portfolio\n" +
            "8.Load Portfolio\n" +
            "9.Use Existing Strategy\n" +
            "10.Exit\n", output.toString());
  }

  @Test
  public void testAddAndViewPortfolio() {

    userInput = "1\nportfolio 1\n2\n11/15/2018\n10\n";
    output = new StringBuffer();
    stockController = new StockController(new StringReader(userInput), output);
    stockController.handleOperation();
    assertEquals("Menu\n" +
            "1.Add Portfolio\n" +
            "2.View Portfolio\n" +
            "3.Buy Stock by Volume\n" +
            "4.Buy Stock by Amount\n" +
            "5.View Stock\n" +
            "6.Invest In Existing Portfolio By Strategy \n" +
            "7.Save Portfolio\n" +
            "8.Load Portfolio\n" +
            "9.Use Existing Strategy\n" +
            "10.Exit\n" +
            "Please enter portfolio name\n" +
            "Menu\n" +
            "1.Add Portfolio\n" +
            "2.View Portfolio\n" +
            "3.Buy Stock by Volume\n" +
            "4.Buy Stock by Amount\n" +
            "5.View Stock\n" +
            "6.Invest In Existing Portfolio By Strategy \n" +
            "7.Save Portfolio\n" +
            "8.Load Portfolio\n" +
            "9.Use Existing Strategy\n" +
            "10.Exit\n" +
            "Enter the start date in MM/DD/YYYY format\n" +
            "Portfolio Name\t\t\tPortfolio Volume\t\t\tPortfolio Cost Basis\t\t\tPortfolio " +
            "Value\t\t\tPortfolio Commission\n" +
            "PORTFOLIO 1\t\t\t\t\t\t.00\t\t\t\t\t\t$0.0\t\t\t\t\t\t$0.0\t\t\t\t\t\t$0.0\n" +
            "(Here Total Cost Basis is sum of the commission paid and amount investment " +
            "made in the stock)\n" +
            "Menu\n" +
            "1.Add Portfolio\n" +
            "2.View Portfolio\n" +
            "3.Buy Stock by Volume\n" +
            "4.Buy Stock by Amount\n" +
            "5.View Stock\n" +
            "6.Invest In Existing Portfolio By Strategy \n" +
            "7.Save Portfolio\n" +
            "8.Load Portfolio\n" +
            "9.Use Existing Strategy\n" +
            "10.Exit\n", output.toString());

  }

  @Test
  public void testAddAndViewMultiplePortfolio() {

    userInput = "1\nportfolio 1\n1\nportfolio 2\n2\n11/15/2018\n10\n";
    output = new StringBuffer();
    stockController = new StockController(new StringReader(userInput), output);
    stockController.handleOperation();
    assertEquals("Menu\n" +
            "1.Add Portfolio\n" +
            "2.View Portfolio\n" +
            "3.Buy Stock by Volume\n" +
            "4.Buy Stock by Amount\n" +
            "5.View Stock\n" +
            "6.Invest In Existing Portfolio By Strategy \n" +
            "7.Save Portfolio\n" +
            "8.Load Portfolio\n" +
            "9.Use Existing Strategy\n" +
            "10.Exit\n" +
            "Please enter portfolio name\n" +
            "Menu\n" +
            "1.Add Portfolio\n" +
            "2.View Portfolio\n" +
            "3.Buy Stock by Volume\n" +
            "4.Buy Stock by Amount\n" +
            "5.View Stock\n" +
            "6.Invest In Existing Portfolio By Strategy \n" +
            "7.Save Portfolio\n" +
            "8.Load Portfolio\n" +
            "9.Use Existing Strategy\n" +
            "10.Exit\n" +
            "Please enter portfolio name\n" +
            "Menu\n" +
            "1.Add Portfolio\n" +
            "2.View Portfolio\n" +
            "3.Buy Stock by Volume\n" +
            "4.Buy Stock by Amount\n" +
            "5.View Stock\n" +
            "6.Invest In Existing Portfolio By Strategy \n" +
            "7.Save Portfolio\n" +
            "8.Load Portfolio\n" +
            "9.Use Existing Strategy\n" +
            "10.Exit\n" +
            "Enter the start date in MM/DD/YYYY format\n" +
            "Portfolio Name\t\t\tPortfolio Volume\t\t\tPortfolio Cost Basis\t\t\tPortfolio " +
            "Value\t\t\tPortfolio Commission\n" +
            "PORTFOLIO 1\t\t\t\t\t\t.00\t\t\t\t\t\t$0.0\t\t\t\t\t\t$0.0\t\t\t\t\t\t$0.0\n" +
            "PORTFOLIO 2\t\t\t\t\t\t.00\t\t\t\t\t\t$0.0\t\t\t\t\t\t$0.0\t\t\t\t\t\t$0.0\n" +
            "(Here Total Cost Basis is sum of the commission paid and amount investment " +
            "made in the stock)\n" +
            "Menu\n" +
            "1.Add Portfolio\n" +
            "2.View Portfolio\n" +
            "3.Buy Stock by Volume\n" +
            "4.Buy Stock by Amount\n" +
            "5.View Stock\n" +
            "6.Invest In Existing Portfolio By Strategy \n" +
            "7.Save Portfolio\n" +
            "8.Load Portfolio\n" +
            "9.Use Existing Strategy\n" +
            "10.Exit\n", output.toString());

  }

  /**
   * This method tests adding a portfolio and add stock to that portfolio.
   */
  @Test
  public void testAddPortfolioAddStock() {

    userInput = "1\nportfolio 1\n3\n1\nAAPL\n2\n15\n11/09/2018\n3\n1\nGOOG\n1\n10\n11/09/2018\n" +
            "5\n1\n11/15/2018\n10\n";
    output = new StringBuffer();
    stockController = new StockController(new StringReader(userInput), output);
    stockController.handleOperation();
    assertEquals("Menu\n" +
            "1.Add Portfolio\n" +
            "2.View Portfolio\n" +
            "3.Buy Stock by Volume\n" +
            "4.Buy Stock by Amount\n" +
            "5.View Stock\n" +
            "6.Invest In Existing Portfolio By Strategy \n" +
            "7.Save Portfolio\n" +
            "8.Load Portfolio\n" +
            "9.Use Existing Strategy\n" +
            "10.Exit\n" +
            "Please enter portfolio name\n" +
            "Menu\n" +
            "1.Add Portfolio\n" +
            "2.View Portfolio\n" +
            "3.Buy Stock by Volume\n" +
            "4.Buy Stock by Amount\n" +
            "5.View Stock\n" +
            "6.Invest In Existing Portfolio By Strategy \n" +
            "7.Save Portfolio\n" +
            "8.Load Portfolio\n" +
            "9.Use Existing Strategy\n" +
            "10.Exit\n" +
            "Select a portfolio\n" +
            "\n" +
            "1.PORTFOLIO 1\n" +
            "\n" +
            "Enter ticker symbol\n" +
            "Enter the commission rate\n" +
            "Enter stock volume\n" +
            "Enter the start date in MM/DD/YYYY format\n" +
            "Menu\n" +
            "1.Add Portfolio\n" +
            "2.View Portfolio\n" +
            "3.Buy Stock by Volume\n" +
            "4.Buy Stock by Amount\n" +
            "5.View Stock\n" +
            "6.Invest In Existing Portfolio By Strategy \n" +
            "7.Save Portfolio\n" +
            "8.Load Portfolio\n" +
            "9.Use Existing Strategy\n" +
            "10.Exit\n" +
            "Select a portfolio\n" +
            "\n" +
            "1.PORTFOLIO 1\n" +
            "\n" +
            "Enter ticker symbol\n" +
            "Enter the commission rate\n" +
            "Enter stock volume\n" +
            "Enter the start date in MM/DD/YYYY format\n" +
            "Menu\n" +
            "1.Add Portfolio\n" +
            "2.View Portfolio\n" +
            "3.Buy Stock by Volume\n" +
            "4.Buy Stock by Amount\n" +
            "5.View Stock\n" +
            "6.Invest In Existing Portfolio By Strategy \n" +
            "7.Save Portfolio\n" +
            "8.Load Portfolio\n" +
            "9.Use Existing Strategy\n" +
            "10.Exit\n" +
            "Select a portfolio\n" +
            "\n" +
            "1.PORTFOLIO 1\n" +
            "\n" +
            "Enter the start date in MM/DD/YYYY format\n" +
            "Stock Name\t\t\tVolume\t\t\tStock Cost Basis\t\t\tStock Value\t\t\tCommission_Paid\n" +
            "GOOG\t\t\t 10.00   \t\t\t$10768.11\t\t\t   $10647.10\t\t\t   $106.62\n" +
            "AAPL\t\t\t 15.00   \t\t\t$3128.39\t\t\t   $2871.15\t\t\t   $61.34\n" +
            "TOTAL\t\t\t 25.00\t\t\t   $13896.50\t\t\t   $13518.25\t\t\t   $167.96\n" +
            "(Here Total Cost Basis is sum of the commission paid and amount " +
            "investment made in the stock)\n" +
            "Menu\n" +
            "1.Add Portfolio\n" +
            "2.View Portfolio\n" +
            "3.Buy Stock by Volume\n" +
            "4.Buy Stock by Amount\n" +
            "5.View Stock\n" +
            "6.Invest In Existing Portfolio By Strategy \n" +
            "7.Save Portfolio\n" +
            "8.Load Portfolio\n" +
            "9.Use Existing Strategy\n" +
            "10.Exit\n", output.toString());

  }

  /**
   * This method tests adding a portfolio and add stock to that portfolio.
   */
  @Test
  public void testAddPortfolioAddStockMultipleDate() {

    userInput = "1\nportfolio 1\n3\n1\nAAPL\n2\n15\n11/09/2018\n3\n1\nGOOG\n1\n10\n11/09/2018\n" +
            "5\n1\n11/15/2018\n5\n1\n11/08/2018\n5\n1\n11/20/2018\n10\n";
    output = new StringBuffer();
    stockController = new StockController(new StringReader(userInput), output);
    stockController.handleOperation();
    assertEquals("Menu\n" +
            "1.Add Portfolio\n" +
            "2.View Portfolio\n" +
            "3.Buy Stock by Volume\n" +
            "4.Buy Stock by Amount\n" +
            "5.View Stock\n" +
            "6.Invest In Existing Portfolio By Strategy \n" +
            "7.Save Portfolio\n" +
            "8.Load Portfolio\n" +
            "9.Use Existing Strategy\n" +
            "10.Exit\n" +
            "Please enter portfolio name\n" +
            "Menu\n" +
            "1.Add Portfolio\n" +
            "2.View Portfolio\n" +
            "3.Buy Stock by Volume\n" +
            "4.Buy Stock by Amount\n" +
            "5.View Stock\n" +
            "6.Invest In Existing Portfolio By Strategy \n" +
            "7.Save Portfolio\n" +
            "8.Load Portfolio\n" +
            "9.Use Existing Strategy\n" +
            "10.Exit\n" +
            "Select a portfolio\n" +
            "\n" +
            "1.PORTFOLIO 1\n" +
            "\n" +
            "Enter ticker symbol\n" +
            "Enter the commission rate\n" +
            "Enter stock volume\n" +
            "Enter the start date in MM/DD/YYYY format\n" +
            "Menu\n" +
            "1.Add Portfolio\n" +
            "2.View Portfolio\n" +
            "3.Buy Stock by Volume\n" +
            "4.Buy Stock by Amount\n" +
            "5.View Stock\n" +
            "6.Invest In Existing Portfolio By Strategy \n" +
            "7.Save Portfolio\n" +
            "8.Load Portfolio\n" +
            "9.Use Existing Strategy\n" +
            "10.Exit\n" +
            "Select a portfolio\n" +
            "\n" +
            "1.PORTFOLIO 1\n" +
            "\n" +
            "Enter ticker symbol\n" +
            "Enter the commission rate\n" +
            "Enter stock volume\n" +
            "Enter the start date in MM/DD/YYYY format\n" +
            "Menu\n" +
            "1.Add Portfolio\n" +
            "2.View Portfolio\n" +
            "3.Buy Stock by Volume\n" +
            "4.Buy Stock by Amount\n" +
            "5.View Stock\n" +
            "6.Invest In Existing Portfolio By Strategy \n" +
            "7.Save Portfolio\n" +
            "8.Load Portfolio\n" +
            "9.Use Existing Strategy\n" +
            "10.Exit\n" +
            "Select a portfolio\n" +
            "\n" +
            "1.PORTFOLIO 1\n" +
            "\n" +
            "Enter the start date in MM/DD/YYYY format\n" +
            "Stock Name\t\t\tVolume\t\t\tStock Cost Basis\t\t\tStock Value\t\t\tCommission_Paid\n" +
            "GOOG\t\t\t 10.00   \t\t\t$10768.11\t\t\t   $10647.10\t\t\t   $106.62\n" +
            "AAPL\t\t\t 15.00   \t\t\t$3128.39\t\t\t   $2871.15\t\t\t   $61.34\n" +
            "TOTAL\t\t\t 25.00\t\t\t   $13896.50\t\t\t   $13518.25\t\t\t   $167.96\n" +
            "(Here Total Cost Basis is sum of the commission paid and amount investment " +
            "made in the stock)\n" +
            "Menu\n" +
            "1.Add Portfolio\n" +
            "2.View Portfolio\n" +
            "3.Buy Stock by Volume\n" +
            "4.Buy Stock by Amount\n" +
            "5.View Stock\n" +
            "6.Invest In Existing Portfolio By Strategy \n" +
            "7.Save Portfolio\n" +
            "8.Load Portfolio\n" +
            "9.Use Existing Strategy\n" +
            "10.Exit\n" +
            "Select a portfolio\n" +
            "\n" +
            "1.PORTFOLIO 1\n" +
            "\n" +
            "Enter the start date in MM/DD/YYYY format\n" +
            "Stock Name\t\t\tVolume\t\t\tStock Cost Basis\t\t\tStock Value\t\t\tCommission_Paid\n" +
            "GOOG\t\t\t .00   \t\t\t$.00\t\t\t   $.00\t\t\t   $.00\n" +
            "AAPL\t\t\t .00   \t\t\t$.00\t\t\t   $.00\t\t\t   $.00\n" +
            "TOTAL\t\t\t .00\t\t\t   $.00\t\t\t   $.00\t\t\t   $.00\n" +
            "(Here Total Cost Basis is sum of the commission paid and amount investment " +
            "made in the stock)\n" +
            "Menu\n" +
            "1.Add Portfolio\n" +
            "2.View Portfolio\n" +
            "3.Buy Stock by Volume\n" +
            "4.Buy Stock by Amount\n" +
            "5.View Stock\n" +
            "6.Invest In Existing Portfolio By Strategy \n" +
            "7.Save Portfolio\n" +
            "8.Load Portfolio\n" +
            "9.Use Existing Strategy\n" +
            "10.Exit\n" +
            "Select a portfolio\n" +
            "\n" +
            "1.PORTFOLIO 1\n" +
            "\n" +
            "Enter the start date in MM/DD/YYYY format\n" +
            "Stock Name\t\t\tVolume\t\t\tStock Cost Basis\t\t\tStock Value\t\t\tCommission_Paid\n" +
            "GOOG\t\t\t 10.00   \t\t\t$10768.11\t\t\t   $10257.60\t\t\t   $106.62\n" +
            "AAPL\t\t\t 15.00   \t\t\t$3128.39\t\t\t   $2654.70\t\t\t   $61.34\n" +
            "TOTAL\t\t\t 25.00\t\t\t   $13896.50\t\t\t   $12912.30\t\t\t   $167.96\n" +
            "(Here Total Cost Basis is sum of the commission paid and amount investment " +
            "made in the stock)\n" +
            "Menu\n" +
            "1.Add Portfolio\n" +
            "2.View Portfolio\n" +
            "3.Buy Stock by Volume\n" +
            "4.Buy Stock by Amount\n" +
            "5.View Stock\n" +
            "6.Invest In Existing Portfolio By Strategy \n" +
            "7.Save Portfolio\n" +
            "8.Load Portfolio\n" +
            "9.Use Existing Strategy\n" +
            "10.Exit\n", output.toString());

  }


  /**
   * This method tests adding a portfolio and add stock to that portfolio.
   */
  @Test
  public void testAddMultiplePortfolioAddStock() {
    userInput = "1\nportfolio 1\n3\n1\nAAPL\n2\n15\n11/09/2018\n1\nportfolio " +
            "2\n3\n2\nGOOG\n1\n10\n11/09/2018\n5\n1\n11/15/2018\n5\n2\n11/15/2018\n10\n";
    output = new StringBuffer();
    stockController = new StockController(new StringReader(userInput), output);
    stockController.handleOperation();
    assertEquals("Menu\n" +
            "1.Add Portfolio\n" +
            "2.View Portfolio\n" +
            "3.Buy Stock by Volume\n" +
            "4.Buy Stock by Amount\n" +
            "5.View Stock\n" +
            "6.Invest In Existing Portfolio By Strategy \n" +
            "7.Save Portfolio\n" +
            "8.Load Portfolio\n" +
            "9.Use Existing Strategy\n" +
            "10.Exit\n" +
            "Please enter portfolio name\n" +
            "Menu\n" +
            "1.Add Portfolio\n" +
            "2.View Portfolio\n" +
            "3.Buy Stock by Volume\n" +
            "4.Buy Stock by Amount\n" +
            "5.View Stock\n" +
            "6.Invest In Existing Portfolio By Strategy \n" +
            "7.Save Portfolio\n" +
            "8.Load Portfolio\n" +
            "9.Use Existing Strategy\n" +
            "10.Exit\n" +
            "Select a portfolio\n" +
            "\n" +
            "1.PORTFOLIO 1\n" +
            "\n" +
            "Enter ticker symbol\n" +
            "Enter the commission rate\n" +
            "Enter stock volume\n" +
            "Enter the start date in MM/DD/YYYY format\n" +
            "Menu\n" +
            "1.Add Portfolio\n" +
            "2.View Portfolio\n" +
            "3.Buy Stock by Volume\n" +
            "4.Buy Stock by Amount\n" +
            "5.View Stock\n" +
            "6.Invest In Existing Portfolio By Strategy \n" +
            "7.Save Portfolio\n" +
            "8.Load Portfolio\n" +
            "9.Use Existing Strategy\n" +
            "10.Exit\n" +
            "Please enter portfolio name\n" +
            "Menu\n" +
            "1.Add Portfolio\n" +
            "2.View Portfolio\n" +
            "3.Buy Stock by Volume\n" +
            "4.Buy Stock by Amount\n" +
            "5.View Stock\n" +
            "6.Invest In Existing Portfolio By Strategy \n" +
            "7.Save Portfolio\n" +
            "8.Load Portfolio\n" +
            "9.Use Existing Strategy\n" +
            "10.Exit\n" +
            "Select a portfolio\n" +
            "\n" +
            "\n" +
            "1.PORTFOLIO 1\n" +
            "2.PORTFOLIO 2\n" +
            "\n" +
            "Enter ticker symbol\n" +
            "Enter the commission rate\n" +
            "Enter stock volume\n" +
            "Enter the start date in MM/DD/YYYY format\n" +
            "Menu\n" +
            "1.Add Portfolio\n" +
            "2.View Portfolio\n" +
            "3.Buy Stock by Volume\n" +
            "4.Buy Stock by Amount\n" +
            "5.View Stock\n" +
            "6.Invest In Existing Portfolio By Strategy \n" +
            "7.Save Portfolio\n" +
            "8.Load Portfolio\n" +
            "9.Use Existing Strategy\n" +
            "10.Exit\n" +
            "Select a portfolio\n" +
            "\n" +
            "\n" +
            "1.PORTFOLIO 1\n" +
            "2.PORTFOLIO 2\n" +
            "\n" +
            "Enter the start date in MM/DD/YYYY format\n" +
            "Stock Name\t\t\tVolume\t\t\tStock Cost Basis\t\t\tStock Value\t\t\tCommission_Paid\n" +
            "AAPL\t\t\t 15.00   \t\t\t$3128.39\t\t\t   $2871.15\t\t\t   $61.34\n" +
            "TOTAL\t\t\t 15.00\t\t\t   $3128.39\t\t\t   $2871.15\t\t\t   $61.34\n" +
            "(Here Total Cost Basis is sum of the commission paid and amount investment " +
            "made in the stock)\n" +
            "Menu\n" +
            "1.Add Portfolio\n" +
            "2.View Portfolio\n" +
            "3.Buy Stock by Volume\n" +
            "4.Buy Stock by Amount\n" +
            "5.View Stock\n" +
            "6.Invest In Existing Portfolio By Strategy \n" +
            "7.Save Portfolio\n" +
            "8.Load Portfolio\n" +
            "9.Use Existing Strategy\n" +
            "10.Exit\n" +
            "Select a portfolio\n" +
            "\n" +
            "\n" +
            "1.PORTFOLIO 1\n" +
            "2.PORTFOLIO 2\n" +
            "\n" +
            "Enter the start date in MM/DD/YYYY format\n" +
            "Stock Name\t\t\tVolume\t\t\tStock Cost Basis\t\t\tStock Value\t\t\tCommission_Paid\n" +
            "GOOG\t\t\t 10.00   \t\t\t$10768.11\t\t\t   $10647.10\t\t\t   $106.62\n" +
            "TOTAL\t\t\t 10.00\t\t\t   $10768.11\t\t\t   $10647.10\t\t\t   $106.62\n" +
            "(Here Total Cost Basis is sum of the commission paid and amount investment " +
            "made in the stock)\n" +
            "Menu\n" +
            "1.Add Portfolio\n" +
            "2.View Portfolio\n" +
            "3.Buy Stock by Volume\n" +
            "4.Buy Stock by Amount\n" +
            "5.View Stock\n" +
            "6.Invest In Existing Portfolio By Strategy \n" +
            "7.Save Portfolio\n" +
            "8.Load Portfolio\n" +
            "9.Use Existing Strategy\n" +
            "10.Exit\n", output.toString());
  }

  /**
   * This method tests adding a portfolio and add stock to that portfolio.
   */
  @Test
  public void testAddMultiplePortfolioAddMultipleStock() {
    userInput = "1\nportfolio 1\n3\n1\nAAPL\n2\n15\n11/09/2018\n3\n1\nGOOG\n1\n25\n11/09/2018" +
            "\n5\n1\n11/15/2018\n1\nportfolio " +
            "2\n3\n2\nGOOG\n4\n10\n11/09/2018\n3\n2\nAAPL\n3\n20\n11/09/2018\n" +
            "5\n2\n11/15/2018\n10";
    output = new StringBuffer();
    stockController = new StockController(new StringReader(userInput), output);
    stockController.handleOperation();
    assertEquals("Menu\n" +
            "1.Add Portfolio\n" +
            "2.View Portfolio\n" +
            "3.Buy Stock by Volume\n" +
            "4.Buy Stock by Amount\n" +
            "5.View Stock\n" +
            "6.Invest In Existing Portfolio By Strategy \n" +
            "7.Save Portfolio\n" +
            "8.Load Portfolio\n" +
            "9.Use Existing Strategy\n" +
            "10.Exit\n" +
            "Please enter portfolio name\n" +
            "Menu\n" +
            "1.Add Portfolio\n" +
            "2.View Portfolio\n" +
            "3.Buy Stock by Volume\n" +
            "4.Buy Stock by Amount\n" +
            "5.View Stock\n" +
            "6.Invest In Existing Portfolio By Strategy \n" +
            "7.Save Portfolio\n" +
            "8.Load Portfolio\n" +
            "9.Use Existing Strategy\n" +
            "10.Exit\n" +
            "Select a portfolio\n" +
            "\n" +
            "1.PORTFOLIO 1\n" +
            "\n" +
            "Enter ticker symbol\n" +
            "Enter the commission rate\n" +
            "Enter stock volume\n" +
            "Enter the start date in MM/DD/YYYY format\n" +
            "Menu\n" +
            "1.Add Portfolio\n" +
            "2.View Portfolio\n" +
            "3.Buy Stock by Volume\n" +
            "4.Buy Stock by Amount\n" +
            "5.View Stock\n" +
            "6.Invest In Existing Portfolio By Strategy \n" +
            "7.Save Portfolio\n" +
            "8.Load Portfolio\n" +
            "9.Use Existing Strategy\n" +
            "10.Exit\n" +
            "Select a portfolio\n" +
            "\n" +
            "1.PORTFOLIO 1\n" +
            "\n" +
            "Enter ticker symbol\n" +
            "Enter the commission rate\n" +
            "Enter stock volume\n" +
            "Enter the start date in MM/DD/YYYY format\n" +
            "Menu\n" +
            "1.Add Portfolio\n" +
            "2.View Portfolio\n" +
            "3.Buy Stock by Volume\n" +
            "4.Buy Stock by Amount\n" +
            "5.View Stock\n" +
            "6.Invest In Existing Portfolio By Strategy \n" +
            "7.Save Portfolio\n" +
            "8.Load Portfolio\n" +
            "9.Use Existing Strategy\n" +
            "10.Exit\n" +
            "Select a portfolio\n" +
            "\n" +
            "1.PORTFOLIO 1\n" +
            "\n" +
            "Enter the start date in MM/DD/YYYY format\n" +
            "Stock Name\t\t\tVolume\t\t\tStock Cost Basis\t\t\tStock Value\t\t\tCommission_Paid\n" +
            "GOOG\t\t\t 25.00   \t\t\t$26920.29\t\t\t   $26617.75\t\t\t   $266.54\n" +
            "AAPL\t\t\t 15.00   \t\t\t$3128.39\t\t\t   $2871.15\t\t\t   $61.34\n" +
            "TOTAL\t\t\t 40.00\t\t\t   $30048.68\t\t\t   $29488.90\t\t\t   $327.88\n" +
            "(Here Total Cost Basis is sum of the commission paid and amount investment " +
            "made in the stock)\n" +
            "Menu\n" +
            "1.Add Portfolio\n" +
            "2.View Portfolio\n" +
            "3.Buy Stock by Volume\n" +
            "4.Buy Stock by Amount\n" +
            "5.View Stock\n" +
            "6.Invest In Existing Portfolio By Strategy \n" +
            "7.Save Portfolio\n" +
            "8.Load Portfolio\n" +
            "9.Use Existing Strategy\n" +
            "10.Exit\n" +
            "Please enter portfolio name\n" +
            "Menu\n" +
            "1.Add Portfolio\n" +
            "2.View Portfolio\n" +
            "3.Buy Stock by Volume\n" +
            "4.Buy Stock by Amount\n" +
            "5.View Stock\n" +
            "6.Invest In Existing Portfolio By Strategy \n" +
            "7.Save Portfolio\n" +
            "8.Load Portfolio\n" +
            "9.Use Existing Strategy\n" +
            "10.Exit\n" +
            "Select a portfolio\n" +
            "\n" +
            "\n" +
            "1.PORTFOLIO 1\n" +
            "2.PORTFOLIO 2\n" +
            "\n" +
            "Enter ticker symbol\n" +
            "Enter the commission rate\n" +
            "Enter stock volume\n" +
            "Enter the start date in MM/DD/YYYY format\n" +
            "Menu\n" +
            "1.Add Portfolio\n" +
            "2.View Portfolio\n" +
            "3.Buy Stock by Volume\n" +
            "4.Buy Stock by Amount\n" +
            "5.View Stock\n" +
            "6.Invest In Existing Portfolio By Strategy \n" +
            "7.Save Portfolio\n" +
            "8.Load Portfolio\n" +
            "9.Use Existing Strategy\n" +
            "10.Exit\n" +
            "Select a portfolio\n" +
            "\n" +
            "\n" +
            "1.PORTFOLIO 1\n" +
            "2.PORTFOLIO 2\n" +
            "\n" +
            "Enter ticker symbol\n" +
            "Enter the commission rate\n" +
            "Enter stock volume\n" +
            "Enter the start date in MM/DD/YYYY format\n" +
            "Menu\n" +
            "1.Add Portfolio\n" +
            "2.View Portfolio\n" +
            "3.Buy Stock by Volume\n" +
            "4.Buy Stock by Amount\n" +
            "5.View Stock\n" +
            "6.Invest In Existing Portfolio By Strategy \n" +
            "7.Save Portfolio\n" +
            "8.Load Portfolio\n" +
            "9.Use Existing Strategy\n" +
            "10.Exit\n" +
            "Select a portfolio\n" +
            "\n" +
            "\n" +
            "1.PORTFOLIO 1\n" +
            "2.PORTFOLIO 2\n" +
            "\n" +
            "Enter the start date in MM/DD/YYYY format\n" +
            "Stock Name\t\t\tVolume\t\t\tStock Cost Basis\t\t\tStock Value\t\t\tCommission_Paid\n" +
            "GOOG\t\t\t 10.00   \t\t\t$11087.96\t\t\t   $10647.10\t\t\t   $426.46\n" +
            "AAPL\t\t\t 20.00   \t\t\t$4212.08\t\t\t   $3828.20\t\t\t   $122.68\n" +
            "TOTAL\t\t\t 30.00\t\t\t   $15300.04\t\t\t   $14475.30\t\t\t   $549.14\n" +
            "(Here Total Cost Basis is sum of the commission paid and amount investment " +
            "made in the stock)\n" +
            "Menu\n" +
            "1.Add Portfolio\n" +
            "2.View Portfolio\n" +
            "3.Buy Stock by Volume\n" +
            "4.Buy Stock by Amount\n" +
            "5.View Stock\n" +
            "6.Invest In Existing Portfolio By Strategy \n" +
            "7.Save Portfolio\n" +
            "8.Load Portfolio\n" +
            "9.Use Existing Strategy\n" +
            "10.Exit\n", output.toString());
  }

  /**
   * This method tests duplicate portfolio name.
   */
  @Test
  public void testDuplicatePortfolio() {
    userInput = "1\nport\n1\nport\nport 2\n10";
    output = new StringBuffer();
    stockController = new StockController(new StringReader(userInput), output);
    stockController.handleOperation();
    assertEquals("Menu\n" +
            "1.Add Portfolio\n" +
            "2.View Portfolio\n" +
            "3.Buy Stock by Volume\n" +
            "4.Buy Stock by Amount\n" +
            "5.View Stock\n" +
            "6.Invest In Existing Portfolio By Strategy \n" +
            "7.Save Portfolio\n" +
            "8.Load Portfolio\n" +
            "9.Use Existing Strategy\n" +
            "10.Exit\n" +
            "Please enter portfolio name\n" +
            "Menu\n" +
            "1.Add Portfolio\n" +
            "2.View Portfolio\n" +
            "3.Buy Stock by Volume\n" +
            "4.Buy Stock by Amount\n" +
            "5.View Stock\n" +
            "6.Invest In Existing Portfolio By Strategy \n" +
            "7.Save Portfolio\n" +
            "8.Load Portfolio\n" +
            "9.Use Existing Strategy\n" +
            "10.Exit\n" +
            "Please enter portfolio name\n" +
            "Invalid portfolio name or duplicate portfolio name\n" +
            "Please enter portfolio name\n" +
            "Menu\n" +
            "1.Add Portfolio\n" +
            "2.View Portfolio\n" +
            "3.Buy Stock by Volume\n" +
            "4.Buy Stock by Amount\n" +
            "5.View Stock\n" +
            "6.Invest In Existing Portfolio By Strategy \n" +
            "7.Save Portfolio\n" +
            "8.Load Portfolio\n" +
            "9.Use Existing Strategy\n" +
            "10.Exit\n", output.toString());
  }

  /**
   * This method tests duplicate portfolio name.
   */
  @Test
  public void testInvalidViewPortfolio() {
    userInput = "1\nport\n2\n3\n1\nsdwds\n12/25/2018\n11/15/2018\n10";
    output = new StringBuffer();
    stockController = new StockController(new StringReader(userInput), output);
    stockController.handleOperation();
    assertEquals("Menu\n" +
            "1.Add Portfolio\n" +
            "2.View Portfolio\n" +
            "3.Buy Stock by Volume\n" +
            "4.Buy Stock by Amount\n" +
            "5.View Stock\n" +
            "6.Invest In Existing Portfolio By Strategy \n" +
            "7.Save Portfolio\n" +
            "8.Load Portfolio\n" +
            "9.Use Existing Strategy\n" +
            "10.Exit\n" +
            "Please enter portfolio name\n" +
            "Menu\n" +
            "1.Add Portfolio\n" +
            "2.View Portfolio\n" +
            "3.Buy Stock by Volume\n" +
            "4.Buy Stock by Amount\n" +
            "5.View Stock\n" +
            "6.Invest In Existing Portfolio By Strategy \n" +
            "7.Save Portfolio\n" +
            "8.Load Portfolio\n" +
            "9.Use Existing Strategy\n" +
            "10.Exit\n" +
            "Enter the start date in MM/DD/YYYY format\n" +
            "Invalid date\n" +
            "Enter the start date in MM/DD/YYYY format\n" +
            "Invalid date\n" +
            "Enter the start date in MM/DD/YYYY format\n" +
            "Invalid date\n" +
            "Enter the start date in MM/DD/YYYY format\n" +
            "Portfolio Name\t\t\tPortfolio Volume\t\t\tPortfolio Cost Basis\t\t\tPortfolio Value\t\t\tPortfolio Commission\n" +
            "PORT\t\t\t\t\t\t.00\t\t\t\t\t\t$0.0\t\t\t\t\t\t$0.0\t\t\t\t\t\t$0.0\n" +
            "(Here Total Cost Basis is sum of the commission paid and amount investment " +
            "made in the stock)\n" +
            "Menu\n" +
            "1.Add Portfolio\n" +
            "2.View Portfolio\n" +
            "3.Buy Stock by Volume\n" +
            "4.Buy Stock by Amount\n" +
            "5.View Stock\n" +
            "6.Invest In Existing Portfolio By Strategy \n" +
            "7.Save Portfolio\n" +
            "8.Load Portfolio\n" +
            "9.Use Existing Strategy\n" +
            "10.Exit\n" +
            "Menu\n" +
            "1.Add Portfolio\n" +
            "2.View Portfolio\n" +
            "3.Buy Stock by Volume\n" +
            "4.Buy Stock by Amount\n" +
            "5.View Stock\n" +
            "6.Invest In Existing Portfolio By Strategy \n" +
            "7.Save Portfolio\n" +
            "8.Load Portfolio\n" +
            "9.Use Existing Strategy\n" +
            "10.Exit\n", output.toString());
  }

  /**
   * This method tests duplicate portfolio name.
   */
  @Test
  public void testInvalidAddStockPortfolio() {
    userInput = "1\nport\n3\n3\n2\n1\nQWERTY123\n2\n10\n11/09/2018\nAAPL\n5\n1\n11/12" +
            "/2018\n10";
    output = new StringBuffer();
    stockController = new StockController(new StringReader(userInput), output);
    stockController.handleOperation();
    assertEquals("Menu\n" +
            "1.Add Portfolio\n" +
            "2.View Portfolio\n" +
            "3.Buy Stock by Volume\n" +
            "4.Buy Stock by Amount\n" +
            "5.View Stock\n" +
            "6.Invest In Existing Portfolio By Strategy \n" +
            "7.Save Portfolio\n" +
            "8.Load Portfolio\n" +
            "9.Use Existing Strategy\n" +
            "10.Exit\n" +
            "Please enter portfolio name\n" +
            "Menu\n" +
            "1.Add Portfolio\n" +
            "2.View Portfolio\n" +
            "3.Buy Stock by Volume\n" +
            "4.Buy Stock by Amount\n" +
            "5.View Stock\n" +
            "6.Invest In Existing Portfolio By Strategy \n" +
            "7.Save Portfolio\n" +
            "8.Load Portfolio\n" +
            "9.Use Existing Strategy\n" +
            "10.Exit\n" +
            "Select a portfolio\n" +
            "\n" +
            "1.PORT\n" +
            "\n" +
            "Invalid portfolio Id\n" +
            "Select a portfolio\n" +
            "\n" +
            "1.PORT\n" +
            "Invalid portfolio Id\n" +
            "Select a portfolio\n" +
            "\n" +
            "1.PORT\n" +
            "Enter ticker symbol\n" +
            "Enter the commission rate\n" +
            "Enter stock volume\n" +
            "Enter the start date in MM/DD/YYYY format\n" +
            "Invalid ticker symbol\n" +
            "Enter ticker symbol\n" +
            "Menu\n" +
            "1.Add Portfolio\n" +
            "2.View Portfolio\n" +
            "3.Buy Stock by Volume\n" +
            "4.Buy Stock by Amount\n" +
            "5.View Stock\n" +
            "6.Invest In Existing Portfolio By Strategy \n" +
            "7.Save Portfolio\n" +
            "8.Load Portfolio\n" +
            "9.Use Existing Strategy\n" +
            "10.Exit\n" +
            "Select a portfolio\n" +
            "\n" +
            "1.PORT\n" +
            "\n" +
            "Enter the start date in MM/DD/YYYY format\n" +
            "Stock Name\t\t\tVolume\t\t\tStock Cost Basis\t\t\tStock Value\t\t\tCommission_Paid\n" +
            "AAPL\t\t\t 10.00   \t\t\t$2085.59\t\t\t   $1941.70\t\t\t   $40.89\n" +
            "TOTAL\t\t\t 10.00\t\t\t   $2085.59\t\t\t   $1941.70\t\t\t   $40.89\n" +
            "(Here Total Cost Basis is sum of the commission paid and amount investment " +
            "made in the stock)\n" +
            "Menu\n" +
            "1.Add Portfolio\n" +
            "2.View Portfolio\n" +
            "3.Buy Stock by Volume\n" +
            "4.Buy Stock by Amount\n" +
            "5.View Stock\n" +
            "6.Invest In Existing Portfolio By Strategy \n" +
            "7.Save Portfolio\n" +
            "8.Load Portfolio\n" +
            "9.Use Existing Strategy\n" +
            "10.Exit\n", output.toString());
  }


  /**
   * This method tests add stock with commission.
   */
  @Test
  public void testAddStockWithCommission() {
    userInput = "1\nport\n3\n1\nAAPL\n2\n12\n11/09/2018\n5\n1\n11/14/2018\n10\n";
    output = new StringBuffer();
    stockController = new StockController(new StringReader(userInput), output);
    stockController.handleOperation();
    assertEquals("Menu\n" +
            "1.Add Portfolio\n" +
            "2.View Portfolio\n" +
            "3.Buy Stock by Volume\n" +
            "4.Buy Stock by Amount\n" +
            "5.View Stock\n" +
            "6.Invest In Existing Portfolio By Strategy \n" +
            "7.Save Portfolio\n" +
            "8.Load Portfolio\n" +
            "9.Use Existing Strategy\n" +
            "10.Exit\n" +
            "Please enter portfolio name\n" +
            "Menu\n" +
            "1.Add Portfolio\n" +
            "2.View Portfolio\n" +
            "3.Buy Stock by Volume\n" +
            "4.Buy Stock by Amount\n" +
            "5.View Stock\n" +
            "6.Invest In Existing Portfolio By Strategy \n" +
            "7.Save Portfolio\n" +
            "8.Load Portfolio\n" +
            "9.Use Existing Strategy\n" +
            "10.Exit\n" +
            "Select a portfolio\n" +
            "\n" +
            "1.PORT\n" +
            "\n" +
            "Enter ticker symbol\n" +
            "Enter the commission rate\n" +
            "Enter stock volume\n" +
            "Enter the start date in MM/DD/YYYY format\n" +
            "Menu\n" +
            "1.Add Portfolio\n" +
            "2.View Portfolio\n" +
            "3.Buy Stock by Volume\n" +
            "4.Buy Stock by Amount\n" +
            "5.View Stock\n" +
            "6.Invest In Existing Portfolio By Strategy \n" +
            "7.Save Portfolio\n" +
            "8.Load Portfolio\n" +
            "9.Use Existing Strategy\n" +
            "10.Exit\n" +
            "Select a portfolio\n" +
            "\n" +
            "1.PORT\n" +
            "\n" +
            "Enter the start date in MM/DD/YYYY format\n" +
            "Stock Name\t\t\tVolume\t\t\tStock Cost Basis\t\t\tStock Value\t\t\tCommission_Paid\n" +
            "AAPL\t\t\t 12.00   \t\t\t$2502.71\t\t\t   $2241.60\t\t\t   $49.07\n" +
            "TOTAL\t\t\t 12.00\t\t\t   $2502.71\t\t\t   $2241.60\t\t\t   $49.07\n" +
            "(Here Total Cost Basis is sum of the commission paid and amount investment " +
            "made in the stock)\n" +
            "Menu\n" +
            "1.Add Portfolio\n" +
            "2.View Portfolio\n" +
            "3.Buy Stock by Volume\n" +
            "4.Buy Stock by Amount\n" +
            "5.View Stock\n" +
            "6.Invest In Existing Portfolio By Strategy \n" +
            "7.Save Portfolio\n" +
            "8.Load Portfolio\n" +
            "9.Use Existing Strategy\n" +
            "10.Exit\n", output.toString());
  }


  /**
   * This method tests add stock with invalid commission.
   */
  @Test
  public void testInvalidAddStockWithCommission() {
    userInput = "1\nport\n3\n1\nAAPL\n-2\n2\n12\n11/09/2018\n5\n1\n11/14/2018\n10\n";
    output = new StringBuffer();
    stockController = new StockController(new StringReader(userInput), output);
    stockController.handleOperation();
    assertEquals("Menu\n" +
            "1.Add Portfolio\n" +
            "2.View Portfolio\n" +
            "3.Buy Stock by Volume\n" +
            "4.Buy Stock by Amount\n" +
            "5.View Stock\n" +
            "6.Invest In Existing Portfolio By Strategy \n" +
            "7.Save Portfolio\n" +
            "8.Load Portfolio\n" +
            "9.Use Existing Strategy\n" +
            "10.Exit\n" +
            "Please enter portfolio name\n" +
            "Menu\n" +
            "1.Add Portfolio\n" +
            "2.View Portfolio\n" +
            "3.Buy Stock by Volume\n" +
            "4.Buy Stock by Amount\n" +
            "5.View Stock\n" +
            "6.Invest In Existing Portfolio By Strategy \n" +
            "7.Save Portfolio\n" +
            "8.Load Portfolio\n" +
            "9.Use Existing Strategy\n" +
            "10.Exit\n" +
            "Select a portfolio\n" +
            "\n" +
            "1.PORT\n" +
            "\n" +
            "Enter ticker symbol\n" +
            "Enter the commission rate\n" +
            "Commission Rate cannot be zero\n" +
            "Enter the commission rate\n" +
            "Enter stock volume\n" +
            "Enter the start date in MM/DD/YYYY format\n" +
            "Menu\n" +
            "1.Add Portfolio\n" +
            "2.View Portfolio\n" +
            "3.Buy Stock by Volume\n" +
            "4.Buy Stock by Amount\n" +
            "5.View Stock\n" +
            "6.Invest In Existing Portfolio By Strategy \n" +
            "7.Save Portfolio\n" +
            "8.Load Portfolio\n" +
            "9.Use Existing Strategy\n" +
            "10.Exit\n" +
            "Select a portfolio\n" +
            "\n" +
            "1.PORT\n" +
            "\n" +
            "Enter the start date in MM/DD/YYYY format\n" +
            "Stock Name\t\t\tVolume\t\t\tStock Cost Basis\t\t\tStock Value\t\t\tCommission_Paid\n" +
            "AAPL\t\t\t 12.00   \t\t\t$2502.71\t\t\t   $2241.60\t\t\t   $49.07\n" +
            "TOTAL\t\t\t 12.00\t\t\t   $2502.71\t\t\t   $2241.60\t\t\t   $49.07\n" +
            "(Here Total Cost Basis is sum of the commission paid and amount investment " +
            "made in the stock)\n" +
            "Menu\n" +
            "1.Add Portfolio\n" +
            "2.View Portfolio\n" +
            "3.Buy Stock by Volume\n" +
            "4.Buy Stock by Amount\n" +
            "5.View Stock\n" +
            "6.Invest In Existing Portfolio By Strategy \n" +
            "7.Save Portfolio\n" +
            "8.Load Portfolio\n" +
            "9.Use Existing Strategy\n" +
            "10.Exit\n", output.toString());

    userInput = "1\nport\n3\n1\nAAPL\n0\n12\n11/09/2018\n5\n1\n11/14/2018\n10\n";
    output = new StringBuffer();
    stockController = new StockController(new StringReader(userInput), output);
    stockController.handleOperation();
    assertEquals("Menu\n" +
            "1.Add Portfolio\n" +
            "2.View Portfolio\n" +
            "3.Buy Stock by Volume\n" +
            "4.Buy Stock by Amount\n" +
            "5.View Stock\n" +
            "6.Invest In Existing Portfolio By Strategy \n" +
            "7.Save Portfolio\n" +
            "8.Load Portfolio\n" +
            "9.Use Existing Strategy\n" +
            "10.Exit\n" +
            "Please enter portfolio name\n" +
            "Menu\n" +
            "1.Add Portfolio\n" +
            "2.View Portfolio\n" +
            "3.Buy Stock by Volume\n" +
            "4.Buy Stock by Amount\n" +
            "5.View Stock\n" +
            "6.Invest In Existing Portfolio By Strategy \n" +
            "7.Save Portfolio\n" +
            "8.Load Portfolio\n" +
            "9.Use Existing Strategy\n" +
            "10.Exit\n" +
            "Select a portfolio\n" +
            "\n" +
            "1.PORT\n" +
            "\n" +
            "Enter ticker symbol\n" +
            "Enter the commission rate\n" +
            "Enter stock volume\n" +
            "Enter the start date in MM/DD/YYYY format\n" +
            "Menu\n" +
            "1.Add Portfolio\n" +
            "2.View Portfolio\n" +
            "3.Buy Stock by Volume\n" +
            "4.Buy Stock by Amount\n" +
            "5.View Stock\n" +
            "6.Invest In Existing Portfolio By Strategy \n" +
            "7.Save Portfolio\n" +
            "8.Load Portfolio\n" +
            "9.Use Existing Strategy\n" +
            "10.Exit\n" +
            "Select a portfolio\n" +
            "\n" +
            "1.PORT\n" +
            "\n" +
            "Enter the start date in MM/DD/YYYY format\n" +
            "Stock Name\t\t\tVolume\t\t\tStock Cost Basis\t\t\tStock Value\t\t\tCommission_Paid\n" +
            "AAPL\t\t\t 12.00   \t\t\t$2453.64\t\t\t   $2241.60\t\t\t   $.00\n" +
            "TOTAL\t\t\t 12.00\t\t\t   $2453.64\t\t\t   $2241.60\t\t\t   $.00\n" +
            "(Here Total Cost Basis is sum of the commission paid and amount investment " +
            "made in the stock)\n" +
            "Menu\n" +
            "1.Add Portfolio\n" +
            "2.View Portfolio\n" +
            "3.Buy Stock by Volume\n" +
            "4.Buy Stock by Amount\n" +
            "5.View Stock\n" +
            "6.Invest In Existing Portfolio By Strategy \n" +
            "7.Save Portfolio\n" +
            "8.Load Portfolio\n" +
            "9.Use Existing Strategy\n" +
            "10.Exit\n", output.toString());

    userInput = "1\nport\n3\n1\nAAPL\n104\n2\n12\n11/09/2018\n5\n1\n11/14/2018\n10\n";
    output = new StringBuffer();
    stockController = new StockController(new StringReader(userInput), output);
    stockController.handleOperation();
    assertEquals("Menu\n" +
            "1.Add Portfolio\n" +
            "2.View Portfolio\n" +
            "3.Buy Stock by Volume\n" +
            "4.Buy Stock by Amount\n" +
            "5.View Stock\n" +
            "6.Invest In Existing Portfolio By Strategy \n" +
            "7.Save Portfolio\n" +
            "8.Load Portfolio\n" +
            "9.Use Existing Strategy\n" +
            "10.Exit\n" +
            "Please enter portfolio name\n" +
            "Menu\n" +
            "1.Add Portfolio\n" +
            "2.View Portfolio\n" +
            "3.Buy Stock by Volume\n" +
            "4.Buy Stock by Amount\n" +
            "5.View Stock\n" +
            "6.Invest In Existing Portfolio By Strategy \n" +
            "7.Save Portfolio\n" +
            "8.Load Portfolio\n" +
            "9.Use Existing Strategy\n" +
            "10.Exit\n" +
            "Select a portfolio\n" +
            "\n" +
            "1.PORT\n" +
            "\n" +
            "Enter ticker symbol\n" +
            "Enter the commission rate\n" +
            "Commission Rate cannot be greater than 100\n" +
            "Enter the commission rate\n" +
            "Enter stock volume\n" +
            "Enter the start date in MM/DD/YYYY format\n" +
            "Menu\n" +
            "1.Add Portfolio\n" +
            "2.View Portfolio\n" +
            "3.Buy Stock by Volume\n" +
            "4.Buy Stock by Amount\n" +
            "5.View Stock\n" +
            "6.Invest In Existing Portfolio By Strategy \n" +
            "7.Save Portfolio\n" +
            "8.Load Portfolio\n" +
            "9.Use Existing Strategy\n" +
            "10.Exit\n" +
            "Select a portfolio\n" +
            "\n" +
            "1.PORT\n" +
            "\n" +
            "Enter the start date in MM/DD/YYYY format\n" +
            "Stock Name\t\t\tVolume\t\t\tStock Cost Basis\t\t\tStock Value\t\t\tCommission_Paid\n" +
            "AAPL\t\t\t 12.00   \t\t\t$2502.71\t\t\t   $2241.60\t\t\t   $49.07\n" +
            "TOTAL\t\t\t 12.00\t\t\t   $2502.71\t\t\t   $2241.60\t\t\t   $49.07\n" +
            "(Here Total Cost Basis is sum of the commission paid and amount investment " +
            "made in the stock)\n" +
            "Menu\n" +
            "1.Add Portfolio\n" +
            "2.View Portfolio\n" +
            "3.Buy Stock by Volume\n" +
            "4.Buy Stock by Amount\n" +
            "5.View Stock\n" +
            "6.Invest In Existing Portfolio By Strategy \n" +
            "7.Save Portfolio\n" +
            "8.Load Portfolio\n" +
            "9.Use Existing Strategy\n" +
            "10.Exit\n", output.toString());
  }


  /**
   * This method tests one time investment which is equal weighted.
   */
  @Test
  public void testOneTimeInvestmentEqualWeighted() {
    userInput = "1\nport\n3\n1\nAAPL\n2\n12\n11/09/2018\n3\n1\nGOOG\n2\n20\n11/23/2018\n" +
            "6\n1\n3\n2000\n1\n11/23/2018\n2\n5\n1\n11/23/2018\n10\n";
    output = new StringBuffer();
    stockController = new StockController(new StringReader(userInput), output);
    stockController.handleOperation();
    assertEquals("Menu\n" +
            "1.Add Portfolio\n" +
            "2.View Portfolio\n" +
            "3.Buy Stock by Volume\n" +
            "4.Buy Stock by Amount\n" +
            "5.View Stock\n" +
            "6.Invest In Existing Portfolio By Strategy \n" +
            "7.Save Portfolio\n" +
            "8.Load Portfolio\n" +
            "9.Use Existing Strategy\n" +
            "10.Exit\n" +
            "Please enter portfolio name\n" +
            "Menu\n" +
            "1.Add Portfolio\n" +
            "2.View Portfolio\n" +
            "3.Buy Stock by Volume\n" +
            "4.Buy Stock by Amount\n" +
            "5.View Stock\n" +
            "6.Invest In Existing Portfolio By Strategy \n" +
            "7.Save Portfolio\n" +
            "8.Load Portfolio\n" +
            "9.Use Existing Strategy\n" +
            "10.Exit\n" +
            "Select a portfolio\n" +
            "\n" +
            "1.PORT\n" +
            "\n" +
            "Enter ticker symbol\n" +
            "Enter the commission rate\n" +
            "Enter stock volume\n" +
            "Enter the start date in MM/DD/YYYY format\n" +
            "Menu\n" +
            "1.Add Portfolio\n" +
            "2.View Portfolio\n" +
            "3.Buy Stock by Volume\n" +
            "4.Buy Stock by Amount\n" +
            "5.View Stock\n" +
            "6.Invest In Existing Portfolio By Strategy \n" +
            "7.Save Portfolio\n" +
            "8.Load Portfolio\n" +
            "9.Use Existing Strategy\n" +
            "10.Exit\n" +
            "Select a portfolio\n" +
            "\n" +
            "1.PORT\n" +
            "\n" +
            "Enter ticker symbol\n" +
            "Enter the commission rate\n" +
            "Enter stock volume\n" +
            "Enter the start date in MM/DD/YYYY format\n" +
            "Menu\n" +
            "1.Add Portfolio\n" +
            "2.View Portfolio\n" +
            "3.Buy Stock by Volume\n" +
            "4.Buy Stock by Amount\n" +
            "5.View Stock\n" +
            "6.Invest In Existing Portfolio By Strategy \n" +
            "7.Save Portfolio\n" +
            "8.Load Portfolio\n" +
            "9.Use Existing Strategy\n" +
            "10.Exit\n" +
            "Select a portfolio\n" +
            "\n" +
            "1.PORT\n" +
            "\n" +
            "Enter the commission rate\n" +
            "Enter the amount to be invested in the stock\n" +
            "INVESTMENT OPTIONS\n" +
            "1.One Time Investment\n" +
            "2.DCA Strategy\n" +
            "ONE TIME INVESTMENT\n" +
            "Enter the start date in MM/DD/YYYY format\n" +
            "AMOUNT DISTRIBUTION OPTIONS\n" +
            "1.Custom Weighted Investment\n" +
            "2.Equal Weighted Investment\n" +
            "Weight for each stock is: 50.0Menu\n" +
            "1.Add Portfolio\n" +
            "2.View Portfolio\n" +
            "3.Buy Stock by Volume\n" +
            "4.Buy Stock by Amount\n" +
            "5.View Stock\n" +
            "6.Invest In Existing Portfolio By Strategy \n" +
            "7.Save Portfolio\n" +
            "8.Load Portfolio\n" +
            "9.Use Existing Strategy\n" +
            "10.Exit\n" +
            "Select a portfolio\n" +
            "\n" +
            "1.PORT\n" +
            "\n" +
            "Enter the start date in MM/DD/YYYY format\n" +
            "Stock Name\t\t\tVolume\t\t\tStock Cost Basis\t\t\tStock Value\t\t\tCommission_Paid\n" +
            "GOOG\t\t\t 20.98   \t\t\t$21917.15\t\t\t   $41955.20\t\t\t   $439.55\n" +
            "AAPL\t\t\t 17.80   \t\t\t$3532.71\t\t\t   $5134.96\t\t\t   $79.07\n" +
            "TOTAL\t\t\t 38.78\t\t\t   $25449.86\t\t\t   $47090.16\t\t\t   $518.62\n" +
            "(Here Total Cost Basis is sum of the commission paid and amount investment" +
            " made in the stock)\n" +
            "Menu\n" +
            "1.Add Portfolio\n" +
            "2.View Portfolio\n" +
            "3.Buy Stock by Volume\n" +
            "4.Buy Stock by Amount\n" +
            "5.View Stock\n" +
            "6.Invest In Existing Portfolio By Strategy \n" +
            "7.Save Portfolio\n" +
            "8.Load Portfolio\n" +
            "9.Use Existing Strategy\n" +
            "10.Exit\n", output.toString());
  }


  /**
   * This method tests multiple one time investment which is equal weighted.
   */
  @Test
  public void testMultipleOneTimeInvestmentEqualWeighted() {
    userInput = "1\nport\n3\n1\nAAPL\n2\n12\n11/09/2018\n3\n1\nGOOG\n2\n20\n11/09/2018\n" +
            "6\n1\n3\n2000\n1\n11/23/2018\n2\n5\n1\n11/23/2018\n6\n1\n5\n5000\n1\n11/26/2018\n2" +
            "\n5\n1\n11/23/2018\n10\n";
    output = new StringBuffer();
    stockController = new StockController(new StringReader(userInput), output);
    stockController.handleOperation();
    assertEquals("Menu\n" +
            "1.Add Portfolio\n" +
            "2.View Portfolio\n" +
            "3.Buy Stock by Volume\n" +
            "4.Buy Stock by Amount\n" +
            "5.View Stock\n" +
            "6.Invest In Existing Portfolio By Strategy \n" +
            "7.Save Portfolio\n" +
            "8.Load Portfolio\n" +
            "9.Use Existing Strategy\n" +
            "10.Exit\n" +
            "Please enter portfolio name\n" +
            "Menu\n" +
            "1.Add Portfolio\n" +
            "2.View Portfolio\n" +
            "3.Buy Stock by Volume\n" +
            "4.Buy Stock by Amount\n" +
            "5.View Stock\n" +
            "6.Invest In Existing Portfolio By Strategy \n" +
            "7.Save Portfolio\n" +
            "8.Load Portfolio\n" +
            "9.Use Existing Strategy\n" +
            "10.Exit\n" +
            "Select a portfolio\n" +
            "\n" +
            "1.PORT\n" +
            "\n" +
            "Enter ticker symbol\n" +
            "Enter the commission rate\n" +
            "Enter stock volume\n" +
            "Enter the start date in MM/DD/YYYY format\n" +
            "Menu\n" +
            "1.Add Portfolio\n" +
            "2.View Portfolio\n" +
            "3.Buy Stock by Volume\n" +
            "4.Buy Stock by Amount\n" +
            "5.View Stock\n" +
            "6.Invest In Existing Portfolio By Strategy \n" +
            "7.Save Portfolio\n" +
            "8.Load Portfolio\n" +
            "9.Use Existing Strategy\n" +
            "10.Exit\n" +
            "Select a portfolio\n" +
            "\n" +
            "1.PORT\n" +
            "\n" +
            "Enter ticker symbol\n" +
            "Enter the commission rate\n" +
            "Enter stock volume\n" +
            "Enter the start date in MM/DD/YYYY format\n" +
            "Menu\n" +
            "1.Add Portfolio\n" +
            "2.View Portfolio\n" +
            "3.Buy Stock by Volume\n" +
            "4.Buy Stock by Amount\n" +
            "5.View Stock\n" +
            "6.Invest In Existing Portfolio By Strategy \n" +
            "7.Save Portfolio\n" +
            "8.Load Portfolio\n" +
            "9.Use Existing Strategy\n" +
            "10.Exit\n" +
            "Select a portfolio\n" +
            "\n" +
            "1.PORT\n" +
            "\n" +
            "Enter the commission rate\n" +
            "Enter the amount to be invested in the stock\n" +
            "INVESTMENT OPTIONS\n" +
            "1.One Time Investment\n" +
            "2.DCA Strategy\n" +
            "ONE TIME INVESTMENT\n" +
            "Enter the start date in MM/DD/YYYY format\n" +
            "AMOUNT DISTRIBUTION OPTIONS\n" +
            "1.Custom Weighted Investment\n" +
            "2.Equal Weighted Investment\n" +
            "Weight for each stock is: 50.0Menu\n" +
            "1.Add Portfolio\n" +
            "2.View Portfolio\n" +
            "3.Buy Stock by Volume\n" +
            "4.Buy Stock by Amount\n" +
            "5.View Stock\n" +
            "6.Invest In Existing Portfolio By Strategy \n" +
            "7.Save Portfolio\n" +
            "8.Load Portfolio\n" +
            "9.Use Existing Strategy\n" +
            "10.Exit\n" +
            "Select a portfolio\n" +
            "\n" +
            "1.PORT\n" +
            "\n" +
            "Enter the start date in MM/DD/YYYY format\n" +
            "Stock Name\t\t\tVolume\t\t\tStock Cost Basis\t\t\tStock Value\t\t\tCommission_Paid\n" +
            "GOOG\t\t\t 20.98   \t\t\t$22779.46\t\t\t   $41955.20\t\t\t   $456.46\n" +
            "AAPL\t\t\t 17.80   \t\t\t$3532.71\t\t\t   $5134.96\t\t\t   $79.07\n" +
            "TOTAL\t\t\t 38.78\t\t\t   $26312.17\t\t\t   $47090.16\t\t\t   $535.53\n" +
            "(Here Total Cost Basis is sum of the commission paid and amount investment" +
            " made in the stock)\n" +
            "Menu\n" +
            "1.Add Portfolio\n" +
            "2.View Portfolio\n" +
            "3.Buy Stock by Volume\n" +
            "4.Buy Stock by Amount\n" +
            "5.View Stock\n" +
            "6.Invest In Existing Portfolio By Strategy \n" +
            "7.Save Portfolio\n" +
            "8.Load Portfolio\n" +
            "9.Use Existing Strategy\n" +
            "10.Exit\n" +
            "Select a portfolio\n" +
            "\n" +
            "1.PORT\n" +
            "\n" +
            "Enter the commission rate\n" +
            "Enter the amount to be invested in the stock\n" +
            "INVESTMENT OPTIONS\n" +
            "1.One Time Investment\n" +
            "2.DCA Strategy\n" +
            "ONE TIME INVESTMENT\n" +
            "Enter the start date in MM/DD/YYYY format\n" +
            "AMOUNT DISTRIBUTION OPTIONS\n" +
            "1.Custom Weighted Investment\n" +
            "2.Equal Weighted Investment\n" +
            "Weight for each stock is: 50.0Menu\n" +
            "1.Add Portfolio\n" +
            "2.View Portfolio\n" +
            "3.Buy Stock by Volume\n" +
            "4.Buy Stock by Amount\n" +
            "5.View Stock\n" +
            "6.Invest In Existing Portfolio By Strategy \n" +
            "7.Save Portfolio\n" +
            "8.Load Portfolio\n" +
            "9.Use Existing Strategy\n" +
            "10.Exit\n" +
            "Select a portfolio\n" +
            "\n" +
            "1.PORT\n" +
            "\n" +
            "Enter the start date in MM/DD/YYYY format\n" +
            "Stock Name\t\t\tVolume\t\t\tStock Cost Basis\t\t\tStock Value\t\t\tCommission_Paid\n" +
            "GOOG\t\t\t 23.36   \t\t\t$22779.46\t\t\t   $41955.20\t\t\t   $456.46\n" +
            "AAPL\t\t\t 32.12   \t\t\t$3532.71\t\t\t   $5134.96\t\t\t   $79.07\n" +
            "TOTAL\t\t\t 55.48\t\t\t   $26312.17\t\t\t   $47090.16\t\t\t   $535.53\n" +
            "(Here Total Cost Basis is sum of the commission paid and amount investment" +
            " made in the stock)\n" +
            "Menu\n" +
            "1.Add Portfolio\n" +
            "2.View Portfolio\n" +
            "3.Buy Stock by Volume\n" +
            "4.Buy Stock by Amount\n" +
            "5.View Stock\n" +
            "6.Invest In Existing Portfolio By Strategy \n" +
            "7.Save Portfolio\n" +
            "8.Load Portfolio\n" +
            "9.Use Existing Strategy\n" +
            "10.Exit\n", output.toString());
  }


  /**
   * This method tests one time investment which is custom weighted.
   */
  @Test
  public void testOneTimeInvestmentCustomWeighted() {
    userInput = "1\nport\n3\n1\nAAPL\n2\n12\n11/09/2018\n3\n1\nGOOG\n2\n20\n11/23/2018\n" +
            "6\n1\n3\n2000\n1\n11/23/2018\n1\n25\n75\n5\n1\n11/23/2018\n10\n";
    output = new StringBuffer();
    stockController = new StockController(new StringReader(userInput), output);
    stockController.handleOperation();
    assertEquals("Menu\n" +
            "1.Add Portfolio\n" +
            "2.View Portfolio\n" +
            "3.Buy Stock by Volume\n" +
            "4.Buy Stock by Amount\n" +
            "5.View Stock\n" +
            "6.Invest In Existing Portfolio By Strategy \n" +
            "7.Save Portfolio\n" +
            "8.Load Portfolio\n" +
            "9.Use Existing Strategy\n" +
            "10.Exit\n" +
            "Please enter portfolio name\n" +
            "Menu\n" +
            "1.Add Portfolio\n" +
            "2.View Portfolio\n" +
            "3.Buy Stock by Volume\n" +
            "4.Buy Stock by Amount\n" +
            "5.View Stock\n" +
            "6.Invest In Existing Portfolio By Strategy \n" +
            "7.Save Portfolio\n" +
            "8.Load Portfolio\n" +
            "9.Use Existing Strategy\n" +
            "10.Exit\n" +
            "Select a portfolio\n" +
            "\n" +
            "1.PORT\n" +
            "\n" +
            "Enter ticker symbol\n" +
            "Enter the commission rate\n" +
            "Enter stock volume\n" +
            "Enter the start date in MM/DD/YYYY format\n" +
            "Menu\n" +
            "1.Add Portfolio\n" +
            "2.View Portfolio\n" +
            "3.Buy Stock by Volume\n" +
            "4.Buy Stock by Amount\n" +
            "5.View Stock\n" +
            "6.Invest In Existing Portfolio By Strategy \n" +
            "7.Save Portfolio\n" +
            "8.Load Portfolio\n" +
            "9.Use Existing Strategy\n" +
            "10.Exit\n" +
            "Select a portfolio\n" +
            "\n" +
            "1.PORT\n" +
            "\n" +
            "Enter ticker symbol\n" +
            "Enter the commission rate\n" +
            "Enter stock volume\n" +
            "Enter the start date in MM/DD/YYYY format\n" +
            "Menu\n" +
            "1.Add Portfolio\n" +
            "2.View Portfolio\n" +
            "3.Buy Stock by Volume\n" +
            "4.Buy Stock by Amount\n" +
            "5.View Stock\n" +
            "6.Invest In Existing Portfolio By Strategy \n" +
            "7.Save Portfolio\n" +
            "8.Load Portfolio\n" +
            "9.Use Existing Strategy\n" +
            "10.Exit\n" +
            "Select a portfolio\n" +
            "\n" +
            "1.PORT\n" +
            "\n" +
            "Enter the commission rate\n" +
            "Enter the amount to be invested in the stock\n" +
            "INVESTMENT OPTIONS\n" +
            "1.One Time Investment\n" +
            "2.DCA Strategy\n" +
            "ONE TIME INVESTMENT\n" +
            "Enter the start date in MM/DD/YYYY format\n" +
            "AMOUNT DISTRIBUTION OPTIONS\n" +
            "1.Custom Weighted Investment\n" +
            "2.Equal Weighted Investment\n" +
            "Enter the percentage of investment for each stock in portfolioThe number of " +
            "stocks in portfolio are: 2\n" +
            "GOOG\n" +
            "AAPL\n" +
            "Menu\n" +
            "1.Add Portfolio\n" +
            "2.View Portfolio\n" +
            "3.Buy Stock by Volume\n" +
            "4.Buy Stock by Amount\n" +
            "5.View Stock\n" +
            "6.Invest In Existing Portfolio By Strategy \n" +
            "7.Save Portfolio\n" +
            "8.Load Portfolio\n" +
            "9.Use Existing Strategy\n" +
            "10.Exit\n" +
            "Select a portfolio\n" +
            "\n" +
            "1.PORT\n" +
            "\n" +
            "Enter the start date in MM/DD/YYYY format\n" +
            "Stock Name\t\t\tVolume\t\t\tStock Cost Basis\t\t\tStock Value\t\t\tCommission_Paid\n" +
            "GOOG\t\t\t 20.49   \t\t\t$21402.15\t\t\t   $41455.20\t\t\t   $424.55\n" +
            "AAPL\t\t\t 20.71   \t\t\t$4047.71\t\t\t   $5634.96\t\t\t   $94.07\n" +
            "TOTAL\t\t\t 41.19\t\t\t   $25449.86\t\t\t   $47090.16\t\t\t   $518.62\n" +
            "(Here Total Cost Basis is sum of the commission paid and amount investment " +
            "made in the stock)\n" +
            "Menu\n" +
            "1.Add Portfolio\n" +
            "2.View Portfolio\n" +
            "3.Buy Stock by Volume\n" +
            "4.Buy Stock by Amount\n" +
            "5.View Stock\n" +
            "6.Invest In Existing Portfolio By Strategy \n" +
            "7.Save Portfolio\n" +
            "8.Load Portfolio\n" +
            "9.Use Existing Strategy\n" +
            "10.Exit\n", output.toString());
  }


  /**
   * This method tests dca investment with equal weighted.
   */
  @Test
  public void testDCAInvestmentEqualWeighted() {
    userInput = "1\nport\n3\n1\nAAPL\n2\n12\n01/23/2018\n3\n1\nGOOG\n2\n20\n01/23/2018\n" +
            "6\n1\n3\n2000\n2\n01/23/2018\nn\n11/23/2018\n3\n2\n5\n1\n11/23/2018\n10\n";
    output = new StringBuffer();
    stockController = new StockController(new StringReader(userInput), output);
    stockController.handleOperation();
    assertEquals("Menu\n" +
            "1.Add Portfolio\n" +
            "2.View Portfolio\n" +
            "3.Buy Stock by Volume\n" +
            "4.Buy Stock by Amount\n" +
            "5.View Stock\n" +
            "6.Invest In Existing Portfolio By Strategy \n" +
            "7.Save Portfolio\n" +
            "8.Load Portfolio\n" +
            "9.Use Existing Strategy\n" +
            "10.Exit\n" +
            "Please enter portfolio name\n" +
            "Menu\n" +
            "1.Add Portfolio\n" +
            "2.View Portfolio\n" +
            "3.Buy Stock by Volume\n" +
            "4.Buy Stock by Amount\n" +
            "5.View Stock\n" +
            "6.Invest In Existing Portfolio By Strategy \n" +
            "7.Save Portfolio\n" +
            "8.Load Portfolio\n" +
            "9.Use Existing Strategy\n" +
            "10.Exit\n" +
            "Select a portfolio\n" +
            "\n" +
            "1.PORT\n" +
            "\n" +
            "Enter ticker symbol\n" +
            "Enter the commission rate\n" +
            "Enter stock volume\n" +
            "Enter the start date in MM/DD/YYYY format\n" +
            "Menu\n" +
            "1.Add Portfolio\n" +
            "2.View Portfolio\n" +
            "3.Buy Stock by Volume\n" +
            "4.Buy Stock by Amount\n" +
            "5.View Stock\n" +
            "6.Invest In Existing Portfolio By Strategy \n" +
            "7.Save Portfolio\n" +
            "8.Load Portfolio\n" +
            "9.Use Existing Strategy\n" +
            "10.Exit\n" +
            "Select a portfolio\n" +
            "\n" +
            "1.PORT\n" +
            "\n" +
            "Enter ticker symbol\n" +
            "Enter the commission rate\n" +
            "Enter stock volume\n" +
            "Enter the start date in MM/DD/YYYY format\n" +
            "Menu\n" +
            "1.Add Portfolio\n" +
            "2.View Portfolio\n" +
            "3.Buy Stock by Volume\n" +
            "4.Buy Stock by Amount\n" +
            "5.View Stock\n" +
            "6.Invest In Existing Portfolio By Strategy \n" +
            "7.Save Portfolio\n" +
            "8.Load Portfolio\n" +
            "9.Use Existing Strategy\n" +
            "10.Exit\n" +
            "Select a portfolio\n" +
            "\n" +
            "1.PORT\n" +
            "\n" +
            "Enter the commission rate\n" +
            "Enter the amount to be invested in the stock\n" +
            "INVESTMENT OPTIONS\n" +
            "1.One Time Investment\n" +
            "2.DCA Strategy\n" +
            "DOLLAR COST AVERAGE STRATEGY\n" +
            "Enter the start date in MM/DD/YYYY format\n" +
            "Press s if you want to skip end date or enter any key to enter end date\n" +
            "Enter the end date in MM/DD/YYYY format\n" +
            "FREQUENCY \n" +
            "1.Yearly\n" +
            "2.Quaterly\n" +
            "3.Monthly\n" +
            "4.Custom Days\n" +
            "AMOUNT DISTRIBUTION OPTIONS\n" +
            "1.Custom Weighted Investment\n" +
            "2.Equal Weighted Investment\n" +
            "Equal WeightMenu\n" +
            "1.Add Portfolio\n" +
            "2.View Portfolio\n" +
            "3.Buy Stock by Volume\n" +
            "4.Buy Stock by Amount\n" +
            "5.View Stock\n" +
            "6.Invest In Existing Portfolio By Strategy \n" +
            "7.Save Portfolio\n" +
            "8.Load Portfolio\n" +
            "9.Use Existing Strategy\n" +
            "10.Exit\n" +
            "Select a portfolio\n" +
            "\n" +
            "1.PORT\n" +
            "\n" +
            "Enter the start date in MM/DD/YYYY format\n" +
            "Stock Name\t\t\tVolume\t\t\tStock Cost Basis\t\t\tStock Value\t\t\tCommission_Paid\n" +
            "GOOG\t\t\t 29.90   \t\t\t$35197.39\t\t\t   $306478.11\t\t\t   $797.99\n" +
            "AAPL\t\t\t 70.88   \t\t\t$13496.97\t\t\t   $87522.73\t\t\t   $372.49\n" +
            "TOTAL\t\t\t 100.78\t\t\t   $48694.36\t\t\t   $394000.84\t\t\t   $1170.48\n" +
            "(Here Total Cost Basis is sum of the commission paid and amount investment " +
            "made in the stock)\n" +
            "Menu\n" +
            "1.Add Portfolio\n" +
            "2.View Portfolio\n" +
            "3.Buy Stock by Volume\n" +
            "4.Buy Stock by Amount\n" +
            "5.View Stock\n" +
            "6.Invest In Existing Portfolio By Strategy \n" +
            "7.Save Portfolio\n" +
            "8.Load Portfolio\n" +
            "9.Use Existing Strategy\n" +
            "10.Exit\n", output.toString());
  }

  /**
   * This method tests multiple dca investment with equal weighted.
   */
  @Test
  public void testMultipleDCAInvestmentEqualWeighted() {
    userInput = "1\nport\n3\n1\nAAPL\n2\n12\n01/23/2018\n3\n1\nGOOG\n2\n20\n01/23/2018\n" +
            "6\n1\n3\n2000\n2\n01/23/2018\nn\n06/23/2018\n3\n2\n5\n1\n11/23/2018\n" +
            "6\n1\n4\n5000\n2\n07/23/2018\nn\n11/23/2018\n3\n2\n5\n1\n11/23/2018\n10\n";
    output = new StringBuffer();
    stockController = new StockController(new StringReader(userInput), output);
    stockController.handleOperation();
    assertEquals("Menu\n" +
            "1.Add Portfolio\n" +
            "2.View Portfolio\n" +
            "3.Buy Stock by Volume\n" +
            "4.Buy Stock by Amount\n" +
            "5.View Stock\n" +
            "6.Invest In Existing Portfolio By Strategy \n" +
            "7.Save Portfolio\n" +
            "8.Load Portfolio\n" +
            "9.Use Existing Strategy\n" +
            "10.Exit\n" +
            "Please enter portfolio name\n" +
            "Menu\n" +
            "1.Add Portfolio\n" +
            "2.View Portfolio\n" +
            "3.Buy Stock by Volume\n" +
            "4.Buy Stock by Amount\n" +
            "5.View Stock\n" +
            "6.Invest In Existing Portfolio By Strategy \n" +
            "7.Save Portfolio\n" +
            "8.Load Portfolio\n" +
            "9.Use Existing Strategy\n" +
            "10.Exit\n" +
            "Select a portfolio\n" +
            "\n" +
            "1.PORT\n" +
            "\n" +
            "Enter ticker symbol\n" +
            "Enter the commission rate\n" +
            "Enter stock volume\n" +
            "Enter the start date in MM/DD/YYYY format\n" +
            "Menu\n" +
            "1.Add Portfolio\n" +
            "2.View Portfolio\n" +
            "3.Buy Stock by Volume\n" +
            "4.Buy Stock by Amount\n" +
            "5.View Stock\n" +
            "6.Invest In Existing Portfolio By Strategy \n" +
            "7.Save Portfolio\n" +
            "8.Load Portfolio\n" +
            "9.Use Existing Strategy\n" +
            "10.Exit\n" +
            "Select a portfolio\n" +
            "\n" +
            "1.PORT\n" +
            "\n" +
            "Enter ticker symbol\n" +
            "Enter the commission rate\n" +
            "Enter stock volume\n" +
            "Enter the start date in MM/DD/YYYY format\n" +
            "Menu\n" +
            "1.Add Portfolio\n" +
            "2.View Portfolio\n" +
            "3.Buy Stock by Volume\n" +
            "4.Buy Stock by Amount\n" +
            "5.View Stock\n" +
            "6.Invest In Existing Portfolio By Strategy \n" +
            "7.Save Portfolio\n" +
            "8.Load Portfolio\n" +
            "9.Use Existing Strategy\n" +
            "10.Exit\n" +
            "Select a portfolio\n" +
            "\n" +
            "1.PORT\n" +
            "\n" +
            "Enter the commission rate\n" +
            "Enter the amount to be invested in the stock\n" +
            "INVESTMENT OPTIONS\n" +
            "1.One Time Investment\n" +
            "2.DCA Strategy\n" +
            "DOLLAR COST AVERAGE STRATEGY\n" +
            "Enter the start date in MM/DD/YYYY format\n" +
            "Press s if you want to skip end date or enter any key to enter end date\n" +
            "Enter the end date in MM/DD/YYYY format\n" +
            "FREQUENCY \n" +
            "1.Yearly\n" +
            "2.Quaterly\n" +
            "3.Monthly\n" +
            "4.Custom Days\n" +
            "AMOUNT DISTRIBUTION OPTIONS\n" +
            "1.Custom Weighted Investment\n" +
            "2.Equal Weighted Investment\n" +
            "Equal WeightMenu\n" +
            "1.Add Portfolio\n" +
            "2.View Portfolio\n" +
            "3.Buy Stock by Volume\n" +
            "4.Buy Stock by Amount\n" +
            "5.View Stock\n" +
            "6.Invest In Existing Portfolio By Strategy \n" +
            "7.Save Portfolio\n" +
            "8.Load Portfolio\n" +
            "9.Use Existing Strategy\n" +
            "10.Exit\n" +
            "Select a portfolio\n" +
            "\n" +
            "1.PORT\n" +
            "\n" +
            "Enter the start date in MM/DD/YYYY format\n" +
            "Stock Name\t\t\tVolume\t\t\tStock Cost Basis\t\t\tStock Value\t\t\tCommission_Paid\n" +
            "GOOG\t\t\t 24.62   \t\t\t$29017.39\t\t\t   $136815.77\t\t\t   $617.99\n" +
            "AAPL\t\t\t 40.65   \t\t\t$7316.97\t\t\t   $27276.73\t\t\t   $192.49\n" +
            "TOTAL\t\t\t 65.26\t\t\t   $36334.36\t\t\t   $164092.50\t\t\t   $810.48\n" +
            "(Here Total Cost Basis is sum of the commission paid and amount investment " +
            "made in the stock)\n" +
            "Menu\n" +
            "1.Add Portfolio\n" +
            "2.View Portfolio\n" +
            "3.Buy Stock by Volume\n" +
            "4.Buy Stock by Amount\n" +
            "5.View Stock\n" +
            "6.Invest In Existing Portfolio By Strategy \n" +
            "7.Save Portfolio\n" +
            "8.Load Portfolio\n" +
            "9.Use Existing Strategy\n" +
            "10.Exit\n" +
            "Select a portfolio\n" +
            "\n" +
            "1.PORT\n" +
            "\n" +
            "Enter the commission rate\n" +
            "Enter the amount to be invested in the stock\n" +
            "INVESTMENT OPTIONS\n" +
            "1.One Time Investment\n" +
            "2.DCA Strategy\n" +
            "DOLLAR COST AVERAGE STRATEGY\n" +
            "Enter the start date in MM/DD/YYYY format\n" +
            "Press s if you want to skip end date or enter any key to enter end date\n" +
            "Enter the end date in MM/DD/YYYY format\n" +
            "FREQUENCY \n" +
            "1.Yearly\n" +
            "2.Quaterly\n" +
            "3.Monthly\n" +
            "4.Custom Days\n" +
            "AMOUNT DISTRIBUTION OPTIONS\n" +
            "1.Custom Weighted Investment\n" +
            "2.Equal Weighted Investment\n" +
            "Equal WeightMenu\n" +
            "1.Add Portfolio\n" +
            "2.View Portfolio\n" +
            "3.Buy Stock by Volume\n" +
            "4.Buy Stock by Amount\n" +
            "5.View Stock\n" +
            "6.Invest In Existing Portfolio By Strategy \n" +
            "7.Save Portfolio\n" +
            "8.Load Portfolio\n" +
            "9.Use Existing Strategy\n" +
            "10.Exit\n" +
            "Select a portfolio\n" +
            "\n" +
            "1.PORT\n" +
            "\n" +
            "Enter the start date in MM/DD/YYYY format\n" +
            "Stock Name\t\t\tVolume\t\t\tStock Cost Basis\t\t\tStock Value\t\t\tCommission_Paid\n" +
            "GOOG\t\t\t 35.58   \t\t\t$42017.39\t\t\t   $295637.49\t\t\t   $1117.99\n" +
            "AAPL\t\t\t 102.28   \t\t\t$20316.97\t\t\t   $93820.64\t\t\t   $692.49\n" +
            "TOTAL\t\t\t 137.86\t\t\t   $62334.36\t\t\t   $389458.13\t\t\t   $1810.48\n" +
            "(Here Total Cost Basis is sum of the commission paid and amount investment" +
            " made in the stock)\n" +
            "Menu\n" +
            "1.Add Portfolio\n" +
            "2.View Portfolio\n" +
            "3.Buy Stock by Volume\n" +
            "4.Buy Stock by Amount\n" +
            "5.View Stock\n" +
            "6.Invest In Existing Portfolio By Strategy \n" +
            "7.Save Portfolio\n" +
            "8.Load Portfolio\n" +
            "9.Use Existing Strategy\n" +
            "10.Exit\n", output.toString());
  }

  /**
   * This method tests dca investment with custom weighted.
   */
  @Test
  public void testDCAInvestmentCustomWeighted() {
    userInput = "1\nport\n3\n1\nAAPL\n2\n12\n01/23/2018\n3\n1\nGOOG\n2\n20\n01/23/2018\n" +
            "6\n1\n3\n2000\n2\n01/23/2018\nn\n11/23/2018\n3\n1\n25\n75\n5\n1\n11/23/2018\n10\n";
    output = new StringBuffer();
    stockController = new StockController(new StringReader(userInput), output);
    stockController.handleOperation();
    assertEquals("Menu\n" +
            "1.Add Portfolio\n" +
            "2.View Portfolio\n" +
            "3.Buy Stock by Volume\n" +
            "4.Buy Stock by Amount\n" +
            "5.View Stock\n" +
            "6.Invest In Existing Portfolio By Strategy \n" +
            "7.Save Portfolio\n" +
            "8.Load Portfolio\n" +
            "9.Use Existing Strategy\n" +
            "10.Exit\n" +
            "Please enter portfolio name\n" +
            "Menu\n" +
            "1.Add Portfolio\n" +
            "2.View Portfolio\n" +
            "3.Buy Stock by Volume\n" +
            "4.Buy Stock by Amount\n" +
            "5.View Stock\n" +
            "6.Invest In Existing Portfolio By Strategy \n" +
            "7.Save Portfolio\n" +
            "8.Load Portfolio\n" +
            "9.Use Existing Strategy\n" +
            "10.Exit\n" +
            "Select a portfolio\n" +
            "\n" +
            "1.PORT\n" +
            "\n" +
            "Enter ticker symbol\n" +
            "Enter the commission rate\n" +
            "Enter stock volume\n" +
            "Enter the start date in MM/DD/YYYY format\n" +
            "Menu\n" +
            "1.Add Portfolio\n" +
            "2.View Portfolio\n" +
            "3.Buy Stock by Volume\n" +
            "4.Buy Stock by Amount\n" +
            "5.View Stock\n" +
            "6.Invest In Existing Portfolio By Strategy \n" +
            "7.Save Portfolio\n" +
            "8.Load Portfolio\n" +
            "9.Use Existing Strategy\n" +
            "10.Exit\n" +
            "Select a portfolio\n" +
            "\n" +
            "1.PORT\n" +
            "\n" +
            "Enter ticker symbol\n" +
            "Enter the commission rate\n" +
            "Enter stock volume\n" +
            "Enter the start date in MM/DD/YYYY format\n" +
            "Menu\n" +
            "1.Add Portfolio\n" +
            "2.View Portfolio\n" +
            "3.Buy Stock by Volume\n" +
            "4.Buy Stock by Amount\n" +
            "5.View Stock\n" +
            "6.Invest In Existing Portfolio By Strategy \n" +
            "7.Save Portfolio\n" +
            "8.Load Portfolio\n" +
            "9.Use Existing Strategy\n" +
            "10.Exit\n" +
            "Select a portfolio\n" +
            "\n" +
            "1.PORT\n" +
            "\n" +
            "Enter the commission rate\n" +
            "Enter the amount to be invested in the stock\n" +
            "INVESTMENT OPTIONS\n" +
            "1.One Time Investment\n" +
            "2.DCA Strategy\n" +
            "DOLLAR COST AVERAGE STRATEGY\n" +
            "Enter the start date in MM/DD/YYYY format\n" +
            "Press s if you want to skip end date or enter any key to enter end date\n" +
            "Enter the end date in MM/DD/YYYY format\n" +
            "FREQUENCY \n" +
            "1.Yearly\n" +
            "2.Quaterly\n" +
            "3.Monthly\n" +
            "4.Custom Days\n" +
            "AMOUNT DISTRIBUTION OPTIONS\n" +
            "1.Custom Weighted Investment\n" +
            "2.Equal Weighted Investment\n" +
            "Custom WeightThe number of stocks in portfolio are: 2\n" +
            "GOOG\n" +
            "AAPL\n" +
            "Menu\n" +
            "1.Add Portfolio\n" +
            "2.View Portfolio\n" +
            "3.Buy Stock by Volume\n" +
            "4.Buy Stock by Amount\n" +
            "5.View Stock\n" +
            "6.Invest In Existing Portfolio By Strategy \n" +
            "7.Save Portfolio\n" +
            "8.Load Portfolio\n" +
            "9.Use Existing Strategy\n" +
            "10.Exit\n" +
            "Select a portfolio\n" +
            "\n" +
            "1.PORT\n" +
            "\n" +
            "Enter the start date in MM/DD/YYYY format\n" +
            "Stock Name\t\t\tVolume\t\t\tStock Cost Basis\t\t\tStock Value\t\t\tCommission_Paid\n" +
            "GOOG\t\t\t 24.95   \t\t\t$29532.39\t\t\t   $276104.66\t\t\t   $632.99\n" +
            "AAPL\t\t\t 100.32   \t\t\t$19161.97\t\t\t   $118879.21\t\t\t   $537.49\n" +
            "TOTAL\t\t\t 125.27\t\t\t   $48694.36\t\t\t   $394983.87\t\t\t   $1170.48\n" +
            "(Here Total Cost Basis is sum of the commission paid and amount investment " +
            "made in the stock)\n" +
            "Menu\n" +
            "1.Add Portfolio\n" +
            "2.View Portfolio\n" +
            "3.Buy Stock by Volume\n" +
            "4.Buy Stock by Amount\n" +
            "5.View Stock\n" +
            "6.Invest In Existing Portfolio By Strategy \n" +
            "7.Save Portfolio\n" +
            "8.Load Portfolio\n" +
            "9.Use Existing Strategy\n" +
            "10.Exit\n", output.toString());
  }
}
