package com.example.chemicalsproject.login.web;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.example.chemicalsproject.login.service.LoginService;
import com.example.chemicalsproject.login.util.LoadUtil;
import com.example.chemicalsproject.login.vo.Banner;
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

    /**
     * 获取轮播图list
     * @return
     */
    @RequestMapping("/bannerList")
    public List<Banner> bannerList(){
        System.out.println("走bannerList");
        List<Banner> list=new ArrayList<>();
        list.add(new Banner(
                "商品管理",
                "https://guli-entrepot.oss-cn-beijing.aliyuncs.com/avatar/2021/03/%E5%95%86%E5%93%81.png",
                "/#/commodity"));
        list.add(new Banner(
                "发票管理",
                "https://guli-entrepot.oss-cn-beijing.aliyuncs.com/avatar/2021/03/%E5%8F%91%E7%A5%A8.png",
                "/#/invoice"));
        list.add(new Banner(
                "库存管理",
                "https://guli-entrepot.oss-cn-beijing.aliyuncs.com/avatar/2021/03/%E5%BA%93%E5%AD%98.png",
                "/#/inventory"));
        list.add(new Banner(
                "供应商管理",
                "https://guli-entrepot.oss-cn-beijing.aliyuncs.com/avatar/2021/03/%E4%BE%9B%E8%B4%A7%E5%95%86.png",
                "/#/supplier"));
        list.add(new Banner(
                "订单管理",
                "https://guli-entrepot.oss-cn-beijing.aliyuncs.com/avatar/2021/03/%E8%AE%A2%E5%8D%95.png",
                "/#/order"));
        list.add(new Banner(
                "采购管理",
                "https://guli-entrepot.oss-cn-beijing.aliyuncs.com/avatar/2021/03/%E9%87%87%E8%B4%AD.png",
                "/#/purchase"));
        list.add(new Banner(
                "业务员管理",
                "https://guli-entrepot.oss-cn-beijing.aliyuncs.com/avatar/2021/03/%E4%B8%9A%E5%8A%A1%E5%91%98.png",
                "/#/user"));
        list.add(new Banner(
                "退货管理",
                "https://guli-entrepot.oss-cn-beijing.aliyuncs.com/avatar/2021/03/%E9%80%80%E8%B4%A7.png",
                "/#/sales"));
        return list;
    }

}
