package wikipedia;

import domain.Championship;
import domain.DriverResult;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

/**
 * Created by 44029273 on 28/03/2017.
 */
public class WikipediaParser {
    public List<DriverResult> parse(String resultsPage, Championship championship, String year) {
        Document parse = Jsoup.parse(resultsPage);

        List<Element> collect = parse.getElementsByTag("table").stream()
                .filter(e -> e.hasClass("wikitable"))
                .filter(e -> e.html().contains(">Driver</th>"))
                .filter(e -> !e.html().contains("Winner"))
                .filter(e -> !e.html().contains(">Rounds</th>"))
                .collect(toList());


        // For LMP1 just take first result
        Element table = collect.get(0);
        Optional<Element> first = table.children().stream().filter(c -> c instanceof Element).findFirst();
        if (first.isPresent()) {
            List<Element> rows = first.get().children().stream()
                    .filter(e -> e instanceof Element)
                    .filter(e -> !e.hasAttr("valign"))
                    .collect(toList());

            List<DriverResult> result = rows.stream()
                    .map(r -> parseRow(r, championship, year))
                    .filter(r -> r != null)
                    .collect(toList());
            return result;
        }
        return new ArrayList<>();
    }

    private DriverResult parseRow(Element element, Championship championship, String year) {

        try {
            String driverName = element.child(1)
                    .text()
                    .replace("(R)", "")
                    .replace("(RY)", "")
                    .replaceAll("\\[.*\\]", "")
                    .trim();
            return new DriverResult(driverName, Integer.parseInt(element.child(0).text()), championship, year);
        } catch (Exception e) {
            return null;
        }
    }

}
