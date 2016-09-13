package controller;

public class ClienteNoExisteException extends Exception {
	public ClienteNoExisteException() {
		super("El cliente no existe");
	}
}
