package org.autocomplete.parser;

import java.util.List;

/**
 * Created by Matilda on 03/02/14.
 */
public interface TextParser {

    List<String> parseText(String text);

    List<String> removeDuplicates(List<String> words);
}
