// File: com/logistics/security/annotation/CurrentUsername.java

package com.logistics.annotation;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@AuthenticationPrincipal(expression = "claims['preferred_username']")
public @interface CurrentUsername {
}
