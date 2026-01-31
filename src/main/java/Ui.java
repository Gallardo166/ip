import java.util.Scanner;

public class Ui {
    // Solution below inspired by https://www.w3schools.com/java/java_user_input.asp
    private static Scanner commandScanner = new Scanner(System.in);

    private String dividerIndentation = " ".repeat(4);
    private String messageIndentation = " ".repeat(5);
    private String taskIndentation = " ".repeat(6);
    private String divider = "_".repeat(39);

    public String readCommand() {
        return commandScanner.nextLine();
    }

    public void displayMessage(String text) {
       System.out.println(messageIndentation + text); 
    }
    
    public void displayTask(Task task) {
        System.out.println(taskIndentation + task);
    }

    public void displayTopLine() {
        System.out.println(dividerIndentation + divider);
    }

    public void displayBottomLine() {
        System.out.println(dividerIndentation + divider + "\n");
    }

    public void end() {
        commandScanner.close();
    }
}
