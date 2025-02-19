
import java.awt.*;
import javax.swing.*;
import java.awt.image.*;
import java.awt.color.*;
import java.awt.geom.*;

public class OperazRaster implements BufferedImageOp
{
	
	private static final int ALPHA=3;
	private static final int BLUE=2;
	private static final int GREEN=1;
	private static final int RED=0;
    int k=0;
	public BufferedImage filter(BufferedImage src, BufferedImage dest) 
    {
        try
        {  
           
            dest=null;//Se tolgo sto null lavoro sull'originale...
            do
                { 
                    k=Integer.parseInt(JOptionPane.showInputDialog("Distanza tra le colonne"));
                }
                while ((k<=0));
           
            
            if (dest==null) dest=createCompatibleDestImage(src,null);
            WritableRaster out=dest.getRaster();
            Raster in=src.getRaster();
            
            
            boolean isSpecial =true;//è una colonna da preservare?
            int prev=0;//Colonna precedente
            int next=k;//Colonna successiva
            
            for (int c=0;c<src.getWidth();c++) 
            {
                
                if ((c%k)==0)
                {
                    prev=c;
                    next=next+k;
                    if (next>=src.getWidth()) next=src.getWidth()-1;
                    isSpecial=true;
                } 
                else 
                {
                    isSpecial=false;
                }
                for (int r=0;r<src.getHeight();r++)
                {
                    if (!isSpecial) 
                    {
                        float[] val = in.getPixel(c,r,(float[])null);
                        
                        int distDalPrec = c-prev;
                        int distDalSucc = next-c;
                        
                        float[] valPrec = in.getPixel(prev,r,(float[])null);
                        float[] valSucc = in.getPixel(next,r,(float[])null);
                        
                        val[RED]= ((valPrec[RED]*distDalPrec)+(valSucc[RED]*distDalSucc))/(distDalPrec+distDalSucc);
                        val[GREEN]= ((valPrec[GREEN]*distDalPrec)+(valSucc[GREEN]*distDalSucc))/(distDalPrec+distDalSucc);
                        val[BLUE]= ((valPrec[BLUE]*distDalPrec)+(valSucc[BLUE]*distDalSucc))/(distDalPrec+distDalSucc);
                        
                        out.setPixel(c,r,val);
                    }
                }
            }
            return dest;
        }
        catch(Exception e)
        {
            System.out.println("ERRORE DI BATTITURA");
            JOptionPane.showMessageDialog(null,"HAI DIGITATO UN CARATTERE ERRATO ","ATTENZIONE",JOptionPane.ERROR_MESSAGE);

        }
        return null;
	}

	public BufferedImage createCompatibleDestImage(BufferedImage src, ColorModel destModel) {
		if (destModel==null) destModel=src.getColorModel();
		BufferedImage image= new BufferedImage( destModel,
		destModel.createCompatibleWritableRaster(src.getWidth(), src.getHeight()),
		destModel.isAlphaPremultiplied(), null);
		return image;
	}
	
	public Rectangle2D getBounds2D(BufferedImage src) {
		return src.getRaster().getBounds();
	}

	public RenderingHints getRenderingHints() {
		return null;
	}

	public Point2D getPoint2D(Point2D srcPoint, Point2D destPoint) {
		if (destPoint==null)
			destPoint =new Point2D.Float();
		destPoint.setLocation(srcPoint.getX(), srcPoint.getY());
		return destPoint;
	}

}

