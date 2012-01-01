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
package de.erichseifert.gral.examples.pieplot;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.Random;

import de.erichseifert.gral.data.DataTable;
import de.erichseifert.gral.examples.ExamplePanel;
import de.erichseifert.gral.plots.PiePlot;
import de.erichseifert.gral.plots.PiePlot.PieSliceRenderer;
import de.erichseifert.gral.plots.colors.LinearGradient;
import de.erichseifert.gral.ui.InteractivePanel;
import de.erichseifert.gral.util.Insets2D;


public class SimplePiePlot extends ExamplePanel {
	private static final long serialVersionUID = 1L;
	private static final int SAMPLE_COUNT = 10;
	/** Instance to generate random data values. */
	private static Random random = new Random();

	@SuppressWarnings("unchecked")
	public SimplePiePlot() {
		// Create data
		DataTable data = new DataTable(Integer.class);
		for (int i = 0; i < SAMPLE_COUNT; i++) {
			int val = random.nextInt(8) + 2;
			data.add((random.nextDouble() <= 0.15) ? -val : val);
		}

		// Create new pie plot
		PiePlot plot = new PiePlot(data);

		// Format plot
		plot.setSetting(PiePlot.TITLE, getDescription());
		// Change relative size of pie
		plot.setSetting(PiePlot.RADIUS, 0.9);
		// Add some margin to the plot area
		plot.setInsets(new Insets2D.Double(20.0, 40.0, 40.0, 40.0));

		// Change relative size of inner region
		plot.getPointRenderer(data).setSetting(PieSliceRenderer.RADIUS_INNER, 0.4);
		// Change the width of gaps between segments
		plot.getPointRenderer(data).setSetting(PieSliceRenderer.GAP, 0.2);
		// Change the colors
		LinearGradient colors = new LinearGradient(COLOR1, COLOR2);
		plot.getPointRenderer(data).setSetting(PieSliceRenderer.COLORS, colors);
		// Show labels
		plot.getPointRenderer(data).setSetting(PieSliceRenderer.VALUE_DISPLAYED, true);
		plot.getPointRenderer(data).setSetting(PieSliceRenderer.VALUE_COLOR, Color.WHITE);
		plot.getPointRenderer(data).setSetting(PieSliceRenderer.VALUE_FONT, Font.decode(null).deriveFont(Font.BOLD));

		// Add plot to Swing component
		add(new InteractivePanel(plot), BorderLayout.CENTER);
	}

	@Override
	public String getTitle() {
		return "Donut plot";
	}

	@Override
	public String getDescription() {
		return String.format("Donut plot of %d random data values", SAMPLE_COUNT);
	}

	public static void main(String[] args) {
		new SimplePiePlot().showInFrame();
	}
}
