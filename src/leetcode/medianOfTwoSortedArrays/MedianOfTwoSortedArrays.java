//给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
//
// 算法的时间复杂度应该为 O(log (m+n)) 。
//
//
//
// 示例 1：
//
//
//输入：nums1 = [1,3], nums2 = [2]
//输出：2.00000
//解释：合并数组 = [1,2,3] ，中位数 2
//
//
// 示例 2：
//
//
//输入：nums1 = [1,2], nums2 = [3,4]
//输出：2.50000
//解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
//
//
//
//
//
//
// 提示：
//
//
// nums1.length == m
// nums2.length == n
// 0 <= m <= 1000
// 0 <= n <= 1000
// 1 <= m + n <= 2000
// -106 <= nums1[i], nums2[i] <= 106
//
// Related Topics 数组 二分查找 分治
// 👍 5747 👎 0

package leetcode.medianOfTwoSortedArrays;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;

class Solution {
    /**
     * Solution: 数组是正序的，暴力破解法是合并数组，之后根据个数的奇偶数计算结果。
     * 不过并不需要全部遍历合并，我们可以进行伪合并，每次判断是否找到结果。
     * Optimization: 使用二分查找，如总共有7个数，则我们需要寻找到第4个数，7/2 = 3，3 + 1 = 4；
     * 此时，先进行二分查找，先排除一半不符合的，设k：4/2 = 2，则可以去除数组A、B中其中不符合的k个；
     * 此时，剩余k = 4 - 2 = 2，继续重复上一步；
     * 直到找到k=1，即剩下一个要找到的数。
     * 1. 当A排除所有不符合的时候，直接找B
     * 2. 当B排除所有不符合的时候，直接找A
     * <p>
     * Debug: 输入
     * [1,3]
     * [2]
     * <p>
     * [1,2]
     * [3,4]
     * <p>
     * [0,0,0,0,0]
     * [-1,0,0,0,0,0,1]
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int total = nums1.length + nums2.length;
        if (total % 2 == 1) {
            int midIndex = total / 2;
            return getKthElement(nums1, nums2, midIndex + 1);
        } else {
            int midIndex1 = total / 2 - 1, midIndex2 = total / 2;
            return (getKthElement(nums1, nums2, midIndex1 + 1) + getKthElement(nums1, nums2, midIndex2 + 1)) / 2F;
        }
    }

    /**
     * 二分查找
     *
     * @param k 第几个数(-1即中位下标)，(m+n)/2
     *          例如：4 / 2 = 2; 0123, k等于第2和3个, 取下标等于1和2
     *          例如：3 / 2 = 1; 012, k等于第2个, 取下标等于1
     * @return
     */
    public double getKthElement(int[] nums1, int[] nums2, int k) {
        /* 主要思路：要找到第 k (k>1) 小的元素，那么就取 pivot1 = nums1[k/2-1] 和 pivot2 = nums2[k/2-1] 进行比较
         * 这里的 "/" 表示整除
         * nums1 中小于等于 pivot1 的元素有 nums1[0 .. k/2-2] 共计 k/2-1 个
         * nums2 中小于等于 pivot2 的元素有 nums2[0 .. k/2-2] 共计 k/2-1 个
         * 取 pivot = min(pivot1, pivot2)，两个数组中小于等于 pivot 的元素共计不会超过 (k/2-1) + (k/2-1) <= k-2 个
         * 这样 pivot 本身最大也只能是第 k-1 小的元素
         * 如果 pivot = pivot1，那么 nums1[0 .. k/2-1] 都不可能是第 k 小的元素。把这些元素全部 "删除"，剩下的作为新的 nums1 数组
         * 如果 pivot = pivot2，那么 nums2[0 .. k/2-1] 都不可能是第 k 小的元素。把这些元素全部 "删除"，剩下的作为新的 nums2 数组
         * 由于我们 "删除" 了一些元素（这些元素都比第 k 小的元素要小），因此需要修改 k 的值，减去删除的数的个数
         */
        int m = nums1.length, n = nums2.length;
        int mi = 0, ni = 0;

        while (true) {
            // 边界情况
            if (mi == m) {
                return nums2[ni + k - 1];
            }
            if (ni == n) {
                return nums1[mi + k - 1];
            }
            if (k == 1) {
                return Math.min(nums1[mi], nums2[ni]);
            }

            // 正常情况
            int half = k / 2;
            int newMi = Math.min(mi + half, m) - 1;
            int newNi = Math.min(ni + half, n) - 1;
            int pivot1 = nums1[newMi], pivot2 = nums2[newNi];
            if (pivot1 <= pivot2) {
                k -= (newMi - mi + 1);
                mi = newMi + 1;
            } else {
                k -= (newNi - ni + 1);
                ni = newNi + 1;
            }
        }
    }
}

public class MedianOfTwoSortedArrays {
    public static int[] stringToIntegerArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return new int[0];
        }

        String[] parts = input.split(",");
        int[] output = new int[parts.length];
        for (int index = 0; index < parts.length; index++) {
            String part = parts[index].trim();
            output[index] = Integer.parseInt(part);
        }
        return output;
    }

    public static String doubleToString(double input) {
        return new DecimalFormat("0.00000").format(input);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int[] nums1 = stringToIntegerArray(line);
            line = in.readLine();
            int[] nums2 = stringToIntegerArray(line);

            double ret = new Solution().findMedianSortedArrays(nums1, nums2);

            String out = doubleToString(ret);

            System.out.print(out);
        }
    }
}
