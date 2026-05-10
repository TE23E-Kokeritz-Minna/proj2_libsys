package jk.registry;

import java.util.ArrayList;
import java.util.HashSet;

public abstract class Register<T>{
    public abstract void add(T item);
    public abstract void add(HashSet<? extends T> list);

    public abstract void remove(T item);

    public abstract void writeAll();

    public abstract ArrayList<T> search (String criteria);


}