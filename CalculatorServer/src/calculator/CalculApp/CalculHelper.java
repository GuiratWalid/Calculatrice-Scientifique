package CalculApp;


/**
* CalculApp/CalculHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from calcul.idl
* vendredi 17 d�cembre 2021 19 h 13 WAT
*/

abstract public class CalculHelper
{
  private static String  _id = "IDL:CalculApp/Calcul:1.0";

  public static void insert (org.omg.CORBA.Any a, CalculApp.Calcul that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static CalculApp.Calcul extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (CalculApp.CalculHelper.id (), "Calcul");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static CalculApp.Calcul read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_CalculStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, CalculApp.Calcul value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static CalculApp.Calcul narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof CalculApp.Calcul)
      return (CalculApp.Calcul)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      CalculApp._CalculStub stub = new CalculApp._CalculStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

  public static CalculApp.Calcul unchecked_narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof CalculApp.Calcul)
      return (CalculApp.Calcul)obj;
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      CalculApp._CalculStub stub = new CalculApp._CalculStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}
