/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weatherdata;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hasitha Iroshana
 */
public class Node extends Thread{
    CreateData data;
    int temp;
    int hum;
    int speed;
    int fall;
    int israin;
    NewJFrame frame;
    int location;
    int sleepTime;
    
    public Node(int i,NewJFrame frame){
        data = new CreateData(i);
        temp=(int)(28 + (Math.random() * 10));
        hum=0;
        speed=0;
        fall=0;
        israin=0;
        this.frame=frame;
        location = i;
        sleepTime = 1000;
    }
    
    
    
    @Override
    public void run(){
        
        
        while(true){
            
            data.next();

            String link = "http://digitalthings.comlu.com/weather/dataset1.php?temp="+data.temp+
                     "&hum="+data.humidity+ "&wind_speed="+data.windSpeed+ "&current_rainfall="+
                    data.rainfall+"&is_raining="+data.rainning+"&location="+
                    data.location;
            
            
            temp =data.temp;
            hum=data.humidity;
            speed=data.windSpeed;
            fall = data.rainfall;
            israin = data.rainning;
            
            frame.setGuiData(location, temp, hum, speed, israin, fall);
            
            
            System.out.println(link);

            try {
                URL url = new URL(link);

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                frame.setStatus(location, 1);//sent
                 
                BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line;
                line = rd.readLine();
                rd.close();
                System.out.println(line);
                
                
                if(line.equals("1")){
                    frame.setStatus(location, 2);//succuss
                }else{
                    frame.setStatus(location, -2);//data error
                }
                
            } catch (MalformedURLException ex) {
                frame.setStatus(location, 0); //url error
                Logger.getLogger(Node.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                frame.setStatus(location, -1);//connetion error
                Logger.getLogger(Node.class.getName()).log(Level.SEVERE, null, ex);
            }





            try {
                sleepTime = frame.getSleepTime();
                Thread.sleep(sleepTime);
            } catch (InterruptedException ex) {
                Logger.getLogger(Node.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    
    }  
}
