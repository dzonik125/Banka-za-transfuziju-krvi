package group8.bloodbank.service.interfaces;

import group8.bloodbank.model.User;

import java.util.NoSuchElementException;

public interface UserService {
    User saveUser(User user);

    User findById(Long id) throws NoSuchElementException;
}
