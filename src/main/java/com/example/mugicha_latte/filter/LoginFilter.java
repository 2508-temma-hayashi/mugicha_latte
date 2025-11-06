package com.example.mugicha_latte.filter;
import com.example.mugicha_latte.repository.entity.User;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        //型変換
        HttpServletRequest httpRequest = (HttpServletRequest)request;
        HttpServletResponse httpResponse = (HttpServletResponse)response;

        User user = (User) httpRequest.getSession().getAttribute("loginUser");
        if (user == null) {
            httpRequest.getSession().setAttribute("errorMessages", "ログインしてください");
            httpResponse.sendRedirect("login");
            return;
        }
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig config) {
    }

    @Override
    public void destroy() {
    }
}
