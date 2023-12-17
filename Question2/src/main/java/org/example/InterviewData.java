package org.example;
import java.util.Date;

public class InterviewData{
    String date;
    String month;
    String Team;
    String panelName;
    String round;
    String skill;
    String time;
    String candidateCurrentLocation;
    String candidatePrefferedLocation;
    String candidateName;

    public String getDate() {
        return date;
    }

    public void setDate(String date){
        this.date = date;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getTeam() {
        return Team;
    }

    public void setTeam(String team) {
        Team = team;
    }

    public String getPanelName() {
        return panelName;
    }

    public void setPanelName(String panelName) {
        this.panelName = panelName;
    }

    public String getRound() {
        return round;
    }

    public void setRound(String round) {
        this.round = round;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCandidateCurrentLocation() {
        return candidateCurrentLocation;
    }

    public void setCandidateCurrentLocation(String candidateCurrentLocation) {
        this.candidateCurrentLocation = candidateCurrentLocation;
    }

    public String getCandidatePrefferedLocation() {
        return candidatePrefferedLocation;
    }

    public void setCandidatePrefferedLocation(String candidatePrefferedLocation) {
        this.candidatePrefferedLocation = candidatePrefferedLocation;
    }

    public String getCandidateName() {
        return candidateName;
    }

    public void setCandidateName(String candidateName) {
        this.candidateName = candidateName;
    }

    @Override
    public String toString() {
        return "ExcelData{" +
                "date='" + date + '\'' +
                ", month='" + month + '\'' +
                ", Team='" + Team + '\'' +
                ", panelName='" + panelName + '\'' +
                ", round='" + round + '\'' +
                ", skill='" + skill + '\'' +
                ", time='" + time + '\'' +
                ", candidateCurrentLocation='" + candidateCurrentLocation + '\'' +
                ", candidatePrefferedLocation='" + candidatePrefferedLocation + '\'' +
                ", candidateName='" + candidateName + '\'' +
                '}';
    }
}

