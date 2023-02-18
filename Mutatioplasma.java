import java.awt.Color;
import java.util.List;
import java.util.Random;
import java.math.*;


public class Mutatioplasma extends Lifeform {
    public static float CONSTANT_DECREASE = 0.01f; //
    public static double probability = 0.4;
    private float degradation = 1; //alpha val
    float neighboursNeededToSurvive = 1 / degradation; //round to 1

    private Color initialColor = new Color(144, 34, 163);
    private Color finalColor = new Color(163, 34, 163);

    

    public Mutatioplasma(){
        super();
        super.type = "mutatioplasma";
        super.color = initialColor;
    }

    public void act(Cell cell){
        List<Cell> neighbours = cell.getField().getNeighbours(cell.getLocation(),true);  
        
        //if(neighboursNeededToSurvive >= 4){neighboursNeededToSurvive = 4;}


        if(neighbours.size() <= neighboursNeededToSurvive && cell.isAlive()){
            cell.setNextState(false);
            cell.setDead();
        }
        else {
            cell.setNextState(true);
        }

        if(degradation > 0.1){
            degradation -= CONSTANT_DECREASE;
            neighboursNeededToSurvive = 1 / degradation;
            //if(neighboursNeededToSurvive >= 3){neighboursNeededToSurvive = 3;}
            if(degradation < 0.5){ super.color = finalColor; }
        }
        

    }


}
