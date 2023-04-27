package service;

import java.util.List;

public interface AppService<T> {
    T create(T t);
    List<T> search(String s);
    boolean delete(T t);
}
