import java.util.ArrayList;
import java.util.List;

public class Swap implements Runnable {

    private String line;

    public Swap(String line) {
        this.line = line;
    }

    @Override
    public void run() {
        assignWork(line);
    }

    public static void assignWork(String line) {
        // Split input line into numbers
        String[] numStrings = line.split(" ");
        int totalSum = 0;
    
        // Calculate total sum of numbers
        for (String num : numStrings) {
            totalSum += Integer.parseInt(num);
        }
    
        // Check for negative total sum
        if (totalSum < 0) {
            System.out.print("Should not apply a mod function to negative numbers.");
        } 
        else {
            List<Integer> integerResults = new ArrayList<>();
    
            // Compute post-modulo values
            for (String num : numStrings) {
                int number = Integer.parseInt(num);
                integerResults.add((totalSum - number) % 26);
            }
    
            List<String> alphabetResult = new ArrayList<>();
    
            // Map integers to alphabet
            for (int index : integerResults) {
                if (index >= 0 && index < SharedAlphabet.alphabetList.size()) {
                    alphabetResult.add(SharedAlphabet.alphabetList.get(index));
                } else {
                    System.out.println("Invalid index: " + index);
                }
            }
    
            // Rotate results by 2 positions
            int rotation = 2;
            List<String> rotatedResults = new ArrayList<>();
            rotatedResults.addAll(alphabetResult.subList(rotation, alphabetResult.size()));
            rotatedResults.addAll(alphabetResult.subList(0, rotation));
    
            // Result display
            for (int i = 0; i < 30; i++) {
                System.out.println("Swap");
                System.out.println(line);
                System.out.println(rotatedResults);
                System.out.println("&&&&&&&&&&&&&&&&&&&");    
            }
        }
    }
}
