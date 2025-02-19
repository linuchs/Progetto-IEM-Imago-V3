import java.awt.image.*;
import java.awt.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import java.awt.event.*;


class ScreenShotFrame extends JFrame implements ActionListener
{
    JMenuBar barra=new JMenuBar();
    JMenu menu1 =new JMenu("Cattura Schermo");
    JMenuItem item1=new JMenuItem("Cattura");
    JMenuItem item2=new JMenuItem("Salva");
    pannello_immagine pannello = new pannello_immagine();
    Cattura_Schermo nuovo=new Cattura_Schermo(); 
    public static void main(String[] args)
    {
        JFrame Main_Frame=new ScreenShotFrame();
        Main_Frame.setVisible(true);
    }
    public ScreenShotFrame()
    {
        Toolkit kit=Toolkit.getDefaultToolkit();
        Dimension DIM=kit.getScreenSize(); 
        setSize(DIM.width/4,DIM.height/4);
        Dimension dim=getSize();
       
        setLocation((DIM.width-dim.width)/2,(DIM.height-dim.height)/2);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        setJMenuBar(barra);
        barra.add(menu1);
        menu1.add(item1);
        menu1.add(item2);
        item1.addActionListener(this);
        item2.addActionListener(this);
        Container contentPane=getContentPane();
        contentPane.add(pannello);
    }
    public void actionPerformed(ActionEvent evento)
    {
        Object sorgente=evento.getSource();
        if(sorgente==item1)
        {  
            
            try
            {
            setExtendedState(JFrame.ICONIFIED);
            nuovo.Screen_Shot();
            pannello.setImage(nuovo.getImage(),nuovo.getImage().getWidth(this),nuovo.getImage().getHeight(this));
          // try {Thread.sleep(500);} catch (Exception e) {}
            setExtendedState(JFrame.MAXIMIZED_BOTH);
                }
            catch(Exception e)
            {
              //  System.out.println(e);
                //JOptionPane.showMessageDialog(null,"CHIUSURA DEL PROGRAMMA");
            }
        }
        else
            if(sorgente==item2)
            { 
                try
                {
                    if( nuovo.getValue())
                    {
                        
                        nuovo.Salva_immagine();
                    }
                }
                catch(Exception e)
                {
                    System.out.println(e);
                    JOptionPane.showMessageDialog(null,"TENTATIVO DI SALVARE UNA IMMAGINE INESISTENTE");
                }
         
            }            
    }
}
/*=======================================================*/
class Cattura_Schermo  
{
      BufferedImage image;
    String nome_immagine="";
    boolean valore;
    public  void Screen_Shot()throws Exception
    {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        
        Rectangle screenRect = new Rectangle(screenSize);
        
        do
        {
            nome_immagine=JOptionPane.showInputDialog("Inserisci il nome dell' immagine");
        }
        while(nome_immagine.length()<1);
            
        // Object[] options = { "Attiva", "Annulla" };
        // int valore=JOptionPane.showOptionDialog(null, "FAI LA TUA SCELTA", "Screenshot", 
        // JOptionPane.WARNING_MESSAGE,JOptionPane.DEFAULT_OPTION, null, options, options[0]);	
        // System.out.println(""+valore);
        //   if(valore==0){.........}
         try {Thread.sleep(500);} catch (Exception e) {}
        Robot robot = new Robot();
        image = robot.createScreenCapture(screenRect);
        valore=true;
    }
    public void Salva_immagine()throws Exception
    {
         ImageIO.write(image, "jpg", new File(nome_immagine+".jpg"));
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
class pannello_immagine extends JPanel
{
    private Image img;
        Dimension DIM;
        
    public pannello_immagine()
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
        // questo costringe il JPanel ad aggiornare
        //  che esso manda allo schermo
        this.repaint();
    }
}

