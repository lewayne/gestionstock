package com.lewayne.gestiondestock.services.impl;

import com.lewayne.gestiondestock.dto.CategorieDTO;
import com.lewayne.gestiondestock.exception.EntityNotFoundException;
import com.lewayne.gestiondestock.exception.ErrorCodes;
import com.lewayne.gestiondestock.exception.InvalidEntityException;
import com.lewayne.gestiondestock.services.CategorieService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

// @RunWith(SpringRunner.class)
// @SpringBootTest prepare le contexte, insere les beans nécessaires pour le test
public class CategorieServiceImplTest {

    @Autowired
    private CategorieService categorieService;

    @Test
    public void shouldSaveCategoryWithSuccess() {
        CategorieDTO expectedCategory = CategorieDTO.builder()
                .code("Cat test")
                .designation("Designation test")
                .idEntreprise(1)
                .build();

        CategorieDTO savedCategory = categorieService.save(expectedCategory);

        assertNotNull(savedCategory);
        assertNotNull(savedCategory.getId());
        assertEquals(expectedCategory.getCode(), savedCategory.getCode());
        assertEquals(expectedCategory.getDesignation(), savedCategory.getDesignation());
        assertEquals(expectedCategory.getIdEntreprise(), savedCategory.getIdEntreprise());
    }

    @Test
    public void shouldUpdateCategoryWithSuccess() {
        CategorieDTO expectedCategory = CategorieDTO.builder()
                .code("Cat test")
                .designation("Designation test")
                .idEntreprise(1)
                .build();

        CategorieDTO savedCategory = categorieService.save(expectedCategory);

        CategorieDTO categoryToUpdate = savedCategory;
        categoryToUpdate.setCode("Cat update");

        savedCategory = categorieService.save(categoryToUpdate);

        assertNotNull(categoryToUpdate);
        assertNotNull(categoryToUpdate.getId());
        assertEquals(categoryToUpdate.getCode(), savedCategory.getCode());
        assertEquals(categoryToUpdate.getDesignation(), savedCategory.getDesignation());
        assertEquals(categoryToUpdate.getIdEntreprise(), savedCategory.getIdEntreprise());
    }

    @Test
    public void shouldThrowInvalidEntityException() {
        CategorieDTO expectedCategory = CategorieDTO.builder().build(); // on crée un objet vide

        InvalidEntityException expectedException = assertThrows(InvalidEntityException.class, () -> categorieService.save(expectedCategory));

        assertEquals(ErrorCodes.CATEGORY_NOT_VALID, expectedException.getErrorCode());
        assertEquals(1, expectedException.getErrors().size());
        assertEquals("veuillez renseigner le code de la catégorie", expectedException.getErrors().get(0));
    }


    /*  Test
    public void shouldThrowEntityNotFoundException() {
        EntityNotFoundException expectedException = assertThrows(EntityNotFoundException.class, () -> categorieService.findById(0));

        assertEquals(ErrorCodes.CATEGORY_NOT_FOUND, expectedException.getErrorCode());
        assertEquals("Aucune category avec l'ID = 0 n' ete trouve dans la BDD", expectedException.getMessage());
    }
*/
    /* Test(expected = EntityNotFoundException.class)
    public void shouldThrowEntityNotFoundException2() {
        categorieService.findById(0);
    } */
}