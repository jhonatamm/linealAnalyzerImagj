package model;

import java.awt.Color;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import java.net.URISyntaxException;

import ij.IJ;
import ij.ImagePlus;
import ij.gui.ImageCanvas;
import ij.gui.Line;
import ij.gui.Overlay;
import ij.gui.Roi;
import ij.io.*;
import ij.measure.Calibration;
import ij.measure.ResultsTable;
import ij.plugin.frame.Recorder;
import ij.plugin.frame.RoiManager;

public class StereologyFractionLinesModel {
	public int numberOfLines;
	public int roiUnderSize = 0;
	public Integer roiAboveSize = 9999999;
	protected Lines lines;
	protected AnalyzeFractionLinear analyzeFractionLinear;
	// protected ChartEstereologyView chart;
	public Color color = Color.red;
	boolean grid = false, bold = false;
	ImagePlus imp;
	public String colorname = "Red";
	protected RoiManager roimanager = RoiManager.getInstance();
	public int pixelBlackorWhite;

	public void setBold(boolean bold) {
		this.bold = bold;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public void openHelpLines() {
		Desktop desktop = null;
		desktop = Desktop.getDesktop();
		// URI uri = null;

		try {
			// uri = new
			// URI(getClass().getResource("/image/index.html").toString());

			File file = new File(getClass().getResource("/image/index.html").toURI());
			// File temp = File.createTempFile("index",".html", new
			// File("C:/teste/"));
			// uri = new
			// URI(getClass().getClassLoader().getResource("/image/index.html").toString());
			// temp.deleteOnExit();
			desktop.open(file);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public StereologyFractionLinesModel() {
		this.analyzeFractionLinear = new AnalyzeFractionLinear();
		// IJ.showMessage("por que esse bug.?");
	}

	public void setnumberOfLines(int numberOfLines) {
		this.numberOfLines = numberOfLines + 1;
		if (this.lines != null) {
			this.lines = null;
		}
		this.lines = new Lines();
	}

	public RoiManager roiManager() {
		IJ.run("Remove Overlay", "");

		if (roimanager == null) {

			roimanager = new RoiManager();
		} else {
			this.analyzeFractionLinear.releaseRoiManager();
			roimanager.runCommand("Show None");
			roimanager.close();
			roimanager = new RoiManager();
		}
		return this.roimanager;
	}

	public Boolean saveResults() {
		// SaveDialog sv = new SaveDialog("", "", "");
		String directoy;
		if (analyzeFractionLinear.getanalyzed() == true) {
			try {
				if (IJ.showMessageWithCancel("Attention", "Click ok to Save?") == true) {
					DirectoryChooser dc = new DirectoryChooser("Chose directory to save save as");
					directoy = dc.getDirectory();
					IJ.saveAs(imp, "PNG", directoy + imp.getShortTitle() + ".png");
					this.analyzeFractionLinear.getfractionlineartable()
							.saveAs(directoy + imp.getShortTitle() + " - LinearFraction.xls");
					this.analyzeFractionLinear.getroitable().saveAs(directoy + imp.getShortTitle() + " - RoiTable.xls");
				}
				return true;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				IJ.showMessage(e.toString());
				return false;
			}
		}
		else{
			return false;
		}
	}

	public void setPixelBlackorWhite() {
		if (this.pixelBlackorWhite == 0) {
			this.pixelBlackorWhite = 255;
		} else {
			this.pixelBlackorWhite = 0;
		}

	}

	public boolean managerImage() {
		// IJ.showMessage(Integer.toString(IJ.getImage().getBitDepth()));;
		if ((IJ.getImage().getBitDepth() == 8) && (IJ.getImage().isThreshold() == true)) {
			if (imp == null) {
				imp = IJ.getImage();
				// IJ.showMessage("passo1");
				// saveResults();
				return true;
			}
			if (IJ.getImage() != imp) {
				IJ.beep();
				// IJ.showMessageWithCancel("Attention working with new
				// image!");
				if (IJ.showMessageWithCancel("Attention", "Do you Want to Work with a new image!?") == true) {
					if (this.analyzeFractionLinear.getroimanager() != null) {
						// IJ.run("Hide Overlay", "");
						// try { Thread.sleep (1000); } catch
						// (InterruptedException ex) {}
						//IJ.showMessage("chegamos aqui"+this.analyzeFractionLinear.getanalyzed());
						saveResults();
						this.analyzeFractionLinear.getroimanager().close();
						// this.roimanager.runCommand("Show All");
						this.roimanager = null;
						imp = IJ.getImage();
						this.lines = new Lines();
						this.analyzeFractionLinear = new AnalyzeFractionLinear();
								
						return true;
					} else {
						try {
							Thread.sleep(1000);
						} catch (InterruptedException ex) {
						}
						this.roimanager.close();
						imp = IJ.getImage();
						return true;
					}
				} else {
					return false;
				}

			} else {
				return true;
			}
		} else {
			IJ.beep();
			IJ.showMessage("The images Must be thresholded using " + "image>Adjust>Threshold ");
			return false;
		}

	}

	public void setRoiUnderSize(int roiUnderSize) {
		this.roiUnderSize = roiUnderSize;
	}

	public void setRoiAboveSize(int roiAboveSize) {
		this.roiAboveSize = roiAboveSize;
	}

	public void chooseColor(String colorname, boolean horizontal, boolean vertical, boolean both) {
		this.grid = both;
		this.color = colorChose(colorname);
		this.drawLines(horizontal, vertical, both);
	}

	public void hideOverlay() {
		if (this.imp == IJ.getImage()) {
			if (this.analyzeFractionLinear.getroishow() == true) {
				IJ.run("Hide Overlay", "");
				this.analyzeFractionLinear.showInterceptos();
			} else {
				IJ.run("Hide Overlay", "");
			}
		}
	}

	public void showOverlay() {
		if (this.imp == IJ.getImage()) {
			IJ.run("Show Overlay", "");
			// this.analyzeFractionLinear.showInterceptos();
		}
	}

	public void hideInterceptos() {
		if (this.imp == IJ.getImage()) {
			this.analyzeFractionLinear.hideIntercptos();
		}
	}

	public void showInterceptos() {
		if (this.imp == IJ.getImage()) {
			this.analyzeFractionLinear.showInterceptos();
		}
	}

	public Color colorChose(String colorname) {
		this.colorname = colorname;
		if (colorname.equals("Blue")) {
			return Color.blue;
		}
		else if (colorname.equals("Red")) {
			return Color.red;
		}
		else if (colorname.equals("Green")) {
			return Color.green;
		}
		else if (colorname.equals("Yellow")) {
			return Color.yellow;
		}
		else if (colorname.equals("Magenta")) {
			return Color.magenta;
		}
		else if (colorname.equals("Black")) {
			return Color.black;
		}
		else if (colorname.equals("Cyan")) {
			return Color.cyan;
		}
		else if (colorname.equals("brown")) {
			return new Color(165, 42, 42);
		}
		else if (colorname.equals("gold")) {
			return new Color(255, 215, 0);
		}
		else if (colorname.equals("Orange")) {
			return Color.orange;
		}
		else if (colorname.equals("navy")) {
			return new Color(0, 0, 128);
		}
		else if (colorname.equals("purple")) {
			return new Color(128, 0, 128);
		} 
		else {
			return Color.yellow;
		}
	}

	public void drawLines(boolean horizontal, boolean vertical, boolean both) {
		this.grid = both;
		
		if (horizontal == true) {
			if (managerImage() == true) {
				this.analyzeFractionLinear.setanalyzed(false);
				this.lines.drawHorizontalLines(this.numberOfLines, this.color, roiManager());
			}
		}
		if (vertical == true) {
			if (managerImage() == true) {
				this.analyzeFractionLinear.setanalyzed(false);
				this.lines.drawVerticalLines(this.numberOfLines, this.color, both, roiManager());
			}
		}
		if (both == true) {
			if (managerImage() == true) {
				this.analyzeFractionLinear.setanalyzed(false);
				RoiManager roimanagerr = roiManager();
				this.lines.drawHorizontalLines(this.numberOfLines, this.color, roimanagerr);
				this.lines.drawVerticalLines(this.numberOfLines, this.color, both, roimanagerr);
				this.roimanager = roimanagerr;
			}
		}

	}

	public void drawLinesbold() {
		this.lines.drawLinesbold();
	}

	public void drawLinesNomal() {
		this.lines.drawLinesnromal();
	}

	public void drawLinesBoldOrNot() {
		if (this.bold == true) {
			this.lines.drawLinesbold();
		} else {
			this.lines.drawLinesnromal();
		}
	}

	public void setAnalyzed(boolean analyzed) {
		this.analyzeFractionLinear.setanalyzed(analyzed);
	}

	public void analyzeLines(Boolean horizontal, Boolean vertical, Boolean chckedge, Boolean checkedgefactor) {
		if (managerImage() == true) {
			if ((this.roimanager != null) && (this.analyzeFractionLinear.getanalyzed() == false)) {
				// IJ.showMessage(Integer.toString(this.roimanager.getCount()));

				this.lines.saveOverlay(this.roimanager);
				IJ.run("Overlay Options...", "stroke=" + colorname + " width=0 fill=none set");
				// IJ.showMessage("teste 1 fail");
				this.analyzeFractionLinear.analyzeLines(this.numberOfLines, this.roiUnderSize, this.roiAboveSize,
						horizontal, vertical, chckedge, this.color, checkedgefactor, this.pixelBlackorWhite);
			} else if (this.analyzeFractionLinear.getanalyzed() == true) {
				this.analyzeFractionLinear.analyzeLines(this.numberOfLines, this.roiUnderSize, this.roiAboveSize,
						horizontal, vertical, chckedge, this.color, checkedgefactor, this.pixelBlackorWhite);
			}

			else {
				// IJ.showMessage("teste 3");
				// this.lines = new Lines();
				if ((horizontal == false) && (vertical == false)) {
					drawLines(horizontal, vertical, true);
				} else {
					setnumberOfLines(numberOfLines);
					drawLines(horizontal, vertical, false);
				}

				// try { Thread.sleep (1000); } catch (InterruptedException ex)
				// {}
				this.lines.saveOverlay(this.roimanager);
				IJ.run("Overlay Options...", "stroke=" + colorname + " width=0 fill=none set");
				// IJ.showMessage("teste 2 fail");
				this.analyzeFractionLinear.analyzeLines(this.numberOfLines, this.roiUnderSize, this.roiAboveSize,
						horizontal, vertical, chckedge, this.color, checkedgefactor, this.pixelBlackorWhite);
			}
			//IJ.showMessage("chegamos aqui"+this.analyzeFractionLinear.getanalyzed());
			if (Recorder.record) {
				String command = "call('Lineal_Analyzer.runMacro','" + this.numberOfLines + "," + this.colorname + ","
						+ horizontal + "," + vertical + "," + this.grid + "," + this.bold + "," + this.roiUnderSize
						+ "," + this.roiAboveSize + "," + chckedge + "," + checkedgefactor + "');";
				Recorder.recordString(command);
			}

		}

	}

}

// --------------------lines draw
// class----------------------------------------///
class Lines {
	protected RoiManager roimanager = RoiManager.getInstance();
	Roi roi;
	boolean showline = false;
	boolean drawline = true;
	ImagePlus imp = IJ.getImage();
	int height = imp.getHeight();
	int width = imp.getWidth();

	public void saveOverlay(RoiManager roimanager) {
		roimanager.moveRoisToOverlay(imp);
		roimanager.close();
	}

	public void drawHorizontalLines(int numberOfLines, Color color, RoiManager roimanager) {
		;

		/*
		 * if(showline == true){ roimanager.runCommand("Show None");
		 * //roimanager.setVisible(false); ///roimanager.reset();
		 * showline=false; return; }
		 */

		// ImagePlus imp2 =IJ.getImage();
		// imp2 =imp.duplicate();

		float spacelines = (height / (float) numberOfLines);
		float acumulatesespacelines = spacelines;
		int counterline = 0;
		while (acumulatesespacelines < height - 1) {
			roi = (new Line(0, acumulatesespacelines, width, acumulatesespacelines));
			roi.setStrokeColor(color);
			roimanager.addRoi(roi);
			roimanager.select(counterline);
			acumulatesespacelines += spacelines;
			roimanager.runCommand("Rename", "Line " + Integer.toString(counterline + 1));
			// roimanager.runCommand("Set Color",color);
			roi = roimanager.getRoi(counterline);
			counterline++;
			showline = true;
			drawline = false;
		}
		roimanager.runCommand("Show All");
		// roimanager.setVisible(true);
	}

	// IJ.showMessage(Float.toString(spacelines));

	// imp2.setOverlay(overlay);

	// this.analyzeFractionLinear.setOverlay(this.roimanager);

	public void drawLinesbold() {
		IJ.run(imp, "Line Width...", "line=2");
	}

	public void drawLinesnromal() {
		IJ.run(imp, "Line Width...", "line=1");
	}

	public void drawVerticalLines(int numberOfLines, Color color, boolean both, RoiManager roimanager) {
		int counterline = 0;
		if (both == true) {
			counterline = roimanager.getCount();
		}

		float spacelines = ((float) width / (float) numberOfLines);
		float acumulatesespacelines = spacelines;

		while (acumulatesespacelines < width - 1) {
			roi = (new Line(acumulatesespacelines, 0, acumulatesespacelines, height));
			roi.setStrokeColor(color);
			roimanager.addRoi(roi);
			roimanager.select(counterline);
			acumulatesespacelines += spacelines;
			roimanager.runCommand("Rename", "Line " + Integer.toString(counterline + 1));
			counterline++;
		}
		// IJ.showMessage(Float.toString(spacelines));
		roimanager.runCommand("Show All");

	}
}

// ---------------------------------------lines analyzer
// class-----------------------------------///
class AnalyzeFractionLinear {
	public Roi roi;
	public Overlay overlay;
	protected RoiManager roimanager; // RoiManager.getInstance();
	private boolean roishow;
	private boolean analyzed = false;
	private int totalanalyzedroi = 0;
	ResultsTable summaryTable, resultsTable, resultsTableClone;
	ImagePlus imp = IJ.getImage();

	// ImagePlus imp = IJ.getImage();
	// int height = imp.getHeight();
	// int width = imp.getWidth();
	// public void setOverlay(RoiManager roimanager2){
	// this.roimanager = roimanager2;
	// }
	public void releaseRoiManager() {
		if (roimanager != null) {
			roimanager.dispose();
			roimanager.close();
			roimanager = new RoiManager();
		}
	}

	public void analyzeLines(int numberOfLines, int roiUnderSize, int roiAboveSize, boolean horiZontal,
			boolean verTical, boolean chckroiedg, Color colorr, boolean checkedgefactor, int pixelblackorwhite) {
		//Calibration cal = imp.getCalibration();
		if (analyzed == false) {
			summaryTable = new ResultsTable();
			resultsTable = new ResultsTable();
			ImagePlus imp = IJ.getImage();
			int height = imp.getHeight();
			int width = imp.getWidth();
			Color color = colorr;
			if (roimanager == null)
				roimanager = new RoiManager();
			boolean detectedroi = false, horizontal = horiZontal, vertical = verTical, chckedge = chckroiedg;
			int roidetectedstart = 0, roidetectedend, pixeltotal = 0, countRoi = 0, intersections =0;
			double spaceline = height / (double) (numberOfLines), accumulatespaceline = spaceline, fractionline,
					pixelsinroi = 0, x = 1, roifactorcount = 0;
			double verticalspaceline = width / (double) (numberOfLines), verticalaccumlatespaceline = verticalspaceline,
					sizemedium,interLinesLength,interTestLine;
			int[] checkpixelcolor;
			// Roi roi[];
			// ---------color---------------//
			if (colorr == Color.red) {
				color = Color.GREEN;
			} else {
				color = Color.RED;
			}
			// -----------roimanager---------------//
			if (roimanager != null) {
				roimanager.dispose();
				roimanager.close();
				roimanager = new RoiManager();
			} else {
				roimanager = new RoiManager();
			}
			// -------------Both----------------//
			if ((vertical == false) && (horizontal == false)) {
				vertical = true;
				horizontal = true;
			}
			// scale set///
			Calibration cal = imp.getCalibration();
			

			// -------Horizontal function------//
			if (horizontal == true) {
				while (accumulatespaceline < height - 1) {
					for (int pixelcounter = 0; pixelcounter <= width; pixelcounter++) {
						checkpixelcolor = imp.getPixel(pixelcounter, (int) accumulatespaceline);
						if ((checkpixelcolor[0] == pixelblackorwhite) && (detectedroi == false)) {
							detectedroi = true;
							roidetectedstart = pixelcounter;

						}
						if (((detectedroi == true) && (checkpixelcolor[0] != pixelblackorwhite))
								|| ((checkpixelcolor[0] == pixelblackorwhite) && (pixelcounter == width))) {
							roidetectedend = pixelcounter;

							// pixelsinroi=pixelsinroi+(roidetectedend-roidetectedstart);
							detectedroi = false;
							if (chckedge == false) {
								if (((roidetectedend - roidetectedstart) > roiUnderSize)
										&& ((roidetectedend - roidetectedstart) < roiAboveSize)) {
									roi = new Line(roidetectedstart, accumulatespaceline, roidetectedend,
											accumulatespaceline);
									roi.setStrokeColor(color);
									roimanager.addRoi(roi);
									roimanager.select(countRoi);
									roimanager.runCommand("Rename", "automatic" + Integer.toString(countRoi + 1));
									if ((checkedgefactor == true)
											&& ((roidetectedstart == 0) || (roidetectedend == width))) {
										x = 0.5;
										roifactorcount += 0.5;
									} else {

										x = 1;
									}
									//--------- count intersections----------------//
									if((roidetectedstart == 0) || (roidetectedend == width)){
										
										intersections = intersections +1;
									}
									else{
										intersections = intersections +2;
									}
									
									
									pixelsinroi = pixelsinroi + (roi.getLength() * x);
									// ----------creates roi tables-----//
									resultsTable.incrementCounter();
									resultsTable.addValue("Index",Integer.toString(countRoi + 1));
									resultsTable.addValue("Method", "automatic ");
									resultsTable.addValue("Length", roi.getLength()*cal.pixelWidth);
									resultsTable.addValue("Weight", x);
									countRoi++;
								}
							}
							if (chckedge == true) {
								if ((roidetectedstart != 0) && (roidetectedend != width)) {
									if (((roidetectedend - roidetectedstart) > roiUnderSize)
											&& ((roidetectedend - roidetectedstart) < roiAboveSize)) {
										roi = new Line(roidetectedstart, accumulatespaceline, roidetectedend,
												accumulatespaceline);
										roi.setStrokeColor(color);
										roimanager.addRoi(roi);
										roimanager.select(countRoi);
										roimanager.runCommand("Rename", "automatic " + Integer.toString(countRoi + 1));
										
										pixelsinroi = pixelsinroi + roi.getLength();
										intersections = intersections +2;
										// ----------creates roi tables-----//
										resultsTable.incrementCounter();
										resultsTable.addValue("Index",Integer.toString(countRoi + 1));
										resultsTable.addValue("Method", "automatic ");
										
										resultsTable.addValue("Length", roi.getLength()*cal.pixelWidth);
										countRoi++;
									}
								}
							}

						}
						pixeltotal++;
					}
					pixeltotal--;
					accumulatespaceline += spaceline;
				}
			}
			// ---------- -------vertical-----------//
			if (vertical == true) {
				detectedroi = false;
				while (verticalaccumlatespaceline < width - 1) {
					for (int pixelcounter = 0; pixelcounter <= height; pixelcounter++) {
						checkpixelcolor = imp.getPixel((int) verticalaccumlatespaceline, pixelcounter);
						if ((checkpixelcolor[0] == pixelblackorwhite) && (detectedroi == false)) {
							detectedroi = true;
							roidetectedstart = pixelcounter;
						}
						if (((detectedroi == true) && (checkpixelcolor[0] != pixelblackorwhite))
								|| ((checkpixelcolor[0] == pixelblackorwhite) && (pixelcounter == height))) {
							roidetectedend = pixelcounter;
							// pixelsinroi=pixelsinroi+(roidetectedend-roidetectedstart);
							detectedroi = false;
							if (chckedge == false) {
								if (((roidetectedend - roidetectedstart) > roiUnderSize)
										&& ((roidetectedend - roidetectedstart) < roiAboveSize)) {
									roi = new Line(verticalaccumlatespaceline, roidetectedstart,
											verticalaccumlatespaceline, roidetectedend);
									roi.setStrokeColor(color);
									roimanager.addRoi(roi);
									roimanager.select(countRoi);
									roimanager.runCommand("Rename", "automatic " + Integer.toString(countRoi + 1));
									if ((checkedgefactor == true)
											&& ((roidetectedstart == 0) || (roidetectedend == height))) {
										x = 0.5;
										roifactorcount += 0.5;
									} else {

										x = 1;
									}
									//--------- count intersections----------------//
									if((roidetectedstart == 0) || (roidetectedend == width)){
										
										intersections = intersections +1;
									}
									else{
										intersections = intersections +2;
									}
									
									pixelsinroi = pixelsinroi + (roi.getLength() * x);
									// ----------creates roi tables-----//
									resultsTable.incrementCounter();
									resultsTable.addValue("Index",Integer.toString(countRoi + 1));
									resultsTable.addValue("Method", "automatic ");
									resultsTable.addValue("Length", roi.getLength()*cal.pixelWidth);
									resultsTable.addValue("Weight", (float) x);
									countRoi++;
								}
							}
							if (chckedge == true) {
								if ((roidetectedstart != 0) && (roidetectedend != width)) {
									if (((roidetectedend - roidetectedstart) > roiUnderSize)
											&& ((roidetectedend - roidetectedstart) < roiAboveSize)) {
										roi = new Line(verticalaccumlatespaceline, roidetectedstart,
												verticalaccumlatespaceline, roidetectedend);
										roi.setStrokeColor(color);
										roimanager.addRoi(roi);
										roimanager.select(countRoi);
										roimanager.runCommand("Rename", "automatic " + Integer.toString(countRoi + 1));
										
										intersections = intersections +2;
										pixelsinroi = pixelsinroi + roi.getLength();
										// ----------creates roi tables-----//
										resultsTable.incrementCounter();
										resultsTable.addValue("Index",Integer.toString(countRoi + 1));
										resultsTable.addValue("Method", "automatic ");
										resultsTable.addValue("Length", roi.getLength()*cal.pixelWidth);
										countRoi++;
										
										
									}
								}
							}
						}
						pixeltotal++;
					}
					pixeltotal--;
					verticalaccumlatespaceline += verticalspaceline;
				}
			}
			// -------------------table-------------------//
			// IJ.showMessage(Double.toString(roifactorcount));
			double grainsize;
			sizemedium = (float)( pixelsinroi*cal.pixelWidth) / countRoi;
			fractionline = (float) pixelsinroi / pixeltotal;
			interLinesLength = (float)roimanager.getCount() / (pixeltotal*cal.pixelWidth);
			interTestLine = (float)intersections/(pixeltotal*cal.pixelWidth);
			grainsize =((-6.643856*Math.log(sizemedium))-3.288); 
			summaryTable.incrementCounter();
			summaryTable.addValue("Number of Intercepts(N)",roimanager.getCount());
			summaryTable.addValue("Mean Intercept (Ī)", sizemedium);
			summaryTable.addValue("Sum of Intercepts ", pixelsinroi*cal.pixelWidth);
			summaryTable.addValue("Sum of Test Lines (L)", pixeltotal*cal.pixelWidth);
			summaryTable.addValue("Intercepts by Lines Length(NL)",interLinesLength );
			summaryTable.addValue("Lineal Fraction (L└)", fractionline);
			summaryTable.addValue("Intersections by Test Line (PL)", interTestLine);
			summaryTable.addValue("ASTM Grain Size", grainsize);
			summaryTable.show("Summary");
			resultsTable.show("Results");
			//ImageCanvas ic = imp.getCanvas();
			//Calibration cal = imp.getCalibration();
			//IJ.showMessage(""+ic.getMagnification()+""+cal.pixelWidth+""+cal.pixelHeight+""+cal.pixelDepth);
			
			// roitable.saveAs("C:\\ImageJ\\Resultis.csv");
			// try {
			// resultsTable.saveAs("C:\\ImageJ\\Results.csv");
			// } catch (IOException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			// }
			//
			// roitable.saveas();
			/*
			 * if(roiundercheck == true){ for(int indexroi =0; indexroi<=
			 * countRoi ;indexroi++){ roimanager.runCommand("Deselect"); roi =
			 * roimanager.getRoi(indexroi);
			 * //IJ.showMessage(Double.toString(roi.getLength()));
			 * 
			 * roimanager.select(indexroi); if(roi.getLength()<roiundersize){
			 * roimanager.runCommand("Delete");
			 * roimanager.runCommand("Deselect");
			 * //roimanager.runCommand("Update"); countroiabovesize++; }
			 * //IJ.showMessage(Double.toString(roi.getLength()));
			 * //roimanager.runCommand("Rename",
			 * "Interceptor "+Integer.toString(indexroi)); }
			 * IJ.showMessage(Integer.toString(countroiabovesize)); }
			 */
			// IJ.showMessage(Integer.toString(countroiabovesize));
			resultsTableClone = (ResultsTable) resultsTable.clone();
			roimanager.runCommand("Show All with labels");
			// imp.setOverlay(overlay);
			roimanager.setVisible(true);
			this.roishow = true;
			imp.show();
			this.analyzed = true;
			totalanalyzedroi = this.roimanager.getCount();
			// IJ.showMessage("ola doido doido:" +x);
		} else {
			// this.roimanager.runCommand("Show None");
			Calibration cal = imp.getCalibration();
			int x = 0;
			x = totalanalyzedroi;
			// IJ.showMessage("ola doido :" +x);
			this.resultsTable = (ResultsTable) resultsTableClone.clone();
			//IJ.showMessage("este e y"+this.roimanager.getCount()+"este e o x"+totalanalyzedroi);
			if(x == this.roimanager.getCount()){
				IJ.showMessage("Image has already been analyzed,please change an attribute or add manual intercepts");
				
			}
			while ((totalanalyzedroi <= this.roimanager.getCount()) || (totalanalyzedroi > this.roimanager.getCount())) {

				// this.roimanager.getRoi(totalanalyzedroi+1).getLength();
				this.roimanager.rename(totalanalyzedroi, "Manual_" + (totalanalyzedroi + 1));

				this.resultsTable.incrementCounter();
				this.resultsTable.addValue("Index",totalanalyzedroi + 1);
				this.resultsTable.addValue("Method", "manual");
				this.resultsTable.addValue("Length", (int) (this.roimanager.getRoi(totalanalyzedroi).getLength()*cal.pixelWidth));
				resultsTable.addValue("Weight", 1);
				this.resultsTable.show("Results");

				// IJ.showMessage("estamos aqui mas nao ta dando
				// certo"+this.roimanager.getCount()+" talves por
				// isso"+totalanalyzedroi);
				totalanalyzedroi++;

			}
			this.roimanager.runCommand(imp, "Show All");

		}

	}

	public boolean getanalyzed() {
		return this.analyzed;
	}

	public void setanalyzed(boolean analyzed) {
		this.analyzed = analyzed;
	}

	public ResultsTable getfractionlineartable() {
		return this.summaryTable;
	}

	public ResultsTable getroitable() {
		return this.resultsTable;
	}

	public boolean getroishow() {
		return this.roishow;
	}

	public RoiManager getroimanager() {
		return this.roimanager;
	}

	public void showInterceptos() {
		roimanager.runCommand("Show All with labels");
		this.roishow = true;
	}

	public void hideIntercptos() {
		roimanager.runCommand("Show None");
		this.roishow = false;
	}

}
