package group8.bloodbank.controller;

import group8.bloodbank.model.DTO.UserDTO;
import group8.bloodbank.model.User;
import group8.bloodbank.service.interfaces.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = "http://localhost:4201")
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


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> saveUser(@RequestBody User user)  {
        User savedUser = null;
        try {
            savedUser = userService.saveUser(user);
            return new ResponseEntity<User>(savedUser, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<User>(savedUser, HttpStatus.CONFLICT);
        }
    }


    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Boolean editUser(@PathVariable long id, @RequestBody UserDTO user) {
        User userRequest = modelMapper.map(user, User.class);
        return userService.updateUser(id, userRequest);
    }


    @GetMapping
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