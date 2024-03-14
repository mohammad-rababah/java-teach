package q4;

import java.io.File;
import java.util.ArrayList;

public class main {
    public static void main(String[] args) {
        // Example usage:


        ArrayList<String> filesOne = new ArrayList<String>();
        filesOne.add("1831-06-01.txt");
        filesOne.add("2003-08-27.txt");

        ArrayList<String> filesTwo = new ArrayList<String>();
        filesTwo.add("1961-04-12.txt");
        filesTwo.add("1972-12-11.txt");

        int numAttempts = 3;

        for (int i = 0; i < numAttempts; i++) {
            System.out.println("Run: " + (i + 1));
            PopThread popRunnableOne = new PopThread(filesOne);
            PopThread popRunnableTwo = new PopThread(filesTwo);
            Thread threadOne = new Thread(popRunnableOne);
            Thread threadTwo = new Thread(popRunnableTwo);
            threadOne.start();
            threadTwo.start();
            try {
                threadOne.join();
                threadTwo.join();
            } catch (InterruptedException e) {
                e.printStackTrace();

            }
        }
    }
}
