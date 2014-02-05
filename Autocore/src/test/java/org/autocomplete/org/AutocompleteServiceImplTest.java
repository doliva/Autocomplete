package org.autocomplete.org;

import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Matilda on 30/01/14.
 */
public class AutocompleteServiceImplTest {

    private static final String TEXT_ONE = "Te quiero";
    private static final String TEXT_TWO = "Quiero besarte";
    private static final String TEXT_THREE = "Quiero abrazarte";
    private static final String TEXT_FOUR = "Quiero besabrazarte";
    private static final String TEXT_FIVE = "La quiero toda";
    private static final String TEXT_SIX = "Te quiero arriba/abajo/atras";
    private static final String TEXT_SEVEN= "Quiero dormir con vos";
    private static final String TEXT_EIGHT= "Quiero más";
    private static final String TEXT_NINE = "Te quiero bañar";
    private static final String TEXT_TEN = "Que quilobmo hicimos";
    private static final String TEXT_ELEVEN = "Quisiera repetirlo";
    private static final String TEXT_TWELVE = "Terminé aniquilada";


    private AutocompleteServiceImpl autocompleteService;



    @Before
    public void setUp(){
        autocompleteService = new AutocompleteServiceImpl();
    }


    @Test
    public void shouldGetTheRightTextOneTextIsPreset(){
        String text = "La venganza será terrible";

        autocompleteService.addTextToAutocomplete("title", text);
        List<String> texts = autocompleteService.getValue("title", "será");
        Assert.assertEquals("The text returned is not the expected",text, Iterators.getOnlyElement(texts.iterator()));
    }

    @Test
    public void shouldGetTwoRightTextWhenThreeTextArePreset(){
        String textOne = "La venganza será terrible";
        String textTwo = "Iván el terrible";
        String textThree = "Esta película es horrible";

        autocompleteService.addTextsToAutocomplete("title", Lists.newArrayList(textOne,textTwo,textThree));
        List<String> texts = autocompleteService.getValue("title", "terrible");
        for(String text:texts){
            System.out.println(text);
        }
        Assert.assertEquals("The quanty of elements is not right", 2, texts.size());
        //Assert.assertEquals("The text returned is not the expected",text, Iterators.getOnlyElement(texts.iterator()));
    }

    @Test
    public void testKeyDoesNotExist(){
        List<String> texts = autocompleteService.getValue("title", "ser");
        Assert.assertNull("The key exists and should not",texts);
    }

    @Test
    public void shouldGetAllRightTextWhenTheTextStartWithOrContainsPrefixCaseInsensitive(){

        List<String> sentencesList = Lists.newArrayList(TEXT_ONE,TEXT_TWO,TEXT_THREE,TEXT_FOUR,TEXT_FIVE,TEXT_SIX,TEXT_SEVEN,TEXT_EIGHT,TEXT_NINE,TEXT_TEN,TEXT_ELEVEN,TEXT_TWELVE);
        Assert.assertEquals(12, sentencesList.size());
        autocompleteService.addTextsToAutocomplete("title", sentencesList);
        List<String> texts = autocompleteService.getValue("title", "qui");
        for(String text:texts){
            System.out.println(text);
        }
        Assert.assertEquals("The quanty of elements is not right", 12, texts.size());
        Assert.assertTrue("The list of texts are not the expected", texts.containsAll(Lists.newArrayList(TEXT_ONE,TEXT_TWO,TEXT_THREE,TEXT_FOUR,TEXT_FIVE,TEXT_SIX,TEXT_SEVEN,TEXT_EIGHT,TEXT_NINE,TEXT_TEN,TEXT_ELEVEN,TEXT_TWELVE)));
    }

    @Test
    public void shouldGetAllRightTextWhenTheTextStartWithPrefixCaseInsensitive(){
        List<String> sentencesList = Lists.newArrayList(TEXT_ONE,TEXT_TWO,TEXT_THREE,TEXT_FOUR,TEXT_FIVE,TEXT_SIX,TEXT_SEVEN,TEXT_EIGHT,TEXT_NINE,TEXT_TEN,TEXT_ELEVEN,TEXT_TWELVE);
        autocompleteService.addTextsToAutocomplete("title", sentencesList);
        autocompleteService.setCompareContains(false);
        List<String> texts = autocompleteService.getValue("title", "qui");

        Assert.assertEquals("The quanty of elements is not right", 11, texts.size());
        Assert.assertTrue("The list of texts are not the expected", texts.containsAll(Lists.newArrayList(TEXT_ONE,TEXT_TWO,TEXT_THREE,TEXT_FOUR,TEXT_FIVE,TEXT_SIX,TEXT_SEVEN,TEXT_EIGHT,TEXT_NINE,TEXT_TEN,TEXT_ELEVEN)));
    }


    @Test
    public void shouldGetAllRightTextWhenTheTextStartWithPrefixCaseSensitive(){

        List<String> sentencesList = Lists.newArrayList(TEXT_ONE,TEXT_TWO,TEXT_THREE,TEXT_FOUR,TEXT_FIVE,TEXT_SIX,TEXT_SEVEN,TEXT_EIGHT,TEXT_NINE,TEXT_TEN,TEXT_ELEVEN,TEXT_TWELVE);
        Assert.assertEquals(12, sentencesList.size());
        autocompleteService.addTextsToAutocomplete("title", sentencesList);
        autocompleteService.setCompareContains(false);
        autocompleteService.setCompareCaseSensitive(true);
        List<String> texts = autocompleteService.getValue("title", "qui");
        Assert.assertEquals("The quanty of elements is not right", 5, texts.size());
        Assert.assertTrue("The list of texts are not the expected", texts.containsAll(Lists.newArrayList(TEXT_ONE,TEXT_FIVE,TEXT_SIX,TEXT_NINE,TEXT_TEN)));
    }
}
