package com.github.elementbound.asciima.image2ascii.command;

import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import java.io.File;
import java.util.concurrent.Callable;

@Command(name = "image2ascii", mixinStandardHelpOptions = true)
public class ConvertImageCommand implements Callable<Integer> {
    @Parameters(index = "0", description = "Input file", paramLabel = "input")
    private File inputFile;

    @Parameters(index = "1", description = "Output file", paramLabel = "output")
    private String outputFilePath;

    @Override
    public Integer call() {
        return 0;
    }
}
