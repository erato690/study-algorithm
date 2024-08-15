package princeton.wekend.secund;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private int tail;
    private int sizeCount;
    private Item[] items;


    //@SuppressWarnings("unchecked")
    public RandomizedQueue() {
        items = (Item[]) new Object[1];
        sizeCount = 0;
        tail = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return sizeCount == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return sizeCount;
    }

    // add the item
    public void enqueue(Item item) {

        if (item == null)
            throw new IllegalArgumentException("O paramentro não pode ser null");


        if (size() == items.length || tail == items.length) {
            this.rezise(items.length * 2);
        }
        sizeCount = sizeCount + 1;
        items[tail++] = item;

    }

    //@SuppressWarnings("unchecked")
    private void rezise(int size) {

        Item[] itemsNew = (Item[]) new Object[size];

        int i = 0;
        for (int j = 0; i < sizeCount; j++) {
            if (items[j] != null) {
                itemsNew[i] = items[j];
                i++;
            }
        }
        tail = sizeCount;
        this.items = itemsNew;
    }


    // remove and return a random item
    public Item dequeue() {

        int size = this.size();
        if (size == 0) {
            throw new NoSuchElementException("Queue is empty");
        }

        // get item from random index
        int rand = StdRandom.uniformInt(0, this.tail);

        while (this.items[rand] == null) {
            if (rand + 1 < tail && this.items[rand + 1] != null) {
                rand = rand + 1;
                break;
            }
            if (rand - 1 > 0 && this.items[rand - 1] != null) {
                rand = rand - 1;
                break;
            }
            rand = StdRandom.uniformInt(0, tail);
        }

        var item = items[rand];

        items[rand] = null;
        sizeCount = sizeCount - 1;


        if (sizeCount < (items.length / 4)) {
            rezise(items.length / 2);
        }

        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {

        if (isEmpty())
            throw new NoSuchElementException("A lista esta vazia.");

        int rand = StdRandom.uniformInt(0, tail);
        while (this.items[rand] == null) {
            if (rand + 1 < tail && this.items[rand + 1] != null) {
                rand = rand + 1;
                break;
            }
            if (rand - 1 > 0 && this.items[rand - 1] != null) {
                rand = rand - 1;
                break;
            }
            rand = StdRandom.uniformInt(0, tail);
        }

        return this.items[rand];
    }

    private class RandomizedQueueIterator implements Iterator<Item> {

        private final Item[] currentArray;
        private int curr;

        /**
         * Construct iterator for randomized queue.
         */
        RandomizedQueueIterator() {
            currentArray = (Item[]) new Object[sizeCount];

            int i = 0;
            for (int j = 0; i < sizeCount; j++) {
                if (items[j] != null) {
                    currentArray[i] = items[j];
                    i++;
                }
            }

            StdRandom.shuffle(currentArray);
            curr = 0;
        }

        @Override
        public boolean hasNext() {
            return curr != currentArray.length;
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return currentArray[curr++];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }


    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    public static void main(String[] args) {

        RandomizedQueue<Integer> random = new RandomizedQueue<>();

        int count = 0;
        while (count < 100000) {
            count++;
            int probability = StdRandom.uniformInt(1, 6);

            switch (probability) {

                case 1:
                    random.enqueue(count);
                    break;
                case 2: {
                    if (!random.isEmpty())
                        System.out.println("Retirada:" + random.dequeue());

                    break;
                }
                case 3: {
                    if (!random.isEmpty())
                        System.out.println("Sample:" + random.sample());
                    break;
                }
                case 4:
                    System.out.println("Size:" + random.size());
                    break;
                case 5:
                    System.out.println("Empty:" + random.isEmpty());
                    break;
                case 6: {
                    var it = random.iterator();
                    while (it.hasNext())
                        System.out.println("It:" + it.next());

                    break;

                }


            }


        }


    }
}
