package wikipedia;

import domain.Championship;
import org.glassfish.jersey.client.JerseyClientBuilder;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;
import java.util.Optional;

import static wikipedia.WikipediaChampionshipMapper.mapKey;


public class WikipediaSender {
    private final Client client;
    private final String baseUrl = "https://en.wikipedia.org/wiki/";
    private Map<Championship, String> subUrls = WikipediaChampionshipMapper.fetch();
    private Map<String, String> specialUrls = WikipediaChampionshipMapper.fetchSpecial();

    public WikipediaSender() {
        this.client = JerseyClientBuilder.newClient();
    }

    public Optional<String> fetchResultsPage(Championship champName, String year) {
        String special = specialUrls.get(mapKey(champName, year));
        String subUrl = special == null ? subUrls.get(champName) : special;
        String url = baseUrl + year + "_" + subUrl;
        System.out.println(url);
        WebTarget target = client.target(url);
        Response response = target.request()
                .accept(MediaType.APPLICATION_XHTML_XML_TYPE)
                .get();
        System.out.println(response);
        if (response.getStatus() == 200) {
            return Optional.of(response.readEntity(String.class));
        }
        // TODO consider 302
        return Optional.empty();
    }
}
