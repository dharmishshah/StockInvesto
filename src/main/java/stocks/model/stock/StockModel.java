package stocks.model.stock;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import stocks.datasource.AlphaVantageAPI;
import stocks.datasource.StockDataSource;
import stocks.model.StockConstants;

/**
 * The following class represents a stock model which implements the stock operation.
 */
public class StockModel implements StockOperations<Stock> {

  //the variable represents stock data.
  private Map<String, Map<String, Map<String, Double>>> stockData;

  // formatting values upto 2 decimal
  private DecimalFormat df;

  //  data source to get cost prices of a ticker symbol
  private StockDataSource dataSource;

  /**
   * The following constructor initializes the stock model.
   *
   * @param stockData represents the stock data.
   */
  public StockModel(Map<String, Map<String, Map<String, Double>>> stockData) {
    if (stockData == null) {
      throw new IllegalArgumentException("stock data is blank");
    }
    this.stockData = stockData;
    this.dataSource = new AlphaVantageAPI();
    df = new DecimalFormat("#.00");
  }

  /**
   * The following method updates the existing stock.
   *
   * @param stock          the stock to be updated.
   * @param volume         the volume of stock to be updated.
   * @param date           the date  at which stock is to be updated.
   * @param commissionRate commission to buy stock.
   * @return the stock in its updated format.
   */
  @Override
  public Stock updateExistingStock(Stock stock, double volume,
                                   LocalDate date, double commissionRate) {

    validateStock(stock);
    validateStockData(stock.getTickerSymbol(), volume, date, commissionRate);

    double costPrice = getCostPriceStock(stock.getTickerSymbol(), date, false);
    //Updating the current price of the stock.
    stock.setCurrentPrice(costPrice);
    double newVolume = stock.getVolume() + volume;
    double newCommission = (volume * costPrice) * (commissionRate / 100);
    double updatedCommission = stock.getTotalCommission() + newCommission;
    //Updating the volume (quantity) of the stock.
    stock.setVolume(newVolume);
    double newInvestment = (costPrice * volume) + stock.getTotalCostBasis();
    double updatedInvestment = newInvestment + newCommission;
    //Updating the investment of the stock.
    stock.setTotalCostBasis(updatedInvestment);
    //Updating the commission paid for the stock
    stock.setTotalCommission(updatedCommission);
    //Creating the transaction record.
    Transaction newTransaction = new Transaction(date, volume, TransactionType.Buy,
            costPrice, commissionRate);
    List<Transaction> transactions = new ArrayList<>(stock.getTransactions());
    transactions.add(newTransaction);
    //Updating the transaction list for the stock.
    stock.setTransactions(transactions);
    double updatedTotalValue = costPrice * newVolume;
    //Updating the current value of the stock.
    stock.setCurrentTotalValue(updatedTotalValue);
    return stock;
  }

  /**
   * The following adds new stock.
   *
   * @param tickerSymbol   the ticker symbol of the stock.
   * @param volume         the quantity of the stock to be added.
   * @param date           the date at which new stock to be added.
   * @param commissionRate commission to buy stock
   * @return the new stock added in the portfolio.
   */
  @Override
  public Stock addNewStock(String tickerSymbol, double volume, LocalDate date,
                           double commissionRate) {

    validateStockData(tickerSymbol, volume, date, commissionRate);
    double costPrice = getCostPriceStock(tickerSymbol, date, false);
    if (costPrice == 0.0) {
      throw new IllegalArgumentException("Cost price cannot be zero.");
    }
    return new Stock(tickerSymbol, costPrice, volume, date, commissionRate);
  }

  /**
   * The following method compares the date of the transaction and the given date.
   *
   * @param transactionDate the date of the transaction.
   * @param givenDate       the date given by the user.
   * @return the comparision of the dates.
   */
  private int compareDates(LocalDate transactionDate, LocalDate givenDate) {
    return transactionDate.compareTo(givenDate);
  }

  /**
   * The following method returns the current valuation of th stock for the given date. eg: the
   * following method will return the price we will fetch if sell the stock on the given date.
   *
   * @param date  the date for which the valuation is to be found.
   * @param stock the stock of the company whose valuation is to be found.
   * @return the current valuation of the stock.
   */
  @Override
  public double getCurrentValuationOfStock(LocalDate date, Stock stock) {
    validateDate(date);
    validateStock(stock);
    List<Transaction> stockTransactions = stock.getTransactions();
    double volumeOfStock = 0;
    double valuationOfShare = 0;
    double costPrice = getCostPriceStock(stock.getTickerSymbol(), date, true);

    for (Transaction transaction : stockTransactions) {
      int dateComparision = compareDates(transaction.getDate(), date);
      if (dateComparision <= 0) {
        volumeOfStock = volumeOfStock + transaction.getVolume();
        valuationOfShare = valuationOfShare + (volumeOfStock * costPrice);
      } else {
        break;
      }
    }
    valuationOfShare = Double.parseDouble(df.format(valuationOfShare));
    return valuationOfShare;
  }

  /**
   * The following method returns the total amount invested in the stock for the given date. eg: the
   * following method will return the amount in the stock till the given date.
   *
   * @param date  the date for which the investment is to be found.
   * @param stock the stock of the company whose valuation is to be found.
   * @return the total investment made in the portfolio.
   */
  @Override
  public double getTotalCostBasisOfStock(LocalDate date, Stock stock) {
    validateDate(date);
    validateStock(stock);
    List<Transaction> stockTransactions = stock.getTransactions();
    double totalInvestment = 0;

    for (Transaction transaction : stockTransactions) {
      int dateComparision = compareDates(transaction.getDate(), date);
      if (dateComparision <= 0) {
        totalInvestment = totalInvestment + transaction.getTransactionCostBasis();
      } else {
        break;
      }
    }
    totalInvestment = Double.parseDouble(df.format(totalInvestment));
    return totalInvestment;
  }

  /**
   * The following method returns the cost price of a stock based on ticker symbol and specified
   * date using third party API. It saves the data as csv file for every unique symbol.
   *
   * @param date         the date for which the cost price is to be found.
   * @param tickerSymbol the stock of the company whose cost price is to be found.
   * @return the unit cost price of the stock.
   */
  @Override
  public double getCostPriceStock(String tickerSymbol, LocalDate date, boolean isView) {
    validateDate(date);
    double costPrice = 0.0;

    String dirName = "./stockData";

    String fileNameString = tickerSymbol + ".txt";
    File dir = new File(dirName);
    File fileName = new File(dir, fileNameString);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-dd");
    try {

      LocalDate d = Instant.ofEpochMilli(fileName.lastModified())
              .atZone(ZoneId.systemDefault())
              .toLocalDate();
      if (compareDates(LocalDate.now(), d) > 0) {
        getLatestStockData(tickerSymbol, fileName, dir);
        readFileContents(tickerSymbol, fileName);
      }

      if (this.stockData.get(tickerSymbol) == null) {
        readFileContents(tickerSymbol, fileName);
      }

      String dateString = date.format(formatter);
      costPrice = getCostPriceFromMap(tickerSymbol, dateString, isView);
    } catch (FileNotFoundException ex) {
      getLatestStockData(tickerSymbol, fileName, dir);
      try {
        readFileContents(tickerSymbol, fileName);
      } catch (IOException ioe) {
        ioe.printStackTrace();
      }
      String dateString = date.format(formatter);
      costPrice = getCostPriceFromMap(tickerSymbol, dateString, isView);

    } catch (IOException ex) {
      System.out.println("Error reading file '" + fileName + "'");
      // Or we could just do this:
      // ex.printStackTrace();
    }
    costPrice = Double.parseDouble(df.format(costPrice));
    return costPrice;

  }

  /**
   * The following method returns the total commission paid for the stock till the given date.
   *
   * @param date  the date till which commission is to be calculated.
   * @param stock the stock under consideration.
   */
  @Override
  public double getTotalCommissionPaid(LocalDate date, Stock stock) {
    validateDate(date);
    validateStock(stock);
    List<Transaction> stockTransactions = stock.getTransactions();
    double totalCommission = 0;
    for (Transaction transaction : stockTransactions) {
      int dateComparision = compareDates(transaction.getDate(), date);
      if (dateComparision <= 0) {
        totalCommission = totalCommission + transaction.getTransactionCommission();
      } else {
        break;
      }
    }
    totalCommission = Double.parseDouble(df.format(totalCommission));
    return totalCommission;
  }

  /**
   * The following method returns the cost price of the portfolio.
   *
   * @param tickerSymbol the ticker symbol of the stock.
   * @param dateString   the date in the string format.
   * @param isView       whether to stocks.view the cost portfolio or not.
   * @return the unit price of each stock.
   */
  private double getCostPriceFromMap(String tickerSymbol, String dateString, boolean isView) {
    double costPrice;
    if (this.stockData.get(tickerSymbol).get(dateString) == null) {
      if (isView) {
        int count = 0;
        while (true) {

          DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
          LocalDate d = LocalDate.parse(dateString, formatter);
          d = d.minusDays(1);
          dateString = d.toString();

          if (this.stockData.get(tickerSymbol).get(dateString) != null
                  && this.stockData.get(tickerSymbol).get(dateString).get("CLOSE") != null) {
            costPrice = this.stockData.get(tickerSymbol).get(dateString).get("CLOSE");
            break;
          }
          count++;
          if (count == 20) {
            throw new IllegalArgumentException(StockConstants.ERROR_PRICE_NOT_FOUND);
          }

        }
      } else {
        throw new IllegalArgumentException(StockConstants.ERROR_MARKET_CLOSED);
      }
    } else {
      costPrice = this.stockData.get(tickerSymbol).get(dateString).get("CLOSE");
    }
    costPrice = Double.parseDouble(df.format(costPrice));
    return costPrice;
  }

  /**
   * The following method reads the contents of the file where the stock data is stored.
   *
   * @param tickerSymbol the ticker symbol of the stock.
   * @param fileName     the name of the file.
   */
  private void readFileContents(String tickerSymbol, File fileName) throws IOException {

    String line;
    // FileReader reads text files in the default encoding.
    FileReader fileReader;
    fileReader = new FileReader(fileName);

    // Always wrap FileReader in BufferedReader.
    BufferedReader bufferedReader = new BufferedReader(fileReader);
    Map<String, Map<String, Double>> dateOutput = new HashMap<String, Map<String, Double>>();
    int count = 1;
    while ((line = bufferedReader.readLine()) != null) {
      if (count == 1) {
        count++;
        continue;
      }
      String[] outputStockData = line.split(",");
      Map<String, Double> output = new HashMap<String, Double>();
      if (outputStockData.length == 6) {
        output.put("OPEN", Double.parseDouble(outputStockData[1]));
        output.put("HIGH", Double.parseDouble(outputStockData[2]));
        output.put("LOW", Double.parseDouble(outputStockData[3]));
        output.put("CLOSE", Double.parseDouble(outputStockData[4]));
        dateOutput.put(outputStockData[0], output);
      }

    }

    this.stockData.put(tickerSymbol, dateOutput);

    // Always close files.
    bufferedReader.close();
  }

  /**
   * The method retrieves the data of the stock from the fie.
   *
   * @param tickerSymbol the ticker symbol of the stock.
   * @param fileName     the name of the file.
   * @param dir          file directory.
   */
  private void getLatestStockData(String tickerSymbol, File fileName, File dir) {


    try {

      StringBuilder output = this.dataSource.getData(tickerSymbol);
      if (output.toString().contains("Invalid API call")) {
        fileName.delete();
        throw new IllegalArgumentException(StockConstants.ERROR_PRICE_NOT_FOUND);
      }

      if (!dir.exists()) {
        dir.mkdir();
      }

      FileWriter fileWriter = new FileWriter(fileName);

      // Always wrap FileWriter in BufferedWriter.
      BufferedWriter bufferedWriter = new BufferedWriter(fileWriter, 10000);

      // Note that write() does not automatically
      // append a newline character.
      bufferedWriter.write(output.toString());


    } catch (IOException e) {
      e.printStackTrace();
      throw new IllegalArgumentException(StockConstants.ERROR_PRICE_NOT_FOUND);
    }
  }

  /**
   * The following method validates the stock data.
   *
   * @param tickerSymbol the ticker symbol of the stock.
   * @param volume       the quantity of the stock.
   * @param date         the date of the transaction.
   * @throws IllegalArgumentException if the volume and ticker symbol is in the invalid format.
   */
  private void validateStockData(String tickerSymbol, double volume, LocalDate date,
                                 double commissionRate) {
    if (tickerSymbol == null || tickerSymbol.isEmpty()) {
      throw new IllegalArgumentException(StockConstants.ERROR_INVALID_TICKER_SYMBOL);
    }

    if (volume <= 0) {
      throw new IllegalArgumentException(StockConstants.ERROR_INVALID_VOLUME);
    }

    if (commissionRate < 0) {
      throw new IllegalArgumentException(StockConstants.ERROR_INVALID_COMMISSION);
    }

    if (commissionRate > 100) {
      throw new IllegalArgumentException(StockConstants.ERROR_INVALID_COMMISSION);
    }

    validateDate(date);

    LocalTime openingHours = LocalTime.now().withHour(9).withMinute(0).withSecond(0);
    LocalTime closingHours = LocalTime.now().withHour(16).withMinute(0).withSecond(0);
//    if (LocalTime.now().compareTo(openingHours) < 0
//            || LocalTime.now().compareTo(closingHours) > 0) {
//      throw new IllegalArgumentException(StockConstants.ERROR_MARKET_CLOSED);
//    }
  }

  /**
   * The following method validates the date.
   *
   * @param date the date in Local date format.
   * @throws IllegalArgumentException when date is in invalid format.
   */
  private void validateDate(LocalDate date) {
    if (date == null) {
      throw new IllegalArgumentException(StockConstants.ERROR_INVALID_DATE);
    }

    if (date.compareTo(LocalDate.now()) > 0) {
      throw new IllegalArgumentException(StockConstants.ERROR_DATE_EXCEEDS);
    }
  }

  /**
   * The following method validates the stock object.
   *
   * @param stock the stock entered by the user.
   * @throws IllegalArgumentException when the stock object is null.
   */
  private void validateStock(Stock stock) {
    if (stock == null) {
      throw new IllegalArgumentException("Stock cannot be blank.");
    }
  }
}
