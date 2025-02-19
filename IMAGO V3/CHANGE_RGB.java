import javax.swing.*;
import java.awt.image.*;
import java.awt.geom.*;
import java.awt.RenderingHints;


/*esempio di BufferedImageOp dobbiamo implementare alcuni metodi.
*/
class CHANGE_RGB implements BufferedImageOp //,Throwable
    {
        int R,G,B;
        public BufferedImage filter(BufferedImage src, BufferedImage dest)
        {
            try
            {
                do
                {
                     R= Integer.parseInt(JOptionPane.showInputDialog("scegli il colore da cambiare col rosso:\n0 per Rosso\n"+
                    "1 per verde\n2 per blu"));
                }
                while ((R<0)||(R>2));
                do
                {
                      G= Integer.parseInt(JOptionPane.showInputDialog("scegli il colore da cambiare col verde:\n0 per Rosso\n"+
                    "1 per verde\n2 per blu"));
                }
                while ((G<0)||(G>2));
                do
                { 
                     B= Integer.parseInt(JOptionPane.showInputDialog("scegli il colore da cambiare col blu:\n0 per Rosso\n"+
                    "1 per verde\n2 per blu"));
                }
                while ((B<0)||(B>2));
                    
                 //if(R>2||G>2||B>2) throws new Exception("ERRORE");
                if (dest==null)
                createCompatibleDestImage(src,null);
                WritableRaster raster=dest.getRaster();
                int [] rosso = src.getRaster().getSamples(0,0,src.getWidth(),src.getHeight(),0,(int [])(null));
                int [] verde = src.getRaster().getSamples(0,0,src.getWidth(),src.getHeight(),1,(int [])(null));
                int [] blu = src.getRaster().getSamples(0,0,src.getWidth(),src.getHeight(),2,(int [])(null));
                int [] alfa = src.getRaster().getSamples(0,0,src.getWidth(),src.getHeight(),3,(int [])(null));
                raster.setSamples(0,0,src.getWidth(),src.getHeight(),R,rosso);
                raster.setSamples(0,0,src.getWidth(),src.getHeight(),G,verde);
                raster.setSamples(0,0,src.getWidth(),src.getHeight(),B,blu);
                raster.setSamples(0,0,src.getWidth(),src.getHeight(),3,alfa); /*canale alfa*/
            }
            catch(Exception e)
            {
            System.out.println("ERRORE DI BATTITURA");
            JOptionPane.showMessageDialog(null,"HAI DIGITATO UN CARATTERE ERRATO OPPURE NON COMPRESO NEL RANGE {0,1,2}","ATTENZIONE",JOptionPane.ERROR_MESSAGE);

                }
              return dest;
             
            }

        public BufferedImage createCompatibleDestImage(BufferedImage src, ColorModel destModel)
            {
              if (destModel==null) destModel=src.getColorModel();
              BufferedImage image= new BufferedImage( destModel,
                                                      destModel.createCompatibleWritableRaster(src.getWidth(),
                                                                                               src.getHeight()),
                                                      destModel.isAlphaPremultiplied(),
                                                      null);
              return image;
            }

        public Rectangle2D getBounds2D(BufferedImage src)
            {
              return src.getRaster().getBounds();
            }

        public RenderingHints getRenderingHints(){return null;}

        public Point2D getPoint2D(Point2D srcPoint, Point2D destPoint)
            {



                if (destPoint==null)
                destPoint =new Point2D.Float();
             destPoint.setLocation(srcPoint.getX(), srcPoint.getY());
             return destPoint;
            }
    }
    