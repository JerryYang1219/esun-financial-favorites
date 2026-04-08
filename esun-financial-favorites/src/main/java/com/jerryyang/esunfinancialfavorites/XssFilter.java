package com.jerryyang.esunfinancialfavorites;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import org.springframework.stereotype.Component;

import java.io.IOException;

// XSS 防護過濾器，過濾所有請求中的惡意腳本
@Component
public class XssFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // 將原始請求包裝成 XssRequestWrapper，對所有輸入進行過濾
        chain.doFilter(new XssRequestWrapper((HttpServletRequest) request), response);
    }

    // 內部類別，負責過濾請求參數中的 XSS 攻擊字元
    private static class XssRequestWrapper extends HttpServletRequestWrapper {

        public XssRequestWrapper(HttpServletRequest request) {
            super(request);
        }

        @Override
        public String getParameter(String name) {
            String value = super.getParameter(name);
            return value != null ? sanitize(value) : null;
        }

        @Override
        public String[] getParameterValues(String name) {
            String[] values = super.getParameterValues(name);
            if (values == null) return null;
            for (int i = 0; i < values.length; i++) {
                values[i] = sanitize(values[i]);
            }
            return values;
        }

        // 過濾掉 HTML 標籤與特殊字元，防止 XSS 攻擊
        private String sanitize(String value) {
            return value.replaceAll("<", "&lt;")
                    .replaceAll(">", "&gt;")
                    .replaceAll("\"", "&quot;")
                    .replaceAll("'", "&#x27;")
                    .replaceAll("/", "&#x2F;");
        }
    }
}