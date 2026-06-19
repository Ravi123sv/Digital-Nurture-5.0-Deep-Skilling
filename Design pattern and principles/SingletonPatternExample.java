class Logger{
    private static Logger Instance;

    private Logger(){
        System.out.println("A New Logger was just created.");
    }
    public static Logger getInstance(){
        if(Instance == null){
            Instance = new Logger();
        }
        return Instance;
    }
    public void log(String message){
        System.out.println("[LOG]" +message);

    }
}
public class SingletonPatternExample  {
    public static void main(String[] args) {
        System.out.println("Starting the program....");

        Logger  firstLogger = Logger.getInstance();
        firstLogger.log("Hello this is my first logger");

        Logger  secondLogger = Logger.getInstance();
        secondLogger.log("Hello this is my second logger");

        System.out.println("----The Big Test----");

        if(firstLogger==secondLogger){
            System.out.println("Success: Both variable points to the same Logger!");
        }
    }
}