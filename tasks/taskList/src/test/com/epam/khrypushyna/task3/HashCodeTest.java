package com.epam.khrypushyna.task3;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import static junit.framework.Assert.assertEquals;

public class HashCodeTest {

    HashMap<VendorCodeStringLength, String> hashMap1;
    HashMap<VendorCodeStringSymbols, String> hashMap2;

    HashMap<VendorCodeStringLength, String> hashMap3;
    HashMap<VendorCodeStringSymbols, String> hashMap4;

    LinkedHashMap<VendorCodeStringLength, String> linkedHashMap1;
    LinkedHashMap<VendorCodeStringSymbols, String> linkedHashMap2;

    LinkedHashMap<VendorCodeStringLength, String> linkedHashMap3;
    LinkedHashMap<VendorCodeStringSymbols, String> linkedHashMap4;

    @Before
    public void before() {

        hashMap1 = new HashMap<>();
        hashMap2 = new HashMap<>();
        hashMap3 = new HashMap<>();
        hashMap4 = new HashMap<>();

        linkedHashMap1 = new LinkedHashMap<>();
        linkedHashMap2 = new LinkedHashMap<>();
        linkedHashMap3 = new LinkedHashMap<>();
        linkedHashMap4 = new LinkedHashMap<>();
    }

    @Test
    public void shouldIterateHashMapUsingStringLengthHashCode(){
        hashMap1.put(new VendorCodeStringLength("ssss"), "WWW");
        hashMap1.put(new VendorCodeStringLength("ssssss"), "EEE");
        hashMap1.put(new VendorCodeStringLength("ss"), "LLL");
        hashMap1.put(new VendorCodeStringLength("s"), "MMM");

        Iterator it =  hashMap1.entrySet().iterator();
        Map.Entry pair1 = (Map.Entry) it.next();
        assertEquals("MMM", pair1.getValue());

        it.next();
        Map.Entry pair2 = (Map.Entry) it.next();
        assertEquals("WWW", pair2.getValue());
    }

    @Test
    public void shouldIterateHashMapUsingStringSymbolsHashCode() {
        hashMap2.put(new VendorCodeStringSymbols("sfdm"), "NNN");
        hashMap2.put(new VendorCodeStringSymbols("mncd"), "BBB");
        hashMap2.put(new VendorCodeStringSymbols("dsdd"), "OOO");
        hashMap2.put(new VendorCodeStringSymbols("wdwf"), "CCC");

        Iterator it =  hashMap2.entrySet().iterator();
        Map.Entry pair1 = (Map.Entry) it.next();
        assertEquals("BBB", pair1.getValue());

        it.next();
        Map.Entry pair2 = (Map.Entry) it.next();
        assertEquals("NNN", pair2.getValue());
    }

    @Test
    public void shouldIterateHashMapUsingStringLengthHashCodeWhenStringLengthAreEquals(){

        hashMap3.put(new VendorCodeStringLength("eee"), "YYY");
        hashMap3.put(new VendorCodeStringLength("ttt"), "KKK");
        hashMap3.put(new VendorCodeStringLength("iii"), "MMM");
        hashMap3.put(new VendorCodeStringLength("ooo"), "EEE");

        Iterator it =  hashMap3.entrySet().iterator();
        Map.Entry pair1 = (Map.Entry) it.next();
        assertEquals("YYY", pair1.getValue());

        it.next();
        Map.Entry pair2 = (Map.Entry) it.next();
        assertEquals("MMM", pair2.getValue());
    }

    @Test
    public void shouldPutOnlyLastElementWhenKeysAreEquals() {
        hashMap4.put(new VendorCodeStringSymbols("gggg"), "LLL");
        hashMap4.put(new VendorCodeStringSymbols("gggg"), "PPP");
        hashMap4.put(new VendorCodeStringSymbols("gggg"), "KKK");
        hashMap4.put(new VendorCodeStringSymbols("gggg"), "RRR");

        assertEquals(1, hashMap4.size());
    }

    @Test
    public void shouldIterateHashMapUsingStringSymbolsWhenKeysSumIsEquals() {
        hashMap4.put(new VendorCodeStringSymbols("abcd"), "LLL");
        hashMap4.put(new VendorCodeStringSymbols("bcad"), "PPP");
        hashMap4.put(new VendorCodeStringSymbols("acbd"), "KKK");
        hashMap4.put(new VendorCodeStringSymbols("abdc"), "RRR");

        Iterator it =  hashMap4.entrySet().iterator();
        Map.Entry pair1 = (Map.Entry) it.next();
        assertEquals("LLL", pair1.getValue());

        it.next();
        Map.Entry pair2 = (Map.Entry) it.next();
        assertEquals("KKK", pair2.getValue());
    }

    @Test
    public void shouldIterateLinkedHashMapUsingStringLengthHashCode(){
        linkedHashMap1.put(new VendorCodeStringLength("xxxx"), "WWW");
        linkedHashMap1.put(new VendorCodeStringLength("xxxxxx"), "EEE");
        linkedHashMap1.put(new VendorCodeStringLength("xx"), "LLL");
        linkedHashMap1.put(new VendorCodeStringLength("x"), "MMM");

        Iterator it =  linkedHashMap1.entrySet().iterator();
        Map.Entry pair1 = (Map.Entry) it.next();
        assertEquals("WWW", pair1.getValue());

        it.next();
        Map.Entry pair2 = (Map.Entry) it.next();
        assertEquals("LLL", pair2.getValue());
    }

    @Test
    public void shouldIterateLinkedHashMapUsingStringSymbolsHashCode() {
        linkedHashMap2.put(new VendorCodeStringSymbols("sfdm"), "NNN");
        linkedHashMap2.put(new VendorCodeStringSymbols("mncd"), "BBB");
        linkedHashMap2.put(new VendorCodeStringSymbols("dsdd"), "OOO");
        linkedHashMap2.put(new VendorCodeStringSymbols("wdwf"), "CCC");

        Iterator it =  linkedHashMap2.entrySet().iterator();
        Map.Entry pair1 = (Map.Entry) it.next();
        assertEquals("NNN", pair1.getValue());

        it.next();
        Map.Entry pair2 = (Map.Entry) it.next();
        assertEquals("OOO", pair2.getValue());
    }

    @Test
    public void shouldIterateLinkedHashMapUsingStringLengthHashCodeWhenStringLengthAreEquals(){

        linkedHashMap3.put(new VendorCodeStringLength("xxx"), "YYY");
        linkedHashMap3.put(new VendorCodeStringLength("kkk"), "KKK");
        linkedHashMap3.put(new VendorCodeStringLength("ggg"), "MMM");
        linkedHashMap3.put(new VendorCodeStringLength("ooo"), "EEE");

        Iterator it =  linkedHashMap3.entrySet().iterator();
        Map.Entry pair1 = (Map.Entry) it.next();
        assertEquals("YYY", pair1.getValue());

        it.next();
        Map.Entry pair2 = (Map.Entry) it.next();
        assertEquals("MMM", pair2.getValue());
    }

    @Test
    public void shouldIterateLinkedHashMapUsingStringSymbolsWhenKeysSumIsEquals() {
        linkedHashMap4.put(new VendorCodeStringSymbols("qwer"), "LLL");
        linkedHashMap4.put(new VendorCodeStringSymbols("rewq"), "PPP");
        linkedHashMap4.put(new VendorCodeStringSymbols("weqr"), "KKK");
        linkedHashMap4.put(new VendorCodeStringSymbols("rweq"), "RRR");

        Iterator it =  linkedHashMap4.entrySet().iterator();
        Map.Entry pair1 = (Map.Entry) it.next();
        assertEquals("LLL", pair1.getValue());

        it.next();
        Map.Entry pair2 = (Map.Entry) it.next();
        assertEquals("KKK", pair2.getValue());
    }
}
