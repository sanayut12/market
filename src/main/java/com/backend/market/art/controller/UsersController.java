package com.backend.market.art.controller;

import com.backend.market.art.model.Users;
import com.backend.market.art.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    UsersRepository usersRepository;

    // /users
    @GetMapping("")
    public ResponseEntity<List<Users>> getAllUsers(
            @RequestHeader("requestUID") String requestUID ,
            @RequestHeader("sourceSystem") String sourceSystem,
            @RequestHeader("resourceOwnerID") String resourceOwnerID
            ){
        System.out.println("requestUID : "+requestUID +" // sourceSystem : " + sourceSystem +" // resourceOwnerID : " + resourceOwnerID);

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
    //   /users/id เช่น /1
    @GetMapping("/{id}")
    public ResponseEntity<Users> getUsersById(
            @RequestHeader("requestUID") String requestUID ,
            @RequestHeader("sourceSystem") String sourceSystem,
            @RequestHeader("resourceOwnerID") String resourceOwnerID,
            @PathVariable("id") long id)
    {
        System.out.println("requestUID : "+requestUID +" // sourceSystem : " + sourceSystem +" // resourceOwnerID : " + resourceOwnerID);
        Optional<Users> UsersData = usersRepository.findById(id);
        if (UsersData.isPresent()){
            return new ResponseEntity<>(UsersData.get(),HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }
    // /users
    @PostMapping("")
    public ResponseEntity<Users> createUsers(
            @RequestHeader("requestUID") String requestUID ,
            @RequestHeader("sourceSystem") String sourceSystem,
            @RequestHeader("resourceOwnerID") String resourceOwnerID,
            @RequestBody Users users
    ){
        System.out.println("requestUID : "+requestUID +" // sourceSystem : " + sourceSystem +" // resourceOwnerID : " + resourceOwnerID);
        try {
            Users _users = usersRepository.save(
                    new Users(
                            users.getUsername(),
                            users.getPassword(),
                            users.getName(),
                            users.getPhone()
                            )
            );
            System.out.println(_users.toString());
            return new ResponseEntity<>(_users , HttpStatus.CREATED);
        }catch (Exception e){
            return  new ResponseEntity<>(null , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Users> changePassword(
            @RequestHeader("requestUID") String requestUID ,
            @RequestHeader("sourceSystem") String sourceSystem,
            @RequestHeader("resourceOwnerID") String resourceOwnerID,
            @PathVariable("id") long id,
            @RequestBody Users users
    ){
        System.out.println("requestUID : "+requestUID +" // sourceSystem : " + sourceSystem +" // resourceOwnerID : " + resourceOwnerID);
        Optional<Users> usersData = usersRepository.findById(id);
        System.out.println( "id : "+id+" phone : "+users.getPhone());
        if (usersData.isPresent()){
            Users _users = usersData.get();

            _users.setPhone(users.getPhone());

            return  new ResponseEntity<>(usersRepository.save(_users),HttpStatus.OK);
        }else {
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteUsers(
            @RequestHeader("requestUID") String requestUID ,
            @RequestHeader("sourceSystem") String sourceSystem,
            @RequestHeader("resourceOwnerID") String resourceOwnerID,
            @PathVariable("id") long id
    ){
        System.out.println("requestUID : "+requestUID +" // sourceSystem : " + sourceSystem +" // resourceOwnerID : " + resourceOwnerID);
        try{
            usersRepository.deleteById(id);
            return  new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
