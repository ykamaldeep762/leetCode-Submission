import java.util.*;

class FoodRatings {

    class Food {
        String food;
        int rating;

        Food(String food, int rating) {
            this.food = food;
            this.rating = rating;
        }
    }

    Map<String, String> foodToCuisine;
    Map<String, Integer> foodToRating;
    Map<String, PriorityQueue<Food>> cuisineMap;

    public FoodRatings(String[] foods, String[] cuisines, int[] ratings) {

        foodToCuisine = new HashMap<>();
        foodToRating = new HashMap<>();
        cuisineMap = new HashMap<>();

        for (int i = 0; i < foods.length; i++) {
            foodToCuisine.put(foods[i], cuisines[i]);
            foodToRating.put(foods[i], ratings[i]);

            cuisineMap.putIfAbsent(
                cuisines[i],
                new PriorityQueue<>(
                    (a, b) -> {
                        if (a.rating != b.rating) {
                            return b.rating - a.rating; // higher rating first
                        }
                        return a.food.compareTo(b.food); // lexicographically smaller first
                    }
                )
            );

            cuisineMap.get(cuisines[i]).offer(
                new Food(foods[i], ratings[i])
            );
        }
    }

    public void changeRating(String food, int newRating) {

        foodToRating.put(food, newRating);

        String cuisine = foodToCuisine.get(food);

        cuisineMap.get(cuisine).offer(
            new Food(food, newRating)
        );
    }

    public String highestRated(String cuisine) {

        PriorityQueue<Food> pq = cuisineMap.get(cuisine);

        while (true) {
            Food top = pq.peek();

            if (foodToRating.get(top.food) == top.rating) {
                return top.food;
            }

            pq.poll(); // remove outdated entry
        }
    }
}


/**
 * Your FoodRatings object will be instantiated and called as such:
 * FoodRatings obj = new FoodRatings(foods, cuisines, ratings);
 * obj.changeRating(food,newRating);
 * String param_2 = obj.highestRated(cuisine);
 */