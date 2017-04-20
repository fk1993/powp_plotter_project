package edu.iis.powp.command;

import java.awt.Point;

public class CommandFactory {

	public static ComplexCommand getRectangleCommand(int x, int y, int width, int height){
		ComplexCommand cmd = new ComplexCommand();
		cmd.add(new CommandSetPosition(x, y));
		cmd.add(new CommandDrawLineToPosition(x + width, y));
		cmd.add(new CommandDrawLineToPosition(x + width, y + height));
		cmd.add(new CommandDrawLineToPosition(x, y + height));
		cmd.add(new CommandDrawLineToPosition(x, y));
		return cmd;
	}
	
	public static ComplexCommand getTriangleCommand(int x1, int y1, int x2, int y2, int x3, int y3){
		ComplexCommand cmd = new ComplexCommand();
		cmd.add(new CommandSetPosition(x1, y1));
		cmd.add(new CommandDrawLineToPosition(x2, y2));
		cmd.add(new CommandDrawLineToPosition(x3, y3));
		cmd.add(new CommandDrawLineToPosition(x1, y1));
		return cmd;
	}
	
	public static ComplexCommand getPolygonCommand(Point p0, Point... points){
		ComplexCommand cmd = new ComplexCommand();
		int x = (int)p0.getX();
		int y = (int)p0.getY();
		cmd.add(new CommandSetPosition(x, y));
		for(Point p: points)
			cmd.add(new CommandDrawLineToPosition((int)p.getX(), (int)p.getY()));
		cmd.add(new CommandDrawLineToPosition(x, y));
		return cmd;
	}
}
