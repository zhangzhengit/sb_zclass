package com.vo.core;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

import com.google.common.collect.Lists;

import groovy.lang.GroovyClassLoader;

/**
 * 
 * 	测试
 * @author zhangzhen
 * @date 2021-12-10 18:59:19
 * 
 */
public class ZCTest1 {
	/*
	 * 
	 * public static void main(final String[] args) throws InstantiationException,
	 * IllegalAccessException, NoSuchMethodException, SecurityException,
	 * IllegalArgumentException, InvocationTargetException { // test_1(); //
	 * test_name(); // test_groovy1(); }
	 * 
	 * public static void test_groovy1() throws InstantiationException,
	 * IllegalAccessException, NoSuchMethodException, SecurityException,
	 * IllegalArgumentException, InvocationTargetException { System.out.println(
	 * "ZCTest1.test_groovy1()" + "\t" + LocalDateTime.now() + "\t" +
	 * Thread.currentThread().getName()); //groovy提供了一种将字符串文本代码直接转换成Java Class对象的功能
	 * final GroovyClassLoader groovyClassLoader = new GroovyClassLoader();
	 * //里面的文本是Java代码,但是我们可以看到这是一个字符串我们可以直接生成对应的Class<?>对象,而不需要我们写一个.java文件 final
	 * Class<?> clazz = groovyClassLoader.parseClass("package com.vo.netty;\r\n" +
	 * "\r\n" + "public class ZCTestClass1{\r\n" + "\r\n" +
	 * " public String test1 () { \r\n" + "\r\n" + " //测试，这是注释 \r\n" + "\r\n" +
	 * " return \"zxA\"; \r\n" + "\r\n" + " } \r\n" + "\r\n" + " }"); final Object
	 * obj = clazz.newInstance(); final Method method =
	 * clazz.getDeclaredMethod("test1"); final Object invoke = method.invoke(obj);
	 * System.out.println(invoke);
	 * 
	 * }
	 * 
	 * public static void test_name() { System.out .println("ZCTest1.test_name()" +
	 * "\t" + LocalDateTime.now() + "\t" + Thread.currentThread().getName());
	 * 
	 * final JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler(); final
	 * String path = "E://__TEMP//ZCTestClass1.java"; final int run =
	 * javaCompiler.run(null, null, null, path); System.out.println(run);
	 * 
	 * }
	 * 
	 * public static void test_1() { System.out.println("ZCTest1.test_1()" + "\t" +
	 * LocalDateTime.now() + "\t" + Thread.currentThread().getName());
	 * 
	 * final ZPackage package1 = new ZPackage();
	 * package1.setPackageString("package com.vo.netty");
	 * 
	 * final ZClass class1 = new ZClass(); class1.setPackage1(package1);
	 * class1.setAccessRights(ZMethodAccessEnum.PUBLIC);
	 * class1.setName("ZCTestClass1");
	 * 
	 * final ZMethod method = new ZMethod();
	 * method.setAccessRights(ZMethodAccessEnum.PUBLIC);
	 * method.setReturnType("String"); method.setName("test1");
	 * method.setArgList(Lists.newArrayList("String name","int age"));
	 * method.setBody("//测试，这是注释");
	 * 
	 * 
	 * final Set<ZMethod> mset = new HashSet<>(); mset.add(method);
	 * class1.setMethodSet(mset);
	 * 
	 * final String string = class1.toString(); System.out.println(string);
	 * 
	 * }
	 */}
