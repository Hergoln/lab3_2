package edu.iis.mto.time;

import org.joda.time.DateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

	// TODO: czy metoda confirm poprawnie ustawia stan obiektu i rzucac wyjątkiem
	@Test
	public void metodaConfirmPowinnaZmienicStanObiektuOrderNaCONFIRMED() {
		testedOrder.submit(DateTime.now());
		testedOrder.confirm(DateTime.now().plusHours(1));

		assertEquals(Order.State.CONFIRMED, testedOrder.getOrderState());
	}
	// TODO: czy metoda realize ustawia stan obiektu Order na State.REALIZED
}