package br.com.repositorio.exceptions;

public class NoRecordFoundException extends Exception{

	private static final long serialVersionUID = 1L;

	public NoRecordFoundException(String msg) {
		super(msg);
	}
	public NoRecordFoundException() {
		super();
	}
}
