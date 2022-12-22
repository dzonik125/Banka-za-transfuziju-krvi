package group8.bloodbank.service.implementations;

import group8.bloodbank.model.Admin;
import group8.bloodbank.model.BloodBank;
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
import java.util.Optional;

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
        admin.setEnabled(true);
        admin.setRoles(roles);
        return adminRepository.save(admin);

    }

    @Override
    public Optional<Admin> getById(Long id) {
        return adminRepository.findById(id);
    }

    @Override
    public void updateAdminPassword(String password, Long id) {
        adminRepository.updateAdmin(id, passwordEncoder.encode(password));
    }


}
