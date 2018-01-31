package de.BenediktKurth.model;

import de.BenediktKurth.myExceptions.ArcFehlerException;
import de.BenediktKurth.myExceptions.DateiFehlerException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Iterator;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

/**
 * Diese Klasse implementiert die Grundlage für einen einfachen PNML Parser.
 *
 * @author Benedikt Kurth und FernUniversität Hagen
 *
 * @since 1.0
 *
 * @version 1.0
 */
public final class PNMLParser {

    /**
     * Dies ist eine Referenz zum Java Datei Objekt.
     *
     * @since 1.0
     */
    private final File                      pnmlDatei;

    /**
     * Dies ist eine Referenz zum XML Parser. Diese Referenz wird durch die
     * Methode parse() initialisiert.
     *
     * @since 1.0
     */
    private XMLEventReader                  xmlParser = null;

    /**
     * Diese Variable dient als Zwischenspeicher für die ID des zuletzt
     * gefundenen Elements.
     *
     * @since 1.0
     */
    private String                          lastId = null;

    /**
     * Dieses Flag zeigt an, ob der Parser gerade innerhalb eines Token Elements
     * liest.
     *
     * @since 1.0
     */
    private boolean                         isToken = false;

    /**
     * Dieses Flag zeigt an, ob der Parser gerade innerhalb eines Name Elements
     * liest.
     *
     * @since 1.0
     */
    private boolean                         isName = false;

    /**
     * Dieses Flag zeigt an, ob der Parser gerade innerhalb eines Value Elements
     * liest.
     *
     * @since 1.0
     */
    private boolean                         isValue = false;

    /**
     * Liste mit allen Kanten,Stellen, Transistions.
     *
     * @since 1.0
     */
    private final ArrayListSearchID<IDBase> speicherArray = new ArrayListSearchID<>();

    /**
     * Dieser Konstruktor erstellt einen neuen Parser für PNML Dateien, dem die
     * PNML Datei als Java {@link File} übergeben wird.
     *
     * @since 1.0
     *
     * @param pnml Objekt der PNML Datei
     */
    public PNMLParser(File pnml) {
        this.pnmlDatei = pnml;
    }

    /**
     * Diese Methode öffnet die PNML Datei als Eingabestrom und initialisiert
     * den XML Parser.
     *
     * @throws de.BenediktKurth.myExceptions.DateiFehlerException Methode wirft
     * DateiFehlerException und informiert den Nutzer.
     *
     * @since 1.0
     */
    public void initParser() throws DateiFehlerException {
        //Versuche Datei zu öffnen und versuche aus Datei zu lesen
        try {
            InputStream dateiEingabeStrom = new FileInputStream(pnmlDatei);
            XMLInputFactory factory = XMLInputFactory.newInstance();
            xmlParser = factory.createXMLEventReader(dateiEingabeStrom);
            
        } catch (XMLStreamException | FileNotFoundException e) {
            //Informiere Nutzer über fehlgeschlagenes laden.
            throw new DateiFehlerException("Datei konnte nicht geladen werden!");
        }
    }

    /**
     * Diese Methode liest die XML Datei und delegiert die gefundenen XML
     * Elemente an die entsprechenden Methoden.
     *
     * @throws de.BenediktKurth.myExceptions.DateiFehlerException Methode wirft
     * DateiFehlerException und informiert den Nutzer.
     *
     * @since 1.0
     */
    public void parse() throws DateiFehlerException {
        //Solange xmlParser Datenvorhanden, entnehme sie.
        while (xmlParser.hasNext()) {
            try {
                XMLEvent event = xmlParser.nextEvent();
                switch (event.getEventType()) {
                    case XMLStreamConstants.START_ELEMENT:
                        handleStartEvent(event);
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        String name = event.asEndElement().getName().toString()
                                .toLowerCase();
                        switch (name) {
                            case "token":
                                isToken = false;
                                break;
                            case "name":
                                isName = false;
                                break;
                            case "value":
                                isValue = false;
                                break;
                            default:
                                break;
                        }
                        break;
                    case XMLStreamConstants.CHARACTERS:
                        if (isValue && lastId != null) {
                            Characters ch = event.asCharacters();
                            if (!ch.isWhiteSpace()) {
                                handleValue(ch.getData());
                            }
                        }
                        break;
                    case XMLStreamConstants.END_DOCUMENT:
                        //schließe den Parser
                        xmlParser.close();
                        break;
                    default:
                        break;
                }
            } catch (XMLStreamException e) {
                //Informiere Nutzer über fehlgeschlagenes laden.
                throw new DateiFehlerException("Datei konnte nicht geladen werden!");
            }
        }
    }

    /**
     * Diese Methode behandelt den Start neuer XML Elemente, in dem der Name des
     * Elements überprüft wird und dann die Behandlung an spezielle Methoden
     * delegiert wird.
     *
     * @param event {@link XMLEvent}
     *
     * @throws de.BenediktKurth.myExceptions.DateiFehlerException Methode wirft
     * DateiFehlerException und informiert den Nutzer.
     *
     * @since 1.0
     */
    private void handleStartEvent(final XMLEvent event) throws DateiFehlerException {
        StartElement element = event.asStartElement();
        switch (element.getName().toString().toLowerCase()) {
            case "transition":
                handleTransition(element);
                break;
            case "place":
                handlePlace(element);
                break;
            case "arc":
                handleArc(element);
                break;
            case "name":
                isName = true;
                break;
            case "position":
                handlePosition(element);
                break;
            case "token":
                isToken = true;
                break;
            case "value":
                isValue = true;
                break;
            default:
                break;
        }
    }

    /**
     * Diese Methode wird aufgerufen, wenn Text innerhalb eines Value Elements
     * gelesen wird.
     *
     * @param value Der gelesene Text als String
     *
     * @since 1.0
     */
    private void handleValue(final String value) {
        if (isName) {
            setName(lastId, value);
        } else if (isToken) {
            setMarking(lastId, value);
        }
    }

    /**
     * Diese Methode wird aufgerufen, wenn ein Positionselement gelesen wird.
     *
     * @param element das Positionselement
     *
     * @throws de.BenediktKurth.myExceptions.DateiFehlerException Methode wirft
     * DateiFehlerException und informiert den Nutzer.
     *
     * @since 1.0
     */
    private void handlePosition(final StartElement element) throws DateiFehlerException {
        String x = null;
        String y = null;
        Iterator<?> attributes = element.getAttributes();
        while (attributes.hasNext()) {
            Attribute attr = (Attribute) attributes.next();
            if (attr.getName().toString().toLowerCase().equals("x")) {
                x = attr.getValue();
            } else if (attr.getName().toString().toLowerCase().equals("y")) {
                y = attr.getValue();
            }
        }
        if (x != null && y != null && lastId != null) {
            setPosition(lastId, x, y);
        } else {
            throw new DateiFehlerException("Unvollständige Position wurde verworfen!");
        }
    }

    /**
     * Diese Methode wird aufgerufen, wenn ein Transitionselement gelesen wird.
     *
     * @param element das Transitionselement
     *
     * @throws de.BenediktKurth.myExceptions.DateiFehlerException Methode wirft
     * DateiFehlerException und informiert den Nutzer.
     *
     * @since 1.0
     */
    private void handleTransition(final StartElement element) throws DateiFehlerException {
        String transitionId = null;
        Iterator<?> attributes = element.getAttributes();
        while (attributes.hasNext()) {
            Attribute attr = (Attribute) attributes.next();
            if (attr.getName().toString().toLowerCase().equals("id")) {
                transitionId = attr.getValue();
                break;
            }
        }
        if (transitionId != null) {
            newTransition(transitionId);
            lastId = transitionId;
        } else {
            lastId = null;
            throw new DateiFehlerException("Transition ohne id wurde verworfen!");

        }
    }

    /**
     * Diese Methode wird aufgerufen, wenn ein Stellenelement gelesen wird.
     *
     * @param element das Stellenelement
     *
     * @since 1.0
     */
    private void handlePlace(final StartElement element) {
        String placeId = null;
        Iterator<?> attributes = element.getAttributes();
        while (attributes.hasNext()) {
            Attribute attr = (Attribute) attributes.next();
            if (attr.getName().toString().toLowerCase().equals("id")) {
                placeId = attr.getValue();
                break;
            }
        }
        if (placeId != null) {
            newPlace(placeId);
            lastId = placeId;
        } else {

            lastId = null;
        }
    }

    /**
     * Diese Methode wird aufgerufen, wenn ein Kantenelement gelesen wird.
     * Unvollständige Kanten werden nicht berücksichtigt.
     *
     * @param element das Kantenelement
     *
     * @since 1.0
     */
    private void handleArc(final StartElement element) {
        String arcId = null;
        String source = null;
        String target = null;
        Iterator<?> attributes = element.getAttributes();
        while (attributes.hasNext()) {
            Attribute attr = (Attribute) attributes.next();
            switch (attr.getName().toString().toLowerCase()) {
                case "id":
                    arcId = attr.getValue();
                    break;
                case "source":
                    source = attr.getValue();
                    break;
                case "target":
                    target = attr.getValue();
                    break;
                default:
                    break;
            }
        }
        if (arcId != null && source != null && target != null) {
            newArc(arcId, source, target);
        }

        //Kanten die nicht vollständig sind werden nicht beachtet!!!!!!!!!!!
        //Die id von Kanten wird nicht gebraucht
        lastId = null;
    }

    /**
     * Diese Methode kann überschrieben werden, um geladene Transitionen zu
     * erstellen.
     *
     * @param id Identifikationstext der Transition
     *
     * @since 1.0
     */
    private void newTransition(final String id) {
        //Erstellt neue Transition mit der übergebenen ID und ansonsten Null Werten.
        Transition tempTran = new Transition(id);
        speicherArray.add(tempTran);
    }

    /**
     * Diese Methode kann überschrieben werden, um geladene Stellen zu
     * erstellen.
     *
     * @param id Identifikationstext der Stelle
     *
     * @since 1.0
     */
    private void newPlace(final String id) {
        //Erstellt neue Stelle mit der übergebenen ID und ansonsten Null Werten.
        Stellen tempStellen = new Stellen(id);
        speicherArray.add(tempStellen);
    }

    /**
     * Die Methode erstellt ein neues sicheres Arc-Objekt. Es wird geprüft ob
     * Start und Ziel nicht Stelle und Stelle oder Transition und Transition
     * ist, weiter wird geprüft ob die beiden Objekte tatsächlich existieren.
     *
     * @param id Identifikationstext der Kante
     * @param source Identifikationstext des Startelements der Kante
     * @param target Identifikationstext des Endelements der Kante
     *
     * @since 1.0
     */
    private void newArc(final String id, final String source, final String target) {

        try {
            Arc temp = new Arc(id, source, target, speicherArray);
            speicherArray.add(temp);
        } catch (ArcFehlerException ex) {

        }

    }

    /**
     * Diese Methode kann überschrieben werden, um die Positionen der geladenen
     * Elemente zu aktualisieren.
     *
     * @param id Identifikationstext des Elements
     * @param x x Position des Elements
     * @param y y Position des Elements
     *
     * @since 1.0
     */
    private void setPosition(final String id, final String x, final String y) {

        //Findet das gesuchte Objekt mit searchID
        IDBase gesuchtesObjekt = speicherArray.sucheID(id);

        // Wenn das gefunden Objekt eine Instanze von GeruestLabel ist und damit 
        // x und y Positionswerte hat, so setze diese, ansonsten tue nichts
        if (gesuchtesObjekt instanceof PosNameBase) {
            PosNameBase temp;
            temp = (PosNameBase) gesuchtesObjekt;
            temp.setPositionfromString(x, y);
        }
    }

    /**
     * Diese Methode kann überschrieben werden, um den Beschriftungstext der
     * geladenen Elemente zu aktualisieren.
     *
     * @param id Identifikationstext des Elements.
     * @param name Beschriftungstext des Elements.
     *
     * @since 1.0
     */
    private void setName(final String id, final String name) {
        //Findet das gesuchte Objekt mit searchID
        IDBase gesuchtesObjekt = speicherArray.sucheID(id);

        // Wenn das gefunden Objekt eine Instanze von GeruestLabel ist und damit 
        // einen Label (Namen) hat, so setze diesen, ansonsten tue nichts
        if (gesuchtesObjekt instanceof PosNameBase) {
            PosNameBase temp;
            temp = (PosNameBase) gesuchtesObjekt;
            temp.setLabel(name);
        }
    }

    /**
     * Diese Methode kann überschrieben werden, um die Markierung der geladenen
     * Elemente zu aktualisieren.
     *
     * @param id Identifikationstext des Elements
     * @param marking Markierung des Elements
     *
     * @since 1.0
     */
    private void setMarking(final String id, final String marking) {

        //Findet das gesuchte Objekt mit searchID
        IDBase gesuchtesObjekt = speicherArray.sucheID(id);

        // Wenn das gefunden Objekt eine Instanze von GeruestLabel ist und damit 
        // einen Label (Namen) hat, so setze diesen, ansonsten tue nichts
        if (gesuchtesObjekt instanceof Stellen) {
            Stellen temp;
            temp = (Stellen) gesuchtesObjekt;
            temp.setInitialMarking(marking);
        }
    }

    /**
     * Gibt das SpeicherArray mit den neuen Basisdaten zurück. Hilfsmethode.
     * 
     * @return  ArrayListSearchID mit allen Basisdaten.
     * 
     * @since 1.0
     */
    private ArrayListSearchID<IDBase> getSpeicherArray(){
        return speicherArray;
    }
    /**
     * Diese Methode ermöglicht es eine eine PNML-Datei mithile ihres
     * Quellverzeichnises aufzurufen. Die Methode liest den Inhalt der
     * PNML-Datei aus und erzeugt ein ArrayListSearchID mit allen Objekt.
     *
     * @param Dateiname Quellverzeichnis inkl. Dateibezeichnung
     * ("D:\\Desktop\\ProPra\\Beispiele\\Beispiel-01.pnml")
     *
     * @return ArrayListSearchID Basisdaten der Datei
     *
     * @throws de.BenediktKurth.myExceptions.DateiFehlerException Methode wirft
     * DateiFehlerException und informiert den Nutzer.
     *
     * @since 1.0
     */
    public static ArrayListSearchID<IDBase> ladenUndGeben(String Dateiname) throws DateiFehlerException {
        PNMLParser pnmlParser;

        //Wenn Dateiname ungleich NULL
        if (Dateiname != null) {
            //Erstlle neues 
            File pnmlDatei = new File(Dateiname);

            //Extrahiere Dateinamen
            String testString = pnmlDatei.getName().toLowerCase();
            //Länge der Zeichenkette, damit offset für Dateiendung geprüft werden kann.
            int testStringLaenge = testString.length();
            
            if (testStringLaenge > 5){
                //Schneide letzten teil des Dateinames ab (Dateiendung)
                testString = testString.substring(testStringLaenge - 5, testStringLaenge);    
            }
            
            //Wenn eine Datei existiert und sie mit ".pnml" endet
            if (pnmlDatei.exists() && testString.equals(".pnml")) {
                //Erzeuge Parser und führe entsprechende Aktionen aus
                pnmlParser = new PNMLParser(pnmlDatei);
                pnmlParser.initParser();
                pnmlParser.parse();
            } else {
                //Falsche Dateiendung
                String uebergabe = "Die Datei | " + pnmlDatei.getName()
                        + " | konnte nicht geöffnet werden!";
                throw new DateiFehlerException(uebergabe);
            }
        } else {
            //Es wurde keine Datei übergeben
            throw new DateiFehlerException("Bitte eine Datei als Parameter angeben!");
        }

        //Es wird das vollständige speicherArray mit allen Basisdaten der Datei zurückgegeben.
        return pnmlParser.getSpeicherArray();
    }
}