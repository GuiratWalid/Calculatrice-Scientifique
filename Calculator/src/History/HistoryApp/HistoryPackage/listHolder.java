package HistoryApp.HistoryPackage;


/**
* HistoryApp/HistoryPackage/listHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from history.idl
* samedi 18 d?cembre 2021 20 h 22 WAT
*/

public final class listHolder implements org.omg.CORBA.portable.Streamable
{
  public String value[] = null;

  public listHolder ()
  {
  }

  public listHolder (String[] initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = HistoryApp.HistoryPackage.listHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    HistoryApp.HistoryPackage.listHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return HistoryApp.HistoryPackage.listHelper.type ();
  }

}
