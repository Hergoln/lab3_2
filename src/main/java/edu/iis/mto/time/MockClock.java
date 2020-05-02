package edu.iis.mto.time;

import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;

public class MockClock implements DateTimeUtils.MillisProvider {

    private long offset;

    @Override
    public long getMillis() {
        return DateTime.now().getMillis() + offset;
    }

    public void setOffset(long time) {
        this.offset = time;
    }

}
