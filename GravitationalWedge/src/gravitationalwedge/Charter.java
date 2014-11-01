/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gravitationalwedge;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Edward
 */
public class Charter extends JPanel{
    double xAxisMin, xAxisMax, xScale;
    double yAxisMin, yAxisMax, yScale;
    double offset = 20;
    double densityMax = -1;
    int size;
    int hashes = 6;
    
    ArrayList<Double> xValues, yValues,densityValues;
    
    public Charter(){this(2);}
    
    public Charter(int s){
         this.setLayout(null);
         size=s;
         xValues=new ArrayList<>();
         yValues=new ArrayList<>();
         densityValues = new ArrayList<>();
         

    }
    
    public void setBounds(double xmin,double xmax,double ymin,double ymax){
        
        xAxisMin = xmin;
        xAxisMax = xmax;
        yAxisMax = ymax;
        yAxisMin = ymin;
        
        rescale();
        
    }
    
    public void addPoint(double x,double y){
       addPoint(x,y,0.0);
    }
    public void addPoint(double x,double y,double density){
        boolean b=false;
        
        if(x>xAxisMax){
            xAxisMax=x;
            b=true;
        }
        if(x<xAxisMin){
            xAxisMin=x;
            b=true;
        }
        if(y>yAxisMax){
            yAxisMax=y;
            b=true;
        }
        if(y<yAxisMin){
            yAxisMin=y;
            b=true;
        }
        
        if(b)rescale();
        
        xValues.add(x);
        yValues.add(y);
        densityValues.add(density);
        
        if(density>densityMax){
            densityMax = density;
        }
        
        /*if(xValues.size()>30000){
            xValues.remove(0);
            yValues.remove(0);
            //scale();
        }*/
        
        repaint();
    }
    
    public void toggleColor(double max){
        densityMax = max;
    }
    
    public void scale(){
        double tempx1=Double.MAX_VALUE,tempx2=Double.MIN_VALUE,tempy1=Double.MAX_VALUE,tempy2=Double.MIN_VALUE;
        
        for(int i=0;i<xValues.size();i++){
            if(xValues.get(i)>tempx2){
                tempx2=xValues.get(i);
            }
            if(xValues.get(i)<tempx1){
                tempx1=xValues.get(i);
            }
            if(yValues.get(i)>tempy2){
                tempy2=yValues.get(i);
            }
            if(yValues.get(i)<tempy1){
                tempy1=yValues.get(i);
            }
        }
        
    }
    
    
    public void paintComponent(Graphics g1){
        //Graphics2D g2=(Graphics2D) g;
        //super.paintComponent(g);
        Graphics2D g=(Graphics2D)g1;
        g.setColor(Color.BLUE);
        
        for(int i=0;i<xValues.size();i++){
        //while(xValues.size()!=0){
            if(densityMax >= 0){
                g.setColor(new Color((float)(densityValues.get(i)/densityMax),(float)(1.0-densityValues.get(i)/densityMax),0.0f));
                //g.setColor(new Color((float)(densityValues.get(i)/densityMax),(float)(1-densityValues.get(i)/densityMax),0.0f));
            }
            g.fillOval((int)(Math.round((xValues.get(i)-xAxisMin)*xScale))+20,(this.getHeight()-20-(int)(Math.round((yValues.get(i)-yAxisMin)*yScale))),size,size);
            //System.out.println(xValues.get(i)+" "+yValues.get(i));
            
        }
        
        g.setColor(Color.BLACK);
        int yAxisLoc = (int)((this.getWidth()-40)*-xAxisMin/(xAxisMax-xAxisMin));
        int xAxisLoc = (int)((this.getHeight()-40)*-yAxisMin/(yAxisMax-yAxisMin));
        
        if(xAxisMin<=0&&xAxisMax>=0)g.drawLine(20+yAxisLoc,0,20+yAxisLoc,this.getHeight());
        if(yAxisMin<=0&&yAxisMax>=0)g.drawLine(0,20+xAxisLoc,this.getWidth(),20+xAxisLoc);
        
        //hashes along vertical axis
        //g.rotate(Math.PI/2);
        if(yAxisMax-yAxisMin>0)
        for(int i=0;i<hashes;i++){
            g.drawString(String.format("%6.2E",(yAxisMax-yAxisMin)*i/(hashes-1)+yAxisMin),20+yAxisLoc-5,(int)this.getHeight()-20-((this.getHeight()-40)*i/(hashes-1)));
            g.drawLine(20+yAxisLoc-5,(int)((this.getHeight()-40)*i/(hashes-1))+20,20+yAxisLoc+5,(int)((this.getHeight()-40)*i/(hashes-1))+20);
        }
        //g.rotate(-Math.PI/2);
        if(xAxisMax-xAxisMin>0)
        for(int i=0;i<hashes;i++){
            g.drawString(String.format("%6.2E",(xAxisMax-xAxisMin)*i/(hashes-1)+xAxisMin),((this.getWidth()-40)*i/(hashes-1))+20,20+xAxisLoc-5);
            g.drawLine((int)((this.getWidth()-40)*i/(hashes-1))+20,20+xAxisLoc-5,(int)((this.getWidth()-40)*i/(hashes-1))+20,20+xAxisLoc+5);
        }
        
    }
    
    
    
    public void rescale(){
        xScale=((double)(this.getWidth()-2*offset)/(xAxisMax-xAxisMin));
        yScale=((double)(this.getHeight()-2*offset)/(yAxisMax-yAxisMin));
    }
    
    public int[] density(int size,boolean bAxis){
        int[] particle_densities=new int[size];
        for(int i=0;i<xValues.size();i++){
            double temp = bAxis?xValues.get(i):yValues.get(i);
            temp-= bAxis?xAxisMin:yAxisMin;
            temp/= bAxis?(xAxisMax-xAxisMin)/size:(yAxisMax-yAxisMin)/size;
            particle_densities[(int)temp]++;
        }
        return particle_densities;
        
        
        
    }
   
    
    
}

