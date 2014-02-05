package org.autocomplete.org;

import java.util.List;

/**
 * Created by Matilda on 30/01/14.
 */
public interface AutocompleteService {

    List<String> getValue(String key, String value);

    boolean addTextToAutocomplete(String key, String value);

    boolean addTextsToAutocomplete(String key, List<String> values);

}
