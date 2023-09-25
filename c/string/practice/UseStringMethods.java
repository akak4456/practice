package c.string.practice;

public class UseStringMethods {
    public static void main(String[] args) {
        String str = "The String class represents character strings.";
        UseStringMethods methods = new UseStringMethods();
        methods.printWords(str);
        methods.findString(str, "string");
        methods.findAnyCaseString(str, "string");
        methods.countChar(str, 's');
        methods.printContainWords(str, "ss");
    }

    public void printWords(String str) {
        String[] array = str.split(" ");
        for(String s : array) {
            System.out.println(s);
        }
    }

    public void findString(String str, String findStr) {
        System.out.println("string is appeared at "+ str.indexOf(findStr));
    }

    public void findAnyCaseString(String str, String findStr) {
        System.out.println("string is appeared at " + str.toLowerCase().indexOf(findStr));
    }

    public void countChar(String str, char c) {
        char[] ch = str.toCharArray();
        int cnt = 0;
        for(char cInStr : ch) {
            if(cInStr == c) {
                cnt++;
            }
        }
        System.out.println("char \'s\' count is "+cnt);
    }

    public void printContainWords(String str, String findStr) {
        String[] array = str.split(" ");
        for(String s : array) {
            if(s.contains(findStr)) {
                System.out.println(s + " contains "+findStr);
            }
        }
    }
}