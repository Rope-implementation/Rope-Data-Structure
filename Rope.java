import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class Rope {
    private static final int MAX_LEAF_LEN = 6;
    Node root;

    public Rope(String str) {
        root = new Node();
        createNewRope(root, str, 0, str.length());


    }

    void createNewRope(Node node, String input, int lIndex, int rIndex) {
//        if(node==null)
//            node = new Node();
//        p.parent = parent_current;

        int leftLen = (int) Math.ceil(input.split(" ").length / 2.0);
        int rightLen = input.split(" ").length - leftLen;
        ArrayList<Integer> x = spaceCharAt(input);

        if (singleWord(input,x)) {
            node.weight +=  input.length();
            node.str = input;
        } else {
            node.weight +=  input.substring(0, x.get(leftLen - 1) + 1).length();

            if(node.left==null){
                node.left = new Node();
            }
            createNewRope(node.left, input.substring(0, x.get(leftLen - 1) + 1), lIndex, x.get(leftLen - 1) + 1);

            if(node.right==null){
                node.right = new Node();
            }
            createNewRope(node.right, input.substring(x.get(leftLen - 1) + 1, input.length()), x.get(leftLen - 1) + 1, rIndex);
        }

        //printstring(root);

    }

    private boolean singleWord(String input, ArrayList<Integer> spacePos) {
        for(int i : spacePos){
            String[] tmp = input.split(" ",2);
            if(Pattern.compile("[a-zA-Z]+").matcher(tmp[1]).find()){
                return false;
            }
        }
        return true;
    }

//    void printstring(Node root)
//    {
//        if (root==null)
//            return;
//        if (root.left==null && root.right==null)
//            System.out.println(root.str);
//        printstring(root.left);
//        printstring(root.right);
//    }


    /*
    This method count the number of space in String and store its position in ArrayList
     */
    ArrayList<Integer> spaceCharAt(String str) {
        int j = 0;
        ArrayList<Integer> x = new ArrayList<>();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ' ')
                x.add(i);
        }
        return x;
    }

}

class Node {
    Node left, right, parent;
    String str;
    int weight=0;

    public Node() {
    }

    public Node(String str) {
        this.str = str;
        weight = str.length();
    }

}