package com.example.restservice.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

//@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {

    //@Id
    //@GeneratedValue
    private Integer id;

    private String description;

    //한 User에 여러개 Post
    //User 검색시마다 모든 Post 조회하지 않도록 fetch Lazy 설정
    //data는 불러오지만 민감정보 출력하지 않도록 JsonIgnore 설정
    //@ManyToOne(fetch = FetchType.LAZY)
    //@JsonIgnore
    //private User user;
    private int user_id;
}
