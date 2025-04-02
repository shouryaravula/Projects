package cs3500.solored.model.hw02;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * Represents the model for the Solo Red Game.
 * This class implements the game logic, managing palettes, hands, and the canvas.
 */

public class SoloRedGameModel implements RedGameModel<ConcreteCard> {
  protected ConcreteCard canvas;
  protected int numPalettes;
  protected int handSize;
  protected List<List<ConcreteCard>> palettes;
  protected ArrayList<ConcreteCard> playingCards;
  protected List<ConcreteCard> deck = new ArrayList<>();
  protected List<ConcreteCard> hand;
  protected boolean playCanvas = false;
  protected boolean isGameStarted = false;
  protected boolean gameOver = false;
  protected int winningPalette = 0;
  protected Random random;
  protected ConcreteCard currentCardCanvas;



  /**
   * Constructs a SoloRedGameModel with default settings.
   * Initializes the canvas and sets the number of palettes and hand size.
   */

  public SoloRedGameModel() {
    this.canvas = new ConcreteCard('R', 1);
    this.deck = new ArrayList<>();
    this.hand = new ArrayList<>();
    this.random = new Random();
  }

  /**
   * Constructs a SoloRedGameModel with a specific random number generator.
   *
   * @param random the Random instance to be used for shuffling or randomizing
   * @throws IllegalArgumentException if random is null
   */

  public SoloRedGameModel(Random random) {
    if (random == null) {
      throw new IllegalArgumentException("Random cannot be null");
    }
    this.canvas = new ConcreteCard('R', 1);
    this.deck = new ArrayList<>();
    this.hand = new ArrayList<>();
    this.random = random;
  }


  /**
   * Determines the winning palette based on the red rule.
   *
   * @return the index of the winning palette according to the red rule
   */
  private int redRule() {
    List<ConcreteCard> topCards = new ArrayList<>(this.palettes.size());
    for (List<ConcreteCard> currentPalette : this.palettes) {
      topCards.add(currentPalette.get(this.getBiggestIndex(currentPalette)));
    }
    return getBiggestIndex(topCards);
  }

  /**
   * Determines the winning palette based on the orange rule.
   *
   * @return the index of the winning palette according to the orange rule
   */
  private int orangeRule() {
    List<List<ConcreteCard>> commonNumberPalettes = new ArrayList<>(this.palettes.size());
    for (List<ConcreteCard> palette : this.palettes) {
      commonNumberPalettes.add(commonNumbers(palette));
    }

    List<ConcreteCard> largestCommonPalette = null;

    for (List<ConcreteCard> palette : commonNumberPalettes) {
      if (largestCommonPalette == null || palette.size() > largestCommonPalette.size()) {
        largestCommonPalette = palette;
      } else if (palette.size() == largestCommonPalette.size()) {
        if (palette.get(0).isGreaterThan(largestCommonPalette.get(0))) {
          largestCommonPalette = palette;
        }
      }
    }
    return commonNumberPalettes.indexOf(largestCommonPalette);
  }

  /**
   * Determines the winning palette based on the blue rule.
   *
   * @return the index of the winning palette according to the blue rule
   */
  private int blueRule() {
    List<List<ConcreteCard>> colorVariations = new ArrayList<>();

    for (List<ConcreteCard> palette : this.palettes) {
      colorVariations.add(colorVariety(palette));
    }

    List<ConcreteCard> largestPalette = null;
    int largestPaletteIndex = -1;

    for (int index = 0; index < colorVariations.size(); index++) {
      List<ConcreteCard> palette = colorVariations.get(index);
      if (largestPalette == null || palette.size() > largestPalette.size()) {
        largestPalette = palette;
        largestPaletteIndex = index;
      } else if (palette.size() == largestPalette.size()) {
        ConcreteCard highestInLargest = largestPalette.get(getBiggestIndex(largestPalette));
        ConcreteCard highestInCurrent = palette.get(getBiggestIndex(palette));

        if (highestInCurrent.isGreaterThan(highestInLargest)) {
          largestPalette = palette;
          largestPaletteIndex = index;
        }
      }
    }
    return largestPaletteIndex;
  }

  /**
   * Determines the winning palette based on the indigo rule.
   *
   * @return the index of the winning palette according to the indigo rule
   */
  private int indigoRule() {
    List<List<ConcreteCard>> longestRunPalettes = new ArrayList<>();

    for (List<ConcreteCard> palette : this.palettes) {
      longestRunPalettes.add(longestRun(palette));
    }

    List<ConcreteCard> longestRun = null;

    for (List<ConcreteCard> palette : longestRunPalettes) {
      if (longestRun == null || palette.size() > longestRun.size()) {
        longestRun = palette;
      } else if (palette.size() == longestRun.size()) {
        ConcreteCard highestInLongest = longestRun.get(getBiggestIndex(longestRun));
        ConcreteCard highestInCurrentRun = palette.get(getBiggestIndex(palette));

        if (highestInCurrentRun.isGreaterThan(highestInLongest)) {
          longestRun = palette;
        }
      }
    }

    return longestRunPalettes.indexOf(longestRun);
  }

  /**
   * Finds the common numbers from the list of ConcreteCards.
   *
   * @param cards the list of ConcreteCards to evaluate
   * @return a list of ConcreteCards that share the same number
   */
  private List<ConcreteCard> commonNumbers(List<ConcreteCard> cards) {
    List<ConcreteCard> longestSequence = new ArrayList<>();

    for (ConcreteCard currentCard : cards) {
      List<ConcreteCard> currentMatches = new ArrayList<>();
      int currentNum = currentCard.getNumber();

      for (ConcreteCard comparisonCard : cards) {
        if (comparisonCard.getNumber() == currentNum) {
          currentMatches.add(comparisonCard);
        }
      }

      if (currentMatches.size() > longestSequence.size()) {
        longestSequence = currentMatches;
      } else if (currentMatches.size() == longestSequence.size()) {
        if (longestSequence.isEmpty()) {
          longestSequence = currentMatches;
        } else {
          int longestNum = longestSequence.get(0).getNumber();
          if (currentNum > longestNum) {
            longestSequence = currentMatches;
          }
        }
      }
    }
    return longestSequence;
  }

  /**
   * Finds all the different colors for the blue rule.
   *
   * @param cards the list of ConcreteCards to evaluate
   * @return a list of unique colored cards
   */
  private List<ConcreteCard> colorVariety(List<ConcreteCard> cards) {
    List<ConcreteCard> uniqueColorCards = new ArrayList<>();
    for (ConcreteCard currentCard : cards) {
      boolean isFound = false;
      for (ConcreteCard uniqueCard : uniqueColorCards) {
        if (uniqueCard.getColor() == currentCard.getColor()) {
          isFound = true;
          if (!uniqueCard.isGreaterThan(currentCard)) {
            uniqueColorCards.set(uniqueColorCards.indexOf(uniqueCard), currentCard);
          }
          break;
        }
      }
      if (!isFound) {
        uniqueColorCards.add(currentCard);
      }
    }
    return uniqueColorCards;
  }

  /**
   * Gets the longest run of consecutive numbers from the given list of cards.
   *
   * @param cards the list of ConcreteCards to evaluate
   * @return a list of cards that forms the longest run
   */
  private List<ConcreteCard> longestRun(List<ConcreteCard> cards) {
    List<ConcreteCard> sortedCards = new ArrayList<>(cards);
    Collections.sort(sortedCards, new ConcreteCardComparator());

    List<ConcreteCard> currentRun = new ArrayList<>();
    List<ConcreteCard> longestRunList = new ArrayList<>();

    for (ConcreteCard currentCard : sortedCards) {
      if (currentRun.isEmpty() || currentRun.get(currentRun.size() - 1).getNumber()
              + 1 == currentCard.getNumber()) {
        currentRun.add(currentCard);
      } else {
        if (currentRun.size() > longestRunList.size()
                || (currentRun.size() == longestRunList.size() && currentRun.get(0).
                isGreaterThan(longestRunList.get(0)))) {
          longestRunList = new ArrayList<>(currentRun);
        }
        currentRun.clear();
        currentRun.add(currentCard);
      }
    }

    if (currentRun.size() > longestRunList.size()
            || (currentRun.size() == longestRunList.size() && currentRun.get(0).
            isGreaterThan(longestRunList.get(0)))) {
      longestRunList = new ArrayList<>(currentRun);
    }

    return longestRunList;
  }

  /**
   * Compares two ConcreteCards by their numbers.
   */
  private static class ConcreteCardComparator implements Comparator<ConcreteCard> {
    @Override
    public int compare(ConcreteCard card1, ConcreteCard card2) {
      return Integer.compare(card1.getNumber(), card2.getNumber());
    }
  }

  /**
   * Determines the winning palette based on the violet rule.
   *
   * @return the index of the winning palette according to the violet rule
   */
  private int violetRule() {
    List<List<ConcreteCard>> underFourPalettes = new ArrayList<>();

    for (List<ConcreteCard> palette : this.palettes) {
      underFourPalettes.add(underFour(palette));
    }

    List<ConcreteCard> largestPalette = null;

    for (List<ConcreteCard> palette : underFourPalettes) {
      if (largestPalette == null || palette.size() > largestPalette.size()) {
        largestPalette = palette;
      } else if (palette.size() == largestPalette.size()) {
        ConcreteCard highestInLargest = largestPalette.get(getBiggestIndex(largestPalette));
        ConcreteCard highestInCurrentPalette = palette.get(getBiggestIndex(palette));

        if (highestInCurrentPalette.isGreaterThan(highestInLargest)) {
          largestPalette = palette;
        }
      }
    }
    return underFourPalettes.indexOf(largestPalette);
  }

  /**
   * Helper for the violet rule to get all cards below four.
   *
   * @param cards the list of ConcreteCards to evaluate
   * @return a list of cards below four
   */
  private List<ConcreteCard> underFour(List<ConcreteCard> cards) {
    List<ConcreteCard> cardsBelowFour = new ArrayList<>();
    for (ConcreteCard currentCard : cards) {
      if (currentCard.getNumber() < 4) {
        cardsBelowFour.add(currentCard);
      }
    }
    return cardsBelowFour;
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
    if (!this.isGameStarted || this.gameOver) {
      throw new IllegalStateException("Game not started");
    }
    if (paletteIdx < 0 || paletteIdx > this.palettes.size() - 1) {
      throw new IllegalArgumentException("Invalid palette index");
    }
    if (cardIdxInHand < 0 || cardIdxInHand > this.hand.size() - 1) {
      throw new IllegalArgumentException("Invalid card index");
    }
    if (paletteIdx == this.winningPalette) {
      throw new IllegalStateException("Palette referred to is winning.");
    }

    try {
      int prevWinner = this.winningPalette;
      ConcreteCard currentCard = this.hand.get(cardIdxInHand);
      this.palettes.get(paletteIdx).add(currentCard);
      this.hand.remove(currentCard);
      int newWinner = getWinningPalette();

      if (this.deck.isEmpty() && this.hand.isEmpty()) {
        this.gameOver = true;
      }

      if (newWinner == prevWinner || this.hand.isEmpty()) {
        this.gameOver = true;
      }

      this.winningPalette = newWinner;
      this.playCanvas = false;
    } catch (IndexOutOfBoundsException e) {
      throw new IndexOutOfBoundsException("Not a valid index.");
    }
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
    if (!this.isGameStarted || this.gameOver) {
      throw new IllegalStateException("Game hasn't started or game is over.");
    }
    if (cardIdxInHand < 0 || cardIdxInHand > this.hand.size() - 1) {
      throw new IllegalArgumentException("Card index not within valid bounds");
    }
    if (playCanvas) {
      throw new IllegalStateException("Play to game has already been run");
    }
    if (this.hand.size() == 1) {
      throw new IllegalStateException("Hand size is 1, so game can't run");
    }
    currentCardCanvas = this.hand.get(cardIdxInHand);
    this.hand.remove(currentCardCanvas);
    this.canvas.setColor(currentCardCanvas.getColor());
    this.winningPalette = getWinningPalette();
    playCanvas = true;

    if (this.deck.isEmpty() && this.hand.isEmpty()) {
      this.gameOver = true;
    }
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
    if (!this.isGameStarted || this.gameOver) {
      throw new IllegalStateException("Game hasn't started or game is over.");
    }
    while (hand.size() < this.handSize && !this.deck.isEmpty()) {
      this.hand.add(this.deck.get(0));
      this.deck.remove(0);
    }
  }

  /**
   * Starts the game with the given options. The deck given is used
   * to set up the palettes and hand. Modifying the deck given to this method
   * will not modify the game state in any way.
   *
   * @param deck        the cards used to set up and play the game
   * @param shuffle     whether the deck should be shuffled prior to setting up the game
   * @param numPalettes number of palettes in the game
   * @param handSize    the maximum number of cards allowed in the hand
   * @throws IllegalStateException    if the game has started or the game is over
   * @throws IllegalArgumentException if numPalettes < 2 or handSize <= 0
   * @throws IllegalArgumentException if deck's size is not large enough to setup the game
   * @throws IllegalArgumentException if deck has non-unique cards or null cards
   */

  @Override
  public void startGame(List<ConcreteCard> deck, boolean shuffle, int numPalettes, int handSize) {
    if (deck == null || deck.isEmpty() || !unique(deck)) {
      throw new IllegalArgumentException("Deck can't be null, empty, or contain duplicate cards.");
    }
    if (this.isGameStarted || this.gameOver) {
      throw new IllegalStateException("Game hasn't started or game is over.");
    }
    if (numPalettes < 2 || handSize <= 0) {
      throw new IllegalArgumentException("Number of palettes and hand size must be greater than 0");
    }
    if (deck.size() < handSize + numPalettes) {
      throw new IllegalArgumentException("Deck size must be bigger than the size of the cards");
    }

    this.numPalettes = numPalettes;
    this.isGameStarted = true;
    this.deck = new ArrayList<>(deck);
    if (shuffle) {
      this.shuffle();
    }
    this.gameOver = false;

    this.palettes = new ArrayList<>();
    for (int i = 0; i < numPalettes; i++) {
      List<ConcreteCard> makeNewPalette = new ArrayList<>();
      makeNewPalette.add(this.deck.remove(0));
      this.palettes.add(makeNewPalette);
    }
    this.handSize = handSize;
    this.drawForHand();
    this.winningPalette = getWinningPalette();
  }

  /**
   * Checks if all cards in the given deck are unique.
   *
   * @param deck the list of cards to check for uniqueness
   * @return true if all cards are unique, false otherwise
   */

  private boolean unique(List<ConcreteCard> deck) {
    Set<ConcreteCard> uniqueCards = new HashSet<>(deck);
    return uniqueCards.size() == deck.size();
  }

  /**
   * Shuffles the deck of cards using a specified random instance.
   * This method randomizes the order of cards in the deck.
   */

  private void shuffle() {
    Collections.shuffle(this.deck, random);
    boolean shuffled = true;
  }

  /**
   * Determines the index of the winning palette based on the color of the card on the canvas.
   * The winning palette is determined by specific rules corresponding to the color.
   *
   * @return the index of the winning palette, or -1 if no valid color is found
   */

  private int getWinningPalette() {
    switch (canvas.getColor()) {
      case 'R':
        return redRule();
      case 'O':
        return orangeRule();
      case 'B':
        return blueRule();
      case 'I':
        return indigoRule();
      case 'V':
        return violetRule();
      default:
        break;
    }
    return -1;
  }

  /**
   * Finds the index of the card with the highest value in the given list of cards.
   * The comparison is made using the getBigger method from the ConcreteCard class.
   *
   * @param cards the list of cards to evaluate
   * @return the index of the card with the highest value
   */

  private int getBiggestIndex(List<ConcreteCard> cards) {
    ConcreteCard highestCard = cards.get(0);
    if (cards.size() > 1) {
      for (int i = 1; i < cards.size(); i++) {
        if (cards.get(i).isGreaterThan(highestCard)) {
          highestCard = cards.get(i);
        }
      }
    }
    return cards.indexOf(highestCard);
  }

  /**
   * Returns the number of cards remaining in the deck used in the game.
   *
   * @return the number of cards in the deck
   * @throws IllegalStateException if the game has not started
   */
  @Override
  public int numOfCardsInDeck() {
    if (!isGameStarted) {
      throw new IllegalStateException("Game is not started");
    }
    return this.deck.size();
  }

  /**
   * Returns the number of palettes in the running game.
   *
   * @return the number of palettes in the game
   * @throws IllegalStateException if the game has not started
   */
  @Override
  public int numPalettes() {
    if (!isGameStarted) {
      throw new IllegalStateException("Game is not started");
    }
    return numPalettes;
  }

  /**
   * Returns the index of the winning palette in the game.
   *
   * @return the 0-based index of the winning palette
   * @throws IllegalStateException if the game has not started
   */
  @Override
  public int winningPaletteIndex() {
    if (!isGameStarted) {
      throw new IllegalStateException("Game has not started");
    }
    return this.winningPalette;
  }

  /**
   * Returns if the game is over as specified by the implementation.
   *
   * @return true if the game has ended and false otherwise
   * @throws IllegalStateException if the game has not started
   */
  @Override
  public boolean isGameOver() {
    if (!isGameStarted) {
      throw new IllegalStateException("Game is not started");
    }
    return this.gameOver;
  }

  /**
   * Returns if the game is won by the player as specified by the implementation.
   *
   * @return true if the game has been won or false if the game has not
   * @throws IllegalStateException if the game has not started or the game is not over
   */
  @Override
  public boolean isGameWon() {
    if (!isGameStarted) {
      throw new IllegalStateException("Game has not started");
    }
    return this.deck.isEmpty() && this.hand.isEmpty();
  }

  /**
   * Returns a copy of the hand in the game. This means modifying the returned list
   * or the cards in the list has no effect on the game.
   */
  @Override
  public List<ConcreteCard> getHand() {
    if (!isGameStarted) {
      throw new IllegalStateException("Game has not started");
    }
    return new ArrayList<>(this.hand);
  }

  /**
   * Returns a copy of the specified palette. This means modifying the returned list
   * or the cards in the list has no effect on the game.
   *
   * @param paletteNum 0-based index of a particular palette.
   */
  @Override
  public List<ConcreteCard> getPalette(int paletteNum) {
    if (!isGameStarted) {
      throw new IllegalStateException("Game has not started");
    }
    if (paletteNum < 0 || paletteNum > this.palettes.size() - 1) {
      throw new IllegalArgumentException("number of palettes can't be less " +
              "than zero or greater than size");
    }
    return new ArrayList<>(this.palettes.get(paletteNum));
  }

  /**
   * Return the top card of the canvas.
   * Modifying this card has no effect on the game.
   *
   * @return the top card of the canvas
   * @throws IllegalStateException if the game has not started or the game is over
   */
  @Override
  public ConcreteCard getCanvas() {
    if (!isGameStarted) {
      throw new IllegalStateException("Game has not started or game is over.");
    }
    return new ConcreteCard(this.canvas.getColor(), this.canvas.getNumber());
  }

  /**
   * Get a NEW list of all cards that can be used to play the game.
   * Editing this list should have no effect on the game itself.
   * Repeated calls to this method should produce a list of cards in the same order.
   * Modifying the cards in this list should have no effect on any returned list
   * or the game itself.
   *
   * @return a new list of all possible cards that can be used for the game
   */
  @Override
  public List<ConcreteCard> getAllCards() {
    List<ConcreteCard> cards = new ArrayList<>();
    List<Character> colors =
            List.of('R', 'O', 'B', 'I', 'V');
    for (int i = 0; i < 5; i++) {
      for (int j = 1; j < 8; j++) {
        cards.add(new ConcreteCard(colors.get(i), j));
      }
    }
    return cards;
  }
}

