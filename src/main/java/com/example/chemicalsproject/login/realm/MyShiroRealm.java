package com.example.chemicalsproject.login.realm;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.chemicalsproject.login.service.LoginService;
import com.example.chemicalsproject.pojo.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

public class MyShiroRealm extends AuthorizingRealm {

    private LoginService userService;

    public void setUserService(LoginService userService) {
        this.userService = userService;
    }

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取username
        Object principal = principalCollection.getPrimaryPrincipal();

        //查询角色
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("user_name",principal);
        User user = userService.getOne(queryWrapper);
        String role = user.getType()==0?"管理员":"业务员";

        SimpleAuthorizationInfo simpleAuthorizationInfo=new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRole(role);
        return simpleAuthorizationInfo;
    }

    /**
     * 认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //将AuthenticationToken转为UsernamePasswordToken
        UsernamePasswordToken token= (UsernamePasswordToken) authenticationToken;

        System.out.println("realm token==="+token.getUsername()+token.getPassword());
        //获取username，从数据库查询是否存在
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("user_name", token.getUsername());
        User user = userService.getOne(queryWrapper);
        System.out.println("Employee==="+user);

        //username不存在，抛出异常
        if(user==null){
            throw new UnknownAccountException();
        }

        ByteSource byteSource = ByteSource.Util.bytes(token.getUsername());

        //username存在，查询密码是否对应
        //创建AuthenticationInfo对象，返回
        SimpleAuthenticationInfo authent=new SimpleAuthenticationInfo(token.getUsername(),user.getPassword(),byteSource,getName());

        return authent;
    }

}
