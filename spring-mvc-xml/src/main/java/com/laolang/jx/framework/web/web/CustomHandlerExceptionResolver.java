package com.laolang.jx.framework.web.web;

import java.nio.charset.StandardCharsets;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.core.Ordered;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laolang.jx.framework.common.core.R;
import com.laolang.jx.framework.common.exception.BusinessException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class CustomHandlerExceptionResolver extends AbstractHandlerExceptionResolver {
    private static final String APPLICATION_JSON_UTF8 = "application/json; charset=utf-8";

    private final ObjectMapper objectMapper;
    
    
    /**
     * 参考: DefaultHandlerExceptionResolver#doResolveException
     */
    @SuppressWarnings("unchecked")
    @Nullable
    @Override
    protected ModelAndView doResolveException(@Nonnull HttpServletRequest request,
            @Nonnull HttpServletResponse response, Object handler, @Nonnull Exception ex) {
        try {
            log.info("请求出错. url:{}, error:{}", request.getRequestURI(), ExceptionUtils.getMessage(ex));
            response.setCharacterEncoding(StandardCharsets.UTF_8.name());
            response.setContentType(APPLICATION_JSON_UTF8);
            R<?> r = R.error();
            if (ex instanceof NoHandlerFoundException) {
                r = handleNoHandlerFoundException(request, response);
            } else if (ex instanceof BusinessException) {
                r = handleBusinessException(response, (BusinessException) ex);
            } else {
                log.error("未知异常. error class:{}", ex.getClass());
            }

            ModelAndView mv = new ModelAndView(new MappingJackson2JsonView());
            mv.addAllObjects(objectMapper.readValue(objectMapper.writeValueAsString(r), Map.class));
            return mv;
        } catch (Exception e) {
            log.error("异常处理出错. e:{}", ExceptionUtils.getMessage(e));
        }
        return null;
    }

    /**
     * 404
     */
    private R<?> handleNoHandlerFoundException(HttpServletRequest request, HttpServletResponse response) {
        R<?> r = R.notFound();
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        log.info("请求地址不存在. url:{}", request.getRequestURI());
        return r;
    }

    /**
     * 业务异常
     */
    private R<?> handleBusinessException(HttpServletResponse response, BusinessException ex) {
        R<?> r = R.failed();
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        r.setPropFromBusinessException(ex);
        log.error("code:{},msg:{}", ex.getCode(), ex.getMsg());
        return r;
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}