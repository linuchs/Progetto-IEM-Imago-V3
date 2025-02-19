import javax.swing.*;
import java.awt.image.*;
import java.awt.geom.*;
import java.awt.RenderingHints;



/*esempio  BufferedImageOp deve implementare alcuni metodi.

cambia il rosso col blu
*/

class CHANGE_RB implements BufferedImageOp
    {
        public BufferedImage filter(BufferedImage src, BufferedImage dest)
            {
              if (dest==null)
                  createCompatibleDestImage(src,null);
              WritableRaster raster=dest.getRaster();
              int [] rosso = src.getRaster().getSamples(0,0,src.getWidth(),src.getHeight(),0,(int [])(null));
              int [] verde = src.getRaster().getSamples(0,0,src.getWidth(),src.getHeight(),1,(int [])(null));
              int [] blu = src.getRaster().getSamples(0,0,src.getWidth(),src.getHeight(),2,(int [])(null));
              int [] alfa = src.getRaster().getSamples(0,0,src.getWidth(),src.getHeight(),3,(int [])(null));
              raster.setSamples(0,0,src.getWidth(),src.getHeight(),0,blu);
              raster.setSamples(0,0,src.getWidth(),src.getHeight(),1,verde);
              raster.setSamples(0,0,src.getWidth(),src.getHeight(),2,rosso);
              raster.setSamples(0,0,src.getWidth(),src.getHeight(),3,alfa);
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