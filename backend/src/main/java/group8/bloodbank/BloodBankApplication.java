package group8.bloodbank;


import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication(exclude={SecurityAutoConfiguration.class})
public class BloodBankApplication {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    public static void main(String[] args) {

        SpringApplication.run(BloodBankApplication.class, args);
        Server server =
                ServerBuilder.forPort(8787)
                        .build();
    }


}
