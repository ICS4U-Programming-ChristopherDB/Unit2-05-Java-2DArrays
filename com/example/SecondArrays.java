package com.example;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Program that gets student marks on assignments.
 *
 * @author Christopher Di Bert
 * @version 1.0
 * @since 2024-04-02
 */

// 2DArrays class
public final class SecondArrays {

  /** Private constructor to prevent instantiation. */
  private SecondArrays() {
    throw new UnsupportedOperationException("Cannot instantiate");
  }

  /**
   * This is the main method.
   *
   * @param args Unused
   */
  public static void main(final String[] args) {
    // Strings containing read paths.
    final String studentNamePath = "students.txt";
    final String assignmentPath = "assignments.txt";

    // Instantiates files with their respective file path.
    File studentNameFile = new File(studentNamePath);
    File assignmentFile = new File(assignmentPath);

    // Array lists for reading the input.
    List<String> listOfStudents = new ArrayList<String>();
    List<String> listOfAssignments = new ArrayList<String>();

    // Try to create scanners using the files.
    try {
      Scanner studentScanner = new Scanner(studentNameFile);
      // Adds student names to the student array list.
      while (studentScanner.hasNextLine()) {
        final String name = studentScanner.nextLine();
        listOfStudents.add(name);
      }

      studentScanner.close();

      // Tries creating an assignment scanner.
      try {
        Scanner assignmentScanner = new Scanner(assignmentFile);
        // Adds every assignment to assignment array list.
        while (assignmentScanner.hasNextLine()) {
          final String assignment = assignmentScanner.nextLine();
          listOfAssignments.add(assignment);
        }
        assignmentScanner.close();
      } catch (Exception e) {
        System.out.println("Invalid assignment file path!");
      }

    } catch (Exception e) {
      System.out.println("Invalid name file path!");
    }

    // Creates name array with size of name array list.
    String[] namesArr = new String[listOfStudents.size()];

    // Adds names to the array.
    for (int i = 0; i < listOfStudents.size(); i++) {
      namesArr[i] = listOfStudents.get(i);
    }

    // Creates assignment array with size of assignment array list.
    String[] assignmentsArr = new String[listOfAssignments.size()];

    // Adds assignments to the array.
    for (int i = 0; i < listOfAssignments.size(); i++) {
      assignmentsArr[i] = listOfAssignments.get(i);
    }

    // Instantiates student mark array with size equal to num students.
    String[][] studentMarks = new String[listOfStudents.size()][];

    // Populates studentMarks with each student and their mark.
    for (int i = 0; i < namesArr.length; i++) {
      String[] studentMark = new String[listOfAssignments.size() + 1];
      studentMark[0] = namesArr[i];
      for (int x = 1; x < assignmentsArr.length + 1; x++) {
        studentMark[x] = generateMarks();
        System.out.println(studentMark[x]);
      }
      studentMarks[i] = studentMark;
    }

    // Writing to CSV file
    try (BufferedWriter w = new BufferedWriter(new FileWriter("marks.csv"))) {
      // Writes all student marks to a single line delimited with comma.
      for (String[] studentMark : studentMarks) {
        w.write(String.join(",", studentMark));
        w.newLine();
      }
      System.out.println("Student marks have been written to " + "marks.csv");
    } catch (IOException e) {
      System.out.println("Error occurred while writing to file");
    }
  }

  // Method to calculate assignment marks.
  private static String generateMarks() {
    Random randGen = new Random();
    final int mark = (int) randGen.nextGaussian() * 10 + 75;
    final String markStr = Integer.toString(mark);
    return markStr;
  }
}
