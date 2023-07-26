package me.kertf22;

import nu.pattern.OpenCV;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;

import javax.swing.*;
import java.awt.*;

public class Main {
    static{ OpenCV.loadLocally();}



    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            Camera camera = new Camera();

            // start camera in theard

            new Thread(() -> {
                camera.startCamera();
            }).start();
        });

    }
}