package view;



import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;


import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import java.awt.Toolkit;


import java.awt.Color;



import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import javax.swing.DefaultComboBoxModel;

import ij.util.Java2;

import javax.swing.border.EtchedBorder;


public class StereologyFractionLinesView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JRadioButton rdbVerticalLines;
	private JRadioButton rdbHorizontalLines;
	private JTextField txtRoiundersize;
	private JCheckBox chckbxIncludeRoiAt;
	private JButton btnAnalyzeLines;
	private JPanel panel_1;
	private JTextField txtMax;
	private JSpinner spnNumberLines;
	private JCheckBox chckbxBoldLines;
	private JComboBox<String> cbxChoseColor;
	private JRadioButton rdbtnBoth;
	private ButtonGroup group;
	private JButton btnhideOverlay;
	private JCheckBox chckEdge05factor;
	private JButton btnshowinterceptos;
	private JButton btnReset;
	private JButton btnInvertLut;
	private JButton btnSave;
	private JButton btnHelpíntercpt;
	private JButton btnHelplines;
	
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EstereologyFractionLinesView frame = new EstereologyFractionLinesView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public StereologyFractionLinesView() {
		Java2.setSystemLookAndFeel();
		setResizable(false);
		setTitle("Lineal Analyzer");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 434, 590);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAnalizeInterceptor = new JLabel("Generate Intercept");
		lblAnalizeInterceptor.setForeground(Color.BLACK);
		lblAnalizeInterceptor.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnalizeInterceptor.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAnalizeInterceptor.setBounds(122, 263, 174, 25);
		contentPane.add(lblAnalizeInterceptor);
		this.group = new ButtonGroup();
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(10, 64, 408, 188);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNunberOfLines = new JLabel("Number of Lines:");
		lblNunberOfLines.setBounds(10, 11, 159, 25);
		panel.add(lblNunberOfLines);
		lblNunberOfLines.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		this.rdbHorizontalLines = new JRadioButton("Horizontal Lines");
		rdbHorizontalLines.setBounds(14, 88, 141, 23);
		panel.add(rdbHorizontalLines);
		rdbHorizontalLines.setSelected(true);
		rdbHorizontalLines.setFont(new Font("Tahoma", Font.PLAIN, 14));
		group.add(rdbHorizontalLines);
		
				
				this.rdbVerticalLines = new JRadioButton("Vertical Lines");
				rdbVerticalLines.setBounds(157, 88, 139, 23);
				panel.add(rdbVerticalLines);
				rdbVerticalLines.setFont(new Font("Tahoma", Font.PLAIN, 14));
				group.add(rdbVerticalLines);
				
				this.rdbtnBoth = new JRadioButton("Grid");
				rdbtnBoth.setFont(new Font("Tahoma", Font.PLAIN, 14));
				rdbtnBoth.setBounds(298, 88, 67, 23);
				panel.add(rdbtnBoth);
				group.add(rdbtnBoth);
				
				this.spnNumberLines  = new JSpinner();
				this.spnNumberLines.setFont(new Font("Tahoma", Font.PLAIN, 15));
				this.spnNumberLines.setBounds(330, 12, 67, 23);
				panel.add(this.spnNumberLines);
				this.spnNumberLines.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
				
				this.chckbxBoldLines = new JCheckBox("Bold");
				chckbxBoldLines.setForeground(Color.BLACK);
				chckbxBoldLines.setFont(new Font("Tahoma", Font.PLAIN, 14));
				chckbxBoldLines.setBounds(24, 114, 89, 23);
				panel.add(chckbxBoldLines);
				
				this.cbxChoseColor = new JComboBox<>();
				cbxChoseColor.setBackground(Color.WHITE);
				cbxChoseColor.setForeground(Color.BLACK);
				cbxChoseColor.setFont(new Font("Tahoma", Font.PLAIN, 13));
				cbxChoseColor.setBounds(308, 46, 89, 23);
				panel.add(cbxChoseColor);
				cbxChoseColor.setModel(new DefaultComboBoxModel<String>(new String[] {"Red", "Green", "Blue", "Magenta", "Cyan", "Yellow", "Orange", "Black"}));
				this.btnhideOverlay = new JButton("Hide");
				btnhideOverlay.setEnabled(false);
				btnhideOverlay.setBounds(309, 143, 89, 34);
				panel.add(btnhideOverlay);
				
				JLabel lblColorOfLines = new JLabel("Color of Lines:");
				lblColorOfLines.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblColorOfLines.setBounds(10, 47, 159, 25);
				panel.add(lblColorOfLines);
				
				/*this.btnHelplines = new JButton("Help");
				btnHelplines.setEnabled(false);
				btnHelplines.setBounds(207, 143, 89, 34);
				panel.add(btnHelplines);
		*/

		
		JLabel lblLineConfig = new JLabel("Generate lines");
		lblLineConfig.setForeground(Color.BLACK);
		lblLineConfig.setHorizontalAlignment(SwingConstants.CENTER);
		lblLineConfig.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblLineConfig.setBounds(122, 28, 174, 25);
		contentPane.add(lblLineConfig);
		
		panel_1 = new JPanel();
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_1.setBounds(10, 299, 408, 251);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setForeground(Color.LIGHT_GRAY);
		panel_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_2.setBounds(10, 11, 388, 90);
		panel_1.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblSizeMinAnd = new JLabel("Size of interest");
		lblSizeMinAnd.setBounds(116, 0, 155, 25);
		panel_2.add(lblSizeMinAnd);
		lblSizeMinAnd.setHorizontalAlignment(SwingConstants.CENTER);
		lblSizeMinAnd.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JLabel lblNewLabel = new JLabel("<   Intercept   <");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(116, 36, 145, 16);
		panel_2.add(lblNewLabel);
		
		this.txtMax = new JTextField();
		txtMax.setFont(new Font("Arial", Font.PLAIN, 14));
		txtMax.setText("infinity");
		txtMax.setBounds(323, 28, 55, 32);
		panel_2.add(txtMax);
		txtMax.setHorizontalAlignment(SwingConstants.CENTER);
		txtMax.setColumns(10);
		
		this.txtRoiundersize = new JTextField();
		txtRoiundersize.setBounds(10, 28, 55, 32);
		panel_2.add(txtRoiundersize);
		txtRoiundersize.setText("0");
		txtRoiundersize.setFont(new Font("Arial", Font.PLAIN, 14));
		txtRoiundersize.setHorizontalAlignment(SwingConstants.CENTER);
		txtRoiundersize.setColumns(10);
		
		this.btnReset = new JButton("Reset");
		btnReset.setBounds(153, 56, 89, 23);
		panel_2.add(btnReset);
		
		this.btnAnalyzeLines = new JButton("Analyze");
		btnAnalyzeLines.setEnabled(false);
		btnAnalyzeLines.setBounds(143, 206, 129, 34);
		panel_1.add(btnAnalyzeLines);
		
		this.chckbxIncludeRoiAt = new JCheckBox("Exclude on Edge");
		chckbxIncludeRoiAt.setBounds(10, 127, 154, 23);
		panel_1.add(chckbxIncludeRoiAt);
		chckbxIncludeRoiAt.setFont(new Font("Tahoma", Font.PLAIN, 14));	
		
		this.chckEdge05factor = new JCheckBox("Edge with 0.5 factor");
		chckEdge05factor.setForeground(Color.BLACK);
		chckEdge05factor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckEdge05factor.setBounds(10, 164, 185, 23);
		panel_1.add(chckEdge05factor);
		
		this.btnshowinterceptos = new JButton("Hide");
		btnshowinterceptos.setBounds(309, 123, 89, 34);
		panel_1.add(btnshowinterceptos);
		btnshowinterceptos.setEnabled(false);
		
		this.btnInvertLut = new JButton("Invert Phase");
		btnInvertLut.setEnabled(false);
		btnInvertLut.setBounds(10, 206, 123, 34);
		panel_1.add(btnInvertLut);
		
		this.btnSave = new JButton("Save");
		btnSave.setEnabled(false);
		btnSave.setBounds(282, 206, 116, 34);
		panel_1.add(btnSave);
		
//		this.btnHelpíntercpt = new JButton("Help");
//		btnHelpíntercpt.setEnabled(false);
//		btnHelpíntercpt.setBounds(210, 123, 89, 34);
//		panel_1.add(btnHelpíntercpt);
	}
	public JButton getbtnHelplines(){
		return this.btnHelplines;
	}
	
	public JButton getbtnReset(){
		return this.btnReset;
	}
	
	public JButton getbtnInvertLu(){
		return this.btnInvertLut;
	} 
	public JButton getbtnSave(){
		return this.btnSave;
	}
	public JButton btnHelpíntercpt(){
		return this.btnHelpíntercpt;
		
	}
	
	
	public JButton getbtnshowinterceptos(){
		return this.btnshowinterceptos;
	}
	
	public JCheckBox getchckEdge05factor(){
		return this.chckEdge05factor;
	}

	public JRadioButton getrdbHorizontalLines(){
		return this.rdbHorizontalLines;
	}
	public JRadioButton getrdbVerticalLines(){
		return this.rdbVerticalLines;
	}
	public JButton getbtnAnalyzeLines(){
		return this.btnAnalyzeLines;
	}
	public JCheckBox getchckbxIncludeRoiAt(){
		return this.chckbxIncludeRoiAt;
	}

	public JTextField gettxtRoiundersize(){
		return this.txtRoiundersize;
	}

	public JSpinner getspnNumberLines(){
		return this.spnNumberLines;
	}
	public JCheckBox getchckbxBoldLines(){
		return this.chckbxBoldLines;
	}
	public JComboBox<String> getcbxChoseColor(){
		return this.cbxChoseColor;
	}
	public JRadioButton getrdbtnBoth(){
		return this.rdbtnBoth;
	}

	public JTextField gettxtMax(){
		return this.txtMax;
	}
	public JButton getbtnhideOverlay(){
		return this.btnhideOverlay ;
	}
}
