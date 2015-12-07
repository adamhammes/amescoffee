package cs311.hw7;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/*
 * An extension of HashSet with some additional useful methods
 */
public class MathSet<T> extends HashSet<T> implements Set<T> {
    public MathSet() {
        super();
    }

    public MathSet(Collection<T> startingItems) {
        super(startingItems);
    }

    public MathSet<T> union(Collection<T> other) {
        MathSet<T> toReturn = new MathSet<>(this);
        for (T t: other) {
            toReturn.add(t);
        }
        return toReturn;
    }

    public MathSet<T> intersect(Collection<T> other) {
        MathSet<T> toReturn = new MathSet<>();
        for (T t: this) {
            if (other.contains(t)) {
                toReturn.add(t);
            }
        }

        return toReturn;
    }

    public MathSet<T> minus(Collection<T> other) {
        MathSet<T> toReturn = new MathSet<>(this);
        for (T t: other) {
            toReturn.remove(t);
        }

        return toReturn;
    }
}
