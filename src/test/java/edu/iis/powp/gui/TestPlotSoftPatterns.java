package edu.iis.powp.gui;

import java.awt.EventQueue;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.iis.client.plottermagic.ClientPlotter;
import edu.iis.client.plottermagic.IPlotter;
import edu.iis.powp.adapter.LinePlotterAdapter;
import edu.iis.powp.adapter.PlotterAdapter;
import edu.iis.powp.app.Application;
import edu.iis.powp.app.Context;
import edu.iis.powp.app.DriverManager;
import edu.iis.powp.appext.ApplicationWithDrawer;
import edu.iis.powp.command.CommandFactory;
import edu.iis.powp.events.predefine.*;
import edu.kis.powp.drawer.panel.DefaultDrawerFrame;
import edu.kis.powp.drawer.panel.DrawPanelController;


public class TestPlotSoftPatterns
{
	private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
		
    /**
	 * Setup test concerning preset figures in context.
	 * 
	 * @param context Application context.
	 */
	private static void setupPresetTests(Context context) {
	    SelectTestFigureOptionListener selectTestFigureOptionListener = new SelectTestFigureOptionListener();
		context.addTest("Figure Joe 1", selectTestFigureOptionListener);
		
		SelectTestFigureOptionListener2 selectTestFigureOptionListener2 = new SelectTestFigureOptionListener2();
		context.addTest("Figure Joe 2", selectTestFigureOptionListener2);
		
		FiguresJaneSelectTestFigureOptionListener figuresJaneselectTestFigureOptionListener = new FiguresJaneSelectTestFigureOptionListener();
		context.addTest("Figure Jane", figuresJaneselectTestFigureOptionListener);
		
		CommandSelectTestFigureOptionListener rectCommand = 
				new CommandSelectTestFigureOptionListener(CommandFactory.getRectangleCommand(-100, -100, 200, 300));
		context.addTest("Rectangle", rectCommand);
		
		CommandSelectTestFigureOptionListener triangleCommand = 
				new CommandSelectTestFigureOptionListener(CommandFactory.getTriangleCommand(-100, -100, 100, 0, 50, 150));
		context.addTest("Triangle", triangleCommand);
		
		CommandSelectTestFigureOptionListener polygonCommand = 
				new CommandSelectTestFigureOptionListener(CommandFactory.getPolygonCommand(new Point(-100, -100), new Point(50, -50), new Point(100, 0), new Point(150, 75), new Point(50, 150), new Point(-50, 100)));
		context.addTest("Polygon", polygonCommand);
	}

	/**
	 * Setup driver manager, and set default IPlotter for application.
	 * 
	 * @param context Application context.
	 */
	private static void setupDrivers(Context context) {
		IPlotter clientPlotter = new ClientPlotter();
		context.addDriver("Client Plotter", clientPlotter);
		Application.getComponent(DriverManager.class).setCurrentPlotter(clientPlotter);
		
		IPlotter plotter = new PlotterAdapter();
		context.addDriver("Buggy Simulator", plotter);
		
		IPlotter linePlotter = new LinePlotterAdapter();
		context.addDriver("Special Line Plotter", linePlotter);

		context.updateDriverInfo();
	}

	/**
	 * Auxiliary routines to enable using Buggy Simulator.
	 * 
	 * @param context Application context.
	 */
	private static void setupDefaultDrawerVisibilityManagement(Context context) {
		DefaultDrawerFrame defaultDrawerWindow = DefaultDrawerFrame.getDefaultDrawerFrame();
        context.addComponentMenuElementWithCheckBox(DrawPanelController.class, "Default Drawer Visibility", 
        		new SelectChangeVisibleOptionListener(defaultDrawerWindow), false);
        defaultDrawerWindow.setVisible(false);
	}
	
	/**
	 * Setup menu for adjusting logging settings.
	 * 
	 * @param context Application context.
	 */
	private static void setupLogger(Context context) {
		Application.addComponent(Logger.class);
		context.addComponentMenu(Logger.class, "Logger", 0);
		context.addComponentMenuElement(Logger.class, "Clear log", (ActionEvent e) -> context.flushLoggerOutput());
		context.addComponentMenuElement(Logger.class, "Fine level", (ActionEvent e) -> LOGGER.setLevel(Level.FINE));
		context.addComponentMenuElement(Logger.class, "Info level", (ActionEvent e) -> LOGGER.setLevel(Level.INFO));
		context.addComponentMenuElement(Logger.class, "Warning level", (ActionEvent e) -> LOGGER.setLevel(Level.WARNING));
		context.addComponentMenuElement(Logger.class, "Severe level", (ActionEvent e) -> LOGGER.setLevel(Level.SEVERE));
		context.addComponentMenuElement(Logger.class, "OFF logging", (ActionEvent e) -> LOGGER.setLevel(Level.OFF));
	}
		
    /**
     * Launch the application.
     */
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                ApplicationWithDrawer.configureApplication();
                Context context = Application.getComponent(Context.class);
                
                setupDefaultDrawerVisibilityManagement(context);
                
            	setupDrivers(context);
            	setupPresetTests(context);
            	setupLogger(context);
            }

        });
    }

}
