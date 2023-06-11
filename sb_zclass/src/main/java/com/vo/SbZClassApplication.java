package com.vo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 运行时动态构造字符串形式的java源码，并且动态编译出java对象
 *
 * @author zhangzhen
 * @date 2023年6月11日
 *
 */
@SpringBootApplication
public class SbZClassApplication {

	public static void main(final String[] args) {
		SpringApplication.run(SbZClassApplication.class, args);
	}

}
