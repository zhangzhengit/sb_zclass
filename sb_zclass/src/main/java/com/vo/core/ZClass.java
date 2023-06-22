package com.vo.core;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;
import java.util.Set;
import java.util.StringJoiner;

import com.google.common.collect.HashBiMap;
import com.google.common.collect.Sets;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 表示java class对象
 *
 * @author zhangzhen
 * @date 2021-12-10 18:50:33
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ZClass {

	private static final String DEAULT_ZCLASS_NAME_PREFIX = "ZClass_";

	private final static HashBiMap<ZClass, Object> SOURCE_MAP = HashBiMap.create();

	private static final String DEFAULT_PACKAGE = "com.vo";

	private static final String ABSTRACT = " abstract ";

	private static final String IMPLEMENTS = " implements ";

	private static final String CLASS = "class ";

	private static final String IMPORT = "import ";

	public static final String NEW_LINE = "\n\r";

	private ZPackage package1;

	// FIXME 这两个先不写了，用body算了
//	private boolean generateAllArgsConstructor;
//	private Set<String> fieldSet;

	// FIXME Abstract的不能实例化
//	private boolean isAbstract;

	private Set<String> importSet;

	private Set<String> annotationSet;

	private ZMethodAccessEnum accessRights;

	private String name;

	private Set<String> implementsSet;

	private Set<ZField> fieldSet;

	private String superClass;
	private String body;

	private Set<ZMethod> methodSet;

	public void addField(final ZField zField) {
		if (this.getFieldSet() == null) {
			this.setFieldSet(Sets.newHashSet());
		}

		this.getFieldSet().add(zField);
	}

	@Override
	public String toString() {

		final StringBuilder builder = new StringBuilder();
		final ZPackage p = this.getPackage1();
		if (Objects.nonNull(p)) {
			builder.append(p.toString());
			builder.append(';');
		}

		builder.append(ZClass.NEW_LINE);

		if (CollUtil.isNotEmpty(this.importSet)) {
			for (final String im : this.importSet) {
				builder.append(ZClass.IMPORT).append(im).append(';').append(ZClass.NEW_LINE);
			}
			builder.append(ZClass.NEW_LINE);
			builder.append(ZClass.NEW_LINE);
		}

		final Set<String> aSet = this.getAnnotationSet();
		if (CollUtil.isNotEmpty(aSet)) {
			for (final String annotation : aSet) {
				builder.append("@").append(annotation).append(ZClass.NEW_LINE);
			}
		}

		final ZMethodAccessEnum accessRights2 = this.getAccessRights();
		builder.append(Objects.isNull(accessRights2) ? ZMethodAccessEnum.PUBLIC.name().toLowerCase()
				: accessRights2.toString().toLowerCase());
		builder.append(" ");
		builder.append(ZClass.CLASS);
		final String name2 = this.getName();

		builder.append(StrUtil.isEmpty(name2) ? ZClass.generateDefaultClassName() : name2);

		final String sc = this.getSuperClass();
		if(StrUtil.isNotEmpty(sc)) {
			builder.append(" extends ").append(sc);
		}

		if (CollUtil.isNotEmpty(this.implementsSet)) {
			final StringJoiner joiner = new StringJoiner(",");
			builder.append(ZClass.IMPLEMENTS);
			for (final String impl : this.implementsSet) {
				joiner.add(impl);
			}
			builder.append(joiner);
		}

		builder.append('{').append(ZClass.NEW_LINE);

		// 字段
		final Set<ZField> fs = this.getFieldSet();
		if (CollUtil.isNotEmpty(fs)) {
			for (final ZField zf : fs) {
//				final String fS = zf.getType().getCanonicalName() + " " + zf.getName() + ";";
				builder.append(zf.toString()).append(NEW_LINE);
			}
		}

		builder.append(this.getBody()).append(ZClass.NEW_LINE);

		final Set<ZMethod> zMethodSet = this.getMethodSet();
		if (CollUtil.isNotEmpty(zMethodSet)) {
			for (final ZMethod zm : zMethodSet) {
				if (zm.isAbstract() ) {
					throw new IllegalArgumentException("非abstract类不允许有abstract方法");
				}
				builder.append(zm.toString());
			}
		}

		builder.append('}');

		return builder.toString();
	}

	/**
	 * 声明默认的类名
	 *
	 * @return
	 *
	 * @author zhangzhen
	 * @date 2022年1月11日
	 */
	public static String generateDefaultClassName() {
		final String id = UUID.randomUUID().toString().replace("-", "");
		return DEAULT_ZCLASS_NAME_PREFIX + id;
	}

	public String getBody() {
		if (StrUtil.isEmpty(this.body)) {
			return "";
		}

		return this.body;
	}

	public Object newInstance() {
		final String source = this.toString();
		try {
			final Object newInstance = ZCU.newInstance(source);
			ZClass.SOURCE_MAP.put(this, newInstance);
			return newInstance;
		} catch (SecurityException | IllegalArgumentException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static ZClass getZClassByObject(final Object object) {

		final ZClass zClass = SOURCE_MAP.inverse().get(object);
		return zClass;
	}


	public static ZClass empty() {
		return empty(ZClass.generateDefaultClassName());
	}

	public static ZClass empty(final String name) {
		final ZClass class1 = new ZClass();
		class1.setName(name);
		final ZPackage package1 = new ZPackage(DEFAULT_PACKAGE);
		class1.setPackage1(package1);

		return class1;
	}

	public static Method[] getDeclaredMethods(final Object object) {
		final Class<? extends Object> cls = object.getClass();
		final Method[] declaredMethods = cls.getDeclaredMethods();

		return declaredMethods;
	}

	public static Method[] getMethods(final Object object) {
		final Class<? extends Object> cls = object.getClass();
		final Method[] declaredMethods = cls.getMethods();
		return declaredMethods;
	}

	public static Method getMethod(final Object object,final String name, final Class<?>... parameterTypes) {
		final Class<? extends Object> cls = object.getClass();
		Method m = null;
		try {
			m = cls.getMethod(name, parameterTypes);
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		return m;
	}

	public static Method getDeclaredMethod(final Object object,final String name, final Class<?>... parameterTypes) {
		final Class<? extends Object> cls = object.getClass();
		Method m = null;
		try {
			m = cls.getDeclaredMethod(name, parameterTypes);
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		return m;
	}


	public static Object invoke(final Object object, final String name) {
		final Method method = getMethod(object, name, null);
		try {
			final Object r = method.invoke(object, null);
			return r;
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Object invoke(final Object object, final String name,final Class<?>[]  parameterTypes,final Object... args) {
		final Method method = getMethod(object, name, parameterTypes);
		try {
			final Object r = method.invoke(object, args);
			return r;
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getSimpleName() {
		final String name2 = this.getName();
		final int i = name2.indexOf("<");
		if(i <= -1) {
			return name2;
		}
		final int i2 = name2.lastIndexOf(">");
		if(i2 > i) {
			final String n2 = name2.substring(0,i);
			return n2;
		}


		return name2;
	}

// FIXME 2023年6月15日 下午3:13:23 zhanghen: 测试此方法
	public void writeToFile(final File file) {
		try {
			final String s = this.toString();
			final BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(s);
			writer.flush();
			writer.close();
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}
}
