package stocks;

import java.io.InputStreamReader;

import stocks.controller.StockController;
import stocks.view.ApplicationMain;

/**
 * The following class checks proper execution of the controller.
 */
public class StockDemo {
  /**
   * The following class checks proper execution of the controller.
   */
  public static void main(String[] args) {

    if (args.length == 2) {

      if (args[0].equalsIgnoreCase("-view") && args[1].equalsIgnoreCase("gui")) {
        new ApplicationMain().main(args);
      } else if (args[0].equalsIgnoreCase("-view") && args[1].equalsIgnoreCase("text")) {
        new StockController(new InputStreamReader(System.in), System.out).handleOperation();
      } else {
        System.out.println("Invalid input.Please pass -view gui or -view text ");
      }
    }
    new StockController(new InputStreamReader(System.in), System.out).handleOperation();
  }
}
