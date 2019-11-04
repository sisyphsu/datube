package com.github.sisyphsu.canoe.transport;

import com.github.sisyphsu.canoe.utils.TimeUtils;
import org.junit.jupiter.api.Test;

/**
 * @author sulin
 * @since 2019-10-08 16:02:37
 */
public class OutputStructPoolTest {

    @Test
    public void test() {
        OutputMetaPool pool = new OutputMetaPool(16);
        try {
            pool.registerTmpStruct(null);
            assert false;
        } catch (Exception e) {
            assert e instanceof NullPointerException;
        }
        try {
            pool.registerCxtStruct(null);
            assert false;
        } catch (Exception e) {
            assert e instanceof NullPointerException;
        }
        assert pool.registerCxtStruct() == 0;
        assert pool.registerTmpStruct() == 0;

        assert pool.registerCxtStruct("id1", "name") == 2 + 1;
        assert pool.registerCxtStruct("id.2", "name") == 2 * 2 + 1;
        assert pool.registerCxtStruct("id.3", "name") == 3 * 2 + 1;
        assert pool.registerCxtStruct("id.4", "name") == 4 * 2 + 1;
        assert pool.registerCxtStruct("id.5", "name") == 5 * 2 + 1;
        assert pool.registerCxtStruct("id1", "name") == 3;

        assert pool.cxtStructIndex.size() == 5;
        assert pool.registerCxtStruct("id1", "name") == 3;
        assert pool.registerCxtStruct("id.5", "name") == 5 * 2 + 1;

        // temporary struct
        assert pool.registerTmpStruct("id1", "name", "desc") == 2;
        assert pool.registerTmpStruct("id2", "name", "desc") == 2 * 2;
        assert pool.registerTmpStruct("id3", "name", "desc") == 3 * 2;
        assert pool.registerTmpStruct("id4", "name", "desc") == 4 * 2;
        assert pool.registerTmpStruct("id5", "name", "desc") == 5 * 2;
        assert pool.registerTmpStruct("id6", "name", "desc") == 6 * 2;
        assert pool.registerTmpStruct("id1", "name", "desc") == 2;

        assert pool.tmpStructIndex.size() == 6;

        pool.reset();
        assert pool.registerCxtStruct("id.5", "name") == 5 * 2 + 1;
        assert pool.registerTmpStruct("id6", "name", "desc") == 2;
    }

    @Test
    public void testStruct() {
        OutputMetaPool.Struct struct1 = new OutputMetaPool.Struct(new String[]{"id", "name"}, null);
        OutputMetaPool.Struct struct2 = new OutputMetaPool.Struct(new String[]{"id", "name"}, null);

        assert struct1.equals(struct2);
        assert struct1.hashCode() == struct2.hashCode();

        assert !struct1.equals(new Object());
    }

    @Test
    public void testExpire() throws InterruptedException {
        TimeUtils.INTERVAL = 8;
        Thread.sleep(1000);

        OutputMetaPool pool = new OutputMetaPool(8);

        Thread.sleep(10);
        assert pool.registerCxtStruct("id1", "name") == 2 + 1;
        Thread.sleep(10);
        assert pool.registerCxtStruct("id2", "name") == 2 * 2 + 1;
        Thread.sleep(10);
        assert pool.registerCxtStruct("id3", "name") == 3 * 2 + 1;
        Thread.sleep(10);
        assert pool.registerCxtStruct("id4", "name") == 4 * 2 + 1;
        Thread.sleep(10);
        assert pool.registerCxtStruct("id5", "name") == 5 * 2 + 1;
        Thread.sleep(10);
        assert pool.registerCxtStruct("id1", "name") == 2 + 1;
        Thread.sleep(10);

        assert pool.registerCxtStruct("id6", "name") == 6 * 2 + 1;
        Thread.sleep(10);
        assert pool.registerCxtStruct("id7", "name") == 7 * 2 + 1;
        Thread.sleep(10);
        assert pool.registerCxtStruct("id8", "name") == 8 * 2 + 1;
        Thread.sleep(10);
        assert pool.registerCxtStruct("id9", "name") == 9 * 2 + 1;
        Thread.sleep(10);
        assert pool.registerCxtStruct("id0", "name") == 10 * 2 + 1;
        Thread.sleep(10);

        pool.reset(); // 2, 3 released
        assert pool.cxtStructIndex.size() == 8;
        assert pool.registerCxtStruct("id3", "name") == 2 * 2 + 1;
        assert pool.registerCxtStruct("id2", "name") == 3 * 2 + 1;

        assert pool.registerCxtStruct("id5", "name") == 5 * 2 + 1;
        Thread.sleep(10);
        assert pool.registerCxtStruct("id10", "name") == 11 * 2 + 1;
        Thread.sleep(10);
        assert pool.registerCxtStruct("id11", "name") == 12 * 2 + 1;
        Thread.sleep(10);

        pool.reset(); // 1, 4 released
        assert pool.registerCxtStruct("id4", "name") == 2 + 1;
        assert pool.registerCxtStruct("id1", "name") == 4 * 2 + 1;
    }

}
