package ManagementFDP.FPDcore.FDPsubclasses;

import java.util.ArrayList;

import ManagementFDP.Exceptions.SecondTutorRepeatedException;
import ManagementFDP.FPDcore.FDP;
import ManagementFDP.FPDcore.Tutor;

public class TeoricalFPD extends FDP {
    private String memory;

    public TeoricalFPD (String author, String title, Tutor tutor, String memory) throws SecondTutorRepeatedException {
        super(author, title, tutor);
        setMemory(memory);
    }

    public String getMemory() {
        return memory;
    }
    public void setMemory(String memory) {
        if (memory == null || memory.isBlank()) {
            throw new IllegalArgumentException("The memory cannot be null or blank.");
        }
        this.memory = memory;
    }

    @Override
    public void finish(float grade, ArrayList<FDP> enrolledList, ArrayList<FDP> finishedList) {
        setGrade(grade);
        enrolledList.remove(this);
        finishedList.add(this);
        setState(State.FINISHED);
        System.out.println("Author: " + this.getAuthor() + "Title: " + this.getTitle() + 
                           "Tutors: " + this.getTutors() + "Memory: " + this.getMemory());
    }

    @Override
    public FDP change(ArrayList<FDP> enrolledList, ArrayList<FDP> finishedList) throws SecondTutorRepeatedException {
        if (this.getState() == State.ENROLLED) {
            enrolledList.remove(this);
        } else {
            finishedList.remove(this);
        }

        ExperimentalFDP experimentalFDP = new ExperimentalFDP(this.getAuthor(), this.getTitle(), this.getTutors().getFirst(), "");
        experimentalFDP.secondTutor(this.getTutors().getLast());
        return experimentalFDP;
    }

    @Override
    public String toString() {
        return (super.toString() + "Memory: " + this.getMemory());
    }
}