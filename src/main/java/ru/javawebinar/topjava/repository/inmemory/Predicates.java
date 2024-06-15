package ru.javawebinar.topjava.repository.inmemory;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.Value;
import ru.javawebinar.topjava.model.AbstractBaseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Value
public class Predicates<T extends AbstractBaseEntity> {
    public static<T extends AbstractBaseEntity> Predicates<T> builder(Class<T> clazz) {
        return new Predicates<>();
    }

    List<Predicate<T>> predicates = new ArrayList<>();

    public <K> Predicates<T> add(K object, Function<K, Predicate<T>> function){
        if (object != null) {
            predicates.add(function.apply(object));
        }
        return this;
    }

    public Predicates<T> build(){
        return new Predicates<>();
    }
}
