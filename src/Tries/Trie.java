package Tries;
import java.util.*;

public class Trie {
    final int LETTERS = 26;
    TrieNode root;

    public Trie(){
        this.root = new TrieNode();
    }

    class TrieNode{
        TrieNode[] children = new TrieNode[LETTERS];
        boolean word;
        public TrieNode(){
            this.word = false;
            Arrays.fill(this.children, null);
        }
    }

    public void insert(String word){
        TrieNode crawl = this.root;
        for(int level=0; level<word.length(); level++){
            int index = word.charAt(level) - 'a';
            if(crawl.children[index] == null) crawl.children[index] = new TrieNode();
            crawl = crawl.children[index];
        }
        crawl.word = true;
    }

    public boolean search(String word){
        TrieNode crawl = this.root;
        for(int level=0; level<word.length(); level++){
            int index = word.charAt(level) - 'a';
            if(crawl.children[index] == null) return false;
            crawl = crawl.children[index];
        }
        return crawl.word;
    }

    public List<String> suggestions(String word){
        List<String> suggestions = new ArrayList<>();
        TrieNode node = this.root;
        for(int level=0; level<word.length(); level++){
            if(node == null) continue;
            int index = word.charAt(level) - 'a';
            node = node.children[index];
        }
        suggestions(node, word, suggestions);
        return suggestions;
    }

    public void suggestions(TrieNode node, String str, List<String> suggestions){
        if(node.word) suggestions.add(str);
        for(int i=0; i<node.children.length; i++){
            TrieNode child = node.children[i];
            if(child == null) continue;
            String word = str + (char)(i+'a');
            suggestions(child, word, suggestions);
        }
    }

    public void display(){
        display(this.root, "");
    }

    private void display(TrieNode node, String str){
        if(node.word) System.out.print(str +" ");
        for(int i=0; i<node.children.length; i++){
            TrieNode child = node.children[i];
            if(child == null) continue;
            String word = str + (char)(i+'a');
            display(child, word);
        }
    }
}


class Tests{
    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("abhijeet");
        trie.insert("abhijeetk");
        trie.insert("kurade");
        trie.insert("absdfhi");
        trie.insert("abhi");
        trie.insert("sdfgadgrs");
        trie.insert("asdfgdfgsbhi");
        trie.insert("abhijeetk");
        //trie.display();
        System.out.println(trie.suggestions("ab"));
    }
}