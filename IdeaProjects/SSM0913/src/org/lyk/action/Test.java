package org.lyk.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by liuyuank on 9/13/2017.
 */
@Controller
@RequestMapping("/test/*")
public class Test
{
    Logger logger = LoggerFactory.getLogger("logfile");
    @RequestMapping("show")
    public ModelAndView show(String msg)
    {
        logger.info("msg:" + msg);
        ModelAndView mav = new ModelAndView("/index.jsp");
        return mav;
    }
}
