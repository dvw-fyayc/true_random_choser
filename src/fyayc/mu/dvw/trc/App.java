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

    String filename = "candidates.txt";
    if (args.length > 1 && args[1] != null) {
      filename = args[1];
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
    System.out.println("Chosen:");
    for (int index = 0; index < chose; index++) {
      try {
        TimeUnit.SECONDS.sleep(5);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println(" * " + candidates.get(index));
      // printName(candidates.get(index));
    }

  }

  private static void printName(String name) {
    System.out.print(" * ");
    for (int i = 0; i < name.length(); i++) {
      try {
        TimeUnit.SECONDS.sleep(2);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.print(name.charAt(i));
    }
    System.out.println();
  }

}
