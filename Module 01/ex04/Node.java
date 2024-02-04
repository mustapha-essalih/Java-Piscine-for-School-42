/**
 * Node
 */
public class Node {
    Transaction data;
    public Node next, prev;

    Node(Transaction data) 
    {
        this.data = data;
        next = null;
        prev = null;
    }
}