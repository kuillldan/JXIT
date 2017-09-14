package org.lyk.action;

import org.lyk.vo.Dept;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by liuyuank on 9/13/2017.
 */
@Controller
@RequestMapping("/test/*")
public class Test
{
    Logger logger = LoggerFactory.getLogger("logfile");
    @RequestMapping("show")
    public ModelAndView show(@RequestParam(defaultValue = "hello shit") String msg, Dept dept,HttpServletRequest request, Date hiredate)
    {
        System.out.println("雇佣日期:" + hiredate);
        ModelAndView mav = new ModelAndView("/index.jsp");
        return mav;
    }
}
