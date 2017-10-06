package de.BenediktKurth.Exceptions;

/**
 *
 * @author clannick
 */
public class ZuVieleEndstellenException extends Exception {

    public ZuVieleEndstellenException(String anzahl){
        super(anzahl);
    }
}
