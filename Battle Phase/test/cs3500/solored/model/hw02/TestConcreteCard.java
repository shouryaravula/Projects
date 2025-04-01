package cs3500.solored.model.hw02;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;


/**
 * Represents the test class for the ConcreteCard class.
 * Contains tests to validate the functionality and behavior
 * of ConcreteCard methods and ensure correct implementation.
 */

public class TestConcreteCard {

  /**
   * Verifies the compareCards method by comparing different ConcreteCard instances
   * and ensuring that it returns the correct card based on the game's color order.
   */

  @Test
  public void testCompareCards() {
    List<Character> colorOrder = Arrays.asList('R', 'O', 'B', 'I', 'V');

    ConcreteCard card1 = new ConcreteCard('R', 5);
    ConcreteCard card2 = new ConcreteCard('B', 3);
    Assert.assertEquals(card1, card1.compareCards(card2));

    ConcreteCard card3 = new ConcreteCard('O', 2);
    ConcreteCard card4 = new ConcreteCard('V', 6);
    Assert.assertEquals(card4, card3.compareCards(card4));

    ConcreteCard card5 = new ConcreteCard('R', 4);
    ConcreteCard card6 = new ConcreteCard('I', 4);
    Assert.assertEquals(card5, card5.compareCards(card6));

    ConcreteCard card7 = new ConcreteCard('B', 7);
    ConcreteCard card8 = new ConcreteCard('R', 7);
    Assert.assertEquals(card8, card7.compareCards(card8));

    ConcreteCard card9 = new ConcreteCard('R', 3);
    Assert.assertEquals(card9, card9.compareCards(card9));
  }

  /**
   * Ensures that an IllegalArgumentException is thrown when attempting to create
   * a ConcreteCard with a number less than 1, validating that the number constraints are enforced.
   */

  @Test
  public void testInvalidNumberLessThanOne() {
    boolean thrown = false;
    try {
      new ConcreteCard('R', 0); // Should throw exception
    } catch (IllegalArgumentException e) {
      thrown = true;
      Assert.assertEquals("Number must be between 1 and 7.", e.getMessage());
    }
    Assert.assertTrue(thrown);
  }

  /**
   * Ensures that an IllegalArgumentException is thrown when attempting to create
   * a ConcreteCard with a number greater than 7, thus enforcing valid number constraints.
   */

  @Test
  public void testInvalidNumberGreaterThanSeven() {
    boolean thrown = false;
    try {
      new ConcreteCard('R', 8); // Should throw exception
    } catch (IllegalArgumentException e) {
      thrown = true;
      Assert.assertEquals("Number must be between 1 and 7.", e.getMessage());
    }
    Assert.assertTrue(thrown);
  }

  /**
   * Ensures that an IllegalArgumentException is thrown when attempting to create
   * a ConcreteCard with an invalid color, validating that only specified colors are accepted.
   */

  @Test
  public void testInvalidColor() {
    boolean thrown = false;
    try {
      new ConcreteCard('X', 5); // Should throw exception
    } catch (IllegalArgumentException e) {
      thrown = true;
      Assert.assertEquals("Card color must be R, O, B, I, or V.", e.getMessage());
    }
    Assert.assertTrue(thrown);
  }

  /**
   * Verifies the correctness of the hashCode implementation by ensuring that
   * ConcreteCards with the same properties yield the same hash code, while
   * different properties yield different hash codes.
   */

  @Test
  public void testHashCode() {
    ConcreteCard cardA = new ConcreteCard('R', 5);
    ConcreteCard cardB = new ConcreteCard('R', 5);
    ConcreteCard cardC = new ConcreteCard('O', 5);

    Assert.assertEquals(cardA.hashCode(), cardB.hashCode()); // Same color and number
    Assert.assertNotEquals(cardA.hashCode(), cardC.hashCode()); // Different color
  }

  /**
   * Tests the toString method of the ConcreteCard class to ensure it returns
   * a string representation in the expected format (e.g., "B4").
   */

  @Test
  public void testToString() {
    ConcreteCard card = new ConcreteCard('B', 4);
    Assert.assertEquals("B4", card.toString()); // Ensure toString works correctly
  }

  /**
   * Validates the creation of ConcreteCards with boundary numbers (1 and 7) to ensure
   * that the color and number attributes are set correctly and comply with expected values.
   */

  @Test
  public void testBoundaryNumbers() {
    ConcreteCard cardMin = new ConcreteCard('R', 1);
    ConcreteCard cardMax = new ConcreteCard('R', 7);

    Assert.assertEquals('R', cardMin.getColor());
    Assert.assertEquals(1, cardMin.getNumber());
    Assert.assertEquals('R', cardMax.getColor());
    Assert.assertEquals(7, cardMax.getNumber());
  }

}
