package com.ergproapontamento.ergpro.enuns;

public enum UserRole {

	ADMIN("ADMIN"),
    LIVRE_ACESSO("LIVRE_ACESSO");
	
	private String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}


