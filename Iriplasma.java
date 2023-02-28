import java.awt.Color;
import java.util.List;

public class Iriplasma extends Lifeform {

    int colorIndex = 0;
    Color[] colorArray = new Color[] {
            Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE, Color.MAGENTA };

    public Iriplasma() {
        super();
        super.type = "iriplasma"; // might be redundant still.
        super.probability = 0.0;
    }

    public void act(Cell cell) {
        List<Cell> neighbours = cell.getField().getNeighbours(cell.getLocation(), true, type);
        if (neighbours.size() == 2 && !cell.isAlive() || (neighbours.size() <= 3 && cell.isAlive())) {
            cell.setNextState(true);
        } else {
            cell.setNextState(false);
        }
        colorIndex++;
        colorIndex = colorIndex % colorArray.length;
    }

    @Override
    public Color getInfectionColor() {
        return colorArray[colorIndex];
    }
}
