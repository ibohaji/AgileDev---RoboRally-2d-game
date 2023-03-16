package Utils;

import java.awt.GridBagConstraints;
import java.awt.Insets;

public class GridBagConstraintsBuilder {
    private GridBagConstraints c = new GridBagConstraints();

    public GridBagConstraintsBuilder(int x, int y) {
        c.gridx = x;
        c.gridy = y;
    }

    public GridBagConstraints build() {
        return c;
    }

    public GridBagConstraintsBuilder inset(int inset) {
        c.insets = new Insets(inset, inset, inset, inset);
        return this;
    }

    public GridBagConstraintsBuilder inset(int horizontal, int vertical) {
        c.insets = new Insets(vertical, horizontal, vertical, horizontal);
        return this;
    }

    public GridBagConstraintsBuilder inset(int top, int left, int bottom, int right) {
        c.insets = new Insets(top, left, bottom, right);
        return this;
    }

    public GridBagConstraintsBuilder insetX(int inset) {
        c.insets = new Insets(0, inset, 0, inset);
        return this;
    }

    public GridBagConstraintsBuilder insetY(int inset) {
        c.insets = new Insets(inset, 0, inset, 0);
        return this;
    }

    public GridBagConstraintsBuilder gridSize(int width, int height) {
        c.gridwidth = width;
        c.gridheight = height;
        return this;
    }

    public GridBagConstraintsBuilder gridWidth(int width) {
        c.gridwidth = width;
        return this;
    }

    public GridBagConstraintsBuilder gridHeight(int height) {
        c.gridheight = height;
        return this;
    }

    public GridBagConstraintsBuilder weight(double x, double y) {
        c.weightx = x;
        c.weighty = y;
        return this;
    }

    public GridBagConstraintsBuilder weightX(double x) {
        c.weightx = x;
        return this;
    }

    public GridBagConstraintsBuilder weightY(double y) {
        c.weighty = y;
        return this;
    }

    public GridBagConstraintsBuilder fill(int fill) {
        c.fill = fill;
        return this;
    }

    public GridBagConstraintsBuilder ipad(int x, int y) {
        c.ipadx = x;
        c.ipady = y;
        return this;
    }

    public GridBagConstraintsBuilder ipadX(int x) {
        c.ipadx = x;
        return this;
    }

    public GridBagConstraintsBuilder ipadY(int y) {
        c.ipady = y;
        return this;
    }

    public GridBagConstraintsBuilder anchor(int anchor) {
        c.anchor = anchor;
        return this;
    }
}