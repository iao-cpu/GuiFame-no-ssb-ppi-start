// GUI for PPI 16.03.2012
package no.ssb.ppi.start;
import com.fame.timeiq.FunctorConsistencyChkException;
import com.fame.timeiq.RegularSeries;
import com.fame.timeiq.TimeScaling;
import com.fame.timeiq.TiqCheckedException;
import com.fame.timeiq.TiqClass;
import com.fame.timeiq.TiqConstants;
import com.fame.timeiq.TiqHelper;
import com.fame.timeiq.TiqObject;
import com.fame.timeiq.TiqObserved;
import com.fame.timeiq.TiqView;
import com.fame.timeiq.data.ArrayAdapter;
import com.fame.timeiq.data.FloatList;
import com.fame.timeiq.data.Observation;
import com.fame.timeiq.data.ObservationList;
import com.fame.timeiq.data.TiqValue;
import com.fame.timeiq.dates.DateHelper;
import com.fame.timeiq.dates.RangeTooLargeChkException;
import com.fame.timeiq.dates.RegularCalendar;
import com.fame.timeiq.dates.SimpleCalendar;
import com.fame.timeiq.dates.TiqDateFormat;
import com.fame.timeiq.dates.TiqFrequency;
import com.fame.timeiq.functors.string.Pad;
import com.fame.timeiq.persistence.ConnectionFailedChkException;
import com.fame.timeiq.persistence.DataStoreOpenChkException;
import com.fame.timeiq.persistence.DateAlignmentChkException;
import com.fame.timeiq.persistence.ObjectAccessChkException;
import com.fame.timeiq.persistence.PropertyChkException;

import com.fame.timeiq.tools.charts.ChartResourceBundle;
import com.fame.timeiq.tools.charts.ImageFileFilter;
import com.fame.timeiq.tools.charts.beans.FameChart;
import com.fame.timeiq.tools.charts.beans.FameCompoundChart;
import com.fame.timeiq.tools.charts.beans.ChartPane;
import com.fame.timeiq.tools.charts.beans.FameDateAxis;
import com.fame.timeiq.tools.charts.beans.FameFrequencyList;
import com.fame.timeiq.tools.charts.beans.FameNamedRange;
import com.fame.timeiq.tools.charts.beans.FameTiqViews;
import com.fame.timeiq.tools.charts.beans.TextAttributes;
import com.fame.timeiq.tools.charts.beans.annotations.ChartDataPointDisplay;
import com.fame.timeiq.tools.charts.beans.annotations.ChartLegend;
import com.fame.timeiq.tools.charts.beans.annotations.LegendEntry;
import com.fame.timeiq.tools.charts.beans.series.AreaSeries;
import com.fame.timeiq.tools.charts.beans.series.BarSeries;
import com.fame.timeiq.tools.charts.beans.series.ChartSeries;
import com.fame.timeiq.tools.charts.beans.series.LineSeries;
import com.fame.timeiq.tools.charts.beans.series.StackedBarSeries;
import com.fame.timeiq.tools.charts.ChartUchkException;
import com.fame.timeiq.tools.charts.ImageFileFilter;
import com.fame.timeiq.tools.charts.ChartResourceBundle;
import com.fame.timeiq.tools.charts.FameDataModel;
import com.fame.timeiq.tools.charts.LabelPosition;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Paint;
import java.awt.ScrollPane;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.AbstractList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.ListIterator;
import java.util.Locale;
import java.util.Properties;
import java.util.TimeZone;
import java.awt.FlowLayout;
import java.awt.event.*;
import java.awt.print.PrinterException;
import java.io.*;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.ListSelectionModel;

import javax.swing.*;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import no.ssb.ppi.apoint.APChkException;
import no.ssb.ppi.apoint.AccessPointClient;
//import no.ssb.ppi.connection.*;
import no.ssb.ppi.connection.ConnectMcadbs;
import no.ssb.ppi.retrieve.TiqFormula;
import no.ssb.ppi.retrieve.TiqRetrieve;

import no.ssb.ppi.tiqviews.TiqViewsData;
import no.ssb.ppi.charts.ChartFormat;
import no.ssb.ppi.print.PrintFitToPage;

import no.ssb.ppi.parser.DomParser;


public class TiqTest extends JApplet {
		
	private static final int LABEL_POS_BETWEEN = 0;
	public JPanel northJPanel, middleJPanel, southJPanel, ppiJPanel, leftNorthPanel, leftMiddlePanel, leftSouthPanel, leftPanel, rightPanel, fameChartPanel, barPanel, table1Panel, lineChartPanel, twoFuncChartPanel,
					twoBarPanel, areaPanel, stackedBarPanel, multiChartPanel, tabbedPanel1, tabbedPanel2, tabbedPanel3,tabbedPanel4, tabbedPanel5, tabbedPanel6, middleInnePanel1, middleInnePanel2, middleInnePanel3, middleInnePanel4;
	public JMenuBar menuBar;
	public JMenu fileMenu, chosenStats, timescale, functorMenu, langMenu, chartMenu, zoomMenu, procMenu, updateMenu, graphType, tableMenu, ordreMenu, gridMenu, func1Menu, func2Menu;
	public JMenuItem  showTableItem, saveAsPngItem, saveAsTxt, ppiItem, konjbarItem, saveAsPsItem, saveAsHtmlItem, norItem, engItem, bothItem, exitItem, compoundChartItem,  
						fameChartItem, resetZoomItem, originalItem, annpctItem, aveItem, csumItem, diffItem, laveItem, lminItem, lmaxItem, lsumItem, maveItem, mavecItem, mstddevItem, pctItem, stddevItem, ytypctItem, ytydiffItem, 
						annpct1Item, ave1Item, csum1Item, diff1Item, lave1Item, lmin1Item, lmax1Item, lsum1Item, mave1Item, mavec1Item, mstddev1Item, pct1Item, stddev1Item, ytypct1Item, ytydiff1Item ,
						areaSeriesItem, barSeriesItem, 
						candleStickSeriesItem, highLowSeriesItem, imageBarSeriesItem, lineSeriesItem, stackedBarSeriesItem, stepSeriesItem, symbolSeriesItem, printItem, invest07tabItem,
						investtabItem, knrtabItem, konjbar07tabItem, konjbartabItem, konsumpristabItem, ordreTab1Item, ordreTab2Item, ordreHtmlTab1Item, ordreHtmlTab2Item,
						ordreHtmlEngTab1Item, ordreHtmlEngTab2Item, kpiProcItem, twoBarSeriesItem, exportItem, onItem, offItem, darkItem;
	
	//add 26.09.2011
	public JTabbedPane tabbedPane;
	public JList ppiList, dateList, dbList1, dbList2;
	public FlowLayout layout;
	public JTextArea tableArea, serDisp, byearDisp;
	public String ppiNames[], db1Names[], db2Names[];
	public String output, printComponent, serOutput, byearOutput;
	//public String title1, title2, title3, title4;
	public JLabel label1, label2, label3, startLabel, endLabel, serLabel, s_label, timescale_label, desc_label, byearLabel;
	public JTextField s_text, desc_text;
	public JButton clear, get_val, whats_b, select_b, unselect_b, graph_b, multigraph_b, multifuncgraph_b, cleanup_b;
	public JScrollPane sp1, sp2, sp2a,  sp3, sp4, sp5, scrollpane;
	public JSplitPane jsp;
	public JComboBox convertTech, dbCmb, serCmb, freq, cb1, cb_sdate, cb_edate, cb_byear;
	public JTable jTable;
	public DefaultTableModel dTableModel;
	public ToolWindow1 window1;
	public Vector serData, dateNameList, ordreData, mData;
	public String[] serElement, ordreElement, metaElement;
	public Enumeration matches;
	public String serName;
	public Container c1, c2;
	public int nCols, nRows;
	public Object[] colLabel, rowLabel, tblValues;
	public static final int columnWidth = 10;
	public FameChart bar1, bar2, bar3,bar4, bar5, bar6, fameLineChart, twoBarChart, areaChart, stackedbarChart, twoFuncChart,
	multiChart1, multiChart2, multiChart3, multiChart4, multiChart5, multiChart6, multiChart7, multiChart8, multiChart9, multiChart10, multiChart11, multiChart12;
	
	public ChartPane topLeftLine, topRightLine, middleLeftLine, middleRightLine, bottomLeftLine, bottomRightLine,
						chartpane1, chartpane2,chartpane3,chartpane4, chartpane5, chartpane6;
	public ChartPane[] paneList;
	public LineSeries lineseries1, lineseries2, lineseries3, lineseries4, lineseries5, lineseries6;
	public AreaSeries areaseries1, areaseries2, areaseries3, areaseries4, areaseries5, areaseries6;
	public BarSeries barseries1, barseries2, barseries3, barseries4, barseries5, barseries6;
	public StackedBarSeries stackedbarseries1, stackedbarseries2, stackedbarseries3, stackedbarseries4, stackedbarseries5, stackedbarseries6;
	public ChartSeries[] topLineLeft, topLineRight, middleLineLeft, middleLineRight, bottomLineLeft, bottomLineRight;
	public FameDateAxis famedateaxis;
	public LegendEntry legendEntry1, legendEntry2, legendEntry3, legendEntry4, legendEntry5, legendEntry6;
	public FameNamedRange fameNamedRange;
	public FameDataModel fameDataModel1;
	public Properties techProp;
	//public String cElement[] = { "CONSTANT", "CONFORM", "LINEAR", "CUBIC", "DISCRETE"};
	public NumberFormat valueFmt;
	public String chartType, s;
		
	private static final int width = 50;
	private static final int lineLen = 180;
	private static final int teller1 = 80;
	private static final String emptyLine = " ";
	private static final String JScrollPaneConstants = null;
	private static final Boolean FALSE = null;
	private static final Boolean True = null;
	public FileOutputStream outStream;
	public PrintStream outFile;
	private String fileName = "ppitable.txt";
		
	public Component c;
	public TiqRetrieve rt;
	public PrintFitToPage printFitToPage;
	public int count;	
	//New
	public DefaultListModel jlistmodel;
	public JList tiqSerList;
	public Object[] selected, db_selected1, db_selected2;
	public String[] chosens, chosens1, descVal, chosens_m_px;
	public int j, q;	
	public Vector ppiSer, ppiScalar, metaVal, metaVal1, metaVal2, metaVal3, metaVal4, colName, colName1, colName2, wildSer, wildDesc, metaDesc;
	public String gloid, tiqobserved, tiqclass, tiqtype, tiqfreq, sDate, eDate, objName, objName1, objName2, objName3, objName4, type_val, ppiSerNames, select_db,
			sDate1, eDate1, sDate2, eDate2, sDate3, eDate3, sDate4, eDate4, wildText, wildNames, wildDes, freq_val, start, end, getFreq, start_q, end_q, func_val, func_val1, byear, funcs;
	String tiqDesc;
	public TiqFrequency f;
	public RegularCalendar tiqBasis;
	public String basis;
	public long startIndex, endIndex, startIndex1, endIndex1, startIndex2, endIndex2, startIndex3, endIndex3, startIndex4, endIndex4, byearStartIdx, byearEndIdx, byearIdx;
	public String[] metaValues, metaValues1, metaValues2, metaValues3, metaValues4;
	public TiqObject tiq, tiq1, tiq2, tiq3, tiq4, tiq5;
	public TiqObject[] apTiq;
	public DateHelper help;
	public JScrollPane scroll,scroll1, scroll2, scroll3;
	public JTable table1, table2, table3;
	public DefaultTableModel tblModel, tblModel1, tblModel2;
	public ConnectMcadbs mcadbsconn;
	public String[] ppiTiqSer, tiqWildSer, tiqWildDesc, tiqValues, tiqVal1, tiqVal2, tiqValueStr, selectedAndValues;
	public String [] totTiqVal, totTiqVal1, totTiqVal2;
	public TableColumn col, col1, col2, col3, col4, col5;
	public int nTabRow, nTabCol;
	public TiqFrequency tiqFreq;
	public Integer new_start_day, new_start_month, new_start_year, new_end_day, new_end_month, new_end_year;
	public char chr;
	public Character getChr;
	public Date d_start, d_end;
	public ObservationList ol1, funcOl1;
	public String[] tiqIndexes1, tiqIndexes2, tiqIndexes3, tiqIndexes4, tiqIndexes5, serNames;
	public String[] tiqValues1, tiqValues2, tiqValues3, tiqValues4, tiqValues5;
	public byte[] statuses;
	public TiqObject tsTiq, tsTiq1, tsTiq2, tsTiq3, tsTiq4,nameSeries2, nameDesc, nameDesc1;
	public TiqObject[] nameSeries, nameSeries1;
	public Vector sdvec, edvec, byearvec;
	public Calendar cal;
	public Object[] tiqObjArray;
	public DecimalFormat twoDigits;
	public Locale nlocale, elocale;
	public NumberFormat nft;
	public TiqView funcView, funcView1, funcView2, funcView3, funcView4, shiftView, shiftView1, divideView, subtractView, ytypctView, ytydiffView;
	public String[] funcIndexes1;
	public String[] funcValues1, funcValues2, funcValues3, funcValues4, funcValues5;
	public TimeScaling ts;
	public TiqView[] chartView, chartView1, timescaleChartView;
	public TiqViewsData tiqviewsdata, tiqviewsdata1, tiqviewsdata2, tiqviewsdata3, tiqviewsdata4, tiqviewsdata5, tiqviewsdata6, tiqviewsdata7, tiqviewsdata8, tiqviewsdata9, tiqviewsdata10,
						tiqviewsdata11, tiqviewsdata12;
	public String spec, db_spec, select_val, apstr, apstr1, apStr2, apstr3, apstr4;
	public String[]apArray;
	public String FAMEServer;
	public String tiqobj;
	public Vector v, addTiq;
	public Object o;
	public int minIndex, maxIndex, str, str_1;
	public String selectsource, oneChart;
	public String[] db_listbox1, db_listbox2, strings, strings_1, strings_2;
	public String db_prefix_1, db_prefix_2;
	public int total;
	public boolean removedRows, setBaseyear;
	public Boolean m_graph;
	public TiqValue tiqvalue;
	public String [][] metatab;
	public TiqObject[]tiqObject, byearObj, tiqObjectBase, tiqObjectFunc1, tiqObjectFunc2, tiqObjectFunc3, tiqObjectFunc4;
	public String[][] myValues;
	public long date1, date2;
	public String filename1 = "excel_pop.xls";
	public String s1, s2, s3, s4, s5, cellRef1, cellRef2, fdate, pop1, idag;
	PrintStream excelOutput;;
	public String[] pop2;
	public ChartSeries[] areaSer, lineSer, twoBarSer, barSer, areaser1, areaser2, areaser3, areaser4, areaser5, areaser6, areaser7, areaser8, areaser9, areaser10, areaser11, areaser12,
						 twoFuncLineSer;
	public ChartPane linePane, areaPane, barPane, twoFuncPane;
	private final Color colors[] = {Color.RED, Color.BLUE, Color.GREEN, Color.CYAN, Color.MAGENTA, Color.ORANGE, 
									Color.PINK, Color.YELLOW, Color.DARK_GRAY, Color.BLACK, Color.GRAY, Color.lightGray, Color.darkGray, Color.RED, Color.BLUE, Color.GREEN, Color.CYAN,
									Color.MAGENTA, Color.ORANGE, Color.PINK, Color.YELLOW, Color.BLACK, Color.LIGHT_GRAY, Color.YELLOW};
									
	public LegendEntry[] legendentry;
	Properties prop, tprop;
	ListSelectionModel listSelectionModel;
	FameFrequencyList fameFrequencyList;
	public boolean t_scale;
	public String frequency;
	//accessPoint variables
	public DateFormat formatter;
	public String apStart, apEnd, apstartDate, apendDate;
	public String cmdRequest;
	public TiqObject[] apDesc;
	AccessPointClient apClient;
	SimpleCalendar sc;
	ObservationList obsList;
	public TiqObject[] funcObj1;
	public DomParser parser;
	public String[] b1, b2;
	public String userx, userName;
	public URL url;
	public String hostname, hostname_1, uri;
	
	// set up GUI
	@SuppressWarnings("deprecation")
	
	// Initiate Applet
	//public TiqTest() throws ObjectAccessChkException, DataStoreOpenChkException, ConnectionFailedChkException, TiqCheckedException
	public void init()
	{		
		setTitle("Fame TimeIQ Web Application");
		try{
		rt = new TiqRetrieve();
		parser = new DomParser();
		wildText = null;
		tiqobj = null;
		str = 0;
		str_1 = 0;
		db_listbox1 = null;
		db_listbox2 = null;
		selectsource = null;
		removedRows = false;
		setBaseyear = false;
		oneChart = null;
		totTiqVal = null;
		addTiq = null;
		s1 = null;
		s2 = null;
		s3 = null;
		s4 = null;
		s5 = null;
		cellRef1 = null;
		cellRef2 = null;
		t_scale = false;
		frequency = null;
		apstartDate = null;
		apendDate = null;
		apStart = null;
		apEnd = null;
		db1Names = null;
		db2Names = null;
		b1 = null;
		b2 = null;
		userx = null;
		userName = null;		
		userName = System.getProperty("user.name");
		url = null;
		hostname = null;
		hostname_1 = null;
		uri = null;
		db_prefix_1 = null;
		db_prefix_2 = null;
		strings = null;
		strings_1 = null;
		strings_2 = null;
		db_selected1 = null;
		
		// Retrieve hostname
		url = this.getCodeBase();    	
    	//System.out.println(url.getHost());
    	hostname = url.getHost();
    	//System.out.println(hostname + "  Hostname");
    	hostname_1 = hostname;
    	rt.getAdr(hostname_1);
		ChartFormat cf = new ChartFormat();
		printFitToPage = new PrintFitToPage(c);		 
		long startIndex = DateHelper.ymdToIndex(1993, 1, 1);
		long endIndex = TiqConstants.RANGE_OPEN_END;		
		date1 = TiqConstants.RANGE_OPEN_END;
		date2 = TiqConstants.RANGE_OPEN_END;	
		// Formats a Date into a date/time string.
        Date today = new Date();
        DateFormat dateFormatter = DateFormat.getDateInstance();
        idag = dateFormatter.format(today);       
        formatter = new SimpleDateFormat("yyyy-MM-dd");     
		valueFmt = NumberFormat.getInstance();
        valueFmt.setMaximumFractionDigits(2);
        valueFmt.setMinimumFractionDigits(0);              				
		chartType = "compoundchart";	
		printComponent = null;
		func_val = null;
		func_val1 = null;
		funcs = null;	
		type_val = "Select the type to view";	
		nlocale = new Locale("no", "NO");
		elocale = new Locale("en", "GB");
		nft = NumberFormat.getInstance(nlocale);
	    twoDigits = new DecimalFormat();
	    twoDigits = (DecimalFormat)nft;
		//nft = NumberFormat.getInstance(nlocale);
		nft.setMaximumFractionDigits(2);
		nft.setMinimumFractionDigits(2);
		nft.setGroupingUsed(false);				
		// create areaseries comment out 13.03
		areaChart = new FameChart();
		areaChart.getZoomEnabled();	
		// Create 2 barSeries
		twoBarChart = new FameChart();
		twoBarChart.getZoomEnabled();	
		// create 2 stackedbarSeries
		stackedbarChart = new FameChart();
		stackedbarChart.getZoomEnabled();			
		// create line series
		fameLineChart = new FameChart();
		//create two lineseries with two functors
		twoFuncChart = new FameChart();	
		// create multichart
		multiChart1 = new FameChart();
		multiChart2 = new FameChart();
		multiChart3 = new FameChart();
		multiChart4 = new FameChart();
		multiChart5 = new FameChart();
		multiChart6 = new FameChart();
		multiChart7 = new FameChart();
		multiChart8 = new FameChart();
		multiChart9 = new FameChart();
		multiChart10 = new FameChart();
		multiChart11 = new FameChart();
		multiChart12 = new FameChart();
		
		// Items in northJPanel
		menuBar = new JMenuBar();
		//Menu item
		fileMenu = new JMenu("File");
		chosenStats = new JMenu("Applications");
		//datoMenu = new JMenu("Date");		
		langMenu = new JMenu("Language");
		functorMenu = new JMenu("TimeIQ Functors");
		func1Menu = new JMenu("Function 1");
		func2Menu = new JMenu("Function 2");
		chartMenu = new JMenu("TimeIQ Charts");
		zoomMenu = new JMenu("Zooming");
		procMenu = new JMenu("Procedure");
		graphType = new JMenu("Graph Type");
		tableMenu = new JMenu("Tabeller");
		ordreMenu = new JMenu("ORDRE");
		//lagerMenu = new JMenu("LAGER");
		gridMenu = new JMenu("Gridlines");
		
		func2Menu.addActionListener(
			 new ActionListener()
			 {
				 public void actionPerformed(ActionEvent e)
				 {
					 funcs = "function2";
				 }
			 }
		);
			
		exportItem = new JMenuItem("Export to Excel");
		export_listener toexcel = new export_listener();
		exportItem.addActionListener(toexcel);	
		saveAsPngItem = new JMenuItem("Save as png");
		saveAsPngItem.addActionListener(
		new ActionListener() {
            public void actionPerformed( ActionEvent e)
            {
               try {
                 JFileChooser fileChooser = new JFileChooser();
                 // Add Windows Bitmap and JPEG , png as filters
                 fileChooser.addChoosableFileFilter( ImageFileFilter.BMP_FILE_FILTER);
                 fileChooser.addChoosableFileFilter( ImageFileFilter.JPG_FILE_FILTER);
                 fileChooser.addChoosableFileFilter( ImageFileFilter.PNG_FILE_FILTER);
                 // Change the description for PNG files
                 fileChooser.addChoosableFileFilter( new ImageFileFilter( "PNG Image Files", "png"));                
                 fameLineChart.saveImage();               	             	 
                }
                catch( com.sun.jimi.core.JimiException er)
                 {
                     // Display any image error messages
                     JOptionPane.showMessageDialog(TiqTest.this.getContentPane(),
                     er.getMessage(), ChartResourceBundle.getString("SaveImageError"), JOptionPane.ERROR_MESSAGE);
                 }
                 catch( Exception er)
                 {
                     // Display any image error messages
                     JOptionPane.showMessageDialog( TiqTest.this.getContentPane(),
                     er.getMessage(), ChartResourceBundle.getString("UnexpectedError"), JOptionPane.ERROR_MESSAGE);      
                 }
            }  
		}  
	);
        
		saveAsPsItem = new JMenuItem("Save as ps");
		saveAsHtmlItem = new JMenuItem("Save as html");
		saveAsHtmlItem.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e)
					{
						rt.tab = "ordretable1";
						try {	
							rt.SaveHtml();
						} catch (FunctorConsistencyChkException e1) {		
							e1.printStackTrace();
						} catch (DateAlignmentChkException e1) {
							e1.printStackTrace();
						} catch (ObjectAccessChkException e1) {
							e1.printStackTrace();
						} catch (ConnectionFailedChkException e1) {
							e1.printStackTrace();
						}
					}
				}	
		);
		
		printItem = new JMenuItem("Print");
		printItem.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						try
						{
						     // Print a FameChart with default LANDSCAPE orientation.
						    if (printComponent == "textarea")
						    {
						    	PrintFitToPage.print(tableArea, 1);
						    }			     
						    else if (printComponent == "linechartpanel")
						    {
						    	PrintFitToPage.print(lineChartPanel);
						    }
						    else if (printComponent == "barpanel")
						    {
						    	PrintFitToPage.print(barPanel);
						    }
						    else 
						    	PrintFitToPage.print(rt.ordreTabPanel);
						}
						catch( PrinterException printerException){
						                
							// Display an error message if there are any Exceptions with printing.
							JOptionPane.showMessageDialog( getContentPane(), printerException.getMessage(), "Printer Error", JOptionPane.ERROR_MESSAGE);
						}
					}	
				}
		);
		
		ordreTab1Item = new JMenuItem("Ordre Html Table V1");
		ordreTab1Item.addActionListener(	
				new ActionListener() {
					public void actionPerformed(ActionEvent e)
					{			
						rt.tab = "ordretable1ana";		
						try {
							rt.getSeries();
							rt.SaveHtml();
						} catch (ObjectAccessChkException e1) {	
							e1.printStackTrace();
						} catch (ConnectionFailedChkException e1) {	
							e1.printStackTrace();
						}
						catch (FunctorConsistencyChkException e1) {
							e1.printStackTrace();
						} catch (DateAlignmentChkException e1) {
							e1.printStackTrace();
						}				
						window1 = new ToolWindow1();
						window1.setSize(1000, 900);
						window1.setVisible(true);	
					}
				}
		);
		ordreTab2Item = new JMenuItem("Ordre Html Table V2");
		ordreTab2Item.addActionListener(	
				new ActionListener() {
					public void actionPerformed(ActionEvent e)
					{
						//rt.tab = "ordretable2";
						rt.tab = "ordretable2ana";					
						try {					
							rt.getSeries();
							rt.SaveHtml();	
						} catch (ObjectAccessChkException e1) {		
							e1.printStackTrace();
						} catch (ConnectionFailedChkException e1) {
							e1.printStackTrace();
						}
						catch (FunctorConsistencyChkException e1) {			
							e1.printStackTrace();
						} catch (DateAlignmentChkException e1) {
							e1.printStackTrace();
						}		
						window1 = new ToolWindow1();
						window1.setSize(1000, 900);
						window1.setVisible(true);
					}
				}
		);
		
		ordreHtmlTab1Item = new JMenuItem("Ordre Html Table 1");
		ordreHtmlTab1Item.addActionListener(	
				new ActionListener() {
					public void actionPerformed(ActionEvent e)
					{
						rt.tab = "ordretable1";
						try {		
							rt.SaveHtml();
						} catch (ObjectAccessChkException e1){		
							e1.printStackTrace();
						} catch (ConnectionFailedChkException e1) {
							e1.printStackTrace();
						} catch (FunctorConsistencyChkException e1) {
							e1.printStackTrace();
						} catch (DateAlignmentChkException e1) {
							e1.printStackTrace();
						}
					}
				}	
		);
		
		ordreHtmlTab2Item = new JMenuItem("Ordre Html Table 2");
		ordreHtmlTab2Item.addActionListener(	
				new ActionListener() {
					public void actionPerformed(ActionEvent e)
					{
						rt.tab = "ordretable2";
						try {	
							rt.SaveHtml();
						} catch (ObjectAccessChkException e1){
							e1.printStackTrace();
						} catch (ConnectionFailedChkException e1) {
							e1.printStackTrace();
						} catch (FunctorConsistencyChkException e1) {
							e1.printStackTrace();
						} catch (DateAlignmentChkException e1) {
							e1.printStackTrace();
						}					
					}
				}	
		);
			
		ordreHtmlEngTab1Item = new JMenuItem("Ordre Html English Table 1");
		ordreHtmlEngTab1Item.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e)
					{
						rt.tab = "ordretable1eng";
						try {	
							rt.SaveHtmlEng();
						} catch (ObjectAccessChkException e1){
							e1.printStackTrace();
						} catch (ConnectionFailedChkException e1) {
							e1.printStackTrace();
						} catch (FunctorConsistencyChkException e1) {
							e1.printStackTrace();
						} catch (DateAlignmentChkException e1) {
							e1.printStackTrace();
						}						
					}
				}
		);
		
		ordreHtmlEngTab2Item = new JMenuItem("Ordre Html English Table 2");
		ordreHtmlEngTab2Item.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e)
					{
						rt.tab = "ordretable2eng";		
						try {
							rt.SaveHtmlEng();
						} catch (ObjectAccessChkException e1){
							e1.printStackTrace();
						} catch (ConnectionFailedChkException e1) {
							e1.printStackTrace();
						} catch (FunctorConsistencyChkException e1) {
							e1.printStackTrace();
						} catch (DateAlignmentChkException e1) {
							e1.printStackTrace();
						}					
					}
				}
		);
		
		ppiItem = new JMenuItem("PPI");
		konjbarItem = new JMenuItem("KONJBAR");
		invest07tabItem = new JMenuItem("Investering_07");
		investtabItem = new JMenuItem("Investering");
		knrtabItem = new JMenuItem("Knr");
		konjbar07tabItem = new JMenuItem("Konjunkturbar_07");
		konjbartabItem = new JMenuItem("Konjunkturbar");
		konsumpristabItem = new JMenuItem("Konsumpris");
		norItem = new JMenuItem("Norwegian");
		engItem = new JMenuItem("English");
		bothItem = new JMenuItem("Both");
		originalItem = new JMenuItem("Original");
		originalItem.addActionListener(		
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					int indx;
					func_val = null;
					func_val1 = null;
					cb_byear.repaint();
					if (chartType == "areaChart")
					{				
						ShowAreaSeries();
						SwingUtilities.updateComponentTreeUI(rightPanel);				
					}	
					else if ( chartType == "twoBarChart")
					{
						ShowTwoBarSeries();
						SwingUtilities.updateComponentTreeUI(rightPanel);
					}
					else if (chartType == "lineChart")
					{
						ShowLineSeries();
						SwingUtilities.updateComponentTreeUI(rightPanel);
					}
					else if (chartType == "stackedBarChart")
					{
						ShowStackedBarSeries();
						SwingUtilities.updateComponentTreeUI(rightPanel);
					}					
				}
			}
		);
		annpctItem = new JMenuItem("Annpct");
		annpctItem.addActionListener(		
			new ActionListener()
			{	
				public void actionPerformed(ActionEvent e)
				{
					func_val = "Annpct";
					if (chartType == "areaChart")
					{
						ShowAreaSeries();
						SwingUtilities.updateComponentTreeUI(rightPanel);
					}
					else if (chartType == "twoBarChart")
					{
						ShowTwoBarSeries();
						SwingUtilities.updateComponentTreeUI(rightPanel);
					}
					else if(chartType == "lineChart")
					{
						ShowLineSeries();
						SwingUtilities.updateComponentTreeUI(rightPanel);
					}
					else if(chartType == "stackedBarChart")
					{
						ShowStackedBarSeries();
						SwingUtilities.updateComponentTreeUI(rightPanel);
					}
				}
			}	
		);
		
		annpct1Item = new JMenuItem("Annpct");
		annpct1Item.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						func_val1 = "Annpct";
					}
				}
		);
		
		aveItem = new JMenuItem("Ave");
		aveItem.addActionListener(
				new ActionListener()
				{	
					public void actionPerformed(ActionEvent e)
					{
						func_val = "Ave";
					}
				}	
		);
		
		ave1Item = new JMenuItem("Ave");
		ave1Item.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						func_val1 = "Ave";
					}
				}	
		);
		
		csumItem = new JMenuItem("Csum");
		csumItem.addActionListener(	
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						func_val = "Csum";
					}
				}
		);
		
		csum1Item = new JMenuItem("Csum");
		csum1Item.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						func_val1 = "Csum";
					}
				}
		);
		
		diffItem = new JMenuItem("Diff");
		diffItem.addActionListener(	
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						func_val = "Diff";
					}
				}
		);
		
		diff1Item = new JMenuItem("Diff");
		diff1Item.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						func_val1 = "Diff";
					}
				}	
		);
			
		laveItem = new JMenuItem("Lave");
		laveItem.addActionListener(	
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						func_val = "Lave";
					}
				}	
		);
		
		lave1Item = new JMenuItem("Lave");
		lave1Item.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						func_val1 = "Lave";
					}
				}	
		);
		
		lmaxItem = new JMenuItem("Lmax");
		lmaxItem.addActionListener(
				new ActionListener()
				{	
					public void actionPerformed(ActionEvent e)
					{
						func_val = "Lmax";
					}
				}
		);
		
		lmax1Item = new JMenuItem("Lmax");
		lmaxItem.addActionListener(	
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						func_val1 = "Lmax";
					}
				}
		);
		
		lminItem = new JMenuItem("Lmin");
		lminItem.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						func_val = "Lmin";
					}
				}
		);
		
		lmin1Item = new JMenuItem("Lmin");
		lmin1Item.addActionListener(	
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						func_val1 = "Lmin";
					}
				}	
		);
		
		lsumItem = new JMenuItem("Lsum");
		lsumItem.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						func_val = "Lsum";
					}
				}
		);
		
		lsum1Item = new JMenuItem("Lsum");
		lsum1Item.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						func_val1 = "Lsum";
					}
				}
		);
		
		maveItem = new JMenuItem("Mave(Series, 3)");
		maveItem.addActionListener(	
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						func_val = "Mave";
					}
				}
		);
		
		mave1Item = new JMenuItem("Mave(Series, 3)");
		mave1Item.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						
						func_val1 = "Mave";
					}
				}	
		);
		
		mavecItem = new JMenuItem("Mavec(Series, 3)");
		mavecItem.addActionListener(	
				new ActionListener()
				{	
					public void actionPerformed(ActionEvent e)
					{
						func_val = "Mavec";
					}
				}
		);
		
		mavec1Item = new JMenuItem("Mavec(Series, 3)");
		mavec1Item.addActionListener(	
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						func_val1 = "Mavec";
					}
				}
		);
		
		mstddevItem = new JMenuItem("Mstddev");
		mstddevItem.addActionListener(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					func_val = "Mstddev";
				}	
			}
		);
		
		mstddev1Item = new JMenuItem("Mstddev");
		mstddev1Item.addActionListener(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					func_val1 = "Mstddev";
				}
			}
		);
		
		pctItem = new JMenuItem("Pct");
		
		pctItem.addActionListener(	
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{		
						func_val = "Pct";
						if(chartType == "areaChart")
						{
							ShowAreaSeries();
							SwingUtilities.updateComponentTreeUI(rightPanel);
						}
						else if(chartType == "twoBarChart")
						{
							ShowTwoBarSeries();
							SwingUtilities.updateComponentTreeUI(rightPanel);
							
						}
						else if (chartType == "stackedBarChart")
						{
							ShowStackedBarSeries();
							SwingUtilities.updateComponentTreeUI(rightPanel);
							
						}
						else if (chartType == "lineChart")
						{
							ShowLineSeries();
							SwingUtilities.updateComponentTreeUI(rightPanel);
						}
					}					
				}
		);
		
		pct1Item = new JMenuItem("Pct");
		pct1Item.addActionListener(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					func_val1 = "Pct";
				}
			}	
		);
			
		stddevItem = new JMenuItem("Stddev");
		stddevItem.addActionListener(
				new ActionListener()
				{	
					public void actionPerformed(ActionEvent e)
					{
						func_val = "Stddev";
					}
				}
		);
		
		stddev1Item = new JMenuItem("Stddev");
		stddev1Item.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						func_val1 = "Stddev";	
					}
				}
		);
		
		ytydiffItem = new JMenuItem("Ytydiff");
		ytydiffItem.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						func_val = "Ytydiff";
					}
				}
		);
		
		ytydiff1Item = new JMenuItem("Ytydiff");
		ytydiff1Item.addActionListener(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					func_val1 = "Ytydiff";
				}
			}
		);
		
		ytypctItem = new JMenuItem("Ytypct");
		ytypctItem.addActionListener(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					func_val = "Ytypct";
				}
			}
		);
		
		ytypct1Item = new JMenuItem("Ytypct");
		ytypct1Item.addActionListener(	
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					func_val1 = "Ytypct";
				}
			}	
		);
					
		exitItem = new JMenuItem("Exit");
		exitItem.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent ae) {
		          System.exit(0);
		       }
		      }
		);
		
		db1Names = new String[parser.mydb.length];
		System.out.println(db1Names.length + "db1Names.length");
			for(int i = 0; i < db1Names.length; i++)
			{
				db1Names[i] = parser.mydb[i];
			}
		
		dbList1 = new JList(db1Names);
		dbList1.setVisibleRowCount(4);
		dbList1.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		dbList1.setMaximumSize(new Dimension(150, 50));
		dbList1_listener  dblist1_listener = new dbList1_listener();
		dbList1.addListSelectionListener(dblist1_listener);
		
		db2Names = new String[parser.ss.length];
			for(int i = 0; i < parser.ss.length; i++)
			{
				db2Names[i] = parser.ss[i];
			}
		        
		dbList2 = new JList(db2Names);
		dbList2.setVisibleRowCount(4);
		dbList2.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		dbList2.setMaximumSize(new Dimension(150, 50));
		dbList2_listener dblist2_listener = new dbList2_listener();
		dbList2.addListSelectionListener(dblist2_listener);
		
		areaSeriesItem = new JMenuItem("AreaSeries");
		areaSeriesItem.addActionListener(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					chartType = "areaChart";	
				}
			}		
		);
			
		twoBarSeriesItem = new JMenuItem("BarSeries");
		twoBarSeriesItem.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						chartType = "twoBarChart";
						printComponent = "twobarpanel";
					}	
				}		
			);
		
		candleStickSeriesItem = new JMenuItem("CandleStickSeries");
		highLowSeriesItem = new JMenuItem("HighLowSeries");
		imageBarSeriesItem = new JMenuItem("ImageBarSeries");
		lineSeriesItem = new JMenuItem("LineSeries");
		lineSeriesItem.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						chartType = "lineChart";
						printComponent = "linechartpanel";
					}
				}
		);
		stackedBarSeriesItem = new JMenuItem("StackedBarSeries");
		stackedBarSeriesItem.addActionListener(
			new ActionListener()
			{
					public void actionPerformed(ActionEvent e)
					{
						chartType = "stackedBarChart";
						printComponent = "stackedbarpanel";
						ShowStackedBarSeries();
						rightPanel.removeAll();
						rightPanel.add(stackedBarPanel, BorderLayout.CENTER);
						SwingUtilities.updateComponentTreeUI(rightPanel);	
					}
			}		
		);
		
		stepSeriesItem = new JMenuItem("StepSeries");
		symbolSeriesItem = new JMenuItem("SymbolSeries");
							
		resetZoomItem = new JMenuItem("Reset zoom");
		resetZoomItem.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{						
						if(chartType == "lineChart")
						{
							if (oneChart == "aChart")
							{
								fameLineChart.getZoomManager().zoomReset();
							}
							if(oneChart == "mChart")
							{
								if (j == 1)
								{
									multiChart1.getZoomManager().zoomReset();
								}
								else if ( j == 2)
								{
									multiChart1.getZoomManager().zoomReset();
									multiChart2.getZoomManager().zoomReset();
								}
								else if ( j == 3)
								{
									multiChart1.getZoomManager().zoomReset();
									multiChart2.getZoomManager().zoomReset();
									multiChart3.getZoomManager().zoomReset();
								}
								else if ( j == 4)
								{
									multiChart1.getZoomManager().zoomReset();
									multiChart2.getZoomManager().zoomReset();
									multiChart3.getZoomManager().zoomReset();
									multiChart4.getZoomManager().zoomReset();
								}
								else if ( j == 5)
								{
									multiChart1.getZoomManager().zoomReset();
									multiChart2.getZoomManager().zoomReset();
									multiChart3.getZoomManager().zoomReset();
									multiChart4.getZoomManager().zoomReset();
									multiChart5.getZoomManager().zoomReset();
								}
								else if( j == 6)
								{
									multiChart1.getZoomManager().zoomReset();
									multiChart2.getZoomManager().zoomReset();
									multiChart3.getZoomManager().zoomReset();
									multiChart4.getZoomManager().zoomReset();
									multiChart5.getZoomManager().zoomReset();
									multiChart6.getZoomManager().zoomReset();
								}
								else if(j == 7)
								{
									multiChart1.getZoomManager().zoomReset();
									multiChart2.getZoomManager().zoomReset();
									multiChart3.getZoomManager().zoomReset();
									multiChart4.getZoomManager().zoomReset();
									multiChart5.getZoomManager().zoomReset();
									multiChart6.getZoomManager().zoomReset();
									multiChart7.getZoomManager().zoomReset();
								}
								else if(j == 8)
								{
									multiChart1.getZoomManager().zoomReset();
									multiChart2.getZoomManager().zoomReset();
									multiChart3.getZoomManager().zoomReset();
									multiChart4.getZoomManager().zoomReset();
									multiChart5.getZoomManager().zoomReset();
									multiChart6.getZoomManager().zoomReset();
									multiChart7.getZoomManager().zoomReset();
									multiChart8.getZoomManager().zoomReset();
								}
								else if(j == 9)
								{
									multiChart1.getZoomManager().zoomReset();
									multiChart2.getZoomManager().zoomReset();
									multiChart3.getZoomManager().zoomReset();
									multiChart4.getZoomManager().zoomReset();
									multiChart5.getZoomManager().zoomReset();
									multiChart6.getZoomManager().zoomReset();
									multiChart7.getZoomManager().zoomReset();
									multiChart8.getZoomManager().zoomReset();
									multiChart9.getZoomManager().zoomReset();
								}
								else if(j == 10)
								{
									multiChart1.getZoomManager().zoomReset();
									multiChart2.getZoomManager().zoomReset();
									multiChart3.getZoomManager().zoomReset();
									multiChart4.getZoomManager().zoomReset();
									multiChart5.getZoomManager().zoomReset();
									multiChart6.getZoomManager().zoomReset();
									multiChart7.getZoomManager().zoomReset();
									multiChart8.getZoomManager().zoomReset();
									multiChart9.getZoomManager().zoomReset();
									multiChart10.getZoomManager().zoomReset();
								}
								else if(j == 11)
								{
									multiChart1.getZoomManager().zoomReset();
									multiChart2.getZoomManager().zoomReset();
									multiChart3.getZoomManager().zoomReset();
									multiChart4.getZoomManager().zoomReset();
									multiChart5.getZoomManager().zoomReset();
									multiChart6.getZoomManager().zoomReset();
									multiChart7.getZoomManager().zoomReset();
									multiChart8.getZoomManager().zoomReset();
									multiChart9.getZoomManager().zoomReset();
									multiChart10.getZoomManager().zoomReset();
									multiChart11.getZoomManager().zoomReset();
								}
								else if(j == 12)
								{
									multiChart1.getZoomManager().zoomReset();
									multiChart2.getZoomManager().zoomReset();
									multiChart3.getZoomManager().zoomReset();
									multiChart4.getZoomManager().zoomReset();
									multiChart5.getZoomManager().zoomReset();
									multiChart6.getZoomManager().zoomReset();
									multiChart7.getZoomManager().zoomReset();
									multiChart8.getZoomManager().zoomReset();
									multiChart9.getZoomManager().zoomReset();
									multiChart10.getZoomManager().zoomReset();
									multiChart11.getZoomManager().zoomReset();
									multiChart12.getZoomManager().zoomReset();
								}
							}
						}
						else if (chartType == "barChart")
						{
							bar1.getZoomManager().zoomReset();
							bar2.getZoomManager().zoomReset();
							bar3.getZoomManager().zoomReset();
							bar4.getZoomManager().zoomReset();
							bar5.getZoomManager().zoomReset();
							bar6.getZoomManager().zoomReset();
						}	
						else if(chartType == "twoBarChart")
						{
							if(oneChart == "aChart")
							{
								twoBarChart.getZoomManager().zoomReset();
							}	
							if(oneChart == "mChart")					
							{	
								if (j == 1)
								{
									multiChart1.getZoomManager().zoomReset();
								}
								else if ( j == 2)
								{
									multiChart1.getZoomManager().zoomReset();
									multiChart2.getZoomManager().zoomReset();
								}
								else if ( j == 3)
								{
									multiChart1.getZoomManager().zoomReset();
									multiChart2.getZoomManager().zoomReset();
									multiChart3.getZoomManager().zoomReset();
								}
								else if ( j == 4)
								{
									multiChart1.getZoomManager().zoomReset();
									multiChart2.getZoomManager().zoomReset();
									multiChart3.getZoomManager().zoomReset();
									multiChart4.getZoomManager().zoomReset();
								}
								else if(j == 5)
								{
									multiChart1.getZoomManager().zoomReset();
									multiChart2.getZoomManager().zoomReset();
									multiChart3.getZoomManager().zoomReset();
									multiChart4.getZoomManager().zoomReset();
									multiChart5.getZoomManager().zoomReset();
								}
								else if(j == 6)
								{
									multiChart1.getZoomManager().zoomReset();
									multiChart2.getZoomManager().zoomReset();
									multiChart3.getZoomManager().zoomReset();
									multiChart4.getZoomManager().zoomReset();
									multiChart5.getZoomManager().zoomReset();
									multiChart6.getZoomManager().zoomReset();
								}
								else if(j == 7)
								{
									multiChart1.getZoomManager().zoomReset();
									multiChart2.getZoomManager().zoomReset();
									multiChart3.getZoomManager().zoomReset();
									multiChart4.getZoomManager().zoomReset();
									multiChart5.getZoomManager().zoomReset();
									multiChart6.getZoomManager().zoomReset();
									multiChart7.getZoomManager().zoomReset();
								}
								else if(j == 8)
								{
									multiChart1.getZoomManager().zoomReset();
									multiChart2.getZoomManager().zoomReset();
									multiChart3.getZoomManager().zoomReset();
									multiChart4.getZoomManager().zoomReset();
									multiChart5.getZoomManager().zoomReset();
									multiChart6.getZoomManager().zoomReset();
									multiChart7.getZoomManager().zoomReset();
									multiChart8.getZoomManager().zoomReset();
								}
								else if(j == 9)
								{
									multiChart1.getZoomManager().zoomReset();
									multiChart2.getZoomManager().zoomReset();
									multiChart3.getZoomManager().zoomReset();
									multiChart4.getZoomManager().zoomReset();
									multiChart5.getZoomManager().zoomReset();
									multiChart6.getZoomManager().zoomReset();
									multiChart7.getZoomManager().zoomReset();
									multiChart8.getZoomManager().zoomReset();
									multiChart9.getZoomManager().zoomReset();
								}
								else if(j == 10)
								{
									multiChart1.getZoomManager().zoomReset();
									multiChart2.getZoomManager().zoomReset();
									multiChart3.getZoomManager().zoomReset();
									multiChart4.getZoomManager().zoomReset();
									multiChart5.getZoomManager().zoomReset();
									multiChart6.getZoomManager().zoomReset();
									multiChart7.getZoomManager().zoomReset();
									multiChart8.getZoomManager().zoomReset();
									multiChart9.getZoomManager().zoomReset();
									multiChart10.getZoomManager().zoomReset();
								}
								else if(j == 11)
								{
									multiChart1.getZoomManager().zoomReset();
									multiChart2.getZoomManager().zoomReset();
									multiChart3.getZoomManager().zoomReset();
									multiChart4.getZoomManager().zoomReset();
									multiChart5.getZoomManager().zoomReset();
									multiChart6.getZoomManager().zoomReset();
									multiChart7.getZoomManager().zoomReset();
									multiChart8.getZoomManager().zoomReset();
									multiChart9.getZoomManager().zoomReset();
									multiChart10.getZoomManager().zoomReset();
									multiChart11.getZoomManager().zoomReset();
								}
								else if(j == 12)
								{
									multiChart1.getZoomManager().zoomReset();
									multiChart2.getZoomManager().zoomReset();
									multiChart3.getZoomManager().zoomReset();
									multiChart4.getZoomManager().zoomReset();
									multiChart5.getZoomManager().zoomReset();
									multiChart6.getZoomManager().zoomReset();
									multiChart7.getZoomManager().zoomReset();
									multiChart8.getZoomManager().zoomReset();
									multiChart9.getZoomManager().zoomReset();
									multiChart10.getZoomManager().zoomReset();
									multiChart11.getZoomManager().zoomReset();
									multiChart12.getZoomManager().zoomReset();
									
								}
							}
						}		
						else if (chartType == "areaChart")
						{
							if(oneChart == "aChart")
							{
								areaChart.getZoomManager().zoomReset();
							}
							if(oneChart == "mChart")
							{	
								if (j == 1)
								{
									multiChart1.getZoomManager().zoomReset();
								}
								else if ( j == 2)
								{
									multiChart1.getZoomManager().zoomReset();
									multiChart2.getZoomManager().zoomReset();
								}
								else if ( j == 3)
								{
									multiChart1.getZoomManager().zoomReset();
									multiChart2.getZoomManager().zoomReset();
									multiChart3.getZoomManager().zoomReset();
								}
								else if ( j == 4)
								{
									multiChart1.getZoomManager().zoomReset();
									multiChart2.getZoomManager().zoomReset();
									multiChart3.getZoomManager().zoomReset();
									multiChart4.getZoomManager().zoomReset();
								}
								else if(j == 5)
								{
									multiChart1.getZoomManager().zoomReset();
									multiChart2.getZoomManager().zoomReset();
									multiChart3.getZoomManager().zoomReset();
									multiChart4.getZoomManager().zoomReset();
									multiChart5.getZoomManager().zoomReset();
								}
								else if(j == 6)
								{
									multiChart1.getZoomManager().zoomReset();
									multiChart2.getZoomManager().zoomReset();
									multiChart3.getZoomManager().zoomReset();
									multiChart4.getZoomManager().zoomReset();
									multiChart5.getZoomManager().zoomReset();
									multiChart6.getZoomManager().zoomReset();
								}
								else if(j == 7)
								{
									multiChart1.getZoomManager().zoomReset();
									multiChart2.getZoomManager().zoomReset();
									multiChart3.getZoomManager().zoomReset();
									multiChart4.getZoomManager().zoomReset();
									multiChart5.getZoomManager().zoomReset();
									multiChart6.getZoomManager().zoomReset();
									multiChart7.getZoomManager().zoomReset();
								}
								else if(j == 8)
								{
									multiChart1.getZoomManager().zoomReset();
									multiChart2.getZoomManager().zoomReset();
									multiChart3.getZoomManager().zoomReset();
									multiChart4.getZoomManager().zoomReset();
									multiChart5.getZoomManager().zoomReset();
									multiChart6.getZoomManager().zoomReset();
									multiChart7.getZoomManager().zoomReset();
									multiChart8.getZoomManager().zoomReset();
								}
								else if(j == 9)
								{
									multiChart1.getZoomManager().zoomReset();
									multiChart2.getZoomManager().zoomReset();
									multiChart3.getZoomManager().zoomReset();
									multiChart4.getZoomManager().zoomReset();
									multiChart5.getZoomManager().zoomReset();
									multiChart6.getZoomManager().zoomReset();
									multiChart7.getZoomManager().zoomReset();
									multiChart8.getZoomManager().zoomReset();
									multiChart9.getZoomManager().zoomReset();
								}
								else if(j == 10)
								{
									multiChart1.getZoomManager().zoomReset();
									multiChart2.getZoomManager().zoomReset();
									multiChart3.getZoomManager().zoomReset();
									multiChart4.getZoomManager().zoomReset();
									multiChart5.getZoomManager().zoomReset();
									multiChart6.getZoomManager().zoomReset();
									multiChart7.getZoomManager().zoomReset();
									multiChart8.getZoomManager().zoomReset();
									multiChart9.getZoomManager().zoomReset();
									multiChart10.getZoomManager().zoomReset();
								}
								else if(j == 11)
								{
									multiChart1.getZoomManager().zoomReset();
									multiChart2.getZoomManager().zoomReset();
									multiChart3.getZoomManager().zoomReset();
									multiChart4.getZoomManager().zoomReset();
									multiChart5.getZoomManager().zoomReset();
									multiChart6.getZoomManager().zoomReset();
									multiChart7.getZoomManager().zoomReset();
									multiChart8.getZoomManager().zoomReset();
									multiChart9.getZoomManager().zoomReset();
									multiChart10.getZoomManager().zoomReset();
									multiChart11.getZoomManager().zoomReset();
								}
								else if(j == 12)
								{
									multiChart1.getZoomManager().zoomReset();
									multiChart2.getZoomManager().zoomReset();
									multiChart3.getZoomManager().zoomReset();
									multiChart4.getZoomManager().zoomReset();
									multiChart5.getZoomManager().zoomReset();
									multiChart6.getZoomManager().zoomReset();
									multiChart7.getZoomManager().zoomReset();
									multiChart8.getZoomManager().zoomReset();
									multiChart9.getZoomManager().zoomReset();
									multiChart10.getZoomManager().zoomReset();
									multiChart11.getZoomManager().zoomReset();
									multiChart12.getZoomManager().zoomReset();
								}
							}
						}
						else if(chartType == "stackedBarChart")
						{
							stackedbarChart.getZoomManager().zoomReset();
						}	
						else if(chartType == "twoFuncChart")
						{
							twoFuncChart.getZoomManager().zoomReset();
						}
					}	
				}
		);
		
		onItem = new JMenuItem("Light Grid");
		onItem.addActionListener(	
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					if(chartType == "areaChart")
					{
						if(func_val == null)
						{
							areaPane.getLeftAxis().setUseInGrid(true);
							areaChart.getDateAxis().setShowGrid(true);
							areaChart.updateDisplay();
						}
						else if(func_val == "Original")
						{
							areaPane.getLeftAxis().setUseInGrid(true);
							areaChart.getDateAxis().setShowGrid(true);
							areaChart.updateDisplay();
						}
						else 
						{
							areaPane.getLeftAxis().setUseInGrid(true);
							areaChart.getDateAxis().setShowGrid(true);
							areaChart.updateDisplay();
						}
					}
					else if(chartType == "twoBarChart")
					{
						if(func_val == null)
						{
							barPane.getLeftAxis().setUseInGrid(true);
							twoBarChart.getDateAxis().setShowGrid(true);
							twoBarChart.updateDisplay();
						}
						else if (func_val == "Original")
						{
							barPane.getLeftAxis().setUseInGrid(true);
							twoBarChart.getDateAxis().setShowGrid(true);
							twoBarChart.updateDisplay();
						}
						else 
						{
							barPane.getLeftAxis().setUseInGrid(true);
							twoBarChart.getDateAxis().setShowGrid(true);
							twoBarChart.updateDisplay();
						}
					}
					else if(chartType == "lineChart")
					{
						if(func_val == null)
						{
							linePane.getLeftAxis().setUseInGrid(true);
							fameLineChart.getDateAxis().setShowGrid(true);
							fameLineChart.updateDisplay();
						}
						else if (func_val == "Original")
						{
							linePane.getLeftAxis().setUseInGrid(true);
							fameLineChart.getDateAxis().setShowGrid(true);
							fameLineChart.updateDisplay();
						}
						else 
						{
							linePane.getLeftAxis().setUseInGrid(true);
							fameLineChart.getDateAxis().setShowGrid(true);
							fameLineChart.updateDisplay();
						}
					}
				}
			}
				
		); 
		
		offItem = new JMenuItem("No Grid");
		offItem.addActionListener(		
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						if(chartType == "areaChart")
						{
							if(func_val == null)
							{
								if(func_val == null)
								{
									areaPane.getLeftAxis().setUseInGrid(false);
									areaChart.getDateAxis().setShowGrid(false);
									areaChart.updateDisplay();
								}
								else if(func_val == "Original")
								{
									areaPane.getLeftAxis().setUseInGrid(false);
									areaChart.getDateAxis().setShowGrid(false);
									areaChart.updateDisplay();
								}
								else 
								{
									areaPane.getLeftAxis().setUseInGrid(false);
									areaChart.getDateAxis().setShowGrid(false);
									areaChart.updateDisplay();
								}
							}		
						}
						else if(chartType == "twoBarChart")
						{
							if(func_val == null)
							{
								barPane.getLeftAxis().setUseInGrid(false);
								twoBarChart.getDateAxis().setShowGrid(false);
								twoBarChart.updateDisplay();
							}
							else if (func_val == "Original")
							{
								barPane.getLeftAxis().setUseInGrid(false);
								twoBarChart.getDateAxis().setShowGrid(false);
								twoBarChart.updateDisplay();
							}
							else 
							{
								barPane.getLeftAxis().setUseInGrid(false);
								twoBarChart.getDateAxis().setShowGrid(false);
								twoBarChart.updateDisplay();
							}
						}
						else if(chartType == "lineChart")
						{
							if(func_val == null)
							{
								linePane.getLeftAxis().setUseInGrid(false);
								fameLineChart.getDateAxis().setShowGrid(false);
								fameLineChart.updateDisplay();
							}
							else if (func_val == "Original")
							{
								linePane.getLeftAxis().setUseInGrid(false);
								fameLineChart.getDateAxis().setShowGrid(false);
								fameLineChart.updateDisplay();
							}
							else 
							{
								linePane.getLeftAxis().setUseInGrid(false);
								fameLineChart.getDateAxis().setShowGrid(false);
								fameLineChart.updateDisplay();
							}
						}
					}
				}	
		);
		
		darkItem = new JMenuItem("Dark Grid");	
		kpiProcItem = new JMenuItem("KPI_Publ.");	
		//sp2 = new JScrollPane(ppiList);
		sp2 = new JScrollPane(dbList1);
		sp2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		sp2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		sp2.setAutoscrolls(true);
		sp2.setMaximumSize(new Dimension(150, 100));
		sp2.setPreferredSize(new Dimension(150, 100));		
		sp2a = new JScrollPane(dbList2);
		sp2a.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		sp2a.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		sp2a.setMaximumSize(new Dimension(150, 100));
		sp2a.setPreferredSize(new Dimension(150, 100));
		
		get_val = new JButton("Get Series Values");
	  	button_listener b = new  button_listener();
	  	get_val.addActionListener(b);
		
		clear = new JButton("Clear");
	    clear_listener c = new clear_listener();
	  	clear.addActionListener(c);
	  	
	  	// Add listener for searching series by wildcard in JTextField 
	  	key_listener_text t = new key_listener_text();
	  	s_label = new JLabel("Enter Wildcard");
	  	s_text = new JTextField();
	  	s_text.setMinimumSize(new Dimension(150, 25));
	  	s_text.setPreferredSize(new Dimension(150, 25));
	  	s_text.setMaximumSize(new Dimension(150, 25));
	  	s_text.addKeyListener(t);
	  	//s_text.setMaximumSize(new Dimension (100,25));
	  	
	  	// Add listener for searching descriptions by wildcard in JTextField 
	  	key_listener_text1 t1 = new key_listener_text1();
	  	desc_label = new JLabel("Enter Text String");
	  	desc_text = new JTextField();
	  	desc_text.addKeyListener(t1);
	  	desc_text.setMinimumSize(new Dimension(150, 25));
	  	desc_text.setPreferredSize(new Dimension(150, 25));
	  	desc_text.setMaximumSize(new Dimension(150, 25));  	
	  	
	  	timescale_label = new JLabel("Time Scale  ");
	  	freq_val = "Original";
	  	Vector freqvec = new Vector();
	  	freqvec.addElement("Original");
	  	freqvec.addElement("MONTHLY"); 	
	  	freqvec.addElement("QUARTERLY");
	  	freqvec.addElement("ANNUAL");
	  	
	  	freq = new JComboBox(freqvec);
	  	freq.setBackground(Color.white);
	  	freq.setMinimumSize(new Dimension(150, 25)); 
	  	freq.setPreferredSize(new Dimension(150, 25)); 
	  	freq.setMaximumSize(new Dimension(150, 25));
	  	freq_listener freq_list = new freq_listener();
	  	freq.addActionListener(freq_list);
	  	
	 // get start year
		cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		
		start = "Start date";
		sdvec = new Vector();
		sdvec.add("Start date");
		
		for (int i = 30; i >= 0; i--)
		{
			sdvec.addElement(year-i);		
		}
	    
		// get end year
		end = "End date";
		edvec = new Vector();
		edvec.add("End date");
		for (int i = 30; i >= 0; i--)
		{
			edvec.addElement(year-i);
		}
	  	
		cb_sdate = new JComboBox(sdvec);
	  	cb_sdate.setMinimumSize(new Dimension(100, 25));
	  	cb_sdate.setMaximumSize(new Dimension(100, 25));	
	  	cb_sdate.setBackground(Color.white);
	  	sdate_listener sd = new sdate_listener();
	  	cb_sdate.addActionListener(sd);
	  	
	  	cb_edate = new JComboBox(edvec);
	  	cb_edate.setMinimumSize(new Dimension(100,25));
	  	cb_edate.setMaximumSize(new Dimension(100,25));	
	  	cb_edate.setBackground(Color.white);
	  	edate_listener ed = new edate_listener();
	  	cb_edate.addActionListener(ed);
	  	
	  	byearLabel = new JLabel("Set common Baseyear  ");
	  	//byear = "Set Common Baseyear..";
	  	//byear = " ";
	  	byearvec = new Vector();
	  	//byearvec.add(byear);
	  	
	  	for(int i = 20; i >=0; i--)
	  	{	
	  		byearvec.addElement(year-i);
	  	}
	  	cb_byear = new JComboBox(byearvec);
	  	cb_byear.setMaximumSize(new Dimension(100, 25));
	  	cb_byear.setPreferredSize(new Dimension(100, 25));
	  	cb_byear.setBackground(Color.WHITE);
	  	byear_listener byear_l = new byear_listener();
	  	cb_byear.addActionListener(byear_l);
	  	
		// add component to menu bar
		menuBar.add(fileMenu);
		menuBar.add(chosenStats);                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             
		menuBar.add(langMenu);
		menuBar.add(functorMenu);
		menuBar.add(chartMenu);
		menuBar.add(zoomMenu);
		menuBar.add(procMenu);
		menuBar.add(byearLabel);
		menuBar.add(cb_byear);
		menuBar.add(gridMenu);
		
		fileMenu.add(exportItem);		
		//fileMenu.add(showTableItem);
		fileMenu.add(saveAsPngItem);
		//fileMenu.add(saveAsTxt);
		fileMenu.add(saveAsHtmlItem);
		fileMenu.add(printItem);
		fileMenu.add(exitItem);
		
		chosenStats.add(ppiItem);
		chosenStats.add(konjbarItem);
		chosenStats.add(ordreMenu);
		//chosenStats.add(lagerMenu);
		chosenStats.add(new JSeparator());
		chosenStats.add(tableMenu);
		ordreMenu.add(ordreTab1Item);
		ordreMenu.add(ordreTab2Item);
		ordreMenu.add(ordreHtmlTab1Item);
		ordreMenu.add(ordreHtmlTab2Item);
		ordreMenu.add(ordreHtmlEngTab1Item);
		ordreMenu.add(ordreHtmlEngTab2Item);
	
		tableMenu.add(invest07tabItem);
		tableMenu.add(investtabItem);
		tableMenu.add(knrtabItem);
		tableMenu.add(konjbar07tabItem);
		tableMenu.add(konjbartabItem);
		tableMenu.add(konsumpristabItem);
													
		functorMenu.add(originalItem);
		functorMenu.add(func1Menu);
		functorMenu.add(new JSeparator());
		functorMenu.add(func2Menu);
		
		func1Menu.add(annpctItem);
		func1Menu.add(aveItem);
		func1Menu.add(csumItem);
		func1Menu.add(diffItem);
		func1Menu.add(laveItem);
		func1Menu.add(lmaxItem);
		func1Menu.add(lminItem);
		func1Menu.add(lsumItem);
		func1Menu.add(maveItem);
		func1Menu.add(mavecItem);
		func1Menu.add(mstddevItem);
		func1Menu.add(pctItem);
		func1Menu.add(stddevItem);
		func1Menu.add(ytydiffItem);
		func1Menu.add(ytypctItem);
		
		func2Menu.add(annpct1Item);
		func2Menu.add(ave1Item);
		func2Menu.add(csum1Item);
		func2Menu.add(diff1Item);
		func2Menu.add(lave1Item);
		func2Menu.add(lmax1Item);
		func2Menu.add(lmin1Item);
		func2Menu.add(lsum1Item);
		func2Menu.add(mave1Item);
		func2Menu.add(mavec1Item);
		func2Menu.add(mstddev1Item);
		func2Menu.add(pct1Item);
		func2Menu.add(stddev1Item);
		func2Menu.add(ytydiff1Item);
		func2Menu.add(ytypct1Item);
		
		langMenu.add(norItem);
		langMenu.add(engItem);
		langMenu.add(bothItem);		
		
		chartMenu.add(new JSeparator());
		chartMenu.add(graphType);
		
		graphType.add(areaSeriesItem);
		graphType.add(twoBarSeriesItem);
	
		graphType.add(candleStickSeriesItem);
		graphType.add(highLowSeriesItem);
		graphType.add(imageBarSeriesItem);
		graphType.add(lineSeriesItem);
		graphType.add(stackedBarSeriesItem);
		graphType.add(stepSeriesItem);
		graphType.add(symbolSeriesItem);						
		zoomMenu.add(resetZoomItem);
		procMenu.add(kpiProcItem);
		gridMenu.add(offItem);
		gridMenu.add(onItem);
		gridMenu.add(darkItem);
		
	    label1 = new JLabel("This is TimeIQ Chartspanel");
	    label2 = new JLabel("Database");
	    label3 = new JLabel("Dato:");
  	      			
	    //Added 24.06.2011
	    startLabel = new JLabel("Date  From");
	    endLabel = new JLabel(" To ");
	    
	    //Added 24.06.2011
	    whats_b = new JButton("Whats");
	    whats_b_listener whats_b_l = new whats_b_listener();
	    whats_b.addActionListener(whats_b_l);
	   
	    select_b = new JButton("Select *");
	    select_b_listener select_b_l = new select_b_listener();
	    select_b.addActionListener(select_b_l);
	    
	    unselect_b = new JButton("Unselect *");
	    unselect_b_listener unselect_b_l = new unselect_b_listener();
	    unselect_b.addActionListener(unselect_b_l);
	    
	    graph_b = new JButton("Graph");
	    graph_b_listener graph_b_l = new graph_b_listener();
	    graph_b.addActionListener(graph_b_l);
	    
	    multigraph_b = new JButton("Multigraph");
	    multigraph_b_listener multigraph_b_l = new multigraph_b_listener();
	    multigraph_b.addActionListener(multigraph_b_l);
	    
	    multifuncgraph_b = new JButton("Multi-Function Graph");
	    multifuncgraph_b_listener multifuncgraph_b_l = new multifuncgraph_b_listener();
	    multifuncgraph_b.addActionListener(multifuncgraph_b_l);
	        
	    cleanup_b = new JButton("Clean Up");
	    cleanup_b_listener cleanup_b_l = new cleanup_b_listener();
	    cleanup_b.addActionListener(cleanup_b_l);
	    
	    tableArea = new JTextArea(30, 600 );
	    tableArea.setBackground(Color.WHITE);
		sp1 = new JScrollPane(tableArea);
		sp1.setMinimumSize(new Dimension(200,200));
	    //sp1.setPreferredSize(new Dimension(200,200));
		sp1.setPreferredSize(new Dimension(700,200));
	    
		serDisp = new JTextArea(30, 600);
		serDisp.setBackground(Color.WHITE);
		
		byearDisp = new JTextArea(30, 600);
		byearDisp.setBackground(Color.WHITE);
		
		northJPanel = new JPanel();
		
		northJPanel.add(menuBar, BorderLayout.WEST);
		northJPanel.setLayout(new BorderLayout());
		northJPanel.add(menuBar);  
			
		leftNorthPanel = new JPanel(new GridBagLayout());	
		GridBagConstraints gc1 = new GridBagConstraints();
		gc1.fill = GridBagConstraints.HORIZONTAL;
		gc1.gridx = 0;
		gc1.gridy = 0;
		gc1.insets = new Insets(10, 10, 0, 10);
		leftNorthPanel.add(label2, gc1);
		
		gc1.fill = GridBagConstraints.VERTICAL;
		gc1.gridx = 0;
		gc1.gridy = 1;
		gc1.insets = new Insets(10, 10, 0, 10);
		leftNorthPanel.add(sp2, gc1);
		
		gc1.fill = GridBagConstraints.VERTICAL;
		gc1.gridx = 1;
		gc1.gridy = 1;
		gc1.insets = new Insets(10, 0, 0, 10);
		leftNorthPanel.add(sp2a, gc1);
		
		leftMiddlePanel = new JPanel(new GridLayout(3, 1 , 0, 20));
		
		middleInnePanel1 = new JPanel(new GridBagLayout());
		middleInnePanel1.setBorder(BorderFactory.createTitledBorder("Search"));
		middleInnePanel2 = new JPanel(new GridBagLayout());
		middleInnePanel2.setBorder(BorderFactory.createTitledBorder("Select the date range and TimeScaling"));
		middleInnePanel3 = new JPanel(new GridBagLayout());
		middleInnePanel3.setBorder(BorderFactory.createTitledBorder("Display Series and TimeIQ Charts"));
		GridBagConstraints gc = new GridBagConstraints();
		
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.gridx = 0;
		gc.gridy = 0;
		gc.insets = new Insets(10, 0, 0, 10);	
		middleInnePanel1.add(s_label, gc);
		
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.gridx = 1;
		gc.gridy = 0;
		gc.gridheight = 1;
		gc.gridwidth = 1;
		gc.insets = new Insets(10, 0, 0, 10);
		middleInnePanel1.add(s_text, gc);
			
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.gridx = 0;
		gc.gridy = 1;
		gc.insets = new Insets(10, 0, 0, 10);  //top padding
		middleInnePanel1.add(desc_label, gc);
		
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.gridx = 1;
		gc.gridy = 1;
		gc.gridheight = 1;
		gc.gridwidth = 1;
		gc.insets = new Insets(10, 0, 0, 10);  //top padding
		middleInnePanel1.add(desc_text, gc);
		
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.gridx = 0;
		gc.gridy = 2;
		gc.insets = new Insets(10, 0, 0, 10);
		middleInnePanel1.add(select_b, gc);
		
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.gridx = 1;
		gc.gridy = 2;
		gc.insets = new Insets(10, 0, 0, 10);
		middleInnePanel1.add(unselect_b, gc);
		
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.gridx = 0;
		gc.gridy = 3;
		gc.insets = new Insets(10, 0, 0, 10);
		middleInnePanel1.add(cleanup_b, gc);
		
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.gridx = 1;
		gc.gridy = 3;
		gc.insets = new Insets(10, 0, 0, 10);
		middleInnePanel1.add(clear, gc);
			
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.gridx = 0;
		gc.gridy = 0;
		gc.insets = new Insets(10, 0, 0, 10);
		middleInnePanel2.add(startLabel, gc);
		
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.gridx = 1;
		gc.gridy = 0;
		gc.insets = new Insets(10, 0, 0, 10);
		middleInnePanel2.add(endLabel, gc);
		
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.gridx = 0;
		gc.gridy = 1;
		gc.insets = new Insets(20, 0, 0, 10);
		middleInnePanel2.add(cb_sdate, gc);
		
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.gridx = 1;
		gc.gridy = 1;	
		gc.insets = new Insets(20, 0, 0, 10);
		middleInnePanel2.add(cb_edate, gc);
		
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.gridx = 0;
		gc.gridy = 2;
		gc.gridheight = 1;
		gc.gridwidth = 1;
		gc.insets = new Insets(10, 0, 0, 10);
		middleInnePanel2.add(timescale_label, gc);
		
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.gridx = 1;
		gc.gridy = 2;
		gc.gridheight = 1;
		gc.gridwidth = 1;
		gc.insets = new Insets(10, 0, 0, 10);
		middleInnePanel2.add(freq, gc);
		
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.gridx = 0;
		gc.gridy = 0;
		gc.gridheight = 1;
		gc.gridwidth = 1;
		gc.insets = new Insets(10, 0, 0, 10);
		middleInnePanel3.add(whats_b, gc);
		
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.gridx = 1;
		gc.gridy = 0;
		gc.gridheight = 1;
		gc.gridwidth = 1;
		gc.insets = new Insets(10, 0, 0, 10);
		middleInnePanel3.add(get_val, gc);
		
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.gridx = 0;
		gc.gridy = 1;
		gc.gridheight = 1;
		gc.gridwidth = 1;
		gc.insets = new Insets(10, 0, 0, 10);
		//leftMiddlePanel.add(graph_b, gc);
		middleInnePanel3.add(graph_b, gc);
			
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.gridx = 1;
		gc.gridy = 1;	
		gc.gridwidth = 1;
		gc.insets = new Insets(10, 0, 0, 10);
		middleInnePanel3.add(multigraph_b, gc);
		
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.gridx = 0;
		gc.gridy = 2;
		gc.gridheight = 1;
		gc.gridwidth = 1;
		gc.insets = new Insets(10, 0, 0, 10);
		middleInnePanel3.add(multifuncgraph_b, gc);
		
		leftMiddlePanel.add(middleInnePanel1);
		leftMiddlePanel.add(middleInnePanel2);
		leftMiddlePanel.add(middleInnePanel3);
				
		leftPanel = new JPanel(new BorderLayout(10,10));
		leftPanel.add(leftNorthPanel, BorderLayout.NORTH);
		leftPanel.add(leftMiddlePanel, BorderLayout.CENTER);
		leftPanel.setSize(1200,600);
		
		rightPanel = new JPanel(new BorderLayout(10, 10));
					
		//add 26.09.2011
		tabbedPane = new JTabbedPane();
		tabbedPanel1 = new JPanel();
		tabbedPanel1.setLayout(new BorderLayout());
		//tabbedPanel1.setBackground(Color.WHITE);
		tabbedPanel1.setSize(600, 400);	
		tabbedPanel2 = new JPanel();
		tabbedPanel2.setLayout(new BorderLayout());
		tabbedPanel3 = new JPanel();
		tabbedPanel3.setLayout(new BorderLayout());
		tabbedPanel4 = new JPanel();
		tabbedPanel4.setLayout(new BorderLayout());
		tabbedPanel5 = new JPanel();
		tabbedPanel5.setLayout(new BorderLayout());
		tabbedPanel6 = new JPanel();
		tabbedPanel6.setLayout(new BorderLayout());
		tabbedPane.addTab("Series / Description", tabbedPanel1);
		tabbedPane.setMnemonicAt(0, KeyEvent.VK_S);
		tabbedPane.addTab("Whats",  tabbedPanel2);
		tabbedPane.setMnemonicAt(1, KeyEvent.VK_W);
		tabbedPane.addTab("Display Values", tabbedPanel3);
		tabbedPane.setMnemonicAt(2, KeyEvent.VK_D);
		tabbedPane.addTab("Show Graph ", tabbedPanel4);
		tabbedPane.setMnemonicAt(3, KeyEvent.VK_G);
		tabbedPane.addTab("Show Multigraph", tabbedPanel5);
		tabbedPane.setMnemonicAt(4, KeyEvent.VK_M);	
		tabbedPane.addTab("Show Multi-Function Graph", tabbedPanel6);
		tabbedPane.setMnemonicAt(5, KeyEvent.VK_F);
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
			
		areaPanel = new JPanel();
		areaPanel.setLayout(new BorderLayout());
		areaPanel.setBackground(Color.white);
		
		barPanel = new JPanel();
		barPanel.setLayout(new GridLayout(3,2));
		barPanel.setBackground(Color.WHITE);
		
		twoBarPanel = new JPanel();
		twoBarPanel.setLayout(new BorderLayout());
		twoBarPanel.setBackground(Color.white);
		
		stackedBarPanel = new JPanel();
		stackedBarPanel.setLayout(new BorderLayout());
		stackedBarPanel.setBackground(Color.white);
			
		lineChartPanel = new JPanel();
		lineChartPanel.setLayout(new BorderLayout());
		lineChartPanel.setBackground(Color.WHITE);
		
		multiChartPanel = new JPanel();
		multiChartPanel.setLayout(new GridLayout(4, 2));
		multiChartPanel.setBackground(Color.WHITE);
		
		twoFuncChartPanel = new JPanel();
		twoFuncChartPanel.setLayout(new BorderLayout());
		twoFuncChartPanel.setBackground(Color.WHITE);
		
		table1Panel = new JPanel();
		table1Panel.setLayout(new BorderLayout());
		table1Panel.setBackground(Color.WHITE);
		rightPanel.add(tabbedPane, BorderLayout.CENTER);
			
		jsp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
		
		Container container = getContentPane();
		//container.setLayout(new BorderLayout());
		container.setLayout(new BorderLayout());
		container.add(northJPanel, BorderLayout.NORTH);
		//container.add(middleJPanel, BorderLayout.CENTER);
		container.add(jsp, BorderLayout.CENTER);
		//container.add(southJPanel, BorderLayout.SOUTH);
		setSize(1300, 820);	
		setVisible(true);
						
	} // end second Try
	catch (Throwable t) {
		t.printStackTrace();
	} 			
	// comment out end of first try 16.03.2012
	
	} // end TiqTest constructor
	
	private void setTitle(String string) {
		
	}

	public void getMultiLegendEntry()
	{
		legendentry = new LegendEntry[j];
		if(j == 1)
		{
			if(chartType == "areaChart")
			{
				legendentry[0] = areaser1[0].getLegendEntry();
			}
			else if(chartType == "twoBarChart")
			{
				legendentry[0] = areaser1[0].getLegendEntry();
			}
			else if(chartType == "lineChart")
			{
				legendentry[0] = areaser1[0].getLegendEntry();
			}
		}
		else if(j == 2)
		{
			if(chartType == "areaChart")
			{
				legendentry[0] = areaser1[0].getLegendEntry();
				legendentry[1] = areaser2[0].getLegendEntry();
			}
			else if(chartType == "twoBarChart")
			{
				legendentry[0] = areaser1[0].getLegendEntry();
				legendentry[1] = areaser2[0].getLegendEntry();
			}
			else if(chartType == "lineChart")
			{
				legendentry[0] = areaser1[0].getLegendEntry();
				legendentry[1] = areaser2[0].getLegendEntry();
			}
		}
		else if(j == 3)
		{
			if(chartType == "areaChart")
			{
				legendentry[0] = areaser1[0].getLegendEntry();
				legendentry[1] = areaser2[0].getLegendEntry();
				legendentry[2] = areaser3[0].getLegendEntry();
			}
			else if(chartType == "twoBarChart")
			{
				legendentry[0] = areaser1[0].getLegendEntry();
				legendentry[1] = areaser2[0].getLegendEntry();
				legendentry[2] = areaser3[0].getLegendEntry();
			}
			else if(chartType == "lineChart")
			{
				legendentry[0] = areaser1[0].getLegendEntry();
				legendentry[1] = areaser2[0].getLegendEntry();
				legendentry[2] = areaser3[0].getLegendEntry();
			}
		}
		else if(j == 4)
		{
			if(chartType == "areaChart")
			{
				legendentry[0] = areaser1[0].getLegendEntry();
				legendentry[1] = areaser2[0].getLegendEntry();
				legendentry[2] = areaser3[0].getLegendEntry();
				legendentry[3] = areaser4[0].getLegendEntry();
			}
			else if(chartType == "twoBarChart")
			{
				legendentry[0] = areaser1[0].getLegendEntry();
				legendentry[1] = areaser2[0].getLegendEntry();
				legendentry[2] = areaser3[0].getLegendEntry();
				legendentry[3] = areaser4[0].getLegendEntry();
			}
			else if(chartType == "lineChart")
			{
				legendentry[0] = areaser1[0].getLegendEntry();
				legendentry[1] = areaser2[0].getLegendEntry();
				legendentry[2] = areaser3[0].getLegendEntry();
				legendentry[3] = areaser4[0].getLegendEntry();
			}
		}
		else if(j == 5)
		{
			if(chartType == "areaChart")
			{
				legendentry[0] = areaser1[0].getLegendEntry();
				legendentry[1] = areaser2[0].getLegendEntry();
				legendentry[2] = areaser3[0].getLegendEntry();
				legendentry[3] = areaser4[0].getLegendEntry();
				legendentry[4] = areaser5[0].getLegendEntry();
			}
			else if(chartType == "twoBarChart")
			{
				legendentry[0] = areaser1[0].getLegendEntry();
				legendentry[1] = areaser2[0].getLegendEntry();
				legendentry[2] = areaser3[0].getLegendEntry();
				legendentry[3] = areaser4[0].getLegendEntry();
				legendentry[4] = areaser5[0].getLegendEntry();
			}
			else if(chartType == "lineChart")
			{
				legendentry[0] = areaser1[0].getLegendEntry();
				legendentry[1] = areaser2[0].getLegendEntry();
				legendentry[2] = areaser3[0].getLegendEntry();
				legendentry[3] = areaser4[0].getLegendEntry();
				legendentry[4] = areaser5[0].getLegendEntry();
			}
		}
		else if(j == 6)
		{
			if(chartType == "areaChart")
			{
				legendentry[0] = areaser1[0].getLegendEntry();
				legendentry[1] = areaser2[0].getLegendEntry();
				legendentry[2] = areaser3[0].getLegendEntry();
				legendentry[3] = areaser4[0].getLegendEntry();
				legendentry[4] = areaser5[0].getLegendEntry();
				legendentry[5] = areaser6[0].getLegendEntry();
			}
			else if(chartType == "twoBarChart")
			{
				legendentry[0] = areaser1[0].getLegendEntry();
				legendentry[1] = areaser2[0].getLegendEntry();
				legendentry[2] = areaser3[0].getLegendEntry();
				legendentry[3] = areaser4[0].getLegendEntry();
				legendentry[4] = areaser5[0].getLegendEntry();
				legendentry[5] = areaser6[0].getLegendEntry();
			}
			else if(chartType == "lineChart")
			{
				legendentry[0] = areaser1[0].getLegendEntry();
				legendentry[1] = areaser2[0].getLegendEntry();
				legendentry[2] = areaser3[0].getLegendEntry();
				legendentry[3] = areaser4[0].getLegendEntry();
				legendentry[4] = areaser5[0].getLegendEntry();
				legendentry[5] = areaser6[0].getLegendEntry();
			}
		}
		else if(j == 7)
		{
			if(chartType == "areaChart")
			{
				legendentry[0] = areaser1[0].getLegendEntry();
				legendentry[1] = areaser2[0].getLegendEntry();
				legendentry[2] = areaser3[0].getLegendEntry();
				legendentry[3] = areaser4[0].getLegendEntry();
				legendentry[4] = areaser5[0].getLegendEntry();
				legendentry[5] = areaser6[0].getLegendEntry();
				legendentry[6] = areaser7[0].getLegendEntry();
			}
			else if(chartType == "twoBarChart")
			{
				legendentry[0] = areaser1[0].getLegendEntry();
				legendentry[1] = areaser2[0].getLegendEntry();
				legendentry[2] = areaser3[0].getLegendEntry();
				legendentry[3] = areaser4[0].getLegendEntry();
				legendentry[4] = areaser5[0].getLegendEntry();
				legendentry[5] = areaser6[0].getLegendEntry();
				legendentry[6] = areaser7[0].getLegendEntry();
			}
			else if(chartType == "lineChart")
			{
				legendentry[0] = areaser1[0].getLegendEntry();
				legendentry[1] = areaser2[0].getLegendEntry();
				legendentry[2] = areaser3[0].getLegendEntry();
				legendentry[3] = areaser4[0].getLegendEntry();
				legendentry[4] = areaser5[0].getLegendEntry();
				legendentry[5] = areaser6[0].getLegendEntry();
				legendentry[6] = areaser7[0].getLegendEntry();
			}		
		}
		else if(j == 8)
		{
			if(chartType == "areaChart")
			{
				legendentry[0] = areaser1[0].getLegendEntry();
				legendentry[1] = areaser2[0].getLegendEntry();
				legendentry[2] = areaser3[0].getLegendEntry();
				legendentry[3] = areaser4[0].getLegendEntry();
				legendentry[4] = areaser5[0].getLegendEntry();
				legendentry[5] = areaser6[0].getLegendEntry();
				legendentry[6] = areaser7[0].getLegendEntry();
				legendentry[7] = areaser8[0].getLegendEntry();
			}
			else if(chartType == "twoBarChart")
			{
				legendentry[0] = areaser1[0].getLegendEntry();
				legendentry[1] = areaser2[0].getLegendEntry();
				legendentry[2] = areaser3[0].getLegendEntry();
				legendentry[3] = areaser4[0].getLegendEntry();
				legendentry[4] = areaser5[0].getLegendEntry();
				legendentry[5] = areaser6[0].getLegendEntry();
				legendentry[6] = areaser7[0].getLegendEntry();
				legendentry[7] = areaser8[0].getLegendEntry();
			}
			else if(chartType == "lineChart")
			{
				legendentry[0] = areaser1[0].getLegendEntry();
				legendentry[1] = areaser2[0].getLegendEntry();
				legendentry[2] = areaser3[0].getLegendEntry();
				legendentry[3] = areaser4[0].getLegendEntry();
				legendentry[4] = areaser5[0].getLegendEntry();
				legendentry[5] = areaser6[0].getLegendEntry();
				legendentry[6] = areaser7[0].getLegendEntry();
				legendentry[7] = areaser8[0].getLegendEntry();
			}		
		}
		else if(j == 9)
		{
			if(chartType == "areaChart")
			{
				legendentry[0] = areaser1[0].getLegendEntry();
				legendentry[1] = areaser2[0].getLegendEntry();
				legendentry[2] = areaser3[0].getLegendEntry();
				legendentry[3] = areaser4[0].getLegendEntry();
				legendentry[4] = areaser5[0].getLegendEntry();
				legendentry[5] = areaser6[0].getLegendEntry();
				legendentry[6] = areaser7[0].getLegendEntry();
				legendentry[7] = areaser8[0].getLegendEntry();
				legendentry[8] = areaser9[0].getLegendEntry();	
			}
			else if(chartType == "twoBarChart")
			{
				legendentry[0] = areaser1[0].getLegendEntry();
				legendentry[1] = areaser2[0].getLegendEntry();
				legendentry[2] = areaser3[0].getLegendEntry();
				legendentry[3] = areaser4[0].getLegendEntry();
				legendentry[4] = areaser5[0].getLegendEntry();
				legendentry[5] = areaser6[0].getLegendEntry();
				legendentry[6] = areaser7[0].getLegendEntry();
				legendentry[7] = areaser8[0].getLegendEntry();
				legendentry[8] = areaser9[0].getLegendEntry();
			}
			else if(chartType == "lineChart")
			{
				legendentry[0] = areaser1[0].getLegendEntry();
				legendentry[1] = areaser2[0].getLegendEntry();
				legendentry[2] = areaser3[0].getLegendEntry();
				legendentry[3] = areaser4[0].getLegendEntry();
				legendentry[4] = areaser5[0].getLegendEntry();
				legendentry[5] = areaser6[0].getLegendEntry();
				legendentry[6] = areaser7[0].getLegendEntry();
				legendentry[7] = areaser8[0].getLegendEntry();
				legendentry[8] = areaser9[0].getLegendEntry();
			}		
		}
		else if(j == 10)
		{
			if(chartType == "areaChart")
			{
				legendentry[0] = areaser1[0].getLegendEntry();
				legendentry[1] = areaser2[0].getLegendEntry();
				legendentry[2] = areaser3[0].getLegendEntry();
				legendentry[3] = areaser4[0].getLegendEntry();
				legendentry[4] = areaser5[0].getLegendEntry();
				legendentry[5] = areaser6[0].getLegendEntry();
				legendentry[6] = areaser7[0].getLegendEntry();
				legendentry[7] = areaser8[0].getLegendEntry();
				legendentry[8] = areaser9[0].getLegendEntry();
				legendentry[9] = areaser10[0].getLegendEntry();	
			}
			else if(chartType == "twoBarChart")
			{
				legendentry[0] = areaser1[0].getLegendEntry();
				legendentry[1] = areaser2[0].getLegendEntry();
				legendentry[2] = areaser3[0].getLegendEntry();
				legendentry[3] = areaser4[0].getLegendEntry();
				legendentry[4] = areaser5[0].getLegendEntry();
				legendentry[5] = areaser6[0].getLegendEntry();
				legendentry[6] = areaser7[0].getLegendEntry();
				legendentry[7] = areaser8[0].getLegendEntry();
				legendentry[8] = areaser9[0].getLegendEntry();
				legendentry[9] = areaser10[0].getLegendEntry();
			}
			else if(chartType == "lineChart")
			{
				legendentry[0] = areaser1[0].getLegendEntry();
				legendentry[1] = areaser2[0].getLegendEntry();
				legendentry[2] = areaser3[0].getLegendEntry();
				legendentry[3] = areaser4[0].getLegendEntry();
				legendentry[4] = areaser5[0].getLegendEntry();
				legendentry[5] = areaser6[0].getLegendEntry();
				legendentry[6] = areaser7[0].getLegendEntry();
				legendentry[7] = areaser8[0].getLegendEntry();
				legendentry[8] = areaser9[0].getLegendEntry();
				legendentry[9] = areaser10[0].getLegendEntry();
			}		
		}
		else if(j == 11)
		{
			if(chartType == "areaChart")
			{
				legendentry[0] = areaser1[0].getLegendEntry();
				legendentry[1] = areaser2[0].getLegendEntry();
				legendentry[2] = areaser3[0].getLegendEntry();
				legendentry[3] = areaser4[0].getLegendEntry();
				legendentry[4] = areaser5[0].getLegendEntry();
				legendentry[5] = areaser6[0].getLegendEntry();
				legendentry[6] = areaser7[0].getLegendEntry();
				legendentry[7] = areaser8[0].getLegendEntry();
				legendentry[8] = areaser9[0].getLegendEntry();
				legendentry[9] = areaser10[0].getLegendEntry();
				legendentry[10]= areaser11[0].getLegendEntry();
				
				
			}
			else if(chartType == "twoBarChart")
			{
				legendentry[0] = areaser1[0].getLegendEntry();
				legendentry[1] = areaser2[0].getLegendEntry();
				legendentry[2] = areaser3[0].getLegendEntry();
				legendentry[3] = areaser4[0].getLegendEntry();
				legendentry[4] = areaser5[0].getLegendEntry();
				legendentry[5] = areaser6[0].getLegendEntry();
				legendentry[6] = areaser7[0].getLegendEntry();
				legendentry[7] = areaser8[0].getLegendEntry();
				legendentry[8] = areaser9[0].getLegendEntry();
				legendentry[9] = areaser10[0].getLegendEntry();
				legendentry[10]= areaser11[0].getLegendEntry();
			}
			else if(chartType == "lineChart")
			{
				legendentry[0] = areaser1[0].getLegendEntry();
				legendentry[1] = areaser2[0].getLegendEntry();
				legendentry[2] = areaser3[0].getLegendEntry();
				legendentry[3] = areaser4[0].getLegendEntry();
				legendentry[4] = areaser5[0].getLegendEntry();
				legendentry[5] = areaser6[0].getLegendEntry();
				legendentry[6] = areaser7[0].getLegendEntry();
				legendentry[7] = areaser8[0].getLegendEntry();
				legendentry[8] = areaser9[0].getLegendEntry();
				legendentry[9] = areaser10[0].getLegendEntry();
				legendentry[10]= areaser11[0].getLegendEntry();
				
			}	
		}
		else if(j == 12)
		{
			if(chartType == "areaChart")
			{
				legendentry[0] = areaser1[0].getLegendEntry();
				legendentry[1] = areaser2[0].getLegendEntry();
				legendentry[2] = areaser3[0].getLegendEntry();
				legendentry[3] = areaser4[0].getLegendEntry();
				legendentry[4] = areaser5[0].getLegendEntry();
				legendentry[5] = areaser6[0].getLegendEntry();
				legendentry[6] = areaser7[0].getLegendEntry();
				legendentry[7] = areaser8[0].getLegendEntry();
				legendentry[8] = areaser9[0].getLegendEntry();
				legendentry[9] = areaser10[0].getLegendEntry();
				legendentry[10]= areaser11[0].getLegendEntry();
				legendentry[11]= areaser12[0].getLegendEntry();
						
			}
			else if(chartType == "twoBarChart")
			{
				legendentry[0] = areaser1[0].getLegendEntry();
				legendentry[1] = areaser2[0].getLegendEntry();
				legendentry[2] = areaser3[0].getLegendEntry();
				legendentry[3] = areaser4[0].getLegendEntry();
				legendentry[4] = areaser5[0].getLegendEntry();
				legendentry[5] = areaser6[0].getLegendEntry();
				legendentry[6] = areaser7[0].getLegendEntry();
				legendentry[7] = areaser8[0].getLegendEntry();
				legendentry[8] = areaser9[0].getLegendEntry();
				legendentry[9] = areaser10[0].getLegendEntry();
				legendentry[10]= areaser11[0].getLegendEntry();
				legendentry[11]= areaser12[0].getLegendEntry();
			}
			else if(chartType == "lineChart")
			{
				legendentry[0] = areaser1[0].getLegendEntry();
				legendentry[1] = areaser2[0].getLegendEntry();
				legendentry[2] = areaser3[0].getLegendEntry();
				legendentry[3] = areaser4[0].getLegendEntry();
				legendentry[4] = areaser5[0].getLegendEntry();
				legendentry[5] = areaser6[0].getLegendEntry();
				legendentry[6] = areaser7[0].getLegendEntry();
				legendentry[7] = areaser8[0].getLegendEntry();
				legendentry[8] = areaser9[0].getLegendEntry();
				legendentry[9] = areaser10[0].getLegendEntry();
				legendentry[10]= areaser11[0].getLegendEntry();
				legendentry[11]= areaser12[0].getLegendEntry();
				
			}	
		}
		
			for(int i = 0; i < j; i++)
			{
				if(func_val == null)
				{	
					legendentry[i].setLabel(chosens[i]);
				}
				else if(func_val == "Annpct")
				{
					legendentry[i].setLabel("Annpct" + "(" + chosens[i] + ")");
				}
				else if(func_val == "Csum")
				{
					legendentry[i].setLabel("Csum" + "(" + chosens[i] + ")");
				}
				else if(func_val == "Diff")
				{	
					legendentry[i].setLabel("Diff" + "(" + chosens[i] +")");	
				}
				else if(func_val == "Mave")
				{	
					legendentry[i].setLabel("Mave" + "(" + chosens[i] +" )");	
				}
				else if(func_val == "Mavec")
				{
					legendentry[i].setLabel("Mavec" + "(" + chosens[i] +")");
				}
				else if(func_val == "Mstddev")
				{
					legendentry[i].setLabel("Mstddev" + "(" + chosens[i] +")");
				}
				else if(func_val == "Pct")
				{
					legendentry[i].setLabel("Pct" + "(" + chosens[i] + ")");
				}
				else if(func_val == "Ytydiff")
				{
					legendentry[i].setLabel("Ytydiff" + "(" + chosens[i] + ")");
				}
				else if(func_val == "Ytypct")
				{
					legendentry[i].setLabel("Ytypct" + "(" + chosens[i] +")");
				}
			}
	}

	public void getPane1()
	{
		ChartPane pane1 = new ChartPane();
		ChartPane[] panelist1 = new ChartPane[1];
		panelist1[0] = pane1;				
		//pane1.setData(tiqviewsdata1);
		if(func_val1 != null)
		{
			areaser1 = new ChartSeries[2];	
			for(int i = 0; i < 2; i++)
			{
				if ( chartType == "areaChart")
				{		
					areaser1[i] =(ChartSeries)new AreaSeries(i);
					//areaser1[1] = (ChartSeries) new AreaSeries(1);
				}
				else if (chartType == "lineChart")
				{
					 areaser1[i] = (ChartSeries) new LineSeries(i);
					 //areaser1[1] = (ChartSeries) new LineSeries(1);
				}
				else if( chartType == "twoBarChart")
				{
					areaser1[i] = (ChartSeries) new BarSeries(i);
					//areaser1[1] = (ChartSeries) new BarSeries(1);
				}
				else if (chartType == "stackedBarChart")
				{
					areaser1[i] = (ChartSeries) new StackedBarSeries();
					//areaser1[1] = (ChartSeries) new StackedBarSeries();
				}
				
				areaser1[i].setColor(colors[i]);
				//str = chosens[i].indexOf("|");
				//areaser1[i].setLabel(chosens[i].substring(0,  str -1));
			}
		}
		else 
		{
			areaser1 = new ChartSeries[1];
			//areaser1 = new ChartSeries[2];
			if ( chartType == "areaChart")
			{		
				areaser1[0] =(ChartSeries)new AreaSeries(0);
				//areaser1[1] = (ChartSeries) new AreaSeries(1);
			}
			else if (chartType == "lineChart")
			{
				areaser1[0] = (ChartSeries) new LineSeries(0);
				//areaser1[1] = (ChartSeries) new LineSeries(1);
			}
			else if( chartType == "twoBarChart")
			{
				areaser1[0] = (ChartSeries) new BarSeries(0);
				//areaser1[1] = (ChartSeries) new BarSeries(1);
			}
			else if (chartType == "stackedBarChart")
			{
				areaser1[0] = (ChartSeries) new StackedBarSeries();
				//areaser1[1] = (ChartSeries) new StackedBarSeries();
			}

			areaser1[0].setColor(colors[0]);
			str = chosens[0].indexOf("|");
			areaser1[0].setLabel(chosens[0].substring(0,  str -1));
			//areaser1[1].setColor(Color.WHITE);
		}// end else
		
		pane1.setData(tiqviewsdata1);
		pane1.setSeries(areaser1);
		pane1.getLeftAxis().setUseInGrid(false);
		pane1.setBackgroundPaint(Color.white);
		ChartLegend chartlegend = pane1.getLegend();
		chartlegend.setBackgroundPaint(Color.WHITE);
		chartlegend.getStartPos().setXOffset(10);
		chartlegend.getStartPos().setYOffset(0);
		chartlegend.setBorder(null);
		chartlegend.setEntryLabelFirst(false);
		chartlegend.setSymbolSize(new Dimension(10,10));
		ChartDataPointDisplay dataPointDisplay = pane1.getDataPointDisplay();
        dataPointDisplay.setEntryValueFormat(nft);
        
		multiChart1.setPanes(panelist1);
		multiChart1.getDateAxis().setShowGrid(false);
		
		validate();
		
		multiChart1.setSize(100, 150);
		multiChart1.setVisible(true);
		multiChart1.updateDisplay();
		multiChartPanel.add(multiChart1);	
	}// end getPane1
	
	public void getPane2()
	{
		ChartPane pane2 = new ChartPane();
		ChartPane[] panelist2 = new ChartPane[1];
		panelist2[0] = pane2;
		//linePane.setData((rt.tiqLineData));	
		//pane2.setData(tiqviewsdata2);
		if(func_val1 != null)
		{
			areaser2 = new ChartSeries[2];	
			for(int i = 0; i < 2; i++)
			{
				if ( chartType == "areaChart")
				{
					areaser2[i] =(ChartSeries)new AreaSeries(i);
				}
				else if (chartType == "lineChart")
				{
					areaser2[i] = (ChartSeries) new LineSeries(i);
				}
				else if( chartType == "twoBarChart")
				{
					areaser2[i] = (ChartSeries) new BarSeries(i);
				}
				else if (chartType == "stackedBarChart")
				{
					areaser2[i] = (ChartSeries) new StackedBarSeries();
				}		
				areaser2[i].setColor(colors[i]);
			}
		}	
		else 
		{
			areaser2 = new ChartSeries[1];
			if ( chartType == "areaChart")
			{
				areaser2[0] =(ChartSeries)new AreaSeries(0);
			}
			else if (chartType == "lineChart")
			{
				areaser2[0] = (ChartSeries) new LineSeries(0);
			}
			else if( chartType == "twoBarChart")
			{
				areaser2[0] = (ChartSeries) new BarSeries(0);
			}
			else if (chartType == "stackedBarChart")
			{
				areaser2[0] = (ChartSeries) new StackedBarSeries();
			}

			areaser2[0].setColor(colors[1] );
			str = chosens[1].indexOf("|");
			areaser2[0].setLabel(chosens[1].substring(0, str -1 ));	
		} // end else
		
		pane2.setData(tiqviewsdata2);
		pane2.setSeries(areaser2);
		pane2.getLeftAxis().setUseInGrid(false);
		pane2.setBackgroundPaint(Color.white);
		ChartLegend chartlegend1 = pane2.getLegend();
		chartlegend1.setBackgroundPaint(Color.WHITE);
		chartlegend1.getStartPos().setXOffset(10);
		chartlegend1.getStartPos().setYOffset(0);
		chartlegend1.setBorder(null);
		chartlegend1.setEntryLabelFirst(false);
		chartlegend1.setSymbolSize(new Dimension(10,10));
		ChartDataPointDisplay dataPointDisplay1 = pane2.getDataPointDisplay();
        dataPointDisplay1.setEntryValueFormat(nft);
        
        //getMultiLegendEntry();
        multiChart2.setPanes(panelist2);
		multiChart2.getDateAxis().setShowGrid(false);	
	
		validate();
		multiChart1.setSize(100, 150);
		multiChart1.setVisible(true);
		multiChart1.updateDisplay();
		multiChart2.setSize(100,150);
		multiChart2.setVisible(true);
		multiChart2.updateDisplay();
		multiChartPanel.add(multiChart1);
		multiChartPanel.add(multiChart2);
	
	}// end getPane2
	
	public void getPane3()
	{
		ChartPane pane3 = new ChartPane();
		ChartPane[] panelist3 = new ChartPane[1];
		panelist3[0] = pane3;
		if(func_val1 != null)
		{
			areaser3 = new ChartSeries[2];
			for(int i = 0; i < 2; i++)
			{
				if ( chartType == "areaChart")
				{		
					areaser3[i] =(ChartSeries)new AreaSeries(i);
				}
				else if (chartType == "lineChart")
				{
					 areaser3[i] = (ChartSeries) new LineSeries(i);
				}
				else if( chartType == "twoBarChart")
				{
					areaser3[i] = (ChartSeries) new BarSeries(i);
				}			
				areaser3[i].setColor(colors[i]);
			}	                           
		}	
		else 
		{
			areaser3 = new ChartSeries[1];		
			if ( chartType == "areaChart")
			{		
				areaser3[0] =(ChartSeries)new AreaSeries(0);
			}
			else if (chartType == "lineChart")
			{
				 areaser3[0] = (ChartSeries) new LineSeries(0);
			}
			else if( chartType == "twoBarChart")
			{
				areaser3[0] = (ChartSeries) new BarSeries(0);
			}
			
			areaser3[0].setColor(colors[2]);
			str = chosens[2].indexOf("|");
			areaser3[0].setLabel(chosens[2].substring(0, str -1));	
		}// end else
		
		pane3.setData(tiqviewsdata3);					
		pane3.setSeries(areaser3);
		pane3.getLeftAxis().setUseInGrid(false);
		pane3.setBackgroundPaint(Color.WHITE);
		ChartLegend chartlegend2 = pane3.getLegend();
		chartlegend2.setBackgroundPaint(Color.WHITE);
		chartlegend2.getStartPos().setXOffset(10);
		chartlegend2.getStartPos().setYOffset(0);
		chartlegend2.setBorder(null);
		chartlegend2.setEntryLabelFirst(false);
		chartlegend2.setSymbolSize(new Dimension (10, 10));
		ChartDataPointDisplay dataPointDisplay11 = pane3.getDataPointDisplay();
        dataPointDisplay11.setEntryValueFormat(nft);
        
       // getMultiLegendEntry();
		
		multiChart3.setPanes(panelist3);
		multiChart3.getDateAxis().setShowGrid(false);
				
		validate();
		multiChart1.setSize(100, 150);
		multiChart1.setVisible(true);
		multiChart1.updateDisplay();
		multiChart2.setSize(100,150);
		multiChart2.setVisible(true);
		multiChart2.updateDisplay();
		multiChart3.setSize(100, 150);
		multiChart3.setVisible(true);
		multiChart3.updateDisplay();
		multiChartPanel.add(multiChart1);
		multiChartPanel.add(multiChart2);
		multiChartPanel.add(multiChart3);
        
	}// end getPane3
	
	public void getPane4()
	{
		ChartPane pane4 = new ChartPane();
		ChartPane[] panelist4 = new ChartPane[1];
		panelist4[0]= pane4;
		areaser4 = new ChartSeries[2];
		if(func_val1 != null)
		{
			for(int i = 0; i < 2; i++)
			{
				if ( chartType == "areaChart")
				{		
					areaser4[1] =(ChartSeries)new AreaSeries(1);
				}
				else if (chartType == "lineChart")
				{
					 areaser4[i] = (ChartSeries) new LineSeries(i);
				}
				else if( chartType == "twoBarChart")
				{
					areaser4[i] = (ChartSeries) new BarSeries(i);
				}
				else if (chartType == "stackedBarChart")
				{
					areaser4[i] = (ChartSeries) new StackedBarSeries();
				}
				areaser4[i].setColor(colors[i]);
			}
		}	
		else 
		{
			areaser4 = new ChartSeries[1];

			if ( chartType == "areaChart")
			{		
				areaser4[0] =(ChartSeries)new AreaSeries(0);
			}
			else if (chartType == "lineChart")
			{
				areaser4[0] = (ChartSeries) new LineSeries(0);
			}
			else if( chartType == "twoBarChart")
			{
				areaser4[0] = (ChartSeries) new BarSeries(0);
			}
			else if (chartType == "stackedBarChart")
			{
				areaser4[0] = (ChartSeries) new StackedBarSeries();
			}		
			areaser4[0].setColor(colors[3]);
			str = chosens[3].indexOf("|");
			areaser4[0].setLabel(chosens[3].substring(0, str -1));
		
		}// end else
		
		//areaser4[0] = (ChartSeries) new AreaSeries(0);
				
		pane4.setData(tiqviewsdata4);
		pane4.setSeries(areaser4);
		pane4.getLeftAxis().setUseInGrid(false);
		pane4.setBackgroundPaint(Color.WHITE);
		ChartLegend chartlegend3 = pane4.getLegend();
		chartlegend3.setBackgroundPaint(Color.WHITE);
		chartlegend3.getStartPos().setXOffset(10);
		chartlegend3.getStartPos().setYOffset(0);
		chartlegend3.setBorder(null);
		chartlegend3.setEntryLabelFirst(false);
		chartlegend3.setSymbolSize(new Dimension (10,10));
		ChartDataPointDisplay dataPointDisplay11111 = pane4.getDataPointDisplay();
        dataPointDisplay11111.setEntryValueFormat(nft);
        
        //getMultiLegendEntry();
        multiChart4.setPanes(panelist4);
		multiChart4.getDateAxis().setShowGrid(false);
							
		validate();
		multiChart1.setSize(100, 150);
		multiChart1.setVisible(true);
		multiChart1.updateDisplay();
		multiChart2.setSize(100,150);
		multiChart2.setVisible(true);
		multiChart2.updateDisplay();
		multiChart3.setSize(100, 150);
		multiChart3.setVisible(true);
		multiChart3.updateDisplay();
		multiChart4.setSize(100, 150);
		multiChart4.setVisible(true);
		multiChart4.updateDisplay();
		multiChartPanel.add(multiChart1);
		multiChartPanel.add(multiChart2);
		multiChartPanel.add(multiChart3);
		multiChartPanel.add(multiChart4);			
	
	}// end getPane4
	
	public void getPane5()
	{
		ChartPane pane5 = new ChartPane();
		ChartPane[] panelist5 = new ChartPane[1];
		panelist5[0] = pane5;
			
		if(func_val1 != null)
		{
			areaser5 = new ChartSeries[2];
			for(int i = 0; i < 2; i++)
			{
				if ( chartType == "areaChart")
				{	
					areaser5[i] =(ChartSeries)new AreaSeries(i);
				}
				else if (chartType == "lineChart")
				{
					 areaser5[i] = (ChartSeries) new LineSeries(i);
				}
				else if( chartType == "twoBarChart")
				{
					areaser5[i] = (ChartSeries) new BarSeries(i);
				}
				else if (chartType == "stackedBarChart")
				{
					areaser5[i] = (ChartSeries) new StackedBarSeries();
				}		
				areaser5[i].setColor(colors[i]);
			}                       
		}
		else 
		{
			areaser5 = new ChartSeries[1];
			if ( chartType == "areaChart")
			{	
				areaser5[0] =(ChartSeries)new AreaSeries(0);
			}
			else if (chartType == "lineChart")
			{
				areaser5[0] = (ChartSeries) new LineSeries(0);
			}
			else if( chartType == "twoBarChart")
			{
				areaser5[0] = (ChartSeries) new BarSeries(0);
			}
			else if (chartType == "stackedBarChart")
			{
				areaser5[0] = (ChartSeries) new StackedBarSeries();
			}	
			areaser5[0].setColor(colors[4]);
			str = chosens[4].indexOf("|");
			areaser5[0].setLabel(chosens[4].substring(0, str -1));
		}
		
		pane5.setData(tiqviewsdata5);
		pane5.setSeries(areaser5);
		pane5.getLeftAxis().setUseInGrid(false);
		pane5.setBackgroundPaint(Color.WHITE);
		ChartLegend chartlegend4 = pane5.getLegend();
		chartlegend4.setBackgroundPaint(Color.WHITE);
		chartlegend4.getStartPos().setXOffset(10);
		chartlegend4.getStartPos().setYOffset(0);
		chartlegend4.setBorder(null);
		chartlegend4.setEntryLabelFirst(false);
		chartlegend4.setSymbolSize(new Dimension (10,10));
		ChartDataPointDisplay dataPointDisplay111111 = pane5.getDataPointDisplay();
        dataPointDisplay111111.setEntryValueFormat(nft);
        
        //getMultiLegendEntry();
        multiChart5.setPanes(panelist5);
        multiChart5.getDateAxis().setShowGrid(false);
        
		validate();
		multiChart1.setSize(100, 150);
		multiChart1.setVisible(true);
		multiChart1.updateDisplay();
		multiChart2.setSize(100,150);
		multiChart2.setVisible(true);
		multiChart2.updateDisplay();
		multiChart3.setSize(100, 150);
		multiChart3.setVisible(true);
		multiChart3.updateDisplay();
		multiChart4.setSize(100, 150);
		multiChart4.setVisible(true);
		multiChart4.updateDisplay();
		multiChart5.setSize(100, 150);
		multiChart5.setVisible(true);
		multiChart5.updateDisplay();
		multiChartPanel.add(multiChart1);
		multiChartPanel.add(multiChart2);
		multiChartPanel.add(multiChart3);
		multiChartPanel.add(multiChart4);	
		multiChartPanel.add(multiChart5);
	
	}// end getPane5
	
	public void getPane6()
	{
		ChartPane pane6 = new ChartPane();
		ChartPane[] panelist6 = new ChartPane[1];
		panelist6[0] = pane6;
		if(func_val1 != null)
		{
			areaser6 = new ChartSeries[2];
			for(int i = 0; i < 2; i++)
			{
				if ( chartType == "areaChart")
				{		
					areaser6[i] =(ChartSeries)new AreaSeries(i);
				}
				else if (chartType == "lineChart")
				{
					 areaser6[i] = (ChartSeries) new LineSeries(i);
				}
				else if( chartType == "twoBarChart")
				{
					areaser6[i] = (ChartSeries) new BarSeries(i);
				}
				else if (chartType == "stackedBarChart")
				{
					areaser6[i] = (ChartSeries) new StackedBarSeries();
				}		
				areaser6[i].setColor(colors[i]);
			}
		}
		else 
		{
			areaser6 = new ChartSeries[1];
			if ( chartType == "areaChart")
			{		
				areaser6[0] =(ChartSeries)new AreaSeries(0);
			}
			else if (chartType == "lineChart")
			{
				areaser6[0] = (ChartSeries) new LineSeries(0);
			}
			else if( chartType == "twoBarChart")
			{
				areaser6[0] = (ChartSeries) new BarSeries(0);
			}
			else if (chartType == "stackedBarChart")
			{
				areaser6[0] = (ChartSeries) new StackedBarSeries();
			}
			areaser6[0].setColor(colors[5]);
			str = chosens[5].indexOf("|");
			areaser6[0].setLabel(chosens[5].substring(0, str - 1));
		}
		
		pane6.setData(tiqviewsdata6);
		pane6.setSeries(areaser6);
		pane6.getLeftAxis().setUseInGrid(false);
		pane6.setBackgroundPaint(Color.WHITE);
		ChartLegend chartlegend5= pane6.getLegend();
		chartlegend5.setBackgroundPaint(Color.WHITE);
		chartlegend5.getStartPos().setXOffset(10);
		chartlegend5.getStartPos().setYOffset(0);
		chartlegend5.setBorder(null);
		chartlegend5.setEntryLabelFirst(false);
		chartlegend5.setSymbolSize(new Dimension (10,10));
		ChartDataPointDisplay dataPointDisplay1111111 = pane6.getDataPointDisplay();
        dataPointDisplay1111111.setEntryValueFormat(nft);
        
        //getMultiLegendEntry();
        multiChart6.setPanes(panelist6);
        multiChart6.getDateAxis().setShowGrid(false);
        
        validate();
		multiChart1.setSize(100, 150);
		multiChart1.setVisible(true);
		multiChart1.updateDisplay();
		multiChart2.setSize(100,150);
		multiChart2.setVisible(true);
		multiChart2.updateDisplay();
		multiChart3.setSize(100, 150);
		multiChart3.setVisible(true);
		multiChart3.updateDisplay();
		multiChart4.setSize(100, 150);
		multiChart4.setVisible(true);
		multiChart4.updateDisplay();
		multiChart5.setSize(100, 150);
		multiChart5.setVisible(true);
		multiChart5.updateDisplay();
		multiChart6.setSize(100, 150);
		multiChart6.setVisible(true);
		multiChart6.updateDisplay();
		multiChartPanel.add(multiChart1);
		multiChartPanel.add(multiChart2);
		multiChartPanel.add(multiChart3);
		multiChartPanel.add(multiChart4);	
		multiChartPanel.add(multiChart5);
		multiChartPanel.add(multiChart6);
	
	}// end getPane6
	
	public void getPane7()
	{
		ChartPane pane7 = new ChartPane();
		ChartPane[] panelist7 = new ChartPane[1];
		panelist7[0] = pane7;
		if(func_val1 != null)
		{
			areaser7 = new ChartSeries[2];	
			for(int i = 0; i < 2; i++)
			{
				if ( chartType == "areaChart")
				{		
					areaser7[i] =(ChartSeries)new AreaSeries(i);
				}
				else if (chartType == "lineChart")
				{
					 areaser7[i] = (ChartSeries) new LineSeries(i);
				}
				else if( chartType == "twoBarChart")
				{
					areaser7[i] = (ChartSeries) new BarSeries(i);
				}
				else if (chartType == "stackedBarChart")
				{
					areaser7[i] = (ChartSeries) new StackedBarSeries();
				}	
				areaser7[i].setColor(colors[i]);
			}
		}
		else 
		{
			areaser7 = new ChartSeries[1];
			if ( chartType == "areaChart")
			{		
				areaser7[0] =(ChartSeries)new AreaSeries(0);
			}
			else if (chartType == "lineChart")
			{
				areaser7[0] = (ChartSeries) new LineSeries(0);
			}
			else if( chartType == "twoBarChart")
			{
				areaser7[0] = (ChartSeries) new BarSeries(0);
			}
			else if (chartType == "stackedBarChart")
			{
				areaser7[0] = (ChartSeries) new StackedBarSeries();
			}
			areaser7[0].setColor(colors[6]);
			str = chosens[6].indexOf("|");
			areaser7[0].setLabel(chosens[6].substring(0, str - 1));
		}
		
		pane7.setData(tiqviewsdata7);
		pane7.setSeries(areaser7);
		pane7.getLeftAxis().setUseInGrid(false);
		pane7.setBackgroundPaint(Color.WHITE);
		ChartLegend chartlegend6= pane7.getLegend();
		chartlegend6.setBackgroundPaint(Color.WHITE);
		chartlegend6.getStartPos().setXOffset(10);
		chartlegend6.getStartPos().setYOffset(0);
		chartlegend6.setBorder(null);
		chartlegend6.setEntryLabelFirst(false);
		chartlegend6.setSymbolSize(new Dimension (10,10));
		ChartDataPointDisplay dataPointDisplay11111111 = pane7.getDataPointDisplay();
        dataPointDisplay11111111.setEntryValueFormat(nft);
        
        //getMultiLegendEntry();
        multiChart7.setPanes(panelist7);
        multiChart7.getDateAxis().setShowGrid(false);
        
        validate();
		multiChart1.setSize(100, 150);
		multiChart1.setVisible(true);
		multiChart1.updateDisplay();
		multiChart2.setSize(100,150);
		multiChart2.setVisible(true);
		multiChart2.updateDisplay();
		multiChart3.setSize(100, 150);
		multiChart3.setVisible(true);
		multiChart3.updateDisplay();
		multiChart4.setSize(100, 150);
		multiChart4.setVisible(true);
		multiChart4.updateDisplay();
		multiChart5.setSize(100, 150);
		multiChart5.setVisible(true);
		multiChart5.updateDisplay();
		multiChart6.setSize(100, 150);
		multiChart6.setVisible(true);
		multiChart6.updateDisplay();
		multiChart7.setSize(100, 150);
		multiChart7.setVisible(true);
		multiChart7.updateDisplay();	
		multiChartPanel.add(multiChart1);
		multiChartPanel.add(multiChart2);
		multiChartPanel.add(multiChart3);
		multiChartPanel.add(multiChart4);	
		multiChartPanel.add(multiChart5);
		multiChartPanel.add(multiChart6);
		multiChartPanel.add(multiChart7);
	
	}// end getPane7
	
	public void getPane8()
	{
		ChartPane pane8 = new ChartPane();
		ChartPane[] panelist8 = new ChartPane[1];
		panelist8[0] = pane8;		
		if(func_val1 != null)
		{
			areaser8 = new ChartSeries[2];	
			for(int i = 0; i < 2; i++)
			{
				if ( chartType == "areaChart")
				{		
					areaser8[i] =(ChartSeries)new AreaSeries(i);
				}
				else if (chartType == "lineChart")
				{
					areaser8[i] = (ChartSeries) new LineSeries(i);
				}
				else if( chartType == "twoBarChart")
				{
					areaser8[i] = (ChartSeries) new BarSeries(i);
				}
				else if (chartType == "stackedBarChart")
				{
					areaser8[i] = (ChartSeries) new StackedBarSeries();
				}
				areaser8[i].setColor(colors[i]);
			}
		}
		else
		{
			areaser8 = new ChartSeries[1];
			if ( chartType == "areaChart")
			{		
				areaser8[0] =(ChartSeries)new AreaSeries(0);
			}
			else if (chartType == "lineChart")
			{
				areaser8[0] = (ChartSeries) new LineSeries(0);
			}
			else if( chartType == "twoBarChart")
			{
				areaser8[0] = (ChartSeries) new BarSeries(0);
			}
			else if (chartType == "stackedBarChart")
			{
				areaser8[0] = (ChartSeries) new StackedBarSeries();
			}
		
			areaser8[0].setColor(colors[7]);
			str = chosens[7].indexOf("|");
			areaser8[0].setLabel(chosens[7].substring(0, str - 1));
		}
		
		pane8.setData(tiqviewsdata8);
		pane8.setSeries(areaser8);
		pane8.getLeftAxis().setUseInGrid(false);
		pane8.setBackgroundPaint(Color.WHITE);
		ChartLegend chartlegend7= pane8.getLegend();
		chartlegend7.setBackgroundPaint(Color.WHITE);
		chartlegend7.getStartPos().setXOffset(10);
		chartlegend7.getStartPos().setYOffset(0);
		chartlegend7.setBorder(null);
		chartlegend7.setEntryLabelFirst(false);
		chartlegend7.setSymbolSize(new Dimension (10,10));
		ChartDataPointDisplay dataPointDisplay11111111 = pane8.getDataPointDisplay();
        dataPointDisplay11111111.setEntryValueFormat(nft);
        
        //getMultiLegendEntry();
        multiChart8.setPanes(panelist8);
        multiChart8.getDateAxis().setShowGrid(false);
        
        validate();
		multiChart1.setSize(100, 150);
		multiChart1.setVisible(true);
		multiChart1.updateDisplay();
		multiChart2.setSize(100,150);
		multiChart2.setVisible(true);
		multiChart2.updateDisplay();
		multiChart3.setSize(100, 150);
		multiChart3.setVisible(true);
		multiChart3.updateDisplay();
		multiChart4.setSize(100, 150);
		multiChart4.setVisible(true);
		multiChart4.updateDisplay();
		multiChart5.setSize(100, 150);
		multiChart5.setVisible(true);
		multiChart5.updateDisplay();
		multiChart6.setSize(100, 150);
		multiChart6.setVisible(true);
		multiChart6.updateDisplay();
		multiChart7.setSize(100, 150);
		multiChart7.setVisible(true);
		multiChart7.updateDisplay();	
		multiChart8.setSize(100, 150);
		multiChart8.setVisible(true);
		multiChart8.updateDisplay();
		multiChartPanel.add(multiChart1);
		multiChartPanel.add(multiChart2);
		multiChartPanel.add(multiChart3);
		multiChartPanel.add(multiChart4);	
		multiChartPanel.add(multiChart5);
		multiChartPanel.add(multiChart6);
		multiChartPanel.add(multiChart7);
		multiChartPanel.add(multiChart8);
	
	}// end getPane8
	
	public void getPane9()
	{
		ChartPane pane9 = new ChartPane();
		ChartPane[] panelist9 = new ChartPane[1];
		panelist9[0] = pane9;
		if(func_val1 != null)
		{
			areaser9 = new ChartSeries[2];
			for(int i = 0; i < 2; i++)
			{
				if ( chartType == "areaChart")
				{		
					areaser9[i] =(ChartSeries)new AreaSeries(i);
				}
				else if (chartType == "lineChart")
				{
					 areaser9[i] = (ChartSeries) new LineSeries(i);
				}
				else if( chartType == "twoBarChart")
				{
					areaser9[i] = (ChartSeries) new BarSeries(i);
				}
				else if (chartType == "stackedBarChart")
				{
					areaser9[i] = (ChartSeries) new StackedBarSeries();
				}
			}
		}
		else 
		{
			areaser9 = new ChartSeries[1];
			if ( chartType == "areaChart")
			{		
				areaser9[0] =(ChartSeries)new AreaSeries(0);
			}
			else if (chartType == "lineChart")
			{
				areaser9[0] = (ChartSeries) new LineSeries(0);
			}
			else if( chartType == "twoBarChart")
			{
				areaser9[0] = (ChartSeries) new BarSeries(0);
			}
			else if (chartType == "stackedBarChart")
			{
				areaser9[0] = (ChartSeries) new StackedBarSeries();
			}

			areaser9[0].setColor(colors[8]);
			str = chosens[8].indexOf("|");
			areaser9[0].setLabel(chosens[8].substring(0, str - 1));
		}
		
		pane9.setData(tiqviewsdata9);
		pane9.setSeries(areaser9);
		pane9.getLeftAxis().setUseInGrid(false);
		pane9.setBackgroundPaint(Color.WHITE);
		ChartLegend chartlegend8= pane9.getLegend();
		chartlegend8.setBackgroundPaint(Color.WHITE);
		chartlegend8.getStartPos().setXOffset(10);
		chartlegend8.getStartPos().setYOffset(0);
		chartlegend8.setBorder(null);
		chartlegend8.setEntryLabelFirst(false);
		chartlegend8.setSymbolSize(new Dimension (10,10));
		ChartDataPointDisplay dataPointDisplay11111111 = pane9.getDataPointDisplay();
        dataPointDisplay11111111.setEntryValueFormat(nft);
        
        //getMultiLegendEntry();
        multiChart9.setPanes(panelist9);
        multiChart9.getDateAxis().setShowGrid(false);
        
        validate();
		multiChart1.setSize(100, 150);
		multiChart1.setVisible(true);
		multiChart1.updateDisplay();
		multiChart2.setSize(100,150);
		multiChart2.setVisible(true);
		multiChart2.updateDisplay();
		multiChart3.setSize(100, 150);
		multiChart3.setVisible(true);
		multiChart3.updateDisplay();
		multiChart4.setSize(100, 150);
		multiChart4.setVisible(true);
		multiChart4.updateDisplay();
		multiChart5.setSize(100, 150);
		multiChart5.setVisible(true);
		multiChart5.updateDisplay();
		multiChart6.setSize(100, 150);
		multiChart6.setVisible(true);
		multiChart6.updateDisplay();
		multiChart7.setSize(100, 150);
		multiChart7.setVisible(true);
		multiChart7.updateDisplay();	
		multiChart8.setSize(100, 150);
		multiChart8.setVisible(true);
		multiChart8.updateDisplay();
		multiChart9.setSize(100, 150);
		multiChart9.setVisible(true);
		multiChart9.updateDisplay();
		multiChartPanel.add(multiChart1);
		multiChartPanel.add(multiChart2);
		multiChartPanel.add(multiChart3);
		multiChartPanel.add(multiChart4);	
		multiChartPanel.add(multiChart5);
		multiChartPanel.add(multiChart6);
		multiChartPanel.add(multiChart7);
		multiChartPanel.add(multiChart8);
		multiChartPanel.add(multiChart9);
	
	}// end getPane9
	
	public void getPane10()
	{
		ChartPane pane10 = new ChartPane();
		ChartPane[] panelist10 = new ChartPane[1];
		panelist10[0] = pane10;
		if(func_val1 != null)
		{
			areaser10 = new ChartSeries[2];	
			for(int i = 0; i < 2; i++)
			{
				if ( chartType == "areaChart")
				{		
					areaser10[i] =(ChartSeries)new AreaSeries(i);
				}
				else if (chartType == "lineChart")
				{
					 areaser10[i] = (ChartSeries) new LineSeries(i);
				}
				else if( chartType == "twoBarChart")
				{
					areaser10[i] = (ChartSeries) new BarSeries(i);
				}
				else if (chartType == "stackedBarChart")
				{
					areaser10[i] = (ChartSeries) new StackedBarSeries();
				}
			}
		}
		else
		{
			areaser10 = new ChartSeries[1];
			if ( chartType == "areaChart")
			{		
				areaser10[0] =(ChartSeries)new AreaSeries(0);
			}
			else if (chartType == "lineChart")
			{
				areaser10[0] = (ChartSeries) new LineSeries(0);
			}
			else if( chartType == "twoBarChart")
			{
				areaser10[0] = (ChartSeries) new BarSeries(0);
			}
			else if (chartType == "stackedBarChart")
			{
				areaser10[0] = (ChartSeries) new StackedBarSeries();
			}
			areaser10[0].setColor(colors[9]);
			str = chosens[9].indexOf("|");
			areaser10[0].setLabel(chosens[9].substring(0, str - 1));
		}
		
		pane10.setData(tiqviewsdata10);
		pane10.setSeries(areaser10);
		pane10.getLeftAxis().setUseInGrid(false);
		pane10.setBackgroundPaint(Color.WHITE);
		ChartLegend chartlegend9 = pane10.getLegend();
		chartlegend9.setBackgroundPaint(Color.WHITE);
		chartlegend9.getStartPos().setXOffset(10);
		chartlegend9.getStartPos().setYOffset(0);
		chartlegend9.setBorder(null);
		chartlegend9.setEntryLabelFirst(false);
		chartlegend9.setSymbolSize(new Dimension (10,10));
		ChartDataPointDisplay dataPointDisplay11111111 = pane10.getDataPointDisplay();
        dataPointDisplay11111111.setEntryValueFormat(nft);
        
        //getMultiLegendEntry();
        multiChart10.setPanes(panelist10);
        multiChart10.getDateAxis().setShowGrid(false);
        
        validate();
		multiChart1.setSize(100, 150);
		multiChart1.setVisible(true);
		multiChart1.updateDisplay();
		multiChart2.setSize(100,150);
		multiChart2.setVisible(true);
		multiChart2.updateDisplay();
		multiChart3.setSize(100, 150);
		multiChart3.setVisible(true);
		multiChart3.updateDisplay();
		multiChart4.setSize(100, 150);
		multiChart4.setVisible(true);
		multiChart4.updateDisplay();
		multiChart5.setSize(100, 150);
		multiChart5.setVisible(true);
		multiChart5.updateDisplay();
		multiChart6.setSize(100, 150);
		multiChart6.setVisible(true);
		multiChart6.updateDisplay();
		multiChart7.setSize(100, 150);
		multiChart7.setVisible(true);
		multiChart7.updateDisplay();	
		multiChart8.setSize(100, 150);
		multiChart8.setVisible(true);
		multiChart8.updateDisplay();
		multiChart9.setSize(100, 150);
		multiChart9.setVisible(true);
		multiChart9.updateDisplay();
		multiChart10.setSize(100, 150);
		multiChart10.setVisible(true);
		multiChart10.updateDisplay();
		multiChartPanel.add(multiChart1);
		multiChartPanel.add(multiChart2);
		multiChartPanel.add(multiChart3);
		multiChartPanel.add(multiChart4);	
		multiChartPanel.add(multiChart5);
		multiChartPanel.add(multiChart6);
		multiChartPanel.add(multiChart7);
		multiChartPanel.add(multiChart8);
		multiChartPanel.add(multiChart9);
		multiChartPanel.add(multiChart10);
	
	}// end getPane10
	
	public void getPane11()
	{
		ChartPane pane11 = new ChartPane();
		ChartPane[] panelist11 = new ChartPane[1];
		panelist11[0] = pane11;
		if(func_val1 != null)
		{
			areaser11 = new ChartSeries[2];
			for(int i = 0; i < 2; i++)
			{
				if ( chartType == "areaChart")
				{		
					areaser11[i] =(ChartSeries)new AreaSeries(i);
				}
				else if (chartType == "lineChart")
				{
					 areaser11[i] = (ChartSeries) new LineSeries(i);
				}
				else if( chartType == "twoBarChart")
				{
					areaser11[i] = (ChartSeries) new BarSeries(i);
				}
				else if (chartType == "stackedBarChart")
				{
					areaser11[i] = (ChartSeries) new StackedBarSeries();
				}
				areaser11[i].setColor(colors[i]);
			}
		}
		else
		{
			areaser11 = new ChartSeries[1];
			if ( chartType == "areaChart")
			{		
				areaser11[0] =(ChartSeries)new AreaSeries(0);
			}
			else if (chartType == "lineChart")
			{
				areaser11[0] = (ChartSeries) new LineSeries(0);
			}
			else if( chartType == "twoBarChart")
			{
				areaser11[0] = (ChartSeries) new BarSeries(0);
			}
			else if (chartType == "stackedBarChart")
			{
				areaser11[0] = (ChartSeries) new StackedBarSeries();
			}
			areaser11[0].setColor(colors[10]);
			str = chosens[10].indexOf("|");
			areaser11[0].setLabel(chosens[10].substring(0, str - 1));
		}
		
		pane11.setData(tiqviewsdata11);
		pane11.setSeries(areaser11);
		pane11.getLeftAxis().setUseInGrid(false);
		pane11.setBackgroundPaint(Color.WHITE);
		ChartLegend chartlegend9 = pane11.getLegend();
		chartlegend9.setBackgroundPaint(Color.WHITE);
		chartlegend9.getStartPos().setXOffset(10);
		chartlegend9.getStartPos().setYOffset(0);
		chartlegend9.setBorder(null);
		chartlegend9.setEntryLabelFirst(false);
		chartlegend9.setSymbolSize(new Dimension (10,10));
		ChartDataPointDisplay dataPointDisplay11111111 = pane11.getDataPointDisplay();
        dataPointDisplay11111111.setEntryValueFormat(nft);
        
        //getMultiLegendEntry();
        multiChart11.setPanes(panelist11);
        multiChart11.getDateAxis().setShowGrid(false);
        
        validate();
		multiChart1.setSize(100, 150);
		multiChart1.setVisible(true);
		multiChart1.updateDisplay();
		multiChart2.setSize(100,150);
		multiChart2.setVisible(true);
		multiChart2.updateDisplay();
		multiChart3.setSize(100, 150);
		multiChart3.setVisible(true);
		multiChart3.updateDisplay();
		multiChart4.setSize(100, 150);
		multiChart4.setVisible(true);
		multiChart4.updateDisplay();
		multiChart5.setSize(100, 150);
		multiChart5.setVisible(true);
		multiChart5.updateDisplay();
		multiChart6.setSize(100, 150);
		multiChart6.setVisible(true);
		multiChart6.updateDisplay();
		multiChart7.setSize(100, 150);
		multiChart7.setVisible(true);
		multiChart7.updateDisplay();	
		multiChart8.setSize(100, 150);
		multiChart8.setVisible(true);
		multiChart8.updateDisplay();
		multiChart9.setSize(100, 150);
		multiChart9.setVisible(true);
		multiChart9.updateDisplay();
		multiChart10.setSize(100, 150);
		multiChart10.setVisible(true);
		multiChart10.updateDisplay();
		multiChart11.setSize(10, 150);
		multiChart11.setVisible(true);
		multiChart11.updateDisplay();
		multiChartPanel.add(multiChart1);
		multiChartPanel.add(multiChart2);
		multiChartPanel.add(multiChart3);
		multiChartPanel.add(multiChart4);	
		multiChartPanel.add(multiChart5);
		multiChartPanel.add(multiChart6);
		multiChartPanel.add(multiChart7);
		multiChartPanel.add(multiChart8);
		multiChartPanel.add(multiChart9);
		multiChartPanel.add(multiChart10);
		multiChartPanel.add(multiChart11);
	
	}// end getPane11
	
	public void getPane12()
	{
		ChartPane pane12 = new ChartPane();
		ChartPane[] panelist12 = new ChartPane[1];
		panelist12[0] = pane12;	
		if(func_val1 != null)
		{
			areaser12 = new ChartSeries[2];
			for(int i = 0; i < 2; i++)
			{
				if ( chartType == "areaChart")
				{		
					areaser12[i] =(ChartSeries)new AreaSeries(i);
				}
				else if (chartType == "lineChart")
				{
					 areaser12[i] = (ChartSeries) new LineSeries(i);
				}
				else if( chartType == "twoBarChart")
				{
					areaser12[i] = (ChartSeries) new BarSeries(i);
				}
				else if (chartType == "stackedBarChart")
				{
					areaser12[i] = (ChartSeries) new StackedBarSeries();
				}
				areaser12[i].setColor(colors[i]);
			}
		}
		else
		{
			areaser12 = new ChartSeries[1];
			if ( chartType == "areaChart")
			{		
				areaser12[0] =(ChartSeries)new AreaSeries(0);
			}
			else if (chartType == "lineChart")
			{
				areaser12[0] = (ChartSeries) new LineSeries(0);
			}
			else if( chartType == "twoBarChart")
			{
				areaser12[0] = (ChartSeries) new BarSeries(0);
			}
			else if (chartType == "stackedBarChart")
			{
				areaser12[0] = (ChartSeries) new StackedBarSeries();
			}
			
			areaser12[0].setColor(colors[10]);
			str = chosens[11].indexOf("|");
			areaser11[0].setLabel(chosens[11].substring(0, str - 1));
		}
		
		pane12.setData(tiqviewsdata12);
		pane12.setSeries(areaser12);
		pane12.getLeftAxis().setUseInGrid(false);
		pane12.setBackgroundPaint(Color.WHITE);
		ChartLegend chartlegend11 = pane12.getLegend();
		chartlegend11.setBackgroundPaint(Color.WHITE);
		chartlegend11.getStartPos().setXOffset(10);
		chartlegend11.getStartPos().setYOffset(0);
		chartlegend11.setBorder(null);
		chartlegend11.setEntryLabelFirst(false);
		chartlegend11.setSymbolSize(new Dimension (10,10));
		ChartDataPointDisplay dataPointDisplay11111111 = pane12.getDataPointDisplay();
        dataPointDisplay11111111.setEntryValueFormat(nft);
        
        //getMultiLegendEntry();
        multiChart12.setPanes(panelist12);
        multiChart12.getDateAxis().setShowGrid(false);
        
        validate();
		multiChart1.setSize(100, 150);
		multiChart1.setVisible(true);
		multiChart1.updateDisplay();
		multiChart2.setSize(100,150);
		multiChart2.setVisible(true);
		multiChart2.updateDisplay();
		multiChart3.setSize(100, 150);
		multiChart3.setVisible(true);
		multiChart3.updateDisplay();
		multiChart4.setSize(100, 150);
		multiChart4.setVisible(true);
		multiChart4.updateDisplay();
		multiChart5.setSize(100, 150);
		multiChart5.setVisible(true);
		multiChart5.updateDisplay();
		multiChart6.setSize(100, 150);
		multiChart6.setVisible(true);
		multiChart6.updateDisplay();
		multiChart7.setSize(100, 150);
		multiChart7.setVisible(true);
		multiChart7.updateDisplay();	
		multiChart8.setSize(100, 150);
		multiChart8.setVisible(true);
		multiChart8.updateDisplay();
		multiChart9.setSize(100, 150);
		multiChart9.setVisible(true);
		multiChart9.updateDisplay();
		multiChart10.setSize(100, 150);
		multiChart10.setVisible(true);
		multiChart10.updateDisplay();
		multiChart11.setSize(10, 150);
		multiChart11.setVisible(true);
		multiChart11.updateDisplay();
		multiChart12.setSize(100, 150);
		multiChart12.setVisible(true);
		multiChart12.updateDisplay();
		multiChartPanel.add(multiChart1);
		multiChartPanel.add(multiChart2);
		multiChartPanel.add(multiChart3);
		multiChartPanel.add(multiChart4);	
		multiChartPanel.add(multiChart5);
		multiChartPanel.add(multiChart6);
		multiChartPanel.add(multiChart7);
		multiChartPanel.add(multiChart8);
		multiChartPanel.add(multiChart9);
		multiChartPanel.add(multiChart10);
		multiChartPanel.add(multiChart11);
		multiChartPanel.add(multiChart12);
	}
	
	// create properties for compoundchart used by all types of charts
	public void ShowMultiSeries()
	{
		ChartFormat cf = new ChartFormat();
		get_funcChart();	
		multiChartPanel.removeAll();	
			if (j == 1)	
			{
				getPane1();
				getMultiLegendEntry();
			}// end if j == 1	
			else if(j == 2)	
			{	
				getPane1();
				getPane2();
				getMultiLegendEntry();		
			}// end if j == 2	
			else if(j == 3)
			{			
				getPane1();
				getPane2();
				getPane3();
				getMultiLegendEntry();				
			}
			else if ( j == 4)
			{			
				getPane1();
				getPane2();
				getPane3();
				getPane4();
				getMultiLegendEntry();
			}
			else if( j == 5)
			{	
				getPane1();
				getPane2();
				getPane3();
				getPane4();
				getPane5();
				getMultiLegendEntry();
			}
			else if (j == 6)
			{		
				getPane1();
				getPane2();
				getPane3();
				getPane4();
				getPane5();
				getPane6();
				getMultiLegendEntry();
			}
			else if(j == 7)
			{	
				getPane1();
				getPane2();
				getPane3();
				getPane4();
				getPane5();
				getPane6();
				getPane7();
				getMultiLegendEntry();			
			}
			else if(j == 8)
			{
				getPane1();
				getPane2();
				getPane3();
				getPane4();
				getPane5();
				getPane6();
				getPane7();
				getPane8();
				getMultiLegendEntry();
			}
			else if(j == 9)
			{
				getPane1();
				getPane2();
				getPane3();
				getPane4();
				getPane5();
				getPane6();
				getPane7();
				getPane8();
				getPane9();
				getMultiLegendEntry();
			}
			else if(j == 10)
			{
				getPane1();
				getPane2();
				getPane3();
				getPane4();
				getPane5();
				getPane6();
				getPane7();
				getPane8();
				getPane9();
				getPane10();
				getMultiLegendEntry();
			}
			else if(j == 11)
			{
				getPane1();
				getPane2();
				getPane3();
				getPane4();
				getPane5();
				getPane6();
				getPane7();
				getPane8();
				getPane9();
				getPane10();
				getPane11();
				getMultiLegendEntry();
			}
			else if(j == 12)
			{
				getPane1();
				getPane2();
				getPane3();
				getPane4();
				getPane5();
				getPane6();
				getPane7();
				getPane8();
				getPane9();
				getPane10();
				getPane11();
				getPane12();
				getMultiLegendEntry();
			}
	}
	
	public void getLinePane()
	{
		linePane = new ChartPane();
		ChartPane[] linePaneList = new ChartPane[1];
		linePaneList[0] = linePane;
		//linePane.setData(tiqviewsdata);		
		lineSer = new ChartSeries[j];				
		for(int i = 0; i < j; i++)
		{
			//lineSer = new ChartSeries[i+1];
			lineSer[i] = (ChartSeries) new LineSeries(i);
			str = chosens[i].indexOf("|");
			lineSer[i].setLabel(chosens[i].substring(0, str -1));
			lineSer[i].setColor(colors[i]);	
		}
		linePane.setData(tiqviewsdata);
		linePane.setSeries(lineSer);
		linePane.getLeftAxis().setUseInGrid(false);
		linePane.setBackgroundPaint(Color.WHITE);
		fameLineChart.setPanes(linePaneList);      
		fameLineChart.getDateAxis().setShowGrid(false);
		fameLineChart.getDateAxis().setLabelPosition(0, LabelPosition.LABEL_POS_BETWEEN);
		fameLineChart.getDateAxis().setLabelPosition(1, LabelPosition.LABEL_POS_BETWEEN);
		//fameLineChart.getDateAxis().getLabelPosition(1);
		ChartLegend chartlegend = linePane.getLegend();
		chartlegend.setBackgroundPaint(Color.WHITE);
		chartlegend.getStartPos().setXOffset(10);
		chartlegend.getStartPos().setYOffset(0);
		chartlegend.setBorder(null);
		chartlegend.setEntryLabelFirst(false);
		chartlegend.setSymbolSize(new Dimension(10,10));
		ChartDataPointDisplay dataPointDisplay = linePane.getDataPointDisplay();
        dataPointDisplay.setEntryValueFormat(nft);
	
	}// end getLinePane
	
	public void getTsLinePane()
	{
		linePane = new ChartPane();
		ChartPane[] linePaneList = new ChartPane[1];
		linePaneList[0] = linePane;
		linePane.setData(tiqviewsdata);		
		lineSer = new ChartSeries[2];
		lineSer[0] = (ChartSeries) new LineSeries(0);
		lineSer[0].setColor(colors[0]);
		lineSer[1] = (ChartSeries) new LineSeries(1);
		lineSer[1].setColor(Color.WHITE);
		//linePane.setData(tiqviewsdata);
		linePane.setSeries(lineSer);
		linePane.getLeftAxis().setUseInGrid(false);
		linePane.setBackgroundPaint(Color.WHITE);
		fameLineChart.setPanes(linePaneList);      
		fameLineChart.getDateAxis().setShowGrid(false);
		fameLineChart.getDateAxis().setLabelPosition(0, LabelPosition.LABEL_POS_BETWEEN);
		fameLineChart.getDateAxis().setLabelPosition(1, LabelPosition.LABEL_POS_BETWEEN);
		//fameLineChart.getDateAxis().getLabelPosition(1);
		ChartLegend chartlegend = linePane.getLegend();
		chartlegend.setBackgroundPaint(Color.WHITE);
		chartlegend.getStartPos().setXOffset(10);
		chartlegend.getStartPos().setYOffset(0);
		chartlegend.setBorder(null);
		chartlegend.setEntryLabelFirst(false);
		chartlegend.setSymbolSize(new Dimension(10,10));
		ChartDataPointDisplay dataPointDisplay = linePane.getDataPointDisplay();
        dataPointDisplay.setEntryValueFormat(nft);
	}
	
	public void getLegendEntry()
	{	
		//LegendEntry e1 = lineSer[0].getLegendEntry();
		LegendEntry[] e1;
		e1 = new LegendEntry[j];
		System.out.println(func_val);
		System.out.println(func_val1);
		for(int i = 0; i < j; i++)
		{	
			if(chartType == "areaChart")
			{
				e1[i] = areaSer[i].getLegendEntry();
			}
			else if (chartType == "twoBarChart")
			{
				e1[i] = barSer[i].getLegendEntry();
			}
			else if(chartType == "lineChart")
			{
				e1[i] = lineSer[i].getLegendEntry();
			}		
			if(func_val == null)
			{
				e1[i].setLabel(chosens[i].toString());	
			}
			else if(func_val == "Annpct")
			{
				e1[i].setLabel("Annpct" + "(" + chosens[i].toString());
			}
			else if(func_val == "Csum")
			{
				e1[i].setLabel("Csum" + "(" + chosens[i].toString());
			}
			else if(func_val == "Diff")
			{
				e1[i].setLabel("Diff" + "(" + chosens[i].toString());
			}
			else if(func_val == "Mave")
			{
				e1[i].setLabel("Mave" + "(" + chosens[i].toString());
			}
			else if(func_val == "Mavec")
			{
				e1[i].setLabel("Mavec" + "(" + chosens[i].toString());
			}
			else if(func_val == "Mstddev")
			{
				e1[i].setLabel("Mstddev" + "(" + chosens[i].toString());
			}
			else if(func_val == "Pct")
			{
				e1[i].setLabel("Pct" + "(" + chosens[i].toString());
			}
			else if(func_val == "Ytydiff")
			{
				e1[i].setLabel("Ytydiff" + "(" + chosens[i].toString());
			}
			else if(func_val == "Ytypct")
			{
				e1[i].setLabel("Ytypct" + "(" + chosens[i].toString());
			}
		}	
		
	}// getLegendEntry
	
	// create LineSeries chart with two series
	@SuppressWarnings("deprecation")
	public void ShowLineSeries()
	{
		//tiqviewsdata = new TiqViewsData("PPI", chartView);	
		lineChartPanel.removeAll();
		get_funcChart();	
		if(j == 1)
		{	
			getLinePane();
			getLegendEntry();			
		}
		else if (j == 2)
		{			
			getLinePane();		
			getLegendEntry();				
		}
		else if(j == 3)
		{					
			getLinePane();		
			getLegendEntry();	
		}
		else if(j == 4)
		{
			getLinePane();
			getLegendEntry();
		}
		else if (j == 5)
		{	
			getLinePane();
			getLegendEntry();
		}
		else if ( j == 6)
		{						
			getLinePane();			
			getLegendEntry();			
		}
		else if(j == 7)
		{		
			getLinePane();		
			getLegendEntry();	
		}
		else if(j == 8)
		{	
			getLinePane();		
			getLegendEntry();
		}	
		else if(j == 9)
		{
			getLinePane();	
			getLegendEntry();
		}
		else if(j == 10)
		{			
			getLinePane();	
			getLegendEntry();			
		}
		else if (j == 11)
		{
			getLinePane();
			getLegendEntry();
		}
		else if( j == 12)
		{
			getLinePane();
			getLegendEntry();
		}
		
		validate();
		fameLineChart.setSize(600, 400);
		fameLineChart.setVisible(true);
		fameLineChart.updateDisplay();
		lineChartPanel.add(fameLineChart, BorderLayout.CENTER);
	}
	
	// Show two different functor series in one chart
	public void TwoFuncSeries()
	{
		twoFuncChartPanel.removeAll();
		tiqObjectFunc1 = null;
		tiqObjectFunc2 = null;
		tiqObjectFunc3 = null;
		tiqObjectFunc4 = null;
		
		tiqObjectFunc1 = new TiqObject[chosens.length];
		tiqObjectFunc2 = new TiqObject[chosens.length];
		tiqObjectFunc3 = new TiqObject[chosens.length];
		tiqObjectFunc4 = new TiqObject[chosens.length];
		 
		for (int i = 0; i < j; i++)
		{	
			 	try {	
					tiqObjectFunc1 = getData8(chosens1[i], apStart, apEnd);
					tiqObjectFunc2 = getData7(chosens1[i], apStart, apEnd);
					tiqObjectFunc3[i] = tiqObjectFunc1[i];
					tiqObjectFunc4[i] = tiqObjectFunc2[i];
				} catch (Exception e) {		
					e.printStackTrace();
				}	 
		 } // end for loop
		
		chartView1 = new TiqView[chosens.length * 2];	
		for (int i = 0; i < chosens.length; i++)
		{
			//chartView1[i] = tiqObjectFunc1[i];
			chartView1[i] = tiqObjectFunc3[i];	
		}
		for (int i = 0; i < chosens.length; i++)
		{
			//chartView1[i + chosens.length] = tiqObjectFunc2[i];
			chartView1[i + chosens.length] = tiqObjectFunc4[i];
		}
		tiqviewsdata = new TiqViewsData("", chartView1);
		twoFuncPane = new ChartPane();
		ChartPane[] twoFuncPaneList = new ChartPane[1];
		twoFuncPaneList[0] = twoFuncPane;
		//linePane.setData(tiqviewsdata);		
		twoFuncLineSer = new ChartSeries[chartView1.length];						
		for(int i = 0; i < chartView1.length; i++)
		{
			if(chartType == "areaChart")
			{
				twoFuncLineSer[i] = (ChartSeries) new AreaSeries(i);
			}
			else if(chartType == "twoBarChart")
			{
				twoFuncLineSer[i] = (ChartSeries) new BarSeries(i);
			}
			else if(chartType == "lineChart")
			{
				twoFuncLineSer[i] = (ChartSeries) new LineSeries(i);
			}
			
			//str = chosens[i].indexOf("|");
			//twoFuncLineSer[i].setLabel(chosens[i].substring(0, str -1));
			twoFuncLineSer[i].setColor(colors[i]);	
		}
		
		twoFuncPane.setData(tiqviewsdata);
		twoFuncPane.setSeries(twoFuncLineSer);
		twoFuncPane.getLeftAxis().setUseInGrid(false);
		twoFuncPane.setBackgroundPaint(Color.WHITE);
		twoFuncChart.setPanes(twoFuncPaneList);      
		twoFuncChart.getDateAxis().setShowGrid(false);
		twoFuncChart.getDateAxis().setLabelPosition(0, LabelPosition.LABEL_POS_BETWEEN);
		twoFuncChart.getDateAxis().setLabelPosition(1, LabelPosition.LABEL_POS_BETWEEN);
		//fameLineChart.getDateAxis().getLabelPosition(1);
		ChartLegend chartlegend = twoFuncPane.getLegend();
		chartlegend.setBackgroundPaint(Color.WHITE);
		chartlegend.getStartPos().setXOffset(10);
		chartlegend.getStartPos().setYOffset(0);
		chartlegend.setBorder(null);
		chartlegend.setEntryLabelFirst(false);
		chartlegend.setSymbolSize(new Dimension(10,10));
		ChartDataPointDisplay dataPointDisplay = twoFuncPane.getDataPointDisplay();
        dataPointDisplay.setEntryValueFormat(nft);
        
        legendentry = new LegendEntry[j * 2];
        for (int i = 0; i < j; i++)
        {
        	legendentry[i] = twoFuncLineSer[i].getLegendEntry();
        	legendentry[i].setLabel(func_val + "(" + chosens[i] + ")");     	
        }
       // LegendEntry[] legendentry1 = new LegendEntry[j];
        for( int i = 0; i < j; i++)
        {
        	legendentry[i+j] = twoFuncLineSer[i+j].getLegendEntry();
        	legendentry[i+j].setLabel(func_val1 + "(" + chosens[i] + ")");
        }
            
        validate();
        twoFuncChart.setSize(600, 400);
        twoFuncChart.setVisible(true);
        twoFuncChart.updateDisplay();
        twoFuncChartPanel.add(twoFuncChart, BorderLayout.CENTER);
	}// end TwoFuncSeries
	
	public void getBarPane()
	{
		//ChartPane barPane = new ChartPane();
		barPane = new ChartPane();
		ChartPane[] barPaneList = new ChartPane[1];
		barPaneList[0] = barPane;
		barPane.setData(tiqviewsdata);
		barSer = new ChartSeries[j];
		for(int i = 0; i < j; i++)
		{	
			barSer[i]=(ChartSeries)new BarSeries(i);
			str = chosens[i].indexOf("|");
			barSer[i].setLabel(chosens[i].substring(0, str -1));
			barSer[i].setColor(colors[i]);
		}
		
		barPane.setSeries(barSer);
		barPane.getLeftAxis().setUseInGrid(false);
		barPane.setBackgroundPaint(Color.white);
		twoBarChart.setPanes(barPaneList);      
		twoBarChart.getDateAxis().setShowGrid(false);
		ChartLegend chartlegend = barPane.getLegend();
		chartlegend.setBackgroundPaint(Color.WHITE);
		chartlegend.getStartPos().setXOffset(10);
		chartlegend.getStartPos().setYOffset(0);
		chartlegend.setBorder(null);
		chartlegend.setEntryLabelFirst(false);
		chartlegend.setSymbolSize(new Dimension(10,10));
		ChartDataPointDisplay dataPointDisplay = barPane.getDataPointDisplay();
        dataPointDisplay.setEntryValueFormat(nft);
	}
	
	// Create two barSeries
	@SuppressWarnings("deprecation")
	public void ShowTwoBarSeries()
	{
		twoBarPanel.removeAll();
		get_funcChart();	
		if(j == 1)
		{
			getBarPane();	
			getLegendEntry();	
		}
		else if (j == 2)
		{
			getBarPane();
			getLegendEntry();
		}
		else if (j == 3)
		{
			getBarPane();
			getLegendEntry();
		}
		else if (j == 4)
		{		
			getBarPane();
			getLegendEntry();	
		}
		else if( j == 5)
		{
			getBarPane();
			getLegendEntry();
		}
		else if( j == 6)
		{
			getBarPane();	
			getLegendEntry();
		}
		else if(j == 7)
		{
			getBarPane();	
			getLegendEntry();
		}
		else if(j == 8)
		{
			getBarPane();	
			getLegendEntry();
		}
		else if(j == 9)
		{
			getBarPane();	
			getLegendEntry();
		}
		else if(j == 10)
		{
			getBarPane();	
			getLegendEntry();
		}
		
		validate();
		twoBarChart.setSize(600, 400);
		twoBarChart.setVisible(true);
		twoBarChart.updateDisplay();
		twoBarPanel.add(twoBarChart, BorderLayout.CENTER);
	
	}// end showtwobarchart
		
	public void getAreaPane()
	{
		//ChartPane areaPane = new ChartPane();
		areaPane = new ChartPane();
 		ChartPane[] areaPaneList = new ChartPane[1];
 		areaPaneList[0] = areaPane;	
 		areaPane.setData(tiqviewsdata);
 		areaSer = new ChartSeries[j];
 		for(int i = 0; i < j; i++)
 		{
 			areaSer[i]=(ChartSeries)new AreaSeries(i);
 			str = chosens[i].indexOf("|");
 			areaSer[i].setLabel(chosens[i].substring(0, str -1));	
 	 		areaSer[i].setColor(colors[i]);
 		}
 		
 		areaPane.setSeries(areaSer);
 		areaPane.getLeftAxis().setUseInGrid(false);
 		areaPane.setBackgroundPaint(Color.white);
 		areaChart.setPanes(areaPaneList);      
 		areaChart.getDateAxis().setShowGrid(false);
 		ChartLegend chartlegend = areaPane.getLegend();
 		chartlegend.setBackgroundPaint(Color.WHITE);
 		chartlegend.getStartPos().setXOffset(10);
 		chartlegend.getStartPos().setYOffset(0);
 		chartlegend.setBorder(null);
 		chartlegend.setEntryLabelFirst(false);
 		chartlegend.setSymbolSize(new Dimension(10,10));
 		ChartDataPointDisplay dataPointDisplay = areaPane.getDataPointDisplay();
        dataPointDisplay.setEntryValueFormat(nft);
	}
	
	// Show areaSeries
	@SuppressWarnings("deprecation")
	public void ShowAreaSeries()
	{
		areaPanel.removeAll();
		get_funcChart();	 	
			if(j == 1)
			{
				getAreaPane();
				getLegendEntry();	
		 	}
		 	else if(j == 2)
		 	{	
		 		getAreaPane();
		 		getLegendEntry();	 		
		 	}
		 	else if(j == 3)
		 	{
		 		getAreaPane();
		 		getLegendEntry();
		 	}
		 	else if ( j == 4)
		 	{	 		
		 		getAreaPane();
		 		getLegendEntry();
		 	}
		 	else if(j == 5)
		 	{		
		 		getAreaPane();
		 		getLegendEntry();
		 	}
		
		 	else if(j == 6)
		 	{	 		
		 		getAreaPane();
		 		getLegendEntry();			
		 	}
		 	else if(j == 7)
		 	{	
		 		getAreaPane();
		 		getLegendEntry();
		 	}
		 	else if(j == 8)
		 	{
		 		getAreaPane();
		 		getLegendEntry();
		 	}
		 	else if (j == 9)
		 	{
		 		getAreaPane();
		 		getLegendEntry();
		 	}
		 	else if(j == 10)
		 	{
		 		getAreaPane();
		 		getLegendEntry();
		 	}
					
			validate();
			areaChart.setSize(600, 400);
			areaChart.setVisible(true);
			areaChart.updateDisplay();
			areaPanel.add(areaChart, BorderLayout.CENTER);	
	}
	
	public void ShowStackedBarSeries()
	{	
		get_funcChart();	
		ChartPane stackedbarPane = new ChartPane();
		ChartPane[] stackedbarPaneList = new ChartPane[1];
		stackedbarPaneList[0] = stackedbarPane;	
		stackedbarPane.setData(tiqviewsdata);
		StackedBarSeries[] stackedbars = new StackedBarSeries[1];
		stackedbars[0] = new StackedBarSeries();
		stackedbars[0].setItemCount(3);
		stackedbars[0].setItemCount(3); 
		Paint[] fills = new Paint[] {Color.RED, Color.BLUE, Color.GREEN};
		stackedbars[0].setFills(fills);
		stackedbarPane.setSeries(stackedbars);
		stackedbarPane.getLeftAxis().setUseInGrid(false);
		stackedbarPane.setBackgroundPaint(Color.white);
		stackedbarChart.setPanes(stackedbarPaneList);      
		stackedbarChart.getDateAxis().setShowGrid(false);
		ChartLegend chartlegend = stackedbarPane.getLegend();
		chartlegend.setBackgroundPaint(Color.WHITE);
		chartlegend.getStartPos().setXOffset(10);
		chartlegend.getStartPos().setYOffset(0);
		chartlegend.setBorder(null);
		chartlegend.setEntryLabelFirst(false);
		chartlegend.setSymbolSize(new Dimension(10,10));
		LegendEntry e1 = stackedbars[0].getLegendEntry();
		e1.setLabel(chosens[0].toString());
		LegendEntry e2 = stackedbars[0].getLegendEntry();
		e2.setLabel(chosens[1].toString());
		LegendEntry e3 = stackedbars[0].getLegendEntry();
		e3.setLabel(chosens[2].toString());
		
		validate();
		stackedbarChart.setSize(600, 400);
		stackedbarChart.setVisible(true);
		stackedbarChart.updateDisplay();
		stackedBarPanel.add(stackedbarChart, BorderLayout.CENTER);
	}
	
	// Get functions of TimeIQ charts
	public void get_funcChart()
	{
		if(func_val == null)
		{
			get_funcViews();
			tiqviewsdata = new TiqViewsData("", chartView);	
		}
		else if(func_val == "Annpct")	
		{
			get_funcViews();
			tiqviewsdata = new TiqViewsData("", chartView);
		}
		else if (func_val == "Csum")
		{
			get_funcViews();
			tiqviewsdata = new TiqViewsData("", chartView);
		}
		else if(func_val == "Diff")
		{	
			get_funcViews();
			tiqviewsdata = new TiqViewsData("", chartView);
		}
		else if(func_val == "Pct")
		{
			get_funcViews();
			tiqviewsdata = new TiqViewsData("", chartView);
		}
		else if (func_val == "Mstddev")
		{
			get_funcViews();
			tiqviewsdata = new TiqViewsData("", chartView);
		}
		else if (func_val == "Stddev")
		{
			get_funcViews();
			tiqviewsdata = new TiqViewsData("", chartView);
		}
		else if(func_val == "Ytydiff")
		{
			get_funcViews();
			tiqviewsdata = new TiqViewsData("", chartView);
		}
		else if(func_val == "Ytypct")	
		{
			get_funcViews();
			tiqviewsdata = new TiqViewsData("", chartView);
		}
		else if(func_val == "Baseyear")
		{
			get_funcViews();
			tiqviewsdata = new TiqViewsData("", chartView);
		}
		
	}// end get_funcChart
	
	// get func views
	public void get_funcViews()
	{
		if(func_val1 != null)
		{
			TwoFuncSeries();
		}	
		get_ts();	
		TiqView[] tw1, tw2, tw3, tw4, tw5, tw6, tw7, tw8, tw9, tw10, tw11, tw12;		
			if(t_scale == false)
			{
				if(func_val1 != null)
				{
					tw1 = new TiqView[2];
					tw2 = new TiqView[2];
					tw3 = new TiqView[2];
					tw4 = new TiqView[2];
					tw5 = new TiqView[2];
					tw6 = new TiqView[2];
					tw7 = new TiqView[2];
					tw8 = new TiqView[2];
					tw9 = new TiqView[2];
					tw10 = new TiqView[2];
					tw11 = new TiqView[2];
					tw12 = new TiqView[2];
				}
				else 
				{
					tw1 = new TiqView[1];
					tw2 = new TiqView[1];
					tw3 = new TiqView[1];
					tw4 = new TiqView[1];
					tw5 = new TiqView[1];
					tw6 = new TiqView[1];
					tw7 = new TiqView[1];
					tw8 = new TiqView[1];
					tw9 = new TiqView[1];
					tw10 = new TiqView[1];
					tw11 = new TiqView[1];
					tw12 = new TiqView[1];
				}
			}
			else 
			{
				tw1 = new TiqView[2];
				tw2 = new TiqView[2];
				tw3 = new TiqView[2];
				tw4 = new TiqView[2];
				tw5 = new TiqView[2];
				tw6 = new TiqView[2];
				tw7 = new TiqView[2];
				tw8 = new TiqView[2];
				tw9 = new TiqView[2];
				tw10 = new TiqView[2];
				tw11 = new TiqView[2];
				tw12 = new TiqView[2];
			}
		
		//if(m_graph = true)
		if(oneChart == "mChart")
		{
			if( j == 1)
			{
				if(t_scale == true)
				{	
					if(func_val1 != null)
					{
						tw1[0] = chartView1[0];
						tw1[1] = chartView1[1];
						tiqviewsdata1 = new TiqViewsData("", tw1);
					}		
					else 
					{
						tw1[0] = chartView[0];
						tw1[1] = chartView[1];
						tiqviewsdata1 = new TiqViewsData("", tw1);
					}
				}
				else 
				{
					if(func_val1 != null)
					{
						tw1[0] = chartView1[0];
						tw1[1] = chartView1[1];
						tiqviewsdata1 = new TiqViewsData("", tw1);
					}
					else 
					{	
						tw1[0] = chartView[0];
						tiqviewsdata1 = new TiqViewsData("", tw1);
					}
				}
			}
			else if (j == 2)
			{
				if(t_scale == true)
				{
					if(func_val1 != null)
					{
						tw1[0] = chartView1[0];
						tw1[1] = chartView1[1];
						tw2[0] = chartView1[2];
						tw2[1] = chartView1[3];
						//tiqviewsdata1 = new TiqViewsData("", tw1);
						//tiqviewsdata2 = new TiqViewsData("", tw2);		
					}
					else
					{
						tw1[0] = chartView[0];
						tw1[1] = chartView[1];
						tw2[0] = chartView[2];
						tw2[1] = chartView[3];	
						//tiqviewsdata1 = new TiqViewsData("", tw1);
						//tiqviewsdata2 = new TiqViewsData("", tw2);	
					}
				}
				else 
				{
					if(func_val1 != null)
					{
						tw1[0] = chartView1[0];
						tw1[1] = chartView1[1];
						tw2[0] = chartView1[2];
						tw2[1] = chartView1[3];
					}
					else 
					{
						tw1[0] = chartView[0];
						tw2[0] = chartView[1];
					}
				}
				tiqviewsdata1 = new TiqViewsData("", tw1);
				tiqviewsdata2 = new TiqViewsData("", tw2);
			}
			else if ( j == 3)
			{
				if(t_scale == true)
				{	
					if(func_val1 != null)
					{
						tw1[0] = chartView1[0];
						tw1[1] = chartView1[1];
						tw2[0] = chartView1[2];
						tw2[1] = chartView1[3];
						tw3[0] = chartView1[4];
						tw3[1] = chartView1[5];
					}
					else 
					{					
						tw1[0] = chartView[0];
						tw2[0] = chartView[1];
						tw3[0] = chartView[2];
						tw1[1] = chartView[3];
						tw2[1] = chartView[4];
						tw3[1] = chartView[5];					
					}
				}
				else 
				{
					if(func_val1 != null)
					{
						tw1[0] = chartView1[0];
						tw1[1] = chartView1[1];
						tw2[0] = chartView1[2];
						tw2[1] = chartView1[3];
						tw3[0] = chartView1[4];
						tw3[1] = chartView1[5];
					}
					else 
					{
						tw1[0] = chartView[0];
						tw2[0] = chartView[1];
						tw3[0] = chartView[2];
					}
				}
				tiqviewsdata1 = new TiqViewsData("", tw1);
				tiqviewsdata2 = new TiqViewsData("", tw2);
				tiqviewsdata3 = new TiqViewsData("", tw3);
			}
			else if ( j == 4)
			{
				if(t_scale == true)
				{
					if(func_val1 != null)
					{
						tw1[0] = chartView1[0];
						tw1[1] = chartView1[1];
						tw2[0] = chartView1[2];
						tw2[1] = chartView1[3];
						tw3[0] = chartView1[4];
						tw3[1] = chartView1[5];
						tw4[0] = chartView1[6];
						tw4[1] = chartView1[7];
					}
					else 
					{
						tw1[0] = chartView[0];
						tw2[0] = chartView[1];
						tw3[0] = chartView[2];
						tw4[0] = chartView[3];
						tw1[1] = chartView[4];
						tw2[1] = chartView[5];
						tw3[1] = chartView[6];
						tw4[1] = chartView[7];
					}
				}
				else 
				{
					if(func_val1 != null)
					{
						tw1[0] = chartView1[0];
						tw1[1] = chartView1[1];
						tw2[0] = chartView1[2];
						tw2[1] = chartView1[3];
						tw3[0] = chartView1[4];
						tw3[1] = chartView1[5];
						tw4[0] = chartView1[6];
						tw4[1] = chartView1[7];
					}
					else 
					{
						tw1[0] = chartView[0];
						tw2[0] = chartView[1];
						tw3[0] = chartView[2];
						tw4[0] = chartView[3];
					}
				}
				tiqviewsdata1 = new TiqViewsData("", tw1);			
				tiqviewsdata2 = new TiqViewsData("", tw2);
				tiqviewsdata3 = new TiqViewsData("", tw3);
				tiqviewsdata4 = new TiqViewsData("", tw4);
			}
			else if(j == 5)
			{	
				if(t_scale == true)
				{
					if(func_val1 != null)
					{
						tw1[0] = chartView1[0];
						tw1[1] = chartView1[1];
						tw2[0] = chartView1[2];
						tw2[1] = chartView1[3];
						tw3[0] = chartView1[4];
						tw3[1] = chartView1[5];
						tw4[0] = chartView1[6];
						tw4[1] = chartView1[7];
						tw5[0] = chartView1[8];
						tw5[1] = chartView1[9];
					}	
					else
					{
						tw1[0] = chartView[0];
						tw2[0] = chartView[1];
						tw3[0] = chartView[2];
						tw4[0] = chartView[3];
						tw5[0] = chartView[4];
						tw1[1] = chartView[5];
						tw2[1] = chartView[6];
						tw3[1] = chartView[7];
						tw4[1] = chartView[8];
						tw5[1] = chartView[9];
					}
				}
				else 
				{
					if(func_val1 != null)
					{
						tw1[0] = chartView1[0];
						tw1[1] = chartView1[1];
						tw2[0] = chartView1[2];
						tw2[1] = chartView1[3];
						tw3[0] = chartView1[4];
						tw3[1] = chartView1[5];
						tw4[0] = chartView1[6];
						tw4[1] = chartView1[7];
						tw5[0] = chartView1[8];
						tw5[1] = chartView1[9];
					}
					else 
					{
						tw1[0] = chartView[0];
						tw2[0] = chartView[1];
						tw3[0] = chartView[2];
						tw4[0] = chartView[3];
						tw5[0] = chartView[4];
					}
				}	
				tiqviewsdata1 = new TiqViewsData("", tw1);	
				tiqviewsdata2 = new TiqViewsData("", tw2);
				tiqviewsdata3 = new TiqViewsData("", tw3);
				tiqviewsdata4 = new TiqViewsData("", tw4);
				tiqviewsdata5 = new TiqViewsData("", tw5);	
			}
			else if(j == 6)
			{	
				if(t_scale == true)
				{
					if(func_val1 != null)
					{
						tw1[0] = chartView1[0];
						tw1[1] = chartView1[1];
						tw2[0] = chartView1[2];
						tw2[1] = chartView1[3];
						tw3[0] = chartView1[4];
						tw3[1] = chartView1[5];
						tw4[0] = chartView1[6];
						tw4[1] = chartView1[7];
						tw5[0] = chartView1[8];
						tw5[1] = chartView1[9];
						tw6[0] = chartView1[10];
						tw6[1] = chartView1[11];
					}
					else 
					{
						tw1[0] = chartView[0];
						tw2[0] = chartView[1];
						tw3[0] = chartView[2];
						tw4[0] = chartView[3];
						tw5[0] = chartView[4];
						tw6[0] = chartView[5];
						tw1[1] = chartView[6];
						tw2[1] = chartView[7];
						tw3[1] = chartView[8];
						tw4[1] = chartView[9];
						tw5[1] = chartView[10];
						tw6[1] = chartView[11];
					}				
				}
				else 
				{
					if(func_val1 != null)
					{
						tw1[0] = chartView1[0];
						tw1[1] = chartView1[1];
						tw2[0] = chartView1[2];
						tw2[1] = chartView1[3];
						tw3[0] = chartView1[4];
						tw3[1] = chartView1[5];
						tw4[0] = chartView1[6];
						tw4[1] = chartView1[7];
						tw5[0] = chartView1[8];
						tw5[1] = chartView1[9];
						tw6[0] = chartView1[10];
						tw6[1] = chartView1[11];
					}
					else 
					{
						tw1[0] = chartView[0];
						tw2[0] = chartView[1];
						tw3[0] = chartView[2];
						tw4[0] = chartView[3];
						tw5[0] = chartView[4];
						tw6[0] = chartView[5];
					}
				}		
				tiqviewsdata1 = new TiqViewsData("", tw1);			
				tiqviewsdata2 = new TiqViewsData("", tw2);	
				tiqviewsdata3 = new TiqViewsData("", tw3);			
				tiqviewsdata4 = new TiqViewsData("", tw4);			
				tiqviewsdata5 = new TiqViewsData("", tw5);		
				tiqviewsdata6 = new TiqViewsData("", tw6);
			}
			else if (j == 7)
			{
				if(t_scale == true)
				{
					if(func_val1 != null)
					{
						tw1[0] = chartView1[0];
						tw1[1] = chartView1[1];
						tw2[0] = chartView1[2];
						tw2[1] = chartView1[3];
						tw3[0] = chartView1[4];
						tw3[1] = chartView1[5];
						tw4[0] = chartView1[6];
						tw4[1] = chartView1[7];
						tw5[0] = chartView1[8];
						tw5[1] = chartView1[9];
						tw6[0] = chartView1[10];
						tw6[1] = chartView1[11];
						tw7[0] = chartView1[12];
						tw7[1] = chartView1[13];
					}
					else 
					{
						tw1[0] = chartView[0];
						tw2[0] = chartView[1];
						tw3[0] = chartView[2];
						tw4[0] = chartView[3];
						tw5[0] = chartView[4];
						tw6[0] = chartView[5];
						tw7[0] = chartView[6];
						tw1[1] = chartView[7];
						tw2[1] = chartView[8];
						tw3[1] = chartView[9];
						tw4[1] = chartView[10];
						tw5[1] = chartView[11];
						tw6[1] = chartView[12];
						tw7[1] = chartView[13];
					}
				}
				else 
				{
					if(func_val1 != null)
					{
						tw1[0] = chartView1[0];
						tw1[1] = chartView1[1];
						tw2[0] = chartView1[2];
						tw2[1] = chartView1[3];
						tw3[0] = chartView1[4];
						tw3[1] = chartView1[5];
						tw4[0] = chartView1[6];
						tw4[1] = chartView1[7];
						tw5[0] = chartView1[8];
						tw5[1] = chartView1[9];
						tw6[0] = chartView1[10];
						tw6[1] = chartView1[11];
						tw7[0] = chartView1[12];
						tw7[1] = chartView1[13];
					}
					else 
					{
						tw1[0] = chartView[0];
						tw2[0] = chartView[1];
						tw3[0] = chartView[2];
						tw4[0] = chartView[3];
						tw5[0] = chartView[4];
						tw6[0] = chartView[5];
						tw7[0] = chartView[6];
					}	
				}
				tiqviewsdata1 = new TiqViewsData("", tw1);		
				tiqviewsdata2 = new TiqViewsData("", tw2);			
				tiqviewsdata3 = new TiqViewsData("", tw3);			
				tiqviewsdata4 = new TiqViewsData("", tw4);				
				tiqviewsdata5 = new TiqViewsData("", tw5);			
				tiqviewsdata6 = new TiqViewsData("", tw6);			
				tiqviewsdata7 = new TiqViewsData("", tw7);
				
			}
			else if (j == 8)
			{
				if(t_scale == true)
				{		
					if(func_val1 != null)
					{
						tw1[0] = chartView1[0];
						tw1[1] = chartView1[1];
						tw2[0] = chartView1[2];
						tw2[1] = chartView1[3];
						tw3[0] = chartView1[4];
						tw3[1] = chartView1[5];
						tw4[0] = chartView1[6];
						tw4[1] = chartView1[7];
						tw5[0] = chartView1[8];
						tw5[1] = chartView1[9];
						tw6[0] = chartView1[10];
						tw6[1] = chartView1[11];
						tw7[0] = chartView1[12];
						tw7[1] = chartView1[13];
						tw8[0] = chartView1[14];
						tw8[1] = chartView1[15];				
					}
					else 
					{
						tw1[0] = chartView[0];
						tw2[0] = chartView[1];
						tw3[0] = chartView[2];
						tw4[0] = chartView[3];
						tw5[0] = chartView[4];
						tw6[0] = chartView[5];
						tw7[0] = chartView[6];
						tw8[0] = chartView[7];
						tw1[1] = chartView[8];
						tw2[1] = chartView[9];
						tw3[1] = chartView[10];
						tw4[1] = chartView[11];
						tw5[1] = chartView[12];
						tw6[1] = chartView[13];
						tw7[1] = chartView[14];
						tw8[1] = chartView[15];
					}
				}
				else 
				{
					if(func_val1 != null)
					{
						tw1[0] = chartView1[0];
						tw1[1] = chartView1[1];
						tw2[0] = chartView1[2];
						tw2[1] = chartView1[3];
						tw3[0] = chartView1[4];
						tw3[1] = chartView1[5];
						tw4[0] = chartView1[6];
						tw4[1] = chartView1[7];
						tw5[0] = chartView1[8];
						tw5[1] = chartView1[9];
						tw6[0] = chartView1[10];
						tw6[1] = chartView1[11];
						tw7[0] = chartView1[12];
						tw7[1] = chartView1[13];
						tw8[0] = chartView1[14];
						tw8[1] = chartView1[15];
					}
					else 
					{
						tw1[0] = chartView[0];
						tw2[0] = chartView[1];
						tw3[0] = chartView[2];
						tw4[0] = chartView[3];
						tw5[0] = chartView[4];
						tw6[0] = chartView[5];
						tw7[0] = chartView[6];
						tw8[0] = chartView[7];
					}
				
				}
				tiqviewsdata1 = new TiqViewsData("", tw1);		
				tiqviewsdata2 = new TiqViewsData("", tw2);			
				tiqviewsdata3 = new TiqViewsData("", tw3);			
				tiqviewsdata4 = new TiqViewsData("", tw4);				
				tiqviewsdata5 = new TiqViewsData("", tw5);			
				tiqviewsdata6 = new TiqViewsData("", tw6);			
				tiqviewsdata7 = new TiqViewsData("", tw7);
				tiqviewsdata8 = new TiqViewsData("", tw8);
				
			}
			else if(j == 9)
			{
				if(t_scale == true)
				{
					if(func_val1 != null)
					{
						tw1[0] = chartView1[0];
						tw1[1] = chartView1[1];
						tw2[0] = chartView1[2];
						tw2[1] = chartView1[3];
						tw3[0] = chartView1[4];
						tw3[1] = chartView1[5];
						tw4[0] = chartView1[6];
						tw4[1] = chartView1[7];
						tw5[0] = chartView1[8];
						tw5[1] = chartView1[9];
						tw6[0] = chartView1[10];
						tw6[1] = chartView1[11];
						tw7[0] = chartView1[12];
						tw7[1] = chartView1[13];
						tw8[0] = chartView1[14];
						tw8[1] = chartView1[15];		
						tw9[0] = chartView1[16];
						tw9[1] = chartView1[17];
					}
					else
					{
						tw1[0] = chartView[0];
						tw2[0] = chartView[1];
						tw3[0] = chartView[2];
						tw4[0] = chartView[3];
						tw5[0] = chartView[4];
						tw6[0] = chartView[5];
						tw7[0] = chartView[6];
						tw8[0] = chartView[7];
						tw9[0] = chartView[8];
						tw1[1] = chartView[9];
						tw2[1] = chartView[10];
						tw3[1] = chartView[11];
						tw4[1] = chartView[12];
						tw5[1] = chartView[13];
						tw6[1] = chartView[14];
						tw7[1] = chartView[15];
						tw8[1] = chartView[16];
						tw9[1] = chartView[17];
					}
				}
				else 
				{
					if(func_val1 != null)
					{
						tw1[0] = chartView1[0];
						tw1[1] = chartView1[1];
						tw2[0] = chartView1[2];
						tw2[1] = chartView1[3];
						tw3[0] = chartView1[4];
						tw3[1] = chartView1[5];
						tw4[0] = chartView1[6];
						tw4[1] = chartView1[7];
						tw5[0] = chartView1[8];
						tw5[1] = chartView1[9];
						tw6[0] = chartView1[10];
						tw6[1] = chartView1[11];
						tw7[0] = chartView1[12];
						tw7[1] = chartView1[13];
						tw8[0] = chartView1[14];
						tw8[1] = chartView1[15];		
						tw9[0] = chartView1[16];
						tw9[1] = chartView1[17];
					}
					else
					{
						tw1[0] = chartView[0];
						tw2[0] = chartView[1];
						tw3[0] = chartView[2];
						tw4[0] = chartView[3];
						tw5[0] = chartView[4];
						tw6[0] = chartView[5];
						tw7[0] = chartView[6];
						tw8[0] = chartView[7];
						tw9[0] = chartView[8];
					}
				}
				tiqviewsdata1 = new TiqViewsData("", tw1);		
				tiqviewsdata2 = new TiqViewsData("", tw2);			
				tiqviewsdata3 = new TiqViewsData("", tw3);			
				tiqviewsdata4 = new TiqViewsData("", tw4);				
				tiqviewsdata5 = new TiqViewsData("", tw5);			
				tiqviewsdata6 = new TiqViewsData("", tw6);			
				tiqviewsdata7 = new TiqViewsData("", tw7);
				tiqviewsdata8 = new TiqViewsData("", tw8);
				tiqviewsdata9 = new TiqViewsData("", tw9);
			}
			else if(j == 10)
			{
				if(t_scale == true)
				{	
					if(func_val1 != null)
					{
						tw1[0] = chartView1[0];
						tw1[1] = chartView1[1];
						tw2[0] = chartView1[2];
						tw2[1] = chartView1[3];
						tw3[0] = chartView1[4];
						tw3[1] = chartView1[5];
						tw4[0] = chartView1[6];
						tw4[1] = chartView1[7];
						tw5[0] = chartView1[8];
						tw5[1] = chartView1[9];
						tw6[0] = chartView1[10];
						tw6[1] = chartView1[11];
						tw7[0] = chartView1[12];
						tw7[1] = chartView1[13];
						tw8[0] = chartView1[14];
						tw8[1] = chartView1[15];		
						tw9[0] = chartView1[16];
						tw9[1] = chartView1[17];
						tw10[0] = chartView1[18];
						tw10[1] = chartView1[19];
					}
					else 
					{
						tw1[0] = chartView[0];
						tw2[0] = chartView[1];
						tw3[0] = chartView[2];
						tw4[0] = chartView[3];
						tw5[0] = chartView[4];
						tw6[0] = chartView[5];
						tw7[0] = chartView[6];
						tw8[0] = chartView[7];
						tw9[0] = chartView[8];
						tw10[0] = chartView[9];
						tw1[1] = chartView[10];
						tw2[1] = chartView[11];
						tw3[1] = chartView[12];
						tw4[1] = chartView[13];
						tw5[1] = chartView[14];
						tw6[1] = chartView[15];
						tw7[1] = chartView[16];
						tw8[1] = chartView[17];
						tw9[1] = chartView[18];
						tw10[1] = chartView[19];
					}
				}
				else 
				{
					if(func_val1 != null)
					{
						tw1[0] = chartView1[0];
						tw1[1] = chartView1[1];
						tw2[0] = chartView1[2];
						tw2[1] = chartView1[3];
						tw3[0] = chartView1[4];
						tw3[1] = chartView1[5];
						tw4[0] = chartView1[6];
						tw4[1] = chartView1[7];
						tw5[0] = chartView1[8];
						tw5[1] = chartView1[9];
						tw6[0] = chartView1[10];
						tw6[1] = chartView1[11];
						tw7[0] = chartView1[12];
						tw7[1] = chartView1[13];
						tw8[0] = chartView1[14];
						tw8[1] = chartView1[15];		
						tw9[0] = chartView1[16];
						tw9[1] = chartView1[17];
						tw10[0] = chartView1[18];
						tw10[1] = chartView1[19];
					}
					else 
					{
						tw1[0] = chartView[0];
						tw2[0] = chartView[1];
						tw3[0] = chartView[2];
						tw4[0] = chartView[3];
						tw5[0] = chartView[4];
						tw6[0] = chartView[5];
						tw7[0] = chartView[6];
						tw8[0] = chartView[7];
						tw9[0] = chartView[8];
						tw10[0] = chartView[9];
					}
				}
				
				tiqviewsdata1 = new TiqViewsData("", tw1);		
				tiqviewsdata2 = new TiqViewsData("", tw2);			
				tiqviewsdata3 = new TiqViewsData("", tw3);			
				tiqviewsdata4 = new TiqViewsData("", tw4);				
				tiqviewsdata5 = new TiqViewsData("", tw5);			
				tiqviewsdata6 = new TiqViewsData("", tw6);			
				tiqviewsdata7 = new TiqViewsData("", tw7);
				tiqviewsdata8 = new TiqViewsData("", tw8);
				tiqviewsdata9 = new TiqViewsData("", tw9);
				tiqviewsdata10 = new TiqViewsData("", tw10);
			}
			else if(j == 11)
			{
				if(t_scale == true)
				{
					if(func_val1 != null)
					{
						tw1[0] = chartView1[0];
						tw1[1] = chartView1[1];
						tw2[0] = chartView1[2];
						tw2[1] = chartView1[3];
						tw3[0] = chartView1[4];
						tw3[1] = chartView1[5];
						tw4[0] = chartView1[6];
						tw4[1] = chartView1[7];
						tw5[0] = chartView1[8];
						tw5[1] = chartView1[9];
						tw6[0] = chartView1[10];
						tw6[1] = chartView1[11];
						tw7[0] = chartView1[12];
						tw7[1] = chartView1[13];
						tw8[0] = chartView1[14];
						tw8[1] = chartView1[15];		
						tw9[0] = chartView1[16];
						tw9[1] = chartView1[17];
						tw10[0]= chartView1[18];
						tw10[1] = chartView1[19];
						tw11[0] = chartView1[20];
						tw11[1] = chartView1[21];
					}
					else 
					{
						tw1[0] = chartView[0];
						tw2[0] = chartView[1];
						tw3[0] = chartView[2];
						tw4[0] = chartView[3];
						tw5[0] = chartView[4];
						tw6[0] = chartView[5];
						tw7[0] = chartView[6];
						tw8[0] = chartView[7];
						tw9[0] = chartView[8];
						tw10[0] = chartView[9];
						tw11[0] = chartView[10];
						tw1[1] = chartView[11];
						tw2[1] = chartView[12];
						tw3[1] = chartView[13];
						tw4[1] = chartView[14];
						tw5[1] = chartView[15];
						tw6[1] = chartView[16];
						tw7[1] = chartView[17];
						tw8[1] = chartView[18];
						tw9[1] = chartView[19];
						tw10[1] = chartView[20];
						tw11[1] = chartView[21];
					}
				}
				else 
				{
					if(func_val1 != null)
					{
						tw1[0] = chartView1[0];
						tw1[1] = chartView1[1];
						tw2[0] = chartView1[2];
						tw2[1] = chartView1[3];
						tw3[0] = chartView1[4];
						tw3[1] = chartView1[5];
						tw4[0] = chartView1[6];
						tw4[1] = chartView1[7];
						tw5[0] = chartView1[8];
						tw5[1] = chartView1[9];
						tw6[0] = chartView1[10];
						tw6[1] = chartView1[11];
						tw7[0] = chartView1[12];
						tw7[1] = chartView1[13];
						tw8[0] = chartView1[14];
						tw8[1] = chartView1[15];		
						tw9[0] = chartView1[16];
						tw9[1] = chartView1[17];
						tw10[0]= chartView1[18];
						tw10[1] = chartView1[19];
						tw11[0] = chartView1[20];
						tw11[1] = chartView1[21];
					}
					else 
					{
						tw1[0] = chartView[0];
						tw2[0] = chartView[1];
						tw3[0] = chartView[2];
						tw4[0] = chartView[3];
						tw5[0] = chartView[4];
						tw6[0] = chartView[5];
						tw7[0] = chartView[6];
						tw8[0] = chartView[7];
						tw9[0] = chartView[8];
						tw10[0] = chartView[9];
						tw11[0] = chartView[10];
					}
				}
				tiqviewsdata1 = new TiqViewsData("", tw1);		
				tiqviewsdata2 = new TiqViewsData("", tw2);			
				tiqviewsdata3 = new TiqViewsData("", tw3);			
				tiqviewsdata4 = new TiqViewsData("", tw4);				
				tiqviewsdata5 = new TiqViewsData("", tw5);			
				tiqviewsdata6 = new TiqViewsData("", tw6);			
				tiqviewsdata7 = new TiqViewsData("", tw7);
				tiqviewsdata8 = new TiqViewsData("", tw8);
				tiqviewsdata9 = new TiqViewsData("", tw9);
				tiqviewsdata10 = new TiqViewsData("", tw10);
				tiqviewsdata11 = new TiqViewsData("", tw11);
			}
			else if(j == 12)
			{
				if(t_scale == true)
				{
					if(func_val1 != null)
					{
						tw1[0] = chartView1[0];
						tw1[1] = chartView1[1];
						tw2[0] = chartView1[2];
						tw2[1] = chartView1[3];
						tw3[0] = chartView1[4];
						tw3[1] = chartView1[5];
						tw4[0] = chartView1[6];
						tw4[1] = chartView1[7];
						tw5[0] = chartView1[8];
						tw5[1] = chartView1[9];
						tw6[0] = chartView1[10];
						tw6[1] = chartView1[11];
						tw7[0] = chartView1[12];
						tw7[1] = chartView1[13];
						tw8[0] = chartView1[14];
						tw8[1] = chartView1[15];		
						tw9[0] = chartView1[16];
						tw9[1] = chartView1[17];
						tw10[0]= chartView1[18];
						tw10[1] = chartView1[19];
						tw11[0] = chartView1[20];
						tw11[1] = chartView1[21];
						tw12[0] = chartView1[22];
						tw12[1] = chartView1[23];
					}
					else 
					{
						tw1[0] = chartView[0];
						tw2[0] = chartView[1];
						tw3[0] = chartView[2];
						tw4[0] = chartView[3];
						tw5[0] = chartView[4];
						tw6[0] = chartView[5];
						tw7[0] = chartView[6];
						tw8[0] = chartView[7];
						tw9[0] = chartView[8];
						tw10[0] = chartView[9];
						tw11[0] = chartView[10];
						tw12[0] = chartView[11];
						tw1[1] = chartView[12];
						tw2[1] = chartView[13];
						tw3[1] = chartView[14];
						tw4[1] = chartView[15];
						tw5[1] = chartView[16];
						tw6[1] = chartView[17];
						tw7[1] = chartView[18];
						tw8[1] = chartView[19];
						tw9[1] = chartView[20];
						tw10[1] = chartView[21];
						tw11[1] = chartView[22];
						tw12[1] = chartView[23];
					}
				}
				else 
				{
					if(func_val1 != null)
					{
						tw1[0] = chartView1[0];
						tw1[1] = chartView1[1];
						tw2[0] = chartView1[2];
						tw2[1] = chartView1[3];
						tw3[0] = chartView1[4];
						tw3[1] = chartView1[5];
						tw4[0] = chartView1[6];
						tw4[1] = chartView1[7];
						tw5[0] = chartView1[8];
						tw5[1] = chartView1[9];
						tw6[0] = chartView1[10];
						tw6[1] = chartView1[11];
						tw7[0] = chartView1[12];
						tw7[1] = chartView1[13];
						tw8[0] = chartView1[14];
						tw8[1] = chartView1[15];		
						tw9[0] = chartView1[16];
						tw9[1] = chartView1[17];
						tw10[0]= chartView1[18];
						tw10[1] = chartView1[19];
						tw11[0] = chartView1[20];
						tw11[1] = chartView1[21];
						tw12[0] = chartView1[22];
						tw12[1] = chartView1[23];
					}
					else 
					{
						tw1[0] = chartView[0];
						tw2[0] = chartView[1];
						tw3[0] = chartView[2];
						tw4[0] = chartView[3];
						tw5[0] = chartView[4];
						tw6[0] = chartView[5];
						tw7[0] = chartView[6];
						tw8[0] = chartView[7];
						tw9[0] = chartView[8];
						tw10[0] = chartView[9];
						tw11[0] = chartView[10];
						tw12[0] = chartView[11];
					}
				}
				
				tiqviewsdata1 = new TiqViewsData("", tw1);		
				tiqviewsdata2 = new TiqViewsData("", tw2);			
				tiqviewsdata3 = new TiqViewsData("", tw3);			
				tiqviewsdata4 = new TiqViewsData("", tw4);				
				tiqviewsdata5 = new TiqViewsData("", tw5);			
				tiqviewsdata6 = new TiqViewsData("", tw6);			
				tiqviewsdata7 = new TiqViewsData("", tw7);
				tiqviewsdata8 = new TiqViewsData("", tw8);
				tiqviewsdata9 = new TiqViewsData("", tw9);
				tiqviewsdata10 = new TiqViewsData("", tw10);
				tiqviewsdata11 = new TiqViewsData("", tw11);
				tiqviewsdata12 = new TiqViewsData("", tw12);
			}
		}		
	}// end get_funcViews
	
	//public static void pad(String str, int width)
	public String pad(String str, int width)
    {     
		StringBuffer buffer = new StringBuffer();
		s = "";
		for (int i=0; i< width-str.length(); i++)
		{
			buffer.append(".");
			s = buffer.toString();		 
		}
		return s;
    }
			
	public String padLeft(String s, int size)
    {
        StringBuffer sb = new StringBuffer(s);
        for (int i = 0; i < size - s.length(); i++)
            sb.append("-");
        return new String(sb);
    }
	
	public String padEmpty(String s, int size)
	{
		StringBuffer sb = new StringBuffer(s);
		for (int i = 0; i < size; i++)
			sb.append(" ");
			return new String(sb);
		
	}
		
	// create table with chart underlying data by using FameDataModel
	public void chartDataModel1()
	{
		try
		{
			fameDataModel1 = new FameDataModel(rt.tiqLineView);
			fameDataModel1 = fameLineChart.getChartInfo().getFameDataModel();
			// get the number of rows and columns of the table
			nCols = fameDataModel1.getColumnCount() + 1;
			nRows = fameDataModel1.getRowCount() + 1;
			jTable = new JTable();
			dTableModel = new DefaultTableModel(nCols, nRows);
			jTable.setModel(dTableModel);
			jTable.setAutoResizeMode(jTable.AUTO_RESIZE_ALL_COLUMNS);		
			// get column label. The size og colLabel array equals number of columns. 
			// the column label is the index of the series					
			colLabel = new Object[fameDataModel1.getColumnCount()];			
			for (int i = 0; i < colLabel.length; i++)
			{			
				colLabel[i] = fameDataModel1.getColumnLabel(i);
				dTableModel.setValueAt(colLabel[i].toString(), i+1, 0);								
			}	
			
			// get row label. The row label is the names of the series
			rowLabel = new Object[fameDataModel1.getRowCount()];
			tblValues = new Object[fameDataModel1.getColumnCount()];		
			for ( int row = 0; row < fameDataModel1.getRowCount(); row++)
			{
				rowLabel[row] = fameDataModel1.getRowLabel(row);
				dTableModel.setValueAt(rowLabel[row].toString(), 0, row+1);
				for (int col = 0; col < fameDataModel1.getColumnCount(); col++)
				{
					tblValues[col] = valueFmt.format(fameDataModel1.getValue(row, col));
					dTableModel.setValueAt(tblValues[col], col+1, row+1);
				}					
			}
			
			sp5 = new JScrollPane(jTable);
			sp5.setMinimumSize(new Dimension(200, 200));
			sp5.setPreferredSize(new Dimension(400, 400));
			jTable.revalidate();
			table1Panel.add(sp5, BorderLayout.CENTER);
			SwingUtilities.updateComponentTreeUI(table1Panel);	
		}
		catch(Exception e)
		{}		
	}
	
	private class ToolWindow1 extends JFrame
	{
		public ToolWindow1() 
		{
			try {
				c1 = getContentPane();
				c1.setLayout(new BorderLayout());
				//c1.setVisible(true);
				c1.removeAll();
				rt.createOrdreTab1(rt.ordreTabData1);
				c1.add(rt.ordreTabPanel, BorderLayout.CENTER);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private class ToolWindow2 extends JFrame
	{
		 public ToolWindow2()
		 {
			 c2 = getContentPane();
			 c2.setLayout(new BorderLayout());
			 c2.removeAll();
			 chartDataModel1();
			 c2.add(table1Panel, BorderLayout.CENTER);
		 }
	}

	// JList listener for dblist1
	public class dbList1_listener implements ListSelectionListener
	{		
		// Handle list selection event
		public void valueChanged(ListSelectionEvent evt)
		{
			//db_listbox1 = null;
			db_selected1 = dbList1.getSelectedValues();
			db_listbox1 = new String[db_selected1.length];	
			
			for (int i = 0; i < db_listbox1.length; i++)
			{
				db_listbox1[i] = db_selected1[i].toString();
				System.out.println(db_listbox1[i] + " dbList1_listener");
			}	
		}
	}
	
	// JList listener for dblist2
	public class dbList2_listener implements ListSelectionListener
	{
		// Handle list selection event
		public void valueChanged(ListSelectionEvent evt)
		{
			db_selected2 = dbList2.getSelectedValues();
			db_listbox2 = new String[db_selected2.length];	
			for (int i = 0; i < db_listbox2.length; i++)
			{
				db_listbox2[i] = db_selected2[i].toString();
				System.out.println(db_listbox2[i] + " dbList2_listener");
				
			}		
		}
	}
	
	// Combine two string arrays and get the selected databases from db_listbox1, db_listbox2
	public String[] combineStringArrays(String[] db_listbox1, String[] db_listbox2)
	{
		if(db_listbox1 == null)
		{
			total = db_listbox2.length;
		}
		else if(db_listbox2 == null)
		{
			total = db_listbox1.length;
		}
		else  
		{
			total = db_listbox1.length + db_listbox2.length;
		}		
		strings = new String[total];	
		if(db_listbox1 != null)
		{
			for (int i = 0; i < db_listbox1.length; i++)
			{
				if(db_listbox2 != null){
					//strings[i] = db_listbox1[i];
					strings[db_listbox2.length + i] = db_listbox1[i];
				}
				else{
					strings[i] = db_listbox1[i];
				}
			}
		}	
		if (db_listbox2 != null)
		{
			for ( int i = 0; i < db_listbox2.length; i++)
			{
				if(db_listbox1 != null)
				{
					strings[db_listbox1.length + i] = db_listbox2[i];
				}
				else 
				{
					strings[i] = db_listbox2[i];
				}
			}
		}
				return strings;
	 }
	  
	 // Create table to show metadata
	 public void make_meta()
	 {  	     		      
		 	table1 = new JTable();  		
		    String[] tableColumnNames = {"Series Name", "Series Description", "Min Date", "Max Date","Tiq Class", "Tiq Type","Frequency","Observed", "Basis"};
 			colName = new Vector();   
 			for (int i = 0; i < chosens.length + 1; i ++)
 			{		
 				colName.add("Metadata Value" + " " + i);
 			}		
 			int numRows = tableColumnNames.length;   
 			//int numColumns = ;    
 			int numColumns = chosens.length + 1;
 			
 			tblModel = new DefaultTableModel(numRows,numColumns); 
 			table1.setModel(tblModel);  
 			tblModel.setColumnIdentifiers(colName);
 			table1.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);      
 			//table1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);		
 			for ( int i = 0; i < chosens.length + 1; i++)
 			{
 				TableColumn column = table1.getColumnModel().getColumn(i);
 				column.setMinWidth(20);
 			}		 
 			for(int i=0; i < tableColumnNames.length; i++)
 			{
 				tblModel.setValueAt(tableColumnNames[i],i,0);
 			}								
 			for(int i = 0; i < metatab.length ; i++ )
 			{			
 				for (int j = 0; j < chosens.length; j++)
 				{
 					tblModel.setValueAt(metatab[i][j], i, j+1);
 				}		
 			}	    
 			table1.revalidate();
 			table1.setPreferredScrollableViewportSize(new Dimension(2000, 400));	
 			table1.setFillsViewportHeight(true);
 			table1.setPreferredSize(new Dimension(1000, 400));	

			scroll1 = new JScrollPane(table1);
			//scroll1.setPreferredSize(new Dimension(600,600));	
			scroll1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			scroll1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			//scroll1.getHorizontalScrollBar().setUnitIncrement(10);
			//scroll1.getVerticalScrollBar().setUnitIncrement(10);
			scroll1.setPreferredSize(new Dimension((table1.getSize())));				
			rightPanel.setBackground(Color.white);
			rightPanel.add(scroll1, BorderLayout.CENTER);
			SwingUtilities.updateComponentTreeUI(rightPanel);	 	
	 }
	  
	 //comment out when using accessPoint 12.03
	// Use accessPoint connection to retrieve series from the chosen databases in db combobox, not in use
	 public void getSerVal() throws ConnectionFailedChkException
	 {
		 		String w;
				w = null;
				w = parser.mydb[0];
				System.out.println(w + "mydb");
				combineStringArrays(db_listbox1, db_listbox2);
				
				spec = "?";

				if(strings.length == 1)
				{
					db_spec = strings[0].toUpperCase();
					//db_prefix_1 = strings[0].toUpperCase();
					//apstr3 = "ppi:remeval($matchlist2(\""+db_spec+"\",\""+spec+"\")))";
					apstr3 = w+":remeval($matchlist2(\""+db_spec+"\",\""+spec+"\")))";
					System.out.println(db_spec + "1");
					try {
						nameSeries = getData(apstr3);
					} catch (Exception e) {
						e.printStackTrace();
					}
					try {
						printData(nameSeries);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				else if (strings.length == 2)
				{
					db_spec = strings[0].toUpperCase();
					//apstr3 = "ppi:remeval($matchlist2(\""+db_spec+"\",\""+spec+"\")))";
					apstr3 = w+":remeval($matchlist2(\""+db_spec+"\",\""+spec+"\")))";
					System.out.println(db_spec + "1");
					db_spec = strings[1].toUpperCase();
					//db_prefix_2 = strings[1];
					//apstr4 = "ppi:remeval($matchlist2(\""+db_spec+"\",\""+spec+"\")))";
					apstr4 = w+":remeval($matchlist2(\""+db_spec+"\",\""+spec+"\")))";
					System.out.println(db_spec + "2");
					try {
						nameSeries = getData(apstr3);
						nameSeries1 = getData(apstr4);
					} catch (Exception e) {
						e.printStackTrace();
					}
					try {
						printData(nameSeries);
						printData1(nameSeries1);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				disp_wildser();
	 }
	 
	public void printData(TiqObject[] data) throws IOException  
	{  	 	
			ObservationList ol = null;               
			ol = data[0].getObservations();
			System.out.println(data[0] + " printData");
			tiqVal1 = ol.getValues().getStringArray();  
			totTiqVal = new String[tiqVal1.length];	
			for (int i = 0; i < tiqVal1.length; i++)
			{
				totTiqVal[i] = tiqVal1[i];
			}		 	
			
	}// end printData
	
	// printData1
	public void printData1(TiqObject[] data) throws IOException
	{
		if(strings.length == 1){
			db_prefix_1 = strings[0];
		}
		else if(strings.length == 2){
			db_prefix_1 = strings[0];
			db_prefix_2 = strings[1];
		}
		ObservationList ol = null;
		ol = data[0].getObservations();
		tiqVal2 = ol.getValues().getStringArray();
		//totTiqVal = new String[tiqVal1.length + tiqVal2.length];
		totTiqVal1 = new String[tiqVal1.length + tiqVal2.length];	
		if(strings.length == 1){
			for ( int i = 0; i < tiqVal1.length; i++)
			{
				//totTiqVal1[i] = tiqVal1[i];
				totTiqVal1[i] = db_prefix_1 + "'" + tiqVal1[i];
			}
		}
		else if(strings.length == 2){
			for ( int i = 0; i < tiqVal1.length; i++)
			{
				//totTiqVal1[i] = tiqVal1[i];
				totTiqVal1[i] = db_prefix_1 + "'" + tiqVal1[i];
			}
			for(int i = 0; i < tiqVal2.length; i++)
			{	
				//totTiqVal1[tiqVal1.length + i] = tiqVal2[i];
				totTiqVal1[tiqVal1.length + i] = db_prefix_2 + "'" + tiqVal2[i];
			}
		}
	}
	  
	// Listener for clear button 
	public class clear_listener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			tabbedPanel1.removeAll();
			tabbedPanel1.setBackground(Color.WHITE);
			SwingUtilities.updateComponentTreeUI(tabbedPanel1);
		}
	}
	
	 // Key press for wildcard search
	 public class key_listener_text extends JFrame implements KeyListener
	 {
		 public void keyPressed(KeyEvent e)
		 { 
			 tabbedPane.setSelectedIndex(0);	 
			 if(e.getKeyCode() == KeyEvent.VK_ENTER)
			 {	 			 
				 try {		
					get_wildcard();
				} catch (IOException e1) {
					e1.printStackTrace();
				}		
			 }
			 //disp_wildser();
		 }
		 
		 @Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}	
	 }
	 
	 //  Key press for wildcard search of descriptions
	 public class key_listener_text1 extends JFrame implements KeyListener
	 {
		 	public void keyPressed(KeyEvent e)
		 	{		 		
		 		tabbedPane.setSelectedIndex(0);	
		 		if (e.getKeyCode()== KeyEvent.VK_ENTER)
		 		{
		 			try {				
		 				get_wilddesc();
		 			} catch (IOException e2){ 				
		 				e2.printStackTrace();
		 			}		 				 			
		 			//disp_wildser();			 			
		 		}
		 	}
		 	 @Override
				public void keyReleased(KeyEvent e) {
					// TODO Auto-generated method stub	
				}

				@Override
				public void keyTyped(KeyEvent e) {
					// TODO Auto-generated method stub	
				}	
	 }
	 
	 public void get_wildcard() throws IOException
	 { 
		  wildText = s_text.getText();	 		 
		  combineStringArrays(db_listbox1, db_listbox2);
		  if (strings.length == 1)
		  {
			  db_spec = strings[0].toUpperCase();
			  System.out.println(db_spec + "get_wildcard");
			  apstr = db_spec+":remeval($matchlist(\""+db_spec+"\",\""+wildText+"\"))";	  
			  TiqObject[] apSer = null;
			  try {
				  apSer = getData(apstr);
			  } catch (Exception e) {
				  e.printStackTrace();
			  }
			  printData(apSer);
		  }
		  else if (strings.length == 2)
		  {
			  db_spec = strings[0].toUpperCase();
			  apstr = db_spec+":remeval($matchlist(\""+db_spec+"\",\""+wildText+"\"))";	 		
			  db_spec = strings[1].toUpperCase();
			  apstr1 = db_spec+":remeval($matchlist(\""+db_spec+"\",\""+wildText+"\"))";
			  
			  TiqObject[] apSer = null;
			  TiqObject[] apSer1 = null;
			  try{
				  apSer = getData(apstr);
				  apSer1 = getData(apstr1);
			  }
			  catch(Exception e)
			  {
				  e.printStackTrace();
			  }
			  printData(apSer);
			  printData1(apSer1);
		  }
		     disp_wildser();
		 
	 }// end get_wildcard
	 
	 // Retrieve Fame case series via accessPoint URL
	    public TiqObject[] getData(String apstr) throws Exception
	    {
	    	// Set up the parameters 	
	    	cmdRequest = null;
	    	cmdRequest = "&cmd=get_objects" +
	    					"&app=getfamedata" +
	    					"&object=" + apstr;            
	    	
	    	getUri();		    	
	    	return apDesc;
	    }
	    
	   // Retrieve Fame TiqObject(time series) via accessPoint
	    public TiqObject[] getData1(String[] apArray) throws Exception
	    {	
	    	// Set up the parameters 	
	    	cmdRequest = null;
	    	cmdRequest = "&cmd=get_objects" +
	    					"&app=getfamedata" + 
	    					getApstr();
	    					                    				  
	    	getUri();    	
	    	return apDesc;
	    }
	    
	 // Retrieve Fame TiqObject(time series) via accessPoint(frequency, startDate, endDate)
	    public TiqObject[] getData2(String[] mystring, String apStart, String apEnd) throws Exception
	    {	
	    	// Set up the parameters 	
	    	cmdRequest = null;
	    	cmdRequest = "&cmd=get_objects" +
	    					"&app=getfamedata" + 
	    					"&object=" + getApFuncStr() +
	    					"&fromdate=" + apStart +
	    					"&todate=" + apEnd;
	    					
	    	getUri();  	
	    	return apDesc;	
	    }
	    
	    //new 26.04.2012
	    public TiqObject[] getData7(String mystring, String apStart, String apEnd) throws Exception
	    {	
	    	// Set up the parameters 	
	    	cmdRequest = null;
	    	cmdRequest = "&cmd=get_objects" +
	    					"&app=getfamedata" + 
	    					"&object=" + getApFunc2Str() +
	    					"&fromdate=" + apStart +
	    					"&todate=" + apEnd;    					
	    	getUri();    	
	    	return apDesc;    	
	    }
	    
	    public TiqObject[] getData8(String mystring, String apStart, String apEnd) throws Exception
	    {
	    	// Set up the parameters 	
	    	cmdRequest = null;
	    	cmdRequest = "&cmd=get_objects" +
	    					"&app=getfamedata" + 
	    					"&object=" + getApFuncStr() +
	    					"&fromdate=" + apStart +
	    					"&todate=" + apEnd;    					
	    	getUri();   	
	    	return apDesc;   	
	    }
	    
	    public TiqObject[] getData3(String[] apArray, String apStart, String apEnd, String freq_val) throws Exception
	    {	
	    	// Set up the parameters 	
	    	cmdRequest = null;
	    	cmdRequest = "&cmd=get_objects" +
	    					"&app=getfamedata" + 
	    					 getApstr()+
	    					 "&fromdate="+apStart +
		    				 "&todate="+apEnd +
		    				 "&scaling=" + freq_val;   					   									                    				  
	    	getUri();    	
	    	return apDesc;    	
	    }
	    
	    public TiqObject[] getData4(String[] apArray, String apStart, String apEnd) throws Exception
	    {	
	    	// Set up the parameters 	
	    	cmdRequest = null;
	    	cmdRequest = "&cmd=get_objects" +
	    					"&app=getfamedata" + 
	    					getApstr()+
	    					"&fromdate="+apStart +
	    					"&todate="+apEnd;    					  	
	    	getUri();    				                    				  
	    	return apDesc;   	
	    }
	    
	    public TiqObject[] getData5(String[] mystring, String apStart, String apEnd, String freq_val) throws Exception
	    {    	
	    	// Set up the parameters 	
	    	cmdRequest = null;
	    	cmdRequest = "&cmd=get_objects" +
	    					"&app=getfamedata" + 
	    					"&object=" + getApFuncStr() +
	    					"&fromdate=" + apStart +
	    					"&todate=" + apEnd +
	    					"&scaling=" + freq_val;  					
	    	getUri();  	    	
	    	return apDesc;    	
	    }
	    
	    public TiqObject[] getData6(String mystring) throws Exception
	    {
	    	cmdRequest = null;
	    	cmdRequest = "&cmd=get_objects" +
			"&app=getfamedata" + 
			getObserved(); 	
			getUri();    	
			return apDesc;  	
	    }
	    
	    // Get URI
	    public void getUri()
	    {	    	
	    	if(hostname_1.equals("w03046.ssb.no"))
	    	{
	    		uri = "http://w03046.ssb.no:8080/accessPoint/accessPoint";
	    		
	    	}
	    	else if(hostname_1.equals("ktli-jboss-t01"))
	    	{
	    		uri = "http://ktli-jboss-t01:8080/accessPoint/accessPoint";
	    		
	    	}
	    	else if(hostname_1.equals("kpli-jboss-as01"))
	    	{
	    		uri = "http://kpli-jboss-as01:8080//accessPoint/accessPoint";
	    		
	    	}
	    	else if(hostname_1.equals("kpli-jboss-as02"))
	    	{
	    		uri = "http://kpli-jboss-as02:8080//accessPoint/accessPoint";
	    	
	    	}    	  	
	    	//String uri = "http://w03046.ssb.no:8080/accessPoint/accessPoint";	   	
	    	//String uri = page.getProtocol() + "://" + page.getHost() +
	        	//":" + page.getPort() + "/" + accessPointContext + "/" + accessPointServlet;
	            	//System.err.println("*** new URI = " + uri + " ***");    	
	       // Helper class from the accessPoint programmers samples pack

	    	apClient = new AccessPointClient(uri);
	    	userName = System.getProperty("user.name");
	    	userx = userName;	    	
	    	try {		 		
	    		apClient.login(userx, "");
	    	} catch (Exception e) {
				e.printStackTrace();
			}
	    	apDesc = null;	    		
	    	try {		
	    		apDesc = apClient.issueRequest(cmdRequest, true);
	    	} catch (OptionalDataException e) {
				e.printStackTrace();
	    	} catch (StreamCorruptedException e) {
				e.printStackTrace();
	    	} catch (MalformedURLException e) {
				e.printStackTrace();
	    	} catch (APChkException e) {	
				e.printStackTrace();
	    	} catch (IOException e) {
				e.printStackTrace();
			}
	    }
	    
	    //Construct String array fra chosens1 to to be called by getData1, new 05.03
	    public String getApstr()
		{ 
			String apStr = null;	 	 
			apStr = "&object=" + db_spec + "'" + chosens1[0].trim();
			for (int i = 1; i < chosens1.length; i++)
			{
				apStr += "&object=" + db_spec + "'" + chosens1[i].trim();		
			}		 
			 return apStr; 
		 }   
	 
	    public String getApFuncStr()
	    {	
	    	String apFuncStr = null;    	
	    	//apFuncStr = "&object=" + "ppi:remeval(Annpct(" + "PPI'" + chosens1[0].trim() + ")";   	
	    	//apFuncStr = "&object=" + "ppi:remeval(" + func_val + "(" + db_spec + "'" + chosens1[0].trim() + ")";
	    	apFuncStr = "&object=" + db_spec + ":remeval(" + func_val + "(" + db_spec + "'" + chosens1[0].trim() + ")";
	    	
	    	for (int i = 1; i < chosens1.length; i++)
	    	{
	    		//apFuncStr += "&object=" + "ppi:remeval(" + func_val + "(" + db_spec + "'" + chosens1[i].trim() + ")";
	    		apFuncStr += "&object=" + db_spec + ":remeval(" + func_val + "(" + db_spec + "'" + chosens1[i].trim() + ")";
	    	}   	
	    	return apFuncStr;
	    }
	    
	    //new 26.04.2012
	    public String getApFunc2Str()
	    {
	    	String apFunc2Str = null;	
	    	//apFuncStr = "&object=" + "ppi:remeval(Annpct(" + "PPI'" + chosens1[0].trim() + ")";
	    	
	    	//apFunc2Str = "&object=" + "ppi:remeval(" + func_val1 + "(" + db_spec + "'" + chosens1[0].trim() + ")";
	    	apFunc2Str = "&object=" + db_spec + ":remeval(" + func_val1 + "(" + db_spec + "'" + chosens1[0].trim() + ")";
	    	
	    	for (int i = 1; i < chosens1.length; i++)
	    	{
	    		//apFunc2Str += "&object=" + "ppi:remeval(" + func_val1 + "(" + db_spec + "'" + chosens1[i].trim() + ")";
	    		apFunc2Str += "&object=" + db_spec + ":remeval(" + func_val1 + "(" + db_spec + "'" + chosens1[i].trim() + ")";
	    	}    	
	    	return apFunc2Str;
	    }
	        
	    public String getObserved()
	    {  	
	    	String apObserved = null;
	    	//apObserved = "&object=" + "ppi:remeval(observed(" + db_spec + "'" + chosens1[0].trim() + ")"; 
	    	apObserved = "&object=" + db_spec + ":remeval(observed(" + db_spec + "'" + chosens1[0].trim() + ")"; 
	    	for (int i = 1; i < chosens.length; i++)
	    	{
	    		//apObserved += "&object=" + "ppi:remeval(observed(" + db_spec + "'" + chosens1[i].trim() + ")";
	    		apObserved += "&object=" + db_spec + ":remeval(observed(" + db_spec + "'" + chosens1[i].trim() + ")";
	    	}	    	
	    	return apObserved; 	
	    }
	    
	    public void get_wilddesc() throws IOException
	    {
	    	wildDes = desc_text.getText();			
	    	combineStringArrays(db_listbox1, db_listbox2); 
	    	
	    	if(strings.length == 1)
	    	{
	    		db_spec = strings[0].toUpperCase();		 
	    		//nameDesc = conn.remEval("$matchlist1(\""+db_spec+"\", upper(\""+wildDes+ "\"))");
	    		//apstr = "ppi:remeval($matchlist1(\""+db_spec+"\",upper(\""+wildDes+"\")))";	
	    		apstr = "ORDRE_07:remeval($matchlist1(\""+db_spec+"\",upper(\""+wildDes+"\")))";
	    		TiqObject[] apDesc = null;
	    		try {
	    			apDesc = getData(apstr);
	    		} catch (Exception e) {
	    			e.printStackTrace();
	    		}
	    		printData(apDesc);
	    	}
	    	else if(strings.length == 2)
	    	{
	    		db_spec = strings[0].toUpperCase();
	    		//nameDesc = conn.remEval("$matchlist1(\""+db_spec+"\", upper(\""+wildDes+ "\"))");
	    		//apstr = "ppi:remeval($matchlist1(\""+db_spec+"\",upper(\""+wildDes+"\")))";
	    		apstr = db_spec + ":remeval($matchlist1(\""+db_spec+"\",upper(\""+wildDes+"\")))";
	    		//printData(nameDesc);
	    		db_spec = strings[1].toUpperCase();
	    		//nameDesc1 = conn.remEval("$matchlist1(\""+db_spec+"\", upper(\""+wildDes+ "\"))");
	    		//apstr1 = "ppi:remeval($matchlist1(\""+db_spec+"\",upper(\""+wildDes+"\")))";
	    		apstr1 = db_spec + ":remeval($matchlist1(\""+db_spec+"\",upper(\""+wildDes+"\")))";
	    		//printData1(nameDesc1);		
	    		TiqObject[] apDesc = null;
	    		TiqObject[] apDesc1 = null;
	    		try {
	    			apDesc = getData(apstr);
	    			apDesc1 = getData(apstr1);
	    		} catch (Exception e) {
	    			e.printStackTrace();
	    		}
	    		printData(apDesc);
	    		printData1(apDesc1);
	    	}		 	
		 disp_wildser();
		 		
	 }// end get_wilddesc
	 
	 // Display series / description that match wildcard(Series /Description button)
	 public void disp_wildser()
	 {				 
		if(strings.length == 1){
			db_prefix_1 = strings[0].toUpperCase();	 
		}
		else if(strings.length == 2){
			db_prefix_1 = strings[0].toUpperCase();
			db_prefix_2 = strings[1].toUpperCase();
		}
		 selectsource = "table";
		 	nTabRow = 0;		
		 	tabbedPanel1.removeAll();
			table3 = new JTable();
			//nTabRow = tiqWildSer.length;	
			// if there are selected rows in the table, add the selected rows and new serach to String[] selectedAndVAlues
			if(j > 0 && removedRows == false)
			{
				//nTabRow = tiqValues.length + j;
				if(strings.length == 1)
				{
					nTabRow = totTiqVal.length + j;
				}
				else if (strings.length == 2)
				{
					nTabRow = totTiqVal1.length + j;
				}
			}
			else if(j > 0 && removedRows == true)
			{
				nTabRow = j;
			}
			else 
			{
				//nTabRow = tiqValues.length;
				if(strings.length == 1)
				{
					nTabRow = totTiqVal.length;
					//selectedAndValues = new String[nTabRow];
				}
				else if(strings.length == 2)
				{
					nTabRow = totTiqVal1.length;
					//selectedAndValues = new String[nTabRow];
				}
			}	
			selectedAndValues = new String[nTabRow];
				
			if (j > 0 && removedRows == false)
			{
				
				for ( int i = 0; i < j; i++) 
				{				
					//selectedAndValues[i] = chosens[i];	
					selectedAndValues[i] = db_prefix_1 + "'" + chosens[i];
					System.out.println(selectedAndValues[i]+ "disp_wildser");
					
				}				
				
				if(strings.length == 1)
				{
					for(int i = 0; i < totTiqVal.length; i++)
					{
						//selectedAndValues[i+j] = db_prefix_1 + "'" + totTiqVal[i];
						selectedAndValues[i+j] = totTiqVal[i];
						//selectedAndValues[i] = db_prefix_1 + "'" + totTiqVal[i];
					}	
				}
				else if(strings.length == 2)
				{
					//for(int i = 0; i < totTiqVal1.length; i++)
					for(int i = 0; i < totTiqVal1.length; i++)
					{
						selectedAndValues[i+j] = totTiqVal1[i];
						//selectedAndValues[i+j+totTiqVal.length] = db_prefix_2 + "'"+ totTiqVal1[i];
					}
				}	             
			}
			else if(j > 0 && removedRows == true)
			{
				for(int i = 0; i < j; i++)
				{
					//selectedAndValues[i] = chosens[i];
					selectedAndValues[i] = db_prefix_1 + "'"+ chosens[i];
				}
			}
			else 
			{
				//for (int i = 0; i < tiqValues.length; i++ )
				if(strings.length == 1)
				{
					for (int i = 0; i < totTiqVal.length; i++)
					{
						//selectedAndValues[i] = tiqValues[i];				
						//selectedAndValues[i] = totTiqVal[i];
						selectedAndValues[i] = db_prefix_1 + "'" + totTiqVal[i];
					}
				}
				else if(strings.length == 2)
				{
					for (int i = 0; i < totTiqVal1.length; i++)
					{
						selectedAndValues[i] = totTiqVal1[i];
					}
				}
			}
					
			//nTabCol = 2;
			nTabCol = 1;
			tblModel2 = new DefaultTableModel(nTabRow, nTabCol);
			table3.setModel(tblModel2);
			table3.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			listSelectionModel = table3.getSelectionModel();
			table3.setSelectionMode(listSelectionModel.MULTIPLE_INTERVAL_SELECTION);
	        listSelectionModel.addListSelectionListener(new SharedListSelectionHandler());
	        table3.setSelectionModel(listSelectionModel);
	        //table3.setPreferredScrollableViewportSize(new Dimension(600, 5000));
	        //table3.setFillsViewportHeight(true);
	        table3.addMouseListener(new MouseListener()
	        {
	        	public void mousePressed(MouseEvent me)
	        	{                
	        		if (me.isControlDown()){                     
	        			//System.out.println("This is working ");                 
	        		} 
	        	}

	        	@Override
	        	public void mouseClicked(MouseEvent e) {
	        		// TODO Auto-generated method stub
	        	}

	        	@Override
	        	public void mouseEntered(MouseEvent e) {
	        		// TODO Auto-generated method stub
	        	}

	        	@Override
	        	public void mouseExited(MouseEvent e) {
	        		// TODO Auto-generated method stub
	        	}

	        	@Override
	        	public void mouseReleased(MouseEvent e) {
	        		// TODO Auto-generated method stub
	        	}
	        }
	      );
	           
	        colName2 = new Vector();
			colName2.add("Series Name");
			//colName2.add("Series Description");
			//tblModel2.setColumnIdentifiers(colName2);			
			//for (int i = 0; i < tiqWildSer.length; i++)
			for ( int i  = 0 ;  i< nTabRow; i++)
			{						
				//tblModel2.setValueAt(tiqValues[i], i, 0);
				tblModel2.setValueAt(selectedAndValues[i], i, 0);			
			}
			boolean toggle = true;
			boolean extend = false;
			int row = j;
			int c = 0;
			
			if (j > 0)
			{
				toggle = false;
				extend = true;
				table3.changeSelection(row-1, c, toggle, extend);
			} //end if
			
			table3.setPreferredScrollableViewportSize(new Dimension(600, nTabRow));
			table3.setFillsViewportHeight(true);
			TableColumn col = table3.getColumnModel().getColumn(0);
			col.setMinWidth(200);
			col.setMaxWidth(1000);
			table3.revalidate();			
			scroll3 = new JScrollPane(table3);	
			scroll3.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			scroll3.setPreferredSize(new Dimension((table3.getSize())));		
			
			tabbedPanel1.setBackground(Color.WHITE);
			tabbedPanel1.add(scroll3, BorderLayout.CENTER);
			SwingUtilities.updateComponentTreeUI(tabbedPanel1);	
		}
	 
	 // Create class for SharedListSelectionHandler(Series/DEscription)
	 // Click 'Get Series Values' when series are selected
	 class SharedListSelectionHandler implements ListSelectionListener {      
		 // Select series from table 'Series and Description' that match wildcard
		 public void valueChanged(ListSelectionEvent e) 
	     {         
			 v = new Vector();
			 ListSelectionModel lsm = (ListSelectionModel)e.getSource();
			 lsm.setSelectionMode(lsm.MULTIPLE_INTERVAL_SELECTION);
			 //lsm.setSelectionMode(lsm.SINGLE_INTERVAL_SELECTION);

			 int firstIndex = e.getFirstIndex();
			 int lastIndex = e.getLastIndex();
			 boolean isAdjusting = e.getValueIsAdjusting(); 
			 // Find out which indexes are selected.
			 minIndex = lsm.getMinSelectionIndex();
			 maxIndex = lsm.getMaxSelectionIndex();      
	         //lsm.removeIndexInterval( new ToggleSelectionModel());       
			 o = null;
			 for (int i = minIndex; i <= maxIndex; i++) 
			 {
				 if (lsm.isSelectedIndex(i)) 
				 {
					 o = table3.getValueAt(i, 0).toString();  
					 v.add(o);  
					 System.out.println(v.toString() + " isSelectedIndex");
				 }
			 }
			 serNames = (String[]) v.toArray(new String[v.size()]);
			 chosens = new String[serNames.length];
			 chosens_m_px = new String[serNames.length];
			 for (int i = 0; i < serNames.length; i++)
			 {		
				 //chosens[i] contains prefix(channel name)
				 //chosens[i] = serNames[i];
				 str = serNames[i].indexOf("'");
				 chosens[i]= serNames[i].substring(str + 1).trim();
				 str_1 = serNames[i].indexOf("|");
				 chosens_m_px[i] = serNames[i].substring(0, str_1-1).trim();
				 System.out.println(chosens_m_px[i] + " Q");
				
			 }
			 	j = chosens.length;	        
	       }        		
	 }
	
	 class ToggleSelectionModel extends DefaultListSelectionModel 
	 { 
		 public void setSelectionInterval(int index0, int index1) 
		 { 
			 if (isSelectedIndex(index0)) 
			 { 
				 super.removeSelectionInterval(index0, index1); 
			 } 
			 else {  
				 super.setSelectionInterval(index0, index1); 
			 } 
		 } 
	 } 
	 
	 // Frequency listener for timescaling
	 public  class freq_listener extends JFrame implements ActionListener
	 {
		 public void actionPerformed(ActionEvent e)
		 { 
			  t_scale = true;
		 	  cb1 = (JComboBox)e.getSource();
		      freq_val = (String)cb1.getSelectedItem();
		      //get_frequency();
		      if (freq_val == "Original")
		      {
		    	  t_scale = false;
		      }
		 }
	 }
	 
	 // Timescaling
	 public void get_ts()
	 { 
		 //add 28.09.2011
		 //chartView = new TiqObject[chosens.length * 2];
		 //t_scale = true;
		 tprop = new Properties();
		 tprop.setProperty("forceDefaultObserved", "true");
		 tprop.setProperty("defaultObserved", "averaged");  
		 ts = null;	 
		 if(freq_val.equals("MONTHLY"))
		 { 		
			 chartView = new TiqObject[chosens.length * 2];
			 ts = new TimeScaling((SimpleCalendar) RegularCalendar.MONTHLY, tprop);	 
			 try {		
				 for(int i = 0; i < tiqObject.length; i++)
				 {		 
					 tiqObject[i] = tiqObject[i].getTiqObjectCopy(ts);
					 chartView[i] = tiqObject[i];
					// chartView[i+1] = tiqObjectBase[i];
					// tiqviewsdata = new TiqViewsData("", chartView);
				 }		 
				 for (int i = 0; i < chosens.length; i++)
				 {
					 chartView[i + chosens.length] = tiqObjectBase[i];
				 }				 
				 tiqviewsdata = new TiqViewsData("", chartView);	 
			 } catch (RangeTooLargeChkException e) {			
				e.printStackTrace();
			}
		 }
		 else if(freq_val.equals("QUARTERLY"))
		 {
			 chartView = new TiqObject[chosens.length * 2];
			 ts = new TimeScaling((SimpleCalendar) RegularCalendar.QUARTERLY, tprop);	 
			 try {			
				 for (int i = 0; i < tiqObject.length; i++)
				 { 
					 tiqObject[i] = tiqObject[i].getTiqObjectCopy(ts);
					 chartView[i] = tiqObject[i];
					// chartView[i+1] = tiqObjectBase[i];
					// tiqviewsdata = new TiqViewsData("", chartView);
				 }
				 for(int i = 0; i < chosens.length; i++ )
				 {
					 chartView[i + chosens.length] = tiqObjectBase[i];
				 }
				 tiqviewsdata = new TiqViewsData("", chartView);
			 } catch (RangeTooLargeChkException e) {
				e.printStackTrace();
			}
		 }
		 else if(freq_val.equals("ANNUAL"))
		 {	 
			chartView = new TiqObject[chosens.length * 2]; 
			ts = new TimeScaling((SimpleCalendar) RegularCalendar.ANNUAL, tprop);	
			try {		
				 
				for(int i = 0; i < tiqObject.length; i++)
				{		
					 tiqObject[i] = tiqObject[i].getTiqObjectCopy(ts);
					 chartView[i] = tiqObject[i];
					 //chartView[i+1] = tiqObjectBase[i];
					// chartView[i+1] = tiqObject[i];
					 //tiqviewsdata = new TiqViewsData("", chartView);
				 } 
				 for (int i = 0; i < chosens.length; i++)
				 {
					 //chartView[i + chosens.length + chosens.length] = tiqObjectBase[i];
					 chartView[i + chosens.length] = tiqObjectBase[i];
				 }	 
				 tiqviewsdata = new TiqViewsData("", chartView);
				 // tiqviewsdata = new TiqViewsData("", timescaleChartView);
				 
			 } catch (RangeTooLargeChkException e) {
				e.printStackTrace();
			} 
		 }
	 
	 }// end get_ts
	
	 public class export_listener extends JFrame implements ActionListener
	 {
		 public void actionPerformed(ActionEvent ev)
		 { 
			//The file name, excel_pop.xls is passed to File object
             File file = new File(filename1);
               FileOutputStream fout1 = null;
               if (!file.exists()) {
                 try {
                   //File communicates with excel_pop.xls through FileOutPutStream fout1
                   //If file does not exist, create a new one
                   fout1 = new FileOutputStream(filename1);
                 }
                 catch(IOException ex) {}
               }
               else {
                    try {
                        //If file exists, open with append mode
                        fout1 = new FileOutputStream(filename1 , false);
                    }
                    catch(FileNotFoundException er) {}
               }//end else
               
               s2 = "Down";
               s3 = "Heading";
               s4 = "Normal";
               s5 = "FAMEDATE";
               cellRef1 = "D1";
               cellRef2 = "F1";
               
               dispSerToJtext();
               pop2 = new String[chosens1.length];
                           
               fdate = "=FAMEData( \"" + s5 + "\" ; " + cellRef1 + " ; " + cellRef2 + " ; 0 ; \"" + s1 + "\" ; \"" + s2 + "\" ; \"" + s3+ "\" ; \"" + s4 + "\" ) " ;
               for(int i = 0; i < chosens1.length; i++)
               {        	            		   
                    if(func_val == null)  
                    {
                    	pop1 = "=FAMEData( \"" + chosens1[i] + "\" ;  " + cellRef1 + " ; " + cellRef2 + " ; 0 ; \"" + s1 + "\" ; \"" + s2 + "\" ; \"" + s3+ "\" ; \"" + s4 + "\" ) " ;
                    	pop2[i] = pop1;
                    }
                    else if(func_val == "Annpct")
                    {
                    	pop1 = "=FAMEData( \""  + "Annpct" + "(" + chosens1[i] + ")" + "\" ;  " + cellRef1 + " ; " + cellRef2 + " ; 0 ; \"" + s1 + "\" ; \"" + s2 + "\" ; \"" + s3+ "\" ; \"" + s4 + "\" ) " ;
                    	pop2[i] = pop1;
                    }
                    else if(func_val == "Csum")
                    {
                    	pop1 = "=FAMEData( \""  + "Csum" + "(" + chosens1[i] + ")" + "\" ;  " + cellRef1 + " ; " + cellRef2 + " ; 0 ; \"" + s1 + "\" ; \"" + s2 + "\" ; \"" + s3+ "\" ; \"" + s4 + "\" ) " ;
                    	pop2[i] = pop1;
                    }
                    else if(func_val == "Diff")
                    {
                    	pop1 = "=FAMEData( \""  + "Diff" + "(" + chosens1[i] + ")" + "\" ;  " + cellRef1 + " ; " + cellRef2 + " ; 0 ; \"" + s1 + "\" ; \"" + s2 + "\" ; \"" + s3+ "\" ; \"" + s4 + "\" ) " ;
                    	pop2[i] = pop1;
                    }
                    else if(func_val == "Mstddev")
                    {
                    	pop1 = "=FAMEData( \""  + "Mstddev" + "(" + chosens1[i] + ")" + "\" ;  " + cellRef1 + " ; " + cellRef2 + " ; 0 ; \"" + s1 + "\" ; \"" + s2 + "\" ; \"" + s3+ "\" ; \"" + s4 + "\" ) " ;
                    	pop2[i] = pop1;
                    }
                    else if(func_val == "Pct")
                    {
                    	pop1 = "=FAMEData( \""  + "Pct" + "(" + chosens1[i] + ")" + "\" ;  " + cellRef1 + " ; " + cellRef2 + " ; 0 ; \"" + s1 + "\" ; \"" + s2 + "\" ; \"" + s3+ "\" ; \"" + s4 + "\" ) " ;
                    	pop2[i] = pop1;
                    }
                    else if(func_val == "Ytydiff")
                    {
                    	pop1 = "=FAMEData( \""  + "Ytydiff" + "(" + chosens1[i] + ")" + "\" ;  " + cellRef1 + " ; " + cellRef2 + " ; 0 ; \"" + s1 + "\" ; \"" + s2 + "\" ; \"" + s3+ "\" ; \"" + s4 + "\" ) " ;
                    	pop2[i] = pop1;
                    }
                    else if(func_val == "Ytypct")
                    {
                    	pop1 = "=FAMEData( \""  + "Ytypct" + "(" + chosens1[i] + ")" + "\" ;  " + cellRef1 + " ; " + cellRef2 + " ; 0 ; \"" + s1 + "\" ; \"" + s2 + "\" ; \"" + s3+ "\" ; \"" + s4 + "\" ) " ;
                    	pop2[i] = pop1;
                    }                 
               }           
               excelOutput = new PrintStream(fout1);      
               excelOutput = new PrintStream(fout1);
               excelOutput.print("Created");
               excelOutput.print("\t");     
               excelOutput.print(idag);
               excelOutput.print("\t");
               excelOutput.print("Start: ");
               excelOutput.print("\t");
               excelOutput.print(start);
               excelOutput.print("\t");
               excelOutput.print("end: ");
               excelOutput.print("\t");
               excelOutput.println(end);
               excelOutput.println("");                
               excelOutput.print(fdate);
         	   excelOutput.print("\t");                    	   
         	   for(int j = 0; j < pop2.length; j++)
         	   {
         		   excelOutput.print(pop2[j]);
         		   excelOutput.print("\t");
         	   }	                  
               excelOutput.close();           
		 }
	 }
	 
	 public class sdate_listener extends JFrame implements ActionListener
	 {
		 public void actionPerformed(ActionEvent ev)
		 {
			 cb1 = (JComboBox)ev.getSource();
			 start = cb1.getSelectedItem().toString();
		 }
	 }
	 
	 public class edate_listener extends JFrame implements ActionListener
	 {
		 public void actionPerformed(ActionEvent ev)
		 {
			 cb1 = (JComboBox)ev.getSource();
			 //end = (String)cb1.getSelectedItem();
			 end = cb1.getSelectedItem().toString();
		 }
	 }
	 
	 // create listener class for common base year combobox
	 public class byear_listener extends JFrame implements ActionListener
	 {
		 public void actionPerformed(ActionEvent ev)
		 {	 
			 func_val = "Baseyear";
			 setBaseyear = true;
			 cb1 = (JComboBox)ev.getSource();
			 byear = cb1.getSelectedItem().toString();
			 get_baseyear();		
			 setBaseyear = false;	 
		 }
	 }
	 
	 //create listner class for whats buttton
	 public class whats_b_listener extends JFrame implements ActionListener
	 { 
		 public void actionPerformed(ActionEvent ev)
		 {	
			 tabbedPane.setSelectedIndex(1);			 
			 //Removed MCADBS 16.03.2012						 
			 tabbedPanel2.removeAll();		
				Vector meta = new Vector();
				metatab = new String[9][j];			   				
				int k = 0;
				int h = 9;
				int p = 0;
				int t = 0;
				String[] description;
				description = new String[chosens.length];			
				TiqObject[] obserVal = null;
				apTiq = null;			
				chosens1 = new String[chosens.length];
				for(int i = 0; i < j; i++)
				{
					str = chosens[i].indexOf("|");
					chosens1[i]= chosens[i].substring(0, str-1).trim();
					description[i] = chosens[i].substring(str+1).trim();
				}
				//get_tiq();		
				for ( int i = 0; i < j; i++)
				{		
					try {
						apTiq = getData1(chosens1);	
					} catch (Exception e) {
						e.printStackTrace();
					}
					//TiqObject tiqDesc = conn.remEval("desc(" + chosens[i].substring(0, str-1) + ")").getTiqObjectCopy();								
					objName = chosens1[i].trim();
					startIndex = apTiq[i].getMinIndex();    
					sDate = help.dateIndexToString(startIndex);
			      	endIndex = apTiq[i].getMaxIndex(); 
			      	eDate = help.dateIndexToString(endIndex);
			      	tiqclass = apTiq[i].getTiqClass().toString();
		      		tiqtype = apTiq[i].getTiqType().toString();
		      		f = apTiq[i].getCalendar().getFrequency();
		      		tiqfreq = f.toString();      										
		      		//tiqvalue = conn.remEval("observed(" + chosens[i].substring(0, str-1) + ")").getTiqValue();							
		      		//tiqobserved = tiqvalue.toString()
		      		//String obser ="ppi:remeval(observed(" + chosens1[i].trim() + ")";
		      		String obser = db_spec + ":remeval(observed(" + chosens1[i].trim() + ")";
		      		try {
		      			obserVal = getData6(obser);
		      		} catch (Exception e) {
		      			e.printStackTrace();
		      		}      		
					tiqBasis = apTiq[i].getCalendar().toBasis();	  
		      		basis = tiqBasis.toString();		      		
		      		meta.addElement(objName);
		        	//meta.addElement(tiqDesc);
		      		meta.addElement(description[i]);
		          	meta.addElement(sDate);
		          	meta.addElement(eDate);	      
		          	meta.addElement(tiqclass);
		          	meta.addElement(tiqtype);
		          	meta.addElement(tiqfreq);
		          	//meta.addElement(tiqobserved);
		          	meta.addElement(obserVal[i].toString());
		          	meta.addElement(basis);		      								
		          	for (k = 0 ; k < h; k++)
		          	{
		          		metatab[k][p] = (String) meta.get(k+t);	
		          	}
		          	p = p + 1;
		          	t = t + 9;			
				}
				make_meta(); 		
				tabbedPanel2.setBackground(Color.WHITE);
				tabbedPanel2.add(scroll1, BorderLayout.CENTER);
				SwingUtilities.updateComponentTreeUI(tabbedPanel2);		
		 } 
	 }
	 
	 //create listener class for select* button
	 public class select_b_listener extends JFrame implements ActionListener
	 { 
		 public void actionPerformed(ActionEvent ev)
		 {
			 table3.selectAll();
		 }
	 }
	 
	 // creat listener class for unselect* button
	 public class unselect_b_listener extends JFrame implements ActionListener
	 {
		 public void actionPerformed(ActionEvent ev)
		 {
			 table3.clearSelection();
		 }
	 }
	 
	 // create listener class for graph button
	 public class graph_b_listener extends JFrame implements ActionListener
	 {
		public void actionPerformed(ActionEvent ev)
		{			 
			 tabbedPane.setSelectedIndex(3);
			 oneChart = "aChart";
			 dispSerToJtext();			 
			 if(chartType == "areaChart")
			 {				 	
					ShowAreaSeries();
					tabbedPanel4.removeAll();
					tabbedPanel4.setBackground(Color.WHITE);
					tabbedPanel4.add(areaPanel, BorderLayout.CENTER);
					SwingUtilities.updateComponentTreeUI(tabbedPanel4);
			 }
			 else if (chartType == "twoBarChart")
			 {				 	
					ShowTwoBarSeries();
					tabbedPanel4.removeAll();
					tabbedPanel4.setBackground(Color.WHITE);
					tabbedPanel4.add(twoBarPanel, BorderLayout.CENTER);
					SwingUtilities.updateComponentTreeUI(tabbedPanel4);	
			 }
			 else if (chartType == "lineChart")
			 { 	
					ShowLineSeries();
					tabbedPanel4.removeAll();
					tabbedPanel4.setBackground(Color.WHITE);
					tabbedPanel4.add(lineChartPanel, BorderLayout.CENTER);
					SwingUtilities.updateComponentTreeUI(tabbedPanel4);
			 }
			 else if ( chartType == "stackedBarChart")
			 { 				 	
					ShowStackedBarSeries();
					/*rightPanel.removeAll();
					rightPanel.add(stackedBarPanel, BorderLayout.CENTER);
					SwingUtilities.updateComponentTreeUI(rightPanel);*/
					tabbedPanel4.removeAll();
					tabbedPanel4.setBackground(Color.WHITE);
					tabbedPanel4.add(stackedBarPanel, BorderLayout.CENTER);
					SwingUtilities.updateComponentTreeUI(tabbedPanel4);
			 } 		 
		}
	 
	 }// end graph_b_listener
	 
	 //create listener class for multigraph
	 public class multigraph_b_listener extends JFrame implements ActionListener
	 { 
		 public void actionPerformed(ActionEvent ev)
		 {	
			 	 tabbedPane.setSelectedIndex(4);
			 	 oneChart = "mChart";
			 	 dispSerToJtext();
			 	 //m_graph = new Boolean(true);
			 	 //m_graph = true;	 
				 //rightPanel.removeAll();
			 	 tabbedPanel5.removeAll();
				 get_funcChart();
				 ShowMultiSeries();
				 //rightPanel.add(multiChartPanel, BorderLayout.CENTER);
				 //SwingUtilities.updateComponentTreeUI(rightPanel);
				 tabbedPanel5.setBackground(Color.WHITE);
				 tabbedPanel5.add(multiChartPanel, BorderLayout.CENTER);
				 SwingUtilities.updateComponentTreeUI(tabbedPanel5);	
		 }
	 }
	 
	 // create listener class for multi-function graph
	 public class multifuncgraph_b_listener extends JFrame implements ActionListener
	 {
		 public void actionPerformed(ActionEvent ev)
		 {		 
			 //chartType = "twoFuncChart"; 
			 tabbedPane.setSelectedIndex(5);
			 dispSerToJtext();				 
			 if(chartType == "areaChart")
			 {
				 TwoFuncSeries();
				 tabbedPanel6.removeAll();
				 tabbedPanel6.setBackground(Color.WHITE);
				 tabbedPanel6.add(twoFuncChartPanel, BorderLayout.CENTER);
				 SwingUtilities.updateComponentTreeUI(tabbedPanel6);
			 }
			 else if(chartType == "twoBarChart")
			 {
				 TwoFuncSeries();
				 tabbedPanel6.removeAll();
				 tabbedPanel6.setBackground(Color.WHITE);
				 tabbedPanel6.add(twoFuncChartPanel, BorderLayout.CENTER);
				 SwingUtilities.updateComponentTreeUI(tabbedPanel6);
			 }
			 else if(chartType == "lineChart")
			 {
				 TwoFuncSeries();
				 tabbedPanel6.removeAll();
				 tabbedPanel6.setBackground(Color.WHITE);
				 tabbedPanel6.add(twoFuncChartPanel, BorderLayout.CENTER);
				 SwingUtilities.updateComponentTreeUI(tabbedPanel6);
			 }
		 }
	 }
	  
	 // create listener class for clean up button
	 public class cleanup_b_listener extends JFrame implements ActionListener
	 { 
		 public void actionPerformed(ActionEvent ev)
		 {	
			removedRows = true;	 
			disp_wildser();
			removedRows = false;	 		 
		 }
	 }
	 
	 // Retrieve series indexes and values using JButton listener 
	 public class button_listener extends JFrame implements ActionListener
	 {	
			public void actionPerformed(ActionEvent evt)
			{			
				tabbedPane.setSelectedIndex(2);
				oneChart = "noChart";
				dispSerToJtext();			
			}
	 }
	 
	 public void get_tiqfunc() throws FunctorConsistencyChkException
	 {
		 TiqFormula tiqformula = new TiqFormula();	 
		 if(func_val.equals("Annpct"))
		 {		  
			 if(freq_val.equals("Original"))
			 {
				 if(j == 1)
				 {
					 funcView = tiqformula.getAnnpct(tiq);
					 funcIndexes1 = funcView.getObservations(startIndex, endIndex).getIndexesAsStrings();		 
					 funcValues1 = funcView.getObservations(startIndex, endIndex).getValues().getStringArray();	 
				 }
				 else if (j == 2)
				 {
					 funcView = tiqformula.getAnnpct(tiq);
					 funcView1 = tiqformula.getAnnpct(tiq1);
					 funcIndexes1 = funcView.getObservations(startIndex, endIndex).getIndexesAsStrings();		 
					 funcValues1 = funcView.getObservations(startIndex, endIndex).getValues().getStringArray();	 
					 funcValues2 = funcView1.getObservations(startIndex, endIndex).getValues().getStringArray();
				 }
				 else if(j == 3)
				 {
					 funcView = tiqformula.getAnnpct(tiq);
					 funcView1 = tiqformula.getAnnpct(tiq1);
					 funcView2 = tiqformula.getAnnpct(tiq2);
					 funcIndexes1 = funcView.getObservations(startIndex, endIndex).getIndexesAsStrings();		 
					 funcValues1 = funcView.getObservations(startIndex, endIndex).getValues().getStringArray();	 
					 funcValues2 = funcView1.getObservations(startIndex, endIndex).getValues().getStringArray();
					 funcValues3 = funcView2.getObservations(startIndex, endIndex).getValues().getStringArray();
				 }
				 else if(j == 4)
				 {
					 funcView = tiqformula.getAnnpct(tiq);
					 funcView1 = tiqformula.getAnnpct(tiq1);
					 funcView2 = tiqformula.getAnnpct(tiq2);
					 funcView3 = tiqformula.getAnnpct(tiq3);
					 funcIndexes1 = funcView.getObservations(startIndex, endIndex).getIndexesAsStrings();		 
					 funcValues1 = funcView.getObservations(startIndex, endIndex).getValues().getStringArray();	 
					 funcValues2 = funcView1.getObservations(startIndex, endIndex).getValues().getStringArray();
					 funcValues3 = funcView2.getObservations(startIndex, endIndex).getValues().getStringArray();
					 funcValues4 = funcView3.getObservations(startIndex, endIndex).getValues().getStringArray();
				 }
			 }
			 else 
			 {
				 if (j == 1)
				 {
					 funcView = tiqformula.getAnnpct(tsTiq);
					 funcIndexes1 = funcView.getObservations(startIndex, endIndex).getIndexesAsStrings();		 
					 funcValues1 = funcView.getObservations(startIndex, endIndex).getValues().getStringArray();	 
				 
				 }
				 else if(j == 2)
				 {
					 funcView = tiqformula.getAnnpct(tsTiq);
					 funcView1 = tiqformula.getAnnpct(tsTiq1);
					 funcIndexes1 = funcView.getObservations(startIndex, endIndex).getIndexesAsStrings();		 
					 funcValues1 = funcView.getObservations(startIndex, endIndex).getValues().getStringArray();	 
					 funcValues2 = funcView1.getObservations(startIndex, endIndex).getValues().getStringArray();
				 }
				 else if(j == 3)
				 {
					 funcView = tiqformula.getAnnpct(tsTiq);
					 funcView1 = tiqformula.getAnnpct(tsTiq1);
					 funcView2 = tiqformula.getAnnpct(tsTiq2);
					 funcIndexes1 = funcView.getObservations(startIndex, endIndex).getIndexesAsStrings();		 
					 funcValues1 = funcView.getObservations(startIndex, endIndex).getValues().getStringArray();	 
					 funcValues2 = funcView1.getObservations(startIndex, endIndex).getValues().getStringArray(); 
					 funcValues3 = funcView2.getObservations(startIndex, endIndex).getValues().getStringArray(); 
				 }
				 else if(j == 4)
				 {
					 funcView = tiqformula.getAnnpct(tsTiq);
					 funcView1 = tiqformula.getAnnpct(tsTiq1);
					 funcView2 = tiqformula.getAnnpct(tsTiq2);
					 funcView3 = tiqformula.getAnnpct(tsTiq3);
					 funcIndexes1 = funcView.getObservations(startIndex, endIndex).getIndexesAsStrings();		 
					 funcValues1 = funcView.getObservations(startIndex, endIndex).getValues().getStringArray();	 
					 funcValues2 = funcView1.getObservations(startIndex, endIndex).getValues().getStringArray(); 
					 funcValues3 = funcView2.getObservations(startIndex, endIndex).getValues().getStringArray(); 
					 funcValues4 = funcView3.getObservations(startIndex, endIndex).getValues().getStringArray(); 
				 }
			 }
				 		
			/* funcOl1 = funcView.getObservations(startIndex, endIndex);
			 //funcOl2 = funcView.getObservations(startIndex, endIndex);
			 funcIndexes1 = funcView.getObservations(startIndex, endIndex).getIndexesAsStrings();		 
			 funcValues1 = funcView.getObservations(startIndex, endIndex).getValues().getStringArray();	 
			 funcValues2 = funcView1.getObservations(startIndex, endIndex).getValues().getStringArray();*/		 	
		 }
		 else if (func_val.equals("Cave"))
		 { 
			 if(freq_val.equals("Original"))
			 {
				 funcView = tiqformula.getCave(tiq);
			 }
			 else 
			 {
				 funcView = tiqformula.getCave(tsTiq);
			 }
			 
			// get_frequency();
			 funcOl1 = funcView.getObservations(startIndex, endIndex);
			 funcIndexes1 = funcView.getObservations(startIndex, endIndex).getIndexesAsStrings();
			 funcValues1 = funcView.getObservations(startIndex, endIndex).getValues().getStringArray();	
		 
		 }
		 else if (func_val.equals("Csum"))
		 {
			 if(freq_val.equals("Original"))
			 {
				 funcView = tiqformula.getCsum(tiq);
			 }
			 else
			 {
				 funcView = tiqformula.getCsum(tsTiq);
			 }
			 
			 //get_frequency();
			 funcOl1 = funcView.getObservations(startIndex, endIndex);
			 funcIndexes1 = funcView.getObservations(startIndex, endIndex).getIndexesAsStrings();
			 funcValues1 = funcView.getObservations(startIndex, endIndex).getValues().getStringArray();	 
		 }
		 else if (func_val.equals("Diff"))
		 {
			 if(freq_val.equals("Original"))
			 {	 
				 funcView = tiqformula.getDiff(tiq);
			 }
			 else 
			 {
				 funcView = tiqformula.getDiff(tsTiq);
			 }		 
			 //get_frequency();
			 funcOl1 = funcView.getObservations(startIndex, endIndex);
			 funcIndexes1 = funcView.getObservations(startIndex, endIndex).getIndexesAsStrings();
			 funcValues1 = funcView.getObservations(startIndex, endIndex).getValues().getStringArray();		 
		 }
		 else if(func_val.equals("Mavec"))
		 { 
			 if(freq_val.equals("Original"))
			 {
				 funcView = tiqformula.getMavec(tiq);
			 }
			 else 
			 {
				 funcView = tiqformula.getMavec(tsTiq);
			 }		 
			 funcOl1 = funcView.getObservations(startIndex, endIndex);
			 funcIndexes1 = funcView.getObservations(startIndex, endIndex).getIndexesAsStrings();
			 funcValues1 = funcView.getObservations(startIndex, endIndex).getValues().getStringArray();	 
		 }
		 else if(func_val.equals("Mstddev"))
		 {	 
			 if(freq_val.equals("Original"))
			 {
				 funcView = tiqformula.getMstddev(tiq);
			 }
			 else 
			 {
				 funcView = tiqformula.getMstddev(tsTiq);
			 } 
			 //get_frequency();
			 funcOl1 = funcView.getObservations(startIndex, endIndex);
			 funcIndexes1 = funcView.getObservations(startIndex, endIndex).getIndexesAsStrings();
			 funcValues1 = funcView.getObservations(startIndex, endIndex).getValues().getStringArray();	
		 }
		 else if(func_val.equals("Mvar"))
		 {	
			 if(freq_val.equals("Original"))
			 {
				 funcView = tiqformula.getMvar(tiq);
			 }
			 else 
			 {
				 funcView = tiqformula.getMvar(tsTiq);
			 }	 
			 //get_frequency();
			 funcOl1 = funcView.getObservations(startIndex, endIndex);
			 funcIndexes1 = funcView.getObservations(startIndex, endIndex).getIndexesAsStrings();
			 funcValues1 = funcView.getObservations(startIndex, endIndex).getValues().getStringArray();	 
		 }
		 else if (func_val.equals("Msum"))
		 { 
			 if(freq_val.equals("Original"))
			 {
				 funcView = tiqformula.getMsum(tiq);
			 }
			 else 
			 {
				 funcView = tiqformula.getMsum(tsTiq);
			 }	 
			 //get_frequency();
			 funcOl1 = funcView.getObservations(startIndex, endIndex);
			 funcIndexes1 = funcView.getObservations(startIndex, endIndex).getIndexesAsStrings();
			 funcValues1 = funcView.getObservations(startIndex, endIndex).getValues().getStringArray();	
		 }
		 else if (func_val.equals("Pct"))
		 { 
			 if(freq_val.equals("Original"))
			 {
				 funcView = tiqformula.getPct(tiq);
			 }
			 else 
			 {
				 funcView = tiqformula.getPct(tsTiq);
			 } 
			 //get_frequency();
			 funcOl1 = funcView.getObservations(startIndex, endIndex);
			 funcIndexes1 = funcView.getObservations(startIndex, endIndex).getIndexesAsStrings();
			 funcValues1 = funcView.getObservations(startIndex, endIndex).getValues().getStringArray();	
		 }
		 else if (func_val.equals("Ytypct"))
		 {
			 if(freq_val.equals("Original"))
			 {
				 ytypctView = tiq;
			 }
			 else
			 {
				 ytypctView = tsTiq;
			 }
			 //get_frequency();
			 shiftView = ytypctView;
			 shiftView1 = tiqformula.getShiftYear(shiftView, -1);
			 divideView = tiqformula.getDivide(shiftView, shiftView1);	
			 subtractView = tiqformula.getSubtract(divideView, tiqformula.s );
			 funcView = tiqformula.getMultiply(subtractView , tiqformula.scalar);		 
			 funcOl1 = tiqformula.getMultiply(funcView, tiqformula.scalar).getObservations(startIndex, endIndex);
			 funcIndexes1 = funcView.getObservations(startIndex, endIndex).getIndexesAsStrings();
			 funcValues1 = funcView.getObservations(startIndex, endIndex).getValues().getStringArray();	
		 }
		 else if (func_val.equals("Ytydiff"))
		 {
			 if(freq_val.equals("Original"))
			 {
				 ytydiffView = tiq;
			 }
			 else 
			 {
				 ytydiffView = tsTiq;
			 }	
			 //get_frequency();
			 shiftView = ytydiffView;
			 shiftView1 = tiqformula.getShiftYear(shiftView, 1);		 
			 funcView = tiqformula.getSubtractDiff(shiftView, shiftView1);		 
			 funcOl1 = funcView.getObservations(startIndex, endIndex);
			 funcIndexes1 = funcView.getObservations(startIndex, endIndex).getIndexesAsStrings();
			 funcValues1 = funcView.getObservations(startIndex, endIndex).getValues().getStringArray();	 
		 }	 
		 tiqObjArray = new Object[funcIndexes1.length];
			for (int i = 0; i < tiqObjArray.length; i++)
			{
				if(j == 1)
				{
					if(funcValues1[i].equals("NaN"))
					{
						funcValues1[i] = funcValues1[i].replace("NaN", "0.0");
					}
					else
					{
						funcValues1[i] = funcValues1[i].replace(".", ",");
						tiqObjArray[i] = parseDouble(funcValues1[i]);
						tiqObjArray[i] = twoDigits.format(tiqObjArray[i]);
						funcValues1[i] = tiqObjArray[i].toString();
					}
				}
				else if(j == 2)
				{					
					if(funcValues1[i].equals("NaN") || funcValues2[i].equals("NaN") )
					{					 
						funcValues1[i] = funcValues1[i].replace("NaN", "0.0");
						funcValues2[i] = funcValues2[i].replace("NaN", "0.0");				
					}
					else
					{			
						funcValues1[i] = funcValues1[i].replace(".", ",");
						tiqObjArray[i] = parseDouble(funcValues1[i]);
						tiqObjArray[i] = twoDigits.format(tiqObjArray[i]);
						funcValues1[i] = tiqObjArray[i].toString();
						funcValues2[i] = funcValues2[i].replace(".", ",");
						tiqObjArray[i] = parseDouble(funcValues2[i]);
						tiqObjArray[i] = twoDigits.format(tiqObjArray[i]);
						funcValues2[i] = tiqObjArray[i].toString();
					}					
				}
				else if(j == 3)
				{
					if(funcValues1[i].equals("NaN")|| funcValues2[i].equals("NaN")||funcValues3[i].equals("NaN"))
					{
						funcValues1[i] = funcValues1[i].replace("NaN", "0.0");
						funcValues2[i] = funcValues2[i].replace("NaN", "0.0");
						funcValues3[i] = funcValues1[i].replace("NaN", "0.0");				
					}
					else 
					{
						funcValues1[i] = funcValues1[i].replace(".", ",");
						tiqObjArray[i] = parseDouble(funcValues1[i]);
						tiqObjArray[i] = twoDigits.format(tiqObjArray[i]);
						funcValues1[i] = tiqObjArray[i].toString();
						funcValues2[i] = funcValues2[i].replace(".", ",");
						tiqObjArray[i] = parseDouble(funcValues2[i]);
						tiqObjArray[i] = twoDigits.format(tiqObjArray[i]);
						funcValues2[i] = tiqObjArray[i].toString();
						funcValues3[i] = funcValues3[i].replace(".", ",");
						tiqObjArray[i] = parseDouble(funcValues3[i]);
						tiqObjArray[i] = twoDigits.format(tiqObjArray[i]);
						funcValues3[i] = tiqObjArray[i].toString();
					}
				}
				else if (j == 4)
				{
					if(funcValues1[i].equals("NaN")|| funcValues2[i].equals("NaN")||funcValues3[i].equals("NaN")||funcValues4[i].equals("NaN"))
					{
						funcValues1[i] = funcValues1[i].replace("NaN", "0.0");
						funcValues2[i] = funcValues2[i].replace("NaN", "0.0");
						funcValues3[i] = funcValues3[i].replace("NaN", "0.0");
						funcValues4[i] = funcValues4[i].replace("NaN", "0.0");	
					}
					else 
					{
						funcValues1[i] = funcValues1[i].replace(".", ",");
						tiqObjArray[i] = parseDouble(funcValues1[i]);
						tiqObjArray[i] = twoDigits.format(tiqObjArray[i]);
						funcValues1[i] = tiqObjArray[i].toString();
						funcValues2[i] = funcValues2[i].replace(".", ",");
						tiqObjArray[i] = parseDouble(funcValues2[i]);
						tiqObjArray[i] = twoDigits.format(tiqObjArray[i]);
						funcValues2[i] = tiqObjArray[i].toString();
						funcValues3[i] = funcValues3[i].replace(".", ",");
						tiqObjArray[i] = parseDouble(funcValues3[i]);
						tiqObjArray[i] = twoDigits.format(tiqObjArray[i]);
						funcValues3[i] = tiqObjArray[i].toString();
						funcValues4[i] = funcValues4[i].replace(".", ",");
						tiqObjArray[i] = parseDouble(funcValues4[i]);
						tiqObjArray[i] = twoDigits.format(tiqObjArray[i]);
						funcValues4[i] = tiqObjArray[i].toString();
					}
				}			
			} 
	 }
	  
	 // Display series in 'Display Values' view 
	 public void dispSerToJtext()
	 { 
		 ts = null;
		 chosens1 = null;
		 int m = 3;
		 tabbedPanel3.removeAll();	 
		 tiqObjectBase = new TiqObject[chosens.length];	 
		 tiqObject = new TiqObject[chosens.length];		 	 
		 //tiqObjectFunc2 = new TiqObject[chosens.length];
		 chosens1 = new String[chosens.length];
		 //chartView = new TiqView[chosens.length];			 
		 chartView = new TiqView[chosens.length * 2];		 	 
		 ObservationList[] obslist = new ObservationList[chosens1.length];		 
		 if(start == "Start date")
		 {
			 startIndex = date1;
		 }
		 if(end == "End date")
		 {
			 endIndex = date2; 
		 }	 	 
		 for(int i = 0; i < chosens.length; i++)
		 {		 
			 str = chosens[i].indexOf("|");
			 chosens1[i] = chosens[i].substring(0, str - 1);	
			 
		 }// end for int = 0		 	
		 
		 TiqObject[] apObject = null;		 
		 try {				
		 		for(int i = 0; i < j; i++)
		 		{
		 			//tiqObject = getData1(chosens1);
		 			//new 2014.02.17
		 			apObject = getData1(chosens1);
		 			System.out.println(chosens1[i].toString() + "dispSerToJtext");
		 		}			
		 	} catch (Exception e1) {			
				e1.printStackTrace();
			}	
		 	
		 	for (int i = 0; i < j; i++)
		 	{
		 		//tiqObjectBase[i] = tiqObject[i];
		 		//obslist[i] = tiqObject[i].getObservations();
		 		tiqObjectBase[i] = apObject[i];
		 		obslist[i]=apObject[i].getObservations();
		 	}
		 	
		 	tiqFreq = obslist[0].getFrequency();
			chr = tiqFreq.encode();
			getChr = new Character(chr);
			getFreq = getChr.toString();				
			// startindex
			if(getFreq.equals("M"))
			{
				//frequency = "freq monthly";
				frequency = "monthly";
				s1 = "Monthly";				
				if(start.equals("Start date"))
				{
					startIndex = date1;
					//String dates[] = tiqObject[0].getObservations().getIndexesAsStrings();
					String dates[] = apObject[0].getObservations().getIndexesAsStrings();
					long dates1[]= apObject[0].getObservations().getIndexes();				
					int len1 = dates[0].length();
					start = dates[0].substring(len1-4);
					apStart = formatter.format(dates1[0]);										
				}
				else
				{
					new_start_month = new Integer(01);
					new_start_day = new Integer(01);	
					new_start_year = new Integer(start);
					//int start_i_yr = new_start_year.intValue();
					int start_i_day = new_start_day.intValue();
					int start_i_month = new_start_month.intValue();
					int start_i_year = new_start_year.intValue();	
					startIndex = help.ymdToIndex(start_i_year,start_i_month,start_i_day);	
					apStart = formatter.format(startIndex);		
				}
			}
			else if(getFreq.equals("Q"))
			{
				frequency = "freq quarterly";
				s1 = "Quarterly";
				if(start.equals("Start date"))
				{
					startIndex = date1;
					//String dates[] = tiqObject[0].getObservations().getIndexesAsStrings();
					String dates[] = apObject[0].getObservations().getIndexesAsStrings();
					long dates1[]= apObject[0].getObservations().getIndexes();
					int len1 = dates[0].length();
					start = dates[0].substring(len1-4);
					apStart = formatter.format(dates1[0]);
				}
				else 
				{
					TiqDateFormat tdf = new TiqDateFormat("yyyy:q");	 					 				
					start_q = start + ":" + 1;	 				
					d_start = tdf.parse(start_q, new ParsePosition(0) );
					startIndex = help.javaDateToIndex(d_start, TimeZone.getDefault());
					//apStart = tdf.format(startIndex);
					apStart = formatter.format(startIndex);
				}
			}
			else if(getFreq.equals("A"))
			{
				frequency = "freq annual";
				s1 = "Annual";	
				if(start.equals("Start date"))
				{
					startIndex = date1;
					//String dates[] = tiqObject[0].getObservations().getIndexesAsStrings();
					String dates[] = apObject[0].getObservations().getIndexesAsStrings();
					int len1 = dates[0].length();
					start = dates[0].substring(len1-4);
					apStart = formatter.format(startIndex);
				}
				else
				{
					Integer new_start_year = new Integer(start); 				
					int start_i_yr = new_start_year.intValue();
					startIndex = help.ymdToIndex(start_i_yr,1,1);
					apStart = formatter.format(startIndex);
					//apstartDate = apStart;
				}
			}// end startIndex
			// begin with end index
			if(getFreq.equals("M"))
			{
					frequency = "freq monthly";	
					if(end.equals("End date"))
					{
						endIndex = date2;	
						//String dates[] = tiqObject[0].getObservations().getIndexesAsStrings();
						String dates[] = apObject[0].getObservations().getIndexesAsStrings();
						long dates1 = apObject[0].getMaxIndex();
						int d = dates.length;
						int len2 = dates[d-1].length();
						end = dates[d-1].substring(len2-4);
						apEnd = formatter.format(dates1);		
					}
					else 
					{
						new_end_day = new Integer(01);
						Integer new_end_month = new Integer(12);
						Integer new_end_year = new Integer(end);
						int end_i_day = new_end_day.intValue();
						int end_i_month = new_end_month.intValue();
						int end_i_yr = new_end_year.intValue();
						endIndex = help.ymdToIndex(end_i_yr,end_i_month,end_i_day);
						apEnd = formatter.format(endIndex);						
					}
				}
				else if(getFreq.equals("Q"))
				{
					frequency = "freq quarterly";	
					if(end.equals("End date"))
					{
						endIndex = date2;
						//String dates[] = tiqObject[0].getObservations().getIndexesAsStrings();
						String dates[] = apObject[0].getObservations().getIndexesAsStrings();
						long dates1 = apObject[0].getMaxIndex();
						int d = dates.length;
						int len2 = dates[d-1].length();
						end = dates[d-1].substring(len2-4);
						apEnd = formatter.format(dates1);
					}
					else 
					{
						TiqDateFormat tdf = new TiqDateFormat("yyyy:q");	
						//end_q = end + ":" + 1;
						end_q = end + ":" + 4;
						d_end = tdf.parse(end_q, new ParsePosition(0) );
						endIndex = help.javaDateToIndex(d_end, TimeZone.getDefault());
						apEnd = formatter.format(endIndex);
					}
				}
				else if(getFreq.equals("A"))
				{
					frequency = "freq annual";	
					if(end.equals("End date"))
					{
						endIndex = date2;
						//String dates[] = tiqObject[0].getObservations().getIndexesAsStrings();
						String dates[] = apObject[0].getObservations().getIndexesAsStrings();
						int d = dates.length;
						int len2 = dates[d-1].length();
						end = dates[d-1].substring(len2-4);
						apEnd = formatter.format(endIndex);
					}
					else 
					{
						Integer new_end_year = new Integer(end);
						int end_i_yr = new_end_year.intValue();
						endIndex = help.ymdToIndex(end_i_yr,1,1);
						apEnd = formatter.format(endIndex);
						//apendDate = apEnd;
					}
				}
		 
		 // to retrieve TimeIQ functor series via accessPoint with given frequency, start index and end index
		 for (int i = 0; i < chosens.length; i++)
		 {		 
			 String ch;
			 ch = null;
			 TiqObject[] funcObj = null;
			 //TiqObject[] funcObj1 = null;	 
			 if(func_val == null)
			 {	
				 try {				
					 if(t_scale != true)
					 {
						 funcObj = getData4(chosens1, apStart, apEnd);
						 tiqObject[i] = funcObj[i];
						 tiqObjectBase[i] = tiqObject[i];
						 chartView[i] = tiqObject[i];
					 }
					 else 
					 {
						 funcObj = getData3(chosens1, apStart, apEnd, freq_val);
						 tiqObject[i] = funcObj[i];
						 tiqObjectBase[i] = tiqObject[i];
						 chartView[i] = tiqObject[i]; 
					 }
				 } catch (Exception e) {			
					e.printStackTrace();
				}				 
				// get_ts(); 			
			 }
			 else if(func_val == "Annpct")
			 { 
				 try {				
					 if(t_scale !=true)
					 {						 
						 funcObj = getData2(chosens1, apStart, apEnd);
						 tiqObject[i] = funcObj[i];
						 tiqObjectBase[i] = tiqObject[i];
						 chartView[i] = tiqObject[i];
					 }			
					 else 
					 {
						 funcObj = getData5(chosens1, apStart, apEnd, freq_val);
						 tiqObject[i] = funcObj[i];
						 tiqObjectBase[i] = tiqObject[i];
						 chartView[i] = tiqObject[i];
					 }
				
				 } catch (Exception e) {
					
					e.printStackTrace();
				}						 
			 }
			 else if(func_val == "Ave")
			 {
				 try {			
					 if(t_scale != true)
					 {
						 funcObj = getData2(chosens1, apStart, apEnd);
						 tiqObject[i] = funcObj[i];
						 tiqObjectBase[i] = tiqObject[i];
						 chartView[i] = tiqObject[i];
					 }
					 else
					 {
						 funcObj = getData5(chosens1, apStart, apEnd, freq_val);
						 tiqObject[i] = funcObj[i];
						 tiqObjectBase[i] = tiqObject[i];
						 chartView[i] = tiqObject[i];
					 }
				 } catch (Exception e) {
					e.printStackTrace();
				}
			 }
			 else if(func_val == "Csum" )
			 {
				 try {				
					 if(t_scale != true)
					 {
						 funcObj = getData2(chosens1, apStart, apEnd);
						 tiqObject[i] = funcObj[i];
						 tiqObjectBase[i] = tiqObject[i];
						 chartView[i] = tiqObject[i];
					 }
					 else 
					 {
						 funcObj = getData5(chosens1, apStart, apEnd, freq_val);
						 tiqObject[i] = funcObj[i];
						 tiqObjectBase[i] = tiqObject[i];
						 chartView[i] = tiqObject[i];
					 }	
				 } catch (Exception e) {				
					e.printStackTrace();
				}
			 }
			 else if(func_val == "Diff")
			 { 
				 try {			
					 if(t_scale != true)
					 {
						 funcObj = getData2(chosens1, apStart, apEnd);
						 tiqObject[i] = funcObj[i];
						 tiqObjectBase[i] = tiqObject[i];
						 chartView[i] = tiqObject[i];
					 }
					 else 
					 {
						 funcObj = getData5(chosens1, apStart, apEnd, freq_val);
						 tiqObject[i] = funcObj[i];
						 tiqObjectBase[i] = tiqObject[i];
						 chartView[i] = tiqObject[i];
					 }	
				 } catch (Exception e) {		
					e.printStackTrace();
				}
			 }
			 else if(func_val == "Lave")
			 { 
				 try {				
					 if(t_scale != true)
					 {
						 funcObj = getData2(chosens1, apStart, apEnd);
						 tiqObject[i] = funcObj[i];
						 tiqObjectBase[i] = tiqObject[i];
						 chartView[i] = tiqObject[i];
					 }
					 else
					 {
						 funcObj = getData5(chosens1, apStart, apEnd, freq_val);
						 tiqObject[i] = funcObj[i];
						 tiqObjectBase[i] = tiqObject[i];
						 chartView[i] = tiqObject[i];
					 }
				}
				 catch (Exception e) {		
					e.printStackTrace();
				} 
			 }
			 else if(func_val == "Lmax")
			 { 
				 try {
					 if(t_scale != true)
					 {
						 funcObj = getData2(chosens1, apStart, apEnd);
						 tiqObject[i] = funcObj[i];
						 tiqObjectBase[i] = tiqObject[i];
						 chartView[i] = tiqObject[i];
					 }
					 else 
					 {
						 funcObj = getData5(chosens1, apStart, apEnd, freq_val);
						 tiqObject[i] = funcObj[i];
						 tiqObjectBase[i] = tiqObject[i];
						 chartView[i] = tiqObject[i];
					 }
				 } catch (Exception e) {
					e.printStackTrace();
				}
			 }
			 else if (func_val == "Lmin")
			 { 
				 try {
					 if(t_scale != true)
					 {
						 funcObj = getData2(chosens1, apStart, apEnd);
						 tiqObject[i] = funcObj[i];
						 tiqObjectBase[i] = tiqObject[i];
						 chartView[i] = tiqObject[i];
					 }
					 else 
					 {
						 funcObj = getData5(chosens1, apStart, apEnd, freq_val);
						 tiqObject[i] = funcObj[i];
						 tiqObjectBase[i] = tiqObject[i];
						 chartView[i] = tiqObject[i];
					 }
				 } catch (Exception e) {
					e.printStackTrace();
				} 
			 }
			 else if ( func_val == "Lsum")
			 { 
				 try {		
					 if(t_scale != true)
					 {
						 funcObj = getData2(chosens1, apStart, apEnd);
						 tiqObject[i] = funcObj[i];
						 tiqObjectBase[i] = tiqObject[i];
						 chartView[i] = tiqObject[i];
					 }
					 else 
					 {
						 funcObj = getData5(chosens1, apStart, apEnd, freq_val);
						 tiqObject[i] = funcObj[i];
						 tiqObjectBase[i] = tiqObject[i];
						 chartView[i] = tiqObject[i]; 
					 }
				 } catch (Exception e) {
					e.printStackTrace();
				} 
			 }
			 else if(func_val == "Mave")
			 { 
				 try {		
					 if(t_scale != true)
					 {
						 funcObj = getData2(chosens1, apStart, apEnd);
						 tiqObject[i] = funcObj[i];
						 tiqObjectBase[i] = tiqObject[i];
						 chartView[i] = tiqObject[i];
					 }
					 else 
					 {
						 funcObj = getData5(chosens1, apStart, apEnd, freq_val);
						 tiqObject[i] = funcObj[i];
						 tiqObjectBase[i] = tiqObject[i];
						 chartView[i] = tiqObject[i];
					 }		
				 } catch (Exception e) {
					e.printStackTrace();
				}
			 }
			 else if(func_val == "Mavec")
			 { 
				 try {
					 if(t_scale != true)
					 {
						 funcObj = getData2(chosens1, apStart, apEnd);
						 tiqObject[i] = funcObj[i];
						 tiqObjectBase[i] = tiqObject[i];
						 chartView[i] = tiqObject[i];
					 }
					 else
					 {
						 funcObj = getData5(chosens1, apStart, apEnd, freq_val);
						 tiqObject[i] = funcObj[i];
						 tiqObjectBase[i] = tiqObject[i];
						 chartView[i] = tiqObject[i];
					 }
				 } catch (Exception e) {	
					e.printStackTrace();
				}
			 }
			 else if (func_val == "Mstddev")
			 {
				 try {
					 if(t_scale != true)
					 {
						 funcObj = getData2(chosens1, apStart, apEnd);
						 tiqObject[i] = funcObj[i];
						 tiqObjectBase[i] = tiqObject[i];
						 chartView[i] = tiqObject[i];
					 }
					 else 
					 {
						 funcObj = getData5(chosens1, apStart, apEnd, freq_val);
						 tiqObject[i] = funcObj[i];
						 tiqObjectBase[i] = tiqObject[i];
						 chartView[i] = tiqObject[i];
					 }
				 } catch (Exception e) {
					e.printStackTrace();
				}
			 }
			 else if(func_val == "Pct")
			 { 
				 try {			
					 if(t_scale != true)
					 {
						 funcObj = getData2(chosens1, apStart, apEnd);
						 tiqObject[i] = funcObj[i];
						 tiqObjectBase[i] = tiqObject[i];
						 chartView[i] = tiqObject[i];
					 }
					 else
					 {
						 funcObj = getData5(chosens1, apStart, apEnd, freq_val);
						 tiqObject[i] = funcObj[i];
						 tiqObjectBase[i] = tiqObject[i];
						 chartView[i] = tiqObject[i];
					 }
				 } catch (Exception e) {	
					e.printStackTrace();
				}
			 }
			 else if(func_val == "Stddev")
			 { 
				 try {	
					 if(t_scale != true)
					 {
						 funcObj = getData2(chosens1, apStart, apEnd);
						 tiqObject[i] = funcObj[i];
						 tiqObjectBase[i] = tiqObject[i];
						 chartView[i] = tiqObject[i];
					 }
					 else
					 {
						 funcObj = getData5(chosens1, apStart, apEnd, freq_val);
						 tiqObject[i] = funcObj[i];
						 tiqObjectBase[i] = tiqObject[i];
						 chartView[i] = tiqObject[i];
					 }
				 } catch (Exception e) {
					e.printStackTrace();
				}
			 }
			 else if(func_val == "Ytydiff")
			 { 
				 try {
					 if(t_scale != true)
					 {
						 funcObj = getData2(chosens1, apStart, apEnd);
						 tiqObject[i] = funcObj[i];
						 tiqObjectBase[i] = tiqObject[i];
						 chartView[i] = tiqObject[i];
					 }
					 else 
					 {
						 funcObj = getData5(chosens1, apStart, apEnd, freq_val);
						 tiqObject[i] = funcObj[i];
						 tiqObjectBase[i] = tiqObject[i];
						 chartView[i] = tiqObject[i];
					 }
				 } catch (Exception e) {		
					e.printStackTrace();
				}  
			 }
			 else if(func_val == "Ytypct")
			 {
				 try {
					 if(t_scale != true)
					 {
						 funcObj = getData2(chosens1, apStart, apEnd);
						 tiqObject[i] = funcObj[i];
						 tiqObjectBase[i] = tiqObject[i];
						 chartView[i] = tiqObject[i];
					 }
					 else 
					 {
						 funcObj = getData5(chosens1, apStart, apEnd, freq_val);
						 tiqObject[i] = funcObj[i];
						 tiqObjectBase[i] = tiqObject[i];
						 chartView[i] = tiqObject[i];
					 }
				 } catch (Exception e) {
					e.printStackTrace();
				}
			 }
			 else if(func_val == "Baseyear")
			 { 				 
				 tiqObject[i] = byearObj[i];
				 tiqObjectBase[i] = byearObj[i];
				 chartView[i] = tiqObject[i];
			 } 
		 }// end for loop
		 
			 for(int i = 0; i < chosens.length; i++)
			 {
				 chartView[chosens.length + i] = tiqObjectBase[i];
				 //chartView[chosens.length + i] = tiqObject[i];
			 }
		 	
		 		printData2(tiqObject); 
		 		Font f =  new Font("COURIER NEW",Font.PLAIN, 12);
				serDisp.setFont(f);
		 		serDisp.setText(serOutput);
		 		scroll2 = new JScrollPane(serDisp);
		 		scroll2.createHorizontalScrollBar();
		 		tabbedPanel3.setBackground(Color.WHITE);
		 		tabbedPanel3.add(scroll2);
		 		SwingUtilities.updateComponentTreeUI(tabbedPanel3);			
		 		   
	 }// end dispSerToJtext
	 
	 // use output to write series
	 public void printData2(TiqObject[] tiqObject)
	 {
	        //String[] selectedAndValues_1;
	        //selectedAndValues_1 = new String[selectedAndValues.length];
		 	ObservationList[] ol2 = new ObservationList[tiqObject.length];
	        for (int i = 0; i < tiqObject.length; i++)
	        {
	            ol2[i] = tiqObject[i].getObservations();
	        }        
	        int i = tiqObject.length;
	        //int j = ol2[1].size();
	        int j = ol2[0].size();       
	        
	        // creating the arrays to hold the date, values and statuses
	        Object values1[][] = new Object[i][j];
	        //String[][] values2 = new String[i][j];
	        Object values2[][] = new Object[i][j];
	        String[] dates2 = new String[j];    // since we are retrieving parallel time series, dates are same for all the objects
	        byte[][] status2 = new byte[i][j];      
	        // Populating the arrsys with dates, values and statuses for each object
	        for (i = 0; i < tiqObject.length; i++)
	        {   
	                //values2[i] = ol2[i].getValues().getStringArray();
	        		values2[i] = ol2[i].getValues().getObjectArray();
	                dates2 = ol2[i].getIndexesAsStrings();
	                status2[i] = ol2[i].getStatuses();
	        }
	        
	       
	        // For printing the first row containing dates and the object names
	        //System.out.print("DATES      ");
	        //serOutput = "DATES      ";
	        serOutput = rightPad("DATES     ", 10);

	        //for(int t = 0; t < v.size(); t++)
	        for(int t = 0; t < tiqObject.length; t++)
	        {  
	        	if(func_val != null)
	        	{
	        		//serOutput += rightPad(func_val + "(" + chosens1[t] + ")", 20);
	        		serOutput += rightPad(func_val + "(" + chosens_m_px[t] + ")", 25);
	        		System.out.println(func_val + chosens_m_px[t]);
	        	}
	        	else 
	        	{
	        		serOutput += rightPad(chosens_m_px[t], 25);
	        		//serOutput += rightPad(chosens1[t], 20);
	        		System.out.println(chosens_m_px[t] + " printData2");
	        	}
	        }   
	        serOutput += "\n" + " ";     
	        // to print the rows      
	       // myValues = new String[tiqObject.length][values2[1].length];
	        myValues = new String[tiqObject.length][values2[0].length];
	       
	        //for (int lcv = 0; lcv < values2[1].length; lcv++)
	        //for (int lcv = 0; lcv < values2[0].length; lcv++) 
	        for (int lcv = 0; lcv < j; lcv++) 
	        {        
	        	serOutput += rightPad(dates2[lcv], 1);             
	            // to print the column values
	            for (int k = 0; k < tiqObject.length; k++)
	            {
	            	if (!TiqHelper.isMissing(status2[k][lcv]))
	            	{
	            		myValues[k][lcv] = nft.format(values2[k][lcv]);
	            		//System.out.print(rightPad(myValues[k][lcv], 20));
	            		serOutput += rightPad(myValues[k][lcv], 20);
	            	}
	                else
	                {
	                    //System.out.print(rightPad("Missing",10));
	                	serOutput += rightPad("Missing", 20);         
	                }
	            }
	            
	            	serOutput += "\n";
	        }
	    }
	 
	 /*public static String rightPad(String s, int width)
	 {
		 return String.format("%-" + width + "s", s);
	 }*/
	 
	 public static String rightPad(String s, int width)
	 {
		 return String.format("%" + width + "s", s);
	 }	
	 
	 // Get common base year
	 public void get_baseyear()
	 {		 		 
		 	int byearValue = 100;	
		 	double[] byearVal = null;	
		 	double b_val;
			Object obj;		
			//String[] ob1 = null;
			String[][] ob1 ;
			String singleVal;
			Observation obs = null;
			ObservationList obsList1 = null;
			//ObservationList obsList2 = null;
			ObservationList[] obsList2;
			obsList2 = new ObservationList[tiqObject.length];
			String [] dates2;
			
			String[] sum_m_val;
			double sum_val = 0;
			double m_ave = 0 ;
			double[] d_val = null;
			TiqView[] tw;	
			//TiqObject[] tiqobj;
			//int st;
			Integer new_base_year = new Integer(byear);
			Integer base_first_month = new Integer(1);
			Integer base_last_month = new Integer(12);
			Integer base_first_day = new Integer(1);
			int start_b_yr = new_base_year.intValue();
			int start_f_m = base_first_month.intValue();
			int start_l_m = base_last_month.intValue();
			int start_b_d = base_first_day.intValue();
			TiqFrequency tiqfrequency;
			char chr;
			Character character;
			String findFreq = null;
			String [][] twoArrays;
			String [][] myArrays;
			String[] tiqVal = null;
			String datePattern = "MMMyyyy";
			TiqDateFormat tdf = new TiqDateFormat(datePattern);
			
			byearIdx = help.ymdToIndex(start_b_yr, 1 , 1);
			byearStartIdx = help.ymdToIndex(start_b_yr, start_f_m, start_b_d);
			byearEndIdx = help.ymdToIndex(start_b_yr, start_l_m, start_b_d);
						
				// Retrieve observations of tiqobject
				for(int i = 0; i < tiqObject.length; i++)
				{
					obsList2[i] = tiqObject[i].getObservations();
				}
				
				int i = tiqObject.length;
				int j = obsList2[0].size();
				twoArrays = new String[i][j];
				ob1 = new String[i][j];
				twoArrays = new String[i][j];
				dates2 = new String[j];
				byearObj = new TiqObject[tiqObject.length];			
				try {					
					//for (int i = 0; i < tiqobj.length; i++)
					for (int i1 = 0; i1 < tiqObject.length; i1++)
					{								
						//obsList1 = tiqobj[i].getObservations(byearStartIdx, byearEndIdx);	
						obsList1 = tiqObject[i1].getObservations(byearStartIdx, byearEndIdx);
						//obsList1[i]= tiqObject[i].getObservations(byearStartIdx, byearEndIdx);
						try {
							//obs = tiqobj[i].getObservation(byearIdx);
							obs = tiqObject[i1].getObservation(byearIdx);

						} catch (DateAlignmentChkException e) {
							e.printStackTrace();
						}										
						//obsList2 = tiqobj[i].getObservations();		
						//obsList2 = tiqObject[i].getObservations();
						obsList2[i1] = tiqObject[i1].getObservations();

						sum_m_val = obsList1.getValues().getStringArray();
						//sum_m_val = obsList1[i].getValues().getStringArray();
						//singleVal = obs.getStringValue();
						b_val = obs.getDoubleValue();

						//tiqfrequency = obsList2.getFrequency();
						tiqfrequency = obsList2[i1].getFrequency();
						chr = tiqfrequency.encode();
						character = new Character(chr);
						findFreq = character.toString();
						
						if (findFreq.equals("M"))
						{
							d_val = new double[sum_m_val.length];
							sum_val = 0;
							for (int j1 = 0; j1 < d_val.length; j1++)
							{
								d_val[j1] = Double.parseDouble(sum_m_val[j1]);
								sum_val += d_val[j1] / 12;
							}
						}// end if
						else if(findFreq.equals("Q"))
						{
							d_val = new double[sum_m_val.length];
							sum_val = 0;							
							for (int j1 = 0; j1 < d_val.length; j1++)
							{
								d_val[j1] = Double.parseDouble(sum_m_val[j1]);
								sum_val += d_val[j1] / 4;
							}
						}
						else if (findFreq.equals("A"))
						{
							sum_val = b_val;
						}
											
						//ob1 = new String[tiqObject.length][obsList2[i].size()];
						//ob1 = obsList2.getValues().getStringArray();
						ob1[i1] = obsList2[i1].getValues().getStringArray();
						dates2 = obsList2[i1].getIndexesAsStrings();

						byearVal = new double[ob1[0].length];
						//tiqValues1 = new String[byearVal.length];
						tiqVal = new String[byearVal.length];
						//tiqVal = new String[tiqObject.length][byearVal.length];
						//twoArrays = new String[tiqVal.length][tiqObject.length];
						//myArrays = new String[tiqObject.length][tiqVal.length];
						//myArrays = new String[tiqVal.length][tiqObject.length];
														
						for(int k = 0; k < ob1[0].length; k++)
						{
							//byearVal[k] = Double.parseDouble(ob1[k]);
							byearVal[k] = Double.parseDouble(ob1[i1][k]);
							byearVal[k] = byearVal[k] / sum_val * byearValue;
							//tiqValues1[k] = nft.format(byearVal[k]);
							tiqVal[k] = nft.format(byearVal[k]);
							twoArrays[i1][k] = tiqVal[k];				
						}													
						long[] dateIndex = new long[dates2.length];
						float[] dataValue = new float[tiqVal.length];
						byte[] bytes = new byte[dates2.length];

						for ( int lcv = 0; lcv < dateIndex.length; lcv++)
						{
							dateIndex[lcv] = DateHelper.javaDateToIndex(tdf.parse(dates2[lcv],new ParsePosition(0)), null);
							bytes[lcv] = Observation.STATUS_OK;
							tiqVal[lcv] = tiqVal[lcv].replace(",", ".");
							dataValue[lcv] = new Float(tiqVal[lcv]).floatValue();
						}
						FloatList floatList = new FloatList(dataValue);						
						if(findFreq.equals("M"))
						{
							sc = new SimpleCalendar(TiqFrequency.MONTHLY, SimpleCalendar.REF_DECEMBER,1);
							obsList = new ObservationList(dateIndex, floatList, bytes, TiqFrequency.MONTHLY);
						}
						else if(findFreq.equals("Q"))
						{
							sc = new SimpleCalendar(TiqFrequency.QUARTERLY, SimpleCalendar.REF_JANUARY,1);
							obsList = new ObservationList(dateIndex, floatList, bytes, TiqFrequency.QUARTERLY);
						}
						else if(findFreq.equals("A"))
						{
							sc = new SimpleCalendar(TiqFrequency.ANNUAL, SimpleCalendar.REF_JANUARY, 1);
							obsList = new ObservationList(dateIndex, floatList, bytes, TiqFrequency.ANNUAL);
						}											
						RegularSeries rs = new RegularSeries(sc,obsList);
						byearObj[i1] = rs;
											
					}// end outer for 
					
			}catch (RangeTooLargeChkException e) {
				e.printStackTrace();
			}	 
	 }// end get_baseyear
	  
	 // Number parse
	 public Number parseDouble(String s)
	 { 	
		ParsePosition p = new ParsePosition(0);
		Number result = nft.parse(s, p);
		nft.setMaximumFractionDigits(2);
		nft.setMinimumFractionDigits(0);

		if (p.getIndex() != s.length())
			throw new IllegalArgumentException(s + "Could not parse " + s + " as a number");
		return result;
	 } 
	 
 } // end Java applet
	 


