import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
//
//class TreeNode
//{
//    int val;
//    TreeNode left;
//    TreeNode right;
//
//    public TreeNode(int v)
//    {
//        val = v;
//    }
//
//    public ArrayList getLastLevel(TreeNode root)
//    {
//        TreeNode cursor = root;
//        ArrayList<TreeNode> list = new ArrayList<>();
//        if(root == null)
//            return list;
//
//        // Travel left down the Tree
//        getLastLevel(cursor.left);
//
//        // Travel right down the Tree
//        getLastLevel(cursor.right);
//
//        if(cursor.left == null && cursor.right == null)
//
//            list.add(cursor);
//
//        return list;
//    }
//
//    public int getHeight(TreeNode root)
//    {
//        if(root == null)
//            return 0;
//
//        int leftHeight = getHeight(root.left);
//        int rightHeight = getHeight(root.right);
//
//        if(leftHeight > rightHeight)
//            return (leftHeight + 1);
//        else
//            return (rightHeight +1);
//
//    }
//
//
//}
//
//
//class Solution {
//    public static void main(String args[] ) throws Exception {
//        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
//        TreeNode root = new TreeNode(1);
//        TreeNode val2 = new TreeNode(2);
//        TreeNode val3 = new TreeNode(3);
//        TreeNode val4 = new TreeNode(4);
//        TreeNode val5 = new TreeNode(5);
//        TreeNode val6 = new TreeNode(6);
//        TreeNode val7 = new TreeNode(7);
//        TreeNode val8 = new TreeNode(8);
//        TreeNode val9 = new TreeNode(9);
//
//        root.left = val2;
//        root.right = val3;
//        val2.left = val4;
//        val2.right = val5;
//        val4.left = val7;
//        val3.right = val6;
//        val6.left = val8;
//        val6.right = val9;
//
//        int height = root.getHeight(root);
//
//        ArrayList<Integer> test = new ArrayList<Integer>();
//        //root.getLastLevel(root, test);
//
//
//        for(int i = 0 ; i < test.size(); i++)
//        {
//            System.out.print(test.get(i));
//            System.out.print(", ");
//        }
//
//    }
//
//
//}