package princeton.wekend.secund;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node first;
    private Node last;
    private int sizeCount;

    public Deque() {
        sizeCount = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return sizeCount == 0;
    }

    // return the number of items on the deque
    public int size() {
        return sizeCount;
    }

    // add the item to the front
    public void addFirst(Item item) {

        validateArgumentsToAdd(item);


        Node newNode = new Node();
        newNode.item = item;
        newNode.next = first;
        newNode.prev = null;
        first = newNode;
        if (last == null) last = first;
        if (first.next != null) first.next.prev = first;


        this.sizeCount++;
    }

    // add the item to the back
    public void addLast(Item item) {

        validateArgumentsToAdd(item);


        Node newNode = new Node();
        newNode.item = item;
        newNode.next = null;
        newNode.prev = last;
        last = newNode;
        if (first == null) first = last;
        if (last.prev != null) last.prev.next = last;


        this.sizeCount++;

    }

    // remove and return the item from the front
    public Item removeFirst() {
        validEmptyDeque();

        Item removingItem = first.item;
        first = first.next;
        if (first != null) first.prev = null;
        else last = null;
        sizeCount--;
        return removingItem;
    }

    private void validEmptyDeque() {

        if (isEmpty())
            throw new NoSuchElementException("A secund.Deque esta vazia.");
    }

    private void validateArgumentsToAdd(Item item) {

        if (item == null)
            throw new IllegalArgumentException("O argumento não pode ser null.");
    }


    // remove and return the item from the back
    public Item removeLast() {

        validEmptyDeque();

        Item removingItem = last.item;
        last = last.prev;
        if (last != null) last.next = null;
        else first = null;
        sizeCount--;
        return removingItem;
    }


    private class Node {

        Item item;
        Node next;
        Node prev;
    }

    public Iterator<Item> iterator() {

        return new Iterator<Item>() {

            private Node current = first;

            public boolean hasNext() {
                return current != null;
            }

            public Item next() {

                if (current == null) {
                    throw new NoSuchElementException("Não tem elemento para chamar.");
                }
                Item item = current.item;
                current = current.next;
                return item;
            }
        };
    }

    public static void main(String[] args) {

    }
}
