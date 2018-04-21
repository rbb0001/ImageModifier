import java.awt.Color;
import java.util.Scanner;

public class ImageModifier {
	
	//Makes a image grayscale
	public static Color[][] GrayScale(Color[][] img) {
		Color[][] changedImage = ImageUtils.cloneArray(img);
		for (int row = 0; row < changedImage.length; row++) {
			for(int col = 0; col < changedImage[row].length; col ++) {
				Color place = changedImage[row][col];
				int red = place.getRed();
				int green = place.getGreen();
				int blue = place.getBlue();
				int avgColor = ((red+green+blue)/3);
				changedImage[row][col] = new Color(avgColor, avgColor, avgColor);
			}
		}
		return changedImage;
	}
	
	//Changes the colors with modifiers specified by the user's inputs
	public static Color[][] Tint(Color[][] img, double changeRed, double changeGreen, double changeBlue) {
		Color[][] changedImage = ImageUtils.cloneArray(img);
		for (int row = 0; row < changedImage.length; row++) {
			for(int col = 0; col < changedImage[row].length; col ++) {
				Color place = changedImage[row][col];
				int red = place.getRed();
				int green = place.getGreen();
				int blue = place.getBlue();
				double newRed = red*changeRed;
				double newGreen = green*changeGreen;
				double newBlue = blue*changeBlue;
				//Makes sure that colors never get modified above their max of 255
				if (newRed > 255) {
					newRed = 255;
				}
				if (newGreen > 255) {
					newGreen = 255;
				}
				if (newBlue > 255) {
					newBlue = 255;
				}
				changedImage[row][col] = new Color((int)newRed, (int)newGreen, (int)newBlue);
			}
		}
		return changedImage;
	}
	
	//"Flips" or rotates the image 180 degrees
	public static Color[][] Flip(Color[][] img) {
		Color[][] oldImage = ImageUtils.cloneArray(img);
		Color[][] changedImage = new Color[oldImage.length][oldImage[0].length];
		for (int row = 0; row < changedImage.length; row++) {
			for(int col = 0; col < changedImage[row].length; col ++) {
				changedImage[row][col] = oldImage[(oldImage.length - 1) - row][(oldImage[row].length - 1) - col];
			}
		}
		return changedImage;
	}
	
	//Mirrors the image along the y-axis
	public static Color[][] MirrorHorizon(Color[][] img) {
		Color[][] image = ImageUtils.cloneArray(img);
		for (int row = 0; row < (image.length/2); row++) {
			for(int col = 0; col < image[row].length; col ++) {
				image[row][col] = image[(image.length - 1) - row][col];
			}
		}
		return image;
	}
	
	//Mirrors the image along the x-axis
	public static Color[][] MirrorVert(Color[][] img) {
		Color[][] image = ImageUtils.cloneArray(img);
		for (int row = 0; row < image.length; row++) {
			for(int col = 0; col < (image[row].length/2); col ++) {
				image[row][col] = image[row][(image[row].length - 1) - col];
			}
		}
		return image;
	}
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		ImageUtils image = new ImageUtils();
		String path = "";
		double red = 0;
		double green = 0;
		double blue = 0;
		
		System.out.println("Enter the path to the picture you want to modify: ");
		path = input.nextLine();
		
		Color[][] original = image.loadImage(path);
		
		System.out.println("Enter the Red, Green, and Blue color modifiers you wish to use to change the image's color: ");
		red = input.nextDouble();
		green = input.nextDouble();
		blue = input.nextDouble();
		
		image.addImage(original, "Original");
		image.addImage(GrayScale(original), "GrayScale");
		image.addImage(Tint(original,red,green,blue),"Tinted");
		image.addImage(Flip(original), "Flipped");
		image.addImage(MirrorHorizon(original), "Mirrored Horizontally");
		image.addImage(MirrorVert(original), "Mirrored Vertically");
		image.display();
		input.close();
	}

}
