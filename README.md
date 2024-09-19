# Socket-Based Command Line Chat App

## Overview

The **Socket-Based Command Line Chat App** is a network chat application that allows users to communicate through a command-line interface. The application supports user management, private and public rooms, message history, and various commands for interacting with the chat system.

## Features

### User Management

### Private Rooms
- **Send Private Messages**: Users can send private messages to each other.

### Public Rooms
- **Join/Leave Rooms**: Users can join or leave public chat rooms.

### Message History
- **Manage Message History**: Maintains a history of messages for reference.

### Command Line Interpreter
The application supports the following commands:
- `/login username` - Login with a specified username.
- `/rooms` - List all available rooms.
- `/join room_name` - Join a specified room.
- `/users` - List users in the current scope.
- `/leave` - Leave the current room.
- `/public room_name` - Send a public message to a specified room.
- `/private room_name` - Send a private message to a specified room.
- `/invite username` - Invite a user to a room.
- `/quit` - Exit the application.
- `/whoami` - Display the current user's username.
- `/whereami` - Show the current room.


### Design Patterns
- **Observer Pattern**: Used for notifying users about changes in room status.
- **Command Pattern**: Used for handling user commands.
- **Singleton Pattern**: Applied to manage chat rooms.

## Getting Started

To run the Socket-Based Command Line Chat App, follow these steps:

1. **Clone the Repository**:
   
   ```sh
   git clone https://github.com/Abduh-Belhaje/Socket-Based-Command-Line-Chat-App.git
