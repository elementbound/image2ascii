package com.github.elementbound.asciima.image2ascii;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.github.elementbound.asciima.image2ascii.command.ConvertImageCommand;
import picocli.CommandLine;

@SpringBootApplication
public class App {
    @Autowired
    private ConvertImageCommand convertImageCommand;

    public static void main(String[] args) {
        new SpringApplicationBuilder(App.class)
                .web(WebApplicationType.NONE)
                .run(args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            CommandLine commandLine = new CommandLine(convertImageCommand);
            commandLine.setCaseInsensitiveEnumValuesAllowed(true);

            int result = commandLine.execute(args);
            System.exit(result);
        };
    }
}
