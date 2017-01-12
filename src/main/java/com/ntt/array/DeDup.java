package com.ntt.array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;

import org.apache.log4j.Logger;

/**
 * Populate a integer Array Without Duplicate element. This utility  program is used remove the duplicate elements 
 * from given input of integer array.
 * This utility is implemented in three different approach using JAVA API.
 * Please go through the Method level Java Doc for details on each implementation.
 * 
 * @author Consanthin Jegan
 *
 */
public class DeDup {
	
	/** The Constant logger to log the result array and Debug the functional Flow of the DeDup Utility. */
	final private Logger logger = Logger.getLogger(DeDup.class);

	/**
	 * Instantiates a new de dup.
	 */
	protected DeDup(){
		logger.debug("DeDup is initialized");
	}
   
	/**
	 * Removes the duplicates using Collection API, classes and Java 8 features, 
	 * 			Keep the Order as given in the Request. \n
	 * Use Case : When the customer order captured from customer portal 
	 * 			  where order duplicates,if any, needs to be removed and the original order should be maintained.
	 * improvement : If removal of collection of object is expected, the collection can be sorted and then
	 * 				  remove the duplicates using linkedHashSet as this will increase the performance.\n
	 * Pro's -  Uses the Collection API (LinkedHashSet will retain the order while removes the duplicates) 
	 * 			and implementations which are provided by Java.\n
	 * 			LinkedHashSet will maintain the order of the element in the Array.\n		
	 * 			Standard and proven data structure which is provided by Java to solve the issue.\n		
	 * 			Less number of lines.\n
	 * Con's -  LinkedHashSet: 
	 * 				1. As HashSet implementation is used to retain the order of the array, usage of Hashing will
	 * 					be costly process in terms of Performance \n
	 * 				2. Conversion for Primitive to Wrapper array and vice versa will become costly process as the size of the array grows.\n	
	 * 			
	 *
	 * @param inputArray the input array
	 * @return the int[]
	 * @throws Exception the exception
	 */
	public int[] removeDuplicatesWithOrinigalOrder(int[] inputArray) throws Exception {
		logger.debug("Entering Method DeDup::removeDuplicatesWithOrinigalOrder()");	
		
		int[] result;
		try {
			if(inputArray.length <= 0) {
				logger.error("Input Array is Empty in Method removeDuplicatesWithOrinigalOrder() " + inputArray);
				return null;
			}

			// Convert the primitives array to wrapper Object object array.
			HashSet<Integer> linkedHashSet = new LinkedHashSet<Integer>();
			for(int i = 0; i < inputArray.length; i++) {
				linkedHashSet.add(inputArray[i]);
			}

			// Convert back the object array to primitives.
			result = new int[linkedHashSet.size()];
			int j = 0;
			for(Integer local : linkedHashSet) {
				result[j++] = local;
			}
		} catch(Exception exp) {
			logger.error("Exception in DeDup::removeDuplicatesWithOrinigalOrder :: ", exp);
			throw exp;
		}
		logger.debug("Exiting Method DeDup::removeDuplicatesWithOrinigalOrder()");
		return result;
	}
	
	/**
	 * Removes the duplicates using Java 8 features.\n
	 * Use Case : When the customer order captured from customer portal \n
	 * 			  where order duplicates,if any, needs to be removed and order is not important in result
	 * 			  array improvement : Functional Programming with Java 8 API(Stream, Lamda) will not add best performance 
	 * 			  like other collection API.\n
	 * 			  There are more discussion in technical forums to validate the key values which has been 
	 * 			  added to JAVA by these latest features added in JAVA 8. \n
	 * 
	 * Pro's -  Uses the latest API with functional programming which are supported by Java.\n
	 * 			As size of the array grows, the performance will be good than other two implementations
	 * 			Less number of lines, Maintainable, readable. \n
	 * 			As size of the array grows, This implementation will give better performance.\n
	 * Con's -  For primitives with small size, performance of Stream and Lamda expression is not good
	 * 			as compared to simple For loop implementations(removeDuplicatesUsingWithLegacyAPI).\n
	 *
	 * @param inputArray the input array
	 * @return the int[]
	 * @throws Exception the exception
	 */
	public int[] removeDuplicatesUsingWithLatestAPI(int[] inputArray) throws Exception {
		logger.debug("Entering Method DeDup::removeDuplicatesUsingWithLatestAPI()");
		int[] result;
		try {
			if(inputArray.length <= 0) {
				logger.error("Input Array is Empty in Method removeDuplicatesUsingWithLatestAPI() " + inputArray);
				return null;
			}
			result = Arrays.stream(inputArray).distinct().toArray();
		} catch(Exception exp) {
			logger.error("Exception in DeDup::removeDuplicatesUsingWithLatestAPI :: ", exp);
			throw exp;
		}
		logger.debug("Exiting Method DeDup::removeDuplicatesUsingWithLatestAPI()");
		return result;
	}
	
	/**
	 * Removes the duplicates using Java 8 features.\n \n
	 * Use Case : When the customer order captured from customer portal \n
	 * 			  where order duplicates,if any, needs to be removed based on specific id (primitive) 
	 * 			  and order is not important in result array, this implementation lives better than other to implementations.\n
	 * improvement : With Highly matured JAVA API, using the plain simple for-loop and conditional flow may come with few cost like,
	 * 				  Maintainability, Readability and Performance in collection of Objects (as in Enterprise level application 
	 * 				  would mostly be handled objects to represent the Real time Objects in each domain). \n
	 * Pro's -  Faster approach in terms of primitive array.\n	 	
	 * 			Performance is better than other two implementation.As Size grows very large, this performance will be go down.\n 				
	 * Con's -  For Primitive array, As Size grows very large, this performance will be go down.
	 * 			For Collection of Object, performance is not best as compared to the implementation using collection API.\n
	 * 			
	 *
	 * @param inputArray the input array
	 * @return the int[]
	 * @throws Exception the exception
	 */
	public int[] removeDuplicatesUsingWithLegacyAPI(int[] inputArray) throws Exception {
		logger.debug("Entering Method DeDup::removeDuplicatesUsingWithLegacyAPI()");
		int[] result;
		try {
			if(inputArray.length <= 0) {
				logger.error("Input Array is Empty in Method removeDuplicatesUsingWithLegacyAPI()" + inputArray.length);
				return null;
			}
			int noOfUniqueElements = inputArray.length;

			// Traverse the array linearly and check for duplicate,
			// If duplicate, over write the value
			// else, continue to traverse.
			for(int i = 0; i < noOfUniqueElements; i++) {
				for(int j = i + 1; j < noOfUniqueElements; j++) {
					if(inputArray[i] == inputArray[j]) {
						inputArray[j] = inputArray[noOfUniqueElements - 1];
						noOfUniqueElements--;
						j--;
					}
				}
			}
			result = Arrays.copyOf(inputArray, noOfUniqueElements);
		} catch(Exception exp) {
			logger.error("Exception in DeDup::removeDuplicatesUsingWithLegacyAPI :: ", exp);
			throw exp;
		}
		logger.debug("Exiting Method DeDup::removeDuplicatesUsingWithLegacyAPI()");		
		return result;
	}
}
