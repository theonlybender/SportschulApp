/*
 * $Source: /cvsroot/obantoo/obantoo/src/de/jost_net/OBanToo/Dtaus/DtausException.java,v $
 * $Revision: 1.6 $
 * $Date: 2007/02/22 18:40:21 $
 * $Author: jost $
 *
 * Copyright 2006 by Heiner Jostkleigrewe
 * Diese Datei steht unter LGPL - siehe beigef�gte lpgl.txt
 */
package de.sportschulApp.server.dtaus;

/**
 * Exception des DTAUS-Parsers
 * 
 * @author Heiner Jostkleigrewe
 */
public class DtausException extends Exception
{
  private static final long serialVersionUID = 790050704552032876L;

  public final static String A_AUSFUEHRUNGSDATUM_FEHLERHAFT = "Ausf�hrungsdatum fehlerhaft";

  public final static String A_SATZLAENGENFELD_FEHLERHAFT = "Satzl�ngenfeld des A-Satzes fehlerhaft: ";

  public final static String A_SATZART_FEHLERHAFT = "Satzart des A-Satzes fehlerhaft: ";

  public final static String A_GUTSCHRIFT_LASTSCHRIFT_FEHLERHAFT = "Gutschrift/Lastschrift-Kennzeichen des A-Satzes fehlerhaft: ";

  public final static String A_BLZ_FEHLERHAFT = "Bankleitzahl des A-Satzes fehlerhaft: ";

  public final static String A_AUFTRAGGEBER_FEHLERHAFT = "L�nge des Auftraggebers fehlerhaft (=0 oder >27)";

  public final static String A_DATEIERSTELLUNGSDATUM_FEHLERHAFT = "Dateierstellungsdatum des A-Satzes ist fehlerhaft: ";

  public final static String A_KONTO_FEHLERHAFT = "Konto des A-Satzes fehlerhaft: ";

  public final static String A_REFERENZ_FEHLERHAFT = "Referenz des A-Satzes nicht numerisch: ";

  public final static String A_WAEHRUNGSKENNZEICHEN_FEHLERHAFT = "W�hrungskennzeichen des A-Satzes fehlerhaft: ";

  public final static String C_SATZLAENGE_FEHLERHAFT = "Satzlaengenfeld des C-Satzes fehlerhaft: ";

  public final static String C_SATZART_FEHLERHAFT = "Satzart des C-Satzes fehlerhaft: ";

  public final static String C_BLZERSTBETEILIGT_FEHLERHAFT = "Bankleitzahl des erstbeteiligten Institutes fehlerhaft: ";

  public final static String C_BLZENDBEGUENSTIGT_FEHLERHAFT = "Bankleitzahl des endbeg�nstigten Institutes fehlerhaft: ";

  public final static String C_KONTONUMMER_FEHLERHAFT = "Kontonummer fehlerhaft: ";

  public final static String C_NAME_EMPFAENGER = "Name des Zahlungsempf�ngers/Zahlungspflichtigen ung�ltig.";

  public final static String C_NAME_EMPFAENGER2 = "Name(2) des Zahlungsempf�ngers/Zahlungspflichtigen ung�ltig.";

  public final static String C_NAME_ABSENDER = "Name des Absenders ung�ltig.";

  public final static String C_NAME_ABSENDER2 = "Name(2) des Absenders ung�ltig.";

  public final static String C_INTERNEKUNDENNUMMER_FEHLERHAFT = "Interne Kundennummer fehlerhaft: ";

  public final static String C_TEXTSCHLUESSEL_FEHLERHAFT = "Textschluessel fehlerhaft: ";

  public final static String C_ERSTBEAUFTRAGTESINSTITUT_FEHLERHAFT = "Erstbeauftragtes Institut fehlerhaft: ";

  public final static String C_KONTOAUFTRAGGEBER_FEHLERHAFT = "Konto Auftraggeber fehlerhaft: ";

  public final static String C_BETRAG_FEHLERHAFT = "Betrag fehlerhaft: ";

  public final static String C_WAEHRUNGSKENNZEICHEN_FEHLERHAFT = "W�hrungskennzeichen fehlerhaft: ";

  public final static String C_ERWEITERUNGSZEICHEN_FEHLERHAFT = "Erweiterungszeichen fehlerhaft: ";

  public final static String C_ERWEITERUNG_FEHLERHAFT = "Erweiterung fehlerhaft: ";

  public final static String C_VERWENDUNGSZWECK_FEHLERHAFT = "Verwendungszweck fehlerhaft (L�nge > 27) oder mehr als 13 St�ck.";

  public final static String E_SATZLAENGENFELD_FEHLERHAFT = "Satzl�ngenfeld des E-Satzes fehlerhaft: ";

  public final static String E_SATZART_FEHLERHAFT = "Satzart des E-Satzes fehlerhaft: ";

  public final static String E_ANZAHL_CSAETZE_FEHLERHAFT = "Anzahl der C-S�tze im E-Satz fehlerhaft: ";

  public final static String E_SUMME_BETRAEGE_FEHLERHAFT = "Summe der Betr�ge im E-Satz fehlerhaft: ";

  public final static String SATZLAENGE_FEHLERHAFT = "Satzl�nge fehlerhaft: ";

  public final static String UNGUELTIGES_ZEICHEN = "Ung�ltiges Zeichen: ";

  public final static String UNGUELTIGE_LOGISCHE_DATEI = "Ung�ltige Logische Datei: ";

  public DtausException(String text)
  {
    super(text);
  }

  public DtausException(String text, String value)
  {
    super(text + value);
  }
}
/*
 * $Log: DtausException.java,v $
 * Revision 1.6  2007/02/22 18:40:21  jost
 * Implementierung der Erweiterungsteile 01 (Name Empfänger/Zahlungspflichtiger 2) und 03 (Absender/Zahlungsempfänger 2)
 *
 * Revision 1.5  2007/01/07 20:42:31  jost
 * Verwendungszwecke der Länge 0 zugelassen.
 *
 * Revision 1.4  2006/06/05 09:35:13  jost
 * Erweiterungen f. d. DtausDateiWriter
 * Revision 1.3 2006/05/30 17:40:09 jost Kommentar
 * eingef�gt Revision 1.2 2006/05/28 09:06:53 jost Zus�tzliche Konstante
 * 
 * Revision 1.1 2006/05/24 16:24:44 jost Prerelease
 * 
 */
