package basic;

import java.util.Arrays;

public class QuickiSort {



     public int pivoMediano(int[] array,int indiceInicio, int indiceFim){

        return array.length  / 2  % 3 == 0 ?  array.length  / 2 : array.length  / 2 +1;

     }

    public int particionamento(int[] array, int indiceInicio, int indiceFim) {

        int pivo = array[pivoMediano(array,indiceInicio,indiceFim)];
        int i = indiceInicio;

        for (int count = indiceInicio + 1; count < indiceFim; count++) {
            if (array[count] <= pivo) {
                i+=1;
                troca(array,i,count);
            }
        }

        troca(array,indiceInicio,i);

        return i;

    }

   public int[] quickSort (int [] array, int indiceInicio, int indiceFim){

        if(indiceInicio < indiceFim){
            int indexPivot = particionamento(array,indiceInicio,indiceFim);
            quickSort(array,indiceInicio,indexPivot-1);
            quickSort(array,indexPivot+1,indiceFim);
        }


        return array;
    }

    public void troca(int [] array, int indice1, int indice2  ){
        int aux = 0;
        aux = array[indice1];
        array[indice1] = array[indice2];
        array[indice2] = aux;

    }

    public static void main(String[] args) {
        QuickiSort quickiSort = new QuickiSort();
        int [] array = new int[]{3,8,7,10,0,23,2,1,77,7};
        Arrays.stream(quickiSort.quickSort(array, 0, array.length)).forEach(System.out::println);
    }
}
