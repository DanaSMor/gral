/*
 * GRAL: GRAphing Library for Java(R)
 *
 * (C) Copyright 2009-2012 Erich Seifert <dev[at]erichseifert.de>,
 * Michael Seifert <michael[at]erichseifert.de>
 *
 * This file is part of GRAL.
 *
 * GRAL is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * GRAL is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with GRAL.  If not, see <http://www.gnu.org/licenses/>.
 */
package de.erichseifert.gral.plots.axes;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class AxisTest {
	private static final double DELTA = 1e-15;
	private Axis axis;

	@Before
	public void setUp() {
		axis = new Axis(-5.0, 5.0);
	}

	@Test
	public void testMin() {
		assertEquals(-5.0, axis.getMin().doubleValue(), DELTA);
		axis.setMin(10.0);
		assertEquals(10.0, axis.getMin().doubleValue(), DELTA);
		// Test auto-scaling mode
		axis.setAutoscaled(true);
		assertEquals(10.0, axis.getMin().doubleValue(), DELTA);
	}

	@Test
	public void testMax() {
		assertEquals(5.0, axis.getMax().doubleValue(), DELTA);
		axis.setMax(10.0);
		assertEquals(10.0, axis.getMax().doubleValue(), DELTA);
		// Test auto-scaling mode
		axis.setAutoscaled(true);
		assertEquals(10.0, axis.getMax().doubleValue(), DELTA);
	}

	@Test
	public void testRange() {
		assertEquals(10.0, axis.getRange(), DELTA);
		axis.setRange(1.0, 3.0);
		assertEquals(2.0, axis.getRange(), DELTA);
		// Test auto-scaling mode
		axis.setAutoscaled(true);
		assertEquals(2.0, axis.getRange(), DELTA);
	}

	private static final class AxisListenerTest implements AxisListener {
		public Axis axis = null;
		public Number min = null;
		public Number max = null;

		public void rangeChanged(Axis axis, Number min, Number max) {
			this.axis = axis;
			this.min = min;
			this.max = max;
		}
	}

	@Test
	public void testAxisListeners() {
		AxisListenerTest l = new AxisListenerTest();

		axis.addAxisListener(l);
		axis.setRange(0.0, 1.0);
		assertEquals(axis, l.axis);
		assertEquals(0.0, l.min.doubleValue(), DELTA);
		assertEquals(1.0, l.max.doubleValue(), DELTA);

		axis.removeAxisListener(l);
		axis.setRange(2.0, 3.0);
		assertEquals(axis, l.axis);
		assertEquals(0.0, l.min.doubleValue(), DELTA);
		assertEquals(1.0, l.max.doubleValue(), DELTA);
	}

}
