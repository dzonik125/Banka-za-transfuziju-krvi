package group8.bloodbank.controller;

import group8.bloodbank.model.Admin;
import group8.bloodbank.model.BloodBank;
import group8.bloodbank.model.DTO.AdministratorDTO;
import group8.bloodbank.model.DTO.ChangePasswordDTO;
import group8.bloodbank.model.DTO.MedicalWorkerDTO;
import group8.bloodbank.model.Gender;
import group8.bloodbank.model.MedicalWorker;
import group8.bloodbank.repository.AdminRepository;
import group8.bloodbank.service.interfaces.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4201")
@RestController
@RequestMapping("/admin")
public class AdminController {

    private AdminService adminService;
    private final AdminRepository adminRepository;

    @Autowired
    public AdminController(AdminService adminService,
                           AdminRepository adminRepository) {
        this.adminService = adminService;
        this.adminRepository = adminRepository;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Admin> getAll() {
        return adminService.getAll();
    }


    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, value = "/createAdmin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Admin> createAdmin(@RequestBody AdministratorDTO administratorDTO) {

        Admin admin = new Admin(administratorDTO.firstLogin, administratorDTO.getName(), administratorDTO.getSurname(), administratorDTO.getEmail(), administratorDTO.getPassword(), administratorDTO.getJmbg(), administratorDTO.getAddress(), administratorDTO.getOccupation(), administratorDTO.getGender());
        try {
            admin = adminService.create(admin);
            return new ResponseEntity<Admin>(admin, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Admin>(admin, HttpStatus.CONFLICT);
        }
    }

    @GetMapping(value = "/getById")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Optional<Admin> getById(@RequestParam(value = "id") Long id) {
        return adminService.getById(id);
    }


    @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, value ="updatePassword")
    public ResponseEntity<Boolean> updatePassword(@RequestBody ChangePasswordDTO dto) {
        try {
            adminService.updateAdminPassword(dto.password, dto.adminID);
            return new ResponseEntity<Boolean>(true, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Boolean>(false, HttpStatus.CONFLICT);
        }
    }

}