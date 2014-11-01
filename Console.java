 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gravitationalwedge;

import java.io.IOException;
import javax.swing.*;
import java.io.*;
import java.util.*;

/**
 *
 * @author Edward
 */
public class Console {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException
    {
        //ParticleDensityScript(new Particle(2,100,100,0,-10,new QuadraticBoundary(.001,866.025404,-10)),.0001,3000);
        /*for(int i=1;i<=9;i++){
            //ParticleDensityScript(new Particle(.01,5,1*Math.cos(Math.PI*2/9*i),1*Math.sin(Math.PI*2/9*i),-10,new ParabolicBoundary(.05,-10)),.001,3000);
            
            ParticleDensityScript(new Particle(.1,5,-1,0,-10,new QuadraticBoundary(1,1/Math.tan(Math.PI*i/24),-10)),.0001,3000);
          //ParticleDensityScript(new Particle(.1,2,-2*i,0,-10,new WedgeBoundary(1/Math.tan(Math.PI*(90.0)/360),-10,10,20)),.0001,3000);
        }*/
        //ParticleDensityScript(new Particle(2,100,-100,0,-10,new WedgeBoundary(1/Math.tan(90*Math.PI/360),-10,800,500)),.0001,3000);
       
        
        Boundary b;
        Particle p;
        
        double[][] quadData = new double[20][20];
        for(int i=1;i<=20;i++)
        {
            for(int j=1;j<=20;j++){
                int i1 = 10*20+i*10,j1=j*10;
                b = new QuadraticBoundary(i1,j1,-1);
                p = new Particle(0,5,3,0,-1,b);//dummy particle so compiler doesn't complain
                double temp = i1*j1*j1;
                double average = 0;
                here: for(int k=0;k<50;k+=1){
                    double d1=0,d2=0;
                    System.out.print(k+" ");
                    do
                    {
                        d1 =
                            Math.random()*temp;
                        d2 =
                            Math.random()*j1;
                    }
                    while(b.function(d2)>d1);
                    for(int l = 0;l<360;l+=4){
                        p=new Particle(d2,d1,Math.sqrt(2*(temp-d1))*Math.cos(2*l*Math.PI/180),Math.sqrt(2*(temp-d1))*Math.sin(2*l*Math.PI/180),-1,b);
                        double kTemp = PercentageScript(p,.01,1000,.05);

                        if(kTemp>=0){average+=kTemp;}
                        else{
                            k--;continue here;
                        }
                    }    
                    
                }
                quadData[i-1][j-1]+=average/90/50;
                PrintWriter pw = new PrintWriter("QuadraticDataDump2.in");
                for(double a=0;a<20;a++)
                {
                    for(double c=0;c<20;c++){
                        pw.println((a/2+10.5)+" "+(c/2)+" "+quadData[(int)a][(int)c]);
                    }
                }
                pw.close();
                System.out.println();
            }
            System.out.println();
        }
        
        for(double i=0;i<20;i++)
        {
            for(double j=0;j<20;j++){
                System.out.println((i/2+10.5)+" "+(j/2)+" "+quadData[(int)i][(int)j]);
            }
        }
        //Wedge Boundary Broad search.
        /*for(double d12 = 0.5;d12<90;d12+=.5)
        {
            b = 
                    //new WedgeBoundary(Math.tan((90-d/2)*Math.PI/180),-1);
                    new WedgeBoundary(Math.tan(d12*Math.PI/180),-1);
                    //new ParabolicBoundary(.22783,-1);
                    //new QuadraticBoundary(.205635,8,-1);
                    //new QuadraticBoundary(.03125,16*Math.sqrt(3),-1);
                    //new QuadraticBoundary(.025,20,-1);
            p = 
                       //new Particle(0,b.function(5),3*Math.cos((d)*Math.PI/180),3*Math.sin((d)*Math.PI/180),-1,b);
                       new Particle(0,5,3,0,-1,b);
            double k = 0;
            here: for(int j=0;j<50;j++)
            {
                System.out.print(j+" ");
                double d1 = 
                        Math.random()*24;
                        //Math.random()*b.getHeight()*b.getWidth()*b.getWidth();
                
                double d2 =
                        Math.random()*24/b.function(1)/1.1;
                        //Math.random()*Math.sqrt(24/b.function(1));
                        //Math.random()*b.getWidth();
                
                while(b.function(d2)>d1)
                {
                    
                    d1 = 
                            Math.random()*24;
                            //Math.random()*b.getHeight()*b.getWidth()*b.getWidth();
                    d2 =   
                            Math.random()*24/b.function(1)/1.1;
                            //Math.random()*Math.sqrt(24/b.function(1));
                            //Math.random()*b.getWidth();
                }
                //System.out.println(d1+" "+d2);
                double kt=0;
                for(int i=0;i<180;i+=2)
                {
                    if(i==45||i==135)continue;
                    double temp = 
                            24;
                            //b.getHeight()*b.getWidth()*b.getWidth();
                    p=new Particle(d2,d1,Math.sqrt(2*(temp-d1))*Math.cos(2*i*Math.PI/180),Math.sqrt(2*(temp-d1))*Math.sin(2*i*Math.PI/180),-1,b);
                    double kTemp = PercentageScript(p,.01,1500,.05);
                    
                    if(kTemp>=0){kt+=kTemp;}
                    else{
                        j--;continue here;
                    }
                    //System.out.print(i+" ");
                }
                k+=kt;
            }
            System.out.println(180-(2*d12)+" "+k/178/50);
            //SurfaceSectionScript(p,100000);
            
        }*/
        
        //CumKinEnergyScript();
        
        /*int i=9,j=36;
        double i1= i/(1.0*i*j*j);
                double j1= j/(1.0*i*j*j);
                Boundary b = 
                        new QuadraticBoundary(i,j,-1);
               //SurfaceSectionScript( new Particle(0,i1*j1*j1*20/100,Math.sqrt(2.0*(100.0-20)/100*i1*j1*j1),0,-1,b),1000000);
                        //new WedgeBoundary(1,-1);
                double[] percentages = new double[100];
                for(int a=1;a<100;a++){
                    double angle = Math.PI/7;
                    Particle p = 
                            new Particle(0,i*j*j*a/100.0,Math.sqrt(2.0*(100.0-a)/100*i*j*j)*Math.cos(angle),Math.sqrt(2.0*(100.0-a)/100*i*j*j)*Math.sin(angle),-1,b);
                            //new Particle(0,a,Math.sqrt(2*(100-a)),0,-1,b);
                    /*if(a==20||a==50||a==80){
                        Particle pTemp = 
                                //new Particle(0,i1*j1*j1*a/100,Math.sqrt(2.0*(100.0-a)/100*i1*j1*j1),0,-1,b);
                                new Particle(0,a,Math.sqrt(2*(100-a)),0,-1,b);
                        ParticleDensityScript(pTemp,.01,100000);
                        pTemp = 
                                //new Particle(0,i1*j1*j1*a/100,Math.sqrt(2.0*(100.0-a)/100*i1*j1*j1),0,-1,b);
                                new Particle(0,a,Math.sqrt(2*(100-a)),0,-1,b);
                        SurfaceSectionScript(pTemp,100000);
                    }*/
                    /*percentages[a] = PercentageScript(p,.01,100000,.05);
                    System.out.println(a+" "+percentages[a]);
                    /*p = 
                            new Particle(0,i1*j1*j1*a/100,Math.sqrt(2.0*(100.0-a)/100*i1*j1*j1),0,-1,b);*/
                   /* double k = //PercentageScript(p,.01,10000000,.05);
                            percentages[a];
                    //if(k<.01)
                    {
                        p = 
                            new Particle(0,i1*j1*j1*a/100,Math.sqrt(2.0*(100.0-a)/100*i1*j1*j1),0,-1,b);
                    //SurfaceSectionScript(p,10000);
                    }
                        
                }
                //StandardDeviationScript(percentages,30,0,1);
                
                //double k = PercentageScript(p,.01,10000000,.05);
                //SurfaceSectionScript(p,1000000);
                //p = new Particle(0,i1*j1*j1*.8,Math.sqrt(.4*i1*j1*j1),0,-1,b);
                //SurfaceSectionScript(p,1000000);
                
       
       //System.out.println(i1+" "+j1+" "+k);
        
        //double d1=10.0*14/(1.0*14*14*4);
        //double d2=10.0*4/(1.0*14*14*4);
        //Boundary b = 
                //new QuadraticBoundary(d1,d2,-1);
                //new WedgeBoundary(1/Math.tan(40*Math.PI/360),-10,10,10);
                //new WedgeBoundary(1/Math.tan(90*Math.PI/360),-1,10,10);
                //new ParabolicBoundary(.05,-10);
       //Particle p = new Particle(-.5*d2,d1*d2*d2*.1,Math.sqrt(1.8*d1*d2*d2),0,-1,b);
       //ParticleDensityScript(p,.01,10000);
       //System.out.println(PercentageScript(p,.01,1000,.05)); 
       
       
       
       
        /*double max = 0;
        int imax = 0;
        for(int i=30;i<85;i++){
            if(i%10==0)System.out.println(i);
            //Boundary b = new WedgeBoundary(1.0/Math.tan(i*Math.PI/180),-1);
            //Boundary b = new ParabolicBoundary(i*1.0/100,-1);
            Particle p = new Particle(-.2,1/(i*4.0/100),-1,0,-1,b);
            double k = PercentageScript(p,.01,10000,.05);
            if(k>max){max=k;imax=i;}
        }
        System.out.println(imax+" "+max);*/
        
        /*double max = 0;
        int imax=0,jmax=0;
        for(int i=1;i<50;i++){
            System.out.print(i);
            for(int j=1;j<50;j++){
                if(j%10==0)System.out.print(j+" ");
                double i1= 10.0*i/(1.0*i*j*j);
                double j1= 10.0*j/(1.0*i*j*j);
                Boundary b = new QuadraticBoundary(i1,j1,-1);
                Particle p = new Particle(0,i1*j1*j1*.1,Math.sqrt(1.8*i1*j1*j1),0,-1,b);
                double k = PercentageScript(p,.1,10000000,.05);
                if(k>max){max=k;imax=i;jmax=j;}
                
                //System.out.printf(i+": %.2f%%%n",PercentageScript(p,.01,30000,.05)*100);
            }
            System.out.println(imax+" "+jmax+" "+max*100);
            System.out.println();
            
        }
        System.out.println(imax+" "+jmax+" "+max*100);*/
        
        //Particle pClone = new Particle(p);
        //ParticleDensityScript(p,.001,3000);
        //SurfaceSectionScript(pClone,10000);
        //CumKinEnergyScript();to
        /*double[] data = new double[100];
        for(int i=0;i<data.length;i++){
           data[i]=10;
        }
        StandardDeviationScript(data,20,0,20);*/
        
    }
    /***PercentageScript***
     * Returns a value representing the percentage of time the particle spends
     * at some fraction of the original particles energy. The particle will have
     * its position incremented by some amount and if its energy kinetic
     * energy is below some threshold of its total energy the time will be added
     * to a counter. This will be used as the basis for the percentage of
     * particles one can expect to be at this threshold.
     */
    public static double PercentageScript(Particle p,double inc, double timeMax,double fraction)throws IOException
    {
        double lowEnergyTime = 0;
        double prevTime = -1;
        double prevX = -1,prevY=-1,initvx=p.vel_X,initvy=p.vel_Y;
        int problemCounter = 0;
        //int percent = 0; //Allows for progress tracking during runtime
        while(p.time<timeMax){
            //if(Math.random()<.0000001)System.out.println(p.time+" "+p.pos_X+" "+p.pos_Y+" ");
            if(Math.abs(prevX-p.pos_X)<.0000001&&Math.abs(prevY-p.pos_Y)<.0000001)
            {
                problemCounter++;
                if(problemCounter==1000){
                    System.out.print("debug");
                    return -1;
                }
            }
            else problemCounter = 0;
            
            
            
            prevX = p.pos_X;prevY=p.pos_Y;
            
            double val = inc;
            //if(p.time/timeMax*100>percent)System.out.println(++percent+"%");
            prevTime = p.time;
            if(p.b.nextBounce(p)>inc){
                p.timeUpdate(inc);
            }
            else{
                
                val = p.b.nextBounce(p);
               // System.out.println(val);
               p.timeUpdate(val);
                //System.out.println(p);
                if(!p.bounceUpdate(0))System.out.print("p");//Method returns false if there's any issue.
                //if(p.b.nextBounce(p)<=0)System.out.println(p);
                //System.out.println(p);
                //pause.next();
              
                
            }
            if(.5*(p.vel_X*p.vel_X+p.vel_Y*p.vel_Y)<=fraction*p.energy()){
                lowEnergyTime+=val;
            }
        }
        return lowEnergyTime/timeMax;
    }
    
    /***ParticleDensityScript***
    * Performs a simulation where the particle bounces around the well for some 
    * period of time and analyzes how much time the particle spent in various 
    * places. This is done using coarse graining. The
    * area is split into squares and the particle is advanced in increments.
    * Whichever square the particle was in gets the time that has passed.
    * 
    */
    public static void ParticleDensityScript(Particle p,double inc,double timeMax)throws IOException
    {
        int h = 100,w = 100;//height and width of display window.
        
        Scanner pause = new Scanner(System.in);//debugging purposes
        
        double[][] grain = new double[h][w];//coarse graining matrix
        int percent = 0; //Allows for progress tracking during runtime
        while(p.time<timeMax){
            if(p.time/timeMax*100>percent)System.out.println(++percent+"%");
            double val = inc;
            //if(p.b.nextBounce(p)<0)System.out.println(p);
            //if(percent==17)System.out.println(p);
            //System.out.println(p.b.nextBounce(p));
            
            //If the time for the next bounce is less than the increment time
            //change the increment time to the next bounce time.
            if(p.b.nextBounce(p)>inc){
                p.timeUpdate(inc);
            }
            else{
                
                val = p.b.nextBounce(p);
                p.timeUpdate(val);
                //System.out.println(p);
                if(!p.bounceUpdate(0))System.out.println("problem");//Method returns false if there's any issue.
                if(p.b.nextBounce(p)<=0)System.out.println(p);
                //System.out.println(p);
                //pause.next();
              
                
            }
            
            //System.out.println(p.pos_X+" "+p.pos_Y);
            //if(percent==75)System.out.println(p.time);
            //check
            /*
            This section sets up coarse graining to properly zoom in on the
            wedge portion
            */
            double x=0,y=0;
            if(p.b instanceof QuadraticBoundary)
            {
                //x = ((p.pos_X/p.b.getWidth())+1)/2; y = (p.pos_Y)/(p.b.getHeight()*p.b.getWidth()*p.b.getWidth());
                x=((p.pos_X/10)+1)/2;y=(p.pos_Y)/10;
            }
            
            else{
             x = (p.pos_X/p.b.getWidth()+1)/2; y = p.pos_Y/p.b.getHeight();
            }
            
            int ix = (int)(x*h),iy=(int)(y*w);
            //if(p.vel_X*p.vel_X+p.vel_Y*p.vel_Y<.4*p.energy())
            if(ix<grain.length&&iy<grain[0].length&&ix>=0&&iy>=0){
                grain[ix][iy]+=val;  
            }
            //else System.out.println(ix+" "+iy);
                  
        }
        
        JFrame jF = new JFrame();
        Charter c = new Charter(5);
        
        c.toggleColor(4);
        //c.setBounds(0, h, 0, w);
        double accum = 0, accum2=0;
        //Inputs grain times.
        //LinkedList<Double> times = new LinkedList<Double>();
        for(int i=0;i<grain.length;i++){
            accum2=0;
            for(int j=0;j<grain[i].length;j++){
                c.addPoint((double)i,(double)j,grain[i][j]);
                accum+=grain[i][j];
                accum2+=grain[i][j];
                //System.out.printf("%-4.1f ",grain[j][i]);
            }
            System.out.println(accum2);
            System.out.println();
        }
        
       
        
        System.out.println(accum);
        
        c.setSize(400,400);
        c.rescale();
        System.out.println(c.getWidth()+" "+c.getHeight());
        System.out.println(c.xAxisMin+" "+c.xAxisMax+" "+c.yAxisMin+" "+c.yAxisMax+" "+c.xScale+" "+c.yScale);
        
        
        
        jF.getContentPane().add(c);
        jF.setSize(400, 400);
        jF.setVisible(true);
        
    }
    
    public static void StandardDeviationScript(double[] data,int buckets,double min,double max){
        double[] data2 = new double[buckets];
        for(double d:data){
            if(d>=min&&d<max){
                data2[(int)((d-min)/(max-min)*buckets)]++;
            }
        }
        double average = 0;
        for(int i=0;i<data2.length;i++){
            average+=data2[i]*(min+(max-min)/buckets*i);
        }
        average/=data.length;
        
        double sigma = 0;
        for(int i=0;i<data2.length;i++){
            double a = (min+(max-min)/buckets*i);
            sigma+=data2[i]*(a-average)*(a-average);
        }
        sigma/=data.length;
        sigma=Math.sqrt(sigma);
        
        System.out.println(average+" "+sigma);
        
    } 
    
    /***SurfaceSectionScript**
     * 
     * @param p - Particle
     * @param time - Time that the particle runs in.
     * @throws IOException - Use of classes requires IOException to be thrown.
     */
   public static void SurfaceSectionScript(Particle p,double time)throws IOException{
        JFrame jF = new JFrame();
        Charter c = new Charter(5);
        
        int percent = 0;
        while(p.time<time){
            //if(p.time/time*100>percent)System.out.println(++percent+"%");
            p.bounceUpdate(p.b.nextBounce(p));
            Particle temp = new Particle(p);
            if(p.pos_X<0^p.vel_X<0&&temp.timeUpdate(-1*p.pos_X/p.vel_X)){
                //Normal Surface of section
                //c.addPoint(temp.pos_Y,temp.vel_Y);
                
                //Radial vs Radial Velocity Surface of section.
                double mag = Math.sqrt(temp.pos_X*temp.pos_X+temp.pos_Y*temp.pos_Y);
                double tx = temp.pos_X/mag,ty = temp.pos_Y/mag;
                double radVel = tx*temp.vel_X+ty*temp.vel_Y;
                double radPos = mag;
                System.out.println(temp.pos_Y+" "+temp.vel_Y);
                
                c.addPoint(radPos,radVel);
            }
            
        }
        c.setSize(500,500);
        c.rescale();
        jF.getContentPane().add(c);
        jF.setSize(500, 500);
        //jF.setVisible(true);
   }
    public static void CumKinEnergyScript()throws IOException
    {
        //final Particle[][] pMat = new Particle[1000][1000];
        
        Boundary b = 
                    //new WedgeBoundary(Math.tan((90-d/2)*Math.PI/180),-1);
                    //new WedgeBoundary(Math.sqrt(3),-1);
                    new ParabolicBoundary(.22783,-1);
                    //new QuadraticBoundary(.205635,8,-1);
                    //new QuadraticBoundary(.03125,16*Math.sqrt(3),-1);
        final Particle[][] pMat = {{new Particle(0,Math.tan(60)*5,3,0,-1,b)}};
        final double[][] KinEnergy = new double[1][1];
            /*for(int i=0;i<pMat.length;i++){
                for(int j=0;j<pMat[0].length;j++){
                    pMat[i][j]=new Particle(0,.5,1.0001,0,-10,new QuadraticBoundary(j+1,i+1,-10));
                }
            }*/
        
        int timeMax = 300;
        //for(int t=0;t<timeMax;t++){
        while(pMat[0][0].time<timeMax){
            /*if(t%(timeMax/100)==0)System.out.println(t/(timeMax/100));
            for(int i=0;i<pMat.length;i++){
                for(int j=0;j<pMat[i].length;j++){
                    Particle p = pMat[i][j];
                    double tempTime = p.time;
                    double timeUp=p.b.nextBounce(p);
                    KinEnergy[i][j] += -(1/3/-10*Math.pow(p.vel_Y,3))
                    +(p.vel_X*p.vel_X*(timeUp)+1/3/-10*Math.pow(p.vel_Y+-10*(timeUp),3));
                    p.bounceUpdate(timeUp);
                }
            }*/
            Particle p = pMat[0][0];
                    double tempTime = p.time;
                    double timeUp=p.b.nextBounce(p);
                    KinEnergy[0][0] += -(1/3/-10*Math.pow(p.vel_Y,3))
                    +(p.vel_X*p.vel_X*(timeUp)+1/3/-10*Math.pow(p.vel_Y+-10*(timeUp),3));
                    p.bounceUpdate(timeUp);
            System.out.println(p.time+" "+KinEnergy[0][0]/pMat[0][0].time/pMat[0][0].energy());
        }
        
        int xM=0,yM=0,xMin=0,yMin=0;
        
        /*for(int i=0;i<pMat.length;i++){
            for(int j=0;j<pMat[i].length;j++){
                double dCMax=KinEnergy[xM][yM]/pMat[xM][yM].time/pMat[xM][yM].energy(),
                        dCMin=KinEnergy[xMin][yMin]/pMat[xMin][yMin].time/pMat[xMin][yMin].energy(),
                        dC=KinEnergy[i][j]/pMat[i][j].time/pMat[i][j].energy();
                
               if(dC<0){System.out.println(i+" "+j+" "+pMat[i][j]);continue;}
                
                if(dC>dCMax)
                {xM=i;yM=j;}
                if(dC<dCMin)
                {xMin=i;yMin=j;}
                //System.out.println((i+1)+" "+(j+1)+" "+KinEnergy[i][j]/pMat[i][j].time/pMat[i][j].energy());
            }
        }
        
        System.out.println("Max: shift = "+(xM+1)+" amplitude = "+(yM+1)+" "+KinEnergy[xM][yM]/pMat[xM][yM].time/pMat[xM][yM].energy());
        System.out.println("Min: shift = "+(xMin+1)+" amplitude = "+(yMin+1)+" "+KinEnergy[xMin][yMin]/pMat[xMin][yMin].time/pMat[xMin][yMin].energy());
        */
        /*for(int i=0;i<6;i++){
            System.out.println(pMat[i][4].b.nextBounce(pMat[i][4]));
        }*/
    }
    
}
