package group8.bloodbank.service.implementations;
import group8.bloodbank.model.User;
import group8.bloodbank.repository.UserRepository;
import group8.bloodbank.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.NoSuchElementException;
import java.util.Optional;


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

    @Override
    public Boolean updateUser(Long id, User user) {
        var u = userRepository.findById(id).get();
        if(u != null) {
            u.setName(user.getName());
            u.setSurname(user.getSurname());
            u.setPassword(user.getPassword());
            u.setAddress(user.getAddress());
            u.setJmbg(user.getJmbg());
            u.setOccupation(user.getOccupation());
            u.setGender(user.getGender());
            userRepository.save(u);
            return true;
        } else {
            return false;
        }
    }
}
