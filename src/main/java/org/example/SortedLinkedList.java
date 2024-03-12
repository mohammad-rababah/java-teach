package org.example;

public class SortedLinkedList implements SortedList {
    private Node head;
    private boolean isAscending = true;
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
        String nodeName = node.getString().toLowerCase();
        if (head == null) {
            head = node;
        } else if (head.getString().toLowerCase().compareTo(nodeName) > 0) {
            node.setNext(head);
            head.setPrev(node);
            head = node;
        } else if (head.getString().toLowerCase().compareTo(nodeName) == 0) {
            return;
        } else {
            Node current = head;
            while (current.getNext() != null && current.getNext().getString().toLowerCase().compareTo(nodeName) < 0) {
                current = current.getNext();
            }
            if (current.getNext() != null && current.getNext().getString().toLowerCase().compareTo(nodeName) == 0) {
                return;
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
        Node current = head;
        while (current != null) {
            if (current.getString().equalsIgnoreCase(string)) {
                return true;
            }
            current = current.getNext();
        }
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
        if (head == null) {
            return false;
        }
        if (head.getNext() == null) {
            head = null;
            return true;
        }
        Node current = head;
        while (current.getNext() != null) {
            current = current.getNext();
        }
        Node prevNode = current.getPrev();
        prevNode.setNext(null);
        return true;

    }

    @Override
    public boolean remove(int index) {
        if (index >= size()) {
            return false;
        }
        if (index == 0) {
            return removeFirst();
        }
        int counter = 1;
        Node current = head;
        while (current.getNext() != null) {
            current = current.getNext();
            if (counter == index) {
                Node prevNode = current.getPrev();
                Node nextNode = current.getNext();
                prevNode.setNext(nextNode);
                if (nextNode != null) {
                    nextNode.setPrev(prevNode);
                }
                return true;
            }
            counter++;
        }
        return false;
    }

    @Override
    public boolean remove(String string) {
        Node current = head;
        while (current != null) {
            if (current.getString().toLowerCase().equals(string.toLowerCase())) {
                if (current.getPrev() != null) {
                    current.getPrev().setNext(current.getNext());
                } else {
                    head = current.getNext();
                }
                if (current.getNext() != null) {
                    current.getNext().setPrev(current.getPrev());
                }
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    @Override
    public void orderAscending() {
        if (isAscending) {
            return;
        }
        isAscending = true;
        Node current = head;
        while (current.getNext() != null) {
            current = current.getNext();
        }
        head = current;
        while (current != null) {
            Node prevNode = current.getPrev();
            Node nextNode = current.getNext();
            current.setNext(prevNode);
            current.setPrev(nextNode);
            current = prevNode;
        }


    }

    @Override
    public void orderDescending() {
        if (!isAscending) {
            return;
        }
        isAscending = false;
        Node current = head;
        while (current.getNext() != null) {
            current = current.getNext();
        }
        head = current;
        while (current != null) {
            Node prevNode = current.getPrev();
            Node nextNode = current.getNext();
            current.setNext(prevNode);
            current.setPrev(nextNode);
            current = prevNode;
        }

    }

    @Override
    public void print() {
        Node current = head;
        while (current != null) {
            System.out.println(current.getString());
            current = current.getNext();
        }
    }



}
