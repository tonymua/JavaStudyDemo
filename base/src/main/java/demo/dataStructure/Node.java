package demo.dataStructure;

/**
 * Java实现链表：节点Node
 *
 * @author:
 * @date: created in 19:45 2021/6/1
 * @version:
 */
public class Node {
    // 数据域
    public Object data;
    // 指针域，指向下一个节点
    public Node nextNode;

    public Node() {}

    public Node(Object data) {
        this.data = data;
    }

    public Node(Object data, Node nextNode) {
        this.data = data;
        this.nextNode = nextNode;
    }

    /**
     * 向链表添加数据
     * 
     * @param value
     *            要添加的数据
     * @param head
     *            头节点
     */
    public static void addData(Object value, Node head) {
        // 初始化要加入的节点
        Node newNode = new Node(value);
        // 临时节点
        Node tempNode = head;
        // 找到尾节点
        while (tempNode.nextNode != null) {
            tempNode = tempNode.nextNode;
        }
        tempNode.nextNode = newNode;
    }

    /**
     * 遍历链表
     * 
     * @param head
     *            头节点
     */
    public static void traverse(Node head) {
        // 临时节点，从头节点开始
        Node tempNode = head.nextNode;
        while (tempNode != null) {
            System.out.println(tempNode.data);
            tempNode = tempNode.nextNode;
        }
    }
}