package princeton.wekend.secund;

public class StackArray {

    private String items [];
    private int count =0;

    public StackArray(){

        items = new String[10];
    }

    public void push(String item){

        if(count == items.length){
            rezise(items.length *2);
        }
        items[count++] = item;

    }

    public String  pop(){

      int index = --count;
      String item = this.items[index];
      this.items[index] = null;
      return item;
    }


    private void rezise(int size){
        String  [] itemsNew = new String[size];

        for(int countCopy =0; countCopy < items.length;countCopy++)
            itemsNew[countCopy] = this.items[countCopy];

        this.items = itemsNew;

        itemsNew = null;
    }

    public static void main(String[] args) {

    }
}
