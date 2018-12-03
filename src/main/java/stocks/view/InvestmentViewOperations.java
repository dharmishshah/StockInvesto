package stocks.view;

import java.util.Map;

/**
 * The following interface represents the operations performed by the investment stocks.view. The
 * operations allowed are 1) Display stocks and 2) Display portfolios.
 */
public interface InvestmentViewOperations {

  /**
   * the following method displays the appropiate message for user convenience.
   *
   * @param appendable the input given by the user.
   * @param message    message to be displayed.
   */
  void displayMessage(Appendable appendable, String message);

  /**
   * the following method displays the stock for a portfolio.
   *
   * @param result result of all stock values to be displayed
   * @return outputted string to be displayed
   */
  public String displayStocks(Map<String, Map<String, Double>> result);


  /**
   * The following method displays the portfolio for the user.
   *
   * @param result result of all portfolios values to be displayed
   * @return outputted string to be displayed
   */
  public String displayPortfolios(Map<String, Map<String, Double>> result);


}
