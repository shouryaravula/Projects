package cs3500.solored.view.hw02;

import java.io.IOException;
import java.util.List;

import cs3500.solored.model.hw02.RedGameModel;

/**
 * Represents the text-based view for the Solo Red game model.
 * Provides a method to convert the game state into a user-friendly string representation.
 */
public class SoloRedGameTextView implements RedGameView {


  private final RedGameModel<?> gameModel;
  private final Appendable appendable;


  /**
   * Initializes the view with the specified game model.
   *
   * @param gameModel the RedGameModel to be represented in this view
   */
  public SoloRedGameTextView(RedGameModel<?> gameModel) {
    this.gameModel = gameModel;
    this.appendable = new StringBuilder();
  }

  /**
   * Initializes the view with the specified game model and an Appendable.
   *
   * @param gameModel  the RedGameModel to be represented in this view
   * @param appendable the Appendable to which the game state will be rendered
   */
  public SoloRedGameTextView(RedGameModel<?> gameModel, Appendable appendable) {
    if (appendable == null) {
      throw new IllegalArgumentException("Appendable may not be null");
    }
    this.gameModel = gameModel;
    this.appendable = appendable;
  }

  /**
   * Renders the current state of the model to the output stream.
   *
   * @throws IOException if the rendering fails for some reason
   */
  @Override
  public void render() throws IOException {
    if (appendable != null) {
      this.appendable.append(this.toString());
    }
  }

  /**
   * Converts the current state of the model into a string representation
   * for display to the user. This includes the canvas and the palettes,
   * highlighting the winning palette.
   *
   * @return a string representation of the current game state
   */
  @Override
  public String toString() {
    StringBuilder displayBuilder = new StringBuilder();
    displayBuilder.append("Canvas: ").append(
            gameModel.getCanvas().toString().charAt(0)).append("\n");

    for (int index = 0; index < gameModel.numPalettes(); index++) {
      if (index == gameModel.winningPaletteIndex()) {
        displayBuilder.append("> ");
      }
      displayBuilder.append("P").append(
              index + 1).append(": ").append(formatCards(gameModel.getPalette(index)));
      if (displayBuilder.length() > 0) {
        displayBuilder.deleteCharAt(displayBuilder.length() - 1);
      }
      displayBuilder.append("\n");
    }
    String handCards = formatCards(gameModel.getHand());
    if (!handCards.isEmpty()) {
      handCards = handCards.substring(0, handCards.length() - 1);
    }
    displayBuilder.append("Hand: ").append(handCards);
    return displayBuilder.toString();
  }

  /**
   * Retrieves the current output from the Appendable and formats it for display.
   */
  public String output() {
    return this.appendable.toString();
  }

  /**
   * Generates a string representation of the list of cards in a given palette.
   *
   * @param palette the list of cards to be converted into a string
   * @return a string representation of the cards in the palette
   */
  private String formatCards(List<?> palette) {
    StringBuilder cardBuilder = new StringBuilder();
    if (palette.isEmpty()) {
      return " ";
    }
    for (Object card : palette) {
      cardBuilder.append(card.toString()).append(" ");
    }
    return cardBuilder.toString();
  }
}
