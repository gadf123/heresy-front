package com.heresy.beanTest;

import com.heresy.utills.Pagination;
import org.junit.Test;

public class testBean {

    @Test
    public void select() throws Exception {
        Pagination a = new Pagination(10, 10, 2);
        System.out.println(a.getOffset());
    }
}
