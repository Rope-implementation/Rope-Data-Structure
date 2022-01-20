public class Rope {
    private static final int MAX_LEAF_LEN = 6;
    Node root;

    public Rope() {
        root = new Node();
    }

    void createNewRope(Node parent, String input, int lIndex,int rIndex) {
        Node p = new Node(input);
        int leftLen = (int) Math.ceil(input.split(" ").length / 2.0);
        int rightLen = input.length() - leftLen;
        int[]x =spaceCharAt(input);
        if(x.length==0){
            parent = p;
        }
        else {
            createNewRope(parent.left,input.substring(lIndex,x[leftLen]+1),lIndex,x[leftLen]);
            createNewRope(parent.right,input.substring(x[leftLen]+1,rIndex),x[leftLen]+1,rIndex);
        }

    }


    int[] spaceCharAt(String str) {
        int j=0,x[]=new int[50];
        for(int i=0;i<str.length();i++){
            if (str.charAt(i)==' ')
                x[j++] = i;
        }
        return x;
    }

}

class Node {
    Node left, right, parent;
    String str;
    int length;

    public Node() {
        left = null;
        right = null;
        str = null;
    }

    public Node(String str) {
        left = right = null;
        length = str.length();
    }

}