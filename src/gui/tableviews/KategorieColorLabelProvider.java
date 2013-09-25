package gui.tableviews;

import model.Buch;
import model.Kategorie;

import org.eclipse.jface.viewers.OwnerDrawLabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.TableItem;

public class KategorieColorLabelProvider extends OwnerDrawLabelProvider{
	private int index;
	
	public KategorieColorLabelProvider(int index){
		this.index = index;
	}

	@Override
	protected void paint(Event event, Object element) {
		Buch b = (Buch)element;
		
		Kategorie k = (Kategorie)b.getKategorie();
		GC gc = event.gc;
		Color foreground = gc.getForeground();
        Color background = gc.getBackground();
        
        String text = k.getName();
		Color newBackground = k.getColor();
		Color newForeground = Display.getCurrent().getSystemColor(SWT.COLOR_WHITE);
		if(k.getColor().getRGB().getHSB()[2] > 0.5) newForeground = Display.getCurrent().getSystemColor(SWT.COLOR_BLACK);
		
		Rectangle bounds = ((TableItem)event.item).getBounds(index);
		
		gc.setBackground(newBackground);
        gc.fillRectangle(bounds);
        
        gc.setForeground(newForeground);
        Point size = event.gc.textExtent(text);
        int offset = Math.max(0, (event.height - size.y) / 2);
        gc.drawText(text, event.x + 2, event.y + offset, true);
        

        gc.setForeground(background);
        gc.setBackground(foreground);
		
	}

	@Override
	protected void measure(Event event, Object element) {
		// TODO Auto-generated method stub
		
	}
}
