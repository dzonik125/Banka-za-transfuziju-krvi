package group8.bloodbank.service.implementations;

import group8.bloodbank.model.Admin;
import group8.bloodbank.model.Role;
import group8.bloodbank.repository.AdminRepository;
import group8.bloodbank.service.interfaces.AdminService;
import group8.bloodbank.service.interfaces.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    AdminRepository adminRepository;

    @Autowired
    RoleService roleService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AdminServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;

    }


    @Override
    public List<Admin> getAll() {
        return adminRepository.findAll();
    }

    @Override
    public Admin create(Admin admin) {
        List<Role> roles = roleService.findByName("ROLE_ADMIN");
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        admin.setLastPasswordResetDate(Timestamp.valueOf(LocalDateTime.now()));

        admin.setRoles(roles);
        return adminRepository.save(admin);

    }
}
