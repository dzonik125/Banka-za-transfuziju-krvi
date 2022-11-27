package group8.bloodbank.service.implementations;

import group8.bloodbank.model.User;
import group8.bloodbank.repository.UserRepository;
import group8.bloodbank.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {

        this.userRepository = userRepository;
//        Address a1 = new Address("Srbija", "Kraljevo", "Cirpanova", "12");
//        Address a2 = new Address("Srbija", "Lebane", "Titova", "123");
//
//        User u1 = new User("Nikola", "Kolarov", "dzonik125@gmail.com", "123456", "1234578965496", a1, "menager", Gender.MALE, UserType.DONOR);
//        User u2 = new User("Petar", "Petrovic", "gasolina@gmail.com", "123", "1234578965496", a2, "f1 driver", Gender.MALE, UserType.MEDICAL_WORKER);
//        userRepository.save(u1);
//        userRepository.save(u2);
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
}
