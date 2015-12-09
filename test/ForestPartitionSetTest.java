package cs311.hw7.test;

import cs311.hw7.ForestPartitionSet;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class ForestPartitionSetTest {
    ForestPartitionSet<Integer> pSet;

    @org.junit.Before
    public void setup() {
        List<Integer> initialElements = Arrays.asList(new Integer[] { 1, 2, 3, 4 });
        pSet = new ForestPartitionSet<>(initialElements);
    }

    @org.junit.Test
    public void testFalseInSameSet() {
        assertFalse(pSet.inSameSet(1, 2));
    }

    @org.junit.Test
    public void testSimpleFind() throws Exception {
        assertEquals(new Integer(1), pSet.find(1));
    }

    @org.junit.Test
    public void testSimpleMakeSet() throws Exception {
        pSet = new ForestPartitionSet<>();
        pSet.makeSet(1);
        assertEquals(new Integer(1), pSet.find(1));
    }

    @org.junit.Test
    public void testUnionAndInSameSet() throws Exception {
        assertFalse(pSet.inSameSet(1, 2));
        pSet.union(1, 2);
        assertTrue(pSet.inSameSet(1, 2));
    }
}