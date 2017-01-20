package dao.repository;

import domain.EnumerationValue;

public interface IEnumerationValueRepository extends IRepository<EnumerationValue> {
 
	public void withName(String name);
	public void withIntKey(int key,String name);
	public void withStringKey(String key,String name);
}
