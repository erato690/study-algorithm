package princeton.wekend.secund;

public class QueueArray {


    private int head;
    private int tail;

    private String[] items;

    public QueueArray() {
        items = new String[1];
        head = 0;
        tail = 0;
    }

    public void enqueue(String item) {


        if (isEmpty()) {
            items[head] = item;
            tail++;
        } else {
            items[tail++] = item;
        }

        if (tail == items.length && head <=  (tail / 4)) {
            int lastTail = tail;
            this.rezise(items.length * 2);
            tail = lastTail - head;

        }

        if (head > (tail / 4)) {
            reziseDown(items.length /2);
            head = 0;
            tail = items.length;
        }


    }

    public boolean isEmpty() {
        return items[head] == null;
    }

    public String dequeue() {
        String item = items[head];
        items[head] = null;
        head++;
        return item;
    }

    private void rezise(int size) {

        String[] itemsNew = new String[size];


        for (int countCopy = 0; countCopy < items.length; countCopy++) {
            itemsNew[countCopy] = items[countCopy];
        }
        this.items = itemsNew;

        itemsNew = null;
    }

    private void reziseDown(int size) {

        String[] itemsNew = new String[size];

        int auxHead = head;
        for (int countCopy = 0; countCopy < size; countCopy++) {
            itemsNew[countCopy] = this.items[auxHead];
            auxHead++;
            tail--;
        }

        this.items = itemsNew;

        itemsNew = null;
    }

    public static void main(String[] args) {
        QueueArray l = new QueueArray();
        l.enqueue("1");
        l.enqueue("2");
        System.out.println(l.dequeue());
        l.enqueue("3");
        System.out.println(l.dequeue());
        l.enqueue("4");
        l.enqueue("5");
        l.enqueue("6");
        l.enqueue("7");
        System.out.println(l.dequeue());
        l.enqueue("8");
    }
}
