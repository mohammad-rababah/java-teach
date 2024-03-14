package q4;

import java.io.File;
import java.util.ArrayList;

public class main {
    public static void main(String[] args) {
        // Example usage:


        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("1831-06-01.txt");
        arrayList.add("1961-04-12.txt");

        PopThread thread1 = new PopThread(arrayList);

        ArrayList<String> arrayList2 = new ArrayList<>();
        arrayList2.add("2003-08-27.txt");

        arrayList2.add("1972-12-11.txt");

        PopThread thread2 = new PopThread(arrayList2);


        Thread t1 = new Thread(thread1);
        Thread t2 = new Thread(thread2);


        t1.start();
        t2.start();


    }
}
