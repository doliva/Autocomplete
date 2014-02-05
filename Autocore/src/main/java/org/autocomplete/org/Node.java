package org.autocomplete.org;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Matilda on 30/01/14.
 */
public class Node {

    private String value;
    private List<Long> referenceIds;
    private Node leftNodel;
    private Node rightNodel;

    public Node(){
        this.value = "";
        this.referenceIds = new ArrayList<Long>();
        this.leftNodel = null;
        this.rightNodel = null;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<Long> getReferenceIds() {
        if(referenceIds== null){
            referenceIds = new ArrayList<Long>();
        }
        return referenceIds;
    }

    public void setReferenceIds(List<Long> referenceIds) {
        this.referenceIds = referenceIds;
    }

    public Node getLeftNode() {
        return leftNodel;
    }

    public void setLeftNodel(Node leftNodel) {
        this.leftNodel = leftNodel;
    }

    public Node getRightNode() {
        return rightNodel;
    }

    public void setRightNode(Node rightNodel) {
        this.rightNodel = rightNodel;
    }
}
