# Bibot

Bibot is a friendly chatbot application that helps you manage your tasks!

<img width="412" height="624" alt="Ui" src="https://github.com/user-attachments/assets/e263563d-c702-4a95-a255-43d503d3a4e1" />

* [Quick Start](#quick-start)
* [Features](#features)
* [Command Summary](#command-summary)
* [Credits](#credits)

## Quick Start

1. Ensure you have Java `17` installed
2. Download `bibot.jar` from [the most recent release](https://github.com/Gallardo166/ip/releases)
3. `cd` into the directory where it is installed
4. Run `java -jar bibot.jar`

## Features

* Create todos, deadlines, and scheduled events.
* Track task completion status.
* Search tasks by keyword.
* Receive reminders for upcoming deadlines and events.
* Simple command-based interaction.

## Command Summary

### Task Creation

* `todo DESCRIPTION`
  Create a ToDo task with a description.

* `deadline DESCRIPTION /by DD/MM/YYYY HHMM`
  Create a Deadline task with a description and a deadline.

* `event DESCRIPTION /from DD/MM/YYYY HHMM /to DD/MM/YYYY HHMM`
  Create an Event with a description and start and end time.

### Task Management

* `list`
  Show all tasks.

* `mark INDEX`
  Mark a task as completed.

* `unmark INDEX`
  Mark a task as not completed.

* `delete INDEX`
  Remove a task at the specified index.

### Search & Reminders

* `find KEYWORD`
  Find tasks with description containing the keyword.

* `remind`
  Show Deadlines with deadline dates and Events with start dates occurring within the next 24 hours.

### Exit

* `bye`
  Exit the application.

## Credits

- Profile pictures: <a href="https://www.vecteezy.com/free-vector/cute">Cute Vectors by Vecteezy</a>
   - Robot picture by [Klik Art Studio](https://www.vecteezy.com/members/klikart_studio)
   - User picture by [Idalba Granada](https://www.vecteezy.com/members/studiogstock)
- User Guide creation aided by [ChatGPT](https://chatgpt.com/share/6997396c-4d88-800c-b5e3-e85ddce2b8f8)
