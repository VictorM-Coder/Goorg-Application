package com.goorg.goorgjava.util.creator;

import java.util.List;

public interface Creator<E> {
    E createValidItem();
    List<E> createValidItemsList();
}
