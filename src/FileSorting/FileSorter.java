/**
 * Student Name:Peifen Lu
 * Student ID:18008550
 * The FileSorter class input and output a ordered text File
 * using quicksort and merge sort
 */
package FileSorting;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gkn3798
 */
public class FileSorter implements Runnable {

    private int maximum;
    private File input;
    private int numFiles;
    private int fileIndex;
    private int readerCount;

    //constructor
    public FileSorter(int maximum, File inputFile) {
        this.maximum = maximum;
        this.input = inputFile;
        this.numFiles = 0;
        this.fileIndex = 0;
        this.readerCount = 1;
    }

    /**
     * 
     * @param inputFile 
     */
    public void split(File inputFile) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(inputFile));

            String line = null;

            int numElements = 0;

            line = reader.readLine();

            while (line != null) {
                String[] list = new String[maximum];
                for (int count = 0; count < maximum; count++) {

                    list[count] = line;
                    numElements++;
                    line = reader.readLine();
                }

                if (numElements % maximum == 0) {
                    quickSort(list);

                    numFiles++;
                    fileIndex++;
//                    System.out.println("\nnumFiles: " + numFiles);
                    BufferedWriter writer = new BufferedWriter(new FileWriter("file" + fileIndex + ".txt"));
                    for (String item : list) {
                        writer.write(item);
                        writer.newLine();;
                    }

                    writer.flush();
                    writer.close();
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println("File not found!");
            Logger.getLogger(FileSorter.class.getName()).log(Level.SEVERE, null, ex);
            
        } catch (IOException ex) {
            Logger.getLogger(FileSorter.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                reader.close();
            } catch (IOException ex) {
                Logger.getLogger(FileSorter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void recusiveMerge() {
        while (this.numFiles > 1) {
            mergeSort();
        }
        System.out.println("Number of Files left: " + numFiles);
        System.out.println("The files have been all sorted and output!");
    }

    public void mergeSort() {
        File temp1 = new File("file" + readerCount + ".txt");

        readerCount++;
        numFiles--;

        File temp2 = new File("file" + readerCount + ".txt");
        readerCount++;
        numFiles--;

        BufferedReader reader1 = null;
        BufferedReader reader2 = null;
        BufferedWriter writer = null;

        try {
            reader1 = new BufferedReader(new FileReader(temp1));
            reader2 = new BufferedReader(new FileReader(temp2));

            fileIndex++;

            writer = new BufferedWriter(new FileWriter("file" + fileIndex + ".txt"));
            numFiles++;
            String line1 = null;
            String line2 = null;
            line1 = reader1.readLine();
            line2 = reader2.readLine();

            while (line1 != null && line2 != null) {
                if (line1.compareTo(line2) == 0) {
                    
                    writer.write(line1);
                    writer.newLine();
                    writer.write(line2);
                    writer.newLine();
                    line1 = reader1.readLine();
                    line2 = reader2.readLine();
                } else if (line1.compareTo(line2) > 0) {

                    writer.write(line2);
                    writer.newLine();
                    line2 = reader2.readLine();
                } else {

                    writer.write(line1);
                    writer.newLine();
                    line1 = reader1.readLine();
                }
            }

            while (line1 != null && line2 == null) {
                writer.write(line1);
                writer.newLine();

                line1 = reader1.readLine();
            }

            while (line2 != null && line1 == null) {
                writer.write(line2);
                writer.newLine();

                line2 = reader2.readLine();
            }

            reader1.close();
            temp1.delete();

            reader2.close();
            temp2.delete();
            writer.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileSorter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileSorter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //The quickSort method is borrowed from the lecture examples
    public void quickSort(String[] list) {
        quickSortSegment(list, 0, list.length);
    }

    private void quickSortSegment(String[] list, int start, int end) {
        if (end - start > 1) {
            int indexPartition = partition(list, start, end);

            quickSortSegment(list, start, indexPartition);

            quickSortSegment(list, indexPartition + 1, end);
        }
    }

    private int partition(String[] list, int start, int end) {
        String temp;
        String partitionElement = list[start];
        int leftIndex = start;
        int rightIndex = end - 1;

        while (leftIndex < rightIndex) {
            while (list[leftIndex].compareTo(partitionElement) <= 0
                    && leftIndex < rightIndex) {
                leftIndex++;
            }
            while (list[rightIndex].compareTo(partitionElement) > 0) {
                rightIndex--;
            }
            if (leftIndex < rightIndex) {
                temp = list[leftIndex];
                list[leftIndex] = list[rightIndex];
                list[rightIndex] = temp;
            }
        }

        list[start] = list[rightIndex];
        list[rightIndex] = partitionElement;
        return rightIndex;
    }

    @Override
    public void run() {
        this.process();
    }

    public void process() {
        split(input);
        System.out.println("\nThe files has finished splitting.");
        recusiveMerge();
        System.out.println("The files have been merged into a single file.");
    }
    
    public static void main(String[] args){
        File input = new File("girlnames.txt");

        FileSorter app = new FileSorter(10, input);
        Thread thread = new Thread(app);
        thread.start();

    }
}
