package com.notes.notelist.security;

public enum ApplicationUserPermission {
    NOTE_READ("note:read"),
    NOTE_WRITE("note:write"),
    USER_READ("user:read"),
    USER_WRITE("user:write");

    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
