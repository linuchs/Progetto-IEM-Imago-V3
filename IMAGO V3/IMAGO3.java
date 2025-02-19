

/*===========================*/
/*TUTTE LE LIBRERIE DA IMPORTARE*/
import javax.swing.*;
import java.awt.*;
import java.io.*;
import javax.imageio.ImageIO.*;
import javax.imageio.*;
import java.awt.image.*;
import java.awt.event.*;
import javax.swing.filechooser.FileFilter;
import java.awt.geom.*;
import java.awt.RenderingHints;

/*===========================*/

class IMAGO3
{
    public static void main(String[] args)
    {
        JFrame F=new frame();
        F.setVisible(true);
    }
}

/*IL FRAME PRINCIPALE*/
class frame extends JFrame implements ActionListener, MouseListener//, MouseMotionListener
{ 
    /*MouseListener , MouseMotionListener(ho scoperto che non mi serve) mi servono per la ROI*/
    // int CONTQWE=0;int CONTQWE1=0;
    
    int JCONT=0;
    JMenuBar BARRA_MENU= new JMenuBar();
    JMenu MENU_FILE= new JMenu("File");
    JMenu MENU_IMMAGINE= new JMenu("Immagine");
    JMenu MENU_BINARIE= new JMenu("Operazioni Binarie");
    JMenu MENU_BUFFERED= new JMenu("BufferedImageOp");
    JMenu MENU_RESCALING= new JMenu("Rescaling");
    JMenu MENU_LUT= new JMenu("Operazioni di LUT");
    JMenu MENU_CONVOLVE= new JMenu("Convoluzioni");
    JMenu SCELTA_MENU10= new JMenu("Cattura schermo"); 
    JMenu SCELTA_MENU8= new JMenu("XXX");
    JMenu SCELTA_MENU9= new JMenu("?");
   
    JMenuItem SOTTO_APRI= new JMenuItem("Apri");
    JMenuItem SOTTO_SALVA= new JMenuItem("Salva");
    JMenuItem SOTTO_CHIUDI= new JMenuItem("Chiudi");
    
    JMenuItem SOTTO_NEGATIVO= new JMenuItem("Negativo");
    JMenuItem SOTTO_CANALER= new JMenuItem("Canale rosso");
    JMenuItem SOTTO_CANALEV= new JMenuItem("Canale verde");
    JMenuItem SOTTO_CANALEB= new JMenuItem("Canale blu");
    JMenuItem SOTTO_CANALER_GRAY= new JMenuItem("Canale rosso in B/N");
    JMenuItem SOTTO_CANALEV_GRAY= new JMenuItem("Canale verde in B/N");
    JMenuItem SOTTO_CANALEB_GRAY= new JMenuItem("Canale blu in B/N");
    JMenuItem SOTTO_RITAGLIA= new JMenuItem("Ritaglia una porzione di immagine");
    JMenuItem SOTTO_UNDO= new JMenuItem("UNDO");

    JMenuItem SOTTO_DITHERING= new JMenuItem("Dithering");
    JMenuItem SOTTO_PATTERNING= new JMenuItem("Patterning");
    JMenuItem SOTTO_ERROR_DIFFUSION= new JMenuItem("Error Diffusion");
    JMenuItem SOTTO_SCAMBIA_RGB= new JMenuItem("Scambia colori rgb");
    JMenuItem SOTTO_SCAMBIA_B= new JMenuItem("Scambia rosso con blu");
    JMenuItem SOTTO_MEDIANO= new JMenuItem("Mediano 3x3");
    JMenuItem SOTTO_TRASLAZIONE= new JMenuItem("Traslazione");
    JMenuItem SOTTO_RISCALAMENTO= new JMenuItem("Risclamento a piacere");
    JMenuItem SOTTO_RISCALAMENTO_PIU= new JMenuItem("Ingrandisci al doppio");
    JMenuItem SOTTO_RISCALAMENTO_MENO= new JMenuItem("Diminuisci alla meta");
    JMenuItem SOTTO_ROTAZIONE= new JMenuItem("Rotazione");
    JMenuItem SOTTO_SHEAR= new JMenuItem("Shear");
    JMenuItem SOTTO_GENERICA_RASTER= new JMenuItem("Generica operazione sul Raster");
    
    JMenuItem SOTTO_GAIN= new JMenuItem("Aggiusta il Gain");
    JMenuItem SOTTO_BIAS= new JMenuItem("Correggi il Bias");
    
    
    JMenuItem SOTTO_ISTO_RGB= new JMenuItem("Istogramma RGB");
    JMenuItem SOTTO_ISTO_RGB_CUM= new JMenuItem("Istogramma RGB cumulativo");
    JMenuItem SOTTO_INVERTI_R= new JMenuItem("Inverti rosso");
    JMenuItem SOTTO_INVERTI_V= new JMenuItem("Inverti verde");
    JMenuItem SOTTO_INVERTI_B= new JMenuItem("Inverti  blu");
    JMenuItem SOTTO_INVERTI_ALL= new JMenuItem("Inverti tutti");
    JMenuItem SOTTO_SOGLIA_CHANNEL= new JMenuItem("Soglie sui channel");
    JMenuItem SOTTO_SOGLIA_UNICA= new JMenuItem("Soglia unica");
    JMenuItem SOTTO_INCUPIRE= new JMenuItem("Incupire");
    JMenuItem SOTTO_SCHIARIRE= new JMenuItem("Schiarire");
    JMenuItem SOTTO_EQUALIZZA= new JMenuItem("Equalizzazione");
    JMenuItem SOTTO_LIVELLI_LOG= new JMenuItem("Su livelli log");
    JMenuItem SOTTO_LIVELLI_K= new JMenuItem("Su k livelli lin");
    JMenuItem SOTTO_LUT_RANDOM= new JMenuItem("LUT random");
    JMenuItem SOTTO_LUT_GRADONI= new JMenuItem("LUT a gradoni");
    
    JMenuItem SOTTO_SMOOTH= new JMenuItem("Smoothing");
    JMenuItem SOTTO_SMOOTH_O= new JMenuItem("Smoothing orizzontale");
    JMenuItem SOTTO_SMOOTH_V= new JMenuItem("Smoothing verticale");
    JMenuItem SOTTO_EDGEH= new JMenuItem("Edge Horizontal");
    JMenuItem SOTTO_EDGEV= new JMenuItem("Edge Vertical");
    JMenuItem SOTTO_MEDIA_ANGOLI= new JMenuItem("Media degli angoli estremi");
    JMenuItem SOTTO_MEDIA_VICINI= new JMenuItem("Media pixel estremi ai lati del pixel centrale");
  
    JMenuItem AVVIA_CATTURA= new JMenuItem("Attiva");
    
    private JScrollPane jScrollPane1 = new JScrollPane(); // serve a gestire le immagini più grandi del frame
    
    pannello_statusbar SUD=new pannello_statusbar();
   
    pannello_immagine CENTRO=new pannello_immagine();
    frame_help nuovo;
    ScreenShotFrame capturer; 
    BufferedImage image=null;
    BufferedImage IMMAGINE=null;// immagine per fare l'undo
    private JFileChooser aprifile1=new JFileChooser(); /*finestra di apertura per salvataggio*/
  
       
    private Point puntoInizialeROI = null;
    private Point puntoFinaleROI = null;
    private boolean ROIselectionMode=false;
        
    /*_______________INIZIA IL FRAME_____________________*/
    
    public frame()
    {
        /*setUndecorated(true);
        getRootPane().setWindowDecorationStyle(JRootPane.FRAME);*/
        
        Toolkit kit=Toolkit.getDefaultToolkit();
        Dimension DIM=kit.getScreenSize();
        setTitle("    IMAGO3  -  INTERFACCIA  PER  LABORATORIO  DI  IeM    ");
     
        setSize(DIM.width-100,DIM.height-100);
        Dimension dim=getSize();
        setLocation((DIM.width-dim.width)/2,(DIM.height-dim.height)*0);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
      
        Image icona=kit.getImage("icona5.gif");
        setIconImage(icona);
        CENTRO.setPreferredSize(new Dimension(((getSize().width/10)*8),(getSize().height/10)*8));
      

        jScrollPane1.getViewport().add(CENTRO, null);
            
        Container contentPane=getContentPane();
        // contentPane.add(NORD,BorderLayout.NORTH);
        contentPane.add(jScrollPane1,BorderLayout.CENTER);
        contentPane.add(SUD,BorderLayout.SOUTH);

        MENU();
        MENU_STATUS_BAR();
        CENTRO.addMouseListener(this);
        //CENTRO.addMouseMotionListener(this);
    }
    
    
//////////////////////////////////////////////////////////////////////////////////////////////////
    private void MENU()             //IL MENU DELLA JMenuBar
    {  
        setJMenuBar(BARRA_MENU);
        BARRA_MENU.add(MENU_FILE);
        BARRA_MENU.add(MENU_IMMAGINE);
        BARRA_MENU.add(MENU_BINARIE);
        BARRA_MENU.add(MENU_BUFFERED);
        BARRA_MENU.add(MENU_RESCALING);
        BARRA_MENU.add(MENU_LUT);
        BARRA_MENU.add(MENU_CONVOLVE);
        
        BARRA_MENU.add(SCELTA_MENU10);
        BARRA_MENU.add(SCELTA_MENU8);
        BARRA_MENU.add(SCELTA_MENU9);
        
        
        
        MENU_FILE.add(SOTTO_APRI);
        MENU_FILE.add(SOTTO_SALVA);
        MENU_FILE.add(SOTTO_CHIUDI);
        
        MENU_IMMAGINE.add(SOTTO_NEGATIVO);
        MENU_IMMAGINE.add(SOTTO_CANALER);//-------------
        MENU_IMMAGINE.add(SOTTO_CANALEV);
        MENU_IMMAGINE.add(SOTTO_CANALEB);
        MENU_IMMAGINE.add(SOTTO_CANALER_GRAY);
        MENU_IMMAGINE.add(SOTTO_CANALEV_GRAY);
        MENU_IMMAGINE.add(SOTTO_CANALEB_GRAY);
        MENU_IMMAGINE.add(SOTTO_RITAGLIA);
        MENU_IMMAGINE.add(SOTTO_UNDO);
        
        MENU_BINARIE.add(SOTTO_DITHERING);//--------------
        MENU_BINARIE.add(SOTTO_PATTERNING);
        MENU_BINARIE.add(SOTTO_ERROR_DIFFUSION);
        
        MENU_BUFFERED.add(SOTTO_SCAMBIA_RGB);
        MENU_BUFFERED.add(SOTTO_SCAMBIA_B);
        MENU_BUFFERED.add(SOTTO_MEDIANO);
        MENU_BUFFERED.add(SOTTO_TRASLAZIONE);
        MENU_BUFFERED.add(SOTTO_RISCALAMENTO);
        MENU_BUFFERED.add(SOTTO_RISCALAMENTO_PIU);
        MENU_BUFFERED.add(SOTTO_RISCALAMENTO_MENO);
        MENU_BUFFERED.add(SOTTO_ROTAZIONE);
        MENU_BUFFERED.add(SOTTO_SHEAR);
        MENU_BUFFERED.add(SOTTO_GENERICA_RASTER);
        
        MENU_RESCALING.add(SOTTO_GAIN);
        MENU_RESCALING.add(SOTTO_BIAS);
        
        MENU_LUT.add(SOTTO_ISTO_RGB);
        MENU_LUT.add(SOTTO_ISTO_RGB_CUM);
        MENU_LUT.add(SOTTO_INVERTI_R);
        MENU_LUT.add(SOTTO_INVERTI_V);
        MENU_LUT.add(SOTTO_INVERTI_B);
        MENU_LUT.add(SOTTO_INVERTI_ALL);
        MENU_LUT.add(SOTTO_SOGLIA_CHANNEL);
        MENU_LUT.add(SOTTO_SOGLIA_UNICA);
        MENU_LUT.add(SOTTO_INCUPIRE);
        MENU_LUT.add(SOTTO_SCHIARIRE);
        MENU_LUT.add(SOTTO_EQUALIZZA);
        MENU_LUT.add(SOTTO_LIVELLI_LOG);
        MENU_LUT.add(SOTTO_LIVELLI_K);
        MENU_LUT.add(SOTTO_LUT_RANDOM);
        MENU_LUT.add(SOTTO_LUT_GRADONI);
        
        MENU_CONVOLVE.add(SOTTO_SMOOTH);
        MENU_CONVOLVE.add(SOTTO_SMOOTH_O);
        MENU_CONVOLVE.add(SOTTO_SMOOTH_V);
        MENU_CONVOLVE.add(SOTTO_EDGEH);
        MENU_CONVOLVE.add(SOTTO_EDGEV);
        MENU_CONVOLVE.add(SOTTO_MEDIA_ANGOLI);
        MENU_CONVOLVE.add(SOTTO_MEDIA_VICINI);
        
        SCELTA_MENU10.add(AVVIA_CATTURA);
        
        /*ascoltatori del menu*/
        SOTTO_APRI.addActionListener(this);
        SOTTO_SALVA.addActionListener(this);
        SOTTO_CHIUDI.addActionListener(this);
        
        SOTTO_NEGATIVO.addActionListener(this);
        SOTTO_CANALER.addActionListener(this);
        SOTTO_CANALEV.addActionListener(this);
        SOTTO_CANALEB.addActionListener(this);
        SOTTO_CANALER_GRAY.addActionListener(this);
        SOTTO_CANALEV_GRAY.addActionListener(this);
        SOTTO_CANALEB_GRAY.addActionListener(this);
        SOTTO_RITAGLIA.addActionListener(this);   
        SOTTO_UNDO.addActionListener(this); 
        
        SOTTO_DITHERING.addActionListener(this);
        SOTTO_PATTERNING.addActionListener(this);
        SOTTO_ERROR_DIFFUSION.addActionListener(this);
        
        SOTTO_SCAMBIA_RGB.addActionListener(this);
        SOTTO_SCAMBIA_B.addActionListener(this);
        SOTTO_MEDIANO.addActionListener(this); 
        SOTTO_TRASLAZIONE.addActionListener(this);
        SOTTO_RISCALAMENTO.addActionListener(this);
        SOTTO_RISCALAMENTO_PIU.addActionListener(this);
        SOTTO_RISCALAMENTO_MENO.addActionListener(this);
        SOTTO_ROTAZIONE.addActionListener(this);
        SOTTO_SHEAR.addActionListener(this); 
        SOTTO_GENERICA_RASTER.addActionListener(this);
        
        SOTTO_GAIN.addActionListener(this);
        SOTTO_BIAS.addActionListener(this);
        
        SOTTO_ISTO_RGB.addActionListener(this);
        SOTTO_ISTO_RGB_CUM.addActionListener(this);
        SOTTO_INVERTI_R.addActionListener(this); 
        SOTTO_INVERTI_V.addActionListener(this);
        SOTTO_INVERTI_B.addActionListener(this);
        SOTTO_INVERTI_ALL.addActionListener(this);
        SOTTO_SOGLIA_CHANNEL.addActionListener(this); 
        SOTTO_SOGLIA_UNICA.addActionListener(this);
        SOTTO_INCUPIRE.addActionListener(this);
        SOTTO_SCHIARIRE.addActionListener(this);
        SOTTO_EQUALIZZA.addActionListener(this);
        SOTTO_LIVELLI_LOG.addActionListener(this);
        SOTTO_LIVELLI_K.addActionListener(this); 
        SOTTO_LUT_RANDOM.addActionListener(this);
        SOTTO_LUT_GRADONI.addActionListener(this);
        
        SOTTO_SMOOTH.addActionListener(this);
        SOTTO_SMOOTH_O.addActionListener(this); 
        SOTTO_SMOOTH_V.addActionListener(this);
        SOTTO_EDGEH.addActionListener(this);
        SOTTO_EDGEV.addActionListener(this);
        SOTTO_MEDIA_ANGOLI.addActionListener(this);
        SOTTO_MEDIA_VICINI.addActionListener(this);
        
        AVVIA_CATTURA.addActionListener(this);
        
        
    }
    public void MENU_STATUS_BAR()
    {
        MENU_FILE.addMouseListener(this);
        MENU_IMMAGINE.addMouseListener(this);
        MENU_BINARIE.addMouseListener(this);
        MENU_BUFFERED.addMouseListener(this);
        MENU_RESCALING.addMouseListener(this);
        MENU_LUT.addMouseListener(this);
        MENU_CONVOLVE.addMouseListener(this);
        SCELTA_MENU8.addMouseListener(this);
        SCELTA_MENU10.addMouseListener(this);   
        SCELTA_MENU9.addMouseListener(this);
            
        
        SOTTO_APRI.addMouseListener(this);
        SOTTO_SALVA.addMouseListener(this);
        SOTTO_CHIUDI.addMouseListener(this);
        
        SOTTO_NEGATIVO.addMouseListener(this);
        SOTTO_CANALER.addMouseListener(this);
        SOTTO_CANALEV.addMouseListener(this);
        SOTTO_CANALEB.addMouseListener(this);
        SOTTO_CANALER_GRAY.addMouseListener(this);
        SOTTO_CANALEV_GRAY.addMouseListener(this);
        SOTTO_CANALEB_GRAY.addMouseListener(this);
        SOTTO_RITAGLIA.addMouseListener(this); 
        SOTTO_UNDO.addMouseListener(this); 
          
          
        SOTTO_DITHERING.addMouseListener(this);
        SOTTO_PATTERNING.addMouseListener(this);
        SOTTO_ERROR_DIFFUSION.addMouseListener(this);
        
        SOTTO_SCAMBIA_RGB.addMouseListener(this);
        SOTTO_SCAMBIA_B.addMouseListener(this);
        SOTTO_MEDIANO.addMouseListener(this);
        SOTTO_TRASLAZIONE.addMouseListener(this);
        SOTTO_RISCALAMENTO.addMouseListener(this);
        SOTTO_ROTAZIONE.addMouseListener(this);
        SOTTO_SHEAR.addMouseListener(this);
        SOTTO_GENERICA_RASTER.addMouseListener(this);
        
        SOTTO_GAIN.addMouseListener(this); 
        SOTTO_BIAS.addMouseListener(this);
        
        SOTTO_ISTO_RGB.addMouseListener(this);
        SOTTO_ISTO_RGB_CUM.addMouseListener(this);
        SOTTO_INVERTI_R.addMouseListener(this); 
        SOTTO_INVERTI_V.addMouseListener(this);
        SOTTO_INVERTI_B.addMouseListener(this);
        SOTTO_INVERTI_ALL.addMouseListener(this);
        SOTTO_SOGLIA_CHANNEL.addMouseListener(this);
        SOTTO_SOGLIA_UNICA.addMouseListener(this);
        SOTTO_INCUPIRE.addMouseListener(this); 
        SOTTO_SCHIARIRE.addMouseListener(this);
        SOTTO_EQUALIZZA.addMouseListener(this);
        SOTTO_LIVELLI_LOG.addMouseListener(this);
        SOTTO_LIVELLI_K.addMouseListener(this); 
        SOTTO_LUT_RANDOM.addMouseListener(this);
        SOTTO_LUT_GRADONI.addMouseListener(this);
        
        SOTTO_SMOOTH.addMouseListener(this);
        SOTTO_SMOOTH_O.addMouseListener(this);
        SOTTO_SMOOTH_V.addMouseListener(this);
        SOTTO_EDGEH.addMouseListener(this); 
        SOTTO_EDGEV.addMouseListener(this);
        SOTTO_MEDIA_ANGOLI.addMouseListener(this);
        SOTTO_MEDIA_VICINI.addMouseListener(this);
        }
 ////////////////////////////////////////////////////////////////////////////////////////   
    public void actionPerformed(ActionEvent e)
    {
        Object source=e.getSource();
        if (source==SOTTO_APRI)                    {    this.apriFilecentro();  }
        else if (source==SOTTO_SALVA)             {    this.salva(); }
        else if (source==SOTTO_CHIUDI)             {    this.esci();  }
        
        else if(source==SOTTO_NEGATIVO){   this.negativeFilter();  }   
        else if(source==SOTTO_CANALER)              {    this.canale_Rosso(); }
        else if(source==SOTTO_CANALEV)              {    this.canale_Verde();  }
        else if(source==SOTTO_CANALEB)              {    this.canale_Blu();       }
        else if(source==SOTTO_CANALER_GRAY)              {    this. canaleRosso();  }
        else if(source==SOTTO_CANALEV_GRAY)              {    this. canaleVerde();  }
        else if(source==SOTTO_CANALEB_GRAY)            {    this.canaleBlue();  }
        else if(source==SOTTO_RITAGLIA)            {    this.ROI();  }
        else if(source==SOTTO_UNDO)              {    this.mostraOriginale();  }//System.out.println("UNDO");

        else if(source==SOTTO_DITHERING)            {    this.dithering();   }
        else if(source==SOTTO_PATTERNING)            {    this.patterning();   }
        else if(source==SOTTO_ERROR_DIFFUSION)            {    this.errorDiffusion();   }
        else if(source==SOTTO_SCAMBIA_RGB)            {    this.scambia_RGB();  }   
        else if(source==SOTTO_SCAMBIA_B)            {    this.scambia_ROSSO_BLU();  }   
        else if(source==SOTTO_MEDIANO)            {    this.mediano3(); }  
        else if(source==SOTTO_TRASLAZIONE)            {     this.trasla();}   
        else if(source==SOTTO_RISCALAMENTO)            {     this.scala();}  
        else if(source==SOTTO_RISCALAMENTO_PIU)            {     this.ingrandisci();}  
        else if(source==SOTTO_RISCALAMENTO_MENO)            {     this.diminuisci();}  
        else if(source==SOTTO_ROTAZIONE)            {    this.ruota(); }   
        else if(source==SOTTO_SHEAR)            {    this.shear(); }   
        else if(source==SOTTO_GENERICA_RASTER)            {     this.operazioneRaster();}   
    
        else if(source==SOTTO_GAIN)            {   this.onlyGain();   }   
        else if(source==SOTTO_BIAS)            {   this.onlyBias();   }   
        
        else if(source==SOTTO_ISTO_RGB)            {    this.istogrammi();   }  
        else if(source==SOTTO_ISTO_RGB_CUM)            {    this.istogrammiCumulativi(); }   
        else if(source==SOTTO_INVERTI_R)            {    this.invertiR(); }   
        else if(source==SOTTO_INVERTI_V)            {    this.invertiG();} 
        else if(source==SOTTO_INVERTI_B)            {    this.invertiB(); } 
        else if(source==SOTTO_INVERTI_ALL)            {    this.invertiAll(); }  
        else if(source==SOTTO_SOGLIA_CHANNEL)            {    this.soglie(); }   
        else if(source==SOTTO_SOGLIA_UNICA)            {    this.sogliaUnica(); }  
        else if(source==SOTTO_INCUPIRE)            {    this.incupire(); } 
        else if(source==SOTTO_SCHIARIRE)            {    this.schiarire(); }
        else if(source==SOTTO_EQUALIZZA)            {    this.equalizza();}
        else if(source==SOTTO_LIVELLI_LOG)            {    this.riquantizzaLog();}  
        else if(source==SOTTO_LIVELLI_K)            {    this.riquantizzaLin();} 
        else if(source==SOTTO_LUT_RANDOM)            {    this.randomLUT();  } 
        else if(source==SOTTO_LUT_GRADONI)            {    this.otherLUT(); } 
        else if(source==SOTTO_SMOOTH)            {     this.smoothing();}  
        else if(source==SOTTO_SMOOTH_O)            {     this.smoothingLH();}   
        else if(source==SOTTO_SMOOTH_V)            {     this.smoothingLV();  }  
        else if(source==SOTTO_EDGEH)            {     this.edgeH(); }  
        else if(source==SOTTO_EDGEV)            {      this.edgeV(); }
        else if(source==SOTTO_MEDIA_ANGOLI)            {   this.otherConvolve();  }   
        else if(source==SOTTO_MEDIA_VICINI)            {   this.nuovaConvoluzione();  }
     
        else if(source==AVVIA_CATTURA){ capturer=new ScreenShotFrame();  capturer.setVisible(true); }   
    }
    
    
 /*_______________INIZIO DEI METODI PER LE FUNZIONALITA' DEL MOUSE______________________  
  
 Si deve mantenere la promessa sottoscritta con "implements MouseListener"
    e "implements MouseMotionListener"
__________________________________________________________________________________*/
  
    //public void mouseMoved(MouseEvent e) {}//MouseMotionListener
   // public void mouseDragged(MouseEvent e){}//MouseMotionListener
        
    public void mousePressed(MouseEvent e)//MouseListener
    { 
        Object source=e.getSource();
        if (source==CENTRO) 
        {
            // System.out.println("pressed"+(++ CONTQWE));
            if (ROIselectionMode)
            puntoInizialeROI=e.getPoint();
            // System.out.println("1i    "+(e.getPoint().getX()));
            // System.out.println("2i    "+(e.getPoint().getY()));
        }
    }  
    JScrollPane scroll_ROI;
    public void mouseReleased(MouseEvent e)//MouseListener
    {
        Object source=e.getSource();
        if (source==CENTRO&&image!=null) 
        {
             /* System.out.println("released"+(++ CONTQWE1));
                System.out.println("1f    "+(e.getPoint().getX()));
                System.out.println("2f    "+(e.getPoint().getY()));*/
            if (ROIselectionMode)
            {
                puntoFinaleROI=e.getPoint();
                ROIselectionMode=false;
                int x1 = (int)puntoInizialeROI.getX();
                int y1 = (int)puntoInizialeROI.getY();
                int x2 = (int)puntoFinaleROI.getX();
                int y2 = (int)puntoFinaleROI.getY();
                if(x1!=x2&&y1!=y2){
                int x_topLeft = (x1>x2)?x2:x1;
                int y_topLeft = (y1>y2)?y2:y1;
                int wROI = Math.abs(x1-x2);
                int hROI = Math.abs(y1-y2);
                BufferedImage temp = new BufferedImage(wROI,hROI, BufferedImage.TYPE_INT_ARGB);
                copiaImmagine(image.getSubimage(x_topLeft, y_topLeft,wROI,hROI),temp);
                
                scroll_ROI= new JScrollPane();
                ROI_frame frameROI = new ROI_frame(temp);
                                
                Toolkit kit=Toolkit.getDefaultToolkit();
                Dimension DIM=kit.getScreenSize();
                   
                frameROI.getContentPane().add(scroll_ROI, BorderLayout.CENTER);
                pannello_immagine pannelloROI= new pannello_immagine();
                pannelloROI.setImage(temp);
                scroll_ROI.getViewport().add(pannelloROI, null);  
                int w=temp.getWidth();
                int h=temp.getHeight();
                pannelloROI.setPreferredSize(new Dimension(w*2,h*2));
              
          
              
                frameROI.setLocation((DIM.width-w)/2,(DIM.height-h)/2); 
                frameROI.setTitle("SELEZIONE "+(++JCONT));
                frameROI.setVisible(true);}
              

            }  
        }
        /*
        JFrame frameROI = new JFrame();
        JScrollPane scroll= new JScrollPane();
        frameROI.getContentPane().add(scroll, BorderLayout.CENTER);
        pannello_immagine pannelloROI= new pannello_immagine();
        pannelloROI.setImage(temp);
        scroll.getViewport().add(pannelloROI, null);
        int w=temp.getWidth();
        int h=temp.getHeight();
        frameROI.setSize(w,h);
        frameROI.setTitle("ROI");
        frameROI.setVisible(true);
        */
    }
    
    public void mouseClicked(MouseEvent e)//MouseListener
    {  
        Object source=e.getSource();
        if (e.getButton()==3) 
        {
            if(source== SCELTA_MENU9)    
            {
               if(SUD.getHelp())
                {
                nuovo=new frame_help();
                nuovo.setTesto("PACKAGE: java.awt.image"+
                "\n_________________________________________________"+
                "\n\nInterface BufferedImageOp\n\nQueste sono tutte le classi implementate:"+
                "\nAffineTransformOp,\nColorConvertOp,\nConvolveOp,\nLookupOp,\nRescaleOp"+
                "\n_________________________________________________"+
                "\n\nInterface ImageConsumer\n\nQueste sono tutte le classi implementate:"+
                "\nImageFilter,\nPixelGrabber"+
                "\n_________________________________________________"+
                "\n\nInterface ImageObserver\n\nAll Known Implementing Classes:"+
                "\nComponent "+
                "\n_________________________________________________"+
                "\n\nInterface ImageProducer\n\nAll Known Implementing Classes:"+
                "\nFilteredImageSource,\nMemoryImageSource,\nRenderableImageProducer"+
                "\n_________________________________________________"+
                 "\n\nInterface RasterOp\n\nAll Known Implementing Classes:"+ 
                "\nAffineTransformOp,\nBandCombineOp,\nColorConvertOp,\nConvolveOp,\nLookupOp,\nRescaleOp"+
                "\n_________________________________________________"+
                "\n\nInterface RenderedImage\n\nAll Known Subinterfaces:"+
                "\nWritableRenderedImage "+
                "\n\nAll Known Implementing Classes:"+"\nBufferedImage"+
                "\n_________________________________________________"+
                "\n\nInterface TileObserver"+
                 "\n_________________________________________________"+
                "\n\nInterface WritableRenderedImage"+
                "\n\nAll Superinterfaces:\nRenderedImage"+ 
                "\n\nAll Known Implementing Classes:\nBufferedImage"+
                 "\n_________________________________________________");
                nuovo.setVisible(true);
                
                }
            }
            else
            if(source== MENU_RESCALING) 
            {
                if(SUD.getHelp())
                {
                    nuovo=new frame_help();
                    nuovo.setTesto("Funzionalita in progress");
                    nuovo.setVisible(true);
                }
            }
        } 
    }
    
/*MouseListener, mouseEntered*/
    public void mouseEntered(MouseEvent e)
    {   
        Object source=e.getSource();
        
        if (source== MENU_FILE)      {    SUD.change_String("Menu file"); }
        else if(source== MENU_IMMAGINE){    SUD.change_String("Operazioni generiche sull' immagine"); }
        else if(source== MENU_BINARIE){    SUD.change_String("Operazioni binarie su immagine"); }
        else if(source== MENU_BUFFERED){ SUD.change_String("Operazioni sulle immagini con l' ausilio di BufferedImageOp");}
        else if(source== MENU_RESCALING){ SUD.change_String("Rescaling");  }
        else if(source== MENU_LUT)        {    SUD.change_String("Operazioni di LUT"); }
        else if(source== MENU_CONVOLVE)        {    SUD.change_String("Convoluzioni"); }
        else if(source== SCELTA_MENU8)        {    SUD.change_String("ANCORA DA IMPLEMENTARE----"); }
        else if(source== SCELTA_MENU10)        {    SUD.change_String("Attiva la funzionalita' di cattura dello schermo");       }
        else if(source== SCELTA_MENU9)        {    SUD.change_String("Piccolo aiuto");       }
        
        else if(source==SOTTO_APRI)          {    SUD.change_String("Apri una immagine secondo l' estenzione voluta");   }
        else if(source==SOTTO_SALVA)          {    SUD.change_String("Salva l' immagine");  }
        else if(source==SOTTO_CHIUDI)          {    SUD.change_String("Esci dal programma");   }
       
        else if(source==SOTTO_NEGATIVO) {SUD.change_String("Fa il negativo dell'immagine");  }
        else if(source==SOTTO_CANALER)          {    SUD.change_String("Fai vedere il canale rosso");  }
        else if(source==SOTTO_CANALEV)          {    SUD.change_String("Fai vedere il canale verde");   }
        else if(source==SOTTO_CANALEB)          {    SUD.change_String("Fai vedere il canale blu ");        }
        else if(source==SOTTO_CANALER_GRAY)          {    SUD.change_String("Fai vedere il canale rosso in toni di grigio");   }
        else if(source==SOTTO_CANALEV_GRAY)          {    SUD.change_String("Fai vedere il canale verde in toni di grigio");   }
        else if(source==SOTTO_CANALEB_GRAY)         {    SUD.change_String("Fai vedere il canale blu in toni di grigio");  }
        else if(source==SOTTO_RITAGLIA)         {    SUD.change_String("Ritaglia porzione di immagine dal punto del primo click fino al rilascio del plsante del mouse");   }
        else if(source==SOTTO_UNDO)          {    SUD.change_String("Visualizza l' immagine originale"); }//System.out.println("UNDO");

        
        else if(source==SOTTO_DITHERING)         {    SUD.change_String("Dithering");   }
        else if(source==SOTTO_PATTERNING)         {    SUD.change_String("Patterning, apre l' immagine utilizzando una nuova finestra");  }
        else if(source==SOTTO_ERROR_DIFFUSION)         {    SUD.change_String("Error diffusion");  }
        
        else if(source==SOTTO_SCAMBIA_RGB)         {    SUD.change_String("Scambia i colori con uno qualsiasi degli rgb (da verificare)");  }   
        else if(source==SOTTO_SCAMBIA_B)         {    SUD.change_String("Scambia il rosso col blu");  } 
        else if(source==SOTTO_MEDIANO)         {    SUD.change_String("Mediano 3x3 produce un effetto acquarello");  }
        else if(source==SOTTO_TRASLAZIONE)         {    SUD.change_String("Traslazione");  } 
        else if(source==SOTTO_RISCALAMENTO)         {    SUD.change_String("Risclamento");  }
        else if(source==SOTTO_ROTAZIONE)         {    SUD.change_String("Rotazione");  }  
        else if(source==SOTTO_SHEAR)         {    SUD.change_String("Shear");  }    
        else if(source==SOTTO_GENERICA_RASTER)         {    SUD.change_String("Generica operazione sul Raster come da compito 1/2/07");  }    
       
        else if(source==SOTTO_GAIN)         {    SUD.change_String("Aggiusta il Gain");  }    
        else if(source==SOTTO_BIAS)         {    SUD.change_String("Correggi il Bias");  }     
        
        else if(source==SOTTO_ISTO_RGB)         {    SUD.change_String("Istogramma RGB");  }    
        else if(source==SOTTO_ISTO_RGB_CUM)         {    SUD.change_String("Istogramma RGB cumulativo");  }    
        else if(source==SOTTO_INVERTI_R)         {    SUD.change_String("Inverti rosso");  }      
        else if(source==SOTTO_INVERTI_V)         {    SUD.change_String("Inverti verde");  }    
        else if(source==SOTTO_INVERTI_B)         {    SUD.change_String("Inverti  blu");  }     
        else if(source==SOTTO_INVERTI_ALL)         {    SUD.change_String("Inverti tutti i colori");  }     
        else if(source==SOTTO_SOGLIA_CHANNEL)         {    SUD.change_String("Dai una soglia diversa a ogni canale");  }   
        else if(source==SOTTO_SOGLIA_UNICA)         {    SUD.change_String("Dai una soglia unica");  }     
        else if(source==SOTTO_INCUPIRE)         {    SUD.change_String("Incupire lossy");  }     
        else if(source==SOTTO_SCHIARIRE)         {    SUD.change_String("Schiarire lossy");  }     
        else if(source==SOTTO_EQUALIZZA)         {    SUD.change_String("Equalizzazione");  }     
        else if(source==SOTTO_LIVELLI_LOG)         {    SUD.change_String("Su livelli log");  }     
        else if(source==SOTTO_LIVELLI_K)         {    SUD.change_String("Su k livelli lin");  }   
        else if(source==SOTTO_LUT_RANDOM)         {    SUD.change_String("LUT random");  }       
        else if(source==SOTTO_LUT_GRADONI)         {    SUD.change_String("LUT a gradoni come da compito 1/2/07");  }     
        
        else if(source==SOTTO_SMOOTH)         {    SUD.change_String("Smoothing, sfoca l' immagine");  }     
        else if(source==SOTTO_SMOOTH_O)         {    SUD.change_String("Smoothing orizzontale");  }     
        else if(source==SOTTO_SMOOTH_V)         {    SUD.change_String("Smoothing verticale");  }     
        else if(source==SOTTO_EDGEH)         {    SUD.change_String("Edge orizzontale");  }    
        else if(source==SOTTO_EDGEV)         {    SUD.change_String("Edge verticale");  }    
        else if(source==SOTTO_MEDIA_ANGOLI)         {    SUD.change_String("Media degli angoli come da compito 1/2/07");  }    
        else if(source==SOTTO_MEDIA_VICINI)         {    SUD.change_String("Media degli pixel estremi ai lati del pixel");  }  
        }
    
    public void mouseExited(MouseEvent e)//MouseListener
    {
        SUD.ripristina_String();
    }

    
/*  metodo per sostiutire la immagine da elaborare con una sua regione di interesse
    serve per ritagliare l'immagine*/
    private void ROI()
    {
        ROIselectionMode=true;
        // il resto è fatto tutto da mouseReleased se siamo in modalità ROIselection
    } 
    
 /*_______________FINE DEI METODI PER LE FUNZIONALITA' DEL MOUSE______________________*/   
          
 
/*__________________________________________________________________________________
    ______________________FUNZIONI DEL MENU' FILE_______________________________________
    _________________________________________________________________________________*/
    
/*Questo metodo e' chiamato per aprire una immagine dal menu File-Apri*/
    private void apriFilecentro()
    {
        // questo metodo dipende pesantemente dal fileChooser di cui
        // dotato il Frame
        // la chiamata ad un metodo del fileChooser
        // attiva automaticamente tale dialogo
          
        //infine alcune cose da precisare per il FileChooser
        // non vogliamo che la applicazione legga tutti i file
        aprifile1.setAcceptAllFileFilterUsed(false);
        // e li precisiamo usando la classe Filter
        aprifile1.setFileFilter(new IlMioFiltro());
        // aprifile1.setFileFilter(new PNGFilter());
        //aprifile1.setFileFilter(new JPGFilter());
       
        int risposta=aprifile1.showOpenDialog(this);
        if (risposta==aprifile1.APPROVE_OPTION) //Se ho premuto il tasto ok nel dialogo
        {
            try
            {
                //Carico l'immagine in una variabile BufferedImage di appoggio
                BufferedImage buff=ImageIO.read(aprifile1.getSelectedFile());
                //recupero le dimensioni dell'immagine
                int w=buff.getWidth();
                int h=buff.getHeight();
                // la variabile immagine del Frame è
                // ricreata interamente nuova delle stesse dimensioni
                // di quella caricata
                image=new BufferedImage(w,h,BufferedImage.TYPE_INT_ARGB);
                IMMAGINE=new BufferedImage(w,h,BufferedImage.TYPE_INT_ARGB);
                // adesso passo a ricopiare l'immagine caricata nella variabile
                // immagine. Tale "copiatura" mi assicura che il TYPE della
                // BufferedImage è TYPE_INT_ARGB e renderà standard le operazioni
                // successive
                // l'operazione di copia richiede un contesto grafico appropriato
                Graphics2D g2=image.createGraphics();
                g2.drawImage(buff,0,0,null);
                // il contesto grafico non serve più e lo distruggo
                g2.dispose();
                //copiatura sulla immagine "di archivio"
                Graphics2D g3=IMMAGINE.createGraphics();
                g3.drawImage(buff,0,0,null);
                g3.dispose();
                //Imposto l'immagine nel pannello
                CENTRO.setImage(image);
                //Imposto le dimensioni del pannello
                CENTRO.setPreferredSize(new Dimension(w,h));
                //Reimposto tutto il layout del pannello
                jScrollPane1.revalidate();
            }
            catch (Exception ex) {}
        }
    }
                  

/*Questo metodo e' chiamato  per salvare una immagine dal menu  File-Salva*/         
    private int salva()
    {
        int risposta2=JOptionPane.NO_OPTION;
        File f=null;
        String ext=null;
        while (risposta2==JOptionPane.NO_OPTION) //Finchè non decido di salvare o di rinunciare
        {
            //Visualizzo la finestra di dialogo
            int risposta=aprifile1.showSaveDialog(this);
            
            if (risposta==aprifile1.APPROVE_OPTION) //Se ho premuto il tasto salva
            {
                try
                {
                    //Recupero il file selezionato
                    f=aprifile1.getSelectedFile();
                    //Recupero l'estensione del file
                    ext=aprifile1.getFileFilter().toString();
                    //Recupero il path del file
                    String str=f.getCanonicalPath();
                    //Se il nome del file non contiene l'estensione, la aggiungo io a mano
                    if (!str.toLowerCase().endsWith("."+ext)) f=new File(str+"."+ext);
                    //Se il file esiste chiedo se lo voglio sovrascrivere
                    if (f.exists()) risposta2=JOptionPane.showConfirmDialog(this,"Il file esiste già,\nlo vuoi sovrascrivere?",
"Sovrascrittura",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
                    else
                    {
                        risposta2=JOptionPane.YES_OPTION;
                    }
                }
                catch (Exception ex)
                {
                }
            } 
            else 
            {
                risposta2=JOptionPane.CANCEL_OPTION;
            }
        }
        if (risposta2==JOptionPane.YES_OPTION)
        {
            try
            {
                //Provo a salvare l'immagine
                ImageIO.write(image,ext,f);
            }
            catch (Exception ex)
            {
            }
        }
        return risposta2;
    }
    
/*questo metodo e' chiamato per chiudere l' applicazione dal menu File-Esci*/
    private void esci()
    {
        System.exit(0);
    }
    
  /* metodo per copiare la copia di lavoro dalla copia originale di una immagine
      usato dai filtri per operare sempre a partire dall'originale ricopia A dentro B */
    private void copiaImmagine(BufferedImage A, BufferedImage B)
    {
        Graphics2D g2=B.createGraphics();
        g2.drawImage(A,0,0,null);
        g2.dispose();//non necessario
    }
    
/*metodo per ripristinare la visione della immagine originale invocato dal menu Operazioni-originale*/
    private void mostraOriginale()
    {
        if(image!=null)
        { 
            copiaImmagine(IMMAGINE,image );//IMMAGINE non viene modificataviene copiata sulle altre per l'undo
            CENTRO.repaint();
        }
    }  
    
    
    
    
/*___________________________________________________________________________________
    ______________________________OPERAZIONI GENERALI___________________________________
    ___________________________________________________________________________________*/
    private void negativeFilter() 
    {  if(image!=null)
        {
        image =negativeFilter(image);
        CENTRO.setImage(image);
        CENTRO.repaint();
        }
    }
    public static BufferedImage negativeFilter(BufferedImage input)
    {
        Color oldColor, newColor;
        int width = input.getWidth();
        int height = input.getHeight();
        int type = input.getType();
        BufferedImage output = new BufferedImage(width, height, type);
        for (int y=0; y<height; y++) 
        {
            for (int x=0; x<width; x++) 
            {
                oldColor = new Color(input.getRGB(x, y), true);
                newColor = new Color(255-oldColor.getRed(),255-oldColor.getGreen(), 255-oldColor.getBlue(),oldColor.getAlpha());
                output.setRGB(x, y, newColor.getRGB());
            }
        }
        return output;
    }
    
  /*metodo per estrarre il canale Rosso  invocato dal menu Operazioni-canale Rosso*/
  private void canale_Rosso()
  {
        if(image!=null)
        {
            copiaImmagine(IMMAGINE,image );
            int width=image.getWidth();
            int height=image.getHeight();
            System.out.println("larg="+width+" alt="+height);
            WritableRaster raster=image.getRaster();
            int [] zeri = new int [width*height];
            // annullo i canali verde e blu
            raster.setSamples(0,0,width,height,1,zeri);
            raster.setSamples(0,0,width,height,2,zeri);
            CENTRO.repaint();
        }
  }

   /* metodo per estrarre il canale Verde  invocato dal menu Operazioni-canale verde  */
    private void canale_Verde()
    {
        if(image!=null)
        { 
            copiaImmagine(IMMAGINE,image );
            int width=image.getWidth();
            int height=image.getHeight();
            System.out.println("larg="+width+" alt="+height);
            WritableRaster raster=image.getRaster();
            int [] zeri = new int [width*height];
            // annullo i canali rosso e blu
            raster.setSamples(0,0,width,height,0,zeri);
            raster.setSamples(0,0,width,height,2,zeri);
            CENTRO.repaint();
        }
    }
    
   /*  metodo per estrarre il canale Blue  invocato dal menu Operazioni-canale blu */
    private void canale_Blu()
    {
        if(image!=null)
        {  
            copiaImmagine(IMMAGINE,image );
            int width=image.getWidth();
            int height=image.getHeight();
            // IMMAGINE=new BufferedImage( width,height,BufferedImage.TYPE_INT_ARGB);
            // IMMAGINE= (BufferedImage)image;
            System.out.println("larg="+width+" alt="+height);
            WritableRaster raster=image.getRaster();
            int [] zeri = new int [width*height];
            // annullo i canali verde e rosso
            raster.setSamples(0,0,width,height,0,zeri);
            raster.setSamples(0,0,width,height,1,zeri);
            CENTRO.repaint();
        } 
    }
/*________ALTRI CANALI MA INTONI DI GRIGIO____________*/
    
/*  metodo per estrarre il canale Rosso in toni di grigio */   
    private void canaleRosso()
    {
        if(image!=null)
        { 
             // per essere certi che il filtro agisca sull'originale
             // e non sulla immagine già elaborata dobbiamo "ricopiare" l'originale
             // che teniamo inalterato nella copia di lavoro.
             // la copia di oggetti complessi non si può ovviamente fare con
             // un semplice assegnamento
             // opereremo esattamente come quando abbiamo caricato l'immagine
             // poichè questa operazione va fatta per tutti i filtri viene
             // isolata in un metodo privato di servizio copiaImm(BufferedImage A, BufferedImage B)
            copiaImmagine(IMMAGINE, image);
             // poi tutto come prima
            int w=image.getWidth();
            int h=image.getHeight();
            WritableRaster wr=image.getRaster();
            // faccio diventare i canali verdi e blu eguali al rosso
            wr.setSamples(0,0,w,h,1,wr.getSamples(0,0,w,h,0,(int [])null));
            wr.setSamples(0,0,w,h,2,wr.getSamples(0,0,w,h,0,(int [])null));
            CENTRO.repaint();
        }
    }

/*  metodo per estrarre il canale Verde in toni di grigio */
    private void canaleVerde()
    {
        if(image!=null)
        { 
             // operazione per preservare l'originale
            copiaImmagine(IMMAGINE, image);
             // poi tutto come prima
            int w=image.getWidth();
            int h=image.getHeight();
            WritableRaster wr=image.getRaster();
            int [] zeri = new int [w*h];
            // faccio diventare i canali rosso e blu eguali al verde
            wr.setSamples(0,0,w,h,0,wr.getSamples(0,0,w,h,1,(int [])null));
            wr.setSamples(0,0,w,h,2,wr.getSamples(0,0,w,h,1,(int [])null));
            CENTRO.repaint();
        }
    }
  
/*  metodo per estrarre il canale Blue in toni di grigio*/
    private void canaleBlue()
    {
        if(image!=null)
        { 
             // operazione per preservare l'originale
            copiaImmagine(IMMAGINE, image);
             // poi tutto come prima
            int w=image.getWidth();
            int h=image.getHeight();
            WritableRaster wr=image.getRaster();
            int [] zeri = new int [w*h];
           // faccio diventare i canali verdi e rosso eguali al blu
            wr.setSamples(0,0,w,h,1,wr.getSamples(0,0,w,h,2,(int [])null));
            wr.setSamples(0,0,w,h,0,wr.getSamples(0,0,w,h,2,(int [])null));
            CENTRO.repaint();
        }
    }
    

   
   
/*________________________________________________________________________________________
_________________________________FILTRI BINARI______________________________________________
   ________________________________________________________________________________________*/   
   
/* :::::::dithering con una matrice 2x2 per semplicità si parte da una RGBA usando solo il canale verde::::::::::::*/

    private void dithering()
    {
        if(image!=null)
        { 
            int [][] matriceDithering = {{0, 128},{192, 64}};
            
            Raster inputRaster=image.getRaster();
            int w=image.getWidth();
            int h=image.getHeight();
            BufferedImage temp = new BufferedImage(w, h, BufferedImage.TYPE_BYTE_BINARY);
            WritableRaster outRaster=temp.getRaster();
            for (int i=0; i<w; i++)
            {
                for(int j=0; j<h; j++)
                {
                    if (inputRaster.getSample(i,j,1)>matriceDithering[i%2][j%2])
                    {
                        outRaster.setSample(i,j,0,1);
                    }
                }
            }
            copiaImmagine(temp, image); // rimetto tutto in una RGBA
            CENTRO.setImage(image);
            CENTRO.repaint();
        } 
    }

/*::::::::: Metodo per portare l'immagine i grigio e colorarla in binario con un pattern 2x2
   Per semplicità i valori di ingresso di una immagine RGBA sono presi solo dal canale G :::::::::::::*/
    private void patterning()
    {
        if(image!=null)
        { 
            Raster inputRaster=image.getRaster();
            int w=image.getWidth();
            int h=image.getHeight();
            BufferedImage temp = new BufferedImage(2*w, 2*h, BufferedImage.TYPE_BYTE_BINARY);
            WritableRaster outRaster=temp.getRaster();
            for (int i=0; i<w; i++)
            {
                for(int j=0; j<h; j++)
                {
                    if (inputRaster.getSample(i,j,1)<51)
                    {
                        outRaster.setSample(2*i,2*j,0,0);
                        outRaster.setSample(2*i+1,2*j,0,0);
                        outRaster.setSample(2*i,2*j+1,0,0);
                        outRaster.setSample(2*i+1,2*j+1,0,0);
                    }
                    else if (inputRaster.getSample(i,j,1)<102)
                    {
                        outRaster.setSample(2*i,2*j,0,1);
                        outRaster.setSample(2*i+1,2*j,0,0);
                        outRaster.setSample(2*i,2*j+1,0,0);
                        outRaster.setSample(2*i+1,2*j+1,0,0);
                    }
                    else if (inputRaster.getSample(i,j,1)<153)
                    {
                        outRaster.setSample(2*i,2*j,0,1);
                        outRaster.setSample(2*i+1,2*j,0,0);
                        outRaster.setSample(2*i,2*j+1,0,0);
                        outRaster.setSample(2*i+1,2*j+1,0,1);
                    }
                    else if (inputRaster.getSample(i,j,1)<204)
                    {
                        outRaster.setSample(2*i,2*j,0,1);
                        outRaster.setSample(2*i+1,2*j,0,1);
                        outRaster.setSample(2*i,2*j+1,0,0);
                        outRaster.setSample(2*i+1,2*j+1,0,1);
                    }
                    else
                    {
                        outRaster.setSample(2*i,2*j,0,1);
                        outRaster.setSample(2*i+1,2*j,0,1);
                        outRaster.setSample(2*i,2*j+1,0,1);
                        outRaster.setSample(2*i+1,2*j+1,0,1);
                    }
                } 
            }
            /*  
                image= new BufferedImage(4*w,4*h,BufferedImage.TYPE_INT_ARGB);
                System.out.println("w   "+w+"     h   "+h);
                copiaImmagine(temp, image);
                CENTRO.setPreferredSize(new Dimension(2*w,2*h));
                CENTRO.setImage(image);
                //Reimposto tutto il layout del pannello
                jScrollPane1.revalidate();
                CENTRO.repaint();*/
            /*apro l'immagine col patternizzata su un frame indipendente*/
            
            JFrame framePattern = new JFrame();
            JScrollPane scroll= new JScrollPane();
            framePattern.getContentPane().add(scroll, BorderLayout.CENTER);
            if(w>800||h>600)
            {                    
                framePattern.setSize(w/2,h/2);
            }
            else    
            {                    
                framePattern.setSize(w,h);
            }
            panel_aux pannelloPattern= new panel_aux();
            pannelloPattern. setPreferredSize(new Dimension(2*w,2*h));
            pannelloPattern.setImage(temp);
            scroll.getViewport().add(pannelloPattern, null);
            scroll.revalidate();
            framePattern.setTitle("IMMAGINE PATTERNED");
            framePattern.setVisible(true);
        }
    }
   
/*:::::::::::::::::::::::dithering con error diffusion -uso canale G di ARGB::::::::::::::::::::::::::::::::::::::::::::::::::*/
    private void errorDiffusion()
    {
        if(image!=null)
        { 
            //==============
            int soglia=200;
            //==============
            WritableRaster inputRaster=image.getRaster();
            int w=image.getWidth();
            int h=image.getHeight();
               
            BufferedImage temp = new BufferedImage(w, h, BufferedImage.TYPE_BYTE_BINARY);
            WritableRaster outRaster=temp.getRaster();
            int errore=0; 
            int valoreOrig=0; 
            int correzione=0;
               
            for (int i=1; i<w-1; i++)
            {
                for(int j=1; j<h-1; j++)  // i bound sono per evitare guai ai bordi
                {
                    if (inputRaster.getSample(i,j,1)<soglia)
                    {
                        errore=inputRaster.getSample(i,j,1); // lascio il pixel nero  e mi registro la luminanza mancante
                    }                                
                    else 
                    {
                        outRaster.setSample(i,j,0,1);
                        errore=255-inputRaster.getSample(i,j,1); // registro la luminanza in eccesso
                    }
                    // disperdere....
                    valoreOrig=inputRaster.getSample(i+1,j,1);
                    correzione=clamp(valoreOrig+0.43*errore);
                    inputRaster.setSample(i+1,j,1,correzione);
                
                    valoreOrig=inputRaster.getSample(i-1,j+1,1);
                    correzione=clamp(valoreOrig+0.18*errore);
                    inputRaster.setSample(i-1,j+1,1,correzione);
                
                    valoreOrig=inputRaster.getSample(i,j+1,1);
                    correzione=clamp(valoreOrig+0.31*errore);
                    inputRaster.setSample(i,j+1,1,correzione);

                    valoreOrig=inputRaster.getSample(i+1,j+1,1);
                    correzione=clamp(valoreOrig+0.08*errore);
                    inputRaster.setSample(i+1,j+1,1,correzione);
                }
            }
            copiaImmagine(temp, image); // rimetto tutto in una RGBA
            CENTRO.setImage(image);
            CENTRO.repaint();
        }
   }

/*metodo per "tagliare" ciò che va oltree 255 e sotto zero*/
    private int clamp(double d)
    {
        int r=(int)d;
        
        if (r>255)
        {
            r=255;
        }
        else
        {
            if (r<0)
            {
                r=0;
            }
        }
        return r;
    }
    
       
 /*_____________________________________________________________________________
    ________________________ALTRI FILTRI BufferedImageOp______________________________
    _____________________________________________________________________________*/  
      
       
/*Metodo per scambiare il rosso col verde usa la classe scambiaRG implementata in seguito*/
    private void scambia_RGB()
    {
        if(image!=null)
        { 
            CHANGE_RGB sRG = new CHANGE_RGB();
            BufferedImage temp=sRG.filter(image,image);
            copiaImmagine(temp, image);
            CENTRO.setImage(image);
            CENTRO.repaint();
        }
    }
    
 /*Metodo per scambiare il rosso col verde usa la classe scambiaRB implementata sul file CHANGE_RB.java*/
    private void scambia_ROSSO_BLU()
    {
        if(image!=null)
        { 
            CHANGE_RB sRG = new CHANGE_RB();
            BufferedImage temp=sRG.filter(image,image);
            copiaImmagine(temp, image);
            CENTRO.setImage(image);
            CENTRO.repaint();
        }
    }
    
/* mediano3x3*/
    private void mediano3()
    {    
        if(image!=null)
        { 
            mediano3 med3 = new mediano3();
            BufferedImage temp=med3.filter(image,image);
            copiaImmagine(temp, image);
            CENTRO.setImage(image);
            CENTRO.repaint();
        }
    }
       
/* traslazione dell' immagine*/
    private void trasla()
    { 
        if(image!=null)
        { 
            try
            {
                // ottengo dall'utente i parametri di traslazione
                int offsetX=Integer.parseInt(JOptionPane.showInputDialog("Offset nella direzione x"));
                int offsetY=Integer.parseInt(JOptionPane.showInputDialog("Offset nella direzione y"));
                 // creo la traslazione identica
                AffineTransform A=new AffineTransform(1,0,0,1,offsetX,offsetY);
                // creo l'operatore affine che applichi la traslazione teste' creata
                AffineTransformOp T=new AffineTransformOp(A,3);// il tipo di interpolazioen Bicubica vale 3
                 // preparo la nuova immagine
                BufferedImage temp=T.filter(image,null);
                image=temp;
                if((offsetX>0))
                {
                    CENTRO.setImage(image,(int)(temp.getWidth()+offsetX),(int)(temp.getHeight()+offsetY));
                }
                else
                if((offsetX<0))
                {
                    CENTRO.setImage(image,(int)(temp.getWidth()-offsetX),(int)(temp.getHeight()-offsetY));
                }
                CENTRO.repaint();
                CENTRO.revalidate();
                 /* ATTENZIONE, si è scelto qui di costruire esplicitamente la matrice di
                    trasformazione per ragioni di chiarezza didattica, potevo pero operare come segue
                    AffineTransofrm A=new AffineTransform(); // la identitï¿½
                    A.translate(offsetX, offsetY); // la A ï¿½ ora la matrice di traslazione richiesta
                    Cose simili si possono fare per la scala e la rotazione
                 */
            }
            catch(Exception e )
            {
             JOptionPane.showMessageDialog(null,"HAI DIGITATO UN CARATTERE ERRATO ",
                "ATTENZIONE",JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
/*riscalamento dell' immagine*/
    private void scala()
    { 
        double scalaX;
        double scalaY;
        if(image!=null)
        { 
            try
            {
                // ottengo dall'utente i parametri di scala
            
                scalaX=Double.parseDouble(JOptionPane.showInputDialog("Fattore di scala nella direzione x"));
                scalaY=Double.parseDouble(JOptionPane.showInputDialog("Fattore di scala nella direzione y"));
                                    
                // creo la trasformazione di scala
                AffineTransform A=new AffineTransform(scalaX,0,0,scalaY,0,0);
                // creo l'operatore affine che applichi la trasformazione teste' creata
                AffineTransformOp T=new AffineTransformOp(A,3);// il tipo di interpolazioen Bicubica vale 3
                // preparo la nuova immagine
                BufferedImage temp=T.filter(image,null);
                image=temp;
               // CENTRO.setPreferredSize(new Dimension((int)(temp.getWidth()*scalaX),(int)(temp.getHeight()*scalaY)));
                CENTRO.setImage(image,(int)(temp.getWidth()),(int)(temp.getHeight()));
                CENTRO.repaint();
                CENTRO.revalidate();
            }
            catch(Exception e )
            {
            JOptionPane.showMessageDialog(null,"HAI DIGITATO UN CARATTERE ERRATO ","ATTENZIONE",JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    /*riscalamento dell' immagine*/
    private void ingrandisci()
    {
        if(image!=null)
        { 
            // ottengo dall'utente i parametri di scala
            double scalaX=2;//Double.parseDouble(JOptionPane.showInputDialog("Fattore di scala nella direzione x"));
            double scalaY=2;//Double.parseDouble(JOptionPane.showInputDialog("Fattore di scala nella direzione y"));
            // creo la trasformazione di scala
            AffineTransform A=new AffineTransform(scalaX,0,0,scalaY,0,0);
            // creo l'operatore affine che applichi la trasformazione teste' creata
            AffineTransformOp T=new AffineTransformOp(A,3);// il tipo di interpolazioen Bicubica vale 3
            // preparo la nuova immagine
            BufferedImage temp=T.filter(image,null);
            image=temp;
             copiaImmagine(temp, image);
            CENTRO.setImage(image,(int)(temp.getWidth()),(int)(temp.getHeight()));
            CENTRO.repaint();
             CENTRO.revalidate();
        }
    }
        /*riscalamento dell' immagine*/
    private void diminuisci()
    {
        if(image!=null)
        { 
            // ottengo dall'utente i parametri di scala
            double scalaX=0.5;//Double.parseDouble(JOptionPane.showInputDialog("Fattore di scala nella direzione x"));
            double scalaY=0.5;//Double.parseDouble(JOptionPane.showInputDialog("Fattore di scala nella direzione y"));
            // creo la trasformazione di scala
            AffineTransform A=new AffineTransform(scalaX,0,0,scalaY,0,0);
            // creo l'operatore affine che applichi la trasformazione teste' creata
            AffineTransformOp T=new AffineTransformOp(A,3);// il tipo di interpolazioen Bicubica vale 3
            // preparo la nuova immagine
            BufferedImage temp=T.filter(image,null);
            image=temp;
             copiaImmagine(temp, image);
            CENTRO.setImage(image,(int)(temp.getWidth()),(int)(temp.getHeight()));
              //CENTRO.setImage(image,(int)(temp.getWidth()*scalaX),(int)(temp.getHeight()*scalaY));
            CENTRO.repaint();
            CENTRO.revalidate();
        }
    }
    
/*rotazione dell' immagine*/
    private void ruota()
        { 
            if(image!=null)
        { 
            try
            {
        // ottengo dall'utente il parametro di rotazione
           double angolo=Double.parseDouble(JOptionPane.showInputDialog("angolo di rotazione in gradi"));
         // creo la matrice di rotazione

         // passo l'angolo in radianti
          angolo=angolo/360*2*Math.PI;
          double m00=Math.cos(angolo);
          double m01=-Math.sin(angolo);
          double m10=Math.sin(angolo);
          double m11=Math.cos(angolo);

        // qui sorge una complicazione se si vuole effettuare la rotazione
        // intorno al centro della immagine.
        // in tal caso essa va prima traslata in modo che la nuova origine
        // sia il centro.
        // essa va poi ruotata ed infine ritraslata indietro.
        // la classe AffineTransform in realtï¿½ offre un comodo metodo per
        // concatenare le trasformazioni

            // traslazione del centro della immagine nella origine
        int w=image.getWidth();
        int h=image.getHeight();
        AffineTransform T1=new AffineTransform(1,0,0,1,-w/2,-h/2);
        AffineTransform T2=new AffineTransform(1,0,0,1,w/2,h/2);
        AffineTransform A=new AffineTransform(m00,m01,m10,m11,0,0);
        // concateno le tre trasformazioni
        A.concatenate(T1);
        T2.concatenate(A);

        // creo l'operatore affine che applichi la rotazione teste' creata
           AffineTransformOp T=new AffineTransformOp(T2,3);// il tipo di interpolazioen Bicubica vale 3
         // preparo la nuova immagine
            BufferedImage temp=T.filter(image,null);
           image=temp;
           CENTRO.setImage(image,(int)(temp.getWidth()),(int)(temp.getHeight()));
           CENTRO.repaint();
            CENTRO.revalidate();
           }
            catch(Exception e )
            {
            JOptionPane.showMessageDialog(null,"HAI DIGITATO UN CARATTERE ERRATO ","ATTENZIONE",JOptionPane.ERROR_MESSAGE);
            }
        }
    }


    private void shear()
    {
        if(image!=null)
        { 
            try
            {
            // ottengo dall'utente i parametri di shearing
            double shearX=Double.parseDouble(JOptionPane.showInputDialog("Fattore di shear nella direzione x"));
            double shearY=Double.parseDouble(JOptionPane.showInputDialog("Fattore di shear nella direzione y"));
             // creo la trasformazione di shear
            AffineTransform A=new AffineTransform(1,shearX,shearY,1,0,0);
            // creo l'operatore affine che applichi la trasformazione teste' creata
            AffineTransformOp T=new AffineTransformOp(A,3);// il tipo di interpolazioen Bicubica vale 3
             // preparo la nuova image
            BufferedImage temp=T.filter(image,null);
            image=temp;
            CENTRO.setImage(image,(int)(temp.getWidth()),(int)(temp.getHeight()));
            CENTRO.repaint();
            CENTRO.revalidate();
            }
            catch(Exception e )
            {
            JOptionPane.showMessageDialog(null,"HAI DIGITATO UN CARATTERE ERRATO ","ATTENZIONE",JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void operazioneRaster() 
    {
        if(image!=null)
        { 

		OperazRaster opR = new OperazRaster();
		BufferedImage temp=opR.filter(image,image);
		copiaImmagine(temp, image);
		CENTRO.setImage(image);
		CENTRO.repaint();
        }
	}



    
    
 /*_______________________________________________________________________________________
    _______________________________RESCALING_______________________________________________
    _______________________________________________________________________________________*/  
    
    
/*metodo di rescaling dei valori complessivi (tutti i canali) controllando solo il gain*/
    private void onlyGain()
    {  
        if(image!=null)
        {
            rescaler R = new rescaler();
            BufferedImage temp=R.gain(image);
            copiaImmagine(temp, image); // rimetto tutto in una RGBA
            CENTRO.setImage(image);
            CENTRO.repaint();
        }
    }

/*metodo di rescaling dei valori complessivi (tutti i canali)  controllando solo il bias*/
    private void onlyBias()
    {
        if(image!=null)
        {
            rescaler R = new rescaler();
            BufferedImage temp=R.bias(image);
            copiaImmagine(temp, image); // rimetto tutto in una RGBA
            CENTRO.setImage(image);
            CENTRO.repaint();
        }
    }
    
    
    
 /*___________________________________________________________________________________
    _____________________OPERAZIONI DI LUT_______________________________________________
    ___________________________________________________________________________________*/  
    
    
    private void istogrammi()
    {
        if(image!=null)
        { 
            istogramma I = new istogramma(image);
            JFrame frameIsto = new JFrame();
            JPanel pannelloIstoR= new PannelloIsto("red",I.R, Color.RED,25);
            JPanel pannelloIstoG= new PannelloIsto("green", I.G, Color.GREEN,25);
            JPanel pannelloIstoB= new PannelloIsto("blue", I.B, Color.BLUE,25);
            GridLayout grid = new GridLayout(3,1,1,1);
            frameIsto.getContentPane().setLayout(grid);
            frameIsto.getContentPane().add(pannelloIstoR);
            frameIsto.getContentPane().add(pannelloIstoG);
            frameIsto.getContentPane().add(pannelloIstoB);
            frameIsto.setSize(256,500);
            frameIsto.setTitle("istogramma");
            frameIsto.setVisible(true);
        }
    }

    private void istogrammiCumulativi()
    {
        if(image!=null)
        { 
            istogramma I = new istogramma(image);
            JFrame frameIsto = new JFrame();
            JPanel pannelloIstoR= new PannelloIsto("red",I.cumulativoR(), Color.RED,1);
            JPanel pannelloIstoG= new PannelloIsto("green", I.cumulativoG(), Color.GREEN,1);
            JPanel pannelloIstoB= new PannelloIsto("blue", I.cumulativoB(), Color.BLUE,1);
            GridLayout grid = new GridLayout(3,1,1,1);
            frameIsto.getContentPane().setLayout(grid);
            frameIsto.getContentPane().add(pannelloIstoR);
            frameIsto.getContentPane().add(pannelloIstoG);
            frameIsto.getContentPane().add(pannelloIstoB);
            frameIsto.setSize(256,500);
            frameIsto.setTitle("istogramma cumulativo");
            frameIsto.setVisible(true);
        }
    } 
    
    private void invertiR()
    {
        if(image!=null)
        { 
            opPuntuale oP = new opPuntuale();
            BufferedImage temp=oP.invertiRed(image);
            copiaImmagine(temp, image); // rimetto tutto in una RGBA
            CENTRO.setImage(image);
            CENTRO.repaint();
        }
    }

    private void invertiG()
    {
        if(image!=null)
        { 
            opPuntuale oP = new opPuntuale();
            BufferedImage temp=oP.invertiGreen(image);
            copiaImmagine(temp, image); // rimetto tutto in una RGBA
            CENTRO.setImage(image);
            CENTRO.repaint();
        }
    }

    private void invertiB()
    {
        if(image!=null)
        { 
            opPuntuale oP = new opPuntuale();
            BufferedImage temp=oP.invertiBlue(image);
            copiaImmagine(temp, image); // rimetto tutto in una RGBA
            CENTRO.setImage(image);
            CENTRO.repaint();
        }
    }
        
    /**/
    private void invertiAll()
    {
        if(image!=null)
        { 
            opPuntuale oP = new opPuntuale();
            BufferedImage temp=oP.invertiAll(image);
            copiaImmagine(temp, image); // rimetto tutto in una RGBA
            CENTRO.setImage(image);
            CENTRO.repaint();
        }
    }
    
    /*modifica tutti i valori dei colori fino ad un dato valore k e lascia invariati quelli superiori all'indice*/
    private void sogliaUnica()
    {
        if(image!=null)
        { 
            opPuntuale oP = new opPuntuale();
            int k=-1;
            do
            {
                
                
                     k=Integer.parseInt(JOptionPane.showInputDialog("inserire il valore di soglia (0-255)"));
                
                
            }
            while ((k<0)||(k>255));

            BufferedImage temp=oP.sogliaAllChannel(image,k);
            copiaImmagine(temp, image); // rimetto tutto in una RGBA
            CENTRO.setImage(image);
            CENTRO.repaint();
        }
    }
/*modifica i valori dei colori fino ad un dato valore k, con k diverso per ogni colore, lascia invariati quelli superiori all'indice k*/
    private void soglie()
    {
        if(image!=null)
        { 
            opPuntuale oP = new opPuntuale();
            
            int kR=-1, kG=-1, kB=-1;
            //rosso
            do
            {
                kR=Integer.parseInt(JOptionPane.showInputDialog("inserire  soglia rosso (0-255)"));
            }
            while ((kR<0)||(kR>255));
            //verde
            do
            {
                kG=Integer.parseInt(JOptionPane.showInputDialog("inserire soglia verde (0-255)"));
            }
            while ((kG<0)||(kG>255));
            //blu
            do
            {
                kB=Integer.parseInt(JOptionPane.showInputDialog("inserire soglia blue (0-255)"));
            }
            while ((kB<0)||(kB>255));

            BufferedImage temp=oP.sogliaChannels(image,kR,kB,kG);
            copiaImmagine(temp, image); // rimetto tutto in una RGBA
            CENTRO.setImage(image);
            CENTRO.repaint();
        }
    }

    private void incupire()
    {
        if(image!=null)
        { 
        opPuntuale oP = new opPuntuale();
        BufferedImage temp=oP.incupire(image);
        copiaImmagine(temp, image); // rimetto tutto in una RGBA
        CENTRO.setImage(image);
        CENTRO.repaint();
        }
    }

    private void schiarire()
    {
        if(image!=null)
        { 
            opPuntuale oP = new opPuntuale();
            BufferedImage temp=oP.schiarire(image);
            copiaImmagine(temp, image); // rimetto tutto in una RGBA
            CENTRO.setImage(image);
            CENTRO.repaint();
        }
    }

    private void riquantizzaLog()
    {
        if(image!=null)
        { 
            opPuntuale oP = new opPuntuale();
            BufferedImage temp=oP.riquantizzaLog(image);
            copiaImmagine(temp, image); // rimetto tutto in una RGBA
            CENTRO.setImage(image);
            CENTRO.repaint();
        }
    }

    private void riquantizzaLin()
    {
        if(image!=null)
        { 
            opPuntuale oP = new opPuntuale();
            int k=-1;
            do
            {
                k=Integer.parseInt(JOptionPane.showInputDialog("num. livelli (1-20)"));
            }
            while ((k<0)||(k>20));
            BufferedImage temp=oP.riquantizzaLin(image,k);
            copiaImmagine(temp, image); // rimetto tutto in una RGBA
            CENTRO.setImage(image);
            CENTRO.repaint();
        }
    }

    private void randomLUT()
    {
        if(image!=null)
        { 
            opPuntuale oP = new opPuntuale();
            BufferedImage temp=oP.randomLUT(image);
            copiaImmagine(temp, image); // rimetto tutto in una RGBA
            CENTRO.setImage(image);
            CENTRO.repaint();
        }
    }

	private void otherLUT()
    {	
        if(image!=null)
        { 
		    opPuntuale oP = new opPuntuale();
		    BufferedImage temp=oP.otherLUT(image);
		    copiaImmagine(temp, image); // rimetto tutto in una RGBA
		    CENTRO.setImage(image);
		    CENTRO.repaint();
	    }
    }
	


    private void equalizza()
    {
        if(image!=null)
        { 
            opPuntuale oP = new opPuntuale();
            BufferedImage temp=oP.equalizza(image);
            copiaImmagine(temp, image); // rimetto tutto in una RGBA
            CENTRO.setImage(image);
            CENTRO.repaint();
        }
    }
 
        
        
/*=========================================================================================
====================  CONVOLUZIONI ===========================================================
==========================================================================================*/
    private void smoothing()
    {
         int raggio=0;
        
        if(image!=null)
        {  
            try
            {  
                do
                {
                    raggio=Integer.parseInt(JOptionPane.showInputDialog("raggio?"));
                }
                while(raggio<=0);
            ConvolveOp op=new ConvolveOp(new kernelCollection().smoothing(raggio),ConvolveOp.EDGE_NO_OP,null);
            BufferedImage temp1=new BufferedImage(image.getWidth(),image.getHeight(),BufferedImage.TYPE_INT_RGB); //
            copiaImmagine(image,temp1);
            BufferedImage temp2=op.filter(temp1,null);
            copiaImmagine(temp2, image); // rimetto tutto in una RGBA
            CENTRO.setImage(image);
            CENTRO.repaint();
              }
            catch(Exception e)
            {
                //System.out.println("ERRORE DI BATTITURA");
                JOptionPane.showMessageDialog(null,"HAI DIGITATO UN CARATTERE ERRATO ","ATTENZIONE",JOptionPane.ERROR_MESSAGE);
            }   
        }
    }

    private void smoothingLH()
    {
        int raggio=0;
        
        if(image!=null)
        {  
            try
            {  
                do
                {
                    raggio=Integer.parseInt(JOptionPane.showInputDialog("raggio?"));
                }
                while(raggio<=0);
                ConvolveOp op=new ConvolveOp(new kernelCollection().smoothingSuRettaOrizzontale(raggio));
                BufferedImage temp1=new BufferedImage(image.getWidth(),image.getHeight(),BufferedImage.TYPE_INT_RGB); //
                copiaImmagine(image,temp1);
                BufferedImage temp2=op.filter(temp1,null);
                copiaImmagine(temp2, image); // rimetto tutto in una RGBA
                CENTRO.setImage(image);
                CENTRO.repaint();
             }
            catch(Exception e)
            {
                //System.out.println("ERRORE DI BATTITURA");
                JOptionPane.showMessageDialog(null,"HAI DIGITATO UN CARATTERE ERRATO ","ATTENZIONE",JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void smoothingLV()
    {
         int raggio=0;
        
        if(image!=null)
        {  
            try
            {  
                do
                {
                    raggio=Integer.parseInt(JOptionPane.showInputDialog("raggio?"));
                }
                while(raggio<=0);
            ConvolveOp op=new ConvolveOp(new kernelCollection().smoothingSuRettaVerticale(raggio));
            BufferedImage temp1=new BufferedImage(image.getWidth(),image.getHeight(),BufferedImage.TYPE_INT_RGB); //
            copiaImmagine(image,temp1);
            BufferedImage temp2=op.filter(temp1,null);
            copiaImmagine(temp2, image); // rimetto tutto in una RGBA
            CENTRO.setImage(image);
            CENTRO.repaint();
             }
            catch(Exception e)
            {
                //System.out.println("ERRORE DI BATTITURA");
                JOptionPane.showMessageDialog(null,"HAI DIGITATO UN CARATTERE ERRATO ","ATTENZIONE",JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void edgeH()
    {
        if(image!=null)
        { 
            ConvolveOp op=new ConvolveOp(new kernelCollection().latoH());
            BufferedImage temp1=new BufferedImage(image.getWidth(),image.getHeight(),BufferedImage.TYPE_INT_RGB); //
            copiaImmagine(image,temp1);
            BufferedImage temp2=op.filter(temp1,null);
            copiaImmagine(temp2, image); // rimetto tutto in una RGBA
            CENTRO.setImage(image);
            CENTRO.repaint();

        }
    }

    private void edgeV()
    {
        if(image!=null)
        { 
            ConvolveOp op=new ConvolveOp(new kernelCollection().latoV());
            BufferedImage temp1=new BufferedImage(image.getWidth(),image.getHeight(),BufferedImage.TYPE_INT_RGB); //
            copiaImmagine(image,temp1);
            BufferedImage temp2=op.filter(temp1,null);
            copiaImmagine(temp2, image); // rimetto tutto in una RGBA
            CENTRO.setImage(image);
            CENTRO.repaint();
        }
    }

	private void otherConvolve()
    {  int dim=0;
        
        if(image!=null)
        {  
            try
            {  
                do
                {
                    dim=Integer.parseInt(JOptionPane.showInputDialog("Dimensione del filtro?"));
                }
                while(dim<=0);
            ConvolveOp op=new ConvolveOp(kernelCollection.otherFilter(dim) );
            //RICORDATI DI CREARE IL FILTRO COME TI SERVE PER L'ESAME!!!
            
            BufferedImage temp1=new BufferedImage(image.getWidth(),image.getHeight(),BufferedImage.TYPE_INT_RGB); 
            copiaImmagine(image,temp1);
            BufferedImage temp2=op.filter(temp1,null);
            copiaImmagine(temp2, image); // rimetto tutto in una RGBA
            CENTRO.setImage(image);
            CENTRO.repaint();
                }
            catch(Exception e)
            {
                //System.out.println("ERRORE DI BATTITURA");
                JOptionPane.showMessageDialog(null,"HAI DIGITATO UN CARATTERE ERRATO ","ATTENZIONE",JOptionPane.ERROR_MESSAGE);
            }
	    }
    }
    
    private void nuovaConvoluzione()
    { 
        int dim=0;
        
        if(image!=null)
        {  
            try
            {  
                do
                {
                    dim=Integer.parseInt(JOptionPane.showInputDialog("Inserisci le dimensione del filtro"));
                }
                while(dim<=0);
           
            ConvolveOp op=new ConvolveOp(kernelCollection.nuovoFiltro(dim) );
            //RICORDATI DI CREARE IL FILTRO COME TI SERVE PER L'ESAME!!!
            
            BufferedImage temp1=new BufferedImage(image.getWidth(),image.getHeight(),BufferedImage.TYPE_INT_RGB); 
            copiaImmagine(image,temp1);
            BufferedImage temp2=op.filter(temp1,null);
            copiaImmagine(temp2, image); // rimetto tutto in una RGBA
            CENTRO.setImage(image);
            CENTRO.repaint();
                }
            catch(Exception e)
            {
                //System.out.println("ERRORE DI BATTITURA");
                JOptionPane.showMessageDialog(null,"HAI DIGITATO UN CARATTERE ERRATO ","WARNING",JOptionPane.ERROR_MESSAGE);
            }
	    }
    }
/*_________________________FINE CONVOLUZIONI______________________________________*/
      
    
    
    
/*_____________________________________FINE FRAME_______________________________________________*/   
}
/*_____________________________________FINE FRAME______________________________________________*/   
 