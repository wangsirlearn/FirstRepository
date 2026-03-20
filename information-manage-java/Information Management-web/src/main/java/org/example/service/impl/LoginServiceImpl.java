package org.example.service.impl;

import org.example.Utils.JWTUtils;
import org.example.mapper.LoginMapper;
import org.example.popj.Emp;
import org.example.popj.Login;
import org.example.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private LoginMapper loginMapper;

    @Override
    public Login login(String username, String password) {
        Emp emp= loginMapper.login(username,password);
        if (emp==null){
            return null;
        } else {
            Map<String, Object> claims=new HashMap<>();
            claims.put("id",emp.getId());
            //生成JWT令牌
            String jwt= JWTUtils.generateJwt(claims);
            return new Login(emp.getUsername(),emp.getId(),emp.getName(),jwt);
        }
    }
}
