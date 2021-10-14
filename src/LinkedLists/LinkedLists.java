package LinkedLists;

public class LinkedLists {
    class ListNode{
        int val;
        ListNode next;

        public ListNode(int val){
            this.val = val;
            this.next = null;
        }

        public ListNode(int val, ListNode next){
            this.val = val;
            this.next = next;
        }
    }

    public static void main(String[] args) {


    }

    public boolean hasCycle(ListNode head) {
        if(head == null)  return true;

        ListNode slow = head;
        ListNode fast = head.next;

        while(fast != null && fast.next != null){
            if(slow == fast) return true;
            slow = slow.next;
            fast = fast.next.next;
        }
        return false;
    }
}

class DoublyLinkedList<T>{
    class ListNode{
        T data;
        ListNode next;
        ListNode prev;

        public ListNode(T data){
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }

    ListNode head;
    ListNode tail;
    int size;

    public DoublyLinkedList(){
        this.head = null;
        this.tail = null;
    }




    public void setHead(ListNode node){
        if (this.head == null) {
            this.tail = node;
        }
        else{
            node.next = this.head;
        }
        this.head = node;
        this.size += 1;
    }

    public void setTail(ListNode node){
        if(this.tail == null){
            this.head = node;
        }
        this.tail = node;
        this.size  += 1;
    }

    public int size(){
        return this.size;
    }

}
