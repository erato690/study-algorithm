package princeton.wekend.secund.plus;

import java.util.Arrays;

public class InsertionSort {

    public static int[] sort(int[] numbers) {

        int lengthNumbers = numbers.length;

        for (int count = 0; count < numbers.length; count++) {

            for (int countSwap = count; countSwap > 0; countSwap--) {

                if (numbers[countSwap] < numbers[countSwap-1]) {
                    int aux = numbers[countSwap-1];
                    numbers[countSwap-1] = numbers[countSwap];
                    numbers[countSwap] = aux;

                }
            }
        }


        return numbers;


    }

    public static void main(String[] args) {

        var sort = InsertionSort.sort(new int[]{24, 10, 17, 9, 5, 9, 1, 23, 300});


        Arrays.stream(sort).forEach(System.out::println);
    }

}
