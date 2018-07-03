package com.bo.springmvc.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description use for exclude token interceptor
 * @author Bo
 * @version 2018年7月3日　下午11:30:44
 * @码云 https://gitee.com/LeisureBo
 */
@Target({
	ElementType.TYPE,
	ElementType.METHOD
})
@Retention(RetentionPolicy.RUNTIME)
public @interface Anonymous {

}
