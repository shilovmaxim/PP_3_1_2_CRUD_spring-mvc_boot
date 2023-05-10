package ru.shilmax.PP_3_1_2_CRUD_springmvc_boot.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.shilmax.PP_3_1_2_CRUD_springmvc_boot.models.User;
import ru.shilmax.PP_3_1_2_CRUD_springmvc_boot.dao.UserDao;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserServiceImp implements UserService {

    private final UserDao userDao;

    public UserServiceImp(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional
    @Override
    public void add(User user) {
        userDao.add(user);
    }

    @Transactional
    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        userDao.deleteById(id);
    }

    @Override
    public User findById(Long id) {
        return userDao.findById(id);
    }

    @Override
    public Long findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public List<User> listUsers() {
        return userDao.listUsers();
    }

}
