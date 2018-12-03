package stocks.model.portfolio;

import com.fasterxml.jackson.databind.ObjectMapper;


import sun.misc.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import stocks.model.StockConstants;
import stocks.model.stock.Stock;
import stocks.model.stock.StockModel;
import stocks.model.stock.StockOperations;
import stocks.model.utils.LocalDateDeserializer;
import stocks.model.utils.LocalDateSerializer;

/**
 * The following class represents the portfolio model which implements the all operations performed
 * by the portfolio.
 */
public class PortfolioModel implements PortfolioOperations<Portfolio> {
  private static DecimalFormat df2 = new DecimalFormat("#.00");
  //The variable represents the list of portfolio.
  private Map<Integer, Portfolio> portfolios;
  //The variable represents the stock operations.
  private StockOperations<Stock> stockOperations;
  //The variable represents the maximum number of portfolio.
  private Integer maxPortfolio;

  /**
   * The following constructor initializes the portfolio model.
   *
   * @param stockData represents the data of the stock.
   */
  public PortfolioModel(Map<String, Map<String, Map<String, Double>>> stockData) {
    if (stockData == null) {
      throw new IllegalArgumentException("stock data is blank");
    }
    this.portfolios = new HashMap<Integer, Portfolio>();
    stockOperations = new StockModel(stockData);
    this.maxPortfolio = 1;
  }

  /**
   * This method creates and adds a new portfolio for the use with zero number of stocks in this
   * current portfolio.
   *
   * @param portfolioName portfolio name
   */
  @Override
  public void addPortfolio(String portfolioName) {

    validatePortfolio(portfolioName);

    Portfolio portfolio = new Portfolio(portfolioName.toUpperCase());
    portfolios.put(maxPortfolio, portfolio);
    maxPortfolio++;

  }

  /**
   * This method gets all the user portfolios created till now by user.
   *
   * @return list of user portfolios
   */
  @Override
  public Map<Integer, Portfolio> viewPortfolios() {
    Map<Integer, Portfolio> newPortfolios = new HashMap<Integer, Portfolio>(portfolios);
    return newPortfolios;
  }

  @Override
  public void savePortfolios() {
    ObjectMapper mapper = new ObjectMapper();

    try {
      String current = new java.io.File( "." ).getCanonicalPath();
      File dir = new File(current + "/stockData/savedPortfolios/");

      if(!dir.exists()){
        dir.mkdir();
      }

      //mapper.writeValue(new File(dir + "/allPortfolios.txt"), portfolios);

      for (Map.Entry<Integer,Portfolio> entry:portfolios.entrySet()){
        Portfolio portfolio = entry.getValue();
        File portfolioFile = new File(dir + "/" + portfolio.getName() + ".txt");
        //Object to JSON in file
        mapper.writeValue( portfolioFile, portfolio);
      }


    } catch (IOException i) {
      throw new IllegalArgumentException(StockConstants.ERROR_INVALID_FILE);
    }
  }

  @Override
  public void loadPortfolios() {
    ObjectMapper mapper = new ObjectMapper();

    try {
      portfolios.clear();
      String current = new java.io.File( "." ).getCanonicalPath();
      File dir = new File(current + "/stockData/savedPortfolios/");

      if(!dir.exists()){
        dir.mkdir();
      }
      File[] files = dir.listFiles();
      int count = 1;

      //portfolios = mapper.readValue(dir + "allPortfolios.txt", Map.class);

      for(File file:files){
        //Object to JSON in file
        Portfolio portfolio = mapper.readValue(file, Portfolio.class);
        portfolios.put(count,portfolio);
        count++;
      }



    } catch (IOException i) {
      i.printStackTrace();
      throw new IllegalArgumentException(StockConstants.ERROR_INVALID_FILE);
    }
  }

  /**
   * This method adds a new stock in user specified portfolio.User specifies a stock's ticker
   * symbol,its volume and date on which he wants buy the stock.
   *
   * @param portfolioId  portfolio name
   * @param tickerSymbol stock ticker symbol
   * @param volume       number of stocks
   * @param date         on which user wants to buy a stock
   */
  @Override
  public void addStock(int portfolioId, String tickerSymbol, double volume, LocalDate date,
                       double commissionRate) {
    validatePortfolioId(portfolioId);
    validateAddStockDate(tickerSymbol, volume, date);
    for (Map.Entry<Integer, Portfolio> entry : portfolios.entrySet()) {
      if (entry.getKey() == portfolioId) {
        Map<String, Stock> stocks = entry.getValue().getStocks();
        if (stocks.get(tickerSymbol) != null) {
          Stock stock = stocks.get(tickerSymbol);
          stock = stockOperations.updateExistingStock(stock, volume, date, commissionRate);
          stocks.put(tickerSymbol, stock);
        } else {
          Stock stock = stockOperations.addNewStock(tickerSymbol, volume, date, commissionRate);
          stocks.put(tickerSymbol, stock);
        }

      }
    }
  }

  /**
   * The following method buys a stock based on the amount invested by the user.
   *
   * @param portfolioId    the id of the portfolio.
   * @param tickerSymbol   the ticker symbol of the stock.
   * @param amountInvested the amount invested by the user.
   * @param date           the date at which stock is bought.
   * @param commissionRate the commission rate of the
   */
  @Override
  public void addStockByAmount(int portfolioId, String tickerSymbol,
                               double amountInvested, LocalDate date, double commissionRate) {
    validatePortfolioId(portfolioId);
    validateAddStockDate(tickerSymbol, amountInvested, date);
    for (Map.Entry<Integer, Portfolio> entry : portfolios.entrySet()) {
      if (entry.getKey() == portfolioId) {
        Map<String, Stock> stocks = entry.getValue().getStocks();
        if (stocks.get(tickerSymbol) != null) {
          Stock stock = stocks.get(tickerSymbol);
          double stockCostPrice = stockOperations.getCostPriceStock(stock.getTickerSymbol(),
                  date, true);
          double volumeToBeBought = amountInvested / stockCostPrice;
          stock =
                  stockOperations.updateExistingStock(stock, volumeToBeBought, date,
                          commissionRate);
          stocks.put(tickerSymbol, stock);
        } else {
          double stockCostPrice = stockOperations.getCostPriceStock(tickerSymbol, date,
                  true);
          double volumeToBeBought = amountInvested / stockCostPrice;
          Stock stock = stockOperations.addNewStock(tickerSymbol, volumeToBeBought,
                  date, commissionRate);
          stocks.put(tickerSymbol, stock);
        }
      }
    }
  }

  /**
   * The following method returns the stocks in the portfolio for the given date.
   *
   * @param portfolioId the id of the portfolio.
   * @param startDate   the date at which stocks are demanded.
   */
  @Override
  public List<Stock> viewPortfolioStocks(int portfolioId, LocalDate startDate) {
    List<Stock> listOfStocks = new ArrayList<>();
    Map<String, Stock> stocks = viewStocks(portfolioId);
    //Retrieving all the stocks in a portfolio.
    Set<String> keys = stocks.keySet();
    for (String key : keys) {
      Stock stock = stocks.get(key);

      if ( stock.getDate().compareTo(startDate) <= 0) {
        listOfStocks.add(stocks.get(key));
      }
    }
    return listOfStocks;
  }

  /**
   * This method gets all stocks with their cost basis and stock values based on user specified
   * date.
   *
   * @param portfolioId portfolio name
   * @return list of stocks in a particular portfolios
   */
  @Override
  public Map<String, Stock> viewStocks(int portfolioId) {

    validatePortfolioId(portfolioId);

    for (Map.Entry<Integer, Portfolio> entry : portfolios.entrySet()) {
      if (entry.getKey() == portfolioId) {
        Map<String, Stock> newStocks = new HashMap<String, Stock>(entry.getValue().getStocks());
        return newStocks;
      }
    }

    return null;

  }

  /**
   * the following method displays the stock for a portfolio.
   *
   * @param portfolioId view stocks for a particular portfolio
   * @param date        the date at which the stocks is to be displayed.
   * @return outputted string to be displayed
   */
  @Override
  public Map<String, Map<String, Double>> displayStocks(int portfolioId, LocalDate date) {
    Map<String, Stock> stocks = viewStocks(portfolioId);
    Map<String, Map<String, Double>> resultMap = new HashMap<String, Map<String, Double>>();

    double totalCostBasis = 0.0;
    double totalValue = 0.0;
    double volume = 0;
    double totalCommission = 0.0;
    for (Map.Entry<String, Stock> stock : stocks.entrySet()) {

      Stock s = stock.getValue();
      Map<String, Double> resultValues = new HashMap<String, Double>();
      double currentCostBasis = stockOperations.getTotalCostBasisOfStock(date, s);
      resultValues.put("costBasis", currentCostBasis);
      double currentTotalValue = stockOperations.getCurrentValuationOfStock(date, s);
      resultValues.put("totalValue", currentTotalValue);
      double commissionPaid = stockOperations.getTotalCommissionPaid(date, s);
      resultValues.put("commission", commissionPaid);
      volume = s.getVolume();
      resultValues.put("volume", volume);
      resultMap.put(s.getTickerSymbol(), resultValues);

      totalCostBasis = totalCostBasis + currentCostBasis;
      totalValue = totalValue + currentTotalValue;
      totalCommission = totalCommission + commissionPaid;
    }
    return resultMap;

  }

  /**
   * The following method displays the portfolio for the user.
   *
   * @param date the date at which portfolio is to be displayed.
   * @return outputted string to be displayed
   */
  @Override
  public Map<String, Map<String, Double>> displayPortfolios(LocalDate date) {

    Map<Integer, Portfolio> portfolios = viewPortfolios();
    Map<String, Map<String, Double>> resultMap = new HashMap<String, Map<String, Double>>();
    if (portfolios == null || portfolios.isEmpty()) {
      return null;
    }
    for (Map.Entry<Integer, Portfolio> entry : portfolios.entrySet()) {

      Portfolio portfolio = entry.getValue();
      Map<String, Stock> stocks = portfolio.getStocks();
      double totalCostBasis = 0.0;
      double totalValue = 0.0;
      double totalCommissionPaid = 0.0;
      double volume = 0;
      Map<String, Double> resultValues = new HashMap<String, Double>();
      for (Map.Entry<String, Stock> stock : stocks.entrySet()) {
        Stock s = stock.getValue();
        double currentCostBasis = stockOperations.getTotalCostBasisOfStock(date, s);
        double currentTotalValue = stockOperations.getCurrentValuationOfStock(date, s);
        double commissionPaid = stockOperations.getTotalCommissionPaid(date, s);
        volume = volume + s.getVolume();
        totalCostBasis = totalCostBasis + currentCostBasis;
        totalValue = totalValue + currentTotalValue;
        totalCommissionPaid = totalCommissionPaid + commissionPaid;
      }

      resultValues.put("costBasis", totalCostBasis);
      resultValues.put("totalValue", totalValue);
      resultValues.put("commission", totalCommissionPaid);
      resultValues.put("volume", volume);

      resultMap.put(portfolio.getName(), resultValues);
    }

    return resultMap;

  }

  /**
   * The following method returns the portfolio in string format.
   */
  @Override
  public String toString() {
    if (portfolios == null || portfolios.isEmpty()) {
      return "";
    }
    String result = "";
    for (Map.Entry<Integer, Portfolio> entry : portfolios.entrySet()) {
      result = "\n" + result + entry.getKey() + "." + entry.getValue().getName() + "\n";
    }
    return result;
  }

  /**
   * The following method checks whether a portfolio already exists or not. It throws illegal
   * argument exception if the portfolio already exists and we try to add same portfolio.
   *
   * @param portfolioName the name of the new portfolio to be added.
   */
  private void validatePortfolio(String portfolioName) {
    if (portfolioName == null || portfolioName.isEmpty()) {
      throw new IllegalArgumentException(StockConstants.ERROR_INVALID_PORTFOLIO_NAME);
    }

    for (Map.Entry<Integer, Portfolio> entry : portfolios.entrySet()) {
      Portfolio portfolio = entry.getValue();
      if (portfolio.getName().equals(portfolioName.toUpperCase())) {
        throw new IllegalArgumentException(StockConstants.ERROR_INVALID_PORTFOLIO_NAME);
      }
    }
  }

  /**
   * The following method checks whether the data given by user is valid or not.
   *
   * @param tickerSymbol the ticker symbol of the stock.
   * @param date         the date at which stock is bought.
   * @param volume       the quantity of stock bought.
   */
  private void validateAddStockDate(String tickerSymbol, double volume, LocalDate date) {
    if (tickerSymbol == null || tickerSymbol.isEmpty()) {
      throw new IllegalArgumentException(StockConstants.ERROR_INVALID_TICKER_SYMBOL);
    }

    if (volume <= 0) {
      throw new IllegalArgumentException(StockConstants.ERROR_INVALID_VOLUME);
    }

    if (date == null) {
      throw new IllegalArgumentException(StockConstants.ERROR_INVALID_DATE);
    }

    if (date.compareTo(LocalDate.now()) > 0) {
      throw new IllegalArgumentException(StockConstants.ERROR_DATE_EXCEEDS);
    }
  }

  /**
   * The following method validates the portfolioId given by the user.
   *
   * @param portfolioId the unique Id of the portfolio.
   */
  private void validatePortfolioId(int portfolioId) {
    if (portfolioId <= 0) {
      throw new IllegalArgumentException(StockConstants.ERROR_INVALID_PORTFOLIO_ID);
    }
    if (!this.toString().contains(portfolioId + ".")) {
      throw new IllegalArgumentException(StockConstants.ERROR_INVALID_PORTFOLIO_ID);
    }
  }

}
