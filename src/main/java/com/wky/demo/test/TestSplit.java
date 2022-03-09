package com.wky.demo.test;

/**
 * @author: wangkunyang
 * @date 2021/12/02 10:37
 */
public class TestSplit {


    public static void main(String[] args) {
//        String oldPrefix = "锐锢商城/企拍部";
        String oldPrefix = "xxx";
        String newPrefix = "锐锢商城/企拍部22";
        String oldDesc = "锐锢商城/企拍部/华南战区/广东大区/梅州";
        String[] split = oldDesc.split(oldPrefix);
        System.out.println(split.length);
        System.out.println(newPrefix + split[split.length - 1]);
    }

}
