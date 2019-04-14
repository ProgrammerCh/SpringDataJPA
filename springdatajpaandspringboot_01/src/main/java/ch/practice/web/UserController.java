package ch.practice.web;

import ch.practice.pojo.User;
import ch.practice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;


    @RequestMapping("/add/{name}")
    public User addUser(@PathVariable String name){
        User user = new User();
        user.setName(name);
        user.setCreated(new Date());
        user.setBirthday(new Date());
        user.setCreated(new Date());
        user.setUserName("zhangsanfeng");
        user.setAge(100);
        user.setSex(1);
        user.setPassword("123456");
        return userService.addUser(user);


    }

    @RequestMapping("/update/{name}")
    public User updateUser(@PathVariable String name){
        User user = userService.findUserByName(name);
        user.setUserName("lina");
        return userService.update(user);

    }
    @RequestMapping("find/{id}")
    public User findUserById(@PathVariable Long id){

        return userService.findUserById(id);
    }

    @RequestMapping("findUsers")
    public List<User> findUsers(){

        return userService.findUsers();
    }
    @RequestMapping("delete/{id}")
    public String  deleteUserById(@PathVariable Long id){
        int delete = userService.delete(id);
        if(delete==1){
            return "删除成功";
        }
        return "删除失败";

    }



}
