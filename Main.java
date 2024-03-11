public class Main{
    public static void main(String[] args) {
        Grid myGrid = new Grid(50, 50);
        myGrid.generateGrid();
        myGrid.displayGrid();
        myGrid.startSimulation();
    }
}