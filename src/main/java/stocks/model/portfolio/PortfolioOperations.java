package stocks.model.portfolio;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import org.jfree.data.category.DefaultCategoryDataset;

import stocks.model.stock.Stock;

/**
 * The following interface represents the operations performed by the portfolio. The operations
 * performed by the portfolio are : 1) add a new portfolio. 2) stocks.view a  portfolio. 3) add a
 * new stock. 4) stocks.view all stocks in a portfolio.
 */
public interface PortfolioOperations<Portfolio> {
  /**
   * The following method adds a new portfolio.
   *
   * @param portfolioName the name of the new portfolio.
   */
  void addPortfolio(String portfolioName);

  /**
   * The following method displays all portfolio of a user by displaying the total investment and
   * total valuation of the each portfolio.
   *
   * @return all portfolios of the user.
   */
  Map<Integer, Portfolio> viewPortfolios();

  /**
   * The following adds the new stock in the portfolio.
   *
   * @param portfolioId    the id of the portfolio in which the stock is to be added.
   * @param tickerSymbol   the ticker symbol of the stock.
   * @param volume         the quantity of stock to be bought.
   * @param date           the date at which the stock is bought.
   * @param commissionRate the commission rate for the buying stock.
   */
  void addStock(int portfolioId, String tickerSymbol, double volume, LocalDate date,
                double commissionRate);

  /**
   * The following method displays the stocks in the portfolio.
   *
   * @param portfolioId the id of the portfolio.
   * @return the stocks in the portfolio.
   */
  Map<String, Stock> viewStocks(int portfolioId);

  /**
   * the following method displays the stock for a portfolio.
   *
   * @param portfolioId view stocks for a particular portfolio
   * @param date        the date at which the stocks is to be displayed.
   * @return outputted string to be displayed
   */
  public Map<String, Map<String, Double>> displayStocks(int portfolioId, LocalDate date);

  /**
   * The following method displays the portfolio for the user.
   *
   * @param date the date at which portfolio is to be displayed.
   * @return outputted string to be displayed
   */
  public Map<String, Map<String, Double>> displayPortfolios(LocalDate date);

  /**
   * The following method buys a stock based on the amount invested by the user.
   *
   * @param portfolioId    the id of the portfolio.
   * @param tickerSymbol   the ticker symbol of the stock.
   * @param amountInvested the amount invested by the user.
   * @param date           the date at which stock is bought.
   * @param commissionRate the commission rate of the
   */
  public void addStockByAmount(int portfolioId, String tickerSymbol, double amountInvested,
                               LocalDate date, double commissionRate);

  /**
   * The following method returns the stocks in the portfolio for the given date.
   *
   * @param portfolioId the id of the portfolio.
   * @param startDate   the date at which stocks are demanded.
   * @return list of stocks for a portfolio
   */
  public List<Stock> viewPortfolioStocks(int portfolioId, LocalDate startDate);



  /**
   * The following method saves all portfolios on a file.
   *
   */
  void savePortfolios(boolean isSaveAll,String portfolioName,int portfolioId);


  /**
   * The following method loads all portfolios from a file.
   *
   */
  void loadPortfolios(boolean isLoadAll,String portfolioName);
  
  
  DefaultCategoryDataset getGraphDataset(LocalDate sdate,
          LocalDate edate, int portId, int frequency);
  
  
  
}
