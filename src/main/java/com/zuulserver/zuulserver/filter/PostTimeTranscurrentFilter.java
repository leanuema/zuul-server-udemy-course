package com.zuulserver.zuulserver.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class PostTimeTranscurrentFilter extends ZuulFilter {

    private static Logger logger = LoggerFactory.getLogger(PostTimeTranscurrentFilter.class);

    @Override
    public String filterType() {
        return "post";
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
        logger.info(String.format("Entrando a post ",
                httpServletRequest.getMethod(), httpServletRequest.getRequestURL().toString()));
        Long initTime = (Long)httpServletRequest.getAttribute("initTime");
        Long endTime = System.currentTimeMillis();
        Long transcurrentTime = endTime - initTime;
        logger.info(String.format("Tiempo transcurrido en milis: ", transcurrentTime));
        logger.info(String.format("Tiempo transcurrido en segundos: ", transcurrentTime.doubleValue()/1000.00));
        return null;
    }

}