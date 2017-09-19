package org.lyk.main;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Created by liuyuank on 9/15/2017.
 */

class Pair<T>
{
    public T middle;
    private T first;
    private T second;

    public Pair()
    {
    }

    public Pair(T first, T second)
    {
        this.first = first;
        this.second = second;
    }

    public T getFirst()
    {
        return first;
    }

    public void setFirst(T first)
    {
        this.first = first;
    }

    public T getSecond()
    {
        return second;
    }

    public void setSecond(T second)
    {
        this.second = second;
    }

    @Override
    public String toString()
    {
        return "Pair{" +
                "first=" + first +
                ", second=" + second +
                '}';
    }
}

class ArrayAlg
{
    public static <T extends Comparable & Serializable> Pair<T> minmax(T[] a)
    {
        if (a == null || a.length == 0)
            return null;
        T min = a[0];
        T max = a[1];
        for (int i = 1; i < a.length; i++)
        {
            if (max.compareTo(a[i]) < 0)
                max = a[i];
            if (min.compareTo(a[i]) > 0)
            {
                min = a[i];
            }
        }
        return new Pair<T>(min, max);
    }
    public static <T extends Throwable> void doWork(T t) throws T
    {
        try
        {}
        catch(Throwable e)
        {
            e.initCause(e);
            throw e;
        }
    }
}

public class Hello
{
    public static void main(String[] args) throws Exception
    {
        Object[] all = new Object[10];
        all[0] = "xxx";
        all[1] = 2;
        System.out.println(Arrays.toString(all));
    }
}
