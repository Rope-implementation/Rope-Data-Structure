/*
Kourosh Hassanzadeh 9912762552
Alireza Sajjadi 9912762596
 */

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Program {
    static ArrayList<Rope> ropes = new ArrayList<>();
    static List<String> result = new ArrayList<>();

    public static void main(String[] args) {

        Rope r = null;
        Trie trie;

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
                case "autocomplete":
                    trie = new Trie(new File("src/testTrie.txt"));
                    autoComp(inp[1], trie);
                    break;
                case "1":
                    newCommand(r, result.get(0));
                    break;
                case "2":
                    newCommand(r, result.get(1));
                    break;
                case "3":
                    newCommand(r, result.get(2));
                    break;
                default:
                    System.err.println("There is not any command named like this");
                    break;
            }
        }
    }

    private static void newCommand(Rope r, String str) {

        r = new Rope(str);
        ropes.add(r);

    }

    private static void autoComp(String pre, Trie trie) {
        result = trie.autoComplete(pre);
        for (int i = 0; i < result.size(); i++) {
            System.out.println(i + 1 + ". " + result.get(i));
        }
    }
}