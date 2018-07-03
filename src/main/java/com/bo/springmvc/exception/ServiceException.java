package com.bo.springmvc.exception;

/**
 * @Description 
 * @author Bo
 * @version 2018年7月3日　下午10:11:06
 * @码云 https://gitee.com/LeisureBo
 */
public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = -8398258804535127969L;

	/**返回码*/
    private String errorCode;
    
    /**信息*/
    private String errorMsg;

    /**
     * 构造函数
     */
    public ServiceException() {
        super();
    }

    /**
     * 构造函数
     * @param errorCode
     */
    public ServiceException(String errorCode) {
        super(errorCode);
    }

    /**
     * 构造函数
     * @param cause
     */
    public ServiceException(Throwable cause) {
        super(cause);
    }

    /**
     * 构造函数
     * @param errorCode
     * @param cause
     */
    public ServiceException(String errorCode, Throwable cause) {
        super(cause);
        this.errorCode = errorCode;
    }

    /**
     * 构造函数
     * @param errorCode
     * @param message
     */
    public ServiceException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
        this.errorMsg = message;
    }

    /**
     * 构造函数
     * @param errorCode
     * @param message
     * @param cause
     */
    public ServiceException(String errorCode, String message, Throwable cause) {
        super(cause);
        this.errorCode = errorCode;
        this.errorMsg = message;
    }

    /**
     * Getter method for property <tt>errorCode</tt>.
     *
     * @return property value of errorCode
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * Setter method for property <tt>errorCode</tt>.
     *
     * @param errorCode value to be assigned to property errorCode
     */
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * Getter method for property <tt>errorMessage</tt>.
     *
     * @return property value of errorMessage
     */
    public String getErrorMsg() {
        return errorMsg;
    }

    /**
     * Setter method for property <tt>errorMessage</tt>.
     *
     * @param errorMessage value to be assigned to property errorMessage
     */
    public void setErrorMsg(String errorMessage) {
        this.errorMsg = errorMessage;
    }

    /**
     * @see Object#toString()
     */
    @Override
    public String toString() {
        return "ServiceException [errorCode=" + errorCode + ", errorMsg=" + errorMsg + "]";
    }
}
