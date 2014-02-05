package org.autocomplete.comparator;

/**
 * Created by Matilda on 03/02/14.
 */
public abstract class TextComparator {

    private boolean compareCaseSensitive;
    private boolean compareContains;

    public abstract Boolean comparateTexts(String actualValue, String valueToSearch);

    public boolean isCompareCaseSensitive() {
        return compareCaseSensitive;
    }

    public void setCompareCaseSensitive(boolean compareCaseSensitive) {
        this.compareCaseSensitive = compareCaseSensitive;
    }

    public boolean isCompareContains() {
        return compareContains;
    }

    public void setCompareContains(boolean compareContains) {
        this.compareContains = compareContains;
    }
}
