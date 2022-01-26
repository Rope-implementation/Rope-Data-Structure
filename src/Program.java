import java.util.ArrayList;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        ArrayList<Rope> ropes = new ArrayList<>();
        Rope r;
        while (true) {
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();
            String[] inp = input.split(" ", 2);
            if (inp[0].equals("new")) {
                String str = inp[1];
                r = new Rope(str.substring(1, str.length() - 1));
                ropes.add(r);
            }
            if (inp[0].equals("status")) {
                Rope.status(ropes);
            }
            if (inp[0].equals("index")) {
                Rope.printIndex(Integer.parseInt(String.valueOf(inp[1].charAt(0))) - 1, Integer.parseInt(String.valueOf(inp[1].charAt(2))), ropes);
            }
            if (inp[0].equals("concat")) {
                Rope.concat(Integer.parseInt(String.valueOf(inp[1].charAt(0))) - 1, Integer.parseInt(String.valueOf(inp[1].charAt(2))) - 1, ropes);
            }
        }
    }
}
