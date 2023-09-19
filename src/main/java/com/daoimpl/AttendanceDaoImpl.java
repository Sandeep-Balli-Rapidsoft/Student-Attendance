package com.daoimpl;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dao.AttendanceDao;
import com.entity.Attendance;

@Repository
@Transactional
public class AttendanceDaoImpl implements AttendanceDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Attendance save(Attendance attendance) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(attendance);
		return attendance;
	}

	@Override
	public List<Attendance> getAll() {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Attendance.class);
		return criteria.list();
	}
	
	public List<Attendance> getWeekData(Date fromDate, Date toDate) {
		
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Attendance.class);
		criteria.add(Restrictions.between("day", fromDate, toDate));
		return criteria.list();
	}

}
