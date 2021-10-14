package Graphs;
import LinkedLists.LinkedLists;
import Stacks.Stacks;

import java.util.*;
public class Graphs {
    public static void main(String[] args) {



    }

    public boolean isInside(int[][] rooms, int row, int col){
        int height = rooms.length;
        int weight = rooms[0].length;
        return 0 <= row && row < height && 0 <= col && col < weight;
    }
    public void travel(int[][] rooms, int row, int col, int steps){
        if(!isInside(rooms, row, col ) || rooms[row][col] <= steps || rooms[row][col] == 0) return ;

        rooms[row][col] = steps;

        travel(rooms, row-1, col, steps+1);
        travel(rooms, row+1, col, steps+1);
        travel(rooms, row, col-1, steps+1);
        travel(rooms, row, col+1, steps+1);
    }
    public void wallsAndGates(int[][] rooms) {
        int height = rooms.length;
        int weight = rooms[0].length;

        for(int row =0; row<height; row++){
            for(int col=0; col<weight; col++){
                if(rooms[row][col] == 0){
                    travel(rooms, row-1, col, 1);
                    travel(rooms, row+1, col, 1);
                    travel(rooms, row, col-1, 1);
                    travel(rooms, row, col+1, 1);
                }
            }
        }
    }
}

class Graph{
    int vertices;
    DoublyLinkedList<Integer> list[];

    public Graph(int vertices){
        this.vertices = vertices;
        list = new DoublyLinkedList[vertices];
        for(int i=0; i<vertices; i++){
            list[i] = new DoublyLinkedList<Integer>();
        }
    }

    public void addEdge(int source, int destination){
        if(source < this.vertices && destination < this.vertices ){
            this.list[source].insertAtEnd(destination);
        }
    }
    public void removeEdge(int source, int destination){
        if(source < this.vertices && destination < this.vertices ){
            DoublyLinkedList<Integer> lst = list[source];
            DoublyLinkedList<Integer>.Node node = list[source].getHeadNode();
            while(node != null){
                if (destination == node.data){
                    lst.deleteNode(node);
                    return;
                }
                node = node.nextNode;
            }
        }
    }

    public String BreathFirstSearch(Graph g){
        String result = "";
        HashSet<Integer> visited = new HashSet<>();
        LinkedList<DoublyLinkedList<Integer>> queue = new LinkedList<>();
        for(int i=0; i<g.vertices; i++){

            if(!visited.contains(i)){
                result += ""+i+"";
                visited.add(i);
                queue.add(g.list[i]);
                while(!queue.isEmpty()){
                    DoublyLinkedList<Integer> lst = queue.remove();
                    DoublyLinkedList<Integer>.Node node = lst.getHeadNode();
                    while (node != null){
                        int nd = node.data;
                        if(!visited.contains(nd)){
                            result += ""+nd+"";
                            visited.add(nd);
                            queue.add(g.list[nd]);
                        }
                    }
                }
            }
        }
        return result;
    }

    public String DepthFirstSearch(Graph g){
        String result = "";
        HashSet<Integer> visited = new HashSet<>();
        Stack<Integer> stack = new Stack<>();
        for(int i=0; i<g.vertices; i++){
            if(!visited.contains(i)){
                stack.add(i);
                while (!stack.isEmpty()){
                    int vertex = stack.pop();
                    result += "" +vertex+"";
                    visited.add(vertex);
                    DoublyLinkedList<Integer>.Node node = g.list[vertex].getHeadNode();
                    while (node != null){
                        int nd = node.data;
                        if(!visited.contains(nd)){
                            stack.add(nd);
                        }
                    }
                }
            }
        }
        return result;
    }

    public void printGraph(){
        System.out.println(">>>Graph<<<");
        for(int i=0; i<this.vertices; i++){
            System.out.print("|"+i+"| =>");

            DoublyLinkedList<Integer>.Node node = this.list[i].getHeadNode();
            while(node != null){
                System.out.print(node.data +  "->");
                node = node.nextNode;
            }
            System.out.println("null");
        }
    }

    public boolean validGraphTree(int n, int[][] edges){
        HashMap<Integer, ArrayList<Integer>> graph = new HashMap<>();
        for(int i=0; i<n; i++){
            ArrayList<Integer> list = new ArrayList<>();
            graph.put(i, list);
        }
        int no_edges = edges.length;
        for(int i=0; i<no_edges; i++){
            graph.get(edges[i][0]).add(edges[i][1]);
            graph.get(edges[i][1]).add(edges[i][0]);
        }

        LinkedList<Integer> queue = new LinkedList<>();
        HashSet<Integer> visited = new HashSet<>();

        queue.add(0);

        while(!queue.isEmpty()){
            int node = queue.remove();
            if(visited.contains(node)) return false;
            visited.add(node);
            for(int i : graph.get(node)){
                if(!visited.contains(i)) queue.add(i);
            }
        }
        if(visited.size()<n) return false;
        return true;
    }

    public boolean validTree(int n, int[][] edges){

        HashMap<Integer, List<Integer>> graph = new HashMap<>();
        boolean[] visited = new boolean[n];
        for(int i=0; i<n; i++){
            List<Integer> list = new ArrayList<>();
            graph.put(i, list);
        }
        int no_edges = edges.length;
        for(int i=0; i<no_edges; i++){
            graph.get(edges[i][0]).add(edges[i][1]);
            graph.get(edges[i][1]).add(edges[i][0]);
        }

        if(!validaTreeDFS(0, -1, graph, visited)) return false;

        for(boolean b : visited) if(!b) return false;

        return true;
    }


    private boolean validaTreeDFS(int curr, int parent, HashMap<Integer, List<Integer>> graph, boolean[] visited) {
        if(visited[curr]) return false;

        visited[curr] = true;

        for(int i : graph.get(curr)){
            if(i != parent && !validaTreeDFS(i, curr, graph, visited)) return false;
        }
        return true;
    }


    public void findBridge(int root, HashMap<Integer, List<Integer>> graph, boolean[] visited,
                           int[] discovery, int[] low, int[] parent, int[] time, List<int[]> bridges ){
        visited[root] = true;

        time[0] += 1;
        discovery[root] = time[0];
        low[root] = time[0];

        Iterator<Integer> i = graph.get(root).iterator();

        while (i.hasNext()){
            int v = i.next();
            if(!visited[v]){
                parent[v] = root;
                findBridge(v, graph, visited, discovery, low, parent, time, bridges);
                low[root] = Math.min(low[root], low[v]);
                if(low[v] > discovery[root]){
                    bridges.add(new int[]{root, v});
                }
            }
            else if(v != parent[root]){
                low[root] = Math.min(low[root], discovery[v]);
            }
        }
        return;
    }

    public int countNodeDFS(int start, HashMap<Integer, List<Integer>> graph, HashSet<Integer> visited){
        int nodes = 0;
        if(!visited.contains(start)){
            Stack<Integer> stack = new Stack<>();
            stack.add(start);
            while (!stack.isEmpty()){
                int vertex = stack.pop();
                nodes += 1;
                visited.add(vertex);
                Iterator<Integer> i = graph.get(vertex).iterator();
                while (i.hasNext()){
                    int node = i.next();
                    if(!visited.contains(node)) stack.add(node);
                }
            }
        }
        return nodes;

    }

    public int special_sol(int N, int M, int[][] Edg){
        HashMap<Integer, List<Integer>> graph = new HashMap<>();
        for(int i=0; i<N; i++) graph.put(i, new ArrayList<>());
        for(int[] edge : Edg){
            graph.get(edge[0]-1).add(edge[1]-1);
            graph.get(edge[1]-1).add(edge[0]-1);
        }
        boolean[] visited = new boolean[N];
        int[] discovery = new int[N];
        int[] low = new int[N];
        int[] parent = new int[N];
        int[] time = new int[]{0};
        for(int i=0; i<N; i++) parent[i] = -1;
        List<int[]> bridges = new ArrayList<>();
        for(int i=0; i<N; i++){
            if(!visited[i]){
                findBridge(i, graph, visited, discovery, low, parent, time, bridges);
            }
        }
        for(int[] bridge : bridges){
            int ind;
            ind = graph.get(bridge[0]).indexOf(bridge[1]);
            graph.get(bridge[0]).remove(ind);

            ind = graph.get(bridge[1]).indexOf(bridge[0]);
            graph.get(bridge[1]).remove(ind);
        }

        HashSet<Integer> vsited = new HashSet<>();
        List<Integer> nodes_in_component = new ArrayList<>();
        for(int i=0; i<N; i++){
            if(!vsited.contains(i)){
                nodes_in_component.add(countNodeDFS(i, graph, vsited));
            }
        }
        int special_val = 1;
        for(int nodes : nodes_in_component) special_val *= nodes;
        return special_val;
    }




}

class DoublyLinkedList<T> {

    //Node inner class for DLL
    public class Node {
        public T data;
        public Node nextNode;
        public Node prevNode;
    }

    public Node headNode;
    public Node tailNode;
    public int size;

    public DoublyLinkedList() {
        this.headNode = null;
        this.tailNode = null;
    }

    public boolean isEmpty() {
        if (headNode == null && tailNode == null)
            return true;
        return false;
    }

    public Node getHeadNode() {
        return headNode;
    }
    public Node getTailNode() {
        return tailNode;
    }

    public void deleteNode(Node node){
        if(node.nextNode != null && node.prevNode != null){
            node.nextNode.prevNode = node.prevNode;
            node.prevNode.nextNode = node.nextNode;
            this.size -= 1;
        }
        else if (node.nextNode == null && node.prevNode != null){
            deleteAtTail();
        }
        else {
            deleteAtHead();
        }
    }

    public int getSize() {
        return size;
    }

    public void insertAtHead(T data) {
        Node newNode = new Node();
        newNode.data = data;
        newNode.nextNode = this.headNode; //Linking newNode to head's nextNode
        newNode.prevNode = null;
        if (headNode != null)
            headNode.prevNode = newNode;
        else
            tailNode = newNode;
        this.headNode = newNode;
        size++;
    }
    public void insertAtEnd(T data) {
        if(isEmpty()) {
            insertAtHead(data);
            return;
        }
        Node newNode = new Node();
        newNode.data = data;
        newNode.nextNode = null;
        newNode.prevNode = tailNode;
        tailNode.nextNode = newNode;
        tailNode = newNode;
        size++;
    }

    public void printList() {
        if (isEmpty()) {
            System.out.println("List is Empty!");
            return;
        }

        Node temp = headNode;
        System.out.print("List : null <- ");

        while (temp.nextNode != null) {
            System.out.print(temp.data.toString() + " <-> ");
            temp = temp.nextNode;
        }

        System.out.println(temp.data.toString() + " -> null");
    }

    public void printListReverse() {
        if (isEmpty()) {
            System.out.println("List is Empty!");
        }

        Node temp = tailNode;
        System.out.print("List : null <- ");

        while (temp.prevNode != null) {
            System.out.print(temp.data.toString() + " <-> ");
            temp = temp.prevNode;
        }

        System.out.println(temp.data.toString() + " -> null");
    }

    public void deleteAtHead() {
        if (isEmpty())
            return;

        headNode = headNode.nextNode;
        if (headNode == null)
            tailNode = null;
        else
            headNode.prevNode = null;
        size -= 1;
    }
    public void deleteAtTail() {
        if (isEmpty())
            return;
        tailNode = tailNode.prevNode;
        if (tailNode == null)
            headNode = null;
        else
            tailNode.nextNode = null;
        size -= 1;
    }
}
