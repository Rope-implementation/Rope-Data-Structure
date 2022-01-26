import java.util.ArrayList;
import java.util.regex.Pattern;

public class Rope {
    //private static final int MAX_LEAF_LEN = 6;
    Node root;

    public Rope(String str) {
        root = new Node();
        createNewRope(root, str);
    }

    public Rope() {
        root = new Node();
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

    /*
    concat rope1 and rope 2 and set the result in new Rope
     */
    public static void concat(int numberRope1, int numberRope2, ArrayList<Rope> rr) {
        Rope r1 = rr.get(numberRope1);
        Rope r2 = rr.get(numberRope2);
        Rope r3 = new Rope();
        r3.root.left = r1.root;
        r3.root.right = r2.root;
        r3.root.weight = r1.root.weight;
        rr.remove(r2);
        rr.set(numberRope1, r3);
    }


    public static void split(int num , int index , ArrayList<Rope> rr){
        Node p = rr.get(num).root;
        Node q=p;
        int newIndex=index;
        while (q.left != null && q.right!=null){
            if(newIndex < q.weight){
                q=q.left;
            }
            else{
                newIndex -= q.weight;
                q = q.right;
            }
        }
        Node newLeft=new Node(q.str.substring(0, newIndex+1));
        Node newRight = new Node(q.str.substring(index+1));
        q.str=null;
        q.left=newLeft;
        q.right=newRight;
        q.weight= newLeft.weight;
        System.out.println(newRight.str);


//        String rightBegin=travers(p).substring(index+1);
//        Program.ropes.add(new Rope(rightBegin));
        //System.out.println(new Rope(rightBegin+1).root.str);
//        while(index>p)
//        new Rope("");
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
        return;
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
        ArrayList<Integer> x = new ArrayList<>();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ' ')
                x.add(i);
        }
        return x;
    }

}

