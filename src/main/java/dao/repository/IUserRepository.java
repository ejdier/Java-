package dao.repository;

import domain.User;

public interface IUserRepository extends IRepository<User> {

	public void withLogin(String login);
	public void withLoginAndPassword(String login,String password);
}
