package org.autocomplete.parser;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * Created by Matilda on 03/02/14.
 */
public class TextParserApache implements TextParser {

    private String separators;
    private final static String defaultSeparators=" ,";

    public TextParserApache(){
    }

    public TextParserApache(final String separators){
        this.separators=separators;

    }

    @Override
    public List<String> parseText(final String text) {
        final List<String> words = Lists.newArrayList(StringUtils.split(text, getSeparators()));
        return words;
    }

    @Override
    public List<String> removeDuplicates(List<String> words) {
        words = Lists.newArrayList(Sets.newHashSet(words));
        return words;
    }

    public String getSeparators() {
        return ((Strings.isNullOrEmpty(separators))?defaultSeparators:separators);
    }

    public void setSeparators(String separators) {
        this.separators = separators;
    }
}
