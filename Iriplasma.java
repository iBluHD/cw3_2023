import java.awt.Color;
import java.util.List;

public class Iriplasma extends Lifeform {
    public static double probability = 0.0;
    

    int colorIndex = 0;
    Color[] colorArray = new Color[]{
        Color.RED, Color. ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE, Color.MAGENTA};
    
    
    public Iriplasma(){
        super();
        super.type = "iriplasma"; //might be redundant still.
    }

    public void act(Cell cell){
        List<Cell> neighbours = cell.getField().getNeighbours(cell.getLocation(), true);
        if(neighbours.size() == 3 || (neighbours.size() == 2 && cell.isAlive())){
            cell.setNextState(true);
        }
        else{
            cell.setNextState(false);
            cell.setDead();
        }
        colorIndex++;
        colorIndex = colorIndex % colorArray.length;
    }
    
    @Override
    public Color getInfectionColor(){
        return colorArray[colorIndex];
    }
}
