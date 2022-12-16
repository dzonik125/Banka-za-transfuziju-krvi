package group8.bloodbank.service.implementations;

import group8.bloodbank.model.BloodType;
import group8.bloodbank.model.Category;
import group8.bloodbank.model.DTO.UserDTO;
import group8.bloodbank.model.Donor;
import group8.bloodbank.model.Role;
import group8.bloodbank.repository.DonorRepository;
import group8.bloodbank.service.interfaces.DonorService;
import group8.bloodbank.service.interfaces.RoleService;
import group8.bloodbank.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DonorServiceImpl implements DonorService {
    @Autowired
    DonorRepository donorRepository;
    @Autowired
    RoleService roleService;

    @Autowired
    UserService userService;


    @Autowired
    public DonorServiceImpl() {
    }

    @Override
    public Donor saveDonor(UserDTO userDTO) {

        Donor d = new Donor();

        d = (Donor) userService.saveUser(d,userDTO);

        List<Role> roles = roleService.findByName("ROLE_DONOR");
        d.setRoles(roles);
        d.setPoints(0);
        d.setCategory(Category.REGULAR);
        d.setPenalty(0);
        d.setBloodType(BloodType.ABneg);
        //d.setComplaint(Collections.emptySet());
        //d.setSurvey(Collections.emptySet());
        d.setEnabled(true);
        d.setHasSurvey(false);

        return this.donorRepository.save(d);
    }



    @Override
    public List<Donor> getAll() {
        return donorRepository.findAll();
    }


}
