package com.example.restservice.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


@Service
public class UserService {
    private static List<User> users = new ArrayList<>();
//
//    private static int usersCount = 3;
//
//    //처음 클래스 로딩되자마자 static 부분 실행
//    static {
//        users.add(new User(1, "ssohee1", new Date(), "pass1", "701010-111111"));
//        users.add(new User(2, "ssohee22", new Date(), "pass2", "801010-222222"));
//        users.add(new User(3, "ssohee333", new Date(), "pass3", "901010-333333"));
//    }

    @Autowired
    private UserMapper userMapper;

    //user 목록 모두 출력
    public List<User> findAll() {
        return userMapper.findAllUsers();
    }

    //id가 같은 user 한명 출력
    public User findUser(int id) {  //User의 integer id (wrapper class: 기본 오브젝트형)와 int id 다르지만
        return userMapper.findUser(id);
    }

    //user에 입력값 저장
    public void saveUser(User user) {
        userMapper.createUser(user);
    }

    //user에서 삭제
    public void deleteUser(int id) {
        userMapper.deleteUser(id);
    }

    public void savePost(Post post) {
        userMapper.createPost(post);
    }
    public List<Post> findAllPost(int id) {
        return userMapper.findAllPost(id);
    }
}
