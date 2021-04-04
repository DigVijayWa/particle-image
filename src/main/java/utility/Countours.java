package utility;

import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Optional;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

public class Countours {

  public static void main(String args[]) throws Exception{

    BufferedImage image = readImage().orElseThrow(() ->new Exception("Read image failed"));

    int[][] countours = getCountours(image);

    System.out.println(image.getWidth()+ ":"+image.getHeight());
  }
  public static Optional<BufferedImage> readImage() {
    try {
      ClassLoader classLoader = Countours.class.getClassLoader();
      return Optional
          .of(ImageIO.read(new File(classLoader.getResource("panda.png").getFile())));
    } catch (IOException e) {
      e.printStackTrace();
    }
    return Optional.empty();
  }

  public static int[][] getCountours(BufferedImage image) {
    int width = image.getWidth();
    int height = image.getHeight();

    int[][] bitMap = new int[width][height];

    for (int i = 1; i < width - 1; i++) {

      for (int j = 1; j < height - 1; j++) {

        int color1 = image.getRGB(i + 1, j);
        int color2 = image.getRGB(i, j + 1);

        int color3 = image.getRGB(i - 1, j);
        int color4 = image.getRGB(i, j - 1);

        float diff1 = getDiff(color1, color3);
        float diff2 = getDiff(color2, color4);

        bitMap[i][j] = (int) (Math.abs(diff1 - diff2));
      }
    }

    return bitMap;
  }

  public static int[][] getCountoursImage(BufferedImage image) {
    int width = image.getWidth();
    int height = image.getHeight();

    int[][] bitMap = new int[width][height];

    for (int i = 0; i < height; i++) {

      for (int j = 0; j < width; j++) {
        bitMap[j][i] = image.getRGB(j,i);
      }
    }

    return bitMap;
  }

  static float getDiff(int color1, int color2) {
    int red = (color1 >> 16) & 0xff;
    int green = (color1 >> 8) & 0xff;
    int blue = (color1) & 0xff;

    float brightness1 = gray(red, green, blue);

    red = (color2 >> 16) & 0xff;
    green = (color2 >> 8) & 0xff;
    blue = (color2) & 0xff;

    float brightness2 = gray(red, green, blue);

    float diff = Math.abs(brightness1 - brightness2);
    return diff;
  }

  // sRGB luminance(Y) values
  static double rY = 0.212655;
  static double gY = 0.715158;
  static double bY = 0.072187;

  // Inverse of sRGB "gamma" function. (approx 2.2)
  static double inv_gam_sRGB(int ic) {
    double c = ic / 255.0;
    if (c <= 0.04045) {
      return c / 12.92;
    } else {
      return Math.pow(((c + 0.055) / (1.055)), 2.4);
    }
  }

  // sRGB "gamma" function (approx 2.2)
  static int gam_sRGB(double v) {
    if (v <= 0.0031308) {
      v *= 12.92;
    } else {
      v = 1.055 * Math.pow(v, 1.0 / 2.4) - 0.055;
    }
    int y = (int) (v * 255 + 0.5);
    return y; // This is correct in C++. Other languages may not
    // require +0.5
  }

  // GRAY VALUE ("brightness")
  static int gray(int r, int g, int b) {
    return gam_sRGB(
        rY * inv_gam_sRGB(r) +
            gY * inv_gam_sRGB(g) +
            bY * inv_gam_sRGB(b)
    );
  }

}

