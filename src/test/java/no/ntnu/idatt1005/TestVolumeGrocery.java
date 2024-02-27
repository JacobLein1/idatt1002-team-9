package no.ntnu.idatt1005;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestVolumeGrocery {
    @Test
    void testVolumeGrocery() {
        VolumeGrocery vg = new VolumeGrocery("Melk", "bilde", 1, "l");
        Assertions.assertEquals(vg.getName(),"Melk");
        Assertions.assertEquals(vg.getImage(),"bilde");
        Assertions.assertEquals(vg.getAmount(), 1);
        Assertions.assertEquals(vg.getUnit(3), "l");

    }
}
