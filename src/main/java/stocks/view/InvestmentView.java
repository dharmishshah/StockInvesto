package stocks.view;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Map;
import stocks.view.InvestmentViewOperations;

/**
 * The following class implements all the functions of the InvestmentViewOperations. The operations
 * allowed are 1) Display stocks and 2) Display portfolios.
 */
public class InvestmentView implements InvestmentViewOperations {

  private static DecimalFormat df2;
  private String seperator;
  //The following string variable represent the dollar symbol
  private String dollar;

  /**
   * This constructor constructs investment view object with default values.
   */
  public InvestmentView() {
    df2 = new DecimalFormat("#.00");
    dollar = "$";
    seperator = "\t\t\t";
  }

  /**
   * the following method displays the appropiate message for user convenience.
   *
   * @param appendable the input given by the user.
   * @param message    message to be displayed.
   */
  @Override
  public void displayMessage(Appendable appendable, String message) {
    if (message == null || message.isEmpty()) {
      return;
    }
    try {
      appendable.append(message);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


  /**
   * the following method displays the stock for a portfolio.
   *
   * @param result result of all stock values to be displayed
   * @return outputted string to be displayed
   */
  @Override
  public String displayStocks(Map<String, Map<String, Double>> result) {
    String finalString = "";
    finalString =
            finalString + "Stock Name" + seperator + "Volume" + seperator + "Stock Cost" +
                    " Basis" + seperator + "Stock Value" + seperator + "Commission_Paid\n";
    double totalCostBasis = 0.0;
    double totalValue = 0.0;
    double volume = 0;
    double totalCommission = 0.0;
    for (Map.Entry<String, Map<String, Double>> stock : result.entrySet()) {

      String tickerSymbol = stock.getKey();
      Map<String, Double> resultValues = stock.getValue();
      double currentCostBasis = resultValues.get("costBasis");
      double currentTotalValue = resultValues.get("totalValue");
      double commissionPaid = resultValues.get("commission");
      volume = volume + resultValues.get("volume");
      finalString =
              finalString + tickerSymbol + seperator + " " +
                      df2.format(resultValues.get("volume")) + "   "
                      + seperator + dollar + df2.format(currentCostBasis) + seperator + "   "
                      + dollar + df2.format(currentTotalValue) + seperator + "   " + dollar
                      + df2.format(commissionPaid) + "\n";
      totalCostBasis = totalCostBasis + currentCostBasis;
      totalValue = totalValue + currentTotalValue;
      totalCommission = totalCommission + commissionPaid;
    }

    finalString =
            finalString + "TOTAL" + seperator + " " + df2.format(volume)
                    + seperator + "   " + dollar + df2.format(totalCostBasis) + seperator + "   "
                    + dollar + df2.format(totalValue) + seperator + "   "
                    + dollar + df2.format(totalCommission) + "\n" +
                    "(Here Total Cost Basis is sum of the commission paid " +
                    "and amount investment made in the stock)\n";
    return finalString;

  }

  /**
   * The following method displays the portfolio for the user.
   *
   * @param result result of all portfolios values to be displayed.
   * @return outputted string to be displayed
   */
  @Override
  public String displayPortfolios(Map<String, Map<String, Double>> result) {

    String finalString = "";
    finalString = finalString + "Portfolio Name" + seperator + "Portfolio Volume" + seperator +
            "Portfolio Cost " + "Basis" + seperator + "Portfolio Value" + seperator +
            "Portfolio Commission\n";

    for (Map.Entry<String, Map<String, Double>> entry : result.entrySet()) {

      String portfolioName = entry.getKey();
      Map<String, Double> portfolio = entry.getValue();
      finalString =
              finalString + portfolioName + seperator + seperator + df2.format(portfolio.get(
                      "volume")) +
                      seperator + seperator + dollar + portfolio.get(
                      "costBasis") + seperator + seperator
                      + dollar + portfolio.get(
                      "totalValue") + seperator + seperator + dollar +
                      portfolio.get(
                              "commission") +
                      "\n";
    }
    finalString = finalString + "(Here Total Cost Basis is sum of"
            + " the commission paid and amount investment made in the stock)\n";

    return finalString;

  }


}
