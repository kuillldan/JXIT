package org.lyk.uitl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by liuyuank on 9/13/2017.
 */
@Service("serviceProxy")
public class ServiceProxy
{
    private static final Logger logger = LoggerFactory.getLogger("logfile");

    public void before()
    {
        logger.info("[切面]在调用服务层之前执行");
    }
    public void after()
    {
        logger.info("[切面]在调用服务层之后执行");
    }
}
