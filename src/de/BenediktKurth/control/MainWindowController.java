package de.BenediktKurth.control;

import de.BenediktKurth.Exceptions.ArcFehlerException;
import de.BenediktKurth.Exceptions.FileNotLoadException;
import de.BenediktKurth.Exceptions.WorkflownetzException;
import de.BenediktKurth.model.Adjazenzmatrix;
import de.BenediktKurth.model.Arc;
import de.BenediktKurth.model.ArrayListSearchID;
import de.BenediktKurth.model.PosNameBase;
import de.BenediktKurth.model.Stellen;
import de.BenediktKurth.model.Transition;
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
import de.BenediktKurth.view.Umbennen;
import de.BenediktKurth.view.VerschiebbarLabel;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Der Controller des Programmes. Er verbindet die GUI mit dem Basisdatenmodel.
 * Wichtiger Bestandteil ist die Simultion eines sicheren Workflownetzes. Die
 * Klasse ist in acht Bereiche geteilt.
 *
 * 1. Variablen 2. Konstruktor 3. Erzeuge neue Objekte 4. Manipuliere Objekte 5.
 * Workflownetz und Simulation 6. Dateimanipulation 7. Darstellung 8. Hilfs- und
 * "Außen" Funktionen
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

    // 1. Variablen                      //////////////////////////////////////////////////////////////////////////////////////////
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
     * Interne String Variable für den zuletzt genutzen Ordner-Pfad.
     *
     * @since 1.0
     */
    private String lastDirectory;

    // 2. Konstruktor                      //////////////////////////////////////////////////////////////////////////////////////////
    /**
     * Leerer Konstruktor zur Initalizierung des Controllers.
     *
     * @since 1.0
     */
    public MainWindowController() {
        //Aufruf Kosntruker von Objekt
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
     * @param window Referenz auf das HauptFenster des Programmes.
     *
     *
     * @see HauptFenster
     * @see ArrayListSearchID
     * @see Adjazenzmatrix
     */
    public void setzeKomponenten(HauptFenster window) {
        //Initalisiere und setze Referenz auf das HauptFenster des Programmes.
        this.window = window;

        //Initalisiere und setze Referenz auf neues Speicherarray für die Basisdaten.
        this.speicherArray = new ArrayListSearchID<>();

        //Initalisiere und setze Referenz auf neue Adjazenzmatrix zur Pfadberechnung.
        this.adjazenzMatrix = new Adjazenzmatrix(this.speicherArray);

        //Initalisiere und setze Prüfvariable des Worklflownetzes.
        this.isWorkflownetz = false;

        //Initalisiere zuletzt genutzen Ordner-Pfad.
        this.lastDirectory = null;
    }

    // 3. Erzeuge neue Objekte                      //////////////////////////////////////////////////////////////////////////////////////////
    /**
     * Diese Methode erzeugt neue Stellen und übergibt sie der
     * Benutzeroberfläche und dem Basisdatenmodel.
     *
     * @since 1.0
     *
     * @see Stellen
     */
    public void neueStellen() {
        //Erzeuge neue Stelle (es wird eine ID vergeben)
        Stellen neueStelle = new Stellen();

        //Füge neue Stelle dem Speicherarray hinzu.
        this.speicherArray.add(neueStelle);

        //Erzeuge neue Darstellung und führe Test auf Workflownetz aus.
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
    public void neueTransition() {
        //Erzeuge neue Transition (es wird eine ID vergeben)
        Transition neueTransition = new Transition();

        //Füge neue Transition dem Speicherarray hinzu.
        speicherArray.add(neueTransition);

        //Erzeuge neue Darstellung und führe Test auf Workflownetz aus.
        neueDarstellungMitTest();
    }

    /**
     * Diese Methode erzeugt neue Kanten und übergibt sie der Benutzeroberfläche
     * und dem Basisdatenmodel. Sollten dabei Fehler entstehen informiert diese
     * Methode zusätzlich den Benutzer über die Art des Fehlers.
     *
     *
     * @param interneIDmarkierter ArrayList mit 2 Einträgen: source und target,
     * damit wir die neue Kante (Arc) erzeugt.
     *
     * @since 1.0
     *
     * @see Arc
     * @see ArcFehlerException
     */
    public void neueArc(ArrayList<Integer> interneIDmarkierter) {

        //Prüfe ob ArrayList 2 Einträge hat (Source/Target)
        if (interneIDmarkierter.size() == 2) {
            //Hole Source- und Target-IDs aus ArrayList 
            int source = interneIDmarkierter.get(0);
            int target = interneIDmarkierter.get(1);

            //ArcFehlerException, wenn Source und Target Stellen oder Transition sind.
            try {
                //Wirft Exception
                //Kante(Arc) bekommt Source- und Target-IDs und eine Referenz auf das SpeicherArray zwecks Prüfung.
                Arc temp = new Arc(speicherArray.getWithInternID(source).getId(), speicherArray.getWithInternID(target).getId(), speicherArray);

                // Füge Kante (Arc)dem speicherArray hinzu, sofern er erfolgreich erzeugt werden konnte.
                speicherArray.add(temp);

                //Erzeuge neue Darstellung und führe Test auf Workflownetz aus.
                neueDarstellungMitTest();

            } catch (ArcFehlerException ex) {
                //Nutzer wird über Fehler informiert.
                window.getFehleranzeigeText().setText(ex.getMessage());
            }

        } else {
            //Nutzer wird aufgefordert 2 unterschiedliche Objekte zu markieren.
            window.getFehleranzeigeText().setText("Bitte eine Stelle und eine Transition auswählen!");
        }

    }

    // 4. Manipuliere Objekte                      //////////////////////////////////////////////////////////////////////////////////////////
    /**
     * Die Methode übernimmt das Umbennen von Stellen und Transition. Die
     * Methode erwartet eine ArrayList mit einer internen ID, diese überprüft,
     * ob es sich um eine Kante (Arc) handelt. Wenn beide Bedingungen erfüllt
     * sind, kann das Objekt umbennant werden.
     *
     * @since 1.0
     *
     * @param interneIDmarkierter ArrayList mit einem Eintrag != Arc
     *
     * @see Umbennen
     */
    public void umbenennen(ArrayList<Integer> interneIDmarkierter) {
        //Prüfe ob ArrayList nur einen Eintrag hat
        boolean bedingung1 = interneIDmarkierter.size() == 1;

        //Prüfe ob markiertes Objekt keine Kante(Arc) ist.
        boolean bedingung2 = speicherArray.getWithInternID(interneIDmarkierter.get(0)) instanceof Arc;

        //Sollten beide Bedingunen erfüllt sein, so bennene Objekt um.
        if (bedingung1 && !bedingung2) {
            //Hol intere ID des Objektes
            int temp = interneIDmarkierter.get(0);

            //Rufe Hilffunktion auf, diese holt das Label des Objektes mithilfe der internenID.
            String label = gibLabel(temp);

            //Erzeuge neues Frame (Umbennen), mit Refrenz auf interne ID, aktuellem Label,
            //Controllerreferenz und Anzeigegröße des Desktops.
            Umbennen test = new Umbennen(temp, label, this, window.screenHeight, window.screenWidth);

        } else {
            //Sollte einer der beiden Bedingungen nicht erfüllt sein, so informiere Nutzer.
            window.getFehleranzeigeText().setText("Bitte nur eine Transition oder Stelle auswählen!");
        }
    }

    /**
     * Die Methode entfernt mit hilfe der ínternen ID Objekte. Methode nutzt
     * eine Hilfsmethode speicherArrayNeu, diese entfernt alle Kanten einer
     * Stelle oder Transition.
     *
     * @since 1.0
     *
     * @param interneID
     */
    public void entfernen(int interneID) {
        //Hole Objekt mit der internen ID als Referenz
        IDBase temp = this.speicherArray.getWithInternID(interneID);

        //Entferne Objekt mit hilfe der Referenz aus dem SpeicherArray
        this.speicherArray.remove(temp);

        //Prüfe ob zu löschendes Objekt eine Stelle oder Transition war.
        if (temp instanceof Stellen || temp instanceof Transition) {
            //Wenn ja, erzeuge neues SpeicherArray (entfernen von allen Kanten des Objektes
            speicherArrayNeu();
        }

        //Erzeuge neue Darstellung mit Prüfung auf Workflownetz
        neueDarstellungMitTest();
    }

    /**
     * Diese Methode verschiebt Stellen oder Transition bis zum Arbeitsflächen
     * maximum bzw minimal auf 0+ IDBase.size/2. Die Methode stellt also sicher,
     * dass keine Objekte aus dem Sichtbereich verschoben weren können.
     *
     * Die Methode verändert die View und das Basisdatenmodel!
     *
     * @since 1.0
     *
     * @param darstellungen ArrayList mit allen Darstellungen
     * @param markierte ArrayList mit internen IDs der markierten Objekte
     * @param offset Vector2D mit dem zu verschiebenden Werten der Objekte
     */
    public void verschiebeMarkierteUmOffset(ArrayList<JLabel> darstellungen, ArrayList<Integer> markierte, Vector2D offset) {

        //Entnehme der Vector2D die X und Y Werte.
        int offsetX = offset.getX();
        int offsetY = offset.getY();

        //Wichtig erst Verschiebbar, dann Pfeile. Sonst falsche Darstellung!!!!!!!!!!
        //Durchlaufe alle Darstellungen       
        for (JLabel x : darstellungen) {

            //Prüfe ob Ogjekt verschiebar ist
            if (x instanceof VerschiebbarLabel) {

                //Hilfsvariable um festzustellen, ob Objekt markiert war. (Focus)
                boolean istMarkiert = false;
                //Hilfsvariable interneID des derzeitigen Objektes
                int interneID = ((VerschiebbarLabel) x).getInterneID();

                //Durchlaufe alle markierten Obejkte und guck ob es dieses ist.
                for (Integer z : markierte) {
                    if (interneID == z) {
                        istMarkiert = true;
                        //Verlasse durchlauf, da gesuchtes Objekt gefunden.
                        break;
                    }
                }

                //Wenn dieses Objekt markiert war, ändere seine Position
                if (istMarkiert) {

                    //Hole Breite und Höhe des Objektes
                    int altHoehe = x.getHeight();
                    int altBreite = x.getWidth();

                    //Hole alte Position des Objektes
                    int altX = x.getX();
                    int altY = x.getY();

                    //Addiere Offeset zur alten Position
                    int neuX = altX + offsetX;
                    int neuY = altY + offsetY;

                    //Sollte X oder Y kleiner 0 + IDBase.Size / 2, setze es auf diesen Wert
                    if (neuX < 0 + IDBase.getSize() / 2) {
                        neuX = 0 + IDBase.getSize() / 2;
                    }
                    if (neuY < 0 + IDBase.getSize() / 2) {
                        neuY = 0 + IDBase.getSize() / 2;
                    }

                    //Sollte X oder Y größer als die maximale Arbeitsflächen Höhe oder Breite sein, begrenze sie
                    if (neuX > window.getZeichenflaeche().getPreferredSize().getWidth() - IDBase.getSize() / 2) {
                        Double temp = window.getZeichenflaeche().getPreferredSize().getWidth() - IDBase.getSize() / 2;
                        neuX = temp.intValue();
                    }

                    if (neuY > window.getZeichenflaeche().getPreferredSize().getHeight() - IDBase.getSize() / 2) {
                        Double temp = window.getZeichenflaeche().getPreferredSize().getHeight() - IDBase.getSize() / 2;
                        neuY = temp.intValue();
                    }

                    //Setze View auf neue X / Y Werte und behalte alte Breite und Höhe
                    x.setBounds(neuX, neuY, altBreite, altHoehe);

                    //Explizite Typumwandlung, um interne ID zu ermitteln
                    VerschiebbarLabel xTemp2 = (VerschiebbarLabel) x;

                    //Hole Referenz auf dieses Objekt in den Basisdaten
                    PosNameBase posNameTemp = (PosNameBase) speicherArray.getWithInternID(xTemp2.getInterneID());

                    //Ändere Basisdaten des Objektes
                    posNameTemp.setPosition(neuX + (altBreite / 2), neuY + (IDBase.getSize() / 2));
                }
            }
        }

        //Durchlaufe ein zweites mal Darstellungen.
        //Wichtig: Erst Verschiebar, dann Pfeile, ansonsten keine Positionsbestimmung möglich.
        for (JLabel x : darstellungen) {
            if (!(x instanceof ArcLabel)) {
                //Keine Kantendarstellung
            } else {
                //Hole akteulle Kante aus den Basisdaten, um deren Source und Target Pos zu ermitteln
                Arc temp = (Arc) speicherArray.getWithInternID(((ArcLabel) x).getInterneID());
                Vector2D sourcePosition = temp.getPositionSource();
                Vector2D targetPosition = temp.getPositionTarget();

                //Berechne Breite und Höhe der Kante
                int breite = Math.abs(sourcePosition.getX() - targetPosition.getX()) + 2;
                int hoehe = Math.abs(sourcePosition.getY() - targetPosition.getY()) + 2;

                //Ermittle Position der Kante (obenerste linke Ecke)
                int posX = Math.min(sourcePosition.getX(), targetPosition.getX()) - 1;
                int posY = Math.min(sourcePosition.getY(), targetPosition.getY()) - 1;

                //Setze Kantendarstellung auf neue Werte
                x.setBounds(posX, posY, breite, hoehe);

                //Setze neue Breite und Höhe der Darstellung (fuer Paint Methode)
                ((ArcLabel) x).setBreite(breite);
                ((ArcLabel) x).setHoehe(hoehe);
            }
        }

        //Zeichne Darstellung neu (Pfeilspitzen)
        window.getZeichenflaeche().repaint();
    }

    /**
     * Diese Methode dient dem Umbennen-Frame um ein Basisdatenmodel Objekt
     * umzubennen.
     *
     * @since 1.0
     *
     * @param interneID Interne ID des Obejktes
     * @param label String mit dem neuen Label des Objektes
     */
    public void umbennenLabel(int interneID, String label) {
        IDBase temp = speicherArray.getWithInternID(interneID);

        //Prüfe ob es Objekt mit inteneID überhaupt gibt
        if (temp != null) {
            //Prüfe ob das Objekt überhaupt ein Label hat (PosNameBase)
            if (speicherArray.getWithInternID(interneID) instanceof PosNameBase) {
                //Wenn ja, dann ändere das Label
                ((PosNameBase) speicherArray.getWithInternID(interneID)).setLabel(label);
            }
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
    public void setzeGlobaleGroesse(int faktor) {
        //Rufe statische Methode auf
        IDBase.setSize(faktor);
    }

    // 5. Workflownetz und Simulation                      //////////////////////////////////////////////////////////////////////////////////////////
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

                boolean warWasMarkiert = false;
                for (IDBase x : this.speicherArray) {
                    if (x instanceof Stellen) {
                        if (((Stellen) (x)).getMarkiert()) {
                            warWasMarkiert = true;
                            break;
                        }
                    }
                }

                if (!warWasMarkiert) {
                    simualtionZurucksetzen();
                } else {
                    simulationFortsetzen();
                }

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
     * Hauptmethode zur Simulation des sicheren Workflownetzes.
     * Die Methode prüft mithilfe der Adjazenzmatrix ob Deadlocks oder Kontakte
     * vorliegen. Weiter verändert die Methode die Darstellung (Farben und Markierungen)
     * der Stellen und Transition.
     * 
     * @since 1.0
     * 
     * @param evt           Wird derzeit noch nicht benötigt (evtl. Erweiterung)
     * @param interneID     Interne ID des ausgewählten Objektes
     */
    public void simuliereSicheresWorklflownetz(java.awt.event.MouseEvent evt, int interneID) {
        //Ist Transition rot?
        boolean bedingungEins = speicherArray.getWithInternID(interneID).getMeineFarbe() == FarbenEnum.rot;
        //Ist ausgewähltes Objekt eine Stelle oder Kante?
        boolean bedingungZwei = speicherArray.getWithInternID(interneID) instanceof Stellen
                || speicherArray.getWithInternID(interneID) instanceof Arc;

        if ((bedingungEins || bedingungZwei)) {
            //Rote oder keine Transition
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

    /**
     * Methode setzt die Simulation nach einer strukturellen Veränderung oder auf Wunsch 
     * des Nutzers zurück.
     * Alle Markierungen der Stellen werden entfernt, alle Objekt weiß gefärbt.
     * Anschließend wird - wenn es sich um ein Workflownetz handelt - der Anfang markiert und 
     * die erste Transition schaltbereit gesetzt (gruen).
     * 
     * @since 1.0
     * 
     */
    public void simualtionZurucksetzen() {
        //Durchlaufe SpeicherArray und setzte Markierungen und Farbe zurück
        for (IDBase x : this.speicherArray) {
            if (x instanceof PosNameBase) {
                ((PosNameBase) x).setMeineFarbe(FarbenEnum.weiss);
                if (x instanceof Stellen) {
                    ((Stellen) x).setMarkiert(false);
                }
            }
        }
        
        //Prüfe ob es sich um ein Workflownetz handelt.
        if (this.isWorkflownetz) {
            //Wenn ja:
            //Hole Anfang mit hilfe der Adjazenmatrix
            Stellen temp = this.adjazenzMatrix.getAnfang().get(0);
            
            //Setze Markierung für den Anfang
            temp.setMarkiert(true);

            //Hole alle Nachfolger des Anfanges (Es muss sich um Transition handeln)
            ArrayList<Integer> nachfolger = adjazenzMatrix.getNachfolger(temp.getInterneID());

            //Durchlaufe alle Nachfolger und setzt deren Farbe auf gruen
            for (Integer x : nachfolger) {
                this.speicherArray.getWithInternID(x).setMeineFarbe(FarbenEnum.gruen);
            }
        }

        //Erstelle neue Darstellung ohne Prüfung
        neueDarstellungOhneTest();
    }

    /**
     * Die Methode ermöglicht das fortsetzen einer gespeicherten Simulation eines Workflownetzes.
     * 
     * @since 1.0
     */
    private void simulationFortsetzen() {
        //Durchlaufe SpeicherArray
        for (IDBase x : this.speicherArray) {
            //Prüfe ob aktuelles Objekt eine Stelle ist
            if (x instanceof Stellen) {
                //Wenn ja, prüfe ob diese auch eine Markierung hat
                if (((Stellen) x).getMarkiert()) {
                    //Wenn beides ja, dann entnehme interne ID und suche Nachfolger
                    int interneID = x.getInterneID();
                    ArrayList<Integer> nachfolger = this.adjazenzMatrix.getNachfolger(interneID);

                    //Durchlaufe alle Nachfolger des aktuellen Objektes (Stelle)
                    for (Integer y : nachfolger) {
                        //Entnehme Basisdaten-Objekt mithilfe der internen ID
                        IDBase temp = this.speicherArray.getWithInternID(y);
                        
                        //Wenn es sich um eine Transition handelt, so färbe diese gruen
                        if (temp instanceof Transition) {
                            temp.setMeineFarbe(FarbenEnum.gruen);
                        }
                    }
                }
            }
        }
    }

    // 6. Dateimanipulation                      //////////////////////////////////////////////////////////////////////////////////////////
    /**
     * Die Methode erledigt für den Nutzer das laden einer Datei in den
     * Workflownetzeditor. Es wird der Methode ein absoluter Pfad zur zu
     * ladenden Datei übergeben. Weiter setzt die Methode des IDBase Counter auf
     * 0 zurück, damit die internenIDs wieder bei 0 beginnen. Die alten
     * Basisdaten werden überschrieben.
     *
     * @since 1.0
     *
     * @see IDBase
     * @see PNMLParser
     * @see FileNotLoadException
     */
    public void laden() {
        //Erstelle neuen FileChooser und setze open auf cancel
        JFileChooser chooser = null;
        int open = -1;

        //Wenn noch kein zuletzt genutzer Pfad gesetzt ist, öffne ohne Pfad-Referenz
        if (lastDirectory == null) {
            chooser = new JFileChooser();
        } 
        //Wenn ein zuletzt genutzer Pfad gesetzt ist, nutze diesen
        else {
            chooser = new JFileChooser(this.lastDirectory);;
        }
        
        //Setze Dateien Filter, damit nur pnml-Dateien geöffnet werden
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Workflownetz",
                "pnml");
        chooser.setFileFilter(filter);

        //Rufe Methode show auf um den "fertigen" OpenDialog aufzurufen
        open = chooser.showOpenDialog(null);

        //Wenn OpenDialog ERFOLGREICH beendet wurde:
        if (open == JFileChooser.APPROVE_OPTION) {

            //Setzte Counter auf 0
            IDBase.resetIdCounter();

            //Öffne und lade Datei in das speicherArray
            try {
                //Öffnet die statische Methode des PNMLParser und bekommt das SpeicherArray mit den Basisdaten
                this.speicherArray = PNMLParser.loadAndGet(chooser.getSelectedFile().getAbsolutePath());

            } catch (FileNotLoadException e) {
                //Bei Fehlern soll Nutzer informiert werden
                window.getFehleranzeigeText().setText(e.getMessage());
            }

            //Setze zuletzt genutzer Pfad auf aktuellen Pfad
            this.lastDirectory = chooser.getSelectedFile().getPath();

            //Ermittle und setze benötige Arbeitsfläche der zu ladenden Datei
            window.getZeichenflaeche().setPreferredSize(findeGroesse());
            
            //Erzeuge Darstellung und prüfe auf Workflownetz
            neueDarstellungMitTest();
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
     * @see PNMLWriter
     */
    public void speichern() {
        //Erstelle neuen FileChooser und setze open auf cancel
        JFileChooser chooser;
        int open = -1;

        //Wenn noch kein zuletzt genutzer Pfad gesetzt ist, öffne ohne Pfad-Referenz
        if (lastDirectory == null) {
            chooser = new JFileChooser();
        } 
        //Wenn ein zuletzt genutzer Pfad gesetzt ist, nutze diesen
        else {
            chooser = new JFileChooser(this.lastDirectory);;
        }

        //Rufe Methode show auf um den "fertigen" SaveDialog aufzurufen
        open = chooser.showSaveDialog(null);

        //Wenn SaveDialog ERFOLGREICH beendet wurde:
        if (open == JFileChooser.APPROVE_OPTION) {
            //Öffnet die statische Methode des PNMLWriter und übergib Referenz auf Basisdaten und Zielpfad
            PNMLWriter.saveFile(chooser.getSelectedFile().getAbsolutePath(), speicherArray);
            
            //Setze zuletzt genutzer Pfad auf aktuellen Pfad
            this.lastDirectory = chooser.getSelectedFile().getPath();
        }
    }

    // 7. Darstellung                       //////////////////////////////////////////////////////////////////////////////////////////
    /**
     * Die Methode erstellt auf Grundlage der Basisdaten eine entprechende
     * Darstellung des jeweiligen Objektes. Zu jeder Stelle, Transition und
     * Kante in den Basisdaten wird sein grafisches Gegenstück erzeugt und
     * gespeichert.
     *
     * @since 1.0
     *
     * @param darstellungen ArrayList mit allen Darstellungen.
     *
     * @see ArrayList
     */
    public void erzeugeDarstellung(ArrayList<JLabel> darstellungen) {

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
     * @param panel             Referenz auf "Zeichenfläche"
     * @param darstellungen     ArrayList mit allen Darstellungen.     
     *
     * @see ArrayList
     */
    public void setzteDarstellung(JLayeredPane panel, ArrayList<JLabel> darstellungen) {

        //Hilfsvariable um Position der PfeileDarstellung in Darstellungen zu ermitteln
        int i = 0;

        //ArrayList mit allen Positionen der PfeilDarstellung
        ArrayList<Integer> speicherPlatzDerKanten = new ArrayList<>();

        //Durchlaufe alle Darstellungen
        for (JLabel x: darstellungen){
            //Prüfe ob aktuelles Objekt eine PfeilDarstellung ist
            if (x instanceof PfeileDarstellung) {
                //Ja, füge Position hinzu
                speicherPlatzDerKanten.add(i);

            } else {
                //Nein, füge aktuelle Darstellung der Zeichenfläche hinzu
                panel.add(x);
            }
            
            //Zähle Hilfvariable hoch
            i++;
        }

        //Durchlaufe alle Positionen der PfeilDarstellung
        for (Integer x: speicherPlatzDerKanten){
            //Hole Pfeildarstellung
            JLabel temp = darstellungen.get(x);
            
            //Setze Pfeildarstellung
            panel.add(temp);
        }
    }

    /**
     * Methode lässt eine neue Darstellung erstellen und lässt prüft ob es um
     * ein Workflownetz handelt.
     * 
     * @since 1.0
     * 
     */
    public void neueDarstellungMitTest() {
        //Erzeuge neue Adjazenzmatrix auf Basis des Basisdatenmodels
        this.adjazenzMatrix = new Adjazenzmatrix(this.speicherArray);
        
        //Teste das Workflownetz 
        testeWorkflownetz();
        
        //Lasse Darstellung erzeugen 
        neueDarstellungOhneTest();
    }
    
    /**
     * Diese Methode erneuert die Darstellung des Basisdatenmodels.
     * Dazu werden alle alten Darstellungen entfernt und neu erzeugt, dies
     * dient der Datenkonsistenz (Darstellung==Basisdaten)
     * 
     * @since 1.0
     * 
     */
    public void neueDarstellungOhneTest() {
        //Entferne alle Objekte von der Zeichenfläche
        window.getZeichenflaeche().removeAll();
                
        //Erzeuge neue Darstellung und ersetze alte
        erzeugeDarstellung(window.getDarstellung());
        
        //Setze Objekte auf Zeichenfläche
        setzteDarstellung(window.getZeichenflaeche(), window.getDarstellung());
        
        //Zeichne Zeichenfläche neu
        window.getZeichenflaeche().repaint();
    }

    // 8. Hilfs- und "Außen" Funktionen                      //////////////////////////////////////////////////////////////////////////////////////////
   
    /**
     * Die Methode dient dazu, alle Kanten einer Stelle oder Transition zu entfernen.
     * Um das zu erreichen werden nur die alten Kanten, versucht diese neu zu erzeugen.
     * Wenn entweder Source oder Target-Objekt nicht mehr existieren, so wird die Kante nicht
     * erstellt und eine Info an Nutzer erzeugt.
     * 
     * @since 1.0
     * 
     * @see ArrayListSearchID 
     * @see ArcFehlerException
     * @see Arc
     */
    private void speicherArrayNeu() {
        //Erzeuge neues leeres SpeicherArray
        ArrayListSearchID<IDBase> neuesSpeicherArray = new ArrayListSearchID<>();
        
        //Durchlaufe altes SpeicherArray
        for (IDBase x: this.speicherArray) {
            //Prüfe ob aktuelles Objekt keine Kante ist
            if (!(x instanceof Arc)) {
                //Ja, Objekt einfach unverändert übernehmen
                neuesSpeicherArray.add(x);
            } else {
                //Nein, es ist eine Kante und es muss geprüft werden ob Source und Target noch vorhanden sind
                
                //Explizieter Typcast auf Arc
                Arc arcTemp = (Arc) x;
                
                //Entnehme ID der Kante (soll die selbe bleiben)
                String id = arcTemp.getId();
                
                //Entnehme Source- und Targetstring
                String source = arcTemp.getSource();
                String target = arcTemp.getTarget();

                //Versuche die Kante zu erzeugen
                try {
                    //Wirft Exception
                    Arc neuArc = new Arc(id, source, target, this.speicherArray);
                    
                    //Füge "richtge" Kante dem neuen SpeicherArray hinzu
                    neuesSpeicherArray.add(neuArc);

                } catch (ArcFehlerException ex) {
                    //Kante konnte nicht erstellt werden, da Source oder Target nicht mehr existieren,
                    //informiere Nutze
                    window.getFehleranzeigeText().setText("Es wurde ein oder mehrere Pfeile entfernt");
                }
            }
         
        }
        //Überschreibe altes SpeicherArray
        this.speicherArray = neuesSpeicherArray;
    }

    /**
     * Hilfsmethode zur Prüfung, ob es sich um eine Transition handelt.
     * 
     * @since 1.0
     * 
     * @param interneID Interne ID des zu prüfenden Objektes
     * 
     * @return  True: Objekt ist eine Transition, False: Objekt ist keine Transition
     */
    public boolean istTransition(int interneID) {
        return speicherArray.getWithInternID(interneID) instanceof Transition;
    }

    /**
     * Hilfsmethode um Label eines PosNameBase-Objektes zu erhalten.
     * 
     * @since 1.0
     * 
     * @param interneID Interne ID des gesuchten Objektes
     * 
     * @return  Leeren String falls Objekt kein eigenes Label hat.
     */
    public String gibLabel(int interneID) {
        //Erzeuge leeren String für evtl. Rückgabe
        String rueckgabe = "";
        
        //Prüfe ob Objekt ein eigenes Label hat
        if (speicherArray.getWithInternID(interneID) instanceof PosNameBase) {
            //Wenn ja, dann setz das eigene Label als Rückgabe-Wert
            rueckgabe = ((PosNameBase) speicherArray.getWithInternID(interneID)).getLabel();
        }
        
        return rueckgabe;
    }

    /**
     * Hilfmethode um von "außen" ein Objekt mit hilfe seiner ID zu suchen.
     * 
     * @since 1.0
     * 
     * @param id    ID des Obejktes als String
     *  
     * @return      Gibt Referenz auf ein existierendes Objekt zurück oder NULL 
     */
    public IDBase sucheMitID(String id) {
        return speicherArray.searchID(id);
    }

    /**
     * Hilfsmethode für JAVA-Swing-Objekts.
     * Java Swing arbeitet mit der Class Dimension, damit dieses 
     * 
     * @since 1.0
     * 
     * @return 
     */
    public Dimension findeGroesse() {
        //Initalisiere Hilfsvariablen
        int maxX = 0;
        int maxY = 0;

        //Durchlauf das speicherArray
        for (IDBase x : speicherArray) {
            //Prüfe ob aktuelles Objekt eine eigene Position hat 
            if (x instanceof PosNameBase) {
                //Hole X und Y Koodinaten und rechne IDBase.size hinzu
                int tempX = ((PosNameBase) x).getPosition().getX() + IDBase.getSize();
                int tempY = ((PosNameBase) x).getPosition().getY() + IDBase.getSize();
                
                //Sollte aktuelle Koordinate "größer" sein als alte, nutze neue Koordinate
                if (tempX > maxX) {
                    maxX = tempX ;
                }
                if (tempY > maxY) {
                    maxY = tempY ;
                }
            }
        }
        
        //Füge minimale Arbeitsfläche hinzu
        maxX += 250;
        maxY += 250;

        //Gibt neue maximal Koordinaten zurück.
        return new Dimension(maxX, maxY);
    }

    /**
     * Hilfsmethode um Größe der Zeichenfläche zu ermitteln.
     * 
     * @since 1.0
     * 
     * @return Vector2D mit der Größe der Zeichenfläche.
     */
    public Vector2D getZeichenflaecheGroesse() {
        //Hole Größe der Zeichenfläche
        int x = (int) window.getZeichenflaeche().getPreferredSize().getWidth();
        int y = (int) window.getZeichenflaeche().getPreferredSize().getHeight();
        
        //Gib neuen Vector2D mit Breite und Höhe zurück
        return new Vector2D(x, y);
    }
}
