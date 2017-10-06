package de.BenediktKurth.Exceptions;

/**
 *
 * @author clannick
 */
public class ZuVieleAnfangsstellenException extends Exception {

    public ZuVieleAnfangsstellenException(String anzahl){
        super(anzahl);
    }
}
