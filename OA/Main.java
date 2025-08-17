// 3
// n arg nytr
// 8
// n y t r a a r g

// trye false fasle true false false false false true

import java.util.*;

public class Main {

    static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isEnd = false;
    }

    static TrieNode root = new TrieNode();
    static Deque<Character> stream = new LinkedList<>();

    // Insert word in reverse
    static void insert(String word) {
        TrieNode node = root;
        for (int i = word.length() - 1; i >= 0; i--) {
            int idx = word.charAt(i) - 'a';
            if (node.children[idx] == null) {
                node.children[idx] = new TrieNode();
            }
            node = node.children[idx];
        }
        node.isEnd = true;
    }

    static boolean query(char ch) {
        stream.addFirst(ch);
        TrieNode node = root;
        for (char c : stream) {
            int idx = c - 'a';
            if (node.children[idx] == null)
                return false;
            node = node.children[idx];
            if (node.isEnd)
                return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        String[] dict = sc.nextLine().split(" ");
        for (String word : dict)
            insert(word);

        int m = Integer.parseInt(sc.nextLine());
        String[] chars = sc.nextLine().split(" ");

        List<String> result = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            char ch = chars[i].charAt(0);
            result.add(query(ch) ? "True" : "False");
        }

        System.out.println(String.join(" ", result));
    }
}
