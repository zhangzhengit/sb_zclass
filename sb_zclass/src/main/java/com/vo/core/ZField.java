package com.vo.core;

import java.util.ArrayList;
import java.util.List;

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

	private List<String> annoList;


	public void addAnno(final String annoString) {
		this.initAnnoList();
		this.annoList.add(annoString);
	}

	public void addAnno(final Class annoClass) {
		this.annoList.add("@" + annoClass.getCanonicalName());
	}

	private void initAnnoList() {
		if(this.annoList == null) {
			this.annoList = new ArrayList<>();
		}
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		if (this.annoList != null) {
			for (final String as : this.annoList) {
				builder.append(as).append("\r\n");
			}
		}

		final StringBuilder append = builder.append(this.getType()).append( " ") .append(this.getName()).append(" = ").append(this.getValue()).append(";");

//		final String s = this.getType() + " " + this.getName() + " = " + this.getValue() + ";";
		return append.toString();
	}

	public ZField(final String type, final String name, final Object value) {
		this.type = type;
		this.name = name;
		this.value = value;
	}

}
