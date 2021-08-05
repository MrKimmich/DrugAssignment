package com.DrugAssignment.app.service;

import com.DrugAssignment.app.model.*;
import com.DrugAssignment.app.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompositionRepositoryService {

    @Autowired
    private CompositionsRepository compositionsRepository;
    @Autowired
    private IngredientsRepository ingredientsRepository;
    @Autowired
    private MoleculesRepository moleculesRepository;
    @Autowired
    private CompositionIngredientsRepository compositionIngredientsRepository;
    @Autowired
    private MoleculeIngredientsRepository moleculeIngredientsRepository;

    public Compositions saveComposition(Compositions composition) throws Exception{
        return compositionsRepository.save(composition);
    }

    public Ingredients saveIngredients(Ingredients ingredient) throws Exception{
        return ingredientsRepository.save(ingredient);
    }

    public Molecules saveMolecule(Molecules molecule) throws Exception{
        return moleculesRepository.save(molecule);
    }

    public void saveCompositionIngredients(CompositionIngredients compositionIngredients){
        compositionIngredientsRepository.save(compositionIngredients);
    }

    public void saveMoleculeIngredients(MoleculeIngredients moleculeIngredients){
        moleculeIngredientsRepository.save(moleculeIngredients);
    }

    public Compositions getCompositionById(int id){
        return compositionsRepository.getById(id);
    }

    public Compositions getCompositionByName(String name){
        return compositionsRepository.findByName(name);
    }

    public List<CompositionIngredients> getCompositionIngredients(Compositions compositions){
        return compositionIngredientsRepository.findByCompositions(compositions);
    }

    public Ingredients getIngredientsById(int id){
        return ingredientsRepository.getById(id);
    }

    public Ingredients getIngredientsByName(String name){
        return ingredientsRepository.findByName(name);
    }

    public Molecules getMoleculeById(int id){
        return moleculesRepository.getById(id);
    }

    public List<MoleculeIngredients> getMoleculeIngredientsByIngredients(Ingredients ingredients){
        return moleculeIngredientsRepository.findByIngredients(ingredients);
    }

    public Molecules getMoleculeByName(String name){
        return moleculesRepository.findByName(name);
    }

    public List<CompositionIngredients> getCompositionByIngredientStrengthUnit(Ingredients ingredients, float strength, String unit){
        return compositionIngredientsRepository.findByIngredientsAndStrengthAndUnit(ingredients, strength, unit);
    }
}
