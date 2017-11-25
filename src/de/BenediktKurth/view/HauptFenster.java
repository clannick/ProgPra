package de.BenediktKurth.view;

import de.BenediktKurth.control.MainWindowController;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

/**
 * Klasse zur graphischen Darstellung des Programmes.
 *
 * Klasse wirft keine Exceptions.
 *
 * Plannung: - Exceptions - Kommentare - JavaDoc-Kommentare
 *
 * @author Benedikt Kurth; NetBeans IDE 8.2 FormEditor
 *
 * @since 1.0
 *
 * @version 1.0
 */
public class HauptFenster extends javax.swing.JFrame {

    private String lastDirectory;

    private boolean wurdeGeaender = false;

    private volatile ArrayList<Integer> interneIDmarkierter = new ArrayList<>();

    private Toolkit desktop = Toolkit.getDefaultToolkit();

    /**
     * Enthält den Controller für diese Klasse
     *
     * @since 1.0
     * 
     * @see MainWindowController
     */
    private final MainWindowController controller;

    private ArrayList<JLabel> darstellungen = new ArrayList<>();

    public final int screenHeight;
    public final int screenWidth;

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

        screenHeight = desktop.getScreenSize().height;
        screenWidth = desktop.getScreenSize().width;
        super.setBounds((screenWidth / 2) - (screenWidth / 4), (screenHeight / 2) - (screenHeight / 4), screenWidth / 2, screenHeight / 2);
        super.setFocusable(true);
        initComponents();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        groessenSchieber = new javax.swing.JSlider();
        fehleranzeigeGross = new javax.swing.JLabel();
        simulationResetKnopf = new javax.swing.JButton();
        speichernKnopf = new javax.swing.JButton();
        ladenKnopf = new javax.swing.JButton();
        loeschenKnopf = new javax.swing.JButton();
        neueTransitionKnopf = new javax.swing.JButton();
        neueStelleKnopf = new javax.swing.JButton();
        groessenText = new javax.swing.JLabel();
        fehleranzeigeText = new javax.swing.JLabel();
        neueVerbindungKnopf = new javax.swing.JButton();
        scrollFenster = new javax.swing.JScrollPane();
        zeichenflaeche = new javax.swing.JLayeredPane();
        umbennenKnopf = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        textHoehe = new javax.swing.JTextField();
        textBreite = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        speichernKnopf1 = new javax.swing.JButton();
        menueLeiste = new javax.swing.JMenuBar();
        menuEintragDatei = new javax.swing.JMenu();
        menuEintragBearbeiten = new javax.swing.JMenu();
        menuEintragHilfe = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Benedikt Kurth 5254540");
        setLocationByPlatform(true);
        setMinimumSize(new java.awt.Dimension(500, 500));
        setName("MainWindow"); // NOI18N
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        groessenSchieber.setMaximum(200);
        groessenSchieber.setMinimum(10);
        groessenSchieber.setToolTipText("");
        groessenSchieber.setValue(100);
        groessenSchieber.setMaximumSize(new java.awt.Dimension(79, 30));
        groessenSchieber.setMinimumSize(new java.awt.Dimension(79, 30));
        groessenSchieber.setPreferredSize(new java.awt.Dimension(79, 30));
        groessenSchieber.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                groessenSchieberStateChanged(evt);
            }
        });

        simulationResetKnopf.setText("Simulation reset");
        simulationResetKnopf.setMaximumSize(new java.awt.Dimension(150, 46));
        simulationResetKnopf.setMinimumSize(new java.awt.Dimension(150, 46));
        simulationResetKnopf.setPreferredSize(new java.awt.Dimension(150, 46));
        simulationResetKnopf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simulationResetKnopfActionPerformed(evt);
            }
        });

        speichernKnopf.setText("Speichern");
        speichernKnopf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                speichernKnopfActionPerformed(evt);
            }
        });

        ladenKnopf.setText("Laden");
        ladenKnopf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ladenKnopfActionPerformed(evt);
            }
        });

        loeschenKnopf.setMnemonic('L');
        loeschenKnopf.setText("Löschen");
        loeschenKnopf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loeschenKnopfActionPerformed(evt);
            }
        });

        neueTransitionKnopf.setText("Neue Transition");
        neueTransitionKnopf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                neueTransitionKnopfActionPerformed(evt);
            }
        });

        neueStelleKnopf.setText("Neue Stelle");
        neueStelleKnopf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                neueStelleKnopfActionPerformed(evt);
            }
        });
        neueStelleKnopf.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                neueStelleKnopfPropertyChange(evt);
            }
        });

        groessenText.setText("Größe");

        fehleranzeigeText.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        neueVerbindungKnopf.setText("Neue Verbindung");
        neueVerbindungKnopf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                neueVerbindungKnopfActionPerformed(evt);
            }
        });

        zeichenflaeche.setBackground(new java.awt.Color(255, 255, 255));
        zeichenflaeche.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        zeichenflaeche.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        zeichenflaeche.setPreferredSize(new java.awt.Dimension(1500, 1500));
        zeichenflaeche.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                zeichenflaecheMouseDragged(evt);
            }
        });
        zeichenflaeche.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                zeichenflaecheMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout zeichenflaecheLayout = new javax.swing.GroupLayout(zeichenflaeche);
        zeichenflaeche.setLayout(zeichenflaecheLayout);
        zeichenflaecheLayout.setHorizontalGroup(
            zeichenflaecheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1500, Short.MAX_VALUE)
        );
        zeichenflaecheLayout.setVerticalGroup(
            zeichenflaecheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1500, Short.MAX_VALUE)
        );

        scrollFenster.setViewportView(zeichenflaeche);

        umbennenKnopf.setMnemonic('L');
        umbennenKnopf.setText("Umbennen");
        umbennenKnopf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                umbennenKnopfActionPerformed(evt);
            }
        });

        textHoehe.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        textHoehe.setText(getSizeWidthString());
        textHoehe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textHoeheActionPerformed(evt);
            }
        });
        textHoehe.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                textHoehePropertyChange(evt);
            }
        });
        textHoehe.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textHoeheKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textHoeheKeyTyped(evt);
            }
        });

        textBreite.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        textBreite.setText(getSizeHeightString());
        textBreite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textBreiteActionPerformed(evt);
            }
        });
        textBreite.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textBreiteKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textBreiteKeyTyped(evt);
            }
        });

        jLabel1.setText("Höhe");

        jLabel2.setText("Breite");

        jLabel3.setText("Arbeitsfläche");

        speichernKnopf1.setText("Speichern");
        speichernKnopf1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                speichernKnopf1ActionPerformed(evt);
            }
        });

        menuEintragDatei.setText("Datei");
        menueLeiste.add(menuEintragDatei);

        menuEintragBearbeiten.setText("Bearbeiten");
        menueLeiste.add(menuEintragBearbeiten);

        menuEintragHilfe.setText("Hilfe");
        menueLeiste.add(menuEintragHilfe);

        setJMenuBar(menueLeiste);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollFenster, javax.swing.GroupLayout.DEFAULT_SIZE, 1002, Short.MAX_VALUE)
                    .addComponent(fehleranzeigeText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addComponent(groessenText))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(umbennenKnopf, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ladenKnopf, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(neueStelleKnopf, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(neueVerbindungKnopf, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(neueTransitionKnopf, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(loeschenKnopf, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(speichernKnopf, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(groessenSchieber, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(simulationResetKnopf, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fehleranzeigeGross, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(speichernKnopf1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(jLabel1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                                    .addComponent(textHoehe, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(textBreite, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(23, 23, 23))
        );
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
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fehleranzeigeGross, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(loeschenKnopf, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(umbennenKnopf, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(simulationResetKnopf, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(167, 167, 167)
                        .addComponent(jLabel3)
                        .addGap(1, 1, 1)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textHoehe, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textBreite, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(groessenText)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(groessenSchieber, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(speichernKnopf1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        loeschenKnopf.getAccessibleContext().setAccessibleName("Loeschen");
        loeschenKnopf.getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void neueStelleKnopfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_neueStelleKnopfActionPerformed
        controller.neueStellen();
    }//GEN-LAST:event_neueStelleKnopfActionPerformed

    private void neueTransitionKnopfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_neueTransitionKnopfActionPerformed
        controller.neueTransition();
    }//GEN-LAST:event_neueTransitionKnopfActionPerformed

    private void simulationResetKnopfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simulationResetKnopfActionPerformed
        controller.simualtionZurucksetzen();

    }//GEN-LAST:event_simulationResetKnopfActionPerformed

    private void speichernKnopfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_speichernKnopfActionPerformed
        controller.speichern();


    }//GEN-LAST:event_speichernKnopfActionPerformed

    private void groessenSchieberStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_groessenSchieberStateChanged

        controller.setzeGlobaleGroesse(groessenSchieber.getValue());
        controller.neueDarstellungOhneTest();

    }//GEN-LAST:event_groessenSchieberStateChanged

    private void ladenKnopfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ladenKnopfActionPerformed
        controller.laden();


    }//GEN-LAST:event_ladenKnopfActionPerformed

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed

    }//GEN-LAST:event_formKeyPressed

    private void loeschenKnopfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loeschenKnopfActionPerformed
        for (Integer x : interneIDmarkierter) {
            controller.entfernen(x);
        }

        interneIDmarkierter.removeAll(interneIDmarkierter);
        controller.neueDarstellungMitTest();
    }//GEN-LAST:event_loeschenKnopfActionPerformed

    private void umbennenKnopfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_umbennenKnopfActionPerformed
        controller.umbenennen(interneIDmarkierter);
    }//GEN-LAST:event_umbennenKnopfActionPerformed

    private void textHoeheKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textHoeheKeyTyped

    }//GEN-LAST:event_textHoeheKeyTyped

    private void textHoeheKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textHoeheKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String inhalt = textHoehe.getText();

            int hoehe = zeichenflaeche.getPreferredSize().height;

            try {
                int temp = Integer.parseInt(inhalt);
                if (temp > -1) {

                    zeichenflaeche.setPreferredSize(new Dimension(temp, hoehe));
                    controller.neueDarstellungOhneTest();
                    scrollFenster.repaint();
                } else {
                    textHoehe.setText(getSizeHeightString());
                    fehleranzeigeText.setText("Bitte geben Sie eine positive ganze Zahl ein.");
                }
            } catch (Exception e) {
                textHoehe.setText(getSizeHeightString());
                fehleranzeigeText.setText("Bitte geben Sie eine ganze Zahl zwischen 0 und " + Integer.MAX_VALUE + " ein.");
            }

        }
    }//GEN-LAST:event_textHoeheKeyPressed

    private void neueVerbindungKnopfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_neueVerbindungKnopfActionPerformed
        controller.neueArc(interneIDmarkierter);
        focusZuruecksetzen();
    }//GEN-LAST:event_neueVerbindungKnopfActionPerformed

    private void textHoeheActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textHoeheActionPerformed

    }//GEN-LAST:event_textHoeheActionPerformed

    private void textBreiteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textBreiteKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_textBreiteKeyTyped

    private void textBreiteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textBreiteKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String inhalt = textBreite.getText();

            int breite = zeichenflaeche.getPreferredSize().height;

            try {
                int temp = Integer.parseInt(inhalt);
                if (temp > -1) {

                    zeichenflaeche.setPreferredSize(new Dimension(breite, temp));
                }
            } catch (Exception e) {
                textBreite.setText(getSizeHeightString());
                fehleranzeigeText.setText("Bitte geben Sie eine ganze Zahl größer 0 ein!");
            }

            controller.neueDarstellungOhneTest();
            scrollFenster.repaint();

        }
    }//GEN-LAST:event_textBreiteKeyPressed

    private void neueStelleKnopfPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_neueStelleKnopfPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_neueStelleKnopfPropertyChange

    private void textHoehePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_textHoehePropertyChange

    }//GEN-LAST:event_textHoehePropertyChange

    private void zeichenflaecheMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_zeichenflaecheMouseClicked
        focusZuruecksetzen();
    }//GEN-LAST:event_zeichenflaecheMouseClicked

    private void zeichenflaecheMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_zeichenflaecheMouseDragged

    }//GEN-LAST:event_zeichenflaecheMouseDragged

    private void textBreiteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textBreiteActionPerformed

    }//GEN-LAST:event_textBreiteActionPerformed

    private void speichernKnopf1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_speichernKnopf1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_speichernKnopf1ActionPerformed

    public void focusZuruecksetzen() {
        interneIDmarkierter.removeAll(interneIDmarkierter);

        controller.neueDarstellungOhneTest();
    }

    private String getSizeHeightString() {
        Integer temp = (int) zeichenflaeche.getPreferredSize().getHeight();
        return temp.toString();
    }

    private String getSizeWidthString() {
        Integer temp = (int) zeichenflaeche.getPreferredSize().getWidth();
        return temp.toString();

    }

    public void setWurdeGeaender(boolean wurdeGeaender) {
        this.wurdeGeaender = wurdeGeaender;
    }

    public ArrayList<Integer> getInterneIDmarkierter() {
        return interneIDmarkierter;
    }

    public boolean getWurdeGeaender() {
        return wurdeGeaender;
    }

    public JLabel getFehleranzeigeText() {
        return this.fehleranzeigeText;
    }

    public JLabel getFehleranzeigeGross() {
        return this.fehleranzeigeGross;
    }

    public JLayeredPane getZeichenflaeche() {
        return this.zeichenflaeche;
    }

    public ArrayList<JLabel> getDarstellung() {
        return this.darstellungen;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel fehleranzeigeGross;
    private javax.swing.JLabel fehleranzeigeText;
    private javax.swing.JSlider groessenSchieber;
    private javax.swing.JLabel groessenText;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JButton ladenKnopf;
    private javax.swing.JButton loeschenKnopf;
    private javax.swing.JMenu menuEintragBearbeiten;
    private javax.swing.JMenu menuEintragDatei;
    private javax.swing.JMenu menuEintragHilfe;
    private javax.swing.JMenuBar menueLeiste;
    private javax.swing.JButton neueStelleKnopf;
    private javax.swing.JButton neueTransitionKnopf;
    private javax.swing.JButton neueVerbindungKnopf;
    private javax.swing.JScrollPane scrollFenster;
    private javax.swing.JButton simulationResetKnopf;
    private javax.swing.JButton speichernKnopf;
    private javax.swing.JButton speichernKnopf1;
    private javax.swing.JTextField textBreite;
    private javax.swing.JTextField textHoehe;
    private javax.swing.JButton umbennenKnopf;
    private javax.swing.JLayeredPane zeichenflaeche;
    // End of variables declaration//GEN-END:variables

}
