package service;

import model.User;

public interface UserService {
    boolean saveUser(User user) throws Exception;
    boolean deleteUserById(Integer id) throws Exception;
    boolean updateUser(User user) throws Exception;
    User getUserById(Integer id) throws Exception;
    User getUserByName(String name) throws Exception;
}
