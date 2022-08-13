package Rubic3;

import Engine3D.Shape;

import java.awt.*;

import static Rubic3.Main.rotate;
import static Rubic3.Main.rubic;

public class BuldProcess extends Thread{
    public String formula;

    BuldProcess(String formula){
        super();
        this.formula = formula;
    }
    @Override
    public void run(){
            Main.buildprocess = true;
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
                final Toolkit tk = Toolkit.getDefaultToolkit();

                long start_time = 0;
                long end_time = 0;
                long summ_time = 0;
                boolean fl = false;
                while(true){
                    if(fl) break;
                    end_time = System.currentTimeMillis();
                    summ_time += end_time - start_time;
                    if(summ_time >= 50){
                        tk.sync();
                        Engine3D.Shape[][] matrix = new Shape[rubic.rubic_size][rubic.rubic_size];
                        boolean drawprocess = true;
                        for (int o = 0; o < rubic.rubic_size; ++o) {
                            for (int k = 0; k < rubic.rubic_size; ++k) {
                                for (int l = 0; l < rubic.rubic_size; ++l) {
                                    switch (finalCommand) {
                                        // "RLUDFBrludfb"
                                        case 1 -> {
                                            if (o == 2) {
                                                drawprocess = Main.scene.RotateX(5, rubic.cube[o][k][l]);//R
                                                matrix[k][l] = rubic.cube[2][k][l];
                                            }
                                            break;
                                        }
                                        case 2 -> {
                                            if (o == 0) {
                                                drawprocess = Main.scene.RotateX(5, rubic.cube[o][k][l]);//L
                                                matrix[k][l] = rubic.cube[o][k][l];
                                            }
                                            break;
                                        }
                                        case 3 -> {
                                            if (k == 2) {
                                                drawprocess = Main.scene.RotateY(5, rubic.cube[o][k][l]);//U
                                                matrix[o][l] = rubic.cube[o][k][l];
                                            }
                                            break;
                                        }
                                        case 4 -> {
                                            if (k == 0) {
                                                drawprocess = Main.scene.RotateY(5, rubic.cube[o][k][l]);//D
                                                matrix[o][l] = rubic.cube[o][k][l];
                                            }
                                            break;
                                        }
                                        case 5 -> {
                                            if (l == 0) {
                                                drawprocess = Main.scene.RotateZ(5, rubic.cube[o][k][l]);//F
                                                matrix[o][k] = rubic.cube[o][k][l];
                                            }
                                            break;
                                        }
                                        case 6 -> {
                                            if (l == 2) {
                                                drawprocess = Main.scene.RotateZ(5, rubic.cube[o][k][l]);//B
                                                matrix[o][k] = rubic.cube[o][k][l];
                                            }
                                            break;
                                        }
                                        case 11 -> {
                                            if (o == 2) {
                                                drawprocess = Main.scene.RotateX(-5, rubic.cube[o][k][l]);//r
                                                matrix[k][l] = rubic.cube[o][k][l];
                                            }
                                            break;
                                        }
                                        case 21 -> {
                                            if (o == 0) {
                                                drawprocess = Main.scene.RotateX(-5, rubic.cube[o][k][l]);//l
                                                matrix[k][l] = rubic.cube[o][k][l];
                                            }
                                            break;
                                        }
                                        case 31 -> {
                                            if (k == 2) {
                                                drawprocess = Main.scene.RotateY(-5, rubic.cube[o][k][l]);//u
                                                matrix[o][l] = rubic.cube[o][k][l];
                                            }
                                            break;
                                        }
                                        case 41 -> {
                                            if (k == 0) {
                                                drawprocess = Main.scene.RotateY(-5, rubic.cube[o][k][l]);//d
                                                matrix[o][l] = rubic.cube[o][k][l];
                                            }
                                            break;
                                        }
                                        case 51 -> {
                                            if (l == 0) {
                                                drawprocess = Main.scene.RotateZ(-5, rubic.cube[o][k][l]);//f
                                                matrix[o][k] = rubic.cube[o][k][l];
                                            }
                                            break;
                                        }
                                        case 61 -> {
                                            if (l == 2) {
                                                drawprocess = Main.scene.RotateZ(-5, rubic.cube[o][k][l]);//b
                                                matrix[o][k] = rubic.cube[o][k][l];
                                            }
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                        Main.scene.repaint();
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
            Main.buildprocess = false;
    }
}
