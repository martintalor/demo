package com.zhuyifan.util;

/**
 * @Author zhuyifan
 * @Time 2019年6月4日 上午10:06:04
 * @Version 1.0
 *          <p>
 *          Description:PropertiesTest.java:
 *          </p>
 */
public class PropertiesTest {

	public static void main(String[] args) {
		PropertiesUtils propertiesUtils = SpringUtil.getBean(PropertiesUtils.class);
		String packedDirTmp = propertiesUtils.getPropertiesValue("packed.dir");

	}

}
