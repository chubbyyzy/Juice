package com.tribeofspirit.utils;

/**
 * Author : gonwang
 * Create time : 2015/12/4.
 */
public class MathUtil {

    public static int max(int first, int... others) {
        int ret = first;
        for (int i : others) {
            ret = Math.max(ret, i);
        }
        return ret;
    }
}
