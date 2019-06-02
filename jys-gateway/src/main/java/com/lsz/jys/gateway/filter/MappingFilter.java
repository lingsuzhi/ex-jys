package com.lsz.jys.gateway.filter;


import com.lsz.jys.gateway.wrapper.RequestWrapper;
import com.lsz.jys.gateway.wrapper.WrappedHttpResponse;
import com.lsz.jys.util.UuidMd5;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;


@Component
public class MappingFilter extends OncePerRequestFilter {

    @Value("${spring.application.name}")
    private String AppName;
    private Logger log = LoggerFactory.getLogger(this.getClass());
    @Value("#{'${serviceList}'.split(',')}")
    private List<String> serviceList;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain
            filterChain) throws ServletException, IOException {
        String uri = request.getRequestURI().substring(1);
        WrappedHttpResponse custResponse = WrappedHttpResponse.wrap(response);
        RequestWrapper fakeRequest = null;
        for (String item : serviceList) {
//            if (uri.startsWith(item)) {
                fakeRequest = new RequestWrapper(request);
                RequestContext ctx = RequestContext.getCurrentContext();
                Map<String, String> zuulRequestHeaders = ctx.getZuulRequestHeaders();

                String threadName = UuidMd5.uuidWith8Bit();
                Thread.currentThread().setName(threadName);
                zuulRequestHeaders.put("SZ-caller", AppName);
                zuulRequestHeaders.put("SZ-threadName", threadName);
            uri = uri.replace("api/","/");
                fakeRequest.setRequestURI("/" + item  +  uri);
                break;
//            }
        }
        if (fakeRequest != null) {
            long startMills = System.currentTimeMillis();
            String body = fakeRequest.getBody();
            log.info("###### Begin [{}] Param: {}", uri, body);
            try{
                filterChain.doFilter(fakeRequest, custResponse);
            }finally {
                String responseStr = custResponse.getResponseContent();
                log.info("##### Finish [{}] Cost[{}ms] Response: {}", uri, System.currentTimeMillis() - startMills, responseStr);
            }

        } else {
            log.info("路径没配置 {}", uri);
            response.setHeader("Content-Type", "application/json;charset=UTF-8");
            response.getWriter().println("{\n  \"code\": \"0404\",\n    \"message\": \"失败\"}");
            response.setStatus(404);
        }

    }
}
