# Jason Task Manager - User Guide

## Table of Contents

- [Introduction](#introduction)
- [Getting Started](#getting-started)
- [Features Overview](#features-overview)
- [Commands Reference](#commands-reference)
- [Task Types](#task-types)
- [Advanced Features](#advanced-features)
- [Storage and Data Persistence](#storage-and-data-persistence)
- [Troubleshooting](#troubleshooting)

## Introduction

Jason is a personal task management application built with JavaFX that helps you organize and track your daily tasks. The application features a clean graphical user interface with a chat-like interaction model, making task management intuitive and user-friendly.

## Getting Started

### System Requirements

- Java Runtime Environment (JRE) 8 or higher
- JavaFX libraries

### Launching Jason

1. Execute the compiled JAR through after entering the ip folder
   '''
   cd build
   cd libs
   java -jar jason.jar
   '''
2. The application window will open with Jason's greeting message
3. Start typing commands in the text input field at the bottom
4. Press Enter or click the Send button to execute commands

### User Interface Layout

The Jason application features a clean, chat-style interface with the following components:

- **Main Window**: 600px height × 400px width application window
- **Dialog Container**: Scrollable chat area showing conversation history
  - User messages appear on the right with user avatar
  - Jason's responses appear on the left with Jason's avatar
  - Automatic scrolling to show latest messages
- **Input Area**: Bottom section for entering commands
  - Text input field (324px wide) for typing commands
  - Send button (76px wide) to execute commands
  - Commands can be executed by pressing Enter or clicking Send

### First Steps

When you first launch Jason, you'll see:

```
Hello! I'm Jason
What can I do for you?
```

The interface displays this greeting message with Jason's avatar on the left side. You can immediately start adding tasks or exploring the available commands by typing in the input field at the bottom.

## Features Overview

Jason supports the following core features:

- **Task Creation**: Create todos, deadlines, and events
- **Task Management**: Mark tasks as done/undone, delete tasks
- **Task Organization**: List all tasks, search for specific tasks
- **Task Scheduling**: Snooze tasks to a future date
- **Data Persistence**: Automatically saves tasks to local storage
- **Intuitive GUI**: Chat-like interface with user and assistant dialog boxes

## Commands Reference

### Basic Commands

#### `list`

Displays all tasks in your task list with their current status.

```
list
```

**Output Example:**

```
Here are the tasks in your list:
1. [T][ ] Buy groceries
2. [D][X] Submit report (by: Dec 25 2024)
3. [E][ ] Team meeting (from: Jan 1 2025 to: Jan 2 2025)
```

#### `bye`

Exits the application with a farewell message.

```
bye
```

### Task Creation Commands

#### `todo`

Creates a simple task without any date constraints.

```
todo <description>
```

**Examples:**

```
todo Buy groceries
todo Call dentist
todo Read chapter 5
```

**Output:**

```
Got it. I've added this task:
  [T][ ] Buy groceries
Now you have 3 tasks in the list.
```

#### `deadline`

Creates a task with a specific deadline using the `/by` keyword.

```
deadline <description> /by <date>
```

**Examples:**

```
deadline Submit assignment /by 2024-12-25
deadline Pay bills /by Dec 15 2024
deadline Complete project /by 2024-01-30
```

**Supported Date Formats:**

- `YYYY-MM-DD` - ISO date format (e.g., 2024-12-25)
- Any other text will be stored as-is (e.g., "next Friday")

#### `event`

Creates a task that spans a time period using `/from` and `/to` keywords.

```
event <description> /from <start_date> /to <end_date>
```

**Examples:**

```
event Conference /from 2024-12-20 /to 2024-12-22
event Vacation /from Mon 2pm /to Fri 5pm
event Workshop /from tomorrow /to 2024-12-30
```

### Task Management Commands

#### `mark`

Marks a task as completed.

```
mark <task_number>
```

**Example:**

```
mark 1
```

**Output:**

```
	Nice! I've marked this task as done:
[T][X] Buy groceries
```

#### `unmark`

Marks a task as not completed.

```
unmark <task_number>
```

**Example:**

```
unmark 1
```

**Output:**

```
	OK, I've marked this task as not done yet:
[T][ ] Buy groceries
```

#### `delete`

Permanently removes a task from your list.

```
delete <task_number>
```

**Example:**

```
delete 2
```

**Output:**

```
Got it. I've removed this task:
  [D][X] Submit report (by: Dec 25 2024)
Now you have 2 tasks in the list.
```

### Search and Filter Commands

#### `find`

Searches for tasks containing a specific keyword (case-insensitive).

```
find <keyword>
```

**Examples:**

```
find meeting
find buy
find report
```

**Output Example:**

```
Here are the matching tasks in your list:
1. [E][ ] Team meeting (from: Jan 1 2025 to: Jan 2 2025)
2. [E][ ] Client meeting (from: Jan 5 2025 to: Jan 5 2025)
```

### Advanced Features

#### `snooze`

Postpones a task until a specified date. Snoozed tasks will display their snooze status.

```
snooze <task_number> <date>
```

**Examples:**

```
snooze 1 tomorrow
snooze 3 2024-12-30
snooze 2 today
```

**Output:**

```
Got it. I've snoozed this task until Dec 30 2024:
[T][ ] Buy groceries (snoozed until Dec 30 2024)
```

## Task Types

### Todo Tasks `[T]`

Simple tasks without date constraints. Perfect for general reminders and to-do items.

- **Format**: `[T][status] description`
- **Example**: `[T][ ] Buy groceries`

### Deadline Tasks `[D]`

Tasks with a specific due date. Ideal for assignments, bill payments, and time-sensitive activities.

- **Format**: `[D][status] description (by: date)`
- **Example**: `[D][X] Submit report (by: Dec 25 2024)`

### Event Tasks `[E]`

Tasks that span a time period. Perfect for meetings, appointments, and scheduled events.

- **Format**: `[E][status] description (from: start_date to: end_date)`
- **Example**: `[E][ ] Conference (from: Dec 20 2024 to: Dec 22 2024)`

### Task Status Indicators

- `[ ]` - Task not completed
- `[X]` - Task completed
- `(snoozed until date)` - Task is snoozed

## Storage and Data Persistence

### Automatic Saving

Jason automatically saves your tasks to a local file (`./data/Jason.txt`) whenever you:

- Add a new task
- Mark/unmark a task
- Delete a task
- Snooze a task

### Data Format

Tasks are stored in a pipe-delimited format:

```
T | 0 | Buy groceries | null
D | 1 | Submit report | Dec 25 2024 | null
E | 0 | Conference | Dec 20 2024-Dec 22 2024 | null
```

### Data Recovery

If the data file becomes corrupted or is deleted, Jason will start with an empty task list and create a new data file automatically.

## Troubleshooting

### Common Error Messages

| Error Message                               | Cause                                           | Solution                                                   |
| ------------------------------------------- | ----------------------------------------------- | ---------------------------------------------------------- |
| "Todo cannot be empty!"                     | You entered `todo` without any description      | Add a description: `todo Buy milk`                         |
| "Give me a deadline! Include /by"           | Deadline command missing `/by` keyword          | Use proper format: `deadline Submit report /by 2024-12-25` |
| "Give me a duration! Include /from and /to" | Event command missing `/from` or `/to` keywords | Use proper format: `event Meeting /from 2pm /to 4pm`       |
| "Please specify task number"                | Command missing task number                     | Include a valid task number: `mark 1`                      |
| "Give me a valid task number"               | Task number doesn't exist or isn't valid        | Use `list` to see available task numbers                   |
| "Invalid date format"                       | Date couldn't be parsed                         | Use: `today`, `tomorrow`, or `YYYY-MM-DD`                  |

### Tips for Best Experience

1. **Use `list` frequently** to see your current tasks and their numbers
2. **Check date formats** when creating deadlines and events
3. **Use descriptive task names** to make searching easier
4. **Regular cleanup** - delete completed tasks you no longer need
5. **Use snooze wisely** - snooze tasks to dates when you can actually work on them
