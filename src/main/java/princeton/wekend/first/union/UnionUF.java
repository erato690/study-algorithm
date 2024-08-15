package princeton.wekend.first.union;

import util.Stopwatch;

public class UnionUF {


    private int [] id;

    public UnionUF(int size){

        id = new int [size];
        init();
    }

    private void init(){
        for(int count = 0 ; count < id.length;count++){
            id[count] =count;
        }
    }
    public int searchRoot(int root){
         while ( root != id[root])
             root= id[root];

         return root;
    }


    public boolean connect(int p , int q){
        return searchRoot(p) == searchRoot(q);
    }

    public void union(int p , int q){
       int numberP = searchRoot(p);
       int numberQ = searchRoot(q);
       id[numberP] = numberQ;

    }


    public static void main(String[] args) {

        Stopwatch stopwatch =new Stopwatch();


        stopwatch.elapsedTime();
    }
}
