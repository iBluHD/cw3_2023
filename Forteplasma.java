import java.awt.Color;
import java.util.List;
import java.util.Random;

/**
 * Simplest form of life.
 * Fun Fact: Mycoplasma are one of the simplest forms of life. A type of
 * bacteria, they only have 500-1000 genes! For comparison, fruit flies have
 * about 14,000 genes.
 *
 * @author David J. Barnes, Michael Kölling & Jeffery Raphael
 * @version 2022.01.06 (1)
 */

public class Forteplasma extends Lifeform {

  /*
   * Create a new Mycoplasma.
   *
   * @param field The field currently occupied.
   * 
   * @param location The location within the field.
   */
  private static double RESPAWN_PROBABILITY = 0.01;
  Random rand = Randomizer.getRandom();

  public Forteplasma() {
    super();
    super.type = "forteplasma"; // this might be a redundant variable.
    super.color = Color.GREEN;
    super.probability = 0.3;

  }

  /**
   * This is how the Mycoplasma decides if it's alive or not
   */
  public void act(Cell cell) {
    List<Cell> neighbours = cell.getField().getNeighbours(cell.getLocation(), true, type);
    if (neighbours.size() <= (1 + rand.nextInt(8)) && cell.isAlive()) {
      cell.setNextState(true);
    } else if (!cell.isAlive() && rand.nextDouble() < RESPAWN_PROBABILITY) {
      cell.setNextState(true);
    } else {
      cell.setNextState(false);
    }

  }
}
