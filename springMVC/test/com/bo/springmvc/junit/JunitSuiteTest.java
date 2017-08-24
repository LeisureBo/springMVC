package com.bo.springmvc.junit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @Description Junit测试套件
 * @author 王博
 * @version 2017年8月24日　下午3:40:57
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({JunitFlowTest.class,CalculateTest.class})
public class JunitSuiteTest {
	/* 
     * 1.测试套件就是组织各个测试类一起运行的 (一个测试套件中包含多个测试类，每次测试系统功能时，只要执行一次测试套件就可以了。)
     *  
     * 写一个作为测试套件的入口类，这个类里不包含其他的方法 
     * 更改测试运行器Suite.class 
     * 将要测试的类作为数组传入到Suite.SuiteClasses（{}） 
     */
}
