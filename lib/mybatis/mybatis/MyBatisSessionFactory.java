package cn.mldn.dbc;
import java.io.Reader;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
public class MyBatisSessionFactory {
	// 设置核心配置文件路径名称
	private static String CONFIG_FILE_LOCATION = "mybatis.cfg.xml";
	// 设置线程与数据库链接
	private static final ThreadLocal<SqlSession> threadLocal = new ThreadLocal<SqlSession>();
	// 创建数据库连接工厂类
	private static SqlSessionFactory sessionFactory;
	// 设置读取核心配置文件的类
	private static Reader configuration = null;
	static {
		try {
			configuration = Resources.getResourceAsReader(CONFIG_FILE_LOCATION);
			sessionFactory = new SqlSessionFactoryBuilder()
					.build(configuration);

		} catch (Exception e) {
			System.err.println("%%%% Error Creating SessionFactory %%%%");
			e.printStackTrace();
		}
	}
	private MyBatisSessionFactory() {
	}
	public static SqlSession  getSession()  {
		SqlSession  session = (SqlSession ) threadLocal.get();
		if (session == null) {
			if (sessionFactory == null) {
				rebuildSessionFactory();
			}
			session = (sessionFactory != null) ? sessionFactory.openSession()
					: null;
			threadLocal.set(session);
		}

		return session;
	}
	public static void rebuildSessionFactory() {
		try {
			configuration = Resources.getResourceAsReader(CONFIG_FILE_LOCATION);
			sessionFactory = new SqlSessionFactoryBuilder()
					.build(configuration);
		} catch (Exception e) {
			System.err.println("%%%% Error Creating SessionFactory %%%%");
			e.printStackTrace();
		}
	}
	public static void closeSession() {
		SqlSession session = (SqlSession) threadLocal.get();
		threadLocal.set(null);

		if (session != null) {
			session.close();
		}
	}
	public static SqlSessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public static Reader getConfiguration() {
		return configuration;
	}

}