package princeton.wekend.first.union;

import edu.princeton.cs.algs4.StdOut;

public class UnionWeightedUF {


    private int [] id;
    private int[]  treeSize;


    public UnionWeightedUF(int size){

        id = new int [size];
        treeSize = new int [size];
        init(id);
        init(treeSize);

    }

    private void init( int [] itens){
        for(int count = 0 ; count < itens.length;count++){
            itens[count] =count;
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

    public int find(int point){

        int auxPoint = point;
        int maxPoint = 0;

        while ( auxPoint != id[auxPoint]) {
            auxPoint = id[auxPoint];

            if(maxPoint < auxPoint)
                maxPoint = auxPoint;
        }


        return maxPoint;
    }
    public void remove(int x){
        union(x, x+1);

    }

    public int successor(int x){
        return id[(searchRoot(x+1))];
    }




    public void union(int p , int q){
       int rootP = searchRoot(p);
       int rootQ = searchRoot(q);

       if(rootP == rootQ)
           return;
       if(treeSize[rootP] < treeSize[rootQ]){
           id[rootP] = rootQ;
           treeSize[rootQ] += treeSize[rootP];
       }else{
           id[rootQ] = rootP;
           treeSize[rootP] += treeSize[rootQ];
       }

    }


    public static void main(String[] args) {

        UnionWeightedUF unionWeightedUF = new UnionWeightedUF(10);
        unionWeightedUF.remove(5);
        int successor = unionWeightedUF.successor(5);

        StdOut.printf("The successor is %s",successor);
    }
}
