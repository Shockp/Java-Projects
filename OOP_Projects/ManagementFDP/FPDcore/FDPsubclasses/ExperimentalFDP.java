package ManagementFDP.FPDcore.FDPsubclasses;

import java.util.ArrayList;

import ManagementFDP.Exceptions.SecondTutorRepeatedException;
import ManagementFDP.FPDcore.FDP;
import ManagementFDP.FPDcore.Tutor;

public class ExperimentalFDP extends FDP {
    private String url;

    public ExperimentalFDP (String author, String title, Tutor tutor, String url) throws SecondTutorRepeatedException {
        super(author, title, tutor);
        setUrl(url);
    }

    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        if (url == null || url.isBlank()) {
            throw new IllegalArgumentException("The url cannot be null or blank.");
        }
        this.url = url;
    }

    @Override
    public void finish(float grade, ArrayList<FDP> enrolledList, ArrayList<FDP> finishedList) {
        setGrade(grade);
        enrolledList.remove(this);
        finishedList.add(this);
        setState(State.FINISHED);
        System.out.println("Author: " + this.getAuthor() + "Title: " + this.getTitle() + 
                           "Tutors: " + this.getTutors() + "url: " + this.getUrl());
    }

    @Override
    public FDP change(ArrayList<FDP> enrolledList, ArrayList<FDP> finishedList) throws SecondTutorRepeatedException {
        if (this.getState() == State.ENROLLED) {
            enrolledList.remove(this);
        } else {
            finishedList.remove(this);
        }

        TeoricalFPD teoricalFPD = new TeoricalFPD(this.getAuthor(), this.getTitle(), this.getTutors().getFirst(), "");
        teoricalFPD.secondTutor(this.getTutors().getLast());
        return teoricalFPD;
    }

    @Override
    public String toString() {
        return (super.toString() + "URL: " + this.getUrl());
    }
}
