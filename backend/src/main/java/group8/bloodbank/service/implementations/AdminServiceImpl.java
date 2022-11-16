package group8.bloodbank.service.implementations;

import group8.bloodbank.model.Address;
import group8.bloodbank.model.Admin;
import group8.bloodbank.model.Gender;
import group8.bloodbank.model.UserType;
import group8.bloodbank.repository.AdminRepository;
import group8.bloodbank.service.interfaces.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    AdminRepository adminRepository;

    @Autowired
    public AdminServiceImpl(AdminRepository adminRepository) {

        this.adminRepository = adminRepository;

//     public Admin(String name, String surname, String email, String password, String jmbg, Address address, String occupation, Gender gender) {
//            super(name, surname, email, password, jmbg, address, occupation, gender, UserType.MEDICAL_WORKER);

        Admin admin1 = new Admin("Slavica", "Savovic","slavica12@mail.com", "slava123", "123123123333", new Address("BiH", "Visegrad", "Andriceva", "11C"), " ", Gender.FEMALE);
        Admin admin2 = new Admin("Slavica", "Savovic","slavica12@mail.com", "slava123", "123123123333", new Address("BiH", "Visegrad", "Andriceva", "11C"), " ", Gender.FEMALE);
        adminRepository.save(admin1);
        adminRepository.save(admin2);

    }


    @Override
    public List<Admin> getAll() {
        return adminRepository.findAll();
    }
}
