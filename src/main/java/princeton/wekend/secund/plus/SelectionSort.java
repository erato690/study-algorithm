package princeton.wekend.secund.plus;

import java.util.Arrays;

public class SelectionSort {

    public static int []  sort(int [] numbers){

        for(int count = 0 ;count < numbers.length;count++){

            int samllPosition = count;

            for(int countSwap = count +1; countSwap < numbers.length;countSwap++){

                if(numbers[countSwap] < numbers[samllPosition]){
                    samllPosition = countSwap;
                }
            }

            if(samllPosition != count){
                int aux = numbers[samllPosition];
                numbers[samllPosition] = numbers[count];
                numbers[count] = aux;

            }
        }

        return  numbers;
    }

    public static void main(String[] args) {

      var sort =  SelectionSort.sort(new int[]{24, 10, 17, 9, 5, 9, 1, 23, 300});


      Arrays.stream(sort).forEach(System.out::println);
    }


}
