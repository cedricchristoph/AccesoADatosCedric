package es.iespuertodelacruz.cc.webappinstituto.model.daos;

import es.iespuertodelacruz.cc.webappinstituto.model.entities.Alumno;
import es.iespuertodelacruz.cc.webappinstituto.model.utils.MyDatabase;

public class AlumnoDAO extends DAO<Alumno>{

	public AlumnoDAO(MyDatabase database, String tableName) {
		super(database, tableName);
	}
	
	@Override
	public Alumno select(int id) {
		return null;
	}

	@Override
	public Alumno[] selectAll() {
		
		return null;
	}

	@Override
	public boolean insert(Alumno entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Alumno entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

}
