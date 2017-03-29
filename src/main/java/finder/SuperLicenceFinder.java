package finder;

import com.google.inject.Inject;
import domain.Championship;
import domain.DriverResult;
import domain.SuperlicenceScore;
import wikipedia.PointsMapFetcher;
import wikipedia.WikipediaChampionshipMapper;
import wikipedia.WikipediaParser;
import wikipedia.WikipediaSender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;


public class SuperLicenceFinder {
    @Inject
    private WikipediaSender wikipediaSender;

    @Inject
    private WikipediaParser wikipediaParser;

    @Inject
    private PointsMapFetcher pointsMapFetcher;


    public List<SuperlicenceScore> calculate() {
        List<String> years = Arrays.asList("2016");
        Map<Championship, Map<Integer, Integer>> championshipPoints = pointsMapFetcher.fetch();
        List<DriverResult> results = years.stream()
                .flatMap(y -> resultsForAllChamps(y).stream())
                .map(r -> new DriverResult(r, getSuperlicencePoints(championshipPoints, r)))
                .collect(toList());

        Map<String, List<DriverResult>> grouped = results.stream().collect(groupingBy(r -> r.getName().toLowerCase()));
        return grouped.entrySet().stream()
                .map(e -> {
                    int points = e.getValue().stream()
                            .mapToInt(x -> x.getSuperlicencePoints())
                            .reduce(0, (x, y) -> x + y);
                    return new SuperlicenceScore(e.getValue().get(0).getName(), points, e.getValue());
                })
                .collect(toList());
    }

    private Integer getSuperlicencePoints(Map<Championship, Map<Integer, Integer>> championshipPoints, DriverResult r) {
        Map<Integer, Integer> pointsMapForChampionship = championshipPoints.get(r.getChampionship());
        if (pointsMapForChampionship == null) {
            return 0;
        }
        Integer points = pointsMapForChampionship
                .get(r.getPosition());
        if (points == null) {
            return 0;
        }
        return points;
    }

    public List<DriverResult> resultsForAllChamps(String year) {
        return WikipediaChampionshipMapper.fetch().keySet().stream()
                .flatMap(c -> resultsForChampionship(c, year).stream())
                .collect(toList());
    }


    public List<DriverResult> resultsForChampionship(Championship championship, String year) {
        System.out.println(championship + ":" + year);
        Optional<String> result = wikipediaSender.fetchResultsPage(championship, year);
        if (!result.isPresent()) {
            return new ArrayList<>();
        }
        return wikipediaParser.parse(result.get(), championship, year);
    }

}
