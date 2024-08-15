package princeton.wekend.secund;

public class LinkedStackOfStrings {


    private Node first = null;

    public void push(String item){
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;

    }

    public String  pop(){

        String item = this.first.item;
        this.first = this.first.next;

        return  item;

    }

    public boolean isEmpty(){
        return this.first == null;
    }



    public static void main(String[] args) {

    }


    private class Node{
        String item;
        Node next;
    }
}
