package com.wky.demo.interview.meituan.third;

import java.util.List;

/**
 * @author wuming
 * @date 2023/8/3/08/03 16:15
 */
public interface ValueLoader<T> {

    List<T> load(long offset, int limit);

}
