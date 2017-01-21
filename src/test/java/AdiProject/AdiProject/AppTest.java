package AdiProject.AdiProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import dao.RepositoryCatalog;
import dao.repository.IRepositoryCatalog;
import dao.uow.IUnitOfWork;
import dao.uow.UnitOfWork;
import domain.EnumerationValue;
import domain.User;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
	public static void test() throws SQLException {
	     
		
    
    
     	
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
   
    	
    	
    	}}
