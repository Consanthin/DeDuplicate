package com.ntt.array;

import java.util.Arrays;

import org.apache.log4j.Logger;

/**
 * The Class DeDupUtility used to remove the duplicate of given array based on different capacity type using removeDuplicate method.
 * Based on capacity, different implementation of DeDup will be used to remove the duplicates from given array.
 * 
 * @author Consanthin Jegan
 */
public class DeDupUtility {
	
	/** The Constant logger to log flow of this Utility Class. */
	final static Logger logger = Logger.getLogger(DeDupUtility.class);
	
	/** The Constant deDup as static property of this Utility Class. */
	final static DeDup deDup = new DeDup();
	
	/**
	 * Removes the duplicate - should be called with input array and array size(Capacity) Enum. 
	 * Based on capacity type, different implementation of DeDup will be used to remove the duplicates from
	 * the given array of integers.\n
	 *
	 * @param inputArray the input array
	 * @param arrayCapacityType the array capacity type
	 * @return the int[]
	 */
	public static int[] removeDuplicate(int[] inputArray, ArrayCapacityType arrayCapacityType) {
		logger.debug("Entering DeDupUtility::removeDuplicate() ::  CapacityType - " + arrayCapacityType.name());
		int[] resultArray;
		try {
			resultArray = new int[inputArray.length];
			display("Before Removal  of Duplicate:: ", inputArray);

			switch(arrayCapacityType) {
			case SMALL:
				resultArray = deDup.removeDuplicatesUsingWithLegacyAPI(inputArray);
				break;
			case MEDIUM:
				resultArray = deDup.removeDuplicatesWithOrinigalOrder(inputArray);
				break;
			case LARGE:
				resultArray = deDup.removeDuplicatesUsingWithLatestAPI(inputArray);
				break;
			default:
				// for Default case, the preferred implementation has been used by utility.
				resultArray = deDup.removeDuplicatesWithOrinigalOrder(inputArray);
			}

			display("After Removal of Duplicate:: ", inputArray);
		} catch(Exception exp) {
			logger.error("Exception in Removing Duplicates :: DeDup utility :: " , exp);
			return null;
		}
		logger.debug("Exiting DeDupUtility::removeDuplicate()");
		
		return resultArray;
	}
	
	
	/**
	 * Display the Content of int array - this Method will be used to print before an after array of the DeDup utility.\n
	 *
	 * @param logGenMsg the log general msg - to specify the before/after message with corresponding interface.\n
	 * @param randomIntegers the random integers \n
	 */
	public static void display(String logGenMsg, int[] randomIntegers) {
		logger.debug("Entering Method - DeDup::display()");
		if(null == randomIntegers) {
			logger.debug("Size of the array :: 0");
		} else {
			logger.debug("Size of the array :: " + randomIntegers.length);
			logger.info(logGenMsg + Arrays.toString(randomIntegers) + "\n");
		}

		logger.debug("Exiting Method - DeDup::display()");
	}
}
