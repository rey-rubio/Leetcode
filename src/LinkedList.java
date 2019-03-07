import java.util.HashMap;
import java.util.Map;

public class LinkedList {

    static Node head;

    static class Node {
        int data;
        Node next;

        Node(int d) {
            data = d;
            next = null;
        }
    }

    public LinkedList(Node n)
    {
        head = n;
    }

    public Node reverse(Node node){
        Node prev = null;
        Node cursor = node;
        Node next = null;

        while(cursor != null){
            next = cursor.next;
            cursor.next = prev;
            prev = cursor;
            cursor = next;

        }
        node = prev;
        return node;
    }

    public Node reverseRecursive(Node cursor, Node prev){

        if(cursor.next == null){
            head = cursor;

            cursor.next = prev;

            return head;
        }

        Node next = cursor.next;
        cursor.next = prev;

        reverseRecursive(next, cursor);

        return head;

    }

    public LinkedList clone(){

        Node origCursor = head;
        Node newCursor = null;

        Map<Node, Node> map = new HashMap<Node, Node>();

        while(origCursor != null){
            newCursor = new Node(origCursor.data);
            map.put(origCursor, newCursor);
            origCursor = origCursor.next;
        }

        origCursor = head;

        while(origCursor != null){
            newCursor = map.get(origCursor);
            newCursor.next = map.get(origCursor.next);
            origCursor = origCursor.next;
        }

        return new LinkedList(map.get(head));
    }

    public void printLinkedList(){
        Node n = head;
        while(n != null){
            System.out.print(n.data + " ");
            n = n.next;
        }
        System.out.println();
    }

    public static void main(String[] args){
        LinkedList test = new LinkedList(new Node(50));
        test.head.next = new Node (21);
        test.head.next.next = new Node(0);
        test.head.next.next.next = new Node(43);

        System.out.println("Original Linked List: ");
        test.printLinkedList();

        head = test.reverse(head);
        System.out.println("Reversed Linked List: ");
        test.printLinkedList();

        head = test.reverseRecursive(head, null);
        System.out.println("Reversed Linked List: ");
        test.printLinkedList();


        System.out.println("Cloned Linked List: ");
        LinkedList cloned = test.clone();
        cloned.printLinkedList();
    }

}
