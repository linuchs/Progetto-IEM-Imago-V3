import javax.swing.*;
import java.awt.*;
import java.io.*;
import javax.imageio.ImageIO.*;
import javax.imageio.*;
import java.awt.image.*;
import java.awt.event.*;
import javax.swing.filechooser.FileFilter;

class ScreenShotFrame extends JFrame implements ActionListener
{   
    public static void main(String[] args)
    {
        JFrame Main_Frame=new ScreenShotFrame();
        Main_Frame.setVisible(true);
        Main_Frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    private JFileChooser aprifile1=new JFileChooser();
    JScrollPane scroll;
    
    JMenuBar barra=new JMenuBar();
    JMenu menu1 =new JMenu("Opzioni");
    JMenuItem item1=new JMenuItem("Cattura");
    JMenuItem item3=new JMenuItem("Cattura selezione");
    JMenuItem item2=new JMenuItem("Salva");
   
    pannello_immagine_C pannello = new pannello_immagine_C();
    Cattura_Schermo nuovo=new Cattura_Schermo();
    
    Dimension DIM;
    Dimension dim;
    
    public ScreenShotFrame()
    {
        Toolkit kit=Toolkit.getDefaultToolkit();
        DIM=kit.getScreenSize(); 
        setSize((DIM.width/4),DIM.height/4);
        dim=getSize();
       
        setTitle("CATTURA DALLO SCHERMO");
        setLocation((DIM.width-dim.width)/2,(DIM.height-dim.height)/2);
     
        
        setJMenuBar(barra);
        barra.add(menu1);
        menu1.add(item1);
        menu1.add(item2);
        item1.addActionListener(this);
        item2.addActionListener(this);
         item3.addActionListener(this);
        
        scroll= new JScrollPane(pannello);
        Container contentPane=getContentPane();
        contentPane.add(scroll,BorderLayout.CENTER);

        scroll.revalidate();
        
    }
    public void actionPerformed(ActionEvent evento)
    {
        Object sorgente=evento.getSource();
        if(sorgente==item1)
        {  
            
            try
            {
           setExtendedState(JFrame.ICONIFIED);
                try {Thread.sleep(500);} catch (Exception e) {}
                    
            nuovo.Screen_Shot(DIM); 
            pannello.setImage(nuovo.getImage(),nuovo.getImage().getWidth(this),nuovo.getImage().getHeight(this));
            
              
               setLocation((DIM.width-dim.width)/2,(DIM.height-dim.height)/2);
                //setSize((DIM.width/2),DIM.height/2); 
                
                setExtendedState(JFrame.NORMAL);
                }
            catch(Exception e)
            {
              //  System.out.println(e);
                //JOptionPane.showMessageDialog(null,"CHIUSURA DEL PROGRAMMA");
            }
        } 
        else
            if(sorgente==item3)
            { 
                try
                {
                    if( nuovo.getValue())
                    {
                        
                        Salva_immagine();
                    }
                }
                catch(Exception e)
                {
                    System.out.println(e);
                    JOptionPane.showMessageDialog(null,"TENTATIVO DI SALVARE UNA IMMAGINE INESISTENTE");
                }
         
            } 
        else
            if(sorgente==item2)
            { 
                try
                {
                    if( nuovo.getValue())
                    {
                        
                        Salva_immagine();
                    }
                }
                catch(Exception e)
                {
                    System.out.println(e);
                    JOptionPane.showMessageDialog(null,"TENTATIVO DI SALVARE UNA IMMAGINE INESISTENTE");
                }
         
            }            
    }
 public void Salva_immagine()throws Exception
    {
         //infine alcune cose da precisare per il FileChooser
        // non vogliamo che la applicazione legga tutti i file
        aprifile1.setAcceptAllFileFilterUsed(false);
        // e li precisiamo usando la classe Filter
        aprifile1.setFileFilter(new IlMioFiltro());
        int risposta;
        int risposta2=JOptionPane.NO_OPTION;
        File f=null;
        String ext=null;
        while (risposta2==JOptionPane.NO_OPTION) //Finchè non decido di salvare o di rinunciare
        {
            //Visualizzo la finestra di dialogo
           risposta=aprifile1.showSaveDialog(this);
            
            if (risposta==aprifile1.APPROVE_OPTION) //Se ho premuto il tasto salva
            {
                try
                {
                    //Recupero il file selezionato
                    f=aprifile1.getSelectedFile();
                    //Recupero l'estensione del file
                    ext=aprifile1.getFileFilter().toString();
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
              //  ImageIO.write(image,ext,f); 
                ImageIO.write( nuovo.getImage(), "jpg", f);
            }
            catch (Exception ex)
            {
            }
        }
       // return risposta2;
    }
/*=======================================================*/
class Cattura_Schermo  
{
   
    BufferedImage image;
   
    boolean valore;
    
    public  void Screen_Shot(Dimension screenSize)throws Exception
    {
       
        
        Rectangle screenRect = new Rectangle(screenSize);
        
        try {Thread.sleep(500);} catch (Exception e) {}
             
        Robot robot = new Robot();
        image = robot.createScreenCapture(screenRect);
        valore=true;
    }
    
   
          
          
    public boolean getValue()
    {
        return valore;
    }
    public BufferedImage getImage()
    {
        return image;
    }
}
/*=============================================*/
class pannello_immagine_C extends JPanel
{
    private Image img;
        Dimension DIM;
        
    public pannello_immagine_C()
    {
      
        setBackground(Color.DARK_GRAY);
    }
        
    public void paintComponent(Graphics g)
    {
        DIM=this.getSize();
        super.paintComponent(g); 
        if (img!=null) 
       
        g.drawImage(img,0,0,img.getWidth(this),img.getHeight(this),null);

    }
    
    //metodo di accesso alla variabile img
    public void setImage(Image imm)
    {
        this.img=imm;
        // questo costringe il JPanel ad aggiornare
        // ciò che esso manda allo schermo
        this.repaint();
    }
             
    //metodo di accesso alla variabile img overloaded
    // per gestire cambi di dimensione nella immagine
    public void setImage(Image imm, int w, int h)
    {
          
        this.img=imm;
        this.setPreferredSize(new Dimension(w,h));
        scroll.revalidate();
        // questo costringe il JPanel ad aggiornare
        //  che esso manda allo schermo
        this.repaint();
    }
}
}
