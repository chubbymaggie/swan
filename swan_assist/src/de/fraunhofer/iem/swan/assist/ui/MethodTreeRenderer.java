package de.fraunhofer.iem.swan.assist.ui;

import com.intellij.ui.JBColor;
import de.fraunhofer.iem.swan.assist.data.InfoBank;
import de.fraunhofer.iem.swan.assist.data.MethodWrapper;
import de.fraunhofer.iem.swan.assist.util.Constants;
import de.fraunhofer.iem.swan.assist.util.Formatter;
import de.fraunhofer.iem.swan.data.CWE;
import de.fraunhofer.iem.swan.data.Category;
import icons.IconUtils;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeCellRenderer;
import java.awt.*;
import java.util.ArrayList;

/**
 * Rendering options for a method in the list.
 *
 * @author Oshando Johnson
 */

public class MethodTreeRenderer extends JLabel implements TreeCellRenderer {

    private JLabel text = new JLabel();

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {

        if (selected)
            text.setForeground(JBColor.WHITE);
        else
            text.setForeground(JBColor.BLACK);

        if (value instanceof DefaultMutableTreeNode) {
            Object object = ((DefaultMutableTreeNode) value).getUserObject();

            if (object instanceof MethodWrapper) {

                MethodWrapper method = (MethodWrapper) object;
                text.setText("<html>" + Formatter.trimProperty(method.getMethodName(false)) + " ( ) <font color='gray'>" + Formatter.trimProperty(method.getReturnType(false)) + "</font></html>");
                text.setIcon(IconUtils.getNodeIcon(method.getTypesList(false)));

                if (method.getUpdateOperation() != null && method.getUpdateOperation().equals(Constants.METHOD_ADDED) && !selected)
                    text.setForeground(new JBColor(new Color(1, 128, 0), new Color(1, 128, 0)));
                else if (method.getUpdateOperation() != null && method.getUpdateOperation().equals(Constants.METHOD_DELETED) && !selected)
                    text.setForeground(new JBColor(new Color(178, 34, 34), new Color(178, 34, 34)));

                text.setToolTipText(method.getMethodName(true));

            } else if (object instanceof Category) {

                Category category = (Category) object;

                text.setIcon(IconUtils.getIcon(category.toString()));
                text.setText(category.toString());
            } else {

                text.setText(value.toString());
                text.setIcon(null);
            }
        }
        return text;
    }
}
