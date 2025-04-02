package cs3500.solored.view.hw02;

import java.io.IOException;

/**
 * An interface representing a view for the Red Game.
 * This interface defines the methods necessary for rendering the game model.
 */

public interface RedGameView {

  /**
   * Renders a model in some manner (e.g. as text, or as graphics, etc.).
   * @throws IOException if the rendering fails for some reason
   */
  void render() throws IOException;
}