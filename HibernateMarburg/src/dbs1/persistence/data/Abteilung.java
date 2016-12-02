package dbs1.persistence.data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;
// alternatitv JPA 2.0 /1.0 hibernate anntotations
@Entity
@Table(name="H_Abteilung")
@TableGenerator(
            name="abt_gen", 
            table="H_ID_GEN", 
            pkColumnName="GEN_KEY", 
            valueColumnName="GEN_VALUE", 
            pkColumnValue="abt_id")
public class Abteilung {
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE, generator="abt_gen")
	private long id;
	@OneToMany(mappedBy="abteilung")
	private Set<Person> person;
	@Column(name="abtName")
	private String abteilungsName;
	@ManyToOne
	@JoinColumn(name="Leiter_ID")
	private Person leiter;
	// nicht persistente property
	@Transient
	private String property;
	
	// bi-direktionale 
	public Abteilung() {
		person = new HashSet<Person>();
	}

	
	public Abteilung(Set<Person> person, String abteilungsName) {
		super();
		this.person = person;
		this.abteilungsName = abteilungsName;
	}


	public Abteilung(Set<Person> person, String abteilungsName, Person leiter) {
		super();
		this.person = person;
		this.abteilungsName = abteilungsName;
		this.leiter = leiter;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Set<Person> getPerson() {
		return person;
	}

	public void setPerson(Set<Person> person) {
		this.person = person;
	}

	public String getAbteilungsName() {
		return abteilungsName;
	}

	public void setAbteilungsName(String abteilungsName) {
		this.abteilungsName = abteilungsName;
	}
	
	
	
	public Person getLeiter() {
		return leiter;
	}


	public void setLeiter(Person leiter) {
		this.leiter = leiter;
	}


	@Override
	public String toString() {
		return "Abteilung [abteilungsName=" + abteilungsName + ", id=" + id
				+ ", person=" + person + "]";
	}


	public static void main(String[] args){ 
		// load xml configuration
		Configuration config = new Configuration().configure(); 
		// Erzeuge Session Factory Objekt
		SessionFactory hibernateSessionFactory = config.buildSessionFactory();  
		// gibt Session objekt
		Long personId = null;
		// create person transient object 
		Abteilung abt1 = new Abteilung();
		abt1.setAbteilungsName("Abteilung 1");
		Abteilung abt2 = new Abteilung();
		abt2.setAbteilungsName("Abteilung 2");
		Person p1 = new Person("Mustermann", "Max", "p#0000001", abt1,  null);
		Person p2 = new Person("M�ller", "Sven", "p#0000002", abt1, null);
		Session session = hibernateSessionFactory.openSession();
		Transaction trx = null;
		try{
			trx = session.beginTransaction();
			abt1.setPerson(new HashSet<Person>());
			abt1.getPerson().add(p1);
			abt1.getPerson().add(p2);
			p1.setAbteilung(abt1);
			p2.setAbteilung(abt1);
			session.save(abt2);
			session.save(abt1);
			session.save(p1);
			session.save(p2);
			
//			Query query = session.createQuery("select a from Abteilung as a inner join a.person as personal  " +
//					" where personal.name = 'Mustermann'");
//			for(Abteilung p : (List<Abteilung>)query.list()){
//				System.out.println(p);
//			}
//			Criteria criteria = session.createCriteria(Abteilung.class).add(Restrictions.eq("abteilungsName", "Abteilung 1"));
//			for(Abteilung a : (List<Abteilung>)criteria.list()){
//				System.out.println(a);
//			}
			
//			Criteria criteria = session.createCriteria(Abteilung.class).
//				add(Restrictions.eq("abteilungsName", "Abteilung 1")).
//				createCriteria("person", "p").add(Restrictions.eq("vorname", "M"));
//			for(Abteilung a : (List<Abteilung>)criteria.list()){
//				System.out.println(a);
//			}
//			Person examplePerson = new Person();
//			examplePerson.setVorname("Max");
//			
//			Criteria criteria = session.createCriteria(Person.class).
//			add(Example.create(examplePerson));
//			for(Person a : (List<Person>)criteria.list()){
//				System.out.println(a);
//			}
			
			
			SQLQuery query = session.createSQLQuery("select * from H_Person");
			for(Person p : (List<Person>)query.addEntity(Person.class).list()){
				System.out.println(p);
			}
			
			
//			Query query = session.createQuery("select p from  Person p where p.abteilung.abteilungsName = 'Abteilung 1'");
//			Query query = session.createQuery("select p from  Person p where p.personalNr = 'p#0000001'");
//			Person p = (Person)query.uniqueResult();
//			System.out.println(p);
		
			
			trx.commit();
		}catch(HibernateException ex){
			if(trx != null){
				trx.rollback();
			}
			throw new RuntimeException( "Hibernate Problem!" ,ex);
		}
		session.close();
		
	}
	
}
