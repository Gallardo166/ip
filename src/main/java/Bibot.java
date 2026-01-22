import java.util.Scanner;

public class Bibot {
  public static void main(String[] args) {
    System.out.println("    _______________________________________");
    System.out.println("    Hello! I'm Bibot!");
    System.out.println("    What can I do for you?");
    System.out.println("    _______________________________________\n");

    //Solution below inspired by https://www.w3schools.com/java/java_user_input.asp
    Scanner scanner = new Scanner(System.in);

    while (true) {
      String command = scanner.nextLine();

      if (command.equals("bye")) {
        System.out.println("    _______________________________________");
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println("    _______________________________________\n");
        scanner.close();
        break;
      } else {
        System.out.println("    _______________________________________");
        System.out.printf("    %s\n", command);
        System.out.println("    _______________________________________\n");
      }
    }

  }
}
