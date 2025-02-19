import javax.swing.filechooser.FileFilter;
import java.io.*;
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////                                                   FILTRI  UNO                                                        ///////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////*CLASSE IlMioFiltro filtra i tipi di file di immagini che saranno aperti*/////////////////////////////////////////
class IlMioFiltro extends FileFilter// mi serve per i filtri
{
    String extend;
    public boolean accept(File f)
    { 
        String str=f.getName().toLowerCase();
        
        if(str.endsWith(".jpg")==true)
        {
            extend="jpg";
        }
        else
            if(str.endsWith(".jpeg")==true)
            {
            
            extend="jpeg";
            }
            else
                if(str.endsWith(".gif")==true) 
                {
                    extend="gif";
                }
                else
                    if(str.endsWith(".png")==true) 
                    {
                         extend="png";
                    }
       
        return(f.isDirectory()||str.endsWith(".gif")||str.endsWith(".png")||str.endsWith(".jpg")||str.endsWith(".jpeg"));
         
    }
    public String getDescription()
    {
        return "Immagini del tipo: gif, png, jpg, jpeg .";
    }
    public String toString()
  {
    return extend;
  }
}

