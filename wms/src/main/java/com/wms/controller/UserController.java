package com.wms.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.common.QueryPageParam;
import com.wms.common.Result;
import com.wms.entity.User;
import com.wms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.HashMap;

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

    // search(ambiguous query or match query)
    @PostMapping("/listP")
    public Result listP(@RequestBody User user){
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper();
        if(StringUtils.isNotBlank(user.getName())){
            lambdaQueryWrapper.like(User::getName, user.getName());
        }
        return Result.suc(userService.list(lambdaQueryWrapper));
    }

    @PostMapping("/listPage")
    public List<User> listPage(@RequestBody QueryPageParam query){
        HashMap param = query.getParam();
        String name = (String)param.get("name");
        System.out.println("name===" + (String)param.get("name"));
        //System.out.println("no===" + (String)param.get("no"));

        /*System.out.println("num===" + query.getPageNum());
        System.out.println("size===" + query.getPageSize());*/

       /* HashMap param = query.getParam();
        System.out.println("name===" + (String)param.get("name"));
        System.out.println("no===" + (String)param.get("no"));*/

        Page<User> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.like(User::getName,name);

        IPage result = userService.page(page,lambdaQueryWrapper);

        System.out.println("total==" + result.getTotal());

        return result.getRecords();
    }

    @PostMapping("/listPageC")
    public List<User> listPageC(@RequestBody QueryPageParam query){
        HashMap param = query.getParam();
        String name = (String)param.get("name");
        System.out.println("name===" + (String)param.get("name"));

        Page<User> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.like(User::getName,name);

        IPage result = userService.pageCC(page,lambdaQueryWrapper);

        System.out.println("total==" + result.getTotal());

        return result.getRecords();
    }

    @PostMapping("/listPageC1")
    public Result listPageC1(@RequestBody QueryPageParam query){
        HashMap param = query.getParam();
        String name = (String)param.get("name");
        System.out.println("name===" + (String)param.get("name"));

        Page<User> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.like(User::getName,name);

        IPage result = userService.pageCC(page,lambdaQueryWrapper);

        System.out.println("total==" + result.getTotal());

        return Result.suc(result.getRecords(),result.getTotal());
    }

}
