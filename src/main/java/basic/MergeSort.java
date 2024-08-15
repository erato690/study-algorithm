package basic;

import java.util.Arrays;

public class MergeSort {



    public static int [] merge(int array []){


        if(array.length > 1) {

            int array1[] = null;
            int array2[] = null;

            if ((array.length / 2) / 2  <=1) {
                array1 = Arrays.copyOfRange(array, 0, array.length / 2);
                array2 = Arrays.copyOfRange(array, (array.length / 2), array.length);
            } else {

                array1 = Arrays.copyOfRange(array, 0, (array.length / 2) + 1);
                array2 = Arrays.copyOfRange(array, (array.length / 2) + 1, array.length);
            }

            merge(array1);
            merge(array2);

            // Initial index of first subarray
            int i = 0;
            // Initial index of second subarray
            int j = 0;
            // Initial index of merged subarray
            int k = 0;

            //Aqui ocorre a divisão do problema
            while(i <  array1.length && j < array2.length){

                if(array1[i] <= array2[j]){
                    array [k] =array1[i];
                    i++;
                }else {
                    array [k] =array2[j];
                    j++;
                }

                k++;
            }

            //Resolvendo a parte esquerda.
            while (i <  array1.length){
                array [k] =array1[i];
                i++;
                k++;
            }

            //Resolvendo a parte direita.
            while (j <  array2.length){
                array [k] =array2[j];
                j++;
                k++;
            }

        }

        return array;

    }

    public  static  void main (String [] args){
        Arrays.stream(merge(new int[] {18,1,99,0})).forEach(System.out::println);
    }

}
