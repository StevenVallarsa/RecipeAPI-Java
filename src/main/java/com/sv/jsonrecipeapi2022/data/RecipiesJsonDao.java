
package com.sv.jsonrecipeapi2022.data;

import com.sv.jsonrecipeapi2022.models.Recipe;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Repository;


/**
 *
 * @author: Steven Vallarsa
 *   email: stevenvallarsa@gmail.com
 *    date: 2022-01-24
 * purpose: 
 */
@Repository
public class RecipiesJsonDao implements RecipiesDao {
    
        private List<Recipe> recipiesList = new ArrayList<>();
        
        // load recipies list at startup
        public RecipiesJsonDao() {
            
            JSONParser parser = new JSONParser();
        
            try {
                Object obj = parser.parse(new FileReader("data.json"));
                JSONObject jObj = (JSONObject)obj;
                JSONArray recipies = (JSONArray)jObj.get("recipes") ;

                Iterator<JSONObject> iterator = recipies.iterator();
                while (iterator.hasNext()) {
                    JSONObject recipe = new JSONObject(iterator.next());
                    Recipe r = new Recipe();
                    r.setName(recipe.get("name").toString());
                    
                    JSONArray ingredientsArray = (JSONArray)recipe.get("ingredients");
                    String[] ingredients = new String[ingredientsArray.size()];
                    for (int i = 0; i < ingredientsArray.size(); i++) {
                        ingredients[i] = ingredientsArray.get(i).toString();
                    }
                    r.setIngredients(ingredients);
                    
                    JSONArray instructionsArray = (JSONArray)recipe.get("instructions");
                    String[] instructions = new String[instructionsArray.size()];
                    for (int i = 0; i < instructionsArray.size(); i++) {
                        instructions[i] = instructionsArray.get(i).toString();
                    }
                    r.setInstructions(instructions);
                    
                    recipiesList.add(r);
                    
//                    System.out.println(recipe.get("name"));
//                    System.out.println(recipe.get("ingredients"));
//                    System.out.println(recipe.get("instructions"));
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        

    @Override
    public List<String> getAllRecipeNames() {
        List<String> recipeNames = new ArrayList<>();
        for (Recipe r : recipiesList) {
            recipeNames.add(r.getName());
        }
        return recipeNames;
    }

    @Override
    public Recipe getRecipe(String recipe) {
        System.out.println(recipe);
        return recipiesList.stream()
                .filter(r -> r.getName().toLowerCase().equals(recipe.toLowerCase()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Recipe addRecipe(Recipe recipe) {
        String name = recipe.getName().toLowerCase();
        Recipe recipeExists = recipiesList.stream()
                .filter(x -> x.getName().toLowerCase() == name)
                .findFirst()
                .orElse(null);
        
        if (recipeExists == null) {
            return null;
        } else {
            recipiesList.add(recipe);
            saveNewJson();
            return recipe;
        }
    }

    @Override
    public boolean updateRecipe(Recipe recipe) {
        String name = recipe.getName().toLowerCase();
        Recipe recipeExists = recipiesList.stream()
                .filter(x -> x.getName().toLowerCase() == name)
                .findFirst()
                .orElse(null);
        
        if (recipeExists == null) {
            return false;
        } else {
            recipiesList.remove(recipe.getName());
            recipiesList.add(recipe);
            saveNewJson();
            return true;
        }
        
    }

    private void saveNewJson() {
        
        JSONObject newJsonFile = new JSONObject();
        
        for (Recipe recipe : recipiesList) {
            newJsonFile.put("name", recipe.getName());
            newJsonFile.put("ingredients", recipe.getIngredients());
            newJsonFile.put("instructions", recipe.getInstructions());
        }
        
        try {
            FileWriter file = new FileWriter("data.json");
            file.write(newJsonFile.toJSONString());
            file.flush();
            file.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

}
