package cs311.hw7;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ForestPartitionSet<T> implements PartitionSet<T> {
    private class Node<T> {
        public T data;
        public int rank;
        public Node<T> parent;

        public Node(T data) {
            this.data = data;
            this.parent = this;
        }
    }

    private Map<T, Node<T>> valueToNode = new HashMap<>();

    public ForestPartitionSet() {};

    public ForestPartitionSet(Collection<? extends T> initialElements) {}

    @Override
    public T find(T toFind) {
        Node<T> curNode = valueToNode.get(toFind);
        while (curNode != curNode.parent) {
            curNode = curNode.parent;
        }
        return curNode.data;
    }

    @Override
    public void makeSet(T element) {
        valueToNode.put(element, new Node<T>(element));
    }

    @Override
    public void union(T t1, T t2) {

    }

    @Override
    public boolean inSameSet(T t1, T t2) {
        return false;
    }
}
