package com.heresy.beanTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextLoader;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.ServletContext;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest
@PropertySource("classpath:/upload.properties")
public class testBean {

    @Autowired
    Environment env;

    @Autowired
    ServletContext servletContext;

    @Test
    public void select() throws Exception {
        String password = "park1234";
        String password1 = "park1233";
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encoded = passwordEncoder.encode(password);
        System.out.println("------------");
        System.out.println("encoded : " + encoded );
        System.out.println("match : " + passwordEncoder.matches(password1, encoded));
        System.out.println("------------");

        Path a = Paths.get(env.getProperty("file.upload.path"),"/");
        System.out.println("serL : "+servletContext.getRealPath("/"));
    }
}
