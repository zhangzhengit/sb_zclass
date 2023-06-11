package com.vo.core;

import lombok.Data;

/**
 * ZMethod的参数
 *
 * @author zhangzhen
 * @date 2023年6月11日
 *
 */
@Data
public class ZMethodArg {

	private String type;

	private String name;

	public ZMethodArg(final Class typeClass, final String name) {
		this.type = typeClass.getCanonicalName();
		this.name = name;
	}

	public ZMethodArg(final String type, final String name) {
		this.type = type;
		this.name = name;
	}

	@Override
	public String toString() {
		final String s = this.getType() + " " + this.getName();
		return s;
	}

}
