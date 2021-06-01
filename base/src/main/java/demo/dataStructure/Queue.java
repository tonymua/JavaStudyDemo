package demo.dataStructure;

/**
 * Java实现队列
 */
public class Queue<E> {
    private Object[] data = null;
    // 队列容量
    private int maxSize;
    // 队列头，允许删除
    private int front;
    // 队列尾，允许插入
    private int rear;

    public Queue(int initialSize) {
        if (initialSize >= 0) {
            this.maxSize = initialSize;
            data = new Object[initialSize];
            front = rear = 0;
        } else {
            throw new RuntimeException("初始化大小不能小于0：" + initialSize);
        }
    }

    // 判空
    public boolean empty() {
        return rear == front ? true : false;
    }

    // 入队
    public boolean add(E e) {
        if (rear == maxSize) {
            throw new RuntimeException("队列已满，无法插入新的元素！");
        } else {
            data[rear++] = e;
            return true;
        }
    }

    // 出队
    public E poll() {
        if (empty()) {
            throw new RuntimeException("空队列！");
        } else {
            // 保留队列的front端的元素的值
            Object value = data[front];
            // 释放队列的front端的元素
            data[front++] = null;
            return (E)value;
        }
    }

    // 队列长度
    public int length() {
        return rear - front;
    }

    // 遍历队列
    public static void traverseQueue(Queue queue) {
        // front的位置
        int front = queue.front;
        while (front != queue.rear) {
            System.out.println(queue.data[front]);
            // 移动front
            front = (front + 1) % queue.data.length;
        }
    }

}
