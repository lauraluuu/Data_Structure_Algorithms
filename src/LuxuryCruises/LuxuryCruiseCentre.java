/**
 * Student Name: Peifen Lu
 * Student ID: 18008550
 */
package LuxuryCruises;

import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author gkn3798
 */
public class LuxuryCruiseCentre {

    private Map<String, Set<CruiseShip>> portMap;

    public LuxuryCruiseCentre() {
        this.portMap = new HashMap<String, Set<CruiseShip>>();
    }

    public List<CruiseJourney> getPossibleJourneys(String startPort, Calendar startDate,
            String endPort) {
        List<CruiseJourney> oneList = new LinkedList<>();
        CruiseJourney oneJourney = new CruiseJourney();
        
        this.findPaths(startPort, startDate, endPort, oneJourney, oneList);

        return oneList;
    }

    /**
     * This add method adds a unique CruiseShip to the map
     * @param ship
     * @return 
     */
    public boolean add(CruiseShip ship) {
        boolean isContain = portMap.containsKey(ship.getDepartPort());

        if (!isContain) {
            portMap.put(ship.getDepartPort(), new HashSet<CruiseShip>());
        }

        portMap.get(ship.getDepartPort()).add(ship);
        return true;
    }

    private void findPaths(String departPort, Calendar departDate, String endPort,
            CruiseJourney currentJourney, List<CruiseJourney> journeyList) {

        Set<CruiseShip> shipSet = this.portMap.get(departPort);
        CruiseJourney oneJourney = currentJourney;

        if (shipSet != null) {
            Iterator it = shipSet.iterator();

            while (it.hasNext()) {
                CruiseShip oneShip = (CruiseShip) it.next();

                oneJourney.addCruise(oneShip);

                if (oneShip.getDepartDate().compareTo(departDate) < 0) {
                    oneJourney.removeLastTrip();
                    
                } else {
                    if (!oneShip.getArrivalPort().equals(endPort)) {
                        this.findPaths(oneShip.getArrivalPort(), oneShip.getArrivalDate(),
                                endPort, oneJourney, journeyList);
                    } else {
                        journeyList.add(oneJourney.cloneJourney());
                    }
                    oneJourney.removeLastTrip();
                }
            }
        }
    }
}
