package com.github.sisyphsu.nakedata.convertor.codec.lang;

import com.github.sisyphsu.nakedata.convertor.codec.Codec;

/**
 * Short's codec
 *
 * @author sulin
 * @since 2019-05-13 18:12:30
 */
public class ShortCodec extends Codec {

    /**
     * Convert Long to Short
     *
     * @param l Long
     * @return Short
     */
    public Short toShort(Long l) {
        return l == null ? null : l.shortValue();
    }

    /**
     * Convert Short to Long
     *
     * @param s Short
     * @return Long
     */
    public Long toLong(Short s) {
        return s == null ? null : s.longValue();
    }

    /**
     * Convert Short[] to short[]
     *
     * @param arr short[]
     * @return Short[]
     */
    public Short[] convert(short[] arr) {
        if (arr == null) {
            return null;
        }
        Short[] result = new Short[arr.length];
        for (int i = 0; i < arr.length; i++) {
            result[i] = arr[i];
        }
        return result;
    }

    /**
     * Convert Short[] to short[]
     *
     * @param arr Short[]
     * @return short[]
     */
    public short[] convert(Short[] arr) {
        if (arr == null) {
            return null;
        }
        short[] result = new short[arr.length];
        for (int i = 0; i < arr.length; i++) {
            result[i] = arr[i];
        }
        return result;
    }

}
