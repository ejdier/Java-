package dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import domain.User;

public class UserMapper implements IMapResultSetToEntity<User>{

	public User map(ResultSet rs) throws SQLException {
		User user = new User();
		user.setId(rs.getInt("id"));
		user.setLogin(rs.getString("login"));
		user.setPassword(rs.getString("password"));
		return user;
	}

}
