package de.BenediktKurth.view;

import de.BenediktKurth.control.MainWindowController;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.*;

/**
 * Klasse zur graphischen Darstellung des Programmes.
 *
 * @author Benedikt Kurth
 *
 * @since 1.0
 *
 * @version 1.1
 */
public class HauptFenster extends JFrame {

    /**
     * Enthält den Controller für diese Klasse
     *
     * @since 1.0
     *
     * @see MainWindowController
     */
    private final MainWindowController          controller;

    /**
     * ArrayList mit allen Darstellungen der Objekte.
     *
     * @since 1.0
     */
    private ArrayList<JLabel>                   darstellungen = new ArrayList<>();

    /**
     * Int-Wert (Final) mit der Bildschirmhöhe des Nutzers.
     *
     * @since 1.0
     */
    public final int                            screenHeight;

    /**
     * Int-Wert (Final) mit der Bildschirmbreite des Nutzers.
     *
     * @since 1.0
     */
    public final int                            screenWidth;

    /**
     * ArrayList mit allen internen ID der markierten Objekten.
     *
     * @since 1.0
     */
    private ArrayList<Integer>                  interneIDmarkierter = new ArrayList<>();

    /**
     * Referenz auf das OS des Nutzers um Bildschirmgröße zu ermitteln.
     *
     * @since 1.0
     */
    private final Toolkit                       desktop = Toolkit.getDefaultToolkit();

    /**
     * JLabel für den Beschreibungstext "Arbeitsfläche".
     *
     * @since 1.0
     */
    private JLabel                              arbeitsflaecheText;

    /**
     * JLabel für die Ausgabe über das evtl vorhanden sein eines Workflownetzes.
     *
     * @since 1.0
     */
    private JLabel                              fehleranzeigeGross;

    /**
     * JLabel für die Fehler-/Informationsausgabe.
     *
     * @since 1.0
     */
    private JLabel                              fehleranzeigeText;

    /**
     * JLabel für den Beschreibungstext "Größe".
     *
     * @since 1.0
     */
    private JLabel                              groessenText;

    /**
     * JLabel für den Beschreibungstext "Höhe".
     *
     * @since 1.0
     */
    private JLabel                              hoeheText;

    /**
     * JLabel für den Beschreibungstext "Breite".
     *
     * @since 1.0
     */
    private JLabel                              breiteText;

    /**
     * JSeparator zur optischen Abtrennung der oberen Fehleranzeige.
     *
     * @since 1.0
     */
    private JSeparator                          obererTeiler;

    /**
     * JSeparator zur optischen Abtrennung der oberen Fehleranzeige.
     *
     * @since 1.0
     */
    private JSeparator                          untererTeiler;

    /**
     * JSlider um die globale Größe von Objekten anpassen zu können.
     *
     * @since 1.0
     */
    private JSlider                             groessenSchieber;

    /**
     * JButton für die Schaltfläche "Laden".
     *
     * @since 1.0
     */
    private JButton                             ladenKnopf;

    /**
     * JButton für die Schaltfläche "Löschen".
     *
     * @since 1.0
     */
    private JButton                             loeschenKnopf;

    /**
     * JButton für die Schaltfläche "Neu".
     *
     * @since 1.0
     */
    private JButton                             neuKnopf;

    /**
     * JButton für die Schaltfläche "Neue Stelle".
     *
     * @since 1.0
     */
    private JButton                             neueStelleKnopf;

    /**
     * JButton für die Schaltfläche "Neue Transition".
     *
     * @since 1.0
     */
    private JButton                             neueTransitionKnopf;

    /**
     * JButton für die Schaltfläche "Neue Verdindung".
     *
     * @since 1.0
     */
    private JButton                             neueVerbindungKnopf;

    /**
     * JButton für die Schaltfläche "Simulation reset".
     *
     * @since 1.0
     */
    private JButton                             simulationResetKnopf;

    /**
     * JButton für die Schaltfläche "Speichern".
     *
     * @since 1.0
     */
    private JButton                             speichernKnopf;

    /**
     * JButton für die Schaltfläche "Umbenennen".
     *
     * @since 1.0
     */
    private JButton                             umbenennenKnopf;
    
    /**
     * JButton für die Schaltfläche "reserve". Diese wird aktuell nicht angezeigt!
     *
     * @since 1.0
     */
    private JButton                             reserve;

    /**
     * JScrollPane um das Scrollen für die Arbeitsfläche zu ermöglichen.
     *
     * @since 1.0
     */
    private JScrollPane                         scrollFenster;

    /**
     * JLayeredPane realisiert die eigentliche Arbeitsfläche auf der der Nutzer
     * arbeiten kann.
     *
     * @since 1.0
     */
    private JLayeredPane                        zeichenflaeche;

    /**
     * JTextField dient zur Eingabe und Anzeige der Arbeitsflächen Breite.
     *
     * @since 1.0
     */
    private JTextField                          eingabeFeldBreite;

    /**
     * JTextField dient zur Eingabe und Anzeige der Arbeitsflächen Höhe.
     *
     * @since 1.0
     */
    private JTextField                          eingabeFeldHoehe;

    /**
     * Konstruktor mit übergabe des Kontrollers (MVC)
     *
     * @param controller MainWindowController zur Steuerung des Programmes und
     * Datenänderungen
     *
     * @see MainWindowController
     */
    public HauptFenster(MainWindowController controller) {
        this.controller = controller;

        this.screenHeight = desktop.getScreenSize().height;
        this.screenWidth = desktop.getScreenSize().width;

        setBounds(0, 0, screenWidth / 2, screenHeight / 2);
        setzeObjekteLayout();

    }

    /**
     * Diese Methode erstellt alle Schaltflächen und Anzeigefelder für das
     * Programm. Weiter setzt die Methode das Layout des Programmes.
     *
     * @since 1.0
     */
    private void setzeObjekteLayout() {

        //Initalisiere variablen
        groessenSchieber = new JSlider();
        fehleranzeigeGross = new JLabel();
        simulationResetKnopf = new JButton();
        speichernKnopf = new JButton();
        ladenKnopf = new JButton();
        loeschenKnopf = new JButton();
        neueTransitionKnopf = new JButton();
        neueStelleKnopf = new JButton();
        groessenText = new JLabel();
        fehleranzeigeText = new JLabel();
        neueVerbindungKnopf = new JButton();
        scrollFenster = new JScrollPane();
        zeichenflaeche = new JLayeredPane();
        umbenennenKnopf = new JButton();
        obererTeiler = new JSeparator();
        untererTeiler = new JSeparator();
        eingabeFeldHoehe = new JTextField();
        eingabeFeldBreite = new JTextField();
        hoeheText = new JLabel();
        breiteText = new JLabel();
        arbeitsflaecheText = new JLabel();
        neuKnopf = new JButton();
        reserve = new JButton();

        //Setze Fensterwerte
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("5254540_kurth_benedikt");
        setLocationByPlatform(true);
        setMinimumSize(new java.awt.Dimension(700, 700));
        setName("5254540_kurth_benedikt");
        setFocusable(true);

        //Größenschieber für globale Darstellung
        groessenSchieber.setMaximum(200);
        groessenSchieber.setMinimum(10);
        groessenSchieber.setToolTipText("<- verkleinern | vergroeßern ->");
        groessenSchieber.setValue(100);
        groessenSchieber.setMaximumSize(new java.awt.Dimension(79, 30));
        groessenSchieber.setMinimumSize(new java.awt.Dimension(79, 30));
        groessenSchieber.setPreferredSize(new java.awt.Dimension(79, 30));
        groessenSchieber.addChangeListener(new javax.swing.event.ChangeListener() {
            @Override
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                groessenSchieber(evt);
            }
        });

        //Schaltflächen von oben nach unten
        neueStelleKnopf.setText("Neue Stelle");
        neueStelleKnopf.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                neueStelleKnopf(evt);
            }
        });
        neueTransitionKnopf.setText("Neue Transition");
        neueTransitionKnopf.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                neueTransitionKnopf(evt);
            }
        });

        neueVerbindungKnopf.setText("Neue Verbindung");
        neueVerbindungKnopf.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                neueVerbindungKnopf(evt);
            }
        });

        loeschenKnopf.setText("Loeschen");
        loeschenKnopf.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loeschenKnopf(evt);
            }
        });

        umbenennenKnopf.setText("Umbenennen");
        umbenennenKnopf.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                umbenennenKnopf(evt);
            }
        });

        simulationResetKnopf.setText("Simulation reset");
        simulationResetKnopf.setMaximumSize(new java.awt.Dimension(150, 46));
        simulationResetKnopf.setMinimumSize(new java.awt.Dimension(150, 46));
        simulationResetKnopf.setPreferredSize(new java.awt.Dimension(150, 46));
        simulationResetKnopf.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simulationResetKnopf(evt);
            }
        });

        //Dient einem evtl erweitern des Programmes
        reserve.setVisible(false);
        reserve.setText("reserve");
        reserve.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reserveKnopf(evt);
            }

            private void reserveKnopf(ActionEvent evt) {
                //Reserve Methode
            }
        });
        
        neuKnopf.setText("Neu");
        neuKnopf.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                neuKnopfActionPerformed(evt);
            }
        });

        speichernKnopf.setText("Speichern");
        speichernKnopf.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                speichernKnopf(evt);
            }
        });

        ladenKnopf.setText("Laden");
        ladenKnopf.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ladenKnopf(evt);
            }
        });

        //Teiler für Menustruktur
        obererTeiler.setRequestFocusEnabled(false);
        untererTeiler.setRequestFocusEnabled(false);

        //Eingabefelder für die Arbeitsflächengröße
        eingabeFeldHoehe.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        eingabeFeldHoehe.setText(getSizeWidthString());
        eingabeFeldHoehe.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                arbeitsflaecheHoeheAendern(evt);
            }
        });

        eingabeFeldBreite.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        eingabeFeldBreite.setText(getSizeHeightString());
        eingabeFeldBreite.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textBreiteKeyPressed(evt);
            }
        });

        //Beschriftungen
        hoeheText.setText("Hoehe");
        breiteText.setText("Breite");
        groessenText.setText("Groeße");
        arbeitsflaecheText.setText("Arbeitsflaeche");
        fehleranzeigeGross.setFont(new java.awt.Font("Tahoma", 0, 12));
        fehleranzeigeText.setFont(new java.awt.Font("Tahoma", 0, 14));
        eingabeFeldBreite.setText("1500");
        eingabeFeldHoehe.setText("1500");

        //Eigenschaften Zeichenfläche
        zeichenflaeche.setBackground(new java.awt.Color(255, 255, 255));
        zeichenflaeche.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        zeichenflaeche.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        zeichenflaeche.setPreferredSize(new java.awt.Dimension(1500, 1500));
        //Ist für die Focus-Funktion (Markieren von Objekten notwendig)
        zeichenflaeche.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                zeichenflaecheMouseClicked(evt);
            }
        });
        zeichenflaeche.setLayout(null);
        
        //Füge dem Scrollbaren-Fenster die Zeichfläche hinzu
        scrollFenster.setViewportView(zeichenflaeche);

        //Layout setzen mit GroupLayout (erstellt von FormEditor)
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        
        //Setze horizontale Positionen
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollFenster, javax.swing.GroupLayout.DEFAULT_SIZE, 1002, Short.MAX_VALUE)
                    .addComponent(fehleranzeigeText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(obererTeiler, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(umbenennenKnopf, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ladenKnopf, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(neueStelleKnopf, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(neueVerbindungKnopf, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(neueTransitionKnopf, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(loeschenKnopf, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(speichernKnopf, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(groessenSchieber, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(simulationResetKnopf, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fehleranzeigeGross, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(neuKnopf, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(reserve, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(groessenText))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(untererTeiler, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(arbeitsflaecheText)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(hoeheText)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                                    .addComponent(eingabeFeldHoehe, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(breiteText)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(eingabeFeldBreite, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(23, 23, 23))
        );
        
        //Setze vertikale Positionen
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(neueStelleKnopf, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(neueTransitionKnopf, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(neueVerbindungKnopf, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(obererTeiler, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fehleranzeigeGross, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(untererTeiler, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(loeschenKnopf, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(umbenennenKnopf, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(simulationResetKnopf, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(reserve, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 143, Short.MAX_VALUE)
                        .addComponent(arbeitsflaecheText)
                        .addGap(1, 1, 1)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(eingabeFeldHoehe, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(hoeheText, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(eingabeFeldBreite, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(breiteText, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(groessenText)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(groessenSchieber, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(neuKnopf, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(speichernKnopf, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(scrollFenster, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ladenKnopf, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fehleranzeigeText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }

    /**
     * Diese Methode wird beim betätigen der Schaltfläche "Neue Stelle"
     * aufgerufen. Die Methode ruft eine passende Methode des Controllers auf.
     *
     * @since 1.0
     *
     * @param evt Info über Aktion des Nutzers (wird nicht verwendet)
     */
    private void neueStelleKnopf(java.awt.event.ActionEvent evt) {
        controller.neueStellen();
        focusZuruecksetzen();
    }

    /**
     * Diese Methode wird beim betätigen der Schaltfläche "Neue Transition"
     * aufgerufen. Die Methode ruft eine passende Methode des Controllers auf
     * und ruft die Methode focusZuruecksetzen() auf, damit werden alle Objekte
     * abgewählt.
     *
     * @since 1.0
     *
     * @param evt Info über Aktion des Nutzers (wird nicht verwendet)
     */
    private void neueTransitionKnopf(java.awt.event.ActionEvent evt) {
        controller.neueTransition();
        focusZuruecksetzen();
    }

    /**
     * Diese Methode wird beim betätigen der Schaltfläche "Neue Verbindung"
     * aufgerufen. Die Methode ruft eine passende Methode des Controllers auf
     * und ruft die Methode focusZuruecksetzen() auf, damit werden alle Objekte
     * abgewählt.
     *
     * @since 1.0
     *
     * @param evt Info über Aktion des Nutzers (wird nicht verwendet)
     */
    private void neueVerbindungKnopf(java.awt.event.ActionEvent evt) {
        controller.neueArc(interneIDmarkierter);
        focusZuruecksetzen();
    }

    /**
     * Diese Methode wird beim betätigen der Schaltfläche "Simulation reset"
     * aufgerufen. Die Methode ruft eine passende Methode des Controllers auf.
     *
     * @since 1.0
     *
     * @param evt Info über Aktion des Nutzers (wird nicht verwendet)
     */
    private void simulationResetKnopf(java.awt.event.ActionEvent evt) {
        controller.simulationZurucksetzen();
        focusZuruecksetzen();
    }

    /**
     * Diese Methode wird beim betätigen der Schaltfläche "Neu" aufgerufen. Die
     * Methode ruft eine passende Methode des Controllers auf. Das alte
     * Workflownetz wird gelöscht und die Arbeitsfläche auf 1500x1500 gesetzt.
     *
     * @since 1.0
     *
     * @param evt Info über Aktion des Nutzers (wird nicht verwendet)
     */
    private void neuKnopfActionPerformed(java.awt.event.ActionEvent evt) {
        WarnungNeu temp = new WarnungNeu(controller, screenHeight, screenWidth);
    }

    /**
     * Diese Methode wird beim betätigen der Schaltfläche "Speichern"
     * aufgerufen. Die Methode ruft eine passende Methode des Controllers auf.
     *
     * @since 1.0
     *
     * @param evt Info über Aktion des Nutzers (wird nicht verwendet)
     */
    private void speichernKnopf(java.awt.event.ActionEvent evt) {
        controller.speichern();
        focusZuruecksetzen();
    }

    /**
     * Diese Methode wird beim betätigen der Schaltfläche "Laden" aufgerufen.
     * Die Methode ruft eine passende Methode des Controllers auf.
     *
     * @since 1.0
     *
     * @param evt Info über Aktion des Nutzers (wird nicht verwendet)
     */
    private void ladenKnopf(java.awt.event.ActionEvent evt) {
        controller.testLaden();
        focusZuruecksetzen();
    }

    /**
     * Diese Methode wird beim betätigen des Schiebereglers aufgerufen. Die
     * Methode ruft eine passende Methode des Controllers auf. Die Darstellung
     * der Objekte wird damit verkleiner bzw. vergrößert.
     *
     * @since 1.0
     *
     * @param evt Info über Aktion des Nutzers (wird nicht verwendet)
     */
    private void groessenSchieber(javax.swing.event.ChangeEvent evt) {
        controller.setzeGlobaleGroesse(groessenSchieber.getValue());
        controller.neueDarstellungOhneTest();
    }

    /**
     * Diese Methode wird beim betätigen der Schaltfläche "Löschen" aufgerufen.
     * Die Methode ruft eine passende Methode des Controllers auf.
     *
     * @since 1.0
     *
     * @param evt Info über Aktion des Nutzers (wird nicht verwendet)
     */
    private void loeschenKnopf(java.awt.event.ActionEvent evt) {
        ArrayList<Integer> tempListe = new ArrayList<>();
        
        for (Integer x : interneIDmarkierter) {
            tempListe.add(x);
        } 
        
        for (Integer x : tempListe) {
            controller.entfernen(x);
        }
        focusZuruecksetzen();
    }

    /**
     * Diese Methode wird beim betätigen der Schaltfläche "Umbenennen"
     * aufgerufen. Die Methode ruft eine passende Methode des Controllers auf.
     *
     * @since 1.0
     *
     * @param evt Info über Aktion des Nutzers (wird nicht verwendet)
     */
    private void umbenennenKnopf(java.awt.event.ActionEvent evt) {
        controller.umbenennen(interneIDmarkierter);
        focusZuruecksetzen();
    }

    /**
     * Diese Methode prüft, ob eine Eingabe für die Höhe der Arbeitsfläche
     * konform ist. Weiter prüft die Methode, ob die Zahl größer als 200 ist.
     * Wenn nicht wird sie auf 200 gesetzt. Es werden entsprechende Methoden des
     * Controllers aufgerufen.
     *
     * @since 1.0
     *
     * @param evt Info über Aktion des Nutzers (wird nicht verwendet)
     */
    private void arbeitsflaecheHoeheAendern(java.awt.event.KeyEvent evt) {
        //Wenn die Enter-Taste gedrückt wurde
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String inhalt = eingabeFeldHoehe.getText();

            //Speicher alten Wert des Eingabefensters
            int breite = zeichenflaeche.getPreferredSize().height;

            try {
                int temp = Integer.parseInt(inhalt);
                if (temp > 200) {
                    //Setze gewünschte Höhe
                    zeichenflaeche.setPreferredSize(new Dimension(breite, temp));

                } else {
                    //Hoehe war kleiner 200, also 200
                    eingabeFeldHoehe.setText("200");
                    zeichenflaeche.setPreferredSize(new Dimension(breite, 200));
                }

            } catch (NumberFormatException e) {
                //Fehler! Info an Nutzer und setzte alten Text zurück
                eingabeFeldHoehe.setText(getSizeHeightString());
                fehleranzeigeText.setText("Bitte geben Sie eine ganze Zahl ein.");
            }
            //Mehtodenaufruf Controller
            controller.passeObjektPositionenAn();
            controller.neueDarstellungOhneTest();
        }
    }

    /**
     * Diese Methode prüft, ob eine Eingabe für die Breite der Arbeitsfläche
     * konform ist. Weiter prüft die Methode, ob die Zahl größer als 200 ist.
     * Wenn nicht wird sie auf 200 gesetzt. Es werden entsprechende Methoden des
     * Controllers aufgerufen.
     *
     * @since 1.0
     *
     * @param evt Info über Aktion des Nutzers (wird nicht verwendet)
     */
    private void textBreiteKeyPressed(java.awt.event.KeyEvent evt) {
        //Wenn die Enter-Taste gedrückt wurde
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String inhalt = eingabeFeldBreite.getText();

            //Speicher alten Wert des Eingabefensters
            int hoehe = zeichenflaeche.getPreferredSize().height;

            try {
                int temp = Integer.parseInt(inhalt);
                if (temp > 200) {
                    //Setze gewünschte Breite
                    zeichenflaeche.setPreferredSize(new Dimension(temp, hoehe));
                } else {
                    //Breite war kleiner 200, also 200
                    zeichenflaeche.setPreferredSize(new Dimension(200, hoehe));
                    eingabeFeldBreite.setText("200");
                }
            } catch (NumberFormatException e) {
                //Fehler! Info an Nutzer und setzte alten Text zurück
                eingabeFeldBreite.setText(getSizeHeightString());
                fehleranzeigeText.setText("Bitte geben Sie eine ganze Zahl ein.");
            }
            //Mehtodenaufruf Controller
            controller.passeObjektPositionenAn();
            controller.neueDarstellungOhneTest();
        }
    }

    /**
     * Die Methode wird beim Klicken auf die Zeichenfläche ausgelöst. Der Focus
     * wird zurückgesetzt, es werden alle markierten Objekte abgewählt.
     *
     * @param evt Info über Aktion des Nutzers (wird nicht verwendet)
     *
     * @since 1.0
     */
    private void zeichenflaecheMouseClicked(java.awt.event.MouseEvent evt) {
        focusZuruecksetzen();
    }

    /////////////////////// Hilfsmethoden ///////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * Hilfsmethode, um alle Objekte abzuwählen. Die Methode löscht alle
     * Einträge aus dem internen Array und ruft Methode für neue Darstellung des
     * Kontrollers auf.
     *
     * @since 1.0
     */
    public void focusZuruecksetzen() {
        interneIDmarkierter.removeAll(interneIDmarkierter);
        controller.neueDarstellungOhneTest();
    }

    /**
     * Hilfsmethode, die Methode gibt die bevorzugte Höhe der Zeichenfläche.
     *
     * @return String der Höhe der Zeichenfläche
     *
     * @since 1.0
     */
    private String getSizeHeightString() {
        Integer temp = (int) zeichenflaeche.getPreferredSize().getHeight();
        return temp.toString();
    }

    /**
     * Hilfsmethode, die Methode gibt die bevorzugte Breite der Zeichenfläche.
     *
     * @return String der Breite der Zeichenfläche
     *
     * @since 1.0
     */
    private String getSizeWidthString() {
        Integer temp = (int) zeichenflaeche.getPreferredSize().getWidth();
        return temp.toString();
    }

    /**
     * Hilfsmethode, die Methode gibt ein Array mit allen internen IDs von
     * markierten Objekten.
     *
     * @return ArrayList mit internen IDs der markierten Objekte
     *
     * @since 1.0
     */
    public ArrayList<Integer> getInterneIDmarkierter() {
        return interneIDmarkierter;
    }

    /**
     * Hilfsmethode, die Methode gibt den unteren Fehlertext des Programms.
     *
     * @return JLabel für die untere Fehlertextanzeige
     *
     * @since 1.0
     */
    public JLabel getFehleranzeigeText() {
        return fehleranzeigeText;
    }

    /**
     * Hilfsmethode, die Methode gibt den oberen Fehlertext des Programms.
     *
     * @return JLabel für die obere Fehlertextanzeige
     *
     * @since 1.0
     */
    public JLabel getFehleranzeigeGross() {
        return fehleranzeigeGross;
    }

    /**
     * Hilfsmethode, die Methode übergibt eine Referenz auf die Zeichenfläche
     * des Programms.
     *
     * @return JLayeredPane mit der Zeichenfläche des Programms.
     *
     * @since 1.0
     */
    public JLayeredPane getZeichenflaeche() {
        return zeichenflaeche;
    }
    
    /**
     * Hilfsmethode, die Methode übergibt eine Referenz auf die ScrollFläche
     * des Programms.
     *
     * @return JScrollPane mit der Zeichenfläche des Programms.
     *
     * @since 1.1
     */
    public JScrollPane getScrollFlaeche() {
        return scrollFenster;
    }

    /**
     * Hilfsmethode, die Methode gibt ein Array mit allen Darstellungen der
     * Objekte.
     *
     * @return ArrayList alle Darstellungen die derzeit angezeigt werden.
     *
     * @since 1.0
     */
    public ArrayList<JLabel> getDarstellung() {
        return darstellungen;
    }

    /**
     * Hilfsmethode, die Methode übergibt eine Referenz auf das Eingabefeld für
     * die Arbeitsflächen Breite.
     *
     * @return JTextField Breite der Arbeitsfläche als String
     *
     * @since 1.0
     */
    public JTextField getTextBreite() {
        return eingabeFeldBreite;
    }

    /**
     * Hilfsmethode, die Methode übergibt eine Referenz auf das Eingabefeld für
     * die Arbeitsflächen Höhe.
     *
     * @return JTextField Höhe der Arbeitsfläche als String
     *
     * @since 1.0
     */
    public JTextField getTextHoehe() {
        return eingabeFeldHoehe;
    }
}