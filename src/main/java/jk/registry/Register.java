package jk.registry;

import java.util.ArrayList;

public abstract class Register<T>{

    public abstract void add(T item);

    public abstract void remove(T item);

    public abstract void writeAll();

    public abstract T search (String criteria);

}