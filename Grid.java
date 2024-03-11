import java.util.ArrayList;
//
public class Grid {
    private final int gridHeight;
    private final int gridWidth;
    private final int infectionRate;
    private final int maxBurnCount;
    private final int maxBurntCount;
    private final int tickRate;
    private static final String ANSI_RED = "\u001b[31m";
    private static final String ANSI_RESET = "\u001b[0m";
    private static final String ANSI_BLACK_BOLD_BRIGHT = "\033[1;90m"; // BLACK

    Grid(int initGridHeight, int initGridWidth){
        gridHeight = initGridHeight;
        gridWidth = initGridWidth;
        infectionRate = 3;
        maxBurnCount = 15000;
        maxBurntCount = 15000;
        tickRate = 1000;
    }

    Grid(int initGridHeight, int initGridWidth, int initInfectionRate, int initMaxBurnCount, int initMaxBurntCount, int initTickRate){
        gridHeight = initGridHeight;
        gridWidth = initGridWidth;
        infectionRate = initInfectionRate;
        maxBurnCount = initMaxBurnCount;
        maxBurntCount = initMaxBurntCount;
        tickRate = initTickRate;
    }

    public void generateGrid(){
        int[][] coords = new int[gridWidth*gridHeight][2];
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
           new Dot(i[0], i[1]);
        }
    }

    public void displayGrid(){
        HelperFunctions.clearConsole();
        int lastY = 0;
        for(Dot d : Dot.returnAllDots()){
            if(d.yCoord > lastY){
                lastY = d.yCoord;
                System.out.println();
            }
            System.out.print(d.color + "." + ANSI_RESET + " ");//1 space
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
            boolean allWhite = true;
            ArrayList<int[]> vulnerableDots = new ArrayList<int[]>();
            vulnerableDots.clear();
            for(Dot d: Dot.returnAllDots()){
                if(d.color.equals(ANSI_RED)){
                    allWhite = false;
                    d.burningCount+= tickRate;
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
                    if(d.burningCount > maxBurnCount){
                        d.burningCount = 0;
                        d.color = ANSI_BLACK_BOLD_BRIGHT;
                    }
                }
                else if(d.color.equals(ANSI_BLACK_BOLD_BRIGHT)){
                    allWhite = false;
                    d.burntCount+=tickRate;
                    if(d.burntCount > maxBurntCount){
                        d.burntCount = 0;
                        d.color = ANSI_RESET;
                    }
                }
            }
            if(allWhite){
                startSimulation();
                return;
            }
            for(int[] coords : vulnerableDots){
                for(Dot d: Dot.returnAllDots()){
                    if(d.xCoord == coords[0] && d.yCoord == coords[1] && d.color.equals(ANSI_RESET)){
                        int infectionChance = (int) ((Math.random() * 10) + 1); //1-10
                        if(infectionChance <= infectionRate){ //30% Chance
                            d.changeDotColor(ANSI_RED);
                        }
                    }
                }
            }
            HelperFunctions.wait(tickRate);
            displayGrid();
        }
    }
}
