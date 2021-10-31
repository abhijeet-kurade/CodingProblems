package GFG_AMAZON;

public class Arrays {
    public static void main(String[] args) {
        System.out.println(convertFive1(100766));
    }

    public static int convertFive(int num){
        int newNum = 0;
        int digit = 1;
        while(num != 0){
            int last_digit = num % 10 == 0 ? 5 : num % 10;
            num = num /10;
            newNum += last_digit * digit;
            digit *= 10;
        }
        return newNum;
    }
    public static int convertFive1(int num){
        String number = String.valueOf(num);
        String newNum = "";
        for(int i=0; i<number.length(); i++){
            newNum += number.charAt(i) == '0' ? '5' : number.charAt(i);
        }
        return Integer.parseInt(newNum);
    }

}
