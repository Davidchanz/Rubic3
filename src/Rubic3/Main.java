package Rubic3;

import Engine3D.Coeff;
import Engine3D.Scene;
import Engine3D.Shape;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Array;
import java.text.spi.BreakIteratorProvider;
import java.util.Random;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Main extends JFrame{
    public static int WIDTH = 700;
    public static int HEIGHT = 700;
    public static Cube rubic;
    public static Scene scene;
    public int w;
    public static boolean buildprocess = false;
    public static String formula = "";
    public static boolean build_flag = false;

    Main(){
        //System.out.println("Hello world!");
        /*scene = new Scene();
        rubic = new Cube(3, -70, -70, 70);
        for (var it : rubic.shapes) {
            scene.add(it);
        }*/

        /*this.setSize(new Dimension(WIDTH + 100, HEIGHT + 100));
        this.add(Build, BorderLayout.EAST);
        this.add(Break, BorderLayout.WEST);
        this.add(scene, BorderLayout.CENTER);*/

        //this.setVisible(true);
        /*this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent keyEvent) {
                for (var it : scene.Shapes) {
                    if(!buildprocess) {
                        switch (keyEvent.getKeyCode()) {
                            case KeyEvent.VK_F1:
                                scene.RotateX(5, it);
                                //it.angX += 5;
                                break;
                            case KeyEvent.VK_F2:
                                scene.RotateX(-5, it);
                                //it.angX -= 5;
                                break;
                            case KeyEvent.VK_F3:
                                scene.RotateY(5, it);
                                //it.angY += 5;
                                break;
                            case KeyEvent.VK_F4:
                                scene.RotateY(-5, it);
                                //it.angY -= 5;
                                break;
                            case KeyEvent.VK_F5:
                                scene.RotateZ(5, it);
                                //it.angZ += 5;
                                break;
                            case KeyEvent.VK_F6:
                                scene.RotateZ(-5, it);
                                //it.angZ -= 5;
                                break;
                            case KeyEvent.VK_0:
                                //formula = disbuild();
                                break;
                            case KeyEvent.VK_1:
                                //Build(formula);
                                break;
                        }
                        scene.repaint();
                    }
                }
                scene.camera.CameraGo(keyEvent);
            }
        });*/
        /*this.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
                scene.camera.CameraMove(e);
            }
        });*/
        /*this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.exit(0);
            }
        });*/

        /*String formula = disbuild();
        Build(formula);

        System.out.println("2345");

        formula = disbuild();
        Build(formula);*/
        //Build("RDburLuFDr");
        //Build("RdfUlRUBdr");
        //Build("RB");
        /*this.setVisible(true);*/
    }
    public static void main(String[] args) {
        scene = new Scene();
        rubic = new Cube(3, -70, -70, 70);
        for (var it : rubic.shapes) {
            scene.add(it);
        }

        JButton Build = new JButton("Build");
        JButton Break = new JButton("Break");

        Frame f = new Frame("Cubic Rubic");

        f.setSize(new Dimension(WIDTH + 100, HEIGHT + 100));
        f.add(Build, BorderLayout.EAST);
        f.add(Break, BorderLayout.WEST);
        f.add(scene, BorderLayout.CENTER);

        f.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.exit(0);
            }
        });

        Build.addActionListener(actionEvent -> {
            f.requestFocus();
            if(!buildprocess) {
                if(!formula.isEmpty()) {
                    build_flag = true;
                    formula = "";
                }
            }
        });
        Break.addActionListener(actionEvent -> {
            f.requestFocus();
            if(!buildprocess) {
                if (!formula.isEmpty()) fatsBuild(formula);
                formula = disbuild();
            }
        });
        f.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent keyEvent) {
                for (var it : scene.Shapes) {
                    if(!buildprocess) {
                        switch (keyEvent.getKeyCode()) {
                            case KeyEvent.VK_F1:
                                scene.RotateX(5, it);
                                //it.angX += 5;
                                break;
                            case KeyEvent.VK_F2:
                                scene.RotateX(-5, it);
                                //it.angX -= 5;
                                break;
                            case KeyEvent.VK_F3:
                                scene.RotateY(5, it);
                                //it.angY += 5;
                                break;
                            case KeyEvent.VK_F4:
                                scene.RotateY(-5, it);
                                //it.angY -= 5;
                                break;
                            case KeyEvent.VK_F5:
                                scene.RotateZ(5, it);
                                //it.angZ += 5;
                                break;
                            case KeyEvent.VK_F6:
                                scene.RotateZ(-5, it);
                                //it.angZ -= 5;
                                break;
                            case KeyEvent.VK_0:
                                formula = disbuild();
                                break;
                            case KeyEvent.VK_1:
                                //Build(formula);
                                break;
                        }
                        scene.repaint();
                    }
                }
                scene.camera.CameraGo(keyEvent);
            }
        });
        f.setVisible(true);
        f.requestFocus();
        while (true){
            if(build_flag){
                Build(formula);
                build_flag = false;
            }
        }
    }
    public static void fatsBuild(String formula){

        for (int o = 0; o < rubic.rubic_size; ++o) {
            for (int k = 0; k < rubic.rubic_size; ++k) {
                for (int l = 0; l < rubic.rubic_size; ++l) {
                    rubic.cube[o][k][l].angX = 0;
                    rubic.cube[o][k][l].angY = 0;
                    rubic.cube[o][k][l].angZ = 0;
                }
            }
        }

        int command = 0;
        for (int u = 0; u < formula.length(); ++u) {
            char c = formula.charAt(u);
            switch (c) {
                case 'R' -> command = 1;
                case 'L' -> command = 2;
                case 'U' -> command = 3;
                case 'D' -> command = 4;
                case 'F' -> command = 5;
                case 'B' -> command = 6;
                case 'r' -> command = 11;
                case 'l' -> command = 21;
                case 'u' -> command = 31;
                case 'd' -> command = 41;
                case 'f' -> command = 51;
                case 'b' -> command = 61;
            }

            Shape[][] matrix = new Shape[rubic.rubic_size][rubic.rubic_size];
            boolean drawprocess = true;
            for (int o = 0; o < rubic.rubic_size; ++o) {
                for (int k = 0; k < rubic.rubic_size; ++k) {
                    for (int l = 0; l < rubic.rubic_size; ++l) {
                        switch (command) {
                            // "RLUDFBrludfb"
                            case 1 -> {
                                if (o == 2) {
                                    //drawprocess = scene.RotateX(5, rubic.cube[o][k][l]);//R
                                    matrix[k][l] = rubic.cube[2][k][l];
                                }
                                break;
                            }
                            case 2 -> {
                                if (o == 0) {
                                    //drawprocess = scene.RotateX(5, rubic.cube[o][k][l]);//L
                                    matrix[k][l] = rubic.cube[o][k][l];
                                }
                                break;
                            }
                            case 3 -> {
                                if (k == 2) {
                                    //drawprocess = scene.RotateY(5, rubic.cube[o][k][l]);//U
                                    matrix[o][l] = rubic.cube[o][k][l];
                                }
                                break;
                            }
                            case 4 -> {
                                if (k == 0) {
                                    //drawprocess = scene.RotateY(5, rubic.cube[o][k][l]);//D
                                    matrix[o][l] = rubic.cube[o][k][l];
                                }
                                break;
                            }
                            case 5 -> {
                                if (l == 0) {
                                    //drawprocess = scene.RotateZ(5, rubic.cube[o][k][l]);//F
                                    matrix[o][k] = rubic.cube[o][k][l];
                                }
                                break;
                            }
                            case 6 -> {
                                if (l == 2) {
                                    //drawprocess = scene.RotateZ(5, rubic.cube[o][k][l]);//B
                                    matrix[o][k] = rubic.cube[o][k][l];
                                }
                                break;
                            }
                            case 11 -> {
                                if (o == 2) {
                                    //drawprocess = scene.RotateX(-5, rubic.cube[o][k][l]);//r
                                    matrix[k][l] = rubic.cube[o][k][l];
                                }
                                break;
                            }
                            case 21 -> {
                                if (o == 0) {
                                    //drawprocess = scene.RotateX(-5, rubic.cube[o][k][l]);//l
                                    matrix[k][l] = rubic.cube[o][k][l];
                                }
                                break;
                            }
                            case 31 -> {
                                if (k == 2) {
                                    //drawprocess = scene.RotateY(-5, rubic.cube[o][k][l]);//u
                                    matrix[o][l] = rubic.cube[o][k][l];
                                }
                                break;
                            }
                            case 41 -> {
                                if (k == 0) {
                                    //drawprocess = scene.RotateY(-5, rubic.cube[o][k][l]);//d
                                    matrix[o][l] = rubic.cube[o][k][l];
                                }
                                break;
                            }
                            case 51 -> {
                                if (l == 0) {
                                    //drawprocess = scene.RotateZ(-5, rubic.cube[o][k][l]);//f
                                    matrix[o][k] = rubic.cube[o][k][l];
                                }
                                break;
                            }
                            case 61 -> {
                                if (l == 2) {
                                    //drawprocess = scene.RotateZ(-5, rubic.cube[o][k][l]);//b
                                    matrix[o][k] = rubic.cube[o][k][l];
                                }
                                break;
                            }
                        }
                    }
                }
            }
            scene.repaint();
            switch (command) {
                case 1 -> {//R
                    Color[] color_array = new Color[12];

                    color_array[0] = matrix[0][0].planes.get(5).color;
                    color_array[1] = matrix[1][0].planes.get(5).color;
                    color_array[2] = matrix[2][0].planes.get(5).color;

                    color_array[3] = matrix[2][0].planes.get(0).color;
                    color_array[4] = matrix[2][1].planes.get(0).color;
                    color_array[5] = matrix[2][2].planes.get(0).color;

                    color_array[6] = matrix[2][2].planes.get(1).color;
                    color_array[7] = matrix[1][2].planes.get(1).color;
                    color_array[8] = matrix[0][2].planes.get(1).color;

                    color_array[9] = matrix[0][2].planes.get(3).color;
                    color_array[10] = matrix[0][1].planes.get(3).color;
                    color_array[11] = matrix[0][0].planes.get(3).color;

                    color_array = rotate(color_array);
                    color_array = rotate(color_array);
                    color_array = rotate(color_array);

                    color_array = rotate(color_array);
                    color_array = rotate(color_array);
                    color_array = rotate(color_array);

                    color_array = rotate(color_array);
                    color_array = rotate(color_array);
                    color_array = rotate(color_array);

                    matrix[0][0].planes.get(5).color = color_array[0];
                    matrix[1][0].planes.get(5).color = color_array[1];
                    matrix[2][0].planes.get(5).color = color_array[2];
                    matrix[2][0].planes.get(0).color = color_array[3];
                    matrix[2][1].planes.get(0).color = color_array[4];
                    matrix[2][2].planes.get(0).color = color_array[5];
                    matrix[2][2].planes.get(1).color = color_array[6];
                    matrix[1][2].planes.get(1).color = color_array[7];
                    matrix[0][2].planes.get(1).color = color_array[8];
                    matrix[0][2].planes.get(3).color = color_array[9];
                    matrix[0][1].planes.get(3).color = color_array[10];
                    matrix[0][0].planes.get(3).color = color_array[11];

                    rotate(matrix, rubic.rubic_size);
                    rotate(matrix, rubic.rubic_size);
                    rotate(matrix, rubic.rubic_size);
                    int q = 0;
                    Color[] turn_array = new Color[9];
                    for (int i = 0; i < matrix.length; ++i) {
                        for (int j = 0; j < matrix.length; ++j) {
                            turn_array[q] = matrix[i][j].planes.get(4).color;
                            q++;
                        }
                    }
                    rotate(matrix, rubic.rubic_size);
                    q = 0;
                    for (int i = 0; i < matrix.length; ++i) {
                        for (int j = 0; j < matrix.length; ++j) {
                            matrix[i][j].planes.get(4).color = turn_array[q];
                            q++;
                            matrix[i][j].angX = 0;
                        }
                    }
                    break;
                }
                case 2 -> {//L
                    Color[] color_array = new Color[12];

                    color_array[0] = matrix[0][0].planes.get(5).color;
                    color_array[1] = matrix[1][0].planes.get(5).color;
                    color_array[2] = matrix[2][0].planes.get(5).color;

                    color_array[3] = matrix[2][0].planes.get(0).color;
                    color_array[4] = matrix[2][1].planes.get(0).color;
                    color_array[5] = matrix[2][2].planes.get(0).color;

                    color_array[6] = matrix[2][2].planes.get(1).color;
                    color_array[7] = matrix[1][2].planes.get(1).color;
                    color_array[8] = matrix[0][2].planes.get(1).color;

                    color_array[9] = matrix[0][2].planes.get(3).color;
                    color_array[10] = matrix[0][1].planes.get(3).color;
                    color_array[11] = matrix[0][0].planes.get(3).color;

                    color_array = rotate(color_array);
                    color_array = rotate(color_array);
                    color_array = rotate(color_array);
                    color_array = rotate(color_array);
                    color_array = rotate(color_array);
                    color_array = rotate(color_array);
                    color_array = rotate(color_array);
                    color_array = rotate(color_array);
                    color_array = rotate(color_array);

                    matrix[0][0].planes.get(5).color = color_array[0];
                    matrix[1][0].planes.get(5).color = color_array[1];
                    matrix[2][0].planes.get(5).color = color_array[2];
                    matrix[2][0].planes.get(0).color = color_array[3];
                    matrix[2][1].planes.get(0).color = color_array[4];
                    matrix[2][2].planes.get(0).color = color_array[5];
                    matrix[2][2].planes.get(1).color = color_array[6];
                    matrix[1][2].planes.get(1).color = color_array[7];
                    matrix[0][2].planes.get(1).color = color_array[8];
                    matrix[0][2].planes.get(3).color = color_array[9];
                    matrix[0][1].planes.get(3).color = color_array[10];
                    matrix[0][0].planes.get(3).color = color_array[11];

                    rotate(matrix, rubic.rubic_size);
                    rotate(matrix, rubic.rubic_size);
                    rotate(matrix, rubic.rubic_size);
                    int q = 0;
                    Color[] turn_array = new Color[9];
                    for (int i = 0; i < matrix.length; ++i) {
                        for (int j = 0; j < matrix.length; ++j) {
                            turn_array[q] = matrix[i][j].planes.get(2).color;
                            q++;
                        }
                    }
                    rotate(matrix, rubic.rubic_size);
                    q = 0;
                    for (int i = 0; i < matrix.length; ++i) {
                        for (int j = 0; j < matrix.length; ++j) {
                            matrix[i][j].planes.get(2).color = turn_array[q];
                            q++;
                            matrix[i][j].angX = 0;
                        }
                    }
                    break;
                }
                case 3 -> {//U
                    Color[] color_array = new Color[12];

                    color_array[0] = matrix[0][0].planes.get(5).color;
                    color_array[1] = matrix[1][0].planes.get(5).color;
                    color_array[2] = matrix[2][0].planes.get(5).color;

                    color_array[3] = matrix[2][0].planes.get(4).color;
                    color_array[4] = matrix[2][1].planes.get(4).color;
                    color_array[5] = matrix[2][2].planes.get(4).color;

                    color_array[6] = matrix[2][2].planes.get(1).color;
                    color_array[7] = matrix[1][2].planes.get(1).color;
                    color_array[8] = matrix[0][2].planes.get(1).color;

                    color_array[9] = matrix[0][2].planes.get(2).color;
                    color_array[10] = matrix[0][1].planes.get(2).color;
                    color_array[11] = matrix[0][0].planes.get(2).color;

                    color_array = rotate(color_array);
                    color_array = rotate(color_array);
                    color_array = rotate(color_array);

                    matrix[0][0].planes.get(5).color = color_array[0];
                    matrix[1][0].planes.get(5).color = color_array[1];
                    matrix[2][0].planes.get(5).color = color_array[2];
                    matrix[2][0].planes.get(4).color = color_array[3];
                    matrix[2][1].planes.get(4).color = color_array[4];
                    matrix[2][2].planes.get(4).color = color_array[5];
                    matrix[2][2].planes.get(1).color = color_array[6];
                    matrix[1][2].planes.get(1).color = color_array[7];
                    matrix[0][2].planes.get(1).color = color_array[8];
                    matrix[0][2].planes.get(2).color = color_array[9];
                    matrix[0][1].planes.get(2).color = color_array[10];
                    matrix[0][0].planes.get(2).color = color_array[11];

                    rotate(matrix, rubic.rubic_size);
                    int q = 0;
                    Color[] turn_array = new Color[9];
                    Color[] turn_array_b = new Color[9];
                    for (int i = 0; i < matrix.length; ++i) {
                        for (int j = 0; j < matrix.length; ++j) {
                            turn_array[q] = matrix[i][j].planes.get(0).color;
                            turn_array_b[q] = matrix[i][j].planes.get(3).color;
                            q++;
                        }
                    }
                    rotate(matrix, rubic.rubic_size);
                    rotate(matrix, rubic.rubic_size);
                    rotate(matrix, rubic.rubic_size);
                    q = 0;
                    for (int i = 0; i < matrix.length; ++i) {
                        for (int j = 0; j < matrix.length; ++j) {
                            matrix[i][j].planes.get(0).color = turn_array[q];
                            matrix[i][j].planes.get(3).color = turn_array_b[q];
                            q++;
                            matrix[i][j].angY = 0;
                        }
                    }
                    break;
                }
                case 4 -> {//D
                    Color[] color_array = new Color[12];

                    color_array[0] = matrix[0][0].planes.get(5).color;
                    color_array[1] = matrix[1][0].planes.get(5).color;
                    color_array[2] = matrix[2][0].planes.get(5).color;

                    color_array[3] = matrix[2][0].planes.get(4).color;
                    color_array[4] = matrix[2][1].planes.get(4).color;
                    color_array[5] = matrix[2][2].planes.get(4).color;

                    color_array[6] = matrix[2][2].planes.get(1).color;
                    color_array[7] = matrix[1][2].planes.get(1).color;
                    color_array[8] = matrix[0][2].planes.get(1).color;

                    color_array[9] = matrix[0][2].planes.get(2).color;
                    color_array[10] = matrix[0][1].planes.get(2).color;
                    color_array[11] = matrix[0][0].planes.get(2).color;

                    color_array = rotate(color_array);
                    color_array = rotate(color_array);
                    color_array = rotate(color_array);

                    matrix[0][0].planes.get(5).color = color_array[0];
                    matrix[1][0].planes.get(5).color = color_array[1];
                    matrix[2][0].planes.get(5).color = color_array[2];
                    matrix[2][0].planes.get(4).color = color_array[3];
                    matrix[2][1].planes.get(4).color = color_array[4];
                    matrix[2][2].planes.get(4).color = color_array[5];
                    matrix[2][2].planes.get(1).color = color_array[6];
                    matrix[1][2].planes.get(1).color = color_array[7];
                    matrix[0][2].planes.get(1).color = color_array[8];
                    matrix[0][2].planes.get(2).color = color_array[9];
                    matrix[0][1].planes.get(2).color = color_array[10];
                    matrix[0][0].planes.get(2).color = color_array[11];

                    rotate(matrix, rubic.rubic_size);
                    int q = 0;
                    Color[] turn_array = new Color[9];
                    for (int i = 0; i < matrix.length; ++i) {
                        for (int j = 0; j < matrix.length; ++j) {
                            turn_array[q] = matrix[i][j].planes.get(3).color;
                            q++;
                        }
                    }
                    rotate(matrix, rubic.rubic_size);
                    rotate(matrix, rubic.rubic_size);
                    rotate(matrix, rubic.rubic_size);
                    q = 0;
                    for (int i = 0; i < matrix.length; ++i) {
                        for (int j = 0; j < matrix.length; ++j) {
                            matrix[i][j].planes.get(3).color = turn_array[q];
                            q++;
                            matrix[i][j].angY = 0;
                        }
                    }
                    break;
                }
                case 5 -> {//F
                    Color[] color_array = new Color[12];

                    color_array[0] = matrix[0][2].planes.get(0).color;
                    color_array[1] = matrix[1][2].planes.get(0).color;
                    color_array[2] = matrix[2][2].planes.get(0).color;

                    color_array[3] = matrix[0][0].planes.get(2).color;
                    color_array[4] = matrix[0][1].planes.get(2).color;
                    color_array[5] = matrix[0][2].planes.get(2).color;

                    color_array[6] = matrix[0][0].planes.get(3).color;
                    color_array[7] = matrix[1][0].planes.get(3).color;
                    color_array[8] = matrix[2][0].planes.get(3).color;

                    color_array[9] = matrix[2][0].planes.get(4).color;
                    color_array[10] = matrix[2][1].planes.get(4).color;
                    color_array[11] = matrix[2][2].planes.get(4).color;

                    color_array = rotate(color_array);
                    color_array = rotate(color_array);
                    color_array = rotate(color_array);

                    matrix[0][2].planes.get(0).color = color_array[0];
                    matrix[1][2].planes.get(0).color = color_array[1];
                    matrix[2][2].planes.get(0).color = color_array[2];
                    matrix[0][0].planes.get(2).color = color_array[3];
                    matrix[0][1].planes.get(2).color = color_array[4];
                    matrix[0][2].planes.get(2).color = color_array[5];
                    matrix[0][0].planes.get(3).color = color_array[6];
                    matrix[1][0].planes.get(3).color = color_array[7];
                    matrix[2][0].planes.get(3).color = color_array[8];
                    matrix[2][0].planes.get(4).color = color_array[9];
                    matrix[2][1].planes.get(4).color = color_array[10];
                    matrix[2][2].planes.get(4).color = color_array[11];

                    rotate(matrix, rubic.rubic_size);
                    int q = 0;
                    Color[] turn_array = new Color[9];
                    for (int i = 0; i < matrix.length; ++i) {
                        for (int j = 0; j < matrix.length; ++j) {
                            turn_array[q] = matrix[i][j].planes.get(5).color;
                            q++;
                        }
                    }
                    rotate(matrix, rubic.rubic_size);
                    rotate(matrix, rubic.rubic_size);
                    rotate(matrix, rubic.rubic_size);
                    q = 0;
                    for (int i = 0; i < matrix.length; ++i) {
                        for (int j = 0; j < matrix.length; ++j) {
                            matrix[i][j].planes.get(5).color = turn_array[q];
                            q++;
                            matrix[i][j].angZ = 0;
                        }
                    }
                    break;
                }
                case 6 -> {//B
                    Color[] color_array = new Color[12];

                                /*matrix[0][0].planes.get(2).color = Color.CYAN;

                                matrix[0][2].planes.get(0).color = Color.MAGENTA;

                                matrix[2][2].planes.get(4).color = Color.PINK;

                                matrix[2][0].planes.get(3).color = Color.LIGHT_GRAY;*/

                    color_array[3] = matrix[0][0].planes.get(2).color;
                    color_array[4] = matrix[0][1].planes.get(2).color;
                    color_array[5] = matrix[0][2].planes.get(2).color;

                    color_array[2] = matrix[0][2].planes.get(0).color;
                    color_array[1] = matrix[1][2].planes.get(0).color;
                    color_array[0] = matrix[2][2].planes.get(0).color;

                    color_array[9] = matrix[2][2].planes.get(4).color;
                    color_array[10] = matrix[2][1].planes.get(4).color;
                    color_array[11] = matrix[2][0].planes.get(4).color;

                    color_array[6] = matrix[2][0].planes.get(3).color;
                    color_array[7] = matrix[1][0].planes.get(3).color;
                    color_array[8] = matrix[0][0].planes.get(3).color;

                    color_array = rotate(color_array);
                    color_array = rotate(color_array);
                    color_array = rotate(color_array);

                    matrix[2][2].planes.get(0).color = color_array[0];
                    matrix[1][2].planes.get(0).color = color_array[1];
                    matrix[0][2].planes.get(0).color = color_array[2];
                    matrix[0][0].planes.get(2).color = color_array[3];
                    matrix[0][1].planes.get(2).color = color_array[4];
                    matrix[0][2].planes.get(2).color = color_array[5];
                    matrix[2][0].planes.get(3).color = color_array[6];
                    matrix[1][0].planes.get(3).color = color_array[7];
                    matrix[0][0].planes.get(3).color = color_array[8];
                    matrix[2][2].planes.get(4).color = color_array[9];
                    matrix[2][1].planes.get(4).color = color_array[10];
                    matrix[2][0].planes.get(4).color = color_array[11];

                    rotate(matrix, rubic.rubic_size);
                                /*rotate(matrix, rubic.rubic_size);
                                rotate(matrix, rubic.rubic_size);*/
                    int q = 0;
                    Color[] turn_array = new Color[9];
                    for (int i = 0; i < matrix.length; ++i) {
                        for (int j = 0; j < matrix.length; ++j) {
                            turn_array[q] = matrix[i][j].planes.get(1).color;
                            q++;
                        }
                    }
                    rotate(matrix, rubic.rubic_size);
                    rotate(matrix, rubic.rubic_size);
                    rotate(matrix, rubic.rubic_size);
                    q = 0;
                    for (int i = 0; i < matrix.length; ++i) {
                        for (int j = 0; j < matrix.length; ++j) {
                            matrix[i][j].planes.get(1).color = turn_array[q];
                            q++;
                            matrix[i][j].angZ = 0;
                        }
                    }
                    break;
                }
                case 11 -> {//r
                    Color[] color_array = new Color[12];

                    color_array[0] = matrix[0][0].planes.get(5).color;
                    color_array[1] = matrix[1][0].planes.get(5).color;
                    color_array[2] = matrix[2][0].planes.get(5).color;

                    color_array[3] = matrix[2][0].planes.get(0).color;
                    color_array[4] = matrix[2][1].planes.get(0).color;
                    color_array[5] = matrix[2][2].planes.get(0).color;

                    color_array[6] = matrix[2][2].planes.get(1).color;
                    color_array[7] = matrix[1][2].planes.get(1).color;
                    color_array[8] = matrix[0][2].planes.get(1).color;

                    color_array[9] = matrix[0][2].planes.get(3).color;
                    color_array[10] = matrix[0][1].planes.get(3).color;
                    color_array[11] = matrix[0][0].planes.get(3).color;

                    color_array = rotate(color_array);
                    color_array = rotate(color_array);
                    color_array = rotate(color_array);

                    matrix[0][0].planes.get(5).color = color_array[0];
                    matrix[1][0].planes.get(5).color = color_array[1];
                    matrix[2][0].planes.get(5).color = color_array[2];
                    matrix[2][0].planes.get(0).color = color_array[3];
                    matrix[2][1].planes.get(0).color = color_array[4];
                    matrix[2][2].planes.get(0).color = color_array[5];
                    matrix[2][2].planes.get(1).color = color_array[6];
                    matrix[1][2].planes.get(1).color = color_array[7];
                    matrix[0][2].planes.get(1).color = color_array[8];
                    matrix[0][2].planes.get(3).color = color_array[9];
                    matrix[0][1].planes.get(3).color = color_array[10];
                    matrix[0][0].planes.get(3).color = color_array[11];

                    rotate(matrix, rubic.rubic_size);
                    int q = 0;
                    Color[] turn_array = new Color[9];
                    for (int i = 0; i < matrix.length; ++i) {
                        for (int j = 0; j < matrix.length; ++j) {
                            turn_array[q] = matrix[i][j].planes.get(4).color;
                            q++;
                        }
                    }
                    rotate(matrix, rubic.rubic_size);
                    rotate(matrix, rubic.rubic_size);
                    rotate(matrix, rubic.rubic_size);
                    q = 0;
                    for (int i = 0; i < matrix.length; ++i) {
                        for (int j = 0; j < matrix.length; ++j) {
                            matrix[i][j].planes.get(4).color = turn_array[q];
                            q++;
                            matrix[i][j].angX = 0;
                        }
                    }
                    break;
                }
                case 21 -> {//r
                    Color[] color_array = new Color[12];

                    color_array[0] = matrix[0][0].planes.get(5).color;
                    color_array[1] = matrix[1][0].planes.get(5).color;
                    color_array[2] = matrix[2][0].planes.get(5).color;

                    color_array[3] = matrix[2][0].planes.get(0).color;
                    color_array[4] = matrix[2][1].planes.get(0).color;
                    color_array[5] = matrix[2][2].planes.get(0).color;

                    color_array[6] = matrix[2][2].planes.get(1).color;
                    color_array[7] = matrix[1][2].planes.get(1).color;
                    color_array[8] = matrix[0][2].planes.get(1).color;

                    color_array[9] = matrix[0][2].planes.get(3).color;
                    color_array[10] = matrix[0][1].planes.get(3).color;
                    color_array[11] = matrix[0][0].planes.get(3).color;

                    color_array = rotate(color_array);
                    color_array = rotate(color_array);
                    color_array = rotate(color_array);

                    matrix[0][0].planes.get(5).color = color_array[0];
                    matrix[1][0].planes.get(5).color = color_array[1];
                    matrix[2][0].planes.get(5).color = color_array[2];
                    matrix[2][0].planes.get(0).color = color_array[3];
                    matrix[2][1].planes.get(0).color = color_array[4];
                    matrix[2][2].planes.get(0).color = color_array[5];
                    matrix[2][2].planes.get(1).color = color_array[6];
                    matrix[1][2].planes.get(1).color = color_array[7];
                    matrix[0][2].planes.get(1).color = color_array[8];
                    matrix[0][2].planes.get(3).color = color_array[9];
                    matrix[0][1].planes.get(3).color = color_array[10];
                    matrix[0][0].planes.get(3).color = color_array[11];

                    rotate(matrix, rubic.rubic_size);
                    int q = 0;
                    Color[] turn_array = new Color[9];
                    for (int i = 0; i < matrix.length; ++i) {
                        for (int j = 0; j < matrix.length; ++j) {
                            turn_array[q] = matrix[i][j].planes.get(2).color;
                            q++;
                        }
                    }
                    rotate(matrix, rubic.rubic_size);
                    rotate(matrix, rubic.rubic_size);
                    rotate(matrix, rubic.rubic_size);
                    q = 0;
                    for (int i = 0; i < matrix.length; ++i) {
                        for (int j = 0; j < matrix.length; ++j) {
                            matrix[i][j].planes.get(2).color = turn_array[q];
                            q++;
                            matrix[i][j].angX = 0;
                        }
                    }
                    break;
                }
                case 31 -> {//u
                    Color[] color_array = new Color[12];

                    color_array[0] = matrix[0][0].planes.get(5).color;
                    color_array[1] = matrix[1][0].planes.get(5).color;
                    color_array[2] = matrix[2][0].planes.get(5).color;

                    color_array[3] = matrix[2][0].planes.get(4).color;
                    color_array[4] = matrix[2][1].planes.get(4).color;
                    color_array[5] = matrix[2][2].planes.get(4).color;

                    color_array[6] = matrix[2][2].planes.get(1).color;
                    color_array[7] = matrix[1][2].planes.get(1).color;
                    color_array[8] = matrix[0][2].planes.get(1).color;

                    color_array[9] = matrix[0][2].planes.get(2).color;
                    color_array[10] = matrix[0][1].planes.get(2).color;
                    color_array[11] = matrix[0][0].planes.get(2).color;

                    color_array = rotate(color_array);
                    color_array = rotate(color_array);
                    color_array = rotate(color_array);
                    color_array = rotate(color_array);
                    color_array = rotate(color_array);
                    color_array = rotate(color_array);
                    color_array = rotate(color_array);
                    color_array = rotate(color_array);
                    color_array = rotate(color_array);

                    matrix[0][0].planes.get(5).color = color_array[0];
                    matrix[1][0].planes.get(5).color = color_array[1];
                    matrix[2][0].planes.get(5).color = color_array[2];
                    matrix[2][0].planes.get(4).color = color_array[3];
                    matrix[2][1].planes.get(4).color = color_array[4];
                    matrix[2][2].planes.get(4).color = color_array[5];
                    matrix[2][2].planes.get(1).color = color_array[6];
                    matrix[1][2].planes.get(1).color = color_array[7];
                    matrix[0][2].planes.get(1).color = color_array[8];
                    matrix[0][2].planes.get(2).color = color_array[9];
                    matrix[0][1].planes.get(2).color = color_array[10];
                    matrix[0][0].planes.get(2).color = color_array[11];

                    rotate(matrix, rubic.rubic_size);
                    rotate(matrix, rubic.rubic_size);
                    rotate(matrix, rubic.rubic_size);
                    int q = 0;
                    Color[] turn_array = new Color[9];
                    Color[] turn_array_b = new Color[9];
                    for (int i = 0; i < matrix.length; ++i) {
                        for (int j = 0; j < matrix.length; ++j) {
                            turn_array[q] = matrix[i][j].planes.get(0).color;
                            turn_array_b[q] = matrix[i][j].planes.get(3).color;
                            q++;
                        }
                    }
                    rotate(matrix, rubic.rubic_size);
                    q = 0;
                    for (int i = 0; i < matrix.length; ++i) {
                        for (int j = 0; j < matrix.length; ++j) {
                            matrix[i][j].planes.get(0).color = turn_array[q];
                            matrix[i][j].planes.get(3).color = turn_array_b[q];
                            q++;
                            matrix[i][j].angY = 0;
                        }
                    }
                    break;
                }
                case 41 -> {//d
                    Color[] color_array = new Color[12];

                    color_array[0] = matrix[0][0].planes.get(5).color;
                    color_array[1] = matrix[1][0].planes.get(5).color;
                    color_array[2] = matrix[2][0].planes.get(5).color;

                    color_array[3] = matrix[2][0].planes.get(4).color;
                    color_array[4] = matrix[2][1].planes.get(4).color;
                    color_array[5] = matrix[2][2].planes.get(4).color;

                    color_array[6] = matrix[2][2].planes.get(1).color;
                    color_array[7] = matrix[1][2].planes.get(1).color;
                    color_array[8] = matrix[0][2].planes.get(1).color;

                    color_array[9] = matrix[0][2].planes.get(2).color;
                    color_array[10] = matrix[0][1].planes.get(2).color;
                    color_array[11] = matrix[0][0].planes.get(2).color;

                    color_array = rotate(color_array);
                    color_array = rotate(color_array);
                    color_array = rotate(color_array);

                    color_array = rotate(color_array);
                    color_array = rotate(color_array);
                    color_array = rotate(color_array);

                    color_array = rotate(color_array);
                    color_array = rotate(color_array);
                    color_array = rotate(color_array);

                    matrix[0][0].planes.get(5).color = color_array[0];
                    matrix[1][0].planes.get(5).color = color_array[1];
                    matrix[2][0].planes.get(5).color = color_array[2];
                    matrix[2][0].planes.get(4).color = color_array[3];
                    matrix[2][1].planes.get(4).color = color_array[4];
                    matrix[2][2].planes.get(4).color = color_array[5];
                    matrix[2][2].planes.get(1).color = color_array[6];
                    matrix[1][2].planes.get(1).color = color_array[7];
                    matrix[0][2].planes.get(1).color = color_array[8];
                    matrix[0][2].planes.get(2).color = color_array[9];
                    matrix[0][1].planes.get(2).color = color_array[10];
                    matrix[0][0].planes.get(2).color = color_array[11];

                    rotate(matrix, rubic.rubic_size);
                    rotate(matrix, rubic.rubic_size);
                    rotate(matrix, rubic.rubic_size);
                    int q = 0;
                    Color[] turn_array = new Color[9];
                    for (int i = 0; i < matrix.length; ++i) {
                        for (int j = 0; j < matrix.length; ++j) {
                            turn_array[q] = matrix[i][j].planes.get(3).color;
                            q++;
                        }
                    }
                    rotate(matrix, rubic.rubic_size);
                    q = 0;
                    for (int i = 0; i < matrix.length; ++i) {
                        for (int j = 0; j < matrix.length; ++j) {
                            matrix[i][j].planes.get(3).color = turn_array[q];
                            q++;
                            matrix[i][j].angY = 0;
                        }
                    }
                    break;
                }
                case 51 -> {//f
                    Color[] color_array = new Color[12];

                    color_array[0] = matrix[0][2].planes.get(0).color;
                    color_array[1] = matrix[1][2].planes.get(0).color;
                    color_array[2] = matrix[2][2].planes.get(0).color;

                    color_array[3] = matrix[0][0].planes.get(2).color;
                    color_array[4] = matrix[0][1].planes.get(2).color;
                    color_array[5] = matrix[0][2].planes.get(2).color;

                    color_array[6] = matrix[0][0].planes.get(3).color;
                    color_array[7] = matrix[1][0].planes.get(3).color;
                    color_array[8] = matrix[2][0].planes.get(3).color;

                    color_array[9] = matrix[2][0].planes.get(4).color;
                    color_array[10] = matrix[2][1].planes.get(4).color;
                    color_array[11] = matrix[2][2].planes.get(4).color;

                    color_array = rotate(color_array);
                    color_array = rotate(color_array);
                    color_array = rotate(color_array);
                    color_array = rotate(color_array);
                    color_array = rotate(color_array);
                    color_array = rotate(color_array);
                    color_array = rotate(color_array);
                    color_array = rotate(color_array);
                    color_array = rotate(color_array);

                    matrix[0][2].planes.get(0).color = color_array[0];
                    matrix[1][2].planes.get(0).color = color_array[1];
                    matrix[2][2].planes.get(0).color = color_array[2];
                    matrix[0][0].planes.get(2).color = color_array[3];
                    matrix[0][1].planes.get(2).color = color_array[4];
                    matrix[0][2].planes.get(2).color = color_array[5];
                    matrix[0][0].planes.get(3).color = color_array[6];
                    matrix[1][0].planes.get(3).color = color_array[7];
                    matrix[2][0].planes.get(3).color = color_array[8];
                    matrix[2][0].planes.get(4).color = color_array[9];
                    matrix[2][1].planes.get(4).color = color_array[10];
                    matrix[2][2].planes.get(4).color = color_array[11];

                    rotate(matrix, rubic.rubic_size);
                    rotate(matrix, rubic.rubic_size);
                    rotate(matrix, rubic.rubic_size);
                    int q = 0;
                    Color[] turn_array = new Color[9];
                    for (int i = 0; i < matrix.length; ++i) {
                        for (int j = 0; j < matrix.length; ++j) {
                            turn_array[q] = matrix[i][j].planes.get(5).color;
                            q++;
                        }
                    }
                    rotate(matrix, rubic.rubic_size);
                    q = 0;
                    for (int i = 0; i < matrix.length; ++i) {
                        for (int j = 0; j < matrix.length; ++j) {
                            matrix[i][j].planes.get(5).color = turn_array[q];
                            q++;
                            matrix[i][j].angZ = 0;
                        }
                    }
                    break;
                }
                case 61 -> {//b
                    Color[] color_array = new Color[12];

                               /* matrix[0][0].planes.get(2).color = Color.CYAN;

                                matrix[0][2].planes.get(0).color = Color.MAGENTA;

                                matrix[2][2].planes.get(4).color = Color.PINK;

                                matrix[2][0].planes.get(3).color = Color.LIGHT_GRAY;*/
                    color_array[0] = matrix[2][2].planes.get(0).color;
                    color_array[1] = matrix[1][2].planes.get(0).color;
                    color_array[2] = matrix[0][2].planes.get(0).color;

                    color_array[3] = matrix[0][0].planes.get(2).color;
                    color_array[4] = matrix[0][1].planes.get(2).color;
                    color_array[5] = matrix[0][2].planes.get(2).color;

                    color_array[6] = matrix[2][0].planes.get(3).color;
                    color_array[7] = matrix[1][0].planes.get(3).color;
                    color_array[8] = matrix[0][0].planes.get(3).color;

                    color_array[9] = matrix[2][2].planes.get(4).color;
                    color_array[10] = matrix[2][1].planes.get(4).color;
                    color_array[11] = matrix[2][0].planes.get(4).color;

                    color_array = rotate(color_array);
                    color_array = rotate(color_array);
                    color_array = rotate(color_array);
                    color_array = rotate(color_array);
                    color_array = rotate(color_array);
                    color_array = rotate(color_array);
                    color_array = rotate(color_array);
                    color_array = rotate(color_array);
                    color_array = rotate(color_array);

                    matrix[2][2].planes.get(0).color = color_array[0];
                    matrix[1][2].planes.get(0).color = color_array[1];
                    matrix[0][2].planes.get(0).color = color_array[2];
                    matrix[0][0].planes.get(2).color = color_array[3];
                    matrix[0][1].planes.get(2).color = color_array[4];
                    matrix[0][2].planes.get(2).color = color_array[5];
                    matrix[2][0].planes.get(3).color = color_array[6];
                    matrix[1][0].planes.get(3).color = color_array[7];
                    matrix[0][0].planes.get(3).color = color_array[8];
                    matrix[2][2].planes.get(4).color = color_array[9];
                    matrix[2][1].planes.get(4).color = color_array[10];
                    matrix[2][0].planes.get(4).color = color_array[11];

                    rotate(matrix, rubic.rubic_size);
                    rotate(matrix, rubic.rubic_size);
                    rotate(matrix, rubic.rubic_size);
                    int q = 0;
                    Color[] turn_array = new Color[9];
                    for (int i = 0; i < matrix.length; ++i) {
                        for (int j = 0; j < matrix.length; ++j) {
                            turn_array[q] = matrix[i][j].planes.get(1).color;
                            q++;
                        }
                    }
                    rotate(matrix, rubic.rubic_size);
                                /*rotate(matrix, rubic.rubic_size);
                                rotate(matrix, rubic.rubic_size);*/
                    q = 0;
                    for (int i = 0; i < matrix.length; ++i) {
                        for (int j = 0; j < matrix.length; ++j) {
                            matrix[i][j].planes.get(1).color = turn_array[q];
                            q++;
                            matrix[i][j].angZ = 0;
                        }
                    }
                    break;
                }
            }
        }
    }
    public static String disbuild() {

        for (int o = 0; o < rubic.rubic_size; ++o) {
            for (int k = 0; k < rubic.rubic_size; ++k) {
                for (int l = 0; l < rubic.rubic_size; ++l) {
                    rubic.cube[o][k][l].angX = 0;
                    rubic.cube[o][k][l].angY = 0;
                    rubic.cube[o][k][l].angZ = 0;
                }
            }
        }

        Random r = new Random();
        StringBuilder formula = new StringBuilder();
        for (int i = 0; i < 20; ++i) {
            int com = r.nextInt(12);
            switch (com) {
                case 0 -> formula.append('R');
                case 1 -> formula.append('L');
                case 2 -> formula.append('U');
                case 3 -> formula.append('D');
                case 4 -> formula.append('F');
                case 5 -> formula.append('B');
                case 6 -> formula.append('r');
                case 7 -> formula.append('l');
                case 8 -> formula.append('u');
                case 9 -> formula.append('d');
                case 10 -> formula.append('f');
                case 11 -> formula.append('b');
            }
        }
        int command = 0;
        for (int u = 0; u < formula.length(); ++u) {
            char c = formula.charAt(u);
            switch (c) {
                case 'R' -> command = 1;
                case 'L' -> command = 2;
                case 'U' -> command = 3;
                case 'D' -> command = 4;
                case 'F' -> command = 5;
                case 'B' -> command = 6;
                case 'r' -> command = 11;
                case 'l' -> command = 21;
                case 'u' -> command = 31;
                case 'd' -> command = 41;
                case 'f' -> command = 51;
                case 'b' -> command = 61;
            }

            Shape[][] matrix = new Shape[rubic.rubic_size][rubic.rubic_size];
            boolean drawprocess = true;
            for (int o = 0; o < rubic.rubic_size; ++o) {
                for (int k = 0; k < rubic.rubic_size; ++k) {
                    for (int l = 0; l < rubic.rubic_size; ++l) {
                        switch (command) {
                            // "RLUDFBrludfb"
                            case 1 -> {
                                if (o == 2) {
                                    //drawprocess = scene.RotateX(5, rubic.cube[o][k][l]);//R
                                    matrix[k][l] = rubic.cube[2][k][l];
                                }
                                break;
                            }
                            case 2 -> {
                                if (o == 0) {
                                    //drawprocess = scene.RotateX(5, rubic.cube[o][k][l]);//L
                                    matrix[k][l] = rubic.cube[o][k][l];
                                }
                                break;
                            }
                            case 3 -> {
                                if (k == 2) {
                                    //drawprocess = scene.RotateY(5, rubic.cube[o][k][l]);//U
                                    matrix[o][l] = rubic.cube[o][k][l];
                                }
                                break;
                            }
                            case 4 -> {
                                if (k == 0) {
                                    //drawprocess = scene.RotateY(5, rubic.cube[o][k][l]);//D
                                    matrix[o][l] = rubic.cube[o][k][l];
                                }
                                break;
                            }
                            case 5 -> {
                                if (l == 0) {
                                    //drawprocess = scene.RotateZ(5, rubic.cube[o][k][l]);//F
                                    matrix[o][k] = rubic.cube[o][k][l];
                                }
                                break;
                            }
                            case 6 -> {
                                if (l == 2) {
                                    //drawprocess = scene.RotateZ(5, rubic.cube[o][k][l]);//B
                                    matrix[o][k] = rubic.cube[o][k][l];
                                }
                                break;
                            }
                            case 11 -> {
                                if (o == 2) {
                                    //drawprocess = scene.RotateX(-5, rubic.cube[o][k][l]);//r
                                    matrix[k][l] = rubic.cube[o][k][l];
                                }
                                break;
                            }
                            case 21 -> {
                                if (o == 0) {
                                    //drawprocess = scene.RotateX(-5, rubic.cube[o][k][l]);//l
                                    matrix[k][l] = rubic.cube[o][k][l];
                                }
                                break;
                            }
                            case 31 -> {
                                if (k == 2) {
                                    //drawprocess = scene.RotateY(-5, rubic.cube[o][k][l]);//u
                                    matrix[o][l] = rubic.cube[o][k][l];
                                }
                                break;
                            }
                            case 41 -> {
                                if (k == 0) {
                                    //drawprocess = scene.RotateY(-5, rubic.cube[o][k][l]);//d
                                    matrix[o][l] = rubic.cube[o][k][l];
                                }
                                break;
                            }
                            case 51 -> {
                                if (l == 0) {
                                    //drawprocess = scene.RotateZ(-5, rubic.cube[o][k][l]);//f
                                    matrix[o][k] = rubic.cube[o][k][l];
                                }
                                break;
                            }
                            case 61 -> {
                                if (l == 2) {
                                    //drawprocess = scene.RotateZ(-5, rubic.cube[o][k][l]);//b
                                    matrix[o][k] = rubic.cube[o][k][l];
                                }
                                break;
                            }
                        }
                    }
                }
            }
            scene.repaint();
            switch (command) {
                case 1 -> {//R
                    Color[] color_array = new Color[12];

                    color_array[0] = matrix[0][0].planes.get(5).color;
                    color_array[1] = matrix[1][0].planes.get(5).color;
                    color_array[2] = matrix[2][0].planes.get(5).color;

                    color_array[3] = matrix[2][0].planes.get(0).color;
                    color_array[4] = matrix[2][1].planes.get(0).color;
                    color_array[5] = matrix[2][2].planes.get(0).color;

                    color_array[6] = matrix[2][2].planes.get(1).color;
                    color_array[7] = matrix[1][2].planes.get(1).color;
                    color_array[8] = matrix[0][2].planes.get(1).color;

                    color_array[9] = matrix[0][2].planes.get(3).color;
                    color_array[10] = matrix[0][1].planes.get(3).color;
                    color_array[11] = matrix[0][0].planes.get(3).color;

                    color_array = rotate(color_array);
                    color_array = rotate(color_array);
                    color_array = rotate(color_array);

                    color_array = rotate(color_array);
                    color_array = rotate(color_array);
                    color_array = rotate(color_array);

                    color_array = rotate(color_array);
                    color_array = rotate(color_array);
                    color_array = rotate(color_array);

                    matrix[0][0].planes.get(5).color = color_array[0];
                    matrix[1][0].planes.get(5).color = color_array[1];
                    matrix[2][0].planes.get(5).color = color_array[2];
                    matrix[2][0].planes.get(0).color = color_array[3];
                    matrix[2][1].planes.get(0).color = color_array[4];
                    matrix[2][2].planes.get(0).color = color_array[5];
                    matrix[2][2].planes.get(1).color = color_array[6];
                    matrix[1][2].planes.get(1).color = color_array[7];
                    matrix[0][2].planes.get(1).color = color_array[8];
                    matrix[0][2].planes.get(3).color = color_array[9];
                    matrix[0][1].planes.get(3).color = color_array[10];
                    matrix[0][0].planes.get(3).color = color_array[11];

                    rotate(matrix, rubic.rubic_size);
                    rotate(matrix, rubic.rubic_size);
                    rotate(matrix, rubic.rubic_size);
                    int q = 0;
                    Color[] turn_array = new Color[9];
                    for (int i = 0; i < matrix.length; ++i) {
                        for (int j = 0; j < matrix.length; ++j) {
                            turn_array[q] = matrix[i][j].planes.get(4).color;
                            q++;
                        }
                    }
                    rotate(matrix, rubic.rubic_size);
                    q = 0;
                    for (int i = 0; i < matrix.length; ++i) {
                        for (int j = 0; j < matrix.length; ++j) {
                            matrix[i][j].planes.get(4).color = turn_array[q];
                            q++;
                            matrix[i][j].angX = 0;
                        }
                    }
                    break;
                }
                case 2 -> {//L
                    Color[] color_array = new Color[12];

                    color_array[0] = matrix[0][0].planes.get(5).color;
                    color_array[1] = matrix[1][0].planes.get(5).color;
                    color_array[2] = matrix[2][0].planes.get(5).color;

                    color_array[3] = matrix[2][0].planes.get(0).color;
                    color_array[4] = matrix[2][1].planes.get(0).color;
                    color_array[5] = matrix[2][2].planes.get(0).color;

                    color_array[6] = matrix[2][2].planes.get(1).color;
                    color_array[7] = matrix[1][2].planes.get(1).color;
                    color_array[8] = matrix[0][2].planes.get(1).color;

                    color_array[9] = matrix[0][2].planes.get(3).color;
                    color_array[10] = matrix[0][1].planes.get(3).color;
                    color_array[11] = matrix[0][0].planes.get(3).color;

                    color_array = rotate(color_array);
                    color_array = rotate(color_array);
                    color_array = rotate(color_array);
                    color_array = rotate(color_array);
                    color_array = rotate(color_array);
                    color_array = rotate(color_array);
                    color_array = rotate(color_array);
                    color_array = rotate(color_array);
                    color_array = rotate(color_array);

                    matrix[0][0].planes.get(5).color = color_array[0];
                    matrix[1][0].planes.get(5).color = color_array[1];
                    matrix[2][0].planes.get(5).color = color_array[2];
                    matrix[2][0].planes.get(0).color = color_array[3];
                    matrix[2][1].planes.get(0).color = color_array[4];
                    matrix[2][2].planes.get(0).color = color_array[5];
                    matrix[2][2].planes.get(1).color = color_array[6];
                    matrix[1][2].planes.get(1).color = color_array[7];
                    matrix[0][2].planes.get(1).color = color_array[8];
                    matrix[0][2].planes.get(3).color = color_array[9];
                    matrix[0][1].planes.get(3).color = color_array[10];
                    matrix[0][0].planes.get(3).color = color_array[11];

                    rotate(matrix, rubic.rubic_size);
                    rotate(matrix, rubic.rubic_size);
                    rotate(matrix, rubic.rubic_size);
                    int q = 0;
                    Color[] turn_array = new Color[9];
                    for (int i = 0; i < matrix.length; ++i) {
                        for (int j = 0; j < matrix.length; ++j) {
                            turn_array[q] = matrix[i][j].planes.get(2).color;
                            q++;
                        }
                    }
                    rotate(matrix, rubic.rubic_size);
                    q = 0;
                    for (int i = 0; i < matrix.length; ++i) {
                        for (int j = 0; j < matrix.length; ++j) {
                            matrix[i][j].planes.get(2).color = turn_array[q];
                            q++;
                            matrix[i][j].angX = 0;
                        }
                    }
                    break;
                }
                case 3 -> {//U
                    Color[] color_array = new Color[12];

                    color_array[0] = matrix[0][0].planes.get(5).color;
                    color_array[1] = matrix[1][0].planes.get(5).color;
                    color_array[2] = matrix[2][0].planes.get(5).color;

                    color_array[3] = matrix[2][0].planes.get(4).color;
                    color_array[4] = matrix[2][1].planes.get(4).color;
                    color_array[5] = matrix[2][2].planes.get(4).color;

                    color_array[6] = matrix[2][2].planes.get(1).color;
                    color_array[7] = matrix[1][2].planes.get(1).color;
                    color_array[8] = matrix[0][2].planes.get(1).color;

                    color_array[9] = matrix[0][2].planes.get(2).color;
                    color_array[10] = matrix[0][1].planes.get(2).color;
                    color_array[11] = matrix[0][0].planes.get(2).color;

                    color_array = rotate(color_array);
                    color_array = rotate(color_array);
                    color_array = rotate(color_array);

                    matrix[0][0].planes.get(5).color = color_array[0];
                    matrix[1][0].planes.get(5).color = color_array[1];
                    matrix[2][0].planes.get(5).color = color_array[2];
                    matrix[2][0].planes.get(4).color = color_array[3];
                    matrix[2][1].planes.get(4).color = color_array[4];
                    matrix[2][2].planes.get(4).color = color_array[5];
                    matrix[2][2].planes.get(1).color = color_array[6];
                    matrix[1][2].planes.get(1).color = color_array[7];
                    matrix[0][2].planes.get(1).color = color_array[8];
                    matrix[0][2].planes.get(2).color = color_array[9];
                    matrix[0][1].planes.get(2).color = color_array[10];
                    matrix[0][0].planes.get(2).color = color_array[11];

                    rotate(matrix, rubic.rubic_size);
                    int q = 0;
                    Color[] turn_array = new Color[9];
                    Color[] turn_array_b = new Color[9];
                    for (int i = 0; i < matrix.length; ++i) {
                        for (int j = 0; j < matrix.length; ++j) {
                            turn_array[q] = matrix[i][j].planes.get(0).color;
                            turn_array_b[q] = matrix[i][j].planes.get(3).color;
                            q++;
                        }
                    }
                    rotate(matrix, rubic.rubic_size);
                    rotate(matrix, rubic.rubic_size);
                    rotate(matrix, rubic.rubic_size);
                    q = 0;
                    for (int i = 0; i < matrix.length; ++i) {
                        for (int j = 0; j < matrix.length; ++j) {
                            matrix[i][j].planes.get(0).color = turn_array[q];
                            matrix[i][j].planes.get(3).color = turn_array_b[q];
                            q++;
                            matrix[i][j].angY = 0;
                        }
                    }
                    break;
                }
                case 4 -> {//D
                    Color[] color_array = new Color[12];

                    color_array[0] = matrix[0][0].planes.get(5).color;
                    color_array[1] = matrix[1][0].planes.get(5).color;
                    color_array[2] = matrix[2][0].planes.get(5).color;

                    color_array[3] = matrix[2][0].planes.get(4).color;
                    color_array[4] = matrix[2][1].planes.get(4).color;
                    color_array[5] = matrix[2][2].planes.get(4).color;

                    color_array[6] = matrix[2][2].planes.get(1).color;
                    color_array[7] = matrix[1][2].planes.get(1).color;
                    color_array[8] = matrix[0][2].planes.get(1).color;

                    color_array[9] = matrix[0][2].planes.get(2).color;
                    color_array[10] = matrix[0][1].planes.get(2).color;
                    color_array[11] = matrix[0][0].planes.get(2).color;

                    color_array = rotate(color_array);
                    color_array = rotate(color_array);
                    color_array = rotate(color_array);

                    matrix[0][0].planes.get(5).color = color_array[0];
                    matrix[1][0].planes.get(5).color = color_array[1];
                    matrix[2][0].planes.get(5).color = color_array[2];
                    matrix[2][0].planes.get(4).color = color_array[3];
                    matrix[2][1].planes.get(4).color = color_array[4];
                    matrix[2][2].planes.get(4).color = color_array[5];
                    matrix[2][2].planes.get(1).color = color_array[6];
                    matrix[1][2].planes.get(1).color = color_array[7];
                    matrix[0][2].planes.get(1).color = color_array[8];
                    matrix[0][2].planes.get(2).color = color_array[9];
                    matrix[0][1].planes.get(2).color = color_array[10];
                    matrix[0][0].planes.get(2).color = color_array[11];

                    rotate(matrix, rubic.rubic_size);
                    int q = 0;
                    Color[] turn_array = new Color[9];
                    for (int i = 0; i < matrix.length; ++i) {
                        for (int j = 0; j < matrix.length; ++j) {
                            turn_array[q] = matrix[i][j].planes.get(3).color;
                            q++;
                        }
                    }
                    rotate(matrix, rubic.rubic_size);
                    rotate(matrix, rubic.rubic_size);
                    rotate(matrix, rubic.rubic_size);
                    q = 0;
                    for (int i = 0; i < matrix.length; ++i) {
                        for (int j = 0; j < matrix.length; ++j) {
                            matrix[i][j].planes.get(3).color = turn_array[q];
                            q++;
                            matrix[i][j].angY = 0;
                        }
                    }
                    break;
                }
                case 5 -> {//F
                    Color[] color_array = new Color[12];

                    color_array[0] = matrix[0][2].planes.get(0).color;
                    color_array[1] = matrix[1][2].planes.get(0).color;
                    color_array[2] = matrix[2][2].planes.get(0).color;

                    color_array[3] = matrix[0][0].planes.get(2).color;
                    color_array[4] = matrix[0][1].planes.get(2).color;
                    color_array[5] = matrix[0][2].planes.get(2).color;

                    color_array[6] = matrix[0][0].planes.get(3).color;
                    color_array[7] = matrix[1][0].planes.get(3).color;
                    color_array[8] = matrix[2][0].planes.get(3).color;

                    color_array[9] = matrix[2][0].planes.get(4).color;
                    color_array[10] = matrix[2][1].planes.get(4).color;
                    color_array[11] = matrix[2][2].planes.get(4).color;

                    color_array = rotate(color_array);
                    color_array = rotate(color_array);
                    color_array = rotate(color_array);

                    matrix[0][2].planes.get(0).color = color_array[0];
                    matrix[1][2].planes.get(0).color = color_array[1];
                    matrix[2][2].planes.get(0).color = color_array[2];
                    matrix[0][0].planes.get(2).color = color_array[3];
                    matrix[0][1].planes.get(2).color = color_array[4];
                    matrix[0][2].planes.get(2).color = color_array[5];
                    matrix[0][0].planes.get(3).color = color_array[6];
                    matrix[1][0].planes.get(3).color = color_array[7];
                    matrix[2][0].planes.get(3).color = color_array[8];
                    matrix[2][0].planes.get(4).color = color_array[9];
                    matrix[2][1].planes.get(4).color = color_array[10];
                    matrix[2][2].planes.get(4).color = color_array[11];

                    rotate(matrix, rubic.rubic_size);
                    int q = 0;
                    Color[] turn_array = new Color[9];
                    for (int i = 0; i < matrix.length; ++i) {
                        for (int j = 0; j < matrix.length; ++j) {
                            turn_array[q] = matrix[i][j].planes.get(5).color;
                            q++;
                        }
                    }
                    rotate(matrix, rubic.rubic_size);
                    rotate(matrix, rubic.rubic_size);
                    rotate(matrix, rubic.rubic_size);
                    q = 0;
                    for (int i = 0; i < matrix.length; ++i) {
                        for (int j = 0; j < matrix.length; ++j) {
                            matrix[i][j].planes.get(5).color = turn_array[q];
                            q++;
                            matrix[i][j].angZ = 0;
                        }
                    }
                    break;
                }
                case 6 -> {//B
                    Color[] color_array = new Color[12];

                                /*matrix[0][0].planes.get(2).color = Color.CYAN;

                                matrix[0][2].planes.get(0).color = Color.MAGENTA;

                                matrix[2][2].planes.get(4).color = Color.PINK;

                                matrix[2][0].planes.get(3).color = Color.LIGHT_GRAY;*/

                    color_array[3] = matrix[0][0].planes.get(2).color;
                    color_array[4] = matrix[0][1].planes.get(2).color;
                    color_array[5] = matrix[0][2].planes.get(2).color;

                    color_array[2] = matrix[0][2].planes.get(0).color;
                    color_array[1] = matrix[1][2].planes.get(0).color;
                    color_array[0] = matrix[2][2].planes.get(0).color;

                    color_array[9] = matrix[2][2].planes.get(4).color;
                    color_array[10] = matrix[2][1].planes.get(4).color;
                    color_array[11] = matrix[2][0].planes.get(4).color;

                    color_array[6] = matrix[2][0].planes.get(3).color;
                    color_array[7] = matrix[1][0].planes.get(3).color;
                    color_array[8] = matrix[0][0].planes.get(3).color;

                    color_array = rotate(color_array);
                    color_array = rotate(color_array);
                    color_array = rotate(color_array);

                    matrix[2][2].planes.get(0).color = color_array[0];
                    matrix[1][2].planes.get(0).color = color_array[1];
                    matrix[0][2].planes.get(0).color = color_array[2];
                    matrix[0][0].planes.get(2).color = color_array[3];
                    matrix[0][1].planes.get(2).color = color_array[4];
                    matrix[0][2].planes.get(2).color = color_array[5];
                    matrix[2][0].planes.get(3).color = color_array[6];
                    matrix[1][0].planes.get(3).color = color_array[7];
                    matrix[0][0].planes.get(3).color = color_array[8];
                    matrix[2][2].planes.get(4).color = color_array[9];
                    matrix[2][1].planes.get(4).color = color_array[10];
                    matrix[2][0].planes.get(4).color = color_array[11];

                    rotate(matrix, rubic.rubic_size);
                                /*rotate(matrix, rubic.rubic_size);
                                rotate(matrix, rubic.rubic_size);*/
                    int q = 0;
                    Color[] turn_array = new Color[9];
                    for (int i = 0; i < matrix.length; ++i) {
                        for (int j = 0; j < matrix.length; ++j) {
                            turn_array[q] = matrix[i][j].planes.get(1).color;
                            q++;
                        }
                    }
                    rotate(matrix, rubic.rubic_size);
                    rotate(matrix, rubic.rubic_size);
                    rotate(matrix, rubic.rubic_size);
                    q = 0;
                    for (int i = 0; i < matrix.length; ++i) {
                        for (int j = 0; j < matrix.length; ++j) {
                            matrix[i][j].planes.get(1).color = turn_array[q];
                            q++;
                            matrix[i][j].angZ = 0;
                        }
                    }
                    break;
                }
                case 11 -> {//r
                    Color[] color_array = new Color[12];

                    color_array[0] = matrix[0][0].planes.get(5).color;
                    color_array[1] = matrix[1][0].planes.get(5).color;
                    color_array[2] = matrix[2][0].planes.get(5).color;

                    color_array[3] = matrix[2][0].planes.get(0).color;
                    color_array[4] = matrix[2][1].planes.get(0).color;
                    color_array[5] = matrix[2][2].planes.get(0).color;

                    color_array[6] = matrix[2][2].planes.get(1).color;
                    color_array[7] = matrix[1][2].planes.get(1).color;
                    color_array[8] = matrix[0][2].planes.get(1).color;

                    color_array[9] = matrix[0][2].planes.get(3).color;
                    color_array[10] = matrix[0][1].planes.get(3).color;
                    color_array[11] = matrix[0][0].planes.get(3).color;

                    color_array = rotate(color_array);
                    color_array = rotate(color_array);
                    color_array = rotate(color_array);

                    matrix[0][0].planes.get(5).color = color_array[0];
                    matrix[1][0].planes.get(5).color = color_array[1];
                    matrix[2][0].planes.get(5).color = color_array[2];
                    matrix[2][0].planes.get(0).color = color_array[3];
                    matrix[2][1].planes.get(0).color = color_array[4];
                    matrix[2][2].planes.get(0).color = color_array[5];
                    matrix[2][2].planes.get(1).color = color_array[6];
                    matrix[1][2].planes.get(1).color = color_array[7];
                    matrix[0][2].planes.get(1).color = color_array[8];
                    matrix[0][2].planes.get(3).color = color_array[9];
                    matrix[0][1].planes.get(3).color = color_array[10];
                    matrix[0][0].planes.get(3).color = color_array[11];

                    rotate(matrix, rubic.rubic_size);
                    int q = 0;
                    Color[] turn_array = new Color[9];
                    for (int i = 0; i < matrix.length; ++i) {
                        for (int j = 0; j < matrix.length; ++j) {
                            turn_array[q] = matrix[i][j].planes.get(4).color;
                            q++;
                        }
                    }
                    rotate(matrix, rubic.rubic_size);
                    rotate(matrix, rubic.rubic_size);
                    rotate(matrix, rubic.rubic_size);
                    q = 0;
                    for (int i = 0; i < matrix.length; ++i) {
                        for (int j = 0; j < matrix.length; ++j) {
                            matrix[i][j].planes.get(4).color = turn_array[q];
                            q++;
                            matrix[i][j].angX = 0;
                        }
                    }
                    break;
                }
                case 21 -> {//r
                    Color[] color_array = new Color[12];

                    color_array[0] = matrix[0][0].planes.get(5).color;
                    color_array[1] = matrix[1][0].planes.get(5).color;
                    color_array[2] = matrix[2][0].planes.get(5).color;

                    color_array[3] = matrix[2][0].planes.get(0).color;
                    color_array[4] = matrix[2][1].planes.get(0).color;
                    color_array[5] = matrix[2][2].planes.get(0).color;

                    color_array[6] = matrix[2][2].planes.get(1).color;
                    color_array[7] = matrix[1][2].planes.get(1).color;
                    color_array[8] = matrix[0][2].planes.get(1).color;

                    color_array[9] = matrix[0][2].planes.get(3).color;
                    color_array[10] = matrix[0][1].planes.get(3).color;
                    color_array[11] = matrix[0][0].planes.get(3).color;

                    color_array = rotate(color_array);
                    color_array = rotate(color_array);
                    color_array = rotate(color_array);

                    matrix[0][0].planes.get(5).color = color_array[0];
                    matrix[1][0].planes.get(5).color = color_array[1];
                    matrix[2][0].planes.get(5).color = color_array[2];
                    matrix[2][0].planes.get(0).color = color_array[3];
                    matrix[2][1].planes.get(0).color = color_array[4];
                    matrix[2][2].planes.get(0).color = color_array[5];
                    matrix[2][2].planes.get(1).color = color_array[6];
                    matrix[1][2].planes.get(1).color = color_array[7];
                    matrix[0][2].planes.get(1).color = color_array[8];
                    matrix[0][2].planes.get(3).color = color_array[9];
                    matrix[0][1].planes.get(3).color = color_array[10];
                    matrix[0][0].planes.get(3).color = color_array[11];

                    rotate(matrix, rubic.rubic_size);
                    int q = 0;
                    Color[] turn_array = new Color[9];
                    for (int i = 0; i < matrix.length; ++i) {
                        for (int j = 0; j < matrix.length; ++j) {
                            turn_array[q] = matrix[i][j].planes.get(2).color;
                            q++;
                        }
                    }
                    rotate(matrix, rubic.rubic_size);
                    rotate(matrix, rubic.rubic_size);
                    rotate(matrix, rubic.rubic_size);
                    q = 0;
                    for (int i = 0; i < matrix.length; ++i) {
                        for (int j = 0; j < matrix.length; ++j) {
                            matrix[i][j].planes.get(2).color = turn_array[q];
                            q++;
                            matrix[i][j].angX = 0;
                        }
                    }
                    break;
                }
                case 31 -> {//u
                    Color[] color_array = new Color[12];

                    color_array[0] = matrix[0][0].planes.get(5).color;
                    color_array[1] = matrix[1][0].planes.get(5).color;
                    color_array[2] = matrix[2][0].planes.get(5).color;

                    color_array[3] = matrix[2][0].planes.get(4).color;
                    color_array[4] = matrix[2][1].planes.get(4).color;
                    color_array[5] = matrix[2][2].planes.get(4).color;

                    color_array[6] = matrix[2][2].planes.get(1).color;
                    color_array[7] = matrix[1][2].planes.get(1).color;
                    color_array[8] = matrix[0][2].planes.get(1).color;

                    color_array[9] = matrix[0][2].planes.get(2).color;
                    color_array[10] = matrix[0][1].planes.get(2).color;
                    color_array[11] = matrix[0][0].planes.get(2).color;

                    color_array = rotate(color_array);
                    color_array = rotate(color_array);
                    color_array = rotate(color_array);
                    color_array = rotate(color_array);
                    color_array = rotate(color_array);
                    color_array = rotate(color_array);
                    color_array = rotate(color_array);
                    color_array = rotate(color_array);
                    color_array = rotate(color_array);

                    matrix[0][0].planes.get(5).color = color_array[0];
                    matrix[1][0].planes.get(5).color = color_array[1];
                    matrix[2][0].planes.get(5).color = color_array[2];
                    matrix[2][0].planes.get(4).color = color_array[3];
                    matrix[2][1].planes.get(4).color = color_array[4];
                    matrix[2][2].planes.get(4).color = color_array[5];
                    matrix[2][2].planes.get(1).color = color_array[6];
                    matrix[1][2].planes.get(1).color = color_array[7];
                    matrix[0][2].planes.get(1).color = color_array[8];
                    matrix[0][2].planes.get(2).color = color_array[9];
                    matrix[0][1].planes.get(2).color = color_array[10];
                    matrix[0][0].planes.get(2).color = color_array[11];

                    rotate(matrix, rubic.rubic_size);
                    rotate(matrix, rubic.rubic_size);
                    rotate(matrix, rubic.rubic_size);
                    int q = 0;
                    Color[] turn_array = new Color[9];
                    Color[] turn_array_b = new Color[9];
                    for (int i = 0; i < matrix.length; ++i) {
                        for (int j = 0; j < matrix.length; ++j) {
                            turn_array[q] = matrix[i][j].planes.get(0).color;
                            turn_array_b[q] = matrix[i][j].planes.get(3).color;
                            q++;
                        }
                    }
                    rotate(matrix, rubic.rubic_size);
                    q = 0;
                    for (int i = 0; i < matrix.length; ++i) {
                        for (int j = 0; j < matrix.length; ++j) {
                            matrix[i][j].planes.get(0).color = turn_array[q];
                            matrix[i][j].planes.get(3).color = turn_array_b[q];
                            q++;
                            matrix[i][j].angY = 0;
                        }
                    }
                    break;
                }
                case 41 -> {//d
                    Color[] color_array = new Color[12];

                    color_array[0] = matrix[0][0].planes.get(5).color;
                    color_array[1] = matrix[1][0].planes.get(5).color;
                    color_array[2] = matrix[2][0].planes.get(5).color;

                    color_array[3] = matrix[2][0].planes.get(4).color;
                    color_array[4] = matrix[2][1].planes.get(4).color;
                    color_array[5] = matrix[2][2].planes.get(4).color;

                    color_array[6] = matrix[2][2].planes.get(1).color;
                    color_array[7] = matrix[1][2].planes.get(1).color;
                    color_array[8] = matrix[0][2].planes.get(1).color;

                    color_array[9] = matrix[0][2].planes.get(2).color;
                    color_array[10] = matrix[0][1].planes.get(2).color;
                    color_array[11] = matrix[0][0].planes.get(2).color;

                    color_array = rotate(color_array);
                    color_array = rotate(color_array);
                    color_array = rotate(color_array);

                    color_array = rotate(color_array);
                    color_array = rotate(color_array);
                    color_array = rotate(color_array);

                    color_array = rotate(color_array);
                    color_array = rotate(color_array);
                    color_array = rotate(color_array);

                    matrix[0][0].planes.get(5).color = color_array[0];
                    matrix[1][0].planes.get(5).color = color_array[1];
                    matrix[2][0].planes.get(5).color = color_array[2];
                    matrix[2][0].planes.get(4).color = color_array[3];
                    matrix[2][1].planes.get(4).color = color_array[4];
                    matrix[2][2].planes.get(4).color = color_array[5];
                    matrix[2][2].planes.get(1).color = color_array[6];
                    matrix[1][2].planes.get(1).color = color_array[7];
                    matrix[0][2].planes.get(1).color = color_array[8];
                    matrix[0][2].planes.get(2).color = color_array[9];
                    matrix[0][1].planes.get(2).color = color_array[10];
                    matrix[0][0].planes.get(2).color = color_array[11];

                    rotate(matrix, rubic.rubic_size);
                    rotate(matrix, rubic.rubic_size);
                    rotate(matrix, rubic.rubic_size);
                    int q = 0;
                    Color[] turn_array = new Color[9];
                    for (int i = 0; i < matrix.length; ++i) {
                        for (int j = 0; j < matrix.length; ++j) {
                            turn_array[q] = matrix[i][j].planes.get(3).color;
                            q++;
                        }
                    }
                    rotate(matrix, rubic.rubic_size);
                    q = 0;
                    for (int i = 0; i < matrix.length; ++i) {
                        for (int j = 0; j < matrix.length; ++j) {
                            matrix[i][j].planes.get(3).color = turn_array[q];
                            q++;
                            matrix[i][j].angY = 0;
                        }
                    }
                    break;
                }
                case 51 -> {//f
                    Color[] color_array = new Color[12];

                    color_array[0] = matrix[0][2].planes.get(0).color;
                    color_array[1] = matrix[1][2].planes.get(0).color;
                    color_array[2] = matrix[2][2].planes.get(0).color;

                    color_array[3] = matrix[0][0].planes.get(2).color;
                    color_array[4] = matrix[0][1].planes.get(2).color;
                    color_array[5] = matrix[0][2].planes.get(2).color;

                    color_array[6] = matrix[0][0].planes.get(3).color;
                    color_array[7] = matrix[1][0].planes.get(3).color;
                    color_array[8] = matrix[2][0].planes.get(3).color;

                    color_array[9] = matrix[2][0].planes.get(4).color;
                    color_array[10] = matrix[2][1].planes.get(4).color;
                    color_array[11] = matrix[2][2].planes.get(4).color;

                    color_array = rotate(color_array);
                    color_array = rotate(color_array);
                    color_array = rotate(color_array);
                    color_array = rotate(color_array);
                    color_array = rotate(color_array);
                    color_array = rotate(color_array);
                    color_array = rotate(color_array);
                    color_array = rotate(color_array);
                    color_array = rotate(color_array);

                    matrix[0][2].planes.get(0).color = color_array[0];
                    matrix[1][2].planes.get(0).color = color_array[1];
                    matrix[2][2].planes.get(0).color = color_array[2];
                    matrix[0][0].planes.get(2).color = color_array[3];
                    matrix[0][1].planes.get(2).color = color_array[4];
                    matrix[0][2].planes.get(2).color = color_array[5];
                    matrix[0][0].planes.get(3).color = color_array[6];
                    matrix[1][0].planes.get(3).color = color_array[7];
                    matrix[2][0].planes.get(3).color = color_array[8];
                    matrix[2][0].planes.get(4).color = color_array[9];
                    matrix[2][1].planes.get(4).color = color_array[10];
                    matrix[2][2].planes.get(4).color = color_array[11];

                    rotate(matrix, rubic.rubic_size);
                    rotate(matrix, rubic.rubic_size);
                    rotate(matrix, rubic.rubic_size);
                    int q = 0;
                    Color[] turn_array = new Color[9];
                    for (int i = 0; i < matrix.length; ++i) {
                        for (int j = 0; j < matrix.length; ++j) {
                            turn_array[q] = matrix[i][j].planes.get(5).color;
                            q++;
                        }
                    }
                    rotate(matrix, rubic.rubic_size);
                    q = 0;
                    for (int i = 0; i < matrix.length; ++i) {
                        for (int j = 0; j < matrix.length; ++j) {
                            matrix[i][j].planes.get(5).color = turn_array[q];
                            q++;
                            matrix[i][j].angZ = 0;
                        }
                    }
                    break;
                }
                case 61 -> {//b
                    Color[] color_array = new Color[12];

                               /* matrix[0][0].planes.get(2).color = Color.CYAN;

                                matrix[0][2].planes.get(0).color = Color.MAGENTA;

                                matrix[2][2].planes.get(4).color = Color.PINK;

                                matrix[2][0].planes.get(3).color = Color.LIGHT_GRAY;*/
                    color_array[0] = matrix[2][2].planes.get(0).color;
                    color_array[1] = matrix[1][2].planes.get(0).color;
                    color_array[2] = matrix[0][2].planes.get(0).color;

                    color_array[3] = matrix[0][0].planes.get(2).color;
                    color_array[4] = matrix[0][1].planes.get(2).color;
                    color_array[5] = matrix[0][2].planes.get(2).color;

                    color_array[6] = matrix[2][0].planes.get(3).color;
                    color_array[7] = matrix[1][0].planes.get(3).color;
                    color_array[8] = matrix[0][0].planes.get(3).color;

                    color_array[9] = matrix[2][2].planes.get(4).color;
                    color_array[10] = matrix[2][1].planes.get(4).color;
                    color_array[11] = matrix[2][0].planes.get(4).color;

                    color_array = rotate(color_array);
                    color_array = rotate(color_array);
                    color_array = rotate(color_array);
                    color_array = rotate(color_array);
                    color_array = rotate(color_array);
                    color_array = rotate(color_array);
                    color_array = rotate(color_array);
                    color_array = rotate(color_array);
                    color_array = rotate(color_array);

                    matrix[2][2].planes.get(0).color = color_array[0];
                    matrix[1][2].planes.get(0).color = color_array[1];
                    matrix[0][2].planes.get(0).color = color_array[2];
                    matrix[0][0].planes.get(2).color = color_array[3];
                    matrix[0][1].planes.get(2).color = color_array[4];
                    matrix[0][2].planes.get(2).color = color_array[5];
                    matrix[2][0].planes.get(3).color = color_array[6];
                    matrix[1][0].planes.get(3).color = color_array[7];
                    matrix[0][0].planes.get(3).color = color_array[8];
                    matrix[2][2].planes.get(4).color = color_array[9];
                    matrix[2][1].planes.get(4).color = color_array[10];
                    matrix[2][0].planes.get(4).color = color_array[11];

                    rotate(matrix, rubic.rubic_size);
                    rotate(matrix, rubic.rubic_size);
                    rotate(matrix, rubic.rubic_size);
                    int q = 0;
                    Color[] turn_array = new Color[9];
                    for (int i = 0; i < matrix.length; ++i) {
                        for (int j = 0; j < matrix.length; ++j) {
                            turn_array[q] = matrix[i][j].planes.get(1).color;
                            q++;
                        }
                    }
                    rotate(matrix, rubic.rubic_size);
                                /*rotate(matrix, rubic.rubic_size);
                                rotate(matrix, rubic.rubic_size);*/
                    q = 0;
                    for (int i = 0; i < matrix.length; ++i) {
                        for (int j = 0; j < matrix.length; ++j) {
                            matrix[i][j].planes.get(1).color = turn_array[q];
                            q++;
                            matrix[i][j].angZ = 0;
                        }
                    }
                    break;
                }
            }
        }
        scene.repaint();
        String tmp = "";
        for(int i = formula.length()-1; i >= 0; --i){
            if(Character.isLowerCase(formula.charAt(i)))tmp+=Character.toUpperCase(formula.charAt(i));
            else if(Character.isUpperCase(formula.charAt(i)))tmp+=Character.toLowerCase(formula.charAt(i));
        }
        return tmp;
    }

    public void ChangeColor(char command){
        Color[] color_array = new Color[4];
        Color[] turn_array = new Color[6];
        for (int o = 0; o < rubic.rubic_size; ++o) {
            for (int k = 0; k < rubic.rubic_size; ++k) {
                for (int l = 0; l < rubic.rubic_size; ++l) {
                    if(command == 'U') {
                        if(k == 2) {
                            color_array[0] = rubic.cube[o][k][l].planes.get(5).color;
                            color_array[1] = rubic.cube[o][k][l].planes.get(4).color;
                            color_array[2] = rubic.cube[o][k][l].planes.get(1).color;
                            color_array[3] = rubic.cube[o][k][l].planes.get(2).color;

                            color_array = rotate(color_array);
                            rubic.cube[o][k][l].planes.get(5).color = color_array[0];
                            rubic.cube[o][k][l].planes.get(4).color = color_array[1];
                            rubic.cube[o][k][l].planes.get(1).color = color_array[2];
                            rubic.cube[o][k][l].planes.get(2).color = color_array[3];
                        }
                    }
                    else if(command == 'R') {
                        if(o == 2) {
                            color_array[0] = rubic.cube[o][k][l].planes.get(5).color;
                            color_array[1] = rubic.cube[o][k][l].planes.get(0).color;
                            color_array[2] = rubic.cube[o][k][l].planes.get(1).color;
                            color_array[3] = rubic.cube[o][k][l].planes.get(3).color;

                            color_array = rotate(color_array);
                            rubic.cube[o][k][l].planes.get(5).color = color_array[0];
                            rubic.cube[o][k][l].planes.get(0).color = color_array[1];
                            rubic.cube[o][k][l].planes.get(1).color = color_array[2];
                            rubic.cube[o][k][l].planes.get(3).color = color_array[3];
                        }
                    }
                }
            }
        }
    }
    // "RLUDFBrludfb"
    public static void Build(String formula) {
        buildprocess = true;

        for (int o = 0; o < rubic.rubic_size; ++o) {
            for (int k = 0; k < rubic.rubic_size; ++k) {
                for (int l = 0; l < rubic.rubic_size; ++l) {
                    rubic.cube[o][k][l].angX = 0;
                    rubic.cube[o][k][l].angY = 0;
                    rubic.cube[o][k][l].angZ = 0;
                }
            }
        }

        int command = 0;
        for(int u = 0; u < formula.length(); ++u) {
            char c = formula.charAt(u);
            switch (c) {
                case 'R':
                    command = 1;
                    break;
                case 'L':
                    command = 2;
                    break;
                case 'U':
                    command = 3;
                    break;
                case 'D':
                    command = 4;
                    break;
                case 'F':
                    command = 5;
                    break;
                case 'B':
                    command = 6;
                    break;
                case 'r':
                    command = 11;
                    break;
                case 'l':
                    command = 21;
                    break;
                case 'u':
                    command = 31;
                    break;
                case 'd':
                    command = 41;
                    break;
                case 'f':
                    command = 51;
                    break;
                case 'b':
                    command = 61;
                    break;
            }
            int finalCommand = command;
            //final Toolkit tk = Toolkit.getDefaultToolkit();

            long start_time = 0;
            long end_time = 0;
            long summ_time = 0;
            boolean fl = false;
            while(true){
                if(fl) break;
                end_time = System.currentTimeMillis();
                summ_time += end_time - start_time;
                if(summ_time >= 50){
                    //tk.sync();
                    Shape[][] matrix = new Shape[rubic.rubic_size][rubic.rubic_size];
                    boolean drawprocess = true;
                    for (int o = 0; o < rubic.rubic_size; ++o) {
                        for (int k = 0; k < rubic.rubic_size; ++k) {
                            for (int l = 0; l < rubic.rubic_size; ++l) {
                                switch (finalCommand) {
                                    // "RLUDFBrludfb"
                                    case 1 -> {
                                        if (o == 2) {
                                            drawprocess = scene.RotateX(5, rubic.cube[o][k][l]);//R
                                            matrix[k][l] = rubic.cube[2][k][l];
                                        }
                                        break;
                                    }
                                    case 2 -> {
                                        if (o == 0) {
                                            drawprocess = scene.RotateX(5, rubic.cube[o][k][l]);//L
                                            matrix[k][l] = rubic.cube[o][k][l];
                                        }
                                        break;
                                    }
                                    case 3 -> {
                                        if (k == 2) {
                                            drawprocess = scene.RotateY(5, rubic.cube[o][k][l]);//U
                                            matrix[o][l] = rubic.cube[o][k][l];
                                        }
                                        break;
                                    }
                                    case 4 -> {
                                        if (k == 0) {
                                            drawprocess = scene.RotateY(5, rubic.cube[o][k][l]);//D
                                            matrix[o][l] = rubic.cube[o][k][l];
                                        }
                                        break;
                                    }
                                    case 5 -> {
                                        if (l == 0) {
                                            drawprocess = scene.RotateZ(5, rubic.cube[o][k][l]);//F
                                            matrix[o][k] = rubic.cube[o][k][l];
                                        }
                                        break;
                                    }
                                    case 6 -> {
                                        if (l == 2) {
                                            drawprocess = scene.RotateZ(5, rubic.cube[o][k][l]);//B
                                            matrix[o][k] = rubic.cube[o][k][l];
                                        }
                                        break;
                                    }
                                    case 11 -> {
                                        if (o == 2) {
                                            drawprocess = scene.RotateX(-5, rubic.cube[o][k][l]);//r
                                            matrix[k][l] = rubic.cube[o][k][l];
                                        }
                                        break;
                                    }
                                    case 21 -> {
                                        if (o == 0) {
                                            drawprocess = scene.RotateX(-5, rubic.cube[o][k][l]);//l
                                            matrix[k][l] = rubic.cube[o][k][l];
                                        }
                                        break;
                                    }
                                    case 31 -> {
                                        if (k == 2) {
                                            drawprocess = scene.RotateY(-5, rubic.cube[o][k][l]);//u
                                            matrix[o][l] = rubic.cube[o][k][l];
                                        }
                                        break;
                                    }
                                    case 41 -> {
                                        if (k == 0) {
                                            drawprocess = scene.RotateY(-5, rubic.cube[o][k][l]);//d
                                            matrix[o][l] = rubic.cube[o][k][l];
                                        }
                                        break;
                                    }
                                    case 51 -> {
                                        if (l == 0) {
                                            drawprocess = scene.RotateZ(-5, rubic.cube[o][k][l]);//f
                                            matrix[o][k] = rubic.cube[o][k][l];
                                        }
                                        break;
                                    }
                                    case 61 -> {
                                        if (l == 2) {
                                            drawprocess = scene.RotateZ(-5, rubic.cube[o][k][l]);//b
                                            matrix[o][k] = rubic.cube[o][k][l];
                                        }
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    scene.repaint();
                    if (!drawprocess) {
                        switch (finalCommand){
                            case 1 -> {//R
                                Color[] color_array = new Color[12];

                                color_array[0] = matrix[0][0].planes.get(5).color;
                                color_array[1] = matrix[1][0].planes.get(5).color;
                                color_array[2] = matrix[2][0].planes.get(5).color;

                                color_array[3] = matrix[2][0].planes.get(0).color;
                                color_array[4] = matrix[2][1].planes.get(0).color;
                                color_array[5] = matrix[2][2].planes.get(0).color;

                                color_array[6] = matrix[2][2].planes.get(1).color;
                                color_array[7] = matrix[1][2].planes.get(1).color;
                                color_array[8] = matrix[0][2].planes.get(1).color;

                                color_array[9] =  matrix[0][2].planes.get(3).color;
                                color_array[10] = matrix[0][1].planes.get(3).color;
                                color_array[11] = matrix[0][0].planes.get(3).color;

                                color_array = rotate(color_array);
                                color_array = rotate(color_array);
                                color_array = rotate(color_array);

                                color_array = rotate(color_array);
                                color_array = rotate(color_array);
                                color_array = rotate(color_array);

                                color_array = rotate(color_array);
                                color_array = rotate(color_array);
                                color_array = rotate(color_array);

                                matrix[0][0].planes.get(5).color = color_array[0];
                                matrix[1][0].planes.get(5).color = color_array[1];
                                matrix[2][0].planes.get(5).color = color_array[2];
                                matrix[2][0].planes.get(0).color = color_array[3];
                                matrix[2][1].planes.get(0).color = color_array[4];
                                matrix[2][2].planes.get(0).color = color_array[5];
                                matrix[2][2].planes.get(1).color = color_array[6];
                                matrix[1][2].planes.get(1).color = color_array[7];
                                matrix[0][2].planes.get(1).color = color_array[8];
                                matrix[0][2].planes.get(3).color = color_array[9];
                                matrix[0][1].planes.get(3).color = color_array[10];
                                matrix[0][0].planes.get(3).color = color_array[11];

                                rotate(matrix, rubic.rubic_size);
                                rotate(matrix, rubic.rubic_size);
                                rotate(matrix, rubic.rubic_size);
                                int q = 0;
                                Color[] turn_array = new Color[9];
                                for (int i = 0; i < matrix.length; ++i) {
                                    for (int j = 0; j < matrix.length; ++j) {
                                        turn_array[q] = matrix[i][j].planes.get(4).color;
                                        q++;
                                    }
                                }
                                rotate(matrix, rubic.rubic_size);
                                q=0;
                                for (int i = 0; i < matrix.length; ++i) {
                                    for (int j = 0; j < matrix.length; ++j) {
                                        matrix[i][j].planes.get(4).color = turn_array[q];
                                        q++;
                                        matrix[i][j].angX = 0;
                                    }
                                }
                                break;
                            }
                            case 2 ->{//L
                                Color[] color_array = new Color[12];

                                color_array[0] = matrix[0][0].planes.get(5).color;
                                color_array[1] = matrix[1][0].planes.get(5).color;
                                color_array[2] = matrix[2][0].planes.get(5).color;

                                color_array[3] = matrix[2][0].planes.get(0).color;
                                color_array[4] = matrix[2][1].planes.get(0).color;
                                color_array[5] = matrix[2][2].planes.get(0).color;

                                color_array[6] = matrix[2][2].planes.get(1).color;
                                color_array[7] = matrix[1][2].planes.get(1).color;
                                color_array[8] = matrix[0][2].planes.get(1).color;

                                color_array[9] =  matrix[0][2].planes.get(3).color;
                                color_array[10] = matrix[0][1].planes.get(3).color;
                                color_array[11] = matrix[0][0].planes.get(3).color;

                                color_array = rotate(color_array);
                                color_array = rotate(color_array);
                                color_array = rotate(color_array);
                                color_array = rotate(color_array);
                                color_array = rotate(color_array);
                                color_array = rotate(color_array);
                                color_array = rotate(color_array);
                                color_array = rotate(color_array);
                                color_array = rotate(color_array);

                                matrix[0][0].planes.get(5).color = color_array[0];
                                matrix[1][0].planes.get(5).color = color_array[1];
                                matrix[2][0].planes.get(5).color = color_array[2];
                                matrix[2][0].planes.get(0).color = color_array[3];
                                matrix[2][1].planes.get(0).color = color_array[4];
                                matrix[2][2].planes.get(0).color = color_array[5];
                                matrix[2][2].planes.get(1).color = color_array[6];
                                matrix[1][2].planes.get(1).color = color_array[7];
                                matrix[0][2].planes.get(1).color = color_array[8];
                                matrix[0][2].planes.get(3).color = color_array[9];
                                matrix[0][1].planes.get(3).color = color_array[10];
                                matrix[0][0].planes.get(3).color = color_array[11];

                                rotate(matrix, rubic.rubic_size);
                                rotate(matrix, rubic.rubic_size);
                                rotate(matrix, rubic.rubic_size);
                                int q = 0;
                                Color[] turn_array = new Color[9];
                                for (int i = 0; i < matrix.length; ++i) {
                                    for (int j = 0; j < matrix.length; ++j) {
                                        turn_array[q] = matrix[i][j].planes.get(2).color;
                                        q++;
                                    }
                                }
                                rotate(matrix, rubic.rubic_size);
                                q=0;
                                for (int i = 0; i < matrix.length; ++i) {
                                    for (int j = 0; j < matrix.length; ++j) {
                                        matrix[i][j].planes.get(2).color = turn_array[q];
                                        q++;
                                        matrix[i][j].angX = 0;
                                    }
                                }
                                break;
                            }
                            case 3 ->{//U
                                Color[] color_array = new Color[12];

                                color_array[0] = matrix[0][0].planes.get(5).color;
                                color_array[1] = matrix[1][0].planes.get(5).color;
                                color_array[2] = matrix[2][0].planes.get(5).color;

                                color_array[3] = matrix[2][0].planes.get(4).color;
                                color_array[4] = matrix[2][1].planes.get(4).color;
                                color_array[5] = matrix[2][2].planes.get(4).color;

                                color_array[6] = matrix[2][2].planes.get(1).color;
                                color_array[7] = matrix[1][2].planes.get(1).color;
                                color_array[8] = matrix[0][2].planes.get(1).color;

                                color_array[9] =  matrix[0][2].planes.get(2).color;
                                color_array[10] = matrix[0][1].planes.get(2).color;
                                color_array[11] = matrix[0][0].planes.get(2).color;

                                color_array = rotate(color_array);
                                color_array = rotate(color_array);
                                color_array = rotate(color_array);

                                matrix[0][0].planes.get(5).color = color_array[0];
                                matrix[1][0].planes.get(5).color = color_array[1];
                                matrix[2][0].planes.get(5).color = color_array[2];
                                matrix[2][0].planes.get(4).color = color_array[3];
                                matrix[2][1].planes.get(4).color = color_array[4];
                                matrix[2][2].planes.get(4).color = color_array[5];
                                matrix[2][2].planes.get(1).color = color_array[6];
                                matrix[1][2].planes.get(1).color = color_array[7];
                                matrix[0][2].planes.get(1).color = color_array[8];
                                matrix[0][2].planes.get(2).color = color_array[9];
                                matrix[0][1].planes.get(2).color = color_array[10];
                                matrix[0][0].planes.get(2).color = color_array[11];

                                rotate(matrix, rubic.rubic_size);
                                int q = 0;
                                Color[] turn_array = new Color[9];
                                Color[] turn_array_b = new Color[9];
                                for (int i = 0; i < matrix.length; ++i) {
                                    for (int j = 0; j < matrix.length; ++j) {
                                        turn_array[q] = matrix[i][j].planes.get(0).color;
                                        turn_array_b[q] = matrix[i][j].planes.get(3).color;
                                        q++;
                                    }
                                }
                                rotate(matrix, rubic.rubic_size);
                                rotate(matrix, rubic.rubic_size);
                                rotate(matrix, rubic.rubic_size);
                                q = 0;
                                for (int i = 0; i < matrix.length; ++i) {
                                    for (int j = 0; j < matrix.length; ++j) {
                                        matrix[i][j].planes.get(0).color = turn_array[q];
                                        matrix[i][j].planes.get(3).color = turn_array_b[q];
                                        q++;
                                        matrix[i][j].angY = 0;
                                    }
                                }
                                break;
                            }
                            case 4 ->{//D
                                Color[] color_array = new Color[12];

                                color_array[0] = matrix[0][0].planes.get(5).color;
                                color_array[1] = matrix[1][0].planes.get(5).color;
                                color_array[2] = matrix[2][0].planes.get(5).color;

                                color_array[3] = matrix[2][0].planes.get(4).color;
                                color_array[4] = matrix[2][1].planes.get(4).color;
                                color_array[5] = matrix[2][2].planes.get(4).color;

                                color_array[6] = matrix[2][2].planes.get(1).color;
                                color_array[7] = matrix[1][2].planes.get(1).color;
                                color_array[8] = matrix[0][2].planes.get(1).color;

                                color_array[9] =  matrix[0][2].planes.get(2).color;
                                color_array[10] = matrix[0][1].planes.get(2).color;
                                color_array[11] = matrix[0][0].planes.get(2).color;

                                color_array = rotate(color_array);
                                color_array = rotate(color_array);
                                color_array = rotate(color_array);

                                matrix[0][0].planes.get(5).color = color_array[0];
                                matrix[1][0].planes.get(5).color = color_array[1];
                                matrix[2][0].planes.get(5).color = color_array[2];
                                matrix[2][0].planes.get(4).color = color_array[3];
                                matrix[2][1].planes.get(4).color = color_array[4];
                                matrix[2][2].planes.get(4).color = color_array[5];
                                matrix[2][2].planes.get(1).color = color_array[6];
                                matrix[1][2].planes.get(1).color = color_array[7];
                                matrix[0][2].planes.get(1).color = color_array[8];
                                matrix[0][2].planes.get(2).color = color_array[9];
                                matrix[0][1].planes.get(2).color = color_array[10];
                                matrix[0][0].planes.get(2).color = color_array[11];

                                rotate(matrix, rubic.rubic_size);
                                int q = 0;
                                Color[] turn_array = new Color[9];
                                for (int i = 0; i < matrix.length; ++i) {
                                    for (int j = 0; j < matrix.length; ++j) {
                                        turn_array[q] = matrix[i][j].planes.get(3).color;
                                        q++;
                                    }
                                }
                                rotate(matrix, rubic.rubic_size);
                                rotate(matrix, rubic.rubic_size);
                                rotate(matrix, rubic.rubic_size);
                                q=0;
                                for (int i = 0; i < matrix.length; ++i) {
                                    for (int j = 0; j < matrix.length; ++j) {
                                        matrix[i][j].planes.get(3).color = turn_array[q];
                                        q++;
                                        matrix[i][j].angY = 0;
                                    }
                                }
                                break;
                            }
                            case 5 ->{//F
                                Color[] color_array = new Color[12];

                                color_array[0] = matrix[0][2].planes.get(0).color;
                                color_array[1] = matrix[1][2].planes.get(0).color;
                                color_array[2] = matrix[2][2].planes.get(0).color;

                                color_array[3] = matrix[0][0].planes.get(2).color;
                                color_array[4] = matrix[0][1].planes.get(2).color;
                                color_array[5] = matrix[0][2].planes.get(2).color;

                                color_array[6] = matrix[0][0].planes.get(3).color;
                                color_array[7] = matrix[1][0].planes.get(3).color;
                                color_array[8] = matrix[2][0].planes.get(3).color;

                                color_array[9] = matrix[2][0].planes.get(4).color;
                                color_array[10]= matrix[2][1].planes.get(4).color;
                                color_array[11]= matrix[2][2].planes.get(4).color;

                                color_array = rotate(color_array);
                                color_array = rotate(color_array);
                                color_array = rotate(color_array);

                                matrix[0][2].planes.get(0).color = color_array[0];
                                matrix[1][2].planes.get(0).color = color_array[1];
                                matrix[2][2].planes.get(0).color = color_array[2];
                                matrix[0][0].planes.get(2).color = color_array[3];
                                matrix[0][1].planes.get(2).color = color_array[4];
                                matrix[0][2].planes.get(2).color = color_array[5];
                                matrix[0][0].planes.get(3).color = color_array[6];
                                matrix[1][0].planes.get(3).color = color_array[7];
                                matrix[2][0].planes.get(3).color = color_array[8];
                                matrix[2][0].planes.get(4).color = color_array[9];
                                matrix[2][1].planes.get(4).color = color_array[10];
                                matrix[2][2].planes.get(4).color = color_array[11];

                                rotate(matrix, rubic.rubic_size);
                                int q = 0;
                                Color[] turn_array = new Color[9];
                                for (int i = 0; i < matrix.length; ++i) {
                                    for (int j = 0; j < matrix.length; ++j) {
                                        turn_array[q] = matrix[i][j].planes.get(5).color;
                                        q++;
                                    }
                                }
                                rotate(matrix, rubic.rubic_size);
                                rotate(matrix, rubic.rubic_size);
                                rotate(matrix, rubic.rubic_size);
                                q=0;
                                for (int i = 0; i < matrix.length; ++i) {
                                    for (int j = 0; j < matrix.length; ++j) {
                                        matrix[i][j].planes.get(5).color = turn_array[q];
                                        q++;
                                        matrix[i][j].angZ = 0;
                                    }
                                }
                                break;
                            }
                            case 6 ->{//B
                                Color[] color_array = new Color[12];

                                color_array[3] = matrix[0][0].planes.get(2).color;
                                color_array[4] = matrix[0][1].planes.get(2).color;
                                color_array[5] = matrix[0][2].planes.get(2).color;

                                color_array[2] = matrix[0][2].planes.get(0).color;
                                color_array[1] = matrix[1][2].planes.get(0).color;
                                color_array[0] = matrix[2][2].planes.get(0).color;

                                color_array[9] = matrix[2][2].planes.get(4).color;
                                color_array[10]= matrix[2][1].planes.get(4).color;
                                color_array[11]= matrix[2][0].planes.get(4).color;

                                color_array[6] = matrix[2][0].planes.get(3).color;
                                color_array[7] = matrix[1][0].planes.get(3).color;
                                color_array[8] = matrix[0][0].planes.get(3).color;

                                color_array = rotate(color_array);
                                color_array = rotate(color_array);
                                color_array = rotate(color_array);

                                matrix[2][2].planes.get(0).color = color_array[0];
                                matrix[1][2].planes.get(0).color = color_array[1];
                                matrix[0][2].planes.get(0).color = color_array[2];
                                matrix[0][0].planes.get(2).color = color_array[3];
                                matrix[0][1].planes.get(2).color = color_array[4];
                                matrix[0][2].planes.get(2).color = color_array[5];
                                matrix[2][0].planes.get(3).color = color_array[6];
                                matrix[1][0].planes.get(3).color = color_array[7];
                                matrix[0][0].planes.get(3).color = color_array[8];
                                matrix[2][2].planes.get(4).color = color_array[9];
                                matrix[2][1].planes.get(4).color = color_array[10];
                                matrix[2][0].planes.get(4).color = color_array[11];

                                rotate(matrix, rubic.rubic_size);
                                int q = 0;
                                Color[] turn_array = new Color[9];
                                for (int i = 0; i < matrix.length; ++i) {
                                    for (int j = 0; j < matrix.length; ++j) {
                                        turn_array[q] = matrix[i][j].planes.get(1).color;
                                        q++;
                                    }
                                }
                                rotate(matrix, rubic.rubic_size);
                                rotate(matrix, rubic.rubic_size);
                                rotate(matrix, rubic.rubic_size);
                                q=0;
                                for (int i = 0; i < matrix.length; ++i) {
                                    for (int j = 0; j < matrix.length; ++j) {
                                        matrix[i][j].planes.get(1).color = turn_array[q];
                                        q++;
                                        matrix[i][j].angZ = 0;
                                    }
                                }
                                break;
                            }
                            case 11 -> {//r
                                Color[] color_array = new Color[12];

                                color_array[0] = matrix[0][0].planes.get(5).color;
                                color_array[1] = matrix[1][0].planes.get(5).color;
                                color_array[2] = matrix[2][0].planes.get(5).color;

                                color_array[3] = matrix[2][0].planes.get(0).color;
                                color_array[4] = matrix[2][1].planes.get(0).color;
                                color_array[5] = matrix[2][2].planes.get(0).color;

                                color_array[6] = matrix[2][2].planes.get(1).color;
                                color_array[7] = matrix[1][2].planes.get(1).color;
                                color_array[8] = matrix[0][2].planes.get(1).color;

                                color_array[9] =  matrix[0][2].planes.get(3).color;
                                color_array[10] = matrix[0][1].planes.get(3).color;
                                color_array[11] = matrix[0][0].planes.get(3).color;

                                color_array = rotate(color_array);
                                color_array = rotate(color_array);
                                color_array = rotate(color_array);

                                matrix[0][0].planes.get(5).color = color_array[0];
                                matrix[1][0].planes.get(5).color = color_array[1];
                                matrix[2][0].planes.get(5).color = color_array[2];
                                matrix[2][0].planes.get(0).color = color_array[3];
                                matrix[2][1].planes.get(0).color = color_array[4];
                                matrix[2][2].planes.get(0).color = color_array[5];
                                matrix[2][2].planes.get(1).color = color_array[6];
                                matrix[1][2].planes.get(1).color = color_array[7];
                                matrix[0][2].planes.get(1).color = color_array[8];
                                matrix[0][2].planes.get(3).color = color_array[9];
                                matrix[0][1].planes.get(3).color = color_array[10];
                                matrix[0][0].planes.get(3).color = color_array[11];

                                rotate(matrix, rubic.rubic_size);
                                int q = 0;
                                Color[] turn_array = new Color[9];
                                for (int i = 0; i < matrix.length; ++i) {
                                    for (int j = 0; j < matrix.length; ++j) {
                                        turn_array[q] = matrix[i][j].planes.get(4).color;
                                        q++;
                                    }
                                }
                                rotate(matrix, rubic.rubic_size);
                                rotate(matrix, rubic.rubic_size);
                                rotate(matrix, rubic.rubic_size);
                                q=0;
                                for (int i = 0; i < matrix.length; ++i) {
                                    for (int j = 0; j < matrix.length; ++j) {
                                        matrix[i][j].planes.get(4).color = turn_array[q];
                                        q++;
                                        matrix[i][j].angX = 0;
                                    }
                                }
                                break;
                            }
                            case 21 ->{//r
                                Color[] color_array = new Color[12];

                                color_array[0] = matrix[0][0].planes.get(5).color;
                                color_array[1] = matrix[1][0].planes.get(5).color;
                                color_array[2] = matrix[2][0].planes.get(5).color;

                                color_array[3] = matrix[2][0].planes.get(0).color;
                                color_array[4] = matrix[2][1].planes.get(0).color;
                                color_array[5] = matrix[2][2].planes.get(0).color;

                                color_array[6] = matrix[2][2].planes.get(1).color;
                                color_array[7] = matrix[1][2].planes.get(1).color;
                                color_array[8] = matrix[0][2].planes.get(1).color;

                                color_array[9] =  matrix[0][2].planes.get(3).color;
                                color_array[10] = matrix[0][1].planes.get(3).color;
                                color_array[11] = matrix[0][0].planes.get(3).color;

                                color_array = rotate(color_array);
                                color_array = rotate(color_array);
                                color_array = rotate(color_array);

                                matrix[0][0].planes.get(5).color = color_array[0];
                                matrix[1][0].planes.get(5).color = color_array[1];
                                matrix[2][0].planes.get(5).color = color_array[2];
                                matrix[2][0].planes.get(0).color = color_array[3];
                                matrix[2][1].planes.get(0).color = color_array[4];
                                matrix[2][2].planes.get(0).color = color_array[5];
                                matrix[2][2].planes.get(1).color = color_array[6];
                                matrix[1][2].planes.get(1).color = color_array[7];
                                matrix[0][2].planes.get(1).color = color_array[8];
                                matrix[0][2].planes.get(3).color = color_array[9];
                                matrix[0][1].planes.get(3).color = color_array[10];
                                matrix[0][0].planes.get(3).color = color_array[11];

                                rotate(matrix, rubic.rubic_size);
                                int q = 0;
                                Color[] turn_array = new Color[9];
                                for (int i = 0; i < matrix.length; ++i) {
                                    for (int j = 0; j < matrix.length; ++j) {
                                        turn_array[q] = matrix[i][j].planes.get(2).color;
                                        q++;
                                    }
                                }
                                rotate(matrix, rubic.rubic_size);
                                rotate(matrix, rubic.rubic_size);
                                rotate(matrix, rubic.rubic_size);
                                q=0;
                                for (int i = 0; i < matrix.length; ++i) {
                                    for (int j = 0; j < matrix.length; ++j) {
                                        matrix[i][j].planes.get(2).color = turn_array[q];
                                        q++;
                                        matrix[i][j].angX = 0;
                                    }
                                }
                                break;
                            }
                            case 31 ->{//u
                                Color[] color_array = new Color[12];

                                color_array[0] = matrix[0][0].planes.get(5).color;
                                color_array[1] = matrix[1][0].planes.get(5).color;
                                color_array[2] = matrix[2][0].planes.get(5).color;

                                color_array[3] = matrix[2][0].planes.get(4).color;
                                color_array[4] = matrix[2][1].planes.get(4).color;
                                color_array[5] = matrix[2][2].planes.get(4).color;

                                color_array[6] = matrix[2][2].planes.get(1).color;
                                color_array[7] = matrix[1][2].planes.get(1).color;
                                color_array[8] = matrix[0][2].planes.get(1).color;

                                color_array[9] =  matrix[0][2].planes.get(2).color;
                                color_array[10] = matrix[0][1].planes.get(2).color;
                                color_array[11] = matrix[0][0].planes.get(2).color;

                                color_array = rotate(color_array);
                                color_array = rotate(color_array);
                                color_array = rotate(color_array);
                                color_array = rotate(color_array);
                                color_array = rotate(color_array);
                                color_array = rotate(color_array);
                                color_array = rotate(color_array);
                                color_array = rotate(color_array);
                                color_array = rotate(color_array);

                                matrix[0][0].planes.get(5).color = color_array[0];
                                matrix[1][0].planes.get(5).color = color_array[1];
                                matrix[2][0].planes.get(5).color = color_array[2];
                                matrix[2][0].planes.get(4).color = color_array[3];
                                matrix[2][1].planes.get(4).color = color_array[4];
                                matrix[2][2].planes.get(4).color = color_array[5];
                                matrix[2][2].planes.get(1).color = color_array[6];
                                matrix[1][2].planes.get(1).color = color_array[7];
                                matrix[0][2].planes.get(1).color = color_array[8];
                                matrix[0][2].planes.get(2).color = color_array[9];
                                matrix[0][1].planes.get(2).color = color_array[10];
                                matrix[0][0].planes.get(2).color = color_array[11];

                                rotate(matrix, rubic.rubic_size);
                                rotate(matrix, rubic.rubic_size);
                                rotate(matrix, rubic.rubic_size);
                                int q = 0;
                                Color[] turn_array = new Color[9];
                                Color[] turn_array_b = new Color[9];
                                for (int i = 0; i < matrix.length; ++i) {
                                    for (int j = 0; j < matrix.length; ++j) {
                                        turn_array[q] = matrix[i][j].planes.get(0).color;
                                        turn_array_b[q] = matrix[i][j].planes.get(3).color;
                                        q++;
                                    }
                                }
                                rotate(matrix, rubic.rubic_size);
                                q = 0;
                                for (int i = 0; i < matrix.length; ++i) {
                                    for (int j = 0; j < matrix.length; ++j) {
                                        matrix[i][j].planes.get(0).color = turn_array[q];
                                        matrix[i][j].planes.get(3).color = turn_array_b[q];
                                        q++;
                                        matrix[i][j].angY = 0;
                                    }
                                }
                                break;
                            }
                            case 41 ->{//d
                                Color[] color_array = new Color[12];

                                color_array[0] = matrix[0][0].planes.get(5).color;
                                color_array[1] = matrix[1][0].planes.get(5).color;
                                color_array[2] = matrix[2][0].planes.get(5).color;

                                color_array[3] = matrix[2][0].planes.get(4).color;
                                color_array[4] = matrix[2][1].planes.get(4).color;
                                color_array[5] = matrix[2][2].planes.get(4).color;

                                color_array[6] = matrix[2][2].planes.get(1).color;
                                color_array[7] = matrix[1][2].planes.get(1).color;
                                color_array[8] = matrix[0][2].planes.get(1).color;

                                color_array[9] =  matrix[0][2].planes.get(2).color;
                                color_array[10] = matrix[0][1].planes.get(2).color;
                                color_array[11] = matrix[0][0].planes.get(2).color;

                                color_array = rotate(color_array);
                                color_array = rotate(color_array);
                                color_array = rotate(color_array);

                                color_array = rotate(color_array);
                                color_array = rotate(color_array);
                                color_array = rotate(color_array);

                                color_array = rotate(color_array);
                                color_array = rotate(color_array);
                                color_array = rotate(color_array);

                                matrix[0][0].planes.get(5).color = color_array[0];
                                matrix[1][0].planes.get(5).color = color_array[1];
                                matrix[2][0].planes.get(5).color = color_array[2];
                                matrix[2][0].planes.get(4).color = color_array[3];
                                matrix[2][1].planes.get(4).color = color_array[4];
                                matrix[2][2].planes.get(4).color = color_array[5];
                                matrix[2][2].planes.get(1).color = color_array[6];
                                matrix[1][2].planes.get(1).color = color_array[7];
                                matrix[0][2].planes.get(1).color = color_array[8];
                                matrix[0][2].planes.get(2).color = color_array[9];
                                matrix[0][1].planes.get(2).color = color_array[10];
                                matrix[0][0].planes.get(2).color = color_array[11];

                                rotate(matrix, rubic.rubic_size);
                                rotate(matrix, rubic.rubic_size);
                                rotate(matrix, rubic.rubic_size);
                                int q = 0;
                                Color[] turn_array = new Color[9];
                                for (int i = 0; i < matrix.length; ++i) {
                                    for (int j = 0; j < matrix.length; ++j) {
                                        turn_array[q] = matrix[i][j].planes.get(3).color;
                                        q++;
                                    }
                                }
                                rotate(matrix, rubic.rubic_size);
                                q=0;
                                for (int i = 0; i < matrix.length; ++i) {
                                    for (int j = 0; j < matrix.length; ++j) {
                                        matrix[i][j].planes.get(3).color = turn_array[q];
                                        q++;
                                        matrix[i][j].angY = 0;
                                    }
                                }
                                break;
                            }
                            case 51 ->{//f
                                Color[] color_array = new Color[12];

                                color_array[0] = matrix[0][2].planes.get(0).color;
                                color_array[1] = matrix[1][2].planes.get(0).color;
                                color_array[2] = matrix[2][2].planes.get(0).color;

                                color_array[3] = matrix[0][0].planes.get(2).color;
                                color_array[4] = matrix[0][1].planes.get(2).color;
                                color_array[5] = matrix[0][2].planes.get(2).color;

                                color_array[6] = matrix[0][0].planes.get(3).color;
                                color_array[7] = matrix[1][0].planes.get(3).color;
                                color_array[8] = matrix[2][0].planes.get(3).color;

                                color_array[9] = matrix[2][0].planes.get(4).color;
                                color_array[10]= matrix[2][1].planes.get(4).color;
                                color_array[11]= matrix[2][2].planes.get(4).color;

                                color_array = rotate(color_array);
                                color_array = rotate(color_array);
                                color_array = rotate(color_array);
                                color_array = rotate(color_array);
                                color_array = rotate(color_array);
                                color_array = rotate(color_array);
                                color_array = rotate(color_array);
                                color_array = rotate(color_array);
                                color_array = rotate(color_array);

                                matrix[0][2].planes.get(0).color = color_array[0];
                                matrix[1][2].planes.get(0).color = color_array[1];
                                matrix[2][2].planes.get(0).color = color_array[2];
                                matrix[0][0].planes.get(2).color = color_array[3];
                                matrix[0][1].planes.get(2).color = color_array[4];
                                matrix[0][2].planes.get(2).color = color_array[5];
                                matrix[0][0].planes.get(3).color = color_array[6];
                                matrix[1][0].planes.get(3).color = color_array[7];
                                matrix[2][0].planes.get(3).color = color_array[8];
                                matrix[2][0].planes.get(4).color = color_array[9];
                                matrix[2][1].planes.get(4).color = color_array[10];
                                matrix[2][2].planes.get(4).color = color_array[11];

                                rotate(matrix, rubic.rubic_size);
                                rotate(matrix, rubic.rubic_size);
                                rotate(matrix, rubic.rubic_size);
                                int q = 0;
                                Color[] turn_array = new Color[9];
                                for (int i = 0; i < matrix.length; ++i) {
                                    for (int j = 0; j < matrix.length; ++j) {
                                        turn_array[q] = matrix[i][j].planes.get(5).color;
                                        q++;
                                    }
                                }
                                rotate(matrix, rubic.rubic_size);
                                q=0;
                                for (int i = 0; i < matrix.length; ++i) {
                                    for (int j = 0; j < matrix.length; ++j) {
                                        matrix[i][j].planes.get(5).color = turn_array[q];
                                        q++;
                                        matrix[i][j].angZ = 0;
                                    }
                                }
                                break;
                            }
                            case 61 ->{//b
                                Color[] color_array = new Color[12];

                                color_array[0] = matrix[2][2].planes.get(0).color;
                                color_array[1] = matrix[1][2].planes.get(0).color;
                                color_array[2] = matrix[0][2].planes.get(0).color;

                                color_array[3] = matrix[0][0].planes.get(2).color;
                                color_array[4] = matrix[0][1].planes.get(2).color;
                                color_array[5] = matrix[0][2].planes.get(2).color;

                                color_array[6] = matrix[2][0].planes.get(3).color;
                                color_array[7] = matrix[1][0].planes.get(3).color;
                                color_array[8] = matrix[0][0].planes.get(3).color;

                                color_array[9] = matrix[2][2].planes.get(4).color;
                                color_array[10]= matrix[2][1].planes.get(4).color;
                                color_array[11]= matrix[2][0].planes.get(4).color;

                                color_array = rotate(color_array);
                                color_array = rotate(color_array);
                                color_array = rotate(color_array);
                                color_array = rotate(color_array);
                                color_array = rotate(color_array);
                                color_array = rotate(color_array);
                                color_array = rotate(color_array);
                                color_array = rotate(color_array);
                                color_array = rotate(color_array);

                                matrix[2][2].planes.get(0).color = color_array[0];
                                matrix[1][2].planes.get(0).color = color_array[1];
                                matrix[0][2].planes.get(0).color = color_array[2];
                                matrix[0][0].planes.get(2).color = color_array[3];
                                matrix[0][1].planes.get(2).color = color_array[4];
                                matrix[0][2].planes.get(2).color = color_array[5];
                                matrix[2][0].planes.get(3).color = color_array[6];
                                matrix[1][0].planes.get(3).color = color_array[7];
                                matrix[0][0].planes.get(3).color = color_array[8];
                                matrix[2][2].planes.get(4).color = color_array[9];
                                matrix[2][1].planes.get(4).color = color_array[10];
                                matrix[2][0].planes.get(4).color = color_array[11];

                                rotate(matrix, rubic.rubic_size);
                                rotate(matrix, rubic.rubic_size);
                                rotate(matrix, rubic.rubic_size);
                                int q = 0;
                                Color[] turn_array = new Color[9];
                                for (int i = 0; i < matrix.length; ++i) {
                                    for (int j = 0; j < matrix.length; ++j) {
                                        turn_array[q] = matrix[i][j].planes.get(1).color;
                                        q++;
                                    }
                                }
                                rotate(matrix, rubic.rubic_size);
                                q=0;
                                for (int i = 0; i < matrix.length; ++i) {
                                    for (int j = 0; j < matrix.length; ++j) {
                                        matrix[i][j].planes.get(1).color = turn_array[q];
                                        q++;
                                        matrix[i][j].angZ = 0;
                                    }
                                }
                                break;
                            }
                        }
                        fl = true;
                        break;
                    }

                    summ_time = 0L;
                }
                start_time = end_time;
            }

            /*java.util.Timer t = new Timer();
            TimerTask tt = new TimerTask() {
                public void run() {
                    tk.sync();
                    Shape[][] matrix = new Shape[rubic.rubic_size][rubic.rubic_size];
                    boolean drawprocess = true;
                    for (int o = 0; o < rubic.rubic_size; ++o) {
                        for (int k = 0; k < rubic.rubic_size; ++k) {
                            for (int l = 0; l < rubic.rubic_size; ++l) {
                                switch (finalCommand) {
                                    // "RLUDFBrludfb"
                                    case 1 -> {
                                        if (o == 2) {
                                            drawprocess = scene.RotateX(5, rubic.cube[o][k][l]);//R
                                            matrix[k][l] = rubic.cube[2][k][l];
                                        }
                                        break;
                                    }
                                    case 2 -> {
                                        if (o == 0) {
                                            drawprocess = scene.RotateX(5, rubic.cube[o][k][l]);//L
                                            matrix[k][l] = rubic.cube[o][k][l];
                                        }
                                        break;
                                    }
                                    case 3 -> {
                                        if (k == 2) {
                                            drawprocess = scene.RotateY(5, rubic.cube[o][k][l]);//U
                                            matrix[o][l] = rubic.cube[o][k][l];
                                        }
                                        break;
                                    }
                                    case 4 -> {
                                        if (k == 0) {
                                            drawprocess = scene.RotateY(5, rubic.cube[o][k][l]);//D
                                            matrix[o][l] = rubic.cube[o][k][l];
                                        }
                                        break;
                                    }
                                    case 5 -> {
                                        if (l == 0) {
                                            drawprocess = scene.RotateZ(5, rubic.cube[o][k][l]);//F
                                            matrix[o][k] = rubic.cube[o][k][l];
                                        }
                                        break;
                                    }
                                    case 6 -> {
                                        if (l == 2) {
                                            drawprocess = scene.RotateZ(5, rubic.cube[o][k][l]);//B
                                            matrix[o][k] = rubic.cube[o][k][l];
                                        }
                                        break;
                                    }
                                    case 11 -> {
                                        if (o == 2) {
                                            drawprocess = scene.RotateX(-5, rubic.cube[o][k][l]);//r
                                            matrix[k][l] = rubic.cube[o][k][l];
                                        }
                                        break;
                                    }
                                    case 21 -> {
                                        if (o == 0) {
                                            drawprocess = scene.RotateX(-5, rubic.cube[o][k][l]);//l
                                            matrix[k][l] = rubic.cube[o][k][l];
                                        }
                                        break;
                                    }
                                    case 31 -> {
                                        if (k == 2) {
                                            drawprocess = scene.RotateY(-5, rubic.cube[o][k][l]);//u
                                            matrix[o][l] = rubic.cube[o][k][l];
                                        }
                                        break;
                                    }
                                    case 41 -> {
                                        if (k == 0) {
                                            drawprocess = scene.RotateY(-5, rubic.cube[o][k][l]);//d
                                            matrix[o][l] = rubic.cube[o][k][l];
                                        }
                                        break;
                                    }
                                    case 51 -> {
                                        if (l == 0) {
                                            drawprocess = scene.RotateZ(-5, rubic.cube[o][k][l]);//f
                                            matrix[o][k] = rubic.cube[o][k][l];
                                        }
                                        break;
                                    }
                                    case 61 -> {
                                        if (l == 2) {
                                            drawprocess = scene.RotateZ(-5, rubic.cube[o][k][l]);//b
                                            matrix[o][k] = rubic.cube[o][k][l];
                                        }
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    scene.repaint();
                    if (!drawprocess) {
                        switch (finalCommand){
                            case 1 -> {//R
                                Color[] color_array = new Color[12];

                                color_array[0] = matrix[0][0].planes.get(5).color;
                                color_array[1] = matrix[1][0].planes.get(5).color;
                                color_array[2] = matrix[2][0].planes.get(5).color;

                                color_array[3] = matrix[2][0].planes.get(0).color;
                                color_array[4] = matrix[2][1].planes.get(0).color;
                                color_array[5] = matrix[2][2].planes.get(0).color;

                                color_array[6] = matrix[2][2].planes.get(1).color;
                                color_array[7] = matrix[1][2].planes.get(1).color;
                                color_array[8] = matrix[0][2].planes.get(1).color;

                                color_array[9] =  matrix[0][2].planes.get(3).color;
                                color_array[10] = matrix[0][1].planes.get(3).color;
                                color_array[11] = matrix[0][0].planes.get(3).color;

                                color_array = rotate(color_array);
                                color_array = rotate(color_array);
                                color_array = rotate(color_array);

                                color_array = rotate(color_array);
                                color_array = rotate(color_array);
                                color_array = rotate(color_array);

                                color_array = rotate(color_array);
                                color_array = rotate(color_array);
                                color_array = rotate(color_array);

                                matrix[0][0].planes.get(5).color = color_array[0];
                                matrix[1][0].planes.get(5).color = color_array[1];
                                matrix[2][0].planes.get(5).color = color_array[2];
                                matrix[2][0].planes.get(0).color = color_array[3];
                                matrix[2][1].planes.get(0).color = color_array[4];
                                matrix[2][2].planes.get(0).color = color_array[5];
                                matrix[2][2].planes.get(1).color = color_array[6];
                                matrix[1][2].planes.get(1).color = color_array[7];
                                matrix[0][2].planes.get(1).color = color_array[8];
                                matrix[0][2].planes.get(3).color = color_array[9];
                                matrix[0][1].planes.get(3).color = color_array[10];
                                matrix[0][0].planes.get(3).color = color_array[11];

                                rotate(matrix, rubic.rubic_size);
                                rotate(matrix, rubic.rubic_size);
                                rotate(matrix, rubic.rubic_size);
                                int q = 0;
                                Color[] turn_array = new Color[9];
                                for (int i = 0; i < matrix.length; ++i) {
                                    for (int j = 0; j < matrix.length; ++j) {
                                        turn_array[q] = matrix[i][j].planes.get(4).color;
                                        q++;
                                    }
                                }
                                rotate(matrix, rubic.rubic_size);
                                q=0;
                                for (int i = 0; i < matrix.length; ++i) {
                                    for (int j = 0; j < matrix.length; ++j) {
                                        matrix[i][j].planes.get(4).color = turn_array[q];
                                        q++;
                                        matrix[i][j].angX = 0;
                                    }
                                }
                                break;
                            }
                            case 2 ->{//L
                                Color[] color_array = new Color[12];

                                color_array[0] = matrix[0][0].planes.get(5).color;
                                color_array[1] = matrix[1][0].planes.get(5).color;
                                color_array[2] = matrix[2][0].planes.get(5).color;

                                color_array[3] = matrix[2][0].planes.get(0).color;
                                color_array[4] = matrix[2][1].planes.get(0).color;
                                color_array[5] = matrix[2][2].planes.get(0).color;

                                color_array[6] = matrix[2][2].planes.get(1).color;
                                color_array[7] = matrix[1][2].planes.get(1).color;
                                color_array[8] = matrix[0][2].planes.get(1).color;

                                color_array[9] =  matrix[0][2].planes.get(3).color;
                                color_array[10] = matrix[0][1].planes.get(3).color;
                                color_array[11] = matrix[0][0].planes.get(3).color;

                                color_array = rotate(color_array);
                                color_array = rotate(color_array);
                                color_array = rotate(color_array);
                                color_array = rotate(color_array);
                                color_array = rotate(color_array);
                                color_array = rotate(color_array);
                                color_array = rotate(color_array);
                                color_array = rotate(color_array);
                                color_array = rotate(color_array);

                                matrix[0][0].planes.get(5).color = color_array[0];
                                matrix[1][0].planes.get(5).color = color_array[1];
                                matrix[2][0].planes.get(5).color = color_array[2];
                                matrix[2][0].planes.get(0).color = color_array[3];
                                matrix[2][1].planes.get(0).color = color_array[4];
                                matrix[2][2].planes.get(0).color = color_array[5];
                                matrix[2][2].planes.get(1).color = color_array[6];
                                matrix[1][2].planes.get(1).color = color_array[7];
                                matrix[0][2].planes.get(1).color = color_array[8];
                                matrix[0][2].planes.get(3).color = color_array[9];
                                matrix[0][1].planes.get(3).color = color_array[10];
                                matrix[0][0].planes.get(3).color = color_array[11];

                                rotate(matrix, rubic.rubic_size);
                                rotate(matrix, rubic.rubic_size);
                                rotate(matrix, rubic.rubic_size);
                                int q = 0;
                                Color[] turn_array = new Color[9];
                                for (int i = 0; i < matrix.length; ++i) {
                                    for (int j = 0; j < matrix.length; ++j) {
                                        turn_array[q] = matrix[i][j].planes.get(2).color;
                                        q++;
                                    }
                                }
                                rotate(matrix, rubic.rubic_size);
                                q=0;
                                for (int i = 0; i < matrix.length; ++i) {
                                    for (int j = 0; j < matrix.length; ++j) {
                                        matrix[i][j].planes.get(2).color = turn_array[q];
                                        q++;
                                        matrix[i][j].angX = 0;
                                    }
                                }
                                break;
                            }
                            case 3 ->{//U
                                Color[] color_array = new Color[12];

                                color_array[0] = matrix[0][0].planes.get(5).color;
                                color_array[1] = matrix[1][0].planes.get(5).color;
                                color_array[2] = matrix[2][0].planes.get(5).color;

                                color_array[3] = matrix[2][0].planes.get(4).color;
                                color_array[4] = matrix[2][1].planes.get(4).color;
                                color_array[5] = matrix[2][2].planes.get(4).color;

                                color_array[6] = matrix[2][2].planes.get(1).color;
                                color_array[7] = matrix[1][2].planes.get(1).color;
                                color_array[8] = matrix[0][2].planes.get(1).color;

                                color_array[9] =  matrix[0][2].planes.get(2).color;
                                color_array[10] = matrix[0][1].planes.get(2).color;
                                color_array[11] = matrix[0][0].planes.get(2).color;

                                color_array = rotate(color_array);
                                color_array = rotate(color_array);
                                color_array = rotate(color_array);

                                matrix[0][0].planes.get(5).color = color_array[0];
                                matrix[1][0].planes.get(5).color = color_array[1];
                                matrix[2][0].planes.get(5).color = color_array[2];
                                matrix[2][0].planes.get(4).color = color_array[3];
                                matrix[2][1].planes.get(4).color = color_array[4];
                                matrix[2][2].planes.get(4).color = color_array[5];
                                matrix[2][2].planes.get(1).color = color_array[6];
                                matrix[1][2].planes.get(1).color = color_array[7];
                                matrix[0][2].planes.get(1).color = color_array[8];
                                matrix[0][2].planes.get(2).color = color_array[9];
                                matrix[0][1].planes.get(2).color = color_array[10];
                                matrix[0][0].planes.get(2).color = color_array[11];

                                rotate(matrix, rubic.rubic_size);
                                int q = 0;
                                Color[] turn_array = new Color[9];
                                Color[] turn_array_b = new Color[9];
                                for (int i = 0; i < matrix.length; ++i) {
                                    for (int j = 0; j < matrix.length; ++j) {
                                        turn_array[q] = matrix[i][j].planes.get(0).color;
                                        turn_array_b[q] = matrix[i][j].planes.get(3).color;
                                        q++;
                                    }
                                }
                                rotate(matrix, rubic.rubic_size);
                                rotate(matrix, rubic.rubic_size);
                                rotate(matrix, rubic.rubic_size);
                                q = 0;
                                for (int i = 0; i < matrix.length; ++i) {
                                    for (int j = 0; j < matrix.length; ++j) {
                                        matrix[i][j].planes.get(0).color = turn_array[q];
                                        matrix[i][j].planes.get(3).color = turn_array_b[q];
                                        q++;
                                        matrix[i][j].angY = 0;
                                    }
                                }
                                break;
                            }
                            case 4 ->{//D
                                Color[] color_array = new Color[12];

                                color_array[0] = matrix[0][0].planes.get(5).color;
                                color_array[1] = matrix[1][0].planes.get(5).color;
                                color_array[2] = matrix[2][0].planes.get(5).color;

                                color_array[3] = matrix[2][0].planes.get(4).color;
                                color_array[4] = matrix[2][1].planes.get(4).color;
                                color_array[5] = matrix[2][2].planes.get(4).color;

                                color_array[6] = matrix[2][2].planes.get(1).color;
                                color_array[7] = matrix[1][2].planes.get(1).color;
                                color_array[8] = matrix[0][2].planes.get(1).color;

                                color_array[9] =  matrix[0][2].planes.get(2).color;
                                color_array[10] = matrix[0][1].planes.get(2).color;
                                color_array[11] = matrix[0][0].planes.get(2).color;

                                color_array = rotate(color_array);
                                color_array = rotate(color_array);
                                color_array = rotate(color_array);

                                matrix[0][0].planes.get(5).color = color_array[0];
                                matrix[1][0].planes.get(5).color = color_array[1];
                                matrix[2][0].planes.get(5).color = color_array[2];
                                matrix[2][0].planes.get(4).color = color_array[3];
                                matrix[2][1].planes.get(4).color = color_array[4];
                                matrix[2][2].planes.get(4).color = color_array[5];
                                matrix[2][2].planes.get(1).color = color_array[6];
                                matrix[1][2].planes.get(1).color = color_array[7];
                                matrix[0][2].planes.get(1).color = color_array[8];
                                matrix[0][2].planes.get(2).color = color_array[9];
                                matrix[0][1].planes.get(2).color = color_array[10];
                                matrix[0][0].planes.get(2).color = color_array[11];

                                rotate(matrix, rubic.rubic_size);
                                int q = 0;
                                Color[] turn_array = new Color[9];
                                for (int i = 0; i < matrix.length; ++i) {
                                    for (int j = 0; j < matrix.length; ++j) {
                                        turn_array[q] = matrix[i][j].planes.get(3).color;
                                        q++;
                                    }
                                }
                                rotate(matrix, rubic.rubic_size);
                                rotate(matrix, rubic.rubic_size);
                                rotate(matrix, rubic.rubic_size);
                                q=0;
                                for (int i = 0; i < matrix.length; ++i) {
                                    for (int j = 0; j < matrix.length; ++j) {
                                        matrix[i][j].planes.get(3).color = turn_array[q];
                                        q++;
                                        matrix[i][j].angY = 0;
                                    }
                                }
                                break;
                            }
                            case 5 ->{//F
                                Color[] color_array = new Color[12];

                                color_array[0] = matrix[0][2].planes.get(0).color;
                                color_array[1] = matrix[1][2].planes.get(0).color;
                                color_array[2] = matrix[2][2].planes.get(0).color;

                                color_array[3] = matrix[0][0].planes.get(2).color;
                                color_array[4] = matrix[0][1].planes.get(2).color;
                                color_array[5] = matrix[0][2].planes.get(2).color;

                                color_array[6] = matrix[0][0].planes.get(3).color;
                                color_array[7] = matrix[1][0].planes.get(3).color;
                                color_array[8] = matrix[2][0].planes.get(3).color;

                                color_array[9] = matrix[2][0].planes.get(4).color;
                                color_array[10]= matrix[2][1].planes.get(4).color;
                                color_array[11]= matrix[2][2].planes.get(4).color;

                                color_array = rotate(color_array);
                                color_array = rotate(color_array);
                                color_array = rotate(color_array);

                                matrix[0][2].planes.get(0).color = color_array[0];
                                matrix[1][2].planes.get(0).color = color_array[1];
                                matrix[2][2].planes.get(0).color = color_array[2];
                                matrix[0][0].planes.get(2).color = color_array[3];
                                matrix[0][1].planes.get(2).color = color_array[4];
                                matrix[0][2].planes.get(2).color = color_array[5];
                                matrix[0][0].planes.get(3).color = color_array[6];
                                matrix[1][0].planes.get(3).color = color_array[7];
                                matrix[2][0].planes.get(3).color = color_array[8];
                                matrix[2][0].planes.get(4).color = color_array[9];
                                matrix[2][1].planes.get(4).color = color_array[10];
                                matrix[2][2].planes.get(4).color = color_array[11];

                                rotate(matrix, rubic.rubic_size);
                                int q = 0;
                                Color[] turn_array = new Color[9];
                                for (int i = 0; i < matrix.length; ++i) {
                                    for (int j = 0; j < matrix.length; ++j) {
                                        turn_array[q] = matrix[i][j].planes.get(5).color;
                                        q++;
                                    }
                                }
                                rotate(matrix, rubic.rubic_size);
                                rotate(matrix, rubic.rubic_size);
                                rotate(matrix, rubic.rubic_size);
                                q=0;
                                for (int i = 0; i < matrix.length; ++i) {
                                    for (int j = 0; j < matrix.length; ++j) {
                                        matrix[i][j].planes.get(5).color = turn_array[q];
                                        q++;
                                        matrix[i][j].angZ = 0;
                                    }
                                }
                                break;
                            }
                            case 6 ->{//B
                                Color[] color_array = new Color[12];

                                color_array[3] = matrix[0][0].planes.get(2).color;
                                color_array[4] = matrix[0][1].planes.get(2).color;
                                color_array[5] = matrix[0][2].planes.get(2).color;

                                color_array[2] = matrix[0][2].planes.get(0).color;
                                color_array[1] = matrix[1][2].planes.get(0).color;
                                color_array[0] = matrix[2][2].planes.get(0).color;

                                color_array[9] = matrix[2][2].planes.get(4).color;
                                color_array[10]= matrix[2][1].planes.get(4).color;
                                color_array[11]= matrix[2][0].planes.get(4).color;

                                color_array[6] = matrix[2][0].planes.get(3).color;
                                color_array[7] = matrix[1][0].planes.get(3).color;
                                color_array[8] = matrix[0][0].planes.get(3).color;

                                color_array = rotate(color_array);
                                color_array = rotate(color_array);
                                color_array = rotate(color_array);

                                matrix[2][2].planes.get(0).color = color_array[0];
                                matrix[1][2].planes.get(0).color = color_array[1];
                                matrix[0][2].planes.get(0).color = color_array[2];
                                matrix[0][0].planes.get(2).color = color_array[3];
                                matrix[0][1].planes.get(2).color = color_array[4];
                                matrix[0][2].planes.get(2).color = color_array[5];
                                matrix[2][0].planes.get(3).color = color_array[6];
                                matrix[1][0].planes.get(3).color = color_array[7];
                                matrix[0][0].planes.get(3).color = color_array[8];
                                matrix[2][2].planes.get(4).color = color_array[9];
                                matrix[2][1].planes.get(4).color = color_array[10];
                                matrix[2][0].planes.get(4).color = color_array[11];

                                rotate(matrix, rubic.rubic_size);
                                int q = 0;
                                Color[] turn_array = new Color[9];
                                for (int i = 0; i < matrix.length; ++i) {
                                    for (int j = 0; j < matrix.length; ++j) {
                                        turn_array[q] = matrix[i][j].planes.get(1).color;
                                        q++;
                                    }
                                }
                                rotate(matrix, rubic.rubic_size);
                                rotate(matrix, rubic.rubic_size);
                                rotate(matrix, rubic.rubic_size);
                                q=0;
                                for (int i = 0; i < matrix.length; ++i) {
                                    for (int j = 0; j < matrix.length; ++j) {
                                        matrix[i][j].planes.get(1).color = turn_array[q];
                                        q++;
                                        matrix[i][j].angZ = 0;
                                    }
                                }
                                break;
                            }
                            case 11 -> {//r
                                Color[] color_array = new Color[12];

                                color_array[0] = matrix[0][0].planes.get(5).color;
                                color_array[1] = matrix[1][0].planes.get(5).color;
                                color_array[2] = matrix[2][0].planes.get(5).color;

                                color_array[3] = matrix[2][0].planes.get(0).color;
                                color_array[4] = matrix[2][1].planes.get(0).color;
                                color_array[5] = matrix[2][2].planes.get(0).color;

                                color_array[6] = matrix[2][2].planes.get(1).color;
                                color_array[7] = matrix[1][2].planes.get(1).color;
                                color_array[8] = matrix[0][2].planes.get(1).color;

                                color_array[9] =  matrix[0][2].planes.get(3).color;
                                color_array[10] = matrix[0][1].planes.get(3).color;
                                color_array[11] = matrix[0][0].planes.get(3).color;

                                color_array = rotate(color_array);
                                color_array = rotate(color_array);
                                color_array = rotate(color_array);

                                matrix[0][0].planes.get(5).color = color_array[0];
                                matrix[1][0].planes.get(5).color = color_array[1];
                                matrix[2][0].planes.get(5).color = color_array[2];
                                matrix[2][0].planes.get(0).color = color_array[3];
                                matrix[2][1].planes.get(0).color = color_array[4];
                                matrix[2][2].planes.get(0).color = color_array[5];
                                matrix[2][2].planes.get(1).color = color_array[6];
                                matrix[1][2].planes.get(1).color = color_array[7];
                                matrix[0][2].planes.get(1).color = color_array[8];
                                matrix[0][2].planes.get(3).color = color_array[9];
                                matrix[0][1].planes.get(3).color = color_array[10];
                                matrix[0][0].planes.get(3).color = color_array[11];

                                rotate(matrix, rubic.rubic_size);
                                int q = 0;
                                Color[] turn_array = new Color[9];
                                for (int i = 0; i < matrix.length; ++i) {
                                    for (int j = 0; j < matrix.length; ++j) {
                                        turn_array[q] = matrix[i][j].planes.get(4).color;
                                        q++;
                                    }
                                }
                                rotate(matrix, rubic.rubic_size);
                                rotate(matrix, rubic.rubic_size);
                                rotate(matrix, rubic.rubic_size);
                                q=0;
                                for (int i = 0; i < matrix.length; ++i) {
                                    for (int j = 0; j < matrix.length; ++j) {
                                        matrix[i][j].planes.get(4).color = turn_array[q];
                                        q++;
                                        matrix[i][j].angX = 0;
                                    }
                                }
                                break;
                            }
                            case 21 ->{//r
                                Color[] color_array = new Color[12];

                                color_array[0] = matrix[0][0].planes.get(5).color;
                                color_array[1] = matrix[1][0].planes.get(5).color;
                                color_array[2] = matrix[2][0].planes.get(5).color;

                                color_array[3] = matrix[2][0].planes.get(0).color;
                                color_array[4] = matrix[2][1].planes.get(0).color;
                                color_array[5] = matrix[2][2].planes.get(0).color;

                                color_array[6] = matrix[2][2].planes.get(1).color;
                                color_array[7] = matrix[1][2].planes.get(1).color;
                                color_array[8] = matrix[0][2].planes.get(1).color;

                                color_array[9] =  matrix[0][2].planes.get(3).color;
                                color_array[10] = matrix[0][1].planes.get(3).color;
                                color_array[11] = matrix[0][0].planes.get(3).color;

                                color_array = rotate(color_array);
                                color_array = rotate(color_array);
                                color_array = rotate(color_array);

                                matrix[0][0].planes.get(5).color = color_array[0];
                                matrix[1][0].planes.get(5).color = color_array[1];
                                matrix[2][0].planes.get(5).color = color_array[2];
                                matrix[2][0].planes.get(0).color = color_array[3];
                                matrix[2][1].planes.get(0).color = color_array[4];
                                matrix[2][2].planes.get(0).color = color_array[5];
                                matrix[2][2].planes.get(1).color = color_array[6];
                                matrix[1][2].planes.get(1).color = color_array[7];
                                matrix[0][2].planes.get(1).color = color_array[8];
                                matrix[0][2].planes.get(3).color = color_array[9];
                                matrix[0][1].planes.get(3).color = color_array[10];
                                matrix[0][0].planes.get(3).color = color_array[11];

                                rotate(matrix, rubic.rubic_size);
                                int q = 0;
                                Color[] turn_array = new Color[9];
                                for (int i = 0; i < matrix.length; ++i) {
                                    for (int j = 0; j < matrix.length; ++j) {
                                        turn_array[q] = matrix[i][j].planes.get(2).color;
                                        q++;
                                    }
                                }
                                rotate(matrix, rubic.rubic_size);
                                rotate(matrix, rubic.rubic_size);
                                rotate(matrix, rubic.rubic_size);
                                q=0;
                                for (int i = 0; i < matrix.length; ++i) {
                                    for (int j = 0; j < matrix.length; ++j) {
                                        matrix[i][j].planes.get(2).color = turn_array[q];
                                        q++;
                                        matrix[i][j].angX = 0;
                                    }
                                }
                                break;
                            }
                            case 31 ->{//u
                                Color[] color_array = new Color[12];

                                color_array[0] = matrix[0][0].planes.get(5).color;
                                color_array[1] = matrix[1][0].planes.get(5).color;
                                color_array[2] = matrix[2][0].planes.get(5).color;

                                color_array[3] = matrix[2][0].planes.get(4).color;
                                color_array[4] = matrix[2][1].planes.get(4).color;
                                color_array[5] = matrix[2][2].planes.get(4).color;

                                color_array[6] = matrix[2][2].planes.get(1).color;
                                color_array[7] = matrix[1][2].planes.get(1).color;
                                color_array[8] = matrix[0][2].planes.get(1).color;

                                color_array[9] =  matrix[0][2].planes.get(2).color;
                                color_array[10] = matrix[0][1].planes.get(2).color;
                                color_array[11] = matrix[0][0].planes.get(2).color;

                                color_array = rotate(color_array);
                                color_array = rotate(color_array);
                                color_array = rotate(color_array);
                                color_array = rotate(color_array);
                                color_array = rotate(color_array);
                                color_array = rotate(color_array);
                                color_array = rotate(color_array);
                                color_array = rotate(color_array);
                                color_array = rotate(color_array);

                                matrix[0][0].planes.get(5).color = color_array[0];
                                matrix[1][0].planes.get(5).color = color_array[1];
                                matrix[2][0].planes.get(5).color = color_array[2];
                                matrix[2][0].planes.get(4).color = color_array[3];
                                matrix[2][1].planes.get(4).color = color_array[4];
                                matrix[2][2].planes.get(4).color = color_array[5];
                                matrix[2][2].planes.get(1).color = color_array[6];
                                matrix[1][2].planes.get(1).color = color_array[7];
                                matrix[0][2].planes.get(1).color = color_array[8];
                                matrix[0][2].planes.get(2).color = color_array[9];
                                matrix[0][1].planes.get(2).color = color_array[10];
                                matrix[0][0].planes.get(2).color = color_array[11];

                                rotate(matrix, rubic.rubic_size);
                                rotate(matrix, rubic.rubic_size);
                                rotate(matrix, rubic.rubic_size);
                                int q = 0;
                                Color[] turn_array = new Color[9];
                                Color[] turn_array_b = new Color[9];
                                for (int i = 0; i < matrix.length; ++i) {
                                    for (int j = 0; j < matrix.length; ++j) {
                                        turn_array[q] = matrix[i][j].planes.get(0).color;
                                        turn_array_b[q] = matrix[i][j].planes.get(3).color;
                                        q++;
                                    }
                                }
                                rotate(matrix, rubic.rubic_size);
                                q = 0;
                                for (int i = 0; i < matrix.length; ++i) {
                                    for (int j = 0; j < matrix.length; ++j) {
                                        matrix[i][j].planes.get(0).color = turn_array[q];
                                        matrix[i][j].planes.get(3).color = turn_array_b[q];
                                        q++;
                                        matrix[i][j].angY = 0;
                                    }
                                }
                                break;
                            }
                            case 41 ->{//d
                                Color[] color_array = new Color[12];

                                color_array[0] = matrix[0][0].planes.get(5).color;
                                color_array[1] = matrix[1][0].planes.get(5).color;
                                color_array[2] = matrix[2][0].planes.get(5).color;

                                color_array[3] = matrix[2][0].planes.get(4).color;
                                color_array[4] = matrix[2][1].planes.get(4).color;
                                color_array[5] = matrix[2][2].planes.get(4).color;

                                color_array[6] = matrix[2][2].planes.get(1).color;
                                color_array[7] = matrix[1][2].planes.get(1).color;
                                color_array[8] = matrix[0][2].planes.get(1).color;

                                color_array[9] =  matrix[0][2].planes.get(2).color;
                                color_array[10] = matrix[0][1].planes.get(2).color;
                                color_array[11] = matrix[0][0].planes.get(2).color;

                                color_array = rotate(color_array);
                                color_array = rotate(color_array);
                                color_array = rotate(color_array);

                                color_array = rotate(color_array);
                                color_array = rotate(color_array);
                                color_array = rotate(color_array);

                                color_array = rotate(color_array);
                                color_array = rotate(color_array);
                                color_array = rotate(color_array);

                                matrix[0][0].planes.get(5).color = color_array[0];
                                matrix[1][0].planes.get(5).color = color_array[1];
                                matrix[2][0].planes.get(5).color = color_array[2];
                                matrix[2][0].planes.get(4).color = color_array[3];
                                matrix[2][1].planes.get(4).color = color_array[4];
                                matrix[2][2].planes.get(4).color = color_array[5];
                                matrix[2][2].planes.get(1).color = color_array[6];
                                matrix[1][2].planes.get(1).color = color_array[7];
                                matrix[0][2].planes.get(1).color = color_array[8];
                                matrix[0][2].planes.get(2).color = color_array[9];
                                matrix[0][1].planes.get(2).color = color_array[10];
                                matrix[0][0].planes.get(2).color = color_array[11];

                                rotate(matrix, rubic.rubic_size);
                                rotate(matrix, rubic.rubic_size);
                                rotate(matrix, rubic.rubic_size);
                                int q = 0;
                                Color[] turn_array = new Color[9];
                                for (int i = 0; i < matrix.length; ++i) {
                                    for (int j = 0; j < matrix.length; ++j) {
                                        turn_array[q] = matrix[i][j].planes.get(3).color;
                                        q++;
                                    }
                                }
                                rotate(matrix, rubic.rubic_size);
                                q=0;
                                for (int i = 0; i < matrix.length; ++i) {
                                    for (int j = 0; j < matrix.length; ++j) {
                                        matrix[i][j].planes.get(3).color = turn_array[q];
                                        q++;
                                        matrix[i][j].angY = 0;
                                    }
                                }
                                break;
                            }
                            case 51 ->{//f
                                Color[] color_array = new Color[12];

                                color_array[0] = matrix[0][2].planes.get(0).color;
                                color_array[1] = matrix[1][2].planes.get(0).color;
                                color_array[2] = matrix[2][2].planes.get(0).color;

                                color_array[3] = matrix[0][0].planes.get(2).color;
                                color_array[4] = matrix[0][1].planes.get(2).color;
                                color_array[5] = matrix[0][2].planes.get(2).color;

                                color_array[6] = matrix[0][0].planes.get(3).color;
                                color_array[7] = matrix[1][0].planes.get(3).color;
                                color_array[8] = matrix[2][0].planes.get(3).color;

                                color_array[9] = matrix[2][0].planes.get(4).color;
                                color_array[10]= matrix[2][1].planes.get(4).color;
                                color_array[11]= matrix[2][2].planes.get(4).color;

                                color_array = rotate(color_array);
                                color_array = rotate(color_array);
                                color_array = rotate(color_array);
                                color_array = rotate(color_array);
                                color_array = rotate(color_array);
                                color_array = rotate(color_array);
                                color_array = rotate(color_array);
                                color_array = rotate(color_array);
                                color_array = rotate(color_array);

                                matrix[0][2].planes.get(0).color = color_array[0];
                                matrix[1][2].planes.get(0).color = color_array[1];
                                matrix[2][2].planes.get(0).color = color_array[2];
                                matrix[0][0].planes.get(2).color = color_array[3];
                                matrix[0][1].planes.get(2).color = color_array[4];
                                matrix[0][2].planes.get(2).color = color_array[5];
                                matrix[0][0].planes.get(3).color = color_array[6];
                                matrix[1][0].planes.get(3).color = color_array[7];
                                matrix[2][0].planes.get(3).color = color_array[8];
                                matrix[2][0].planes.get(4).color = color_array[9];
                                matrix[2][1].planes.get(4).color = color_array[10];
                                matrix[2][2].planes.get(4).color = color_array[11];

                                rotate(matrix, rubic.rubic_size);
                                rotate(matrix, rubic.rubic_size);
                                rotate(matrix, rubic.rubic_size);
                                int q = 0;
                                Color[] turn_array = new Color[9];
                                for (int i = 0; i < matrix.length; ++i) {
                                    for (int j = 0; j < matrix.length; ++j) {
                                        turn_array[q] = matrix[i][j].planes.get(5).color;
                                        q++;
                                    }
                                }
                                rotate(matrix, rubic.rubic_size);
                                q=0;
                                for (int i = 0; i < matrix.length; ++i) {
                                    for (int j = 0; j < matrix.length; ++j) {
                                        matrix[i][j].planes.get(5).color = turn_array[q];
                                        q++;
                                        matrix[i][j].angZ = 0;
                                    }
                                }
                                break;
                            }
                            case 61 ->{//b
                                Color[] color_array = new Color[12];

                                color_array[0] = matrix[2][2].planes.get(0).color;
                                color_array[1] = matrix[1][2].planes.get(0).color;
                                color_array[2] = matrix[0][2].planes.get(0).color;

                                color_array[3] = matrix[0][0].planes.get(2).color;
                                color_array[4] = matrix[0][1].planes.get(2).color;
                                color_array[5] = matrix[0][2].planes.get(2).color;

                                color_array[6] = matrix[2][0].planes.get(3).color;
                                color_array[7] = matrix[1][0].planes.get(3).color;
                                color_array[8] = matrix[0][0].planes.get(3).color;

                                color_array[9] = matrix[2][2].planes.get(4).color;
                                color_array[10]= matrix[2][1].planes.get(4).color;
                                color_array[11]= matrix[2][0].planes.get(4).color;

                                color_array = rotate(color_array);
                                color_array = rotate(color_array);
                                color_array = rotate(color_array);
                                color_array = rotate(color_array);
                                color_array = rotate(color_array);
                                color_array = rotate(color_array);
                                color_array = rotate(color_array);
                                color_array = rotate(color_array);
                                color_array = rotate(color_array);

                                matrix[2][2].planes.get(0).color = color_array[0];
                                matrix[1][2].planes.get(0).color = color_array[1];
                                matrix[0][2].planes.get(0).color = color_array[2];
                                matrix[0][0].planes.get(2).color = color_array[3];
                                matrix[0][1].planes.get(2).color = color_array[4];
                                matrix[0][2].planes.get(2).color = color_array[5];
                                matrix[2][0].planes.get(3).color = color_array[6];
                                matrix[1][0].planes.get(3).color = color_array[7];
                                matrix[0][0].planes.get(3).color = color_array[8];
                                matrix[2][2].planes.get(4).color = color_array[9];
                                matrix[2][1].planes.get(4).color = color_array[10];
                                matrix[2][0].planes.get(4).color = color_array[11];

                                rotate(matrix, rubic.rubic_size);
                                rotate(matrix, rubic.rubic_size);
                                rotate(matrix, rubic.rubic_size);
                                int q = 0;
                                Color[] turn_array = new Color[9];
                                for (int i = 0; i < matrix.length; ++i) {
                                    for (int j = 0; j < matrix.length; ++j) {
                                        turn_array[q] = matrix[i][j].planes.get(1).color;
                                        q++;
                                    }
                                }
                                rotate(matrix, rubic.rubic_size);
                                q=0;
                                for (int i = 0; i < matrix.length; ++i) {
                                    for (int j = 0; j < matrix.length; ++j) {
                                        matrix[i][j].planes.get(1).color = turn_array[q];
                                        q++;
                                        matrix[i][j].angZ = 0;
                                    }
                                }
                                break;
                            }
                        }

                        t.cancel();
                        t.purge();
                        w = 1;
                    }
                }
            };

            t.scheduleAtFixedRate(tt, 50, 50);
            try {
                while(w!=1){
                    Thread.currentThread().sleep(1000);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            w = 0;*/
        }
        buildprocess = false;
    }
    public static Color[] rotate(Color[] matrix){
        Color[] tmp = new Color[matrix.length];
        for(int i = 0; i < matrix.length-1; ++i){
            tmp[i] = matrix[i+1];
        }
        tmp[matrix.length-1] = matrix[0];
        return tmp;
    }
    public static void rotate(Shape[][] matrix, int n) {
        for (int layer = 0; layer < n / 2; ++layer) {
            int first = layer;
            int last = n - 1 - layer;
            for(int i = first; i < last; ++i) {
                int offset = i - first;
                Shape top = matrix[first][i]; // save top

                // left -> top
                matrix[first][i] = matrix[last-offset][first];

                // bottom -> left
                matrix[last-offset][first] = matrix[last][last - offset];

                // right -> bottom
                matrix[last][last - offset] = matrix[i][last];

                // top -> right
                matrix[i][last] = top; // right <- saved top
            }
        }
    }
}