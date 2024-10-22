import java.util.Arrays;
import java.util.List;

public class SharedAlphabet {
    // Static alphabet list that is shared among all threads
    public static final List<String> alphabetList = Arrays.asList(
        "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
        "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"
    );
}
