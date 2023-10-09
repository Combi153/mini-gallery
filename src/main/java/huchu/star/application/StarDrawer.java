package huchu.star.application;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class StarDrawer {

    private final BufferedImage bufferedImage;
    private final Graphics2D graphics2D;

    public StarDrawer(BufferedImage bufferedImage, Graphics2D graphics2D) {
        this.bufferedImage = bufferedImage;
        this.graphics2D = graphics2D;
    }

    public static StarDrawer from(int imageSize) {
        BufferedImage bufferedImage = new BufferedImage(imageSize, imageSize, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics2D = bufferedImage.createGraphics();
        graphics2D.setColor(Color.BLACK);
        graphics2D.fillRect(0, 0, imageSize, imageSize);
        drawStar(imageSize, graphics2D);
        return new StarDrawer(bufferedImage, graphics2D);
    }

    private static void drawStar(int imageSize, Graphics2D graphics2D) {
        graphics2D.setColor(Color.WHITE);

        int centerX = imageSize / 2;
        int centerY = imageSize / 2;
        int outerRadius = imageSize / 3;
        int innerRadius = outerRadius / 2;

        int numPoints = 5; // 5개의 꼭짓점을 가진 별 모양을 그립니다.
        int angleIncrement = 360 / numPoints;

        int[] xPoints = new int[numPoints * 2];
        int[] yPoints = new int[numPoints * 2];

        for (int i = 0; i < numPoints * 2; i++) {
            int radius = (i % 2 == 0) ? outerRadius : innerRadius;
            double angle = Math.toRadians(i * angleIncrement - 90); // -90을 더해 위쪽을 시작으로 설정합니다.

            xPoints[i] = centerX + (int) (radius * Math.cos(angle));
            yPoints[i] = centerY + (int) (radius * Math.sin(angle));
        }

        graphics2D.drawPolygon(xPoints, yPoints, numPoints * 2);
    }

    public void dispose() {
        graphics2D.dispose();
    }

    public BufferedImage bufferedImage() {
        return bufferedImage;
    }

    public Graphics2D graphics2D() {
        return graphics2D;
    }
}
