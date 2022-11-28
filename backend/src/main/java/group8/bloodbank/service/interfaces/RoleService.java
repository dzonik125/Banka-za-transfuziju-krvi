package group8.bloodbank.service.interfaces;

import group8.bloodbank.model.Role;

import java.util.List;

public interface RoleService {
	Role findById(Long id);
	List<Role> findByName(String name);
}
