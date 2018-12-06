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

    if(args.length > 0 ){

      if(args[0].equalsIgnoreCase("gui")){
        new ApplicationMain().main(args);
      }else if(args[0].equalsIgnoreCase("text")){
        new StockController(new InputStreamReader(System.in), System.out).handleOperation();
      }else{
        System.out.println("Invalid input.Please pass gui or text ");
      }
    }

    new ApplicationMain().main(args);


  }
}
