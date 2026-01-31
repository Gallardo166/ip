public class Parser {
    private boolean isFinished = false;

    public boolean isFinished() {
        return this.isFinished;
    }

    public Command parse(String input) throws BibotException {
        if (input.equals("bye")) {
            this.isFinished = true;
            return new ExitCommand();
        } else if (input.equals("list")) {
            return new ListCommand();
        } else if (input.startsWith("mark ")) {
            // Solution below adapted from
            // https://stackoverflow.com/questions/5585779/how-do-i-convert-a-string-to-an-int-in-java
            int index = Integer.parseInt(input.split(" ")[1]) - 1;
            return new MarkCommand(index);
        } else if (input.startsWith("unmark ")) {
            int index = Integer.parseInt(input.split(" ")[1]) - 1;
            return new UnmarkCommand(index);
        } else if (input.startsWith("todo ")) {
            if (input.split(" +").length < 2) {
                throw new BibotException("Please write the task description!");
            } else {
                String description = input.replaceFirst("todo +", "");
                ToDo todo = new ToDo(description);
                return new AddCommand(todo);
            }
        } else if (input.startsWith("deadline ")) {
            String[] splitCommand = input.split(" /by ");
            if (splitCommand.length != 2) {
                throw new BibotException("Please use this format:\n     deadline [description] /by [datetime]");
            } else {
                String description = splitCommand[0].replaceFirst("deadline ", "");
                String date = splitCommand[1];
                Deadline deadline = new Deadline(description, date);
                return new AddCommand(deadline);
            }

        } else if (input.startsWith("event ")) {
            String[] splitCommand = input.split(" /from ");
            if (splitCommand.length != 2 || splitCommand[1].split(" /to ").length != 2) {
                throw new BibotException(
                        "Please use this format:\n     event [description] /from [datetime] /to [datetime]");
            } else {
                String description = splitCommand[0].replaceFirst("event ", "");
                String startDate = splitCommand[1].split(" /to ")[0];
                String endDate = splitCommand[1].split(" /to ")[1];
                Event event = new Event(description, startDate, endDate);
                return new AddCommand(event);
            }

        } else if (input.startsWith("delete ")) {
            String[] splitCommand = input.split(" +");
            if (splitCommand.length != 2) {
                throw new BibotException("Please specify the index to delete!");
            } else {
                int index = Integer.parseInt(input.split(" +")[1]) - 1;
                return new DeleteCommand(index);
            }
        } else {
            throw new BibotException("I'm not familiar with that input..");
        }
    }
}
