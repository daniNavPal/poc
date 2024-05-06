package com.daninavarro.policy.application.port.input;

public interface UseCase<I, O> {
  O execute(I input);
}