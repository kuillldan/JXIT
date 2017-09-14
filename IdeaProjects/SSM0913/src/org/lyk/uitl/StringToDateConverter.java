package org.lyk.uitl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by liuyuank on 9/14/2017.
 */
@Component("stringToDateConverter")
public class StringToDateConverter implements Converter<String,Date>
{
    private static final Logger logger = LoggerFactory.getLogger("logfile");
    @Override
    public Date convert(String s)
    {
        if(s == null || "".equals(s))
            return null;

        SimpleDateFormat sdf = null;
        if(s.matches("\\d{4}-\\d{2}-\\d{2}"))
        {
            sdf = new SimpleDateFormat("yyyy-MM-dd");
        }
        else if(s.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}"))
        {
            sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
        else if(s.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}\\.\\d{3}"))
        {
            sdf = new SimpleDateFormat("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}");
        }
        else
        {
            logger.warn("数据转失败。无法将数据("+s+")转换为日期格式");
        }

        try
        {
            return sdf.parse(s);
        } catch (ParseException e)
        {
            logger.error("日期("+s+")解析失败.");
            return null;
        }
    }
}
