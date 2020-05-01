package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ij.IJ;
import ij.plugin.Colors;
import ij.plugin.frame.ColorPicker;
import model.StereologyFractionLinesModel;
import view.StereologyFractionLinesView;

public class StereologyFractionLinesController {
	protected StereologyFractionLinesView stereologyFractionLinesView;
	protected StereologyFractionLinesModel stereologyFractionLinesModel;
	// testeblock//
	protected ColorPicker colorpicker;

	public void estereologyFractionLinesController() {
		stereologyFractionLinesView = new StereologyFractionLinesView();
		stereologyFractionLinesView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		stereologyFractionLinesView.setVisible(true);
		// colorpicker= new ColorPicker();
		// colorpicker.setVisible(true);
		stereologyFractionLinesModel = new StereologyFractionLinesModel();
		// this.estereologyFractionLinesView.getbtnChooseColor().addActionListener(new
		// ChooseColor(this.estereologyFractionLinesModel));
		// this.estereologyFractionLinesView.gettxtNumberOfLines().addFocusListener(new
		// GetNumberofLines(this.estereologyFractionLinesModel,this.estereologyFractionLinesView));
		this.stereologyFractionLinesView.gettxtRoiundersize().addFocusListener(
				new GetRoiUnderSize(this.stereologyFractionLinesModel, this.stereologyFractionLinesView));
		this.stereologyFractionLinesView.getbtnAnalyzeLines().addActionListener(
				new AnalizeLines(this.stereologyFractionLinesModel, this.stereologyFractionLinesView));
		// this.estereologyFractionLinesView.getbtnRoicolor().addActionListener(new
		// ChooseColor(this.estereologyFractionLinesModel));
		// this.estereologyFractionLinesView.getspnNumberLines().addFocusListener(new
		// GetNumberofLines(this.estereologyFractionLinesModel,this.estereologyFractionLinesView));
		this.stereologyFractionLinesView.getspnNumberLines().addChangeListener(
				new GetNumberofLinesListener(this.stereologyFractionLinesModel, this.stereologyFractionLinesView));
		this.stereologyFractionLinesView.getchckbxBoldLines().addActionListener(
				new DrawBoldLines(this.stereologyFractionLinesModel, this.stereologyFractionLinesView));
		this.stereologyFractionLinesView.getcbxChoseColor().addActionListener(
				new ChooseColor(this.stereologyFractionLinesModel, this.stereologyFractionLinesView));
		this.stereologyFractionLinesView.getrdbHorizontalLines().addActionListener(
				new DrawLines(this.stereologyFractionLinesModel, this.stereologyFractionLinesView));
		this.stereologyFractionLinesView.getrdbVerticalLines().addActionListener(
				new DrawLines(this.stereologyFractionLinesModel, this.stereologyFractionLinesView));
		this.stereologyFractionLinesView.getrdbtnBoth().addActionListener(
				new DrawLines(this.stereologyFractionLinesModel, this.stereologyFractionLinesView));
		this.stereologyFractionLinesView.gettxtMax().addFocusListener(
				new GetRoiAboveSize(this.stereologyFractionLinesModel, this.stereologyFractionLinesView));
		this.stereologyFractionLinesView.getbtnhideOverlay().addActionListener(
				new HideOverlay(this.stereologyFractionLinesModel, this.stereologyFractionLinesView));
		this.stereologyFractionLinesView.getchckbxIncludeRoiAt().addActionListener(
				new CheckEdge(this.stereologyFractionLinesModel, this.stereologyFractionLinesView));
		this.stereologyFractionLinesView.getbtnshowinterceptos().addActionListener(
				new HideInterceptos(this.stereologyFractionLinesModel, this.stereologyFractionLinesView));
		this.stereologyFractionLinesView.getbtnSave().addActionListener(
				new SaveResult(this.stereologyFractionLinesModel, this.stereologyFractionLinesView));
		this.stereologyFractionLinesView.getbtnReset().addActionListener(
				new ResetEdgeValues(this.stereologyFractionLinesModel, this.stereologyFractionLinesView));
		this.stereologyFractionLinesView.getbtnInvertLu().addActionListener(
				new InvertLut(this.stereologyFractionLinesModel, this.stereologyFractionLinesView));
		this.stereologyFractionLinesView.getbtnHelplines().addActionListener(
				new OpenHelpLines(this.stereologyFractionLinesModel, this.stereologyFractionLinesView));
		this.stereologyFractionLinesView.addWindowListener(new WindowsOut(this.stereologyFractionLinesModel, this.stereologyFractionLinesView));

		// this.estereologyFractionLinesView.getbtncolorPicker().addActionListener(new
		// ColorPicke(this.estereologyFractionLinesModel,this.estereologyFractionLinesView));
		// this.colorpicker.addMouseListener(new
		// ColorPicke(this.estereologyFractionLinesModel,this.estereologyFractionLinesView,this.colorpicker));

		// -----teste----- block-----//

	}

	class WindowsOut implements WindowListener{
		protected StereologyFractionLinesModel stereologyFractionLinesModel;
		protected StereologyFractionLinesView stereologyFractionLinesView;
		
		public WindowsOut(StereologyFractionLinesModel stereologyFractionLinesModel,
				StereologyFractionLinesView stereologyFractionLinesView) {
			this.stereologyFractionLinesModel = stereologyFractionLinesModel;
			this.stereologyFractionLinesView = stereologyFractionLinesView;
		}
		
		@Override
		public void windowActivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowClosed(WindowEvent e) {
			//StereologyController.StereologygetInstance().stereologyView.getbtnFractionLinearStart().setEnabled(true);
			//StereologyController.StereologygetInstance().stereologyView.getbtnFractionPoints().setEnabled(true);
			this.stereologyFractionLinesModel.saveResults();
			
		}

		@Override
		public void windowClosing(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowDeactivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowDeiconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowIconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowOpened(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	
	
	class OpenHelpLines implements ActionListener {

		protected StereologyFractionLinesModel stereologyFractionLinesModel;
		protected StereologyFractionLinesView stereologyFractionLinesView;

		public OpenHelpLines(StereologyFractionLinesModel stereologyFractionLinesModel,
				StereologyFractionLinesView stereologyFractionLinesView) {
			this.stereologyFractionLinesModel = stereologyFractionLinesModel;
			this.stereologyFractionLinesView = stereologyFractionLinesView;
		}

		public void actionPerformed(ActionEvent e) {
			this.stereologyFractionLinesModel.openHelpLines();

		}
	}

	// ---------class criada domingo dia 24-------//
	class InvertLut implements ActionListener {

		protected StereologyFractionLinesModel stereologyFractionLinesModel;
		protected StereologyFractionLinesView stereologyFractionLinesView;

		public InvertLut(StereologyFractionLinesModel stereologyFractionLinesModel,
				StereologyFractionLinesView stereologyFractionLinesView) {
			this.stereologyFractionLinesModel = stereologyFractionLinesModel;
			this.stereologyFractionLinesView = stereologyFractionLinesView;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			this.stereologyFractionLinesModel.setAnalyzed(false);
			this.stereologyFractionLinesModel.setPixelBlackorWhite();
			boolean checkRoiAtEdge;
			boolean Horizontal, vertical, checkedgefactor;
			Horizontal = this.stereologyFractionLinesView.getrdbHorizontalLines().isSelected();
			vertical = this.stereologyFractionLinesView.getrdbVerticalLines().isSelected();
			checkRoiAtEdge = this.stereologyFractionLinesView.getchckbxIncludeRoiAt().isSelected();
			checkedgefactor = this.stereologyFractionLinesView.getchckEdge05factor().isSelected();
			this.stereologyFractionLinesModel.analyzeLines(Horizontal, vertical, checkRoiAtEdge, checkedgefactor);
			this.stereologyFractionLinesView.getbtnshowinterceptos().setEnabled(true);
			this.stereologyFractionLinesView.getbtnhideOverlay().setText("Hide");
			this.stereologyFractionLinesView.getbtnSave().setEnabled(true);
		}

	}

	// -------class 22/09--------------//
	class ResetEdgeValues implements ActionListener {
		protected StereologyFractionLinesModel stereologyFractionLinesModel;
		protected StereologyFractionLinesView stereologyFractionLinesView;

		public ResetEdgeValues(StereologyFractionLinesModel stereologyFractionLinesModel,
				StereologyFractionLinesView stereologyFractionLinesView) {
			this.stereologyFractionLinesModel = stereologyFractionLinesModel;
			this.stereologyFractionLinesView = stereologyFractionLinesView;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			this.stereologyFractionLinesView.gettxtMax().setText("infinity");
			this.stereologyFractionLinesView.gettxtRoiundersize().setText("0");
			this.stereologyFractionLinesModel.setRoiUnderSize(0);
			this.stereologyFractionLinesModel.setRoiAboveSize(999999);

		}
	}

	// class 20/09 ///
}

class SaveResult implements ActionListener {
	protected StereologyFractionLinesModel stereologyFractionLinesModel;
	protected StereologyFractionLinesView stereologyFractionLinesView;

	public SaveResult(StereologyFractionLinesModel stereologyFractionLinesModel,
			StereologyFractionLinesView stereologyFractionLinesView) {
		this.stereologyFractionLinesModel = stereologyFractionLinesModel;
		this.stereologyFractionLinesView = stereologyFractionLinesView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.stereologyFractionLinesModel.saveResults();

	}
}

// -------------------------------------class modified 19/07
// ------------------------------------//
class ChooseColor implements ActionListener {
	protected StereologyFractionLinesModel stereologyFractionLinesModel;
	protected StereologyFractionLinesView stereologyFractionLinesView;

	public ChooseColor(StereologyFractionLinesModel stereologyFractionLinesModel,
			StereologyFractionLinesView stereologyFractionLinesView) {
		this.stereologyFractionLinesModel = stereologyFractionLinesModel;
		this.stereologyFractionLinesView = stereologyFractionLinesView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.stereologyFractionLinesView.getbtnshowinterceptos().setEnabled(false);
		this.stereologyFractionLinesView.getbtnInvertLu().setEnabled(false);
		int numberOfLines = Integer
				.parseInt(this.stereologyFractionLinesView.getspnNumberLines().getValue().toString());
		this.stereologyFractionLinesModel.setnumberOfLines(numberOfLines);
		this.stereologyFractionLinesModel.chooseColor(
				this.stereologyFractionLinesView.getcbxChoseColor().getSelectedItem().toString(),
				this.stereologyFractionLinesView.getrdbHorizontalLines().isSelected(),
				this.stereologyFractionLinesView.getrdbVerticalLines().isSelected(),
				this.stereologyFractionLinesView.getrdbtnBoth().isSelected());
	}
}

// -------------------------------------class
// 22/07------------------------------------//
class DrawBoldLines implements ActionListener {
	protected StereologyFractionLinesView stereologyFractionLinesView;
	protected StereologyFractionLinesModel stereologyFractionLinesModel;

	public DrawBoldLines(StereologyFractionLinesModel stereologyFractionLinesModel,
			StereologyFractionLinesView stereologyFractionLinesView) {
		this.stereologyFractionLinesView = stereologyFractionLinesView;
		this.stereologyFractionLinesModel = stereologyFractionLinesModel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.stereologyFractionLinesView.getbtnInvertLu().setEnabled(false);
		int numberOfLines = Integer
				.parseInt(this.stereologyFractionLinesView.getspnNumberLines().getValue().toString());
		if (numberOfLines > 0) {
			this.stereologyFractionLinesView.getbtnhideOverlay().setEnabled(true);
			this.stereologyFractionLinesView.getbtnInvertLu().setEnabled(false);
		} else {
			this.stereologyFractionLinesView.getbtnhideOverlay().setEnabled(false);
		}
		this.stereologyFractionLinesModel.setnumberOfLines(numberOfLines);
		if (this.stereologyFractionLinesView.getchckbxBoldLines().isSelected() == true) {
			this.stereologyFractionLinesModel.drawLinesbold();
		} else {
			this.stereologyFractionLinesModel.drawLinesNomal();
		}
		this.stereologyFractionLinesModel.drawLines(
				this.stereologyFractionLinesView.getrdbHorizontalLines().isSelected(),
				this.stereologyFractionLinesView.getrdbVerticalLines().isSelected(),
				this.stereologyFractionLinesView.getrdbtnBoth().isSelected());

	}
}

// ---------------------------------------class
// 19/07-------------------------------//
class GetNumberofLinesListener implements ChangeListener {
	protected StereologyFractionLinesView stereologyFractionLinesView;
	protected StereologyFractionLinesModel stereologyFractionLinesModel;

	public GetNumberofLinesListener(StereologyFractionLinesModel stereologyFractionLinesModel,
			StereologyFractionLinesView stereologyFractionLinesView) {
		this.stereologyFractionLinesModel = stereologyFractionLinesModel;
		this.stereologyFractionLinesView = stereologyFractionLinesView;
	}

	public void stateChanged(ChangeEvent e) {
		this.stereologyFractionLinesView.getbtnshowinterceptos().setEnabled(false);
		int numberOfLines = Integer
				.parseInt(this.stereologyFractionLinesView.getspnNumberLines().getValue().toString());
		if (numberOfLines > 0) {
			this.stereologyFractionLinesView.getbtnhideOverlay().setEnabled(true);
			this.stereologyFractionLinesView.getbtnAnalyzeLines().setEnabled(true);
			this.stereologyFractionLinesView.getbtnInvertLu().setEnabled(false);
			
		} else {
			this.stereologyFractionLinesView.getbtnhideOverlay().setEnabled(false);
			this.stereologyFractionLinesView.getbtnAnalyzeLines().setEnabled(false);
			this.stereologyFractionLinesView.getbtnInvertLu().setEnabled(false);
			
		}
		this.stereologyFractionLinesModel.setnumberOfLines(numberOfLines);
		if (this.stereologyFractionLinesView.getchckbxBoldLines().isSelected() == true) {
			this.stereologyFractionLinesModel.drawLinesbold();
		} else {
			this.stereologyFractionLinesModel.drawLinesNomal();
		}
		this.stereologyFractionLinesModel.drawLines(
				this.stereologyFractionLinesView.getrdbHorizontalLines().isSelected(),
				this.stereologyFractionLinesView.getrdbVerticalLines().isSelected(),
				this.stereologyFractionLinesView.getrdbtnBoth().isSelected());

	}

}

// -------------------------------class
// 04/08------------------------------------//
class CheckEdge implements ActionListener {
	protected StereologyFractionLinesModel stereologyFractionLinesModel;
	protected StereologyFractionLinesView stereologyFractionLinesView;

	public CheckEdge(StereologyFractionLinesModel stereologyFractionLinesModel,
			StereologyFractionLinesView stereologyFractionLinesView) {
		this.stereologyFractionLinesModel = stereologyFractionLinesModel;
		this.stereologyFractionLinesView = stereologyFractionLinesView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (this.stereologyFractionLinesView.getchckbxIncludeRoiAt().isSelected() == true) {
			this.stereologyFractionLinesView.getchckEdge05factor().setSelected(false);
			this.stereologyFractionLinesView.getchckEdge05factor().setEnabled(false);
		} else {
			this.stereologyFractionLinesView.getchckEdge05factor().setEnabled(true);
		}
	}
}

// ---------------------------class-----------------------------------//
class GetRoiUnderSize implements FocusListener {
	protected StereologyFractionLinesView stereologyFractionLinesView;
	protected StereologyFractionLinesModel stereologyFractionLinesModel;

	public GetRoiUnderSize(StereologyFractionLinesModel stereologyFractionLinesModel,
			StereologyFractionLinesView stereologyFractionLinesView) {
		this.stereologyFractionLinesModel = stereologyFractionLinesModel;
		this.stereologyFractionLinesView = stereologyFractionLinesView;
	}

	public void focusGained(FocusEvent e) {
		this.stereologyFractionLinesView.gettxtRoiundersize().addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					e.consume();
				}
			}
		});
	}

	public void focusLost(FocusEvent e) {
		if (!e.isTemporary()) {
			int Roiundersize = Integer.parseInt(this.stereologyFractionLinesView.gettxtRoiundersize().getText());

			this.stereologyFractionLinesModel.setRoiUnderSize(Roiundersize);
		}

	}
}

// -------------------------------------class
// 21/07-------------------------------------//
class GetRoiAboveSize implements FocusListener {
	protected StereologyFractionLinesView stereologyFractionLinesView;
	protected StereologyFractionLinesModel stereologyFractionLinesModel;

	public GetRoiAboveSize(StereologyFractionLinesModel stereologyFractionLinesModel,
			StereologyFractionLinesView stereologyFractionLinesView) {
		this.stereologyFractionLinesModel = stereologyFractionLinesModel;
		this.stereologyFractionLinesView = stereologyFractionLinesView;
	}

	public void focusGained(FocusEvent e) {
		this.stereologyFractionLinesView.gettxtMax().setText(null);
		this.stereologyFractionLinesView.gettxtRoiundersize().addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					e.consume();
				}
			}
		});
	}

	public void focusLost(FocusEvent e) {
		if (!e.isTemporary()) {
			if( this.stereologyFractionLinesView.gettxtMax().getText().isEmpty() == false){
				int Roiundersize = Integer.parseInt(this.stereologyFractionLinesView.gettxtMax().getText());
				this.stereologyFractionLinesModel.setRoiAboveSize(Roiundersize);
			}
			else{
				this.stereologyFractionLinesModel.setRoiAboveSize(9999999);
				this.stereologyFractionLinesView.gettxtMax().setText("infinity");
			}
		}

	}
}
// ------------------------------------class 19/07
// modified//--------------------------//

class DrawLines implements ActionListener {
	protected StereologyFractionLinesModel stereologyFractionLinesModel;
	protected StereologyFractionLinesView stereologyFractionLinesView;

	public DrawLines(StereologyFractionLinesModel stereologyFractionLinesModel,
			StereologyFractionLinesView stereologyFractionLinesView) {
		this.stereologyFractionLinesModel = stereologyFractionLinesModel;
		this.stereologyFractionLinesView = stereologyFractionLinesView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.stereologyFractionLinesView.getbtnInvertLu().setEnabled(false);
		this.stereologyFractionLinesView.getbtnshowinterceptos().setEnabled(false);
		
		boolean Horizontal;
		boolean vertical;
		boolean both;
		Horizontal = this.stereologyFractionLinesView.getrdbHorizontalLines().isSelected();
		vertical = this.stereologyFractionLinesView.getrdbVerticalLines().isSelected();
		both = this.stereologyFractionLinesView.getrdbtnBoth().isSelected();
		this.stereologyFractionLinesModel.drawLines(Horizontal, vertical, both);

	}
}

// ------------------------------class------------------------//
class AnalizeLines implements ActionListener {
	protected StereologyFractionLinesModel stereologyFractionLinesModel;
	protected StereologyFractionLinesView stereologyFractionLinesView;

	public AnalizeLines(StereologyFractionLinesModel stereologyFractionLinesModel,
			StereologyFractionLinesView stereologyFractionLinesView) {
		this.stereologyFractionLinesModel = stereologyFractionLinesModel;
		this.stereologyFractionLinesView = stereologyFractionLinesView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		boolean checkRoiAtEdge;
		boolean Horizontal, vertical, checkedgefactor;
		Horizontal = this.stereologyFractionLinesView.getrdbHorizontalLines().isSelected();
		vertical = this.stereologyFractionLinesView.getrdbVerticalLines().isSelected();
		checkRoiAtEdge = this.stereologyFractionLinesView.getchckbxIncludeRoiAt().isSelected();
		checkedgefactor = this.stereologyFractionLinesView.getchckEdge05factor().isSelected();
		this.stereologyFractionLinesModel.analyzeLines(Horizontal, vertical, checkRoiAtEdge, checkedgefactor);
		this.stereologyFractionLinesView.getbtnshowinterceptos().setEnabled(true);
		this.stereologyFractionLinesView.getbtnhideOverlay().setText("Hide");
		this.stereologyFractionLinesView.getbtnSave().setEnabled(true);
		this.stereologyFractionLinesView.getbtnInvertLu().setEnabled(true);
	}
}

// -----------------------------class 22/07-------------------------//
class HideOverlay implements ActionListener {
	protected StereologyFractionLinesModel stereologyFractionLinesModel;
	protected StereologyFractionLinesView stereologyFractionLinesView;

	public HideOverlay(StereologyFractionLinesModel stereologyFractionLinesModel,
			StereologyFractionLinesView stereologyFractionLinesView) {
		this.stereologyFractionLinesModel = stereologyFractionLinesModel;
		this.stereologyFractionLinesView = stereologyFractionLinesView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (this.stereologyFractionLinesView.getbtnhideOverlay().getText() == "Hide") {
			this.stereologyFractionLinesView.getbtnhideOverlay().setText("Show");
			this.stereologyFractionLinesModel.hideOverlay();
		} else {
			this.stereologyFractionLinesView.getbtnhideOverlay().setText("Hide");
			this.stereologyFractionLinesModel.showOverlay();

		}
	}
}

// ------------------------------------class15/08----------------------------------//
class HideInterceptos implements ActionListener {
	protected StereologyFractionLinesModel stereologyFractionLinesModel;
	protected StereologyFractionLinesView stereologyFractionLinesView;

	public HideInterceptos(StereologyFractionLinesModel stereologyFractionLinesModel,
			StereologyFractionLinesView stereologyFractionLinesView) {
		this.stereologyFractionLinesModel = stereologyFractionLinesModel;
		this.stereologyFractionLinesView = stereologyFractionLinesView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (this.stereologyFractionLinesView.getbtnshowinterceptos().getText() == "Hide") {
			this.stereologyFractionLinesView.getbtnshowinterceptos().setText("Show");
			this.stereologyFractionLinesModel.hideInterceptos();
		} else {
			this.stereologyFractionLinesView.getbtnshowinterceptos().setText("Hide");
			this.stereologyFractionLinesModel.showInterceptos();

		}
	}

}

// ----------------------------------classs17/8------------------------------//
class ColorPicke implements MouseListener {
	protected ColorPicker colorpicker;
	protected Colors color;
	protected Color cor;
	protected StereologyFractionLinesModel stereologyFractionLinesModel;
	protected StereologyFractionLinesView stereologyFractionLinesView;

	public ColorPicke(StereologyFractionLinesModel stereologyFractionLinesModel,
			StereologyFractionLinesView stereologyFractionLinesView, ColorPicker colorPicker) {
		this.stereologyFractionLinesModel = stereologyFractionLinesModel;
		this.stereologyFractionLinesView = stereologyFractionLinesView;
		this.colorpicker = colorPicker;
		colorpicker.addMouseListener(this);

		this.color = new Colors();
		this.cor = Color.red;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		IJ.showMessage("seu Doido");
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		IJ.showMessage("seu Doido");
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		IJ.showMessage("seu Doido");
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		IJ.showMessage("seu Doido");
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		IJ.showMessage("seu Doido");
	}

}
