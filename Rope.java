public class Rope {
    private static final int MAX_LEAF_LEN = 6;
    Node root;

    public Rope(){
        root = new Node();
    }

    void createNewRope(Node root, String input,int len) {
    }

}

class Node {
    Node left, right, parent;
    String str;
    int length;

    public Node(){
        left=null;
        right=null;
        str=null;
    }

    public Node(String str){
        left=right=null;
        length=str.length();
    }

}