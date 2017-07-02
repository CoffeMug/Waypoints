package utility;

import com.fasterxml.jackson.databind.ObjectMapper;
import domain.Waypoint;
import exception.WaypointsParserException;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@Slf4j
public class WaypointsParser {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static List<Waypoint> parsedWaypoints(String jsonPath) {
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new FileReader(jsonPath));
        } catch (FileNotFoundException e) {
            log.error("Json file not found in the path {}", jsonPath);
            throw new WaypointsParserException("Json file not found in the path %s!", jsonPath);
        }

        List<Waypoint> waypoints;

        try {
            waypoints = mapper.readValue(bufferedReader, mapper.getTypeFactory().constructCollectionType(List.class, Waypoint.class));
        } catch (IOException e) {
            log.error("Exception in reading json file from path {}", jsonPath);
            throw new WaypointsParserException("IO error while reading json file in the path %s!", jsonPath);
        }

        return waypoints;
    }
}
