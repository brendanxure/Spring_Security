package com.example.securitydemo.entity;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

public enum Role {
    ADMIN(Set.of(Permission.WEATHER_READ, Permission.WEATHER_WRITE, Permission.WEATHER_DELETE)),
    USER(Set.of(Permission.WEATHER_READ));

    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }
}
