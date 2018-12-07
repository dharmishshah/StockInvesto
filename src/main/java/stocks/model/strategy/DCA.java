package stocks.model.strategy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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


  /**
   * The following method saves all portfolios on a file.
   */
  @Override
  public void saveStrategy(String data) {


    try {
      String current = new java.io.File(".").getCanonicalPath();
      File dir = new File(current + "/stockData/savedStrategies/");

      if (!dir.exists()) {
        dir.mkdir();
      }

      File strategyFile = new File(dir + "/strategies.txt");
      BufferedWriter out = new BufferedWriter(
              new FileWriter(strategyFile, true));
      out.write(data);
      out.newLine();
      out.close();
    } catch (IOException io) {
      throw new IllegalArgumentException("Invalid file directory path while saving strategy.");
    }
  }


  /**
   * The following method loads all portfolios from a file.
   */
  @Override
  public List<String> loadStrategy() {
    List<String> savedStrategies = new ArrayList<String>();

    try {
      String current = new java.io.File(".").getCanonicalPath();
      File file = new File(current + "/stockData/savedStrategies/strategies.txt");
      FileReader fileReader = new FileReader(file);
      // Always wrap FileReader in BufferedReader.
      BufferedReader bufferedReader = new BufferedReader(fileReader);
      String line;
      if (file.exists()) {
        while ((line = bufferedReader.readLine()) != null) {
          savedStrategies.add(line);
        }
      }

    } catch (IOException ioe) {
      throw new IllegalArgumentException("Invalid file directory path while loading strategy.");
    }

    return savedStrategies;
  }
}