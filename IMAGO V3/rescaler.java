// questa classe (data less)
// coordina tutte le operazioni di rescale dell'applicativo

import java.awt.*;
import javax.swing.*;
import java.awt.image.*;
import java.awt.color.*;

class rescaler
{

    public BufferedImage gain(BufferedImage I)
    {
        try
        {
           // ottengo il parametro di gain
           float gain= Float.parseFloat(JOptionPane.showInputDialog("Dammi il valore del gain tra 0<...>200"));
           // costruisco il vettore dei gains
            float [] gains = new float [4];
           gains[3]=1; // per il canale alfa
           gains[0]=gains[1]=gains[2]=gain;
            // lascio a zero il vettore dei biases
           float [] biases = new float[4];
           RescaleOp oG = new RescaleOp(gains, biases,null);
           BufferedImage temp=oG.filter(I, null);
           return temp;
        }
        catch(Exception e)
        {
            System.out.println("ERRORE DI BATTITURA");
            JOptionPane.showMessageDialog(null,"HAI DIGITATO UN CARATTERE ERRATO ","ATTENZIONE",JOptionPane.ERROR_MESSAGE);

        }  return null;
    }

        /*==============================
        metodo di rescaling dei valori complessivi (tutti i canali)
        controllando solo il bias
        ================================*/

     public BufferedImage bias(BufferedImage I)
    {
        try
        {
           // ottengo il parametro di gain
           float bias= Float.parseFloat(JOptionPane.showInputDialog("Dammi il valore del bias tra:  -200<....>250"));
           // costruisco il vettore dei gains
            float [] biases = new float [4];
           biases[3]=0; // per il canale alfa
           biases[0]=biases[1]=biases[2]=bias;
            // metto a uno i valori dei gains
           float [] gains = new float[4]; gains[0]=gains[1]=gains[2]=gains[3]=1;
           RescaleOp oG = new RescaleOp(gains, biases,null);
           BufferedImage temp=oG.filter(I, null);
           return temp;
        }
            catch(Exception e)
        {
            System.out.println("ERRORE DI BATTITURA");
            JOptionPane.showMessageDialog(null,"HAI DIGITATO UN CARATTERE ERRATO ","ATTENZIONE",JOptionPane.ERROR_MESSAGE);

        }  return null;
    }

}