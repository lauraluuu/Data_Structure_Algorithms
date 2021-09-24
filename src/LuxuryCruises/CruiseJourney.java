/**
 * Student Name: Peifen Lu
 * Student ID: 18008550
 */
package LuxuryCruises;

import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author gkn3798
 */
public class CruiseJourney {

    private List<CruiseShip> shipList;

    public CruiseJourney(List<CruiseShip> aList) {
        this.shipList = aList;
    }

    public CruiseJourney() {
        this.shipList = new LinkedList<CruiseShip>();
    }

    /**
     * The containsPort method checks whether the port parameter is
     * in the journey
     * @param port
     * @return 
     */
    public boolean containsPort(String port) {
        boolean isContain = false;

        for (CruiseShip ship : shipList) {
            if (ship.getDepartPort().equals(port)) {
                isContain = true;
            } else if (ship.getArrivalPort().equals(port)) {
                isContain = true;
            }
        }

        return isContain;
    }

    /**
     * This removeLastTrip method removes the lastly added CruiseShip from the
     * current journey
     *
     * @return
     */
    public boolean removeLastTrip() {

        if (this.shipList.size() == 0) {
            return false;
        }
        this.shipList.remove(this.shipList.size() - 1);
        return true;
    }

    /**
     * The addCruise method adds a CruiseShip to the journey
     * @param trip
     * @return 
     */
    public boolean addCruise(CruiseShip trip) {

        if (!this.shipList.isEmpty()) {
            if (this.getEndPort().equals(trip.getDepartPort())) {
                if (this.getEndDate().compareTo(trip.getDepartDate()) <= 0) {
                    if (!containsPort(trip.getArrivalPort())) {
                        this.shipList.add(trip);
                        return true;
                    }
                }
            }
        }
        this.shipList.add(trip);
        return true;
    }

    public String getStartPort() {
        String startPort = "";
        if (this.shipList.size() == 0) {
            startPort = null;
        } else {
            startPort = this.shipList.get(0).getDepartPort();
        }
        return startPort;
    }
    
    public String getEndPort() {
        String endPort = "";
        if (this.shipList.size() == 0) {
            endPort = null;
        } else {
            endPort = this.shipList.get(this.shipList.size() - 1).getArrivalPort();
        }
        return endPort;
    }
    
    public Calendar getEndDate() {
        Calendar date = null;
        if (this.shipList.size() != 0) {
            date = this.shipList.get(this.shipList.size() - 1).getArrivalDate();
        }
        return date;
    }

    public Calendar getStartDate() {
        Calendar date = null;
        if (this.shipList.size() != 0) {
            date =  this.shipList.get(0).getDepartDate();
        }
        return date;
    }
    
    /**
     * This getTotalCost method returns the total cost of all CruiseShip trips
     * in this journey
     *
     * @return
     */
    public double getTotalCost() {
        double amount = 0;
        
        Iterator it = shipList.iterator();
        while (it.hasNext()){
            CruiseShip ship = (CruiseShip) it.next();
            amount += ship.getCost();
        }

        return amount;
    }

    public CruiseJourney cloneJourney() {
        CruiseJourney newJourney = new CruiseJourney();
        
        Iterator it = shipList.iterator();
        
        while (it.hasNext()){
            newJourney.shipList.add((CruiseShip) it.next());
        }
        return newJourney;
    }

    /**
     * This toString method prints out a String representation of all trips in
     * this journey and the total cost
     *
     * @return
     */
    public String toString() {
        String result = "";

        for (CruiseShip ship : shipList) {
            result += ship + "\n";
        }

        result += "Total Cost of this OPTION is: $" + this.getTotalCost();

        return result;
    }

    /**
     * This method returns the number of CruiseShips which comprise the journey
     *
     * @return
     */
    public int getNumberOfTrips() {
        return this.shipList.size();
    }

}
