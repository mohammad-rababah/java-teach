package org.example;

public class SortedLinkedList implements SortedList {
    private Node head;

    SortedLinkedList() {
        head = null;
    }

    @Override
    public int size() {
        if (head == null) {
            return 0;
        }
        if (head.getNext() == null) {
            return 1;
        }
        int count = 1;
        Node current = head;
        while (current.getNext() != null) {
            current = current.getNext();
            count++;
        }
        return count;
    }

    @Override
    public void add(String string) {
        Node newNode = new Node(string);
        add(newNode);
    }

    @Override
    public void add(Node node) {
        if (head == null) {
            head = node;
        } else if (head.getString().compareTo(node.getString()) > 0) {
            node.setNext(head);
            head.setPrev(node);
            head = node;
        } else {
            Node current = head;
            while (current.getNext() != null && current.getNext().getString().compareTo(node.getString()) < 0) {
                current = current.getNext();
            }

            node.setNext(current.getNext());
            if (current.getNext() != null) {
                current.getNext().setPrev(node);
            }
            current.setNext(node);
            node.setPrev(current);
        }
    }

    @Override
    public Node getFirst() {
        return head;
    }

    @Override
    public Node getLast() {
        if (head == null) {
            return null;
        }
        if (head.getNext() == null) {
            return head;
        }
        Node current = head;
        while (head.getNext() != null) {
            current = current.getNext();
        }

        return current;
    }

    @Override
    public Node get(int index) {
        if (index >= size()) {
            return null;
        }
        if (index == 0) {
            return head;
        }
        int counter = 1;
        Node current = head;
        while (current.getNext() != null) {
            current = current.getNext();
            if (counter == index) {
                return current;
            }
            counter++;
        }
        return null;
    }

    @Override
    public boolean isPresent(String string) {
        return false;
    }

    @Override
    public boolean removeFirst() {
        if (head == null) {
            return false;
        }
        if (head.getNext() == null) {
            head = null;
            return true;
        }
        Node nextNode = head.getNext();
        nextNode.setPrev(null);
        head = nextNode;
        return true;
    }

    @Override
    public boolean removeLast() {
        return false;
    }

    @Override
    public boolean remove(int index) {
        return false;
    }

    @Override
    public boolean remove(String string) {
        return false;
    }

    @Override
    public void orderAscending() {

    }

    @Override
    public void orderDescending() {

    }

    @Override
    public void print() {
        Node current = head;
        while (current != null) {
            System.out.println(current.getString());
            current = current.getNext();
        }
    }


//    private Node head;
//
//    SortedLinkedList() {
//        head = null;
//    }
//
//    void add(String name) {
//        Node newNode = new Node(name);
//        if (head == null) {
//            head = newNode;
//        } else if (head.getString().compareTo(name) > 0) {
//            newNode.setNext(head);
//            head.setPrev(newNode);
//            head = newNode;
//        } else {
//            Node current = head;
//            while (current.getNext() != null && current.getNext().getString().compareTo(name) < 0) {
//                current = current.getNext();
//            }
//
//            newNode.setNext(current.getNext());
//            if (current.getNext() != null) {
//                current.getNext().setPrev(newNode);
//            }
//            current.setNext(newNode);
//            newNode.setPrev(current);
//        }
//    }
//
//    void remove(String name) {
//        Node current = head;
//        while (current != null) {
//            if (current.getString().equals(name)) {
//                if (current.getPrev() != null) {
//                    current.getPrev().setNext(current.getNext());
//                } else {
//                    head = current.getNext();
//                }
//                if (current.getNext() != null) {
//                    current.getNext().setPrev(current.getPrev());
//                }
//                return;
//            }
//            current = current.getNext();
//        }
//    }
//
//    void print() {
//        Node current = head;
//        while (current != null) {
//            System.out.println(current.getString());
//            current = current.getNext();
//        }
//    }

}
