package my.sunway.group5.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class TopScoresService {

    private final String path;

    public TopScoresService(String path) {
        this.path = path;
    }

    public List<String> getTopScores() {
        try {
            return Files.readAllLines(Paths.get(path));
        } catch (IOException ioe) {
            return new ArrayList<>();
        }
    }

    public boolean saveTopScores(List<String> strings) throws Exception {
        Files.write(Paths.get(path), strings);
        return true;
    }
}
