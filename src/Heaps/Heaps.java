package Heaps;
import java.util.*;

public class Heaps {
    public static void main(String[] args) {
        RunningMedian rm = new RunningMedian();
        System.out.println(rm.insert(4));
        System.out.println(rm.insert(5));
        System.out.println(rm.insert(10));
        System.out.println(rm.insert(19));
        System.out.println(rm.insert(14));
        System.out.println(rm.insert(-4));
        System.out.println(rm.insert(4));
    }

}

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

class  Algorithms{
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> heap = new PriorityQueue<>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val - o2.val;
            }
        });
        int len = lists.length;
        for(int i=0; i<len; i++){
            if(lists[i] != null) heap.add(lists[i]);
        }
        ListNode root = new ListNode(Integer.MIN_VALUE);
        ListNode root1 = root;
        while (!heap.isEmpty()){
            ListNode node = heap.remove();
            root.next = node;
            root = root.next;
            node = node.next;
            if(node != null) heap.add(node);
        }
        return  root1.next;
    }
    public ListNode mergeKLists1(ListNode[] lists) {
        PriorityQueue<ListNode> heap = new PriorityQueue<>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val - o2.val;
            }
        });
        int len = lists.length;
        for(int i=0; i<len; i++){
            if(lists[i] != null) heap.add(lists[i]);
        }
        ListNode root = new ListNode(Integer.MIN_VALUE);
        ListNode root1 = root;
        while (!heap.isEmpty()){
            ListNode node = heap.remove();
            root.next = node;
            root = root.next;
            node = node.next;
            if(node != null) heap.add(node);
        }
        return  root1.next;
    }

}

class RunningMedian{
    private PriorityQueue<Integer> maxHeap;
    private PriorityQueue<Integer> minHeap;
    public RunningMedian(){
        this.minHeap = new PriorityQueue<>();
        this.minHeap.add(Integer.MAX_VALUE);
        this.maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        this.maxHeap.add(Integer.MIN_VALUE);
    }
    public double insert(int num){
        double median = 0;
        if(this.maxHeap.size() == this.minHeap.size()){
            int mx = this.maxHeap.peek();
            int mn = this.minHeap.poll();
            if(num <= mn){
                median = num <= mx ? mx : num;
                this.maxHeap.add(num);
                this.minHeap.add(mn);
            }
            else {
                median = mn;
                this.maxHeap.add(mn);
                this.minHeap.add(num);
            }
        }
        else{
            int mx = this.maxHeap.poll();
            int anotherNum;
            if(num <= mx){
                this.maxHeap.add(num);
                this.minHeap.add(mx);
                anotherNum = this.maxHeap.peek();
            }
            else{
                this.maxHeap.add(mx);
                this.minHeap.add(num);
                anotherNum = this.minHeap.peek();
            }
            median = (double) ( mx + anotherNum ) / 2;
        }
        return median;
    }
}
