import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class Rope {
    private static final int MAX_LEAF_LEN = 6;
    Node root;

    public Rope(String str) {
        root = new Node();
        createNewRope(root, str);
    }

    /*
      create new Rope
     */
    void createNewRope(Node node, String input) {

        int leftLen = (int) Math.ceil(input.split(" ").length / 2.0);
        int rightLen = input.split(" ").length - leftLen;
        ArrayList<Integer> x = spaceCharAt(input);

        if (singleWord(input, x)) {
            node.weight += input.length();
            node.str = input;
        } else {
            node.weight += input.substring(0, x.get(leftLen - 1) + 1).length();

            if (node.left == null) {
                node.left = new Node();
            }
            createNewRope(node.left, input.substring(0, x.get(leftLen - 1) + 1));

            if (node.right == null) {
                node.right = new Node();
            }
            createNewRope(node.right, input.substring(x.get(leftLen - 1) + 1, input.length()));
        }

    }

    public static void status(ArrayList<Rope> ropes) {
        int count = 0;
        for (Rope r : ropes) {
            System.out.print(++count + ". ");
            travers(r.root);
            System.out.println();
        }
    }

    /*
    print the wanted index from appropriate rope
     */
    public static void printIndex(int num, int index, ArrayList<Rope> rr) {
        Node p = rr.get(num).root;

        while (p.left != null && p.right != null) {
            if (p.weight > index) {
                p = p.left;
            } else {                                        //if p.weight <= index
                index -= p.weight;
                p = p.right;
            }
        }
        System.out.println(p.str.charAt(index));
    }

    private static void travers(Node n) {
        if (n == null)
            return;
        if (n != null) {
            travers(n.left);
            travers(n.right);

            if (n.str != null)
                System.out.print(n.str);
        }
    }

    /*
        check if input is single word or not
     */
    private boolean singleWord(String input, ArrayList<Integer> spacePos) {
        for (int i : spacePos) {
            String[] tmp = input.split(" ", 2);
            if (Pattern.compile("[a-zA-Z]+").matcher(tmp[1]).find()) {
                return false;
            }
        }
        return true;
    }

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
    Node left, right;
    String str;
    int weight = 0;

    public Node() {
    }

    public Node(String str) {
        this.str = str;
        weight = str.length();
    }

}