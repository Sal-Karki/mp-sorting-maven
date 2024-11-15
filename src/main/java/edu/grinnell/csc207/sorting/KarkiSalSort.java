package edu.grinnell.csc207.sorting;

import java.util.Comparator;

/**
 * Combination of quicksort and insertion sort. Quicksort for arrays larger than size 10,
 * insertion sort for arrays equal to or smaller than size 10
 * @param <T>
 *   The types of values that are sorted.
 * @author Samuel A. Rebelsky
 * @author Sal Karki
 */
public class KarkiSalSort<T> implements Sorter<T> {
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The comparator that defines the order of elements.
   */
  Comparator<? super T> order;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

   /**
   * Create a sorter using a particular comparator.
   * @param comparator
   *   The order in which elements in the array should be ordered
   *   after sorting.
   */
  public KarkiSalSort(Comparator<? super T> comparator) {
    this.order = comparator;
  } // KarkiSalSort

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Sorts an array in place using a combination of Quicksort and Insertion Sort.
   *
   * @param values
   *   The array to sort.
   */
  @Override
  public void sort(T[] values) {
    quicksort(values, 0, values.length - 1);
  } // sort(T[])

  /**
   * Recursively sorts a subarray using the Quicksort algorithm for large subarrays,
   * and switches to Insertion Sort for smaller subarrays.
   *
   * @param values
   *   The array to sort.
   * @param left
   *   The left boundary of the subarray.
   * @param right
   *   The right boundary of the subarray.
   */
  private void quicksort(T[] values, int left, int right) {
    if (right - left <= 10) {
      // Switch to insertion sort for small arrays
      insertionSort(values, left, right);
    } else if (left < right) {
      // Partition and recursively sort if subarray is large
      int pivotIndex = partition(values, left, right);
      quicksort(values, left, pivotIndex - 1);
      quicksort(values, pivotIndex + 1, right);
    }
  } // quicksort

  /**
   * Partitions a subarray for Quicksort around a pivot.
   *
   * @param values
   *   The array to partition.
   * @param left The left boundary of the subarray.
   * @param right The right boundary of the subarray.
   * @return The index of the pivot after partitioning.
   */
  private int partition(T[] values, int left, int right) {
    T pivot = values[right];
    int i = left - 1;

    for (int j = left; j < right; j++) {
      if (order.compare(values[j], pivot) <= 0) {
        swap(values, ++i, j);
      }
    }
    swap(values, i + 1, right);
    return i + 1;
  } // partition

  /**
   * Sorts a subarray using Insertion Sort.
   *
   * @param values
   *   The array to sort.
   * @param left
   *   The left boundary of the subarray.
   * @param right
   *   The right boundary of the subarray.
   */
  private void insertionSort(T[] values, int left, int right) {
    for (int i = left + 1; i <= right; i++) {
      T key = values[i];
      int j = i - 1;

      while (j >= left && order.compare(values[j], key) > 0) {
        values[j + 1] = values[j];
        j--;
      }
      values[j + 1] = key;
    }
  } // insertionSort

  /**
   * Swaps two elements in an array.
   *
   * @param values
   *   The array containing elements to swap.
   * @param i
   *   The index of the first element.
   * @param j
   *   The index of the second element.
   */
  private void swap(T[] values, int i, int j) {
    T temp = values[i];
    values[i] = values[j];
    values[j] = temp;
  } // swap
} // class LastFirstSort
