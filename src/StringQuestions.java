import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class StringQuestions {


    public boolean isPalindrome(String str){

        if(str.length() <= 0){
            return false;
        }

        for(int i = 0; i < str.length()/2; i ++)
        {
            if(str.charAt(i) != str.charAt(str.length() - i - 1))
                return false;
        }
        return true;

    }

    public boolean wordPattern(String pattern, String str) {

        String[] strArr = str.split(" ");

        if(pattern.length() != strArr.length)
            return false;

        //Map<Character, String> map = new HashMap<Character, String>();
        Map map = new HashMap();
        //map.put(pattern.charAt(0), strArr[0]);

        for(Integer i = 0; i < pattern.length(); i++)
        {
            if (map.put(pattern.charAt(i), i) != map.put(strArr[i], i))
                return false;
        }
        return true;
    }



    public String convertToWords(int n){

        if(n == 0)
            return "zero";

        String word  = "";

        // handle hundreds place
        word += convertToWordsHelper((n / 100) % 10, "hundred ");

        if(n > 100)
        {
            word += "and ";
        }

        // handle tens and ones place
        word += convertToWordsHelper( (n % 100), "");

        return word;

    }


    private String convertToWordsHelper(int n, String word){

        final String ones[] = {"", "one ", "two ", "three ", "four " , "five ", "six ", "seven ", "eight ",
                "nine ", "ten ", "eleven ", "twelve ", "thirteen ", "fourteen ", "fifteen ", "sixteen ",
                "seventeen ", "eighteen ", "nineteen "};

        final String tens[] = {"", "", "twenty ", "thirty ", "forty ", "fifty ", "sixty ", "seventy ", "eighty ", "ninety "};

        String str = "";

        if(n >= 20){
            str += tens[n/10] + ones[n%10];
        }
        else{
            str += ones[n];
        }

        if(n!=0)
            str += word;


        return str;
    }

    public boolean isValid(String s) {
        Stack<Character> myStack = new Stack<Character>();

        for(char c : s.toCharArray()){
            if(c == '(')
                myStack.push(')');
            else if(c == '{')
                myStack.push('}');
            else if(c == '[')
                myStack.push(']');
            else if(myStack.isEmpty() || myStack.pop() != c)
                return false;
        }
        return myStack.isEmpty();
    }


    public int numJewelsInStones(String J, String S) {
        Map<Character, Character> map = new HashMap<Character, Character>();

        for(char j : J.toCharArray()){
            map.put(j,j);
        }

        int count = 0;
        for(char s : S.toCharArray()){
            if(map.containsKey(s))
                count++;
        }

        return count;
    }

    public boolean isAnagram(String s, String t) {

//         if(s.length() != t.length())
//             return false;

//         Map<Character, Integer> map = new HashMap<Character, Integer>();

//         for(int i = 0; i < s.length(); i++){
//             map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
//             map.put(t.charAt(i), map.getOrDefault(t.charAt(i), 0) - 1);
//         }

//         for(Integer i:map.values()){
//             if(i != 0)
//                 return false;
//         }

//         return true;
        if(s.length() != t.length())
            return false;

        int[] letters = new int['z'-'a'+1];

        for(int i = 0; i < s.length(); i++){
            letters[s.charAt(i) - 'a']++;
            letters[t.charAt(i) - 'a']--;
        }


        for(int count: letters) {
            if(count != 0) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args)
    {
        StringQuestions test = new StringQuestions();



        System.out.println("__________________________________________________________________\n");
        System.out.println("public boolean wordPattern(String pattern, String str) ");
        String pattern = "aaaabaaaa";
        String words = "dog dog dog dog cat dog dog dog dog";

        System.out.println(String.format("pattern = %s \nwords = %s", pattern, words));
        System.out.println(test.wordPattern(pattern, words));


        System.out.println("__________________________________________________________________\n");
        System.out.println("public boolean isPalindrome(String str)");
        String palindrome = "racecarracecar";
        System.out.println(palindrome);
        System.out.println(test.isPalindrome(palindrome));

        System.out.println("__________________________________________________________________\n");
        System.out.println("public String convertToWords(int n)");
        int n = 218;
        System.out.println(n + " = " + test.convertToWords(n));
        n = 220;
        System.out.println(n + " = " + test.convertToWords(n));

        n = 999;
        System.out.println(n + " = " + test.convertToWords(n));
        n = 1;
        System.out.println(n + " = " + test.convertToWords(n));
        n = 0;
        System.out.println(n + " = " + test.convertToWords(n));

        n = 100;
        System.out.println(n + " = " + test.convertToWords(n));
        n = 101;
        System.out.println(n + " = " + test.convertToWords(n));

        System.out.println("__________________________________________________________________\n");
        System.out.println("public boolean isValid(String s)");
        String checkString = "(((((((((([[[[[[[[[[{{{{{}}}}}]]]]]]]]]]))))))))))";
        System.out.println(String.format("checkString = %s", checkString));
        System.out.println(test.isValid(checkString));

        System.out.println("__________________________________________________________________\n");
        System.out.println("public int numJewelsInStones(String J, String S)");
        String J = "aAtsdf";
        String S = "aAAbbbbtsdfdrtsdfgsdrtsdfg";
        System.out.println(String.format("J = %s, S = %s", J, S));
        System.out.println(test.numJewelsInStones(J, S));
    }
}
