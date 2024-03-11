public class HelperFunctions {
    public static void wait(int seconds){
        try{
            Thread.sleep(seconds * 1000);
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
