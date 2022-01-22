import java.util.ArrayList;

public class Program {
    public static void main(String[] args) {
        Rope r=new Rope("i am ali");
        Rope r1= new Rope("i am rope DS");
        ArrayList<Rope> rr =new ArrayList<>();
        rr.add(r);rr.add(r1);
        Rope.status(rr);
    }
}
