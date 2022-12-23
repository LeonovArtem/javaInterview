package al.spring.service;

import al.spring.model.User;

import java.util.List;

public interface UserService {
    List<User> list();

    User findById(Integer id);
}
