package com.spring.read_service.CustomAnnotations;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.nio.file.AccessDeniedException;

@Aspect
@Component
public class RoleAspect {

    @Before("@annotation(com.spring.read_service.CustomAnnotations.Admin)")
    public void checkAdminRole() throws AccessDeniedException {
        if (!hasRole("ROLE_ADMIN")) {
            throw new AccessDeniedException("Access denied: Admin role required.");
        }
    }
    @Before("@annotation(com.spring.read_service.CustomAnnotations.User)")
    public void checkUserRole() throws AccessDeniedException {
        if (!hasRole("ROLE_USER")) {
            throw new AccessDeniedException("Access denied: User role required.");
        }
    }
    private boolean hasRole(String role) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getAuthorities() != null) {
            return authentication.getAuthorities().stream()
                    .anyMatch(authority -> authority.getAuthority().equals(role));
        }
        return false;
    }

}

