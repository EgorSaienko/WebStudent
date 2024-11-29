package sumdu;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan("sumdu.edu.ua.controller")
public class ApplicationStarter {
    
    public static void main(String[] args){
        SpringApplication.run(ApplicationStarter.class, args);
        
        try {
            openHomePage();
        } catch (IOException ex) {
            Logger.getLogger(ApplicationStarter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void openHomePage() throws IOException {
       Runtime rt = Runtime.getRuntime();
       rt.exec("rundll32 url.dll,FileProtocolHandler " + "http://localhost:8080/");
    }
}
