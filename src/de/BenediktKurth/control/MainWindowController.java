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
import de.BenediktKurth.view.BasisLabel;
import de.BenediktKurth.view.HauptFenster;
import de.BenediktKurth.view.PfeileDarstellung;
import de.BenediktKurth.view.StellenLabel;
import de.BenediktKurth.view.TransitionLabel;
import de.BenediktKurth.view.Umbennen;
import de.BenediktKurth.view.VerschiebbarLabel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
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
     * Internes Speicherarray mit den Basisdaten aller Stellen, Transitions und
     * Kanten.
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
     * Interner boolean, gibt an ob es sich um eien Workflownetz handelt.
     *
     * @since 1.0
     */
    private boolean isWorkflownetz;

    /**
     * Leerer Konstruktor zur Initalizierung des Controllers.
     *
     * @since 1.0
     */
    public MainWindowController() {
        super();
    }

    /**
     * Diese Methode muss bei Programmstart in der Main-Methode aufgerufen
     * werden. Da die Benutzeroberfläche und der Controller eine gegenseitige
     * Referenz benötigen und nicht beide gleichzeitig instanziert werden
     * können, muss mit dieser Methode die Benutzeroberfläche am Controller
     * "angemeldet" werden. Weiter wird eine ArrayListSearchID und eine
     * Adjazenzmatrix eingerichtet, diese beinhalten die Programmrelevantendaten
     * des Basisdatenmodels.
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
        this.adjazenzMatrix = new Adjazenzmatrix(this.speicherArray);
        this.isWorkflownetz = true;
    }

    /**
     * Diese Methode erzeugt neue Stellen und übergibt sie der
     * Benutzeroberfläche und dem Basisdatenmodel.
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
     * Diese Methode erzeugt neue Transitions und übergibt sie der
     * Benutzeroberfläche und dem Basisdatenmodel.
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
     * Diese Methode erzeugt neue Kanten und übergibt sie der Benutzeroberfläche
     * und dem Basisdatenmodel. Sollten dabei Fehler entstehen informiert diese
     * Methode zusätzlich den Benutzer über die Art des Fehlers.
     *
     * @param source String Referenz zum Quellobjekt (ID)
     * @param target String Referenz zum Zielobjekt (ID)
     *
     * @since 1.0
     *
     * @see Arc
     * @see ArcFehlerException
     */
    public void newArc(ArrayList<Integer> interneIDmarkierter) {

        if (interneIDmarkierter.size() == 2) {
            int source = interneIDmarkierter.get(0);
            int target = interneIDmarkierter.get(1);

            Arc temp;
            try {
                temp = new Arc(speicherArray.getWithInternID(source).getId(), speicherArray.getWithInternID(target).getId(), speicherArray);
                speicherArray.add(temp);
                neueDarstellungMitTest();
            } catch (ArcFehlerException ex) {
                window.getFehleranzeigeText().setText(ex.getMessage());
            }

        } else {
            window.getFehleranzeigeText().setText("Bitte eine Stelle und eine Transition auswählen!");
        }

    }

    /**
     * Die Mehtode setzt die globale Größe für Stellen, Transitions und
     * Pfeilspitzen. Hierfür wird der Mehtode ein Faktor(in %) übergeben und
     * IDBase berechnet die Größe.
     *
     * @since 1.0
     *
     * @param faktor int Parameter enthält die Prozentualle relative Größe aller
     * Obejkte (exkl. Kanten).
     *
     * @see IDBase
     */
    public void setSize(int faktor) {
        IDBase.setSize(faktor);
    }

    /**
     * Die Methode testet mithilfe der Adjazenzmatrix und ihrer Methoden auf
     * vorhandensein eines Workflownetzes. Weiter informiert die Methode durch
     * die WorkflownetzException den Benutzer über einen möglichen Grund, warum
     * das vorliegende Netz kein Workflownetz ist oder es wird das vorhanden
     * sein ein Workflownetz bestätigtö.
     *
     * @since 1.0
     *
     * @see Adjazenzmatrix
     * @see WorkflownetzException
     */
    public void testeWorkflownetz() {

        try {
            if (adjazenzMatrix.pruefeWorkflownetz()) {
                //Keine Fehler -> Nutzer wird informiert
                window.getFehleranzeigeText().setText("Keine Fehler.");
                window.getFehleranzeigeGross().setText("Es ist ein Workflownetz.");
                this.isWorkflownetz = true;
                simualtionZurucksetzen();

            }
        } catch (WorkflownetzException ex) {
            //Fehler -> Nutzer wird informiert
            window.getFehleranzeigeText().setText(ex.getMessage());
            window.getFehleranzeigeGross().setText("Es ist kein Workflownetz.");
            this.isWorkflownetz = false;
            simualtionZurucksetzen();
        }

    }

    /**
     * Die Methode erledigt für den Nutzer das laden einer Datei in den
     * Workflownetzeditor. Es wird der Methode ein absoluter Pfad zur zu
     * ladenden Datei übergeben. Weiter setzt die Methode des IDBase Counter auf
     * 0 zurück, damit die internenIDs wieder bei 0 beginnen. Die alten
     * Basisdaten werden überschrieben.
     *
     * @since 1.0
     *
     * @param absoluterPfad String Referenz mit dem Dateipfad
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
        } catch (Exception e) {

        }

    }

    /**
     * Die Methode erledigt für den Nutzer das speichern einer Datei. Es wird
     * der Methode ein absoluter Pfad zu der gewünschten Datei übergeben. Die
     * Methode speichert automatisch die im speicherArray hinterlegten
     * Basisdaten.
     *
     * @since 1.0
     *
     * @param absoluterPfad String Referenz mit dem Dateipfad
     *
     *
     * @see PNMLWriter
     *
     */
    public void speichern(String absoluterPfad) {
        PNMLWriter.saveFile(absoluterPfad, speicherArray);
    }

    /**
     * Die Methode erstellt auf Grundlage der Basisdaten eine entprechende
     * Darstellung des jeweiligen Objektes. Zu jeder Stelle, Transition und
     * Kante in den Basisdaten wird sein grafisches Gegenstück erzeugt und
     * gespeichert.
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
        for (IDBase x : this.speicherArray) {
            if (x instanceof Stellen) {
                //Erstelle Stellendarstellung
                JLabel stellenLabel = new StellenLabel((Stellen) x, this, window);

                darstellungen.add(stellenLabel);

            } else if (x instanceof Transition) {
                //Erstelle Transitiondarstellung
                JLabel transitionLabel = new TransitionLabel((Transition) x, this, window);

                darstellungen.add(transitionLabel);

            } else if (x instanceof Arc) {
                //Erstelle Kantendarstellung
                JLabel arcLabel = new ArcLabel((Arc) x, this, window);
                PfeileDarstellung pfeil = new PfeileDarstellung((Arc) x, this);

                darstellungen.add(arcLabel);
                darstellungen.add(pfeil);
            }
        }
    }

    /**
     * Die Methode setzt die Darstellung auf die Zeichenfläche. Zuvor werden die
     * Pfeilspitzen jedoch aussortiert, damit diese in den Hintergrund
     * gezeichnet werden können
     *
     * @since 1.0
     *
     * @param panel
     * @param darstellungen
     *
     * @see ArrayList
     */
    public void setzteDarstellung(JLayeredPane panel, ArrayList<JLabel> darstellungen) {

        int i = 0;

        ArrayList<Integer> panelTest = new ArrayList<>();

        while (i < darstellungen.size()) {
            JLabel temp = darstellungen.get(i);
            if (temp instanceof PfeileDarstellung) {
                panelTest.add(i);

            } else {
                panel.add(temp);
            }

            i++;
        }

        i = 0;
        while (i < panelTest.size()) {
            JLabel temp = darstellungen.get(panelTest.get(i));
            panel.add(temp);
            i++;
        }
    }

    public void simuliereSicheresWorklflownetz(java.awt.event.MouseEvent evt, int interneID) {
        //Ist Transition rot
        boolean bedingungEins = speicherArray.getWithInternID(interneID).getMeineFarbe() == FarbenEnum.rot;
        //Ist ausgewähltes Objekt eine Stelle?
        boolean bedingungZwei = speicherArray.getWithInternID(interneID) instanceof Stellen
                || speicherArray.getWithInternID(interneID) instanceof Arc;

        if ((bedingungEins || bedingungZwei)) {
            //Rote Transition oder eine Stelle
            window.getFehleranzeigeText().setText("Simulation nicht möglich, bitte wähle Sie eine nicht rote Transition aus.");

        } else {
            //Simulation möglich:
            window.getFehleranzeigeText().setText("Keine Fehler.");
            //Bestimme Nachfolger,Vorgänger und aktuelle Position. Initalisiere Stellenzähler
            Transition jetziger = (Transition) speicherArray.getWithInternID(interneID);
            ArrayList<Integer> listeNachfolger = adjazenzMatrix.getNachfolger(interneID);
            ArrayList<Integer> listeVorgaenger = adjazenzMatrix.getVorganger(interneID);
            int anzahlMarkierterStellen = 0;

            //Zähle die Anfangsmarken auf allen Vorgängerstellen
            for (Integer x : listeVorgaenger) {
                Stellen temp = (Stellen) speicherArray.getWithInternID(x);
                if (temp.getMarkiert()) {
                    anzahlMarkierterStellen++;
                }
            }

            if (anzahlMarkierterStellen == 0) {
                //Keine Markierungen

            } else {

                boolean warBereitsMarkiert = false;

                for (Integer x : listeNachfolger) {
                    IDBase temp = speicherArray.getWithInternID(x);
                    if (temp instanceof Stellen) {
                        if (((Stellen) temp).getMarkiert()) {
                            if (x != interneID) {
                                warBereitsMarkiert = true;
                            }

                        }
                    }
                }

                //Wenn alle Marken auf Vorgängerstellen gesetzt?
                if (!(listeVorgaenger.size() == anzahlMarkierterStellen)) {
                    jetziger.setMeineFarbe(FarbenEnum.rot);
                    window.getFehleranzeigeText().setText("Simulation nicht möglich, da ein Deadlock vorliegt.");

                } else if (warBereitsMarkiert) {
                    jetziger.setMeineFarbe(FarbenEnum.rot);
                    window.getFehleranzeigeText().setText("Simulation nicht möglich, da ein Kontakt vorliegt.");

                } else {

                    ArrayList<ArrayList<Integer>> listeNachNachfolger = new ArrayList<>();

                    //Finde Nachfolger (Transition) der Nachfolger (Stellen)
                    for (Integer x : listeNachfolger) {
                        listeNachNachfolger.add(adjazenzMatrix.getNachfolger(x));
                    }
                    for (Integer x : listeVorgaenger) {
                        Stellen temp = (Stellen) speicherArray.getWithInternID(x);
                        temp.setMarkiert(false);
                    }
                    for (IDBase x : speicherArray) {
                        if (x instanceof Transition) {
                            if ((x.getMeineFarbe().equals(FarbenEnum.rot))) {

                            } else {
                                ArrayList<Integer> jetzigeVorganger = adjazenzMatrix.getVorganger(x.getInterneID());
                                boolean alleGesetzt = true;
                                for (Integer y : jetzigeVorganger) {
                                    IDBase temp = speicherArray.getWithInternID(y);
                                    if (temp instanceof Stellen) {
                                        if (!((Stellen) temp).getMarkiert()) {
                                            alleGesetzt = false;
                                        }
                                    }
                                }

                                if (alleGesetzt) {

                                } else {
                                    x.setMeineFarbe(FarbenEnum.weiss);
                                }
                            }
                        }
                    }

                    jetziger.setMeineFarbe(FarbenEnum.gelb);

                    for (Integer x : listeNachfolger) {
                        Stellen temp = (Stellen) speicherArray.getWithInternID(x);
                        temp.setMarkiert(true);
                        if (x == adjazenzMatrix.getEnde().get(0).getInterneID()) {
                            window.getFehleranzeigeText().setText("Ende erreicht!");
                        }
                    }

                    //Setze Nachfolgermarkierung
                    for (Integer x : listeNachfolger) {
                        ((Stellen) speicherArray.getWithInternID(x)).setMarkiert(true);
                    }

                    for (ArrayList<Integer> x : listeNachNachfolger) {
                        for (Integer y : x) {
                            IDBase temp = speicherArray.getWithInternID(y);

                            ArrayList<Integer> jetzigeVorganger = adjazenzMatrix.getVorganger(y);

                            boolean alleMarkiert = true;

                            for (Integer z : jetzigeVorganger) {
                                IDBase temp2 = speicherArray.getWithInternID(z);
                                if (temp2 instanceof Stellen) {
                                    if (!((Stellen) temp2).getMarkiert()) {
                                        alleMarkiert = false;
                                    }

                                }

                            }

                            if (temp instanceof Transition) {
                                if (alleMarkiert) {
                                    ((Transition) temp).setMeineFarbe(FarbenEnum.gruen);
                                } else {
                                    ((Transition) temp).setMeineFarbe(FarbenEnum.rot);
                                    window.getFehleranzeigeText().setText("Simulation nicht möglich, da ein Deadlock vorliegt.");
                                }

                            }
                        }
                    }
                }
            }
            neueDarstellungOhneTest();

        }
    }

    public void setPosition(MouseEvent evt, int interneID) {
        PosNameBase temp = (PosNameBase) this.speicherArray.getWithInternID(interneID);
        temp.setPosition(evt.getX(), evt.getY());

    }

    public void remove(int interneID) {
        IDBase temp = this.speicherArray.getWithInternID(interneID);
        this.speicherArray.remove(temp);

        if (temp instanceof Stellen || temp instanceof Transition) {
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

                try {
                    Arc neuArc = new Arc(id, source, target, this.speicherArray);
                    neuesSpeicherArray.add(neuArc);

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
        this.adjazenzMatrix = new Adjazenzmatrix(this.speicherArray);
        testeWorkflownetz();
        neueDarstellungOhneTest();
    }

    public void neueDarstellungOhneTest() {
        window.getZeichenflaeche().removeAll();
        window.getZeichenflaeche().revalidate();
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

                if (neuX < 0 + IDBase.getSize() / 2) {
                    neuX = 0 + IDBase.getSize() / 2;
                }
                if (neuY < 0 + IDBase.getSize() / 2) {
                    neuY = 0 + IDBase.getSize() / 2;
                }

                if (neuX > window.getZeichenflaeche().getPreferredSize().getWidth() - IDBase.getSize() / 2) {
                    Double temp = window.getZeichenflaeche().getPreferredSize().getWidth() - IDBase.getSize() / 2;
                    neuX = temp.intValue();
                }

                if (neuY > window.getZeichenflaeche().getPreferredSize().getHeight() - IDBase.getSize() / 2) {
                    Double temp = window.getZeichenflaeche().getPreferredSize().getHeight() - IDBase.getSize() / 2;
                    neuY = temp.intValue();
                }
                basis.setPosition(neuX, neuY);
            }
        }

        neueDarstellungOhneTest();
    }

    public void verschiebeMarkierteUmOffsetTest(ArrayList<JLabel> darstellungen, ArrayList<Integer> markierte, Vector2D offset) {

        int offsetX = offset.getX();
        int offsetY = offset.getY();

        for (JLabel x : darstellungen) {

            if (x instanceof VerschiebbarLabel) {
                boolean istMarkiert = false;
                int interneID = ((VerschiebbarLabel) x).getInterneID();

                for (Integer z : markierte) {
                    if (interneID == z) {
                        istMarkiert = true;
                        break;
                    }
                }

                if (istMarkiert) {

                    int altHoehe = x.getHeight();
                    int altBreite = x.getWidth();

                    System.out.println(altHoehe + " " + altBreite);
                    int altX = x.getX();
                    int altY = x.getY();

                    int neuX = altX + offsetX;
                    int neuY = altY + offsetY;

                    if (neuX < 0 + IDBase.getSize() / 2) {
                        neuX = 0 + IDBase.getSize() / 2;
                    }
                    if (neuY < 0 + IDBase.getSize() / 2) {
                        neuY = 0 + IDBase.getSize() / 2;
                    }

                    if (neuX > window.getZeichenflaeche().getPreferredSize().getWidth() - IDBase.getSize() / 2) {
                        Double temp = window.getZeichenflaeche().getPreferredSize().getWidth() - IDBase.getSize() / 2;
                        neuX = temp.intValue();
                    }

                    if (neuY > window.getZeichenflaeche().getPreferredSize().getHeight() - IDBase.getSize() / 2) {
                        Double temp = window.getZeichenflaeche().getPreferredSize().getHeight() - IDBase.getSize() / 2;
                        neuY = temp.intValue();
                    }
                    x.setBounds(neuX, neuY, altBreite, altHoehe);
                    VerschiebbarLabel xTemp2 = (VerschiebbarLabel) x;
                    PosNameBase posNameTemp = (PosNameBase) speicherArray.getWithInternID(xTemp2.getInterneID());

                    posNameTemp.setPosition(neuX + (altBreite / 2), neuY + (IDBase.getSize() / 2));
                }
            }
        }

        for (JLabel x : darstellungen) {
            if (x instanceof ArcLabel) {
              
                Arc temp = (Arc) speicherArray.getWithInternID(((ArcLabel) x).getInterneID());
                Vector2D sourcePosition = temp.getPositionSource();
                Vector2D targetPosition = temp.getPositionTarget();

                int breite = Math.abs(sourcePosition.getX() - targetPosition.getX()) + 2;
                int hoehe = Math.abs(sourcePosition.getY() - targetPosition.getY()) + 2;

                int posX = Math.min(sourcePosition.getX(), targetPosition.getX()) - 1;
                int posY = Math.min(sourcePosition.getY(), targetPosition.getY()) - 1;

                x.setBounds(posX, posY, breite, hoehe);
                ((ArcLabel) x).setBreite(breite);
                ((ArcLabel) x).setHoehe(hoehe);
                
               
                
               
               
            } else {
                 
            }
        }
        
        
        

        window.getZeichenflaeche().repaint();
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

    public IDBase sucheMitID(String id) {
        return speicherArray.searchID(id);
    }

    public Dimension findeGroesse() {
        int maxX = 0;
        int maxY = 0;

        for (IDBase x : speicherArray) {
            if (x instanceof PosNameBase) {
                int tempX = ((PosNameBase) x).getPosition().getX();
                int tempY = ((PosNameBase) x).getPosition().getY();
                if (tempX > maxX) {
                    maxX = tempX;
                }
                if (tempY > maxY) {
                    maxY = tempY;
                }

            }
        }
        return new Dimension(maxY, maxY);
    }

    public Vector2D getZeichenflaecheGroesse() {

        int x = (int) window.getZeichenflaeche().getPreferredSize().getWidth();
        int y = (int) window.getZeichenflaeche().getPreferredSize().getHeight();
        return new Vector2D(x, y);
    }

    public void simualtionZurucksetzen() {
        for (IDBase x : this.speicherArray) {
            if (x instanceof PosNameBase) {
                ((PosNameBase) x).setMeineFarbe(FarbenEnum.weiss);
                if (x instanceof Stellen) {
                    ((Stellen) x).setMarkiert(false);
                }
            }

        }
        if (this.isWorkflownetz) {
            Stellen temp = this.adjazenzMatrix.getAnfang().get(0);
            temp.setMarkiert(true);

            ArrayList<Integer> nachfolger = adjazenzMatrix.getNachfolger(temp.getInterneID());

            for (Integer x : nachfolger) {
                this.speicherArray.getWithInternID(x).setMeineFarbe(FarbenEnum.gruen);
            }

        }

        neueDarstellungOhneTest();
    }
    
    public void umbenennen(ArrayList<Integer> interneIDmarkierter) {
        boolean bedingung1 = interneIDmarkierter.size() == 1;
        boolean bedingung2 = speicherArray.getWithInternID(interneIDmarkierter.get(0)) instanceof Arc;
        
        if (bedingung1 && !bedingung2){
            int temp = interneIDmarkierter.get(0);
            
               String label = getLabel(temp);
               Umbennen test = new Umbennen(temp, label, this, window.screenHeight, window.screenWidth);  
           
               
        } else {
            window.getFehleranzeigeText().setText("Bitte nur eine Transition oder Stelle auswählen!");
        }
    }
}
