package ru.javamentor.demospring.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import ru.javamentor.demospring.model.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler{

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException{
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        final Person person = (Person) authentication.getPrincipal();
        if (roles.contains("ROLE_ADMIN")) {
            httpServletResponse.sendRedirect("/person/admin");
        } else {
            httpServletResponse.sendRedirect("/person/one");
        }
    }
}
