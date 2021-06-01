package demo.dataStructure;

/**
 * Java实现栈：Stack
 *
 * @author:
 * @date: created in 20:00 2021/6/1
 * @version:
 */
public class Stack {
    // 栈顶
    public Node stackTop;
    // 栈底
    public Node stackBottom;

    public Stack() {}

    public Stack(Node stackTop, Node stackBottom) {
        this.stackTop = stackTop;
        this.stackBottom = stackBottom;
    }

    /**
     * 进栈
     * 
     * @param stack
     * @param value
     */
    public static void pushStack(Stack stack, Object value) {
        // 封装数据成节点
        Node newNode = new Node(value);
        // 栈顶本来指向的节点交由新节点来指向
        newNode.nextNode = stack.stackTop;
        // 栈顶指针指向新节点
        stack.stackTop = newNode;
    }

    /**
     * 遍历栈
     */
    public static void traverse(Stack stack) {
        Node stackTop = stack.stackTop;
        // 栈顶元素的指针不指向栈底，就一直遍历
        while (stackTop != stack.stackBottom) {
            System.out.println(stackTop.data);
            stackTop = stackTop.nextNode;
        }
    }
}