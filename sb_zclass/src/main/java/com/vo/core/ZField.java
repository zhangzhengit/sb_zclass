package com.vo.core;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ZClass的字段，使用toString 获取结果
 *
 * @author zhangzhen
 * @date 2023年6月16日
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ZField {

	private String type;

	private String name;

	private Object value;

	@Override
	public String toString() {
		final String s = this.getType() + " " + this.getName() +  " = " + this.getValue() + ";";
		return s;
	}

}
