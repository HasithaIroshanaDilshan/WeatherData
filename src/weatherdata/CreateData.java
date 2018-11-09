/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weatherdata;

import java.util.Random;

/**
 *
 * @author Hasitha Iroshana
 */
public class CreateData {
    int temp;
    int humidity;        
    int windSpeed;
    int rainning;
    int location;
    private int dataRounds;
    int rainfall;
    private int counter;
   
    
    public CreateData(int location){
        temp =(int) (30 + (Math.random() * 10));
        humidity =(int) (70 + (Math.random() * 10));
        windSpeed=(int) (5 + (Math.random() * 10));
        rainning=rand.nextInt(2);
        counter=0;
        rainfall=(int) (10 + (Math.random() * 10));
        this.location = location;
    }
    
    Random rand = new Random();
    private int change;
    private int israin;
     
    public void next(){
        counter++;
        
        change = (int)(-2 + (Math.random() * 4));
        temp+=change;
        if(temp>40){
            temp--;
        }
        
        change = (int)(-2 + (Math.random() * 4));
        humidity+=change;
        if(humidity>101){
            humidity--;
        }
        
        change = (int)(-1 + (Math.random() * 4));
        windSpeed+=change;
        
        if(counter%10==0){
            israin = rand.nextInt(2);
            if(israin==1){
                rainning = 1;
            }else{
                rainning = 0;
            }
        }
        if(rainning==1){
            rainfall+=rand.nextInt(3);
        }
        
        
    }
}
