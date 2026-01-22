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
        scanner.close();
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println("    _______________________________________\n");
        break;
        
      } else if (command.equals("list")) {
        taskList.display();
        
      } else if (command.startsWith("mark ")) {
        //Solution below adapted from https://stackoverflow.com/questions/5585779/how-do-i-convert-a-string-to-an-int-in-java
        int index = Integer.parseInt(command.split(" ")[1]) - 1;
        taskList.markTask(index);
        System.out.println("    Nice! I've marked this task as done:\n");
        System.out.printf("    %s\n", taskList.get(index));
        
      } else if (command.startsWith("unmark ")) {
        int index = Integer.parseInt(command.split(" ")[1]) - 1;
        taskList.unmarkTask(index);
        System.out.println("   OK, I've marked this task as not done yet:\n"); 
        System.out.printf("    %s\n", taskList.get(index));
        
      } else {
        System.out.printf("    added: %s\n", command);
        taskList.add(command);

      }

      System.out.println("    _______________________________________\n");
    }

  }
}
