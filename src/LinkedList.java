import java.util.HashMap;
import java.util.Map;

public class LinkedList {

    ListNode head;

    static class ListNode {
        int data;
        ListNode next;

        ListNode(int d) {
            data = d;
            next = null;
        }
    }

    public LinkedList(ListNode n)
    {
        head = n;
    }

    public LinkedList(){};

    // Method to insert a new node
    public static LinkedList insert(LinkedList list, int data)
    {
        // Create a new node with given data
        ListNode n = new ListNode(data);
        n.next = null;

        // If the Linked List is empty,
        // then make the new node as head
        if (list.head == null) {
            list.head = n;
        }
        else {
            // Else traverse to the last node
            ListNode cursor = list.head;
            while (cursor.next != null) {
                cursor = cursor.next;
            }
            // Insert the new_node at last node
            cursor.next = n;
        }
        return list;
    }

    public ListNode reverse(ListNode node){
        ListNode prev = null;
        ListNode cursor = node;
        ListNode next = null;

        while(cursor != null){
            next = cursor.next;
            cursor.next = prev;
            prev = cursor;
            cursor = next;

        }
        node = prev;
        return node;
    }

    public ListNode reverseRecursive(ListNode cursor, ListNode prev){

        if(cursor.next == null){
            head = cursor;

            cursor.next = prev;

            return head;
        }

        ListNode next = cursor.next;
        cursor.next = prev;

        reverseRecursive(next, cursor);

        return head;

    }

    public LinkedList clone(){

        ListNode origCursor = head;
        ListNode newCursor = null;

        Map<ListNode, ListNode> map = new HashMap<ListNode, ListNode>();

        // Traverse the original list and make a copy of that
        // in the clone linked list.
        while(origCursor != null){
            newCursor = new ListNode(origCursor.data);
            map.put(origCursor, newCursor);
            origCursor = origCursor.next;
        }

        // Adjusting the original list reference again.
        origCursor = head;

        // Traversal of original list again to adjust the next
        // and random references of clone list using hash map.
        while(origCursor != null){
            newCursor = map.get(origCursor);
            newCursor.next = map.get(origCursor.next);
            origCursor = origCursor.next;
        }

        return new LinkedList(map.get(head));
    }

    public ListNode oddEvenList(ListNode head) {
        if(head == null)
            return null;

        ListNode odd = head;
        ListNode even = head.next;
        ListNode evenHead= even;

        while(even != null && even.next!=null)
        {
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }

        // "merge" lists
        odd.next = evenHead;

        return head;

    }

    public void printKthLastNode(ListNode head, int k){

        if(head == null)
            return;

        ListNode refNode = head;
        ListNode kNode = head;

        int count = 0;

        // move refNode k times
        while(count < k ){

            if(refNode == null) {
                System.out.println("Size of Linked List is smaller than n");
                return;
            }
            else {
                refNode = refNode.next;
                count++;
            }
        }
        // move refNode to the end of the linked list
        // kNode will follow
        while(refNode != null){
            refNode = refNode.next;
            kNode = kNode.next;
        }

        System.out.println("The " + k + "th node from the end of the list is: " + kNode.data + "\n");
        return;
    }

    public ListNode mergeTwoSortedLists(ListNode l1, ListNode l2){

        if(l1 == null)
            return l2;
        else if(l2 == null)
            return l1;

        ListNode cursor, tempHead = new ListNode(0);
        cursor = tempHead;

        while(l1 != null && l2 != null){
            if(l1.data <= l2.data){
                cursor.next = l1;
                l1 = l1.next;
            }
            else {
                cursor.next = l2;
                l2 = l2.next;
            }
            cursor = cursor.next;
        }

        if((cursor.next = l1) != null)
            cursor.next = l1;
        else
            cursor.next = l2;

        return tempHead.next;
    }


    public ListNode addTwoNumbers(ListNode l1, ListNode l2, ListNode l3) {

        int carry = 0;
        ListNode cursor, tempHead = new ListNode(0);
        cursor = tempHead;

        while(l1 != null || l2 != null || l3 != null || carry != 0) {

            if(l1 != null){
                carry +=  l1.data;
                l1 = l1.next;
            }

            if(l2 != null){
                carry += l2.data;
                l2 = l2.next;
            }

            if(l3 != null){
                carry += l3.data;
                l3 = l3.next;
            }

            cursor.next = new ListNode(carry % 10);
            carry /= 10;
            cursor = cursor.next;

        }
        return tempHead.next;
    }
    

    public boolean hasCycle(ListNode head){
        if(head == null)
            return false;
        ListNode walker = head;
        ListNode runner = head;
        while(runner.next != null && runner.next.next !=null){
            walker = walker.next;
            runner = runner.next.next;

            if(walker == runner)
                return true;
        }

        return false;
    }

    
    public static void printLinkedList(ListNode n){
        while(n != null){
            System.out.print(n.data + " ");
            n = n.next;
        }
        System.out.println();
    }

    public static void main(String[] args){

        System.out.println("Original Linked List: ");
        LinkedList test = new LinkedList();
        test.insert(test, 21);
        test.insert(test, 0);
        test.insert(test, 51);
        test.insert(test, 42);
        test.insert(test, 200);
        test.printLinkedList(test.head);


        System.out.println("__________________________________________________________________\n");
        System.out.println("public ListNode reverse(ListNode node)");
        test.head = test.reverse(test.head);
        test.printLinkedList(test.head);



        System.out.println("__________________________________________________________________\n");
        System.out.println("public ListNode reverseRecursive(ListNode cursor, ListNode prev)");
        test.head = test.reverseRecursive(test.head, null);
        test.printLinkedList(test.head);





        System.out.println("__________________________________________________________________\n");
        System.out.println("public LinkedList clone()");
        LinkedList cloned = test.clone();
        cloned.printLinkedList(test.head);


        System.out.println("__________________________________________________________________\n");
        System.out.println("public ListNode oddEvenList(ListNode head) ");
        test.head = test.oddEvenList(test.head);
        test.printLinkedList(test.head);


        System.out.println("__________________________________________________________________\n");
        System.out.println("public void printKthLastNode(ListNode head, int k)");
        test.printKthLastNode(test.head, 4);

        System.out.println("__________________________________________________________________\n");
        System.out.println("public ListNode addTwoNumbers(ListNode l1, ListNode l2, ListNode l3)");
        LinkedList test2 = new LinkedList();
        test2.insert(test2, 5);
        test2.insert(test2, 5);
        test2.insert(test2, 5);
        test2.insert(test2, 5);
        test2.insert(test2, 9);
        printLinkedList(test2.head);

        LinkedList test3 = new LinkedList();
        test3.insert(test3, 1);
        test3.insert(test3, 2);
        test3.insert(test3, 3);
        test3.insert(test3, 4);
        test3.insert(test3, 9);
        printLinkedList(test3.head);

        LinkedList test4 = new LinkedList();
        test4.insert(test4, 9);
        test4.insert(test4, 8);
        test4.insert(test4, 7);
        test4.insert(test4, 6);
        test4.insert(test4, 9);
        printLinkedList(test4.head);

        LinkedList testAddTwoNumbers = new LinkedList(test2.addTwoNumbers(test2.head, test3.head, test4.head));
        testAddTwoNumbers.printLinkedList(testAddTwoNumbers.head);


        System.out.println("__________________________________________________________________\n");
        System.out.println("public boolean hasCycle(ListNode head)");

        LinkedList testHasCycle = new LinkedList();
        testHasCycle.head = new ListNode(3);
        testHasCycle.head.next = new ListNode(2);
        testHasCycle.head.next.next = new ListNode(0);
        testHasCycle.head.next.next.next = new ListNode(-4);
        testHasCycle.head.next.next.next.next  = testHasCycle.head.next;
        System.out.println(testHasCycle.hasCycle(testHasCycle.head));

    }
}
