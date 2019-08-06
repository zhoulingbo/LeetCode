package pers.zhoulingbo.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 字典数
 * 
 */
public class Trie
{
    private TrieNode root;

    public Trie(TrieNode root)
    {
        this.root = root;
    }

    public boolean exist(String word)
    {
        if (word == null || word.length() == 0)
            return false;

        char[] chars = word.toCharArray();
        TrieNode node = root;
        int length = 0;
        while (length < chars.length)
        {
            char ch = chars[length++];
            int pos = ch - 'a';
            TrieNode child = node.children[pos];
            if (child == null)
                return false;
            node = child;
        }

        if (node == null)
            return false;
        return node.isEnd;
    }

    public boolean startsWith(String prefix)
    {
        if (prefix == null || prefix.length() == 0)
            return false;

        char[] chars = prefix.toCharArray();
        TrieNode node = root;
        int length = 0;
        while (length < chars.length)
        {
            char ch = chars[length++];
            int pos = ch - 'a';
            TrieNode child = node.children[pos];
            if (child == null)
                return false;
            node = child;
        }

        if (node == null)
            return false;
        return true;
    }

    public void insert(String word)
    {
        if (word == null || word.length() == 0)
            return;

        char[] chars = word.toCharArray();
        TrieNode node = root;
        int length = 0;
        while (length < chars.length)
        {
            char ch = chars[length++];
            int pos = ch - 'a';
            TrieNode child = node.children[pos];
            if (child == null)
            {
                child = new TrieNode(ch);
                node.children[pos] = child;
            }
            node = child;
        }

        node.num += 1;
        node.isEnd = true;
    }

    private void preOrder(TrieNode node, StringBuffer strb, List<String> list)
    {
        if (node == null)
            return;

        if (node.val != 0)
            strb.append(node.val);
        if (node.isEnd)
            list.add(strb.toString());

        for (int i = 0; i < node.children.length; i++)
        {
            if (node.children[i] != null)
                preOrder(node.children[i], strb, list);
        }

        if (strb.length() > 0)
        {
            int last = strb.length() - 1;
            strb.deleteCharAt(last);
        }
    }

    public List<String> all()
    {
        List<String> list = new ArrayList<>();
        preOrder(root, new StringBuffer(), list);
        return list;
    }
}
