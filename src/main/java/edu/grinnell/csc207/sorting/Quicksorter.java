package edu.grinnell.csc207.sorting;

import java.util.Comparator;

/**
 * Sorts an array in place using Quicksort with three-way partitioning.
 * @param <T>
 *   The types of values that are sorted.
 * @author Samuel A. Rebelsky
 * @author Sal Karki
 */
public class Quicksorter<T> implements Sorter<T> {
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The way in which elements are ordered.
   */
  private final Comparator<? super T> order;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create a sorter using a particular comparator.
   *
   * @param comparator
   *   The order in which elements in the array should be ordered after sorting.
   */
  public Quicksorter(Comparator<? super T> comparator) {
    this.order = comparator;
  } // Quicksorter(Comparator)

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Sort an array in place using Quicksort.
   *
   * @param values
   *   An array to sort.
   */
  @Override
  public void sort(T[] values) {
    quicksort(values, 0, values.length - 1);
  } // sort(T[])

  /**
   * Quicksort algorithm
   * @param values
   *   The array to sort.
   * @param left
   *   The left boundary of the subarray.
   * @param right
   *   The right boundary of the subarray.
   */
  private void quicksort(T[] values, int left, int right) {
    if (left < right) {
      //split array using dutch flag method
      int[] bounds = divideDutch(values, left, right);
      // call quicksort on each subarray
      quicksort(values, left, bounds[0] - 1);
      quicksort(values, bounds[1] + 1, right);
    }
  } // quicksort

  /**
   * Divides array into 3 parts:
   * less than pivot.
   * equal to pivot.
   * larger than pivot.
   * @param values
   *   array of some values.
   * @param left
   *   left boundary.
   * @param right
   *   right boundary.
   * @return
   *   bounds of the section to pivot.
   */
  private int[] divideDutch(T[] values, int left, int right) {
    T pivot = values[right];
    int low = left;
    int high = right;
    int mid = left;

    // Dutch National Flag partitioning
    while (mid <= high) {
      int comparedToPivot = order.compare(values[mid], pivot);

      if (comparedToPivot < 0) {
        // swap low and mid, then increment
        T temp = values[low];
        values[low] = values[mid];
        values[mid] = temp;
        low++;
        mid++;
      } else if (comparedToPivot> 0) {
        // swap mid and high, and increment
        T temp = values[mid];
        values[mid] = values[high];
        values[high] = temp;
        high--;
      } else {
        // move the middle index
        mid++;
      }
    }
    //returns bounds of pivot
    return new int[]{low, high};
  } // partition
} // class Quicksorter
