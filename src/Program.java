import java.util.ArrayList;
import java.util.Scanner;

public class Program {
    static ArrayList<Rope> ropes = new ArrayList<>();
    public static void main(String[] args) {
        Rope r;
        while (true) {
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();
            String[] inp = input.split(" ");
            if (inp[0].equals("new")) {
                String str = input.split(" " , 2)[1];
                r = new Rope(str.substring(1, str.length() - 1));
                ropes.add(r);
            }
            if (inp[0].equals("status")) {
                Rope.status(ropes);
            }
            if (inp[0].equals("index")) {
                Rope.printIndex(Integer.parseInt(inp[1]) - 1, Integer.parseInt(inp[2]), ropes);
            }
            if (inp[0].equals("concat")) {
                Rope.concat(Integer.parseInt(inp[1]) - 1, Integer.parseInt(inp[2]) - 1, ropes);
            }
            if(inp[0].equals("split")){
                Rope.split(Integer.parseInt(inp[1]) - 1 , Integer.parseInt(inp[2]), ropes);
            }
        }
    }
}