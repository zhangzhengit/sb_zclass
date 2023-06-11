package com.vo.core;

import cn.hutool.core.util.StrUtil;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Package声明
 *
 * @author zhangzhen
 * @date 2021-12-10 18:59:27
 *
 */
@Data
@NoArgsConstructor
public class ZPackage {

	private static final String PACKAGE = "package ";

	/**
	 * 	类的包声明，可以是 empty，或者必须符合规范
	 */
	private String packageString;

	public ZPackage(final String packageString) {
		this.setPackageString(packageString);
	}

	public void setPackageString(final String packageString) {
		if (StrUtil.isBlank(packageString)) {
			this.packageString = packageString;
			return;
		}


		final String ps = packageString.startsWith(PACKAGE) ? packageString : PACKAGE + packageString;
		this.packageString = ps;
	}


	@Override
	public String toString() {
		return this.getPackageString();
	}

}
