package cs3500.solored;

import java.io.InputStreamReader;

import cs3500.solored.controller.SoloRedTextController;
import cs3500.solored.model.hw02.ConcreteCard;
import cs3500.solored.model.hw02.RedGameModel;
import cs3500.solored.model.hw04.RedGameCreator;

/**
 * This class is the entry point for the Solo Red card game.
 * It initializes the game based on user-specified parameters and starts the game.
 */

public final class SoloRed {

  /**
   * The main method to run the Solo Red game.
   *
   * @param args Command-line arguments for game type, number of palettes, and hand size.
   *             Expected arguments:
   *             - args[0]: Type of game ("basic" or "advanced").
   *             - args[1]: Number of palettes (positive integer, default is 4).
   *             - args[2]: Size of hand (positive integer, default is 7).
   *
   * @throws IllegalArgumentException if the game type is invalid or if palettes and hand sizes
   *       are not valid.
   */

  public static void main(String[] args) {

    RedGameModel<ConcreteCard> game = null;


    if ((args[0].equals("basic"))) {
      game = RedGameCreator.createGame(RedGameCreator.GameType.BASIC);
    } else if ((args[0].equals("advanced"))) {
      game = RedGameCreator.createGame(RedGameCreator.GameType.ADVANCED);
    }
    if (args.length == 0 || !(args[0].equals("basic") || args[0].equals("advanced"))) {
      throw new IllegalArgumentException("Invalid game type specified. Use 'basic' or 'advanced'.");
    }

    int numPalettes = 4;
    int handSize = 7;

    try {
      if (args.length > 1) {
        numPalettes = Integer.parseInt(args[1]);
        if (numPalettes < 2) {
          numPalettes = 4; // default value
        }
      }
      if (args.length > 2) {
        handSize = Integer.parseInt(args[2]);
        if (handSize <= 0) {
          handSize = 7; // default value
        }
      }
    } catch (NumberFormatException e) {
      // If parsing fails, default values will be used.
    }

    try {
      new SoloRedTextController(new InputStreamReader(System.in), System.out).playGame(game,
              game.getAllCards(), true, numPalettes, handSize);
    } catch (Exception e) {
      // nothing 
    }
  }
}