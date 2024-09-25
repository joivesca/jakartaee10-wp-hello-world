package com.onndoo.jakarta.hello.web;

import java.io.IOException;

import jakarta.inject.Inject;
import jakarta.security.enterprise.SecurityContext;
import jakarta.security.enterprise.authentication.mechanism.http.FormAuthenticationMechanismDefinition;
import jakarta.security.enterprise.authentication.mechanism.http.LoginToContinue;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HttpConstraint;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/")
@FormAuthenticationMechanismDefinition(
		loginToContinue = @LoginToContinue(errorPage = "/error.html",loginPage = "/welcome.html"))
@ServletSecurity(value = @HttpConstraint(rolesAllowed = { "user", "admin" }, transportGuarantee = ServletSecurity.TransportGuarantee.CONFIDENTIAL))
public class HomeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

    @Inject
    private SecurityContext securityContext;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (securityContext.isCallerInRole(Utils.ADMIN)) {
            response.sendRedirect("/admin.xhtml");
       
        } else if  (securityContext.isCallerInRole(Utils.USER)) {
            response.sendRedirect("/user.xhtml");
        }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
