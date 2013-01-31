/**
 * Copyright 2012 A24Group
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 */
package org.ssgwt.client.ui.tree;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.ClientBundle.Source;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;

/**
 * The tree class.
 * 
 * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
 * @since  31 Jan 2013
 *
 * @param <NodeType> The type of the top level nodes
 * @param <SubNodes> The type of the sub nodes that can be found on the top level nodes
 */
public abstract class Tree<NodeType extends NodeObject, SubNodes extends NodeObject> extends Composite {
    
    /**
     * The main panel that holds all the items
     */
    private FlowPanel mainPanel = new FlowPanel();
    
    /**
     * The data used to create the tree
     */
    private List<NodeType> treeData;
    
    /**
     * This holds all the selected items
     */
    private List<Object> selectedItems = new ArrayList<Object>();
    
    /**
     * Class constructor
     * 
     * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
     * @since  31 Jan 2013
     */
    public Tree() {
        initWidget(mainPanel);
    }
    
    /**
     * Set the data the tree should display. Calling this function will redraw the tree
     * 
     * @param treeData - The data that will be used to draw the tree
     * @param viewState - Flag indicating if the tree is in view or edit state
     * 
     * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
     * @since  31 Jan 2013
     */
    public void setData(List<NodeType> treeData, boolean viewState) {
        this.treeData = treeData;
        createNodes(viewState);
    }
    
    /**
     * Retrieves the data the that was used to draw the tree
     * 
     * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
     * @since  31 Jan 2013
     * 
     * @return The tree data set using the setData function
     */
    public List<NodeType> getData() {
        return treeData;
    }
    
    /**
     * Retrieves only the selected items
     * 
     * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
     * @since  31 Jan 2013
     * 
     * @return The selected items
     */
    public List<?> getSelectedItems() {
        return treeData;
    }
    
    /**
     * Adds a selected item to the selected item list
     * 
     * @param selectedItem The selected that should be added to the selected item list
     * 
     * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
     * @since  31 Jan 2013
     */
    public void addSelectedItem(Object selectedItem) {
        selectedItems.add(selectedItem);
    }
    
    /**
     * Removes a selected item from the selected item list
     * 
     * @param selectedItem The that should the removed from the selected item list
     * 
     * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
     * @since  31 Jan 2013
     */
    public void removeSelectedItem(Object selectedItem) {
        selectedItems.remove(selectedItem);
    }
    
    /**
     * Creates all the node top level nodes for the tree
     * 
     * @param viewState Flag to indicate if the tree should be in view or edit state
     * 
     * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
     * @since  31 Jan 2013
     */
    private void createNodes(boolean viewState) {
        for (NodeType nodeData : treeData) {
            TreeNode tempNode = createNode();
            tempNode.setNodeData(nodeData, viewState);
            tempNode.setParentTree(this);
            mainPanel.add(tempNode);
        }
    }
    
    /**
     * Creates an instance of a top level node
     * 
     * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
     * @since  31 Jan 2013
     * 
     * @return The new instance of the top level node
     */
    public abstract TreeNode<NodeType, SubNodes> createNode();
}