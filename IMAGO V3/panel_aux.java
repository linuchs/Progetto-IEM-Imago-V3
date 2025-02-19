
import javax.swing.*;
     import java.awt.*; 
  class panel_aux extends JPanel//IL PANNELLO ausiliario per il patterning
    {
        private Image img;
        Dimension DIM;
        public panel_aux()
        {
            
           setBackground(Color.YELLOW);
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
    }
  