package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    static void main() {
        LOGGER.info("Hello and welcome!");

        for (int i = 1; i <= 5; i++) {
            LOGGER.info("i = {}", i);
        }
    }
}
