package messagerenderingtoolAPI;

import messagerenderingtoolAPI.Implementations.EmulatorsFromConfigFactory;
import messagerenderingtoolAPI.Implementations.EmulatorsManager;
import messagerenderingtoolAPI.Services.IEmulatorsFactory;
import messagerenderingtoolAPI.Services.IEmulatorsManager;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


}
