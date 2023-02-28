import java.awt.Color;
import java.util.List;
import java.util.Random;
import java.math.*;

public class Mutatioplasma extends Lifeform {
    public static float CONSTANT_DECREASE = 0.1f; //
    private float degradation = 1; // alpha val
    float neighboursNeededToSurvive = 1 / degradation; // round to 1

    private Color initialColor = new Color(144, 34, 163);
    // private Color finalColor = new Color(163, 34, 163);

    public Mutatioplasma() {
        super();
        super.type = "mutatioplasma";
        super.color = initialColor;
        super.probability = 0.0;
    }

    public void act(Cell cell) {
        List<Cell> neighbours = cell.getField().getNeighbours(cell.getLocation(), true, type);

        if (neighbours.size() >= neighboursNeededToSurvive && cell.isAlive()) {
            cell.setNextState(true);
        } else if (!cell.isAlive() && neighbours.size() >= 3) {
            cell.alive = true;
            cell.setNextState(true);
            degradation = 1;
        } else {
            cell.setNextState(false);
            cell.setDead();
        }
        if (degradation > 0.1) {
            degradation -= CONSTANT_DECREASE;
            neighboursNeededToSurvive = 1 / degradation;
        }

    }

    @Override
    public Color getInfectionColor() {
        double rValue = (-80 * degradation) + 165.111;
        return new Color((int) rValue, initialColor.getGreen(), initialColor.getBlue());
    }
}
