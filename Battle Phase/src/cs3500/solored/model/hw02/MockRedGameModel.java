package cs3500.solored.model.hw02;

import java.util.List;


/**
 * A mock implementation of the RedGameModel for testing purposes.
 * This class simulates the behavior of a RedGameModel without any actual game logic.
 */

public class MockRedGameModel extends SoloRedGameModel {
  private int numCardsInDeck = 10;

  /**
   * Constructs a MockRedGameModel with a predefined canvas card.
   */

  public MockRedGameModel() {
    Card canvasCard = new ConcreteCard('R', 1);
  }

  /**
   * Play the given card from the hand to the losing palette chosen.
   * The card is removed from the hand and placed at the far right
   * end of the palette.
   *
   * @param paletteIdx a 0-index number representing which palette to play to
   * @param cardIdxInHand a 0-index number representing the card to play from the hand
   * @throws IllegalStateException if the game has not started or the game is over
   * @throws IllegalArgumentException if paletteIdx < 0 or more than the number of palettes
   * @throws IllegalArgumentException if cardIdxInHand < 0
   *     or greater/equal to the number of cards in hand
   * @throws IllegalStateException if the palette referred to by paletteIdx is winning
   */

  @Override
  public void playToPalette(int paletteIdx, int cardIdxInHand) {
    // No operation needed for this test
  }

  /**
   * Play the given card from the hand to the canvas.
   * This changes the rules of the game for all palettes.
   * The method can only be called once per turn.
   *
   * @param cardIdxInHand a 0-index number representing the card to play from the hand
   * @throws IllegalStateException if the game has not started or the game is over
   * @throws IllegalArgumentException if cardIdxInHand < 0
   *     or greater/equal to the number of cards in hand
   * @throws IllegalStateException if this method was already called once in a given turn
   * @throws IllegalStateException if there is exactly one card in hand
   */

  @Override
  public void playToCanvas(int cardIdxInHand) {
    // No operation needed for this test
  }

  /**
   * Draws cards from the deck until the hand is full
   * OR until the deck is empty, whichever occurs first. Newly drawn cards
   * are added to the end of the hand (far-right conventionally).
   * SIDE-EFFECT: Allows the player to play to the canvas again.
   *
   * @throws IllegalStateException if the game has not started or the game is over
   */

  @Override
  public void drawForHand() {
    // No operation needed for this test
  }


  /**
   * Returns the number of cards remaining in the deck used in the game.
   * @return the number of cards in the deck
   * @throws IllegalStateException if the game has not started
   */

  @Override
  public int numOfCardsInDeck() {
    return numCardsInDeck;
  }

  /**
   * Returns the number of palettes in the running game.
   * @return the number of palettes in the game
   * @throws IllegalStateException if the game has not started
   */

  @Override
  public int numPalettes() {
    return 4;
  }

  /**
   * Returns the index of the winning palette in the game.
   * @return the 0-based index of the winning palette
   * @throws IllegalStateException if the game has not started
   */

  @Override
  public int winningPaletteIndex() {
    return 0;
  }

  /**
   * Returns if the game is over as specified by the implementation.
   * @return true if the game has ended and false otherwise
   * @throws IllegalStateException if the game has not started
   */

  @Override
  public boolean isGameOver() {
    return false;
  }

  /**
   * Returns if the game is won by the player as specified by the implementation.
   * @return true if the game has been won or false if the game has not
   * @throws IllegalStateException if the game has not started or the game is not over
   */

  @Override
  public boolean isGameWon() {
    return false;
  }

  /**
   * Returns a copy of the hand in the game. This means modifying the returned list
   * or the cards in the list has no effect on the game.
   * @return a new list containing the cards in the player's hand in the same order
   *     as in the current state of the game.
   * @throws IllegalStateException if the game has not started
   */

  @Override
  public List<ConcreteCard> getHand() {
    return super.getHand();
  }

  /**
   * Returns a copy of the specified palette. This means modifying the returned list
   * or the cards in the list has no effect on the game.
   * @param paletteNum 0-based index of a particular palette
   * @return a new list containing the cards in specified palette in the same order
   *     as in the current state of the game.
   * @throws IllegalStateException if the game has not started
   * @throws IllegalArgumentException if paletteIdx < 0 or more than the number of palettes
   */

  @Override
  public List<ConcreteCard> getPalette(int paletteNum) {
    return super.getPalette(paletteNum);
  }

  /**
   * Return the top card of the canvas.
   * Modifying this card has no effect on the game.
   * @return the top card of the canvas
   * @throws IllegalStateException if the game has not started or the game is over
   */

  @Override
  public ConcreteCard getCanvas() {
    return super.getCanvas();
  }

  /**
   * Get a NEW list of all cards that can be used to play the game.
   * Editing this list should have no effect on the game itself.
   * Repeated calls to this method should produce a list of cards in the same order.
   * Modifying the cards in this list should have no effect on any returned list
   * or the game itself.
   * @return a new list of all possible cards that can be used for the game
   */

  @Override
  public List<ConcreteCard> getAllCards() {
    return super.getAllCards();
  }
}