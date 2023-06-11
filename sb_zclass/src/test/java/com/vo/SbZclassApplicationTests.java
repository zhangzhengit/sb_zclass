package com.vo;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

import org.checkerframework.checker.units.qual.m;
import org.junit.jupiter.api.Test;
import org.omg.CORBA.INTERNAL;
import org.springframework.boot.test.context.SpringBootTest;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.vo.core.ZClass;
import com.vo.core.ZMethod;
import com.vo.core.ZMethod.ZMethodBuilder;

import cn.hutool.crypto.digest.HMac;

import com.vo.core.ZMethodAccessEnum;
import com.vo.core.ZMethodArg;
import com.vo.core.ZPackage;

/**
 *
 *
 * @author zhangzhen
 * @date 2023年6月11日
 *
 */
@SpringBootTest
class SbZclassApplicationTests {


	@Test
	public void testMethodArg1() {
		System.out.println(java.time.LocalDateTime.now() + "\t" + Thread.currentThread().getName() + "\t"
				+ "SbZclassApplicationTests.testMethodArg1()");

		final String body = "System.out.println(\"name = \" + name  + \"\\t\" + \"age = \" + age);";

		final ZMethod mhello = ZMethod.builder()
				.isFinal(true)
				.isStatic(true)
				.isSynchronized(true)
				.name("hello")
				.body(body)
				.build();

		final ZMethodArg name = new ZMethodArg(String.class, "name");
		final ZMethodArg age = new ZMethodArg(Integer.class, "age");

		final ZMethod m = mhello.methodArg(name).methodArg(age);
		System.out.println(m);

		final ZClass c = ZClass.empty();
		c.setMethodSet(Sets.newHashSet(mhello));

		final Object object = c.newInstance();

		System.out.println("object = " + object.getClass());

		ZClass.invoke(object, "hello", new Class[] { String.class, Integer.class }, "zhangsan", Integer.valueOf(20));

	}

	@Test
	public void testM1() {
		System.out.println(java.time.LocalDateTime.now() + "\t" + Thread.currentThread().getName() + "\t"
				+ "SbZclassApplicationTests.testM1()");

		final String body = "System.out.println(\"name = \" + name  + \"\\t\" + \"age = \" + age);";
		final ZMethod mhello = ZMethod.builder()
			.isFinal(true)
			.isStatic(true)
			.isSynchronized(true)
			.name("hello")
			.body(body)
			.build();

		final ZMethodArg name = new ZMethodArg(String.class, "name");
		final ZMethodArg age = new ZMethodArg(Integer.class, "age");
		mhello.methodArg(name).methodArg(age);

		final ZClass c = ZClass.empty();
		c.setMethodSet(Sets.newHashSet(mhello));

		final Object o = c.newInstance();
		System.out.println("-----------");
//		System.out.println(o);
		System.out.println(c);

		ZClass.invoke(o, "hello", new Class[] { String.class, Integer.class }, "zhangsan", 20);

	}

	@Test
	public void testempty1() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		System.out.println(java.time.LocalDateTime.now() + "\t" + Thread.currentThread().getName() + "\t"
				+ "SbZclassApplicationTests.testempty1()");


		final ZMethod me = ZMethod.buildReturn(Integer.class,"age");
//		final ZMethod me = ZMethod.buildReturn(String.class.getCanonicalName(),"age");
//		System.out.println(me);

		final ZClass empty = ZClass.empty();
		empty.setMethodSet(Sets.newHashSet(me));
		System.out.println(empty);

		final Object o = empty.newInstance();

		final Method age = ZClass.getMethod(o, "age", null);
		System.out.println(age);
		final Object invoke = age.invoke(o, null);
		System.out.println("invoke = " + invoke);

		final Object r = ZClass.invoke(o, "age");
		System.out.println("r = " + r);
	}

	@Test
	public void test1() {
		System.out.println(java.time.LocalDateTime.now() + "\t" + Thread.currentThread().getName() + "\t"
				+ "SbZclassApplicationTests.test1()");

		final ZClass class1 = new ZClass();
		class1.setName("A");
		final ZPackage package1 = new ZPackage("com.vo.test");
		class1.setPackage1(package1);

//		int n = 5;
//		for (int i = 1; i <= n; i++) {
//			System.out.println("hello, i = " + i);
//		}

		final String body =
				  "		int n = 5;\n"
				+ "		for (int i = 1; i <= n; i++) {\n"
				+ "			System.out.println(\"hello, i = \" + i);\n"
				+ "		}";

		final ZMethod method = new ZMethod();
		method.setName("hello");
		method.setReturnType("String");
		method.setBody(body);


		class1.setMethodSet(Sets.newHashSet(method));

		System.out.println("---------");
		final String s = class1.toString();
		System.out.println(s);

		System.out.println("new");
		try {

			final Object o = class1.newInstance();
			System.out.println("o.class.name= " + o.getClass().getCanonicalName());

			System.out.println("------------method.invoke结果------------");
			final Method hello = o.getClass().getMethod("hello");
			hello.setAccessible(true);
			final Object invoke = hello.invoke(o);

		} catch (final Exception e) {
			e.printStackTrace();
		}

	}


//	@Test
	void contextLoads() {
		System.out.println(java.time.LocalDateTime.now() + "\t" + Thread.currentThread().getName() + "\t"
				+ "SbZclassApplicationTests.contextLoads()");
	}

}
