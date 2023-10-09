package huchu.star.application;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class StarDrawingExample extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int width = getWidth();
        int height = getHeight();

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();

        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, width, height);

        g2d.setColor(Color.WHITE);

        int centerX = width / 2;
        int centerY = height / 2;
        int outerRadius = Math.min(width, height) / 3;
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

        g2d.drawPolygon(xPoints, yPoints, numPoints * 2);

        g2d.dispose();

        g.drawImage(image, 0, 0, null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Star Drawing Example");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 400);
            frame.add(new StarDrawingExample());
            frame.setVisible(true);
        });
    }
}
