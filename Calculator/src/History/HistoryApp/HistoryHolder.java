package HistoryApp;

/**
* HistoryApp/HistoryHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from history.idl
* samedi 18 d�cembre 2021 20 h 22 WAT
*/

public final class HistoryHolder implements org.omg.CORBA.portable.Streamable
{
  public HistoryApp.History value = null;

  public HistoryHolder ()
  {
  }

  public HistoryHolder (HistoryApp.History initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = HistoryApp.HistoryHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    HistoryApp.HistoryHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return HistoryApp.HistoryHelper.type ();
  }

}
