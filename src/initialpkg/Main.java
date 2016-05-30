package initialpkg;

import java.awt.*;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import com.opencsv.CSVReader;

import javax.swing.*;





/**
 * 
 * @author Marc Pelve
 * @author Andrew Seidel
 * @since May 5, 2016
 * @since JDK 8
 * @version 1.0
 *
 */
//Requires openCSV API 
//Took Seidel's progress bar, prompt, getString

public class Main {

	static ArrayList<Storm> fileData = new ArrayList<Storm>();

	public static void main(String[] args) {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception ex) {
			ex.printStackTrace();
		} 

	
		Thread t = new Thread(new Runnable (){

			public void run() {
				FileOpen.createAndShowGUI();

			}
		});


		Thread nt = new Thread(new Runnable () {
			public void run () {

				fileData = fileToStorm(FileOpen.getPath(), countLines(FileOpen.getPath()));

			}
		});

		t.start();



		while(FileOpen.getPath() == null){
			try {
				Thread.sleep(0);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		nt.start();

		while(fileData.size() == 0) {
			try {
				Thread.sleep(0);
			} catch (Exception e){
				e.printStackTrace();
			}
		}


		ArrayList<Storm> sortedData = mergeSort(fileData, "begintime");

		
		for (int i = 0; i < sortedData.size(); i++){
			System.out.println(sortedData.get(i).getDataInt("begintime"));
		} 



	}





	/**
	 * This method sorts an array of Storm objects based off the specified data type in a recursive-top down format
	 *
	 * @param array is the input array that needs to be sorted
	 * @param type is what the method is going to sort based off of
	 * @return the sorted array
	 */
	public static ArrayList<Storm> mergeSort(ArrayList<Storm> array, String type){
		if (array.size() <= 1) return array;

		int leftSize = array.size()/2;
		int rightSize;

		if(array.size() % 2 == 0) rightSize = leftSize;
		else rightSize = leftSize + 1;

		ArrayList<Storm> firstHalf = new ArrayList<Storm>(array.subList(0, leftSize));
		ArrayList<Storm> secondHalf = new ArrayList<Storm>(array.subList(rightSize, array.size()));

		firstHalf = mergeSort(firstHalf, type);
		secondHalf = mergeSort(secondHalf, type);

		return merge(firstHalf, secondHalf, type);
	}

	/**
	 * This method merges the first half split with the second half split in the mergeSort process
	 *
	 * @param array1 is the first half of the split
	 * @param array2 is the second half of the split
	 * @return array3 is the finalized merged array of the first half and second half 
	 */
	public static ArrayList<Storm> merge(ArrayList<Storm> array1, ArrayList<Storm> array2, String type){
		ArrayList<Storm> array3 =  new ArrayList<Storm>();

		String checkType = fileData.get(0).checkType(type).toLowerCase();

		if (checkType.equals("string")){

			while (array1.size() > 0 && array2.size() > 0){

				if (compareData(array1.get(0).getDataString(type), array2.get(0).getDataString(type)) >= 0){
					array3.add(array2.get(0));
					array2.remove(0);
				} else {
					array3.add(array1.get(0));
					array1.remove(0);
				}
			}

			while (array1.size() > 0){
				array3.add(array1.get(0));
				array1.remove(0);
			}

			while (array2.size() > 0){
				array3.add(array2.get(0));
				array2.remove(0);
			}
		}

		else if (checkType.equals("integer")){
			while (array1.size() > 0 && array2.size() > 0){

				if (compareData(array1.get(0).getDataInt(type), array2.get(0).getDataInt(type)) >= 0){
					array3.add(array2.get(0));
					array2.remove(0);
				} else {
					array3.add(array1.get(0));
					array1.remove(0);
				}
			}

			while (array1.size() > 0){
				array3.add(array1.get(0));
				array1.remove(0);
			}

			while (array2.size() > 0){
				array3.add(array2.get(0));
				array2.remove(0);
			}
		}

		else if (checkType.equals("double")){
			while (array1.size() > 0 && array2.size() > 0){

				if (compareData(array1.get(0).getDataDouble(type), array2.get(0).getDataDouble(type)) >= 0){
					array3.add(array2.get(0));
					array2.remove(0);
				} else {
					array3.add(array1.get(0));
					array1.remove(0);
				}
			}

			while (array1.size() > 0){
				array3.add(array1.get(0));
				array1.remove(0);
			}

			while (array2.size() > 0){
				array3.add(array2.get(0));
				array2.remove(0);
			}
		}

		else if (checkType.equals("character")){
			while (array1.size() > 0 && array2.size() > 0){

				if (compareData(array1.get(0).getDataChar(type), array2.get(0).getDataChar(type)) >= 0){
					array3.add(array2.get(0));
					array2.remove(0);
				} else {
					array3.add(array1.get(0));
					array1.remove(0);
				}
			}

			while (array1.size() > 0){
				array3.add(array1.get(0));
				array1.remove(0);
			}

			while (array2.size() > 0){
				array3.add(array2.get(0));
				array2.remove(0);
			}
		}




		return array3;
	}


	public static int compareData(int first, int second){
		return Integer.compare(first, second);
	}

	public static int compareData(double first, double second){
		return Double.compare(first, second);
	}

	public static int compareData(String first, String second) {
		return first.compareTo(second);
	}

	/**
	 * This method takes in a file name (if path relative) or a path with then number of lines and creates a Storm object based off each line of the CSV file using openCSV API
	 * 
	 * @param fileName is the name of the file (if the path is relative) or the path itself
	 * @param lines the number of lines in the file that is inputed that will most likely be calculated by countLines method
	 * @return the array of Storm objects with all relevant parameters inputed
	 */
	public static ArrayList<Storm> fileToStorm(String fileName, int lines){
		ArrayList<Storm> array = new ArrayList<Storm>();

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
			ex.printStackTrace();
		}

		final JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(1000, 100);
		frame.setVisible(true);
		frame.setTitle("File data to Storm object");
		final DefaultBoundedRangeModel model = new DefaultBoundedRangeModel();
		final JProgressBar progressBar = new JProgressBar(model);
		progressBar.setStringPainted(true);
		frame.add(progressBar);
		progressBar.setValue(0);
		int progressValue = 0;

		try (CSVReader csvr = new CSVReader(new InputStreamReader(new FileInputStream(fileName)))){

			String [] thisLine = null;


			while ((thisLine = csvr.readNext()) != null){

				if (progressValue != 0){

					array.add(new Storm(thisLine[0], checkParseInt(thisLine[1]), checkParseInt(thisLine[2]), thisLine[3], checkParseInt(thisLine[4]), checkParseInt(thisLine[5]), 
							checkParseInt(thisLine[6]), checkParseInt(thisLine[7]), thisLine[8], checkParseInt(thisLine[9]), checkParseInt(thisLine[10]), thisLine[11], thisLine[12], 
							thisLine[13].charAt(0), checkParseInt(thisLine[14]), thisLine[15], thisLine[16], thisLine[17], thisLine[18], thisLine[19], checkParseInt(thisLine[20]), 
							checkParseInt(thisLine[21]), checkParseInt(thisLine[22]), checkParseInt(thisLine[23]), thisLine[24], thisLine[25], thisLine[26], checkParseDouble(thisLine[27]),
							thisLine[28], thisLine[29], thisLine[30], thisLine[31], checkParseDouble(thisLine[32]), checkParseDouble(thisLine[33]), thisLine[34], thisLine[35], 
							checkParseInt(thisLine[36]), thisLine[37], checkParseDouble(thisLine[38]), thisLine[39], thisLine[40], checkParseDouble(thisLine[41]), thisLine[42], thisLine[43], 
							checkParseDouble(thisLine[44]), checkParseDouble(thisLine[45]), checkParseDouble(thisLine[46]), checkParseDouble(thisLine[47]), thisLine[48], thisLine[49], thisLine[50], 
							thisLine[51], thisLine[52], thisLine[53], thisLine[54], thisLine[55], thisLine[56], thisLine[57]));
				}


				try {
					progressValue++;
					final int setValue = (int)((1.0 * progressValue)/(lines * 1.0)*100.0);	//updates progress bar and shows
					SwingUtilities.invokeLater(new Runnable() {								//the text.  This runs in another 
						public void run() {													//Thread.
							progressBar.setValue(setValue);
							progressBar.setString("Reading file - [" + setValue + "%]");
						}
					});

				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			SwingUtilities.invokeLater(new Runnable() {
				public void run(){
					frame.dispose();
				}
			});

		} catch (Exception e){
			e.printStackTrace();
		}


		return array;
	}


	/**
	 * This method counts the number of lines in the file using openCSV API
	 * Also includes a loading bar and line counter to visualize the process
	 * 
	 * @param fileName is the input file name if opened relatively or path 
	 * @return the number of lines counted (int)
	 */
	public static int countLines(String fileName){
		int progressValue = 0;

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
		}


		//Initialize JFrame, JPanel, JLabel
		final JFrame frame = new JFrame("Line Counter");
		final JPanel panel = new JPanel();
		final JLabel counter = new JLabel();

		//Label (Line count)
		counter.setText("Number of lines: " + progressValue);

		//Panel (Contains label)
		panel.setLayout(new GridBagLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		panel.add(counter);

		//Frame
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(250, 75);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.add(panel);


		try {
			CSVReader csvr = new CSVReader(new FileReader(fileName));
			while(csvr.readNext() != null){
				progressValue++;
				counter.setText("Number of lines: " + progressValue); //update counter
			}
			csvr.close();

			SwingUtilities.invokeLater(new Runnable() {
				public void run(){
					frame.dispose();
				}
			});

			return (int) csvr.getRecordsRead();
		} catch (Exception e){
			e.printStackTrace();
		}


		return 0;
	}

	/**
	 * This method returns the integer value of the string if it is not empty, if it is empty the method returns -1
	 * 
	 * @param input the input string that needs to be changed into an integer
	 * @return the value -1 or the integer value of the inputed string (int)
	 */
	public static int checkParseInt(String input){
		if (input.equals("")){ return -1; }
		else { return Integer.parseInt(input); }
	}




	/**
	 * This method returns the double value of the string if it is not empty, if it is empty the method returns -1
	 * 
	 * @param input the input string that needs to be changed into an double
	 * @return the value -1 or the double value of the inputed string (double)
	 */
	public static double checkParseDouble(String input){
		if (input.equals("")){ return -1; }
		else { return Double.parseDouble(input); }
	}

}
