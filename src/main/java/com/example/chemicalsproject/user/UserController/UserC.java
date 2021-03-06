package com.example.chemicalsproject.user.UserController;

import com.example.chemicalsproject.order.util.LayUiData;
import com.example.chemicalsproject.pojo.User;
import com.example.chemicalsproject.user.UserService.UserS;
import com.example.chemicalsproject.user.util.UserListConditionUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(methods = {RequestMethod.PUT,RequestMethod.DELETE,RequestMethod.GET,RequestMethod.POST})
public class UserC {
    @Autowired
    private UserS userS;

    /**
     * 查询User
     * @return
     *//*
    @RequestMapping("/queryUser")
    public List<User> queryUser(){
        System.out.println("查询queryUser:"+userS.list());
        return userS.list();
    }
*/
    /**
     * 查询发票列表
     * @param user
     * @return
     */
    @RequestMapping("/queryUser")
    public LayUiData queryUser(@RequestBody UserListConditionUtil user){
        System.out.println("invoice=="+user);
        Integer page=user.getPage();
        Integer limit=user.getLimit();
        PageInfo<User> userPageInfo = userS.queryAll(page, limit, user);
        List<User> list = userPageInfo.getList();
        LayUiData layUiData = new LayUiData();
        layUiData.setCode(0);
        layUiData.setMsg("");
        layUiData.setCount(Integer.valueOf(String.valueOf(userPageInfo.getTotal())));
        layUiData.setData(list);
        return layUiData;
    }

    /**
     * 添加User
     * @return
     */
    @RequestMapping("/addUser")
    public boolean addUser(@RequestBody User user){
        System.out.println("添加addUser: "+user);
        if(user!=null){
            userS.save(user);
            return true;
        }
        return false;
    }

    /**
     * 修改User
     * @return
     */
    @RequestMapping("/updateUser")
    public boolean updateUser(@RequestBody User user){
        System.out.println("修改updateUser: "+user);
        return userS.updateById(user);
    }

    /**
     * 删除User
     * @param uid
     * @return
     */
    @RequestMapping("/deleteUser")
    public boolean deleteUser(Integer uid){
        System.out.println("删除deleteUser uid:"+uid);
        return userS.removeById(uid);
    }
}
