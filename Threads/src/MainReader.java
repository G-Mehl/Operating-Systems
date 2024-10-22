
import java.awt.FileDialog;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFrame;

public class MainReader {
    public static void main(String[] args) {
        
        //Creating JFrame as the parent for the FileDialog
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation((JFrame.EXIT_ON_CLOSE));
        frame.setSize(300, 200);

        //FileDialog for .txt files in my specific directory
        FileDialog fd = new FileDialog(frame, "Choose a text file", FileDialog.LOAD);
        fd.setDirectory("C:\\Users\\gmehl\\OneDrive\\Desktop\\School\\Fall 2024\\Intro to OS\\ThreadsAssignment\\Threads\\");
        fd.setFile("*.txt");
        fd.setVisible(true);

        // Get selected file
        String filename = fd.getFile();
        String directory = fd.getDirectory();

        if(filename == null)
            System.out.println("No file has been selected.");
        else {
            String filePath = directory + filename;

            try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                StringBuilder fileContent = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    fileContent.append(line).append("\n");
                }
                // Splitting contents for thread processing
                String[] lines = fileContent.toString().split("\\n");
                List<String[]> recordsList = new ArrayList<>();
                
                for (String currentLine : lines) {
                    //trim white space first
                    currentLine = currentLine.trim();

                    // Check if line starts with '*' and process accordingly
                    if (currentLine.startsWith("*")) {
                        processLine(currentLine); // gets the right runnable;
                    }
                    else {
                        System.out.println("Line does not start with an asterisk: " + currentLine);
                    }
                }
            }
            catch (IOException e) {
                System.out.println("Error reading the file: " + e.getMessage());
            }
        }

        //disposing frame after its closed
        frame.dispose();
    }

    // Helper method to process segments based on the number of leading asterisks
    public static void processLine(String line) {
        // Regular expression to find groups of asterisks followed by some content
        Pattern pattern = Pattern.compile("(\\*+)([^*]+)"); 
        Matcher matcher = pattern.matcher(line);

        // Iterate through the matches
        while (matcher.find()) {
            String asterisks = matcher.group(1);  // The group of asterisks
            String content = matcher.group(2).trim();  // the content after the asterisks

            // get the number of asterisks
            int asteriskCount = asterisks.length();

            // Assign Runnable based on the number of asterisks
            Runnable task = null;
            switch (asteriskCount) {
                case 1:
                    task = new Substitute(content); // For *
                    break;
                case 2:
                    task = new Hill(content);       // For **
                    break;
                case 3:
                    task = new Summit(content);     // For ***
                    break;
                case 4:
                    task = new Swap(content);       // For ****
                    break;
                default:
                    System.out.println("Invalid segment: " + content);
                    continue;
            }

            // Create and start a thread for each task
            if (task != null) {
                Thread thread = new Thread(task);
                thread.start();
            }
        }
    }
}
