package group8.bloodbank.service.interfaces;

import group8.bloodbank.model.Admin;
import group8.bloodbank.model.BloodBank;

import java.util.List;
import java.util.Optional;

public interface AdminService {
    public List<Admin> getAll();

    Admin create(Admin admin);

    Optional<Admin> getById(Long id);

    void updateAdminPassword(String password, Long id);
}
