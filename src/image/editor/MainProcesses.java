/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package image.editor;

import static image.editor.ImageEditor.getRGBAFromPixel;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.Arrays;
import javax.imageio.ImageIO;

/**
 *
 * @author eyuel
 */
public class MainProcesses {
    
    public static int[][] imageToTwoD(String inputFileOrLink){
        
        try{
        
            BufferedImage image = null;
            
        if(inputFileOrLink.substring(0, 4).toLowerCase().equals("http")){
            
            URL imageUrl = new URL(inputFileOrLink);
            image = ImageIO.read(imageUrl);
            
            if(image == null){
                
                System.out.println("Failed to get image from provided URL.");
            }
        }
        
        else{
            image = ImageIO.read(new File(inputFileOrLink));
        }
        
        int imgRows = image.getHeight();
        int imgCols = image.getWidth();
        
        int[][] pixelData = new int[imgRows][imgCols];
        
        for(int i=0; i<imgRows; i++){
            for(int j=0; j<imgCols; j++){
                
                pixelData[i][j] = image.getRGB(j, i);
            }
        }
        
        return pixelData;
      } catch(Exception e){
          
          System.out.println("Failed to load image: " + e.getLocalizedMessage());
          return null;
      }
    }
    
    public static void twoDToImage(int[][] imageData, String fileName){
        
        try{
            int imgRows = imageData.length;
            int imgCols = imageData[0].length;
            
            BufferedImage result = new BufferedImage(imgCols, imgRows, BufferedImage.TYPE_INT_RGB);
            
            for(int i=0; i<imgRows; i++){
                for(int j=0; j<imgCols; j++){
                   
                    result.setRGB(j, i, imageData[i][j]);
                }
            }
            
            File output = new File(fileName);
            ImageIO.write(result, "jpg", output);
            
        } catch(Exception e){
            
            System.out.println("Failed to save image: " + e.getLocalizedMessage());
        }
    }
    
    public static void viewImageData(int[][] imageTwoD) {
		if (imageTwoD.length > 3 && imageTwoD[0].length > 3) {
			int[][] rawPixels = new int[3][3];
			
                        for (int i = 0; i < 3; i++) {
                            for (int j = 0; j < 3; j++) {
				
                                rawPixels[i][j] = imageTwoD[i][j];
				}
			}
			System.out.println("Raw pixel data from the top left corner.");
			System.out.print(Arrays.deepToString(rawPixels).replace("],", "],\n") + "\n");
			
                        int[][][] rgbPixels = new int[3][3][4];
                        for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					rgbPixels[i][j] = getRGBAFromPixel(imageTwoD[i][j]);
				}
			}
			System.out.println();
			System.out.println("Extracted RGBA pixel data from top the left corner.");
			for (int[][] row : rgbPixels) {
                            System.out.print(Arrays.deepToString(row) + System.lineSeparator());
			}
		} else {
                    System.out.println("The image is not large enough to extract 9 pixels from the top left corner");
		}
	}
}
