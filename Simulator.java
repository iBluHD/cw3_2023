import java.util.Random;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.Color;

/**
 * A Life (Game of Life) simulator, first described by British mathematician
 * John Horton Conway in 1970.
 *
 * @author David J. Barnes, Michael Kölling & Jeffery Raphael
 * @version 2022.01.06 (1)
 */

public class Simulator {
    // The default width for the grid.
    private static final int DEFAULT_WIDTH = 100;

    // The default depth of the grid.
    private static final int DEFAULT_DEPTH = 80;

    // List of cells in the field.
    private List<Cell> cells;

    // The current state of the field.
    private Field field;

    // The current generation of the simulation.
    private int generation;

    // A graphical view of the simulation.
    private SimulatorView view;

    /**
     * Execute simulation
     */
    public static void main(String[] args) {
      Simulator sim = new Simulator();
      sim.simulate(100);
    }

    /**
     * Construct a simulation field with default size.
     */
    public Simulator() {
        this(DEFAULT_DEPTH, DEFAULT_WIDTH);
    }

    /**
     * Create a simulation field with the given size.
     * @param depth Depth of the field. Must be greater than zero.
     * @param width Width of the field. Must be greater than zero.
     */
    public Simulator(int depth, int width) {
        if (width <= 0 || depth <= 0) {
            System.out.println("The dimensions must be greater than zero.");
            System.out.println("Using default values.");
            depth = DEFAULT_DEPTH;
            width = DEFAULT_WIDTH;
        }

        cells = new ArrayList<>();
        field = new Field(depth, width);

        // Create a view of the state of each location in the field.
        view = new SimulatorView(depth, width);

        // Setup a valid starting point.
        reset();
    }

    /**
     * Run the simulation from its current state for a reasonably long period,
     * (4000 generations).
     */
    public void runLongSimulation() {
        simulate(4000);
    }

    /**
     * Run the simulation from its current state for the given number of
     * generations.  Stop before the given number of generations if the
     * simulation ceases to be viable.
     * @param numGenerations The number of generations to run for.
     */
    public void simulate(int numGenerations) {
        for (int gen = 1; gen <= numGenerations && view.isViable(field); gen++) {
            simOneGeneration();
            delay(500);   // comment out to run simulation faster
        }
    }

    /**
     * Run the simulation from its current state for a single generation.
     * Iterate over the whole field updating the state of each life form.
     */
    public void simOneGeneration() {
      HashMap<String, Set<Cell> > deadNeighbours = new HashMap<String, Set<Cell> >(); // lifeform type --> set of dead neighbouring cells.

        generation++;
        for (Iterator<Cell> it = cells.iterator(); it.hasNext(); ) {
            Cell cell = it.next();
            Lifeform cellInfection = cell.getInfection();
            if(cellInfection != null){
              cellInfection.act(cell); 
              List<Cell> neighbours = cell.getField().getNeighbours(cell.getLocation(),false);  
              Set<Cell> temp = deadNeighbours.get(cellInfection.getInfectionType());
              if(temp != null){
                temp.addAll(neighbours);
              }
              else{
                deadNeighbours.put(cellInfection.getInfectionType(), new HashSet<Cell>());
              }
            }
        }
        //check neighbours
        for (Map.Entry<String, Set<Cell>> entry : deadNeighbours.entrySet()){
          String type = entry.getKey();
          Set<Cell> set = entry.getValue();
          switch (type) {
            case "mycoplasma":
              for(Cell cell : set){
                cell.setInfection(new Mycoplasma());
                cell.getInfection().act(cell);
              }
              break;
            case "iriplasma":
              for(Cell cell : set){
                cell.setInfection(new Iriplasma());
                cell.getInfection().act(cell);
              }
              break;
            case "mutationplasma":
              for(Cell cell : set){
                cell.setInfection(new Mutatioplasma());
                cell.getInfection().act(cell);
              }
              break;
            case "cordyceps":
              for(Cell cell : set){
                cell.setInfection(new Cordyceps());
                cell.getInfection().act(cell);
              }
              break;
            
            default:
              break;
          }
          
        }
        for (Cell cell : cells) {
          cell.updateState();
        }
        view.showStatus(generation, field);
    }
    /**
     * Create lifeform instances in the dead neighbouring cells. Each of these are checked
     * to see if they will survive in the next generation. This allows us to develop behaviour 
     * for making dead cells come back to life. More computationally efficient than the original implementation,
     * which checked all cells. This reduces the data load.
     * @param cell
     */
    public void simulateCellNeighbours(Cell cell){

    }
    /**
     * Reset the simulation to a starting position.
     */
    public void reset() {
        generation = 0;
        cells.clear();
        populate();

        // Show the starting state in the view.
        view.showStatus(generation, field);
    }

    /**
     * Randomly populate the field live/dead life forms
     */
    private void populate() {
      Random rand = Randomizer.getRandom();
      field.clear();
      for (int row = 0; row < field.getDepth(); row++) {
        for (int col = 0; col < field.getWidth(); col++) {
          Location location = new Location(row, col);
          Cell cell = new Cell(field, location);
          if (rand.nextDouble() <= Mycoplasma.probability) {
            cell.setInfection(new Mycoplasma());
          }
          else if (rand.nextDouble() <= Iriplasma.probability){
            cell.setInfection(new Iriplasma());
          }
          else if(rand.nextDouble() <= Mutatioplasma.probability){
            cell.setInfection(new Mutatioplasma());
          }
          else if(rand.nextDouble() <= Cordyceps.probability){
            cell.setInfection(new Cordyceps());
          }
          else if(rand.nextDouble() <= Forteplasma.probability){
            cell.setInfection(new Forteplasma());
          }
          else {
            cell.setDead();
          }
          cells.add(cell);
        }
      }
    }

    /**
     * Pause for a given time.
     * @param millisec  The time to pause for, in milliseconds
     */
    private void delay(int millisec) {
        try {
            Thread.sleep(millisec);
        }
        catch (InterruptedException ie) {
            // wake up
        }
    }
}
