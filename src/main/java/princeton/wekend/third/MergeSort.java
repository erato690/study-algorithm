package princeton.wekend.third;

import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class MergeSort {

    public int [] merge(int [] array){
        if(array.length <= 1)
            return array;

        int mid = array.length / 2;
        int [] listInitToMid = copyArray(0,mid,array,mid);
        int [] listMidToEnd = copyArray(mid,array.length,array,array.length-mid);

        int [] mergeMidToEnd = merge(listMidToEnd);
        int [] mergeInitToMid =  merge(listInitToMid);

        return sort(mergeInitToMid,mergeMidToEnd);

    }

    public int [] sort(final int [] initArray, final int [] endArray){

        int [] arrayMerge  = new int [initArray.length+endArray.length];

        int indexArrayInit = 0;
        int  indexArrayEnd = 0;
        int indexArrayMerge = 0;

        while (indexArrayInit < initArray.length && indexArrayEnd < endArray.length) {

            if(initArray[indexArrayInit] <= endArray[indexArrayEnd]){
                arrayMerge[indexArrayMerge] = initArray[indexArrayInit];
                indexArrayInit+=1;
            }else{

                arrayMerge[indexArrayMerge] = endArray[indexArrayEnd];
                indexArrayEnd+=1;
            }
            indexArrayMerge+=1;
        }

        while (indexArrayInit < initArray.length) {

                arrayMerge[indexArrayMerge] = initArray[indexArrayInit];
                indexArrayInit+=1;
                indexArrayMerge+=1;
        }

        while (indexArrayEnd < endArray.length) {

            arrayMerge[indexArrayMerge] = endArray[indexArrayEnd];
            indexArrayEnd+=1;
            indexArrayMerge+=1;
        }

        return arrayMerge;
    }

    private int [] copyArray(int initPosition,int endPosition, int [] array,int size){

        int  [] copy = new int [size];
        int aux = 0;
        for( int count = initPosition; count < endPosition; count++){

            copy[aux] = array[count];
            aux++;

        }
        return  copy;
    }

    public static void main(String[] args) {
        int[] arr = {8, 3, 4, 2, 1, 6, 7, 5,9};
        MergeSort m = new MergeSort();

        Arrays.stream(m.merge(arr)).forEach(System.out::println);

        StdOut.println(Character.getNumericValue('A'));
    }
}
