import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Game {
	private static Scanner s = new Scanner(System.in);

	public static void main(String[] args) {
		
		int playersChoice= getSelection("players");
		int columns = getSelection("columns");
		int rows = getSelection("rows");
		int connect = getSelection("connect");

		GameBoard g= new GameBoard(columns, rows, connect);
		Lock lock = new ReentrantLock();
		
		s.nextLine();
		System.out.println("Enter player 1 name: ");
		String name = s.nextLine();
		if (playersChoice == 3) {
			System.out.println("Enter player 2 name: ");
			name = s.nextLine();
		}
		Player p1 = new HumanPlayer(g,'x',lock,0, name);
		Player p2 = null;
		String toplay = "y";
		
		g.Print();
			
			switch (playersChoice) {
			case 1:
				p2 = new MachinePlayer(g,'0',lock,0);
				System.out.printf("user score: "+p1.GetPoints()+" other score: "+p2.GetPoints()+"\n");
				p1.start();
				p2.start(); 
				break;
			case 2:
				p2 = new MachinePlayer(g,'0',lock,0);
				System.out.printf("user score: "+p1.GetPoints()+" other score: "+p2.GetPoints()+"\n");
				p2.start();
				p1.start(); 
				break;
			case 3:
				p2 = new HumanPlayer(g,'0',lock,0, name);
				System.out.printf("user score: "+p1.GetPoints()+" other score: "+p2.GetPoints()+"\n");
				p1.start();
				p2.start();
				break;
			}
			try {
				p1.join();
				p2.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.printf("user score: "+p1.GetPoints()+" other score: "+p2.GetPoints()+"\n");
			System.out.println("do you want to play another game enter y/n: ");
			toplay=s.nextLine();
			while (!toplay.equals("y") && !toplay.equals("n")) {
				System.out.println("what? y/n:");
				toplay=s.nextLine();
			}
			int howStart = 2;  // for 2 human players
			
			while(toplay.equals("y")) {
				g = new GameBoard(columns, rows, connect);
				g.Print();
			if (playersChoice == 1) {
				p1 = new HumanPlayer(g,'x',lock,p1.GetPoints(), p1.name);
				p2 = new MachinePlayer(g,'0',lock,p2.GetPoints());
				p2.start();
				p1.start();
				playersChoice = 2;
			}
			else if(playersChoice ==2) {
				p1 = new HumanPlayer(g,'x',lock,p1.GetPoints(), p1.name);
				p2 = new MachinePlayer(g,'0',lock,p2.GetPoints());
				p1.start();
				p2.start();
				playersChoice = 1;
			}
			else if(playersChoice ==3) {
				
				p1 = new HumanPlayer(g,'x',lock,p1.GetPoints(), p1.name);
				p2 = new HumanPlayer(g,'0',lock,p2.GetPoints(), p2.name);
				if(howStart == 2) {
					p2.start();
					p1.start();
					howStart = 1;
				}
				else {
					p1.start();
					p2.start();
					howStart = 2;
				}
			}
			
			
			try {
				p1.join();
				p2.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.printf("user score: "+p1.GetPoints()+" other score: "+p2.GetPoints()+"\n");
	        
			System.out.println("do you want to play another game enter y/n:");
			
			toplay=s.nextLine();
			while (!toplay.equals("y") && !toplay.equals("n")) {
				System.out.println("what? y/n:");
				toplay=s.nextLine();
			}
		}
		// Update statistics for player 1
        updateStatisticsFile("statistics.txt", p1.name, p1.GetPoints(), p2.GetPoints(), 1);
        
        // Update statistics for player 2
        updateStatisticsFile("statistics.txt", p2.name, p2.GetPoints(), p1.GetPoints(), 1);
		System.out.println("Bye Bye!");	
	}
	
	public static int getSelection(String type) {
		boolean flag = true;
		int selection = 0;
		while (flag) {
			switch(type) {
			case "players":
				System.out.println("Welcome to Connect X in row \n"
						+ "please select number:\n"
						+ "1. play with the computer and be the first.\n"
						+ "2. play with the computer and be the second.\n"
						+ "3. play with friend\n"
						+ "your choice: ");
				break;
			case "columns":
				System.out.println("Please select the number of columns you want(at least 3): ");
				break;
			case "rows":
				System.out.println("Please select the number of rows you want(at least 2): ");
				break;
			case "connect":
				System.out.println("Please select the number of connect you want(at least 2): ");
				break;
			}
			
			try {
				selection = s.nextInt();
			}
			catch(Exception e) {
				System.out.println("wrong type, try again");
				s.nextLine();
			}
			
			if ((selection > 0 && selection < 4 && type == "players") ||
				(selection > 2 && type == "columns") ||
				(selection > 1 && type == "rows")    ||
				(selection > 1 && type == "connect") )
				flag = false;
			else
				System.out.println("The number you select is not in range, try again!");
		}
		return selection;
	}
	
	public static void updateStatisticsFile(String filename, String playerName, int wins, int losses, int totalGames) {
        Map<String, int[]> stats = new LinkedHashMap<>();
        
        // Read the existing file if it exists
        File file = new File(filename);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length == 4) {
                        String name = parts[0];
                        int[] values = new int[3];
                        values[0] = Integer.parseInt(parts[1]); // Wins
                        values[1] = Integer.parseInt(parts[2]); // Losses
                        values[2] = Integer.parseInt(parts[3]); // Total Games
                        stats.put(name, values);
                    }
                }
            } catch (IOException e) {
                System.err.println("Error reading the statistics file: " + e.getMessage());
            }
        }

        // Update the statistics for the player
        int[] playerStats = stats.getOrDefault(playerName, new int[3]);
        playerStats[0] += wins;
        playerStats[1] += losses;
        playerStats[2] += totalGames;
        stats.put(playerName, playerStats);

        // Write the updated statistics back to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Map.Entry<String, int[]> entry : stats.entrySet()) {
                writer.write(entry.getKey() + "," + entry.getValue()[0] + "," + entry.getValue()[1] + "," + entry.getValue()[2]);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to the statistics file: " + e.getMessage());
        }
    }
	
}
