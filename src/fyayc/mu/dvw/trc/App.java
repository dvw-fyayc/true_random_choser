package fyayc.mu.dvw.trc;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class App {

  public static void main(String[] args) {
    SecureRandom sr = new SecureRandom();

    int chose = 1;

    if (args.length > 0 && args[0] != null) {
      try {
        chose = Integer.parseInt(args[0]);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    int groups = 1;
    if (args.length > 1 && args[1] != null) {
      try {
        groups = Integer.parseInt(args[1]);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    String filename = "candidates.txt";
    if (args.length > 2 && args[2] != null) {
      filename = args[2];
    }
    Path path = new File(filename).toPath();

    List<String> candidates = Collections.emptyList();
    try {
      candidates = new ArrayList<>(Files.readAllLines(path, StandardCharsets.UTF_8).stream()
          .filter(s -> s.length() > 0).collect(Collectors.toSet()));
    } catch (IOException e) {
      e.printStackTrace();
    }

    if (candidates.isEmpty()) {
      System.out.println("No candidates in " + path.toAbsolutePath());
      System.exit(0);
    }

    System.out.println("Candidates:");
    candidates.forEach(candidate -> System.out.print(candidate + ", "));
    System.out.println();

    Collections.shuffle(candidates, sr);

    System.out.println("--------------------");
    System.out.println();

    int loopSize = candidates.size() / groups;
    for (int group = 1; group <= groups; group++) {
      System.out.println("Group: " + group);

      int counter = 1;
      for (int index = (group - 1) * loopSize; index < group * loopSize
          && index < candidates.size(); index++) {
        try {
          TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        System.out.println("\t" + counter + ". " + candidates.get(index));
        counter++;
      }

      if (group != groups) {
        System.out.println("--------------------");
      }
    }

  }

}
