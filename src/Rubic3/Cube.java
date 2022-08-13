package Rubic3;

import Engine3D.Shape;

import java.util.ArrayList;

public class Cube {
    public int rubic_size;
    public Shape[][][] cube;
    public ArrayList<Shape> shapes = new ArrayList<>();

    public

    Cube(int size, int x, int y, int z){
        this.rubic_size = size;//todo why 3 == 2)
        this.cube = new Shape[size][size][size];
        for (int o = 0; o < this.rubic_size; ++o) {
            for (int k = 0; k < this.rubic_size; ++k) {
                for (int l = 0; l < this.rubic_size; ++l) {
                    //if(o != 1 || ((k != 0 || k != 2) || (l != 0 || l != 2))) {
                        Shape shape = new Shape("(" + o + "," + k + "," + l + ")", 1, x + o * 70, y + k * 70, z + l * -70);
                        shapes.add(shape);
                        cube[o][k][l] = shape;
                    //}
                }
            }
        }
    }
}
