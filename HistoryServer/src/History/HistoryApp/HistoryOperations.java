package HistoryApp;


/**
* HistoryApp/HistoryOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from history.idl
* samedi 18 d�cembre 2021 20 h 22 WAT
*/

public interface HistoryOperations 
{
  void addCalculHistory (String cal);
  String[] getCalculHistory ();
  void deleteCalculHistory ();
  void addConvertHistory (String cal);
  String[] getConvertHistory ();
  void deleteConvertHistory ();
  void shutdown ();
} // interface HistoryOperations