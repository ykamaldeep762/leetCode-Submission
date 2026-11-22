/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public int countNodes(TreeNode root) {
        if (root == null) return 0;
        
        int leftH = height(root.left);
        int rightH = height(root.right);
        
        if (leftH == rightH) {
            // Left is perfect
            return (1 << leftH) + countNodes(root.right);
        } else {
            // Right is perfect
            return (1 << rightH) + countNodes(root.left);
        }
    }
    
    int height(TreeNode node) {
        int h = 0;
        while (node != null) {
            h++;
            node = node.left;
        }
        return h;
    }
}