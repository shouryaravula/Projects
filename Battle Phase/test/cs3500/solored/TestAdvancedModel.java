package cs3500.solored;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import cs3500.solored.model.hw02.ConcreteCard;
import cs3500.solored.model.hw04.AdvancedSoloRedGameModel;

/**
 * Test class for the public methods. Tests methods in AdvancedSoloRedGameModel class.
 */

public class TestAdvancedModel extends TestBasicModel {


  /**
   * Tests that drawing a card after playing to a palette works correctly.
   */
  @Test
  public void testPlayToPaletteDrawsCard() {
    AdvancedSoloRedGameModel model = new AdvancedSoloRedGameModel();
    List<ConcreteCard> deck = new ArrayList<>();

    // Add enough cards to the deck
    deck.add(new ConcreteCard('R', 1));
    deck.add(new ConcreteCard('R', 2));
    deck.add(new ConcreteCard('O', 1));
    deck.add(new ConcreteCard('B', 1));
    deck.add(new ConcreteCard('I', 1));
    deck.add(new ConcreteCard('V', 1));

    model.startGame(deck, false, 2, 2);

    int initialHandSize = model.getHand().size();
    model.playToPalette(0, 0);
    Assert.assertEquals(initialHandSize, model.getHand().size());
  }


  /**
   * Tests that an IllegalArgumentException is thrown when trying to play when the game is over.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testPlayAfterGameOver() {
    AdvancedSoloRedGameModel model = new AdvancedSoloRedGameModel();
    List<ConcreteCard> deck = new ArrayList<>();

    deck.add(new ConcreteCard('R', 1));
    deck.add(new ConcreteCard('R', 2));

    model.startGame(deck, false, 2, 2);

    model.playToPalette(0, 0);
    model.playToPalette(0, 1);
    model.playToPalette(0, 0); // trying to play when game over
  }

  /**
   * Tests that the player does not draw any cards if the deck is empty after playing to the
   * palette.
   */
  @Test
  public void testNoDrawWhenDeckIsEmpty() {
    AdvancedSoloRedGameModel model = new AdvancedSoloRedGameModel();
    List<ConcreteCard> deck = new ArrayList<>();

    // Create a deck with limited cards
    deck.add(new ConcreteCard('R', 1));
    deck.add(new ConcreteCard('R', 2));
    deck.add(new ConcreteCard('O', 1));
    deck.add(new ConcreteCard('B', 1));
    deck.add(new ConcreteCard('I', 1));

    model.startGame(deck, false, 4, 7);

    for (int i = 0; i < 5; i++) {
      model.playToPalette(0, 0); // Play cards until the deck is empty
    }

    int initialHandSize = model.getHand().size();
    model.playToPalette(0, 0);
    Assert.assertEquals(initialHandSize, model.getHand().size());
  }

  /**
   * Tests that a player draws two cards when playing to the canvas with a higher number than
   * the palette.
   */
  @Test
  public void testTwoCardDrawAfterCanvasPlay() {
    AdvancedSoloRedGameModel model = new AdvancedSoloRedGameModel();
    List<ConcreteCard> deck = new ArrayList<>();

    // Create a deck
    deck.add(new ConcreteCard('R', 1));
    deck.add(new ConcreteCard('R', 2));
    deck.add(new ConcreteCard('O', 1));
    deck.add(new ConcreteCard('B', 1));
    deck.add(new ConcreteCard('I', 1));
    deck.add(new ConcreteCard('V', 1));

    model.startGame(deck, false, 2, 2);

    model.playToPalette(1, 2);

    model.playToCanvas(1);

    // Draw two cards after playing to the palette
    model.playToPalette(0, 1);
    Assert.assertEquals(3, model.getHand().size());
  }

  /**
   * Tests that the game initializes correctly with default values for palettes and hand size.
   */
  @Test
  public void testStartGameWithDefaultValues() {
    AdvancedSoloRedGameModel model = new AdvancedSoloRedGameModel();

    // Create a deck with enough unique cards
    List<ConcreteCard> defaultDeck = new ArrayList<>();
    defaultDeck.add(new ConcreteCard('R', 1));
    defaultDeck.add(new ConcreteCard('R', 2));
    defaultDeck.add(new ConcreteCard('O', 1));
    defaultDeck.add(new ConcreteCard('B', 1));
    defaultDeck.add(new ConcreteCard('I', 1));
    defaultDeck.add(new ConcreteCard('V', 1));
    defaultDeck.add(new ConcreteCard('O', 2));
    defaultDeck.add(new ConcreteCard('B', 2));
    defaultDeck.add(new ConcreteCard('I', 2));
    defaultDeck.add(new ConcreteCard('R', 3));
    defaultDeck.add(new ConcreteCard('O', 3));


    // Start the game with the default parameters
    model.startGame(defaultDeck, false, 4, 7);

    Assert.assertEquals("Initial hand size should be 7.", 7, model.getHand().
            size());
    Assert.assertFalse("The game should not be over at start.", model.isGameOver());
    Assert.assertFalse("The game should not be won at start.", model.isGameWon());
  }

  /**
   * Tests that only one card is drawn after playing to the canvas when the canvas card is not
   * greater than the winning palette.
   */
  @Test
  public void testDrawOneCardAfterPlayToCanvasWhenNotGreater() {
    AdvancedSoloRedGameModel model = new AdvancedSoloRedGameModel();
    List<ConcreteCard> deck = new ArrayList<>();

    deck.add(new ConcreteCard('R', 1));
    deck.add(new ConcreteCard('O', 1));

    model.startGame(deck, false, 2, 2);
    model.playToPalette(0, 0);

    int initialHandSize = model.getHand().size();
    model.playToCanvas(0);

    Assert.assertEquals(initialHandSize + 1, model.getHand().size());
  }

  /**
   * Tests that a player does not draw two cards after playing to the canvas if the number is not
   * greater than the winning palette.
   */

  @Test
  public void testNoTwoCardDrawIfCanvasNumberNotGreater() {
    AdvancedSoloRedGameModel model = new AdvancedSoloRedGameModel();
    List<ConcreteCard> deck = new ArrayList<>();

    // Create a deck
    deck.add(new ConcreteCard('R', 1));
    deck.add(new ConcreteCard('R', 2));
    deck.add(new ConcreteCard('O', 1));
    deck.add(new ConcreteCard('B', 1));
    deck.add(new ConcreteCard('I', 1));
    deck.add(new ConcreteCard('V', 1));

    model.startGame(deck, false, 2, 2);

    model.playToPalette(0, 0); // Play 1

    int initialHandSize = model.getHand().size();
    model.playToCanvas(0);

    model.playToPalette(1, 1);

    Assert.assertEquals(initialHandSize + 1, model.getHand().size());
  }

  /**
   * Tests that the draw count resets to one after drawing two cards.
   */

  @Test
  public void testDrawCountResetsToOneAfterDrawingTwoCards() {
    AdvancedSoloRedGameModel model = new AdvancedSoloRedGameModel();
    List<ConcreteCard> deck = new ArrayList<>();

    deck.add(new ConcreteCard('R', 1));
    deck.add(new ConcreteCard('O', 1));
    deck.add(new ConcreteCard('G', 1));
    deck.add(new ConcreteCard('B', 1));

    model.startGame(deck, false, 2, 2);

    model.playToPalette(0, 0);

    model.playToCanvas(2);

    int handSizeAfterTwoDraws = model.getHand().size();

    model.playToPalette(0, 1);

    int handSizeAfterOneDraw = model.getHand().size();
    Assert.assertEquals(handSizeAfterTwoDraws + 1, handSizeAfterOneDraw);
  }


}
