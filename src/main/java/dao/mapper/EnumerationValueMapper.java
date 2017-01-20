package dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import domain.EnumerationValue;

public class EnumerationValueMapper implements IMapResultSetToEntity<EnumerationValue> {

	public EnumerationValue map(ResultSet rs) throws SQLException {
		EnumerationValue en = new EnumerationValue();
		en.setId(rs.getInt("id"));
		en.setIntKey(rs.getInt("intKey"));
		en.setStringKey(rs.getString("stringKey"));
		en.setValue(rs.getInt("value"));
		en.setEnumerationName(rs.getString("enumerationName"));
		return en;
	}

}
