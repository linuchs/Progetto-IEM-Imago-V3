import java.awt.*;
import javax.swing.*;
import java.awt.image.*;
import java.awt.color.*;

class istogramma
{
    public double [] R = new double [256];
    public double [] G = new double [256];
    public double [] B = new double [256];

    public istogramma(BufferedImage I)
    {
        int [] rosso = I.getRaster().getSamples(0,0,I.getWidth(),I.getHeight(),0,(int [])(null));
        int [] verde = I.getRaster().getSamples(0,0,I.getWidth(),I.getHeight(),1,(int [])(null));
        int [] blu = I.getRaster().getSamples(0,0,I.getWidth(),I.getHeight(),2,(int [])(null));
        // calcolo occorrenze
        for (int i=0; i<rosso.length; i++)
        {
            R[rosso[i]]+=1;
            G[verde[i]]+=1;
            B[blu[i]]+=1;
        }
        // dalle occorrenze alle frequenze
        for (int i=0; i<R.length; i++)
        {
            R[i]=R[i]/rosso.length;
            G[i]=G[i]/verde.length;
            B[i]=B[i]/blu.length;
        }
    }

    public double [] cumulativoR()
    {
        double [] cumulativo = new double[256];
        cumulativo[0]=R[0];
        for(int i=1; i<cumulativo.length; i++)
        {
            cumulativo[i]=cumulativo[i-1]+R[i];
        }
        return cumulativo;
    }

    public double [] cumulativoG()
    {
        double [] cumulativo = new double[256];
        cumulativo[0]=G[0];
        for(int i=1; i<cumulativo.length; i++)
        {
            cumulativo[i]=cumulativo[i-1]+G[i];
        }
        return cumulativo;
    }

    public double [] cumulativoB()
    {
        double [] cumulativo = new double[256];
        cumulativo[0]=B[0];
        for(int i=1; i<cumulativo.length; i++) 
        {
            cumulativo[i]=cumulativo[i-1]+B[i];
        }
        return cumulativo;
    }

}
