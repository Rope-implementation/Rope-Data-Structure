/*
Kourosh Hassanzadeh 9912762552
Alireza Sajjadi 9912762596
 */

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