package calculadora.enums;

public enum Operacion {
	SUMA("+"),
	RESTA("-"),
	MULTIPLCACION("*"),
	DIVISION("/");
	
	private final String simbolo;

	Operacion(String simbolo) {
		this.simbolo = simbolo;
	}
	
	public String getSimbolo() {
		return this.simbolo;
	}

}
