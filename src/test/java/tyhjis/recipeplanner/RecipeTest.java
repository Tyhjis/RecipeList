package tyhjis.recipeplanner;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class RecipeTest {

    @Test
    public void testSetUrl() {
        Recipe recipe = new Recipe();
        recipe.setUrl("http://google.com");
        assertEquals("http://google.com", recipe.getUrl());
    }

    @Test
    public void testSetCategoryId() {
        Recipe recipe = new Recipe();
        recipe.setCategoryId(3);
        assertEquals(3, recipe.getCategoryId());
    }

    @Test
    public void testSetId() {
        Recipe recipe = new Recipe();
        recipe.setId(1);
        assertEquals(1, recipe.getId());
    }

    @Test
    public void testGetTable() {
        Recipe recipe = new Recipe();
        assertEquals("recipes", recipe.getTable());
    }
}