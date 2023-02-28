import java.awt.Color;

public abstract class Lifeform {

    public double probability; // TODO: make these vars static, rename probability to SPAWNING_PROBABILITY
    public double IMMMUNITY_PROBABILITY = 1; // by default, all lifeforms are completely immune.
    public static double GLOBAL_DISEASE_PROBABILITY = 0.2;
    Color color = null;
    String type = null;

    public Lifeform() {
    };
    // public static double probability;

    public boolean immune = false;
    public boolean infected = false;// todo: make private later, develop accessors.

    public void setDiseased(boolean isDiseased) {
    };

    public void act(Cell cell) {
    };

    public String getInfectionType() {
        return type;
    }

    public Color getInfectionColor() {
        return color;
    }

}