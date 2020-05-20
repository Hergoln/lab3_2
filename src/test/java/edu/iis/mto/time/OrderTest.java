package edu.iis.mto.time;

import org.joda.time.Period;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OrderTest {

    private static Order testedOrder;
    private static TestingClock clock;

    @BeforeEach
    public void prepare() {
        clock = new TestingClock();
        testedOrder = new Order();

        testedOrder.withClock(clock);
    }

    @Test
    public void metodaSubmitPowinnaZmienicStanObiektuOrderNaSUBMITTED() {
        testedOrder.submit();

        assertEquals(Order.State.SUBMITTED, testedOrder.getOrderState());
    }

    @Test
    public void metodaAddItemPowinnaZmienicStanObiektuuOrderNaCREATED() {
        testedOrder.addItem(new OrderItem());
        assertEquals(Order.State.CREATED, testedOrder.getOrderState());
    }

    @Test
    public void metodaConfirmPowinnaZmienicStanObiektuOrderNaCONFIRMED() {
        testedOrder.submit();
        testedOrder.confirm();

        assertEquals(Order.State.CONFIRMED, testedOrder.getOrderState());
    }

    @Test
    public void metodaRealizePowinnaZmienicStanObiektuOrderNaREALIZED() {
        testedOrder.submit();
        testedOrder.confirm();
        testedOrder.realize();

        assertEquals(Order.State.REALIZED, testedOrder.getOrderState());
    }

    @Test
    public void metodaConfirmPowinnaRzucicWyjatkiemOrazZmienicStanNaCANCELLEDPrzyPodaniuDatyDalejNiz24HOdWykonaniaFunkcjiSubmit() {
        testedOrder.submit();
        clock.travelInTime(new Period().withDays(3));

        assertThrows(OrderExpiredException.class, () -> testedOrder.confirm());
        assertEquals(Order.State.CANCELLED, testedOrder.getOrderState());
    }
}