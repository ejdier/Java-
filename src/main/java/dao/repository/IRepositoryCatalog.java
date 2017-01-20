package dao.repository;

import java.sql.SQLException;

import dao.EnumerationValueRepository;
import dao.UserRepository;

public interface IRepositoryCatalog {

	EnumerationValueRepository enumeration();
	UserRepository users();
	void saveAndClose() throws SQLException ;
}
