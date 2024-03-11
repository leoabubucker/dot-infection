import java.util.Scanner;
public class Main{
  private static final Scanner CONSOLE = new Scanner(System.in);
    public static void main(String[] args) {
      Grid myGrid = null;
      System.out.println("Welcome to an infection simulator. This simulator will create a grid of dots with one randomly starting burning/infected (red) and the rest starting neutral (white/black). Every tick, every dot that is adjacent to an burning/infected dot will have a chance to become burning/infected. After a certain amount of ticks, an burning/infected dot will become burnt/dead (grey). After a certain amount of ticks, a burnt/dead dot will reset and become neutral (white/black). If all dots are neutral, the simulation will automatically reset.");
      System.out.println("Press Ctrl+C at any time to exit the simulation.");
      System.out.println("Let's Begin!");
      System.out.println("Enter the size of the grid (Integers Only). Please keep in mind the size of your screen and the processing power of your device.");
      System.out.print("Height: ");
      int height = CONSOLE.nextInt();
      System.out.print("\nWidth: ");
      int width = CONSOLE.nextInt();
      System.out.println("Do you want to further customize the simulation or use default settings? (y/n)");
      String userChoice = CONSOLE.next();
      if(userChoice.trim().equalsIgnoreCase("y")){
      System.out.print("\n\nNext, please enter the infection/spread rate (an integer from 1-10 representing a 10-100% chance of infection): ");
      int infectionRate = CONSOLE.nextInt();

      System.out.println("\nNext, please enter the time that a red dot should turn grey (burnt). Use an integer that represents the number of miliseconds (1 second = 1000 miliseconds): ");
      int maxBurnCount = CONSOLE.nextInt();
      System.out.print("\nNext, please enter the time that a grey dot should turn white (reset). Use an integer that represents the number of miliseconds (1 second = 1000 miliseconds): ");
      int maxBurntCount = CONSOLE.nextInt();
      System.out.print("\nFinally, please enter the time between each update. Use an integer that represents the number of miliseconds (1 second = 1000 miliseconds). Miliseconds must be greater than 100 and one should keep in mind the processing power of one's device: ");
      int tickRate = CONSOLE.nextInt();
      myGrid = new Grid(height, width, infectionRate, maxBurnCount, maxBurntCount, tickRate);
    }
    else if (userChoice.equalsIgnoreCase("n")){
      myGrid = new Grid(height, width);
    }
    else{
      main(new String[0]);
    }
      myGrid.generateGrid();
      myGrid.displayGrid();
      myGrid.startSimulation();
    }
}