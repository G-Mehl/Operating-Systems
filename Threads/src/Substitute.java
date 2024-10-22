

public class Substitute implements Runnable {

    private String line;

    public Substitute(String line) {
        this.line = line;
    }

    @Override
    public void run() {
        assignWork(line);
    }

    public static void assignWork(String line) {
        // for testing line assignment accuracy
            // System.out.println("* thread processing: " + line);

        //getting rid of spacing
        String[] letters = line.split(" ");

        // Validate that the first element contains only one character
        if (letters.length > 0 && letters[0].length() != 1) {
            System.out.println("This is an invalid token.");
            return; // Exit the method if the first element is invalid
        }

        int indexMove = 0;
        
        //iterate over first part and second part
        for (int i = 0; i < letters.length; i++) {
            //only for first character so calc indexMove
            if(i == 0) {
                indexMove = SharedAlphabet.alphabetList.indexOf(String.valueOf(letters[i]));
                indexMove = (indexMove % 10) + 2;
            }
            else if (i >= 1) {
                StringBuilder shiftedString = new StringBuilder();

                // Loop through each char in letters[1]
                for (int j = 0; j < letters[i].length(); j++) {
                    String letter = String.valueOf(letters[i].charAt(j));

                    // find current letter index in alphabet list
                    int currentIndex = SharedAlphabet.alphabetList.indexOf(letter);

                    // calc new shifted index, wrapping around if needed
                    int newIndex = (currentIndex - indexMove) % 26;
                    if (newIndex < 0) {
                        newIndex += 26; // wrapping around for negative indeces
                    }

                    shiftedString.append(SharedAlphabet.alphabetList.get(newIndex));
                }

                // Result display
                for (int k = 0; k < 30; k++) {
                    System.out.println("Substitute");
                    System.out.println(line);
                    System.out.println(shiftedString.toString());
                    System.out.println("$$$$$$$$$$$$$$$$$");
                }
            }
        }
    }
}
