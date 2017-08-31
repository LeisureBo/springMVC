package com.bo.springmvc.junit;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * @Description Junit参数化测试
 * @author 王博
 * @version 2017年8月24日　下午4:02:35
 */
@RunWith(Parameterized.class) //1.更改默认的测试运行器为RunWith(Parameterized.class)  
public class JunitParameterizeTest {
	// 2.声明预期值变量、测试数据变量
	int expected, input1, inpuit2;
	
	//3.声明提供批量测试数据的公共静态方法。返回值类型为Collection，并使用@Parameters进行修饰 
	@Parameters
	public static Collection<Object[]> data(){
		return Arrays.asList(new Object[][]{
				{3,1,2},
				{4,2,2},
				{10,20,-9}
		});
	}

	//4.为测试类声明一个带有参数的公共构造函数，并在其中为之声明变量赋值 
	public JunitParameterizeTest(int expected, int input1, int inpuit2) {
		this.expected = expected;
		this.input1 = input1;
		this.inpuit2 = inpuit2;
	}
	
	//5.运行测试方法，即可完成对多组数据的测试
	@Test
	public void addTest(){
		Assert.assertEquals("加法运算结果与预期结果不符", expected, new Calculate().add(input1, inpuit2));
	}
	
}
