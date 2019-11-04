package com.github.sisyphsu.canoe.benchmark;

import com.github.sisyphsu.canoe.Canoe;
import com.github.sisyphsu.canoe.convertor.ConverterPipeline;
import com.github.sisyphsu.canoe.node.Node;
import org.apache.commons.lang3.RandomUtils;
import org.openjdk.jmh.annotations.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author sulin
 * @since 2019-10-30 20:21:38
 */
@Warmup(iterations = 2, time = 2)
@Fork(2)
@Measurement(iterations = 3, time = 3)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class TestBenchmark {

    static ConverterPipeline pipeline = Canoe.CODEC.getPipeline(Date.class, Node.class);
    static Date              date     = new Date();

    static Object[] arr  = new Object[1024];
    static List     list = new ArrayList<>();

    static {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = RandomUtils.nextDouble();
            list.add(arr[i]);
        }
    }

    @Benchmark
    public void test() {
//        schema.reset(); // 4ns
//        Canoe.CODEC.toXType(Node.class); // 3ns

//        pipeline.convert(date, Canoe.CODEC.toXType(Node.class)); // 44ns
//        VarintNode.valueOf(date.getTime()); // 1.3ns
        Class<?> prevCls = null;
        Class<?> currCls = null;
        int i = 0;
        // 674ns
        for (Object o : list) {
            currCls = o == null ? null : o.getClass();
            if (prevCls == currCls) {
                i++;
            }
            prevCls = currCls;
        }
    }

}
