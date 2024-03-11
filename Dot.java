import java.util.ArrayList;
public class Dot {
    public final int xCoord;
    public final int yCoord;
    public String color;
    public int burningCount;
    public int burntCount;
    private static final String ANSI_RESET = "\u001B[0m";
    private static ArrayList<Dot> dots = new ArrayList<Dot>();

    Dot(int initXCoord, int initYCoord){
        xCoord = initXCoord;
        yCoord = initYCoord;
        color = ANSI_RESET;
        burningCount = 0;
        burntCount = 0;
        dots.add(this);
    }
    public static ArrayList<Dot> returnAllDots(){
        return dots;
    }

    public void changeDotColor(String newColor){
        color = newColor;
    }
   
}
