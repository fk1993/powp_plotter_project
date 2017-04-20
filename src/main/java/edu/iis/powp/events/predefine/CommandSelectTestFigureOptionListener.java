package edu.iis.powp.events.predefine;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.iis.powp.app.Application;
import edu.iis.powp.app.DriverManager;
import edu.iis.powp.command.PlotterCommand;

public class CommandSelectTestFigureOptionListener implements ActionListener {

	private PlotterCommand cmd;
	
	public CommandSelectTestFigureOptionListener(PlotterCommand cmd) {
		super();
		this.cmd = cmd;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		cmd.execute(Application.getComponent(DriverManager.class).getCurrentPlotter());
	}
}
