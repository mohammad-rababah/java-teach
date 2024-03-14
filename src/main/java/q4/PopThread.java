package q4;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class PopThread implements Runnable {
    private static final Object lock = new Object();
    private static final AtomicInteger fileCounter = new AtomicInteger(1);
    private final ArrayList<String> filenames;
    static final String filePath = "src/main/java/q4";

    public PopThread(ArrayList<String> filenames) {
        this.filenames = filenames;
        int[] counts = new int[filenames.size()];
        try { // Read the file content and find the file label

            for (int i = 0; i < filenames.size(); i++) {
                String currentDir = System.getProperty("user.dir");
                String fullPath = Paths.get(currentDir, filePath, filenames.get(i)).toString();
                BufferedReader reader = new BufferedReader(new FileReader(fullPath));
                String line;
                String fileLabel = null;


                while ((line = reader.readLine()) != null) {
                    if (line.startsWith("#")) {
                        fileLabel = line.substring(1).trim();
                        break;
                    }
                }
                counts[i] = Integer.parseInt(fileLabel.split("/")[0]);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


        for (int i = 0; i < counts.length; i++) {
            int min = counts[i];
            int minIndex = i;
            for (int j = i + 1; j < counts.length; j++) {
                if (counts[j] < min) {
                    min = counts[j];
                    minIndex = j;
                }
            }
            String temp = filenames.get(i);
            filenames.set(i, filenames.get(minIndex));
            filenames.set(minIndex, temp);
        }
    }

    @Override
    public void run() {
        try {
            for (String filename : filenames) {
                String currentDir = System.getProperty("user.dir");
                String fullPath = Paths.get(currentDir, filePath, filename).toString();
                BufferedReader reader = new BufferedReader(new FileReader(fullPath));
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
                        while (fileCounter.get() != count) {
                            lock.wait(); // Wait for the file to be processed
                        }
                        String resultPath = Paths.get(currentDir, filePath, "result.txt").toString();
                        reader = new BufferedReader(new FileReader(fullPath));

                        BufferedWriter writer = new BufferedWriter(new FileWriter(resultPath, true));
                        while ((line = reader.readLine()) != null) {
                            writer.write(line);
                            writer.newLine();
                        }
                        writer.close();
                        fileCounter.incrementAndGet();
                        lock.notifyAll(); // Notify waiting threads
                    }
                }
                reader.close();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
