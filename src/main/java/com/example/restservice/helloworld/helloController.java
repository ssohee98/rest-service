package com.example.restservice.helloworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class helloController {

    @Autowired
    private MessageSource messageSource;

    @GetMapping("/hello-world")
    public String helloWorld() {
        return "Hello World :)";
    }

    @GetMapping("/hello-world-bean")
    public HelloWorldBean helloWorldBean() {
        return new HelloWorldBean("Hello World Bean!");
    }

    @GetMapping("/hello-world-bean/path-variable/{name}")
    public HelloWorldBean helloWorldBean(@PathVariable String name) {
        return new HelloWorldBean(String.format("Hello World Bean!, %s", name));
    }

    @GetMapping("/hello-world-international")
    public String helloWorldInternational(
            @RequestHeader(name="Accept-Language", required = false)Locale locale) {

        //@RequestHeader "Accept-Language"로 반드시 언어 정보를 전달받아야함
        //사용자가 설정한 Locale 값에 따른 greeting.message 출력
        return messageSource.getMessage("greeting.message", null, locale);
    }


}
