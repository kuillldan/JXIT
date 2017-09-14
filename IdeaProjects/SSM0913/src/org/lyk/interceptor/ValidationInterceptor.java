package org.lyk.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;

/**
 * Created by liuyuank on 9/14/2017.
 */
public class ValidationInterceptor implements HandlerInterceptor
{
    private static final Logger LOGGER = LoggerFactory.getLogger("logfile");

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception
    {

//        EnumerationhttpServletRequest.getParameterNames();


        HandlerMethod handlerMethod = (HandlerMethod)o;
        Object actionObject = handlerMethod.getBean();
        String methodName = handlerMethod.getMethod().getName();
        String ruleName = methodName + "Rule";
        Field ruleField = actionObject.getClass().getDeclaredField(ruleName);
        if(ruleField == null)
        {
            LOGGER.info(methodName + "没有校验规则");
            return true;
        }
        ruleField.setAccessible(true);
        String rule = (String)ruleField.get(actionObject);
        if(rule == null || "".equals(rule))
        {
            LOGGER.warn(methodName + "设置了相应的校验规则，但是的校验规则为空");
            return true;
        }
        String[] allRules = rule.split("\\|");
        for(String eachRule : allRules)
        {
            String[] requiredFieldAndType = eachRule.split(":");
            if(requiredFieldAndType == null || "".equals(requiredFieldAndType))
            {
                LOGGER.warn("在" + methodName + "中检测到不合法的校验规则");
                continue;
            }
            String requiredField = requiredFieldAndType[0];
            String requiredType = requiredFieldAndType[1];
//            if(httpServletRequest.getParameter())
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception
    {
        LOGGER.info("[拦截器] 执行Action之后" + o.getClass());
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception
    {
        LOGGER.info("[拦截器] 执行Action之后2" + o.getClass());
    }
}
