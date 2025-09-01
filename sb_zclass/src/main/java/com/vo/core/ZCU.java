package com.vo.core;

import java.io.IOException;

import org.codehaus.groovy.control.CompilationFailedException;

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
	 * @param package1 TODO
	 * @param className TODO
	 * @return
	 */
	public static Object newInstance(final String source, final String package1, final String className) {
//		// 2 janino
		// FIXME 2025年9月2日 上午12:48:40 zhangzhen: 继续研究用janino，要改不少生成的东西
//		System.out.println("package1 = " + package1);
//		System.out.println("className = " + className);
//		System.out.println("source = " + source);
//		final SimpleCompiler compiler = new SimpleCompiler();
//		try {
//			compiler.cook(source);
//			
//			final String cN = package1.substring(package1.indexOf("package ") + "package ".length()) + "." + className;
//			final Class<?> cls = compiler.getClassLoader().loadClass(cN);
//			final Object newInstance = cls.newInstance();
//			
//			System.out.println("newInstance = " + newInstance);
//			return newInstance;
//		} catch (final CompileException | ClassNotFoundException | InstantiationException | IllegalAccessException e1) {
//			e1.printStackTrace();
//		}
//		
//		return null;
		
		
		// 1 groovy
		try (GroovyClassLoader groovyClassLoader = new GroovyClassLoader()) {
			final Class<?> clazz = groovyClassLoader.parseClass(source);
			return clazz.newInstance();
		} catch (CompilationFailedException | IOException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}

		return null;
	}

}
