package cs311.hw7;

import java.util.HashMap;

public class DefaultHashMap<K, V> extends HashMap<K, V> {
    protected V defaultValue;

    public DefaultHashMap(V defaultValue) {
        this.defaultValue = defaultValue;
    }

    @Override
    public V get(Object k) {
        V maybeValue = super.get(k);
        return maybeValue == null ? defaultValue : maybeValue;
    }
}
