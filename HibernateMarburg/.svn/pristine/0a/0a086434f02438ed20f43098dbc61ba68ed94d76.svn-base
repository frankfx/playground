package dbs1.persistence.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

@Entity
@Table(name="H_Abteilung")
@TableGenerator(
            name="per_gen", 
            table="H_ID_GEN", 
            pkColumnName="GEN_KEY", 
            valueColumnName="GEN_VALUE", 
            pkColumnValue="per_id")
public class Person {
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE, generator="per_gen")
	long id; 
	
	String name; 
	
	String vorname;
	
	String personalNr;
	
	@ManyToOne
	@JoinColumn(name="abt_id")
	Abteilung abteilung; 
	
	
	String lohn;
	
	public Person(){
		
	}

	public Person(String name, String vorname, String personalNr,
			Abteilung abteilung, String lohn) {
		super();
		this.name = name;
		this.vorname = vorname;
		this.personalNr = personalNr;
		this.abteilung = abteilung;
		this.lohn = lohn;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public String getPersonalNr() {
		return personalNr;
	}

	public void setPersonalNr(String personalNr) {
		this.personalNr = personalNr;
	}

	public Abteilung getAbteilung() {
		return abteilung;
	}

	public void setAbteilung(Abteilung abteilung) {
		this.abteilung = abteilung;
	}
	
	public String getLohn() {
		return lohn;
	}

	public void setLohn(String lohn) {
		this.lohn = lohn;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((personalNr == null) ? 0 : personalNr.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (personalNr == null) {
			if (other.personalNr != null)
				return false;
		} else if (!personalNr.equals(other.personalNr))
			return false;
		return true;
	}
	
	
	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", personalNr="
				+ personalNr + ", vorname=" + vorname + "]";
	}

	

	/**
	 * 
	 * simple test
	 */
	public static void main(String[] args){ 
//		// create person transient object 
//		Person p = new Person("Mustermann", "Max", "p#0000001", null, null );
//		// load xml configuration
//		Configuration config = new Configuration().configure(); 
//		// Erzeuge Session Factory Objekt
//		SessionFactory hibernateSessionFactory = config.buildSessionFactory();  
//		// gibt Session objekt
//		Long personId = null;
//		Session session = hibernateSessionFactory.openSession();
//		Transaction trx = null;
//		try{
//			trx = session.beginTransaction();
//			// speichere Objekt
//			personId = (Long) session.save(p);
//			trx.commit();
//		}catch(HibernateException ex){
//			if(trx != null){
//				trx.rollback();
//			}
//			throw new RuntimeException( "Hibernate Problem!" ,ex);
//		}
//		session.close();
//		p = null;
//		// hole objekt aus der Datenbank
//		session = hibernateSessionFactory.openSession();
//		try{
//			trx = session.beginTransaction();
//			// speichere Objekt
//			p = (Person) session.load(Person.class, personId);
//			System.out.println(p);
//			trx.commit();
//		}catch(HibernateException ex){
//			if(trx != null){
//				trx.rollback();
//			}
//			throw new RuntimeException( "Hibernate Problem!" ,ex);
//		}
//		session.close();
		
		
		// oder 
		//Session session = hibernateSessionFactory.getCurrentSession();
	}
	
}
