/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes or the window is resized.
 */

import acm.graphics.*;
import java.awt.event.*;
import java.util.*;
import java.awt.*;

public class NameSurferGraph extends GCanvas
	implements NameSurferConstants, ComponentListener {

	/**
	* Creates a new NameSurferGraph object that displays the data.
	*/
	public NameSurferGraph() {
		addComponentListener(this);
		update();
	}
	
	/**
	* Clears the list of name surfer entries stored inside this class.
	*/
	public void clear() {
		nameSurferEntries.clear();
		update();
	}
	
	/* Method: addEntry(entry) */
	/**
	* Adds a new NameSurferEntry to the list of entries on the display.
	* Note that this method does not actually draw the graph, but
	* simply stores the entry; the graph is drawn by calling update.
	*/
	public void addEntry(NameSurferEntry entry) {
		nameSurferEntries.add(entry);
		update();
	}
	
	
	
	/**
	* Updates the display image by deleting all the graphical objects
	* from the canvas and then reassembling the display according to
	* the list of entries. Your application must call update after
	* calling either clear or addEntry; update is also called whenever
	* the size of the canvas changes.
	*/
	public void update() {
		removeAll();
		
		//DRAW GRAPH
				int width = getWidth();
				int height = getHeight();
				for (int i = 0; i < NDECADES; i++) {
					add(new GLine(i*getWidth()/NDECADES,0,i*getWidth()/NDECADES, getHeight()));
					String date = Integer.toString(1900 + i*10);
					add(new GLabel(date, i*getWidth()/NDECADES, getHeight()));
				}
				add(new GLine(0, GRAPH_MARGIN_SIZE, getWidth(), GRAPH_MARGIN_SIZE));
				add(new GLine(0, getHeight() - GRAPH_MARGIN_SIZE, getWidth(), getHeight() - GRAPH_MARGIN_SIZE));
				
		//Draw namesurferentry lines
		for (int j = 0; j < nameSurferEntries.size(); j++) {
			int colorIndex = colorIndexer % 4;
			Color entryColor = null;
			switch (colorIndex) {
				case 0: entryColor = Color.BLACK; break;
				case 1: entryColor = Color.RED; break;
				case 2: entryColor = Color.BLUE; break;
				case 3: entryColor = Color.MAGENTA; break;
			}
			NameSurferEntry entry = nameSurferEntries.get(j);
			String name = entry.getName();
			int lastRank = 0;
			if (entry.getRank(0) != 0) {
				GLabel l = new GLabel(name + " " + entry.getRank(0), 0, entry.getRank(0)+GRAPH_MARGIN_SIZE);
				add(l);
				l.setColor(entryColor);
				lastRank = entry.getRank(0)+GRAPH_MARGIN_SIZE;
			}
			else {
				GLabel l = new GLabel(name + "*", 0, getHeight()-GRAPH_MARGIN_SIZE);
				add(l);
				l.setColor(entryColor);
				lastRank = getHeight()-GRAPH_MARGIN_SIZE;
			}
			for (int k = 1; k < NDECADES; k++) {
				int rank = entry.getRank(k);
				if (entry.getRank(k) != 0) {
					GLine li = new GLine((k-1)*getWidth()/NDECADES,lastRank + GRAPH_MARGIN_SIZE,k*getWidth()/NDECADES, rank + GRAPH_MARGIN_SIZE);
					add(li);
					li.setColor(entryColor);
					GLabel la = new GLabel(name + " " + rank, k*getWidth()/NDECADES, rank + GRAPH_MARGIN_SIZE);
					add(la);
					la.setColor(entryColor);
					lastRank = rank;
				}
				else {
					GLine li = new GLine((k-1)*getWidth()/NDECADES,lastRank,k*getWidth()/NDECADES, getHeight()-GRAPH_MARGIN_SIZE);
					add(li);
					li.setColor(entryColor);
					GLabel la = new GLabel(name + "*", k*getWidth()/NDECADES, getHeight()-GRAPH_MARGIN_SIZE);
					add(la);
					la.setColor(entryColor);
					lastRank = getHeight()-GRAPH_MARGIN_SIZE;
				}
			}
			colorIndexer = colorIndexer + 1;
		}
	}
	
	
	
	/* Implementation of the ComponentListener interface */
	public void componentHidden(ComponentEvent e) { }
	public void componentMoved(ComponentEvent e) { }
	public void componentResized(ComponentEvent e) { update(); }
	public void componentShown(ComponentEvent e) { }
	private ArrayList<NameSurferEntry> nameSurferEntries = new ArrayList<NameSurferEntry>();
	private int colorIndexer = 0;
}
