import java.util.ArrayList;
import java.util.List;

class Person
{
    private String name;
    private String age;

    public void setName(String name)
    {
        this.name = name;
        
    }
    public String getName()
    {
        return this.name;
    }
}



public class Hello
{
    public static void main(String[] args) throws Exception
    { 
        System.out.println("hello shit");
        System.out.println("//Main done~~~");
        List<String> allList = new ArrayList<>();
        allList.add("Hello");
        System.out.println(allList);
    }
}