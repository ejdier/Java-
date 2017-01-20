package dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import domain.Role;

public class RoleMapper implements IMapResultSetToEntity<Role>{

	public Role map(ResultSet rs) throws SQLException {
		Role role = new Role();
		role.setId(rs.getInt("id"));
		role.setName(rs.getString("name"));
		return role;
	}

}
