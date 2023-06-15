package com.endtoend.historyOfMine.websecurity;

import com.endtoend.historyOfMine.service.UserService;
import com.endtoend.historyOfMine.utils.securityutils.JWTUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

@Service
public class InternalFilter extends OncePerRequestFilter {
    private final static String TOKEN = "jwtToken";
    private final static String NO_AUTH = "noAuth";
    private final UserDetailsService userService;

    public InternalFilter(UserService userService) {
        this.userService = userService;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        final String token;
        final String username;
        var tokenCookie = new Cookie(TOKEN, NO_AUTH);

        var cookieList = request.getCookies();
        if(cookieList != null) {
            for (var cookie : cookieList) {
                if (Objects.equals(cookie.getName(), TOKEN)) {
                    tokenCookie = cookie;
                }
            }
        }
        token = tokenCookie.getValue();
        if(Objects.equals(token, NO_AUTH)) {
            filterChain.doFilter(request, response);
            return;
        }

        username = JWTUtils.getUsername(token);
        if(username != null
                && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userService.loadUserByUsername(username);
            if (JWTUtils.isTokenValid(token, userDetails)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}

