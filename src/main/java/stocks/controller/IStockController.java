package stocks.controller;

/**
 * The following interface represents the tasks performed by the stock market. It represents the
 * controller of the system. It allows user to perform following operations : 1) Create Portfolio.
 * 2) View Portfolio. (displays total investment made in the portfolio and current valuation) 3) Add
 * stock to the portfolio. 4) View all stocks in a portfolio.
 */
public interface IStockController {

  void handleOperation();

}
