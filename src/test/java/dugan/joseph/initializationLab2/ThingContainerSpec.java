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
    private static ColorfulThing colorfulThing1 = new ColorfulThing(ColorfulThing.Color.BLACK);
    private static ColorfulThing colorfulThing2 = new ColorfulThing(ColorfulThing.Color.BLUE);
    private static ColorfulThing colorfulThing3 = new ColorfulThing(ColorfulThing.Color.RED);
    private static ColorfulThing colorfulThing4 = new ColorfulThing(ColorfulThing.Color.GREEN);

    // some ColorfulThing s array
    private static ColorfulThing[] loadColorfulThings = {colorfulThing1, colorfulThing2, colorfulThing3};
    private static ColorfulThing[] overloadColorfulThings = {colorfulThing1, colorfulThing2, colorfulThing3, colorfulThing4};

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

    private static ColorfulThing[] colorfulThingsPostPop = {colorfulThing1, colorfulThing2, null};
    private static ColorfulThing poppedThing = colorfulThing3;

    @Test
    public void popItemOffEndOfArray() {
        // load array
        for (ColorfulThing thing : loadColorfulThings) {
            fullThingContainer.add(thing);
        }

        // test popped
        // 1. are arrays indiscernible
        ColorfulThing poppedOff = fullThingContainer.pop();
        ColorfulThing[] expectedArray =  colorfulThingsPostPop;
        ColorfulThing[] actualArray = fullThingContainer.getColorfulThings();
        assertArrayEquals(expectedArray, actualArray);
        // 2. was the expected item popped
        ColorfulThing expectedThing = poppedThing;
        ColorfulThing actualThing = poppedOff;
        assertEquals(expectedThing, actualThing);
    }

    private static ColorfulThing[] colorfulThingPostRemoveByColor = {colorfulThing1, colorfulThing3, null};
    private static ColorfulThing expectedColorfulThingWhenThingQualifies = colorfulThing2;
    private static ColorfulThing.Color searchByColor = colorfulThing2.getColor();
    private static ColorfulThing expectedColorfulThingWhenNoElementQualifies = null;
    private static ColorfulThing.Color unusedColor = ColorfulThing.Color.YELLOW;

    @Test
    public void removeItemByColor() {
        // load array
        for (ColorfulThing thing : loadColorfulThings) {
            fullThingContainer.add(thing);
        }

        // test with element in array
        ColorfulThing actualColorfulThing = fullThingContainer.remove(searchByColor);
        assertEquals(expectedColorfulThingWhenThingQualifies, actualColorfulThing);

        // test for absent element in array.
        ColorfulThing actualColorfulThingWhenNoElementQualifies = fullThingContainer.remove(unusedColor);
        assertEquals(expectedColorfulThingWhenNoElementQualifies, actualColorfulThingWhenNoElementQualifies);

        // test arrays for indiscernibility
        ColorfulThing[] actualArray = fullThingContainer.getColorfulThings();
        assertArrayEquals(colorfulThingPostRemoveByColor, actualArray);


    }

    private static ColorfulThing[] colorfulThingsPostRemoveByObject = {colorfulThing2, colorfulThing3, null};
    private static ColorfulThing colorfulThingToMatch = colorfulThing1;
    private static ColorfulThing colorfulThingToMiss = colorfulThing4;
    private static ColorfulThing valueOfColorfulThingWhenMissed = null;

    @Test
    public void removeItemByObject() {
        // load array
        for (ColorfulThing thing : loadColorfulThings) {
            fullThingContainer.add(thing);
        }

        // test with matching object
        ColorfulThing actualRemovedThing = fullThingContainer.remove(colorfulThingToMatch);
        ColorfulThing expectedRemovedThing = colorfulThingToMatch;
        assertEquals(expectedRemovedThing, actualRemovedThing);

        // test with missing object
        actualRemovedThing = fullThingContainer.remove(colorfulThingToMiss);
        expectedRemovedThing = valueOfColorfulThingWhenMissed;
        assertEquals(expectedRemovedThing, actualRemovedThing);

        // test array
        assertArrayEquals(fullThingContainer.getColorfulThings(), colorfulThingsPostRemoveByObject);

    }

    @After
    public void tearDown() {
        fullThingContainer = null;
        thingContainer = null;
    }
}
