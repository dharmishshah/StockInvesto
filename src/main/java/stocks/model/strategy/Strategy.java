package stocks.model.strategy;

import java.time.LocalDate;
import java.util.List;

import stocks.model.stock.Stock;

/**
 * The following class represents the a Strategy. A strategy consists of the start date, end date of
 * the the strategy. The id of the portfolio on which strategy is applied. The amount invested in
 * the portfolio. The commission rate charged for the transaction.
 */
public class Strategy {

  //The following variable represents the start date of a strategy.
  private LocalDate startDate;
  //The following variable represents the end date of a strategy.
  private LocalDate endDate;
  //The following variable represents the portfolio on which the strategy is applied.
  private int portfolioId;
  //The following variable represents the amount to be invested in the portfolio.
  private double amountInvestedInPortfolio;
  //The following variable represents the commission charged during strategy.
  private double commissionRate;
  //The following variable represents the frequency of the strategy.
  private int investmentFrequency;
  //The following variable represents the stocks in the portfolio.
  private List<Stock> stocksInPortfolio;
  //The following variable represents the weightage given to each stock
  private List<Double> weightedInvestment;

  /**
   * This constructor constructs a strategy object with investment details.
   *
   * @param start                     start date from when stocks need to be bought
   * @param end                       end date till when stock
   * @param portfolioId               portfolio in which stocks need to be invested
   * @param amountInvestedInPortfolio amount to be invested
   * @param commissionRate            commission to buy a stock
   * @param stocksInPortfolio         stocks in existing portfolios
   * @param weightedInvestment        weights for each stock
   * @param investmentFrequency       frequency of days in which stocks needs to be invested
   */
  public Strategy(LocalDate start, LocalDate end, int portfolioId,
                  Double amountInvestedInPortfolio, Double commissionRate,
                  List<Stock> stocksInPortfolio, List<Double> weightedInvestment,
                  int investmentFrequency) {
    this.startDate = start;
    this.endDate = end;
    this.portfolioId = portfolioId;
    this.amountInvestedInPortfolio = amountInvestedInPortfolio;
    this.commissionRate = commissionRate;
    this.stocksInPortfolio = stocksInPortfolio;
    this.weightedInvestment = weightedInvestment;
    this.investmentFrequency = investmentFrequency;
  }

  public int getInvestmentFrequency() {
    return investmentFrequency;
  }

  public LocalDate getStartDate() {
    return startDate;
  }

  public LocalDate getEndDate() {
    return endDate;
  }

  public int getPortfolio() {
    return portfolioId;
  }

  public double getAmountInvestedInPortfolio() {
    return amountInvestedInPortfolio;
  }

  public double getCommissionRate() {
    return commissionRate;
  }

  public int getPortfolioId() {
    return portfolioId;
  }

  public List<Double> getWeightedInvestment() {
    return weightedInvestment;
  }

  public List<Stock> getStocksInPortfolio() {
    return stocksInPortfolio;
  }
}
