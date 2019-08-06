package pers.zhoulingbo.leetcode;


public class TrieNode
{
    int num;
    char val;
    TrieNode[] children;
    boolean isEnd;
    
    TrieNode(char val)
    {
        this.num = 0;
        this.val = val;
        this.children = new TrieNode[26];
        this.isEnd = false;
    }

}
