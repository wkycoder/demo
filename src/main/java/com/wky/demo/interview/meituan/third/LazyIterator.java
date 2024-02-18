package com.wky.demo.interview.meituan.third;

import org.apache.commons.collections4.CollectionUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

/**
 * 第三题
 * 将下面这个未完成的Java工具类补充完成，实现懒加载的功能，该类需要实现Iterable接口，能够遍历所有数据。具体要求如下：
 * 工具类提供了一个ValueLoader接口，用于获取数据，其中ValueLoader的接口定义为：
 * public interface ValueLoader<T> { List<T> load(long offset, int limit); }。使用该工具类的地方，需要提供ValueLoader的实现类。
 * 工具类需要支持懒加载，即遍历时按需获取数据，而非一次性获取所有数据到内存之后，再进行遍历；
 * 工具类需要实现Iterable接口，支持使用foreach语句遍历所有数据；
 * 当ValueLoader.load()方法返回null时，认为已经遍历完成；
 * @author wuming
 * @date 2023/8/3/08/03 16:13
 */
public class LazyIterator<T> implements Iterable<T> {

    private final ValueLoader<T> valueLoader;
    private final int limit;

    public LazyIterator(ValueLoader<T> valueLoader, int limit) {
        this.valueLoader = valueLoader;
        this.limit = limit;
    }

    public LazyIterator(ValueLoader<T> valueLoader) {
        this(valueLoader, 50);
    }

    @Override
    public Iterator<T> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<T> {
        int cursor = 0;
        long offset = 0;
        List<T> list = new ArrayList<>();

        @Override
        public boolean hasNext() {
            if (cursor == list.size()) {
                // 加载数据到内存中
                list = valueLoader.load(offset, limit);
                offset += limit;
                cursor = 0;
            }
            return CollectionUtils.isNotEmpty(list);
        }

        @Override
        public T next() {
            return list.get(cursor++);
        }

        @Override
        public void remove() {
            Iterator.super.remove();
        }

        @Override
        public void forEachRemaining(Consumer<? super T> action) {
            Iterator.super.forEachRemaining(action);
        }

    }



}
