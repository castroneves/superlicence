package domain;

import java.util.List;


public class SuperlicenceScore {
    private String name;
    private Integer totalScore;
    private List<DriverResult> results;

    public SuperlicenceScore(String name, Integer totalScore, List<DriverResult> results) {
        this.name = name;
        this.totalScore = totalScore;
        this.results = results;
    }

    public Integer getTotalScore() {
        return totalScore;
    }

    public List<DriverResult> getResults() {
        return results;
    }

    public String getName() {
        return name;
    }
}
