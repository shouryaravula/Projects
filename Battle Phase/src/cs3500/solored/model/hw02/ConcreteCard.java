package cs3500.solored.model.hw02;

import java.util.Objects;

/**
 * Represents a card in a card game with a specific color and number.
 * The color is one of the characters in the order "ROBIV", and the number
 * ranges from 1 to 7.
 */
public class ConcreteCard implements Card {

  private char color;
  private int number;

  // String representing the order of colors
  String colorOrder = "ROBIV";

  /**
   * Constructs a ConcreteCard with the specified color and number.
   *
   * @param cardColor  the color of the card, which must be one of R, O, B, I, or V
   * @param cardNumber the number of the card, which must be between 1 and 7
   * @throws IllegalArgumentException if the color is not valid or if the number is out of range
   */
  public ConcreteCard(char cardColor, int cardNumber) {
    if (cardNumber < 1 || cardNumber > 7) {
      throw new IllegalArgumentException("Card number must be between 1 and 7");
    }
    if (colorOrder.indexOf(cardColor) == -1) {
      throw new IllegalArgumentException("Card color must be one of R, O, B, I, or V");
    }
    this.color = cardColor;
    this.number = cardNumber;
  }

  /**
   * Compares this card with another card to determine which is greater.
   * The comparison is based first on the number and then on the color if the numbers are equal.
   *
   * @param otherCard the card to compare with
   * @return the card that is greater
   */
  public ConcreteCard compareCards(ConcreteCard otherCard) {
    if (this.number > otherCard.number) {
      return this;
    } else if (this.number < otherCard.number) {
      return otherCard;
    }

    int thisColorIndex = colorOrder.indexOf(this.color);
    int otherColorIndex = colorOrder.indexOf(otherCard.color);
    if (thisColorIndex < otherColorIndex) {
      return this;
    } else {
      return otherCard;
    }
  }

  /**
   * Checks if this card is greater than another card based on its value.
   *
   * @param otherCard the card to compare with
   * @return true if this card is greater, false otherwise
   */
  public boolean isGreaterThan(ConcreteCard otherCard) {
    return this.getValue() > otherCard.getValue();
  }

  /**
   * Calculates the value of the card based on its number and color.
   * The value is computed as (number * 10) + color value.
   *
   * @return the calculated value of the card
   */
  public int getValue() {
    int totalValue = 0;
    totalValue += this.number * 10;

    switch (this.color) {
      case 'R':
        totalValue += 5;
        break;
      case 'O':
        totalValue += 4;
        break;
      case 'B':
        totalValue += 3;
        break;
      case 'I':
        totalValue += 2;
        break;
      case 'V':
        totalValue += 1;
        break;
      default:
        break;
    }
    return totalValue;
  }

  /**
   * Retrieves the color of the card.
   *
   * @return the color of the card
   */
  public char getColor() {
    return color;
  }

  /**
   * Retrieves the number of the card.
   *
   * @return the number of the card
   */
  public int getNumber() {
    return number;
  }

  /**
   * Sets the color of the card to a new value.
   *
   * @param newColor the new color to set for the card
   */
  public void setColor(char newColor) {
    this.color = newColor;
  }

  /**
   * Returns a string representation of the card in the format "colorNumber".
   *
   * @return the string representation of the card
   */
  @Override
  public String toString() {
    return "" + color + number;
  }

  /**
   * Compares this card with another object for equality.
   * Two cards are considered equal if they have the same color and number.
   *
   * @param obj the object to compare with
   * @return true if the cards are equal, false otherwise
   */
  @Override
  public boolean equals(Object obj) {
    return this == obj || (this.color == ((ConcreteCard) obj).getColor() &&
            this.number == ((ConcreteCard) obj).getNumber());
  }

  /**
   * Returns the hash code for this card based on its color and number.
   *
   * @return the hash code value
   */
  @Override
  public int hashCode() {
    return Objects.hash(color, number);
  }
}
