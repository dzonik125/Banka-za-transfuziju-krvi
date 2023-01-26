package group8.bloodbank.service.implementations;

import group8.bloodbank.model.DTO.UserDTO;
import group8.bloodbank.model.User;
import group8.bloodbank.repository.UserRepository;
import group8.bloodbank.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User saveUser(User user, UserDTO userDTO) {
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setName(userDTO.getName());
        user.setSurname(userDTO.getSurname());
        user.setEmail(userDTO.getEmail());
        user.setGender(userDTO.getGender());
        user.setAddress(userDTO.getAddress());
        user.setJmbg(userDTO.getJmbg());
        user.setOccupation(userDTO.getOccupation());
        user.setLastPasswordResetDate(Timestamp.valueOf(LocalDateTime.now()));
        
        return user;
    }

    @Override
    public User findById(Long id) throws NoSuchElementException {
        return userRepository.findById(id).get();
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
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

    @Override
    public User findByUsername(String email) {
        return userRepository.findByEmail(email);
    }

}
