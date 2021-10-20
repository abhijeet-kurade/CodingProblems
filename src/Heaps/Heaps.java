package Heaps;
import javax.swing.*;
import java.util.*;

public class Heaps {


    public static void main(String[] args) {

    }
    public static void print_arr(int[] arr){
        int len = arr.length;
        for(int i=0; i<len; i++){
            System.out.print(""+ arr[i] + " ");
        }
        System.out.println();
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




    public static List<Integer> mergeSortedArrays(List<List<Integer>> arrays) {
        List<Integer> sorted = new ArrayList<>();
        int len = arrays.size();

        PriorityQueue<int[]> heap = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        for(int i=0; i<len; i++)
            heap.add(new int[]{arrays.get(i).get(0), i, 0});

        while(! heap.isEmpty()){
            int[] triplet = heap.poll();
            int number = triplet[0];
            int array = triplet[1];
            int index = triplet[2];
            sorted.add(number);
            index += 1;
            if(index >= arrays.get(array).size()) continue;
            triplet[0] = arrays.get(array).get(index);
            triplet[2] = index;
            heap.add(triplet);
        }
        return sorted;
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

/*

parent to child =>
    parent = i
    left child = 2 * i) + 1
    right child = (2 * i) + 2

child to parent
    child = i
    parent = floor(i / 2)

 */
class Heap{
    public Heap(int[] arr){
        heapify(arr);
    }
    public Heap(){

    }
    public void heapify(int[] arr){
        int len = arr.length - 2;
        for(int i = len / 2; i>=0; i--){
            shiftDown(arr, i, arr.length - 1);
            Heaps.print_arr(arr);
        }
    }

    private void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    public void shiftDown(int[] arr, int index, int len){
        int parent = index;
        while(index <= len){
            int leftChild = 2 * parent + 1;
            if(leftChild > len) break;
            int rightChild = 2 * parent + 2;
            if(rightChild <= len){
                int minChild = Math.min(arr[leftChild], arr[rightChild]);
                System.out.println(minChild);
                if(minChild >= arr[parent]) break;
                int swapIndex = minChild == arr[leftChild] ? leftChild : rightChild;
                swap(arr, parent, swapIndex);
                parent = swapIndex;
            }
            else{
                if(arr[parent] > arr[leftChild]) swap(arr, parent, leftChild);
                break;
            }
        }



    }

    public void shiftUP(int[] arr, int index){
        int child = index;
        while(child > 0){
            int parent = (child - 1)/2;

            if(arr[child] >= arr[parent]) return;
            swap(arr, parent, child);
            child = parent;
        }
    }
}
