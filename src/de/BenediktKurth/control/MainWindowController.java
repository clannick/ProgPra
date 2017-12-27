package de.BenediktKurth.control;

import de.BenediktKurth.myExceptions.ArcFehlerException;
import de.BenediktKurth.myExceptions.DateiFehlerException;
import de.BenediktKurth.myExceptions.WorkflownetzException;

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
import de.BenediktKurth.view.Umbenennen;
import de.BenediktKurth.view.VerschiebbarLabel;

import java.awt.Dimension;

import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Der Controller des Programmes. Er verbindet die GUI mit dem Basisdatenmodel.
 * Wichtiger Bestandteil ist die Simultion eines sicheren Workflownetzes. Die
 * Klasse ist in acht Bereiche geteilt. <br>
 * <br>
 * 1. Variablen <br>
 * 2. Konstruktor <br>
 * 3. Erzeuge neue Objekte <br>
 * 4. Manipuliere Objekte <br>
 * 5. Workflownetz und Simulation <br>
 * 6. Dateimanipulation  <br>
 * 7. Darstellung  <br>
 * 8. Hilfs- und "Außen" Funktionen <br>
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

    // 1. Variablen                                 //////////////////////////////////////////////////////////////////////////////////////////
    /**
     * Referenz auf die Benutzeroberfläche.
     *
     * @since 1.0
     */
    private HauptFenster fenster;

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
    private boolean istWorkflownetz;

    /**
     * Interne String Variable für den zuletzt genutzen Ordner-Pfad.
     *
     * @since 1.0
     */
    private String zuLetztGenutzterDateiPfad;

    // 2. Konstruktor                              //////////////////////////////////////////////////////////////////////////////////////////
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
        this.fenster = window;

        //Initalisiere und setze Referenz auf neues Speicherarray für die Basisdaten.
        this.speicherArray = new ArrayListSearchID<>();

        //Initalisiere und setze Referenz auf neue Adjazenzmatrix zur Pfadberechnung.
        this.adjazenzMatrix = new Adjazenzmatrix(speicherArray);

        //Initalisiere und setze Prüfvariable des Worklflownetzes.
        this.istWorkflownetz = false;

        //Initalisiere zuletzt genutzen Ordner-Pfad.
        this.zuLetztGenutzterDateiPfad = null;
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

        //Füge neue Stelle dem Speicherarray hinzu, aktualisiere Matrix
        speicherArray.add(neueStelle);
        istWorkflownetz = false;

        //Suche freie Position
        verschiebeAufFreiePosition(neueStelle.gibInterneID());

        //Erzeuge neue Darstellung und führe Test auf Workflownetz aus.
        simulationZurucksetzen();
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

        //Füge neue Transition dem Speicherarray hinzu, aktualisiere Matrix
        speicherArray.add(neueTransition);
        istWorkflownetz = false;

        //Suche freie Position
        verschiebeAufFreiePosition(neueTransition.gibInterneID());

        //Erzeuge neue Darstellung und führe Test auf Workflownetz aus.
        simulationZurucksetzen();
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
                Arc temp = new Arc(speicherArray.gibMitInternID(source).gibID(), speicherArray.gibMitInternID(target).gibID(), speicherArray);

                // Füge Kante (Arc)dem speicherArray hinzu, sofern er erfolgreich erzeugt werden konnte.
                speicherArray.add(temp);

                //Erzeuge neue Darstellung und führe Test auf Workflownetz aus.
                simulationZurucksetzen();
                neueDarstellungMitTest();

            } catch (ArcFehlerException ex) {
                //Nutzer wird über Fehler informiert.
                fenster.getFehleranzeigeText().setText(ex.getMessage());
            }

        } else {
            //Nutzer wird aufgefordert 2 unterschiedliche Objekte zu markieren.
            fenster.getFehleranzeigeText().setText("Bitte eine Stelle und eine Transition auswählen!");
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
     * @see Umbenennen
     */
    public void umbenennen(ArrayList<Integer> interneIDmarkierter) {
        //Prüfe ob ArrayList nur einen Eintrag hat
        boolean bedingung1 = interneIDmarkierter.size() == 1;

        if (bedingung1) {
            //Prüfe ob markiertes Objekt keine Kante(Arc) ist.
            boolean bedingung2 = speicherArray.gibMitInternID(interneIDmarkierter.get(0)) instanceof Arc;

            //Sollten beide Bedingunen erfüllt sein, so bennene Objekt um.
            if (!bedingung2) {
                //Hol intere ID des Objektes
                int temp = interneIDmarkierter.get(0);

                //Rufe Hilffunktion auf, diese holt das Label des Objektes mithilfe der internenID.
                String label = gibLabel(temp);

                //Erzeuge neues Frame (Umbennen), mit Refrenz auf interne ID, aktuellem Label,
                //Controllerreferenz und Anzeigegröße des Desktops.
                Umbenennen test = new Umbenennen(temp, label, this, fenster.screenHeight, fenster.screenWidth);

            }
        } else {
            //Sollte einer der beiden Bedingungen nicht erfüllt sein, so informiere Nutzer.
            fenster.getFehleranzeigeText().setText("Bitte nur eine Transition oder Stelle auswählen!");
        }

    }

    /**
     * Die Methode entfernt mit hilfe der ínternen ID Objekte. Methode nutzt
     * eine Hilfsmethode speicherArrayNeu, diese entfernt alle Kanten einer
     * Stelle oder Transition.
     *
     * @since 1.0
     *
     * @param interneID Interne-ID des zu löschenden Objektes.
     */
    public void entfernen(int interneID) {
        //Hole Objekt mit der internen ID als Referenz
        IDBase temp = speicherArray.gibMitInternID(interneID);

        //Entferne Objekt mit hilfe der Referenz aus dem SpeicherArray
        speicherArray.remove(temp);

        //Prüfe ob zu löschendes Objekt eine Stelle oder Transition war.
        if (temp instanceof Stellen || temp instanceof Transition) {
            //Wenn ja, erzeuge neues SpeicherArray (entferne allen Kanten des Objektes)
            speicherArrayNeu();
        }

        //Wenn speicherArray leer, dann ohne Prüfung und zurücksetzen.
        if (speicherArray.isEmpty()) {
            neueDarstellungOhneTest();
            istWorkflownetz = false;
            fenster.getFehleranzeigeGross().setText("");
            IDBase.zuruecksetzenIdCounter();
        } else {
            
            if (speicherArray.size() == 1){
                IDBase temp2 = speicherArray.get(0);
                if (temp2 instanceof Stellen){
                    ((Stellen) temp2).setzeMarkiert(true);
                    istWorkflownetz = true;
                    fenster.getFehleranzeigeText().setText("Keine Fehler.");
                    fenster.getFehleranzeigeGross().setText("Es ist ein Workflownetz.");
                }
            } else{
               //Erzeuge neue Darstellung mit Prüfung auf Workflownetz
            neueDarstellungMitTest(); //Simulation auf null
        simulationZurucksetzen();
            }
            
        }
        
        
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
        int offsetX = offset.gibX();
        int offsetY = offset.gibY();

        //Wichtig erst Verschiebbar, dann Pfeile. Sonst falsche Darstellung!!!!!!!!!!
        //Durchlaufe alle Darstellungen       
        for (JLabel x : darstellungen) {

            //Prüfe ob Ogjekt verschiebar ist
            if (x instanceof VerschiebbarLabel) {

                //Hilfsvariable um festzustellen, ob Objekt markiert war. (Focus)
                boolean istMarkiert = false;
                //Hilfsvariable interneID des derzeitigen Objektes
                int interneID = ((VerschiebbarLabel) x).gibInterneID();

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
                    int altBreite = x.getWidth();

                    //Hole alte Position des Objektes
                    int altX = x.getX();
                    int altY = x.getY();

                    //Addiere Offeset zur alten Position
                    int neuX = altX + offsetX;
                    int neuY = altY + offsetY;

                    //Sollte X oder Y kleiner 0, setze es auf diesen Wert
                    int minimaleGroesse = 0;
                    if (neuX < minimaleGroesse) {
                        neuX = minimaleGroesse;
                    }
                    if (neuY < minimaleGroesse) {
                        neuY = minimaleGroesse;
                    }

                    //Sollte X oder Y größer als die maximale Arbeitsflächen Höhe oder Breite sein, begrenze sie
                    if (neuX > fenster.getZeichenflaeche().getPreferredSize().getWidth() - IDBase.gibGroesse()) {
                        Double temp = fenster.getZeichenflaeche().getPreferredSize().getWidth() - IDBase.gibGroesse();
                        neuX = temp.intValue();
                    }

                    if (neuY > fenster.getZeichenflaeche().getPreferredSize().getHeight() - IDBase.gibGroesse()) {
                        Double temp = fenster.getZeichenflaeche().getPreferredSize().getHeight() - IDBase.gibGroesse();
                        neuY = temp.intValue();
                    }

                    //Setze View auf neue X / Y Werte
                    x.setLocation(neuX, neuY);

                    //Explizite Typumwandlung, um interne ID zu ermitteln
                    VerschiebbarLabel xTemp2 = (VerschiebbarLabel) x;

                    //Hole Referenz auf dieses Objekt in den Basisdaten
                    PosNameBase posNameTemp = (PosNameBase) speicherArray.gibMitInternID(xTemp2.gibInterneID());

                    //Ändere Basisdaten des Objektes (Mittelpunkt des Objektes!!!!!)
                    posNameTemp.setzePosition(neuX + (altBreite / 2), neuY + (IDBase.gibGroesse() / 2));
                }
            }
        }

        //Durchlaufe ein zweites mal Darstellungen.
        //Wichtig: Erst Verschiebar, dann Pfeile, ansonsten keine Positionsbestimmung möglich.
        darstellungen.forEach((x) -> {
            if (!(x instanceof ArcLabel)) {
                //Keine Kantendarstellung
            } else {
                //Hole akteulle Kante aus den Basisdaten, um deren Source und Target Pos zu ermitteln
                Arc temp = (Arc) speicherArray.gibMitInternID(((ArcLabel) x).gibInterneID());
                Vector2D sourcePosition = temp.gibPositionSource();
                Vector2D targetPosition = temp.gibPositionTarget();

                //Berechne Breite und Höhe der Kante
                int breite = Math.abs(sourcePosition.gibX() - targetPosition.gibX()) + 2;
                int hoehe = Math.abs(sourcePosition.gibY() - targetPosition.gibY()) + 2;

                //Ermittle Position der Kante (obenerste linke Ecke)
                int posX = Math.min(sourcePosition.gibX(), targetPosition.gibX()) - 1;
                int posY = Math.min(sourcePosition.gibY(), targetPosition.gibY()) - 1;

                //Setze Kantendarstellung auf neue Werte
                x.setBounds(posX, posY, breite, hoehe);

                //Setze neue Breite und Höhe der Darstellung (fuer Paint Methode)
                ((ArcLabel) x).setzeBreite(breite);
                ((ArcLabel) x).setzeHoehe(hoehe);
            }
        });

        //Zeichne Darstellung neu (Pfeilspitzen)
        fenster.getZeichenflaeche().repaint();
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
        IDBase temp = speicherArray.gibMitInternID(interneID);

        //Prüfe ob es Objekt mit inteneID überhaupt gibt
        if (temp != null) {
            //Prüfe ob das Objekt überhaupt ein Label hat (PosNameBase)
            if (speicherArray.gibMitInternID(interneID) instanceof PosNameBase) {
                //Wenn ja, dann ändere das Label
                ((PosNameBase) speicherArray.gibMitInternID(interneID)).setzeLabel(label);
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
        IDBase.setzeGroesse(faktor);
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
                fenster.getFehleranzeigeText().setText("Keine Fehler.");
                fenster.getFehleranzeigeGross().setText("Es ist ein Workflownetz.");
                istWorkflownetz = true;

                int interneIDAnfang = adjazenzMatrix.gibAnfang().get(0).gibInterneID();

                //Guck ob es Markierungen gibt (außer Anfang)
                boolean warWasMarkiert = false;
                for (IDBase x : speicherArray) {
                    if (x instanceof Stellen) {
                        if (x.gibInterneID() == interneIDAnfang) {
                            //Nur der Anfang ist Markiert
                        } else {
                            if (((Stellen) (x)).gibMarkiert()) {
                                warWasMarkiert = true;
                                break;
                            }
                        }
                    }
                }

                //Ist was außer Anfang gesetzt?
                if (!warWasMarkiert) {
                    simulationZurucksetzen();
                } else {
                    simulationFortsetzen();
                }

            }
        } catch (WorkflownetzException ex) {
            //Fehler -> Nutzer wird informiert
            fenster.getFehleranzeigeText().setText(ex.getMessage());
            fenster.getFehleranzeigeGross().setText("Es ist kein Workflownetz.");
            istWorkflownetz = false;
        }

    }

    /**
     * Hauptmethode zur Simulation des sicheren Workflownetzes. Die Methode
     * prüft mithilfe der Adjazenzmatrix ob Deadlocks oder Kontakte vorliegen.
     * Weiter verändert die Methode die Darstellung (Farben und Markierungen)
     * der Stellen und Transition. Es wird zu beginn geprüft, ob es sich um eine
     * rote oder keine Transition handelt. Anschließend wird die eigentliche
     * Simulation durchgeführt.
     *
     *
     * @since 1.0
     *
     * @param evt Wird derzeit noch nicht benötigt (evtl. Erweiterung)
     * @param interneID Interne ID des ausgewählten Objektes
     */
    public void simuliereSicheresWorklflownetz(java.awt.event.MouseEvent evt, int interneID) {

        if (!istWorkflownetz) {
            //Eine Simulation ist nicht möglich, da kein WFN: Nutzer informieren!
            fenster.getFehleranzeigeText().setText("Es handelt sich um kein Workflownetz, daher ist auch keine Simulation moeglich.");
        } else {
            //Test generell moeglich
            IDBase objekt = speicherArray.gibMitInternID(interneID);
            ArrayList<Integer> nachfolger = adjazenzMatrix.gibNachfolger(interneID);
            ArrayList<Integer> vorgaenger = adjazenzMatrix.gibVorgaenger(interneID);

            //Test ob es Objekt überhaupt gibt
            if (objekt != null) {
                //Handelt es sich um eine gruene Transition
                boolean bedingungEins = objekt instanceof Transition;
                boolean bedigungZwei = objekt.gibMeineFarbe().equals(FarbenEnum.gruen);

                if (!bedingungEins || !bedigungZwei) {
                    //Nutzer hat eine nicht schaltbare Transition ausgewählt, Info an Nutzer
                    fenster.getFehleranzeigeText().setText("Bitte wählen Sie eine grüne Transition aus.");
                } else {

                    //Durchlaufe alle Vorgänger und entnehme Marken und färbe Vorgänger und Vorvorgänger weiß
                    for (Integer x : vorgaenger) {
                        Stellen temp = ((Stellen) speicherArray.gibMitInternID(x));
                        temp.setzeMarkiert(false);
                        temp.setzeMeineFarbe(FarbenEnum.weiss);

                        //Finde alle Vorvorgänger der geschlateten Transition
                        ArrayList<Integer> vorVorgaenger = adjazenzMatrix.gibVorgaenger(x);
                        for (Integer z : vorVorgaenger) {
                            Transition temp2 = (Transition) speicherArray.gibMitInternID(z);
                            temp2.setzeMeineFarbe(FarbenEnum.weiss);
                        }
                    }

                    //Hilfsvariablen, Endstelle und evtl. Korrektur bei Kontakt
                    int interneIDEndstelle = adjazenzMatrix.gibEnde().get(0).gibInterneID();
                    int bisherErsetzteNachfolger = 0;
                    boolean lagEinKontaktVor = false;

                    //Durchlaufe alle Nachfolger und guck ob es Kontakte gibt, wenn ja Korrektur
                    for (Integer x : nachfolger) {
                        Stellen temp = ((Stellen) speicherArray.gibMitInternID(x));

                        //Wenn bereits markiert, dann Kontakt und alle bisher gesetzte Marken zurücknehmen.
                        if (temp.gibMarkiert()) {
                            //Info an Nutzer und Transition rot einfärben
                            fenster.getFehleranzeigeText().setText("Kontakt, es handelt sich um ein unsicheres Workflownetz.");
                            objekt.setzeMeineFarbe(FarbenEnum.rot);
                            lagEinKontaktVor = true;

                            //Setzte Eingangsstellen zurück (Marken setzten)
                            for (Integer y : vorgaenger) {
                                Stellen temp1 = ((Stellen) speicherArray.gibMitInternID(y));
                                temp1.setzeMarkiert(true);
                            }

                            //Setzte bisher gesetzte Ausgangstellen zurück  
                            for (int i = 0; i < bisherErsetzteNachfolger; i++) {
                                ((Stellen) speicherArray.gibMitInternID(nachfolger.get(i))).setzeMarkiert(false);
                            }

                            //Breche for-Schleife Nachfolger ab, da Kontakt vorliegt
                            break;

                            //Stelle war nicht markiert und kann daher besetzt werden.
                        } else {
                            bisherErsetzteNachfolger++;
                            temp.setzeMarkiert(true);

                            for (IDBase h : speicherArray) {
                                if (h instanceof Transition) {
                                    ArrayList<Integer> vorgaengerIntern = adjazenzMatrix.gibVorgaenger(h.gibInterneID());
                                    boolean flag = true;
                                    for (Integer y : vorgaengerIntern) {
                                        Stellen temp2 = (Stellen) speicherArray.gibMitInternID(y);
                                        if (!temp2.gibMarkiert()) {
                                            flag = false;
                                        }
                                    }

                                    if (flag) {
                                        h.setzeMeineFarbe(FarbenEnum.gruen);
                                    } else {
                                        h.setzeMeineFarbe(FarbenEnum.weiss);
                                    }
                                }
                            }

                            if (x == interneIDEndstelle) {
                                fenster.getFehleranzeigeText().setText("Das Ende der Simulation, wurde erreicht.");
                                temp.setzeMeineFarbe(FarbenEnum.gruen);

                            } else {
                                fenster.getFehleranzeigeText().setText("Keine Fehler.");
                            }

                        }

                    }

                    //Hilfsvariable, um herauszufinden ob das Netz noch aktive "Posten" hat.
                    boolean gibtEsGrueneTransitions = false;
                    

                    //Durchlaufe das SpeicherArray und filter Stellen und Transition
                    for (IDBase x : speicherArray) {
                        //Wenn min. eine Transition gruen ist (schaltbar)
                        if (x instanceof Transition) {
                            if (x.gibMeineFarbe().equals(FarbenEnum.gruen)) {
                                gibtEsGrueneTransitions = true;
                            }
                        }
                    }

                    if ( !gibtEsGrueneTransitions && lagEinKontaktVor){
                        fenster.getFehleranzeigeText().setText("Kontakt mit Deadlock!");
                        adjazenzMatrix.gibEnde().get(0).setzeMeineFarbe(FarbenEnum.rot);
                    } else if (!gibtEsGrueneTransitions && !adjazenzMatrix.gibEnde().get(0).gibMarkiert()){
                        fenster.getFehleranzeigeText().setText("Deadlock!");
                        adjazenzMatrix.gibEnde().get(0).setzeMeineFarbe(FarbenEnum.rot);
                    }
                        /*
                        //boolean gibtEsNochMarkierungen = false;
                        //boolean aktiveDinge = (gibtEsGrueneTransitions || gibtEsNochMarkierungen);
                        //Wenn min eine Stelle noch eine Marke trägt (exkl. Endmarkierung)
                    
                        if (x instanceof Stellen) {
                            if (((Stellen) x).gibMarkiert()) {
                                if (x.gibInterneID() != interneIDEndstelle) {
                                    gibtEsNochMarkierungen = true;
                                }
                            }
                        }
                    
                        if (!gibtEsGrueneTransitions && !adjazenzMatrix.gibEnde().get(0).gibMarkiert()) {
                            //Es gibt keine gruenen also schaltbare Transitions und Ende wurde nicht erreicht
                            fenster.getFehleranzeigeText().setText("Deadlock! Das Ende der Simulation, kann nicht erreicht werden.");
                            adjazenzMatrix.gibEnde().get(0).setzeMeineFarbe(FarbenEnum.rot);
                        } else if (aktiveDinge && adjazenzMatrix.gibEnde().get(0).gibMarkiert() && !lagEinKontaktVor) {
                            //Unsicheres Workflownetz da Ende zwar erreicht, aber noch aktive Transition oder Stellenmarkierungen vorhanden sind.
                            fenster.getFehleranzeigeText().setText("Unsicheres Workflownetz. Es gibt noch aktive Transition oder markierte Stellen, obwohl das Ende erreicht wurde.");
                            adjazenzMatrix.gibEnde().get(0).setzeMeineFarbe(FarbenEnum.rot);
                        }

                        */
                    neueDarstellungOhneTest();
                }
            }
        }
    }

    /**
     * Methode setzt die Simulation nach einer strukturellen Veränderung oder
     * auf Wunsch des Nutzers zurück. Alle Markierungen der Stellen werden
     * entfernt, alle Objekt weiß gefärbt. Anschließend wird - wenn es sich um
     * ein Workflownetz handelt - der Anfang markiert und die erste Transition
     * schaltbereit gesetzt (gruen).
     *
     * @since 1.0
     *
     */
    public void simulationZurucksetzen() {
        //Hilfsvariable
        boolean gibtEsSchaltbareTran = false;

        //Durchlaufe SpeicherArray und setzte Markierungen und Farbe zurück
        for (IDBase x : speicherArray) {
            if (x instanceof PosNameBase) {
                ((PosNameBase) x).setzeMeineFarbe(FarbenEnum.weiss);
                if (x instanceof Stellen) {
                    ((Stellen) x).setzeMarkiert(false);
                }
            }
        }

        //Prüfe ob es sich um ein Workflownetz handelt.
        if (istWorkflownetz) {
            //Wenn keine Fehler aufgetretten -> Nutzer informieren (Nachricht wird bei Fehlverhalten überschrieben)
            fenster.getFehleranzeigeText().setText("Keine Fehler.");
            //Wenn ja:
            //Hole Anfang und Ende mit der Adjazenmatrix

            Stellen anfang = adjazenzMatrix.gibAnfang().get(0);
            Stellen ende = adjazenzMatrix.gibEnde().get(0);

            //Setze Markierung für den Anfang
            anfang.setzeMarkiert(true);

            //Hole alle Nachfolger des Anfanges (Es muss sich um Transition handeln)
            ArrayList<Integer> nachfolger = adjazenzMatrix.gibNachfolger(anfang.gibInterneID());

            //Durchlaufe alle Nachfolger und setzt deren Farbe auf gruen oder Deadlock
            for (Integer x : nachfolger) {
                Transition temp = (Transition) speicherArray.gibMitInternID(x);
                if (temp != null) {
                    ArrayList<Integer> vorgaenger = adjazenzMatrix.gibVorgaenger(x);

                    //Hilfsvariable um nicht schaltbare Transition zu erkennen
                    boolean flag = true;
                    for (Integer y : vorgaenger) {
                        Stellen temp2 = (Stellen) speicherArray.gibMitInternID(y);

                        //Wenn es die Stelle gibt (Kontrollprüfung)
                        if (temp2 != null) {
                            //Die Stelle war b
                            if (!temp2.gibMarkiert()) {
                                flag = false;
                            }
                        }
                    }

                    //Sind alle Eingangsstellen besetzt
                    if (flag) {
                        temp.setzeMeineFarbe(FarbenEnum.gruen);
                        //Markiere Anfang und Ende Farblich
                        anfang.setzeMeineFarbe(FarbenEnum.gelb);
                        ende.setzeMeineFarbe(FarbenEnum.gelb);
                        gibtEsSchaltbareTran = true;
                    }

                    //Gibt es überhaupt schaltbare Transition 
                    if (!gibtEsSchaltbareTran) {
                        fenster.getFehleranzeigeText().setText("Deadlock! Keine Transition ist schaltbar.");
                        ende.setzeMeineFarbe(FarbenEnum.rot);
                        anfang.setzeMarkiert(false);
                    }
                }
            }

        } else {
            //kein WFN, daher auch keine Simulation möglich -> Nutzer informieren 
            fenster.getFehleranzeigeText().setText("Simulation konnte nicht zurückgesetzt werden, da kein Workflownetz vorliegt.");
        }

        //Erstelle neue Darstellung ohne Prüfung
        neueDarstellungOhneTest();
    }

    /**
     * Die Methode ermöglicht das fortsetzen einer gespeicherten Simulation
     * eines Workflownetzes. Die Methode prüft dabei, ob die ersten Transitions
     * überhaupt schaltbar sind. Sollten sie das nicht sein, so wird Nutzer
     * informiert.
     *
     * @since 1.0
     */
    private void simulationFortsetzen() {
        //Durchlaufe SpeicherArray
        for (IDBase x : this.speicherArray) {
            //Prüfe ob aktuelles Objekt eine Stelle ist
            if (x instanceof Stellen) {
                //Wenn ja, prüfe ob diese auch eine Markierung hat
                if (((Stellen) x).gibMarkiert()) {
                    //Wenn beides ja, dann entnehme interne ID und suche Nachfolger
                    int interneID = x.gibInterneID();
                    ArrayList<Integer> nachfolger = this.adjazenzMatrix.gibNachfolger(interneID);

                    //Prüfvariable ob es schaltbare Transition gibt
                    boolean nichtSchaltbar = true;

                    //Durchlaufe alle Nachfolger des aktuellen Objektes (Stelle)
                    for (Integer y : nachfolger) {
                        //Entnehme Basisdaten-Objekt mithilfe der internen ID
                        IDBase temp = speicherArray.gibMitInternID(y);

                        //Wenn es sich um eine Transition handelt, so färbe diese gruen
                        if (temp instanceof Transition) {
                            //Prüfe ob alle Eingangstellen marken tragen 
                            ArrayList<Integer> vorgaenger = adjazenzMatrix.gibVorgaenger(y);
                            boolean alleMarkiert = true;
                            for (Integer z : vorgaenger) {
                                Stellen vorgangerStelle = (Stellen) speicherArray.gibMitInternID(z);
                                if (!vorgangerStelle.gibMarkiert()) {
                                    //Stelle trägt keine Marke
                                    alleMarkiert = false;
                                }
                            }
                            //Alle Eingangsstellen sind markiert -> Transition schaltbar
                            if (alleMarkiert) {
                                temp.setzeMeineFarbe(FarbenEnum.gruen);
                                nichtSchaltbar = false;
                            }
                        }
                    }
                    //Keine der Transition ist schaltbar, daher Fehler
                    if (nichtSchaltbar) {
                        fenster.getFehleranzeigeText().setText("Simulation konnte nicht fortgesetzt werden, bitte prüfen oder Simulation zurücksetzten.");
                        adjazenzMatrix.gibEnde().get(0).setzeMeineFarbe(FarbenEnum.rot);
                    }
                }
            }
        }
    }

    // 6. Dateimanipulation                      //////////////////////////////////////////////////////////////////////////////////////////
    /**
     * Diese Methode löscht alle Daten und setzt die Arbeitsfläche auf eine
     * Größe von 1500x1500.
     *
     * @since 1.0
     */
    public void neu() {
        //Initalisiere und setze Referenz auf neues Speicherarray für die Basisdaten.
        speicherArray = new ArrayListSearchID<>();
        IDBase.zuruecksetzenIdCounter();
        //Initalisiere und setze Referenz auf neue Adjazenzmatrix zur Pfadberechnung.
        adjazenzMatrix = new Adjazenzmatrix(speicherArray);

        //Initalisiere und setze Prüfvariable des Worklflownetzes.
        istWorkflownetz = false;

        //Setzte Arbeitsfläche und ändere Anzeigen
        fenster.getZeichenflaeche().setPreferredSize(new Dimension(1500, 1500));
        fenster.getFehleranzeigeGross().setText("");
        fenster.getFehleranzeigeText().setText("");
        fenster.getTextBreite().setText("1500");
        fenster.getTextHoehe().setText("1500");

        //Erzeuge neue Darstellung, ohne Test!
        neueDarstellungOhneTest();
    }

    /**
     * Die Methode erledigt für den Nutzer das laden einer Datei in den
     * Workflownetzeditor. Die Methode öffnet eine Datei anhand des absoluten
     * Pfads Datei. Weiter setzt die Methode des IDBase Counter auf 0 zurück,
     * damit die internenIDs wieder bei 0 beginnen. Die alten Basisdaten werden
     * überschrieben.
     *
     * @since 1.0
     *
     * @see IDBase
     * @see PNMLParser
     * @see DateiFehlerException
     */
    public void laden() {
        //zwischenspeicher Basisdaten
        ArrayListSearchID<IDBase> zwischenSpeicher = speicherArray;
        int zwischenSpeicherIDGroesse = IDBase.gibIdCounterOhneHochzaehlen();
        
        //Erstelle neuen FileChooser und setze open auf cancel
        JFileChooser chooser;
        int open;

        //Wenn noch kein zuletzt genutzer Pfad gesetzt ist, öffne ohne Pfad-Referenz
        if (zuLetztGenutzterDateiPfad == null) {
            chooser = new JFileChooser();
        } //Wenn ein zuletzt genutzer Pfad gesetzt ist, nutze diesen
        else {
            chooser = new JFileChooser(zuLetztGenutzterDateiPfad);
        }

        //Setze Dateien Filter, damit nur pnml-Dateien geöffnet werden
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Workflownetz",
                "pnml");
        chooser.setFileFilter(filter);

        //Rufe Methode show auf um den "fertigen" OpenDialog aufzurufen
        open = chooser.showOpenDialog(null);

        //Wenn OpenDialog ERFOLGREICH beendet wurde:
        if (open == JFileChooser.APPROVE_OPTION) {

            //Setze zuletzt genutzer Pfad auf aktuellen Pfad
            zuLetztGenutzterDateiPfad = chooser.getSelectedFile().getPath();

            //Öffne und lade Datei in das speicherArray
            try {
                //Setzte Counter auf 0
                IDBase.zuruecksetzenIdCounter();
                
                //Öffnet die statische Methode des PNMLParser und bekommt das SpeicherArray mit den Basisdaten
                speicherArray = PNMLParser.ladenUndGeben(chooser.getSelectedFile().getAbsolutePath());

                //Wenn neue Einträge vorhanden -> neues Projekt, ansonsten altes "retten"
                if (!speicherArray.isEmpty()) {
                    //Ermittle und setze benötige Arbeitsfläche der zu ladenden Datei
                    Dimension groesseArbeitsfläche = findeGroesse();
                    int breite = (int) groesseArbeitsfläche.getWidth();
                    int hohe = (int) groesseArbeitsfläche.getHeight();
                    fenster.getZeichenflaeche().setPreferredSize(groesseArbeitsfläche);
                    //Umwandlung in String durch + ""
                    fenster.getTextBreite().setText(breite + "");
                    fenster.getTextHoehe().setText(hohe + "");

                    //Erzeuge Darstellung und prüfe auf Workflownetz
                    neueDarstellungMitTest();
                    fenster.getZeichenflaeche().repaint();
                } else {
                    //Datei war leer oder konnte nicht geladen werden
                    speicherArray = zwischenSpeicher;
                    IDBase.setzteIDCounter(zwischenSpeicherIDGroesse);
                    fenster.getFehleranzeigeText().setText("Die ausgewählte Datei war leer, bzw. es konnten keine Daten ermittelt werden.");
                }
            } catch (DateiFehlerException e) {
                //Bei Fehlern soll Nutzer informiert werden
                fenster.getFehleranzeigeText().setText(e.getMessage());
            }
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
        int open;

        //Wenn noch kein zuletzt genutzer Pfad gesetzt ist, öffne ohne Pfad-Referenz
        if (zuLetztGenutzterDateiPfad == null) {
            chooser = new JFileChooser();
        } //Wenn ein zuletzt genutzer Pfad gesetzt ist, nutze diesen
        else {
            chooser = new JFileChooser(zuLetztGenutzterDateiPfad);
        }

        //Setze Dateien Filter, damit nur pnml-Dateien geöffnet werden
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Workflownetz",
                "pnml");
        chooser.setFileFilter(filter);

        //Rufe Methode show auf um den "fertigen" SaveDialog aufzurufen
        open = chooser.showSaveDialog(null);

        //Wenn SaveDialog ERFOLGREICH beendet wurde:
        if (open == JFileChooser.APPROVE_OPTION) {
            //Setze zuletzt genutzer Pfad auf aktuellen Pfad
            zuLetztGenutzterDateiPfad = chooser.getSelectedFile().getPath();

            //Dateipfad extrahieren
            String absoluterDateiPfad = chooser.getSelectedFile().getAbsolutePath();

            //Wenn Dateiendung != ".pnml" setzte sie
            if (!absoluterDateiPfad.endsWith(".pnml")) {
                absoluterDateiPfad = absoluterDateiPfad + ".pnml";
            }

            try {
                //Öffnet die statische Methode des PNMLWriter und übergib Referenz auf Basisdaten und Zielpfad
                PNMLWriter.saveFile(absoluterDateiPfad, speicherArray);

            } catch (DateiFehlerException ex) {
                fenster.getFehleranzeigeText().setText(ex.getMessage());
            }
        }
    }

    // 7. Darstellung                            //////////////////////////////////////////////////////////////////////////////////////////
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

        //Durchlaufe alle Basisdaten und suche Stellen und Transitions
        for (IDBase x : speicherArray) {
            if (x instanceof Stellen) {
                //Erstelle Stellendarstellung
                JLabel stellenLabel = new StellenLabel((Stellen) x, this, fenster);

                darstellungen.add(stellenLabel);

            } else if (x instanceof Transition) {
                //Erstelle Transitiondarstellung
                JLabel transitionLabel = new TransitionLabel((Transition) x, this, fenster);

                darstellungen.add(transitionLabel);

            }
        }
        //Durchlaufe alle Basisdaten und suche Kanten/Arc
        for (IDBase x : speicherArray) {    
            if (x instanceof Arc) {
                //Erstelle Kantendarstellung
                JLabel arcLabel = new ArcLabel((Arc) x, this, fenster);
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
     * @param panel Referenz auf "Zeichenfläche"
     * @param darstellungen ArrayList mit allen Darstellungen.
     *
     * @see ArrayList
     */
    public void setzteDarstellung(JLayeredPane panel, ArrayList<JLabel> darstellungen) {

        //Hilfsvariable um Position der PfeileDarstellung in Darstellungen zu ermitteln
        int i = 0;

        //ArrayList mit allen Positionen der PfeilDarstellung
        ArrayList<Integer> speicherPlatzDerKanten = new ArrayList<>();

        //Durchlaufe alle Darstellungen
        for (JLabel x : darstellungen) {
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
        for (Integer x : speicherPlatzDerKanten) {
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
        adjazenzMatrix = new Adjazenzmatrix(speicherArray);

        //Teste das Workflownetz 
        testeWorkflownetz();

        //Lasse Darstellung erzeugen 
        neueDarstellungOhneTest();
    }

    /**
     * Diese Methode erneuert die Darstellung des Basisdatenmodels. Dazu werden
     * alle alten Darstellungen entfernt und neu erzeugt, dies dient der
     * Datenkonsistenz (Darstellung==Basisdaten)
     *
     * @since 1.0
     *
     */
    public void neueDarstellungOhneTest() {
        //Entferne alle Objekte von der Zeichenfläche
        fenster.getZeichenflaeche().removeAll();

        //Erzeuge neue Darstellung und ersetze alte
        erzeugeDarstellung(fenster.getDarstellung());

        //Setze Objekte auf Zeichenfläche
        setzteDarstellung(fenster.getZeichenflaeche(), fenster.getDarstellung());

        //Zeichne Zeichenfläche neu
        fenster.getZeichenflaeche().repaint();
    }

    // 8. Hilfs- und "Außen" Funktionen                      //////////////////////////////////////////////////////////////////////////////////////////
    /**
     * Die Methode dient dazu, alle Kanten einer Stelle oder Transition zu
     * entfernen. Um das zu erreichen werden nur die alten Kanten, versucht
     * diese neu zu erzeugen. Wenn entweder Source oder Target-Objekt nicht mehr
     * existieren, so wird die Kante nicht erstellt und eine Info an Nutzer
     * erzeugt.
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
        for (IDBase x : this.speicherArray) {
            //Prüfe ob aktuelles Objekt keine Kante ist
            if (!(x instanceof Arc)) {
                //Ja, Objekt einfach unverändert übernehmen
                neuesSpeicherArray.add(x);
            } else {
                //Nein, es ist eine Kante und es muss geprüft werden ob Source und Target noch vorhanden sind

                //Explizieter Typcast auf Arc
                Arc arcTemp = (Arc) x;

                //Entnehme ID der Kante (soll die selbe bleiben)
                String id = arcTemp.gibID();

                //Entnehme Source- und Targetstring
                String source = arcTemp.gibSource();
                String target = arcTemp.gibTarget();

                //Versuche die Kante zu erzeugen
                try {
                    //Wirft Exception
                    Arc neuArc = new Arc(id, source, target, this.speicherArray);

                    //Füge "richtge" Kante dem neuen SpeicherArray hinzu
                    neuesSpeicherArray.add(neuArc);

                } catch (ArcFehlerException ex) {
                    //Kante konnte nicht erstellt werden, da Source oder Target nicht mehr existieren,
                    //informiere Nutze
                    fenster.getFehleranzeigeText().setText("Es wurde ein oder mehrere Pfeile entfernt");
                }
            }
        }
        //Überschreibe altes SpeicherArray
        this.speicherArray = neuesSpeicherArray;
    }

    /**
     * Diese Methode versetzt die Objekte nach einer Verkleinerung der
     * Arbeitsfläche.
     *
     * @since 1.0
     */
    public void passeObjektPositionenAn() {
        int breite = fenster.getZeichenflaeche().getPreferredSize().width;
        int hoehe = fenster.getZeichenflaeche().getPreferredSize().height;

        for (IDBase x : speicherArray) {
            if (x instanceof PosNameBase) {
                int posX = ((PosNameBase) x).gibPosition().gibX();
                int posY = ((PosNameBase) x).gibPosition().gibY();
                int minmalerAbstand = IDBase.gibGroesse() / 2;

                if (posX < minmalerAbstand) {
                    posX = minmalerAbstand;
                }

                if (posY < minmalerAbstand) {
                    posY = minmalerAbstand;
                }

                if (posX > breite) {
                    posX = breite - IDBase.gibGroesse();
                }
                if (posY > hoehe) {
                    posY = hoehe - IDBase.gibGroesse();
                }
                ((PosNameBase) x).setzePosition(posX, posY);
            }
        }
    }

    /**
     * Hilfsmethode zur Prüfung, ob es sich um eine Transition handelt.
     *
     * @since 1.0
     *
     * @param interneID Interne ID des zu prüfenden Objektes
     *
     * @return True: Objekt ist eine Transition, False: Objekt ist keine
     * Transition
     */
    public boolean istTransition(int interneID) {
        return speicherArray.gibMitInternID(interneID) instanceof Transition;
    }

    /**
     * Hilfsmethode um Label eines PosNameBase-Objektes zu erhalten.
     *
     * @since 1.0
     *
     * @param interneID Interne ID des gesuchten Objektes
     *
     * @return Leeren String falls Objekt kein eigenes Label hat.
     */
    public String gibLabel(int interneID) {
        //Erzeuge leeren String für evtl. Rückgabe
        String rueckgabe = "";

        //Prüfe ob Objekt ein eigenes Label hat
        if (speicherArray.gibMitInternID(interneID) instanceof PosNameBase) {
            //Wenn ja, dann setz das eigene Label als Rückgabe-Wert
            rueckgabe = ((PosNameBase) speicherArray.gibMitInternID(interneID)).gibLabel();
        }
        return rueckgabe;
    }

    /**
     * Hilfmethode um von "außen" ein Objekt mit hilfe seiner ID zu suchen.
     *
     * @since 1.0
     *
     * @param id ID des Obejktes als String
     *
     * @return Gibt Referenz auf ein existierendes Objekt zurück oder NULL
     */
    public IDBase sucheMitID(String id) {
        return speicherArray.sucheID(id);
    }

    /**
     * Hilfsmethode für JAVA-Swing-Objekts. Java Swing arbeitet mit der Class
     * Dimension, damit dieses
     *
     * @since 1.0
     *
     * @return Benötigte Größe der Arbeitsfläche als Dimensions-Objekt
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
                int tempX = ((PosNameBase) x).gibPosition().gibX() + IDBase.gibGroesse();
                int tempY = ((PosNameBase) x).gibPosition().gibY() + IDBase.gibGroesse();

                //Sollte aktuelle Koordinate "größer" sein als alte, nutze neue Koordinate
                if (tempX > maxX) {
                    maxX = tempX;
                }
                if (tempY > maxY) {
                    maxY = tempY;
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
        int x = (int) fenster.getZeichenflaeche().getPreferredSize().getWidth();
        int y = (int) fenster.getZeichenflaeche().getPreferredSize().getHeight();

        //Gib neuen Vector2D mit Breite und Höhe zurück
        return new Vector2D(x, y);
    }

    /**
     * Hilfmethode um neue Stellen und Transitions auf eine freie Position zu
     * verschieben.
     *
     * @param interneID Interne ID des Objektes das verschoben werden soll.
     * 
     * @since 1.0
     */
    private void verschiebeAufFreiePosition(int interneID) {
        //Hilfvariablen und Offset
        int offset = IDBase.gibGroesse();
        IDBase zuVerschieben = speicherArray.gibMitInternID(interneID);

        //Prüfung kann man Objekt Verschieben 
        if (zuVerschieben instanceof PosNameBase) {
            //Extrahiere x und y aus Objekt
            PosNameBase temp = (PosNameBase) zuVerschieben;
            int posX = temp.gibPosition().gibX();
            int posY = temp.gibPosition().gibX();

            //Sicherung für nur ein Objekt, ansonsten Endlosschleife
            boolean gibtEsObjektDa = true;
            if (speicherArray.size() < 2) {
                gibtEsObjektDa = false;
            }

            //Wiederhole solange es noch nicht auf einem freien Platz ist 
            while (gibtEsObjektDa) {

                //Durchlaufe Daten ob ein anderes Objekt an gleicher Position ist
                for (IDBase x : speicherArray) {
                    if (x instanceof PosNameBase) {
                        if (x.gibInterneID() != interneID) {
                            int posXvergleich = ((PosNameBase) x).gibPosition().gibX();
                            int posYVergleich = ((PosNameBase) x).gibPosition().gibY();

                            if (posX == posXvergleich && posY == posYVergleich) {
                                gibtEsObjektDa = true;
                                break;
                            } else {
                                gibtEsObjektDa = false;
                            }
                        }
                    }
                }

                //Wenn ein Objekt an selber Position dann verschieben um Offset
                if (gibtEsObjektDa) {
                    posX += offset;
                    posY += offset;
                    //Sollte X oder Y größer als die maximale Arbeitsflächen Höhe oder Breite sein, begrenze sie
                    if (posX > fenster.getZeichenflaeche().getPreferredSize().getWidth() - IDBase.gibGroesse()) {
                        Double tempX = fenster.getZeichenflaeche().getPreferredSize().getWidth() - IDBase.gibGroesse();
                        posX = tempX.intValue();
                        //Endlosschleifensicherung (Wenn Objekt in der untersten linken Ecke angekommen
                        gibtEsObjektDa = false;
                    }

                    if (posY > fenster.getZeichenflaeche().getPreferredSize().getHeight() - IDBase.gibGroesse()) {
                        Double tempY = fenster.getZeichenflaeche().getPreferredSize().getHeight() - IDBase.gibGroesse();
                        posY = tempY.intValue();
                        //Endlosschleifensicherung (Wenn Objekt in der untersten linken Ecke angekommen
                        gibtEsObjektDa = false;
                    }
                    temp.setzePosition(posX, posY);
                }
            }
        }
    }
}
