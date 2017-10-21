package de.BenediktKurth.control;

import de.BenediktKurth.Exceptions.ArcFehlerException;
import de.BenediktKurth.Exceptions.FileNotLoadException;
import de.BenediktKurth.model.Adjazenzmatrix;
import de.BenediktKurth.model.Arc;
import de.BenediktKurth.model.ArrayListSearchID;
import de.BenediktKurth.model.PosNameBase;
import de.BenediktKurth.model.Stellen;
import de.BenediktKurth.model.Transition;
import de.BenediktKurth.Exceptions.WorkflownetzException;
import de.BenediktKurth.model.FarbenEnum;
import de.BenediktKurth.model.IDBase;
import de.BenediktKurth.model.PNMLParser;
import de.BenediktKurth.model.PNMLWriter;
import de.BenediktKurth.model.Vector2D;
import de.BenediktKurth.view.ArcLabel;
import de.BenediktKurth.view.HauptFenster;
import de.BenediktKurth.view.PfeileDarstellung;
import de.BenediktKurth.view.StellenLabel;
import de.BenediktKurth.view.TransitionLabel;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

/**
 * Der Controller des Programmes. Er verbindet die GUI mit dem Basisdatenmodel.
 * 
 * @author Benedikt Kurth
 *
 * @since 1.0
 *
 * @version 1.0
 * 
 * @see Adjazenzmatrix
 * @see Vector2D
 * @see ArrayListSearchID
 */
public class MainWindowController {

    /**
     * Referenz auf die Benutzeroberfläche.
     * 
     * @since 1.0
     */
    private HauptFenster window;
    
    /**
     * Internes Speicherarray mit den Basisdaten aller Stellen, Transitions und Kanten.
     * 
     * @since 1.0
     */
    private ArrayListSearchID<IDBase> speicherArray;
    
    /**
     * Interne Adjazenzmatrix zur Prüfung des Workflownetzes.
     * 
     * @since 1.0
     */
    private Adjazenzmatrix adjazenzMatrix;


    /**
     * Leerer Konstruktor zur Initalizierung des Controllers.
     * 
     * @since 1.0
     */
    public MainWindowController() {
        super();
    }

    /**
     * Diese Methode muss bei Programmstart in der Main-Methode aufgerufen werden.
     * Da die Benutzeroberfläche und der Controller eine gegenseitige Referenz benötigen
     * und nicht beide gleichzeitig instanziert werden können, muss mit dieser Methode
     * die Benutzeroberfläche am Controller "angemeldet" werden.
     * Weiter wird eine ArrayListSearchID und eine Adjazenzmatrix eingerichtet, diese beinhalten
     * die Programmrelevantendaten des Basisdatenmodels.
     * 
     * @since 1.0
     * 
     * @param window
     * 
     * @param speicherArray 
     * 
     * @see HauptFenster
     * @see ArrayListSearchID
     * @see Adjazenzmatrix
     */
    public void setComponents(HauptFenster window, ArrayListSearchID<IDBase> speicherArray) {
        this.window = window;
        this.speicherArray = speicherArray;
        this.adjazenzMatrix = new Adjazenzmatrix(speicherArray);
    }

    /**
     * Diese Methode erzeugt neue Stellen und übergibt sie der Benutzeroberfläche und
     * dem Basisdatenmodel.
     * 
     * @since 1.0
     * 
     * @see Stellen
     */
    public void newStellen() {
        Stellen neueStelle = new Stellen();

        this.speicherArray.add(neueStelle);
        neueDarstellungMitTest();
        

    }

    /**
     * Diese Methode erzeugt neue Transitions und übergibt sie der Benutzeroberfläche und
     * dem Basisdatenmodel.
     * 
     * @since 1.0
     * 
     * @see Transition
     */
    public void newTransition() {
        Transition neueTransition = new Transition();

        speicherArray.add(neueTransition);
        neueDarstellungMitTest();
    }

    /**
     * Diese Methode erzeugt neue Kanten und übergibt sie der Benutzeroberfläche und
     * dem Basisdatenmodel. Sollten dabei Fehler entstehen informiert diese Methode 
     * zusätzlich den Benutzer über die Art des Fehlers.
     * 
     * @param source    String  Referenz zum Quellobjekt (ID)
     * @param target    String  Referenz zum Zielobjekt (ID)
     * 
     * @since 1.0
     * 
     * @see Arc
     * @see ArcFehlerException
     */    
    public void newArc(String source, String target) {
        Arc neueArc;
        try {
            // Erstelle neue Kante, hierbei entsteht ArcFehlerException
            neueArc = new Arc(source, target, this.speicherArray);
            
            //Speicher Kante wenn vorhanden
            this.speicherArray.add(neueArc);
            
            //Erzeuge neue Darstellung mit Test auf Workflownetz
            neueDarstellungMitTest();
            
        } catch (ArcFehlerException ex) {
            //Informiere Nutzer über einen Fehler
            window.getFehleranzeigeGross().setText("Verbindung konnte nicht erstellt werden");
        }

    }

    /**
     * Die Mehtode setzt die globale Größe für Stellen, Transitions und Pfeilspitzen.
     * Hierfür wird der Mehtode ein Faktor(in %) übergeben und IDBase berechnet die Größe.
     * 
     * @since 1.0
     * 
     * @param faktor int Parameter enthält die Prozentualle relative Größe aller Obejkte (exkl. Kanten).
     * 
     * @see IDBase
     */
    public void setSize(int faktor) {
        IDBase.setSize(faktor);
    }

    /**
     * Die Methode testet mithilfe der Adjazenzmatrix und ihrer Methoden auf vorhandensein
     * eines Workflownetzes. Weiter informiert die Methode durch die WorkflownetzException
     * den Benutzer über einen möglichen Grund, warum das vorliegende Netz kein Workflownetz ist
     * oder es wird das vorhanden sein ein Workflownetz bestätigtö.
     * 
     * @since 1.0
     *      
     * @see Adjazenzmatrix
     * @see WorkflownetzException
     */
    public void testeWorkflownetz() {

        //adjazenzMatrix wird zurückgestzt und mit aktuellem Basisdaten instanziert
        adjazenzMatrix = new Adjazenzmatrix(this.speicherArray);
        try {
            if (adjazenzMatrix.pruefeWorkflownetz()) {
                //Keine Fehler -> Nutzer wird informiert
                window.getFehleranzeigeText().setText("Keine Fehler.");
                window.getFehleranzeigeGross().setText("Es ist ein Workflownetz.");
            }
        } catch (WorkflownetzException ex) {
            //Fehler -> Nutzer wird informiert
            window.getFehleranzeigeText().setText(ex.getMessage());
            window.getFehleranzeigeGross().setText("Es ist kein Workflownetz.");
        }
    }

    /**
     * Die Methode erledigt für den Nutzer das laden einer Datei in den 
     * Workflownetzeditor.
     * Es wird der Methode ein absoluter Pfad zur zu ladenden Datei übergeben.
     * Weiter setzt die Methode des IDBase Counter auf 0 zurück, damit die internenIDs
     * wieder bei 0 beginnen. Die alten Basisdaten werden überschrieben.
     * 
     * @since 1.0
     * 
     * @param absoluterPfad String  Referenz mit dem Dateipfad
     * 
     * @see IDBase
     * @see PNMLParser
     * @see FileNotLoadException
     */
    public void laden(String absoluterPfad) {
        //Setzte Counter auf 0
        IDBase.resetIdCounter();
        
        //Öffne und lade Datei in das speicherArray
        try {
            this.speicherArray = PNMLParser.loadAndGet(absoluterPfad);
        } catch (FileNotLoadException e) {
            //Bei Fehlern soll Nutzer informiert werden
            window.getFehleranzeigeText().setText(e.getMessage());
        }  catch (Exception e){
            
        }

    }

    /**
     * Die Methode erledigt für den Nutzer das speichern einer Datei.
     * Es wird der Methode ein absoluter Pfad zu der gewünschten Datei übergeben.
     * Die Methode speichert automatisch die im speicherArray hinterlegten Basisdaten. 
     * 
     * @since 1.0
     * 
     * @param absoluterPfad String  Referenz mit dem Dateipfad
     * 
     * 
     * @see PNMLWriter
     * 
     */
    public void speichern(String absoluterPfad) {
        PNMLWriter.saveFile(absoluterPfad, speicherArray);
    }

    /**
     * Die Methode erstellt auf Grundlage der Basisdaten eine entprechende Darstellung des
     * jeweiligen Objektes.
     * Zu jeder Stelle, Transition und Kante in den Basisdaten wird sein grafisches 
     * Gegenstück erzeugt und gespeichert.
     * 
     * @since 1.0
     * 
     * @param darstellungen 
     * 
     * @see ArrayList
     */
    public void createView(ArrayList<JLabel> darstellungen) {
        
        //Entferne alle alten Darstellungen
        darstellungen.removeAll(darstellungen);

        //Durchlaufe alle Basisdaten
        for (IDBase x: this.speicherArray){
            if (x instanceof Stellen) {
                    JLabel stellenLabel = new StellenLabel((Stellen) x, this, window);

                    darstellungen.add(stellenLabel);

                } else if (x instanceof Transition) {
                    JLabel transitionLabel = new TransitionLabel((Transition) x, this, window);

                    darstellungen.add(transitionLabel);

                } else if (x instanceof Arc) {
                    JLabel arcLabel = new ArcLabel((Arc) x, this, window);
                    PfeileDarstellung pfeil = new PfeileDarstellung((Arc) x, this);

                    darstellungen.add(arcLabel);
                    darstellungen.add(pfeil);
                }    
        }
    }

    /**
     * Die Methode setzt die Darstellung auf die Zeichenfläche. Zuvor werden die 
     * Pfeilspitzen jedoch aussortiert, damit diese in den Hintergrund gezeichnet werden können
     *      * 
     * @since 1.0
     * 
     * @param panel
     * @param darstellungen 
     * 
     * @see ArrayList
     */
    public void setzteDarstellung(JLayeredPane panel, ArrayList<JLabel> darstellungen) {
        int i = 0;

        ArrayList<Integer> panelTest= new ArrayList<>();
        
        while (i < darstellungen.size()) {
            JLabel temp = darstellungen.get(i);
            if (temp instanceof PfeileDarstellung){
                panelTest.add(i);
                i++;
             
                continue;
            }
   
            i++;
        }
        
        
        i = 0;
        while (i < darstellungen.size()) {
            JLabel temp = darstellungen.get(i);
            if (temp instanceof PfeileDarstellung){
                i++;
                continue;
            }
            
            panel.add(temp);
            i++;
        }
        
        i = 0;
        while (i < panelTest.size()) {
            JLabel temp = darstellungen.get(panelTest.get(i));
            panel.add(temp);
            i++;
        }
    }

    public void findeNachfolger(java.awt.event.MouseEvent evt, int interneID) {

        if ((speicherArray.getWithInternID(interneID).getMeineFarbe() == FarbenEnum.rot)
                || speicherArray.getWithInternID(interneID) instanceof Transition) {
            //Rote Stelle oder eine Transition
            neueDarstellungOhneTest();
        } else {

            for (IDBase x : speicherArray) {
                x.setMeineFarbe(FarbenEnum.weiss);

            }

            IDBase jetziger = speicherArray.getWithInternID(interneID);
            ArrayList<Integer> ArrayListTemp = adjazenzMatrix.getNachfolger(interneID);

            boolean bedingungEins = 1 == ArrayListTemp.size();

            boolean bedingungVier = jetziger.getMeineFarbe() == FarbenEnum.weiss;

            try {
                if (adjazenzMatrix.pruefeWorkflownetz() && bedingungVier) {
                    jetziger.setMeineFarbe(FarbenEnum.gruen);
                }

                boolean bedingungZwei = jetziger.getMeineFarbe() == FarbenEnum.gruen;
                boolean bedingungDrei = jetziger.getMeineFarbe() == FarbenEnum.gelb;

                if (bedingungEins && (bedingungZwei || bedingungDrei)) {
                    //Es gibt nur einen Nachfolger und jetziger war okay.
                    int interneIDnachfolger = ArrayListTemp.get(0);
                    IDBase nachfolger = speicherArray.getWithInternID(interneIDnachfolger);

                    jetziger.setMeineFarbe(FarbenEnum.gruen);
                    nachfolger.setMeineFarbe(FarbenEnum.gelb);

                } else if (bedingungZwei || bedingungDrei) {
                    jetziger.setMeineFarbe(FarbenEnum.rot);

                    for (Integer x : ArrayListTemp) {
                        speicherArray.getWithInternID(x).setMeineFarbe(FarbenEnum.rot);
                    }

                }
            } catch (WorkflownetzException ex) {
                //Kein Workflownetz !!!!!!!!!!!
            }

            neueDarstellungOhneTest();

        }

    }

    public void setPosition(MouseEvent evt, int interneID) {
        PosNameBase temp = (PosNameBase) this.speicherArray.getWithInternID(interneID);
        temp.setPosition(evt.getX(), evt.getY());
        System.out.println(evt.getX() + " " + evt.getY());
    }

    public void remove(int interneID) {
        IDBase temp = this.speicherArray.getWithInternID(interneID);
        if (this.speicherArray.remove(temp)) {
            speicherArrayNeu();
        }
        neueDarstellungMitTest();
    }

    private void speicherArrayNeu() {
        ArrayListSearchID<IDBase> neuesSpeicherArray = new ArrayListSearchID<>();
        int i = 0;
        while (i < this.speicherArray.size()) {
            IDBase temp = this.speicherArray.get(i);
            if (temp instanceof Arc) {
                Arc arcTemp = (Arc) temp;
                String id = arcTemp.getId();
                String source = arcTemp.getSource();
                String target = arcTemp.getTarget();
                System.out.println("1");
                try {
                    Arc neuArc = new Arc(id, source, target, this.speicherArray);
                    neuesSpeicherArray.add(neuArc);
                    System.out.println("2");
                } catch (ArcFehlerException ex) {
                    window.getFehleranzeigeText().setText("Es wurde ein oder mehrere Pfeile entfernt");
                }

            } else {
                neuesSpeicherArray.add(temp);

            }

            i++;
        }
        this.speicherArray = neuesSpeicherArray;
    }

    public void neueDarstellungMitTest() {
        testeWorkflownetz();
        neueDarstellungOhneTest();
    }

    public void neueDarstellungOhneTest() {
        window.getZeichenflaeche().removeAll();
        createView(window.getDarstellung());
        setzteDarstellung(window.getZeichenflaeche(), window.getDarstellung());
        window.getZeichenflaeche().repaint();
    }

    public void verschiebeMarkierteUmOffset(ArrayList<Integer> markierte, Vector2D offset) {

        int offsetX = offset.getX();
        int offsetY = offset.getY();

        for (Integer x : markierte) {
            if (speicherArray.getWithInternID(x) instanceof PosNameBase) {
                PosNameBase basis = (PosNameBase) speicherArray.getWithInternID(x);
                int altX = basis.getPosition().getX();
                int altY = basis.getPosition().getY();

                int neuX = altX + offsetX;
                int neuY = altY + offsetY;

                if (neuX < 0 )  {
                    neuX = 0;
                }
                if (neuY < 0) {
                    neuY = 0;
                }

                if (neuX > window.getZeichenflaeche().getPreferredSize().getWidth()) {
                    Double temp = window.getZeichenflaeche().getPreferredSize().getWidth();
                    neuX = temp.intValue();
                }

                if (neuY > window.getZeichenflaeche().getPreferredSize().getHeight()) {
                    Double temp = window.getZeichenflaeche().getPreferredSize().getHeight();
                    neuY = temp.intValue();
                }

                basis.setPosition(neuX, neuY);

            }

        }
        neueDarstellungOhneTest();
    }

    public boolean isTransition(int interneID) {
        return speicherArray.getWithInternID(interneID) instanceof Transition;
    }

    public String getLabel(int interneID) {
        String rueckgabe = "";
        if (speicherArray.getWithInternID(interneID) instanceof PosNameBase) {
            rueckgabe = ((PosNameBase) speicherArray.getWithInternID(interneID)).getLabel();
        }
        return rueckgabe;
    }

    public void umbennenLabel(int interneID, String label) {

        if (speicherArray.getWithInternID(interneID) instanceof PosNameBase) {
            ((PosNameBase) speicherArray.getWithInternID(interneID)).setLabel(label);
        }

    }


    public IDBase sucheMitID (String id){
        return speicherArray.searchID(id);
    }

    public Dimension findeGroesse() {
        int maxX = 0;
        int maxY = 0;
        
        for (IDBase x: speicherArray){
            if (x instanceof PosNameBase){
                int tempX = ((PosNameBase) x).getPosition().getX();
                int tempY = ((PosNameBase) x).getPosition().getY();
                if (tempX > maxX){
                    maxX = tempX;
                }
                if (tempY > maxY){
                    maxY = tempY;
                }
                
            }
        }
        return new Dimension(maxY, maxY);
    }
    
    public Vector2D getZeichenflaecheGroesse(){
        
        int x = (int)window.getZeichenflaeche().getPreferredSize().getWidth();
        int y = (int)window.getZeichenflaeche().getPreferredSize().getHeight();
        return new Vector2D(x,y);
    }

}
