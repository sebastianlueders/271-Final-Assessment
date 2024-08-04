import java.lang.Math;
import java.util.Arrays;

public class Sorting271 {


    /*
     * Sorts the elements in array using the merge function in nlog(n) time complexity
     * 
     * @return int[] containing the sorted version of the array passed to it
     */
    private static int[] sort(int[] array) {

        int mergesNeededThisRound = array.length / 2; // Initializes the number of merges needed per round
        int subArraySize = 1; // Initializes the subarray size of left and right arrays to 1

        while (subArraySize < array.length) {
            
            int indexBookmark = 0; // Tracks the index number for insertion into the left/right array from the main array
            int arrayIndexTracker = 0; // Tracks the index number to place each element of merged subarrays back into the main array
            int mergesCompleted = 0; // Keeps track of how many merges have occurred each round

            while (mergesCompleted < mergesNeededThisRound) {

                int indexIncrementer = 0; // Tracks the number of elements added to left/right array to ensure that the appropriate number is added on each round
                

                int[] left = new int[subArraySize]; // Creates an array to store the elements in the left subarray for each merge

                int[] right = new int[subArraySize]; // Creates an array to store the elements in the right subarray for each merge

                while (indexIncrementer < subArraySize) {
                    left[indexIncrementer++] = array[indexBookmark++]; // Inserts elements into the left array until the amount specified by this round's subarray size is met
                }
                
                indexIncrementer = 0; // resets value to zero for the same operation to the right array

                while (indexIncrementer < subArraySize) {
                    right[indexIncrementer++] = array[indexBookmark++]; // Inserts elements into the right array until the amount specified by this round's subarray size is met
                }

                indexIncrementer = 0; // resets value to zero for use in copying the returned array from merge() into the primary array

                int[] mergeReturn = new int[subArraySize * 2]; // Initializes an array to store the sorted array returned from merge()
                mergeReturn = merge(left, right); // Assigns the returned values from merge() to the mergeReturn array

                while (indexIncrementer < subArraySize * 2) {
                    array[arrayIndexTracker++] = mergeReturn[indexIncrementer++]; // Reassigns the values from mergeReturn to the main array
                }
                
                
                mergesCompleted++; // increments mergesCompleted to ensure the while loop runs the correct number of times


            }

            subArraySize *= 2; // Increases the subarray size by double each time a round is completed
            mergesNeededThisRound /= 2; // Adjusts the number of merges needed for the upcoming round to account for the increased subarray size

        }

        return array;
        
    }

    /*
     * Merges two arrays into a single sorted array
     * 
     * @return int[] containing the sorted values of the two arrays passed to it
     */
    private static int[] merge(int[] leftArr, int[] rightArr) {

        int[] temp = new int[leftArr.length + rightArr.length]; // Creates a temporary array to hold the sorted values of both arrays
        int tempIndex = 0; // Declares and initializes a tempIndex variable to track the index for next value placement


        int leftStart = 0; // Tracks the index to start with on the leftArr
        int leftEnd = leftArr.length - 1; // Records last index of the leftArr
        int rightStart = 0; // Tracks the index to start with on the rightArr
        int rightEnd = rightArr.length - 1; // Records last index of the rightArr

        boolean remainingRight = false; // Creates a variable to determine if there are more values to add from the rightArr
        boolean remainingLeft = false; // Creates a variable to determine if there are more values to add from the leftArr

        while (leftStart <= leftEnd && rightStart <= rightEnd) { // Continues the operation while the leftArr & rightArr values have not all been used
            if (leftArr[leftStart] == rightArr[rightStart]) { // Checks if the current value being checked in the leftArr and rightArr are equal
                temp[tempIndex++] = leftArr[leftStart++]; // Adds the leftArr value to the temp array and increments both the tempIndex & the leftStart value
                temp[tempIndex++] = rightArr[rightStart++]; // Adds the rightArr value to the temp array and increments both the tempIndex & the rightStart value
            } else if (leftArr[leftStart] < rightArr[rightStart]) { 
                temp[tempIndex++] = leftArr[leftStart++]; // only adds the leftArr value and necesarry increments
            } else {
                temp[tempIndex++] = rightArr[rightStart++]; // only adds the rightArr value and necesarry increments
            }

            /*
             * Sets the boolean value to the correct value to indicate that there are either elements within the right or left array that have not been
             * added yet
             */
            if (leftStart > leftEnd && rightStart <= rightEnd) {
                remainingRight = true; 
            } else if (leftStart <= leftEnd && rightStart > rightEnd) {
                remainingLeft = true;
            }


        }

        if (remainingRight) {
            while (rightStart <= rightEnd) {
                temp[tempIndex++] = rightArr[rightStart++]; // Adds the remaining elements in the right array (if there are any remaining)
            }
        } else if (remainingLeft) {
            while (leftStart <= leftEnd) {
                temp[tempIndex++] = leftArr[leftStart++]; // Adds the remaining elements in the left array (if there are any remaining)
            }
        }

        return temp; 
    }


    public static void main(String[] args) {
        int exp = (int) (Math.random() * 8);  // Determines a random exponent value to calc the size of the array
        int arraySize = (int) Math.pow(2, exp); // Determines the array size using the random exp value
        int[] unsorted = new int[arraySize]; // Initializes the randomly generated array

        int index = 0; // Starting index for random value element assignment
        while (unsorted[arraySize - 1] == 0) {
            unsorted[index++] = (int) ((Math.random() * 100) + 1); // Assigns a random value between 1-100 for each element until the last element is assigned
        }

        System.out.println("Unsorted Array (Size: " + unsorted.length + "):"); 
        System.out.println(Arrays.toString(unsorted)); // Prints randomly generated array
        System.out.println();
        System.out.println("Sorted Array:"); 
        System.out.println(Arrays.toString(sort(unsorted))); // Prints resultant array after completing the sorting operations
    }
}