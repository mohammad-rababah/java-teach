package q4;

import java.io.*;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.System.*;

public class PopThread implements Runnable {
    private static final Object lock = new Object();
    private static final AtomicInteger fileCounter = new AtomicInteger(1);
    private final String filename;

    public PopThread(String filename) {
        this.filename = filename;
    }

    @Override
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            String fileLabel = null;

            // Read the file content and find the file label
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("#")) {
                    fileLabel = line.substring(1).trim();
                    break;
                }
            }

            // If file label found, append content to result.txt in order
            if (fileLabel != null) {
                synchronized (lock) {
                    int count = Integer.parseInt(fileLabel.split("/")[0]);
                    System.out.println("count: " + count);
                    while (fileCounter.get() != count) {
                        lock.wait(); // Wait for the correct order
                    }
                    String prefix = "/Users/mohammadrababah/IdeaProjects/Q3/src/main/java/q4/";

                    BufferedWriter writer = new BufferedWriter(new FileWriter(prefix + "result.txt", true));
                    reader = new BufferedReader(new FileReader(filename));
                    while ((line = reader.readLine()) != null) {
                        System.out.println("Writing: " + line);
                        writer.write(line);
                        writer.newLine();
                    }
                    writer.close();
                    fileCounter.incrementAndGet();
                    lock.notifyAll(); // Notify waiting threads
                }
            }
            reader.close();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }


}
