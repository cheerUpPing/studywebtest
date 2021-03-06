package com.elon.studydemo.controller;

import com.elon.studydemo.entity.MsgObj;
import com.elon.studydemo.entity.Person;
import com.elon.studydemo.entity.User;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

@Controller
public class LoginController {

    @RequestMapping("/login")
    @ResponseBody
    public MsgObj login(){
        return new MsgObj();
    }

    @PostMapping("/logintest")
    @ResponseBody
    public MsgObj login(HttpServletRequest request, @RequestHeader("requestHead") String requestHead, String userName, String password){
        //servletPath:[/logintest] contextPath:[/studydemo] requestHead:[abc]
        String url = request.getRequestURL().toString();
        String uri = request.getRequestURI();
        String serverName = request.getServerName();
        String serverPort = request.getServerPort() + "";
        String serverContext = request.getServletContext().getServerInfo();
        boolean secure = request.isSecure();
        String protcol = secure ? "https://" : "http://";
        String result = "servletPath:[" + request.getServletPath() + "] contextPath:[" + request.getContextPath()
                + "] requestHead:[" + requestHead + "] userName:[" + userName + "] password:" + password;
        System.out.println(result);
        if ("1".equals(requestHead)){
            throw new RuntimeException("头部不能为1");
        }
        return new MsgObj(result);
    }

    @RequestMapping(value = "/login/{name}/{pwd}/r")
    @ResponseBody
    public MsgObj login(@PathVariable String name,@PathVariable String pwd){
        String path = name + "/" + pwd;
        File file = new File(path);
        System.out.println(path);
        return new MsgObj();
    }

    public static void main(String[] args) {
        User user1 = new User("小明", 1);
        User user2 = new User("小明", 2);
        int ha1 = user1.hashCode();
        int ha2 = user2.hashCode();
        System.out.println(user1 == user2);
        System.out.println(user1.equals(user2));

        String sa = new String("小明");
        String sb = new String("小明");
        int hasa = sa.hashCode();
        int hasb = sb.hashCode();
        System.out.println(sa == sb);
        System.out.println(sa.equals(sb));

        Person person1 = new Person(1, "小明");
        Person person2 = new Person(1, "小明");
        int hap1 = person1.hashCode();
        int hap2 = person2.hashCode();
        System.out.println(person1 == person2);
        System.out.println(person1.equals(person2));
        System.out.println("============================");
    }

}
