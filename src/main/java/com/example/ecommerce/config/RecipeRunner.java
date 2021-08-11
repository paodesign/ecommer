package com.example.ecommerce.config;

import java.util.List;

import com.example.ecommerce.entity.*;
import com.example.ecommerce.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class RecipeRunner implements CommandLineRunner {

    @Autowired
    private UserRepository recetaRepository;

    @Override
    public void run(String... args) throws Exception {
/*
        Recipe recipe = new Recipe();
        recipe.setDescription("Una buena Receta de budin de chocolate,facil de realizar.");
        recipe.setCookTime("50 minutos a 200 grados.");
        recipe.setPrepTime("10 minutos.");
        recipe.setDifficulty(Difficulty.MODERATE);

        Category dulce = new Category("Dulcesy");
        Category chocolate = new Category("Chocolate");
        recipe.setCategories(List.of(dulce, chocolate));

        Ingredient ingredient = new Ingredient();
        ingredient.setDescription("Harina");
        ingredient.setAmount(300);
        ingredient.setUnitOfMesure(new UnitOfMesure("gr."));

        Ingredient ingredient1 = new Ingredient();
        ingredient1.setDescription("Azucar");
        ingredient1.setAmount(300);
        UnitOfMesure unitOfMesure1 = new UnitOfMesure();
        unitOfMesure1.setUnitOfMesure("gr.");
        ingredient1.setUnitOfMesure(unitOfMesure1);

        Ingredient ingredient2 = new Ingredient();
        ingredient2.setDescription("Huevos");
        ingredient2.setAmount(2);
        UnitOfMesure unitOfMesure2 = new UnitOfMesure();
        unitOfMesure2.setUnitOfMesure("unid.");
        ingredient2.setUnitOfMesure(unitOfMesure2);

        Ingredient ingredient3 = new Ingredient();
        ingredient3.setDescription("Chocolate");
        ingredient3.setAmount(100);
        UnitOfMesure unitOfMesure3 = new UnitOfMesure();
        unitOfMesure3.setUnitOfMesure("gr.");
        ingredient3.setUnitOfMesure(unitOfMesure3);

        Ingredient ingredient4 = new Ingredient();
        ingredient4.setDescription("Leche");
        ingredient4.setAmount(100);
        UnitOfMesure unitOfMesure4 = new UnitOfMesure();
        unitOfMesure4.setUnitOfMesure("ml.");
        ingredient4.setUnitOfMesure(unitOfMesure4);

        recipe.setIngredients(List.of(ingredient, ingredient1, ingredient2, ingredient3, ingredient4));

        recipe = recetaRepository.save(recipe);

        System.out.println("Fin");
        */
    }
}
