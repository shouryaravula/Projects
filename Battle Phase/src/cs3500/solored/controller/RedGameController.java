package cs3500.solored.controller;

import cs3500.solored.model.hw02.RedGameModel;
import cs3500.solored.model.hw02.Card;

import java.util.List;

/**
 * Interface for the controller of the Solo Red game.
 */
public interface RedGameController {

  /**
   * Plays a new game of Solo Red using the provided model.
   *
   * @param model       the RedGameModel to be used for the game
   * @param deck        the deck of cards to be used in the game
   * @param shuffle     whether to shuffle the deck before starting the game
   * @param numPalettes the number of palettes to use in the game
   * @param handSize    the size of the hand for the game
   * @throws IllegalArgumentException if the model is null, or if the game cannot be started
   * @throws IllegalStateException    if the controller is unable to successfully receive input or
   *                                  transmit output
   */
  <C extends Card> void playGame(RedGameModel<C> model, List<C> deck, boolean shuffle,
                                 int numPalettes, int handSize);
}
