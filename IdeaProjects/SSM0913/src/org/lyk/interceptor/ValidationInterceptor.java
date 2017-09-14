package org.lyk.interceptor;

import org.lyk.uitl.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

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
        Enumeration<String> allParameterNames = httpServletRequest.getParameterNames();

        while (allParameterNames.hasMoreElements())
        {
            String parameterName = allParameterNames.nextElement();
            String parameterValue = httpServletRequest.getParameter(parameterName);
            System.out.println(parameterName + ":" + parameterValue);
        }


        HandlerMethod handlerMethod = (HandlerMethod) o;
        Object actionObject = handlerMethod.getBean();
        String methodName = handlerMethod.getMethod().getName();
        String ruleName = methodName + "Rule";
        Field ruleField = actionObject.getClass().getDeclaredField(ruleName);
        if (ruleField == null)
        {
            LOGGER.info(methodName + "没有校验规则");
            return true;
        }
        ruleField.setAccessible(true);
        String rule = (String) ruleField.get(actionObject);
        if (rule == null || "".equals(rule))
        {
            LOGGER.warn(methodName + "设置了相应的校验规则，但是的校验规则为空");
            return true;
        }
        String[] allRules = rule.split("\\|");
        Map<String, String> errors = new HashMap<>();
        for (String eachRule : allRules)
        {
            String[] requiredFieldAndType = eachRule.split(":");
            if (requiredFieldAndType == null || "".equals(requiredFieldAndType))
            {
                LOGGER.warn("在" + methodName + "中检测到不合法的校验规则");
                continue;
            }
            String requiredField = requiredFieldAndType[0];
            String requiredType = requiredFieldAndType[1];
            String realValue = httpServletRequest.getParameter(requiredField);
            if (StringUtils.isEmpty(realValue))
            {
                errors.put(requiredField, "该字段不能为空");
                continue;
            }
            if("String".equalsIgnoreCase(requiredType))
            {
                continue;
            }

            if ("int".equalsIgnoreCase(requiredType) || "Integer".equalsIgnoreCase(requiredType))
            {
                this.isInt(realValue, errors, requiredField);
                continue;
            }


            if ("double".equalsIgnoreCase(requiredType) || "float".equalsIgnoreCase(requiredType))
            {
                this.isDoubleOrFload(realValue, errors, requiredField);
                continue;
            }


            if ("date".equalsIgnoreCase(requiredType))
            {
                this.isDate(realValue, errors, requiredField);
                continue;
            }

            String[] realValues = httpServletRequest.getParameterValues(requiredField);
            if (realValues == null || realValues.length <= 0)
            {
                errors.put(requiredField, "该字段不能为空");
            }

            LOGGER.debug("requiredType:" + requiredType);
            if("String[]".equalsIgnoreCase(requiredType))
            {
                for(String eachValue : realValues)
                {
                    if(StringUtils.isEmpty(eachValue))
                    {
                        errors.put(requiredField,"该字段元素不能为空");
                        continue;
                    }
                }
                continue;
            }

            if ("int[]".equalsIgnoreCase(requiredType) || "Integer[]".equalsIgnoreCase(requiredType))
            {
                for (String eachValue : realValues)
                {
                    if (StringUtils.isEmpty(eachValue))
                    {
                        errors.put(requiredField, "该字段的元素不能为空");
                        continue;
                    } else
                    {
                        if (!this.isInt(eachValue, errors, requiredField))
                        {
                            continue;
                        }
                    }
                }
                continue;
            }

            if ("double[]".equalsIgnoreCase(requiredType) || "Double[]".equalsIgnoreCase(requiredType) || "float[]".equalsIgnoreCase(requiredType) || "Float[]".equalsIgnoreCase(requiredType))
            {
                for (String eachValue : realValues)
                {
                    if (StringUtils.isEmpty(eachValue))
                    {
                        errors.put(requiredField, "该字段的元素不能为空");
                        continue;
                    } else
                    {
                        if (!this.isDoubleOrFload(eachValue, errors, requiredField))
                        {
                            continue;
                        }
                    }
                }
                continue;
            }

            if ("date[]".equalsIgnoreCase(requiredType))
            {
                for (String eachValue : realValues)
                {
                    if (StringUtils.isEmpty(eachValue))
                    {
                        errors.put(requiredField, "该字段的元素不能为空");
                        continue;
                    } else
                    {
                        if (!this.isDate(eachValue, errors, requiredField))
                        {
                            continue;
                        }
                    }
                }

                continue;
            } else
            {
                LOGGER.warn("不支持的数据类型(" + requiredType + "),无法完成数据验证");
            }
        }

        if (errors.size() <= 0)
        {
            return true;
        } else
        {
            LOGGER.warn("数据验证失败");
            for (String key : errors.keySet())
            {
                LOGGER.warn(key + ":" + errors.get(key));
            }
            return false;
        }
    }

    private boolean isInt(String realValue, Map<String, String> errors, String requiredField)
    {
        if (!realValue.matches("\\d+"))
        {
            errors.put(requiredField, "上传了非法的整型格式");
            return false;
        }
        return true;
    }

    private boolean isDoubleOrFload(String realValue, Map<String, String> errors, String requiredField)
    {
        if (!realValue.matches("\\d+\\.\\d+"))
        {
            errors.put(requiredField, "上传了非法的小数格式");
            return false;
        }
        return true;
    }

    private boolean isDate(String realValue, Map<String, String> errors, String requiredField)
    {
        if (!realValue.matches("\\d{4}-\\d{2}-\\d{2}") && !realValue.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}") && !realValue.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}\\.\\d{3}"))
        {
            errors.put(requiredField, "上传了非法的日期格式");
            return false;
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
