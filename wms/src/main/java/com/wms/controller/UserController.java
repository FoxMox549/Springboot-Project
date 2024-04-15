package com.wms.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wms.entity.User;
import com.wms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  Front-end controller
 * </p>
 *
 * @author wms
 * @since 2024-04-14
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public List<User> list(){
        return userService.list();
    }

    // add
    @PostMapping("/save")
    public boolean save(@RequestBody User user){
        return userService.save(user);
    }

    // modify
    @PostMapping("/mod")
    public boolean mod(@RequestBody User user){
        return userService.updateById(user);
    }

    // add or modify
    @PostMapping("/saveOrMod")
    public boolean saveOrMod(@RequestBody User user){
        return userService.saveOrUpdate(user);
    }

    // delete
    @GetMapping("/delete")
    public boolean delete(Integer id){
        return userService.removeById(id);
    }

    // search(ambiguity or match)
    @PostMapping("/listP")
    public List<User> listP(@RequestBody User user){
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.like(User::getName, user.getName());
        return userService.list(lambdaQueryWrapper);
    }

}
