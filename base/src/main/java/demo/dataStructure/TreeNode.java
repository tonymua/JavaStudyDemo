package demo.dataStructure;

import lombok.Data;

/**
 * Java实现二叉树
 */
@Data
public class TreeNode {
    // 数据
    private Object value;
    // 左节点
    private TreeNode leftNode;
    // 右节点
    private TreeNode rightNode;

    public TreeNode(Object value) {
        this.value = value;
    }

    /**
     * 中序遍历
     * 
     * @param rootTreeNode 根节点
     */
    public static void inTraverseBTree(TreeNode rootTreeNode) {
        if (rootTreeNode != null) {
            // 访问根节点
            System.out.println(rootTreeNode.getValue());
            // 访问左节点
            System.out.println(rootTreeNode.getLeftNode());
            // 访问右节点
            System.out.println(rootTreeNode.getRightNode());
        }
    }
}
