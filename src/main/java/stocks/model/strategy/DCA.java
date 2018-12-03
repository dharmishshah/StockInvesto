package stocks.model.strategy;

import java.time.LocalDate;

import stocks.model.portfolio.PortfolioOperations;

/**
 * This class implements dollar cost averaging strategy for a amount from a time period in a
 * particular portfolio.
 */
public class DCA implements StrategyOperations {

  PortfolioOperations portfolioOperations;

  /**
   * This constructor creates a DCA object with existing portfolios.
   *
   * @param portfolioOperations portfolios operations to be performed using existing portfolios.
   */
  public DCA(PortfolioOperations portfolioOperations) {
    if (portfolioOperations == null) {
      throw new IllegalArgumentException("Portfolio operation is blank");
    }
    this.portfolioOperations = portfolioOperations;
  }

  /**
   * The following method executes a new strategy based on user data.
   *
   * @param strategy strategy object given by user the start date of the strategy.
   */
  @Override
  public void executeStrategy(Strategy strategy) {


    LocalDate currentDate = strategy.getStartDate();

    while (currentDate.compareTo(strategy.getEndDate()) < 0
            || currentDate.compareTo(strategy.getEndDate()) == 0) {
      for (int i = 0; i < strategy.getWeightedInvestment().size(); i++) {
        double amountToBeInvestedInStock = (strategy.getWeightedInvestment().get(i) / 100)
                * strategy.getAmountInvestedInPortfolio();
        String stockTickerSymbol = strategy.getStocksInPortfolio().get(i).getTickerSymbol();
        while (true) {
          try {
            portfolioOperations.addStockByAmount(strategy.getPortfolioId(), stockTickerSymbol,
                    amountToBeInvestedInStock, currentDate, strategy.getCommissionRate());
            break;
          } catch (IllegalArgumentException iae) {
            currentDate = currentDate.plusDays(1);
            if (currentDate.compareTo(strategy.getEndDate()) > 0) {
              break;
            }
            continue;
          }
        }
      }
      currentDate = currentDate.plusDays(strategy.getInvestmentFrequency());
    }


  }
}