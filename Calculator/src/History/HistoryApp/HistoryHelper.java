package HistoryApp;


/**
* HistoryApp/HistoryHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from history.idl
* samedi 18 d�cembre 2021 20 h 22 WAT
*/

abstract public class HistoryHelper
{
  private static String  _id = "IDL:HistoryApp/History:1.0";

  public static void insert (org.omg.CORBA.Any a, HistoryApp.History that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static HistoryApp.History extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (HistoryApp.HistoryHelper.id (), "History");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static HistoryApp.History read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_HistoryStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, HistoryApp.History value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static HistoryApp.History narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof HistoryApp.History)
      return (HistoryApp.History)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      HistoryApp._HistoryStub stub = new HistoryApp._HistoryStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

  public static HistoryApp.History unchecked_narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof HistoryApp.History)
      return (HistoryApp.History)obj;
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      HistoryApp._HistoryStub stub = new HistoryApp._HistoryStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}