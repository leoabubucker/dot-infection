public class HelperFunctions {
    public static void wait(int miliseconds){
        try{
            Thread.sleep(miliseconds);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    public static void clearConsole(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
