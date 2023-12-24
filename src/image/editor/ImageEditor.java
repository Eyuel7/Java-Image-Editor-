/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package image.editor;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.Arrays;
import javax.imageio.ImageIO;

/**
 *
 * @author eyuel
 */
public class ImageEditor {

  
    public static void main(String[] args) {
        
        
    }
    
    public static int[][] trimBorders(int[][] imageTwoD, int pixelCount){
        
        if(imageTwoD.length > pixelCount * 2 && imageTwoD[0].length > pixelCount * 2){
            
            //We multiply the pixel count by 2 because we are trimming from both sides
            int[][] trimmedImage = new int[imageTwoD.length - pixelCount * 2]
                    [imageTwoD[0].length - pixelCount * 2];
            
            for(int i=0; i<trimmedImage.length; i++){
                for(int j=0; j<trimmedImage[i].length; j++){
                    
                    //We are adding the pixelCount here because we want to jump
                    //pixels before that
                    trimmedImage[i][j] = imageTwoD[i + pixelCount][j + pixelCount];
                }
            }
            
            return trimmedImage;
        }
        
        else{
            
            System.out.println("Cannot trim that many pixels from given image.");
            return imageTwoD;
        }
    }
    
    public static int[][] negativeColor(int[][] imageTwoD){
        
        int[][] negativeImage = new int[imageTwoD.length][imageTwoD[0].length];
        
        for(int i=0; i<negativeImage.length; i++){
            for(int j=0; j<negativeImage[0].length; j++){
                
                int[] rgba = getRGBAFromPixel(imageTwoD[i][j]);
                rgba[0] = 255 - rgba[0];
                rgba[1] = 255 - rgba[1];
                rgba[2] = 255 - rgba[2];
                
                negativeImage[i][j] = getColorIntValueFromRGBA(rgba);
            }
        }
        
        return negativeImage;
    }
    
    public static int[] getRGBAFromPixel(int pixelColorValue){
        
        Color pixelColor = new Color(pixelColorValue);
        
        return new int[]{ pixelColor.getRed(), pixelColor.getGreen()
                , pixelColor.getBlue(), pixelColor.getAlpha()};
    }
    
    public static int getColorIntValueFromRGBA(int[] colorData){
        
        if(colorData.length == 4){
            
            Color color = new Color(colorData[0], colorData[1], 
                    colorData[2], colorData[3]);
            return color.getRGB();
        }
        else{
            
            System.out.println("Incorrect number of elements in RGBA array");
            return -1;
        }
    }
    
}
