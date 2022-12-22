package group8.bloodbank.controller;

import group8.bloodbank.model.DTO.UserDTO;
import group8.bloodbank.model.User;
import group8.bloodbank.service.interfaces.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_DONOR')")
    public Boolean editUser(@PathVariable long id, @RequestBody UserDTO user) {
        User userRequest = modelMapper.map(user, User.class);
        return userService.updateUser(id, userRequest);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_DONOR', 'ROLE_MEDICALWORKER', 'ROLE_ADMIN')")
    public ResponseEntity<User> findById(@RequestParam Long id){
        try {
            User toReturn = userService.findById(id);
            return new ResponseEntity<User>(toReturn, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }


}