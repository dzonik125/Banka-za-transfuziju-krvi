package group8.bloodbank.service.implementations;

import group8.bloodbank.model.User;
import group8.bloodbank.repository.UserRepository;
import group8.bloodbank.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findById(Long id) throws NoSuchElementException {
        return userRepository.findById(id).get();
    }
}
