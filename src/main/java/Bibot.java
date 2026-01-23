import java.util.Scanner;

public class Bibot {
  public static void main(String[] args) {
    System.out.println("    _______________________________________");
    System.out.println("     Hello! I'm Bibot!");
    System.out.println("     What can I do for you?");
    System.out.println("    _______________________________________\n");

    TaskList taskList = new TaskList();

    // Solution below inspired by https://www.w3schools.com/java/java_user_input.asp
    Scanner scanner = new Scanner(System.in);

    while (true) {
      try {
        String command = scanner.nextLine();

        System.out.println("    _______________________________________");

        if (command.equals("bye")) {
          scanner.close();
          System.out.println("     Bye. Hope to see you again soon!");
          System.out.println("    _______________________________________\n");
          break;

        } else if (command.equals("list")) {
          System.out.println("     Here are the tasks in your list:");
          taskList.display();

        } else if (command.startsWith("mark ")) {
          // Solution below adapted from
          // https://stackoverflow.com/questions/5585779/how-do-i-convert-a-string-to-an-int-in-java
          int index = Integer.parseInt(command.split(" ")[1]) - 1;
          taskList.markTask(index);
          System.out.println("     Nice! I've marked this task as done:");
          System.out.printf("      %s\n", taskList.get(index));

        } else if (command.startsWith("unmark ")) {
          int index = Integer.parseInt(command.split(" ")[1]) - 1;
          taskList.unmarkTask(index);
          System.out.println("     OK, I've marked this task as not done yet:");
          System.out.printf("      %s\n", taskList.get(index));

        } else if (command.startsWith("todo ")) {
          if (command.split(" +").length < 2) {
            throw new BibotException("Please write the task description!");
          } else {
            String description = command.replaceFirst("todo +", "");
            ToDo todo = new ToDo(description);
            taskList.add(todo);
            System.out.println("     Got it. I've added this task:");
            System.out.printf("      %s\n", todo);
            taskList.printLength();
          }

        } else if (command.startsWith("deadline ")) {
          String[] splitCommand = command.split(" /by ");
          String description = splitCommand[0].replaceFirst("deadline ", "");
          String date = splitCommand[1];
          Deadline deadline = new Deadline(description, date);
          taskList.add(deadline);
          System.out.println("     Got it. I've added this task:");
          System.out.printf("      %s\n", deadline);
          taskList.printLength();

        } else {
          String[] splitCommand = command.split(" /from ");
          String description = splitCommand[0].replaceFirst("event ", "");
          String startDate = splitCommand[1].split(" /to ")[0];
          String endDate = splitCommand[1].split(" /to ")[1];
          Event event = new Event(description, startDate, endDate);
          taskList.add(event);
          System.out.println("     Got it. I've added this task:");
          System.out.printf("      %s\n", event);
          taskList.printLength();

        }
      } catch (BibotException e) {
        System.out.printf("     %s\n", e.getMessage());

      }

      System.out.println("    _______________________________________\n");
    }

  }
}
