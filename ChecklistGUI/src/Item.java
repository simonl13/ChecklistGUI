import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Scanner;

public class Item {

	static Scanner itemInput = new Scanner(System.in); //new Scanner
	static ArrayList<String> checkList = new ArrayList<>(); //new ArrayList for checklist.

	public void newItem(String newItem) throws IOException {
		FileWriter output = new FileWriter("checklist.txt", true); //Uses checklist.txt to score data.
		
		output.write(newItem + System.getProperty("line.separator")); //writes item to file.
		output.flush(); //flushes output for faster writing.

		checkList.add(newItem); //adds item to array in memory.
		output.close(); //closes writer.
	}
	
	public void listItems() {
		if (checkList.size() == 0) { //checks if any items exist in array.
			System.out.println("There are no checklist items yet. Type in \"new\" to make one.");
		}
		else {
			System.out.println("Checklist for Success!");
			for (int i = 0; i < checkList.size(); i++) { //displays line for each item in array.
				System.out.println("#" + i + ". " + checkList.get(i)); //prints line for each item
			}
		}
	}
	
	public void removeItem(int removedItem) throws IOException {
		removedItem -= 1;
		
		//Process to transfer existing file to temporary file in order to exclude the removed item then write to file.
		File inputFile = new File("checkList.txt");
		File tempFile = new File("myTempFile.txt");
		
		BufferedReader reader = new BufferedReader(new FileReader(inputFile));
		BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
		
		String lineToRemove = checkList.get(removedItem); //line that is supposed to be removed.
		String currentLine;
		
		while((currentLine = reader.readLine()) != null) { //if the current line is not blank, continue
			String trimmedLine = currentLine.trim(); //set deleted line to variable
			if(trimmedLine.equals(lineToRemove)) continue; //if trimmed line does not equal the removed line, continue past next line
			writer.write(currentLine + System.getProperty("line.separator")); //writes the line if it is not supposed to be removed. also adds a line separators, dependent on OS.
		}
		
		writer.close(); 
		reader.close();
		checkList.remove(removedItem);
		if(tempFile.renameTo(inputFile)) { 
			//rename for Unix-based - only works on Unix based systems.
		}
		else {
			inputFile.delete(); //if Windows, first deletes old file, then "moves" new file to old file path.
			Files.move(tempFile.toPath(), inputFile.toPath()); 
		}
		
	}

	public static void loadItems() throws IOException {
		try (BufferedReader reader = new BufferedReader(new FileReader("checklist.txt"))) { 
			String line; //sets the line content to a string.
			while ((line = reader.readLine()) !=null) { //repeats for every non-blank line in the file.
				checkList.add(line); //adds all non-blank lines to the Array.
			}
		} catch (FileNotFoundException event){
			File newFile = new File("checklist.txt"); //if file does not exist, create file.
			newFile.createNewFile();
		}
	}

	public void clearAll() throws IOException {
		File checklistFile = new File ("checklist.txt");
		checklistFile.delete();
		System.out.println("Deleted Checklist File.");
		checkList.clear();
		System.out.println("Cleared Checklist.");
	}
}
