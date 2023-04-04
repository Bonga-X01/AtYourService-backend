package com.nkosicode.serviceProv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/app/v1/user")
public class MainController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping(path = "/register")
    public @ResponseBody String addNewUser (
            @RequestParam(value = "firstName") String firstName,
            @RequestParam(value = "lastName") String lastName,
            @RequestParam(value = "email") String email,
            @RequestParam(value = "password") String password,
            @RequestParam(value = "phoneNumber") String phoneNumber,
            @RequestParam(value = "serviceType") String serviceType,
            @RequestParam(value = "city") String city,
            @RequestParam(value = "imageUrl") String imageUrl
            ){
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(password);
        user.setPhoneNumber(phoneNumber);
        user.setServiceType(serviceType);
        user.setCity(city);
        user.setImageUrl(imageUrl);

        userRepository.save(user);

        return " \" valid \" ";

    }
    @GetMapping(path="/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        // This returns a JSON or XML with the users
        return userRepository.findAll();
    }

    @GetMapping(path="/login")
    public @ResponseBody ResponseEntity<?> getUsers(
            @RequestParam(value = "phoneNumber") String phoneNumber,
            @RequestParam(value = "password") String password
    ) {

        if(!userRepository.findByPhoneNumber(phoneNumber).isEmpty()) {
            User user = userRepository.findByPhoneNumber(phoneNumber).get(0);
            if(password.equals(user.getPassword())) {
                return ResponseEntity.ok(user);
            }
            else {
                return ResponseEntity.status(400).build();
            }
        }
        else {
            return ResponseEntity.status(404).build();
        }
    }
}
