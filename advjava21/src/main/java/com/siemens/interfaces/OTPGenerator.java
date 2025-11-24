package com.siemens.interfaces;
@FunctionalInterface
public interface OTPGenerator {
    int getOTP(int min, int max);
}
