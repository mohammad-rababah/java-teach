package org.example;

public class Main {
    public static void main(String[] args) {
        SortedLinkedList list = new SortedLinkedList();
        list.add("Bob");
        list.add("Alice");
        list.add("David");
        list.add("Charlie");
        list.remove("Charlie");
        list.orderDescending();
        list.print();
    }

}