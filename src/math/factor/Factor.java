// 一个整数可以被分解为多个整数的乘积，
// 例如，6可以分解为2x3。请使用递归编程的方法，为给定的整数n，找到所有可能的分解（1在解中最多只能出现1次）。
// 例如，输入8，输出是可以是1x8, 8x1, 2x4, 4x2, 1x2x2x2, 1x2x4, ……

package math.factor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

class Solution {

    public void factor(Long num, ArrayList<Long> res) {
        if (num == 1) {
            if (!res.contains(1L)) res.add(1L);
            System.out.println(res);
            return;
        }
        for (long i = 1; i <= num; i++) {
            // 子集跳过1
            if (i == 1 && res.contains(1L)) continue;
            if (num % i == 0) {
                ArrayList<Long> newList = (ArrayList<Long>) res.clone();
                newList.add(i);
                factor(num / i, newList);
            }
        }
    }
}

public class Factor {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            Long num = Long.valueOf(line);
            new Solution().factor(num, new ArrayList<>());
        }
    }
}
