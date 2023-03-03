package patterns.builder;

import java.util.LinkedList;
import java.util.List;

public class NutritionLabelBuilder {
    
    private String name;
    private List<String> ingredientList = new LinkedList<>();
    private int calories = 0;
    private double vitaminA = 0;
    private double vitaminB = 0;
    private double vitaminC = 0;
    private double vitaminD = 0;
    private double calcium = 0;

    public NutritionLabelBuilder(String name) {
        this.name = name;
    }

    public NutritionLabelBuilder ingredient(String ingredient, int calories) {
        this.ingredientList.add(ingredient);
        this.calories += calories;
        return this;
    }

    public NutritionLabelBuilder vitaminA(double vitaminA) {
        this.vitaminA = vitaminA;
        return this;
    }

    public NutritionLabelBuilder vitaminB(double vitaminB) {
        this.vitaminB = vitaminB;
        return this;
    }

    public NutritionLabelBuilder vitaminC(double vitaminC) {
        this.vitaminC = vitaminC;
        return this;
    }

    public NutritionLabelBuilder vitaminD(double vitaminD) {
        this.vitaminD = vitaminD;
        return this;
    }

    public NutritionLabelBuilder calcium(double calcium) {
        this.calcium = calcium;
        return this;
    }

    public NutritionLabel build() {
        String ingredientList = this.ingredientList.toString();
        return new NutritionLabel(name, ingredientList, calories, vitaminA, vitaminB, vitaminC, vitaminD, calcium);
    }
}
