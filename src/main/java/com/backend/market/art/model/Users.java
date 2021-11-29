package com.backend.market.art.model;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  long id;

    @Column(name = "username")
    private  String username;

    @Column(name = "password")
    private  String password;

    @Column(name = "name")
    private String name;

    @Column(name = "phone")
    private  String phone;

    public Users(){

    }

    public Users(String username,String password ,String name , String phone){
        this.username = username;
        this.password = password;
        this.name = name;
        this.phone = phone;
    }

    public long getId(){
        return  this.id;
    }

    public String getUsername(){
        return  this.username;
    }

    public  String getPassword(){
        return this.password;
    }

    public  String getName(){
        return  this.name;
    }

    public  String getPhone(){
        return  this.phone;
    }

    public  void setUsername(String username){
        this.username = username;
    }

    public  void setPassword(String password){
        this.password = password;
    }

    public  void setName(String name){
        this.name = name;
    }

    public  void  setPhone(String phone){
        this.phone = phone;
    }

    @Override
    public String toString(){
        return  "Users [id="+id+",username="+username+",password="+password+",name="+name+",phone="+phone+"]";
    }
}
