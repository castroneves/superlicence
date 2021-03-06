package wikipedia;

import domain.Championship;

import java.util.HashMap;
import java.util.Map;

import static domain.Championship.*;


public class WikipediaChampionshipMapper {
    // TODO Refactor this to have series, link, startYEAr, endYEar......even if by a generator

    public static Map<Championship, String> fetch() {
        Map<Championship, String> result = new HashMap<>();
        result.put(GP2, "GP2_Series");
        result.put(INDYCAR, "IndyCar_Series");
        result.put(LMP1, "FIA_World_Endurance_Championship");
        result.put(WSR, "Formula_Renault_3.5_Series");
        result.put(EURO_F3, "FIA_Formula_3_European_Championship");
        result.put(FORMULA_E, "Formula_E_season");
        result.put(GP3, "GP3_Series");
        result.put(SUPER_FORMULA, "Super_Formula_Championship");
        result.put(WTCC, "World_Touring_Car_Championship");
        result.put(WRC, "World_Rally_Championship");
        result.put(DTM, "Deutsche_Tourenwagen_Masters");
        result.put(INDY_LIGHTS, "Indy_Lights_season");
        result.put(V8_SUPERCARS, "International_V8_Supercars_Championship");

        return result;
    }

    public static Map<String, String> fetchSpecial() {
        Map<String, String> result = new HashMap<>();
        result.put(mapKey(WSR, "2016"), "Formula_V8_3.5_Series");
        result.put(mapKey(INDY_LIGHTS, "2016"), "Indy_Lights");

        return result;
    }


    public static String mapKey(Championship championship, String year) {
        return championship.name() + year;
    }


    // TODO Build special cases map per series, Eg, F3000 years
}
