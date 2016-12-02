package de.application.hibernate.hibernateUserTypes;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import de.application.spending.ActivityEnum;
import de.application.spending.SpendingItem;

public class HibernateUtil {
	private SessionFactory sessionFactory;
	
	public static void main(String[] args) {
		HibernateUtil HB = new HibernateUtil();
		
		SpendingItem item1 = new SpendingItem(1, LocalDate.now(), ActivityEnum.SPORT, "this is a test 1", 3.2, true);
		
		/* Add few spendingItem records in database */
		Integer itemID1 = HB.addSpendingItem(item1);
		
		/* List down all the employees */
		HB.listSpendingItems();
		
		/* Update employee's records */
		//HB.updateSpendingItem(itemID1, "updated description");
		
		/* Delete an employee from the database */
		//HB.deleteSpendingItem(itemID1);

		/* List down new list of the employees */
		//HB.listSpendingItems();
		
		HB.sessionFactory.close();
	}	
	
	public HibernateUtil(){
		this.sessionFactory = buildSessionFactory();
	}
	
	private SessionFactory buildSessionFactory() {
		try{
			// configures settings from hibernate.cfg.xml
			StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
			return new MetadataSources( registry ).buildMetadata().buildSessionFactory();
		} catch (Throwable ex){
			System.err.println("Initial SessionFactory creation failed. " + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}	
	
	public SessionFactory getSessionFactory(){
		return sessionFactory;
	}


// ==================================================================
// All database functions
// ==================================================================
	
	/* Method to CREATE a SpendingItem in the database */
	public Integer addSpendingItem(SpendingItem item) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		Integer itemID = null;
		try {
			tx = session.beginTransaction();
			itemID = (Integer) session.save(item);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return itemID;
	}	
	
	/* Method to READ all the items */
	public void listSpendingItems() {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			List<?> items = session.createQuery("FROM SpendingItem").list();
			for (Iterator<?> iterator = items.iterator(); iterator.hasNext();) {
				SpendingItem item = (SpendingItem) iterator.next();
				System.out.println(item);
			}
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	/* Method to UPDATE description or an spendingItem */
	public void updateSpendingItem(Integer itemID, String desc) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			SpendingItem item = (SpendingItem) session.get(SpendingItem.class, itemID);
			item.setDescription(desc);
			session.update(item);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	/* Method to DELETE an spendingItem from the records */
	public void deleteSpendingItem(Integer itemID) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			SpendingItem item = (SpendingItem) session.get(SpendingItem.class, itemID);
			session.delete(item);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
}
