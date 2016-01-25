package dugan.joseph.initializationLab2;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by joseph on 1/25/16.
 */
public class ThingContainerSpec {
    // ThingContainer aray size
    private final static int SIZE = 3;

    // Some ColorfulThing s
    private final static ColorfulThing colorfulThing1 = new ColorfulThing(ColorfulThing.Color.BLACK);
    private final static ColorfulThing colorfulThing2 = new ColorfulThing(ColorfulThing.Color.BLUE);
    private final static ColorfulThing colorfulThing3 = new ColorfulThing(ColorfulThing.Color.RED);
    private final static ColorfulThing colorfulThing4 = new ColorfulThing(ColorfulThing.Color.GREEN);

    // some ColorfulThing s array
    private final static ColorfulThing[] loadColorfulThings = {colorfulThing1, colorfulThing2, colorfulThing3};
    private final static ColorfulThing[] overloadColorfulThings = {colorfulThing1, colorfulThing2, colorfulThing3, colorfulThing4};

    // ThingContainer s
    private ThingContainer fullThingContainer;
    private ThingContainer thingContainer;


    @Before
    public void setUp() {
        // set up full thingContainer
        fullThingContainer = new ThingContainer(SIZE);

        // set up thingContainer
        thingContainer = new ThingContainer(SIZE);
    }

    @Test
    public void addItemToColorfulThings() {
        // over-fill container
        for (ColorfulThing thing : overloadColorfulThings) {
            fullThingContainer.add(thing);
        }
        // is it not equal to full container
        ColorfulThing[] expectedOverFull = overloadColorfulThings;
        ColorfulThing[] actualOverFull = fullThingContainer.getColorfulThings();
        assertTrue(!expectedOverFull.equals(actualOverFull));

        //fill container
        int idx = 0;
        for (ColorfulThing thing : loadColorfulThings) {
            thingContainer.add(thing);
            //check to see if things are added in order given
            ColorfulThing expectedObj = loadColorfulThings[idx];
            ColorfulThing actualObj = thingContainer.getColorfulThings()[idx];
            assertEquals(expectedObj, actualObj);
            idx++;
        }
        // check if full array is correct
        ColorfulThing[] expectedFull = loadColorfulThings;
        ColorfulThing[] actualFull = thingContainer.getColorfulThings();
        assertArrayEquals(expectedFull, actualFull);

    }

    @After
    public void tearDown() {
        fullThingContainer = null;
        thingContainer = null;
    }
}
