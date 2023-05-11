package ru.shilmax.PP_3_1_2_CRUD_springmvc_boot.dao;


import ru.shilmax.PP_3_1_2_CRUD_springmvc_boot.model.User;

import java.util.List;

public interface UserDao {
    void add(User user);

    void update(User user);

    void deleteById(Long id);

    User findById(Long id);

    Long findByEmail(String email);

    List<User> listUsers();
}
