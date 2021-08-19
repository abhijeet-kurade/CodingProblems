package Stacks;
import java.util.*;

public class Stacks {


    public boolean find132pattern(int[] nums) {
        int n = nums.length;
        Stack<Integer> stk = new Stack<>();
        int middle = Integer.MIN_VALUE;

        for(int i=n-1; i>=0; --i)   {
            if(nums[i] < middle) return true;
            while(!stk.isEmpty() && nums[i] > stk.peek())  middle = stk.pop();
            stk.push(nums[i]);
        }

        return false;
    }

    public int longestValidParentheses(String str) {
        int len = str.length();
        Stack<Integer> stk = new Stack<>();
        stk.push(-1);
        int longest = 0;
        for(int i=0; i<len; i++){
            char c = str.charAt(i);
            if(stk.peek() == -1) stk.push(i);
            else if(c == '(') stk.push(i);
            else{
                if(str.charAt(stk.peek()) == '('){
                    stk.pop();
                    int current  = i - stk.peek();
                    longest = Math.max(current, longest);
                }
                else{
                    stk.push(i);
                }
            }
        }
        return longest;
    }

    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int[] left = new int[n];
        int[] right = new int[n];
        Stack<Integer> stack = new Stack<>();

        for(int i=0; i<n; i++){
            while(!stack.isEmpty()){
                if(heights[stack.peek()] >= heights[i])
                    stack.pop();
                else break;
            }
            left[i] = stack.size() == 0 ? -1 : stack.peek();
            stack.push(i);
        }
        //for(int i=0; i<n; i++) System.out.print(left[i]);
        stack.clear();
        for(int i=n-1; i>=0; i--){
            while(!stack.isEmpty()){
                if(heights[stack.peek()] >= heights[i])
                    stack.pop();
                else break;
            }
            right[i] = stack.size() == 0 ? n : stack.peek();
            stack.push(i);
        }
        //for(int i=0; i<n; i++) System.out.print(right[i]);
        int max_area = 0;


        for(int i=0; i<n; i++){
            int curr_area = heights[i] * (right[i] - left[i] -1);
            max_area = Math.max(max_area, curr_area);
        }
        return max_area;
    }

    public int[] asteroidCollision(int[] asteroids) {

        int n = asteroids.length;
        Stack<Integer> stack = new Stack<>();
        for(int i=0; i<n; i++){
            int asteroid = asteroids[i];
            boolean isItDestroyed = false;
            while(!isItDestroyed  && !stack.isEmpty()){
                int last_ast = stack.peek();
                if( (last_ast < 0 && asteroid < 0) || (last_ast > 0 && asteroid > 0)) break;
                if(last_ast == asteroid){
                    stack.pop();
                    isItDestroyed= true;
                    break;
                }
                if(Math.abs(asteroid) > Math.abs(last_ast)){
                    stack.pop();
                }
                else {
                    isItDestroyed= true;
                }
            }
            if(!isItDestroyed) stack.push(asteroid);
        }
        int s = stack.size();
        if(stack.isEmpty()) return new int[]{};
        int[] final_ast = new int[s];
        for(int i=s-1; i>=0; i--) final_ast[i] = stack.pop();
        return final_ast;
    }

    public String simplifyPath(String path) {
        int len = path.length();
        Stack<String> stack = new Stack<>();

        String directory = "";
        int i =0;
        while(i < len){
            directory = "";
            while(i<len && path.charAt(i) != '/'){
                directory += path.charAt(i);
                i += 1;
            }
            if(directory.equals(".") || directory.equals("")  ){
                i++;
                continue;
            }
            else if(directory.equals("..")){
                if(!stack.isEmpty()) stack.pop();
            }
            else{
                stack.push(directory);
            }
            i++;
        }
        String newPath = "";
        while(!stack.isEmpty()){
            newPath = newPath + '/' + stack.pop();
        }
        return newPath;
    }


}

class MinStack{
    Stack<Integer> stack;
    Stack<Integer> minStack;
    int size ;
    public MinStack() {
        stack = new Stack<>();
        minStack = new Stack<>();
        size = 0;
    }

    public void push(int val) {
        if(size == 0){
            stack.push(val);
            minStack.push(val);
            size += 1;
        }
        else{
            int min = minStack.peek();
            stack.push(val);
            minStack.push(Math.min(val, min));
            size += 1;
        }
    }

    public void pop() {
        minStack.pop();
        stack.pop();
        size -= 1;
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}
