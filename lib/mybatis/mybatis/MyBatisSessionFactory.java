package cn.mldn.dbc;
import java.io.Reader;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
public class MyBatisSessionFactory {
	// ���ú��������ļ�·������
	private static String CONFIG_FILE_LOCATION = "mybatis.cfg.xml";
	// �����߳������ݿ�����
	private static final ThreadLocal<SqlSession> threadLocal = new ThreadLocal<SqlSession>();
	// �������ݿ����ӹ�����
	private static SqlSessionFactory sessionFactory;
	// ���ö�ȡ���������ļ�����
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