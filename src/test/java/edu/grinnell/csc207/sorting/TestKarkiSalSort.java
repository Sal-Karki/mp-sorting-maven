package edu.grinnell.csc207.sorting;

import org.junit.jupiter.api.BeforeAll;

/**
 * Tests of our InsertionSorter.
 */
public class TestKarkiSalSort extends TestSorter {
  /**
   * Set up the sorters.
   */
  @BeforeAll
  static void setup() {
    stringSorter = new KarkiSalSort<String>((x,y) -> x.compareTo(y));
    intSorter = new KarkiSalSort<Integer>((x,y) -> x.compareTo(y));
  } // setup()

} // class TestInsertionSorter
