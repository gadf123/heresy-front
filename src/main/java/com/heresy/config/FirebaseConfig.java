package com.heresy.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

/**
 * @user park
 * @date 2018. 9. 25.
 **/

@Configuration
public class FirebaseConfig {

    @Bean
    public FirebaseApp firebaseApp(){
        FirebaseApp firebaseApp = null;
        try {
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials
                            .fromStream(new ClassPathResource("/firebaseKey/serviceAccountKey.json").getInputStream()))
                    .setDatabaseUrl("https://three-caliber.firebaseio.com").build();
            if (FirebaseApp.getApps().isEmpty()) {
                firebaseApp = FirebaseApp.initializeApp(options);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return firebaseApp;
    }
}
