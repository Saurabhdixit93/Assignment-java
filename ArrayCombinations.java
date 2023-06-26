Question - 
Given an array of integers and a target value, you must determine which two integers' sum

equals the target and return a 2D array. Then merge the array into a single array with sorting (

ascending ) order, in the next step double the target value and find again the combination of

digits (can be multiple digits ) that are equal to the double targeted value and returned into a 2D

array.

Sample Input : [1, 3, 2, 2, -4, -6, -2, 8];

Target Value = 4,

Output: First Combination For “4” : [ [1,3],[2,2],[-4,8],[-6,2] ];

Merge Into a single Array : [-6,-4,1,2,2,2,3,8];

Second Combination For “8” : [ [ 1,3,2,2], [8,-4,2,2],....,[n,n,n,n] ]


----------------------------------------------------------------------------------------------------------


Answer -



import java.util.*;

public class ArrayCombinations {
    public static void main(String[] args) {
        int[] nums = {1, 3, 2, 2, -4, -6, -2, 8};
        int target = 4;

        List<int[]> combinations = findCombinations(nums, target);
        System.out.println("First Combination for \"" + target + "\":");
        printCombinations(combinations);

        int[] mergedArray = mergeArrays(combinations);
        System.out.println("Merge into a Single Array:");
        printArray(mergedArray);

        int doubleTarget = target * 2;
        List<int[]> combinationsDouble = findCombinations(mergedArray, doubleTarget);
        System.out.println("Second Combination for \"" + doubleTarget + "\":");
        printCombinations(combinationsDouble);
    }

    public static List<int[]> findCombinations(int[] nums, int target) {
        List<int[]> combinations = new ArrayList<>();
        Map<Integer, Integer> numCount = new HashMap<>();

        for (int num : nums) {
            int complement = target - num;
            if (numCount.containsKey(complement) && numCount.get(complement) > 0) {
                int[] combination = {num, complement};
                combinations.add(combination);
                numCount.put(complement, numCount.get(complement) - 1);
            } else {
                numCount.put(num, numCount.getOrDefault(num, 0) + 1);
            }
        }

        return combinations;
    }

    public static int[] mergeArrays(List<int[]> combinations) {
        List<Integer> mergedList = new ArrayList<>();
        for (int[] combination : combinations) {
            for (int num : combination) {
                mergedList.add(num);
            }
        }

        Collections.sort(mergedList);
        int[] mergedArray = new int[mergedList.size()];
        for (int i = 0; i < mergedArray.length; i++) {
            mergedArray[i] = mergedList.get(i);
        }

        return mergedArray;
    }

    public static void printCombinations(List<int[]> combinations) {
        for (int[] combination : combinations) {
            System.out.print("[");
            for (int i = 0; i < combination.length; i++) {
                System.out.print(combination[i]);
                if (i != combination.length - 1) {
                    System.out.print(",");
                }
            }
            System.out.println("]");
        }
    }

    public static void printArray(int[] array) {
        System.out.print("[");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
            if (i != array.length - 1) {
                System.out.print(",");
            }
        }
        System.out.println("]");
    }
}
