package lyk.main;

import java.util.ArrayList;
import java.util.List; 
import java.util.Set;

 






import org.hibernate.Query;
import org.hibernate.Session;

import dbc.HibernateSessionFactory;
import lyk.vo.Groups;
import lyk.vo.Item; 
import lyk.vo.MemberDetails;
import lyk.vo.MemberLogin;
import lyk.vo.News;
import lyk.vo.Role;
import lyk.vo.Subitem; 
public class Hello
{
	public static List<News> allNews = new ArrayList<News>();
	public static void main(String[] args) throws InterruptedException
	{
		Session hibernateSession = HibernateSessionFactory.getSession();
//		 
//		MemberLogin login = new MemberLogin();
//		login.setMid("sheldon-complete");
//		login.setPassword("123");
//		
//		MemberDetails details = new MemberDetails();
//		details.setMid("sheldon-complete");
//		details.setName("yuankui");
//		details.setPhone("132123fffff");
//		details.setEmail("kuikui@hpe.com");
//		
//		login.setMemberDetails(details);
//		
//		hibernateSession.update(login); 
//		hibernateSession.beginTransaction().commit();
		
		
//		MemberLogin login = (MemberLogin) hibernateSession.get(MemberLogin.class, "sheldon-complete");
//		System.out.println(login);
		
		String hsql = " FROM MemberLogin WHERE mid=? ";
		Query query = hibernateSession.createQuery(hsql);
		query.setParameter(0, "sheldon-complete");
		MemberLogin login = (MemberLogin) query.uniqueResult();
		System.out.println(login);
		System.out.println(login.getMemberDetails());
		System.out.println("//Main done~~~");
	}
}


