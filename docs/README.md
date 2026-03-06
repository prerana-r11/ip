# Cosmic User Guide
Cosmic is a CLI chatbot which helps you manage tasks like todo, events and deadline.
It allows you to add, list, mark, unmark, find and delete tasks easily.

## Getting started:

1. Download the .jar file.
2. Open a terminal containing the jar file
3. The chatbot will greet you and wait for commands.


Notes about the command format:
•	Words in UPPER_CASE are the parameters to be supplied by the user.
e.g. in add n/NAME, NAME is a parameter which can be used as add n/Peter Parker.

## Command Summary

| Action | Format / Example |
|------|------|
| List tasks | `list` |
| Add todo task | `todo DESCRIPTION` |
| Add event task | `event DESCRIPTION /from START /to END` |
| Add deadline task | `deadline DESCRIPTION /by DATE` |
| Mark task | `mark TASK_INDEX` |
| Unmark task | `unmark TASK_INDEX` |
| Delete task | `delete TASK_INDEX` |
| Find task | `find KEYWORD` |
| Exit | `bye` |

## **Features:** 

# **Adding a todo task: todo**

Adds a task.

Format: todo DESCRIPTION

Examples:	

•todo read book 

•todo eat dinner

# **Adding a deadline task: deadline**

Adds a task with a deadline.

Examples:

•	deadline finish assignment /by 11 March 2026

•	deadline finish exam /by 5:30

# **Adding an event task: event**

Adds a task with a start and end date/time.

Format: event DESCRIPTION /from START /to END

Examples:

•	event team meeting /from Monday 2pm /to Monday 4pm

# **Listing all tasks: list**

Returns a list of all tasks provided by the user.

Format: list

# **Marking a task: mark**

Marks a task as done.

Format: mark TASK_INDEX

•	TASK_INDEX must be a positive integer.

Examples:

•	mark 1

•	mark 10

# **Unmarking a task: unmark**

Unmarks a task as not done.

Format: unmark TASK_INDEX

•	TASK_INDEX must be a positive integer.

Example:

•	unmark 5

# **Deleting a task: delete**

Deletes a particular task as specified by the task index provided by the user.

Format: delete TASK_INDEX

Example 

•	delete 1

•	delete 5

# **Exiting the program: bye**

Stops execution of the program with an exit message

# **Finding a keyword in the task list: find**

Returns all the tasks containing the keyword specified by the user

Format: find KEYWORD

Examples:

•	find book


