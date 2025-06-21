package br.imd.ufrn.feirinhas_ufrn.domain.usuario;

public enum UserRole {
    ADMIN("ADMIN"),
    USER("USER");

    private String role;

    UserRole(String role){
        this.role = role.toUpperCase();
    }

    public String getRole(){
        return role;
    }
}
