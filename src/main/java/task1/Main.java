package task1;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class Main {

    public static void main(String[] args) throws IOException {

        File file = new File("data/l2.txt");
        List<String> arr = Files.lines(Paths.get(file.getAbsolutePath()))
                .peek(e -> log.info(e))
                .toList();

        try (FileWriter fw = new FileWriter("data/result.csv", true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {

            Files.lines(Paths.get("data/l1.csv"))
                    .filter(e -> {
                        return arr.stream()
                                .anyMatch(s -> e.contains(s));
                    })
                    .peek(e -> log.info(e) )
                    .forEach(e -> out.println(e));
        }
    }
}
