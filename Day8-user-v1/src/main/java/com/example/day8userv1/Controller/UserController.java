package com.example.day8userv1.Controller;

import com.example.day8userv1.Dio.ApiRosponse;
import com.example.day8userv1.Model.User;
import com.example.day8userv1.Service.Userservice;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@RestController
public class UserController {
    private final Userservice userservice;
    @GetMapping("/get")
    public ResponseEntity getUser(){
        List<User> blogs= userservice.getUser();
        return ResponseEntity.status(200).body(blogs);
    }


    @PostMapping("/add")
    public ResponseEntity addUser(User user, Errors errors){
        if (errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body (message);
        }
        userservice.addUser(user);
        return ResponseEntity.status(200).body (new ApiRosponse("Add user !!"));
    }


    @PutMapping("/update")
    public ResponseEntity updateUser(@PathVariable @Valid Integer id, User user, Errors errors){
        if (errors.hasErrors()){

            return ResponseEntity.status(400).body (errors.getFieldError().getDefaultMessage());
        }
        userservice.updateUser(id,user);

        return ResponseEntity.status(200).body(new ApiRosponse(" user Updated !!"));

    }



    @DeleteMapping("/delete")
    public String deletUser (@PathVariable @Valid Integer id){
        userservice.deleteUser(id);
        return "user DELETED";}



    @GetMapping("/byi/{id}")
    public ResponseEntity  getUserById(@PathVariable Integer id )
    {
        User user = userservice.getUserById(id);
        return ResponseEntity.status(200).body(user);
    }



    @GetMapping("/getemail")
    public ResponseEntity getUserByEmail(@RequestBody String email)
    {
        User user =userservice.getUserByEmail(email);
        return ResponseEntity.status(200).body(user);
    }



    @GetMapping("/getp/{Password}")
    public ResponseEntity getUserByUsernameAndPassword(@RequestBody String  username  , @PathVariable Integer password )
    {
        User user = userservice.getUserByUsernameAndPassword(username,password);
        return ResponseEntity.status(200).body(user);
    }



    @GetMapping("/getRole")
    public ResponseEntity getAllByRole(@RequestBody String role)
    {
        List<User> user =userservice.getAllByRole(role);
        return ResponseEntity.status(200).body(user);

    }

    @GetMapping("/getAge")
    public ResponseEntity getUsersByAge(@RequestBody Integer age )
    {
        List<User> user =userservice.getUsersByAge(age);
        return ResponseEntity.status(200).body(user);
    }




}


