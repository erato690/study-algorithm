package princeton.wekend.first.union;

import edu.princeton.cs.algs4.StdOut;

import java.time.LocalDateTime;

public class SocialFriend {

    private  int  [] members;
    private int[]  treeSize;
    private int size;

    public SocialFriend(int sizeMembers){
        this.members = new int[sizeMembers];
        this.treeSize = new int[sizeMembers];
        init(members);
        initMember(treeSize);
        this.size = sizeMembers;
    }


    private void init( int [] itens){
        for(int count = 0 ; count < itens.length;count++){
            itens[count] = count;
        }
    }

    private void initMember( int [] itens){
        for(int count = 0 ; count < itens.length;count++){
            itens[count] = 1;
        }
    }


    public int searchRoot(int root){
        while ( root != members[root])
            root=  members[root];

        return root;
    }


    public void union(int p , int q){
        int rootP = searchRoot(p);
        int rootQ = searchRoot(q);


        if(rootP == rootQ)
            return;
        if(treeSize[rootP] > treeSize[rootQ]){
            members[rootP] =rootQ;
            treeSize[rootQ] +=1;
        }else{
            members[rootQ]=rootP;
            treeSize[rootP] += 1;

        }
        this.size --;

    }

    public void allConnected(){

        if(size-1 == 0){
            StdOut.printf("All friends are connect in  date %s", LocalDateTime.now());
        }
    }

    public static void main(String[] args) {

        SocialFriend socialFriend = new SocialFriend(6);

        socialFriend.union(1,5);
        socialFriend.union(2,4);
        socialFriend.union(1,3);
        socialFriend.union(5,2);
        socialFriend.union(0,3);
        socialFriend.union(2,1);

        socialFriend.allConnected();

    }


}
