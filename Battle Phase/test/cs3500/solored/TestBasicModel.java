package cs3500.solored;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import cs3500.solored.model.hw02.ConcreteCard;
import cs3500.solored.model.hw02.SoloRedGameModel;
import cs3500.solored.view.hw02.SoloRedGameTextView;

import static org.junit.Assert.fail;


/**
 * An abstract test class for the basic functionality of the Solo Red game model.
 * This class contains a series of unit tests that validate the core functionalities
 * of the Solo Red game, including card retrieval, game state management,
 * and error handling for invalid operations.
 */

public abstract class TestBasicModel {

  /**
   * Tests the retrieval of all cards to ensure that the
   * correct number of cards is returned and that all expected cards are included in the list.
   */

  @Test
  public void getAllCards() {
    List<ConcreteCard> allCards = new ArrayList<>();
    allCards.add(new ConcreteCard('R', 1));
    allCards.add(new ConcreteCard('R', 2));
    allCards.add(new ConcreteCard('R', 3));
    allCards.add(new ConcreteCard('R', 4));
    allCards.add(new ConcreteCard('R', 5));
    allCards.add(new ConcreteCard('R', 6));
    allCards.add(new ConcreteCard('R', 7));
    allCards.add(new ConcreteCard('O', 1));
    allCards.add(new ConcreteCard('O', 2));
    allCards.add(new ConcreteCard('O', 3));
    allCards.add(new ConcreteCard('O', 4));
    allCards.add(new ConcreteCard('O', 5));
    allCards.add(new ConcreteCard('O', 6));
    allCards.add(new ConcreteCard('O', 7));
    allCards.add(new ConcreteCard('B', 1));
    allCards.add(new ConcreteCard('B', 2));
    allCards.add(new ConcreteCard('B', 3));
    allCards.add(new ConcreteCard('B', 4));
    allCards.add(new ConcreteCard('B', 5));
    allCards.add(new ConcreteCard('B', 6));
    allCards.add(new ConcreteCard('B', 7));
    allCards.add(new ConcreteCard('I', 1));
    allCards.add(new ConcreteCard('I', 2));
    allCards.add(new ConcreteCard('I', 3));
    allCards.add(new ConcreteCard('I', 4));
    allCards.add(new ConcreteCard('I', 5));
    allCards.add(new ConcreteCard('I', 6));
    allCards.add(new ConcreteCard('I', 7));
    allCards.add(new ConcreteCard('V', 1));
    allCards.add(new ConcreteCard('V', 2));
    allCards.add(new ConcreteCard('V', 3));
    allCards.add(new ConcreteCard('V', 4));
    allCards.add(new ConcreteCard('V', 5));
    allCards.add(new ConcreteCard('V', 6));
    allCards.add(new ConcreteCard('V', 7));
    SoloRedGameModel model = new SoloRedGameModel();
    Assert.assertEquals(35, model.getAllCards().size());

    // test that list has all expected cards
    for (char color : List.of('R', 'O', 'B', 'I', 'V')) {
      for (int i = 1; i <= 7; i++) {
        Assert.assertTrue(allCards.contains(new ConcreteCard(color, i)));
      }
    }
  }

  /**
   * Tests when the list of all cards is modified. It verifies that the model still
   * returns the correct number and type of cards,
   * regardless of modifications to the list.
   */

  @Test
  public void testModifiedListForAllCards() {
    SoloRedGameModel model = new SoloRedGameModel();
    List<ConcreteCard> allCards = model.getAllCards();

    allCards.clear();

    List<ConcreteCard> newAllCards = model.getAllCards();
    Assert.assertEquals(35, newAllCards.size());

    for (char color : List.of('R', 'O', 'B', 'I', 'V')) {
      for (int i = 1; i <= 7; i++) {
        Assert.assertTrue(newAllCards.contains(new ConcreteCard(color, i)));
      }
    }
  }

  /**
   * Ensures that multiple calls to getAllCards return the cards in a consistent order.
   * This helps verify that the card retrieval process is stable and does not change across calls.
   */

  @Test
  public void testSameOrderForAllCards() {
    SoloRedGameModel model = new SoloRedGameModel();
    List<ConcreteCard> allCards1 = model.getAllCards();
    List<ConcreteCard> allCards2 = model.getAllCards();
    Assert.assertEquals(allCards1, allCards2);
  }


  /**
   * Tests the retrieval of the canvas card after starting the game.
   * It verifies that the card retrieved matches the expected color and number.
   */

  @Test
  public void testReturnCorrectCard() {
    SoloRedGameModel model = new SoloRedGameModel();
    model.startGame(model.getAllCards(), false, 3, 7);
    ConcreteCard card = model.getCanvas();
    Assert.assertEquals('R', card.getColor());
    Assert.assertEquals(1, card.getNumber());
  }


  /**
   * Verifies that an IllegalStateException is thrown when trying to retrieve the canvas card
   * after the game has ended.
   */

  @Test(expected = IllegalStateException.class)
  public void testGetCanvasWhenGameOver() {
    SoloRedGameModel model = new SoloRedGameModel();
    while (model.numOfCardsInDeck() > 0) {
      model.drawForHand();
    }
    model.getCanvas();
  }

  /**
   * Tests the retrieval of the palette at an out-of-range index, expecting an
   * IllegalArgumentException to ensure that index bounds are enforced.
   */

  @Test(expected = IllegalArgumentException.class)
  public void testGetPaletteOutOfRange() {
    SoloRedGameModel model = new SoloRedGameModel();
    model.startGame(model.getAllCards(), false, 3, 7);
    model.getPalette(7);
  }

  /**
   * Verifies that an IllegalStateException is thrown when attempting to get the canvas card
   * before the game has started.
   */

  @Test(expected = IllegalStateException.class)
  public void testGetCanvasWhenGameNotStarted() {
    SoloRedGameModel model = new SoloRedGameModel();
    model.getCanvas();
  }


  /**
   * Ensures that an IllegalStateException is thrown when attempting to retrieve the palette
   * before the game has started.
   */

  @Test(expected = IllegalStateException.class)
  public void testGetPaletteGameNotStarted() {
    SoloRedGameModel model = new SoloRedGameModel();
    model.getPalette(0);
  }


  /**
   * Tests the retrieval of the palette at a negative index, expecting an IllegalArgumentException
   * to be thrown, thus validating that index bounds are enforced.
   */

  @Test(expected = IllegalArgumentException.class)
  public void testGetPaletteNegativeIndex() {
    SoloRedGameModel model = new SoloRedGameModel();
    model.startGame(model.getAllCards(), false, 3, 7);
    model.getPalette(-1);
  }


  /**
   * Tests the retrieval of the hand after starting the game, ensuring that the size of the hand
   * is correct and does not change when a card is removed directly from the list.
   */

  @Test
  public void testGetHandCorrect() {
    SoloRedGameModel model = new SoloRedGameModel();
    List<ConcreteCard> deck = new ArrayList<>();
    deck.add(new ConcreteCard('R', 1));
    deck.add(new ConcreteCard('O', 2));
    deck.add(new ConcreteCard('B', 3));
    deck.add(new ConcreteCard('R', 4));
    deck.add(new ConcreteCard('O', 5));
    deck.add(new ConcreteCard('B', 6));
    deck.add(new ConcreteCard('I', 1));
    deck.add(new ConcreteCard('V', 2));
    model.startGame(deck, false, 3, 5);
    List<ConcreteCard> hand = model.getHand();
    Assert.assertEquals(5, hand.size());
    hand.remove(0);
    Assert.assertEquals(5, model.getHand().size());
  }

  /**
   * Ensures that the contents of the hand include a specific card after starting the game,
   * thus validating the card distribution in the hand.
   */

  @Test
  public void testGetHandContents() {
    SoloRedGameModel model = new SoloRedGameModel();
    List<ConcreteCard> deck = new ArrayList<>();
    deck.add(new ConcreteCard('R', 1));
    deck.add(new ConcreteCard('O', 2));
    deck.add(new ConcreteCard('B', 3));
    deck.add(new ConcreteCard('R', 4));
    deck.add(new ConcreteCard('O', 5));
    deck.add(new ConcreteCard('B', 6));
    deck.add(new ConcreteCard('I', 1));
    deck.add(new ConcreteCard('V', 2));

    model.startGame(deck, false, 3, 5); // Start the game
    List<ConcreteCard> hand = model.getHand();
    Assert.assertTrue(hand.contains(new ConcreteCard('R', 1)));
  }

  /**
   * Tests the retrieval of the palette at a specific index, ensuring that the correct
   * card is present and its properties (color and number) match the expected values.
   */

  @Test
  public void testGetPaletteCorrect() {
    SoloRedGameModel model = new SoloRedGameModel();
    model.startGame(model.getAllCards(), false, 3, 7);
    SoloRedGameTextView view = new SoloRedGameTextView(model);
    System.out.println(view.toString());
    List<ConcreteCard> palette = model.getPalette(0);
    ConcreteCard card = palette.get(0);
    Assert.assertEquals('V', card.getColor());
    Assert.assertEquals(7, card.getNumber());
  }


  /**
   * Verifies that an IllegalStateException is thrown when attempting to retrieve the hand
   * before the game has started.
   */

  @Test(expected = IllegalStateException.class)
  public void testGetHandGameNotStarted() {
    List<ConcreteCard> deck = new ArrayList<>();
    deck.add(new ConcreteCard('R', 1));
    deck.add(new ConcreteCard('O', 2));
    deck.add(new ConcreteCard('B', 3));
    deck.add(new ConcreteCard('R', 4));
    deck.add(new ConcreteCard('O', 5));
    deck.add(new ConcreteCard('B', 6));
    deck.add(new ConcreteCard('I', 1));
    deck.add(new ConcreteCard('V', 2));
    SoloRedGameModel model = new SoloRedGameModel();
    model.playToCanvas(0);
    model.getHand();
  }

  /**
   * Tests the functionality of the game-over condition, ensuring that the model accurately
   * reflects whether the game has ended based on the current state of the game.
   */

  @Test
  public void testIsGameOver() {
    SoloRedGameModel model = new SoloRedGameModel();
    List<ConcreteCard> deck = new ArrayList<>();
    deck.add(new ConcreteCard('O', 7));
    deck.add(new ConcreteCard('B', 7));
    deck.add(new ConcreteCard('R', 4));
    deck.add(new ConcreteCard('O', 5));
    deck.add(new ConcreteCard('B', 6));
    deck.add(new ConcreteCard('V', 1));
    model.startGame(deck, false, 4, 1);

    SoloRedGameTextView view = new SoloRedGameTextView(model);
    System.out.println(view.toString());

    model.playToPalette(3, 0);
    System.out.println(view.toString());

    model.playToPalette(0, 0);

    // Assert that the game is over after the last move
    Assert.assertTrue("Game should be over after the last play", model.isGameOver());
  }

  /**
   * Tests the functionality of the game-won condition, ensuring that the model accurately
   * reflects whether the game has been won at the correct time during gameplay.
   */

  @Test
  public void testIsGameWon() {
    SoloRedGameModel model = new SoloRedGameModel();
    List<ConcreteCard> deck = new ArrayList<>();
    deck.add(new ConcreteCard('O', 7));
    deck.add(new ConcreteCard('B', 7));
    deck.add(new ConcreteCard('R', 4));
    deck.add(new ConcreteCard('O', 5));
    deck.add(new ConcreteCard('B', 6));
    deck.add(new ConcreteCard('V', 1));
    model.startGame(deck, false, 4, 1);

    SoloRedGameTextView view = new SoloRedGameTextView(model);
    System.out.println(view.toString());

    model.playToPalette(3, 0);
    System.out.println(view.toString());

    model.playToPalette(0, 0);

    // Assert that the game has been won after the last play
    Assert.assertTrue("Game should be won after the last play", model.isGameWon());
  }

  /**
   * Ensures that an IllegalStateException is thrown when attempting to play a card to the palette
   * before the game has started.
   */

  @Test(expected = IllegalStateException.class)
  public void testPlayToPaletteGameNotStarted() {
    SoloRedGameModel model = new SoloRedGameModel();
    model.playToPalette(0, 0);
  }

  /**
   * Tests the behavior of the model when attempting to play a card at an invalid index,
   * expecting an IllegalArgumentException to be thrown to ensure index validation.
   */

  @Test(expected = IllegalArgumentException.class)
  public void testPlayToInvalidCardIndex() {
    SoloRedGameModel model = new SoloRedGameModel();
    model.startGame(model.getAllCards(), false, 3, 7);
    model.playToPalette(-1, 0);
  }

  /**
   * Verifies that an IllegalArgumentException is thrown when trying to play a card to the palette
   * at an invalid index in the hand.
   */

  @Test(expected = IllegalArgumentException.class)
  public void testPlayToPaletteInvalidHandIndex() {
    SoloRedGameModel model = new SoloRedGameModel();
    model.startGame(model.getAllCards(), false, 3, 7);
    model.playToPalette(0, -1);
  }

  /**
   * Ensures that an IllegalStateException is thrown when attempting to play to a winning palette,
   * preventing invalid moves after winning conditions have been met.
   */

  @Test(expected = IllegalStateException.class)
  public void testPlayToPaletteWinningPalette() {
    SoloRedGameModel model = new SoloRedGameModel();
    model.startGame(model.getAllCards(), false, 3, 7);
    int winningPaletteIdx = model.winningPaletteIndex();
    model.playToPalette(winningPaletteIdx, 0);
  }

  /**
   * Ensures that an IllegalStateException is thrown when attempting to play after the
   * game has ended,thus validating that game state is properly managed.
   */

  @Test(expected = IllegalStateException.class)
  public void testAfterGameEnds() {
    SoloRedGameModel model = new SoloRedGameModel();
    model.startGame(model.getAllCards(), false, 3, 7);
    while (!model.isGameOver()) {
      model.playToPalette(0, 0);
    }
    model.playToPalette(0, 0);
  }

  /**
   * Ensures that an IllegalStateException is thrown when trying to start a new game
   * when a game is already in progress, thus preventing state conflicts.
   */

  @Test(expected = IllegalStateException.class)
  public void testStartGameWhenAlreadyStarted() {
    SoloRedGameModel model = new SoloRedGameModel();
    List<ConcreteCard> deck = new ArrayList<>();
    deck.add(new ConcreteCard('R', 1));
    deck.add(new ConcreteCard('O', 2));
    deck.add(new ConcreteCard('B', 3));
    deck.add(new ConcreteCard('R', 4));
    deck.add(new ConcreteCard('O', 5));
    deck.add(new ConcreteCard('B', 6));
    deck.add(new ConcreteCard('I', 1));
    deck.add(new ConcreteCard('V', 2));
    model.startGame(deck, false, 3, 5);
    model.startGame(deck, false, 3, 5);
  }

  /**
   * Tests the drawing of cards for the hand, verifying that no additional cards are drawn
   * when the hand is already full.
   */

  @Test
  public void testDrawForHand() {
    SoloRedGameModel gameModel = new SoloRedGameModel();
    List<ConcreteCard> deck = gameModel.getAllCards();

    gameModel.startGame(deck, false, 4, 7);
    int initialHandSize = gameModel.getHand().size();
    gameModel.drawForHand();
    Assert.assertEquals(initialHandSize, gameModel.getHand().size());
  }


  /**
   * Verifies that the palette is retrieved correctly at a valid index after starting the game,
   * ensuring that the palette contains the expected cards.
   */

  @Test
  public void testGetPaletteValidIndex() {
    SoloRedGameModel gameModel = new SoloRedGameModel();
    List<ConcreteCard> deck = gameModel.getAllCards();

    // Start the game with 4 players and 7 cards per hand
    gameModel.startGame(deck, false, 4, 7);

    // Get the palette for a valid index (e.g., palette 0)
    List<ConcreteCard> palette = gameModel.getPalette(0);

    // Verify that the palette is not null and is of the expected size
    Assert.assertFalse(palette.isEmpty());
  }


  /**
   * Tests the game-won condition to ensure that it does not falsely indicate a win
   * immediately after starting the game.
   */

  @Test
  public void testIsGameWonInProgress() {
    SoloRedGameModel gameModel = new SoloRedGameModel();
    List<ConcreteCard> deck = gameModel.getAllCards();

    // Start the game
    gameModel.startGame(deck, false, 4, 7);

    // Ensure that the game is not won immediately after starting
    Assert.assertFalse(gameModel.isGameWon());
  }

  /**
   * Ensures that an IllegalArgumentException is thrown when attempting to start a game
   * with a null deck, thus validating that the deck's integrity is enforced.
   */

  @Test
  public void testStartGameWithNullDeck() {
    SoloRedGameModel gameModel = new SoloRedGameModel();
    Exception exception = Assert.assertThrows(IllegalArgumentException.class, () -> {
      gameModel.startGame(null, false, 3, 5);
    });
    Assert.assertEquals("Deck can't be null, empty, or have non-unique cards",
            exception.getMessage());
  }

  /**
   * Ensures that an IllegalArgumentException is thrown when attempting to start a game
   * with a non-unique deck, thus validating that the deck's integrity is enforced.
   */

  @Test
  public void testStartGameWithNonUniqueDeck() {
    SoloRedGameModel gameModel = new SoloRedGameModel();
    List<ConcreteCard> deck = new ArrayList<>();

    // Add duplicate cards to the deck to create a non-unique scenario
    deck.add(new ConcreteCard('R', 1));
    deck.add(new ConcreteCard('O', 2));
    deck.add(new ConcreteCard('R', 1)); // Duplicate card

    try {
      gameModel.startGame(deck, false, 3, 5);
      fail("Expected IllegalArgumentException to be thrown");
    } catch (IllegalArgumentException e) {
      Assert.assertEquals("Deck can't be null, empty, or have non-unique cards",
              e.getMessage());
    }
  }


  /**
   * Ensures that an IllegalArgumentException is thrown when attempting to start a game
   * with an invalid hand size of 0, thus validating that the game's constraints are enforced.
   */

  @Test
  public void testStartGameWithInvalidHandSize() {
    SoloRedGameModel gameModel = new SoloRedGameModel();
    List<ConcreteCard> deck = gameModel.getAllCards();
    try {
      gameModel.startGame(deck, false, 3, 0);
      fail("Expected IllegalArgumentException to be thrown");
    } catch (IllegalArgumentException e) {
      Assert.assertEquals("Number of palettes can't be negative and handsize can't be " +
              "less than 0", e.getMessage());
    }
  }

  /**
   * Ensures that an IllegalArgumentException is thrown when attempting to start a game
   * with a negative number of palettes, thereby enforcing the game's requirements for
   * valid parameters.
   */

  @Test
  public void testStartGameWithNegativeNumPalettes() {
    SoloRedGameModel gameModel = new SoloRedGameModel();
    List<ConcreteCard> deck = gameModel.getAllCards();
    try {
      gameModel.startGame(deck, false, -1, 5);
      fail("Expected IllegalArgumentException to be thrown");
    } catch (IllegalArgumentException e) {
      Assert.assertEquals("Number of palettes can't be negative and handsize " +
              "can't be less than 0", e.getMessage());
    }
  }

  /**
   * Ensures that an IllegalStateException is thrown when attempting to retrieve the number
   * of palettes before the game has started, thus validating that the game's state management
   * is functioning correctly.
   */

  @Test(expected = IllegalStateException.class)
  public void testNumPalettesWhenGameHasNotStarted() {
    SoloRedGameModel model = new SoloRedGameModel();
    model.numPalettes();
  }

}
