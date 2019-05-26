package com.github.elementbound.asciima.image2ascii;

import com.github.elementbound.asciima.image2ascii.command.ConsoleCommand;
import com.github.elementbound.asciima.image2ascii.command.ConvertImageCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import picocli.CommandLine;

import java.util.List;

@SpringBootApplication
public class App {
    @Autowired
    private List<ConsoleCommand> commandList;

    public static void main(String[] args) {
        new SpringApplicationBuilder(App.class)
                .web(WebApplicationType.NONE)
                .run(args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            Object command = new ConvertImageCommand();
            CommandLine commandLine = new CommandLine(command);
            commandList.forEach(commandLine::addSubcommand);

            int result = commandLine.execute(args);
            System.exit(result);
        };
    }
}
