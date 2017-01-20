package domain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import dao.RepositoryCatalog;
import dao.repository.IRepositoryCatalog;
import dao.uow.IUnitOfWork;
import dao.uow.UnitOfWork;


public class App {

	public static void main(String[] args) {
	     
		
    	try{
    
    
     	
    	Connection connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/workdb");
		IUnitOfWork uow = new UnitOfWork(connection);
		IRepositoryCatalog catalogOf = new RepositoryCatalog(connection,uow);
		User user = new User();
	     user.setLogin("adrian");
	     user.setPassword("elejdiero");
	     
	     EnumerationValue enumeration = new EnumerationValue();
	     enumeration.setIntKey(1);
	     enumeration.setStringKey("ppp");
	     enumeration.setValue(213);
	     enumeration.setEnumerationName("changed");
		catalogOf.users().save(user);
		catalogOf.enumeration().save(enumeration);
		
		catalogOf.saveAndClose();
    	}catch(SQLException e){
    		
    		e.printStackTrace();
    	}
    	
    	}
	}


