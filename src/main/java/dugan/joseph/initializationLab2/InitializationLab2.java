package dugan.joseph.initializationLab2;

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

    public ThingContainer(ColorfulThing[] colorfulThings) {
        this(colorfulThings.length);
        for (ColorfulThing thing : colorfulThings) {
            add(thing);
        }
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
        ColorfulThing colorfulThing1 = new ColorfulThing(ColorfulThing.Color.BLACK);
        ColorfulThing colorfulThing2 = new ColorfulThing(ColorfulThing.Color.BLUE);
        ColorfulThing colorfulThing3 = new ColorfulThing(ColorfulThing.Color.GREEN);

        ThingContainer thingContainerToPass = new ThingContainer(3);

        thingContainerToPass.add(colorfulThing1);
        thingContainerToPass.add(colorfulThing2);
        thingContainerToPass.add(colorfulThing3);

        System.out.println("ThingContainer to be passed to constructor: ");
        thingContainerToPass.printThings();

        ThingContainer thingContainerFromThingContainerArgConstructor = new ThingContainer(thingContainerToPass.getColorfulThings());

        System.out.println("ThingContainer created with ThingContainer arg constructor: ");
        thingContainerFromThingContainerArgConstructor.printThings();

        System.out.println("ThingContainers are the same objects: " +
                thingContainerToPass.equals(thingContainerFromThingContainerArgConstructor));


    }
}
