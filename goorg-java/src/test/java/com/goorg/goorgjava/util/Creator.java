package com.goorg.goorgjava.util;

import java.util.List;

public interface Creator<E> {
    E createValidItem();
    List<E> createValidItemsList();
}
