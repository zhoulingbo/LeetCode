package pers.zhoulingbo.leetcode;

import java.util.Random;

public class LinkedListRandomNode
{

    private ListNode head;
    private int size = 0;

    public LinkedListRandomNode(ListNode head)
    {
        this.head = head;
        ListNode node = head;
        while (node != null)
        {
            size ++;
            node = node.next;
        }
    }

    public int getRandom()
    {
        Random random = new Random();
        int i = random.nextInt(size);
        int k = 0;
        ListNode node = head;
        while (k < i)
        {
            k ++;
            node = node.next;
        }
        return node.val;
    }
}
