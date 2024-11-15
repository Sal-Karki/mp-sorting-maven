package edu.grinnell.csc207.sorting;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Sorts an array using merge sort.
 *
 * @param <T>
 *   The types of values that are sorted.
 * @author Samuel A. Rebelsky
 * @author Sal Karki
 */
public class MergeSorter<T> implements Sorter<T> {
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
  public MergeSorter(Comparator<? super T> comparator) {
    this.order = comparator;
  } // MergeSorter(Comparator)

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Sort an array in place using merge sort.
   *
   * @param values
   *   an array to sort.
   *
   * @post
   *   The array has been sorted according to some order (often
   *   one given to the constructor).
   * @post
   *   For all i, 0 < i < values.length,
   *     order.compare(values[i-1], values[i]) <= 0
   */
  @Override
  public void sort(T[] values) {
    //implemented recursively
    //if the array is just one or zero elements, return nothing
    if (values.length <= 1) {
      return; 
    } // if
    // calculate midpoint, divide arrays into left and right
    int mid = values.length / 2;
    T[] left = Arrays.copyOfRange(values, 0, mid);
    T[] right = Arrays.copyOfRange(values, mid, values.length);
    // call sort on each half
    sort(left);
    sort(right);
    // call merge method on original array, and left and right values
    merge(values, left, right);
  } // sort(T[])

  /**
   * Merge two sorted arrays into a single sorted array.
   * @param result final array.
   * @param left left half of the array.
   * @param right right half of the array.
   */
  private void merge(T[] result, T[] left, T[] right) {
    //three arbitrary variables used to iterate for the while loops below
    int i = 0;
    int j = 0;
    int k = 0;
    // takes element from each array, compares and inserts into final array
    while (i < left.length && j < right.length) {
      if (order.compare(left[i], right[j]) <= 0) {
        result[k++] = left[i++];
      } else {
        result[k++] = right[j++];
      } // if
    } // while
    //put any elements left over from left array into result array
    while (i < left.length) {
      result[k++] = left[i++];
    } // while
    // same as above but for the right array
    while (j < right.length) {
      result[k++] = right[j++];
    } // while
  } // merge
} // class MergeSorter
