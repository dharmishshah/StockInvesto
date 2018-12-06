package stocks.datasource;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import stocks.model.StockConstants;

public class AlphaVantageAPI implements StockDataSource {

  @Override
  public StringBuilder getData(String tickerSymbol) {
    String apiKey = "SBLPS6ZZ1HFAR8VM";
    URL url;

    try {
      /*
      create the URL. This is the query to the web service. The query string
      includes the type of query (DAILY stock prices), stock symbol to be
      looked up, the API key and the format of the returned
      data (comma-separated values:csv). This service also supports JSON
      which you are welcome to use.
       */

      String parameterString = "?function=TIME_SERIES_DAILY"
              + "&outputsize=full"
              + "&symbol=" + tickerSymbol + "&apikey=" + apiKey + "&datatype=csv";
      url = new URL(StockConstants.ALPHA_VANTAGE_API + parameterString);
    } catch (MalformedURLException e) {
      throw new RuntimeException("the alphavantage API has either changed or "
              + "no longer works");
    }

    InputStream in;
    StringBuilder output = new StringBuilder();

    try {
      /*
      Execute this query. This returns an InputStream object.
      In the csv format, it returns several lines, each line being separated
      by commas. Each line contains the date, price at opening time, highest
      price for that date, lowest price for that date, price at closing time
      and the volume of trade (no. of shares bought/sold) on that date.

      This is printed below.
       */
      in = url.openStream();
      int b;

      while ((b = in.read()) != -1) {
        output.append((char) b);
      }
    } catch (IOException io) {
      throw new IllegalArgumentException("Invalid url");
    }
    return output;
  }

}
