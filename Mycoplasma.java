import java.awt.Color;
import java.util.List;
import java.util.Random;

import javax.swing.text.AbstractDocument.BranchElement;

/**
 * Simplest form of life.
 * Fun Fact: Mycoplasma are one of the simplest forms of life. A type of
 * bacteria, they only have 500-1000 genes! For comparison, fruit flies have
 * about 14,000 genes.
 *
 * @author David J. Barnes, Michael Kölling & Jeffery Raphael
 * @version 2022.01.06 (1)
 */

public class Mycoplasma extends Lifeform {

  public Mycoplasma() {
    super();
    super.type = "mycoplasma"; // this might be a redundant variable.
    if (immune == true) {
      super.color = Color.ORANGE;
    } else {
      super.color = Color.LIGHT_GRAY;
    }
    super.probability = 0.4;
    super.IMMMUNITY_PROBABILITY = 0.9;
  }

  public void setDiseased(boolean isDiseased) {
    infected = isDiseased;
    super.color = isDiseased ? Color.CYAN : Color.ORANGE;
  }

  /**
   * This is how the Mycoplasma decides if it's alive or not
   */
  public void act(Cell cell) {
    List<Cell> neighbours = cell.getField().getNeighbours(cell.getLocation(), true, type);
    cell.setNextState(false);

    // non-diseased behaviour
    if (!infected) {

      if ((neighbours.size() == 3 || neighbours.size() == 2) && cell.isAlive()) {
        cell.setNextState(true);

        if (immune == false) {
          // check if a neighbour is infected
          for (Cell neighbour : neighbours) {
            if (neighbour.getInfection().infected == true) {
              setDiseased(true);
              break;
            }
          }

        }
      } else if (neighbours.size() == 3 && !cell.isAlive()) {
        cell.setNextState(true);

      }
    }

    // diseased behaviour
    else {
      if (neighbours.size() >= 2) {
        cell.setNextState(true);
      } else {
        setDiseased(false);
      }
    }

  }
}
