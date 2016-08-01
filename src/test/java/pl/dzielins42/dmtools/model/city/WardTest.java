package pl.dzielins42.dmtools.model.city;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class WardTest {

    @Test
    public void testWard() {
        new Ward(Ward.Type.ADMINISTRATION, 10.0d, null);

        boolean exceptionCaught;

        exceptionCaught = false;
        try {
            new Ward(null, 10.0d, null);
        } catch (IllegalArgumentException e) {
            exceptionCaught = true;
        }
        if (!exceptionCaught) {
            fail("Expected exception: java.lang.IllegalArgumentException");
        }

        exceptionCaught = false;
        try {
            new Ward(Ward.Type.ADMINISTRATION, 0.0d, null);
        } catch (IllegalArgumentException e) {
            exceptionCaught = true;
        }
        if (!exceptionCaught) {
            fail("Expected exception: java.lang.IllegalArgumentException");
        }

        exceptionCaught = false;
        try {
            new Ward(Ward.Type.ADMINISTRATION, -10.0d, null);
        } catch (IllegalArgumentException e) {
            exceptionCaught = true;
        }
        if (!exceptionCaught) {
            fail("Expected exception: java.lang.IllegalArgumentException");
        }
    }

    @Test
    public void testGetType() {
        Ward ward = new Ward(Ward.Type.ADMINISTRATION, 10.0d, null);
        assertEquals(ward.getType(), Ward.Type.ADMINISTRATION);
    }

    @Test
    public void testSetType() {
        Ward ward = new Ward(Ward.Type.ADMINISTRATION, 10.0d, null);

        boolean exceptionCaught = false;
        try {
            ward.setType(null);
        } catch (IllegalArgumentException e) {
            exceptionCaught = true;
        }
        if (!exceptionCaught) {
            fail("Expected exception: java.lang.IllegalArgumentException");
        }

        ward.setType(Ward.Type.CRAFTSMEN);
        assertEquals(ward.getType(), Ward.Type.CRAFTSMEN);
    }

    @Test
    public void testGetArea() {
        Ward ward = new Ward(Ward.Type.ADMINISTRATION, 10.0d, null);
        assertEquals(ward.getArea(), 10.0d, 0.0d);
    }

    @Test
    public void testSetArea() {
        Ward ward = new Ward(Ward.Type.ADMINISTRATION, 10.0d, null);

        boolean exceptionCaught;

        exceptionCaught = false;
        try {
            ward.setArea(0.0d);
        } catch (IllegalArgumentException e) {
            exceptionCaught = true;
        }
        if (!exceptionCaught) {
            fail("Expected exception: java.lang.IllegalArgumentException");
        }

        exceptionCaught = false;
        try {
            ward.setArea(-10.0d);
        } catch (IllegalArgumentException e) {
            exceptionCaught = true;
        }
        if (!exceptionCaught) {
            fail("Expected exception: java.lang.IllegalArgumentException");
        }

        ward.setArea(20.0d);
        assertEquals(ward.getArea(), 20.0d, 0.0d);
    }

    @Test
    public void testGetBuildings() {
        Ward ward;

        ward = new Ward(Ward.Type.ADMINISTRATION, 10.0d, null);
        assertNull(ward.getBuildings());

        List<WardBuilding> buildings = new ArrayList<WardBuilding>();
        buildings.add(new WardBuilding(WardBuilding.Style.LUXURIOUS, WardBuilding.Type.ADMIN));
        ward = new Ward(Ward.Type.ADMINISTRATION, 10.0d, buildings);
        assertNotNull(ward.getBuildings());
    }

    @Test
    public void testSetBuildings() {
        Ward ward = new Ward(Ward.Type.ADMINISTRATION, 10.0d, null);

        List<WardBuilding> buildings = new ArrayList<WardBuilding>();
        buildings.add(new WardBuilding(WardBuilding.Style.LUXURIOUS, WardBuilding.Type.ADMIN));

        assertNull(ward.getBuildings());
        ward.setBuildings(buildings);
        assertNotNull(ward.getBuildings());
    }

    @Test
    public void testGetBuildingCount() {
        Ward ward = new Ward(Ward.Type.ADMINISTRATION, 10.0d, null);

        List<WardBuilding> buildings = new ArrayList<WardBuilding>();
        buildings.add(new WardBuilding(WardBuilding.Style.LUXURIOUS, WardBuilding.Type.ADMIN));

        assertEquals(ward.getBuildingCount(), 0);
        ward.setBuildings(buildings);
        assertEquals(ward.getBuildingCount(), buildings.size());
    }

}
