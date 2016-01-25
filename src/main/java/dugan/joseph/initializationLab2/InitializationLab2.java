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

    public ColorfulThing pop() {
        if (numOfObjs == 0) {
            System.out.println("ColorfulThing array is empty");
            return null;
        }
        else {
            int idx = numOfObjs - 1;
            ColorfulThing popped = colorfulThings[idx];
            colorfulThings[idx] = null;
            numOfObjs--;
            return popped;
        }
    }

    public ColorfulThing remove(ColorfulThing.Color color) {
        for (int i = 0; i < colorfulThings.length; i++) {
            if (colorfulThings[i] != null &&
                    color.equals(colorfulThings[i].getColor())) {
                ColorfulThing removed = colorfulThings[i];
                colorfulThings[i] = null;
                numOfObjs--;
                if (i < colorfulThings.length - 1)
                    shift(i);
                return removed;
            }
        }
        return null;
    }

    public ColorfulThing remove(ColorfulThing colorfulThing) {
        for (int i = 0; i < colorfulThings.length; i++) {
            ColorfulThing currentThing = colorfulThings[i];
            if (currentThing != null &&
                    currentThing.equals(colorfulThing)) {
                ColorfulThing removed = currentThing;
                colorfulThings[i] = null;
                numOfObjs--;
                if (i < colorfulThings.length - 1)
                    shift(i);
                return removed;
            }
        }
        return null;
    }

    public void printThings() {
        for (ColorfulThing thing: colorfulThings) {
            if (thing == null)
                System.out.println("null");
            else
                System.out.println(thing.toString());
        }
    }

    private void shift(int idx) {
        for (int i = idx; i < colorfulThings.length - 1; i++) {
            ColorfulThing next = colorfulThings[i + 1];
            colorfulThings[i] =  next;
            colorfulThings[i + 1] = null;
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

        ThingContainer[] resultContainer = new ThingContainer[NUM_CONTAINERS];
        int idx = 0;

        for (ThingContainer thingContainer: collection) {
            int size = random.nextInt(CONTAINER_SIZE_LIMIT) + 1;
            thingContainer = new ThingContainer(size);
            for (int i = 0; i <= size; i++) {
                thingContainer.add(new ColorfulThing(ColorfulThing.Color.getRandom()));
            }
            thingContainer.printThings();
            resultContainer[idx] = thingContainer;
            idx++;
        }

        final ColorfulThing.Color colorToRemove = ColorfulThing.Color.BLUE;
        ThingContainer demonstrationCollection = resultContainer[0];
        ColorfulThing[] thingsArray = demonstrationCollection.getColorfulThings();
        ColorfulThing thingToRemove = thingsArray[0];

        System.out.println("Thing container before removes: ");
        demonstrationCollection.printThings();

        ColorfulThing removedThing1 = demonstrationCollection.remove(colorToRemove);
        ColorfulThing removedThing2 = demonstrationCollection.remove(thingToRemove);

        System.out.println("ColorfulThings removed: ");
        System.out.println(removedThing1.toString());
        System.out.println(removedThing2.toString());

        System.out.println("Thing container after removes: ");
        demonstrationCollection.printThings();


    }
}
