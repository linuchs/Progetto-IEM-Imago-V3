import javax.swing.filechooser.FileFilter;
import java.io.*;
/*==============================================

Classe per filtrare i file png che potremo aprire con il
FileChooser

=================================================*/


class PNGFilter extends FileFilter
{
  public boolean accept(File f)
  {
    return f.getName().toLowerCase().endsWith(".png")||f.isDirectory();
  }

  public String getDescription()
  {
    return "IMMAGINE PNG";
  }

  public String toString()
  {
    return "png";
  }
}