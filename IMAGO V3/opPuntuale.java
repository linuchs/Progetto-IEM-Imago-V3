// questa classe (data less)
// coordina tutte le operazioni di LUT dell'applicativo

import java.awt.*;
import javax.swing.*;
import java.awt.image.*;
import java.awt.color.*;
import javax.swing.*;

class opPuntuale
{
        
    public BufferedImage invertiRed(BufferedImage I)
    {
            /* creo la tabella LUT necessaria, PROBLEMA: la corrispondenza indici colori*/
            /*IMPORTANTE RICORDARSI:  
                il rosso è 0,
                il verde è 1,
                il blu è 2,
                il canale alpha che ci dà la trasparenza è 3. */
         
        byte [][] tabella = new byte [4][256];
        
        for(int i=0; i<256; i++)
        {
            tabella[0][i]=(byte)(255-i);
            tabella[1][i]=(byte)i;
            tabella[2][i]=(byte)i;
            tabella[3][i]=(byte)i;
        }
              // dalla tabello ottengo la LUT corrispondente
        ByteLookupTable inversione=new ByteLookupTable(0,tabella);

              // costruisco l'operatore che mi serve
        LookupOp iR=new LookupOp(inversione,null);

              // lo applico
        BufferedImage temp=iR.filter(I, null);
        return temp;

    }

    public BufferedImage invertiGreen(BufferedImage I)
    {
            // creo la tabella LUT necessaria
                /*IMPORTANTE RICORDARSI:  
                    il rosso è 0,
                    il verde è 1,
                    il blu è 2,
                    il canale alpha che ci dà la trasparenza è 3. */
         
            byte [][] tabella = new byte [4][256];
            for(int i=0; i<256; i++)
            {
                tabella[0][i]=(byte)i;
                tabella[1][i]=(byte)(255-i);
                tabella[2][i]=(byte)i;
                tabella[3][i]=(byte)i;
            }
              // dalla tabello ottengo la LUT corrispondente
            ByteLookupTable inversione=new ByteLookupTable(0,tabella);

              // costruisco l'operatore che mi serve
            LookupOp iR=new LookupOp(inversione,null);

              // lo applico
            BufferedImage temp=iR.filter(I, null);
            return temp;
    }

     public BufferedImage invertiBlue(BufferedImage I)
     {
            // creo la tabella LUT necessaria
            /*IMPORTANTE RICORDARSI:  
                il rosso è 0,
                il verde è 1,
                il blu è 2,
                il canale alpha che ci dà la trasparenza è 3. */

            byte [][] tabella = new byte [4][256];
         
            for(int i=0; i<256; i++)
            {
                tabella[0][i]=(byte)i;
                tabella[1][i]=(byte)i;
                tabella[2][i]=(byte)(255-i);
                tabella[3][i]=(byte)i;
            }
            // dalla tabello ottengo la LUT corrispondente
            ByteLookupTable inversione=new ByteLookupTable(0,tabella);

            // costruisco l'operatore che mi serve
            LookupOp iR=new LookupOp(inversione,null);

            // lo applico
            BufferedImage temp=iR.filter(I, null);
            return temp;
    }

	public BufferedImage invertiAll(BufferedImage I)
    {
		
        // creo la tabella LUT necessaria
		/*IMPORTANTE RICORDARSI:  
                il rosso è 0,
                il verde è 1,
                il blu è 2,
                il canale alpha che ci dà la trasparenza è 3. */
		
		byte [][] tabella = new byte [4][256];
		for(int i=0; i<256; i++) {
			tabella[0][i]=(byte)(255-i);
			tabella[1][i]=(byte)(255-i);
			tabella[2][i]=(byte)(255-i);
			tabella[3][i]=(byte)(i);
		}
		// dalla tabello ottengo la LUT corrispondente
		ByteLookupTable inversione=new ByteLookupTable(0,tabella);
		
		// costruisco l'operatore che mi serve
		LookupOp iR=new LookupOp(inversione,null);
		
		// lo applico
		BufferedImage temp=iR.filter(I, null);
		return temp;
	}
    
/*------------------------------------------------------------------------------------------------------------------
    */
    public BufferedImage sogliaAllChannel(BufferedImage I, int k)
    {
        /*creo la tabella LUT necessaria
         PROBLEMA: la corrispondenza indici colori ï¿½
        un po' "da scoprire". inverto tutti e tre i canali tranne alfa
        l'inversione di alfa mi ï¿½ al momento incomprensibile...*/
        byte [][] tabella = new byte [4][256];
        
        for(int i=0; i<k; i++)
        {
            tabella[0][i]=0;
            tabella[1][i]=0;
            tabella[2][i]=0;
            tabella[3][i]=(byte)255;
        }
        for(int i=k; i<256; i++)
        {
            tabella[0][i]=(byte)255;
            tabella[1][i]=(byte)255;
            tabella[2][i]=(byte)255;
            tabella[3][i]=(byte)255;
        }
        // dalla tabello ottengo la LUT corrispondente
        ByteLookupTable soglia=new ByteLookupTable(0,tabella);

        // costruisco l'operatore che mi serve
        LookupOp iR=new LookupOp(soglia,null);

        // lo applico
        BufferedImage temp=iR.filter(I, null);
        return temp;

    }

    public BufferedImage sogliaChannels(BufferedImage I, int r, int g, int b)
    {
        // creo la tabella LUT necessaria

        byte [][] tabella = new byte [4][256];

        // sistemo il canale alfa
        for(int i=0; i<256; i++) tabella[3][i]=(byte)255;

        // sistemo canale red
        for(int i=0; i<r; i++) tabella[1][i]=(byte)0;
        for(int i=r; i<256; i++) tabella[1][i]=(byte)255;

        // sistemo canale green
        for(int i=0; i<g; i++) tabella[0][i]=(byte)0;
        for(int i=g; i<256; i++) tabella[0][i]=(byte)255;

        // sistemo canale blue
        for(int i=0; i<b; i++) tabella[2][i]=(byte)0;
        for(int i=b; i<256; i++) tabella[2][i]=(byte)255;

        // dalla tabello ottengo la LUT corrispondente
        ByteLookupTable soglia=new ByteLookupTable(0,tabella);

        // costruisco l'operatore che mi serve
        LookupOp iR=new LookupOp(soglia,null);

        // lo applico
        BufferedImage temp=iR.filter(I, null);
        return temp;
    }  

    public BufferedImage incupire(BufferedImage I)
    {
        // creo la tabella LUT necessaria

        byte [][] tabella = new byte [4][256];

        // sistemo il canale alfa
        for(int i=0; i<256; i++) tabella[3][i]=(byte)255;

        // sugli altri tre canali debbo applicare una LUT
        // di tipo "concavo"
        // usiamo una funzione quadratica (tanto per)

        double [] valoriLUT = new double [256];
        for(int i=0; i<256; i++) valoriLUT[i]=(i*i/256); // perchï¿½ funzia? riflettere

        //for(int i=0; i<256; i++)  System.out.println(valoriLUT[i]);

        for(int i=0; i<256; i++) tabella[1][i]=(byte)valoriLUT[i];
        for(int i=0; i<256; i++) tabella[0][i]=(byte)valoriLUT[i];
        for(int i=0; i<256; i++) tabella[2][i]=(byte)valoriLUT[i];
        // for(int i=0; i<256; i++)  System.out.println(tabella[1][i]);

        // dalla tabello ottengo la LUT corrispondente
        ByteLookupTable soglia=new ByteLookupTable(0,tabella);

        // costruisco l'operatore che mi serve
        LookupOp iR=new LookupOp(soglia,null);

        // lo applico
        BufferedImage temp=iR.filter(I, null);
        return temp;
    }

    public BufferedImage schiarire(BufferedImage I)
    {
        // creo la tabella LUT necessaria
        /*IMPORTANTE RICORDARSI:  
                il rosso è 0,
                il verde è 1,
                il blu è 2,
                il canale alpha che ci dà la trasparenza è 3. */
        byte [][] tabella = new byte [4][256];

        // sistemo il canale alfa
        for(int i=0; i<256; i++) tabella[3][i]=(byte)255;

        // sugli altri tre canali debbo applicare una LUT
        // di tipo "concavo"
        // usiamo una funzione quadratica (tanto per)

        double [] valoriLUT = new double [256];
        for(int i=0; i<256; i++) valoriLUT[i]=2*i-(i*i/200); // perchï¿½ funzia? riflettere
        //for(int i=0; i<256; i++)  System.out.println(valoriLUT[i]);
        for(int i=0; i<256; i++) tabella[1][i]=(byte)valoriLUT[i];
        for(int i=0; i<256; i++) tabella[0][i]=(byte)valoriLUT[i];
        for(int i=0; i<256; i++) tabella[2][i]=(byte)valoriLUT[i];

        // dalla tabello ottengo la LUT corrispondente
        ByteLookupTable soglia=new ByteLookupTable(0,tabella);

        // costruisco l'operatore che mi serve
        LookupOp iR=new LookupOp(soglia,null);

        // lo applico
        BufferedImage temp=iR.filter(I, null);
        return temp;
    }
/*-------------------------------------------------------------------------------------------------------------------
    IMPLEMENTAZIONE DELLA EQUALIZZAZIONE */
    
    public BufferedImage equalizza(BufferedImage I)
    {
     
        /*l'algoritmo spiegato a lezione ci dice che
          la lUT da usare ï¿½ proprio l'istogramma cumulativo...
            se lo calcolo usando la classe istogramma ottengo numeri tra 0 e 1
            mentre mi servopno byte tra 0 e 255... facile provi rimedio...*/

        istogramma isto= new istogramma(I);

        // creo la tabella LUT necessaria

        byte [][] tabella = new byte [4][256];

        // sistemo il canale alfa
        for(int i=0; i<256; i++) tabella[3][i]=(byte)255;

        // altri canali
        for(int i=0; i<256; i++){tabella[1][i]=(byte)(isto.cumulativoR()[i]*256);}
        for(int i=0; i<256; i++){tabella[0][i]=(byte)(isto.cumulativoG()[i]*256);}
        for(int i=0; i<256; i++){tabella[2][i]=(byte)(isto.cumulativoB()[i]*256);}

        // dalla tabello ottengo la LUT corrispondente
        ByteLookupTable soglia=new ByteLookupTable(0,tabella);

        // costruisco l'operatore che mi serve
        LookupOp iR=new LookupOp(soglia,null);

        // lo applico
        BufferedImage temp=iR.filter(I, null);
        return temp;
    }
    
/*-------------------------------------------------------------------------------------------------------------------
    */
    public BufferedImage riquantizzaLog(BufferedImage I)
    {
        // creo la tabella LUT necessaria
        double fattore = Double.parseDouble(JOptionPane.showInputDialog("base del log?"));
        byte [][] tabella = new byte [4][256];

        // sistemo il canale alfa
        for(int i=0; i<256; i++) tabella[3][i]=(byte)255;

        // mi calcolo la LUT di riquantizzazione
        // riflettere sulla formula!!!!
        byte [] rL = new byte [256];

        for(int i=0; i<rL.length; i++)
        {
            rL[i]=(byte)(Math.pow(fattore,(int)(Math.log(i)/Math.log(fattore))));
        }
            //for(int i=0; i<rL.length; i++) System.out.println(rL[i]);

        for(int i=0; i<256; i++) tabella[1][i]=rL[i];
        for(int i=0; i<256; i++) tabella[0][i]=rL[i];
        for(int i=0; i<256; i++) tabella[2][i]=rL[i];

        // dalla tabello ottengo la LUT corrispondente
        ByteLookupTable soglia=new ByteLookupTable(0,tabella);

        // costruisco l'operatore che mi serve
        LookupOp iR=new LookupOp(soglia,null);

        // lo applico
        BufferedImage temp=iR.filter(I, null);
        return temp;
    }

    BufferedImage riquantizzaLin(BufferedImage I,int livelli)
    {
        // creo la tabella LUT necessaria

        byte [][] tabella = new byte [4][256];

        // sistemo il canale alfa
        for(int i=0; i<256; i++) tabella[3][i]=(byte)255;

        // mi calcolo la LUT di riquantizzazione
        // riflettere sulla formula!!!!
        byte [] rL = new byte [256];
        int rangeLiv = 256/livelli;
        for(int i=0; i<rL.length; i++)
        {
            rL[i]=(byte)(i-i%rangeLiv);
        }
        //for(int i=0; i<rL.length; i++) System.out.println(rL[i]);

        for(int i=0; i<256; i++) tabella[1][i]=rL[i];
        for(int i=0; i<256; i++) tabella[0][i]=rL[i];
        for(int i=0; i<256; i++) tabella[2][i]=rL[i];

        // dalla tabello ottengo la LUT corrispondente
        ByteLookupTable soglia=new ByteLookupTable(0,tabella);

        // costruisco l'operatore che mi serve
        LookupOp iR=new LookupOp(soglia,null);

        // lo applico
        BufferedImage temp=iR.filter(I, null);
        return temp;
    }
    
/*-------------------------------------------------------------------------------------------------------------------
    IMPLEMENTAZIONE DI UN ALUT RANDOM I VALORI ASSEGNATI AI COLORI SONO
    CALCOLATI DA UN FUNZIONE RANDOM*/
    
    public  BufferedImage randomLUT(BufferedImage I)
    {
        // creo la tabella LUT necessaria

        byte [][] tabella = new byte [4][256];

        // sistemo il canale alfa
        for(int i=0; i<256; i++) tabella[3][i]=(byte)255;

        for(int i=0; i<256; i++) tabella[1][i]=(byte)(256*Math.random());
        for(int i=0; i<256; i++) tabella[0][i]=(byte)(256*Math.random());
        for(int i=0; i<256; i++) tabella[2][i]=(byte)(256*Math.random());

        // dalla tabello ottengo la LUT corrispondente
        ByteLookupTable soglia=new ByteLookupTable(0,tabella);

        // costruisco l'operatore che mi serve
        LookupOp iR=new LookupOp(soglia,null);

        // lo applico
        BufferedImage temp=iR.filter(I, null);
        return temp;
    }
    
/*----------------------------------------------------------------------------------------------------------------
    LA LUT A GRADONI DEL COMPITO DEL PRIMO FEBBRAIO 
    MODIFICATA AGGIUSTANDO I CANALI*/
    
    private static final int ALPHA=3;
    private static final int BLUE=2;
    private static final int GREEN=1;
    private static final int RED=0;
            
    public BufferedImage otherLUT(BufferedImage I)
    {
        /*per prima cosa creo la tabella*/
        byte [][] tabella = new byte [4][256];
        for(int i=0; i<256; i++) 
        {	
            tabella[ALPHA][i]=(byte)(255);	
            tabella[RED][i]=(byte)(i);	
            tabella[GREEN][i]=(byte)(i);	
            tabella[BLUE][i]=(byte)(i);	
        }
        /*prendo il parametro che mi serve*/
        int k=0;int z=0;
        try
        {
            z=Integer.parseInt(JOptionPane.showInputDialog("Numero di gradoni?"));
            /*METTO L'INCREMENTO DI K PERCHE' SE METTESSI ZERO NON FUNZIONEREBBE*/
            // k++
        }
        catch(Exception e)
        {
         
            JOptionPane.showMessageDialog(null,"HAI DIGITATO ZERO","ATTENZIONE",JOptionPane.ERROR_MESSAGE);

        }
        
        /*NON POSSO ELIMINARE QUESTA CONDIZIONE*/
        if (z*3>=255) return I;
        {//k=z*3;
            //Canale ROSSO k gradoni
            int gradoni= (int)((float)255F/k);
            byte val=-128; //Idioti progettisti di java con il tipo byte diverso :-@
            byte incremento=(byte)(255F/k);
            for(int i=0; i<256; i++)
            {				
                tabella[RED][i]=val;
                if (i % gradoni==0 && i!=0)
                    {
                        val+=incremento;//=(byte)(i-128);
                        System.out.println("Posizione: "+i+" incremento="+incremento);
                        System.out.println("Ora val e' uguale a: "+val);
                    }
            }
        }
        /*POSSO ELIMINARE QUESTA CONDIZIONE LA LASCIO PER COMPLETEZZA*/
        if (z*2>=255) return I;
        {    
            //Canale VERDE k gradoni
            k=z*2;
            int gradoni= (int)((float)255F/k);
            byte val=-128; //Idioti progettisti di java con il tipo byte diverso :-@
            byte incremento=(byte)(255F/k);
            for(int i=0; i<256; i++) 
            {				
                tabella[GREEN][i]=val;
                if (i % gradoni==0 && i!=0)
                {
                    val+=incremento;
                    System.out.println("Posizione: "+i+" incremento="+incremento);
                    System.out.println("Ora val Ã¨ uguale a: "+val);
                }
            }
        }
        /*POSSO ELIMINARE QUESTA CONDIZIONE LA LASCIO PER COMPLETEZZA*/
        if (z+1>=255) return I;      
        {
            //Canale BLU k gradoni
           k=z*3;
           // k=z+1;
            int gradoni= (int)((float)255F/k);
            byte val=-128; //Idioti progettisti di java con il tipo byte diverso :-@
            byte incremento=(byte)(255F/k);
            for(int i=0; i<256; i++) 
            {				
                tabella[BLUE][i]=val;
                if (i % gradoni==0 && i!=0) 
                {
                    val+=incremento;
                    System.out.println("Posizione: "+i+" incremento="+incremento);
                    System.out.println("Ora val Ã¨ uguale a: "+val);
                }
            }
        }
         
        // creo la tabella LUT necessaria
        // PROBLEMA: la corrispondenza indici colori 
      
    // dalla tabello ottengo la LUT corrispondente
        ByteLookupTable inversione=new ByteLookupTable(0,tabella);
                
        // costruisco l'operatore che mi serve
        LookupOp iR=new LookupOp(inversione,null);
                
        // lo applico
        BufferedImage temp=iR.filter(I, null);
        return temp;
    }
    
/*----------------------------------------------------------------------------------------------------------------
    */
}
	