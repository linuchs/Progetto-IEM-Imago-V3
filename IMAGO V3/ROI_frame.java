/*===========================*/
/*TUTTE LE LIBRERIE DA IMPORTARE*/
import javax.swing.*;
import java.awt.*;
import java.io.*;
import javax.imageio.ImageIO.*;
import javax.imageio.*;
import java.awt.image.*;
import java.awt.event.*;
import javax.swing.filechooser.FileFilter;
import java.awt.geom.*;
import java.awt.RenderingHints;

/*===========================*/

class ROI_frame extends JFrame implements ActionListener//, MouseListener, MouseMotionListener
{  
    JMenuBar BARRA_MENU= new JMenuBar();
    JMenu MENU_FILE= new JMenu("File");
    JMenuItem SOTTO_SALVA= new JMenuItem("Salva selezione");
   // JMenuItem SOTTO_CHIUDI= new JMenuItem("Chiudi");
     
    private JFileChooser aprifile1=new JFileChooser(); 
        
    BufferedImage image=null;
    
    public ROI_frame(BufferedImage selezione) 
    {  
           /* setUndecorated(true);
            getRootPane().setWindowDecorationStyle(JRootPane.FRAME);*/

        Toolkit kit=Toolkit.getDefaultToolkit();
        Dimension DIM=kit.getScreenSize();
        
        int w=selezione.getWidth();
        int h=selezione.getHeight();
        image=new BufferedImage(w,h, BufferedImage.TYPE_INT_RGB);  
        image=selezione;   
        
        this.setSize(w+10,h+10);
        //this.setDefaultCloseOperation(EXIT_ON_CLOSE);
       
        
        BARRA_MENU.add(MENU_FILE);
        setJMenuBar(BARRA_MENU);
        MENU_FILE.add(SOTTO_SALVA);
      //  MENU_FILE.add(SOTTO_CHIUDI);
        SOTTO_SALVA.addActionListener(this);
      //  SOTTO_CHIUDI.addActionListener(this);
    }
    public void actionPerformed(ActionEvent e)
    {
        Object source=e.getSource();
        if (source==SOTTO_SALVA)       {    this.salva(); }
           // else if (source==SOTTO_CHIUDI)  { ;}
    }
   
    
    
   /*Questo metodo e' chiamato  per salvare una immagine dal menu  File-Salva*/         
    private int salva()
    { 
        //infine alcune cose da precisare per il FileChooser
        // non vogliamo che la applicazione legga tutti i file
        aprifile1.setAcceptAllFileFilterUsed(false);
        // e li precisiamo usando la classe Filter
        aprifile1.setFileFilter(new IlMioFiltro());
        int risposta2=JOptionPane.NO_OPTION;
        File f=null;
        String ext=null;
        while (risposta2==JOptionPane.NO_OPTION) //Finchè non decido di salvare o di rinunciare
        {
            //Visualizzo la finestra di dialogo
            int risposta=aprifile1.showSaveDialog(this);
            
            if (risposta==aprifile1.APPROVE_OPTION) //Se ho premuto il tasto salva
            {
                try
                {
                    //Recupero il file selezionato
                    f=aprifile1.getSelectedFile();
                    //Recupero l'estensione del file
                    ext=aprifile1.getFileFilter().toString();
                    System.out.println("***************"+ext);
                    //Recupero il path del file
                    String str=f.getCanonicalPath();
                    //Se il nome del file non contiene l'estensione, la aggiungo io a mano
                    if (!str.toLowerCase().endsWith("."+ext)) f=new File(str+"."+ext);
                    //Se il file esiste chiedo se lo voglio sovrascrivere
                    if (f.exists()) risposta2=JOptionPane.showConfirmDialog(this,"Il file esiste già,\nlo vuoi sovrascrivere?",
                    "Sovrascrittura",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
                    else
                    {
                        risposta2=JOptionPane.YES_OPTION;
                    }
                }
                catch (Exception ex)
                {
                }
            } 
            else 
            {
                risposta2=JOptionPane.CANCEL_OPTION;
            }
        }
        if (risposta2==JOptionPane.YES_OPTION)
        {
            try
            {
                //Provo a salvare l'immagine
                
                ImageIO.write(image,ext,f);
            }
            catch (Exception ex)
            {
            }
        }
        return risposta2;
    }
}