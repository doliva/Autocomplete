package org.autocomplete.manager;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.autocomplete.comparator.TextComparator;
import org.autocomplete.org.Node;
import org.autocomplete.parser.TextParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Matilda on 03/02/14.
 */
public class AutocompleteManagerBinaryTree implements AutocompleteManager {

    private TextComparator textComparator;
    private String middleWord;
    private Map<String, Map<Long, String>> keyTextMap;
    private Map<Long, String> idTextMap;
    private Node rootNode;
    private Node tmpNode;
    private Long lastId;
    private String separators;

    private String valueToSearch;
    private List<Long> referenceIds;


    private TextParser textParser;

    public AutocompleteManagerBinaryTree(){
        idTextMap = new HashMap<Long, String>();
        keyTextMap = new HashMap<String, Map<Long, String>>();
        rootNode = new Node();
        rootNode.setValue((Strings.isNullOrEmpty(middleWord)?"Lamela":middleWord));
        lastId = Long.valueOf(0);
        separators = (Strings.isNullOrEmpty(separators))?" ,":separators;
        referenceIds = new ArrayList<Long>();
    }
    @Override
    public Boolean addText(String key, String text) {
        Long actualId = getNextId();
        Map<Long, String> idTextTmp = keyTextMap.get(key);
        if(idTextTmp==null){
            idTextTmp = new HashMap<Long, String>();
        }
        idTextTmp.put(actualId,text);
        Node node;
        List<String> words = textParser.parseText(text);
        words = textParser.removeDuplicates(words);
        for (String word:words){
            tmpNode = rootNode;
            node = new Node();
            node.setValue(word);
            node.setReferenceIds(Lists.newArrayList(actualId));
            addValue(node);
        }
        keyTextMap.put(key, idTextTmp);
        return true;
    }

    @Override
    public List<String> getTextsList(String key, String word) {
        tmpNode = rootNode;
        Map<Long, String> idTextMapTmp = keyTextMap.get(key);
        if(idTextMapTmp ==null){
            return null;
        }
        valueToSearch = word;
        getReferencesIdsFromValue(tmpNode);
        List<String> values = new ArrayList<String>();
        for(Long id:referenceIds){
            values.add(idTextMapTmp.get(id));
        }
        return values;

    }

    private void getReferencesIdsFromValue(Node node){

        if(node!=null){
            if (textComparator.comparateTexts(node.getValue(), valueToSearch)){
                referenceIds.addAll(node.getReferenceIds());
            }

            getReferencesIdsFromValue(node.getLeftNode());
            getReferencesIdsFromValue(node.getRightNode());
        }

    }
    private Node addValue(Node node){

        if(tmpNode.getValue().compareTo(node.getValue())==0){
            System.out.println("Adding Id to an exiting Node. Word: "+node.getValue());
            tmpNode.getReferenceIds().addAll(node.getReferenceIds());
            return null;
        }
        if(tmpNode.getValue().compareTo(node.getValue())< 0){
            if(tmpNode.getLeftNode()==null){
                System.out.println("Adding Node as left child. Word: "+node.getValue());
                tmpNode.setLeftNodel(node);
                return null;
            }
            tmpNode = tmpNode.getLeftNode();
            System.out.println("Searching node in left child. Word: "+node.getValue());
            return addValue(node);
        }
        else{
            if(tmpNode.getRightNode()==null){
                System.out.println("Adding Node as right child. Word: "+node.getValue());
                tmpNode.setRightNode(node);
                return null;
            }
            System.out.println("Searching node in right child. Word: "+node.getValue());
            tmpNode = tmpNode.getRightNode();
            return addValue(node);
        }
    }

    private Node getNode(Node node, String value){
        if(node==null){
            return null;
        }
        if(node.getValue().compareTo(value) < 0){
            return getNode(node.getLeftNode(), value);
        }else if(node.getValue().compareTo(value) > 0){
            return getNode(node.getRightNode(),value);
        }
        //StringUtils.startsWith(node.getValue(),value);
        return ((StringUtils.startsWith(node.getValue(),value))?node:null);
    }

    private Long getNextId() {
        return lastId += 1;
    }

    public String getMiddleWord() {
        return middleWord;
    }

    public void setMiddleWord(String middleWord) {
        this.middleWord = middleWord;
    }

}
