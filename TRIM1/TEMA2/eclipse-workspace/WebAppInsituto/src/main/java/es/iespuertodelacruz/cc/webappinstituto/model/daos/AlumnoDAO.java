package es.iespuertodelacruz.cc.webappinstituto.model.daos;

import java.util.List;

import es.iespuertodelacruz.cc.webappinstituto.model.entities.Alumno;
import es.iespuertodelacruz.cc.webappinstituto.model.utils.MyDatabase;

public class AlumnoDAO implements ICRUD<Alumno, Integer>{

	MyDatabase db;
	
	public AlumnoDAO(MyDatabase db) {
		this.db = db;
	}
	
	public AlumnoDAO(String ddbb, String user, String pwd) {
		this.db = new MyDatabase(ddbb, user, pwd);
	}
	
	@Override
	public Alumno select(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Alumno> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Alumno insert(Alumno entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Alumno entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}


}
