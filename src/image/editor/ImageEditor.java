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
import java.util.Scanner;
import javax.imageio.ImageIO;

/**
 *
 * @author eyuel
 */
public class ImageEditor {

  
    public static void main(String[] args) {
        
        System.out.println("****************************************************");
        
        System.out.println("What Do you want to do? ");
        System.out.print("1. Trim the borders of the image        ");
        System.out.println("2. Show negative color of Image");
        System.out.print("3. Stretch Horizontally                 ");
        System.out.println("4. Shrink Vertically");
        System.out.println("5. Invert the image");
        
        System.out.println("****************************************************");
        
        Scanner scan = new Scanner(System.in);
        int input = scan.nextInt();
        
        int[][] imageData = MainProcesses.imageToTwoD("C:\\Users\\eyuel\\Documents\\NetBeansProjects\\Image Editor\\src\\image\\editor\\Images\\dog.jpg");
        
        switch(input){
            case 1:
                int[][] trimmed = trimBorders(imageData, 60);
                MainProcesses.twoDToImage(trimmed, "C:\\Users\\eyuel\\Documents\\NetBeansProjects\\Image Editor\\src\\image\\editor\\Images\\trimmed.jpg");
                break;
             
            case 2:
                int[][] negative = negativeColor(imageData);
                MainProcesses.twoDToImage(negative, "C:\\Users\\eyuel\\Documents\\NetBeansProjects\\Image Editor\\src\\image\\editor\\Images\\negative.jpg");
                break;
                
            case 3:
                int[][] stretchImageHorizontally = stretchHorizontally(imageData);
                MainProcesses.twoDToImage(stretchImageHorizontally, "C:\\Users\\eyuel\\Documents\\NetBeansProjects\\Image Editor\\src\\image\\editor\\Images\\stretched.jpg");
                break;
                
            case 4:
                int[][] shrinkImageVertically = shrinkVertically(imageData);
                MainProcesses.twoDToImage(shrinkImageVertically, "C:\\Users\\eyuel\\Documents\\NetBeansProjects\\Image Editor\\src\\image\\editor\\Images\\shrunk.jpg");
                break;
        
            case 5:
                int[][] invertedImage = invertImage(imageData);
                MainProcesses.twoDToImage(invertedImage, "C:\\Users\\eyuel\\Documents\\NetBeansProjects\\Image Editor\\src\\image\\editor\\Images\\inverted.jpg");
                break;
               
              }
        
        
        
        
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
    
    public static int[][] stretchHorizontally(int[][] imageTwoD){
        
        int[][] stretchedImage = new int[imageTwoD.length][2*imageTwoD[0].length];
        int pos = 0;
        
        for(int i=0; i<imageTwoD.length; i++){
            for(int j=0; j<imageTwoD[0].length; j++){
                
                pos = j * 2;
                stretchedImage[i][pos] = imageTwoD[i][j];
                stretchedImage[i][pos + 1] = imageTwoD[i][j];
            }
        }
        
        return stretchedImage;
    }
    
    public static int[][] shrinkVertically(int[][] imageTwoD){
        
        int[][] shrunkImage = new int[imageTwoD.length / 2][imageTwoD[0].length];
        
        for(int i=0; i<imageTwoD[0].length; i++){
            for(int j=0; j<imageTwoD.length - 1; j+=2){
                
                shrunkImage[j/2][i] = imageTwoD[j][i];
            }
        }
        
        return shrunkImage;
    }
    
    public static int[][] invertImage(int[][] imageTwoD){
        
        int[][] invertedImage = new int[imageTwoD.length][imageTwoD[0].length];
        
        for(int i=0; i<imageTwoD.length; i++){
            for(int j=0; j<imageTwoD[0].length; j++){
                
                invertedImage[i][j] = imageTwoD[(imageTwoD.length-1) - i][(imageTwoD[0].length-1) - j];
            }
        }
        
        return invertedImage;
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
