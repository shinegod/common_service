package com.fx.shiro.exception;

import org.apache.shiro.authc.AccountException;

/**
 * Created by bei2love@gmail.com on 15/9/22.
 */
public class ExistUserLoginException extends AccountException {

    /**
     * Creates a new ExistUserLoginException.
     */
    public ExistUserLoginException() {
        super();
    }

    /**
     * Constructs a new ExistUserLoginException.
     *
     * @param message the reason for the exception
     */
    public ExistUserLoginException(String message) {
        super(message);
    }

    /**
     * Constructs a new ExistUserLoginException.
     *
     * @param cause the underlying Throwable that caused this exception to be thrown.
     */
    public ExistUserLoginException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a new ExistUserLoginException.
     *
     * @param message the reason for the exception
     * @param cause   the underlying Throwable that caused this exception to be thrown.
     */
    public ExistUserLoginException(String message, Throwable cause) {
        super(message, cause);
    }
}
