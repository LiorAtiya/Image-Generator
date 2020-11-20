package ImageIO;

import java.awt.Color;
import java.util.Arrays;

public class Main {

	//1. Convert from a color image to a gray image.
	public static int[][] rgb2gray(int[][][] im){
		//create a new 2D array to contain the gray color values.
		int[][] grayRGB = new int[im[0].length][im[0][0].length];

		for(int i=0; i< im[0].length ; i++) {
			for(int j=0 ; j < im[0][0].length ; j++) {
				//Change values to the gray color of each cell in the array by RGB
				grayRGB[i][j] = (int)((0.3*im[0][i][j])+(0.59*im[1][i][j])+(0.11*im[2][i][j]))*255;
			}
		}
		return grayRGB;
	}

	//2.Convert image to solid color: 0-red | 1-green | 2-blue.
	public static int[][][] channels(int[][][] im, int n){
		//create a new 3D array to contain the solid color values.
		int[][][] solidColor = new int[im.length][im[0].length][im[0][0].length];

		for(int k=0 ; k < im.length ; k++) {
			for(int i=0; i< im[0].length ; i++) {
				for(int j=0 ; j<im[0][0].length ; j++) {
					//implement the selected channel values in the array, and the other channels remains 0;
					if(k == n) solidColor[k][i][j] = im[k][i][j];
				}
			}
		}

		return solidColor;
	}

	//Value histogram of the picture.
	public static int [][] histogram(int [][][] img){
		//Create a new 2D array to contain the counting of each color value.
		int[][] histogram = new int[3][256];

		//Count the number of colors between 0 to 255.
		for(int k=0 ; k < img.length ; k++) {
			for(int i = 0 ; i < img[0].length ; i++) {
				for(int j=0 ; j < img[0][0].length ; j++) {
					//Counting the color value of img, and put them in the histogram[][].
					histogram[k][img[k][i][j]]++;
				}
			}
		}

		//Bubble sort of histogram array.
		for (int i = 0; i < histogram.length; i++) { 
			for (int j = 0; j < histogram[i].length; j++) {
				for (int k = 0; k < histogram[i].length - j - 1; k++) { 
					//Check if the next cell is smaller than the current cell.
					if (histogram[i][k] > histogram[i][k + 1]) { 
						//swap
						int t = histogram[i][k]; 
						histogram[i][k] = histogram[i][k + 1]; 
						histogram[i][k + 1] = t; 
					} 
				} 
			} 
		} 



		return histogram;
	}

	//Reduce the number of colors to n colors.
	public static int[][][] pix(int[][][] im, int n){
		//Create a new 3D array to contain the new value after the reduction.
		int[][][] pixel = new int[im.length][im[0].length][im[0][0].length];

		//Reducing array values
		int reduction = (255/n);

		for(int k=0 ; k < im.length ; k++) {
			for(int i=0 ; i < im[0].length ; i++) {
				for(int j=0 ; j < im[0][0].length ; j++) {
					//implement the new color value in the pixel[][][]
					pixel[k][i][j] = (im[k][i][j] /(reduction+1)*(reduction+1));
				}
			}
		}

		return pixel;
	}


	public static void main(String[] args) {
		//		int [][][] image = MyImageIO.readImageFromFile("C://Users//Lior//Desktop//cat.jpg");
		////		MyImageIO.writeImageToFile("C://Users//Lior//Desktop//îãòé äîçùá//îáåà ìçéùåá//Picture//catCopy1",rgb2gray(image));
		//		MyImageIO.writeImageToFile("C://Users//Lior//Desktop//catCopy2",channels(image, 0));

		//NEED PICTURE OF CAT.JPG IN DESKTOP!!
		int [][][] im=MyImageIO.readImageFromFile("C://Users//Lior//Desktop//cat.jpg");
		int[][] gray= rgb2gray(im);
		MyImageIO.writeImageToFile("C://Users//Lior//Desktop//catGray1.jpg",channels(im, 0));
		MyImageIO.writeImageToFile("C://Users//Lior//Desktop//catGray2.jpg",channels(im, 1));
		MyImageIO.writeImageToFile("C://Users//Lior//Desktop//catGray3.jpg",channels(im, 2));
		//		MyImageIO.writeImageToFile("C://Users//Lior//Desktop//îãòé äîçùá//îáåà ìçéùåá//Picture//catCopy3",pix(image, 25));

		//		for(int i=0 ; i < 3 ; i++) {
		//			System.out.println(Arrays.toString(histogram(image)[i]));
	}

}


