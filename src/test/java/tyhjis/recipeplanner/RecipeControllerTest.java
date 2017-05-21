package tyhjis.recipeplanner;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import tyhjis.recipeplanner.databaseconnection.ConnectionInterface;
import tyhjis.recipeplanner.databasecontrollers.RecipeController;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.mock;

public class RecipeControllerTest {

    static ArrayList<Recipe> expectedRecipes;
    static ArrayList<Recipe> expectedRecipes2;
    static RecipeController controller;

    @BeforeClass
    public static void initializeExpected() {
        expectedRecipes = new ArrayList<>();
        Recipe recipe = new Recipe();
        recipe.setCategoryId(1l);
        recipe.setId(1l);
        recipe.setUrl("testurl");
        expectedRecipes.add(recipe);
        recipe = new Recipe();
        recipe.setCategoryId(2l);
        recipe.setId(2l);
        recipe.setUrl("testUrl2");
        expectedRecipes.add(recipe);
    }

    @BeforeClass
    public static void initializeExpectedOne() {
        expectedRecipes2 = new ArrayList<>();
        Recipe recipe = new Recipe();
        recipe.setCategoryId(1l);
        recipe.setId(1l);
        recipe.setUrl("Another test url");
        expectedRecipes2.add(recipe);
    }

    @BeforeClass
    public static void initializeControllerObject() {
        controller = new RecipeController(new RecipeDatabaseConnectionMock());
    }

    @Test
    public void testGetAll() {
        ArrayList<Recipe> actualRecipes = controller.selectAll();
        Recipe expected = expectedRecipes.get(0);
        Recipe actual = actualRecipes.get(0);
        assertEquals(expected.getCategoryId(), actual.getCategoryId());
        expected = expectedRecipes.get(1);
        actual = actualRecipes.get(1);
        assertEquals(expected.getCategoryId(), actual.getCategoryId());
    }

    @Test
    public void testFindBy() {
        ArrayList<Recipe> actualResult = controller.findBy("id", 1);
        Recipe expected = expectedRecipes2.get(0);
        Recipe actual = actualResult.get(0);
        assertEquals(expected.getUrl(), actual.getUrl());
    }

    static class RecipeDatabaseConnectionMock implements ConnectionInterface {

        @Override
        public ResultSet query(String query) {
            ResultSet rsMock = mock(ResultSet.class);
            try {
                Mockito.when(rsMock.next()).thenReturn(true).thenReturn(true).thenReturn(false);
                Mockito.when(rsMock.getLong("id")).thenReturn(1l).thenReturn(2l);
                Mockito.when(rsMock.getLong("category_id")).thenReturn(1l).thenReturn(2l);
                Mockito.when(rsMock.getString("url")).thenReturn("testurl").thenReturn("testurl2");
                return rsMock;
            } catch(SQLException e) {
                System.err.println(e.getMessage());
                return null;
            }
        }

        @Override
        public ResultSet findByParameter(String table, String column, int value) {
            ResultSet rsMock = mock(ResultSet.class);
            try {
                Mockito.when(rsMock.next()).thenReturn(true).thenReturn(false);
                Mockito.when(rsMock.getLong("id")).thenReturn(1l);
                Mockito.when(rsMock.getLong("category_id")).thenReturn(1l);
                Mockito.when(rsMock.getString("url")).thenReturn("Another test url");
                return rsMock;
            } catch(SQLException e) {
                System.err.println(e.getMessage());
                return null;
            }
        }

        @Override
        public ResultSet findByParameter(String table, String column, String value) {
            return null;
        }

        @Override
        public ResultSet findByParameter(String table, String column, long value) {
            return null;
        }

        @Override
        public ResultSet findByParameter(String table, String column, float value) {
            return null;
        }

        @Override
        public ResultSet findByParameter(String table, String column, double value) {
            return null;
        }
    }
}
