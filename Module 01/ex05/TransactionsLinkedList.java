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
    public Transaction removeTransactionById(String transactionId) {

        Node temp = head, prev = null;

         
        if (temp != null && temp.data.getIdentifier().equals(transactionId)) {
            head = temp.next;
            size--;
            return temp.data;
        }

        while (temp != null && !temp.data.getIdentifier().equals(transactionId)) {
            
            prev = temp;
            temp = temp.next;
        }

        if (temp == null)
        {
            throw new TransactionNotFoundException("Transaction Not Found.");
        }
        
        prev.next = temp.next;
        size--;
        return temp.data;
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