/**
 * TransactionsLinkedList
 */
public class TransactionsLinkedList implements TransactionsList {

    private int size ;

    public TransactionsLinkedList(){
        size = 0;
    }

    Node head;

    @Override
    public void addTransaction(Transaction data) {
        Node newNode = new Node(data);
        newNode.next = head;
        newNode.prev = null;

        // if  had already >=1 nodes
        if (head != null)
            head.prev = newNode;

        head = newNode;
        size++;
    }

    @Override
    public void removeTransactionById(String transactionId) {

        
        // The list is empty, nothing to delete
        if (head == null) 
            return;
            
        // If the node to be deleted is the head
        if (head.data.getIdentifier() == transactionId) {
            head = head.next;
            size--;
            return;
        }
        
        // Search for the node with the specified value
        Node current = head;
        Node previous = null;

        while (current != null && current.data.getIdentifier() != transactionId) {
            previous = current;
            current = current.next;
        }

        // If the node with the specified value is found
        if (current != null) {

            previous.next = current.next;
        }
        else{
            throw new TransactionNotFoundException("transaction Id not found");
        }
        size--;
    }
    
    @Override
    public Transaction[] transformTransactionToArray() {
        Transaction[] transactions = new Transaction[size];
        Node node = head;
        Node end = null;
        //as linked list will end when Node reaches Null

        int i = 0;
        while (node != null) {
            transactions[i] = node.data;
            end = node;
            node = node.next;
            i++;
        }

        return transactions;
    }

    @Override
    public void displayTransaction() {
        Node node = head;
        Node end = null;

        while (node != null) {
            System.out.println(node.data);
            end = node;
            node = node.next;
        }
    }

}