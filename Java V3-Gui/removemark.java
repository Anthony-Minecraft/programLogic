import java.io.File;
import java.util.*;

public class removemark {

  public void removeMark(String folder) {
 
    String[] pathnames;
    File f = new File(folder);
    pathnames = f.list();
    int fileNumber = 0;
    for (String pathname : pathnames) {
      fileNumber++;
    }
    System.out.println("\nThis directory has " + fileNumber + " files");
    System.out.println(f + " contains " + Arrays.toString(pathnames) + "\n");
    for (String pathname : pathnames) {
      if (pathname.contains("-nrw_")) {
        System.out.println("Removing mark on: " + pathname);
        int endIndex = pathname.indexOf("-nrw_");
        String toBeReplaced = pathname.substring(0, endIndex + 5);
        String newPathName = pathname.replace(toBeReplaced, "");
        String newFileName = folder + "/" + newPathName;
        File file = new File(folder + "/" + pathname);
        File newFile = new File(newFileName);
        System.out.println(file + " will be renamed " + newFile);
        file.renameTo(newFile);
        System.out.println("Done\n" + file + " renamed to " + newFile + "\n");
      } else {
        System.out.println(pathname + " is not a rewritten file; Nothing changed");
      }
    }
    System.out.println("Done");
  }
}