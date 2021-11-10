package com.bridgelabz.niofileoperations;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

public class NIOFileAPITest {
    private static final String HOME = System.getProperty("D:/JAVA-class");

    @Test
    public void givenPathWhenCheckedThenConfirm() throws IOException {
        //check file Exists
        Path homePath = Paths.get(HOME);
        Assert.assertTrue(Files.exists(homePath));

        //Delete file and check file not exist
        String PLAY_WITH_NIO = "TempPlayGround";
        Path playPath = Paths.get(HOME + "/" + PLAY_WITH_NIO);
        if (Files.exists(playPath)) {
            FileUtils.deleteFiles(playPath.toFile());
        }
        Assert.assertTrue(Files.exists(playPath));

        //create directory
        Files.createDirectory(playPath);
        Assert.assertTrue(Files.exists(playPath));

        //create file
        IntStream.range(1, 10).forEach(cntr -> {
            Path tempFile = Paths.get(playPath + "/temp" + cntr);
            Assert.assertTrue(Files.notExists(tempFile));
            try {
                Files.createFile(tempFile);
            }
            catch (NullPointerException | IOException e){ }
            Assert.assertTrue(Files.exists(tempFile));
        });

        //List files, directories as well as files with extensions
        Files.list(playPath).filter(Files::isRegularFile).forEach(System.out::println);
        Files.newDirectoryStream(playPath).forEach(System.out::println);
        Files.newDirectoryStream(playPath, path -> path.toFile().isFile() && path.toString().startsWith("temp")).forEach(System.out::println);
    }
}