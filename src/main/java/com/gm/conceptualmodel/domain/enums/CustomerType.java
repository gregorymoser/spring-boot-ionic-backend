package com.gm.conceptualmodel.domain.enums;

public enum CustomerType {

	LEGALPERSON(1, "Legal Person"),
	NATURALPERSON(2, "Natural Person");
	
	private int code;
	private String description;
	
	private CustomerType(int code, String description) {
		this.code = code;
		this.description = description;
	}

	public int getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}
	
	public static CustomerType toEnum (Integer code) {
		if (code == null) {
			return null;
		}
		
		for (CustomerType x : CustomerType.values()) {
			if(code.equals(x.getCode())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Invalid id: " + code);
	}
}