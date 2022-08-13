package Rubic3;

import java.awt.*;

public class DrawTimer extends Thread{
    public Toolkit tk;
    DrawTimer(Toolkit tk){
        this.tk = tk;
    }

    @Override
    public void run() {
        super.run();
        tk.sync();
        /*boolean fl = false;
        for (int o = 0; o < rubic.rubic_size; ++o) {
            for (int k = 0; k < rubic.rubic_size; ++k) {
                for (int l = 0; l < rubic.rubic_size; ++l) {
                    if (l == 2) fl = scene.RotateZ(rubic.cube[o][k][l]);
                }
            }
        }
        scene.repaint();
        if (fl) this.cancel();*/
    }
}
