import javax.swing.filechooser.FileFilter;
import java.io.*;
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////                                                   FILTRI     DUE                                             ////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/*==============================================

Classe per filtrare i file jpg che potremo aprire con il
FileChooser

=================================================*/
class JPGFilter extends FileFilter
{
  public boolean accept(File f)
  {
    String str=f.getName().toLowerCase();
     
    return str.endsWith(".jpg")||str.endsWith(".jpeg")||f.isDirectory();
  }

  public String getDescription()
  {
    return "IMMAGINE JPG";
  }

  public String toString()
  {
    return "jpg";
  }
}
