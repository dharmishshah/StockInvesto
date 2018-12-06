package stocks.model;

public class StockConstants {

  public static String MENU = "Menu\n1.Add Portfolio\n2.View Portfolio\n3.Buy Stock by Volume"
          + "\n4.Buy Stock by Amount\n5.View Stock\n6.Invest In Existing Portfolio By Strategy \n" +
          "7.Save Portfolio\n8.Load Portfolio\n9.Use Existing Strategy\n10.Exit\n";
  public static String ADD_PORTFOLIO_NAME = "Please enter portfolio name\n";
  public static String ADD_DATE = "Enter the start date in MM/DD/YYYY format\n";
  public static String ADD_END_DATE = "Enter the end date in MM/DD/YYYY format\n";
  public static String SELECT_PORTFOLIO = "Select a portfolio\n";
  public static String ADD_TICKER_SYMBOL = "Enter ticker symbol\n";
  public static String ADD_STOCK_VOLUME = "Enter stock volume\n";
  public static String ADD_AMOUNT_INVESTED = "Enter the amount to be invested in the stock\n";
  public static String ADD_STOCK_COMMISSION = "Enter the commission rate\n";
  public static String INVESTMENT_MENU = "AMOUNT DISTRIBUTION OPTIONS\n1.Custom Weighted Investment"
          + "\n2.Equal Weighted Investment\n";
  public static String STRATEGY_MENU = "INVESTMENT OPTIONS\n1.One Time Investment" +
          "\n2.DCA Strategy\n";
  public static String FREQUENCY_MENU = "FREQUENCY \n1.Yearly"
          + "\n2.Quaterly\n3.Monthly\n4.Custom Days\n";
  public static String SAVE_MENU = "SAVE PORTFOLIO OPTIONS\n1.Save a specific portfolio\n2.Save " +
          "all\n";
  public static String LOAD_MENU = "LOAD PORTFOLIO OPTIONS\n1.Load a specific portfolio\n2.Load " +
          "all\n";
  public static String ALPHA_VANTAGE_API = "https://www.alphavantage.co/query";

  public static String ERROR_MARKET_CLOSED = "Market is closed\n";
  public static String ERROR_PRICE_NOT_FOUND = "No price data found\n";
  public static String ERROR_INVALID_DATE = "Invalid date\n";
  public static String ERROR_INVALID_PORTFOLIO_ID = "Invalid portfolio Id\n";
  public static String ERROR_INVALID_PORTFOLIO_NAME = "Invalid portfolio name or duplicate " +
          "portfolio name\n";
  public static String ERROR_INVALID_TICKER_SYMBOL = "Invalid ticker symbol\n";
  public static String ERROR_INVALID_VOLUME = "Invalid volume\n";
  public static String ERROR_INVALID_FREQUENCY = "Invalid frequency\n";
  public static String ERROR_INVALID_MENU = "Invalid Menu item selected\n";
  public static String ERROR_INVALID_AMOUNT = "Invalid amount\n";
  public static String ERROR_NO_PORTFOLIOS = "No portfolios added.\n";
  public static String ERROR_INVALID_GENERIC_VALUE = "Invalid value entered\n";
  public static String ERROR_DATE_EXCEEDS = "Date cannot be greater than today's date\n";
  public static String ERROR_COMMISSION_RATE_ZERO = "Commission Rate cannot be zero\n";
  public static String ERROR_COMMISSION_RATE_INVALID = "Commission Rate cannot be greater than " +
          "100\n";
  public static String ERROR_WEIGHTAGE_SIZE = "The weights in the list do not match with"
          + "with the number of stocks";
  public static String ERROR_NEGATIVE_WEIGHTAGE = "WEIGHT IS NEGATIVE";
  public static String ERROR_GREATER_WEIGHTAGE = "WEIGHT CANNOT BE GREATER THAN 100";
  public static String ERROR_SUM_WEIGHTS = "SUM OF THE WEIGHT CANNOT BE GREATER THAN 100";
  public static String ERROR_SUM_WEIGHTS_LESS = "SUM OF THE WEIGHT CANNOT BE LESS THAN 100";
  public static String ERROR_INVALID_COMMISSION = "Commission Rate cannot be less than 0 or " +
          "greater than 100\n";

  public static String ERROR_DAYS = "INVALID DAY FREQIUENCY";
  public static String ERROR_INVALID_FILE = "Invalid file\n";
  public static String SOMETHING_WENT_WRONG = "Ooops!!!! Something went wrong\n";
  public static String ERROR_SAME_DATE = "START AND END DATE CAN BE SAME";
  public static String ERROR_START_DATE = "START DATE CANNOT BE PRIOR TO END DATE";


}
