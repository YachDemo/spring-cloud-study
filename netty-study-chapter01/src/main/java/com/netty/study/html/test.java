package com.netty.study.html;

import java.util.Random;

/**
 * 
 * @author YanCh
 * @version v1.0
 * Create by 2020-04-23 10:23
 **/
public class test {

    /**
     * 生成64的盐
     *
     * @return
     */
    public static String createSalt() {
        int maxNum = 62;
        int i;
        int count = 0;
        char[] str = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',
                'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
                'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
                'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
                'x', 'y', 'z'};
        StringBuilder code = new StringBuilder();
        Random r = new Random();
        while (count < 10) {
            i = Math.abs(r.nextInt(maxNum));
            if (i >= 0 && i < str.length) {
                code.append(str[i]);
                count++;
            }
        }
        return code.toString();
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(createSalt());
        }
    }
}
