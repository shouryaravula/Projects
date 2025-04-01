package cs3500.solored.controller;

import org.junit.Test;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import cs3500.solored.model.hw02.ConcreteCard;
import cs3500.solored.model.hw02.MockRedGameModel;
import cs3500.solored.model.hw02.SoloRedGameModel;
import cs3500.solored.view.hw02.SoloRedGameTextView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Tests for the SoloRedTextController class.
 */

public class TestController {

  /**
   * Tests the constructor of SoloRedTextController with a null Appendable.
   * Expects an IllegalArgumentException to be thrown.
   */

  @Test
  public void testControllerWithNullAppendable() {
    try {
      SoloRedTextController controller = new SoloRedTextController(new StringReader("input"), null);
      fail("Expected IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      // Test passes
    }
  }

  /**
   * Tests the constructor of SoloRedTextController with a null Readable.
   * Expects an IllegalArgumentException to be thrown.
   */

  @Test
  public void testControllerWithNullReadable() {
    try {
      SoloRedTextController controller = new SoloRedTextController(null, new StringWriter());
      fail("Expected IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      // Test passes
    }
  }

  /**
   * Tests the SoloRedGameTextView constructor with a null Appendable.
   * Expects an IllegalArgumentException to be thrown.
   */

  @Test
  public void testViewWithNullAppendable() {
    try {
      MockRedGameModel mockModel = new MockRedGameModel();
      SoloRedGameTextView view = new SoloRedGameTextView(mockModel, null);
      fail("Expected IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      // Test passes
    }
  }

  /**
   * Tests the playGame method of SoloRedTextController with null arguments.
   * Expects IllegalArgumentException for null model or deck,
   * and verifies the specific error messages.
   */

  @Test
  public void testControllerWithNullArgs() {
    StringWriter output = new StringWriter();
    SoloRedTextController controller = new SoloRedTextController(new StringReader("input"),
            output);

    try {
      controller.playGame(null, null, false, 0, 0);
      fail("Expected IllegalArgumentException due to null model or deck");
    } catch (IllegalArgumentException e) {
      // Test passes
      assertEquals("Model cannot be null.", e.getMessage());
    }

    try {
      MockRedGameModel mockModel = new MockRedGameModel();
      controller.playGame(mockModel, null, false, 0, 0);
      fail("Expected IllegalArgumentException due to null deck");
    } catch (IllegalArgumentException e) {
      // Test passes
      assertEquals("Deck cannot be null.", e.getMessage());
    }
  }

  /**
   * Tests quitting the game mid-command.
   * Expects the correct output when quitting is initiated.
   */

  @Test
  public void testQuittingMidCommand() {
    StringWriter output = new StringWriter();
    String input = "palette 1 1\nq";
    SoloRedTextController controller = new SoloRedTextController(new StringReader(input), output);

    MockRedGameModel mockModel = new MockRedGameModel();

    try {
      controller.playGame(mockModel, new ArrayList<>(), false, 3, 3);
    } catch (Exception e) {
      fail("Expected no exception, but got: " + e.getMessage());
    }

    // Expected output when quitting mid-command
    String expectedOutput = "Invalid entry. Try again.\n" +
            "Game quit! State of the game when quit:\n" +
            "Number of cards in deck: 10\n";

    assertEquals(expectedOutput, output.toString());
  }

  /**
   * Tests the playGame method of SoloRedTextController with a null model.
   * Expects an IllegalArgumentException to be thrown.
   */

  @Test
  public void testControllerWithNullModel() {
    StringWriter output = new StringWriter();
    SoloRedTextController controller = new SoloRedTextController(new StringReader("input"),
            output);

    try {
      controller.playGame(null, new ArrayList<>(), false, 0, 0);
      fail("Expected IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      // Test passes
    }
  }

  /**
   * Tests the game quitting functionality.
   * Expects the correct output when quitting the game.
   */

  @Test
  public void testGameQuit() {
    StringWriter output = new StringWriter();
    SoloRedTextController controller = new SoloRedTextController(new StringReader("q"), output);

    MockRedGameModel mockModel = new MockRedGameModel();

    controller.playGame(mockModel, new ArrayList<>(), false, 0, 0);

    String expectedOutput = "Game quit! State of the game when quit:\nNumber of cards in deck: " +
            "10\n";
    assertEquals(expectedOutput, output.toString());
  }


  /**
   * Tests starting the game with a valid deck and parameters.
   */

  @Test
  public void testStartGame() {
    StringWriter output = new StringWriter();
    String input = "start\n";
    SoloRedGameModel model = new SoloRedGameModel();
    SoloRedTextController controller = new SoloRedTextController(new StringReader(input), output);

    // Manually create a deck of cards
    List<ConcreteCard> deck = new ArrayList<>();
    char[] colors = {'R', 'O', 'B', 'I', 'V'};
    for (char color : colors) {
      for (int number = 1; number <= 7; number++) {
        deck.add(new ConcreteCard(color, number));
      }
    }

    try {
      controller.playGame(model, deck, false, 3, 5);
    } catch (Exception e) {
      fail("Expected no exception, but got: " + e.getMessage());
    }

    String expectedOutput = "Game started! State of the game:\nNumber of cards in deck: " +
            (deck.size() - 3) + "\n";
    assertEquals(expectedOutput, output.toString());
  }

  /**
   * Tests playing a card to a palette.
   */

  @Test
  public void testPlayCardToPalette() {
    StringWriter output = new StringWriter();
    String input = "palette 0 0\n"; // Play the first card to the first palette
    SoloRedGameModel model = new SoloRedGameModel();
    SoloRedTextController controller = new SoloRedTextController(new StringReader(input), output);

    // creates deck of cards
    List<ConcreteCard> deck = new ArrayList<>();
    char[] colors = {'R', 'O', 'B', 'I', 'V'};
    for (char color : colors) {
      for (int number = 1; number <= 7; number++) {
        deck.add(new ConcreteCard(color, number));
      }
    }

    // Start the game with the deck
    try {
      controller.playGame(model, deck, false, 3, 5);
    } catch (Exception e) {
      fail("Expected no exception, but got: " + e.getMessage());
    }

    controller.playGame(model, deck, false, 3, 5);

    String expectedOutput = "Played card to palette 0.\n";
    assertEquals(expectedOutput, output.toString());
  }

  /**
   * Tests playing a card to the canvas.
   */
  @Test
  public void testPlayCardToCanvas() {
    StringWriter output = new StringWriter();
    String input = "canvas 0\n"; // Play the first card to the canvas
    SoloRedGameModel model = new SoloRedGameModel();
    SoloRedTextController controller = new SoloRedTextController(new StringReader(input), output);

    // Manually create a deck of cards
    List<ConcreteCard> deck = new ArrayList<>();
    char[] colors = {'R', 'O', 'B', 'I', 'V'};
    for (char color : colors) {
      for (int number = 1; number <= 7; number++) {
        deck.add(new ConcreteCard(color, number));
      }
    }

    // Start the game with the deck
    try {
      controller.playGame(model, deck, false, 3, 5);
    } catch (Exception e) {
      fail("Expected no exception, but got: " + e.getMessage());
    }

    // Now play a card to the canvas
    controller.playGame(model, deck, false, 3, 5);

    String expectedOutput = "Played card to canvas.\n";
    assertEquals(expectedOutput, output.toString());
  }

  /**
   * Tests handling of invalid commands.
   */

  @Test
  public void testInvalidCommand() {
    StringWriter output = new StringWriter();
    String input = "invalid_command\nq"; // Invalid command followed by quit
    SoloRedGameModel model = new SoloRedGameModel();
    SoloRedTextController controller = new SoloRedTextController(new StringReader(input), output);

    // Manually create a deck of cards
    List<ConcreteCard> deck = new ArrayList<>();
    char[] colors = {'R', 'O', 'B', 'I', 'V'};
    for (char color : colors) {
      for (int number = 1; number <= 7; number++) {
        deck.add(new ConcreteCard(color, number));
      }
    }

    controller.playGame(model, deck, false, 3, 5);

    String expectedOutput = "Invalid command. Try again.\n" +
            "Game quit! State of the game when quit:\n" +
            "Number of cards in deck: " + (deck.size() - 3) + "\n";
    assertEquals(expectedOutput, output.toString());
  }

  /**
   * Tests for invalid palette indices.
   */

  @Test
  public void testInvalidPaletteIndex() {
    StringWriter output = new StringWriter();
    String input = "palette 10 0\nq"; // Invalid palette index
    SoloRedGameModel model = new SoloRedGameModel();
    SoloRedTextController controller = new SoloRedTextController(new StringReader(input), output);

    List<ConcreteCard> deck = new ArrayList<>();
    char[] colors = {'R', 'O', 'B', 'I', 'V'};
    for (char color : colors) {
      for (int number = 1; number <= 7; number++) {
        deck.add(new ConcreteCard(color, number));
      }
    }

    controller.playGame(model, deck, false, 3, 5);

    String expectedOutput = "Invalid palette index. Try again.\n" +
            "Game quit! State of the game when quit:\n" +
            "Number of cards in deck: " + (deck.size() - 3) + "\n";
    assertEquals(expectedOutput, output.toString());
  }

  /**
   * Tests for invalid canvas indices.
   */

  @Test
  public void testInvalidCanvasIndex() {
    StringWriter output = new StringWriter();
    String input = "canvas -1\nq"; // Invalid canvas index
    SoloRedGameModel model = new SoloRedGameModel();
    SoloRedTextController controller = new SoloRedTextController(new StringReader(input), output);

    List<ConcreteCard> deck = new ArrayList<>();
    char[] colors = {'R', 'O', 'B', 'I', 'V'};
    for (char color : colors) {
      for (int number = 1; number <= 7; number++) {
        deck.add(new ConcreteCard(color, number));
      }
    }

    controller.playGame(model, deck, false, 3, 5);

    String expectedOutput = "Invalid canvas index. Try again.\n" +
            "Game quit! State of the game when quit:\n" +
            "Number of cards in deck: " + (deck.size() - 3) + "\n";
    assertEquals(expectedOutput, output.toString());
  }

  /**
   * Tests handling of invalid number of parameters.
   */

  @Test
  public void testInvalidNumberOfParameters() {
    StringWriter output = new StringWriter();
    String input = "palette\nq"; // Missing parameters
    SoloRedGameModel model = new SoloRedGameModel();
    SoloRedTextController controller = new SoloRedTextController(new StringReader(input), output);

    List<ConcreteCard> deck = new ArrayList<>();
    char[] colors = {'R', 'O', 'B', 'I', 'V'};
    for (char color : colors) {
      for (int number = 1; number <= 7; number++) {
        deck.add(new ConcreteCard(color, number));
      }
    }

    controller.playGame(model, deck, false, 3, 5);

    String expectedOutput = "Invalid entry. Try again.\n" +
            "Game quit! State of the game when quit:\n" +
            "Number of cards in deck: " + (deck.size() - 3) + "\n";
    assertEquals(expectedOutput, output.toString());
  }

  /**
   * Tests for attempting to play a card when the deck is empty.
   */

  @Test
  public void testPlayCardWithEmptyDeck() {
    StringWriter output = new StringWriter();
    String input = "palette 0 0\nq"; // Attempting to play a card
    SoloRedGameModel model = new SoloRedGameModel();
    SoloRedTextController controller = new SoloRedTextController(new StringReader(input), output);

    // Empty deck
    List<ConcreteCard> deck = new ArrayList<>();

    controller.playGame(model, deck, false, 3, 5);

    String expectedOutput = "No cards left in the deck. Cannot play a card.\n" +
            "Game quit! State of the game when quit:\n" +
            "Number of cards in deck: 0\n";
    assertEquals(expectedOutput, output.toString());
  }

  /**
   * Tests for invalid parameters when starting the game.
   */

  @Test
  public void testStartGameWithInvalidParameters() {
    StringWriter output = new StringWriter();
    String input = "start -1\n"; // Invalid number of rows
    SoloRedGameModel model = new SoloRedGameModel();
    SoloRedTextController controller = new SoloRedTextController(new StringReader(input), output);

    // Manually create a deck of cards
    List<ConcreteCard> deck = new ArrayList<>();
    char[] colors = {'R', 'O', 'B', 'I', 'V'};
    for (char color : colors) {
      for (int number = 1; number <= 7; number++) {
        deck.add(new ConcreteCard(color, number));
      }
    }

    controller.playGame(model, deck, false, 3, 5);

    String expectedOutput = "Invalid parameters for starting the game. Try again.\n" +
            "Game state remains unchanged.\n"; // Adjust based on game logic
    assertEquals(expectedOutput, output.toString());
  }

  /**
   * Tests for invalid card placement (e.g., playing a card to a full palette).
   */

  @Test
  public void testPlayCardToFullPalette() {
    StringWriter output = new StringWriter();
    String input = "palette 0 0\nq"; // Attempt to play a card to a full palette
    SoloRedGameModel model = new SoloRedGameModel();
    SoloRedTextController controller = new SoloRedTextController(new StringReader(input), output);

    // Manually create a deck of cards
    List<ConcreteCard> deck = new ArrayList<>();
    char[] colors = {'R', 'O', 'B', 'I', 'V'};
    for (char color : colors) {
      for (int number = 1; number <= 7; number++) {
        deck.add(new ConcreteCard(color, number));
      }
    }

    // Simulate the game starting and filling the palette
    controller.playGame(model, deck, false, 3, 5);
    controller.playGame(model, deck, false, 3, 5);
    controller.playGame(model, deck, false, 3, 5);

    String expectedOutput = "Palette is full. Cannot play card.\n" +
            "Game quit! State of the game when quit:\n" +
            "Number of cards in deck: " + (deck.size() - 3) + "\n";
    assertEquals(expectedOutput, output.toString());
  }

  /**
   * Tests for attempting to play a card from an empty hand.
   */

  @Test
  public void testPlayCardWithEmptyHand() {
    StringWriter output = new StringWriter();
    String input = "canvas 0\nq"; // Attempt to play a card to canvas
    SoloRedGameModel model = new SoloRedGameModel();
    SoloRedTextController controller = new SoloRedTextController(new StringReader(input), output);

    // Manually create a deck of cards
    List<ConcreteCard> deck = new ArrayList<>();
    char[] colors = {'R', 'O', 'B', 'I', 'V'};
    for (char color : colors) {
      for (int number = 1; number <= 7; number++) {
        deck.add(new ConcreteCard(color, number));
      }
    }

    // Start the game with the deck
    controller.playGame(model, deck, false, 3, 5);


    controller.playGame(model, deck, false, 3, 5);

    String expectedOutput = "No cards in hand to play. Cannot play to canvas.\n" +
            "Game quit! State of the game when quit:\n" +
            "Number of cards in deck: " + (deck.size() - 3) + "\n";
    assertEquals(expectedOutput, output.toString());
  }

  /**
   * Tests playing a card to the canvas with index 0.
   */

  @Test
  public void testPlayCardToCanvasIndexZero() {
    StringWriter output = new StringWriter();
    String input = "canvas 0\nq"; // Play the first card to the canvas
    SoloRedGameModel model = new SoloRedGameModel();
    SoloRedTextController controller = new SoloRedTextController(new StringReader(input), output);

    // Manually create a deck of cards
    List<ConcreteCard> deck = new ArrayList<>();
    char[] colors = {'R', 'O', 'B', 'I', 'V'};
    for (char color : colors) {
      for (int number = 1; number <= 7; number++) {
        deck.add(new ConcreteCard(color, number));
      }
    }

    // Start the game with the deck
    try {
      controller.playGame(model, deck, false, 3, 5);
    } catch (Exception e) {
      fail("Expected no exception, but got: " + e.getMessage());
    }

    // Now play a card to the canvas
    try {
      controller.playGame(model, deck, false, 3, 5);
    } catch (Exception e) {
      fail("Expected no exception, but got: " + e.getMessage());
    }

    String expectedOutput = "Played card to canvas.\n";
    assertEquals(expectedOutput, output.toString());
  }

  /**
   * Tests for negative input handling when playing a card to the canvas.
   */

  @Test
  public void testPlayCardToCanvasNegativeIndex() {
    StringWriter output = new StringWriter();
    String input = "canvas -1\nq"; // Invalid canvas index
    SoloRedGameModel model = new SoloRedGameModel();
    SoloRedTextController controller = new SoloRedTextController(new StringReader(input), output);

    // Manually create a deck of cards
    List<ConcreteCard> deck = new ArrayList<>();
    char[] colors = {'R', 'O', 'B', 'I', 'V'};
    for (char color : colors) {
      for (int number = 1; number <= 7; number++) {
        deck.add(new ConcreteCard(color, number));
      }
    }

    controller.playGame(model, deck, false, 3, 5);

    String expectedOutput = "Invalid canvas index. Try again.\n" +
            "Game quit! State of the game when quit:\n" +
            "Number of cards in deck: " + (deck.size() - 3) + "\n";
    assertEquals(expectedOutput, output.toString());
  }

  /**
   * Tests for negative palette index handling.
   */

  @Test
  public void testPlayCardToNegativePaletteIndex() {
    StringWriter output = new StringWriter();
    String input = "palette -1 0\nq"; // Invalid palette index
    SoloRedGameModel model = new SoloRedGameModel();
    SoloRedTextController controller = new SoloRedTextController(new StringReader(input), output);

    // Manually create a deck of cards
    List<ConcreteCard> deck = new ArrayList<>();
    char[] colors = {'R', 'O', 'B', 'I', 'V'};
    for (char color : colors) {
      for (int number = 1; number <= 7; number++) {
        deck.add(new ConcreteCard(color, number));
      }
    }

    controller.playGame(model, deck, false, 3, 5);

    String expectedOutput = "Invalid palette index. Try again.\n" +
            "Game quit! State of the game when quit:\n" +
            "Number of cards in deck: " + (deck.size() - 3) + "\n";
    assertEquals(expectedOutput, output.toString());
  }

}
