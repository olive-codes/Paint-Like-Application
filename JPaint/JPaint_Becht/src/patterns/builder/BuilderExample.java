package patterns.builder;

public class BuilderExample {
 
    public static void main(String[] args) {
        NutritionLabelBuilder builder = new NutritionLabelBuilder("Pizza");
        NutritionLabel pizza = builder
          .vitaminA(.10)  
          .ingredient("Flour", 150)
          .ingredient("Pepperoni", 150)
          .ingredient("Cheese", 200)
          .ingredient("Tomato Sauce", 250)
          .build();
        System.out.println(pizza.vitaminA);
    }
    
}
