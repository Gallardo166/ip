import java.util.Scanner;

public class Bibot {
  public static void main(String[] args) {
    System.out.println("    _______________________________________");
    System.out.println("    Hello! I'm Bibot!");
    System.out.println("    What can I do for you?");
    System.out.println("    _______________________________________\n");

    TaskList taskList = new TaskList();

    //Solution below inspired by https://www.w3schools.com/java/java_user_input.asp
    Scanner scanner = new Scanner(System.in);

    while (true) {
      String command = scanner.nextLine();

      System.out.println("    _______________________________________");
      
      if (command.equals("bye")) {
        System.out.println("    Bye. Hope to see you again soon!");
        scanner.close();
        System.out.println("    _______________________________________\n");
        break;
      } else if (command.equals("list")) {
        taskList.display();
      } else {
        System.out.printf("    added: %s\n", command);
        taskList.add(command);
      }

      System.out.println("    _______________________________________\n");
    }

  }
}
