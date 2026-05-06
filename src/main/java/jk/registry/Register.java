package jk.registry;

import java.util.ArrayList;
import java.util.HashSet;

public abstract class Register<T>{

    //TODO properly better to have sets and not arraylist actually, should fix that maybe
    public abstract void add(T item);
    public abstract void add(HashSet<? extends T> list);

    public abstract void remove(T item);

    public abstract void writeAll();

    public abstract T search (String criteria);

}