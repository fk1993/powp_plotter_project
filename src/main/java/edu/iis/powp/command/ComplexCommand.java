package edu.iis.powp.command;

import java.util.*;
import edu.iis.client.plottermagic.IPlotter;

public class ComplexCommand implements PlotterCommand {

	private List<PlotterCommand> commandList;
	
	public ComplexCommand(){
		commandList = new ArrayList<>();
	}
	
	public void add(PlotterCommand command){
		commandList.add(command);
	}
	
	public void add(PlotterCommand... commands){
		for(PlotterCommand cmd: commands)
			commandList.add(cmd);
	}

	@Override
	public void execute(IPlotter driver) {
		commandList.forEach(cmd -> cmd.execute(driver));
	}
}
