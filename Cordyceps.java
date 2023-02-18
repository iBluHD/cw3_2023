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

public class Cordyceps extends Lifeform {

  public static double probability = 0.00;
  public static float INFECTION_PROBABILITY = 0.001f;
  Random rand = Randomizer.getRandom();
    /*
        Cordyceps parasitic behaviour. Attacks and forces others to become itself.
    */
    public Cordyceps() {
        super();
        super.type = "cordyceps"; //this might be a redundant variable.
        super.color = Color.RED;
    }
    /**
     * This is how the Mycoplasma decides if it's alive or not
     */
     public void act(Cell cell) {
        //Spread the parasite(well, technically its actually a fungi, not a parasite but whatever)
        List<Cell> neighbours = cell.getField().getNeighbours(cell.getLocation(),true);  
        for(Cell neighbour : neighbours){
            Lifeform neighbourLifeform = neighbour.getInfection();
            if(neighbourLifeform.getInfectionType() != "cordyceps"){
                if(rand.nextFloat() <= INFECTION_PROBABILITY){
                    //neighbouring cell lifeform is not a cordycep, and passes probability check to become a cordycep.
                    neighbour.setInfection(new Cordyceps());
                }
            }
        }
        if(neighbours.size() <= 2 && cell.isAlive()){
            cell.setNextState(true);
          }
        else{
            cell.setNextState(false);
            cell.setDead();
        }


        
     }
}
