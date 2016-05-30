package com.example.paciu.belmondo.Utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by paciu on 14.05.2016.
 */
public class IteratorUtils {
    public static <T> List<T> toList(Iterator<T> iterator){
        List<T> objects = new ArrayList<T>();
        while(iterator.hasNext()){
            objects.add(iterator.next());
        }
        return objects;
    }
}
