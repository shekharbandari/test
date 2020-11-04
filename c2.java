package com.stackroute.keepnote.dao;
import java.util.List;
import javax.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.stackroute.keepnote.model.User;

@Repository
@Transactional
public class UserDAOImpl implements UserDAO {
	
	@Autowired
	SessionFactory sessionFactory;
	
	public UserDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory=sessionFactory;
	}

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public boolean save(User user) {
		getSession().save(user);
		return true;
	}

	@Override
	public boolean delete(User user) {
		getSession().delete(user);
		return true;
	}

	@Override
	public List<User> getAllUsers() {
		return getSession().createQuery("from User").list();
	}

	@Override
	public boolean updateUser(User user) {
		getSession().update(user);
		return true;
	}

	@Override
	public boolean validate(String username, String password) {

		User user = (User) getSession().createQuery("from User where username = ? and password =?")
						.setString(0,username)
						.setString(1,password)
						.uniqueResult();
		if(user != null) {
			return true;
		}
		
		return false;
	}
	

	

}
