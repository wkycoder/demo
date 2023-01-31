package com.wky.demo.tree;

import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 多叉树遍历
 * @author wangkunyang
 * @date 2023/01/31 16:57
 */
public class Demo {

    public static void main(String[] args) {
        // 第一层
        Node root = new Node(1, null);
        List<Node> children = new ArrayList<>();
        Node node2 = new Node(21, null);
        node2.setChildren(Collections.singletonList(new Node(31, null)));
        children.add(node2);
        children.add(new Node(22, null));
        root.setChildren(children);
        recurse(root, 1, 3);
    }

    private static void recurse(Node root, Integer current, Integer target) {
        System.out.println(current);
        System.out.println(root.getData());
        List<Node> children = root.getChildren();
        if (CollectionUtils.isNotEmpty(children) && current < target) {
            current++;
            for (Node child : children) {
                recurse(child, current, target);
            }
        }
    }

}
