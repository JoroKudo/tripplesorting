import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;


public class SortTemplate {

    public List<Integer> unsortedArrayList;
    private int[] unsortedArray;

    private int[] sortedQuick;
    private int[] sortedBubble;
    private int[] sortedMerge;


    private URL expurl;

    public static int SIZE;
    private final String[] DAT = {"10Digits.dat", "100Digits.dat", "1000Digits.dat"};


    //Test
    public static void main(String[] args) throws IOException {



        for (int index = 0; index < 3; index++) {

            long startTime1;
            long startTime2;
            long startTime3;
            SortTemplate programm = new SortTemplate(index);


            programm.printArray(programm.unsortedArray, programm.SIZE);

            QuickSort quickSort = new QuickSort(programm.unsortedArrayList.stream().mapToInt(i -> i).toArray());
            startTime1 = new Date().getTime();

            programm.sortedQuick = quickSort.sort(0, programm.SIZE - 1);
            System.out.println("Dauer der Sortierung [Quicksort]: " + (new Date().getTime() - startTime1) + "ms");
            programm.printArray(programm.sortedQuick, programm.SIZE);



            Bubblesort bubblesort = new Bubblesort(programm.unsortedArrayList.stream().mapToInt(i -> i).toArray());
            startTime2 = new Date().getTime();
            programm.sortedBubble = bubblesort.sort();
            System.out.println("Dauer der Sortierung [Bubblesort]: " + (new Date().getTime() - startTime2) + "ms");
            programm.printArray(programm.sortedBubble, programm.SIZE);



            MergeSort mergesort = new MergeSort(programm.unsortedArrayList.stream().mapToInt(i -> i).toArray());
            startTime3 = new Date().getTime();
            programm.sortedMerge = mergesort.sort(0, programm.SIZE - 1);
            System.out.println("Dauer der Sortierung [Mergesort]: " + (new Date().getTime() - startTime3) + "ms");
            programm.printArray(programm.sortedBubble, programm.SIZE);

            Scanner scan = new Scanner(System.in);

            System.out.print("What should the exported file be called?(Without file extension)");
            String expfilename = scan.next();

            programm.expurl = programm.getClass().getResource("/exports/");

            File expfile = new File(programm.expurl.getPath() + expfilename + ".csv");
            expfile.createNewFile();
            programm.exportcsv(expfile);


        }
    }

    SortTemplate(int index) {

        try {
            SIZE = getfromfile(DAT[index]).size();


            unsortedArray = new int[SIZE];
            for (int i = 0; i < SIZE; i++) unsortedArray[i] = getfromfile(DAT[index]).get(i);

            unsortedArrayList = Arrays.stream(unsortedArray)
                    .boxed()
                    .collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    private List<Integer> getfromfile(String datfilename) throws IOException {
        URL resurl = getClass().getResource("resources/" + datfilename);
        File datfile = new File(resurl.getPath());

        Scanner scanner = new Scanner(datfile);
        List<Integer> integers = new ArrayList<>();
        while (scanner.hasNext()) {
            if (scanner.hasNextInt()) {
                integers.add(scanner.nextInt());
            } else {
                scanner.next();
            }
        }
        return integers;
    }

    /*  private void resetarray(int index) {
          for (int i = 0; i < SIZE; i++) {
              try {
                  unsortedArrayList[i] = getfromfile(DAT[index]).get(i);
              } catch (IOException e) {
                  e.printStackTrace();
              }
          }

      }
  */
    private void printArray(int[] arr, int size) {
        for (int i = 0; i < size; i++)
            System.out.print(arr[i] + " ");

        System.out.println();
    }


    private void exportcsv(File expfile) {
        try (PrintWriter writer = new PrintWriter(expfile)) {
            char seperator = ';';
            StringBuilder sb = new StringBuilder();
            sb.append("\"Unsorted\"");
            sb.append(seperator);
            sb.append("\"QuickSort\"");
            sb.append(seperator);
            sb.append("\"BubbleSort\"");

            sb.append("\n");
            for (int i = 0; i < SIZE; i++) {
                sb.append(unsortedArray[i]);
                sb.append(seperator);
                sb.append(sortedQuick[i]);
                sb.append(seperator);
                sb.append(sortedBubble[i]);

                sb.append("\n");
            }


            writer.write(sb.toString());
            writer.close();
            System.out.println("done!");

        } catch (FileNotFoundException e) {
            System.out.println("AAAAAAAAAAAAAAAAAAAAAAAA");
        }
    }


}
