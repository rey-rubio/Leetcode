import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;
import java.util.Stack;

class  TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}


class Tree {

    TreeNode root;

    public Tree(){
        root = null;
    }

    public TreeNode prune(TreeNode root){

        if(root == null)
            return null;

        // traverse left subtree
        root.left = prune(root.left);
        // traverse right subtree
        root.right = prune(root.right);

        //System.out.print(root.val + " ");

        if(root.val == 0 && root.left == null && root.right == null)
            root = null;

        return root;
    }

    public void preorderTraversalRec(TreeNode root){

        if(root == null)
            return;

        System.out.print(root.val + " ");
        preorderTraversalRec(root.left);
        preorderTraversalRec(root.right);
        return;

    }

    public void preorderTraveral(TreeNode root){

        if(root == null)
            return;

        Stack<TreeNode> treeNodeStack = new Stack<TreeNode>();
        treeNodeStack.push(root);

        while(treeNodeStack.empty() == false){

            TreeNode node = treeNodeStack.peek();
            treeNodeStack.pop();


            System.out.print(node.val + " ");

            if(node.right != null){
                treeNodeStack.push(node.right);
            }



            if(node.left != null){
                treeNodeStack.push(node.left);
            }
        }

    }


    public static void main(String[] args){

        Tree tree = new Tree();
        tree.root =  new TreeNode(1);
        tree.root.left = new TreeNode(1);
        tree.root.left.left = new TreeNode(1);
        tree.root.left.right = new TreeNode(1);
        tree.root.left.left.left = new TreeNode(0);

        tree.root.right = new TreeNode(0);
        tree.root.right.left = new TreeNode(0);
        tree.root.right.right = new TreeNode(1);

        tree.preorderTraveral(tree.root);

        System.out.println();
        tree.prune(tree.root);

        tree.preorderTraveral(tree.root);

        System.out.println();


        File[] files = new File("R:\\Etc\\Dashcam\\LIE\\Test").listFiles();
        for(File file: files){
            if(file.isFile()){
                try {
                    FileReader fr = new FileReader(file);
                    int i;
                    while ((i=fr.read()) != -1)
                        System.out.print((char) i);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println(file.getName());
            }
        }

    }
}
