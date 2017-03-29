import com.google.inject.Guice;
import com.google.inject.Injector;
import domain.SuperlicenceScore;
import finder.SuperLicenceFinder;
import module.SuperLicenceModule;

import java.util.Comparator;
import java.util.List;
import java.util.Map;


public class Application {
    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new SuperLicenceModule());
        SuperLicenceFinder finder = injector.getInstance(SuperLicenceFinder.class);
        List<SuperlicenceScore> calculate = finder.calculate();
        calculate.stream()
                .sorted((x,y) -> y.getTotalScore().compareTo(x.getTotalScore()))
                .forEach(s -> System.out.println(s.getName() + "   :" + s.getTotalScore()));
    }
}
