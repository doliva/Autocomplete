package org.autocomplete.manager;

import java.util.List;

/**
 * Created by Matilda on 03/02/14.
 */
public interface AutocompleteManager {


    Boolean addText(String key, String text);

    List<String> getTextsList(String key, String word);


}
