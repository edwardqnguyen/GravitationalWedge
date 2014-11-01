/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gravitationalwedge;


/**
 * @author Edward
 */
public class AsymBoundary implements Boundary{
    double amp1,shift1;
    double amp2,shift2;
    double accel;
    
    public AsymBoundary(double x1,double x2,double y1,double y2,double acc){
        amp1=x1;
        shift1=x2;
        amp2=y1;
        shift2=y2;
        accel=acc;
    }
    @Override
    public double nextBounce(Particle p){
        double a,b,c;
        
        a=.5*accel + amp1*p.vel_X*p.vel_X;
        b=p.vel_Y+amp1*2*p.vel_X*shift1+amp1*2*p.pos_X*p.vel_X;
        c=amp1*p.pos_X*p.pos_X+2*amp1*p.pos_X*shift1+p.pos_Y;
        
        double t=quadraticSolve(a,b,c);
        if(t>0&&inBounds(p,t))return t;
        
            a=.5*accel + amp2*p.vel_X*p.vel_X;
            b=p.vel_Y-amp2*2*p.vel_X*shift2+amp2*2*p.pos_X*p.vel_X;
            c=amp2*p.pos_X*p.pos_X-2*amp2*p.pos_X*shift2+p.pos_Y;
        
        t=quadraticSolve(a,b,c);
        if(t>0&&inBounds(p,t))return t;
        
        return -1;
        
        
    }
    public double quadraticSolve(double a,double b,double c){
        double discriminant=b*b-4*a*c;
        if(discriminant<0)return -1;
        
        if(a==0)return -b/c<0?-1:-b/c;
        
        double sol1,sol2;
        
        sol1=(-b+Math.sqrt(discriminant))/(2*a);
        sol2=(-b-Math.sqrt(discriminant))/(2*a);
        
        double acc = 0.000000001;
        
        
        if(sol2<=acc && sol1<=acc)return -1;
        if(sol2<=acc && sol1>acc)return sol1;
        if(sol2>acc  && sol1<=acc )return sol2;
        if(sol2>acc  && sol1>acc )return sol2<sol1?sol2:sol1;
        return -1;
        
    }
    public boolean inBounds(Particle p,double time){
       
        double pos_X2=p.pos_X+p.vel_X*time;
        double pos_Y2=p.pos_Y+p.vel_Y*time+.5*accel*time*time;
        
        if(pos_X2>shift2||pos_X2<-shift1||pos_Y2<0||pos_Y2-function(pos_X2)<-.000000001)return false;
        return true;
        
    }
    @Override
    public double[] normal(double x){
        double[] ret = new double[2];
        ret[0] = 2*(x<0?amp1*(x+shift1):amp2*(x-shift2));
        ret[1] = 1;
        
        double mag = Math.sqrt(ret[0]*ret[0]+ret[1]*ret[1]);
        ret[0]/= mag;
        ret[1]/= mag;
        
        return ret;
    }
    @Override
    public double function(double x){
        if(x<0){
            return -1*amp1*(x+shift1)*(x+shift1)+amp1*shift1*shift1;
        }
        if(x == 0)return 0;
        else return -1*amp2*(x-shift2)*(x-shift2)+amp2*shift2*shift2;
    }
    
}
