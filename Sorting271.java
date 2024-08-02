import java.lang.Math;
import java.util.Arrays;

public class Sorting271 {
    private static int[] sort(int[] array) {
        
        int[] placeholder = new int[array.length];

        int roundsNeeded = Math.log(array.length) / Math.log(2) + 1; // Calculates log2(n) using loga(b) formula and then adds one additional array to account for 2^0 to find the number of times to conduct merge
        int roundsCompleted = 0;

        int mergesNeededThisRound = array.length;
        int mergesCompleted = 0;

        int subArraySize = 1;




        while (roundsCompleted < roundsNeeded) {
            
            while (mergesCompleted < mergesNeededThisRound) {

            }
        }
        
        
    }

    private static int[] merge(int[] leftArr, int[] rightArr) {

        int[] temp = new int[leftArr.length + rightArr.length];
        int tempIndex = 0;


        int leftStart = 0;
        int leftEnd = leftArr.length - 1;
        int rightStart = 0;
        int rightEnd = rightArr.length - 1;

        boolean remainingRight = false;
        boolean remainingLeft = false;

        while (leftStart <= leftEnd && rightStart <= rightEnd) {
            if (leftArr[leftStart] == rightArr[rightStart]) {
                temp[tempIndex++] = leftArr[leftStart++];
                temp[tempIndex++] = rightArr[rightStart++];
            } else if (leftArr[leftStart] < rightArr[rightStart]) {
                temp[tempIndex++] = leftArr[leftStart++];
            } else {
                temp[tempIndex++] = rightArr[rightStart++];
            }

            if (leftStart > leftEnd && rightStart <= rightEnd) {
                remainingRight = true;
            } else if (leftStart <= leftEnd && rightStart > rightEnd) {
                remainingLeft = true;
            }


        }

        if (remainingRight) {
            while (rightStart <= rightEnd) {
                temp[tempIndex++] = rightArr[rightStart++];
            }
        } else if (remainingLeft) {
            while (leftStart <= leftEnd) {
                temp[tempIndex++] = leftArr[leftStart++];
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

        System.out.println("Unsorted Array:"); 
        System.out.println(Arrays.toString(unsorted)); // Prints randomly generated array
        System.out.println();
        System.out.println("Sorted Array:"); 
        System.out.println(sort(unsorted)); // Prints resultant array after completing the sorting operations
    }
}