import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class Rope {

    Node root;
    static String s2 = "";

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
        if (ropes.size() == 0) {
            System.out.println("There isn't any rope");
        } else {
            for (Rope r : ropes) {
                System.out.print(++count + ". ");
                travers(r.root);
                System.out.println();
            }
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
        Rope r1 , r2;
        boolean check=true;
        if(numberRope2 < numberRope1) {
            check=false;
        }
        r1 = rr.get(numberRope1);
        r2 = rr.get(numberRope2);
        Rope r3 = new Rope();
        r3.root.left = r1.root;
        r3.root.right = r2.root;
        r3.root.weight = r1.root.weight;
        if(check){
            rr.remove(r2);
            rr.set(numberRope1, r3);
        }
        else{
            rr.remove(r1);
            rr.set(numberRope2, r3);
        }

    }

    /*
    split a rope into two ropes
     */
    public static void split(int num, int index, ArrayList<Rope> rr) {
        Node p = rr.get(num).root;
        Node q = p;
        String rightResult = "";
        String leftResult = "";
        int count = 0, count1 = 0, count2 = 0;
        int newIndex = index;
        while (q.left != null && q.right != null) {
            if (newIndex < q.weight) {

                if (index >= p.weight) {
                    rightResult = newTravers(q.right);
                    s2 = "";
                }
                q = q.left;
                if (newIndex < q.weight && index < p.weight && q.left != null && q.right != null) {
                    if (count1 == 0) {
                        rightResult = newTravers(q.right);
                        s2 = "";
                    } else {
                        rightResult = newTravers(q.right) + rightResult;
                        s2 = "";
                    }
                    count1++;
                }

                if (count == 0) {
                    rightResult += newTravers(p.right);
                    s2 = "";
                    count++;
                }
            } else {
                newIndex -= q.weight;

                if (newIndex < q.weight && index < p.weight) {
                    leftResult += newTravers(q.left);
                    s2 = "";
                }
                q = q.right;

                if (count == 0) {
                    leftResult = newTravers(p.left);
                    s2 = "";
                    count++;
                }
                if (newIndex < q.weight && index >= p.weight && q.left != null && q.right != null) {
                    if (count2 == 0) {
                        leftResult += newTravers(q.left);
                        s2 = "";
                    } else {
                        leftResult = leftResult + newTravers(q.left);
                        s2 = "";
                    }
                    count2++;
                }
                if (newIndex >= q.weight) {
                    rightResult += newTravers(q.right);
                    s2 = "";
                }
            }
        }

        Node newLeft = new Node(q.str.substring(0, newIndex + 1));
        Node newRight = new Node(q.str.substring(newIndex + 1));
        String result1;
        String result2;

        Program.ropes.remove(num);

        if (newLeft.weight == 0) {
            result2 = leftResult;
        } else {
            result2 = leftResult + newLeft.str;
        }
        if (!result2.equals("")) {
            Program.ropes.add(num, new Rope(result2));
        }

        if (newRight.weight == 0) {
            result1 = rightResult;
        } else {
            result1 = newRight.str + rightResult;
        }
        if (!result1.equals("")) {
            Program.ropes.add(num + 1, new Rope(result1));
        }
    }

    /*
    insert rope 2 value to rope 1, after index
     */
    public static void insert(int numRope1, int index, int numRope2, ArrayList<Rope> rr) {
        Node p = rr.get(numRope1).root;
        Node q = rr.get(numRope2).root;
        split(numRope1, index, rr);
        concat(numRope1, numRope2 + 1, rr);
        concat(numRope1, numRope1 + 1, rr);
    }

    /*
    delete a part of a node
     */
    public static void delete(int num, int beginning, int end, ArrayList<Rope> rr) {
        Node p = rr.get(num).root;
        if (beginning + 1 == 0 && end == newTravers(p).length()) {
            rr.remove(num);
        } else {
            split(num, beginning, rr);
            split(num + 1, end - beginning - 2, rr);
            rr.remove(num + 1);
            if (rr.size() > 1) {
                concat(num, num + 1, rr);
            }

        }
    }

    public static String newTravers(Node root) {
        if (root != null) {
            s2 = newTravers(root.left);
            s2 = newTravers(root.right);

            if (root.str != null) {
                s2 += root.str;

            }
        }
        return s2;
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
        ArrayList<Integer> x = new ArrayList<>();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ' ')
                x.add(i);
        }
        return x;
    }

}