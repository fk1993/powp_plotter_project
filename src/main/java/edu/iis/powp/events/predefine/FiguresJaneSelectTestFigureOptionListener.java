package edu.iis.powp.events.predefine;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.iis.client.plottermagic.IPlotter;
import edu.iis.client.plottermagic.preset.*;
import edu.iis.powp.adapter.FiguresJaneAdapter;
import edu.iis.powp.app.Application;
import edu.iis.powp.app.DriverManager;

public class FiguresJaneSelectTestFigureOptionListener  implements ActionListener
{

    @Override
    public void actionPerformed(ActionEvent e)
    {
        IPlotter driver = Application.getComponent(DriverManager.class).getCurrentPlotter();
    	FiguresJane.figureScript(new FiguresJaneAdapter(driver));
    }
}
