package cs3500.solored.model.hw04;

import cs3500.solored.model.hw02.ConcreteCard;
import cs3500.solored.model.hw02.RedGameModel;
import cs3500.solored.model.hw02.SoloRedGameModel;


/**
 * A factory class for creating instances of the Solo Red game.
 * This class provides a method to create either a basic or advanced
 * version of the Solo Red game model based on the specified game type.
 */

public class RedGameCreator {

  /**
   * Enum representing the different types of Solo Red games that can be created.
   */

  public enum GameType {
    BASIC, ADVANCED
  }

  /**
   * Creates a new instance of the Solo Red game model based on the specified game type.
   *
   * @param gameType the type of game to create, either BASIC or ADVANCED
   * @return a RedGameModel instance corresponding to the specified game type
   * @throws IllegalArgumentException if an invalid GameType is specified
   */

  public static RedGameModel<ConcreteCard> createGame(GameType gameType) {
    if (gameType == GameType.BASIC) {
      return new SoloRedGameModel();
    } else if (gameType == GameType.ADVANCED) {
      return new AdvancedSoloRedGameModel();
    } else {
      throw new IllegalArgumentException("Invalid GameType specified.");
    }
  }

}
