package ua.edu.ucu.flowerstore.flower;

//Propose an approach to represent flower color (enum class). 
public enum FlowerColor {
    RED("#FF0000"), BLUE("#0000FF"),
     WHITE("#000000"), YELLOW("#FFFF00"),
     ORANGE("FF5C00");
    private final String stringRepresentation;

    FlowerColor(String stringRepresentation) {
        this.stringRepresentation = stringRepresentation;
    }

    @Override
    public String toString() {
        return stringRepresentation;
    }
}
