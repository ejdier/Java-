package dao;

import java.sql.Connection;
import java.sql.SQLException;

import dao.mapper.IMapResultSetToEntity;
import dao.repository.IUserRepository;
import dao.uow.IUnitOfWork;
import domain.User;


public class UserRepository extends Repository<User> implements IUserRepository{

	public UserRepository(IUnitOfWork uow, IMapResultSetToEntity<User> mapper, Connection connection) {
		super(uow, mapper, connection);
		
	}

	public void withLogin(String login) {
	
		
	}

	public void withLoginAndPassword(String login, String password) {
	
		
	}

	public void setupPermission(User user) {
		
		
	}

	@Override
	protected String getTableName() {
		return "user";
	}

	@Override
	protected String insertSql() {
		return "INSERT INTO user(login,password) VALUES (?,?)";
	}

	@Override
	protected String updateSql() {
		return "UPDATE user SET login = ?, password = ? WHERE id = ?";
	}

	@Override
	protected String createTableSql() {
		return "CREATE TABLE user("
				+ "id bigint GENERATED BY DEFAULT AS IDENTITY,"
				+ "login VARCHAR(20)," + "password VARCHAR(50)"
				+ ")";
	}

	@Override
	protected void setUpdate(User entity) throws SQLException { 
	   update.setString(1, entity.getLogin());
	   update.setString(2, entity.getPassword());
	}

	@Override
	protected void setInsert(User entity) throws SQLException {
		insert.setString(1, entity.getLogin());
		insert.setString(2, entity.getPassword());
		
	}


}
