package service.impl;

import dao.UserMapper;
import model.User;
import org.springframework.stereotype.Service;
import service.UserService;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    UserMapper userMapper;

    @Override
    public boolean saveUser(User user) throws Exception {
        return userMapper.saveUser(user) == 1;
    }

    @Override
    public boolean deleteUserById(Integer id) throws Exception {
        return userMapper.deleteUserById(id) == 1;
    }

    @Override
    public boolean updateUser(User user) throws Exception {
        return userMapper.updateUser(user) == 1;
    }

    @Override
    public User getUserById(Integer id) throws Exception {
        return userMapper.getUserById(id);
    }

    @Override
    public User getUserByName(String name) throws Exception {
        return userMapper.getUserByName(name);
    }
}
