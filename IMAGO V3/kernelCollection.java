// classe che raccoglie alcuni kernel importanti
// restituendoli su richiesta10

import java.awt.image.*;
import javax.swing.JOptionPane;


class kernelCollection
{
  /* un kernel si crea passando le dimensioni e i valori del
    kernel stesso in un array di float unidimenisionale */

  //kernel di smothing con raggio R
  //cio� di dimensioni 2R+1
  public static Kernel smoothing(int raggio)
      {
        int N=(2*raggio+1)*(2*raggio+1);
        float [] coefficienti =new float [N];
        for(int i=0; i<N; i++) coefficienti[i]=1F/N;
        Kernel K=new Kernel(2*raggio+1,2*raggio+1,coefficienti);
        return K;
      }

  public static Kernel smoothingSuRettaOrizzontale(int lunghezza)
      {
        float [] coefficienti =new  float [lunghezza];
        for(int i=0; i<lunghezza; i++) coefficienti[i]=1F/lunghezza;
        Kernel K=new Kernel(lunghezza,1,coefficienti);
        return K;
      }

  public static  Kernel smoothingSuRettaVerticale(int lunghezza)
      {
        float [] coefficienti =new float [lunghezza];
        for(int i=0; i<lunghezza; i++) coefficienti[i]=1F/lunghezza;
        Kernel K=new Kernel(1,lunghezza,coefficienti);
        return K;
      }

  public static Kernel latoH()
      {
        float [] c =new float [9];
        //===========================
        c[0]=-1;  c[1]=-1;  c[2]=-1;
        c[3]=0;   c[4]=0;   c[5]=0;
        c[6]=1;   c[7]=1;   c[8]=1;
        //===========================
        Kernel K=new Kernel(3,3,c);
        return K;
      }

   public static Kernel latoV()
      {
        float [] c =new float [9];
        //===========================
        c[0]=1; c[1]=0; c[2]=-1;
        c[3]=1; c[4]=0; c[5]=-1;
        c[6]=1; c[7]=0; c[8]=-1;
        //===========================
        Kernel K=new Kernel(3,3,c);
        return K;
      }
      
   
    public static Kernel otherFilter(int k)  
    {
        k=2*k+1;//Lato del filtro
        System.out.println("_____________"+k);
        float[] filtro = new float[k*k];
        filtro[0]=0.25F; // Angolo in alto a sinistra
        filtro[k-1]=0.25F;//Angolo in alto a destra
        filtro[k*(k-1)]=0.25F;//Angolo in basso a sinistra.
        filtro[(k*k)-1]=0.25F; //Angolo in basso a destra
        Kernel K=new Kernel(k,k,filtro);
        return K;
    }

    /*filtro degli estremi ai lati del pixel centrale*/
    public static Kernel nuovoFiltro(int k) 
    {
        k=2*k+1;//Lato del filtro
        System.out.println("_____________"+k);
        float[] filtro = new float[k*k];
        filtro[(k-1)/2]=0.25F;// alto 
        filtro[k*((k-1)/2)]=0.25F;// sinistra. 
        filtro[(k*((k+1)/2))-1]=0.25F; //  destra
        filtro[(k*k)-((k+1)/2)]=0.25F; // basso
        Kernel K=new Kernel(k,k,filtro);
        return K;
    }
    
public static Kernel sobel() 
    {
	      float [] c =new float [9];
	      //===========================
	      c[0]=-1; c[1]=-2; c[2]=-1;
	      c[3]=0; c[4]=0; c[5]=0;
	      c[6]=-1; c[7]=-2; c[8]=-1;
	      //===========================	      
	      return new Kernel(3,3,c);
      }
      
      public static Kernel derivata() {
	      float [] c =new float [9];
	      //===========================
	      c[0]=0; c[1]=0; c[2]=0;
	      c[3]=1; c[4]=0; c[5]=-1;
	      c[6]=0; c[7]=0; c[8]=0;
	      //===========================	      
	      return new Kernel(3,3,c);
      }
      
      public static Kernel laplace() {
	      float [] c =new float [9];
	      //===========================
	      c[0]=0; c[1]=-1; c[2]=0;
	      c[3]=-1; c[4]=4; c[5]=-1;
	      c[6]=0; c[7]=-1; c[8]=0;
	      //===========================	      
	      return new Kernel(3,3,c);
      }

      public static Kernel laplaceEnergyPreserving() {
	      float [] c =new float [9];
	      //===========================
	      c[0]=-1; c[1]=-1; c[2]=-1;
	      c[3]=-1; c[4]=8; c[5]=-1;
	      c[6]=-1; c[7]=-1; c[8]=-1;
	      //===========================	      
	      return new Kernel(3,3,c);
      }
}