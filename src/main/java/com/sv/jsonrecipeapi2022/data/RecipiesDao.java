/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sv.jsonrecipeapi2022.data;

import com.sv.jsonrecipeapi2022.models.Recipe;
import java.util.List;

/**
 *
 * @author StevePro
 */
public interface RecipiesDao {
    
    List<String> getAllRecipeNames();
    Recipe getRecipe(String recipe);
    Recipe addRecipe(Recipe recipe);
    boolean updateRecipe(Recipe recipe);
    
}
