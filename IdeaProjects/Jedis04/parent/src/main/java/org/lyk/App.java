package org.lyk;

import redis.clients.jedis.Jedis;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Set;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws UnsupportedEncodingException
    {
        Jedis jedis = new Jedis("192.168.194.128",6379);
        jedis.auth("123");
        Map<String,String> all = jedis.hgetAll("clueCode");
        Set<String> keys = all.keySet();
        String dd = new String(all.get("2").getBytes("UTF-8"),"UTF-8");
        byte[] allBytes = dd.getBytes();
        for(byte b : allBytes)
            System.out.print(b);
        System.out.println();
        allBytes = new String("À´µç".getBytes("UTF-8"),"UTF-8").getBytes();
        for(byte b : allBytes)
            System.out.print(b);
    }
}
