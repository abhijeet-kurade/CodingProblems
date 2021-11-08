package Trees;
import java.util.*;

public class Trees {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) {

        String s = "0123435";
        String [] lst = s.split("3");
        for(String ss : lst ) System.out.println(ss);
        //System.out.println(s.substring(0, 2));
    }

    public static int closestValueRecursive(TreeNode node, double target) {
        if(node.left == null && node.right == null) return node.val;
        double curr_diff = Math.abs(target - node.val);
        int child;
        if(node.val < target) child = node.right != null ? closestValueRecursive(node.right, target) : node.val;
        else child = node.left != null ? closestValueRecursive(node.left, target) : node.val;
        return curr_diff < Math.abs(child - target) ? node.val : child;
    }

    public static int closestValueIterative(TreeNode root, double target) {
        if(root== null) return -1;
        int closest = root.val;
        TreeNode node = root;
        while (node != null){
            if(Math.abs(target - node.val) == 0 ) return node.val;
            closest = Math.abs(target - node.val) < Math.abs(target - closest) ? node.val : closest;
            if(node.val < target) node = node.right;
            else node = node.left;
        }
        return closest;
    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        Stack<TreeNode> leftToRight = new Stack<>();
        Stack<TreeNode> rightToLeft = new Stack<>();
        List<List<Integer>> zigzag = new ArrayList<>();
        if(root==null) return zigzag;
        boolean left_to_right = true;
        leftToRight.add(root);
        while(!leftToRight.isEmpty() || !rightToLeft.isEmpty()){
            List<Integer> level = new ArrayList<>();
            if(left_to_right){
                int total_nodes = leftToRight.size();
                for(int i=0; i<total_nodes; i++){
                    TreeNode node = leftToRight.pop();
                    level.add(node.val);
                    if(node.left != null) rightToLeft.add(node.left);
                    if(node.right != null) rightToLeft.add(node.right);
                }
            }
            else{
                int total_nodes = rightToLeft.size();
                for(int i=0; i<total_nodes; i++){
                    TreeNode node = rightToLeft.pop();
                    level.add(node.val);
                    if(node.right != null) leftToRight.add(node.right);
                    if(node.left != null) leftToRight.add(node.left);
                }
            }
            zigzag.add(level);
            left_to_right = !left_to_right;
        }
        return zigzag;
    }

}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class BSTIterator{
    Stack<TreeNode> stack;
    public BSTIterator(TreeNode root) {
        this.stack = new Stack<>();
        insertIntoStack(this.stack, root);
    }

    public int next() {
        if(this.stack.size() == 0) return -1;
        TreeNode node = this.stack.pop();
        insertIntoStack(this.stack, node.right);
        return node.val;
    }

    public boolean hasNext() {
        return this.stack.size() != 0;
    }

    public void insertIntoStack(Stack<TreeNode> stack, TreeNode node){
        while(node != null){
            stack.push(node);
            node = node.left;
        }
    }
}
