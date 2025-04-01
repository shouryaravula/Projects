package cs3500.solored.model.hw04;


import java.util.Random;

import cs3500.solored.model.hw02.SoloRedGameModel;

/**
 * Represents the model for the Advanced Solo Red Game.
 * This class implements the game logic with advanced rules.
 */

public class AdvancedSoloRedGameModel extends SoloRedGameModel {

  public AdvancedSoloRedGameModel() {
    super();
  }

  public AdvancedSoloRedGameModel(Random random) {
    super(random);
  }

  /**
   * Play the given card from the hand to the losing palette chosen.
   * The card is removed from the hand and placed at the far right
   * end of the palette.
   *
   * @param paletteIdx    a 0-index number representing which palette to play to
   * @param cardIdxInHand a 0-index number representing the card to play from the hand
   * @throws IllegalStateException    if the game has not started or the game is over
   * @throws IllegalArgumentException if paletteIdx < 0 or more than the number of palettes
   * @throws IllegalArgumentException if cardIdxInHand < 0
   *                                  or greater/equal to the number of cards in hand
   * @throws IllegalStateException    if the palette referred to by paletteIdx is winning
   */

  @Override
  public void playToPalette(int paletteIdx, int cardIdxInHand) {
    super.playToPalette(paletteIdx, cardIdxInHand);

    // Draw one card after playing to the palette
    drawOneCard();

  }

  /**
   * Play the given card from the hand to the canvas.
   * This changes the rules of the game for all palettes.
   * The method can only be called once per turn.
   *
   * @param cardIdxInHand a 0-index number representing the card to play from the hand
   * @throws IllegalStateException    if the game has not started or the game is over
   * @throws IllegalArgumentException if cardIdxInHand < 0
   *                                  or greater/equal to the number of cards in hand
   * @throws IllegalStateException    if this method was already called once in a given turn
   * @throws IllegalStateException    if there is exactly one card in hand
   */

  @Override
  public void playToCanvas(int cardIdxInHand) {
    super.playToCanvas(cardIdxInHand);


    if (currentCardCanvas.getNumber() > palettes.get(winningPalette).size()) {
      drawTwoCards();
    } else {
      drawOneCard();
    }
  }

  /**
   * Draws one card from the deck to the hand, if the deck is not empty
   * and the hand has not reached its maximum size.
   */

  private void drawOneCard() {
    if (!deck.isEmpty() && this.hand.size() < this.handSize) {
      hand.add(deck.remove(0));
    }
  }

  /**
   * Draws two cards from the deck to the hand, if the deck is not empty
   * and the hand has not reached its maximum size.
   */

  private void drawTwoCards() {
    for (int i = 0; i < 2; i++) {
      if (!deck.isEmpty() && this.hand.size() < this.handSize) {
        hand.add(deck.remove(0));
      }
    }
  }

}
