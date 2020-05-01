

import javax.swing.SwingUtilities;

import controller.StereologyFractionLinesController;
import ij.IJ;
import ij.Prefs;
import ij.plugin.PlugIn;
import model.StereologyFractionLinesModel;




public class Lineal_Analyzer implements PlugIn {
	protected StereologyFractionLinesController stereologyFractionLinesController;
	
	public void startEstereology() {
		
		IJ.getImage();
		Prefs.blackBackground = true;
		Prefs.enhancedLineTool = true;
		IJ.setTool("line");
		this.stereologyFractionLinesController = new StereologyFractionLinesController();
		this.stereologyFractionLinesController.estereologyFractionLinesController();

		
		
		//this.estereologyController.EstereologyController();
		

	}
	
	public static void runMacro(String paramsArgs){
		
		
		
		
		StereologyFractionLinesModel stereologyFractionLinesModel = new StereologyFractionLinesModel();
		//String params = "20,Blue,true,false,false,false,0,999999,false,false";
		
		String arrayParams[] = new String[10];
		arrayParams = paramsArgs.split(",");
		if(arrayParams.length < 9){
			IJ.showMessage("Command Invalid");
		}
		else{
			//IJ.showMessage(arrayParams[1]);
			String cor = arrayParams[1];
			stereologyFractionLinesModel.setColor(stereologyFractionLinesModel.colorChose(cor));
			stereologyFractionLinesModel.setnumberOfLines(Integer.parseInt(arrayParams[0]));
		
			stereologyFractionLinesModel.drawLines(Boolean.valueOf(arrayParams[2]), Boolean.valueOf(arrayParams[3]), Boolean.valueOf(arrayParams[4]));
			stereologyFractionLinesModel.setRoiUnderSize(Integer.parseInt(arrayParams[6]));
			stereologyFractionLinesModel.setRoiAboveSize(Integer.parseInt(arrayParams[7]));
			stereologyFractionLinesModel.analyzeLines(Boolean.valueOf(arrayParams[2]), Boolean.valueOf(arrayParams[3]), Boolean.valueOf(arrayParams[8]), Boolean.valueOf(arrayParams[9]));
		}
	}
	
	public void run(String arg) {		
		 SwingUtilities.invokeLater(new Runnable(){ 
	          public void run(){
	        	  startEstereology();
	          }
	      });
	}
}