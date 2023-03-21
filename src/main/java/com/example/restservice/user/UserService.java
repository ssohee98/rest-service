package com.example.restservice.user;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
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

    //user 목록 모두 출력
    public List<User> findAll() {
        return users;
    }

    //id가 같은 user 한명 출력
    public User findOne(int id) {  //User의 integer id (wrapper class: 기본 오브젝트형)와 int id 다르지만
        for(User user : users) {  //users배열에서 user로 하나씩 받아와서
            if(user.getId() == id) {  //자동 언박싱(user.getId() integer -> int)
                return user;
            }
        }
        return null;
    }

    //user에 입력값 저장
    public User saveUser(User user) {
        if(user.getId() == null) {
            user.setId(++usersCount);
        }
        users.add(user);

        return user;
    }

    //user에서 삭제
    public User deleteById(int id) {
        Iterator<User> iterator = users.iterator();
        
        while(iterator.hasNext()) { //iterator에 값이 있으면
            User user = iterator.next();  //하나씩 user에 가져오기
            
            if (user.getId() == id) {
                users.remove(id);
                return user;
            } 
        }
        
        return null;
    }
}
