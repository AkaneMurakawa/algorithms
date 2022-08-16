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

package medianOfTwoSortedArrays;

import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;

class Solution {
    /**
     * Solution: 数组是正序的，暴力破解法是合并数组，之后根据个数的奇偶数计算结果。
     * 不过并不需要全部遍历合并，我们可以进行伪合并，每次判断是否找到结果。
     * Optimization:
     * Debug: 输入
[1,3]
[2]

[1,2]
[3,4]

[0,0,0,0,0]
[-1,0,0,0,0,0,1]
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        // m + n >= 1
        if (m + n == 1) {
            return m == 1 ? nums1[0] : nums2[0];
        }
        // 中位下标
        int mid = (m + n) / 2;
        boolean isOdd = (m + n) % 2 == 1;
        int i = 0, mi = 0, ni = 0, cur;
        Double pre = null;
        while (mi < m && ni < n) {
            // m
            while (mi < m && ni < n && nums1[mi] <= nums2[ni]) {
                cur = nums1[mi];
                Pair<Boolean, Double> pair = findAns(i, mid, isOdd, pre, cur);
                if (pair.getKey()){
                    return pair.getValue();
                }else {
                    pre = pair.getValue();
                }
                i++;mi++;
            }
            // n
            while (mi < m && ni < n && nums1[mi] >= nums2[ni]) {
                cur = nums2[ni];
                Pair<Boolean, Double> pair = findAns(i, mid, isOdd, pre, cur);
                if (pair.getKey()){
                    return pair.getValue();
                }else {
                    pre = pair.getValue();
                }
                i++;ni++;
            }
        }
        // m has next
        while (mi < m) {
            cur = nums1[mi];
            Pair<Boolean, Double> pair = findAns(i, mid, isOdd, pre, cur);
            if (pair.getKey()){
                return pair.getValue();
            }else {
                pre = pair.getValue();
            }
            i++;mi++;
        }
        // n has next
        while (ni < n) {
            cur = nums2[ni];
            Pair<Boolean, Double> pair = findAns(i, mid, isOdd, pre, cur);
            if (pair.getKey()){
                return pair.getValue();
            }else {
                pre = pair.getValue();
            }
            i++;ni++;
        }
        return 0;
    }

    /**
     * 是否寻找到结果
     * @param i 遍历次数
     * @param mid  中位下标，(m+n)/2 示例:3 / 2 = 1; 4 / 2 = 2
     * @param isOdd 是否是奇数
     * @param pre 前一个 例如：4 / 2 = 2; 0123, 取i等于1和2
     * @param cur 当前
     * @return
     */
    public Pair<Boolean, Double> findAns(int i, int mid, boolean isOdd, Double pre, int cur){
        // 奇数
        if (i == mid && isOdd){
            return new Pair(Boolean.TRUE, new Double(cur));
        }
        // pre
        if (i + 1 == mid){
            return new Pair(Boolean.FALSE, new Double(cur));
        }
        // 偶数
        if (i == mid) {
            return new Pair(Boolean.TRUE, new Double((pre + cur) / 2F));
        }
        return new Pair(Boolean.FALSE, null);
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
