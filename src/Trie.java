import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Trie {
    TrieNode root;

    public Trie(File file) {
        root = new TrieNode();
        fileReader(file);
    }

    public void insert(String word) {
        TrieNode cur = root;

        for (char c : word.toCharArray()) {
            if (!cur.children.containsKey(c)) {
                cur.children.put(c, new TrieNode(c));
            }
            cur = cur.children.get(c);
        }
        cur.isWord = true;
    }

    public List<String> autoComplete(String prefix) {
        TrieNode cur = root;
        List<String> response = new ArrayList<>();
        for (char c : prefix.toCharArray()) {
            if (!cur.children.containsKey(c))
                return null;

            cur = cur.children.get(c);
        }  // this loop runs till we get to the last of prefix so cur = TrieNode with value : last char of prefix



        finder(cur, prefix, response,3);

        return response;
    }

    private void finder(TrieNode trieNode, String s, List<String> response,int limit) {
        if(limit==0)
            return;
        if (trieNode.isWord) {
            response.add(s);
            limit--;
        }
        for (TrieNode child : trieNode.children.values()) {
            finder(child, s + child.value, response,limit);
        }

    }

    private void fileReader(File file) {
        BufferedReader input = null;
        try {
            input = new BufferedReader(new FileReader(file));
            while (true) {
                String line = input.readLine();
                if (line == null) {
                    break;
                }
                insert(line);


            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

class TrieNode {
    HashMap<Character, TrieNode> children;
    char value;
    boolean isWord;

    public TrieNode() {
        children = new HashMap<>();
        isWord = false;
    }

    public TrieNode(char value) {
        this();
        this.value = value;
    }
}
