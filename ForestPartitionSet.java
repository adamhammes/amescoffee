package cs311.hw7;

import java.util.Collection;

public class ForestPartitionSet<T> implements PartitionSet<T> {

    public ForestPartitionSet() {};

    public ForestPartitionSet(Collection<? extends T> initialElements) {}

    @Override
    public T find(T toFind) {
        return null;
    }

    @Override
    public void makeSet(T element) {

    }

    @Override
    public void union(T t1, T t2) {

    }

    @Override
    public boolean inSameSet(T t1, T t2) {
        return false;
    }
}
