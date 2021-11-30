import java.util.Scanner;
import java.io.File;
import java.util.Random;
import java.util.*;

class programlogic {

  public static void programLogic(String folderPath) {
    //Make the pathnames
    String[] pathnames;
    File f = new File(folderPath);
    pathnames = f.list();
    int fileNumber = 0;
    for (String pathname : pathnames) {
      fileNumber++;
    }
    int fileNumMax = fileNumber + 1000;
    System.out.println("\nThis directory has " + fileNumber + " files, and will create tags with numbers 0 - " + fileNumMax);
    //Out print what is in the file
    System.out.println(f + " contains " + Arrays.toString(pathnames) + "\n");
    //Rename the name for each file
    for (String pathname : pathnames) {
      String contans = "n";
      if (pathname.contains("-nrw_")) {
        Scanner parcont = new Scanner(System.in);
        System.out.println("It seems this file already has been modified. If you enter the \\Remove command, then it will remove the tag. Would you like to continue? (y or n)");
        contans = parcont.nextLine();
      } 
      if (!pathname.contains("-nrw_")||contans.contains("y")) {
        System.out.println("Working on: " + pathname);
        //Make a random number
        Random rand = new Random();
        int n = rand.nextInt(fileNumber + 1000);
        //Make new files and define what the newFileName will be
        String newFileName = folderPath + "/" + n + "-nrw_" + pathname;
        File file = new File(folderPath + "/" + pathname);
        File newFile = new File(newFileName);
        //Outprint what will happen
        System.out.println(file + " will be renamed " + newFile);
        //Check to see if the file already exists
        if (!newFile.exists()) {
          //If it does not, rename the file
          file.renameTo(newFile);
          System.out.println("Done\n" + file + " renamed to " + newFile + "\n");
        } else if (newFile.exists()) {
          //keep trying to find a name that works
          while (newFile.exists()) {
            int s = rand.nextInt(fileNumber + 1000);
            String newFileName2 = folderPath + "/" + s + "-nrw_" + pathname;
            newFile = new File(newFileName2);
          }
          //Rename the file
          file.renameTo(newFile);
        } else {
          //This will only happen if the system somehow breaks
          System.out.println("Something went very wrong.");
        }
      }
    }
    System.out.println("Done");
  }
}