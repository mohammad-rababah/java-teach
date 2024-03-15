package org.example;

public class Main {
    public static void main(String[] args) {
        SortedLinkedList list = new SortedLinkedList();
        list.print();

        list.add(new Node("Charlie"));
        list.add(new Node("Alpha"));
        list.orderDescending();
        list.add(new Node("Bravo"));
        list.add(new Node("Delta"));
        list.print();
    }

}