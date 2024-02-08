package org.choongang.commons.validators;

public interface Validator<T> {
  void check(T t);
}