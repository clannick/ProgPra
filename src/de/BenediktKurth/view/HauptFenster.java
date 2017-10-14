package de.BenediktKurth.view;

import de.BenediktKurth.control.MainWindowController;
import de.BenediktKurth.model.IDBase;
import de.BenediktKurth.model.Transition;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *  Klasse zur graphischen Darstellung des Programmes. 
 * 
 *  Klasse wirft keine Exceptions.
 * 
 *  Plannung:   - Exceptions
 *              - Kommentare
 *              - JavaDoc-Kommentare
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
    
    private boolean istWasMarkiert = false;
    
    private ArrayList<Integer> interneIDmarkierter = new ArrayList<>();
    
    /**
     *  Enthält den Controller für diese Klasse
     * 
     *  @see MainWindowController
     */
    private final MainWindowController controller;
    
    private ArrayList<JLabel> darstellungen = new ArrayList<>();
    
     
    /**
     * Konstruktor mit übergabe des Kontrollers (MVC)
     * 
     * @param controller MainWindowController zur Steuerung des Programmes und Datenänderungen
     * 
     * @see MainWindowController
     */
    public HauptFenster(MainWindowController controller) {
        this.controller = controller;
        initComponents();
        super.setFocusable(true);
        
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
        neuKnopf = new javax.swing.JButton();
        speichernKnopf = new javax.swing.JButton();
        ladenKnopf = new javax.swing.JButton();
        neueVerbindungKnopf = new javax.swing.JButton();
        neueTransitionKnopf = new javax.swing.JButton();
        neueStelleKnopf = new javax.swing.JButton();
        groessenText = new javax.swing.JLabel();
        fehleranzeigeText = new javax.swing.JLabel();
        zeichenflaeche = new javax.swing.JPanel();
        neueVerbindungKnopf1 = new javax.swing.JButton();
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

        neuKnopf.setText("Neu");
        neuKnopf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                neuKnopfActionPerformed(evt);
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

        neueVerbindungKnopf.setMnemonic('L');
        neueVerbindungKnopf.setText("Löschen");
        neueVerbindungKnopf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                neueVerbindungKnopfActionPerformed(evt);
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

        groessenText.setText("Größe");

        zeichenflaeche.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        zeichenflaeche.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                zeichenflaecheMouseClicked(evt);
            }
        });
        zeichenflaeche.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                zeichenflaecheKeyTyped(evt);
            }
        });
        zeichenflaeche.setLayout(null);

        neueVerbindungKnopf1.setText("Neue Verbindung");

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(fehleranzeigeText, javax.swing.GroupLayout.PREFERRED_SIZE, 721, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(zeichenflaeche, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(groessenSchieber, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(neuKnopf, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(speichernKnopf, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ladenKnopf, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(neueTransitionKnopf, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(neueVerbindungKnopf, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fehleranzeigeGross, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(groessenText, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(neueStelleKnopf, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(neueVerbindungKnopf1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(neueStelleKnopf, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(neueTransitionKnopf, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(neueVerbindungKnopf1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)
                        .addComponent(fehleranzeigeGross, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(neueVerbindungKnopf, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 120, Short.MAX_VALUE)
                        .addComponent(groessenText)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(groessenSchieber, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(neuKnopf, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(speichernKnopf, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(zeichenflaeche, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ladenKnopf, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fehleranzeigeText, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        neueVerbindungKnopf.getAccessibleContext().setAccessibleName("Loeschen");
        neueVerbindungKnopf.getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void neueStelleKnopfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_neueStelleKnopfActionPerformed
       controller.newStellen();
    }//GEN-LAST:event_neueStelleKnopfActionPerformed

    private void neueTransitionKnopfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_neueTransitionKnopfActionPerformed
        JLabel test = new BasisLabel(new Transition(), controller, this);
        

        zeichenflaeche.add(test);
        test.setBounds(10, 10, IDBase.getSize()+30, IDBase.getSize()+20);
        
       
    }//GEN-LAST:event_neueTransitionKnopfActionPerformed

    private void neuKnopfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_neuKnopfActionPerformed
        zeichenflaeche.removeAll();
        //controller.getArraySize();
        controller.testeWorkflownetz();
        controller.createView(darstellungen);
        controller.setzteDarstellung(zeichenflaeche, darstellungen);
        zeichenflaeche.repaint();
       // controller.paintComponents(zeichenflaeche);
    }//GEN-LAST:event_neuKnopfActionPerformed

    private void speichernKnopfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_speichernKnopfActionPerformed
        
        JFileChooser chooser;
        if (lastDirectory == null){
           chooser = new JFileChooser();
           
        } else {
           chooser = new JFileChooser(this.lastDirectory); 
        }
        
        int open = chooser.showSaveDialog(null); 
        
        if (open == JFileChooser.APPROVE_OPTION){
            controller.speichern(chooser.getSelectedFile().getAbsolutePath());
            this.lastDirectory = chooser.getSelectedFile().getPath();
        }
    }//GEN-LAST:event_speichernKnopfActionPerformed

    private void groessenSchieberStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_groessenSchieberStateChanged
        zeichenflaeche.removeAll();
        controller.setSize(groessenSchieber.getValue());
        controller.createView(darstellungen);
        controller.setzteDarstellung(zeichenflaeche, darstellungen);
        zeichenflaeche.repaint();
 
    }//GEN-LAST:event_groessenSchieberStateChanged

    private void ladenKnopfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ladenKnopfActionPerformed
        JFileChooser chooser;
        if (lastDirectory == null){
           chooser = new JFileChooser();
           
        } else {
           chooser = new JFileChooser(this.lastDirectory);; 
        }
        
        int open = chooser.showOpenDialog(null); 
        
        if (open == JFileChooser.APPROVE_OPTION){
            controller.laden(chooser.getSelectedFile().getAbsolutePath());
            this.lastDirectory = chooser.getSelectedFile().getPath();
        }
        
        
        
    }//GEN-LAST:event_ladenKnopfActionPerformed

    private void zeichenflaecheMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_zeichenflaecheMouseClicked
            focusZuruecksetzen();
            interneIDmarkierter.removeAll(interneIDmarkierter);
    }//GEN-LAST:event_zeichenflaecheMouseClicked

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed

    }//GEN-LAST:event_formKeyPressed

    private void zeichenflaecheKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_zeichenflaecheKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_zeichenflaecheKeyTyped

    private void neueVerbindungKnopfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_neueVerbindungKnopfActionPerformed
        int i = 0;
        
        while ( i < interneIDmarkierter.size()) {
            controller.removePosNameBase(interneIDmarkierter.get(i));
            i++;
        }
        interneIDmarkierter.removeAll(interneIDmarkierter);
    }//GEN-LAST:event_neueVerbindungKnopfActionPerformed

    public void focusZuruecksetzen(){
        int i = 0;
        while (i < darstellungen.size()){
            BasisLabel temp = (BasisLabel)darstellungen.get(i);
            temp.setFocus(false);
            temp.setBorder(BorderFactory.createEmptyBorder());
            i++;
        }
        interneIDmarkierter.removeAll(interneIDmarkierter);
    }
    
    public void setWurdeGeaender(boolean wurdeGeaender) {
        this.wurdeGeaender = wurdeGeaender;
    }

    public void setIstWasMarkiert(boolean istWasMarkiert) {
        this.istWasMarkiert = istWasMarkiert;
    }

    public ArrayList<Integer> getInterneIDmarkierter() {
        return interneIDmarkierter;
    }

    
    
    public boolean getWurdeGeaender() {
        return wurdeGeaender;
    }

    public boolean getIstWasMarkiert() {
        return istWasMarkiert;
    }


    public JLabel gibFehleranzeigeText(){
        return this.fehleranzeigeText;
    }
    public JLabel gibFehleranzeigeGross(){
        return this.fehleranzeigeGross;
    }
    public JPanel getZeichenflaeche(){
        return this.zeichenflaeche;
    }
    
    public ArrayList<JLabel> getDarstellung(){
        return this.darstellungen;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel fehleranzeigeGross;
    private javax.swing.JLabel fehleranzeigeText;
    private javax.swing.JSlider groessenSchieber;
    private javax.swing.JLabel groessenText;
    private javax.swing.JButton ladenKnopf;
    private javax.swing.JMenu menuEintragBearbeiten;
    private javax.swing.JMenu menuEintragDatei;
    private javax.swing.JMenu menuEintragHilfe;
    private javax.swing.JMenuBar menueLeiste;
    private javax.swing.JButton neuKnopf;
    private javax.swing.JButton neueStelleKnopf;
    private javax.swing.JButton neueTransitionKnopf;
    private javax.swing.JButton neueVerbindungKnopf;
    private javax.swing.JButton neueVerbindungKnopf1;
    private javax.swing.JButton speichernKnopf;
    private javax.swing.JPanel zeichenflaeche;
    // End of variables declaration//GEN-END:variables
}