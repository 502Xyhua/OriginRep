package com.xyhua.controller;

import com.xyhua.bean.Student;
import com.xyhua.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.sql.*;

@SuppressWarnings("all")
@Controller
public class TestSpringController {

    @Value("${local.url}")
    String url;

    @Value("${local.user}")
    String user;

    @Value("${local.password}")
    String password;

    @Resource
    DataSource druidDataSource;

    @Resource
    StudentMapper studentMapper;


    @RequestMapping("/test")
    public String testSpringMvc(){
        return "test";
    }

    @RequestMapping("/testJdbc")
    @ResponseBody
    public String testJdbc() throws ClassNotFoundException, SQLException {
        String sql = "select * from student";
        Class.forName("oracle.jdbc.OracleDriver");
        Connection ct = DriverManager.getConnection(url, user, password);
        PreparedStatement ps = ct.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            String id = rs.getString("id");
            String name = rs.getString("name");
            int gender = rs.getInt("gender");
            Student student = new Student(id, name, gender);
            System.out.println(student);
        }
        ct.close();
        return "testJdbc";
    }




    @RequestMapping("/testDruid")
    public String testDruid() throws SQLException {
        String sql = "select * from student";
        Connection ct = druidDataSource.getConnection();
        PreparedStatement ps = ct.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            String id = rs.getString("id");
            String name = rs.getString("name");
            int gender = rs.getInt("gender");
            Student student = new Student(id, name, gender);
            System.out.println(student);
        }
        ct.close();
        return "testDruid";
    }
    @RequestMapping("/testMybatis")
    public String testMybatis(){
        Student student = studentMapper.getStudent("1");
        System.out.println(student);
        return "testMybatis";
    }


    @RequestMapping("/loginPage")
    public String loginPage(){
        return "loginPage";
    }

    @RequestMapping(value = "/testLogin", method = RequestMethod.POST)
    @ResponseBody
    public String testLogin(String userid, String username, int gender){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        String userid = request.getParameter("userid");
//        String username = request.getParameter("username");
//        String gender = request.getParameter("gender");
        studentMapper.insertOne(userid, username, gender);
        return "loginSuccess";
    }

}
