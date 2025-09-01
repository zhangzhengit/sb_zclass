package com.vo.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * ZClass的字段，使用toString 获取结果
 *
 * @author zhangzhen
 * @date 2023年6月16日
 *
 */
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

		final StringBuilder append = builder.append(this.getType()).append( " ") 
				.append(this.getName()).append(" = ")
				.append("(").append(this.getType()).append(")")
				.append(this.getValue()).append(";");

//		final String s = this.getType() + " " + this.getName() + " = " + this.getValue() + ";";
		return append.toString();
	}

	public ZField(final String type, final String name, final Object value) {
		this.type = type;
		this.name = name;
		this.value = value;
	}

	public String getType() {
		return type;
	}

	public void setType(final String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(final Object value) {
		this.value = value;
	}

	public List<String> getAnnoList() {
		return annoList;
	}

	public void setAnnoList(final List<String> annoList) {
		this.annoList = annoList;
	}

	public ZField(final String type, final String name, final Object value, final List<String> annoList) {
		super();
		this.type = type;
		this.name = name;
		this.value = value;
		this.annoList = annoList;
	}

	public ZField() {
		super();
	}

	@Override
	public int hashCode() {
		return Objects.hash(annoList, name, type, value);
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final ZField other = (ZField) obj;
		return Objects.equals(annoList, other.annoList) && Objects.equals(name, other.name)
				&& Objects.equals(type, other.type) && Objects.equals(value, other.value);
	}

}
