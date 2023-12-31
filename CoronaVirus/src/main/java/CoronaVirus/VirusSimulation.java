

package CoronaVirus;

import java.awt.*;

public class VirusSimulation {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Region virusFrame = new Region("Corona Virus Simulation");//calls the HeatChamber constructor
                virusFrame.Initalize();
            }
        });
    }

}
