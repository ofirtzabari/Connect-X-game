# 🕹️ Connect X Game

## 🎯 Overview

Welcome to **Connect X** — a Java-based implementation of the classic "Four in a Row" game, where strategy meets fun! The goal is simple yet challenging: be the first to connect a sequence of your pieces in a straight line, whether horizontally, vertically, or diagonally, on the game board.

## 🛠️ Project Structure

- **`Game.java`**: The central hub of the game. It manages the overall game flow and coordinates the actions between the players and the game board.
- **`GameBoard.java`**: The backbone of the game board. It handles the board's state, manages moves, and checks for win conditions or draws.
- **`HumanPlayer.java`**: A class representing a human player. It captures and processes user input to make moves on the board.
- **`MachinePlayer.java`**: This class embodies the AI player, which can make automated decisions and moves during the game.
- **`Player.java`**: An abstract class that serves as the blueprint for both human and machine players, defining shared behaviors and functionalities.

## 🚀 Getting Started

### Prerequisites

- Ensure you have Java Development Kit (JDK) installed on your system.

### How to Run

1. **Compile the Java files**: Open your terminal, navigate to the project directory, and run:
   ```bash
   javac Game.java GameBoard.java HumanPlayer.java MachinePlayer.java Player.java

2. Start the game: Execute the following command to play
    ```
    java Game
## 🎮 Gameplay Instructions

- **Starting the Game:** The game initializes a fresh game board and prepares the players for a head-to-head match.
- **Making Moves:** Players take turns dropping their pieces ('X' for player 1, 'O' for player 2) into the columns of the board.
- **Winning the Game:** The first player to align a sequence of their pieces in a row (horizontally, vertically, or diagonally) wins. If the board fills up with no winning sequence, the game ends in a draw.
- **Customization:** You can modify the game's rules, such as the board size or the number of connected pieces required to win, by tweaking the parameters in the GameBoard class.

## 🌟 Features

- Human vs. Human: Play against a friend on the same machine.
- Human vs. AI: Challenge the machine player and test your skills against different levels of AI difficulty.
- Customizable Board: Easily modify the game to create different board sizes or victory conditions.

## 🚧 Future Enhancements

- Graphical User Interface (GUI): Add a sleek GUI for a more interactive experience.
- Enhanced AI: Develop different difficulty levels for the machine player, from beginner to expert.
- Multiplayer Mode: Implement online multiplayer functionality to compete with friends over the network.

### 📄 License

This project is licensed under the MIT License. Feel free to use, modify, and distribute as you see fit.
