//给你一个字符串 s，找到 s 中最长的回文子串。
//
//
//
// 示例 1：
//
//
//输入：s = "babad"
//输出："bab"
//解释："aba" 同样是符合题意的答案。
//
//
// 示例 2：
//
//
//输入：s = "cbbd"
//输出："bb"
//
//
//
//
// 提示：
//
//
// 1 <= s.length <= 1000
// s 仅由数字和英文字母组成
//
// Related Topics 字符串 动态规划
// 👍 5584 👎 0

package longestPalindromicSubstring;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Solution {
    public String longestPalindrome(String s) {
        int sz = s.length();
        int rk = sz - 1;
        int left = 0, right = 0;
        String ans = "";
        for (int i = 0; i < sz; ) {
            if (left == right) {
                left = i;
                // 匹配左边第一个
                while (s.charAt(left) != s.charAt(rk) && i + 1 <= rk) {
                    rk--;
                }
                // 匹配右边第一个
                if (s.charAt(left) == s.charAt(rk)) {
                    right = rk;
                    if (i + 1 == rk || i == rk) {
                        ans = ans.length() > s.substring(left, right + 1).length() ? ans : s.substring(left, right + 1);
                        i++;
                    } else {
                        i++;
                        rk--;
                        continue;
                    }
                }
                // 未匹配则重置
                left++;
                i = right = left;
                rk = sz - 1;
            } else {
                // 比现有回文子串长则进行对比
                if (s.substring(left, right + 1).length() > ans.length()){
                    // i和rk之间存在两个数以上才需要比较
                    while (i + 2 <= rk) {
                        if (s.charAt(i) != s.charAt(rk)) {
                            break;
                        }
                        i++;
                        rk--;
                    }
                    if ((i + 1 == rk || i == rk) && s.charAt(i) == s.charAt(rk)) {
                        ans = ans.length() > s.substring(left, right + 1).length() ? ans : s.substring(left, right + 1);
                    }
                }
                // 未匹配则重置，如右边包含多个left，则继续匹配
                boolean multiLeft = s.substring(left + 1, right).indexOf(s.charAt(left)) != -1;
                rk =  multiLeft ? right - 1 : sz - 1;
                left = multiLeft ? left : left + 1;
                i = right = left;
            }
        }
        return ans;
    }
}

public class LongestPalindromicSubstring {
    public static String stringToString(String input) {
        //return JsonArray.readFrom("[" + input + "]").get(0).asString();
        return input;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            String s = stringToString(line);

            String ret = new Solution().longestPalindrome(s);

            String out = (ret);

            System.out.print(out);
        }
    }
}
