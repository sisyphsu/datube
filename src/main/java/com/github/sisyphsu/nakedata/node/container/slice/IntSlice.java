package com.github.sisyphsu.nakedata.node.container.slice;

import com.github.sisyphsu.nakedata.context.model.ContextType;
import com.github.sisyphsu.nakedata.node.container.Slice;
import com.github.sisyphsu.nakedata.type.DataType;

/**
 * @author sulin
 * @since 2019-06-05 15:54:27
 */
public class IntSlice extends Slice {

    private int[] items;

    @Override
    public int size() {
        return 0;
    }

    @Override
    public DataType dataType() {
        return null;
    }

    @Override
    public ContextType contextType() {
        return null;
    }

}
