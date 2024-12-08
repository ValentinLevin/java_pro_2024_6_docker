package com.example.worker.shelterdata;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication(scanBasePackages = {"com.example.worker.shelterdata", "com.example.core"})
public class ShelterDataWorkerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShelterDataWorkerApplication.class, args);
    }

    @Component
    public class MyTask implements CommandLineRunner {
        @Override
        public void run(String[] args) throws Exception {
            while (true) {
                Thread.sleep(1000);
            }
        }
    }
}
