import javax.swing.*;
import java.awt.*;
import java.awt.event.*;//per WindowAdapter()

/*Rappresenta il frame che si apre quando viene attivata la modalita' help dal Jpulsante della status-bar*/

class frame_help extends JFrame
{
    private JTextArea testo;
     // JPanel pannello_testo=new JPanel();
    JScrollPane scroll=new JScrollPane();
    
    public frame_help()
    { Toolkit kit=Toolkit.getDefaultToolkit();
        Dimension DIM=kit.getScreenSize();
        setSize(DIM.width/2,DIM.height/2);
         
        setBackground(Color.LIGHT_GRAY);
    
        testo=new JTextArea();
         Font f=new Font("ARIAL",Font.BOLD,14);
        testo.setFont(f);
        testo.setLineWrap(true);
        testo.setWrapStyleWord(true);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE );
        JScrollPane scroll=new JScrollPane(testo,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        testo.setEditable(false);
        Container contentPane=getContentPane();
        contentPane.add(scroll,BorderLayout.CENTER);
        
        addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                 //----   System.out.println("la finestra si sta chiudendo"); 
                    // nelle applicazioni reali si deve togliere questa traccia
                    //System.exit(0);
                    /*String S=JOptionPane.showInputDialog("LA FINESTRA SI STA PER CHIUDERE");
                        
                    int INPUT=Integer.parseInt(S);
                    if(INPUT==0)
                    {
                        System.exit(0);
                    }*/
                Object[] options = { "CHIUDI", "NON CHIUDERE" };
               
                int i=JOptionPane.showOptionDialog(null, "FAI LA TUA SCELTA", "ATTENZIONE", 
                JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
                null, options, options[0]);	
                System.out.println("-----------"+i); 
                 if(i==0)
                {
                    setDefaultCloseOperation(DISPOSE_ON_CLOSE); 
                } 
            }
        }); 
    }
    
    public void setTesto(String text_help)
    {
        testo.setText(text_help);
    }
}
  