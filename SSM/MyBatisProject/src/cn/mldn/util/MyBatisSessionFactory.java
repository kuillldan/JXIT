package cn.mldn.util;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * @author mldn
 * 此类负责取得MyBatis的连接对象以及关闭操作
 */
public class MyBatisSessionFactory {
	// 定义的是资源文件的读取位置，由于不会改变，将其定义为全局常量
	private static final String CONFIG_FILE_LOCATION = "mybatis.cfg.xml" ;
	// 方便在同一线程中管理SqlSession接口对象
	private static final ThreadLocal<SqlSession> threadLocal = new ThreadLocal<SqlSession>() ;
	// 定义sessionFactory接口对象
	private static SqlSessionFactory sessionFactory ;
	// 读取资源文件需要使用IO流完成
	private static Reader reader = null ;
	static {	// 定义一个static代码块，在这个代码块里面负责取得sessionFactory
		try {
			// 根据资源文件的路径进行内容的读取
			reader = Resources.getResourceAsReader(CONFIG_FILE_LOCATION) ;
			// 根据资源文件的定义，来创建一个SqlSessionFactory接口对象
			sessionFactory = new SqlSessionFactoryBuilder().build(reader) ;
		} catch (Exception e) {
			e.printStackTrace(); 
		}
	}
	private MyBatisSessionFactory() {}
	/**
	 * 取得SqlSessionFactory接口对象
	 * @return 一个实例化好的SqlSessionFactory接口对象
	 */
	public static SqlSessionFactory getSessionFactory() {
		return sessionFactory ;
	}
	/**
	 * 取得SqlSession接口对象，返回的对象保存在了ThreadLocal中
	 * @return 一个实力好的SqlSession接口对象
	 */
	public static SqlSession getSession() {
		// 首先判断在ThreadLocal类中是否存在有SqlSession接口对象
		SqlSession session = threadLocal.get() ;
		if (session == null) {	// 没有保存过此对象
			if (sessionFactory == null) {
				rebuildSessionFactory(); 	// 重新创建SqlSessionFactory接口对象
			}
			session = (sessionFactory != null) ? sessionFactory.openSession()
					: null; // 创建SqlSession对象
			threadLocal.set(session); // 保存在ThreadLocal之中
		}
		return session ;
	}
	/**
	 * 以事务控制的方式取得SqlSession对象
	 * @param autoCommit 是否要进行自动提交，如果设置为true，表示自动提交
	 * @return
	 */
	public static SqlSession getSession(boolean autoCommit) {
		// 首先判断在ThreadLocal类中是否存在有SqlSession接口对象
		SqlSession session = threadLocal.get() ;
		if (session == null) {	// 没有保存过此对象
			if (sessionFactory == null) {
				rebuildSessionFactory(); 	// 重新创建SqlSessionFactory接口对象
			}
			session = (sessionFactory != null) ? sessionFactory.openSession(autoCommit)
					: null; // 创建SqlSession对象
			threadLocal.set(session); // 保存在ThreadLocal之中
		}
		return session ;
	}
	/**
	 * 负责创建新的SqlSessionFactory类对象
	 */
	private static void rebuildSessionFactory() {
		try {
			// 根据资源文件的路径进行内容的读取
			reader = Resources.getResourceAsReader(CONFIG_FILE_LOCATION) ;
			// 根据资源文件的定义，来创建一个SqlSessionFactory接口对象
			sessionFactory = new SqlSessionFactoryBuilder().build(reader) ;
		} catch (Exception e) {
			e.printStackTrace(); 
		}
	}
	/**
	 * 关闭SqlSession连接
	 */
	public static void closeSession() {
		SqlSession session = threadLocal.get() ;
		threadLocal.set(null);	// 清空保存的数据
		if (session != null) {
			session.close(); 
		}
	}
}
