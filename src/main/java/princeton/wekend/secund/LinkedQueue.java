package princeton.wekend.secund;

public class LinkedQueue {

    private Node first;
    private Node last;

    private class Node{
        String item;
        Node next;
    }

    public void enqueue(String item){

        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;

        if(isEmpty()) first = last;
        else oldLast.next =last;


    }

    public String dequeue(){

        Node oldFirst = first;
        first = last;
        return  oldFirst.item;


    }
    public boolean isEmpty(){
        return this.first == null;
    }

    public static void main(String[] args) {
        LinkedQueue l = new LinkedQueue();
        l.enqueue("1");
        l.enqueue("2");

        System.out.println(l.dequeue());
        System.out.println(l.dequeue());
    }
}
