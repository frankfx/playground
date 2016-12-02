package dbs1.persistence;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

/**
 * 
 * 
 *
 */
public class HibernateUtils{
	/**
	 * 
	 */
	private static final SessionFactory hibernateSessionFactory;
	/**
	 * 
	 */
	private static final ThreadLocal threadSession = new ThreadLocal();
	/**
	 * 
	 */
	private static final ThreadLocal threadTransaction = new ThreadLocal();
	/**
	 * static consturctor
	 */
	static{
	  try {
		  Configuration config = new Configuration().configure();
		  hibernateSessionFactory = config.buildSessionFactory();
      } 
	  catch (Throwable ex) {
          // Make sure you log the exception, as it might be swallowed
          System.err.println("Initial SessionFactory creation failed." + ex);
          ex.printStackTrace();
          throw new ExceptionInInitializerError(ex);
      }
	}
	/**
	 *  
	 * @return session factory 
	 */
	public static SessionFactory getHibernateSessionFactory(){
		return hibernateSessionFactory;
	}
	/**
	 * Ensure that hibernate feature  "current_session_context_class = thread" set to thread. 
	 * If this fetaure enabled, hiberante ensures that each thread gets only one session. 
	 */
	public static Session getCurrentSession(){
		return hibernateSessionFactory.getCurrentSession();
	}
	/**
	 * Marks Begin of transaction. 
	 */
	public static void beginCurrentTransaction() throws RuntimeException{
		try{
			 getCurrentSession().beginTransaction();
		}catch(HibernateException ex){
			throw new RuntimeException(ex);
		}
	}
	/**
	 * 
	 * @throws HibernateException
	 */
	public static void commitCurrentTransaction() throws RuntimeException{
		try{
			getCurrentSession().getTransaction().commit();
		}catch(HibernateException ex){
			throw new RuntimeException(ex);
		}
	}
	/**
	 * 
	 * @throws RuntimeException
	 */
	public static void rollbackCurrentTransaction() throws RuntimeException{
		try{
			getCurrentSession().getTransaction().rollback();
		}catch(HibernateException ex){
			throw new RuntimeException(ex);
		}
	}
	/**
	 * 
	 * @return
	 */
	public static Session getSession() {
		Session s = (Session) threadSession.get();
		try {
		if (s == null) {
			s = hibernateSessionFactory.openSession();
			// set one session per thread
			threadSession.set(s);
		}
		} catch (HibernateException ex) {
			throw ex;
		}
		return s;
	}
	/**
	 * 
	 */
	public static void closeSession(){
		try {
			Session s = (Session) threadSession.get();
			threadSession.set(null);
		if (s != null && s.isOpen())
			s.close();
		} catch (HibernateException ex) {
			throw ex;
		}
	}
	/**
	 * 
	 * @return
	 */
	public static Transaction getTransaction(){
		Transaction trans = (Transaction)threadTransaction.get();
		try {
			if(trans == null){
				trans = getSession().getTransaction();
				// set transaction one to one session ont to one thread 
				threadTransaction.set(trans);
			}
		}catch(HibernateException ex){//TODO
			throw new RuntimeException("", ex);
		}
		return trans;
	}
	/**
	 * 
	 */
	public static void beginTransaction(){
		try{
			getTransaction().begin();
		}catch(HibernateException ex){
			throw new RuntimeException("", ex);
		}
	}
	/**
	 * 
	 */
	public static void commitTransaction(){
		try{
			getTransaction().commit();
			threadTransaction.remove();
		}catch(HibernateException ex){
			throw new RuntimeException("", ex);
		}
	}
}