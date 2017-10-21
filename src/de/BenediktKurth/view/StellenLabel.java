package de.BenediktKurth.view;

import de.BenediktKurth.control.MainWindowController;
import de.BenediktKurth.model.FarbenEnum;
import de.BenediktKurth.model.IDBase;
import de.BenediktKurth.model.Stellen;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

/**
 *
 * @author clannick
 */
public class StellenLabel extends VerschiebbarLabel {

    private final static int OFFSET = 6;
    private boolean markiertAnfangEnde;
    private FarbenEnum meineFarbe;

    public StellenLabel(Stellen basis, MainWindowController controller, HauptFenster mother) {
        super(basis, controller, mother);
      
        this.markiertAnfangEnde = basis.getMarkiert();

        this.meineFarbe = basis.getMeineFarbe();

        int size = IDBase.getSize() + OFFSET;
        int posX = this.position.getX() - (size / 2);
        int posY = this.position.getY() - (size / 2);

        super.setBounds(posX, posY, size, size);
        super.setToolTipText("Stelle: " + basis.getId() + " (x:" + this.position.getX() + "/y:" + this.position.getY() + ")" );
    

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
        if (markiertAnfangEnde) {
            int size = (IDBase.getSize() + OFFSET) / 2;
            int kreisGroesse = IDBase.getSize() / 8;
            size -= kreisGroesse / 2;

            g.fillOval(size, size, kreisGroesse, kreisGroesse);
        }
        temp.dispose();
        g.dispose();
    }
}
