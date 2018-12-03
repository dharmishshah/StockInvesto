package stocks;

import java.io.InputStreamReader;

import stocks.controller.StockController;

/**
 * The following class checks proper execution of the controller.
 */
public class StockDemo {
  /**
   * The following class checks proper execution of the controller.
   */
  public static void main(String[] args) {
    new StockController(new InputStreamReader(System.in), System.out).handleOperation();
  }
}
