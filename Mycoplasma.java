import java.awt.Color;
import java.util.List;
import java.util.Random;

/**
 * Simplest form of life.
 * Fun Fact: Mycoplasma are one of the simplest forms of life.  A type of
 * bacteria, they only have 500-1000 genes! For comparison, fruit flies have
 * about 14,000 genes.
 *
 * @author David J. Barnes, Michael Kölling & Jeffery Raphael
 * @version 2022.01.06 (1)
 */

public class Mycoplasma extends Lifeform {

  public static double probability = 0.2;
    /*
     * Create a new Mycoplasma.
     *
     * @param field The field currently occupied.
     * @param location The location within the field.
    */
    public Mycoplasma() {
        super();
        super.type = "mycoplasma"; //this might be a redundant variable.
        super.color = Color.ORANGE;
    }
    /**
     * This is how the Mycoplasma decides if it's alive or not
     */
     public void act(Cell cell) {
      List<Cell> neighbours = cell.getField().getNeighbours(cell.getLocation(),true);  
        //TODO: check for static/dynamic type issues.
        if((neighbours.size() == 3 && cell.isAlive()) || (!cell.isAlive() && neighbours.size() == 2)){
          cell.setNextState(true);
        }
        else{
          cell.setNextState(false);
          cell.setDead();
        }
     }
}
