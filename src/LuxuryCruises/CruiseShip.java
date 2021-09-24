package LuxuryCruises;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Seth
 */
public class CruiseShip {
    private final Calendar departDate;
    private final Calendar arrivalDate;
    private final String boatName;
    private final float cost;
    private final String arrivalPort;
    private final String departPort;
    private final SimpleDateFormat fmt;

    public CruiseShip(String boatName, String departPort, Calendar departDate, String arrivalPort, Calendar arrivalDate, float cost) {
        this.departDate = departDate;
        this.arrivalDate = arrivalDate;
        this.boatName = boatName;
        this.cost = cost;
        this.arrivalPort = arrivalPort;
        this.departPort = departPort;
        fmt = new SimpleDateFormat("dd-MMM-yyyy");
        if(arrivalPort.equalsIgnoreCase(departPort))
            throw new IllegalArgumentException("CruiseShip constructor: ARRIVAL PORT "+ arrivalPort+" IS EQUAL TO DEPART PORT "+departPort);
        if(departDate.compareTo(arrivalDate)>0)
            throw new IllegalArgumentException("CruiseShip constructor: ARRIVAL DATE "+arrivalDate+" LATER THAN DEPART DATE "+departDate);
    }

    public Calendar getDepartDate() {
        return departDate;
    }

    public Calendar getArrivalDate() {
        return arrivalDate;
    }

    public String getBoatName() {
        return boatName;
    }

    public float getCost() {
        return cost;
    }

    public String getArrivalPort() {
        return arrivalPort;
    }

    public String getDepartPort() {
        return departPort;
    }
    @Override
    public String toString()
    {   fmt.setCalendar(departDate);
        String departFormatted = fmt.format(departDate.getTime());
        fmt.setCalendar(arrivalDate);
        String arrivalFormatted = fmt.format(arrivalDate.getTime());
        String s = ">>>>> BOAT :"+boatName+" COST : $"+cost+" <<<<<< "+
            "\nLEAVING "+departPort+" AT "+departFormatted+
            "\nARRIVING :"+arrivalPort+" AT "+arrivalFormatted;
        return s;
    }
    @Override
    public boolean equals(Object o)
    {
        if(o instanceof CruiseShip)
        {   CruiseShip s = (CruiseShip)o;
        
            return boatName.equals(s.boatName) && departDate.equals(s.departDate) 
                    && departPort.equals(s.departPort);
        }
        else return false;
    }

}
