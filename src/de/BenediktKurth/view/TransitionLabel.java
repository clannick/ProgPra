package de.BenediktKurth.view;

import de.BenediktKurth.control.MainWindowController;
import de.BenediktKurth.model.FarbenEnum;
import de.BenediktKurth.model.IDBase;
import de.BenediktKurth.model.Transition;
import java.awt.Color;
import java.awt.Graphics;

/**
 * Diese Klasse realisiert die Darstellung einer Transition aus den
 * Basisdatenmodel.
 *
 * @author Benedikt Kurth
 *
 * @version 1.0
 *
 * @since 1.0
 *
 * @see FarbenEnum
 * @see Transition
 * @see MainWindowController
 * @see HauptFenster
 */
public class TransitionLabel extends VerschiebbarLabel {

    /**
     * Farbwert für das Objekt. Auswahl aus FarbenEnum, verändert die
     * Hintergrundfarbe des Kreises.
     *
     * @since 1.0
     */
    private final FarbenEnum meineFarbe;

    /**
     * Vollständiger Konstruktor. Erhält Referenz des Basisdatenmodel (speichert
     * diese aber nicht), weiter wird der Klasse "sein" Kontroller und das
     * HauptFenster mitgeteilt. Konstruktor setzt die Farbe des Objektes.
     *
     * @since 1.0
     *
     * @param basis Referenz auf Basisdatenmodel.
     * @param controller Referenz auf Kontroller.
     * @param mother Referenz auf HauptFenster.
     *
     * @see MainWindowController
     * @see HauptFenster
     * @see Transition
     */
    public TransitionLabel(Transition basis, MainWindowController controller, HauptFenster mother) {
        //Rufe Konstruktor von VerschiebbarLabel auf
        super(basis, controller, mother);

        //Setzte Farbe
        this.meineFarbe = basis.gibMeineFarbe();

        //Ermittle Größe und Position des Objektes
        int size = IDBase.gibGroesse() + 21;
        int posX = this.position.gibX() - (size / 2);
        int posY = this.position.gibY() - ((size - 20) / 2);

        if (basis.gibLabel().equals("null") || basis.gibLabel().equals("")) {
            //Ohne Text

            //Setze Objektgröße und Position
            super.setBounds(posX, posY, size, size - 19);

            //Setze Mausover-Text
            super.setToolTipText("Transition: " + basis.gibID());

        } else {
            //Mit Text
            
            //Setze Objektgröße und Position
            super.setBounds(posX, posY, size, size);

            //Setze Text der Transition und Mausover-Text
            super.setText(basis.gibLabel());
            super.setToolTipText("Transition: " + basis.gibID() + " Label: " + basis.gibLabel());

            //Setze Ausrichtung innerhalb des Objektes
            super.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            super.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
            super.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        }
    }

    /**
     * Diese Methode überschreibt die paint Mehtode der Superklasse, damit
     * Transitions gezeichnet werden.
     *
     * @since 1.0
     *
     * @param g Referenz auf Grafik (s. JavaDoc)
     *
     */
    @Override
    public void paint(Graphics g) {
        //Paintmethode JLabel, für sichere Konsitenz
        super.paint(g);

        //Setze Farbe des Objektes
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

        //Zeichne einen farbigen ausgefülltes Viereck
        g.fillRect(10, 0, IDBase.gibGroesse(), IDBase.gibGroesse());

        //Setze Farbe auf Schwarz und zeichne einen leeres Viereck
        g.setColor(Color.black);
        g.drawRect(10, 0, IDBase.gibGroesse(), IDBase.gibGroesse());

        //Vernichte Grafik
        g.dispose();
    }
}
