/*
// Definition for a Node.
class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;

    public Node() {}

    public Node(int _val,Node _prev,Node _next,Node _child) {
        val = _val;
        prev = _prev;
        next = _next;
        child = _child;
    }
};
*/
class Solution {
  public Node flatten(Node head) {
    if (head == null) return head;
    // pseudo head to ensure the `prev` pointer is never none
    Node dummy = new Node(0, null, head, null);

    flattenDFS(dummy, head);

    // detach the pseudo head from the real head
    dummy.next.prev = null;
    return dummy.next;
  }
  /* return the tail of the flatten list */
  public Node flattenDFS(Node prev, Node curr) {
    if (curr == null) return prev;
    curr.prev = prev;
    prev.next = curr;

    // the curr.next would be tempered in the recursive function
    Node tempNext = curr.next;

    Node tail = flattenDFS(curr, curr.child);
    curr.child = null;

    return flattenDFS(tail, tempNext);
  }
}