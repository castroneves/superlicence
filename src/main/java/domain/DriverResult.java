package domain;


public class DriverResult {
    private String name;
    private int position;
    private Championship championship;
    private String year;
    private Integer superlicencePoints;

    public DriverResult(String name, int position, Championship championship, String year) {
        this.name = name;
        this.position = position;
        this.championship = championship;
        this.year = year;
    }

    public DriverResult(DriverResult driverResult, Integer superlicencePoints) {
        this.name = driverResult.getName();
        this.championship = driverResult.getChampionship();
        this.position = driverResult.getPosition();
        this.year = driverResult.getYear();
        this.superlicencePoints = superlicencePoints;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public Championship getChampionship() {
        return championship;
    }

    public String getYear() {
        return year;
    }

    public Integer getSuperlicencePoints() {
        return superlicencePoints;
    }
}
