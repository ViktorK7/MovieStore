package CourseProjectPackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class IOUtil {
	public static ArrayList<String> readFileByRow(String filePath){
        ArrayList<String> result = new ArrayList<>();

        try(BufferedReader bw = Files.newBufferedReader(Paths.get(filePath))){
            result = (ArrayList<String>) bw.lines().collect(Collectors.toList());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }
}
