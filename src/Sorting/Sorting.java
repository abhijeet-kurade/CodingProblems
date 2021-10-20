package Sorting;
import java.util.*;


class HeapSort{
    public static int[] heapSort(int[] array) {
        int len = array.length-1;
        for(int ind = (len-1)/2; ind>=0; ind--)
            shiftDown(array, ind, len);
        int headEnd = len;
        for(int ind=len; ind>=0; ind--){
            swap(array, ind, 0);
            headEnd -= 1;
            shiftDown(array, 0, headEnd);
        }
        for(int ind = 0; ind<=(len-1)/2; ind++)
            swap(array, ind, len-ind);
        return array;
    }

    private static void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void shiftDown(int[] arr, int index, int len){
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

}

public class Sorting {
    public static void main(String[] args) {

        int[] arr = new int[]{6,20,1,5,7,12,6,21,3,33};
        QuickSort qs = new QuickSort();

        qs.quickSort(arr, 0, arr.length-1);
        print_arr(arr);
    }

    public static void print_arr(int[] arr){
        int len = arr.length;
        for(int i=0; i<len; i++){
            System.out.print(""+ arr[i] + " ");
        }
        System.out.println();
    }



}
class QuickSort{

    public void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public void quickSort1(int[] arr, int start, int end){
        if(start >= end ) return;
        int p = start;
        int l = p+1;
        int r = end;

        while(l<r){
            if(arr[p] < arr[l] && arr[p] > arr[r]) swap(arr, l++, r--);
            if(arr[p] >= arr[l]) l += 1;
            if(arr[p] <= arr[r]) r -=1;
        }
        swap(arr, p, r);

        quickSort1(arr, start, r-1);
        quickSort1(arr, r+1, end);
        return;
    }
    public void quickSort(int[] arr, int start, int end){
        if(start >= end) return;

        int pivot = start;
        int left = start + 1;
        int right = end;

        while(left <= right){
            if(arr[left] > arr[pivot] && arr[right] < arr[pivot]){
                swap(arr, left++, right--);
            }
            if(arr[left] <= arr[pivot]) left += 1;
            if(arr[right] >= arr[pivot]) right -= 1;
        }
        swap(arr, pivot, right);
        quickSort(arr, start+0, right-1);
        quickSort(arr, right+1, end+0);
        return;
    }
}

class MergeSort{
    public static void main(String[] args) {
        int[] arr = new int[]{9,-1,0,77,-90};

        int[] aux = mergeSort(arr);
        Sorting.print_arr(aux);
    }
    public static int[] mergeSort(int[] array) {
        int len = array.length;
        int[] aux = new int[len];
        splitList(array, 0, len-1, aux);
        return aux;
    }

    public static void splitList(int[] arr, int start, int end, int[] aux){
        if(start >= end) return;
        int mid = start + (end - start) / 2;
        splitList(arr, start, mid, aux);
        splitList(arr, mid+1, end, aux);
        toMerge(arr, start,  end, aux);
    }

    public static void toMerge(int[] arr, int start, int end, int[] aux){
        int mid = start + (end - start) / 2;
        int middle = mid;
        mid += 1;
        int index = start;
        while(start <= middle || mid <= end){
            int numOne = start <= middle ? arr[start] : Integer.MAX_VALUE;
            int numTwo = mid <= end ? arr[mid] : Integer.MAX_VALUE;
            if(numOne <= numTwo){
                aux[index] = numOne;
                start += 1;
            }
            else {
                aux[index] = numTwo;
                mid += 1;
            }
            index += 1;
        }
    }
    public static int[] merge(int[] listOne, int[] listTwo){
        int lenOne = listOne.length;
        int lenTwo = listTwo.length;
        int[] merged = new int[lenOne + lenTwo];
        int indexOne = 0;
        int indexTwo = 0;
        int index = 0;
        while(indexOne < lenOne || indexTwo < lenTwo){
            int numOne = indexOne < lenOne ? listOne[indexOne] : Integer.MAX_VALUE;
            int numTwo = indexTwo < lenTwo ? listTwo[indexTwo] : Integer.MAX_VALUE;
            if(numOne <= numTwo){
                merged[index] = numOne;
                indexOne += 1;
            }
            else {
                merged[index] = numTwo;
                indexTwo += 1;
            }
            index += 1;
        }
        return merged;
    }
}
