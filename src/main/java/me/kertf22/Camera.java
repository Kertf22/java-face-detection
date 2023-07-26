package me.kertf22;

import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Camera extends JFrame {

    private final JLabel cameraScreen;
    private  boolean clicked = false;
    public Camera() {
        // UI
        setLayout(null);

        cameraScreen = new JLabel();
        cameraScreen.setBounds(0, 0, 600, 480);
        add(cameraScreen);

        JButton captureButton = new JButton("Capture");
        captureButton.setBounds(300, 480, 80, 40);
        add(captureButton);

        captureButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clicked = true;
            }
        });

        setSize(new Dimension(640, 560));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void startCamera(){
        VideoCapture videoCapture = new VideoCapture(0);
        Mat image = new Mat();
        byte[] imageData;
        ImageIcon imageIcon;

        while (true) {
            // read image
            videoCapture.read(image);

            // convert image to byte array
            final MatOfByte buf = new MatOfByte();
            Imgcodecs.imencode(".jpg", image, buf);

            imageData = buf.toArray();
            imageIcon = new ImageIcon(imageData);
            cameraScreen.setIcon(imageIcon);

            if(clicked) {
                String name = new SimpleDateFormat("yyyy-mm-dd-hh-mm-ss").format(new Date());
                Imgcodecs.imwrite("images/" + name +".jpg", image);
                clicked = false;
            }

        }
    }

}
