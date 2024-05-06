package com.daninavarro.customer.customer.applicartion.port.input;

public interface UseCase<I, O> {
  O execute(I input);
}