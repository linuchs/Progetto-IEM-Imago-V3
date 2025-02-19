
/*
esempio di filtraggio non lineare
prendo il livello di colore e ne calcolo il logaritmo
deve implementare alcuni metodi...
*/

import java.awt.*;
import javax.swing.*;
import java.awt.image.*;
import java.awt.geom.*;

class mediano3 implements BufferedImageOp
    {
        // cuore del filtro ; qui si deve dire cosa fare
        public BufferedImage filter(BufferedImage src, BufferedImage dest)
            {
              if (dest==null)
                  dest=createCompatibleDestImage(src,null);
              WritableRaster raster=dest.getRaster();
              Raster sRaster=src.getRaster();
              int [][] valori = new int[3][9];
              int cont;
              for(int i=1;i<src.getWidth()-1;i++)
                  for(int j=1;j<src.getHeight()-1; j++)
                    for(int k=0; k<3; k++)
                        {
                         cont=0;
                         for(int h=-1;h<2;h++)
                          for(int m=-1;m<2;m++)
                            valori[k][cont++]=sRaster.getSample(i+h,j+m,k);
                         bubble(valori[k]);
                        // raster.setSample(i,j,k,valori[k][(int)(Math.random()*9)]); casuale
                        raster.setSample(i,j,k,valori[k][4]); // mediano
                          // raster.setSample(i,j,k,valori[k][0]); // minimo ... dispendioso
                        // raster.setSample(i,j,k,valori[k][4]); // massimo ... dispendioso
                        }
              return dest;
            }

         // banale bubblesort
         private void bubble(int[] A)
             {  int temp;
                for(int i=0; i<A.length;i++)
                  for(int j=0; j<A.length-i-1; j++)
                    {
                      if (A[j]>A[j+1]) {temp=A[j]; A[j]=A[j+1]; A[j+1]=A[j];}
                    }
                // tracing
               // for(int i=0; i<A.length; i++)  System.out.print("--"+i+"-"+A[i]);
                // System.out.println();
              }

        // serve a definire il Color model per la immagine che deve essere ottenuta
        // dalla operazione di filtraggio
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

        // serve ad indicare le dimensioni della immagine
        // che si ottiene dalla immagine iniziale
        // nel nostro caso le stesse di partenza
        public Rectangle2D getBounds2D(BufferedImage src)
            {
              return src.getRaster().getBounds();
            }

        // raramente c'è bisogno di rendering hints differenti dal null
        public RenderingHints getRenderingHints()
            {return null;}

        // il seguente metodo dice dove deve andare a finire ogni PIXEL ogni volta
        // che viene progettato
        // nel nostro caso il punto di destinaziuone è lo stesso
        // del punto di partenza
        public Point2D getPoint2D(Point2D srcPoint, Point2D destPoint)
            {
             if (destPoint==null)
             destPoint =new Point2D.Float();
             destPoint.setLocation(srcPoint.getX(), srcPoint.getY());
             return destPoint;
            }
    }

