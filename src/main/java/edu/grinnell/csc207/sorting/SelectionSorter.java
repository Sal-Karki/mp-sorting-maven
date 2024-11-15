package edu.grinnell.csc207.sorting;

import java.util.Comparator;

/**
 * Something that sorts using selection sort.
 *
 * @param <T>
 *   The types of values that are sorted.
 * @author Samuel A. Rebelsky
 * @author Sal Karki
 */

public class SelectionSorter<T> implements Sorter<T> {
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The way in which elements are ordered.
   */
  Comparator<? super T> order;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create a sorter using a particular comparator.
   *
   * @param comparator
   *   The order in which elements in the array should be ordered
   *   after sorting.
   */
  public SelectionSorter(Comparator<? super T> comparator) {
    this.order = comparator;
  } // SelectionSorter(Comparator)

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Sort an array in place using selection sort.
   *
   * @param values
   *   an array to sort.
   *
   * @post
   *   The array has been sorted according to some order (often
   *   one given to the constructor).
   * @post
   *   For all i, 0 &lt; i &lt; values.length,
   *     order.compare(values[i-1], values[i]) &lt;= 0
   */
  @Override
  public void sort(T[] values) {
    int n = values.length;
    // iterate through array
    for (int i = 0; i < n - 1; i++) {
      // finds index of smallest array
      int indexofSmallest = i;
      for (int j = i + 1; j < n; j++) {
        if (order.compare(values[j], values[indexofSmallest]) < 0) {
          indexofSmallest = j;
        }
      }
      // swaps smallest element with current element
      if (indexofSmallest != i) {
        T temp = values[i];
        values[i] = values[indexofSmallest];
        values[indexofSmallest] = temp;
      }
    }
  } // sort(T[])
} // class SelectionSorter
