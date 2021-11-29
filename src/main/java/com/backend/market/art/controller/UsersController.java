package com.backend.market.art.controller;

import com.backend.market.art.model.Users;
import com.backend.market.art.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    UsersRepository usersRepository;

    @GetMapping("/")
    public ResponseEntity<List<Users>> getAllUsers(@RequestHeader("requestUID") String requestUID , @RequestHeader("sourceSystem") String sourceSystem){
        System.out.println(requestUID);
        System.out.println(sourceSystem);
        try{
            List<Users> Userss = new ArrayList<Users>();
            usersRepository.findAll().forEach(Userss :: add);

            if(Userss.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }else {
                return  new ResponseEntity<>(Userss,HttpStatus.OK);
            }
        }
        catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
