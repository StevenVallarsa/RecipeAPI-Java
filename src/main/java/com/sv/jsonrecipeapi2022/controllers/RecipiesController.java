
package com.sv.jsonrecipeapi2022.controllers;

import com.sv.jsonrecipeapi2022.data.RecipiesDao;
import com.sv.jsonrecipeapi2022.models.Recipe;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author: Steven Vallarsa
 *   email: stevenvallarsa@gmail.com
 *    date: 2022-01-24
 * purpose: 
 */
@RestController
@RequestMapping("/recipies")
public class RecipiesController {

    private final RecipiesDao dao;
    
    public RecipiesController(RecipiesDao dao) {
        this.dao = dao;
    }
    
    @GetMapping
    public List<String> getAllRecipiesNames() {
        
        return dao.getAllRecipeNames();
    }
    
    @GetMapping("/{recipeName}")
    public ResponseEntity<Recipe> getRecipe(@PathVariable String recipeName) {
        System.out.println(recipeName);
        Recipe recipe = dao.getRecipe(recipeName);
        
        if (recipe == null) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(recipe);
    }
    
    
}
