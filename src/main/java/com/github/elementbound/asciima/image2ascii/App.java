package com.github.elementbound.asciima.image2ascii;

import com.github.elementbound.asciima.image2ascii.command.ConvertImageCommand;
import picocli.CommandLine;

public class App {
    public static void main(String[] args) {
        Object command = new ConvertImageCommand();
        CommandLine commandLine = new CommandLine(command);

        int result = commandLine.execute("");
        System.exit(result);
    }
}
