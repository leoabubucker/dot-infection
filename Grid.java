import java.util.ArrayList;
public class Grid {
    private final int gridHeight;
    private final int gridWidth;
    private static final String ANSI_RED = "\u001b[31m";
    private static final String ANSI_RESET = "\u001b[0m";

    Grid(int initGridHeight, int initGridWidth){
        gridHeight = initGridHeight;
        gridWidth = initGridWidth;
    }

    public void generateGrid(){
        int[][] coords = new int[gridWidth*gridHeight][2];
        int test = 0;
        int xCoord = 0;
        int yCoord = 0;
        for(int i = 0; i < coords.length; i++){
            coords[i][0] = xCoord;
            coords[i][1] = yCoord;
            
            xCoord++;
            if(xCoord % gridWidth == 0){
                xCoord = 0;
                yCoord++;
            }
        }
        for(int[] i : coords){
            Dot d = new Dot(i[0], i[1]);
        }
    }

    public void displayGrid(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
        int lastY = 0;
        for(Dot d : Dot.returnAllDots()){
            if(d.yCoord > lastY){
                lastY = d.yCoord;
                System.out.println();
            }
            System.out.print(d.color + "." + ANSI_RESET + "   ");
        }
    }

    public void startSimulation(){
        int chosenX = (int) (Math.random() * gridWidth);
        int chosenY = (int) (Math.random() * gridHeight);

        for(Dot d: Dot.returnAllDots()){
            if(d.xCoord == chosenX && d.yCoord == chosenY){
                d.changeDotColor(ANSI_RED);
            }
        }
        displayGrid();
        runSimulation();
    }

    public void runSimulation(){
        while(true){
            ArrayList<int[]> vulnerableDots = new ArrayList<int[]>();
            for(Dot d: Dot.returnAllDots()){
                if(d.color.equals(ANSI_RED)){
                    if(d.xCoord > 0){
                        vulnerableDots.add(new int[]{d.xCoord-1, d.yCoord});
                    }
                    if(d.xCoord < gridWidth){
                        vulnerableDots.add(new int[]{d.xCoord+1, d.yCoord});
                    }
                    if(d.yCoord > 0){
                        vulnerableDots.add(new int[]{d.xCoord, d.yCoord - 1});
                    }
                    if(d.yCoord < gridHeight){
                        vulnerableDots.add(new int[]{d.xCoord, d.yCoord + 1});
                    }
                }
            }
            for(int[] coords : vulnerableDots){
                int infectionChance = (int) ((Math.random() * 10) + 1); //1-10
                if(infectionChance <= 3){ //30% Chance
                    for(Dot d: Dot.returnAllDots()){
                        if(d.xCoord == coords[0] && d.yCoord == coords[1]){
                            d.changeDotColor(ANSI_RED);
                        }
                    }
                }
            }
            try{
                Thread.sleep(1000);
            }
            catch(InterruptedException e){
                e.printStackTrace();
            }
            displayGrid();
        }
    }
}
