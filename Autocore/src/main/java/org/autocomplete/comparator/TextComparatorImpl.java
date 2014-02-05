package org.autocomplete.comparator;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by Matilda on 03/02/14.
 */
public class TextComparatorImpl extends TextComparator{

    @Override
    public Boolean comparateTexts(String actualValue, String valueToSearch) {

        Boolean comparationOk=false;
        if(isCompareContains()){
            if(isCompareCaseSensitive()){
                comparationOk=(StringUtils.startsWith(actualValue, valueToSearch)||StringUtils.contains(actualValue, valueToSearch));
            }else {
                comparationOk=(StringUtils.startsWithIgnoreCase(actualValue, valueToSearch)||StringUtils.contains(actualValue, valueToSearch));
            }
        }else{
            if(isCompareCaseSensitive()){
                comparationOk=(StringUtils.startsWith(actualValue,valueToSearch));
            }else {
                comparationOk=(StringUtils.startsWithIgnoreCase(actualValue, valueToSearch));
            }

        }
        return comparationOk;
    }

}
