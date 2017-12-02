package com.jpmc.trading.reportengine.exception;

/**
 * The Class ApplicationException.
 *
 * @author Ananda Sarma Damaraju
 */
public class ApplicationException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 3286604544244420054L;

	/**
	 * Instantiates a new ApplicationException.
	 *
	 * @param message
	 *            the message
	 */
	public ApplicationException(final String message) {
		super(message);
	}

	/**
	 * Instantiates a new application exception.
	 *
	 * @param message
	 *            the message
	 * @param exception
	 *            the exception
	 */
	public ApplicationException(final String message, final Exception exception) {
		super(message, exception);
	}

	/**
	 * Instantiates a new application exception.
	 *
	 * @param message
	 *            the message
	 * @param cause
	 *            the cause
	 */
	public ApplicationException(final String message, final Throwable cause) {
		super(message, cause);
	}
}
