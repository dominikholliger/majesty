package leberkaes.appClasses;

import leberkaes.jat2.ServiceLocator;
import leberkaes.abstractClasses.Model;

/**
 * Copyright 2015, FHNW, Prof. Dr. Brad Richards. All rights reserved. This code
 * is licensed under the terms of the BSD 3-clause license (see the file
 * license.txt).
 *
 * @author Dominik Holliger
 */
public class HighScore_Model extends Model {
    ServiceLocator serviceLocator;
    private int value;

    public HighScore_Model() {
        serviceLocator = ServiceLocator.getServiceLocator();
        serviceLocator.getLogger().info("Application model initialized");
    }


}
