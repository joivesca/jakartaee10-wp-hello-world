package com.onndoo.jakarta.hello.web;

import java.io.Serializable;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.security.enterprise.SecurityContext;

@Named("userBean")
@RequestScoped

public class UserBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private SecurityContext securityContext;

    public String getUsername() {
        return securityContext.getCallerPrincipal().getName();
    }

    public String getRoles() {
        String roles = "";
        if (securityContext.isCallerInRole(Utils.ADMIN)) {
            roles = Utils.ADMIN;
        }
        if (securityContext.isCallerInRole(Utils.USER)) {
            if (!roles.isEmpty()) {
                roles += ", ";
            }
            roles += Utils.USER;
        }
        return roles;
    }
}