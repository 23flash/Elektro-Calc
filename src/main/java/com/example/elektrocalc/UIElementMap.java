package com.example.elektrocalc;

/**
 * A generic class that holds a triplet of elements.
 *
 * @param <F> the type of the first element
 * @param <S> the type of the second element
 * @param <T> the type of the third element
 */
public class UIElementMap<F,S,T,E> {
    private final F first;
    private final S second;
    private final T third;
    private final E forth;
    /**
     * Constructs a UIElementMap with the specified values.
     *
     * @param first  the first element
     * @param second the second element
     * @param third  the third element
     */
    public UIElementMap(F first, S second, T third,E forth) {
        this.first = first;
        this.second = second;
        this.third = third;
        this.forth = forth;
    }
    /**
     * Returns the first second third element.
     *
     * @return the first second third element
     */
    public F getFirst() { return first; }
    public S getSecond() { return second; }
    public T getThird() { return third; }
    public E getForth() { return forth; }
}
