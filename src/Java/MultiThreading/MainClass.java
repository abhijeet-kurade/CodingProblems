package Java.MultiThreading;

public class MainClass {
    public static void main(String[] args) {
        String a1 = "Apple";
        String a2 = "Apple";
        String a3 = "Apple";
        String a4 = "Apple";

        System.out.println(a1 == a2);
        System.out.println(a1 == a3);
        System.out.println(a2 == a3);
        System.out.println(a3 == a4);

        System.out.println(a1.equals(a2));
        System.out.println(a1.equals(a3));
        System.out.println(a1.equals(a3));
        System.out.println(a1.equals(a4));
    }

    static int missingAP(int[] arr){
        int d1 = arr[1] - arr[0];
        int d2 = arr[2] - arr[1];
        int d3 = arr[3] - arr[2];
        int diff = -1;
        if(d1==d2) diff = d1;
        else if(d2 == d3) diff = d2;
        else diff = d1;
        //System.out.println(diff);
        //tn = a0 + d*(n-1)

        int a0 = arr[0];
        
        int left = 0;
        int right = arr.length-1;
        while(left <= right){
            int mid = left + (right - left);
            
        }



        for(int i=0; i<arr.length; i++){
            int expected = a0 + diff * i;
            if(expected != arr[i]) return expected;
        }
        return -1;
    }
}
