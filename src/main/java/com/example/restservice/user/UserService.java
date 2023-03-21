package com.example.restservice.user;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class UserService {
    private static List<User> users = new ArrayList<>();

    private static int usersCount = 3;

    //처음 클래스 로딩되자마자 static 부분 실행
    static {
        users.add(new User(1, "ssohee1", new Date()));
        users.add(new User(2, "ssohee22", new Date()));
        users.add(new User(3, "ssohee333", new Date()));
    }

    public List<User> findAll() {
        return users;
    }

    public User findOne(int id) {  //User의 integer id (wrapper class: 기본 오브젝트형)와 int id 다르지만
        for(User user : users) {  //users배열에서 user로 하나씩 받아와서
            if(user.getId() == id) {  //자동 언박싱(user.getId() integer -> int)
                return user;
            }
        }
        return null;
    }
}
