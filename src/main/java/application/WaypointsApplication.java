package application;

import domain.OrganizedResult;
import domain.Waypoint;
import lombok.extern.slf4j.Slf4j;
import utility.WaypointsParser;

@Slf4j
class WaypointsApplication {
    
    public static void main (String[] args) {

        if(args.length != 1) {
            log.error("Wrong number of arguments, please provide the path to json file as argument.");
            System.exit(0);
        }

        OrganizedResult organizedResult = Waypoint.organizeWaypointsData(WaypointsParser.parsedWaypoints(args[0]));

        log.info("Distance speeding {} (km).", organizedResult.getDistanceSpeeding());
        log.info("Duration speeding {} (seconds).", organizedResult.getDurationSpeeding());
        log.info("Total distance traveled {} (km).", organizedResult.getTotalDistance());
        log.info("Total duration traveled {} (seconds).", organizedResult.getTotalDuration());

    }
}
