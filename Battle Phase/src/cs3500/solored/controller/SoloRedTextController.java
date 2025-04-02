package cs3500.solored.controller;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import cs3500.solored.model.hw02.Card;
import cs3500.solored.model.hw02.RedGameModel;
import cs3500.solored.view.hw02.RedGameView;
import cs3500.solored.view.hw02.SoloRedGameTextView;

/**
 * This class implements the RedGameController interface
 * to manage user interactions and game flow for a solo version of the card game.
 */
public class SoloRedTextController implements RedGameController {

  private final Readable rd;
  private final Appendable ap;
  private RedGameModel<?> model;
  private RedGameView view;
  private boolean quitGame = false;
  private boolean gameOver = false;
  private Scanner scanner;

  /**
   * Constructs a SoloRedTextController with the specified input and output.
   *
   * @param rd the source of user input
   * @param ap the destination for output messages
   * @throws IllegalArgumentException if either rd or ap is null
   */
  public SoloRedTextController(Readable rd, Appendable ap) {
    if (rd == null || ap == null) {
      throw new IllegalArgumentException("Readable or Appendable cannot be null");
    }
    this.rd = rd;
    this.ap = ap;
  }

  /**
   * Plays the game using the provided model and deck.
   *
   * @param model       the game model to be used
   * @param deck        the list of cards to be used in the game
   * @param shuffle     boolean indicating whether the deck should be shuffled
   * @param numPalettes number of palettes to use in the game
   * @param handSize    maximum number of cards in the player's hand
   * @throws IllegalArgumentException if the model or deck is null
   */
  @Override
  public <C extends Card> void playGame(RedGameModel<C> model, List<C> deck,
                                        boolean shuffle, int numPalettes, int handSize) {
    validateGameParameters(model, deck);
    scanner = new Scanner(rd);
    this.model = model;
    view = new SoloRedGameTextView(model, ap);
    try {
      model.startGame(deck, shuffle, numPalettes, handSize);
    } catch (Exception e) {
      throw new IllegalArgumentException("Unable to start game", e);
    }
    initializeGame();
    while (true) {
      String userInp = getUserInput();
      processInput(userInp);
      if (gameOver) {
        displayGameResult();
        initializeGame();
        return;
      } else if (quitGame) {
        return;
      } else {
        initializeGame();
      }
    }
  }

  /**
   * Validates the game parameters to ensure they are not null.
   *
   * @param model the game model
   * @param deck  the deck of cards
   * @throws IllegalArgumentException if either model or deck is null
   */
  private void validateGameParameters(RedGameModel<?> model, List<?> deck) {
    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null.");
    }
    if (deck == null) {
      throw new IllegalArgumentException("Deck cannot be null.");
    }
  }

  /**
   * Gets the user input in a safe manner.
   *
   * @return the input from the user as a string
   */
  private String getUserInput() {
    String userInput = "";
    try {
      if (scanner.hasNext()) {
        userInput = scanner.next().toLowerCase();
      }
    } catch (Exception e) {
      throw new IllegalStateException("Invalid read input", e);
    }
    return userInput;
  }

  /**
   * Displays the result of the game (win/loss).
   */
  private void displayGameResult() {
    if (model.isGameWon()) {
      transmit("Game won.");
    } else {
      transmit("Game lost.");
    }
  }

  /**
   * Processes user input commands.
   *
   * @param userInput the command input by the user
   */
  private void processInput(String userInput) {
    switch (userInput) {
      case "palette":
        handlePaletteInput();
        break;
      case "canvas":
        handleCanvasInput();
        break;
      case "q":
        handleQuit();
        break;
      default:
        transmit("Invalid command. Try again.");
        break;
    }
  }

  /**
   * Handles the palette input command.
   */
  private void handlePaletteInput() {
    int paletteIndex = getValidInput();
    int handIndex = getValidInput();
    if (paletteIndex < 0) {
      handleQuit();
      return;
    }
    else if (handIndex < 0) {
      handleQuit();
      return;
    }
    try {
      model.playToPalette(paletteIndex, handIndex);
      if (model.isGameWon() || model.isGameOver()) {
        gameOver = true;
      } else {
        model.drawForHand();
      }
    } catch (Exception e) {
      transmit("Invalid move. Try again. " + e.getMessage());
    }
  }

  /**
   * Handles the canvas input command.
   */
  private void handleCanvasInput() {
    int canvasIndex = getValidInput();
    if (canvasIndex < 0) {
      handleQuit();
      return;
    }
    try {
      model.playToCanvas(canvasIndex);
    } catch (Exception e) {
      transmit("Invalid move. Try again. " + e.getMessage());
    }
  }

  /**
   * Handles the quit command, terminating the game and displaying the state.
   */
  private void handleQuit() {
    quitGame = true;
    transmit("Game quit!\nState of game when quit:");
    initializeGame();
  }

  /**
   * Transmits the message to the output destination.
   *
   * @param message the message to be sent
   */
  private void transmit(String message) {
    try {
      ap.append(message).append("\n");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Gets a valid input from the user, ensuring it is a non-negative integer.
   *
   * @return the valid input as an integer
   */
  private Integer getValidInput() {
    while (true) {
      if (scanner.hasNextInt()) {
        int num = scanner.nextInt();
        if (num > 0) {
          return num - 1;
        }
      } else if (scanner.hasNext()) {
        String input = scanner.next();
        if (input.equals("q")) {
          quitGame = true;
          return -1;
        }
      }
    }
  }

  /**
   * Initializes the game view and displays the current state.
   */
  private void initializeGame() {
    try {
      view.render();
      ap.append("\n");
      transmit("Number of cards in deck: " + model.numOfCardsInDeck());
    } catch (IOException e) {
      throw new IllegalStateException("Cannot render view");
    }
  }
}
