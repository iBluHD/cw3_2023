import java.awt.Color;
import java.util.List;

import javax.management.loading.PrivateClassLoader;

/**
 * A class representing the shared characteristics of all forms of life
 *
 * @author David J. Barnes, Michael Kölling & Jeffery Raphael
 * @version 2022.01.06 (1)
 */

public class Cell {
    // Whether the cell is alive or not.
    public boolean alive; //TODO: make private again!

    // Whether the cell will be alive in the next generation.
    private boolean nextAlive;

    // The cell's field.
    private Field field;

    // The cell's position in the field.
    private Location location;

    // The cell's defaukt color
    public Color default_color = Color.WHITE; //TODO: make private again

    private Lifeform infection = null; //TODO: make private, give accessor method.

    /**
     * Create a new cell at location in field.
     *
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public Cell(Field field, Location location) {
        alive = false;
        nextAlive = false;
        this.field = field;
        setLocation(location);
    }

    /**
     * Check whether the cell is alive or not.
     * @return true if the cell is still alive.
     */
    protected boolean isAlive() {
        return alive;
    }

    /**
     * Indicate that the cell is no longer alive.
     */
    protected void setDead() {
        alive = false;
    }
    /**
     * Indicate that the cell will be alive or dead in the next generation.
     */
    public void setNextState(boolean value) {
      nextAlive = value;
    }
    public String getInfectionType() {
      return infection.getInfectionType();
    }
    public Lifeform getInfection(){
      return infection;
    }
    public void setInfection(Lifeform i){
      infection = i;
    }
    /**
     * Changes the state of the cell
     */
    public void updateState() {
      alive = nextAlive;
    }


    /**
     * Returns the cell's color
     */
    public Color getColor() {
      if(infection != null){
        Color col = infection.getInfectionColor();
        return infection.getInfectionColor();
      }
      else{
        return default_color;
      }
    }

    /**
     * Return the cell's location.
     * @return The cell's location.
     */
    protected Location getLocation() {
        return location;
    }

    /**
     * Place the cell at the new location in the given field.
     * @param location The cell's location.
     */
    protected void setLocation(Location location) {
        this.location = location;
        field.place(this, location);
    }

    /**
     * Return the cell's field.
     * @return The cell's field.
     */
    protected Field getField() {
        return field;
    }
}
