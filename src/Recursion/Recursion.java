package Recursion;

import java.util.ArrayList;

public class Recursion {
    public static void main(String[] args) {
        Permutation p = new Permutation();
        String str = "8467";
        ArrayList<String> set = new ArrayList<>();
        p.permuteString(0, new StringBuilder(str), set);
        System.out.println(set);
    }

}

class Permutation{
    /*
    *  Permute String : ladksjfgh, DLSKJFGH
    *  Permute Number : 8234675, 198345
    *  Permute Array : [3, 4, 7, 8, 5, 6], ['J','W','Y','E','F']
    * */
    public void permuteString(int index, StringBuilder str, ArrayList<String> set){
        if(index == str.length()-1){
            set.add(String.valueOf(str));
        }
        char c;
        for(int i=index; i<str.length(); i++){
            c = str.charAt(index);
            str.setCharAt(index, str.charAt(i));
            str.setCharAt(i, c);

            permuteString(index+1, str, set);

            c = str.charAt(index);
            str.setCharAt(index, str.charAt(i));
            str.setCharAt(i, c);
        }
    }
    public void permuteArray(int index, int[] arr, ArrayList<int[]> set){
        if(index == arr.length - 1){
            int[] array = new int[arr.length];
            int i=0;
            for(int num : arr) {
                array[i++] = num;
                System.out.print(num +" ");
            }
            System.out.println();
            set.add(array);
        }
        int temp ;
        for(int i = index; i<arr.length; i++){
            temp = arr[i];
            arr[i] = arr[index];
            arr[index] = temp;

            permuteArray(index+1, arr, set);

            temp = arr[i];
            arr[i] = arr[index];
            arr[index] = temp;
        }
    }
}
