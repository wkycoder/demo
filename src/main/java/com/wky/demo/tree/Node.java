package com.wky.demo.tree;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author wangkunyang
 * @date 2023/01/31 16:57
 */
@Data
@AllArgsConstructor
public class Node {

    private Integer data;

    private List<Node> children;

}
