package com.vo.core;

import java.io.IOException;
import java.util.Date;

import org.codehaus.groovy.control.CompilationFailedException;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.UUID;
import groovy.lang.GroovyClassLoader;

/**
 *
 *
 * @author zhangzhen
 * @date 2023年6月11日
 *
 */
public class ZCU {

	public static final String ZCLASS = "ZClass";

	/**
	 * 从字符串形式的java代码来构造一个对象
	 *
	 * @param source
	 * @return
	 */
	public static Object newInstance(final String source) {
		try (GroovyClassLoader groovyClassLoader = new GroovyClassLoader()) {
			final Class<?> clazz = groovyClassLoader.parseClass(source);
			return clazz.newInstance();
		} catch (CompilationFailedException | IOException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}

		return null;
	}

}
