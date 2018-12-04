package stocks.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import stocks.model.StockConstants;
import stocks.model.portfolio.Portfolio;
import stocks.model.portfolio.PortfolioModel;
import stocks.model.portfolio.PortfolioOperations;
import stocks.model.stock.Stock;
import stocks.model.strategy.DCA;
import stocks.model.strategy.Strategy;
import stocks.model.strategy.StrategyOperations;
import stocks.view.investment.InvestmentView;
import stocks.view.InvestmentViewOperations;

/**
 * The following class implements the IStockController interface. It allows user to perform
 * following operations : 1) Create Portfolio. 2) View Portfolio. (displays total investment made in
 * the portfolio and current valuation) 3) Add stock to the portfolio. 4) View all stocks in a
 * portfolio.
 */

public class StockController implements IStockController {
  //Variable represents the input given by the user.
  private final Readable readable;
  //Variable represents the output.
  private final Appendable appendable;
  //Variable represents the portfolio operations.
  private final PortfolioOperations<Portfolio> portfolioOperations;
  //Variable represents the investment stocks.view operations.
  private final InvestmentViewOperations investmentViewOperations;
  //date formatter which accepts date in a MM/dd/YYYY format
  private DateTimeFormatter formatter;

  /**
   * The following constructor initializes the stock controller.
   *
   * @param r the input given by the user.
   * @param a the output transmitted by the system.
   * @throws IllegalArgumentException when the input or output is in invalid format.
   */
  public StockController(Readable r, Appendable a) {
    //Throw exception if the output or input is in invalid format.
    if (r == null) {
      throw new IllegalArgumentException("Input Readable object is blank.");
    }
    if (a == null) {
      throw new IllegalArgumentException("Output Appendable object is blank.");
    }
    this.appendable = a;
    this.readable = r;
    Map<String, Map<String, Map<String, Double>>> stockData = new HashMap<String, Map<String,
            Map<String, Double>>>();
    this.portfolioOperations = new PortfolioModel(stockData);
    this.investmentViewOperations = new InvestmentView();
    this.formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
  }

  /**
   * The following method handles the operation performed by the stock market. The following
   * operations are performed by the system. 1) Create Portfolio. 2) View Portfolio. (displays total
   * investment made in the portfolio and current valuation) 3) Add stock to the portfolio. 4) View
   * all stocks in a portfolio.
   */
  public void handleOperation() {
    Scanner scan = new Scanner(this.readable);
    while (true) {
      try {
        investmentViewOperations.displayMessage(appendable, StockConstants.MENU);
        String userChoice = scan.nextLine();
        if (userChoice.isEmpty()) {
          continue;
        }
        int choice = 0;
        try {
          choice = Integer.parseInt(userChoice);
        } catch (NumberFormatException nbe) {
          continue;
        }

        switch (choice) {
          // Create a new portfolio.
          case 1:
            investmentViewOperations.displayMessage(appendable, StockConstants.ADD_PORTFOLIO_NAME);
            addPortFolio(scan);
            break;

          //View all portfolios on the date given by the user.
          case 2:
            investmentViewOperations.displayMessage(appendable, StockConstants.ADD_DATE);
            viewPortFolio(scan);
            break;

          //Add a new stock in the portfolio based upon quantity.
          case 3:
            handleBuyStockByVolume(scan);
            break;

          //Buying new stock based upon the amount entered
          case 4:
            handleBuyStockByAmount(scan);
            break;

          //View a composition of a portfolio at given date.
          case 5:
            handleViewStocks(scan);
            break;

          // Buying stocks using strategy
          case 6:
            handleStrategyInput(scan);
            break;
          case 7:
            portfolioOperations.savePortfolios();
            break;
          case 8:
            portfolioOperations.loadPortfolios();
            break;
          case 9:
            return;
          default:
            investmentViewOperations.displayMessage(appendable, StockConstants.ERROR_INVALID_MENU);
        }
      } catch (Exception e) {
        investmentViewOperations.displayMessage(appendable, StockConstants.SOMETHING_WENT_WRONG);
        continue;
      }
    }
  }

  private void handleBuyStockByVolume(Scanner scan) {
    //if the portfolio is not selected then it asks user to select the portfolio.
    investmentViewOperations.displayMessage(appendable,
            StockConstants.SELECT_PORTFOLIO + portfolioOperations.toString() + "\n");
    if (portfolioOperations.toString().isEmpty()) {
      investmentViewOperations.displayMessage(appendable, "No porfolios found.\n");
      return;
    }
    //Accepting the valid portfolio number
    int portfolio1 = getPortFolioId(scan, portfolioOperations.toString());

    //Accepting the ticker symbol
    investmentViewOperations.displayMessage(appendable, StockConstants.ADD_TICKER_SYMBOL);
    String tickerSymbol = getTickerSymbol(scan);

    //Accepting the commission rate
    investmentViewOperations.displayMessage(appendable, StockConstants.ADD_STOCK_COMMISSION);
    double commissionRate = getCommissionRate(scan);

    //Accepting the valid stock volume.
    investmentViewOperations.displayMessage(appendable, StockConstants.ADD_STOCK_VOLUME);
    double volume = getStockVolume(scan);

    //Accepting the valid date
    LocalDate d = getDate(scan, false);

    addStock(scan, portfolio1, tickerSymbol, volume, d, commissionRate);
    return;
  }


  private void handleBuyStockByAmount(Scanner scan) {
    //if the portfolio is not selected then it asks user to select the portfolio.
    investmentViewOperations.displayMessage(appendable,
            StockConstants.SELECT_PORTFOLIO + portfolioOperations.toString() + "\n");
    if (portfolioOperations.toString().isEmpty()) {
      investmentViewOperations.displayMessage(appendable, "No porfolios found.\n");
      return;
    }
    //Accepting the valid portfolio number
    int portfolio2 = getPortFolioId(scan, portfolioOperations.toString());

    //Accepting the ticker symbol
    investmentViewOperations.displayMessage(appendable, StockConstants.ADD_TICKER_SYMBOL);
    String tickerSymbol2 = getTickerSymbol(scan);

    //Accepting the commission rate
    investmentViewOperations.displayMessage(appendable, StockConstants.ADD_STOCK_COMMISSION);
    double commissionRate2 = getCommissionRate(scan);

    //Accepting the valid amount volume.
    investmentViewOperations.displayMessage(appendable, StockConstants.ADD_AMOUNT_INVESTED);
    double amountToBeInvested = getStockVolume(scan);

    //Accepting the valid date
    LocalDate d2 = getDate(scan, false);
    addStockByAmount(scan, portfolio2, tickerSymbol2, amountToBeInvested, d2, commissionRate2);
    return;
  }

  private void handleStrategyInput(Scanner scan) {
    investmentViewOperations.displayMessage(appendable,
            StockConstants.SELECT_PORTFOLIO + portfolioOperations.toString() + "\n");
    //Accepting the portfolio id
    int portfolioId = getPortFolioId(scan, portfolioOperations.toString());
    //Map to store the stocks in the portfolio.
    Map<String, Stock> portfolioStocks;
    //Accepting the commission for the investment
    investmentViewOperations.displayMessage(appendable, StockConstants.ADD_STOCK_COMMISSION);
    double commissionRateForPortfolio = getCommissionRate(scan);
    //Accepting the valid amount.
    investmentViewOperations.displayMessage(appendable, StockConstants.ADD_AMOUNT_INVESTED);
    double amountToBeInvestedInPortfolio = getStockVolume(scan);
    //Accepting the Strategy options
    investmentViewOperations.displayMessage(appendable, StockConstants.STRATEGY_MENU);
    String strategyOption = scan.nextLine();
    if (strategyOption.isEmpty()) {
      return;
    }
    int strategyChoice = 0;
    try {
      strategyChoice = Integer.parseInt(strategyOption);
    } catch (NumberFormatException nbe) {
      return;
    }
    switch (strategyChoice) {
      //One time investment strategy.
      case 1:
        investmentViewOperations.displayMessage(appendable,
                "ONE TIME INVESTMENT\n");
        handleOneTimeInvestment(scan, portfolioId, amountToBeInvestedInPortfolio,
                commissionRateForPortfolio);
        break;
      case 2:
        investmentViewOperations.displayMessage(appendable,
                "DOLLAR COST AVERAGE STRATEGY\n");
        handleDCAStrategy(scan, portfolioId, amountToBeInvestedInPortfolio,
                commissionRateForPortfolio);
        break;

      default:
        investmentViewOperations.displayMessage(appendable,
                StockConstants.ERROR_INVALID_MENU);
    }
    return;
  }


  private void handleViewStocks(Scanner scan) {
    investmentViewOperations.displayMessage(appendable,
            StockConstants.SELECT_PORTFOLIO + portfolioOperations.toString() + "\n");
    //Accepting the valid portfolio number
    int portfolio3 = getPortFolioId(scan, portfolioOperations.toString());
    //Accepting the valid date
    LocalDate d = getDate(scan, false);
    Map<String, Map<String, Double>> resultMap =
            portfolioOperations.displayStocks(portfolio3, d);
    String formattedOutput = investmentViewOperations.displayStocks(resultMap);
    investmentViewOperations.displayMessage(appendable, formattedOutput);
  }

  /**
   * The following method handles the DCA strategy. * @param scan Scanner object to accept the user
   * input. * @param portfolioId the id of the portfolio in which stock is to be added. * @param
   * amountToBeInvestedInPortfolio the amount to be invested in the portfolio. * @param
   * commissionRateForPortfolio the commission charged for the portfolio.
   */
  private void handleDCAStrategy(Scanner scan, int portfolioId,
                                 double amountToBeInvestedInPortfolio,
                                 double commissionRateForPortfolio) {
    //Initializing the DCA strategy object.
    StrategyOperations dcaStrategy = new DCA(portfolioOperations);
    //Accepting the start date of the strategy.
    LocalDate startDate = getDate(scan, false);
    investmentViewOperations.displayMessage(appendable,
            "Press s if you want to skip end date or enter any key to enter end date\n");
    String isSkip = scan.nextLine();
    LocalDate endDate;
    if (isSkip.equalsIgnoreCase("s")) {
      endDate = LocalDate.now();
    } else {
      //Accepting the end date of the strategy.
      endDate = getDate(scan, true);
    }

    while (true) {
      if (startDate.compareTo(endDate) == 0) {
        investmentViewOperations.displayMessage(appendable,
                "Start and end date cannot be same");
        startDate = getDate(scan, false);
        endDate = getDate(scan, true);
      } else if (startDate.compareTo(endDate) > 0) {
        investmentViewOperations.displayMessage(appendable,
                "Start date cannot be after end date");
        startDate = getDate(scan, false);
        endDate = getDate(scan, true);
      } else {
        break;
      }
    }

    //Accepting the frequency for the investment.
    int investmentFrequency = getFrequency(scan);

    while (true) {
      investmentViewOperations.displayMessage(appendable, StockConstants.INVESTMENT_MENU);
      String investmentOption = scan.nextLine();
      if (investmentOption.isEmpty()) {
        continue;
      }
      int investmentChoice = 0;
      try {
        investmentChoice = Integer.parseInt(investmentOption);
      } catch (NumberFormatException nbe) {
        continue;
      }
      //Retrieving the stocks in portfolio.
      List<Stock> listOfStocks = portfolioOperations.viewPortfolioStocks(portfolioId, startDate);
      List<Double> weightedInvestments = new ArrayList<>();

      switch (investmentChoice) {
        case 1:
          investmentViewOperations.displayMessage(appendable,
                  "Custom Weight");
          weightedInvestments = getWeightageForEachStock(weightedInvestments,
                  listOfStocks, scan);
          Strategy strategyC = new Strategy(startDate, endDate, portfolioId,
                  amountToBeInvestedInPortfolio, commissionRateForPortfolio,
                  listOfStocks, weightedInvestments, investmentFrequency);
          dcaStrategy.executeStrategy(strategyC);
          break;
        case 2:
          investmentViewOperations.displayMessage(appendable,
                  "Equal Weight");
          double weightForEachStock = 100 / listOfStocks.size();
          for (int i = 0; i < listOfStocks.size(); i++) {
            weightedInvestments.add(weightForEachStock);
          }
          Strategy strategyE = new Strategy(startDate, endDate, portfolioId,
                  amountToBeInvestedInPortfolio, commissionRateForPortfolio,
                  listOfStocks, weightedInvestments, investmentFrequency);
          dcaStrategy.executeStrategy(strategyE);
          break;
        default:
          investmentViewOperations.displayMessage(appendable,
                  StockConstants.ERROR_INVALID_MENU);
      }
      break;
    }

  }

  /**
   * The following method handles one time investment options.
   *
   * @param scan                          Scanner object to accept the user input.
   * @param portfolioId                   the id of the portfolio in which stock is to be added.
   * @param amountToBeInvestedInPortfolio the amount to be invested in the portfolio.
   * @param commissionRateForPortfolio    the commission charged for the portfolio.
   */
  private void handleOneTimeInvestment(Scanner scan, int portfolioId,
                                       double amountToBeInvestedInPortfolio,
                                       double commissionRateForPortfolio) {
    List<Double> weightedInvestments = new ArrayList<>();
    LocalDate startDate = getDate(scan, false);
    //List to store the stocks in portfolio.
    List<Stock> listOfStocks = new ArrayList<Stock>();
    //Retrieving all the stocks in a portfolio.
    listOfStocks = portfolioOperations.viewPortfolioStocks(portfolioId, startDate);

    while (true) {
      investmentViewOperations.displayMessage(appendable, StockConstants.INVESTMENT_MENU);
      String investmentOption = scan.nextLine();
      if (investmentOption.isEmpty()) {
        continue;
      }
      int investmentChoice = 0;
      try {
        investmentChoice = Integer.parseInt(investmentOption);
      } catch (NumberFormatException nbe) {
        continue;
      }
      switch (investmentChoice) {
        case 1:
          investmentViewOperations.displayMessage(appendable,
                  "Enter the percentage of investment for each stock in portfolio");
          weightedInvestments = getWeightageForEachStock(weightedInvestments,
                  listOfStocks, scan);
          investInPortfolio(scan, portfolioId, amountToBeInvestedInPortfolio,
                  listOfStocks, commissionRateForPortfolio, weightedInvestments, startDate);
          break;
        case 2:

          if (listOfStocks.size() == 0) {
            throw new IllegalArgumentException("No stocks found");
          }

          double weightForEachStock = 100 / listOfStocks.size();
          investmentViewOperations.displayMessage(appendable,
                  "Weight for each stock is: " + weightForEachStock);
          for (int i = 0; i < listOfStocks.size(); i++) {
            weightedInvestments.add(weightForEachStock);
          }
          investInPortfolio(scan, portfolioId, amountToBeInvestedInPortfolio,
                  listOfStocks, commissionRateForPortfolio, weightedInvestments, startDate);
          break;
        default:
          investmentViewOperations.displayMessage(appendable,
                  StockConstants.ERROR_INVALID_MENU);
      }
      break;
    }
  }


  /**
   * The following method verifies whether the given date is in valid format or not.
   *
   * @param dateString the date in string format.
   * @return true if the given date is valid.
   */
  private boolean validateDate(String dateString) {
    DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    dateFormat.setLenient(false);
    try {
      LocalDate.parse(dateString, formatter);
    } catch (DateTimeException d) {
      return false;
    }
    return true;
  }

  /**
   * The following method checks the validity of the date string given by the user.
   *
   * @param scan the scanner class used for scanning the input.
   * @return the date in string if it is in valid format.
   */
  private String checkDateValidity(Scanner scan, boolean isEndDate) {
    if (!isEndDate) {
      investmentViewOperations.displayMessage(appendable, StockConstants.ADD_DATE);
    } else {
      investmentViewOperations.displayMessage(appendable, StockConstants.ADD_END_DATE);
    }

    String date = scan.nextLine();
    boolean dateValidity = validateDate(date);
    if (dateValidity) {
      return date;
    } else {
      investmentViewOperations.displayMessage(appendable, StockConstants.ERROR_INVALID_DATE);
      return "Invalid Date";
    }
  }

  /**
   * The following method checks whether the given volume of shares is valid or not.
   *
   * @param volumeOfShareEntered the volume of shares entered by the user.
   * @return true if the given volume is in valid format.
   */
  private boolean validateVolume(double volumeOfShareEntered) {
    int maxValue = Integer.MAX_VALUE;
    return volumeOfShareEntered > 0 && volumeOfShareEntered != maxValue;

  }

  private boolean validateRate(double commissionRate) {
    return commissionRate >= 0 && commissionRate < 100;
  }

  /**
   * This method adds a new portfolio specified by user.It throws an IllegalArgumentException if
   * duplicate portfolio is found.
   *
   * @param scan Scanner object from where we accept portfolio name
   * @throws IllegalArgumentException duplicate portfolio
   */
  private void addPortFolio(Scanner scan) {
    while (true) {
      String portfolioName = scan.nextLine();
      try {
        portfolioOperations.addPortfolio(portfolioName);
        break;
      } catch (IllegalArgumentException iae) {
        investmentViewOperations.displayMessage(appendable,
                StockConstants.ERROR_INVALID_PORTFOLIO_NAME
                        + StockConstants.ADD_PORTFOLIO_NAME);
      }
    }
  }

  /**
   * The following method accepts the weightage given to stock in the portfolio.
   *
   * @param investments  the list of weigthage
   * @param listOfStocks the list of stocks in the portfolio
   * @param scan         the input given by the user.
   */
  private List<Double> getWeightageForEachStock(List<Double> investments,
                                                List<Stock> listOfStocks, Scanner scan) {
    investmentViewOperations.displayMessage(appendable,
            "The number of stocks in portfolio are: " + listOfStocks.size() + "\n");
    while (true) {
      double investmentSum = 0;
      List<Double> weightedInvestments = new ArrayList<>(investments);
      for (Stock stock : listOfStocks) {
        investmentViewOperations.displayMessage(appendable,
                stock.getTickerSymbol() + "\n");
        double weightageInNumbers = getStockVolume(scan);
        weightedInvestments.add(weightageInNumbers);
        investmentSum = investmentSum + weightageInNumbers;
      }
      //The following case handles all the edge cases.
      if (investmentSum < 100) {
        investmentViewOperations.displayMessage(appendable,
                "The investment in portfolio cannot be less than 100%");
      } else if (investmentSum > 100) {
        investmentViewOperations.displayMessage(appendable,
                "The investment in portfolio cannot be greater than 100%");
      } else {
        return weightedInvestments;
      }

    }

  }

  /**
   * The following method invests the given amount in portfolio.
   *
   * @param scan                the input given by the user.
   * @param portfolioId         the id of the portfolio.
   * @param amount              the amount to be invested in the stock.
   * @param stocksInPortfolio   the list of stocks in portfolio.
   * @param commissionRate      the commission charged for the transaction.
   * @param weightedInvestments the list of investment weights given to each investment.
   * @param d                   the date at which investment was made.
   */
  private void investInPortfolio(Scanner scan, int portfolioId, double amount,
                                 List<Stock> stocksInPortfolio, double commissionRate,
                                 List<Double> weightedInvestments, LocalDate d) {

    for (int i = 0; i < weightedInvestments.size(); i++) {
      double amountToBeInvestedInStock = (weightedInvestments.get(i) / 100) * amount;
      String stockTickerSymbol = stocksInPortfolio.get(i).getTickerSymbol();
      addStockByAmount(scan, portfolioId, stockTickerSymbol,
              amountToBeInvestedInStock, d, commissionRate);
    }
  }

  /**
   * The following method buys a stock in a portfolio based on the volume of stock to be bought.
   *
   * @param scan           the input given by the user.
   * @param portfolioId    the id of the portfolio in which amount is to be invested.
   * @param volume         the quantity of stock to be bought.
   * @param d              the date at which stock is to be bought.
   * @param commissionRate the commission rate charged for the transaction.
   */
  private void addStock(Scanner scan, int portfolioId, String tickerSymbol, double volume,
                        LocalDate d, double commissionRate) {
    while (true) {
      try {
        portfolioOperations.addStock(portfolioId, tickerSymbol, volume, d, commissionRate);
        return;
      } catch (IllegalArgumentException iae) {
        //handleExceptions(iae,scan,d,tickerSymbol);
        if (iae.getMessage().contains(StockConstants.ERROR_MARKET_CLOSED)) {
          investmentViewOperations.displayMessage(appendable,
                  StockConstants.ERROR_MARKET_CLOSED);
          d = getDate(scan, false);
        } else if (iae.getMessage().contains(StockConstants.ERROR_PRICE_NOT_FOUND)
                || iae.getMessage().contains(StockConstants.ERROR_DATE_EXCEEDS)) {
          investmentViewOperations.displayMessage(appendable,
                  StockConstants.ERROR_INVALID_TICKER_SYMBOL
                          + StockConstants.ADD_TICKER_SYMBOL);
          tickerSymbol = getTickerSymbol(scan);
        } else {
          investmentViewOperations.displayMessage(appendable, iae.getMessage());
        }
      }
    }
  }

  /**
   * The following method buys stock based on the amount invested by the user.
   *
   * @param scan           the input given by the user.
   * @param portfolioId    the id of the portfolio in which amount is tobe invested.
   * @param tickerSymbol   the ticker symbol of the stock.
   * @param d              the date at which amount is to be invested.
   * @param commissionRate the commission rate charged for the transaction.
   */
  private void addStockByAmount(Scanner scan, int portfolioId, String tickerSymbol, double amount,
                                LocalDate d, double commissionRate) {
    while (true) {
      try {
        portfolioOperations.addStockByAmount(portfolioId, tickerSymbol, amount, d, commissionRate);
        return;
      } catch (IllegalArgumentException iae) {
        //handleExceptions(iae,scan,d,tickerSymbol);
        if (iae.getMessage().contains(StockConstants.ERROR_MARKET_CLOSED)) {
          investmentViewOperations.displayMessage(appendable,
                  StockConstants.ERROR_MARKET_CLOSED);
          d = getDate(scan, false);
        } else if (iae.getMessage().contains(StockConstants.ERROR_PRICE_NOT_FOUND)
                || iae.getMessage().contains(StockConstants.ERROR_DATE_EXCEEDS)) {
          investmentViewOperations.displayMessage(appendable,
                  StockConstants.ERROR_INVALID_TICKER_SYMBOL
                          + StockConstants.ADD_TICKER_SYMBOL);
          tickerSymbol = getTickerSymbol(scan);
        } else {
          investmentViewOperations.displayMessage(appendable, iae.getMessage());
        }
      }
    }
  }

  /**
   * The following method handles the illegal argument exception.
   */
  private void handleExceptions(IllegalArgumentException iae, Scanner scan,
                                LocalDate d, String tickerSymbol) {
    if (iae.getMessage().contains(StockConstants.ERROR_MARKET_CLOSED)) {
      investmentViewOperations.displayMessage(appendable,
              StockConstants.ERROR_MARKET_CLOSED);
      d = getDate(scan, false);
    } else if (iae.getMessage().contains(StockConstants.ERROR_PRICE_NOT_FOUND)
            || iae.getMessage().contains(StockConstants.ERROR_DATE_EXCEEDS)) {
      investmentViewOperations.displayMessage(appendable,
              StockConstants.ERROR_INVALID_TICKER_SYMBOL
                      + StockConstants.ADD_TICKER_SYMBOL);
      tickerSymbol = getTickerSymbol(scan);
    } else {
      investmentViewOperations.displayMessage(appendable, iae.getMessage());
    }
  }


  /**
   * This method shows total cost basis and total value of all portfolios for a specified date.It
   * takes stocks of each portfolio upto that date. It throws an IllegalArgumentException if invalid
   * date is passed.
   *
   * @param scan Scanner object from where we accept date
   * @throws IllegalArgumentException invalid date
   */
  private void viewPortFolio(Scanner scan) {

    while (true) {
      String date = scan.nextLine();
      boolean dateValidity = validateDate(date);
      if (dateValidity) {
        LocalDate d = LocalDate.parse(date, formatter);
        Map<String, Map<String, Double>> resultMap = portfolioOperations.displayPortfolios(d);
        String formattedOutput = investmentViewOperations.displayPortfolios(resultMap);
        investmentViewOperations.displayMessage(appendable, formattedOutput);
        break;
      } else {
        investmentViewOperations.displayMessage(appendable,
                StockConstants.ERROR_INVALID_DATE + StockConstants.ADD_DATE);
      }
    }
  }

  /**
   * This method gets portfolio id from user selecting one from existing portfolios.It throws an
   * IllegalArgumentException if invalid portfolio id is passed.
   *
   * @param scan Scanner object from where we accept portfolio id
   * @throws IllegalArgumentException invalid portfolio id
   */
  private int getPortFolioId(Scanner scan, String portfolioOperations) {
    while (true) {
      try {
        int portfolioId = Integer.parseInt(scan.nextLine());
        if (portfolioOperations.contains(portfolioId + ".")) {
          return portfolioId;
        }
        //invalid input
        throw new NumberFormatException();
      } catch (NumberFormatException nbe) {
        investmentViewOperations.displayMessage(appendable,
                StockConstants.ERROR_INVALID_PORTFOLIO_ID +
                        StockConstants.SELECT_PORTFOLIO + portfolioOperations);
      }
    }
  }

  /**
   * This method gets ticker symbol from user.
   *
   * @param scan Scanner object from where we accept ticker symbol.
   * @return the ticker symbol of the stock.
   */
  private String getTickerSymbol(Scanner scan) {
    return scan.nextLine();
  }

  /**
   * This method gets stock volume for a ticker symbol while adding new stock.It throws an
   * IllegalArgumentException if invalid stock volume is passed. It does not accept negative
   * values.
   *
   * @param scan Scanner object from where we accept portfolio id
   * @return the volume of the stock.
   * @throws IllegalArgumentException invalid stock volume
   */
  private double getStockVolume(Scanner scan) {
    while (true) {
      double volumeEnteredByUser = Double.parseDouble(scan.nextLine());
      boolean volumeValidity = validateVolume(volumeEnteredByUser);
      if (volumeValidity) {
        return volumeEnteredByUser;
      } else {
        investmentViewOperations.displayMessage(appendable,
                StockConstants.ERROR_INVALID_VOLUME +
                        StockConstants.ADD_STOCK_VOLUME);
      }
    }
  }

  /**
   * The following method accepts the commission for the transaction.
   *
   * @param scan the input given by the user.
   */
  private double getCommissionRate(Scanner scan) {
    while (true) {
      double commissionRate = Double.parseDouble(scan.nextLine());
      boolean rateValidity = validateRate(commissionRate);
      if (rateValidity) {
        return commissionRate;
      } else {
        if (commissionRate < 0) {
          investmentViewOperations.displayMessage(appendable,
                  StockConstants.ERROR_COMMISSION_RATE_ZERO
                          + StockConstants.ADD_STOCK_COMMISSION);
        } else {
          investmentViewOperations.displayMessage(appendable,
                  StockConstants.ERROR_COMMISSION_RATE_INVALID
                          + StockConstants.ADD_STOCK_COMMISSION);
        }
      }
    }
  }

  /**
   * This method gets date from user while adding stock.It throws an IllegalArgumentException if
   * invalid date is passed. It accepts in MM/dd/YYYY format otherwise it is invalid.
   *
   * @param scan Scanner object from where we accept portfolio id
   * @return the date in Local Date format.
   * @throws IllegalArgumentException invalid date
   */
  private LocalDate getDate(Scanner scan, boolean isEndDate) {
    LocalDate d;
    while (true) {
      String inputValidity = checkDateValidity(scan, isEndDate);
      if (!inputValidity.matches("Invalid Date")) {
        d = LocalDate.parse(inputValidity, formatter);
        return d;
      }
    }
  }

  /**
   * The following method accepts the frequency of the investment for the transaction.
   *
   * @param scan the input of the user.
   */
  private int getFrequency(Scanner scan) {
    while (true) {
      investmentViewOperations.displayMessage(appendable, StockConstants.FREQUENCY_MENU);
      String investmentOption = scan.nextLine();
      if (investmentOption.isEmpty()) {
        continue;
      }
      int investmentChoice = 0;
      int days = 0;
      try {
        investmentChoice = Integer.parseInt(investmentOption);
        switch (investmentChoice) {
          case 1:
            days = 365;
            break;
          case 2:
            days = 120;
            break;
          case 3:
            days = 30;
            break;
          case 4:

            while (true) {
              investmentViewOperations.displayMessage(appendable, "Enter custom days?\n");
              String customDays = scan.nextLine();
              if (investmentOption.isEmpty()) {
                continue;
              }
              try {
                int customDaysValue = Integer.parseInt(customDays);
                if (customDaysValue < 0 || customDaysValue > 365) {
                  throw new NumberFormatException("Invalid");
                }
                days = customDaysValue;
                break;
              } catch (NumberFormatException nbe) {
                continue;
              }
            }
            break;
          default:
            throw new IllegalArgumentException("Invalid frequency");
        }
      } catch (NumberFormatException nbe) {
        continue;
      }
      return days;
    }

  }

}
