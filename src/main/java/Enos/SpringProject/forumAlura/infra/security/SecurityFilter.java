package Enos.SpringProject.forumAlura.infra.security;

import Enos.SpringProject.forumAlura.domain.repositories.IUserRepository;
import Enos.SpringProject.forumAlura.domain.services.user.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;
    @Autowired
    private IUserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //verifica e captura em caso de chegada do tokenJWT
        var tokenJWT = retrieveToken(request);
        if(tokenJWT != null){
            //verifica validade do token
            var subject = tokenService.getSubject(tokenJWT);
            var user = userRepository.findByUsernameAndActive(subject,1);
            //autentifica usuario
            var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        //chama o filtro do Spring Boot
        filterChain.doFilter(request,response);
    }

    private String retrieveToken(HttpServletRequest request) {
        //recebe o token do cabeçario authorization
        var authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null){
            //verifica se há um cabeçario
            return authorizationHeader.replace("Bearer ","").trim();
        }
        return null;
    }
}