package cs311.hw7;

public interface PartitionSet<T> {
    /*
     * Return a representative member of toFind's set
     */
    T find(T toFind);

    /*
     * Create a subset containing element
     */
    void makeSet(T element);

    /*
     * Combine the subsets containing t1 and t2
     */
    void union(T t1, T t2);

    /*
     * Return true if t1 and t2 are in the same subset.
     */
    boolean inSameSet(T t1, T t2);
}
