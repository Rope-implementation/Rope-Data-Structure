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
            switch (inp[0]) {
                case "new":
                    String str = input.split(" ", 2)[1];
                    r = new Rope(str.substring(1, str.length() - 1));
                    ropes.add(r);
                    break;
                case "status":
                    Rope.status(ropes);
                    break;
                case "index":
                    Rope.printIndex(Integer.parseInt(inp[1]) - 1, Integer.parseInt(inp[2]), ropes);
                    break;
                case "concat":
                    Rope.concat(Integer.parseInt(inp[1]) - 1, Integer.parseInt(inp[2]) - 1, ropes);
                    break;
                case "split":
                    Rope.split(Integer.parseInt(inp[1]) - 1, Integer.parseInt(inp[2]), ropes);
                    break;
                case "insert":
                    Rope.insert(Integer.parseInt(inp[1]) - 1, Integer.parseInt(inp[2]), Integer.parseInt(inp[3]) - 1, ropes);
                    break;
                case "delete":
                    Rope.delete(Integer.parseInt(inp[1]) - 1, Integer.parseInt(inp[2]) - 1, Integer.parseInt(inp[3]), ropes);
                    break;
                default:
                    System.err.println("There is not any command named like this");
                    break;
            }
        }
    }
}