/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gravitationalwedge;

import Boundaries.QuadraticBoundary;
import Boundaries.Boundary;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Sheet;

/**
 *
 * @author Edward
 */
public class Main {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        //GravitationalWedgeUI gui=new GravitationalWedgeUI();
        String sBase="Patterns";
        Boundary boun=new QuadraticBoundary(10,5,-10);
        Workbook wb=new HSSFWorkbook();
        FileOutputStream fs=new FileOutputStream("Test2.xls");
        
        CreationHelper createHelper = wb.getCreationHelper();
        
        for(int i=0;i<30;i++){
            /*System.out.println(i);
            Sheet s=(Sheet) wb.createSheet("sheet "+i);
            Particle p=new Particle(-1,90,Math.sqrt(800),Math.sqrt(.125),-10,boun);
            p.vel_X+=i;
            DataGenerator dg=new DataGenerator(sBase+i+".dat");
            dg.generate(p,1000);
            
            SurfaceSectionDataWriter ssdw=new SurfaceSectionDataWriter(sBase+i+"Translated.dat",0);
            ssdw.translate(sBase+i+".dat");
            ssdw.stopSaving();
            
            Scanner sc=new Scanner(new File(ssdw.name));
            int j=0;
            while(sc.hasNext()){
                Row r=s.createRow(j);
                r.createCell(0).setCellValue(sc.nextDouble());
                r.createCell(1).setCellValue(sc.nextDouble());
                j++;
            }*/
            //Density dens=new Density(10,10,sBase+i+"Translated.dat");
        }
        wb.write(fs);
        fs.close();
        /*SurfaceSectionDataWriter ssdw=new SurfaceSectionDataWriter("trial4Translated.dat",0);
        ssdw.translate("trial4.dat");
        ssdw.stopSaving();*/    


    }
}
