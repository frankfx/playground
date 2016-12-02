package dbs1.tests;

import java.util.HashSet;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import dbs1.persistence.HibernateUtils;
import dbs1.persistence.data.Abteilung;
import dbs1.persistence.data.ElektroMaschine;
import dbs1.persistence.data.KraftstoffMaschine;
import dbs1.persistence.data.PMZuteilung;
import dbs1.persistence.data.Person;




public class PersistentQueries {

	public static void initdb(){
		/*
		 * erzeuge abteilungen 
		 */
		Abteilung  spielzeug = new Abteilung(new HashSet<Person>(), "Spielzeug");
		Abteilung  computer = new Abteilung(new HashSet<Person>(), "Computer");
		Abteilung suppen = new Abteilung(new HashSet<Person>(), "Computer");
		/*
		 * erzeuge Maschinen
		 */
		ElektroMaschine presse = new ElektroMaschine();
		presse.setName("presse");
		presse.setAufnahmeleistung(1000);
		presse.setLeistung(800);
		presse.setNetzspannung(220);
		ElektroMaschine fAnlage = new ElektroMaschine();
		fAnlage.setName("fuellanlage");
		fAnlage.setAufnahmeleistung(2000);
		fAnlage.setLeistung(1800);
		fAnlage.setNetzspannung(380);
		KraftstoffMaschine saege = new KraftstoffMaschine();
		saege.setName("saege");
		saege.setLeistung(2000);
		saege.setVerbrauch(2);
		saege.setKraftstroff("benzin");
		/*
		 * erzeuge personal 
		 */
		Person helmut =      new Person("Meier", "Helmut", "67" , spielzeug,  "L4");
		Person margot =      new Person("Mueller", "Margot", "73" , spielzeug,  "L5");
		Person martin =      new Person("Bayer", "Martin", "114" , computer,  "L6");
		Person birgit =      new Person("Daum", "Birgit", "51" , suppen,  "L8");
		Person willi =       new Person("Stoermer", "Willi", "69" , suppen, "L6");
		Person hans =        new Person("Haar", "Hans", "333" , computer,  "L6");
		Person reinerWilli = new Person("Reiner", "Willi", "701" , suppen, "L6");
		Person michael = new Person("Just", "Michael", "82" , suppen, "L6");
		// add to abteilung 
		spielzeug.getPerson().add(helmut);
		spielzeug.getPerson().add(margot);
		computer.getPerson().add(martin);
		computer.getPerson().add(hans);
		suppen.getPerson().add(birgit);
		suppen.getPerson().add(willi);
		suppen.getPerson().add(reinerWilli);
		suppen.getPerson().add(michael);
		spielzeug.setLeiter(helmut);
		computer.setLeiter(hans);
		suppen.setLeiter(hans);
		/*
		 * erzeuge pmzuteilung 
		 */
		PMZuteilung helmutPresse = new PMZuteilung(3, presse, helmut);
		PMZuteilung helmutFAnlage = new PMZuteilung(2, fAnlage, helmut);
		PMZuteilung helmutSaege = new PMZuteilung(3, saege, helmut);
		PMZuteilung margotPresse = new PMZuteilung(5,presse, margot);
		PMZuteilung martinFAnlage = new PMZuteilung(5, fAnlage, martin);
		PMZuteilung martinSaege = new PMZuteilung(3, saege, martin);
		PMZuteilung birgitfAnlage = new PMZuteilung(2, fAnlage, birgit);
		PMZuteilung williSaege = new PMZuteilung(2, saege, willi);
		PMZuteilung hansPresse = new PMZuteilung(3, presse, hans);
		PMZuteilung reinerWilliPresse = new PMZuteilung(2, presse, reinerWilli);
		PMZuteilung reinerWilliSaege = new PMZuteilung(2, saege, reinerWilli);
		PMZuteilung michaelSaege = new PMZuteilung(2, saege, michael);
		/*
		 * save
		 */
		Session session = HibernateUtils.getCurrentSession();
		HibernateUtils.beginCurrentTransaction();
		session.save(spielzeug);
		session.save(computer);
		session.save(suppen);
		session.save(presse);
		session.save(fAnlage);
		session.save(saege);
		session.save(helmut);
		session.save(margot);
		session.save(martin);
		session.save(birgit);
		session.save(willi);
		session.save(hans);
		session.save(reinerWilli);
		session.save(michael);
		session.save(helmutPresse);
		session.save(helmutFAnlage);
		session.save(helmutSaege);
		session.save(margotPresse);
		session.save(martinFAnlage);
		session.save(martinSaege);
		session.save(birgitfAnlage);
		session.save(williSaege);
		session.save(hansPresse);
		session.save(reinerWilliPresse);
		session.save(reinerWilliSaege);
		session.save(michaelSaege);
		HibernateUtils.commitCurrentTransaction();
	}
	
	
	public static class ExampleObject{
		String arg1;
		String arg2;
		
		public ExampleObject(){
			
		}

		public String getArg1() {
			return arg1;
		}

		public void setArg1(String arg1) {
			this.arg1 = arg1;
		}

		public String getArg2() {
			return arg2;
		}

		public void setArg2(String arg2) {
			this.arg2 = arg2;
		}

		@Override
		public String toString() {
			return "ExampleObject [arg1=" + arg1 + ", arg2=" + arg2 + "]";
		}
		
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// erstelle Datenbank
		initdb();
		
		System.out.println("finish db initialization");
		
		/*
		 * Anfragen: HQL
		 * 
		 */
		// Anfrage 1
		HibernateUtils.beginCurrentTransaction();
		String hqlQuery = "SELECT s.person FROM Abteilung s WHERE s.abteilungsName = 'Spielzeug' ";
		Query hqlAnfrage = HibernateUtils.getCurrentSession().createQuery(hqlQuery);
		System.out.println("Anfrage 1:");
		for(Person p : (List<Person>)hqlAnfrage.list()){
			System.out.println(p);
		}
		HibernateUtils.commitCurrentTransaction();

		// Anfrage 2
		HibernateUtils.beginCurrentTransaction();
		hqlQuery = "SELECT p FROM Person p WHERE p.abteilung.abteilungsName = 'Spielzeug' ";
		hqlAnfrage = HibernateUtils.getCurrentSession().createQuery(hqlQuery);
		System.out.println("Anfrage 2:");
		for(Person p : (List<Person>)hqlAnfrage.list()){
			System.out.println(p);
		}
		HibernateUtils.commitCurrentTransaction();
		
		// Anfrage 3 
		HibernateUtils.beginCurrentTransaction();
		hqlQuery =" SELECT p " +
				"   FROM Person p " +
				"   WHERE id =  " +
				"        (SELECT z.mitarbeiter.id " +
				"         FROM PMZuteilung z " +
				"         GROUP by z.mitarbeiter.id " +
				"         HAVING count(z.maschine)  = " +
				"                (SELECT count(*) FROM AbstrakteMaschine   )  ) ";
		hqlAnfrage = HibernateUtils.getCurrentSession().createQuery(hqlQuery);
		System.out.println("Anfrage 3:");
		for(Person p : (List<Person>)hqlAnfrage.list()){
			System.out.println(p);
		}
		HibernateUtils.commitCurrentTransaction();
		
		// Anfarge 4
		HibernateUtils.beginCurrentTransaction();
		hqlQuery ="SELECT p FROM Person p WHERE p not in ( " +
				" SELECT pm.mitarbeiter FROM PMZuteilung pm WHERE pm.note < ? )  ";
		hqlAnfrage = HibernateUtils.getCurrentSession().createQuery(hqlQuery);
		hqlAnfrage.setInteger(0, 4);
		System.out.println("Anfrage 4:");
		for(Person p : (List<Person>)hqlAnfrage.list()){
			System.out.println(p);
		}
		HibernateUtils.commitCurrentTransaction();
		
		/*
		 * Criteria 
		 */
		// Anfrage 1
		HibernateUtils.beginCurrentTransaction();
		Criteria criteria =  HibernateUtils.getCurrentSession().
		createCriteria(Person.class).addOrder(Order.asc("name")).
		createCriteria("abteilung").add(Restrictions.eq("abteilungsName", "Spielzeug"));
		for(Person p : (List<Person>)criteria.list()){
			System.out.println(p);
		}
		HibernateUtils.commitCurrentTransaction();

		// Anfrage 2
		HibernateUtils.beginCurrentTransaction();
		Person examplePerson = new Person();
		examplePerson.setVorname("Willi");
		criteria =  HibernateUtils.getCurrentSession().createCriteria(Person.class).
		add(Example.create(examplePerson));
		for(Person p :  (List<Person>)criteria.list()){
			System.out.println(p);
		}
		HibernateUtils.commitCurrentTransaction();
		/*
		 * SQL Queries
		 * 
		 */
//		HibernateUtils.beginCurrentTransaction();
//		SQLQuery sqlQuery = HibernateUtils.getCurrentSession().
//		createSQLQuery("SELECT name as arg1, vorname as arg2  FROM  h_person ");
//		for(Object p :  sqlQuery.list()){
//			System.out.println(((Object[])p)[1]);
//		}
//		HibernateUtils.commitCurrentTransaction();	
	}

}
