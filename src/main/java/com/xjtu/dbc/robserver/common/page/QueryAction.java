package com.xjtu.dbc.robserver.common.page;

import java.util.List;

/**
 * @FunctionalInterface 函数式接口注解
 * 该注解只能用于只有一个抽象方法的接口上
 * 该注解并非必须，加上该注解只是为了编译器更好的处理
 * 如果该注解加载了不符合条件的接口上，则会报错 
 */
@FunctionalInterface
public interface QueryAction<T> {
	public List<T> execute();
}
