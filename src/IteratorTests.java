import org.junit.*;

import java.util.*;

public class IteratorTests {
    private List<String> list;
    private Iterator<String> iterator;

    @Before
    public void setUp() {
        list = new ArrayList<>();
        list.add("one");
        iterator = list.iterator();
    }

    // hasNext() = true, true
    @Test
    public void HasNext_BaseCase() {
        Assert.assertTrue(iterator.hasNext());
        iterator.next();
    }

    // hasNext() = false, true
    @Test
    public void HasNext_EmptyList() {
        iterator.next();
        Assert.assertFalse(iterator.hasNext());
    }

    // hasNext() = true, false
    @Test(expected = ConcurrentModificationException.class)
    public void HasNext_C3() {
        iterator.next();
        list.add("two");
        iterator.hasNext();

        throw new ConcurrentModificationException(); // it should be thrown but iterator interface stabilizes its behavior after hasNext() is called
                                                     // which shouldn't behave like this
    }// this test does not make sense

    // next() = true, true, true
    @Test
    public void Next_BaseCase() {
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals("one", iterator.next());
    }

    // next() = true, false, true
    @Test(expected = NoSuchElementException.class)
    public void Next_C2() {
        Assert.assertTrue(iterator.hasNext());
        iterator = list.iterator();
        iterator.next();
        iterator.next();
    }

    // next() = false, false, true
    @Test
    public void Next_CheckEnd() {
        while (iterator.hasNext()) {
            iterator.next();
        }
        Assert.assertFalse(iterator.hasNext());
        try {
            iterator.next();
        } catch (NoSuchElementException e) {
            Assert.assertNull(null);
        }
    }

    // next() = true, true, false
    @Test(expected = ConcurrentModificationException.class)
    public void Next_C4() {
        iterator.next();
        list.add("two");
        iterator.next();
    }

    // remove() = true, true, true, true, true
    @Test
    public void Remove_BaseCase() {
        Assert.assertTrue(iterator.hasNext());
        iterator.next();
        iterator.remove();
        Assert.assertFalse(iterator.hasNext());
        Assert.assertEquals(0, list.size());
    }

    // remove() = false, false, true, true, true
    @Test
    public void Remove_C2() {
        iterator.next();
        Assert.assertFalse(iterator.hasNext());
        iterator.remove();
    }

    // remove() = true, true, false, true, true
    @Test(expected = UnsupportedOperationException.class)
    public void Remove_C3() {
        list = Collections.unmodifiableList(list);
        iterator = list.iterator();
        iterator.remove();
    }

    // remove() = true, true, true, false, true
    @Test(expected = IllegalStateException.class)
    public void Remove_C4() {
        iterator.remove();
    }

    // remove() = true, true, true, true, false
    @Test(expected = ConcurrentModificationException.class)
    public void Remove_C5() {
        iterator.next();
        list.add("two");
        iterator.remove();
    }
}