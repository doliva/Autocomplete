package org.autocomplete.org;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.autocomplete.manager.AutocompleteManager;
import org.autocomplete.parser.TextParser;

import java.util.List;

/**
 * Created by Matilda on 30/01/14.
 */
public class AutocompleteServiceImpl implements AutocompleteService {


    private AutocompleteManager autocompleteManager;


    private TextParser textParser;

    public AutocompleteServiceImpl(){

    }

    @Override
    public List<String> getValue(String key, String value) {
        return autocompleteManager.getTextsList(key,value);
    }

    @Override
    public boolean addTextToAutocomplete(String key, String value) {
        return autocompleteManager.addText(key,value);
    }



    private List<String> removeDuplicates(List<String> words) {
        words = Lists.newArrayList(Sets.newHashSet(words));
        return words;
    }


    @Override
    public boolean addTextsToAutocomplete(String key, List<String> values) {
        for (String text:values){
            addTextToAutocomplete(key,text);
        }
        return true;
    }


}
