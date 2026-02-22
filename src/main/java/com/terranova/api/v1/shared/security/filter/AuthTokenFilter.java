package com.terranova.api.v1.shared.security.filter;


import com.terranova.api.v1.shared.security.utils.JwtUtilAdapter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AuthTokenFilter extends OncePerRequestFilter {

    private final JwtUtilAdapter jwtUtilAdapter;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Optional<String> tokenOptional = extractBearerToken(request, response, filterChain);

        if (tokenOptional.isEmpty()){
            filterChain.doFilter(request, response);
            return;
        }

        String token = tokenOptional.get();

//        try {
//            jwtUtil.validateJwtToken(token);
//
//            Claims claims = jwtUtil.extractAllClaims(token);
//            String identification = jwtUtil.getIdentificationFromToken(token);
//            List<String> roles = claims.get("roles", List.class);
//            List<SimpleGrantedAuthority> authorities = roles.stream()
//                    .map(SimpleGrantedAuthority::new)
//                    .toList();
//
//            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
//                    identification,
//                    null,
//                    authorities
//            );
//
//            authentication.setDetails(
//                    new WebAuthenticationDetailsSource().buildDetails(request)
//            );
//
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//
//            filterChain.doFilter(request, response);
//        } catch (TokenExpiredException | InvalidJwtTokenException ex) {
//            SecurityContextHolder.clearContext();
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//            response.setContentType("application/json");
//            response.getWriter().write("""
//                    {
//                        "message": "%s"
//                    }
//                    """.formatted(ex.getMessage()));
//        }
    }

    private Optional<String> extractBearerToken(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (authHeader == null || authHeader.isBlank() || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return Optional.empty();
        }

        String token = authHeader.substring(7).trim();

        if (token.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(token);
    }

}
