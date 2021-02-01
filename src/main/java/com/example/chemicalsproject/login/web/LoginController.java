package com.example.chemicalsproject.login.web;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.example.chemicalsproject.login.service.LoginService;
import com.example.chemicalsproject.login.util.LoadUtil;
import com.example.chemicalsproject.pojo.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@CrossOrigin
@RestController
public class LoginController {

    @Autowired
    private LoginService userService;

    @RequestMapping("/login")
    public boolean login(@RequestBody User user){
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("user_name",user.getUsername());
        User one = userService.getOne(queryWrapper);
        if(one!=null){//有这个账号
//            queryWrapper.eq()
            userService.getOne(queryWrapper);
            return true;
        }
        return false;
    }

    /**
     * 登录
     * @return
     */
//    @RequiresRoles(value = {"manager","经理","职员","用户"},logical = Logical.AND)
//    @RequiresPermissions(value = {"user:load"},logical = Logical.OR)
    @RequestMapping("/load")
    public R load(String username, String password){//@RequestBody User user
        UsernamePasswordToken token=new UsernamePasswordToken(username,password);
        System.out.println("token==="+token.getPassword()+":"+token.getUsername());
        Subject subject = SecurityUtils.getSubject();
        LoadUtil util=new LoadUtil();
        try {
            subject.login(token);
        }catch (UnknownAccountException e){
            System.out.println("账号不存在");
            e.printStackTrace();
            return R.failed(e.getMessage());
        }catch (AuthenticationException e) {
            System.out.println("账号密码不存在");
            e.printStackTrace();
            return R.failed("账号密码不存在");
        }
        Session session = SecurityUtils.getSubject().getSession();
        session.setAttribute("username",token);
        System.out.println("认证成功");
        return R.ok(new LoadUtil(username,password,username,20000));
    }

    /**
     * getUserInfo
     * @return
     */
//    @RequiresRoles(value = {"manager","经理","职员","用户"},logical = Logical.AND)
//    @RequiresPermissions(value = {"user:load"},logical = Logical.OR)
    @RequestMapping("/getUserInfo")
    public R getUserInfo(String token){
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("user_name",token);
        User one = userService.getOne(queryWrapper);
        if(one!=null){
            Integer id=one.getUid();
            String username=one.getUsername();
            String password=one.getPassword();
            Integer rules=one.getType();
            return R.ok(new LoadUtil(id,username,password,username,20000,rules));
        }
        R r=new R();
        r.setCode(50008);
        r.setMsg("Login failed, unable to get user details.");
        return r;
    }

    @RequestMapping("/queryAllName")
    public List<String> queryAllName(){
        List<User> list = userService.list();
        List<String> names=new ArrayList<>();
        for (User user:list){
            names.add(user.getUsername());
        }
        return names;
    }

}
