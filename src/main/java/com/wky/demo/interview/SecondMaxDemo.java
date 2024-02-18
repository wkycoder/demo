package com.wky.demo.interview;

/**
 * @author wuming
 * @date 2023/8/10/08/10 09:38
 */
public class SecondMaxDemo {


    public static void main(String[] args) {

        int[] arr = new int[] {1, 2, 5, 8, 0};

        int secondMax = getSecondMax(arr);
        System.out.println(secondMax);

    }

    private static int getSecondMax(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new RuntimeException();
        }
        int max = arr[0];
        int sec = Integer.MIN_VALUE;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                sec = max;
                max = arr[i];
            } else if (arr[i] > sec) {
                sec = arr[i];
            }
        }
        return sec;
    }


}
