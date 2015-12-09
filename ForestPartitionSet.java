package cs311.hw7;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ForestPartitionSet<T> implements PartitionSet<T> {
    private class Node {
        public T data;
        public int rank = 1;
        public Node parent;

        public Node(T data) {
            this.data = data;
            this.parent = this;
        }
    }

    private Map<T, Node> valueToNode = new HashMap<>();

    public ForestPartitionSet() {}

    public ForestPartitionSet(Collection<? extends T> initialElements) {
        for (T t: initialElements) {
            makeSet(t);
        }
    }

    private Node findRepresentative(Node startNode) {
        Node curNode = startNode;
        while (curNode != curNode.parent) {
            curNode = findRepresentative(curNode.parent);
        }
        return curNode;
    }

    @Override
    public T find(T toFind) {
        Node node = valueToNode.get(toFind);
        return findRepresentative(node).data;
    }

    @Override
    public void makeSet(T element) {
        valueToNode.put(element, new Node(element));
    }

    @Override
    public void union(T t1, T t2) {
        Node root1 = findRepresentative(valueToNode.get(t1));
        Node root2 = findRepresentative(valueToNode.get(t2));

        if (root1.rank < root2.rank) {
            root1.parent = root2;
        } else if (root1.rank > root2.rank) {
            root2.parent = root1;
        } else {
            root2.parent = root1;
            root1.rank += 1;
        }
    }

    @Override
    public boolean inSameSet(T t1, T t2) {
        return find(t1).equals(find(t2));
    }
}
