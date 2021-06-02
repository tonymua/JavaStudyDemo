package demo.dataStructure;

import java.util.HashMap;

/**
 * 双向链表+哈希表实现LRU
 *
 * @author:
 * @date: created in 19:16 2021/6/2
 * @version:
 */
public class LRUCache {
    // 定义节点
    private class Node {
        private Object key;
        private Object value;
        private Node pre;
        private Node next;

        public Node() {}

        public Node(Object key, Object value) {
            this.key = key;
            this.value = value;
        }
    }

    // 虚拟头节点
    private Node dummyHead = new Node();
    // 虚拟尾节点
    private Node dummyTail = new Node();
    // LRUCache的容量
    private int capacity;
    // 当前节点的数量
    private int size;

    // 哈希表用来查询，存入双向链表的同时，放一份在HashMap
    private HashMap<Object, Node> hashMap = new HashMap<>();

    // 添加至头节点
    private void add(Node node) {
        // 在originHead和dummyHead中间插入node 双向链表
        Node originHead = dummyHead.next;
        dummyHead.next = node;
        node.pre = dummyHead;
        node.next = originHead;
        originHead.pre = node;
    }

    // 删除某节点
    private void del(Node node) {
        Node preNode = node.pre;
        Node nextNode = node.next;
        preNode.next = nextNode;
        nextNode.pre = preNode;
        node.pre = null;
        node.next = null;
    }

    // 初始化一个LRUCache
    public LRUCache(int capacity) {
        dummyHead.next = dummyTail;
        dummyTail.pre = dummyHead;
        this.capacity = capacity;
        size = 0;
    }

    public Object get(Object key) {
        Node node = hashMap.get(key);
        // 如果链表中没有则返回-1
        if (null == node) {
            return -1;
        } else {
            del(node);
            add(node);
            return node.value;
        }
    }

    public void put(Object key, Object value) {
        Node node = hashMap.get(key);
        // 如果链表中存在改节点，则更新改节点，并放至头节点
        if (null != node) {
            // 更新节点的值
            node.value = value;
            del(node);
            add(node);
        } else {
            // 链表不存在该节点
            // 如果此时链表还没满
            if (size <= capacity) {
                size++;
            } else {
                Node delNode = dummyTail.pre;
                hashMap.remove(delNode.key);
                del(delNode);
            }
            // 添加节点
            Node newNode = new Node(key, value);
            add(newNode);
            hashMap.put(key, new Node());
        }
    }
}