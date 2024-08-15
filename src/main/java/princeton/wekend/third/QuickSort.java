package princeton.wekend.third;

import java.util.Arrays;

public class QuickSort {


    public static int [] sort(int[] array,int init, int end){

        int pivo = 0;

        if (end > init) {
            pivo = partition(array, init, end);

            sort(array, init, pivo - 1);
            sort(array, pivo + 1, end);
        }

        return  array;
    }

    private static int partition(int[] array, int init, int end) {

        int pivot = array[init];
        int left = init;
        int right = end;


        while (left < right) {

            while (left < right && array[left] <= pivot)
                left++;

            while (array[right] > pivot)
                right--;

            if (left < right) {
                int aux = array[left];
                array[left] = array[right];
                array[right] = aux;
            }


        }
        array[init] = array[right];
        array[right] = pivot;


        return right;
    }


    public static void main(String[] args) {

        int a[] = new int[]{9,2, 0, 3, 5, 7, 1, 4, 6, 8,11};
        sort(a, 0, a.length - 1);

        Arrays.stream(a).forEach(System.out::println);
    }

}
