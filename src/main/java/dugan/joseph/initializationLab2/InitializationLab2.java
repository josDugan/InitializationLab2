package dugan.joseph.initializationLab2;

import java.util.Random;

/**
 * Created by joseph on 1/25/16.
 */
class ColorfulThing {

    enum Color {
        BLACK, BLUE, RED, GREEN, YELLOW;

        public static Color getRandom() {

            return values()[(int)(Math.random() * values().length)];
        }
    }

    private Color color;

    public ColorfulThing(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public String toString() {
        return "Color object with color: " + color.toString();
    }
}

class ThingContainer {
    private ColorfulThing[] colorfulThings;
    private int size;
    private int numOfObjs;

    public ThingContainer(int size) {
        colorfulThings = new ColorfulThing[size];
        this.size = size;
        numOfObjs = 0;
    }

    public ColorfulThing[] getColorfulThings() {
        return colorfulThings;
    }

    public void add(ColorfulThing colorfulThing) {
        if (numOfObjs < size) {
            int idx = numOfObjs;
            colorfulThings[idx] = colorfulThing;
            numOfObjs++;
        }
        else {
            System.out.println("ThingContainer is full");
        }

    }

    public void printThings() {
        for (ColorfulThing thing: colorfulThings) {
            System.out.println(thing.toString());
        }
    }
}

public class InitializationLab2 {


    public static void main(String[] args) {
        Random random = new Random();

        ThingContainer thingContainer1 = null;
        ThingContainer thingContainer2 = null;
        ThingContainer thingContainer3 = null;

        final int NUM_CONTAINERS = 3;
        final int CONTAINER_SIZE_LIMIT = 20;

        ThingContainer[] collection = new ThingContainer[NUM_CONTAINERS];
        collection[0] = thingContainer1;
        collection[1] = thingContainer2;
        collection[2] = thingContainer3;

        for (ThingContainer thingContainer: collection) {
            int size = random.nextInt(CONTAINER_SIZE_LIMIT);
            thingContainer = new ThingContainer(size);
            for (int i = 0; i <= size; i++) {
                thingContainer.add(new ColorfulThing(ColorfulThing.Color.getRandom()));
            }
            thingContainer.printThings();
        }


    }
}
