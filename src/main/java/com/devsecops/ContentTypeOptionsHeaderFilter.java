package com.devsecops;
import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ContentTypeOptionsHeaderFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Do nothing
    }
    public HttpServletResponse setContentTypeOptionsHeader(HttpServletResponse response) {
        response.setHeader("X-Content-Type-Options", "nosniff");
        return response;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
    throws IOException, ServletException 
    {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse = setContentTypeOptionsHeader(httpResponse);
        chain.doFilter(request, httpResponse);
    }

    @Override
    public void destroy() {
        // Do nothing
    }
}
