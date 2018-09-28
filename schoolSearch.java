import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.lang.*;

public class schoolSearch {
    private static void switchStatement(String[] array, String[][] data, int line, String[][] teachers, int teacherLine)
    {
                           String fName = "";
                           String lName = "";
                           String GPA   = "";  
                           String teach = "";
                           String bus   = "";
        int[] printer = {12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12};
        int[] enrollment = {0,0,0,0,0,0,0,0,0,0,0,0,0};
        switch (array[0]) {
           case "S":
           case "Student":
                for (int i = 0; i < line; i++) {
                    if (array[1].equals(data[i][0])) {
                        System.out.print(data[i][0] + ", " + data[i][1] + ", ");
                        if (array.length == 3) {
                            if (array[2].equals("B") || array[2].equals("Bus"))
                                System.out.print(data[i][4] + "\n");
                        } else {
                            System.out.print(data[i][2] + ", " + data[i][3] + ", ");
                            for (int j = 0; j < teacherLine; j++) {
                                if (data[i][3].equals(teachers[j][2])) {
                                    System.out.print(teachers[j][0] + ", " + teachers[j][1] + "\n");
                                }
                            }
                        }
                    }
                }
            case "T":
            case "Teacher":
                for (int i = 0; i < teacherLine; i++) {
                    if (array[1].equals(teachers[i][0])) {
                        for(int j = 0; j < line; j++) {
                            if(teachers[i][2].equals(data[j][3]))
                                System.out.println(data[j][0] + ", " + data[j][1]);
                        }
                    }
                }
                break;
            case "B":
            case "Bus":
                for (int i = 0; i < line; i++) {
                    if (array[1].equals(data[i][4])) {
                        System.out.println(data[i][1] + " " + data[i][0] + ", " + data[i][2] + ", " + data[i][3]);
                    }
                }
                break;
            case "G":
            case "Grade":
                double high = 0;
                double low  = 0;
                if(array.length == 3)
                {
                    if(Integer.parseInt(array[1]) > 6)
                        break;
                   if(array[2].equals("H") || array[2].equals("High"))
                   {
                     for (int l = 0; l < line; l++) {
                        if (Double.parseDouble(data[l][5]) > high && /*greatest GPA */
                              array[1].equals(data[l][2]))           /* with given score*/
                        {
                           fName = data[l][1];
                           lName = data[l][0];
                           GPA   = data[l][5];
                           teach = data[l][6];
                           bus   = data[l][4];

                           high = Double.parseDouble(data[l][5]);

                        }
                     }
                     System.out.println(fName + " " + lName + ", " + GPA + ", "
                           + teach + ", " + bus);
                  }
                  else if(array[2].equals("L") || array[2].equals("Low"))
                  {
                     for (int l = 0; l < line; l++) {
                        if(array[1].equals(data[l][2]) && low == 0) 
                        {low = Double.parseDouble(data[l][5]);}

                     if (Double.parseDouble(data[l][5]) <= low && /*greatest GPA */
                           array[1].equals(data[l][2]))           /* with given score*/
                     {
                           fName = data[l][1];
                           lName = data[l][0];
                           GPA   = data[l][5];
                           teach = data[l][6];
                           bus   = data[l][4];

                           low = Double.parseDouble(data[l][5]);

                        }
                     }
                     System.out.println(fName + " " + lName + ", " + GPA + ", "
                           + teach + ", " + bus);
                  }

               }
                else{
                for (int i = 0; i < line; i++) {
                    if (array[1].equals(data[i][2])) {
                        System.out.println("LastName:\t" + data[i][0]);
                        System.out.println("FirstName:\t" + data[i][1]);
                    }
                }
                }
                break;
            case "A":
            case "Average":
            double sum = 0;
            int count = 0;
               for (int i = 0; i < line; i++)
               {
                  if(array[1].equals(data[i][2]))
                  {
                     sum += Double.parseDouble(data[i][5]);
                     count++;
                  }
               }
               System.out.println(array[1] + " - Average is: " + sum/count);
                break;
            case "I":
            case "Info":
                if(array.length != 1)
                    break;
                for (int i = 0; i<=6; i++)
                {
                   int students = 0;
                   for (int j = 0; j < line; j++)
                   {
                     if(i == Integer.parseInt(data[j][2]))
                     {
                        students++;
                     } 
                   }
                   System.out.println(i + ": " + students);
                }
                break;


            case "CS":
            case "ClassroomS":

                for (int i = 0; i < line; i++)
                {
                    if(array[1].equals(data[i][3]))
                    {
                        System.out.println(data[i][0] + ", " + data[i][1]);
                    }
                }
                break;

            case "CT":
                for (int i = 0; i < teacherLine; i++)
                {
                    if(array[1].equals(teachers[i][2]))
                    {
                        System.out.println(teachers[i][0] + ", " + teachers[i][1]);
                    }
                }
                break;

            case "GT":
                for (int i = 0; i < line; i++)
                {
                    if(array[1].equals(data[i][2])) {
                        for (int j = 0; j < teacherLine; j++) {
                            if (data[i][3].equals(teachers[j][2]) && printer[j] != 50) {
                                System.out.println(teachers[j][0] + ", " + teachers[j][1]);
                                printer[j] = 50;
                            }
                        }
                    }
                }
                break;

            case "R":
            case "Report":
                for (int i = 0; i < line; i++)
                {
                    int roomNumber = Integer.parseInt(data[i][3]) - 100;
                    enrollment[roomNumber] = enrollment[roomNumber] + 1;
                }
                for (int j = 1; j < 13; j++)
                {
                    System.out.println("1" + String.format("%02d", j) + ": " + enrollment[j]);
                }
                break;
            case "Stat":
            case "Statistics":
                List<String> grades = new ArrayList<String>();
                double average = 0;
                count = 0;
                String tName;
               /* average GPA per grade */
               System.out.println("");
               System.out.println("Average GPA per grade");
               for(int i = 0; i < line; i++)
               {
                  average = 0;
                  count   = 0;
                  for(int j = 0; j < line; j++)
                  {
                     if(data[i][2].equals(data[j][2]))
                     {
                        average += Double.parseDouble(data[j][5]);
                     count++;
                     }
                  }
                  if(!grades.contains(data[i][2]))
                  {
                     grades.add(data[i][2]);
                     System.out.println("Grade " + data[i][2] 
                           + " average GPA: " + average/count);
                  }
               }
                List<String> teachrs = new ArrayList<String>();


               // average GPA of students under each teacher
               System.out.println("");
               System.out.println("Average GPA per teacher");
               for(int k = 0; k < teacherLine; k++) // loop students
               {
                  average = 0;
                  count   = 0;
                  tName = teachers[k][1] + " " + teachers[k][0];
                  if(!teachrs.contains(data[k][3])) //if you havent checked yet
                  {
                     for(int l = 0; l < line; l++) //loop students
                     {
                        if(teachers[k][2].equals(data[l][3])) // if same classroom
                        {
                           /*System.out.println("NEW ----" + data[l][0] + " GPA: "
                                 + data[l][5]);*/
                           average += Double.parseDouble(data[l][5]);
                           count++;
                        }
                     }
                     for(int m = 0; m < teacherLine; m++) // find name of teacher
                     {
                        if(teachers[m][2].equals(data[k][3]))
                        {
                        System.out.println(tName +"'s student average GPA: " + average/count);
                        break;
                        }
                     }
                  }
               }

               /* average GPA of students under each bus route */
               List<String> routes = new ArrayList<String>();
               System.out.println("");
               System.out.println("Average GPA per bus route");
               for(int r = 0; r < line; r++) // loop students
               {
                  average = 0;
                  count   = 0;
                  if(!routes.contains(data[r][4])) //if you havent checked yet
                  {
                     routes.add(data[r][4]);
                     for(int s = 0; s < line; s++) //loop students
                     {
                        if(data[r][4].equals(data[s][4])) // if same route
                        {
                           average += Double.parseDouble(data[s][5]);
                           count++;
                        }
                     }
                     System.out.println("Route " + data[r][4] + " " + average/count);
                  }
               }

            default:
        }
    }

    public static void main(String[] args) throws Exception {

        File file = new File("list.txt");
        File file1 = new File("teachers.txt");
        if(!file.exists() && !file1.exists())
        {
            System.out.println("One of more files do not exist.");
            System.exit(1);
        }

        String[][] studentData = new String[1000][6];
        String[][] teacherData = new String[1000][3];
        Scanner Read = new Scanner(file);
        Scanner Read1 = new Scanner(file1);
        String myLine;
        int line = 0;

        while (Read.hasNextLine()) {
            String[] array1 = Read.nextLine().split(", |,");
            for (int i = 0; i < array1.length; i++) {
                studentData[line][i] = array1[i];
            }
            line++;
        }

        int teacherLine = 0;
        while (Read1.hasNextLine()) {
            String[] array = Read1.nextLine().split(", ");
            for (int i = 0; i < array.length; i++) {
                teacherData[teacherLine][i] = array[i];
            }
            teacherLine++;
        }

        //start of the loop
        System.out.println("Enter command :");
        Scanner input = new Scanner(System.in);
        String in = "start";

        while (!in.equals("Q") && !in.equals("Quit")){
            in = input.nextLine();

            if(in.equals("Q") || in.equals("Quit"))
                break;

            String[] array2 = in.split(": | ");

            switchStatement(array2, studentData, line, teacherData, teacherLine);
            System.out.println("Enter command :");
        }
    }
}
