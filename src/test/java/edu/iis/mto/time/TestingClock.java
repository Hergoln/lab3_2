package edu.iis.mto.time;

import org.joda.time.DateTime;
import org.joda.time.Period;

public class TestingClock implements Clock {

    private Period periodToTravel;

    public void travelInTime(Period ms) {
        periodToTravel = ms;
    }

    public DateTime now() {
        return DateTime.now().plus(periodToTravel);
    }
}
