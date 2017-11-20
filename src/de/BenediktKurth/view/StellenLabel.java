package de.BenediktKurth.view;

import de.BenediktKurth.control.MainWindowController;
import de.BenediktKurth.model.FarbenEnum;
import de.BenediktKurth.model.IDBase;
import de.BenediktKurth.model.Stellen;
import de.BenediktKurth.model.Vector2D;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JLabel;
import static javax.swing.SwingConstants.TOP;

/**
 *
 * @author clannick
 */
public class StellenLabel extends VerschiebbarLabel {

    private final static int OFFSET = 6;
    private boolean markiert;
    private FarbenEnum meineFarbe;

    public StellenLabel(Stellen basis, MainWindowController controller, HauptFenster mother) {
        super(basis, controller, mother);

        this.markiert = basis.getMarkiert();
        this.meineFarbe = basis.getMeineFarbe();
        
        
        int size = IDBase.getSize() + OFFSET;

        int posX;
        int posY;
       
        
        if (basis.getLabel().equals("null")) {
            // Keine Bezeichnung für Label bzw. "null"
            posX = this.position.getX() - (size / 2);
            posY = this.position.getY() - (size / 2);
            super.setBounds(posX, posY, size, size);
            super.setToolTipText("Stelle: " + basis.getId() + " (x:" + this.position.getX() + "/y:" + this.position.getY() + ")");

            
        } else {
            //Stelle mit Label
            posX = this.position.getX() - (size / 2);
            posY = this.position.getY() - ((size) / 2) ;

            super.setBounds(posX, posY, size, size + 20);
            super.setToolTipText("Stelle: ID " + basis.getId() + " Label: " + basis.getLabel() + " (x:" + this.position.getX() + "/y:" + this.position.getY() + ")");
            
            super.setText(basis.getLabel());

            super.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            super.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

            super.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        }

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D temp = (Graphics2D) g;
        temp.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int pos = OFFSET / 2;

        switch (meineFarbe) {
            case weiss:
                g.setColor(Color.white);
                break;
            case rot:
                g.setColor(Color.red);
                break;
            case gelb:
                g.setColor(Color.yellow);
                break;
            case grau:
                g.setColor(Color.gray);
                break;
            case gruen:
                g.setColor(Color.green);
                break;
            default:
                g.setColor(Color.black);
                break;
        }

        g.fillOval(pos, pos, IDBase.getSize(), IDBase.getSize());
        g.setColor(Color.black);
        g.drawOval(pos, pos, IDBase.getSize(), IDBase.getSize());
        if (markiert) {
            int size = (IDBase.getSize() + OFFSET) / 2;
            int kreisGroesse = IDBase.getSize() / 8;
            size -= kreisGroesse / 2;

            g.fillOval(size, size, kreisGroesse, kreisGroesse);
        }
        temp.dispose();
        g.dispose();
    }
}
