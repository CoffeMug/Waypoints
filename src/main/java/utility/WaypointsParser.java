package utility;

import com.fasterxml.jackson.databind.ObjectMapper;
import domain.Waypoint;
import exception.WaypointsParserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by amin on 2017-06-02.
 */
public class WaypointsParser {

    private final static Logger LOGGER = LoggerFactory.getLogger(WaypointsParser.class);

    private static List<Waypoint> waypoints = new ArrayList<Waypoint>();

    static ObjectMapper mapper = new ObjectMapper();

    public static List<Waypoint> parsedWaypoints(String jsonPath) {
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(jsonPath));
        } catch (FileNotFoundException e) {
            LOGGER.error("Json file not found in the path {}", jsonPath);
            throw new WaypointsParserException("Json file not found in the path %s!", jsonPath);
        }

        try {
            waypoints = mapper.readValue(bufferedReader, mapper.getTypeFactory().constructCollectionType(List.class, Waypoint.class));
        } catch (IOException e) {
            LOGGER.error("Exception in reading json file from path {}", jsonPath);
            throw new WaypointsParserException("IO error while reading json file in the path %s!", jsonPath);
        }

        return waypoints;
    }
}
