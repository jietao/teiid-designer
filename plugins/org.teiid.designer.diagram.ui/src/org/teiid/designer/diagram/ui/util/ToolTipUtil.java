/*
 * JBoss, Home of Professional Open Source.
 *
 * See the LEGAL.txt file distributed with this work for information regarding copyright ownership and licensing.
 *
 * See the AUTHORS.txt file distributed with this work for a full listing of individual contributors.
 */
package org.teiid.designer.diagram.ui.util;

import java.util.Iterator;
import java.util.List;
import org.eclipse.draw2d.Border;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.swt.graphics.Font;

/**
 * @author BLaFond
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 *
 * @since 8.0
 */
public class ToolTipUtil {
	private static final Border TOOLTIP_BORDER = new MarginBorder(2, 2, 2, 2);
	private static Font toolTipFont = DiagramUiUtilities.getToolTipFont();

	public static IFigure createToolTip(final String toolTipString) {
		Figure baseFigure = new Figure();
		
		baseFigure.setBorder(TOOLTIP_BORDER);
		
		int maxWidth = 10;
		int totalHeight = 0;
		
		Label nextLabel = new Label();
		nextLabel.setFont(toolTipFont);
		nextLabel.setText(toolTipString);
		nextLabel.setPreferredSize(nextLabel.getPreferredSize().width + 5, nextLabel.getPreferredSize().height);
		nextLabel.setSize(nextLabel.getPreferredSize().width + 5, nextLabel.getPreferredSize().height);
		baseFigure.add(nextLabel);
		nextLabel.setLocation(new Point(0, totalHeight));
		maxWidth = Math.max(maxWidth, nextLabel.getSize().width);
		totalHeight += nextLabel.getSize().height;	
		
		totalHeight +=1;

		if( maxWidth >  400 ) {
			if( totalHeight > 100 )
				baseFigure.setPreferredSize(400, 100);
			else
				baseFigure.setPreferredSize(400, totalHeight);
		} else {
			if( totalHeight > 100 )
				baseFigure.setPreferredSize(maxWidth, 100);
			else
				baseFigure.setPreferredSize(maxWidth, totalHeight);
		}
		
		return baseFigure;
	}
	
	
	public static IFigure createToolTip(List toolTipStrings) {
		Figure baseFigure = new Figure();
		
		baseFigure.setBorder(TOOLTIP_BORDER);
		Iterator iter = toolTipStrings.iterator();
		
		int maxWidth = 10;
		String nextString = null;
		int totalHeight = 0;
		while( iter.hasNext() ) {
			nextString = (String)iter.next();
			
			Label nextLabel = new Label();
			nextLabel.setFont(toolTipFont);
			nextLabel.setText(nextString);
			nextLabel.setPreferredSize(nextLabel.getPreferredSize().width + 5, nextLabel.getPreferredSize().height);
			nextLabel.setSize(nextLabel.getPreferredSize().width + 5, nextLabel.getPreferredSize().height);
			baseFigure.add(nextLabel);
			nextLabel.setLocation(new Point(0, totalHeight));
			maxWidth = Math.max(maxWidth, nextLabel.getSize().width);
			totalHeight += nextLabel.getSize().height;
		}
		
		totalHeight +=1;
		int xLimit = 800;
		int yLimit = 200;
		if( maxWidth >  xLimit ) {
			if( totalHeight > yLimit )
				baseFigure.setPreferredSize(xLimit, yLimit);
			else
				baseFigure.setPreferredSize(xLimit, totalHeight);
		} else {
			if( totalHeight > yLimit )
				baseFigure.setPreferredSize(maxWidth, yLimit);
			else
				baseFigure.setPreferredSize(maxWidth, totalHeight);
		}
		
		return baseFigure;
	}

}
