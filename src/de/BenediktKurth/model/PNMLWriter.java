package de.BenediktKurth.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * Diese Klasse implementiert eine einfache XML Ausgabe für
 * PNML Dateien.
 *
 * @author Benedikt Kurth und FernUniversität Hagen
 * 
 * @since 1.0
 * 
 * @version 1.0
 */
public final class PNMLWriter {

    /**
     * Diese Methode erzeugt eine Datei am angegebenen Pfad
     * 
     * @param fileName  Enthält den Dateinpfad für die zu speichernde Datei
     * 
     * @param ausgabeListe  
     *      
     */
    public static void saveFile(final String fileName, ArrayListSearchID ausgabeListe) throws NoSaveFileException{
        if (fileName != null) {
            File pnmlDatei = new File(fileName);
            PNMLWriter pnmlWriter = new PNMLWriter(pnmlDatei);
            pnmlWriter.startXMLDocument();

            int i = 0;
            
            while (i < ausgabeListe.size()-1){
                if (ausgabeListe.get(i) instanceof Transition){
                    pnmlWriter.addTransition((Transition)ausgabeListe.get(i));
                    i++;
                } 
                else if (ausgabeListe.get(i) instanceof Stellen){
                    pnmlWriter.addStellen((Stellen)ausgabeListe.get(i));
                    i++;
                }
                else if (ausgabeListe.get(i) instanceof Arc){
                    pnmlWriter.addArc((Arc)ausgabeListe.get(i));
                    i++;
                }
            }

            pnmlWriter.finishXMLDocument();
        } else {
            throw new NoSaveFileException("Bitte eine Datei auswählen");
        }
    }

    /**
     * Dies ist eine Referenz zum Java Datei Objekt.
     */
    private File            pnmlDatei;

    /**
     * Dies ist eine Referenz zum XML Writer. Diese Referenz wird durch die
     * Methode startXMLDocument() initialisiert.
     */
    private XMLStreamWriter writer = null;

    /**
     * Dieser Konstruktor erstellt einen neuen Writer für PNML Dateien,
     * dem die PNML Datei als Java {@link File} übergeben wird.
     * 
     * @param pnml
     *      Java {@link File} Objekt der PNML Datei
     */
    public PNMLWriter(final File pnml) {
        super();

        pnmlDatei = pnml;
    }

    /**
     * Diese Methode beginnt ein neues XML Dokument und initialisiert den
     * XML Writer für diese Datei.
     */
    public void startXMLDocument() {
        try {
            FileOutputStream fos = new FileOutputStream(pnmlDatei);
            XMLOutputFactory factory = XMLOutputFactory.newInstance();
            writer = factory.createXMLStreamWriter(fos, "UTF-8");
            // XML Dokument mit Version 1.0 und Kodierung UTF-8 beginnen
            writer.writeStartDocument("UTF-8", "1.0");
            writer.writeStartElement("pnml");
            writer.writeStartElement("net");
        } catch (FileNotFoundException e) {
            System.err.println("Die Datei " + pnmlDatei.getAbsolutePath()
                    + " kann nicht geschrieben werden! " + e.getMessage());
            e.printStackTrace();
        } catch (XMLStreamException e) {
            System.err.println("XML Fehler: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Diese Methode beendet das Schreiben eines Petrinetzes als XML Datei.
     */
    public void finishXMLDocument() {
        if (writer != null) {
            try {
                writer.writeEndElement();
                writer.writeEndElement();
                writer.writeEndDocument();
                writer.close();
            } catch (XMLStreamException e) {
                System.err.println("XML Fehler: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            System.err.println("Das Dokument wurde noch nicht gestartet!");
        }
    }

    /**
     * Diese Methode fügt eine neue Transition zum XML Dokument hinzu. Vor dieser Methode muss
     * startXMLDocument() aufgerufen worden sein.
     * 
     * @param transition
     * 
     */
    public void addTransition(Transition transition) {
        if (writer != null) {
            try {
                writer.writeStartElement("", "transition", "");
                writer.writeAttribute("id", transition.getId());

                writer.writeStartElement("", "name", "");
                writer.writeStartElement("", "value", "");
                writer.writeCharacters(transition.getLabel());
                writer.writeEndElement();
                writer.writeEndElement();

                writer.writeStartElement("", "graphics", "");
                writer.writeStartElement("", "position", "");
                writer.writeAttribute("x", transition.getxPosition());
                writer.writeAttribute("y", transition.getyPosition());
                writer.writeEndElement();
                writer.writeEndElement();

                writer.writeEndElement();
            } catch (XMLStreamException e) {
                System.err
                        .println("Transition " + transition.getId()
                                + " konnte nicht geschrieben werden! "
                                + e.getMessage());
                e.printStackTrace();
            }

        } else {
            System.err.println("Das Dokument muss zuerst gestartet werden!");
        }
    }

    /**
     * Diese Methode fügt eine neue Stelle zum XML Dokument hinzu. Vor dieser Methode muss
     * startXMLDocument() aufgerufen worden sein.
     * 
     * @param id
     *      Indentifikationstext der Stelle
     * @param label
     *      Beschriftung der Stelle
     * @param xPosition
     *      x Position der Stelle
     * @param yPosition
     *      y Position der Stelle
     * @param initialMarking
     *      Anfangsmarkierung der Stelle
     */
    public void addStellen(Stellen stelle) {
        if (writer != null) {
            try {
                writer.writeStartElement("", "place", "");
                writer.writeAttribute("id", stelle.getId());

                writer.writeStartElement("", "name", "");
                writer.writeStartElement("", "value", "");
                writer.writeCharacters(stelle.getLabel());
                writer.writeEndElement();
                writer.writeEndElement();

                writer.writeStartElement("", "initialMarking", "");
                writer.writeStartElement("", "token", "");
                writer.writeStartElement("", "value", "");
                writer.writeCharacters(stelle.getInitialMarking());
                writer.writeEndElement();
                writer.writeEndElement();
                writer.writeEndElement();

                writer.writeStartElement("", "graphics", "");
                writer.writeStartElement("", "position", "");
                writer.writeAttribute("x", stelle.getxPosition());
                writer.writeAttribute("y", stelle.getyPosition());
                writer.writeEndElement();
                writer.writeEndElement();

                writer.writeEndElement();
            } catch (XMLStreamException e) {
                System.err
                        .println("Stelle " + stelle.getId()
                                + " konnte nicht geschrieben werden! "
                                + e.getMessage());
                e.printStackTrace();
            }

        } else {
            System.err.println("Das Dokument muss zuerst gestartet werden!");
        }
    }

    /**
     * Diese Methode fügt eine neue Kante zum XML Dokument hinzu. Vor dieser Methode muss
     * startXMLDocument() aufgerufen worden sein.
     * 
     * @param id
     *      Indentifikationstext der Stelle
     * @param source
     *      Indentifikationstext des Startelements der Kante
     * @param target
     *      Indentifikationstext der Endelements der Kante
     */
    public void addArc(Arc arc) {
        if (writer != null) {
            try {
                writer.writeStartElement("", "arc", "");
                writer.writeAttribute("id", arc.getId());
                writer.writeAttribute("source", arc.getSource());
                writer.writeAttribute("target", arc.getTarget());
                writer.writeEndElement();
            } catch (XMLStreamException e) {
                System.err
                        .println("Kante " + arc.getId()
                                + " konnte nicht geschrieben werden! "
                                + e.getMessage());
                e.printStackTrace();
            }

        } else {
            System.err.println("Das Dokument muss zuerst gestartet werden!");
        }
    }
}
