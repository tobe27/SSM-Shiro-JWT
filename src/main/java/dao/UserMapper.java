package dao;

import model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    int saveUser(User user);
    int deleteUserById(Integer id);
    int updateUser(User user);
    User getUserById(Integer id);
    User getUserByName(String name);
}
