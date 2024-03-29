package initialpkg;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;


import processing.core.PApplet;

/**
 * This StormView will be used as the main client that the user interacts with and where the data is displayed
 * 
 * @author Marc Pelve
 * @since JDK 8
 * @since June 14, 2016
 * @version 1.1
 *
 */
public class StormView extends JFrame implements ActionListener{
	
	private String [] categories = {"beginYearMonth", "beginDay", "beginTime", "episodeID", "eventID", "state", "stateFIPS", "eventType", "czType", "czName", "wfo", "timezone", "magnitude", "magnitudeType", "floodCause", "torFScale", "torLength", "torWidth", "torState", "torName", "beginLocation", "beginLatitude", "beginLongitude"};
	
	private JComboBox sortBy = new JComboBox(categories);
	private JComboBox sortBy2 = new JComboBox();
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	JPanel bottompanel;
	JPanel centerpanel;
	
	/**
	 * This is the default constructor that is called to display the client that displays visuals and allows the user interactivity
	 */
	StormView(){
		JPanel dropDown = new JPanel(new FlowLayout());
		centerpanel = new JPanel();
		bottompanel = new JPanel();
		
		PApplet centerSketch = new CenterPanel();
		CenterPanel bottomSketch = new LowerPanel();
		
		centerpanel.add(centerSketch);
		bottompanel.add(bottomSketch);
		centerSketch.init();
		bottomSketch.init();
		
		setLayout(new BorderLayout());
		
		setSize((int) (screenSize.getWidth() * 0.9), (int) (screenSize.getHeight() * 0.8));
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Storm Data Timeline");
		
		

		sortBy.setSelectedIndex(0);
		sortBy.addActionListener(this);
		sortBy2.addActionListener(this);
		dropDown.add(sortBy);
		dropDown.add(sortBy2);
		
		this.add(dropDown, BorderLayout.PAGE_START);
		this.add(centerpanel, BorderLayout.CENTER);
		this.add(bottompanel, BorderLayout.PAGE_END);

		
		

	}
	
	/**
	 * This method returns a list of strings that includes a unique term from the list of Storm data inputed, which is specified by the inputed type
	 * 
	 * @param array is the input array that contains the Storm data
	 * @param type is the type of data from Storm parameter to sort from
	 * @return the categories non repeated in an arraylist of strings
	 */
	public static ArrayList<String> returnNonRepeats(ArrayList<Storm> array, String type){
		ArrayList<String> nonRepeats = new ArrayList<String>();
		
		for (int i = 0; i < array.size(); i++){
			if (!(nonRepeats.contains(array.get(i).displayData(type)))){
				nonRepeats.add(array.get(i).displayData(type));
			} 
		}
		
		
		return nonRepeats;
		
	}

	
	/**
	 * This method detects for an action listener and carries out a function based off where the action comes from
	 */
	public void actionPerformed(ActionEvent e){
		if (e.getSource() == sortBy){
			JComboBox localSortBy = (JComboBox)e.getSource();
			String category = (String)localSortBy.getSelectedItem();
			
			ArrayList<String> categoryValue;
			
			switch (category.toLowerCase()){
			
			case "beginyearmonth":
				categoryValue = returnNonRepeats(StormController.getSortedBeginYearMonth(), "beginyearmonth");
				sortBy2.removeAllItems();
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i));
				}
				break;
				
			case "beginday":
				categoryValue = returnNonRepeats(StormController.getSortedBeginDay(), "beginday");
				sortBy2.removeAllItems();
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i));
				}
				break;
			
			case "begintime":
				categoryValue = returnNonRepeats(StormController.getSortedBeginTime(), "begintime");
				sortBy2.removeAllItems();
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i));
				}
				break;
				
				
			case "episodeid":
				categoryValue = returnNonRepeats(StormController.getSortedEpisodeID(), "episodeid");
				sortBy2.removeAllItems();
				for (int i = 0; i < StormController.getSortedEpisodeID().size(); i++){
					sortBy2.addItem(StormController.getSortedEpisodeID().get(i).displayData("episodeid"));
				}
				break;
				
			case "eventid":
				sortBy2.removeAllItems();
				for (int i = 0; i < StormController.getSortedEventID().size(); i++){
					sortBy2.addItem(StormController.getSortedEventID().get(i).displayData("eventid"));
				}
				break;
				
			case "state":
				categoryValue = returnNonRepeats(StormController.getSortedState(), "state");
				sortBy2.removeAllItems();
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i));
				}
				break;
			
			case "statefips":
				categoryValue = returnNonRepeats(StormController.getSortedStateFIPS(), "statefips");
				sortBy2.removeAllItems();
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i));
				}
				break;

				
			case "eventtype":
				categoryValue = returnNonRepeats(StormController.getSortedEventType(), "eventtype");
				sortBy2.removeAllItems();
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i));
				}
				break;
				
			case "cztype":
				categoryValue = returnNonRepeats(StormController.getSortedCzType(), "cztype");
				sortBy2.removeAllItems();
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i));
				}
				break;
				
				
			case "czName":
				categoryValue = returnNonRepeats(StormController.getSortedCzName(), "czName");
				sortBy2.removeAllItems();
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i));
				}
				break;
				
			case "wfo":
				categoryValue = returnNonRepeats(StormController.getSortedWfo(), "wfo");
				sortBy2.removeAllItems();
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i));
				}
				break;
				
							
			case "directdeaths":
				categoryValue = returnNonRepeats(StormController.getSortedDirectDeaths(), "directdeaths");
				sortBy2.removeAllItems();
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i));
				}
				break;
				
			case "indirectDeaths":
				categoryValue = returnNonRepeats(StormController.getSortedIndirectDeaths(), "indirectDeaths");
				sortBy2.removeAllItems();
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i));
				}
				break;

				
			case "magnitude":
				categoryValue = returnNonRepeats(StormController.getSortedMagnitude(), "magnitude");
				sortBy2.removeAllItems();
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i));
				}
				break;
				
			case "magnitudetype":
				categoryValue = returnNonRepeats(StormController.getSortedMagnitudeType(), "magnitudetype");
				sortBy2.removeAllItems();
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i));
				}
				break;
				
			case "floodcause":
				categoryValue = returnNonRepeats(StormController.getSortedFloodCause(), "floodcause");
				sortBy2.removeAllItems();
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i));
				}
				break;
				
				
			case "torfscale":
				categoryValue = returnNonRepeats(StormController.getsortedTorFSCale(), "torfscale");
				sortBy2.removeAllItems();
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i));
				}
				break;
				
			case "torlength":
				categoryValue = returnNonRepeats(StormController.getSortedTorLength(), "torlength");
				sortBy2.removeAllItems();
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i));
				}
				break;
			
			case "torwidth":
				categoryValue = returnNonRepeats(StormController.getSortedTorWidth(), "torwidth");
				sortBy2.removeAllItems();
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i));
				}
				break;

				
			case "torstate":
				categoryValue = returnNonRepeats(StormController.getSortedTorState(), "torstate");
				sortBy2.removeAllItems();
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i));
				}
				break;
				
				
			case "torname":
				categoryValue = returnNonRepeats(StormController.getSortedTorName(), "torname");
				sortBy2.removeAllItems();
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i));
				}
				break;
				

			case "beginlocation":
				categoryValue = returnNonRepeats(StormController.getSortedBeginLocation(), "beginlocation");
				sortBy2.removeAllItems();
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i));
				}
				break;
				
				
			case "beginlatitude":
				categoryValue = returnNonRepeats(StormController.getSortedBeginLatitude(), "beginlatitude");
				sortBy2.removeAllItems();
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i));
				}
				break;
				
			case "beginlongitude":
				categoryValue = returnNonRepeats(StormController.getSortedBeginLongitude(), "beginlongitude");
				sortBy2.removeAllItems();
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i));
				}
				break;
				
			}
		}

	}

}
