package de.BenediktKurth.model;

import de.BenediktKurth.myExceptions.DateiFehlerException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * Diese Klasse implementiert eine einfache XML Ausgabe für PNML Dateien.
 *
 * @author Benedikt Kurth und FernUniversität Hagen
 *
 * @since 1.0
 *
 * @version 1.0
 */
public final class PNMLWriter {

    /**
     * Dies ist eine Referenz zum Java Datei Objekt.
     *
     * @since 1.0
     */
    private final File                          pnmlDatei;

    /**
     * Dies ist eine Referenz zum XML Writer. Diese Referenz wird durch die
     * Methode startXMLDocument() initialisiert.
     *
     * @since 1.0
     */
    private XMLStreamWriter                     writer = null;

    /**
     * Dient dem schließen der zu speichernden Datei.
     *
     * @since 1.0
     */
    private FileOutputStream                    fos;

    /**
     * Dieser Konstruktor erstellt einen neuen Writer für PNML Dateien, dem die
     * PNML Datei als Java {@link File} übergeben wird.
     *
     * @param pnml Java {@link File} Objekt der PNML Datei
     *
     * @since 1.0
     */
    public PNMLWriter(final File pnml) {
        super();
        this.pnmlDatei = pnml;
    }

    /**
     * Diese Methode beginnt ein neues XML Dokument und initialisiert den XML
     * Writer für diese Datei.
     *
     * @throws de.BenediktKurth.myExceptions.DateiFehlerException Methode wirft
     * DateiFehlerException und informiert den Nutzer.
     *
     * @since 1.0
     */
    public void startXMLDocument() throws DateiFehlerException {
        try {
            fos = new FileOutputStream(pnmlDatei);
            XMLOutputFactory factory = XMLOutputFactory.newInstance();
            writer = factory.createXMLStreamWriter(fos, "UTF-8");
            // XML Dokument mit Version 1.0 und Kodierung UTF-8 beginnen
            writer.writeStartDocument("UTF-8", "1.0");
            writer.writeStartElement("pnml");
            writer.writeStartElement("net");

        } catch (FileNotFoundException e) {
            throw new DateiFehlerException("Die Datei " + pnmlDatei.getAbsolutePath()
                    + " kann nicht geschrieben werden! " + e.getMessage());

        } catch (XMLStreamException e) {
            throw new DateiFehlerException("XML Fehler: " + e.getMessage());
        }
    }

    /**
     * Diese Methode beendet das Schreiben eines Petrinetzes als XML Datei.
     *
     * @throws de.BenediktKurth.myExceptions.DateiFehlerException Methode wirft
     * DateiFehlerException und informiert den Nutzer.
     *
     * @since 1.0
     */
    public void finishXMLDocument() throws DateiFehlerException {
        if (writer != null) {
            try {
                writer.writeEndElement();
                writer.writeEndElement();
                writer.writeEndDocument();
                writer.close();
                fos.close();
            } catch (XMLStreamException e) {
                throw new DateiFehlerException("XML Fehler: " + e.getMessage());

            } catch (IOException ex) {
                throw new DateiFehlerException("Datei Fehler!");
            }
        } else {
            throw new DateiFehlerException("Das Dokument wurde noch nicht gestartet!");
        }
    }

    /**
     * Diese Methode fügt eine neue Transition zum XML Dokument hinzu. Vor
     * dieser Methode muss startXMLDocument() aufgerufen worden sein.
     *
     * @param transition Transition
     *
     * @throws de.BenediktKurth.myExceptions.DateiFehlerException Methode wirft
     * DateiFehlerException und informiert den Nutzer.
     *
     * @since 1.0
     */
    public void addTransition(Transition transition) throws DateiFehlerException {
        if (writer != null) {
            try {
                writer.writeStartElement("", "transition", "");
                writer.writeAttribute("id", transition.getID());

                writer.writeStartElement("", "name", "");
                writer.writeStartElement("", "value", "");
                writer.writeCharacters(transition.getLabel());
                writer.writeEndElement();
                writer.writeEndElement();

                writer.writeStartElement("", "graphics", "");
                writer.writeStartElement("", "position", "");
                writer.writeAttribute("x", transition.gibXPosition());
                writer.writeAttribute("y", transition.gibYPosition());
                writer.writeEndElement();
                writer.writeEndElement();

                writer.writeEndElement();
            } catch (XMLStreamException e) {
                throw new DateiFehlerException("Transition " + transition.getID()
                        + " konnte nicht geschrieben werden! "
                        + e.getMessage());

            }

        } else {
            throw new DateiFehlerException("Das Dokument muss zuerst gestartet werden!");
        }
    }

    /**
     * Diese Methode fügt eine neue Stelle zum XML Dokument hinzu. Vor dieser
     * Methode muss startXMLDocument() aufgerufen worden sein.
     *
     * @param stelle Stellen
     *
     * @throws de.BenediktKurth.myExceptions.DateiFehlerException Methode wirft
     * DateiFehlerException und informiert den Nutzer.
     *
     * @since 1.0
     */
    public void addStellen(Stellen stelle) throws DateiFehlerException {
        if (writer != null) {
            try {
                writer.writeStartElement("", "place", "");
                writer.writeAttribute("id", stelle.getID());

                writer.writeStartElement("", "name", "");
                writer.writeStartElement("", "value", "");
                writer.writeCharacters(stelle.getLabel());
                writer.writeEndElement();
                writer.writeEndElement();

                writer.writeStartElement("", "initialMarking", "");
                writer.writeStartElement("", "token", "");
                writer.writeStartElement("", "value", "");
                writer.writeCharacters(stelle.getMarkierungString());
                writer.writeEndElement();
                writer.writeEndElement();
                writer.writeEndElement();

                writer.writeStartElement("", "graphics", "");
                writer.writeStartElement("", "position", "");
                writer.writeAttribute("x", stelle.gibXPosition());
                writer.writeAttribute("y", stelle.gibYPosition());
                writer.writeEndElement();
                writer.writeEndElement();

                writer.writeEndElement();
            } catch (XMLStreamException e) {
                throw new DateiFehlerException("Stelle " + stelle.getID()
                        + " konnte nicht geschrieben werden! "
                        + e.getMessage());

            }

        } else {
            throw new DateiFehlerException("Das Dokument muss zuerst gestartet werden!");
        }
    }

    /**
     * Diese Methode fügt eine neue Kante zum XML Dokument hinzu. Vor dieser
     * Methode muss startXMLDocument() aufgerufen worden sein.
     *
     * @param arc Arc
     *
     * @throws de.BenediktKurth.myExceptions.DateiFehlerException Methode wirft
     * DateiFehlerException und informiert den Nutzer.
     *
     * @since 1.0
     */
    public void addArc(Arc arc) throws DateiFehlerException {
        if (writer != null) {
            try {
                writer.writeStartElement("", "arc", "");
                writer.writeAttribute("id", arc.getID());
                writer.writeAttribute("source", arc.getSource());
                writer.writeAttribute("target", arc.getTarget());
                writer.writeEndElement();
            } catch (XMLStreamException e) {
                throw new DateiFehlerException("Kante " + arc.getID()
                        + " konnte nicht geschrieben werden! "
                        + e.getMessage());

            }

        } else {
            throw new DateiFehlerException("Das Dokument muss zuerst gestartet werden!");
        }
    }

    /**
     * Diese Methode erzeugt eine Datei am angegebenen Pfad
     *
     * @param fileName Enthält den Dateinpfad für die zu speichernde Datei
     *
     * @param ausgabeListe ArrayListSearchID
     *
     * @throws de.BenediktKurth.myExceptions.DateiFehlerException Methode wirft
     * DateiFehlerException und informiert den Nutzer.
     *
     * @since 1.0
     */
    public static void saveFile(final String fileName, ArrayListSearchID<IDBase> ausgabeListe) throws DateiFehlerException {
        //Wenn fileName ungleich NULL        
        if (fileName != null) {
            //Erzeuge neue Datei und neues PNMLDatei inkl start
            File pnmlDatei = new File(fileName);
            PNMLWriter pnmlWriter = new PNMLWriter(pnmlDatei);
            pnmlWriter.startXMLDocument();

            //Durchlaufe ausgabeListe und starte entsprechende Funktion
            for (IDBase x : ausgabeListe) {
                if (x instanceof Transition) {
                    pnmlWriter.addTransition((Transition) x);
                } else if (x instanceof Stellen) {
                    pnmlWriter.addStellen((Stellen) x);
                } else if (x instanceof Arc) {
                    pnmlWriter.addArc((Arc) x);
                }
            }
            //Schließe die PNML-Datei ab
            pnmlWriter.finishXMLDocument();
        }
    }
}