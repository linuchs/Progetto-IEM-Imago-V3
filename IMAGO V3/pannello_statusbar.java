import java.awt.event.*;
import javax.swing.*;
import java.awt.*;


import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileView;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
class pannello_statusbar extends JPanel implements MouseListener//IL PANNELLO DI SUD
{ 
    String S="Status bar";
    String S1="Stato dell'aiuto: ";
    String stato="Spento";
   
    boolean HELP=false;
    JButton bottone_help=new JButton("Help");
    Color COLORE=Color.RED;
    
      Shape area1;
    Shape area2;
  //  JLabel label=new JLabel(stato,SwingConstants.LEFT);
    public pannello_statusbar()
    {
        setLayout(new BorderLayout());
        add(bottone_help,BorderLayout.EAST);
       setBackground(Color.WHITE);
        Font f=new Font("ARIAL",Font.PLAIN,12);
        setFont(f);
        setPreferredSize(new Dimension(500,25));
        bottone_help.addMouseListener(this);
    }
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2=(Graphics2D)g;
        g.drawString(S,5,16);
        
        /*=========zona pulssante help============*/
        area1=new Rectangle2D.Double(getSize().width-101,1,22,22);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.BLACK);
        g2.fill(area1);
        g2.drawString(S1+stato,getSize().width-240,16);
    //    g2.drawString(stato,getSize().width-240,16); 
        
        area2=new Ellipse2D.Double(getSize().width-100,2,20,20);
        g2.setColor(COLORE);
        g2.fill(area2); 
        /*===================================*/
    }
     public void change_String(String nuova)
    {
        S=nuova;
        repaint();
    }
     public void ripristina_String()
    {
        S="Status bar";
         repaint();
    }
    /*I metodi che debbo obbligatoriamente implementare per rispettare l' interfaccia MouseListener*/
    public void mouseClicked(MouseEvent e){}
    public void mouseEntered(MouseEvent e)
    {   
        Object source=e.getSource();
        if (source== bottone_help) 
        {   
            this.change_String("Attiva la modalita' aiuto,"+
            " cliccando col tasto destro sulle voci del Menù si avviera' una finestra");
        }
    }
    public void mouseExited(MouseEvent e)
    {
              this.ripristina_String();
    }
    public void mouseReleased(MouseEvent e) 
    {
    }
    public void mousePressed(MouseEvent e) 
    { Object source=e.getSource();
        if (source== bottone_help) 
        {   
            HELP=!HELP;
            cambia_colore(HELP);
        }else
        if(source==area2)
        {
        }
    }  
    /*il metodo che mi dice se la modalita' help e' attivata */
    public boolean getHelp()
    {
        return HELP;
    }
    public void setHelp(boolean b)
    {
        HELP=b;
    }
    public void cambia_colore(boolean c)
    {
        if(c==true)
        {
            COLORE=Color.GREEN;
           set_stato("acceso");
        }
        else
        {
            if(c==false)
            {
                COLORE=Color.RED;
                set_stato("spento");
            }
        }
        repaint();
    }
    public void set_stato(String stato_help)
    {
        stato=stato_help;
    }
}
