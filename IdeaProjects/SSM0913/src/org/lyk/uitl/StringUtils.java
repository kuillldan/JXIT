package org.lyk.uitl;

/**
 * Created by liuyuank on 9/14/2017.
 */
public class StringUtils
{
    public static boolean isEmpty(String str)
    {
        if(null == str || "".equals(str))
        {
            return true;
        }
        return false;
    }
}
