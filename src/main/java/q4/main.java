package q4;

public class main {
    public static void main(String[] args) {
        // Example usage:
        String prefix = "/Users/mohammadrababah/IdeaProjects/Q3/src/main/java/q4/";
        PopThread thread1 = new PopThread(prefix + "1831-06-01.txt");
        PopThread thread2 = new PopThread(prefix +"2003-08-27.txt");
        PopThread thread3 = new PopThread(prefix +"1961-04-12.txt");
        PopThread thread4 = new PopThread(prefix +"1972-12-11.txt");

        Thread t1 = new Thread(thread1);
        Thread t2 = new Thread(thread2);
        Thread t3 = new Thread(thread3);
        Thread t4 = new Thread(thread4);

        t1.start();
        t2.start();
        t3.start();
        t4.start();

}
}
