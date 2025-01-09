package ManagementFDP.FPDcore;

import java.io.Serializable;
import java.util.ArrayList;

import ManagementFDP.Exceptions.SecondTutorRepeatedException;
import ManagementFDP.FPDcore.FDPsubclasses.State;

public abstract class FDP implements Comparable<FDP>, Serializable {
    private String author;
    private String title;
    private ArrayList<Tutor> tutors;
    private State state;
    private float grade;

    public FDP(String author, String title, Tutor tutor) throws SecondTutorRepeatedException {
        setAuthor(author);
        setTitle(title);
        this.tutors = new ArrayList<>();
        setTutor(tutor);
        this.state = State.ENROLLED;
        this.grade = -1;
    }

    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        if (author == null || author.isBlank()) {
            throw new IllegalArgumentException("The author cannot be blank or null");
        }
        this.author = author;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("The title cannot be blank or null");
        }
        this.title = title;
    }

    public ArrayList<Tutor> getTutors() {
        return new ArrayList<>(tutors);
    }
    public void setTutor(Tutor tutor) throws SecondTutorRepeatedException {
        if (tutor == null) {
            throw new IllegalArgumentException("Tutor cannot be null.");
        }
        if (tutors.size() >= 2) {
            throw new SecondTutorRepeatedException("The FDP already has two tutors.");
        }
        if (tutors.contains(tutor)) {
            throw new SecondTutorRepeatedException("This tutor is already assigned to the FDP.");
        }
        tutors.add(tutor);
    }

    public State getState() {
        return state;
    }
    public void setState(State state) {
        if (state == null) {
            throw new IllegalArgumentException("State cannot be null.");
        }
        this.state = state;
    }

    public float getGrade() {
        return grade;
    }
    public void setGrade(float grade) {
        if (grade < 0 || grade > 10) {
            throw new IllegalArgumentException("Grade cannot be negative or above 10.");
        }
        this.grade = grade;
    }

    @Override
    public int compareTo(FDP fdp) {
        return (int) Float.compare(this.grade, fdp.grade);
    }

    @Override
    public String toString() {
        return "Author: " + this.author + "Title: " + this.title + "Tutors: " + this.tutors +
               "State: " + this.state + "Grade: " + this.grade;
    }

    public abstract void finish(float grade, ArrayList<FDP> enrolledList, ArrayList<FDP> finishedList);

    public abstract FDP change(ArrayList<FDP> enrolledList, ArrayList<FDP> finishedList) throws SecondTutorRepeatedException;

    public void secondTutor(Tutor tutor) throws SecondTutorRepeatedException {
        if (tutors.size() < 1) {
            throw new IllegalAccessError("This method is to assign the 2nd tutor no the 1st.");
        }
        setTutor(tutor);
    }
}