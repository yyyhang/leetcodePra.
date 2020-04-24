public class HelloWorld{

    public static void main(String []args){
        System.out.println("Hello World");
        String digits = "234";
        String[] d = new String[]{" ",
                "",
                "abc",
                "def",
                "ghi",
                "jkl",
                "mno",
                "pqrs",
                "tuv",
                "wxyz"};
        int l = 2;
        String s = d[Character.getNumericValue(digits.charAt(l))];
        System.out.println(Character.getNumericValue('9'));
    }
}