import javax.swing.*;
import java.awt.*;
 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    class pannello_immagine extends JPanel//IL PANNELLO DI CENTRO
    {
        private Image img;
        Dimension DIM;
        
    public pannello_immagine()
    {
        //setBackground(Color.WHITE);
        setBackground(Color.DARK_GRAY);
    }
        
    public void paintComponent(Graphics g)
    {
        DIM=this.getSize();
        super.paintComponent(g); if (img!=null) 
        // g.drawImage(img,(DIM.width-(img.getWidth(this)/2))/2,(DIM.height-(img.getHeight(this)/2))/2,img.getWidth(this)/2,img.getHeight(this)/2,null);
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
        // ciÃ² che esso manda allo schermo
        this.repaint();
    }
}
  //QUI FINISCE IL panel5,  IL MIO PANNELLO CENTRALE