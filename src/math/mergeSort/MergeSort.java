package math.mergeSort;

import java.util.Arrays;

class Solution {

    public int[] mergeSort(int[] arr) {
        if (null == arr) return new int[0];
        // 分解到只剩一个数
        if (arr.length == 1) return arr;

        // 分解
        int mid = arr.length / 2;
        int[] left = Arrays.copyOfRange(arr, 0, mid);
        int[] right = Arrays.copyOfRange(arr, mid, arr.length);
        left = mergeSort(left);
        right = mergeSort(right);

        // 合并
        return merge(left, right);
    }

    private int[] merge(int[] left, int[] right) {
        if (null == left || null == right) return new int[0];
        int li = 0;
        int ri = 0;
        int mi = 0;
        int[] res = new int[left.length + right.length];
        while (li < left.length && ri < right.length) {
            if (left[li] < right[ri]) {
                res[mi] = left[li];
                li++;
            } else {
                res[mi] = right[ri];
                ri++;
            }
            mi++;
        }

        if (li < left.length) {
            for (int i = li; i < left.length; i++) {
                res[mi] = left[i];
                mi++;
            }
        } else if (ri < right.length) {
            for (int i = ri; i < right.length; i++) {
                res[mi] = right[i];
                mi++;
            }
        }
        return res;
    }
}

public class MergeSort {

    public static void main(String[] args) {
        int[] arr = new int[]{1, 3, 2, 6, 4, 10, 7};
        int[] mergeSortArr = new Solution().mergeSort(arr);
        for (int i : mergeSortArr) {
            System.out.print(i + ", ");
        }
    }
}
