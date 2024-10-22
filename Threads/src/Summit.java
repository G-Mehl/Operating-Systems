public class Summit implements Runnable {

    private String line;

    public Summit(String line) {
        this.line = line;
    }

    @Override
    public void run() {
        assignWork(line);
    }

    public static void assignWork(String line) {
        // System.out.println("*** thread processing: " + line);
        //operations will go here
    }
}
