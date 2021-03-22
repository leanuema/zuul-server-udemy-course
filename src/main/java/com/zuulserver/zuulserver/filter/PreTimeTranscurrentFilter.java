package com.zuulserver.zuulserver.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class PreTimeTranscurrentFilter extends ZuulFilter {

    private static Logger logger = LoggerFactory.getLogger(PreTimeTranscurrentFilter.class);

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest httpServletRequest = requestContext.getRequest();
        logger.info(String.format("Request enrutado a ",
                httpServletRequest.getMethod(), httpServletRequest.getRequestURL().toString()));
        Long initTime = System.currentTimeMillis();
        httpServletRequest.setAttribute("initTime", initTime);
        return null;
    }

}