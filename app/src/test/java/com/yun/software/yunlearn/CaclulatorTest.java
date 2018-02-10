package com.yun.software.yunlearn;

import android.util.Log;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by yanliang
 * on 2017/12/22 12:42
 */
public class CaclulatorTest {
    Caclulator mCaclulator;
    @Before
    public void setUp() throws Exception {
           mCaclulator=new Caclulator();
    }
    @Test
    public void testSum() throws Exception {
        //expected: 6, sum of 1 and 5
        Log.i("http",mCaclulator.sum(3,4)+"sum");
    }
    @Test
    public void sum() throws Exception {

    }

    @Test
    public void substract() throws Exception {

    }

    @Test
    public void divide() throws Exception {

    }

    @Test
    public void multiply() throws Exception {

    }

}