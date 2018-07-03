
package com.bo.springmvc.exception;

import org.apache.log4j.Logger;

import com.bo.springmvc.constants.ResponseCodeEnum;

public class ExceptionUtils {

    private static Logger logger = Logger.getLogger(ExceptionUtils.class);

    /**
     * 将下层抛出的异常转换为resp返回码
     *
     * @param e Exception
     * @return
     */
    public static Exception handlerException4biz(Exception e) {
        Exception ex = null;
        if (!(e instanceof Exception)) {
            return null;
        }
        if (e instanceof ValidateException) {
            ex = new ServiceException(((ValidateException) e).getErrorCode(), ((ValidateException) e).getErrorMsg());
        }else if (e instanceof Exception) {
            ex = new ServiceException(ResponseCodeEnum.SYSTEM_BUSY.getCode(),
                    ResponseCodeEnum.SYSTEM_BUSY.getMsg());
        }
        logger.error("ExceptionUtil.handlerException4biz, Exception=" + e.getMessage(), e);
        return ex;
    }
}
