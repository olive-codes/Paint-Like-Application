package patterns.builder;

public class NutritionLabel {
    public final String name;
    public final String ingredientList;
    public final int calories;
    public final double vitaminA;
    public final double vitaminB;
    public final double vitaminC;
    public final double vitaminD;

    public final double calcium;

    public NutritionLabel(String name, String ingredientList, int calories, double vitaminA, double vitaminB, double vitaminC, double vitaminD, double calcium) {
        this.name = name;
        this.ingredientList = ingredientList;
        this.calories = calories;
        this.vitaminD = vitaminD;
        this.vitaminA = vitaminA;
        this.vitaminB = vitaminB;
        this.vitaminC = vitaminC;
        this.calcium = calcium;
    }

}
