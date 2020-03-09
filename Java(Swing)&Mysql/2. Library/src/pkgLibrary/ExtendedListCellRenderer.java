/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgLibrary;

import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

/**
 * Combo box with default message
 * to see default again, set index -1
 * https://tips4java.wordpress.com/2008/10/18/combo-box-prompt/
 */
class ExtendedListCellRenderer extends DefaultListCellRenderer{
 
    @Override
    public Component getListCellRendererComponent(JList<?> list,
                                           Object value,
                                           int index,
                                           boolean isSelected,
                                           boolean cellHasFocus)
    {
        super.getListCellRendererComponent(list,value,index,isSelected,cellHasFocus);
        
        if(value == null){
            setText("-- please select --");
        }
        return this;
    }
}