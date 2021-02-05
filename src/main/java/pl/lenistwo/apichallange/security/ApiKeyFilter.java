package pl.lenistwo.apichallange.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class ApiKeyFilter extends OncePerRequestFilter {

    @Value("${api.key.header}")
    private String API_KEY_HEADER;

    @Value("${api.key}")
    private String API_KEY;

    private final ObjectMapper mapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        if (request.getHeader(API_KEY_HEADER) == null || !request.getHeader(API_KEY_HEADER).equals(API_KEY)) {
            response.setStatus(HttpStatus.FORBIDDEN.value());
            var responseMessage = mapper.createObjectNode()
                                              .put("status", HttpStatus.FORBIDDEN.value())
                                              .put("message", "Provide Valid Api Key")
                                              .toPrettyString();
            response.getWriter().write(responseMessage);
            return;
        }

        filterChain.doFilter(request, response);
    }
}
