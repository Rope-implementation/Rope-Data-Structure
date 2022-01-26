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