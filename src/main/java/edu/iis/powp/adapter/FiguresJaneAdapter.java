package edu.iis.powp.adapter;

import edu.iis.client.plottermagic.*;

public class FiguresJaneAdapter extends AbstractPlotter {

	private IPlotter driver;
	
	public FiguresJaneAdapter(IPlotter driver){
		super(0, 0);
		this.driver = driver;
	}
	
	@Override
	public void drawTo(int x, int y) {
		driver.drawTo(x, y);
	}

}
