import java.util.Scanner;
import java.io.File;

class Main {

  public static void listBash(String fileName) {
    String folder = fileName.replace("\\ls ", "");
    String[] lspathnames;
    File f = new File(folder);
    lspathnames = f.list();
    for (String lspathname : lspathnames) {
      System.out.println(lspathname);
    }
  }

  //The main class, where the program will run first
  public static void main(String[] args) {
    int startUpInt = 1;
    String helpInfo = "\n\\Exit -- Terminate the program\n\\Help -- Bring up the information\n\\ls (directory) -- Print the contents of a directory\n\\Remove (directory) -- Remove the -nrw_ tag from the contents of a directory";
    File thisOne = new File("Main.java");
    System.out.println("[--------------------------------------------------------]\nMyriware: Software Solutions\nThe current location of this file is: " + thisOne.getAbsolutePath() + "Enter \\Help to get the list of commands.");
    //This keeps the program running over and over again
    while (startUpInt == 1) {
      try {
        //Ask what file to look at.
        Scanner parfileName = new Scanner(System.in);
        System.out.println("[--------------------------------------------------------]\nEnter Folder name or Command");
        String fileName = parfileName.nextLine();
        if (fileName.contains("\\Exit")) {
          //If they say to exit, stop the program
          parfileName.close();
          startUpInt = 0;
        } else if (fileName.contains("\\ls")) {
          listBash(fileName);
        } else if (fileName.contains("\\Help")) {
          System.out.println(helpInfo);
        } else if (fileName.contains("\\Remove")) {
          removemark.removeMark(fileName);
        } else {
          //If not, then go and do the programLogic
          try {
          programlogic.programLogic(fileName);
          } catch(Exception e) {
            System.out.println("Your file does not exist");
          }
        }
      } catch(Exception e) {
        System.out.println("Something did not work");
      }
    }
    System.out.println("Program terminated");
  }
}