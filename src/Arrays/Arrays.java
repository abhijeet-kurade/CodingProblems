package Arrays;
import java.util.*;

public class Arrays {
    public static void main(String[] args) {

        int count = 1;
        int j = 0;
        for (int i = 0; i < 5; i++) {
            for (; j < 5; j++) {
                System.out.println(count);
                count++;
            }
        }
    }
    public class Interval {
        int start;
        int end;

        Interval() {
            start = 0;
            end = 0;
        }

        Interval(int s, int e) {
            start = s;
            end = e;
        }
    }
    public ArrayList<Interval> insert(ArrayList<Interval> intervals, Interval newInterval) {

        int start = newInterval.start;
        int end = newInterval.end;
        ArrayList<Interval> newIntervals = new ArrayList<>();
        boolean startPlaced = false;
        for (Interval interval : intervals) {
            if(!startPlaced && start > interval.end){
                newIntervals.add(interval);
            }
            else if(start > interval.end){
                startPlaced = true;
            }

        }
        return newIntervals;
    }

    public int[] intersection(int[] nums1, int[] nums2){

        HashSet<Integer> set = new HashSet<>();
        HashSet<Integer> intersection = new HashSet<>();

        for(int i : nums1) set.add(i);

        for(int i : nums2){
            if(set.contains(i)){
                intersection.add(i);
            }
        }
        int size = intersection.size();
        int[] output = new int[size];
        int index = 0;
        for(Integer i : intersection) output[index++] = i;

        return output;
    }
    public int[] intersectionBruteForce(int[] nums1, int[] nums2){

        int n = nums1.length;
        int m = nums2.length;
        HashSet<Integer> intesection = new HashSet<>();
        for(int i =0; i<n; i++){
            for(int j=0; j<m; j++){
                if(nums1[i] == nums2[j]){
                    intesection.add(nums2[j]);
                }
            }
        }
        int size = intesection.size();
        int[] output = new int[size];
        int index = 0;
        for(Integer i : intesection) output[index++] = i;

        return output;
    }
    public int removeDuplicates(int[] nums) {
        int n = nums.length;
        if(n==0) return 0;
        if(n==1) return 1;
        int index = 0;
        int prev = nums[index];

        for(int i=1; i<n; ++i){
            if(prev != nums[i]){
                nums[index++] = prev;
                prev = nums[i];
            }
        }
        nums[index] = prev;

        return index+1;
    }

    public int maxAbsDiff(int[] arr){
        int min  = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for(int num : arr){
            min = Math.min(min, num);
            max = Math.max(max, num);
        }
        return Math.abs(max - min);
    }

    public int smallestMissingNumber(int[] arr){
        int len = arr.length;

        for(int i=0; i<len; i++){
            if(arr[i] <= 0) arr[i] = len + 1;
        }

        for(int i=0; i<len; i++){
            if(arr[i] <= len){
                if(arr[arr[i]] > 0){
                    arr[arr[i]] = arr[arr[i]] * -1;
                }
            }
        }

        return len;


    }


}
