package wikipedia;

import domain.Championship;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toMap;


public class PointsMapFetcher {

    public static Map<Championship, Map<Integer, Integer>> fetch() {
        Map<Championship, Map<Integer, Integer>> result = new HashMap<>();
        result.put(Championship.GP2, pointsMap(Arrays.asList(40, 40, 30, 20, 10, 8, 6, 4, 3,2)));
        result.put(Championship.EURO_F3, pointsMap(Arrays.asList(40, 30, 20, 10, 8, 6, 4, 3, 2,1)));
        result.put(Championship.FORMULA_E, pointsMap(Arrays.asList(40, 30, 20, 10, 8, 6, 4, 3, 2,1)));
        result.put(Championship.LMP1, pointsMap(Arrays.asList(40, 30, 20, 10, 8, 6, 4, 3, 2,1)));
        result.put(Championship.INDYCAR, pointsMap(Arrays.asList(40, 30, 20, 10, 8, 6, 4, 3, 2,1)));
        result.put(Championship.WSR, pointsMap(Arrays.asList(35, 25, 20, 15, 10, 7, 5, 3, 2,1)));

        return result;
    }

    private static Map<Integer, Integer> pointsMap(List<Integer> vars) {
        return IntStream.range(0, vars.size()).mapToObj(i -> new Integer(i)).collect(toMap(i -> i + 1, i -> vars.get(i)));
    }
}
