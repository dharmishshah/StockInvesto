package stocks.model.strategy;

/**
 * This interface defines a generic strategy which executes a strategy and buys stocks accordingly.
 */
public interface StrategyOperations {

  /**
   * The following method executes a new strategy based on user data.
   *
   * @param strategy strategy object given by user the start date of the strategy.
   */
  public void executeStrategy(Strategy strategy);
}
