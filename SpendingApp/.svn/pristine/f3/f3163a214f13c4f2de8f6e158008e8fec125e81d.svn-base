package de.application.hibernate.hibernateUserTypes;

import java.io.Serializable;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.usertype.UserType;

import de.application.spending.ActivityEnum;

public class StringToActivity implements UserType {

	@Override
	public Object assemble(Serializable arg0, Object arg1) throws HibernateException {
		return arg0;
	}

	@Override
	public Object deepCopy(Object arg0) throws HibernateException {
		return arg0;
	}

	@Override
	public Serializable disassemble(Object arg0) throws HibernateException {
		return (Serializable)arg0;
	}

	@Override
	public boolean equals(Object arg0, Object arg1) throws HibernateException {
		if (arg0 == null || arg1 == null)
			return false;
		else
			return arg0.equals(arg1);
	}

	@Override
	public int hashCode(Object arg0) throws HibernateException {
		assert(arg0 != null);
		return arg0.hashCode();
	}

	@Override
	public boolean isMutable() {
		return true;
	}

	@Override
	public Object nullSafeGet(ResultSet rs, String[] names, SessionImplementor arg2, Object arg3)
			throws HibernateException, SQLException {
		
		return ActivityEnum.getType(rs.getString(names[0]));
	}

	@Override
	public void nullSafeSet(PreparedStatement st, Object value, int index, SessionImplementor arg3)
			throws HibernateException, SQLException {
		String activity = null;
				
		if (value != null)
			activity = value.toString(); 
		
		st.setString(index, activity);
	}

	@Override
	public Object replace(Object arg0, Object arg1, Object arg2) throws HibernateException {
		return arg0;
	}

	@Override
	public Class returnedClass() {
		return ActivityEnum.class;
	}

	@Override
	public int[] sqlTypes() {
		return new int []{Types.VARCHAR};
	}

}
