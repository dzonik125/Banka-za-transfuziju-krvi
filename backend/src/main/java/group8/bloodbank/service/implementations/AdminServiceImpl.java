package group8.bloodbank.service.implementations;

import group8.bloodbank.model.Admin;
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
    }


    @Override
    public List<Admin> getAll() {
        return adminRepository.findAll();
    }
}
