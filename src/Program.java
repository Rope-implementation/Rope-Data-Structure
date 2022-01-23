import java.util.ArrayList;

public class Program {
    public static void main(String[] args) {
        Rope r = new Rope("i am ali");
        Rope r1 = new Rope("i am rope DS");
        Rope r2 = new Rope("here is a test");
        ArrayList<Rope> rr = new ArrayList<>();
        rr.add(r);
        rr.add(r1);
        rr.add(r2);
        Rope.status(rr);
        Rope.printIndex(2, 2, rr);
    }
}
