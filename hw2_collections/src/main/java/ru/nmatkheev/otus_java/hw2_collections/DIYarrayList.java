package ru.nmatkheev.otus_java.hw2_collections;


import java.util.*;
import java.util.function.UnaryOperator;


public class DIYarrayList<T> implements List<T> {

    private static final Object[] EMPTY_ELEMENTDATA = {};

    private static final int DEFAULT_CAPACITY = 10;

    private int size = 0;

    private Object[] elementData;

    public DIYarrayList(int initCapacity) {
        if (initCapacity > 0) {
            this.elementData = new Object[initCapacity];
        }
        else if (initCapacity == 0)
            this.elementData = EMPTY_ELEMENTDATA;
        else
            throw new IllegalArgumentException("Capacity must be non-negative integer!");
    }

    public DIYarrayList() {
        this.elementData = EMPTY_ELEMENTDATA;
    }


    public static <T> void copy(List<? super T> dest, List<? extends T> src) {
        int srcSize = src.size();
        if (srcSize > dest.size())
            throw new IndexOutOfBoundsException("Source does not fit in dest");

        for (int i=0; i<srcSize; i++)
            dest.set(i, src.get(i));
    }


    private void grow(int to_alloc) {
        int eff_vol = Math.max(to_alloc, DEFAULT_CAPACITY);
        int real_vol = eff_vol + size;

        if (real_vol - elementData.length > 0) {
            elementData = Arrays.copyOf(elementData, real_vol);
        }
    }

    private void rangeCheck(int index) {
        if (index >= size)
            throw new IndexOutOfBoundsException("Out of bound");
    }


    public static <T> boolean addAll(Collection<? super T> c, T... elements) {
        boolean result = false;
        for (T element : elements)
            result |= c.add(element);
        return result;
    }


    public static <T> void sort(List<T> list, Comparator<? super T> c) {
        Object[] a = list.toArray();
        Arrays.sort(a, (Comparator) c);

        for (int i = 0; i < list.size(); i++) {
            list.set(i, (T) a[i]);
        }
    }

    public boolean addAll(Collection<? extends T> c) {
        throw new UnsupportedOperationException();
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public boolean contains(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<T> iterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elementData, size);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < size)
            // Make a new array of a's runtime type, but my contents:
            return (T[]) Arrays.copyOf(elementData, size, a.getClass());
        System.arraycopy(elementData, 0, a, 0, size);
        if (a.length > size)
            a[size] = null;
        return a;
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        for (int i=0; i<size; i++) {
            out.append(String.format("%s, ", elementData[i]));
        }
        return "[" + out.toString() + String.format("], size=%d", size);
    }

    @Override
    public boolean add(T t) {
        grow(1);
        this.elementData[size++] = t;
        return true;
    }

    public boolean remove(Object o) {
        return false;
    }

    public boolean containsAll(Collection<?> c) {
        return false;
    }

    public boolean addAll(int index, Collection<? extends T> c) {
        throw new UnsupportedOperationException();
    }

    public boolean removeAll(Collection<?> c) {
        return false;
    }

    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    public void replaceAll(UnaryOperator<T> operator) {

    }

    public void sort(Comparator<? super T> c) {

    }

    public void clear() {
        throw new UnsupportedOperationException();
    }

    public T set(int index, T element) {
        rangeCheck(index);

        T oldValue = (T) elementData[index];
        elementData[index] = element;
        return oldValue;
    }

    public T get(int index) {
        rangeCheck(index);
        return (T) elementData[index];
    }

    public void add(int index, T element) {
        throw new UnsupportedOperationException();
    }

    public T remove(int index) {
        throw new UnsupportedOperationException();
    }

    public int indexOf(Object o) {
        throw new UnsupportedOperationException();
    }

    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException();
    }

    public ListIterator<T> listIterator() {
        throw new UnsupportedOperationException();
    }

    public ListIterator<T> listIterator(int index) {
        throw new UnsupportedOperationException();
    }

    public List<T> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    public Spliterator<T> spliterator() {
        throw new UnsupportedOperationException();
    }

    private class ListItr extends AbstractList.Itr implements ListIterator<E> {
        ListItr(int index) {
            cursor = index;
        }

        public boolean hasPrevious() {
            return cursor != 0;
        }

        public E previous() {
            checkForComodification();
            try {
                int i = cursor - 1;
                E previous = get(i);
                lastRet = cursor = i;
                return previous;
            } catch (IndexOutOfBoundsException e) {
                checkForComodification();
                throw new NoSuchElementException();
            }
        }

        public int nextIndex() {
            return cursor;
        }

        public int previousIndex() {
            return cursor-1;
        }

        public void set(E e) {
            if (lastRet < 0)
                throw new IllegalStateException();
            checkForComodification();

            try {
                AbstractList.this.set(lastRet, e);
                expectedModCount = modCount;
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }

        public void add(E e) {
            checkForComodification();

            try {
                int i = cursor;
                AbstractList.this.add(i, e);
                lastRet = -1;
                cursor = i + 1;
                expectedModCount = modCount;
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }
    }
}
