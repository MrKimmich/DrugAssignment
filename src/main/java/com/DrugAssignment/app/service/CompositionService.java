package com.DrugAssignment.app.service;

import com.DrugAssignment.app.dto.CompositionDto;
import com.DrugAssignment.app.dto.DataDto;
import com.DrugAssignment.app.dto.IngredientDataDto;
import com.DrugAssignment.app.dto.IngredientDto;
import com.DrugAssignment.app.model.*;
import com.DrugAssignment.app.utils.ServiceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.google.common.collect.Sets;

@Service
public class CompositionService {

    @Autowired
    private CompositionRepositoryService compositionRepositoryService;

    public String saveCompositions(List<DataDto> dataDtoList){
        ServiceUtils serviceUtils = new ServiceUtils();
        for (DataDto data: dataDtoList) {
            List<IngredientDataDto> ingredientsList = new ArrayList<>();

            String compositionName = serviceUtils.getCompositionName(data.getIngredientDto());
            try{
                compositionRepositoryService.saveComposition(new Compositions(compositionName));
            }catch (Exception e){
                System.out.println("Duplicate Composition");
            }

            for (IngredientDto ingredientDto: data.getIngredientDto()) {
                try{
                    Ingredients ingredient = compositionRepositoryService.saveIngredients(new Ingredients(ingredientDto.getName()));
                    IngredientDataDto ingredientDataDto = new IngredientDataDto();
                    ingredientDataDto.setIngredient(ingredient);
                    ingredientDataDto.setStrength(ingredientDto.getStrength());
                    ingredientDataDto.setUnit(ingredientDto.getUnit());
                    ingredientsList.add(ingredientDataDto);
                }catch (Exception e){
                    System.out.println("Duplicate Ingredient");
                    Ingredients ingredient = compositionRepositoryService.getIngredientsByName(ingredientDto.getName());
                    IngredientDataDto ingredientDataDto = new IngredientDataDto();
                    ingredientDataDto.setIngredient(ingredient);
                    ingredientDataDto.setStrength(ingredientDto.getStrength());
                    ingredientDataDto.setUnit(ingredientDto.getUnit());
                    ingredientsList.add(ingredientDataDto);
                }

            }

            String moleculeName = serviceUtils.getMoleculeName(data.getIngredientDto());
            System.out.println("Debug1: "+moleculeName);
            try {
                Molecules molecule = new Molecules(moleculeName, data.isRxRequired());
                Molecules molecules = compositionRepositoryService.saveMolecule(molecule);
                System.out.println("Debug2: "+molecules.getName());
            }catch (Exception e){
                System.out.println("Duplicate Molecule");
            }

            for (IngredientDataDto ingredientDataDto: ingredientsList) {
                Compositions composition = compositionRepositoryService.getCompositionByName(compositionName);
                CompositionIngredients compositionIngredients = new CompositionIngredients();
                compositionIngredients.setIngredients(ingredientDataDto.getIngredient());
                compositionIngredients.setCompositions(composition);
                compositionIngredients.setStrength(ingredientDataDto.getStrength());
                compositionIngredients.setUnit(ingredientDataDto.getUnit());
                compositionRepositoryService.saveCompositionIngredients(compositionIngredients);
            }

            for (IngredientDataDto ingredientDataDto: ingredientsList) {
                Molecules molecule = compositionRepositoryService.getMoleculeByName(moleculeName);
                MoleculeIngredients moleculeIngredients = new MoleculeIngredients();
                moleculeIngredients.setMolecules(molecule);
                moleculeIngredients.setIngredients(ingredientDataDto.getIngredient());
                compositionRepositoryService.saveMoleculeIngredients(moleculeIngredients);
            }
        }
        return "Success";
    }

    public CompositionDto getCompositionById(int id){
        List<IngredientDto> ingredientDtoList = new ArrayList<>();
        List<Set<Integer>> moleculeIdSetList = new ArrayList<>();

        Compositions composition = compositionRepositoryService.getCompositionById(id);
        List<CompositionIngredients> compositionIngredientsList = compositionRepositoryService.getCompositionIngredients(composition);

        for (CompositionIngredients compositionIngredients: compositionIngredientsList) {
            IngredientDto ingredientDto = new IngredientDto();
            ingredientDto.setName(compositionIngredients.getIngredients().getName());
            ingredientDto.setStrength(compositionIngredients.getStrength());
            ingredientDto.setUnit(compositionIngredients.getUnit());
            ingredientDtoList.add(ingredientDto);
        }

        for (CompositionIngredients compositionIngredients: compositionIngredientsList) {
            List<MoleculeIngredients> moleculeIngredients = compositionRepositoryService.getMoleculeIngredientsByIngredients(compositionIngredients.getIngredients());
            Set<Integer> moleculeIdSet = new HashSet<>();
            for (MoleculeIngredients moleculeIngredients1: moleculeIngredients){
                moleculeIdSet.add(moleculeIngredients1.getMolecules().getId());
            }
            moleculeIdSetList.add(moleculeIdSet);
        }
        Set<Integer> moleculeIngredients1 = moleculeIdSetList.get(0);
        for (Set<Integer> moleculeIngredientsHashSet : moleculeIdSetList){
            moleculeIngredients1 = Sets.intersection(moleculeIngredients1, moleculeIngredientsHashSet);
        }
        ArrayList<Integer> moleculeId = new ArrayList<>(moleculeIngredients1);
        Molecules molecule = compositionRepositoryService.getMoleculeById(moleculeId.get(0));

        CompositionDto compositionDto = new CompositionDto();
        compositionDto.setCompositionName(composition.getName());
        compositionDto.setIngredientDtoList(ingredientDtoList);
        compositionDto.setMolecule(molecule);
        return compositionDto;
    }

    public List<CompositionDto> getCompositionByIngredientStrengthUnit(int id, float strength, String unit){
        Ingredients ingredient = compositionRepositoryService.getIngredientsById(id);
        List<CompositionIngredients> compositionIngredientsList = compositionRepositoryService.getCompositionByIngredientStrengthUnit(ingredient, strength, unit);

        List<CompositionDto> compositionDtoList = new ArrayList<>();
        for (CompositionIngredients compositionIngredient:compositionIngredientsList){
            CompositionDto compositionDto = new CompositionDto();
            compositionDto.setCompositionName(compositionIngredient.getCompositions().getName());
            compositionDtoList.add(compositionDto);
        }
        return compositionDtoList;
    }
}
