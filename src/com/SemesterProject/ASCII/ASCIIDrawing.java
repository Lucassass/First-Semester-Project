package com.SemesterProject.ASCII;

import java.io.*;
import java.util.stream.Stream;

public class ASCIIDrawing
{
    /**
     * Load ASCII drawing from file
     * @param name Name of the file
     */
    public static void load(String name) {
        var drawing = getDrawing(name);

        if (drawing == null)
        {
            try {
                throw new Exception("File not found");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else
        {
            for (var lines: drawing.toArray()) {
                System.out.println(lines);
            }
        }
    }

    /**
     * Gets drawing from file
     * @param name Name of the file
     * @return return a stream of strings
     */
    private static Stream<String> getDrawing(String name)
    {
        try {
            BufferedReader reader = getFile(name);
            return reader.lines();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param name Name of the file
     * @return a bufferedReader of file
     * @throws FileNotFoundException If file not found, throw exception
     */
    private static BufferedReader getFile(String name) throws FileNotFoundException {
        String folderName = "Drawing";
        return new BufferedReader(new FileReader(System.getProperty("user.dir") + "/"+ folderName + "/" +name + ".txt"));
    }


}
