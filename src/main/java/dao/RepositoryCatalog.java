package dao;

import java.sql.Connection;
import java.sql.SQLException;

import dao.mapper.EnumerationValueMapper;
import dao.mapper.UserMapper;
import dao.repository.IRepositoryCatalog;
import dao.uow.IUnitOfWork;


public class RepositoryCatalog implements IRepositoryCatalog{
	

	IUnitOfWork uow;
	Connection connection;
	
	public RepositoryCatalog(Connection connection,IUnitOfWork uow)
	{
		this.connection = connection;
		this.uow = uow;
	}


	public void saveAndClose() throws SQLException {
		uow.saveChange();
		connection.close();
		connection=null;
		
	}

	public EnumerationValueRepository enumeration() {
		return new EnumerationValueRepository(uow,new EnumerationValueMapper(), connection);
	}

	public UserRepository users() {
		return new UserRepository(uow, new UserMapper(), connection);
	}

		
	
}
