package com.ntt.test;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import com.ntt.array.DeDupUtility;
import com.ntt.array.ArrayCapacityType;

/**
 * The Class DeDupTest to perform Unit Test of the DeDup implementation and run performance test for different 
 * input size.
 * 
 * @author Consanthin Jegan
 */
public class DeDupTest {
	
	/** The Constant logger. */
	final static Logger logger = Logger.getLogger(DeDupTest.class);
	
	/**
	 * Test remove duplicates using with latest API.
	 */
	@Test
	public void testRemoveDuplicatesLargeArray() {
		int[] inputArray = { 1, 2, 34, 34, 25, 1, 45, 3, 26, 85, 4, 34, 86, 25, 43, 2, 1, 10000, 11, 16,
				19, 1, 18, 4, 9, 3, 20, 17, 8, 15, 6, 2, 5, 10, 14, 12, 13, 7, 8, 9, 1, 2, 15, 12, 18, 10, 14, 20, 17, 16, 3,
				6, 19, 13, 5, 11, 4, 7, 19, 16, 5, 9, 12, 3, 20, 7, 15, 17, 10, 6, 1, 8, 18, 4, 14, 13, 2, 11 };
		Assert.assertTrue(checkDuplicate(DeDupUtility.removeDuplicate(inputArray, ArrayCapacityType.LARGE)));
	}
	
	/**
	 * Test remove duplicates with original order.
	 */
	@Test
	public void testRemoveDuplicatesMediumArray() {
		int[] inputArray = { 1, 2, 34, 34, 25, 1, 45, 3, 26, 85, 15, 17, 10, 6, 1, 8, 18, 4, 14, 13, 2, 11 };
		Assert.assertTrue(checkDuplicate(DeDupUtility.removeDuplicate(inputArray, ArrayCapacityType.MEDIUM)));

	}
	
	/**
	 * Test remove duplicates using with legacy API.
	 */
	@Test
	public void testRemoveDuplicatesSmallArray() {
		int[] inputArray = { 1, 2, 34, 34, 25, 1, 45, 3, 2, 17, 26, 85, 15, 17 };
		Assert.assertTrue(checkDuplicate(DeDupUtility.removeDuplicate(inputArray, ArrayCapacityType.SMALL)));

	}
	
	/**
	 * Test remove duplicates exception.
	 */
	@Test
	public void testRemoveDuplicatesEmptyArray() {
		int[] inputArray = {};
		Assert.assertFalse(checkDuplicate(DeDupUtility.removeDuplicate(inputArray, ArrayCapacityType.SMALL)));
	}
	
	/**
	 * Test remove duplicates exception.
	 */
	@Test
	public void testRemoveDuplicatesExcpetion() {
		int[] inputArray = null;
		Assert.assertFalse(checkDuplicate(DeDupUtility.removeDuplicate(inputArray, ArrayCapacityType.SMALL)));
	}
	
	/**
	 * Test performance of each implementation for each input size of the array.
	 */
	@Test
	public void testPerformance() {
		runPerformanceTest(populatePerfArray(100));
	}
	
	/**
	 * Check duplicate to assert the returned Array by DeDup Utility.
	 *
	 * @param resultArr the result arr
	 * @return true, if successful
	 */
	private boolean checkDuplicate(int[] resultArr) {
		if (resultArr == null || resultArr.length <= 0) {
			return false;
		}
		List<Integer> current = new ArrayList<Integer>();
		for(int i=0; i < resultArr.length;i++) {
			if(current.contains(Integer.valueOf(resultArr[i]))) {
				return false;
			}
			current.add(Integer.valueOf(resultArr[i]));
		}
		return true;
	}
	
	/**
	 * Populate perf array to run performance Test.
	 *
	 * @param size the size
	 * @return the int[]
	 */
	private int[] populatePerfArray(int size) {
		int[] perfArray = new int[size];
		int min = 12;
		for(int i = 0; i < size ; i++){
			perfArray[i] =  min + (int)(Math.random() * size);
		}
		return perfArray;
	}
	
	/**
	 * Run performance test.
	 *
	 * @param array the array
	 * @return the long[]
	 */
	private long[] runPerformanceTest(int[] array) {
		long startTime, runTime;
		long[] runTimeArray = new long[3];
		startTime = System.currentTimeMillis();
		DeDupUtility.removeDuplicate(array, ArrayCapacityType.LARGE);
		runTime = System.currentTimeMillis() - startTime;
		logger.info("Running Time in MilliSeconds:: removeDuplicate with Large Array ::" + runTime);
		runTimeArray[0] = runTime;
		
		startTime = System.currentTimeMillis();
		DeDupUtility.removeDuplicate(array, ArrayCapacityType.MEDIUM);
		runTime = System.currentTimeMillis() - startTime;
		logger.info("Running Time in MilliSeconds:: removeDuplicate with Large Medium ::" + runTime);
		runTimeArray[1] = runTime;
		
		startTime = System.currentTimeMillis();
		DeDupUtility.removeDuplicate(array, ArrayCapacityType.SMALL);
		runTime = System.currentTimeMillis() - startTime;
		logger.info("Running Time in MilliSeconds:: removeDuplicate with Large Small ::" + runTime);
		runTimeArray[2] = runTime;
		return runTimeArray;
	}
}
