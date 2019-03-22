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

    public String longestPalindrome(String s){
        int length = s.length();
        if(s == null || length < 1)
            return "";
        int begin = 0;
        int end = 0;

        for(int i = 0; i < length; i++){
            int length1 = palindromeHelper(s, i, i);
            int length2 = palindromeHelper(s, i, i+1);
            int maxLength = Math.max(length1, length2);
            if(maxLength > end - begin){
                begin = i - (maxLength - 1) / 2;
                end = i + (maxLength / 2);
            }

        }

        return s.substring(begin, end + 1);
    }

    private int palindromeHelper(String s, int l, int r){
        while(l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)){
            l--;
            r++;
        }
        return r - l - 1;

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


    private int numRows;
    private int numCols;
    public int numIslands(char[][] grid){

        numRows = grid.length;
        if(numRows == 0)
            return 0;

        numCols = grid[0].length;
        int count = 0;
        for(int i = 0; i < numRows; i++){
            for(int j = 0; j < numCols; j++){
                if(grid[i][j] == '1'){
                    DFSIslandHelper(grid, i, j);
                    count++;
                }
            }
        }
        return count;
    }

    private void DFSIslandHelper(char[][]grid, int i, int j){
        if(i < 0 || j < 0 || i >= numRows || j >= numCols || grid[i][j] != '1')
            return;
        // Unmark this and other surrounding 1s (Up, Down, Left, Right)
        grid[i][j] = 0;
        DFSIslandHelper(grid, i-1, j);
        DFSIslandHelper(grid, i+1, j);
        DFSIslandHelper(grid, i, j-1);
        DFSIslandHelper(grid, i, j+1);
    }


    public int lengthOfLongestSubstring(String s){
        int len = s.length();
        int answer = 0;

        if(len <= 1)
            return len;

        Map<Character, Integer> map = new HashMap<>();

        for(int i = 0, j = 0; j < len; j++){
            // if HashMap contains the character at this j index,
            //   move left index to position of character + 1
            if(map.containsKey(s.charAt(j)))
                i = Math.max(map.get(s.charAt(j)) + 1, i);

            answer = Math.max(answer, j - i  + 1);
            map.put(s.charAt(j), j);
        }
        return answer;


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


        System.out.println("__________________________________________________________________\n");
        System.out.println("public int numIslands(char[][] grid)");
        char[][] grid = {   {'1', '1', '1', '1', '0'},
                            {'1', '1', '0', '1', '0'},
                            {'1', '1', '0', '0', '0'},
                            {'0', '0', '1', '0', '1'}};

        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("Number of Islands: " + test.numIslands(grid));



        System.out.println("__________________________________________________________________\n");
        System.out.println("public String longestPalindrome(String s)");
        String s = "babad";
        System.out.println(String.format("Longest Palindrome in \"%s\" is \"%s\"", s, test.longestPalindrome(s)));


        System.out.println("__________________________________________________________________\n");
        System.out.println("public int lengthOfLongestSubstring(String s)");
        s = "abcabcbb";
        System.out.println(String.format("Length of Longest Substring without Repeating Characters in \"%s\" is %d", s, test.lengthOfLongestSubstring(s)));
    }
}
