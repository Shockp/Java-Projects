package ManagementFDP.FPDcore;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ManagementPlatform {
    /*
     * Dos listas una con los FDP inscritos y otra con los FDP finalizados.
     * 
     * Método sobresalientes() imprime un fichero de texto la información de todos los FDP
     * con una calificación mayor o igual a 9.
     * 
     * Método ordenados() que guarde un fichero binario de todos los FPD finalizados
     * y ordenados por calificación.
     */
    private ArrayList<FDP> enrolledList;
    private ArrayList<FDP> finishedList;

    public ManagementPlatform() {
        enrolledList = new ArrayList<>();
        finishedList = new ArrayList<>();
    }

    public ArrayList<FDP> getEnrolledList() {
        return enrolledList;
    }

    public ArrayList<FDP> getFinishedList() {
        return finishedList;
    }

    public void outstandings() {
        String fileName = "outstandings.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (FDP fdp : finishedList) {
                if (fdp.getGrade() >= 9.0) {
                    writer.write(fdp.toString());
                    writer.newLine();
                }
            }
            System.out.println("Outstandings FDPs have been written to " + fileName);
        } catch (IOException e) {
            System.err.println("Error writing to the file " + fileName + "\nError: " + e.getMessage());
        }
    }

    public void getFDPsOrderedByGrade() {
        String fileName = "orderedGrades.ser";

        ArrayList<FDP> sortedList = new ArrayList<>(finishedList);
        sortedList.sort(null);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(sortedList);
            System.out.println("Sorted list by grade finished in the file: " + fileName);
        } catch (IOException e) {
            System.err.println("Error writing to the binary file: " + fileName + "\nError: " + e.getMessage());
        }
    }
}