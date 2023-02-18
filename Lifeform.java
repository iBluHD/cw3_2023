import java.awt.Color;


public abstract class Lifeform {

    Color color = null;
    String type = null;

    public void act(Cell cell){};

    public String getInfectionType(){
        return type;
    }
    public Color getInfectionColor(){
        return color;
    }

}