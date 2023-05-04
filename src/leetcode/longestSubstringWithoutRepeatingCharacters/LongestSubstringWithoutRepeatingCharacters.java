//给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
//
//
//
// 示例 1:
//
//
//输入: s = "abcabcbb"
//输出: 3
//解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
//
//
// 示例 2:
//
//
//输入: s = "bbbbb"
//输出: 1
//解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
//
//
// 示例 3:
//
//
//输入: s = "pwwkew"
//输出: 3
//解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
//     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
//
//
//
//
// 提示：
//
//
// 0 <= s.length <= 5 * 104
// s 由英文字母、数字、符号和空格组成
//
// Related Topics 哈希表 字符串 滑动窗口
// 👍 7985 👎 0

package leetcode.longestSubstringWithoutRepeatingCharacters;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

class Solution {
    /**
     * Solution: 最长不重复子串，暴力破解法，遍历字符串，如有重复则从上一个串的第二个字符重新开始继续遍历
     * Optimization: 滑动窗口，设置左右两个指针，交叉移动，直到右指针不再重复或遍历结束。
     * 其实和暴力破解法是类似的，遇到重复则从左字符串下一个开始继续遍历，通过HashSet以空间换时间，并且提前结束减少不必要的遍历
     * <p>
     * Debug: 输入dvdf
     */
    public int lengthOfLongestSubstring(String s) {
        // 不重复子串集合
        HashSet<Character> occ = new HashSet<>();
        int sz = s.length();
        // rk: 右指针，表示最长字符串结束位置下标
        int rk = 0, ans = 0;
        // i: 左指针，表示左边开始下标
        for (int i = 0; i < sz; i++) {
            // 右指针移动
            while (rk < sz && !occ.contains(s.charAt(rk))) {
                occ.add(s.charAt(rk));
                rk++;
            }
            ans = Math.max(ans, rk - i);

            // i: 左指针向右移动一格
            occ.remove(s.charAt(i));

            if (rk == sz - 1) {
                break;
            }
        }
        return ans;
    }
}

public class LongestSubstringWithoutRepeatingCharacters {
    public static String stringToString(String input) {
        //return JsonArray.readFrom("[" + input + "]").get(0).asString();
        return input;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            String s = stringToString(line);

            int ret = new Solution().lengthOfLongestSubstring(s);

            String out = String.valueOf(ret);

            System.out.print(out);
        }
    }
}
