/*
Kourosh Hassanzadeh 9912762552
Alireza Sajjadi 9912762596
 */

public class PQueue {

    static newNode head;
    static int size = 0;

    public PQueue() {
        head = new newNode(0, "");
    }

    static newNode pop() {
        newNode p = head;
        head = head.next;
        return p;

    }

    static void push(int x, String y) {
        newNode node;
        newNode first = null;
        if (head.priority == 0) {
            head = new newNode(x, y);
        } else {
            first = head;
            node = new newNode(x, y);
            if (head.priority <= x) {

                node.next = head;
                head = node;
            } else {

                while (first.next != null && first.next.priority > x) {
                    first = first.next;
                }

                node.next = first.next;
                first.next = node;
            }

        }
        size++;
    }

}

class newNode {
    int priority;
    String data;
    newNode next;

    public newNode() {

    }

    public newNode(int priority, String data) {
        this.data = data;
        this.priority = priority;
        this.next = null;
    }
}