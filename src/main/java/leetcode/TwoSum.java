package leetcode;

import java.util.Arrays;

public class TwoSum {

    public int[] twoSum2(int[] nums, int target) {

        for (int i = 0; i < nums.length - 1; i++) {
            for(int j = i + 1; j < nums.length; j++){
                if(nums[i] + nums[j] == target){
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{};
    }

    public int[] twoSum(int[] nums, int target) {
        int[] sortedNums = Arrays.copyOf(nums, nums.length);
        Arrays.sort(sortedNums);
        int[] values = getValues(0, nums.length-1, sortedNums, target);

        int leftIndex = -1;
        int rightIndex = -1;

        for (int i = 0; i < nums.length; i++) {
            if (leftIndex >= 0 && rightIndex >= 0) {
                break;
            }
            if (leftIndex < 0 && nums[i] == values[0]) {
                leftIndex = i;
                continue;
            }
            if (rightIndex < 0 && nums[i] == values[1]) {
                rightIndex = i;
            }
        }
        return new int[]{leftIndex, rightIndex};
    }

    private int[] getValues(int leftIndex, int rightIndex, int[] nums, int target) {
        int sum = nums[leftIndex] + nums[rightIndex];
        if (sum == target) {
            return new int[]{nums[leftIndex], nums[rightIndex]};
        }
        if (sum > target) {
            return getValues(leftIndex, rightIndex - 1, nums, target);
        }
        if (sum < target) {
            return getValues(leftIndex + 1, rightIndex, nums, target);
        }
        throw new IllegalStateException("");
    }
}
