package edu.iis.mto.time;

import org.joda.time.DateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OrderTest {

    private static Order testedOrder;

    @BeforeEach
    public void prepare() {
        testedOrder = new Order();
    }

    @Test
    public void metodaSubmitPowinnaZmienicStanObiektuOrderNaSUBMITTED() {
        testedOrder.submit(DateTime.now());

        assertEquals(Order.State.SUBMITTED, testedOrder.getOrderState());
    }

    @Test
    public void metodaAddItemPowinnaZmienicStanObiektuuOrderNaCREATED() {
        testedOrder.addItem(new OrderItem());
        assertEquals(Order.State.CREATED, testedOrder.getOrderState());
    }

    @Test
    public void metodaConfirmPowinnaZmienicStanObiektuOrderNaCONFIRMED() {
        testedOrder.submit(DateTime.now());
        testedOrder.confirm(DateTime.now().plusHours(1));

        assertEquals(Order.State.CONFIRMED, testedOrder.getOrderState());
    }

    @Test
    public void metodaRealizePowinnaZmienicStanObiektuOrderNaREALIZED() {
        testedOrder.submit(DateTime.now());
        testedOrder.confirm(DateTime.now().plusHours(1));
        testedOrder.realize();

        assertEquals(Order.State.REALIZED, testedOrder.getOrderState());
    }

    @Test
    public void metodaConfirmPowinnaRzucicWyjatkiemOrazZmienicStanNaCANCELLEDPrzyPodaniuDatyDalejNiz24HOdWykonaniaFunkcjiSubmit() {
        testedOrder.submit(DateTime.now());

        assertThrows(OrderExpiredException.class, () -> testedOrder.confirm(DateTime.now().plusHours(30)));
        assertEquals(Order.State.CANCELLED, testedOrder.getOrderState());
    }
}