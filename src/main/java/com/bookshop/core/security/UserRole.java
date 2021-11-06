package com.bookshop.core.security;

public enum UserRole {
    ADMIN("ADMIN"), CLIENT("CLIENT");

    private final String role;

    UserRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return role;
    }
}
